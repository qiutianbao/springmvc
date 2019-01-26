package com.bigdata.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.bigdata.HtmlHeadConstants;

/**
 * WEB文件下载
 * @Title: FileUtils.java
 * @Description: 文件下载相关处理
 * @version v1.0
 */
public class FileUtils {

	/**
	 * 设置下载头部信息
	 * 
	 * @param response HttpServletResponse响应 
	 * @param exportFileName 输出的文件名称，如：a.xls
	 * @param headerType 设置http头部下载类型
	 * @throws java.io.UnsupportedEncodingException 文件转码异常
	 * 
	 */
	public static void setResponseHeader(HttpServletResponse response,
			String headerType, String exportFileName) throws UnsupportedEncodingException {
		if(StringUtils.isBlank(headerType)){
			headerType = HtmlHeadConstants.HTML_HEAD_HTML_HTML;
		}
		response.setContentType(headerType);
		response.setHeader("Content-Disposition", "attachment;filename=\""+new String(exportFileName.getBytes("gb2312"),"iso8859-1")+"\"");
		response.setHeader("Cache-Control", "must-revalidate,post-check=0,pre-check=0");
		response.setHeader("Pragma", "public");
		response.setDateHeader("Expires", (System.currentTimeMillis()+1000));
	}
	
	/**
	 * 文件下载
	 * @param file         创建的文件对象 new File()
	 * @param response     HttpServletResponse响应
	 * @throws java.io.IOException 当文件不存在或者创建文件流时 会抛此异常
	 */
	public static void downloadFile(File file,HttpServletResponse response) throws IOException{
		if(!file.exists()){
			throw new FileNotFoundException("file not found:"+file.getAbsolutePath());
		}
		BufferedInputStream bufferStream =new BufferedInputStream(new FileInputStream(file));
		byte[] buffer = new byte[1024];
		int n;
		while(-1!=(n=bufferStream.read(buffer))){
			response.getOutputStream().write(buffer,0,n);
		}
		response.getOutputStream().flush();
		bufferStream.close();
	}
	
}
