package com.bigdata.util;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.regex.Pattern;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Title: StringUtils.java
 * @Description: 通用工具类
 * @version v1.0
 */
public class StringUtils {
	
	
	/**
	 *  从缓存中中获取当前用户的权限列表
	 * @param request
	 * @return:权限列表
	 */
	@SuppressWarnings("unchecked")
	public static Collection getUrlList() {
		//List<PdmModuleNew> urlList =new ArrayList<PdmModuleNew>(0);
		//Object sessionList=request.getSession().getAttribute("urlList");
		Authentication a = SecurityContextHolder.getContext().getAuthentication(); //当前用户认证信息
		Collection coll=a.getAuthorities();// 从缓存中取出当前用户的权限信息
		
		if(coll!=null)
			return coll;
		return null;
	}
	
	/**
	 * @Description: 判断当前登陆用户是否具有某角色
	 * @param String roleId
	 * @return
	 */
	public static boolean checkHasRole(String roleId){
	    for (Object obj : getUrlList()) {
            if(obj.equals(roleId)){
                return true ;
            }
        }
	    return false ;
	}
	
	/**
	 *  检索某一数组中是否含有某一对象(若对象是字符串则不区分大小写)
	 * @param arr: 数组
	 * @param obj: 待检测的对象
	 * @return
	 */
	public static Boolean findObjectInArray(Object[] arr,Object obj) {
		for(int i=0,length=arr.length;i<length;i++){
			if(arr[i].equals(obj))
				return true;
			if( (arr[i] instanceof String) && ((String)arr[i]).equalsIgnoreCase((String)obj))
				return true;
		}
		return false;
	}
	
	public static String numToHanzi(String str){
        String output = "";
        for (int i = 0; i < str.length(); i++) {
            char aa = str.charAt(i); // 取字符串下标索引是i的数 i循环的次数根据字符串的长度.
            if (aa == '1')
                output += "一";
            if (aa == '2')
                output += "二";
            if (aa == '3')
                output += "三";
            if (aa == '4')
                output += "四";
            if (aa == '5')
                output += "五";
            if (aa == '6')
                output += "六";
            if (aa == '7')
                output += "七";
            if (aa == '8')
                output += "八";
            if (aa == '9')
                output += "九";
            if (aa == '0')
                output += "零";
        }
        return output ;
	}
	/**
	 * String 类型转 Blob
	 * @param str
	 * @return
	 */
	public static Blob string2Blob(String str){
		if(str==null){
			return null;
		}
		Blob blob = null;
		try {
			blob  = (Blob) new SerialBlob(str.getBytes("GBK"));
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blob;
	}
	/**
	 * String 转clob
	 * @Description: TODO
	 * @param str
	 * @return
	 */
	public static Clob string2Clob(String str){
		if(str==null){
			return null;
		}
		Clob clob = null;
		try {
			clob = (Clob) new SerialClob(str.toCharArray());
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clob;
	}
	/**
	 * blob 转String
	 * @Description: TODO
	 * @param b
	 * @return
	 */
	public static String blob2String(Blob b){
		if(b==null){
			return "";
		}
		String blobString = null;
		try {
			blobString = new String(((SerialBlob) b).getBytes(1, (int) ((SerialBlob) b).length()),"GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SerialException e) {
			e.printStackTrace();
		}
		return blobString;
	}
	/**
	 * clob转String
	 * @Description: TODO
	 * @param c
	 * @return
	 */
	public static String clob2String(Clob c){
		if(c==null){
			return "";
		}
		String clobString = null;
		try {
			clobString = c.getSubString(1, (int) c.length());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clobString;
	}
	/**
	 * 按照类型切字符
	 * @Description: TODO
	 * @param str
	 * @param type
	 * @return
	 */
	public static String formatdisplayStr(String str,String type){
		if(str!=null&&str.length()>0){
			if("phone".equalsIgnoreCase(type)){
				return str.split(",")[0];
			}
		}
		return "";
	}
	
	/**
	 * 判断是否为4位数值
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
		String regx = "\\d{4}";
		if(str != null && !"".equals(str)){
			Pattern pattern = Pattern.compile(regx);
			return pattern.matcher(str).matches();
		}
		return false;
	}
	
	/**
	 * @Description: 中文转unicode
	 * @param gbString
	 * @return
	 */
	public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\\\u" + hexB;
        }
        System.out.println("unicodeBytes is: " + unicodeBytes);
        return unicodeBytes;
    }
	/**
	 * @Description: unicode 转汉字
	 * @param dataStr
	 * @return
	 */
	public static String decodeUnicode( final String dataStr ) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
	
	/**
     * Replaces all instances of oldString with newString in line.
     *
     * @param line the String to search to perform replacements on
     * @param oldString the String that should be replaced by newString
     * @param newString the String that will replace all instances of oldString
     *
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replace( String line, String oldString, String newString )
    {
        if (line == null) {
            return null;
        }
        int i=0;
        if ( ( i=line.indexOf( oldString, i ) ) >= 0 ) {
            char [] line2 = line.toCharArray();
            char [] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while( ( i=line.indexOf( oldString, i ) ) > 0 ) {
                buf.append(line2, j, i-j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }
}
