package com.bigdata.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bigdata.bean.SysUser;
import com.bigdata.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 常量类
 */
public class WaveAuthUtil {

    /**
     * 密码
     */
    public static final String PWD = "DF_PWD";

    /**
     * 拦截所有的.jsp .do 请求
     */
    public static final String LIMIT_TYPE_ALL = "ALL";

    /**
     * 调用spring security认证入口
     *
     * @param userId 用户ID
     */
    public static void authSecurity(String userId, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, PWD);
        authToken.setDetails(new WaveDetails(request));

        Authentication authentication = ((AuthenticationManager) CommonUtil.getBean("authenticationManager")).authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static WaveDetails getDfDetails() {
    	SecurityContext ss=SecurityContextHolder.getContext();
    	System.out.println(ss);
        return (WaveDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

    public static SysUser getSysUser() {
        return getDfDetails().getSysUser();
    }

    public static String getUserId() {
        return getSysUser().getUserId();
    }

    public static String getEmpId() {
        return getSysUser().getUserId();
    }

    public static String getEmpName() {
        return getSysUser().getNickname();
    }


    /**
     * 设置模版
     *
     * @param list 模版文字列表
     */
    public static void setLogTemplate(List<String> list) {
        getDfDetails().setLogTemplateParamter(list);
    }

    /**
     * 设置添加模版
     *
     * @param list 模版文字列表
     */
    public static void setAppendTemplate(List<String> list) {
        List<String> temps = getLogTemplate();
        if (temps == null) {
            temps = new ArrayList<String>();
        }
        temps.addAll(list);
        setLogTemplate(temps);
    }

    /**
     * 得到模版
     *
     * @return
     */
    public static List<String> getLogTemplate() {
        return getDfDetails().getLogTemplateParamter();
    }
   
}
