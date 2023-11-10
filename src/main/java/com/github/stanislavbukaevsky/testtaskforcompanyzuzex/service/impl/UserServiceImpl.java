package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service.impl;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.CreateUserRequestDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.CreateUserResponseDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.User;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.enums.Status;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.exception.StatusForbiddenException;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.exception.UserNameAlreadyExistsException;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.mapper.UserMapper;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.repository.UserRepository;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service.UserService;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.token.TokenDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant.ExceptionTextMessageConstant.*;
import static com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant.LoggerTextMessageConstant.FIND_USER_BY_EMAIL_MESSAGE_LOGGER_SERVICE;

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

    /**
     * Реализация метода для создания новых пользователей на платформе
     *
     * @param createUserRequestDto класс-DTO для создания пользователя на платформе
     * @return Возвращает DTO с информацией о созданном пользователе
     */
    @Override
    public CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        Boolean checkUser = userRepository.existsUserByEmail(createUserRequestDto.getEmail());

        if (checkUser) {
            throw new UserNameAlreadyExistsException(CREATE_USER_MESSAGE_EXCEPTION_SERVICE
                    + createUserRequestDto.getEmail() + CREATE_USER_MESSAGE_EXCEPTION_SERVICE_2);
        } else {
            User user = userMapper.toCreateUserDto(createUserRequestDto);
            user.setPassword(passwordEncoder.encode(createUserRequestDto.getPassword()));
            user.setStatus(Status.TENANT);
            User result = userRepository.save(user);
            String accessToken = tokenDetailsService.generateAccessToken(result);
            CreateUserResponseDto createUserResponseDto = userMapper.toEntityUser(result);
            createUserResponseDto.setAccessToken(accessToken);
            return createUserResponseDto;
        }
    }

    @Override
    public User updateUser(User user) {
        String email = user.getEmail();
        User findUser = findUserByEmail(email);
        return userRepository.save(findUser);
    }

    @Override
    public void deleteUser(String email) {
        User user = findUserByEmail(email);
        if (user.getStatus().equals(Status.OWNER)) {
            userRepository.delete(user);
        }
        throw new StatusForbiddenException("У вас нет прав доступа на это действие!");
    }

    /**
     * Реализация метода для поиска пользователей по его электронной почте
     *
     * @param email электронная почта пользователя
     * @return Возвращает найденного пользователя или выбрасывает исключение {@link UsernameNotFoundException}, если такого не существует
     */
    @Override
    public User findUserByEmail(String email) {
        log.info(FIND_USER_BY_EMAIL_MESSAGE_LOGGER_SERVICE, email);
        return userRepository.findUserByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(USER_NOT_FOUND_EXCEPTION));
    }
}
