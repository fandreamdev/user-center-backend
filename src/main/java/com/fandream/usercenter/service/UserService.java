package com.fandream.usercenter.service;

import com.fandream.usercenter.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author fandream
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2026-03-11 18:10:39
*/
public interface UserService extends IService<User> {

    long register(String account, String password, String checkPassword);

    User login(String account, String password, HttpServletRequest request);
}
