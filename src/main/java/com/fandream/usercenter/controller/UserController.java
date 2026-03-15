package com.fandream.usercenter.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.fandream.usercenter.common.BaseResponse;
import com.fandream.usercenter.common.PageResult;
import com.fandream.usercenter.dto.UserDto;
import com.fandream.usercenter.exception.MyBizException;
import com.fandream.usercenter.model.User;
import com.fandream.usercenter.service.UserService;
import com.fandream.usercenter.service.impl.UserServiceImpl;
import com.fandream.usercenter.vo.UserVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * UserController
 *
 * @author fandream
 * @date 2026-03-11 18:08:47
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping
    public BaseResponse<UserVo> get(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(UserServiceImpl.USER_LOGIN_STATE);
        if (user == null) {
            throw new MyBizException("请先登录！");
        }
        return BaseResponse.success(BeanUtil.copyProperties(userService.getById(user.getId()), UserVo.class));
    }


    @PostMapping("/register")
    public BaseResponse<Boolean> register(@RequestBody UserDto userDto) {
        boolean isSuccess = userService.register(userDto.getAccount(), userDto.getPassword(), userDto.getCheckPassword()) > 0;
        return BaseResponse.success(isSuccess);
    }

    @PostMapping("/outLogin")
    public BaseResponse<Boolean> outLogin(HttpSession session) {
        session.removeAttribute(UserServiceImpl.USER_LOGIN_STATE);
        return BaseResponse.success(true);
    }

    @PostMapping("login")
    public BaseResponse<UserVo> login(@RequestBody UserDto userDto, HttpServletRequest request) {
        User curUser = userService.login(userDto.getAccount(), userDto.getPassword(), request);
        return BaseResponse.success(BeanUtil.copyProperties(curUser, UserVo.class));
    }

    @GetMapping("/list")
    public BaseResponse<PageResult<UserVo>> list(@RequestParam(required = false) String username, HttpServletRequest request) {
        if (notAdmin(request)){
            throw new MyBizException("您没有权限操作！");
        }
        List<User> userList = userService.lambdaQuery()
                .like(StrUtil.isNotBlank(username), User::getUsername, username)
                .list();
        List<UserVo> voList = userList
                .stream()
                .map(user -> BeanUtil.copyProperties(user, UserVo.class))
                .toList();
        return BaseResponse.success(PageResult.successWithDate(voList));
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> delete(@RequestBody List<Long> idList, HttpServletRequest request) {
        if (notAdmin(request)){
            throw new MyBizException("您没有权限操作！");
        }
        for (Long id : idList) {
            userService.removeById(id);
        }
        return BaseResponse.success(true);
    }

    private boolean notAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(UserServiceImpl.USER_LOGIN_STATE);
        return user == null || user.getRoleId() != 0L;
    }
}
