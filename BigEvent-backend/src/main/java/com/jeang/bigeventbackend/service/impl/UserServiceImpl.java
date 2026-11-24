package com.jeang.bigeventbackend.service.impl;

import com.jeang.bigeventbackend.mapper.UserMapper;
import com.jeang.bigeventbackend.pojo.Resulat;
import com.jeang.bigeventbackend.pojo.User;
import com.jeang.bigeventbackend.service.UserService;
import com.jeang.bigeventbackend.utils.Md5Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public Resulat<Void> register(User user) {
        user.setPassword(Md5Util.encrypt(user.getPassword()));
        userMapper.insertUser(user);
        return Resulat.success();
    }
}
