package com.bigdata.service;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import com.bigdata.bean.SysUserRes;


/**
 * 用户与资源关系
 * @todo    
 * @version 
 * @created 2013-07-29 22:38:01
 * @author  jameson.fang
 */
public interface SysUserResService {

    int insertSysUserRes(SysUserRes sysUserRes) throws SQLException;

	int deleteSysUserRes(Map parameterMap) throws SQLException;

	
	int updateSysUserRes(SysUserRes sysUserRes) throws SQLException;
	
	
	List searchSysUserResByParams(Map parameterMap) throws SQLException;

	
	SysUserRes searchSysUserResById(String id) throws SQLException;
	
	
	List searchAllSysUserRes() throws SQLException;

	void updateSysUserResByUserIds(SysUserRes sysUserRes) throws SQLException;

}