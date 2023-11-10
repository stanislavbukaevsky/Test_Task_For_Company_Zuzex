package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.security;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Collection;
import java.util.List;

/**
 * Этот класс предоставляет всю необходимую информацию для построения объекта аутентификации.
 * Здесь находятся методы, которые достают данные пользователя из базы данных. <br>
 * Реализует методы интерфейса {@link UserDetails}
 */
@Component
@RequestScope
@Getter
@Setter
@RequiredArgsConstructor
public class UserSecurity implements UserDetails {
    private User user;

    /**
     * Метод для получения email пользователя
     *
     * @return Возвращает email пользователя
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Метод для получения пароля пользователя
     *
     * @return Возвращает пароль пользователя
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Метод для получении коллекции статуса пользователей дома
     *
     * @return Возвращает список статусов пользователей дома
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getStatus().name()));
    }

    /**
     * Метод об истечении срока действия учетной записи пользователя
     *
     * @return Возвращает true, если у учетной записи пользователя не истек срок действия
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Метод о блокировки учетной записи пользователя
     *
     * @return Возвращает true, если учетная запись пользователя не заблокирована
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Метод об истечении срока действия учетных данных пользователя
     *
     * @return Возвращает true, если у учетных данных пользователя не истек срок действия
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Метод об активности пользователя
     *
     * @return Возвращает true, если пользователь активен
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
