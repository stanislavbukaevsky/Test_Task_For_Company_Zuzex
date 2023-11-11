package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service.impl;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.RequestHouseDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseHouseDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseHouseToUserDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.House;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.User;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.enums.Status;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.exception.HouseNotFoundException;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.exception.StatusForbiddenException;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.exception.UserNotFoundException;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.mapper.HouseMapper;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.repository.HouseRepository;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.repository.UserRepository;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.security.UserSecurity;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant.ExceptionTextMessageConstant.*;
import static com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant.LoggerTextMessageConstant.*;

/**
 * Сервис-класс с бизнес-логикой для всех домов.
 * Реализует интерфейс {@link HouseService}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final UserSecurity userSecurity;
    private final HouseMapper houseMapper;

    /**
     * Реализация метода для добавления новых домов в приложение
     *
     * @param requestHouseDto запрос от пользователя
     * @return Возвращает DTO с ответом пользователю
     */
    @Override
    public ResponseHouseDto addHouse(RequestHouseDto requestHouseDto) {
        House house = houseMapper.toEntity(requestHouseDto);
        House result = houseRepository.save(house);
        log.info(ADD_HOUSE_MESSAGE_LOGGER_SERVICE, requestHouseDto);
        return houseMapper.toResponseHouseDto(result);
    }

    /**
     * Реализация метода для изменения информации о доме
     *
     * @param id              уникальный идентификатор дома
     * @param requestHouseDto запрос от пользователя
     * @return Возвращает DTO с ответом пользователю
     */
    @Override
    public ResponseHouseDto updateHouse(Long id, RequestHouseDto requestHouseDto) {
        House house = findHouseById(id);
        house.setAddress(requestHouseDto.getAddress());
        House result = houseRepository.save(house);
        log.info(UPDATE_HOUSE_MESSAGE_LOGGER_SERVICE, id, requestHouseDto);
        return houseMapper.toResponseHouseDto(result);
    }

    /**
     * Реализация метода для поиска домов
     *
     * @param id уникальный идентификатор дома
     * @return Возвращает DTO с ответом пользователю
     */
    @Override
    public ResponseHouseDto findHouse(Long id) {
        House house = findHouseById(id);
        log.info(FIND_HOUSE_MESSAGE_LOGGER_SERVICE, id);
        return houseMapper.toResponseHouseDto(house);
    }

    /**
     * Реализация метода для удаления домов
     *
     * @param id уникальный идентификатор дома
     * @return Возвращает ответ пользователю
     */
    @Override
    public String deleteHouse(Long id) {
        House house = findHouseById(id);
        houseRepository.delete(house);
        log.info(DELETE_HOUSE_MESSAGE_LOGGER_SERVICE, id);
        return "Дом успешно удален из базы данных";
    }

    /**
     * Реализация метода для добавления жильцов в дома
     *
     * @param idHouse уникальный идентификатор дома
     * @param idUser  уникальный идентификатор добавляемого пользователя
     * @return Возвращает DTO с ответом пользователю
     */
    @Override
    public ResponseHouseToUserDto addUserToHouse(Long idHouse, Long idUser) {
        User currentUser = userRepository.findUserByEmail(userSecurity.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException(USER_NOT_FOUND_EXCEPTION));
        House house = findHouseById(idHouse);
        User addUser = userRepository.findById(idUser).orElseThrow(() ->
                new UserNotFoundException(USER_NOT_FOUND_BY_ID_EXCEPTION));

        if (currentUser.getStatus().equals(Status.OWNER) && addUser.getStatus().equals(Status.TENANT)) {
            addUser.setHouse(house);
            User result = userRepository.save(addUser);
            ResponseHouseToUserDto responseHouseToUserDto = new ResponseHouseToUserDto();
            responseHouseToUserDto.setIdUser(result.getId());
            responseHouseToUserDto.setName(result.getName());
            responseHouseToUserDto.setAge(result.getAge());
            responseHouseToUserDto.setEmail(result.getEmail());
            responseHouseToUserDto.setPassword(result.getPassword());
            responseHouseToUserDto.setStatus(result.getStatus());
            responseHouseToUserDto.setAddress(result.getHouse().getAddress());
            responseHouseToUserDto.setDescription("Вы добавили новго жильца с электронной почтой " + result.getEmail() + " по адресу " + result.getHouse().getAddress());
            log.info(ADD_USER_TO_HOUSE_MESSAGE_LOGGER_SERVICE, idHouse, idUser);
            return responseHouseToUserDto;
        }
        throw new StatusForbiddenException(STATUS_FORBIDDEN_EXCEPTION_2);
    }

    private House findHouseById(Long id) {
        return houseRepository.findById(id).orElseThrow(() ->
                new HouseNotFoundException(HOUSE_NOT_FOUND_EXCEPTION));
    }

}
