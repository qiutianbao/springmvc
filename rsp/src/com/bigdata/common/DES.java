package com.bigdata.common;
/**
 * 描述：
 * 作者：廖明乾
 * 时间：2014-10-24 上午9:52:35
 */
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DES {

    private String key = "B49A86FA425D439dB510A234A3E25A3E";
    private BASE64Decoder decoder = new BASE64Decoder();
    private BASE64Encoder encoder = new BASE64Encoder();
    private final static String DES = "DES";

    public DES() {
    };

    public DES(String key) {
        this.key = key;
    }

    /**
     * 加密
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回加密后的数据
     * @throws Exception
     */
    public byte[] encrypt(byte[] src, byte[] key) throws Exception {
        //DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(src);
    }

    /**
     * 解密
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     * @throws Exception
     */
    public byte[] decrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        // 现在，获取数据并解密
        // 正式执行解密操作
        return cipher.doFinal(src);
    }

    /**
     * 解密字符串
     * @param data
     * @deprecated
     * @return
     * @throws Exception
     */
    public final String decrypt(String data) {
        try {
            return new String(decrypt(hex2byte(data), key.getBytes()));
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 解密字符串
     * @param data
     * @return
     * @throws Exception
     */
    public final String decrypt(String data,String charsetName) {
        try {
            return new String(decrypt(hex2byte(data), key.getBytes()),charsetName);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * @deprecated
     * 加密字符串
     * @param data
     * @return
     * @throws Exception
     */
    public final String encrypt(String data) {
        try {
            return byte2hex(encrypt(data.getBytes(), key.getBytes()));
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 加密字符串
     * @param data
     * @return
     * @throws Exception
     */
    public final String encrypt(String data,String charsetName) {
        try {
            return byte2hex(encrypt(data.getBytes(charsetName), key.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String byte2hex(byte b[]) {
        return encoder.encode(b);
    }

    private byte[] hex2byte(String hex) throws IOException {
        return decoder.decodeBuffer(hex);
    }

    public void setKey(String key) {
        this.key = key;
    }
}