package com.mode.technology;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author humingming
 * @date 2023/9/4 15:19
 */
@ApiModel("微队号码识别请求data")
@Setter
@Getter
@Accessors(chain = true)
public class WdHeaderDataReq {




    @ApiModelProperty(value = "合作伙伴唯一ID，8个字符，每次请求时传递，用于身份识别", required = true)
    private String appid = WDHttpUtil.APPID;

    @ApiModelProperty(value = "精确到毫", required = true)
    private Long timestamp;

    @ApiModelProperty(value = "客戶端请求ip", required = true)
    private String v = WDHttpUtil.VERSION;

    @ApiModelProperty(value = "随机字符串，每次请求都必须不同，防止重放攻击", required = true)
    private String nonce;

    /**
     * 请求参数签名，以下三种字符串拼接后经md5计算出的32个十六进制字符的后16个字符:
     *  1.header按key以字典序排序后，value拼接
     *  2.ask加密之前的原始json字符串
     *  3.secret
     */
    @ApiModelProperty(value = "签名", required = true)
    private String sign;




}
