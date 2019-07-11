package com.cloud.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.net.URLDecoder;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Created  by sun on 2017/9/13.
 */
public class DesUtil {
    private static final String DESKEY = "ssh$shz*"; // 字节数必须是8的倍数
    private static final byte[] iv1 = { (byte) 0x38, (byte) 0x37, (byte) 0x36, (byte) 0x35, (byte) 0x34, (byte) 0x33, (byte) 0xC32, (byte) 0x31 };

    /**
     * 解密
     * @param datas 密文
     */
    public static String decrypt(String datas){
        return decrypt(datas, DESKEY);
    }
    public static String decrypt(String datas, String key){
        if (key == null){
            key = DESKEY;
        }
        byte[] bytes = decode(hex2byte(datas.getBytes()), key);
        if (bytes == null){
            return "";
        }else {
            try {
                return URLDecoder.decode(new String(bytes), "utf-8");
            } catch (Exception e) {
                return "";
            }

        }
    }

    //加密
    public static String encrypt(String input) {
        return encrypt(input, DESKEY);
    }
    public static String encrypt(String input, String key) {
        if (key == null){
            key = DESKEY;
        }
        try {
            return parseByte2HexStr(desEncrypt(input.getBytes(), key));
        } catch (Exception e) {
            return "";
        }
    }

    //加密
    private static byte[] desEncrypt(byte[] plainText, String desKey) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(iv1);
        DESKeySpec dks = new DESKeySpec(desKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(plainText);
    }

    /**
     * DES算法，解密
     * @param data 待解密字符串
     * @return 解密后的字节数组
     */
    private static byte[] decode(byte[] data, String desKey){
        try{
            DESKeySpec dks = new DESKeySpec(desKey.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv1);
            cipher.init(Cipher.DECRYPT_MODE, secretKey,paramSpec);
            return cipher.doFinal(data);
        } catch (Exception e){
//         e.printStackTrace();
            return null;
        }
    }




    /**
     * 将二进制转换成16进制
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (byte aBuf : buf) {
            String hex = Integer.toHexString(aBuf & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
//			sb.append(hex.toUpperCase());
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    private static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    public String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp = "";
        for (byte aB : b) {
            stmp = (Integer.toHexString(aB & 0XFF));
            if (stmp.length() == 1)
                hs.append("0").append(stmp);
            else
                hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }
}
