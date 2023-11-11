package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.repository;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс-репозиторий для работы с методами всех домов.
 * Наследуется от интерфейса {@link JpaRepository}. Параметры: <br>
 * {@link House} - класс-сущность <br>
 * {@link Long} - идентификатор <br>
 */
@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}
