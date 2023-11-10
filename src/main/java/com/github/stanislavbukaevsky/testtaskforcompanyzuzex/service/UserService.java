package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.CreateUserRequestDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.CreateUserResponseDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Сервис-интерфейс для работы со всеми пользователями дома.
 * В этом интерфейсе прописана только сигнатура методов без реализации
 */
public interface UserService {
    /**
     * Сигнатура метода для создания новых пользователей на платформе
     *
     * @param createUserRequestDto класс-DTO для создания пользователя на платформе
     * @return Возвращает DTO с информацией о созданном пользователе
     */
    CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto);

    User updateUser(User user);

    void deleteUser(String email);

    /**
     * Сигнатура метода для поиска пользователей по его электронной почте
     *
     * @param email электронная почта пользователя
     * @return Возвращает найденного пользователя или выбрасывает исключение {@link UsernameNotFoundException}, если такого не существует
     */
    User findUserByEmail(String email);
}
