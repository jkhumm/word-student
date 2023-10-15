package com.mode.technology;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
  
public class AESExample {
    private static final String KEY = WDHttpUtil.SECRET; // 16个字符，128位  密钥使用之前分配的secret
    //private static final String INIT_VECTOR = "abcdefghijklmnop"; // 16个字符，iv使用计算出的sign


    /**
     * 加密
     * iv使用计算的signature
     */
    public static String encrypt(String askJson, String signature) {
        try {
            byte[] rawData = askJson.getBytes(StandardCharsets.UTF_8);
            int size = alignBlockSize(rawData);
            byte[] paddedData = new byte[size];
            System.arraycopy(rawData, 0, paddedData, 0, rawData.length);

            IvParameterSpec iv = new IvParameterSpec(signature.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipherEncrypt = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipherEncrypt.init(Cipher.ENCRYPT_MODE, keySpec, iv);

            byte[] encryptVal = cipherEncrypt.doFinal(paddedData);
            // 根据关联方要求 减去多填充的16个
            encryptVal = Arrays.copyOfRange(encryptVal, 0, encryptVal.length - 16);
            return Base64.getEncoder().encodeToString(encryptVal);
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        return null;  
    }

    /**
     * 解密
     */
//    public static String decrypt(String encrypted, String signature) {
//        try {
//            IvParameterSpec iv = new IvParameterSpec(signature.getBytes(StandardCharsets.UTF_8));
//            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
//            Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//            cipherDecrypt.init(Cipher.DECRYPT_MODE, keySpec, iv);
//
//            byte[] decrypted = cipherDecrypt.doFinal(Base64.getDecoder().decode(encrypted));
//            return new String(decrypted, StandardCharsets.UTF_8);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 注意:当使用AES CBC加密时,可能需要以下操作:如果需要加密的字节数组长度不是16的整数倍,需要使用空字节去填充
     */
    private static int alignBlockSize(byte[] bytes) {
        // 填充原始数据，块大小为16字节
        int yuNum = bytes.length % 16;
        if (yuNum != 0) {
            int paddingLength = 16 - yuNum;
            return bytes.length + paddingLength;
        }else {
            return bytes.length;
        }
    }
  
//    public static void main(String[] args) {
//        String originalString = "hello word";
//        String encryptedString = encrypt(originalString, "abcdefghijklmnop");
//        System.out.println("加密后 - " + encryptedString);
//        String decryptedString = decrypt(encryptedString, "abcdefghijklmnop");
//        System.out.println("解密后 - " + decryptedString);
//    }

}