package com.cat.recruit.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * @author cat
 */
@Slf4j
@Component
public class JwtUtils {

    // 配置文件的数据
    @Value("${jwt.secret}")
    private String secretProperty;

    @Value("${jwt.expiration}")
    private Long expirationProperty;

    private static String secret;

    private static Long EXPIRATION_TIME;

    private static SecretKey KEY;

    @PostConstruct
    public void init() {
        secret = secretProperty;
        EXPIRATION_TIME = expirationProperty;
        log.info("JWT secret: {}", secret);
        log.info("JWT expiration: {}", EXPIRATION_TIME);
        log.info("JWT key: {}", Jwts.SIG.HS256.key());
        KEY = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成JWT Token
     * @param claims 包含用户信息的Map
     * @return JWT Token
     */
    public static String generateToken(Map<String, Object> claims) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(new Date(nowMillis + EXPIRATION_TIME))
                .signWith(KEY)
                .compact();
    }

    // 验证JWT Token并解析其中的信息
    public static Claims parseToken(String token) {
        // jws解析
        Claims claims= Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        // 验证是否过期
        if (claims.getExpiration().before(new Date())) {
            throw new RuntimeException("Token expired");
        }
        return claims;
    }

}
