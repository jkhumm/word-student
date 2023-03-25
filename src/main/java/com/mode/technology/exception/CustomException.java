package com.mode.technology.exception;

import com.mode.technology.enums.HttpCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author heian
 * @data 2020/6/12 9:45
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -7787200346109889949L;

    private int code;

    private String message;

    public CustomException(String message) {
        this.code = HttpCodeEnum.ERROR.getCode();
        this.message = message;
    }

    public CustomException(HttpCodeEnum httpCodeEnum) {
        this.code = httpCodeEnum.getCode();
        this.message = httpCodeEnum.getMessage();
    }

    public CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
