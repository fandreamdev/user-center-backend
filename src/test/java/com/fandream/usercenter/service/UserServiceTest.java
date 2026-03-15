package com.fandream.usercenter.service;
import java.util.Date;
import java.util.List;

import com.fandream.usercenter.model.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @Resource
    UserService userService;


    @Test
    public void testAddUser() {
        User user = new User();
        user.setUserAccount("fandream");
        user.setUsername("fandream");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setPassword("11111111");
        user.setPhone("17839945513");
        user.setEmail("2435878928@qq.com");
        user.setStatus(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setDeleted(0);
        userService.save(user);
    }

    @Test
    public void testRegister() {
        long tom = userService.register("to.<>m1", "11111111", "11111111");
        System.out.println(tom);
    }

    @Test
    public void testGetUser() {
        List<User> tom1 = userService.lambdaQuery().eq(User::getUserAccount, "tom1").list();
        System.out.println(tom1);
    }

    @Test
    public void testLogin(HttpServletRequest request) {
        User user = userService.login("tom1", "11111111", request);
        System.out.println(user);
    }
}