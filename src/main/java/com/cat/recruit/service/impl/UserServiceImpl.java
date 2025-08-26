package com.cat.recruit.service.impl;

import com.cat.recruit.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author cat
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDetails Login(String userNumber, String password) {
        // TODO: 实现登录逻辑
        return null;
    }
}
