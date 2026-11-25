package com.jeang.bigeventbackend.controller;

import com.jeang.bigeventbackend.pojo.Resulat;
import com.jeang.bigeventbackend.pojo.User;
import com.jeang.bigeventbackend.service.UserService;
import com.jeang.bigeventbackend.utils.JwtUtil;
import com.jeang.bigeventbackend.utils.Md5Util;
import com.jeang.bigeventbackend.utils.ThreadLocalUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", byUsername.getId());
        claims.put("username", byUsername.getUsername());
        String token = JwtUtil.genToken(claims);
        return Resulat.success(token);
    }

    // userInfo
    @GetMapping("/userInfo")
    public Resulat userInfo(/* @RequestHeader(name = "Authorization") String token */) {
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");
        // 使用ThreadLocal获取token中的数据
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Resulat.success(user);
    }
}
