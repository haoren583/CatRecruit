package com.cat.recruit.service;

import com.cat.recruit.common.result.Result;
import org.springframework.stereotype.Service;


public interface AdminService {
    /**
     * 管理员登录
     * @param userName 用户名
     * @param password 密码
     * @return 登录结果
     */
    Result login(String userName, String password);
}
