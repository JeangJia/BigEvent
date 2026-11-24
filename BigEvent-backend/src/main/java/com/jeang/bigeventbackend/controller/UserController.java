package com.jeang.bigeventbackend.controller;

import com.jeang.bigeventbackend.pojo.Resulat;
import com.jeang.bigeventbackend.pojo.User;
import com.jeang.bigeventbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // register
    @PostMapping("/register")
    public Resulat register(@RequestBody User user) {
        User byUsername = userService.findByUsername(user.getUsername());
        if (byUsername != null) {
            return Resulat.fail("用户名已存在");
        } else {
            return userService.register(user);
        }
    }
}
