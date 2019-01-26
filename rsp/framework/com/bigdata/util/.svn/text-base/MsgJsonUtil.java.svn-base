package com.bigdata.util;


/**
 * 拼接错误信息的JSON格式
 * @Title: ErrorMsgJsonUtil.java
 * @Description: TODO
 * @version v1.0
 */
public class MsgJsonUtil {
	
	/**
	 * 获取错误返回的JSON格式
	 * {"jsonrpc" : "2.0", "error" : {"code": 101, "message": "Failed to open input stream."}, "id" : "id"}
	 * @param key  错误编号
	 * @return
	 */
	public static String spellMsgJson(){
		return initMsgErrJson();
	}
	
	/**
	 * 获取正确的返回的JSON格式
	 * @return   请查找SUCE
	 */
	public static String spellMsgJson(String id){
		return initMsgSuccessJson(id);
	}
	
	/**
	 * 构造错误的返回JSON格式
	 * @param key
	 * @return
	 */
	private static String initMsgErrJson(){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"jsonrpc\":\"2.0\",")
				.append("\"error\":{").append("\"code\":").append("10002")
				.append(",\"message\":").append("\"").append("上传失败！").append("\"")
				.append("},\"id\":\"id\", \"rid\" : \"id\"}");
		return sb.toString();
	}
	
	/**
	 * 构造正确的返回JSON格式
	 * @param id
	 * @return
	 */
	private static String initMsgSuccessJson(String id){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"jsonrpc\":\"2.0\",\"result\" : null,\"id\":\"id\", \"rid\" : \""+id+"\"}");
		return sb.toString();
	}
	
}
