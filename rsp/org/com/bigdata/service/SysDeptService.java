package com.bigdata.service;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.bigdata.bean.SysDept;


/**
 * 部门
 * @todo    
 * @version 
 * @created 2013-09-10 23:00:18
 * @author  jameson.fang
 */
public interface SysDeptService {

    int insertSysDept(SysDept sysDept) throws SQLException;

	int deleteSysDept(Map parameterMap) throws SQLException;

	
	int updateSysDept(SysDept sysDept) throws SQLException;
	
	
	List searchSysDeptByParams(Map parameterMap) throws SQLException;

	
	SysDept searchSysDeptById(String id) throws SQLException;
	
	
	List searchAllSysDept() throws SQLException;

	String searchDeptInfoFormInitiallyOpen() throws SQLException;

	String searchDeptInfoByDeptParIdToJson(String id) throws SQLException;
	
	String searchDeptInfoByDeptParId(String deptParId, String empId) throws SQLException;

	String searchDeptByParentIdJson(HttpServletRequest request) throws SQLException;

}