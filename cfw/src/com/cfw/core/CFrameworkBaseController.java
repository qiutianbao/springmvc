package com.cfw.core;

import core.controller.ExtJSBaseController;
import core.support.ExtJSBaseParameter;

/**
 * @author Ray
 * @
 */
public abstract class CFrameworkBaseController<E extends ExtJSBaseParameter> extends ExtJSBaseController<E> implements Constant {

//	public SysUser getCurrentSysUser() {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//		return (SysUser) request.getSession().getAttribute(SESSION_SYS_USER);
//	}

}
