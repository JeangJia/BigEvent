package com.jeang.bigeventbackend.service;

import com.jeang.bigeventbackend.pojo.Resulat;
import com.jeang.bigeventbackend.pojo.User;

public interface UserService {
    // 根据用户名查询用户
    User findByUsername(String username);

    // 注册用户
    Resulat<Void> register(User user);

    // 更新用户信息
    boolean update(User user);

    // 更新头像
    void updateAvatar(String url);

    // 修改密码
    void updatePwd(String newPwd);
}
