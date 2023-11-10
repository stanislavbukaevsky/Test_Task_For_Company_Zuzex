package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.exception;

/**
 * Класс-исключение, если логин (электронная почта) присутствует в базе данных. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class UserNameAlreadyExistsException extends RuntimeException {
    public UserNameAlreadyExistsException(String message) {
        super(message);
    }
}
