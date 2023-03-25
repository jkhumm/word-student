package com.mode.technology.vo.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class UserBean {

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "手机号")
    private String mobile;

}
