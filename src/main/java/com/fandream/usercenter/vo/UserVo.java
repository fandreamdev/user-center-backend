package com.fandream.usercenter.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * UserVo
 *
 * @author fandream
 * @date 2026-03-11 19:32:26
 */
@Data
public class UserVo {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 性别(1: 男性，2: 女性)
     */
    private Integer gender;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户状态（1：正常；2：锁定；3：注销）
     */
    private Integer status;

    private Long roleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
