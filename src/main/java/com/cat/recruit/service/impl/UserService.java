package com.cat.recruit.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author cat
 */
@Service
public interface UserService {
    UserDetails loadUserByUserId(Integer userId);
}
