package com.bigdata.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.bigdata.exception.BDRuntimeException;
import com.bigdata.exception.BDRuntimeException;

/**
 * MD5加密工具类
 * @Title: EncryptUtil.java
 * @Description: MD5加密工具类
 * @version v1.0
 */
public class EncryptUtil {
	
	/**
	 * MD5加密算法
	 * @param needStr   需要加密的内容
	 * @return
	 */
    public static String md5(String needStr) throws BDRuntimeException {
        return encrypt(needStr, "md5");  
    }  

    /** 
     * md5或者sha-1加密 
     *  
     * @param inputText 
     *            要加密的内容 
     * @param algorithmName 
     *            加密算法名称：md5或者sha-1，不区分大小写 
     * @return 
     * @throws com.bigdata.exception.BDRuntimeException
     */  
    public static String encrypt(String inputText, String algorithmName) throws BDRuntimeException {
        if (inputText == null || "".equals(inputText.trim())) {  
            throw new BDRuntimeException("请输入要加密的内容");
        }
        if (algorithmName == null || "".equals(algorithmName.trim())) {  
            algorithmName = "md5";
        }
        String encryptText = null;
        try {  
            MessageDigest m = MessageDigest.getInstance(algorithmName);  
            m.update(inputText.getBytes("UTF8"));  
            byte s[] = m.digest();  
            // m.digest(inputText.getBytes("UTF8"));
            return hex(s);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return encryptText;  
    }  
  
    /**
     * 返回十六进制字符串
     * @param arr 
     * @return
     */ 
    private static String hex(byte[] arr) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < arr.length; ++i) {  
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,3));  
        }  
        return sb.toString();  
    }  
    
    public static void main(String[] args) throws BDRuntimeException {
		System.out.println(md5("AnxIn123"));
	}
    
}
