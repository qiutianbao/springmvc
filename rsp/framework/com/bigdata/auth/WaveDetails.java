package com.bigdata.auth;

import com.bigdata.bean.SysUser;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class WaveDetails extends WebAuthenticationDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 日志模版需要替换的参数值
     */
    private List<String> logTemplateParamter;
    private String remoteHost;
    private SysUser sysUser;
    
    public SysUser getSysUser() {
		return sysUser;
	}

	private String userId;
    private String nameAndDeptName;

    public WaveDetails(HttpServletRequest request) {
        super(request);
        this.remoteHost = request.getRemoteHost();
        
        this.sysUser = (SysUser) request.getSession().getAttribute("sysUser");
        System.out.println(this.sysUser.getNickname());
        this.userId = this.sysUser.getUserId();
    }

    /**
     * 获取模版参数值
     *
     * @return
     */
    public List<String> getLogTemplateParamter() {
        return logTemplateParamter;
    }

    /**
     * 获取request
     *
     * @return
     */
    public String getRemoteHost() {
        return remoteHost;
    }

    /**
     * 设置模版参数值
     *
     * @param logTemplateParamter
     */
    public void setLogTemplateParamter(List<String> logTemplateParamter) {
        this.logTemplateParamter = logTemplateParamter;
    }

    public String getUserId() {
        return userId;
    }

    public String getNameAndDeptName() {
        return nameAndDeptName;
    }
}
