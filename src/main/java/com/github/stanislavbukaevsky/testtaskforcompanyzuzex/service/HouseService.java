package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.RequestHouseDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseHouseDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseHouseToUserDto;

/**
 * Сервис-интерфейс для работы со всеми домами.
 * В этом интерфейсе прописана только сигнатура методов без реализации
 */
public interface HouseService {
    /**
     * Сигнатура метода для добавления новых домов в приложение
     *
     * @param requestHouseDto запрос от пользователя
     * @return Возвращает DTO с ответом пользователю
     */
    ResponseHouseDto addHouse(RequestHouseDto requestHouseDto);

    /**
     * Сигнатура метода для изменения информации о доме
     *
     * @param id              уникальный идентификатор дома
     * @param requestHouseDto запрос от пользователя
     * @return Возвращает DTO с ответом пользователю
     */
    ResponseHouseDto updateHouse(Long id, RequestHouseDto requestHouseDto);

    /**
     * Сигнатура метода для поиска домов
     *
     * @param id уникальный идентификатор дома
     * @return Возвращает DTO с ответом пользователю
     */
    ResponseHouseDto findHouse(Long id);

    /**
     * Сигнатура метода для удаления домов
     *
     * @param id уникальный идентификатор дома
     * @return Возвращает ответ пользователю
     */
    String deleteHouse(Long id);

    /**
     * Сигнатура метода для добавления жильцов в дома
     *
     * @param idHouse уникальный идентификатор дома
     * @param idUser  уникальный идентификатор добавляемого пользователя
     * @return Возвращает DTO с ответом пользователю
     */
    ResponseHouseToUserDto addUserToHouse(Long idHouse, Long idUser);

}
