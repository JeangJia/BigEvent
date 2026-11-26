package com.jeang.bigeventbackend.pojo.dto;

import com.jeang.bigeventbackend.validated.Rule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 注册入参：只接收用户名和密码，其余资料后续通过更新接口补充。
 */
@Data
public class UserRegisterDTO {

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = Rule.USERNAME_REGEX, message = "用户名长度必须为5-16位且不含空格")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = Rule.PASSWORD_REGEX, message = "密码长度必须为5-16位且不含空格")
    private String password;
}
