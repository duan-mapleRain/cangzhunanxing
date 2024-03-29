package com.steafan.cangzhu.api.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.steafan.cangzhu.api.enums.ResponseCode;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BaseResponse<T>(int responseCode, String message, T data) implements Serializable {

    public static <T> BaseResponse<T> success() {
        return success(null, null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return success(null, data);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(ResponseCode.SUCCESS, message, data);
    }


    public static <T> BaseResponse<T> fail(int code, String msg) {
        return fail(code, msg, null);
    }

    public static <T> BaseResponse<T> fail(int code, String msg, T data) {
        return new BaseResponse<>(code, msg, data);
    }


}
