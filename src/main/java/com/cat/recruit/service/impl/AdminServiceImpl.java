package com.cat.recruit.service.impl;

import com.cat.recruit.common.exception.BusinessException;
import com.cat.recruit.common.exception.BusinessExceptionEnum;
import com.cat.recruit.common.result.Result;
import com.cat.recruit.entity.Admin;
import com.cat.recruit.mapper.AdminMapper;
import com.cat.recruit.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    AdminMapper adminMapper;

    /**
     * 生成管理员token
     * @param admin 管理员信息
     * @return token
     */
    private String generateToken(Admin admin) {
        //构建载体
        // TODO: 构建载体
        return null;
    }

    /**
     * 管理员登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public Result login(String userName, String password) {
        // 在数据库中查询用户信息
        Admin admin = adminMapper.selectByUserName(userName);
        // 判断用户是否存在
        if (admin == null) {
            throw bizException(BusinessExceptionEnum.USER_NOT_EXIST);
        }
        // 判断密码是否正确
        if (!admin.getPassword().equals(password)) {
            throw bizException(BusinessExceptionEnum.PASSWORD_ERROR);
        }
        // 生成token
        // TODO: 生成token
        return null;
    }
}
