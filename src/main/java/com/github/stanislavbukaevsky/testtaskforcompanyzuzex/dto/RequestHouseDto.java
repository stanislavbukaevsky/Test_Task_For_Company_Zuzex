package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Класс-DTO для запроса пользователя на действие с домами
 */
@Data
@Schema(description = "Объект запроса от пользователя на действие с домами в приложении")
public class RequestHouseDto {
    @NotEmpty(message = "Поле имени адреса не должно быть пустым!")
    @Size(min = 12, max = 128, message = "Адрес должен содержать от 12 до 128 символов!")
    @Schema(description = "Адрес дома")
    private String address;
}
