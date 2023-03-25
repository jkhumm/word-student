package com.mode.technology.enums;

public enum ExceptionEnum {
    SERVER_ERROR(500,"服务器繁忙"),

    TOKEN_INVALID(1001, "token失效"),

    PARAMETER_ERROR(1002, "输入有误"),

    USER_REGISTER_ACCOUNT_EXISTS(1003, "账号已存在，请前往登录"),

    USER_FIND_PASSWORD_ACCOUNT_NOT_EXISTS(1004, "账号不存在，请先注册"),

    ;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    //错误码
    private int code;

    //错误信息
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
