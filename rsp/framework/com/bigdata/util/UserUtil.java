package com.bigdata.util;

import javax.servlet.http.HttpSession;

import com.bigdata.bean.SysUser;

/**
 * 用户工具类
 * <p>Title: UserUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: wave</p>
 * @author bob.fang
 * @date 2013-7-28
 * @version 1.0
 */
public class UserUtil {

	public static final String CURRENT_USER = "CURRENT_USER";

	/**
	 * 设置用户到session
	 * @param session
	 * @param user
	 */
	public static void setUserToSession(HttpSession session, SysUser user) {
		session.setAttribute(CURRENT_USER, user);
	}

	/**
	 * 从Session获取当前用户信息
	 * @param session
	 * @return
	 */
	public static SysUser getUserFromSession(HttpSession session) {
		Object attribute = session.getAttribute(CURRENT_USER);
		return attribute == null ? null : (SysUser) attribute;
	}

}
