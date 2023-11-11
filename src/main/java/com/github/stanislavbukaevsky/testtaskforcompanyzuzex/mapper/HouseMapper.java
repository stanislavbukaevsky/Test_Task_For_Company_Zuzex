package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.mapper;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.RequestHouseDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseHouseDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.House;
import org.mapstruct.Mapper;

/**
 * Маппер-интерфейс, который преобразует информацию о доме в DTO/Entity
 */
@Mapper
public interface HouseMapper {
    House toEntity(RequestHouseDto requestHouseDto);

    ResponseHouseDto toResponseHouseDto(House house);
}
