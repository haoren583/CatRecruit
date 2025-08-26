package com.cat.recruit.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author cat
 */
public interface UserService {
    UserDetails Login(String userNumber,String password);
}
