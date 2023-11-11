package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.repository;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Интерфейс-репозиторий для работы с методами всех пользователей дома.
 * Наследуется от интерфейса {@link JpaRepository}. Параметры: <br>
 * {@link User} - класс-сущность <br>
 * {@link Long} - идентификатор <br>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Boolean existsUserByEmail(String email);
}
