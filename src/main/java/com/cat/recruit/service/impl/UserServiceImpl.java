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
        if (openId == null || openId.trim().isEmpty()) {
            //TODO: 创建一个数据不应该为空的业务异常
            return Result.error(BusinessException.bizException(BusinessExceptionEnum.BAD_REQUEST));
        }
        User user = new User();
        user.setOpenId(openId);
        if (userMapper.insert(user) > 0) {
            return Result.success(user);
        }else {
            //TODO: 创建一个插入用户失败的业务异常
            return Result.error(BusinessException.bizException(BusinessExceptionEnum.INTERNAL_SERVER_ERROR));
        }
    }

    @Override
    public int selectUserIsExist(String openId) {
        //返回0代表改用户不存在，返回1代表该用户已存在
        if (openId == null || openId.trim().isEmpty()) {
            //TODO: 创建一个数据不应该为空的业务异常 并抛出该业务异常
            return 0;
        }
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
