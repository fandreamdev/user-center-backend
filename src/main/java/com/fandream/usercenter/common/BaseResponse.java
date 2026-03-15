package com.fandream.usercenter.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * BaseResponse
 *
 * @author fandream
 * @date 2026-03-14 16:07:11
 */
@Data
@AllArgsConstructor
public class BaseResponse<T> {

    private int code;

    private boolean success;

    private String message;

    private T data;


    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, true, "NO-ERROR", data);
    }

    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<>(500, false, message, null);
    }
}
