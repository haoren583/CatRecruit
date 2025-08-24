package com.cat.recruit;

import com.cat.recruit.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RecruitApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 测试JetUtils工具类
     */
    @Test
    void jetUtilsTest() {
        // 生成token
        Map<String, Object> claims = Map.of("username", "admin", "roles", "admin");
        String token = JwtUtils.generateToken(claims);
        System.out.println(token);

        // 解析token
        Claims claims1 = JwtUtils.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6ImFkbWluIiwidXNlcm5hbWUiOiJhZG1pbiIsImlhdCI6MTc1NjAyOTU5MCwiZXhwIjoxNzU2MTE1OTkwfQ.uR2WfNRv7Agkq8bErDKG1r1IE5lkysgRQ04HTI_HAr8");
        System.out.println(claims1);
    }


}
