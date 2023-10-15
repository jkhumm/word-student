package com.mode.technology;

import com.alibaba.fastjson.JSON;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author humingming
 * @date 2023/9/4 11:31
 * @description 微队-号码识别 https://easydoc.soft.360.cn/doc?project=c2aaced9777c70b3b75e9dd0a314adf9&doc=afadb8f99d568f6d5a2fc75b3c160842&config=title
 */
@Slf4j
@Component
public class WDHttpUtil {

    public static final String APPID = "KM9rRQjn";
    public static final String SECRET = "3PEcTMPwQ0lyDggX";
    public static final String VERSION = "1.0";
    public static final String AddressUrl1 = "http://openapi.shouji.360.cn/OpenapiMerchantIdentify";
    public static final String AddressUrl2 = "http://openapi.shouji.360.cn/OpenapiMerchantIdentifyV2";
    public static final String AddressUrlTest = "http://180.163.246.125/OpenapiMerchantIdentifyV2";
    public static final String PRODUCT = "oppo";


    private static String getSign(WdAskReq originalAsk, String nonce, long timestamp) {

        Map<String, String> header = new TreeMap<>();
        header.put("appid", APPID);
        header.put("v", VERSION);
        header.put("nonce", nonce);
        header.put("timestamp", String.valueOf(timestamp));
        // header 排序之后把val进行字符串拼接
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> entry : header.entrySet()) {
            sb.append(entry.getValue());
        }
        sb.append(JSON.toJSONString(originalAsk)).append(SECRET);
        byte[] digest = DigestUtils.md5Digest(sb.toString().getBytes(StandardCharsets.UTF_8));
        // 将结果转换为十六进制字符串，后截取16位
        StringBuilder result = new StringBuilder();
        for (byte b : digest) {
            result.append(String.format("%02x", b & 0xff));
        }
        return result.substring(16);
    }

    public static void doWdRecognize(String phone, List<String> signs) throws Exception {
        String nonce = "123456789"; //UUID.randomUUID().toString()
        long timestamp = 1694136742327L;
        System.out.println(timestamp);
        List<String> baseSigns = signs.stream().map(s -> Base64Utils.encodeToString(s.getBytes(StandardCharsets.UTF_8))).collect(Collectors.toList());

        WdAskReq originalAsk = new WdAskReq()
                .setProduct(PRODUCT)
                .setQuery(new WdAskReq.Query()
                        .setPhone(phone)
                        .setSigns(baseSigns)
                );
        // 24.76
        WdHeaderDataReq header = new WdHeaderDataReq()
                .setTimestamp(timestamp)
                .setNonce(nonce)
                .setSign(getSign(originalAsk, nonce, timestamp));

        String ask = AESExample.encrypt(JSON.toJSONString(originalAsk), header.getSign());

        WdReq body = new WdReq().setHeader(header).setAsk(ask).setUid(null);
        String h = MessageHeader.buildRequestHeader();
        // 6个字节的二进制报文头 + JSON字符串报文体
        String total = h + JSON.toJSONString(body);
        System.out.println("请求参数：" + total);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.TEXT_PLAIN_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(total, headers);
        ResponseEntity<String> response = new RestTemplate().postForEntity(AddressUrlTest, entity, String.class);
        HttpStatus code = response.getStatusCode();
        if (code.value() != 200) {
            System.out.println("接口调用失败！");
        }else {
            String responseBody = response.getBody();
            System.out.println(responseBody);
        }
        log.info("---------- 接口响应：" + response);

    }


    public static void main(String[] args) throws Exception {
        String phone = "10086";
        List<String> signs = Collections.singletonList("中国移动");
        doWdRecognize(phone, signs);
    }


}
