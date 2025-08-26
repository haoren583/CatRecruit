package com.cat.recruit.service.impl;

import com.cat.recruit.common.constant.ConstantField;
import com.cat.recruit.config.JwtUserDetails;
import com.cat.recruit.entity.User;
import com.cat.recruit.mapper.UserMapper;
import com.cat.recruit.service.JwtUserDetailsService;
import com.cat.recruit.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cat
 */
@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService  {
    UserMapper userMapper;

    @Override
    public UserDetails loadUserById(Integer id) throws AuthenticationException {
        // 根据id查询用户信息
        User user = userMapper.selectById(id);
        if (user == null){
            throw new UsernameNotFoundException("用户id不存在: " + id);
        }

        // 封装用户权限
        // 这里直接以角色名作为权限名
        Collection<GrantedAuthority> thisAuthorities = new ArrayList<>();
        thisAuthorities.add(user::getRole);

        // 封装并返回 JwtUserDetails
        return JwtUserDetails.builder()
                .username(user.getUsername())
                .authorities(thisAuthorities)
                .role(user.getRole())
                .id(user.getUserId())
                .password(user.getPassword())
                .build();
    }

    /**
     * 生成JWT
     *
     * @param user 用户信息
     */
    @Override
    public String generateToken(User user) {
        // 构造载体
        Map<String, Object> claims = new HashMap<>();
        claims.put(ConstantField.ID, user.getUserId());
        claims.put(ConstantField.NAME, user.getUsername());
        claims.put(ConstantField.ROLE, user.getRole());
        claims.put(ConstantField.PERMISSION, user.getRole());

        // 生成token
        return JwtUtils.generateToken(claims);
    }
}
