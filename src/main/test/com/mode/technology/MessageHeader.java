package com.mode.technology;

import java.nio.ByteBuffer;
import java.util.Random;

public class MessageHeader {  

    /**
     * 字节	含义
     * 0	报文头大小，目前填0x06
     * 1	报文头版本，目前填0x51
     * 2-3	消息ID，报文唯一标识，从0开始顺序生成，到最大值后归0。类型为16位无符号整数，遵循网络字节序，即big-endian
     * 4-5	保留部分，目前填0x00, 类型为16位无符号整数，遵循网络字节序，即big-endian
     */
    public static String buildRequestHeader() {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        buffer.put((byte) 0x06); // 报文头大小
        buffer.put((byte) 0x51); // 报文头版本
        buffer.put((byte) 123); // 消息ID
        buffer.put((byte) 123); // 消息ID
        buffer.put((byte) 0x00);
        buffer.put((byte) 0x00);
        byte[] bytes = buffer.array();
        return new String(bytes);
    }

    public static String getBodyHeader() {
        byte[] bytes = new byte[] { Byte.parseByte("06", 16), Byte.parseByte("51", 16), (byte) 123,
                (byte) 123, Byte.parseByte("00", 16), Byte.parseByte("00", 16) };
        return new String(bytes);
    }


//    public static void main(String[] args) {
//        System.out.println(buildRequestHeader());
//        // 生成消息ID为12的请求头
//        byte[] bytes = new byte[] { Byte.parseByte("06", 16), Byte.parseByte("51", 16), (byte) 12,
//                (byte) 12, Byte.parseByte("00", 16), Byte.parseByte("00", 16) };
//        System.out.println(new String(bytes));
//
//    }




}