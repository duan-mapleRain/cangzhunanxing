package com.steafan.cangzhu.enums;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum HttpStatus {


    USER_EXIST(20001, "用户已存在"),
    USER_NO_EXIST(20002, "用户不存在"),
    USER_INFO_UPDATE_FAILED(20003, "用户信息与mysql交互失败"),
    USER_LOGIN_FAIL(20004, "账户或密码错误"),
    USER_OLD_PASSWORD_ERROR(20005, "旧密码错误"),

    DEVICE_TYPE_EXIST(30001,"设备类型已存在"),

    DEVICE_TYPE_NO_EXIST(30002,"设备类型不存在"),

    DEVICE_TYPE_INFO_UPDATE_FAILED(30003,"设备类型与mysql交互失败"),


    DEVICE_EXIST(40001,"设备已存在"),

    DEVICE_NO_EXIST(40002,"设备不存在"),

    DEVICE_INFO_UPDATE_FAILED(40003,"设备信息与mysql交互失败");


    public final int code;
    public final String message;
}
