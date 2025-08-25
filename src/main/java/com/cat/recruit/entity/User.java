package com.cat.recruit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class User {
    private int userId;
    private String openId;
    private String code;
    private String name;
    private String userNumber;
    private String academy;
    private String phoneNumber;
    private String email;
    private String userIntro;
    private String direction;
    private String status;
    private String role;
    private String password;
    private String username;
}
