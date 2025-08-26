package com.cat.recruit.bean.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author cat
 */
@Data
public class AdminLoginRequest {
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @Size(min = 6, max = 20, message = "密码长度必须在6到20个字符之间")
    private String password;
}
