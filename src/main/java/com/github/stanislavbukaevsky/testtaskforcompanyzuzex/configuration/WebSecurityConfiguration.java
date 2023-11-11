package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.configuration;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.token.TokenFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Конфигурационный класс для настройки Spring Security
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    private final TokenFilterService tokenFilterService;
    private static final String[] FREE_ACCESS = {
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/webjars/**",
            "/users/create",
            "/users/access-token/**"
    };

    private static final String[] AUTH_ACCESS = {
            "/users/update/**",
            "/users/update-status/**",
            "/users/delete/**",
            "/users/find",
            "/homes/add-house",
            "/homes/update-house/**",
            "/homes/find-house/**",
            "/homes/delete-house/**"
    };
    private static final String[] OWNER_ACCESS = {
            "/homes/user-house/**"
    };

    /**
     * Этот метод настраивает правила безопасности для работы с приложением и запрещает/разрешает доступ к определенным ресурсам
     *
     * @param http конфигурация http
     * @return Возвращает сконфигурированный и настроенный клиент http
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(FREE_ACCESS).permitAll();
                    auth.requestMatchers(AUTH_ACCESS).authenticated();
                    auth.requestMatchers(OWNER_ACCESS).hasAuthority("OWNER");
                })
                .addFilterAfter(tokenFilterService, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Этот метод используется для шифрования паролей
     *
     * @return Возвращает зашифрованный пароль с защитой 12
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
