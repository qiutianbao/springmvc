package com.bigdata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.bigdata.bean.SysResource;
import com.bigdata.service.SysResourceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.bigdata.bean.SysResource;
import com.wave.dao.FrameworkDao;
import com.wave.service.BaseService;
import com.bigdata.service.SysResourceService;
import com.bigdata.util.JsonUtils;

/**
 * 系统资源表
 * @todo    
 * @version 
 * @created 2013-07-29 22:06:40
 * @author  jameson.fang
 * 
 */
@Component(value="sysResourceService")
public class SysResourceServiceImpl extends BaseService implements SysResourceService {
	
	private static final Logger logger = Logger.getLogger(SysResourceServiceImpl.class);

	@Autowired
	private FrameworkDao frameworkDao;
	
	public int insertSysResource(SysResource sysResource) throws SQLException {
		Integer primaryKey = (Integer)frameworkDao.insert("insertSysResource", sysResource);
		if (primaryKey != null) {
			return primaryKey.intValue();
		}
		return 0;
	}


	public int deleteSysResource(Map parameterMap) throws SQLException {
		return frameworkDao.delete("deleteSysResource",parameterMap);
	}


	public int updateSysResource(SysResource sysResource) throws SQLException {
		return frameworkDao.update("updateSysResource",sysResource);
	}
	
	@Cacheable(value = "resCache", key = "methodName")
	public List searchSysResourceByParams(Map parameterMap) throws SQLException {
		List sysResourceList = (List) frameworkDao.queryForList(
			"searchSysResourceByParams", parameterMap);
		return sysResourceList;
	}

	public SysResource searchSysResourceById(String id) throws SQLException {
		HashMap parameterMap = new HashMap();
		parameterMap.put("resId", id);
		List sysResourceList = searchSysResourceByParams(parameterMap);
		if (sysResourceList != null && sysResourceList.size() > 0) {
			return (SysResource)sysResourceList.get(0);
		}
		return new SysResource();
	}
	
	@Cacheable(value = "resCache", key = "methodName")
	public List searchAllSysResource() throws SQLException {
		return searchSysResourceByParams(new HashMap());
	}

	@Override
	public String searchResByParentIdJson(HttpServletRequest request) {
		String resParentId = request.getParameter("resId");
		List<SysResource> sysResourceList = new ArrayList<SysResource>();
        Map<String, String> paramMap = new HashMap<String, String>();
        try {
        	logger.info("resParentId===============>>" + resParentId);
            paramMap.put("resParentId", resParentId);
            paramMap.put("status", "Y");
            sysResourceList = (List<SysResource>) frameworkDao.queryForList("searchSysResourceByParentId", paramMap);
            return treeDataParseIntoJson(sysResourceList);
        } catch (SQLException e) {
        	logger.error("SysResourceServiceImpl==>>searchResByParentIdJson", e);
        }
        return null;
	}


	private String treeDataParseIntoJson(List<SysResource> sysResourceList) {
		StringBuffer result = new StringBuffer();
        for (int i = 0, size = sysResourceList.size(); i < size; i++) {
            SysResource res = sysResourceList.get(i);
            if (i == 0) {
                result.append("[");
            }
            result.append("{")
                    .append("\"data\":\"").append(res.getResName()).append("\",")
                    .append("\"attr\":").append(JsonUtils.object2json(res)).append(",")
                    .append("\"children\":[],")
                    .append("\"state\":" + ((res.getCountSub() != 0) ? "\"closed\"" : "\"opened\""))
                    .append("}");
            if (i == sysResourceList.size() - 1) {
                result.append("]");
            } else {
                result.append(",");
            }
        }
        return result.toString();
	}


	@Override
	public int deleteSysResourceByResId(String resId) throws SQLException {
		//取得资源bean
        Map<String, String> parameterMap = new HashMap<String, String>();
        parameterMap.put("resId", resId);
        parameterMap.put("status", "Y");
        SysResource sysResource = (SysResource) frameworkDao.queryForObject("searchSysResourceByResId", parameterMap);

        if (0 == sysResource.getCountSub()) {//如果是leaf的话
        	frameworkDao.delete("deleteSysResourceByResId", resId);
        } else {//如果是目录的话
            List<String> resParentIds = new ArrayList<String>();
            resParentIds.add(resId);

            parameterMap.clear();
            parameterMap.put("resParentId", resId);
            parameterMap.put("status", "Y");
            List<SysResource> children = frameworkDao.queryForList("searchSysResourceByParentId", parameterMap);
            //删除本身
            frameworkDao.delete("deleteSysResourceByResId", resId);
            //开始删除子级
            for (SysResource child : children) {
                //递归删除
                this.deleteSysResourceByResId(child.getResId());
            }

        }
        
        //删除sys_role_res在sysResource没有的。
        frameworkDao.delete("deleteSysRoleResNotInSysResource");
        
        //删除sys_user_res在sysResource没有的。
        frameworkDao.delete("deleteSysUserResNotInSysResource");
        
        
        return 0;
	}

}