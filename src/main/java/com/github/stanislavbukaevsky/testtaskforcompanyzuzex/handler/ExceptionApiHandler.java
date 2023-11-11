package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.handler;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.exception.*;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Этот класс для обработки всех исключений приложения на уровне контроллеров
 */
@Slf4j
@RestControllerAdvice
public class ExceptionApiHandler {

    /**
     * Этот метод обрабатывает все исключения, возникшие с поиском пользователя по его электронной почте
     *
     * @param exception исключение
     * @return Возвращает сформированное сообщение пользователю об ошибке, возникшей в результате неправильного запроса
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseApiException> usernameNotFoundException(UsernameNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseApiException(HttpStatus.NOT_FOUND.value(), exception.getMessage(), getDateTime()));
    }

    /**
     * Этот метод обрабатывает все исключения, возникшие с неправильной валидацией
     *
     * @param exception исключение
     * @return Возвращает сформированное сообщение пользователю об ошибке, возникшей в результате неправильного запроса
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseValidationException> onConstraintValidationException(ConstraintViolationException exception) {
        final List<Violation> violations = exception.getConstraintViolations().stream()
                .map(violation ->
                        new Violation(violation.getPropertyPath().toString(), violation.getMessage(), getDateTime())
                ).collect(Collectors.toList());
        log.error(exception.getMessage(), exception);
        return ResponseEntity.ok(new ResponseValidationException(violations));
    }

    /**
     * Этот метод обрабатывает все исключения, возникшие с неправильной валидацией
     *
     * @param exception исключение
     * @return Возвращает сформированное сообщение пользователю об ошибке, возникшей в результате неправильного запроса
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseValidationException> onMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        final List<Violation> violations = exception.getBindingResult().getFieldErrors().stream()
                .map(violation ->
                        new Violation(violation.getField(), violation.getDefaultMessage(), getDateTime())
                ).collect(Collectors.toList());
        log.error(exception.getMessage(), exception);
        return ResponseEntity.ok(new ResponseValidationException(violations));
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ResponseApiException> addressNotFoundException(AddressNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseApiException(HttpStatus.NOT_FOUND.value(), exception.getMessage(), getDateTime()));
    }

    /**
     * Этот метод обрабатывает все исключения, возникшие с неправильным доступом пользователя
     *
     * @param exception исключение
     * @return Возвращает сформированное сообщение пользователю об ошибке, возникшей в результате неправильного запроса
     */
    @ExceptionHandler(StatusForbiddenException.class)
    public ResponseEntity<ResponseApiException> statusForbiddenException(StatusForbiddenException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ResponseApiException(HttpStatus.FORBIDDEN.value(), exception.getMessage(), getDateTime()));
    }

    /**
     * Этот метод обрабатывает все исключения, возникшие с повтором электронной почты
     *
     * @param exception исключение
     * @return Возвращает сформированное сообщение пользователю об ошибке, возникшей в результате неправильного запроса
     */
    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<ResponseApiException> userNameAlreadyExistsException(UserNameAlreadyExistsException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ResponseApiException(HttpStatus.FORBIDDEN.value(), exception.getMessage(), getDateTime()));
    }

    /**
     * Этот метод обрабатывает все исключения, возникшие с неправильным доступом пользователя
     *
     * @param exception исключение
     * @return Возвращает сформированное сообщение пользователю об ошибке, возникшей в результате неправильного запроса
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseApiException> userNotFoundException(UserNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseApiException(HttpStatus.NOT_FOUND.value(), exception.getMessage(), getDateTime()));
    }

    /**
     * Этот метод обрабатывает все исключения, возникшие с неправильным доступом пользователя
     *
     * @param exception исключение
     * @return Возвращает сформированное сообщение пользователю об ошибке, возникшей в результате неправильного запроса
     */
    @ExceptionHandler(HouseNotFoundException.class)
    public ResponseEntity<ResponseApiException> houseNotFoundException(HouseNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseApiException(HttpStatus.NOT_FOUND.value(), exception.getMessage(), getDateTime()));
    }

    /**
     * Этот метод обрабатывает все исключения, возникшие с неправильным запросом пользователя
     *
     * @param exception исключение
     * @return Возвращает сформированное сообщение пользователю об ошибке, возникшей в результате неправильного запроса
     */
    @ExceptionHandler(UserNameBadRequestException.class)
    public ResponseEntity<ResponseApiException> userNameBadRequestException(UserNameBadRequestException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseApiException(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), getDateTime()));
    }

    /**
     * Приватный метод, который формирует настоящие дату и время
     *
     * @return Возвращает настоящие дату и время, когда было выброшено исключение
     */
    private LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }
}
