package com.jeang.bigeventbackend.validation;

/**
 * 参数校验规则集中管理。
 * 注解的 regexp 要求编译期常量，所以这里只用 static final String。
 */
public final class Rule {

    private Rule() {
    }

    /** 用户名：5-16 位，不含空白字符 */
    public static final String USERNAME_REGEX = "^\\S{5,16}$";

    /** 密码：5-16 位，不含空白字符 */
    public static final String PASSWORD_REGEX = "^\\S{5,16}$";

    /** 昵称：1-10 位，不含空白字符 */
    public static final String NICKNAME_REGEX = "^\\S{1,10}$";
}
