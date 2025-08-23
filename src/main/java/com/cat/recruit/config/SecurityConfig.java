package com.cat.recruit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author cat
 *
 * 配置前后端分离的Spring Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 前后端分离通常关闭 CSRF
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // 登录注册等接口允许匿名访问
                        .requestMatchers("api/login/**", "api/register/**").permitAll()
                        // 其它请求需要认证
                        .anyRequest().authenticated()
                )
                // 禁用表单登录（前后端分离一般不用）
                .formLogin(AbstractHttpConfigurer::disable)
                // 禁用 Basic Auth
                .httpBasic(AbstractHttpConfigurer::disable)
                // 禁用 session
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 加入 JWT 过滤器
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }

    /**
     * 密码加密器
     * @return 密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
