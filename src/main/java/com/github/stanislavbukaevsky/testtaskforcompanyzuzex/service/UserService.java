package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.RequestUserDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseUserDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseUserFullInfoDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.enums.Status;

/**
 * Сервис-интерфейс для работы со всеми пользователями дома.
 * В этом интерфейсе прописана только сигнатура методов без реализации
 */
public interface UserService {
    /**
     * Сигнатура метода для создания новых пользователей на платформе
     *
     * @param requestUserDto класс-DTO для создания пользователя на платформе
     * @return Возвращает DTO с информацией о созданном пользователе
     */
    ResponseUserFullInfoDto createUser(RequestUserDto requestUserDto);

    /**
     * Сигнатура метода для измения информации о пользователе
     *
     * @param id             идентификатор пользователя
     * @param requestUserDto класс-DTO для запроса от пользователя
     * @return Возвращает измененную информацию о пользователе
     */

    ResponseUserDto updateUser(Long id, RequestUserDto requestUserDto);

    /**
     * Сигнатура метода для измения статуса пользователе
     *
     * @param id     идентификатор пользователя
     * @param status статус пользователя
     * @return Возвращает измененную информацию о пользователе
     */
    ResponseUserDto updateStatusUser(Long id, Status status);

    /**
     * Сигнатура метода для поиска пользователей по его электронной почте
     *
     * @param email электронная почта пользователя
     * @return Возвращает найденного пользователя
     */
    ResponseUserDto findUser(String email);

    /**
     * Сигнатура метода для удаления пользователей
     *
     * @param id уникальный идентификатор удаляемого пользователя
     * @return Возвращает сообщение о выполнении действия
     */
    String deleteUser(Long id);

    /**
     * Сигнатура метода для получения нового access-токена для зарегистрированного пользователя
     *
     * @param id уникальный идентификатор пользователя
     * @return Возвращает ответ с личной информацией о пользователе с новым, сгенерированным access-токеном
     */
    ResponseUserFullInfoDto generateAccessToken(Long id);
}
