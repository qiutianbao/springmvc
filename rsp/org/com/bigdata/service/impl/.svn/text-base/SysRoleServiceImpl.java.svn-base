package com.bigdata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigdata.bean.SysRole;
import com.wave.dao.FrameworkDao;
import com.bigdata.exception.BDRuntimeException;
import com.wave.service.BaseService;
import com.bigdata.service.SysRoleService;

																		

/**
 * 系统角色
 * @todo    
 * @version 
 * @created 2013-07-29 21:55:42
 * @author  jameson.fang
 * 
 */
@Component(value="sysRoleService")
public class SysRoleServiceImpl extends BaseService implements SysRoleService{
	private static final Logger logger = Logger.getLogger(SysRoleServiceImpl.class);

	@Autowired
	private FrameworkDao frameworkDao;
	
	public int insertSysRole(SysRole sysRole) throws SQLException {
		Integer primaryKey = (Integer)frameworkDao.insert("insertSysRole", sysRole);
		if (primaryKey != null) {
			return primaryKey.intValue();
		}
		return 0;
	}


	public int deleteSysRole(Map parameterMap) throws SQLException {
		return frameworkDao.delete("deleteSysRole",parameterMap);
	}


	public int updateSysRole(SysRole sysRole) throws SQLException {
		return frameworkDao.update("updateSysRole",sysRole);
	}

	public List searchSysRoleByParams(Map parameterMap) throws SQLException {
		List sysRoleList = (List) frameworkDao.queryForList(
			"searchSysRoleByParams", parameterMap);
		return sysRoleList;
	}

	public SysRole searchSysRoleById(String id) throws SQLException {
		Map parameterMap = new HashMap();
		parameterMap.put("roleId", id);
		List sysRoleList = searchSysRoleByParams(parameterMap);
		if (sysRoleList != null && sysRoleList.size() > 0) {
			return (SysRole)sysRoleList.get(0);
		}
		return new SysRole();
	}
	
	public List searchAllSysRole() throws SQLException {
		return searchSysRoleByParams(new HashMap());
	}


	@Override
	public List<String> searchRoleIdListByUserId(String userId) throws SQLException {
		return frameworkDao.queryForList("searchRoleIdListByUserId", userId);
	}


	@Override
	public List<SysRole> searchSysRoleByResId(String resId) throws SQLException {
		return frameworkDao.queryForList("searchSysRoleByResId", resId);
	}

}