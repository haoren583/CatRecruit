package com.cat.recruit.service.impl;

import com.cat.recruit.bean.dto.UserDTO;
import com.cat.recruit.common.exception.BusinessExceptionEnum;
import com.cat.recruit.common.result.Result;
import com.cat.recruit.entity.User;
import com.cat.recruit.mapper.UserMapper;
import com.cat.recruit.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.cat.recruit.common.exception.BusinessException.bizException;

/**
 * @author cat
 *
 * 用于与管理后台相关的业务逻辑的实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUserDetailsServiceImpl jwtUserDetailsService;


    /**
     * 管理员登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public Result login(String username, String password) {
        // 在数据库中查询用户信息
        User user = userMapper.selectByUsername(username);
        // 判断用户是否存在
        if (user == null) {
            throw bizException(BusinessExceptionEnum.USER_NOT_EXIST);
        }
        // 判断密码是否正确
        if (!user.getPassword().equals(passwordEncoder.encode(password))) {
            throw bizException(BusinessExceptionEnum.PASSWORD_ERROR);
        }
        // 生成token
        String token = jwtUserDetailsService.generateToken(user);

        // 生成用户信息DTO
        UserDTO userDTO = new UserDTO(user);

        // 封装数据
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", userDTO);

        return Result.success(userDTO);

    }
}
