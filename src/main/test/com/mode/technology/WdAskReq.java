package com.mode.technology;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
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
public class WdAskReq {

    /**
     * {
     *   "product": "xxxx",
     *   "query": {
     *     "phone": "95533",
     *     "signs": [
     *       "5bu66K6+6ZO26KGM",
     *       "5rip6aao5o+Q6YaS"
     *     ]
     *   }
     * }
     */
    @ApiModelProperty(value = "产品名" , required = true)
    private String product;

    @ApiModelProperty(value = "查询的实体内容" , required = false)
    private Query query;


    @Data
    @Accessors(chain = true)
    public static class Query {
        @ApiModelProperty(value = "电话" , required = true)
        private String phone;

        @ApiModelProperty(value = "签名，可以有多个，需要base64" , required = true)
        private List<String> signs;
    }



}
