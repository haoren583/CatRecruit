package com.cat.recruit.service;

import com.cat.recruit.service.impl.UserService;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author cat
 */
public class UserServiceImpl implements UserService {
    @Override
    public UserDetails loadUserByUserId(Integer userId) {
        // TODO: implement load user by user id
        return null;
    }
}
