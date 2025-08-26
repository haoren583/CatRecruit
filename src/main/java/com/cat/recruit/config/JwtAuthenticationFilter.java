package com.cat.recruit.config;

import com.cat.recruit.common.constant.ConstantField;
import com.cat.recruit.service.JwtUserDetailsService;
import com.cat.recruit.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        Claims claims;
        String jwt;
        try {
            // 从请求头中获取 Authorization 字段
            final String authHeader = request.getHeader("Authorization");

            jwt = null;
            claims = null;

            // 检查 Header 是否存在，并且格式是否为 "Bearer <token>"
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                // 去掉 "Bearer " 前缀
                jwt = authHeader.substring(7);
                // 从 Token 中解析载体
                claims = JwtUtils.parseToken(jwt);
            }
        } catch (Exception e) {
            // 解析 Token 失败，抛出认证异常(凭证无效)
            throw new BadCredentialsException("无效token", e);
        }

        // 如果解析出了载体，且当前 SecurityContext 中还没有认证信息
        if (claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 加载用户信息（比如从数据库，通过 UserDetailsService）
            UserDetails userDetails = this.jwtUserDetailsService.loadUserById(claims.get(ConstantField.ID, Integer.class));

            // 认证成功，设置 SecurityContext
            if (userDetails != null && userDetails.isEnabled() && userDetails.isAccountNonLocked()) {
                JwtAuthentication authentication = new JwtAuthentication(userDetails, jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("用户 'id={},name={}' 访问API:{}", claims.getId(), claims.getSubject(), request.getRequestURI());
            }
        }


        // 继续执行过滤器链
        filterChain.doFilter(request, response);

    }

}
