package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Объект создания пользователя в приложении")
public class CreateUserRequestDto {
    @NotEmpty(message = "Поле имени пользователя не должно быть пустым!")
    @Size(min = 6, max = 64, message = "Имя должно содержать от 6 до 64 символов!")
    @Schema(description = "Имя пользователя")
    private String name;
    @NotNull(message = "Поле возраста не должно быть пустым!")
    @Positive(message = "Поле возраста должно содержать только положительные цифры")
    @Schema(description = "Возраст пользователя")
    private Integer age;
    @NotEmpty(message = "Поле электронной почты не должно быть пустым!")
    @Size(min = 6, max = 64, message = "Электронная почта должна содержать от 6 до 64 символов!")
    @Schema(description = "Электронная почта пользователя при создании")
    private String email;
    @NotEmpty(message = "Поле пароля не должно быть пустым!")
    @Schema(description = "Пароль пользователя")
    private String password;
    private String address;
}
