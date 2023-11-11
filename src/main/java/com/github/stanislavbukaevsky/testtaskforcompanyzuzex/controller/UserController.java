package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.controller;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.RequestUserDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseUserDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseUserFullInfoDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.enums.Status;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant.LoggerTextMessageConstant.*;

/**
 * Класс-контроллер для работы со всеми пользователями
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Работа со всеми пользователями на платформе", description = "Позволяет управлять методами по работе со всеми пользователями на платформе")
public class UserController {
    private final UserService userService;

    /**
     * Этот метод позволяет создать нового пользователя
     *
     * @param requestUserDto класс-DTO для создания пользователя на платформе
     * @return Возвращает созданного пользователя и всю информацию о нем
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Новый пользователь зарегистрирован (OK)",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseUserFullInfoDto.class))),
            @ApiResponse(responseCode = "400", description = "Неккоректный запрос (Bad Request)"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера (Internal Server Error)")
    })
    @Operation(summary = "Метод создания пользователей на платформе", description = "Позволяет создать нового пользователя на платформе")
    @PostMapping("/create")
    public ResponseEntity<ResponseUserFullInfoDto> createUser(@RequestBody @Valid RequestUserDto requestUserDto) {
        ResponseUserFullInfoDto responseUserFullInfoDto = userService.createUser(requestUserDto);
        log.info(CREATE_USER_MESSAGE_LOGGER_CONTROLLER, requestUserDto);
        return ResponseEntity.ok(responseUserFullInfoDto);
    }

    /**
     * Этот метод позволяет изменить информацию о пользователе
     *
     * @param id             идентификатор пользователя
     * @param requestUserDto класс-DTO для запроса от пользователя
     * @return Возвращает измененную информацию о пользователе
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно изменен (OK)",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseUserDto.class))),
            @ApiResponse(responseCode = "400", description = "Неккоректный запрос (Bad Request)"),
            @ApiResponse(responseCode = "401", description = "Неаутентифицированный пользователь (Unauthorized)"),
            @ApiResponse(responseCode = "403", description = "Пользователю запрещен вход на этот ресурс (Forbidden)"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера (Internal Server Error)")
    })
    @Operation(summary = "Метод изменения информации о пользователе", description = "Позволяет изменить информацию о пользователе")
    @PatchMapping("/update/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ResponseUserDto> updateUser(@Parameter(description = "Идентификатор пользователя")
                                                      @PathVariable @Positive Long id,
                                                      @RequestBody @Valid RequestUserDto requestUserDto) {
        ResponseUserDto responseUserDto = userService.updateUser(id, requestUserDto);
        log.info(UPDATE_USER_MESSAGE_LOGGER_CONTROLLER, id);
        return ResponseEntity.ok(responseUserDto);
    }

    /**
     * Этот метод позволяет изменять статус пользователя
     *
     * @param id     идентификатор пользователя
     * @param status статус пользователя
     * @return Возвращает измененную информацию о пользователе
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно изменен (OK)",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseUserDto.class))),
            @ApiResponse(responseCode = "400", description = "Неккоректный запрос (Bad Request)"),
            @ApiResponse(responseCode = "401", description = "Неаутентифицированный пользователь (Unauthorized)"),
            @ApiResponse(responseCode = "403", description = "Пользователю запрещен вход на этот ресурс (Forbidden)"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера (Internal Server Error)")
    })
    @Operation(summary = "Метод изменения статуса пользователя", description = "Позволяет изменить статус пользователя")
    @PostMapping("/update-status/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ResponseUserDto> updateStatusUser(@Parameter(description = "Идентификатор пользователя")
                                                            @PathVariable @Positive Long id,
                                                            @Parameter(description = "Выбор статуса для пользователя")
                                                            @RequestParam Status status) {
        ResponseUserDto responseUserDto = userService.updateStatusUser(id, status);
        log.info(UPDATE_STATUS_USER_MESSAGE_LOGGER_CONTROLLER, id);
        return ResponseEntity.ok(responseUserDto);
    }

    /**
     * Этот метод позволяет получить пользователя и просмотреть информацию о нем
     *
     * @param email электронная почта пользователя
     * @return Возвращает информацию о найденном пользователе
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно найден (OK)",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseUserDto.class))),
            @ApiResponse(responseCode = "400", description = "Неккоректный запрос (Bad Request)"),
            @ApiResponse(responseCode = "401", description = "Неаутентифицированный пользователь (Unauthorized)"),
            @ApiResponse(responseCode = "403", description = "Пользователю запрещен вход на этот ресурс (Forbidden)"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера (Internal Server Error)")
    })
    @Operation(summary = "Метод для поиска и просмотра информации о пользователе",
            description = "Позволяет получить пользователя и просмотреть информацию о нем")
    @GetMapping("/find")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ResponseUserDto> findUser(@Parameter(description = "Электронная почта пользователя") @RequestParam @Email String email) {
        ResponseUserDto responseUserDto = userService.findUser(email);
        log.info(FIND_USER_MESSAGE_LOGGER_CONTROLLER, email);
        return ResponseEntity.ok(responseUserDto);
    }

    /**
     * Этот метод позволяет удалить пользователя с платформы по его идентификатору
     *
     * @param id идентификатор удаляемого пользователя
     * @return Возвращает сообщение о выполнении действия
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно удален (OK)"),
            @ApiResponse(responseCode = "401", description = "Неавторизированный пользователь (Unauthorized)"),
            @ApiResponse(responseCode = "403", description = "Пользователю запрещен вход на этот ресурс (Forbidden)")
    })
    @Operation(summary = "Метод для удаления пользователя", description = "Позволяет удалить пользователя")
    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<String> deleteUser(@Parameter(description = "Идентификатор пользователя") @PathVariable @Positive Long id) {
        String result = userService.deleteUser(id);
        log.info(DELETE_USER_MESSAGE_LOGGER_CONTROLLER);
        return ResponseEntity.ok(result);
    }

    /**
     * Этот метод позволяет получить новый access-токен для зарегистрированного пользователя
     *
     * @param id уникальный идентификатор пользователя
     * @return Возвращает ответ с личной информацией о пользователе с новым, сгенерированным access-токеном
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Новый access-токен успешно выдан (OK)",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseUserFullInfoDto.class))),
            @ApiResponse(responseCode = "400", description = "Неккоректный запрос (Bad Request)"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден (Not Found)")
    })
    @Operation(summary = "Метод для получения новго access-токена",
            description = "Позволяет сгенерировать новый access-токен для зарегистрированных пользователей")
    @PostMapping("/access-token/{id}")
    public ResponseEntity<ResponseUserFullInfoDto> generateAccessToken(@Parameter(description = "Идентификатор пользователя") @PathVariable @Positive Long id) {
        ResponseUserFullInfoDto responseUserFullInfoDto = userService.generateAccessToken(id);
        log.info(GENERATE_ACCESS_TOKEN_MESSAGE_LOGGER_CONTROLLER, id);
        return ResponseEntity.ok(responseUserFullInfoDto);
    }
}
