package com.bigdata.auth;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class WaveAccessDecisionManager implements AccessDecisionManager {
	private static final Logger logger = Logger.getLogger(WaveAccessDecisionManager.class);

	private String limitType ;
	
	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
		
		logger.info("this is object url===================>"+object.toString());
        Iterator<ConfigAttribute> ite=configAttributes.iterator();//可访问该资源的角色列表
        
        while(ite.hasNext()){
        	ConfigAttribute ca=ite.next();
        	String needRole = ((SecurityConfig) ca).getAttribute();// 访问资源必须的权限
        	if ("ROLE_NEVER".equals(needRole)) {//如果访问资源具有ROLE_NERVER 则去验证此时的 权限过滤级别是否为all 如果为all则不可访问，否则可以访问
			    if(WaveAuthUtil.LIMIT_TYPE_ALL.equals(limitType.trim())){
			    	logger.debug("权限不足.");
			        throw new AccessDeniedException("权限不足.");
			    }else{
			        return;
			    }
			}
        	for (GrantedAuthority ga : authentication.getAuthorities()) {//循环当前用户所具有的角色信息，如果有匹配的则此角色可以访问此资源
				String userRole = ga.getAuthority();
				if("ROLE_ANONYMOUS".equals(userRole)){
					logger.debug("用户未登陆,或认证未通过.");
					throw new AccessDeniedException("用户未登陆,或认证未通过.");
				}else if (needRole.equals(userRole)) { // ga 是用户的权限
					return;
				}
			}
        }
        //如果走程序走到这里，则说明此用户无权限对资源进行访问
		throw new AccessDeniedException("权限不足.");

	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
