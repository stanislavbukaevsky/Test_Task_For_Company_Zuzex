package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.security;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.User;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant.ExceptionTextMessageConstant.USER_NOT_FOUND_EXCEPTION;
import static com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant.LoggerTextMessageConstant.LOAD_USER_BY_USERNAME_MESSAGE_LOGGER_SECURITY;

/**
 * Этот класс используется для поиска пользователей в базе данных, через этот сервис.
 * Реализует интерфейс {@link UserDetailsService}
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserSecurity userSecurity;

    /**
     * Этот метод ищет пользователя по его уникальной электронной почте
     *
     * @param username электронная почта пользователя
     * @return Возвращает найденного пользователя, через защищенный сервис {@link UserSecurity}
     * @throws UsernameNotFoundException исключение, если пользователя с такой электронной почтой не существует
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(USER_NOT_FOUND_EXCEPTION));
        userSecurity.setUser(user);
        log.info(LOAD_USER_BY_USERNAME_MESSAGE_LOGGER_SECURITY, userSecurity.getUsername());
        return userSecurity;
    }
}
