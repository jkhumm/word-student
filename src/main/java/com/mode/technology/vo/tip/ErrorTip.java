package com.mode.technology.vo.tip;

import com.alibaba.fastjson.JSON;
import com.mode.technology.enums.ExceptionEnum;
import com.mode.technology.enums.HttpCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 返回给前台的错误提示
 * @author heian
 */
public class ErrorTip<T> extends Tip<T> {

	final static private Logger LOGGER = LoggerFactory.getLogger(ErrorTip.class);

	private final static int ERROR_CODE = HttpCodeEnum.FAILURE.getCode();
	private final static String ERROR_MESSAGE = HttpCodeEnum.FAILURE.getMessage();

	public ErrorTip() {
		this.code = ERROR_CODE;
		this.message = ERROR_MESSAGE;
		this.time = System.currentTimeMillis();
		LOGGER.error("ErrorTip: {}/{}", this.code, this.message);
	}

	public ErrorTip(T errResult) {
		this.code = ERROR_CODE;
		this.message = ERROR_MESSAGE;
		this.time = System.currentTimeMillis();
		this.data = errResult;
	}

	public ErrorTip(String message) {
		super();
		this.code = ERROR_CODE;
		this.message = message;
		this.time = System.currentTimeMillis();
		this.data = (T) new Object();
		LOGGER.error("ErrorTip: {}/{}", this.code, this.message);
	}

	public ErrorTip(int code, String message) {
		super();
		this.code = code;
		this.message = message;
		this.time = System.currentTimeMillis();
		this.data = (T) new Object();
		LOGGER.error("ErrorTip: {}/{}", this.code, this.message);
	}

	public ErrorTip(ExceptionEnum exceptionEnum) {
		this.code = exceptionEnum.getCode();
		this.message = exceptionEnum.getMessage();
		this.time = System.currentTimeMillis();
		this.data = (T) new Object();
		LOGGER.error("ErrorTip: {}/{}", this.code, this.message);
	}

	public ErrorTip(HttpCodeEnum httpCodeEnum) {
		this.code = httpCodeEnum.getCode();
		this.message = httpCodeEnum.getMessage();
		this.time = System.currentTimeMillis();
		this.data = (T) new Object();
		LOGGER.error("ErrorTip: {}/{}", this.code, this.message);
	}

	public ErrorTip(HttpCodeEnum httpCodeEnum, T data) {
		this.code = httpCodeEnum.getCode();
		this.message = httpCodeEnum.getMessage();
		this.time = System.currentTimeMillis();
		this.data = data;
		LOGGER.error("ErrorTip: {}/{}", this.code, this.message);
	}

	public ErrorTip(ExceptionEnum exceptionEnum, T data) {
		this.code = exceptionEnum.getCode();
		this.message = exceptionEnum.getMessage();
		this.time = System.currentTimeMillis();
		this.data = data;
		LOGGER.error("ErrorTip: {}/{}/{}", this.code, this.message, JSON.toJSONString(data));
	}

	public ErrorTip(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.time = System.currentTimeMillis();
		this.data = data;
		LOGGER.error("ErrorTip: {}/{}/{}", this.code, this.message, JSON.toJSONString(data));
	}
}
