package com.fandream.usercenter.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fandream.usercenter.exception.MyBizException;
import com.fandream.usercenter.mapper.UserMapper;
import com.fandream.usercenter.model.User;
import com.fandream.usercenter.service.UserService;
import com.fandream.usercenter.utils.Md5Utils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Pattern;

/**
* @author fandream
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2026-03-11 18:10:39
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    public static final Pattern SPECIAL_CHAR_REG = Pattern.compile("[\\u00A0\\s\"`~!@#$%^&*()+=|{}':;,\\[\\].<>/?！￥…（）—【】‘；：”“’。，、？]") ;
    public static final String USER_LOGIN_STATE = "userLoginState";
    @Resource
    private UserMapper userMapper;


    @Override
    public long register(String account, String password, String checkPassword) {
        if (StrUtil.hasBlank(account, password, checkPassword)) {
            throw new MyBizException("账号或密码不能为空！");
        }
        unValid(account, password);

        if (!password.equals(checkPassword)) {
            throw new MyBizException("两次密码不一致！");
        }

        long count = userMapper.selectCount(this.lambdaQuery()
                .eq(User::getUserAccount, account)
                .eq(User::getDeleted, 0).getWrapper());
        if (count > 0) {
            throw new MyBizException("账号已存在！");
        }

        User user = new User();
        user.setUserAccount(account);
        user.setPassword(Md5Utils.digestAsHex(password));
        user.setStatus(1);
        user.setRoleId(1L);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setDeleted(0);
        if (!this.save(user)) {
            throw new MyBizException("保存失败！");
        }
        return user.getId();
    }

    @Override
    public User login(String account, String password, HttpServletRequest request) {
        if (StrUtil.hasBlank(account, password)) {
            throw new MyBizException("账号或密码不能为空！");
        }

        unValid(account, password);

        User user = userMapper.selectOne(this.lambdaQuery()
                .eq(User::getUserAccount, account).getWrapper());
        if (user == null) {
            throw new MyBizException("账号或密码错误！");
        }

        if (user.getStatus() != 1) {
            throw new MyBizException("当前账号已注销或已锁定，请联系管理员！");
        }

        if (!Md5Utils.digestAsHex(password).equals(user.getPassword())) {
            throw new MyBizException("账号或密码错误！");
        }

        HttpSession session = request.getSession();
        session.setAttribute(USER_LOGIN_STATE, user);

        return user;
    }


    private void unValid(String account, String password) {
        if (account.length() < 4) {
            throw new MyBizException("账号不能小于4位！");
        }
        if (password.length() < 8) {
            throw new MyBizException("密码不能小于8位！");
        }

        if (SPECIAL_CHAR_REG.matcher(account).find()) {
            throw new MyBizException("账号不能包含特殊字符！");
        }
    }
}




