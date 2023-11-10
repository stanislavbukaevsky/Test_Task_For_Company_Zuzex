package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service.impl;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.House;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.User;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.enums.Status;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.exception.AddressNotFoundException;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.exception.StatusForbiddenException;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.repository.HouseRepository;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.security.UserSecurity;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service.HouseService;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервис-класс с бизнес-логикой для всех домов.
 * Реализует интерфейс {@link HouseService}
 */
@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final UserService userService;
    private final UserSecurity userSecurity;

    @Override
    public House addHouse(House house) {
        return houseRepository.save(house);
    }

    @Override
    public String addUserToHouse(String email, String address) {
        User user = userService.findUserByEmail(email);
        House house = findHouseByAddress(address);
        if (userSecurity.getUser().getStatus().equals(Status.OWNER)) {
            house.setUser(user);
            houseRepository.save(house);
            return "Вы добавили жильца по адресу " + house.getAddress();
        }
        throw new StatusForbiddenException("У вас нет доступа на это действие!");
    }

    @Override
    public House findHouseByAddress(String address) {
        return houseRepository.findHouseByAddress(address).orElseThrow(() ->
                new AddressNotFoundException("Это адреса не существует!"));
    }

}
