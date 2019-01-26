package com.bigdata.service;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import com.bigdata.bean.SysRole;
import com.bigdata.exception.BDRuntimeException;


/**
 * 系统角色
 * @todo    
 * @version 
 * @created 2013-07-29 21:55:42
 * @author  jameson.fang
 */
public interface SysRoleService {

    int insertSysRole(SysRole sysRole) throws SQLException;

	int deleteSysRole(Map parameterMap) throws SQLException;

	
	int updateSysRole(SysRole sysRole) throws SQLException;
	
	
	List searchSysRoleByParams(Map parameterMap) throws SQLException;

	
	SysRole searchSysRoleById(String id) throws SQLException;
	
	
	List searchAllSysRole() throws SQLException;

	List<String> searchRoleIdListByUserId(String userId) throws SQLException;

	List<SysRole> searchSysRoleByResId(String resId) throws SQLException;

}