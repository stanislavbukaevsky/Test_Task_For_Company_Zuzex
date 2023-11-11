package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant;

/**
 * Этот класс содержит текстовые константные переменные для всех логов в приложении
 */
public class LoggerTextMessageConstant {
    // Логи для методов в контроллере
    public static final String CREATE_USER_MESSAGE_LOGGER_CONTROLLER = "Вызван метод создания нового пользователя в контроллере. Запрос пользователя: {}";
    public static final String UPDATE_USER_MESSAGE_LOGGER_CONTROLLER = "Вызван метод изменения информации пользователя в контроллере. Уникальный идентификатор пользователя: {}";
    public static final String UPDATE_STATUS_USER_MESSAGE_LOGGER_CONTROLLER = "Вызван метод изменения статуса пользователя в контроллере. Уникальный идентификатор пользователя: {}";
    public static final String FIND_USER_MESSAGE_LOGGER_CONTROLLER = "Вызван метод поиска пользователя по электронной почте в контроллере. Электронная почта пользователя: {}";
    public static final String DELETE_USER_MESSAGE_LOGGER_CONTROLLER = "Вызван метод удаления пользователя в контроллере";
    public static final String GENERATE_ACCESS_TOKEN_MESSAGE_LOGGER_CONTROLLER = "Вызван метод для получения нового access-токена в контроллере. Уникальный идентификатор пользователя: {}";
    public static final String ADD_HOUSE_MESSAGE_LOGGER_CONTROLLER = "Вызван метод для добавления новых домов в контроллере. Запрос пользователя: {}";
    public static final String UPDATE_HOUSE_MESSAGE_LOGGER_CONTROLLER = "Вызван метод для изменения информации о доме в контроллере. Уникальный идентификатор дома: {}. Запрос пользователя: {}";
    public static final String FIND_HOUSE_MESSAGE_LOGGER_CONTROLLER = "Вызван метод для поиска домов в контроллере. Уникальный идентификатор дома: {}";
    public static final String DELETE_HOUSE_MESSAGE_LOGGER_CONTROLLER = "Вызван метод для удаления домов в контроллере. Уникальный идентификатор дома: {}";
    public static final String ADD_USER_TO_HOUSE_MESSAGE_LOGGER_CONTROLLER = "Вызван метод для добавления жильцов в дома в контроллере. Уникальный идентификатор дома: {}. Уникальный идентификатор заселяемого жильца: {}";

    // Логи для методов в сервисах
    public static final String FIND_USER_BY_EMAIL_MESSAGE_LOGGER_SERVICE = "Вызван метод поиска пользователя по его логину в сервисе. Логин пользователя: {}";
    public static final String CREATE_USER_MESSAGE_LOGGER_SERVICE = "Вызван метод создания нового пользователя в сервисе. Запрос пользователя: {}";
    public static final String UPDATE_USER_MESSAGE_LOGGER_SERVICE = "Вызван метод изменения информации пользователя в сервисе. Уникальный идентификатор пользователя: {}";
    public static final String UPDATE_STATUS_USER_MESSAGE_LOGGER_SERVICE = "Вызван метод изменения статуса пользователя в сервисе. Уникальный идентификатор пользователя: {}";
    public static final String FIND_USER_MESSAGE_LOGGER_SERVICE = "Вызван метод поиска пользователя по электронной почте в сервисе. Электронная почта пользователя: {}";
    public static final String DELETE_USER_MESSAGE_LOGGER_SERVICE = "Вызван метод удаления пользователя в сервисе";
    public static final String GENERATE_ACCESS_TOKEN_MESSAGE_LOGGER_SERVICE = "Вызван метод для получения нового access-токена в сервисе. Уникальный идентификатор пользователя: {}";
    public static final String ADD_HOUSE_MESSAGE_LOGGER_SERVICE = "Вызван метод для добавления новых домов в сервисе. Запрос пользователя: {}";
    public static final String UPDATE_HOUSE_MESSAGE_LOGGER_SERVICE = "Вызван метод для изменения информации о доме в сервисе. Уникальный идентификатор дома: {}. Запрос пользователя: {}";
    public static final String FIND_HOUSE_MESSAGE_LOGGER_SERVICE = "Вызван метод для поиска домов в сервисе. Уникальный идентификатор дома: {}";
    public static final String DELETE_HOUSE_MESSAGE_LOGGER_SERVICE = "Вызван метод для удаления домов в сервисе. Уникальный идентификатор дома: {}";
    public static final String ADD_USER_TO_HOUSE_MESSAGE_LOGGER_SERVICE = "Вызван метод для добавления жильцов в дома в сервисе. Уникальный идентификатор дома: {}. Уникальный идентификатор заселяемого жильца: {}";

    // Логи для пакета security
    public static final String LOAD_USER_BY_USERNAME_MESSAGE_LOGGER_SECURITY = "Вызван метод для поиска пользователя по его уникальному логину и помещен в security context. Логин пользователя: {}";
}
