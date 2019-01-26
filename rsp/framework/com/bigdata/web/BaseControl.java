package com.bigdata.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata.exception.BDRuntimeException;
import com.bigdata.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bigdata.bean.SysUser;
import com.bigdata.exception.BDRuntimeException;
import com.bigdata.service.SysUserService;
import com.bigdata.util.Constants;
import com.bigdata.util.JsonUtils;

@Controller
public class BaseControl {
	

	/**
	 * 获取spring容器中的用户对象
	 * @param beanId
	 * @return
	 */
	protected Object getBean(String beanId){
		 WebApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		 return act.getBean(beanId);
	}


	@Autowired
	private SysUserService sysUserService;


	
	/**
	 * 获得当前人员信息
	 * @param request
	 * @return
	 */
	protected SysUser getSysUserSessionInfo(HttpServletRequest request) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("CURRENT_USER");
		return sysUser;
	}
	

	protected void printJsonHeadString(HttpServletResponse response, String message) throws IOException{
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		PrintWriter printWriter = response.getWriter();
		printWriter.write(message);
		printWriter.flush();
		printWriter.close();
	}
	
	/***
	 * json字符串返回
	 * @param response
	 * @param status
	 * @param message
	 * @throws java.io.IOException
	 */
	protected void printJsonAjaxString(HttpServletResponse response,boolean status, String message) throws IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
		PrintWriter printWriter = response.getWriter();
		Map<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put(Constants.RETURN_SUCCESS, status);
		resultMap.put(Constants.RETURN_MESSAGE, message);
		printWriter.write(JsonUtils.map2json(resultMap));
		printWriter.flush();
		printWriter.close();
	}
}
