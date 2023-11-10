package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.controller;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.CreateUserRequestDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.dto.CreateUserResponseDto;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.User;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Работа со всеми пользователями на платформе", description = "Позволяет управлять методами по работе со всеми пользователями на платформе")
public class UserController {
    private final UserService userService;

    /**
     * Этот метод позволяет создать нового пользователя на платформе
     *
     * @param createUserRequestDto класс-DTO для создания пользователя на платформе
     * @return Возвращает созданного пользователя и всю информацию о нем
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Новый пользователь зарегистрирован (OK)", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateUserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Неккоректный запрос (Bad Request)"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера (Internal Server Error)")
    })
    @Operation(summary = "Метод создания пользователей на платформе", description = "Позволяет создать нового пользователя на платформе")
    @PostMapping("/create")
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody @Valid CreateUserRequestDto createUserRequestDto) {
        CreateUserResponseDto createUserResponseDto = userService.createUser(createUserRequestDto);
        return ResponseEntity.ok(createUserResponseDto);
    }

    @PatchMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User result = userService.updateUser(user);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam String email) {
        userService.deleteUser(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public ResponseEntity<User> findUser(@RequestParam String email) {
        User user = userService.findUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
