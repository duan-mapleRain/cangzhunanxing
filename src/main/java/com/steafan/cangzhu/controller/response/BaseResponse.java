package com.steafan.cangzhu.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.steafan.cangzhu.enums.ResponseCode;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BaseResponse<T>(int responseCode, String message, T data) implements Serializable {


    public static BaseResponse success() {
        return new BaseResponse(ResponseCode.SUCCESS, null, null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse(ResponseCode.SUCCESS, null, data);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse(ResponseCode.SUCCESS, message, data);
    }


    public static <T> BaseResponse<T> fail(int code, String msg) {
        return new BaseResponse(code, msg, null);
    }

    public static <T> BaseResponse<T> fail(int code, String msg, T data) {
        return new BaseResponse(code, msg, null);
    }


}
