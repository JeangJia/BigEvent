package com.jeang.bigeventbackend.controller;

import com.jeang.bigeventbackend.pojo.Resulat;
import com.jeang.bigeventbackend.pojo.User;
import com.jeang.bigeventbackend.pojo.dto.UserRegisterDTO;
import com.jeang.bigeventbackend.service.UserService;
import com.jeang.bigeventbackend.utils.JwtUtil;
import com.jeang.bigeventbackend.utils.Md5Util;
import com.jeang.bigeventbackend.utils.ThreadLocalUtil;
import com.jeang.bigeventbackend.pojo.Rule;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.URL;
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
    public Resulat register(@RequestBody @Validated UserRegisterDTO dto) {
        User byUsername = userService.findByUsername(dto.getUsername());
        if (byUsername != null) {
            return Resulat.error("用户名已存在");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return userService.register(user);
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

    // update
    @PutMapping("/update")
    public Resulat update(@RequestBody @Validated User user) {
        if (userService.update(user))
            return Resulat.success(null);
        return Resulat.error("更新失败");
    }

    // updateAvatar
    @PatchMapping("/updateAvatar")
    public Resulat updateAvatar(@RequestParam("avatarUrl") @URL String url) {
        userService.updateAvatar(url);
        return Resulat.success();
    }

    // updatePwd
    @PatchMapping("/updatePwd")
    public Resulat updatePwd(@RequestBody Map<String, String> params) {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (StringUtils.isAnyBlank(oldPwd, newPwd, rePwd)) {
            return Resulat.error("缺少必要参数!");
        }
        // 通过ThreadLocal获取token中的数据
        Map<String, Object> map = ThreadLocalUtil.get();
        User user = userService.findByUsername((String) map.get("username"));
        if (!user.getPassword().equals(Md5Util.encrypt(oldPwd))) {
            return Resulat.error("旧密码错误");
        }
        if (!newPwd.matches(Rule.PASSWORD_REGEX)) {
            return Resulat.error("新密码长度必须为5-16位且不含空格");
        }
        if (!rePwd.equals(newPwd)) {
            return Resulat.error("两次密码不一致");
        }
        userService.updatePwd(newPwd);
        return Resulat.success();
    }
}
