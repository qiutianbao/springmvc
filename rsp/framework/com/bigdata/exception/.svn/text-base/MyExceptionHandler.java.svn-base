package com.bigdata.exception;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata.util.JsonUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: bob
 * Date: 14-11-18
 * Time: 下午12:23
 * 自定义捕获异常处理，可扩展Exception
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		String url = request.getRequestURI().toLowerCase();


		if(url.indexOf(".ajax")>0){
			try {
				if(ex instanceof BDRuntimeException){
					response.setContentType("html/txt");
					response.setCharacterEncoding("utf-8");
					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "no-cache, must-revalidate");
					response.setHeader("Pragma", "no-cache");
					
					Writer printWriter = response.getWriter();
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("hasError", new Boolean(true));
					map.put("errorMsg", clearError(ex.getMessage()));
					printWriter.write(JsonUtils.map2json(map));//json 格式返回
					printWriter.flush();
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			Map<String, Object> model = new HashMap<String, Object>();
			// 根据不同错误转向不同页面
			if(ex instanceof BDRuntimeException) {
                request.setAttribute("errorMsg",ex.getMessage());
				return new ModelAndView("error", model);
			}
		}
		return null;
	}

	private String clearError(String message) {
		if(message==null){
			return "未知错误";
		}
		String str=message;
		str=str.replaceAll("\n", "\\n");
		str=str.replaceAll("'", "‘");
		str=str.replaceAll("\"", "“");
		return str;
	}
}
