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
public class WdReq {

    /**
     * {
     *   "header": {
     *     "appid": "yluRDy9S",
     *     "nonce": "e481497bc9051d3a",
     *     "timestamp": 1646654023408,
     *     "v": "1.0",
     *     "sign": "82c1501cae876968"
     *   },
     *   "uid": "111-111-111-10",
     *   "ask": "TSfVv4U4/KokF0OQTr8Xe1xZiCQvLhAiQonGnoFC9hX8DWWWZqBvELoR4bgVwPJAczSWyONJJutI0xj/rmmAObuGOxPErV554l+c2mXTxth5FGlm5mdt2Aa5eNLqlpom"
     * }
     */

    private WdHeaderDataReq header;

    @ApiModelProperty(value = " 非必填, 用户唯一标识,例如用户id或通过某种算法算出来的用户唯一标识", required = false)
    private String uid;

    /**
     * @see WdAskReq
     * 最后生成经过AES CBC加密,再进行base64加密之后的字符串,密钥使用之前分配的secret,iv使用计算出的sign
     * 注意:当使用AES CBC加密时,可能需要以下操作:如果需要加密的字节数组长度不是16的整数倍,需要使用空字节去填充
     */
    @ApiModelProperty(value = "ask 请求体", required = true)
    private String ask;




}
