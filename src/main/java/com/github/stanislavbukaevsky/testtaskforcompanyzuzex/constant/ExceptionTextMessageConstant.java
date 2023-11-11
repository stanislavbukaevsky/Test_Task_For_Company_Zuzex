package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant;

/**
 * Этот класс содержит текстовые константные переменные для всех исключений в приложении
 */
public class ExceptionTextMessageConstant {
    public static final String USER_NOT_FOUND_EXCEPTION = "Пользователя, с таким логином не существует в базе данных. Попробуйте ввести другой логин.";
    public static final String EXPIRED_JWT_EXCEPTION_MESSAGE_SERVICE = "Срок действия токена истек! Получите новый JWT токен и повторите попытку";
    public static final String UNSUPPORTED_JWT_EXCEPTION_MESSAGE_SERVICE = "Неподдерживаемый токен! Получите новый JWT токен и повторите попытку";
    public static final String MALFORMED_JWT_EXCEPTION_MESSAGE_SERVICE = "Токен неправильно сформирован! Получите новый JWT токен и повторите попытку";
    public static final String SIGNATURE_EXCEPTION_MESSAGE_SERVICE = "Подпись токена не действительна! Получите новый JWT токен и повторите попытку";
    public static final String EXCEPTION_MESSAGE_SERVICE = "Недопустимый токен! Получите новый JWT токен и повторите попытку";
    public static final String CREATE_USER_MESSAGE_EXCEPTION_SERVICE = "Пользователь с электронной почтой: ";
    public static final String CREATE_USER_MESSAGE_EXCEPTION_SERVICE_2 = " уже существует! Попробуйте ввести другую электронную почту.";
    public static final String USER_NOT_FOUND_BY_ID_EXCEPTION = "Пользователя с таким идентификатором не существует. Попробуйте ввести другой идентификатор пользователя.";
    public static final String STATUS_FORBIDDEN_EXCEPTION = "У вас нет прав доступа на это действие! Эту операцию могут совершать только пользователи со статусом Хозяин";
    public static final String STATUS_FORBIDDEN_EXCEPTION_2 = "У вас нет прав доступа на это действие, либо неправильно выбран пользователь! Эту операцию могут совершать только пользователи со статусом Хозяин и он в доме должен быть один";
    public static final String USER_NAME_BAD_REQUEST_EXCEPTION = "Вы неправильно сделали запрос!";
    public static final String HOUSE_NOT_FOUND_EXCEPTION = "Дома с таким идентификатором не существует! Попробуйте ввести другой идентификатор";

}
