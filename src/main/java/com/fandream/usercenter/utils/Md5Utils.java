package com.fandream.usercenter.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.crypto.digest.MD5;

import java.nio.charset.StandardCharsets;

/**
 * Md5Utils
 *
 * @author fandream
 * @date 2026-03-11 18:55:24
 */
public class Md5Utils {
    public static final MD5 M = MD5.create();

    public static final byte[] SALT = "hello, fandream".getBytes(StandardCharsets.UTF_8);

    public static String digestAsHex(String data) {
        byte[] byteData = data.getBytes(StandardCharsets.UTF_8);
        byte[] saltedData = ArrayUtil.addAll(byteData, SALT);
        return M.digestHex(saltedData);
    }
}
