package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.mapper;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.RequestUserDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseUserDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseUserFullInfoDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Маппер-интерфейс, который преобразует информацию о пользователе в DTO/Entity
 */
@Mapper
public interface UserMapper {
    @Mapping(ignore = true, target = "password")
    User toRequestUserDto(RequestUserDto requestUserDto);

    ResponseUserFullInfoDto toEntityUser(User user);

    ResponseUserDto toResponseUserDto(User user);
}
