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

    /*
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
     */


    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 前后端分离关闭 CSRF
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // 登录注册等接口允许匿名访问
                        .requestMatchers("api/wx/login/**", "api/wx/register/**").permitAll()
                        .requestMatchers("api/admin/login/**", "api/admin/register/**").permitAll()
                        .requestMatchers("/api/wx/**").hasRole("USER")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        // 其它请求需要认证
                        .anyRequest().authenticated()
                )
                // 禁用表单登录
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
    */

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    JwtAccessDeniedHandler jwtAccessDeniedHandler;

    /**
     * 过滤器链：
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception 异常
     */
    @Bean
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http

                // 只匹配 /api 开头的路径
                .securityMatcher("/api/**")
                // 前后端分离关闭 CSRF
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        // 处理未认证异常
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        // 处理权限不足异常
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        // 登录注册等接口允许匿名访问
                        .requestMatchers("api/admin/login/**", "api/admin/register/**").permitAll()
                        .requestMatchers("/api/wx/login/**", "/api/wx/register/**").permitAll()
                        // 需要管理员权限
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        // 需要用户权限
                        .requestMatchers("/api/wx/**").hasRole("USER")
                        // 其它请求需要认证
                        .anyRequest().authenticated()
                )
                // 禁用表单登录
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
