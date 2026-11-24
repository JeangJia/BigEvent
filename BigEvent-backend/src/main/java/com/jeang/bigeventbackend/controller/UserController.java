package com.jeang.bigeventbackend.controller;

import com.jeang.bigeventbackend.pojo.Resulat;
import com.jeang.bigeventbackend.pojo.User;
import com.jeang.bigeventbackend.service.UserService;
import com.jeang.bigeventbackend.utils.Md5Util;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // register
    @PostMapping("/register")
    public Resulat register(@RequestBody @Valid User user) {
        User byUsername = userService.findByUsername(user.getUsername());
        if (byUsername != null) {
            return Resulat.error("用户名已存在");
        } else {
            return userService.register(user);
        }
    }

    // login
    @PostMapping("/login")
    public Resulat login(@RequestBody User user) {
        User byUsername = userService.findByUsername(user.getUsername());
        if (byUsername == null) {
            return Resulat.error("用户名不存在");
        }
        if (!Md5Util.encrypt(user.getPassword()).equals(byUsername.getPassword())) {
            return Resulat.error("密码错误");
        }
        return Resulat.success();
    }
}
