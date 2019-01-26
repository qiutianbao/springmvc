package com.bigdata.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class WaveFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

	//与applicationContext-security.xml里的myFilter的属性securityMetadataSource对应，  
    //其他的两个组件，已经在AbstractSecurityInterceptor定义  
    private FilterInvocationSecurityMetadataSource securityMetadataSource;  
    
	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

	@Override
	public Class<?> getSecureObjectClass() {
		//下面的MyAccessDecisionManager的supports方面必须放回true,否则会提醒类型错误  
        return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);  
        invoke(fi);  
	}

	private void invoke(FilterInvocation fi) throws IOException, ServletException {
		// object为FilterInvocation对象  
        // super.beforeInvocation(fi);源码  
        //1.获取请求资源的权限  
        //执行Collection<ConfigAttribute> attributes = SecurityMetadataSource.getAttributes(object);  
        //2.是否拥有权限  
        //this.accessDecisionManager.decide(authenticated, object, attributes);  
        InterceptorStatusToken token = super.beforeInvocation(fi);  
        try {


            /*// 从 HTTP 头中取得 Referer 值
            String referer=fi.getRequest().getHeader("Referer");
            // 判断 Referer 是否以 bank.example 开头
            if((referer!=null) &&(referer.trim().indexOf("/rsp")>-1)){
                fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            }else{
                fi.getRequest().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(fi.getRequest(),fi.getResponse());
            }*/
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {  
            super.afterInvocation(token, null);  
        }  
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
