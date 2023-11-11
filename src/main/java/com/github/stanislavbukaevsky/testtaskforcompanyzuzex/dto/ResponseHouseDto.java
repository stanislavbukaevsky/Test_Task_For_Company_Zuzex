package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Класс-DTO для ответа пользователю с информацией о домах
 */
@Data
@Schema(description = "Объект создания ответа пользователю")
public class ResponseHouseDto {
    @Schema(description = "Уникальный идентификатор дома")
    private Long id;
    @Schema(description = "Адрес дома")
    private String address;
}
