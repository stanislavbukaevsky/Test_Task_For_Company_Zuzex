package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Класс-DTO для ответа пользователю с полной информацией
 */
@Data
@Schema(description = "Объект создания ответа пользователю с полной информацией")
public class ResponseUserFullInfoDto {
    @Schema(description = "Уникальный идентификатор пользователя")
    private Long id;
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
    @Schema(description = "Access токен пользователя")
    private String accessToken;
}
