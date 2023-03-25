package com.mode.technology.open.vo;

import com.mode.technology.open.constants.LanguageConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author heian
 * @date 2022/6/22 23:32
 */
@Setter
@Getter
@Accessors(chain = true)
@ApiModel(description = "单词查询请求")
public class WordQueryReq implements Serializable {


    @ApiModelProperty(value = "待查询的文字",required = true)
    private String q;

    /**
     * @see LanguageConstants
     */
    @ApiModelProperty(value = "源语言",notes = "枚举类型查看LanguageConstants",required = true)
    private String from;

    @ApiModelProperty(value = "目标语言",notes = "枚举类型查看LanguageConstants",required = true)
    private String to;

    @ApiModelProperty(value = "应用ID",required = true)
    private String appKey;

    @ApiModelProperty(value = "UUID",notes = "uuid，唯一通用识别码",required = true)
    private String salt;

    @ApiModelProperty(value = "签名",notes = "sha256(应用ID+input+salt+curtime+应用密钥)",required = true)
    private String sign;

    @ApiModelProperty(value = "签名类型",notes = "v3",required = true)
    private String signType;

    @ApiModelProperty(value = "当前UTC时间戳(秒)",notes = "TimeStamp",required = true)
    private String curtime;

    @ApiModelProperty(value = "翻译结果音频格式，支持mp3",notes = "TimeStamp",required = false)
    private String ext;

    @ApiModelProperty(value = "翻译结果发音选择",notes = "0为女声，1为男声。默认为女声",required = false)
    private String voice;

    @ApiModelProperty(value = "是否严格按照指定from和to进行翻译：true/false",notes = "如果为false，则会自动中译英，英译中。默认为false",required = false)
    private String strict;

    @ApiModelProperty(value = "您的用户词表ID（用户上传的词典）",notes = "用户指定的词典 out_id，目前支持英译中",required = false)
    private String vocabId;

}
