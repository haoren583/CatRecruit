package com.cat.recruit.service;

import com.cat.recruit.common.result.Result;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {
    UserDetails loadUserByUserId(Integer userId);
    Result createUser(String openId);
    int selectUserIsExist(String openId);
}
