package com.mode.technology.enums;

/**
 * @author heian
 * @date 2022/6/20 23:43
 * http 状态码
 * ----------------------------------------------------------------------------
 * 200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
 * 400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
 * 401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
 * 403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
 * 404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
 * 406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
 * 410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
 * 422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
 * 500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。
 * 600 UN_KNOW_ERROR - 未知错误
 * ----------------------------------------------------------------------------
 */
public enum HttpCodeEnum {

    OK(200, "操作成功"),
    FAILURE(400, "业务异常"),
    USERNAME_OR_PASSWORD_ERR(401, "请登录！"),
    NOT_FOUND(404, "服务未找到"),
    REQUIRED_REQUEST_BODY_IS_MISSING(424, "输入内容格式不正确"),
    METHOD_ARGUMENT_TYPE_MISMATCH(425, "方法参数类型不匹配异常,请确认请求路径、请求方式是否正确"),
    ERROR(500, "服务器繁忙"),
    ILLEGAL_OPERATION(501,"非法操作"),


    /**************  业务异常具体枚举 ***************/
    INSUFFICIENT_BALANCE(501, "金币/钻石不足，请前去充值"),
    EXCEL_ERROR(502, "excel解析异常"),
    SENSITIVE_WORD_ERROR(503, "存在敏感词汇"),
    NOT_ANCHOR(504,"非主播身份"),
    CURRENT_USER_FAIL(505, "获取当前用户失败"),
    GLOBAL_PARAM_ERROR(506, "参数错误"),
    ACCOUNT_LOGOUT(507, "账号注销"),
    ACCOUNT_BLOCK(508, "账号被封禁"),
    NO_SUIT(509, "暂无装备，请前去添加"),
    ALREADY_CLICK_LIKE_COVER(510, "当前装扮您已经点过赞，无需再次点击"),









    ;
    private final int code;
    private final String message;

    HttpCodeEnum(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
