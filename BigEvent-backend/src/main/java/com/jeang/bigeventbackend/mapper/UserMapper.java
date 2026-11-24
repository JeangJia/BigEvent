package com.jeang.bigeventbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jeang.bigeventbackend.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

    @Insert("insert into user(username, password, create_time, update_time) " +
            "values(#{username}, #{password},now(), now())")
    void insertUser(User user);
}
