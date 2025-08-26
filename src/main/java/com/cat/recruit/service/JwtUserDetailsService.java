package com.cat.recruit.service;

import com.cat.recruit.entity.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface JwtUserDetailsService{
    String ID = "id";

    /**
     * 根据id查询用户信息
     * @param id 用户id
     * @return 用户信息
     * @throws AuthenticationException 认证异常
     */
    public UserDetails loadUserById(Integer id) throws AuthenticationException;

    /**
     * 生成JWT
     *
     * @param user 用户信息
     */
    public String generateToken(User user) ;

}
