package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Класс-DTO для ответа пользователю с информацией о заселенном пользователе и доме
 */
@Data
@Schema(description = "Объект создания ответа пользователю")
public class ResponseHouseToUserDto {
    @Schema(description = "Уникальный идентификатор пользователя")
    private Long idUser;
    @Schema(description = "Имя пользователя")
    private String name;
    @Schema(description = "Возраст пользователя")
    private Integer age;
    @Schema(description = "Электронная почта пользователя при создании")
    private String email;
    @Schema(description = "Пароль пользователя")
    private String password;
    @Schema(description = "Статус пользователя")
    private Status status;
    @Schema(description = "Адрес дома")
    private String address;
    @Schema(description = "Описание совершенного действия")
    private String description;
}
