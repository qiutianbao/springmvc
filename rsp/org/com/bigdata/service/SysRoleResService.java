package com.bigdata.service;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import com.bigdata.bean.SysRoleRes;
import com.bigdata.bean.SysResource;
import com.bigdata.bean.SysRoleRes;


/**
 * 角色资源关系表
 * @todo    
 * @version 
 * @created 2013-07-29 22:21:35
 * @author  jameson.fang
 */
public interface SysRoleResService {

    int insertSysRoleRes(SysRoleRes sysRoleRes) throws SQLException;

	int deleteSysRoleRes(Map parameterMap) throws SQLException;

	
	int updateSysRoleRes(SysRoleRes sysRoleRes) throws SQLException;
	
	
	List searchSysRoleResByParams(Map parameterMap) throws SQLException;

	
	SysRoleRes searchSysRoleResById(String id) throws SQLException;
	
	
	List searchAllSysRoleRes() throws SQLException;

	List<SysRoleRes> searchSysRoleResByResId(String resId) throws SQLException;

	void updateSysRoleResByRoleIds(SysRoleRes sysRoleRes) throws SQLException;

}