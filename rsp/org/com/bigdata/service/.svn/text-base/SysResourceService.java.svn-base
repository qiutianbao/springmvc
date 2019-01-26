package com.bigdata.service;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.bigdata.bean.SysResource;


/**
 * 系统资源表
 * @todo    
 * @version 
 * @created 2013-07-29 22:06:40
 * @author  jameson.fang
 */
public interface SysResourceService {

    int insertSysResource(SysResource sysResource) throws SQLException;

	int deleteSysResource(Map parameterMap) throws SQLException;

	
	int updateSysResource(SysResource sysResource) throws SQLException;
	
	
	List searchSysResourceByParams(Map parameterMap) throws SQLException;

	
	SysResource searchSysResourceById(String id) throws SQLException;
	
	
	List searchAllSysResource() throws SQLException;

	String searchResByParentIdJson(HttpServletRequest request);

	int deleteSysResourceByResId(String resId) throws SQLException;

}