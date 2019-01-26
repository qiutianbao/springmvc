package com.bigdata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import com.bigdata.exception.BDRuntimeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigdata.bean.SysUserRole;
import com.wave.dao.FrameworkDao;
import com.bigdata.exception.BDRuntimeException;
import com.wave.service.BaseService;
import com.bigdata.service.SysUserRoleService;

/**
 * 
 * @version 
 * @created 2013-07-29 22:13:36
 * @author  jameson.fang
 * 
 */
@Component(value="sysUserRoleService")
public class SysUserRoleServiceImpl extends BaseService implements SysUserRoleService{
	private static final Logger logger = Logger.getLogger(SysUserRoleServiceImpl.class);

	@Autowired
	private FrameworkDao frameworkDao;
	
	public int insertSysUserRole(SysUserRole sysUserRole) throws SQLException {
		Integer primaryKey = (Integer)frameworkDao.insert("insertSysUserRole", sysUserRole);
		if (primaryKey != null) {
			return primaryKey.intValue();
		}
		return 0;
	}


	public int deleteSysUserRole(Map parameterMap) throws SQLException {
		return frameworkDao.delete("deleteSysUserRole",parameterMap);
	}


	public int updateSysUserRole(SysUserRole sysUserRole) throws SQLException {
		return frameworkDao.update("updateSysUserRole",sysUserRole);
	}

	public List searchSysUserRoleByParams(Map parameterMap) throws SQLException {
		List sysUserRoleList = (List) frameworkDao.queryForList(
			"searchSysUserRoleByParams", parameterMap);
		return sysUserRoleList;
	}

	public SysUserRole searchSysUserRoleById(String id) throws SQLException {
		Map parameterMap = new HashMap();
		parameterMap.put("userId", id);
		List sysUserRoleList = searchSysUserRoleByParams(parameterMap);
		if (sysUserRoleList != null && sysUserRoleList.size() > 0) {
			return (SysUserRole)sysUserRoleList.get(0);
		}
		return new SysUserRole();
	}
	
	public List searchAllSysUserRole() throws SQLException {
		return searchSysUserRoleByParams(new HashMap());
	}

	@Override
	public List<String> searchRoleIdListByUserId(String userId) throws BDRuntimeException {
		try {			
			return frameworkDao.queryForList("searchRoleIdListByUserId", userId);
		} catch (SQLException e) {
			logger.error("SysUserRoleServiceImpl.searchRoleIdListByUserId", e);
			throw new BDRuntimeException("SysUserRoleServiceImpl.searchRoleIdListByUserId" , e);
		}
	}

}