package com.bigdata.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统用户
 * 系统用户
 * @version 
 * @created 2013-07-27 02:33:46
 * @author jameson.fang
 */
public class SysUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *  用户ID
	 */
	private String userId;
	/**
	 *  用于登录账号
	 */
	private String userName;
	/**
	 *  昵称
	 */
	private String nickname;
	/**
	 *  密码
	 */
	private String pwd;

    private String loginDateTime;

    public String getLoginDateTime() {
        return loginDateTime;
    }

    public void setLoginDateTime(String loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    /**
	 *  状态标示符
	 */
	private String status;

    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}