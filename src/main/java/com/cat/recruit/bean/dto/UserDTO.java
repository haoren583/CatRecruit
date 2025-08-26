package com.cat.recruit.bean.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cat.recruit.entity.User;
import lombok.Builder;
import lombok.Data;

/**
 * 用于向query方法传递参数的DTO对象
 */
@Data
@Builder
public class UserDTO {
    /**
     * 主键
     */
    private Integer userId;

    /**
     * 用户唯一标识
     */
    private String openId;

    /**
     * 临时登录凭证
     */
    private String code;

    /**
     * 用户名字（用户专用）
     */
    private String name;

    /**
     * 学号
     */
    private String userNumber;

    /**
     * 学院专业
     */
    private String academy;

    /**
     * 联系方式（手机）
     */
    private String phoneNumber;

    /**
     * 联系方式（邮箱）
     */
    private String email;

    /**
     * 自我介绍
     */
    private String userIntro;

    /**
     * 学习方向（前后端）
     */
    private String direction;

    /**
     * 状态（如一面，二面，初面）
     */
    private String state;

    /**
     * 权限
     */
    private String role;

    /**
     * 用户名（管理员专用）
     */
    private String username;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.openId = user.getOpenId();
        this.code = user.getCode();
        this.name = user.getName();
        this.userNumber = user.getUserNumber();
        this.academy = user.getAcademy();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.userIntro = user.getUserIntro();
        this.direction = user.getDirection();
        this.state = user.getState();
        this.role = user.getRole();
        this.username = user.getUsername();
    }

}
