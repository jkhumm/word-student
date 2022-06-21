package com.mode.technology.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author heian
 * @date 2022/6/21 0:30
 */
@Setter
@Getter
@ApiModel(description = "查询单词请求")
public class WordQueryReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单词", required = true)
    @NotBlank(message = "单词不能为空")
    private String word;

    @ApiModelProperty(value = "单词类型", required = true)
    @NotBlank(message = "单词类型不能为空")
    private String type;


}
