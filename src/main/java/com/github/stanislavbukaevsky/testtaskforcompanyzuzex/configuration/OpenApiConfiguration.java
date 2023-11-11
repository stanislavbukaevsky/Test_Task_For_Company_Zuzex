package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для настройки и аутентификации в Swagger
 */
@Configuration
@OpenAPIDefinition(info =
@Info(title = "Приложение для регистрации жильцов в жилых домах", description = "Web интерфейс для регистрации жильцов в домах", contact =
@Contact(name = "Букаевский Станислав", email = "stanislavi18061989@gmail.com"))
)
@SecurityScheme(name = "JWT", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class OpenApiConfiguration {
}
