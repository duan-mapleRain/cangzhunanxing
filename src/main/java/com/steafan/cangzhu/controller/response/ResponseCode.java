package com.steafan.cangzhu.controller.response;

public record ResponseCode() {

    /**
     * 状态码
     */
    public static final int SUCCESS = 200;

    /**
     * 对应信息
     */
    public static final String LOGIN_SUCCESS = "登录成功";
    public static final String REGISTER_SUCCESS = "注册成功";
    public static final String REGISTER_FAIL = "注册失败，邮箱已存在";


    public static final String UPDATE_PASSWORD_SUCCESS = "密码修改成功";
    public static final String UPDATE_INFO_SUCCESS = "账号信息更新成功";


}
