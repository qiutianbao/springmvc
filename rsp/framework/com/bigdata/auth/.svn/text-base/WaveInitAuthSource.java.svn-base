package com.bigdata.auth;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bigdata.exception.BDRuntimeException;
import com.bigdata.service.AuthManager;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.bigdata.service.AuthManager;
import com.bigdata.util.CommonUtil;

public class WaveInitAuthSource implements FilterInvocationSecurityMetadataSource {

	private static final Logger logger = Logger.getLogger(WaveInitAuthSource.class);
	
	private String nofilters;
	
	public void setNofilters(String nofilters) {
		this.nofilters = nofilters;
	}

	/**
	 * OA 系统自动登录合规系统不做拦截
	 */
	private static final String OAUIDLOGINURL="&uid=";
	/**
	 * 文件上传不做拦截
	 */
	private static final String UPLOADURL="dfWfUploadAction.do";
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj)
			throws IllegalArgumentException {
		String url = ((FilterInvocation)obj).getRequestUrl() ; 
		Map<String , Collection<ConfigAttribute>> resourceMap = null ;
		try {
			resourceMap = initResource() ;
		} catch (BDRuntimeException e) {
            logger.error(" getAttributes SQLException ", e);
        }
        return getAttrByUrl(resourceMap, url);
	}
	
	private Collection<ConfigAttribute> getAttrByUrl(
			Map<String, Collection<ConfigAttribute>> resourceMap, String url) {
		url+="<";
		Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();// 迭代系统定义的URL
            if (StringUtils.isBlank(resURL) || resURL.split(",").length < 2 ) {
                continue;
            }
            String regx = "/" + resURL.split(",")[1]+"<";
            if (url.contains("?")) {
                if (url.startsWith(regx)) {
                    return resourceMap.get(resURL);
                }
            } else {
                if (url.equals(regx)) {
                    return resourceMap.get(resURL);
                }
            }
        }
        return returnConfigAttribute(url);
	}
	
	private Collection<ConfigAttribute> returnConfigAttribute(String url){
		String roleName = "ROLE_NEVER" ;
		
		if(url.indexOf(OAUIDLOGINURL)>0){//设置UID访问权限,主要是OA那边需要此功能
			return neverDefinedResource(roleName);
		}
		if(url.indexOf(UPLOADURL)>=0){//设置文件上传访问权限
			return neverDefinedResource(roleName);
		}
		String [] nofiltersStr = nofilters.split(",");
		for( String nofilter : nofiltersStr ){
			//nofilter = "/" + StringUtils.trim(nofilter) + "<";
            nofilter = StringUtils.trim(nofilter) + "<";
			if(url.startsWith(nofilter)){
				return neverDefinedResource(roleName);
			}
		}
		
		//Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();//getDetails();
		Object obj = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if( obj instanceof WaveDetails){
			logger.info("succeess--------->-----Login Nickname："+((WaveDetails) obj).getSysUser().getUserName());
			 
		}else{
			roleName = "ROLE_ANONYMOUS" ;
		}
		return neverDefinedResource(roleName);
	}

	private Collection<ConfigAttribute> neverDefinedResource(String roleName) {
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
        ConfigAttribute ca = new SecurityConfig(roleName);
        atts.add(ca);
        return atts;
	}

	/**
	 * 认证管理接口
	 * @return
	 */
	private AuthManager getAuthManager(){
		return (AuthManager)CommonUtil.getBean("authManager");
	}
	
	
	/**
	 * 初始化数据库中定义的资源
	 * @throws java.sql.SQLException
	 */
	private Map<String , Collection<ConfigAttribute>> initResource() throws BDRuntimeException{
		Map<String , Collection<ConfigAttribute>> resultMap = new HashMap<String, Collection<ConfigAttribute>>();
		Iterator<Entry<String, List<String>>> resourceIt = getAuthManager().getAllResource().entrySet().iterator() ;
		while(resourceIt.hasNext()){
			Entry<String, List<String>> entryStr = resourceIt.next();
			resultMap.put(CommonUtil.getTrimMethodUrl(entryStr.getKey()), initSpringSecurityData(entryStr.getValue()));
		}
		return resultMap;
	}

	/**
	 * 初始化springsecurity数据
	 * @param roles 角色ID或人员ID
	 * @return
	 */
	private Collection<ConfigAttribute> initSpringSecurityData(List<String> roles){
		Collection<ConfigAttribute> attr = new ArrayList<ConfigAttribute>();
		for( String role : roles ){
			ConfigAttribute attribute = new SecurityConfig(role);
			attr.add(attribute);
		}
		return attr;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
