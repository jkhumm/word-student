package com.mode.technology.vo.tip;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回给前台的提示（最终转化为json形式）
 * @author heian
 */
@Data
public class Tip<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码", required = true)
    protected int code;

    @ApiModelProperty(value = "消息内容", required = true)
    protected String message;

    @ApiModelProperty(value = "业务数据")
    protected T data;

    @ApiModelProperty(value = "时间戳", required = true)
    protected long time;

}
