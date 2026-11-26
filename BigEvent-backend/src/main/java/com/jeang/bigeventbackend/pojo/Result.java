package com.jeang.bigeventbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    // 返回操作成功响应结果(带操作数据)
    public static <E> Result<E> success(E data) {
        return new Result<>(Code.SUCCESS, "success", data);
    }

    // 返回操作成功响应结果(不带操作数据)
    public static <E> Result<E> success() {
        return new Result<>(Code.SUCCESS, "success", null);
    }

    // 操作失败的响应结果
    public static <E> Result<E> error(String msg) {
        return new Result<>(Code.FAIL, msg, null);
    }

}
