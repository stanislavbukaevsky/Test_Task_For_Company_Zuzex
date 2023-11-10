package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.mapper;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.CreateUserRequestDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.CreateUserResponseDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    @Mapping(ignore = true, target = "password")
    User toCreateUserDto(CreateUserRequestDto createUserRequestDto);

    CreateUserResponseDto toEntityUser(User user);
}
