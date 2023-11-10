package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant;

/**
 * Этот класс содержит текстовые константные переменные для всех логов в приложении
 */
public class LoggerTextMessageConstant {
    // Логи для методов в сервисах
    public static final String FIND_USER_BY_EMAIL_MESSAGE_LOGGER_SERVICE = "Вызван метод поиска пользователя по его логину в сервисе. Логин пользователя: {}";

    // Логи для пакета security
    public static final String LOAD_USER_BY_USERNAME_MESSAGE_LOGGER_SECURITY = "Вызван метод для поиска пользователя по его уникальному логину и помещен в security context. Логин пользователя: {}";
}
