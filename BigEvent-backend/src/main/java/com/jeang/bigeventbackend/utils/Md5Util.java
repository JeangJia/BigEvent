package com.jeang.bigeventbackend.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class Md5Util {

    private Md5Util() {
    }

    /**
     * 32 位小写 MD5
     */
    public static String encrypt(String raw) {
        return DigestUtils.md5DigestAsHex(raw.getBytes(StandardCharsets.UTF_8));
    }
}
