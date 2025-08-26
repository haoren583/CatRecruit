package com.cat.recruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cat.recruit.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author cat
 */

public interface UserMapper extends BaseMapper<User> {
    User selectByUsername(String username);
    // TODO : Implement UserMapper
}
