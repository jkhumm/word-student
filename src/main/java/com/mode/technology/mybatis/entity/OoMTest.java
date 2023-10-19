package com.mode.technology.mybatis.entity;

import lombok.Data;

/**
 * @author heian
 * @date 2023/10/19 22:14
 */
@Data
public class OoMTest {

    private String str = "这是1个测试字符串";

    // 10M
    private Byte[] bytes = new Byte[1024*1024*10];

}
