package com.bigdata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import com.bigdata.bean.SysRoleRes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigdata.bean.SysResource;
import com.bigdata.bean.SysRoleRes;
import com.wave.dao.FrameworkDao;
import com.wave.service.BaseService;
import com.bigdata.service.SysRoleResService;

												

/**
 * 角色资源关系表
 * @todo    
 * @version 
 * @created 2013-07-29 22:21:35
 * @author  jameson.fang
 * 
 */
@Component(value="sysRoleResService")
public class SysRoleResServiceImpl extends BaseService implements SysRoleResService{
	private static final Logger logger = Logger.getLogger(SysRoleResServiceImpl.class);

	@Autowired
	private FrameworkDao frameworkDao;
	
	public int insertSysRoleRes(SysRoleRes sysRoleRe) throws SQLException {
		Integer primaryKey = (Integer)frameworkDao.insert("insertSysRoleRe", sysRoleRe);
		if (primaryKey != null) {
			return primaryKey.intValue();
		}
		return 0;
	}


	public int deleteSysRoleRes(Map parameterMap) throws SQLException {
		return frameworkDao.delete("deleteSysRoleRe",parameterMap);
	}


	public int updateSysRoleRes(SysRoleRes sysRoleRe) throws SQLException {
		return frameworkDao.update("updateSysRoleRe",sysRoleRe);
	}

	public List searchSysRoleResByParams(Map parameterMap) throws SQLException {
		List sysRoleReList = (List) frameworkDao.queryForList(
			"searchSysRoleResByParams", parameterMap);
		return sysRoleReList;
	}

	public SysRoleRes searchSysRoleResById(String id) throws SQLException {
		HashMap parameterMap = new HashMap();
		parameterMap.put("roleId", id);
		List sysRoleReList = searchSysRoleResByParams(parameterMap);
		if (sysRoleReList != null && sysRoleReList.size() > 0) {
			return (SysRoleRes)sysRoleReList.get(0);
		}
		return new SysRoleRes();
	}
	
	public List searchAllSysRoleRes() throws SQLException {
		return searchSysRoleResByParams(new HashMap());
	}


	@Override
	public List<SysRoleRes> searchSysRoleResByResId(String resId) throws SQLException {
		Map<String, String> paramterMap=new HashMap<String, String>();
		paramterMap.put("resId", resId);
		return searchSysRoleResByParams(paramterMap);
	}


	@SuppressWarnings("unchecked")
	@Override
	public void updateSysRoleResByRoleIds(SysRoleRes sysRoleRes) throws SQLException {
		String resId=sysRoleRes.getResId();
		//String[] roleIds=sysRoleRes.getRoleIds()!=null?sysRoleRes.getRoleIds().split(","):null;
		String createby=sysRoleRes.getCreateBy();
		Date now = new Date();
		List<String> childrenResList = new ArrayList<String>();//所有子孙节点
        List<String> parentList = new ArrayList<String>();//所有祖节点
        //取得资源bean
        Map<String, String> parameterMap = new HashMap<String, String>();
        parameterMap.put("resId", resId);
        parameterMap.put("status", "Y");
        SysResource sysResource = (SysResource) frameworkDao.queryForObject("searchSysResourceByParams", parameterMap);
        int level = sysResource.getResLevel();
        String pId = sysResource.getResParentId();//父菜单id
        
        if (3 == level) {
            parentList.add(pId);//此时pId是二级菜单
            //查询父--2级菜单
            parameterMap = new HashMap<String, String>();
            parameterMap.put("resId", pId);
            parameterMap.put("status", "Y");
            SysResource pSysResource = (SysResource) frameworkDao.queryForObject("searchSysResourceByParams", parameterMap);
            String gId = pSysResource.getResParentId();//1级菜单id
            parentList.add(gId);
        } else if (2 == level) {
            parentList.add(pId);//此时pId是一级菜单
            //查询下级(三级)子节点
            parameterMap = new HashMap<String, String>();
            parameterMap.put("resParentId", resId);
            parameterMap.put("status", "Y");
            List<SysResource> childrenList = frameworkDao.queryForList("searchSysResourceByParentId", parameterMap);
            for (SysResource dfSysRRes1 : childrenList) {
                childrenResList.add(dfSysRRes1.getResId());
            }
        } else if (1 == level) {
            StringBuilder sbLevel2 = new StringBuilder();
            //查询下级(二级)子节点
            parameterMap = new HashMap<String, String>();
            parameterMap.put("resParentId", resId);
            parameterMap.put("status", "Y");
            List<SysResource> childrenList = frameworkDao.queryForList("searchSysResourceByParentId", parameterMap);
            boolean first = true;
            for (SysResource sysRResource1 : childrenList) {
                childrenResList.add(sysRResource1.getResId());
                if (!first) {
                    sbLevel2.append(",");
                }
                sbLevel2.append("'").append(sysRResource1.getResId()).append("'");
                first = false;
            }
            //查询下级(三级)子节点
            parameterMap = new HashMap<String, String>();
            parameterMap.put("resParentIds", sbLevel2.toString());
            parameterMap.put("status", "Y");
            List<SysResource> children2List = frameworkDao.queryForList("searchSysResourceByParentId", parameterMap);
            for (SysResource sysRResource1 : children2List) {
                childrenResList.add(sysRResource1.getResId());
            }
        } else {//0级菜单(即根节点),暂不处理
        	
        }
        
        List<String> selfAndChildrenList = new ArrayList<String>();//自身及所有子孙节点
        selfAndChildrenList.add(resId);
        selfAndChildrenList.addAll(childrenResList);
        List<String> resIdList = new ArrayList<String>();//自身及所有子孙节点及所有祖节点
        resIdList.addAll(selfAndChildrenList);
        resIdList.addAll(parentList);
        frameworkDao.batchDelete("deleteSysRoleResByResId", selfAndChildrenList);
        
        List<String> roleIdList = sysRoleRes.getRoleIdsList();

        if (roleIdList.size() > 0) {
            List<Map<String, String>> batchDeleteList = new ArrayList<Map<String, String>>();
            for (String roleId : roleIdList) {
                for (String resId1 : parentList) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("roleId", roleId);
                    map.put("resId", resId1);
                    batchDeleteList.add(map);
                }
            }
            frameworkDao.batchDelete("deleteSysRoleResByRoleIdAndResId", batchDeleteList);
        }

        //再批量插入新关系
        List<SysRoleRes> sysRoleResList = new ArrayList<SysRoleRes>();
        for (String roleId : roleIdList) {
            for (String aResId : resIdList) {
            	SysRoleRes sysRoleResTemp = new SysRoleRes();
            	sysRoleResTemp.setRoleId(roleId);
            	sysRoleResTemp.setResId(aResId);
            	sysRoleResTemp.setFlag("1");
            	sysRoleResTemp.setCreateBy(createby);
            	sysRoleResTemp.setCreateTime(now);
                sysRoleResList.add(sysRoleResTemp);
            }
        }

        if (sysRoleResList.size() > 0) {
        	frameworkDao.batchInsert("insertSysRoleRes", sysRoleResList);
        }
	}


}