package com.bigdata.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.bigdata.bean.SysUpload;
import com.bigdata.bean.SysUser;
import com.bigdata.exception.BDRuntimeException;
import com.bigdata.exception.BDRuntimeException;

/**
 * 系统用户
 * @todo    
 * @version 
 * @created 2013-07-27 02:33:46
 * @author  jameson.fang
 */
public interface SysUserService {

    int insertSysUser(SysUser sysUser) throws BDRuntimeException;

	int deleteSysUser(Map parameterMap) throws BDRuntimeException;
	
	int updateSysUser(SysUser sysUser) throws BDRuntimeException;
	
	List searchSysUserByParams(Map parameterMap) throws BDRuntimeException;

	SysUser searchSysUserById(String id) throws BDRuntimeException;
	
	List<SysUser> searchAllSysUser() throws BDRuntimeException;

	String listSysUserJsonStr(Map<String, String> qcByShjdyParameterMap,
                              String sortName, String sortOrder, int pages, int rp) throws BDRuntimeException;

	String insertSysUser(SysUpload bean, String userId, String executionId);

	List<String> searchRoleIdListByUserId(String userId) throws SQLException;

	List<String> searchRoleCodeListByUserId(String userId) throws SQLException;

	List<SysUser> searchSysUserListByResId(String resId) throws SQLException;

	String searchSysUserInfoByDeptIdToJson(String deptId) throws SQLException;

	String searchPersonSplitSign(String flowAssignerIntoOne) throws BDRuntimeException;

    int updateSysUser(Map<String, String> parameterMap, String userType) throws BDRuntimeException;
}