package com.bigdata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigdata.bean.SysUserDept;
import com.wave.dao.FrameworkDao;
import com.wave.service.BaseService;
import com.bigdata.service.SysUserDeptService;

/**
 * 
 * @todo    
 * @version 
 * @created 2013-09-10 22:59:38
 * @author  jameson.fang
 * 
 */
@Component(value="sysUserDeptService")
public class SysUserDeptServiceImpl extends BaseService implements SysUserDeptService{
	private static final Logger logger = Logger.getLogger(SysUserDeptServiceImpl.class);
	
	@Autowired
	private FrameworkDao frameworkDao;

	public int insertSysUserDept(SysUserDept sysUserDept) throws SQLException {
		Integer primaryKey = (Integer)frameworkDao.insert("insertSysUserDept", sysUserDept);
		if (primaryKey != null) {
			return primaryKey.intValue();
		}
		return 0;
	}


	public int deleteSysUserDept(Map parameterMap) throws SQLException {
		return frameworkDao.delete("deleteSysUserDept",parameterMap);
	}


	public int updateSysUserDept(SysUserDept sysUserDept) throws SQLException {
		return frameworkDao.update("updateSysUserDept",sysUserDept);
	}

	public List searchSysUserDeptByParams(Map parameterMap) throws SQLException {
		List sysUserDeptList = (List) frameworkDao.queryForList(
			"searchSysUserDeptByParams", parameterMap);
		return sysUserDeptList;
	}

	public SysUserDept searchSysUserDeptById(String id) throws SQLException {
		HashMap parameterMap = new HashMap();
		parameterMap.put("userId", id);
		List sysUserDeptList = searchSysUserDeptByParams(parameterMap);
		if (sysUserDeptList != null && sysUserDeptList.size() > 0) {
			return (SysUserDept)sysUserDeptList.get(0);
		}
		return new SysUserDept();
	}
	
	public List searchAllSysUserDept() throws SQLException {
		return searchSysUserDeptByParams(new HashMap());
	}

}