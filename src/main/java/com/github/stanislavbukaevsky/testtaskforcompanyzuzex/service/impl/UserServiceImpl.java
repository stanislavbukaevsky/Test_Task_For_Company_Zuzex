package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service.impl;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.RequestUserDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseUserDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseUserFullInfoDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.User;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.enums.Status;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.exception.StatusForbiddenException;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.exception.UserNameAlreadyExistsException;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.exception.UserNameBadRequestException;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.exception.UserNotFoundException;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.mapper.UserMapper;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.repository.UserRepository;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.security.UserSecurity;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service.UserService;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.token.TokenDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant.ExceptionTextMessageConstant.*;
import static com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant.LoggerTextMessageConstant.*;

/**
 * Сервис-класс с бизнес-логикой для всех пользователей домов.
 * Реализует интерфейс {@link UserService}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenDetailsService tokenDetailsService;
    private final UserSecurity userSecurity;

    /**
     * Реализация метода для создания новых пользователей на платформе
     *
     * @param requestUserDto класс-DTO для создания пользователя на платформе
     * @return Возвращает DTO с информацией о созданном пользователе
     */
    @Override
    public ResponseUserFullInfoDto createUser(RequestUserDto requestUserDto) {
        Boolean checkUser = userRepository.existsUserByEmail(requestUserDto.getEmail());

        if (checkUser) {
            throw new UserNameAlreadyExistsException(CREATE_USER_MESSAGE_EXCEPTION_SERVICE
                    + requestUserDto.getEmail() + CREATE_USER_MESSAGE_EXCEPTION_SERVICE_2);
        } else {
            User user = userMapper.toRequestUserDto(requestUserDto);
            user.setPassword(passwordEncoder.encode(requestUserDto.getPassword()));
            user.setStatus(Status.TENANT);
            User result = userRepository.save(user);
            log.info(CREATE_USER_MESSAGE_LOGGER_SERVICE, result);
            return generateResponseUserWithAccessToken(result);
        }
    }

    /**
     * Реализация метода для измения информации о пользователе
     *
     * @param id             идентификатор пользователя
     * @param requestUserDto класс-DTO для запроса от пользователя
     * @return Возвращает измененную информацию о пользователе
     */
    @Override
    public ResponseUserDto updateUser(Long id, RequestUserDto requestUserDto) {
        User user = findUserById(id);
        if (user.getEmail().equals(userSecurity.getUsername())) {
            user.setName(requestUserDto.getName());
            user.setAge(requestUserDto.getAge());
            user.setEmail(requestUserDto.getEmail());
            user.setPassword(requestUserDto.getPassword());
            User result = userRepository.save(user);
            log.info(UPDATE_USER_MESSAGE_LOGGER_SERVICE, id);
            return generateResponseUser(result);
        }
        throw new UserNameBadRequestException(USER_NAME_BAD_REQUEST_EXCEPTION);
    }

    /**
     * Реализация метода для измения статуса пользователя
     *
     * @param id     идентификатор пользователя
     * @param status статус пользователя
     * @return Возвращает измененную информацию о пользователе
     */
    @Override
    public ResponseUserDto updateStatusUser(Long id, Status status) {
        User user = findUserById(id);
        user.setStatus(status);
        User result = userRepository.save(user);
        log.info(UPDATE_STATUS_USER_MESSAGE_LOGGER_SERVICE, id);
        return generateResponseUser(result);
    }

    /**
     * Реализация метода для поиска пользователей по его электронной почте
     *
     * @param email электронная почта пользователя
     * @return Возвращает найденного пользователя
     */
    @Override
    public ResponseUserDto findUser(String email) {
        log.info(FIND_USER_BY_EMAIL_MESSAGE_LOGGER_SERVICE, email);
        User user = findUserByEmail(email);
        log.info(FIND_USER_MESSAGE_LOGGER_SERVICE, email);
        return generateResponseUser(user);
    }

    /**
     * Реализация метода для удаления пользователей
     *
     * @param id уникальный идентификатор удаляемого пользователя
     * @return Возвращает сообщение о выполнении действия
     */
    @Override
    public String deleteUser(Long id) {
        User currentUser = findUserByEmail(userSecurity.getUsername());
        User user = findUserById(id);
        if (currentUser.getStatus().equals(Status.OWNER)) {
            userRepository.delete(user);
            log.info(DELETE_USER_MESSAGE_LOGGER_SERVICE);
            return "Пользователь успешно удален из базы данных";
        }
        throw new StatusForbiddenException(STATUS_FORBIDDEN_EXCEPTION);
    }

    /**
     * Реализация метода для получения нового access-токена для зарегистрированного пользователя
     *
     * @param id уникальный идентификатор пользователя
     * @return Возвращает ответ с личной информацией о пользователе с новым, сгенерированным access-токеном
     */
    @Override
    public ResponseUserFullInfoDto generateAccessToken(Long id) {
        User user = findUserById(id);
        log.info(GENERATE_ACCESS_TOKEN_MESSAGE_LOGGER_SERVICE, id);
        return generateResponseUserWithAccessToken(user);
    }

    private ResponseUserFullInfoDto generateResponseUserWithAccessToken(User user) {
        String accessToken = tokenDetailsService.generateAccessToken(user);
        ResponseUserFullInfoDto responseUserFullInfoDto = userMapper.toEntityUser(user);
        responseUserFullInfoDto.setAccessToken(accessToken);
        return responseUserFullInfoDto;
    }

    private ResponseUserDto generateResponseUser(User user) {
        return userMapper.toResponseUserDto(user);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(USER_NOT_FOUND_BY_ID_EXCEPTION));
    }

    private User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(USER_NOT_FOUND_EXCEPTION));
    }
}
