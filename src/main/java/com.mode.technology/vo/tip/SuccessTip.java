package com.mode.technology.vo.tip;

/**
 * 返回给前台的成功提示
 * @author heian
 */
public class SuccessTip<T> extends Tip<T> {

	private final static int SUCCESS_CODE = 200;

	private final static String DEFAULT_MSG = "操作成功";

	public SuccessTip(){
		this.code = SUCCESS_CODE;
		this.message = DEFAULT_MSG;
		this.time = System.currentTimeMillis();
		this.data = (T) new Object();
	}

	public SuccessTip(T data){
		this.code = SUCCESS_CODE;
		this.message = DEFAULT_MSG;
		this.time = System.currentTimeMillis();
		this.data = data;
	}

}
