package com.jeang.bigeventbackend.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeang.bigeventbackend.validation.Rule;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;//主键ID

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = Rule.USERNAME_REGEX, message = "用户名长度必须为5-16位且不含空格")
    private String username;//用户名

    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = Rule.PASSWORD_REGEX, message = "密码长度必须为5-16位且不含空格")
    private String password;//密码

    private String nickname;//昵称

    @Email(message = "邮箱格式不正确")
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
