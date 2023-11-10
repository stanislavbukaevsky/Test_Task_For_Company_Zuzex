package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.token;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.github.stanislavbukaevsky.testtaskforcompanyzuzex.constant.ExceptionTextMessageConstant.*;

/**
 * Класс для генерации и валидации access токена
 */
@Slf4j
@Component
public class TokenDetailsService {
    private final SecretKey accessSecret;

    public TokenDetailsService(@Value("${secret-key.for.access-token}") String accessSecret) {
        this.accessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret));
    }

    /**
     * Этот метод генерирует access токен
     *
     * @param user сущность пользователя
     * @return Возвращает сгенерированный JWT access токен
     */
    public String generateAccessToken(@NonNull User user) {
        final LocalDateTime createdDate = LocalDateTime.now();
        final long duration = 60;
        final Instant accessExpirationInstant = createdDate.plusMinutes(duration).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(accessExpiration)
                .signWith(accessSecret)
                .claim("status", user.getStatus())
                .compact();
    }

    /**
     * Этот метод проверяет валидность access токена
     *
     * @param accessToken access токен
     * @return Возвращает true или false, в зависимости от того валиден access токен или нет
     */
    public boolean validateAccessToken(@NonNull String accessToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(accessSecret)
                    .build()
                    .parseClaimsJws(accessToken);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error(EXPIRED_JWT_EXCEPTION_MESSAGE_SERVICE, expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error(UNSUPPORTED_JWT_EXCEPTION_MESSAGE_SERVICE, unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error(MALFORMED_JWT_EXCEPTION_MESSAGE_SERVICE, mjEx);
        } catch (SignatureException sEx) {
            log.error(SIGNATURE_EXCEPTION_MESSAGE_SERVICE, sEx);
        } catch (Exception e) {
            log.error(EXCEPTION_MESSAGE_SERVICE, e);
        }
        return false;
    }

    /**
     * Этот метод формирует из access токена данные о пользователе
     *
     * @param accessToken access токен
     * @return Возвращает сгенерированные данные о пользователе по его access токену
     */
    public Claims getAccessClaims(@NonNull String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(accessSecret)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
    }
}
