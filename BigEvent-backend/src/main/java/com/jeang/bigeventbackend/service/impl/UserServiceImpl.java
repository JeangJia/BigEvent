package com.jeang.bigeventbackend.service.impl;

import com.jeang.bigeventbackend.mapper.UserMapper;
import com.jeang.bigeventbackend.pojo.Result;
import com.jeang.bigeventbackend.pojo.User;
import com.jeang.bigeventbackend.service.UserService;
import com.jeang.bigeventbackend.utils.Md5Util;
import com.jeang.bigeventbackend.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public Result<Void> register(User user) {
        user.setPassword(Md5Util.encrypt(user.getPassword()));
        userMapper.insertUser(user);
        return Result.success();
    }

    @Override
    public boolean update(User user) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        user.setId(id);
        return userMapper.updateUser(user);
    }

    @Override
    public void updateAvatar(String url) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(url, id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.encrypt(newPwd), id);
    }
}
