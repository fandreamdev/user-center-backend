package com.fandream.usercenter.model;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@Data
@TableName(value ="user")
public class User {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    @TableField(value = "user_account")
    private String userAccount;

    /**
     * 用户名称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户头像
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 性别(1: 男性，2: 女性)
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户状态（1：正常；2：锁定；3：注销）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否删除（0：正常，1：删除）
     */
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;

    @TableField(value = "role_id")
    private Long roleId;
}