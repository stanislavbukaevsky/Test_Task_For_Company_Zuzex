package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.controller;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.RequestHouseDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseHouseDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.ResponseHouseToUserDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service.HouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant.LoggerTextMessageConstant.*;

/**
 * Класс-контроллер для работы со всеми домами
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/homes")
@RequiredArgsConstructor
@Tag(name = "Работа со всеми домами на платформе", description = "Позволяет управлять методами по работе со всеми домами на платформе")
public class HouseController {
    private final HouseService houseService;

    /**
     * Этот метод позволяет добавлять новые дома в приложении
     *
     * @param requestHouseDto класс-DTO для запроса от пользователя
     * @return Возвращает DTO с ответом пользователю
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Новый дом успешно добавлен (OK)",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseHouseDto.class))),
            @ApiResponse(responseCode = "400", description = "Неккоректный запрос (Bad Request)"),
            @ApiResponse(responseCode = "401", description = "Неаутентифицированный пользователь (Unauthorized)"),
            @ApiResponse(responseCode = "403", description = "Пользователю запрещен вход на этот ресурс (Forbidden)"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера (Internal Server Error)")
    })
    @Operation(summary = "Метод для добавления новых домов в приложении", description = "Позволяет добавлять новые дома в приложении")
    @PostMapping("/add-house")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ResponseHouseDto> addHouse(@RequestBody @Valid RequestHouseDto requestHouseDto) {
        ResponseHouseDto responseHouseDto = houseService.addHouse(requestHouseDto);
        log.info(ADD_HOUSE_MESSAGE_LOGGER_CONTROLLER, requestHouseDto);
        return ResponseEntity.ok(responseHouseDto);
    }

    /**
     * Этот метод позволяет изменять информацию о доме в приложении
     *
     * @param id              уникальный идентификатор дома
     * @param requestHouseDto класс-DTO для запроса от пользователя
     * @return Возвращает DTO с ответом пользователю
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о доме успешно изменена (OK)",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseHouseDto.class))),
            @ApiResponse(responseCode = "400", description = "Неккоректный запрос (Bad Request)"),
            @ApiResponse(responseCode = "401", description = "Неаутентифицированный пользователь (Unauthorized)"),
            @ApiResponse(responseCode = "403", description = "Пользователю запрещен вход на этот ресурс (Forbidden)"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера (Internal Server Error)")
    })
    @Operation(summary = "Метод для изменения информации о доме в приложении", description = "Позволяет изменять информацию о доме в приложении")
    @PutMapping("/update-house/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ResponseHouseDto> updateHouse(@Parameter(description = "Идентификатор дома")
                                                        @PathVariable @Positive Long id,
                                                        @RequestBody @Valid RequestHouseDto requestHouseDto) {
        ResponseHouseDto responseHouseDto = houseService.updateHouse(id, requestHouseDto);
        log.info(UPDATE_HOUSE_MESSAGE_LOGGER_CONTROLLER, id, requestHouseDto);
        return ResponseEntity.ok(responseHouseDto);
    }

    /**
     * Этот метод позволяет искать информацию о доме в приложении
     *
     * @param id уникальный идентификатор дома
     * @return Возвращает DTO с ответом пользователю
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о доме успешно найдена (OK)",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseHouseDto.class))),
            @ApiResponse(responseCode = "400", description = "Неккоректный запрос (Bad Request)"),
            @ApiResponse(responseCode = "401", description = "Неаутентифицированный пользователь (Unauthorized)"),
            @ApiResponse(responseCode = "403", description = "Пользователю запрещен вход на этот ресурс (Forbidden)"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера (Internal Server Error)")
    })
    @Operation(summary = "Метод для поиска информации о доме в приложении", description = "Позволяет искать информацию о доме в приложении")
    @GetMapping("/find-house/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ResponseHouseDto> findHouse(@Parameter(description = "Идентификатор дома") @PathVariable @Positive Long id) {
        ResponseHouseDto responseHouseDto = houseService.findHouse(id);
        log.info(FIND_HOUSE_MESSAGE_LOGGER_CONTROLLER, id);
        return ResponseEntity.ok(responseHouseDto);
    }

    /**
     * Этот метод позволяет удалять информацию о доме в приложении
     *
     * @param id уникальный идентификатор дома
     * @return Возвращает ответ пользователю в виде сообщения
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о доме успешно удалена (OK)"),
            @ApiResponse(responseCode = "401", description = "Неаутентифицированный пользователь (Unauthorized)"),
            @ApiResponse(responseCode = "403", description = "Пользователю запрещен вход на этот ресурс (Forbidden)")
    })
    @Operation(summary = "Метод для удаления информации о доме в приложении", description = "Позволяет удалять информацию о доме в приложении")
    @DeleteMapping("/delete-house/{id}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<String> deleteHouse(@Parameter(description = "Идентификатор дома") @PathVariable @Positive Long id) {
        String result = houseService.deleteHouse(id);
        log.info(DELETE_HOUSE_MESSAGE_LOGGER_CONTROLLER, id);
        return ResponseEntity.ok(result);
    }

    /**
     * Этот метод позволяет добавлять жильцов в дома в приложении
     *
     * @param idHouse уникальный идентификатор дома
     * @param idUser  уникальный идентификатор добавляемого пользователя
     * @return Возвращает DTO с ответом пользователю
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно заселен в дом (OK)",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseHouseToUserDto.class))),
            @ApiResponse(responseCode = "400", description = "Неккоректный запрос (Bad Request)"),
            @ApiResponse(responseCode = "401", description = "Неаутентифицированный пользователь (Unauthorized)"),
            @ApiResponse(responseCode = "403", description = "Пользователю запрещен вход на этот ресурс (Forbidden)"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера (Internal Server Error)")
    })
    @Operation(summary = "Метод для добавления жильцов в дома в приложении", description = "Позволяет добавлять жильцов в дома в приложении")
    @PostMapping("/user-house/{idUser}")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ResponseHouseToUserDto> addUserToHouse(@Parameter(description = "Идентификатор дома") @RequestParam Long idHouse,
                                                                 @Parameter(description = "Идентификатор пользователя") @PathVariable @Positive Long idUser) {
        ResponseHouseToUserDto responseHouseToUserDto = houseService.addUserToHouse(idHouse, idUser);
        log.info(ADD_USER_TO_HOUSE_MESSAGE_LOGGER_CONTROLLER, idHouse, idUser);
        return ResponseEntity.ok(responseHouseToUserDto);
    }
}
