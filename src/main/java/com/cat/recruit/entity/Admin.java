package com.cat.recruit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("governor")
public class Admin {
    private String id;
    private String userName;
    private String password;
}
