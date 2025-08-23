package com.cat.recruit.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * @author cat
 */
public class JwtUtils {

    // 密钥
    @Value("${jwt.secret}")
    private static String secret;

    // 过期时间
    @Value("${jwt.expiration}")
    private static Long EXPIRATION_TIME;

    // 将密钥转换为Key对象
    private static final SecretKey KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));

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
