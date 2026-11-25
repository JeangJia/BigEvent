package com.jeang.bigeventbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jeang.bigeventbackend.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

    @Insert("insert into user(username, password, create_time, update_time) " +
            "values(#{username}, #{password},now(), now())")
    void insertUser(User user);

    // 只能更改 昵称、邮箱
    @Update("update user " +
            "set nickname = #{nickname}, email = #{email}, update_time = now() " +
            "where id = #{id}")
    boolean updateUser(User user);
}
