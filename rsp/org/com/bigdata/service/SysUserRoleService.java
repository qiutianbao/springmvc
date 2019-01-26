package com.bigdata.service;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import com.bigdata.bean.SysUserRole;
import com.bigdata.exception.BDRuntimeException;
import com.bigdata.exception.BDRuntimeException;


/**
 * @todo    
 * @version 
 * @created 2013-07-29 22:13:36
 * @author  jameson.fang
 */
public interface SysUserRoleService {

    int insertSysUserRole(SysUserRole sysUserRole) throws SQLException;

	int deleteSysUserRole(Map parameterMap) throws SQLException;

	
	int updateSysUserRole(SysUserRole sysUserRole) throws SQLException;
	
	
	List searchSysUserRoleByParams(Map parameterMap) throws SQLException;

	
	SysUserRole searchSysUserRoleById(String id) throws SQLException;
	
	
	List searchAllSysUserRole() throws SQLException;

	List<String> searchRoleIdListByUserId(String userId) throws BDRuntimeException;

}