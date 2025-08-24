package com.cat.recruit.controller;

import com.cat.recruit.bean.pojo.AdminLoginRequest;
import com.cat.recruit.common.result.Result;
import com.cat.recruit.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/api/admin/**")
@Validated
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 管理员登录
     * @param adminLoginRequest 管理员信息
     * @return result
     */
    @RequestMapping("/login")
    public Result login(@RequestBody AdminLoginRequest adminLoginRequest) {
        return adminService.login(
                adminLoginRequest.getUserName(),
                adminLoginRequest.getPassword()
        );
    }

}
