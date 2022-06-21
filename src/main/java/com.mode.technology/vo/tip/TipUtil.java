package com.mode.technology.vo.tip;


import com.mode.technology.enums.ExceptionEnum;
import com.mode.technology.enums.HttpCodeEnum;

/**
 * @author heian
 * @date 2022/6/20 23:43
 * @description 返回结果工具类
 */
public class TipUtil<T> {


    public static <T> SuccessTip<T> success() {
        return new SuccessTip<T>();
    }

    public static <T> SuccessTip<T> success(T data) {
        return new SuccessTip<T>(data);
    }

    //默认业务失败
    public static <T> ErrorTip<T> error() {
        return new ErrorTip<T>();
    }

    //枚举形式
    public static <T> ErrorTip<T> error(ExceptionEnum exceptionEnum) {
        return new ErrorTip<T>(exceptionEnum);
    }


    //自定义错误msg
    public static <T> ErrorTip<T> error(String errMsg) {
        return new ErrorTip<T>(errMsg);
    }

    //自定义错误msg
    public static <T> ErrorTip<T> error(int code, String errMsg) {
        return new ErrorTip<T>(code, errMsg);
    }

    //错误枚举code
    public static <T> ErrorTip<T> error(HttpCodeEnum httpCodeEnum) {
        return new ErrorTip<T>(httpCodeEnum.getCode(), httpCodeEnum.getMessage());
    }

    //自定义错误结果
    public static <T> ErrorTip<T> error(T data) {
        return new ErrorTip<T>(data);
    }

    public static <T> ErrorTip<T> error(HttpCodeEnum httpCodeEnum,T data) {
        return new ErrorTip<T>(httpCodeEnum,data);
    }

    public static <T> ErrorTip<T> error(int code,String msg,T data) {
        return new ErrorTip<T>(code,msg,data);
    }


}
