package com.cat.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cat.recruit.common.exception.BusinessException;
import com.cat.recruit.common.exception.BusinessExceptionEnum;
import com.cat.recruit.common.result.Result;
import com.cat.recruit.entity.User;
import com.cat.recruit.mapper.UserMapper;
import com.cat.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUserId(Integer userId) {
        // TODO: implement load user by user id
        return null;
    }

    @Override
    public Result createUser(String openId) {
        User user = new User();
        user.setOpenId(openId);
        if (userMapper.insert(user) > 0) {
            return Result.success(user);
        }else {
            return Result.error(BusinessException.bizException(BusinessExceptionEnum.INSERT_FAILED));
        }
    }

    @Override
    public int selectUserIsExist(String openId) {
        //返回0代表改用户不存在，返回1代表该用户已存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id", openId);
        long count = userMapper.selectCount(queryWrapper);
        // 不管存不存在，都要生成token
        // TODO:生成token
        if (count > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
