package com.jeang.bigeventbackend.controller;

import com.jeang.bigeventbackend.pojo.Result;
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
    public Result register(@RequestBody @Validated UserRegisterDTO dto) {
        User byUsername = userService.findByUsername(dto.getUsername());
        if (byUsername != null) {
            return Result.error("用户名已存在");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return userService.register(user);
    }

    // login
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return Result.error("用户名或密码错误");
        }
        User byUsername = userService.findByUsername(user.getUsername());
        if (byUsername == null || !Md5Util.encrypt(user.getPassword()).equals(byUsername.getPassword())) {
            return Result.error("用户名或密码错误");
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", byUsername.getId());
        claims.put("username", byUsername.getUsername());
        String token = JwtUtil.genToken(claims);
        return Result.success(token);
    }

    // userInfo
    @GetMapping("/userInfo")
    public Result userInfo(/* @RequestHeader(name = "Authorization") String token */) {
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");
        // 使用ThreadLocal获取token中的数据
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    // update
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        if (userService.update(user))
            return Result.success(null);
        return Result.error("更新失败");
    }

    // updateAvatar
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam("avatarUrl") @URL String url) {
        userService.updateAvatar(url);
        return Result.success();
    }

    // updatePwd
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (StringUtils.isAnyBlank(oldPwd, newPwd, rePwd)) {
            return Result.error("缺少必要参数!");
        }
        // 通过ThreadLocal获取token中的数据
        Map<String, Object> map = ThreadLocalUtil.get();
        User user = userService.findByUsername((String) map.get("username"));
        if (!user.getPassword().equals(Md5Util.encrypt(oldPwd))) {
            return Result.error("旧密码错误");
        }
        if (!newPwd.matches(Rule.PASSWORD_REGEX)) {
            return Result.error("新密码长度必须为5-16位且不含空格");
        }
        if (!rePwd.equals(newPwd)) {
            return Result.error("两次密码不一致");
        }
        userService.updatePwd(newPwd);
        return Result.success();
    }
}
