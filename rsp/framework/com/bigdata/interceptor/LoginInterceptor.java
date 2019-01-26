package com.bigdata.interceptor;

import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private int openingTime;
	private int closingTime;
	
	public int getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(int openingTime) {
		this.openingTime = openingTime;
	}

	public int getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(int closingTime) {
		this.closingTime = closingTime;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//System.out.println("*********************preHandle********************");  
	    System.out.println(handler.getClass());  
	    HandlerMethod handlerMethod = (HandlerMethod) handler;
		if(handlerMethod.getMethod().getName().indexOf("logon")!=-1){
			System.out.println("+++++++++++++++++++++++++++++++++=========>"+handlerMethod.getMethod().getName());  
			Calendar cal = Calendar.getInstance();
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			if(openingTime <=hour&&hour<=closingTime){
				return true;
			}else{
				
				StringBuffer sb=new StringBuffer();
				sb.append("<script language=javascript>");
				sb.append("alert('下班时间！禁止访问！请明天9点后访问！')");
				sb.append("</script>");
				
				PrintWriter printWriter = response.getWriter();
				response.setCharacterEncoding("utf-8");       
			    response.setContentType("text/html; charset=utf-8");  
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache, must-revalidate");
				response.setHeader("Pragma", "no-cache");
				
				printWriter.write(sb.toString());//json 格式返回
				printWriter.flush();
				printWriter.close();
				return false;
			}
		}
		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
}
