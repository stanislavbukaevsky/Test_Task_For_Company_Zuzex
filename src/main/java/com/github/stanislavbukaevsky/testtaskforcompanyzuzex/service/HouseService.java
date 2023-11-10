package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.House;

/**
 * Сервис-интерфейс для работы со всеми домами.
 * В этом интерфейсе прописана только сигнатура методов без реализации
 */
public interface HouseService {
    House addHouse(House house);

    String addUserToHouse(String email, String address);

    House findHouseByAddress(String address);
}
