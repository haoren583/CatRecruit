package com.cat.recruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cat.recruit.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * mapper类
 * 用于管理员相关的数据库操作
 *
 * @author cat
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 根据用户名查询管理员信息
     * @param userName 用户名
     * @return 管理员信息
     */
    Admin selectByUserName(String userName);
}
