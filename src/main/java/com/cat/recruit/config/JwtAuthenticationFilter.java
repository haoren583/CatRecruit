package com.cat.recruit.config;

import com.cat.recruit.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author cat
 *
 * 过滤器，用于处理 JWT 认证
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            // 从请求头中获取 Authorization 字段
            final String authHeader = request.getHeader("Authorization");

            String jwt = null;
            Claims claims = null;

            // 检查 Header 是否存在，并且格式是否为 "Bearer <token>"
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                // 去掉 "Bearer " 前缀
                jwt = authHeader.substring(7);
                // 从 Token 中解析载体
                claims = JwtUtils.parseToken(jwt);
            }

            // 如果解析出了载体，且当前 SecurityContext 中还没有认证信息
            if (claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 加载用户信息（比如从数据库，通过 UserDetailsService）
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(claims.getId());
            }

            // 继续执行过滤器链
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            // 清理上下文
            SecurityContextHolder.clearContext();
        }
    }
}
