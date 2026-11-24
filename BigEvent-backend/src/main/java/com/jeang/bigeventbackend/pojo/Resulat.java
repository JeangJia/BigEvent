package com.jeang.bigeventbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Resulat<T> {
    private Integer code;
    private String msg;
    private T data;

    // 返回操作成功响应结果(带操作数据)
    public static <E> Resulat<E> success(E data) {
        return new Resulat<>(Code.SUCCESS, "success", data);
    }

    // 返回操作成功响应结果(不带操作数据)
    public static <E> Resulat<E> success() {
        return new Resulat<>(Code.SUCCESS, "success", null);
    }

    // 操作失败的响应结果
    public static <E> Resulat<E> fail(String msg) {
        return new Resulat<>(Code.FAIL, msg, null);
    }

}
