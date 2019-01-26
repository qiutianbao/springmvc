package com.bigdata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigdata.bean.SysResource;
import com.bigdata.bean.SysUserRes;
import com.wave.dao.FrameworkDao;
import com.wave.service.BaseService;
import com.bigdata.service.SysUserResService;

												

/**
 * 用户与资源关系
 * @todo    
 * @version 
 * @created 2013-07-29 22:38:01
 * @author  jameson.fang
 * 
 */
@Component(value="sysUserResService")
public class SysUserResServiceImpl extends BaseService implements SysUserResService{
	private static final Logger logger = Logger.getLogger(SysUserResServiceImpl.class);

	@Autowired
	private FrameworkDao frameworkDao;
	
	public int insertSysUserRes(SysUserRes sysUserRes) throws SQLException {
		Integer primaryKey = (Integer)frameworkDao.insert("insertSysUserRes", sysUserRes);
		if (primaryKey != null) {
			return primaryKey.intValue();
		}
		return 0;
	}


	public int deleteSysUserRes(Map parameterMap) throws SQLException {
		return frameworkDao.delete("deleteSysUserRes",parameterMap);
	}


	public int updateSysUserRes(SysUserRes sysUserRes) throws SQLException {
		return frameworkDao.update("updateSysUserRes",sysUserRes);
	}

	public List searchSysUserResByParams(Map parameterMap) throws SQLException {
		List sysUserReList = (List) frameworkDao.queryForList(
			"searchSysUserResByParams", parameterMap);
		return sysUserReList;
	}

	public SysUserRes searchSysUserResById(String id) throws SQLException {
		HashMap parameterMap = new HashMap();
		parameterMap.put("userId", id);
		List sysUserReList = searchSysUserResByParams(parameterMap);
		if (sysUserReList != null && sysUserReList.size() > 0) {
			return (SysUserRes)sysUserReList.get(0);
		}
		return new SysUserRes();
	}
	
	public List searchAllSysUserRes() throws SQLException {
		return searchSysUserResByParams(new HashMap());
	}


	@Override
	public void updateSysUserResByUserIds(SysUserRes sysUserRes) throws SQLException {
		//当前时间
		Date now = new Date();
        String createBy = sysUserRes.getCreateBy();
        //此处从前台传过来的是empId
        List<String> userIdList = sysUserRes.getUserIdsList();
        String resId = sysUserRes.getResId();//必有
        
        List<String> childrenResList = new ArrayList<String>();//所有子孙节点
        List<String> parentList = new ArrayList<String>();//所有祖节点

        //取得资源bean
        Map<String, String> parameterMap = new HashMap<String, String>();
        parameterMap.put("resId", resId);
        parameterMap.put("status", "Y");
        SysResource dfSysRRes = (SysResource) frameworkDao.queryForObject("searchSysResourceByResId", parameterMap);
        int level = dfSysRRes.getResLevel();
        String pId = dfSysRRes.getResParentId();//父菜单id
        if (3 == level) {
            parentList.add(pId);//此时pId是二级菜单
            //查询父--2级菜单
            parameterMap = new HashMap<String, String>();
            parameterMap.put("resId", pId);
            parameterMap.put("status", "Y");
            SysResource pDfSysRRes = (SysResource) frameworkDao.queryForObject("searchSysResourceByResId", parameterMap);
            String gId = pDfSysRRes.getResParentId();//1级菜单id
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
            for (SysResource dfSysRRes1 : childrenList) {
                childrenResList.add(dfSysRRes1.getResId());
                if (!first) {
                    sbLevel2.append(",");
                }
                sbLevel2.append("'").append(dfSysRRes1.getResId()).append("'");
                first = false;
            }
            //查询下级(三级)子节点
            parameterMap = new HashMap<String, String>();
            parameterMap.put("resParentIds", sbLevel2.toString());
            parameterMap.put("status", "Y");
            List<SysResource> children2List = frameworkDao.queryForList("searchSysResourceByParentId", parameterMap);
            for (SysResource dfSysRRes1 : children2List) {
                childrenResList.add(dfSysRRes1.getResId());
            }
        } else {//0级菜单(即根节点),暂不处理

        }

        List<String> selfAndChildrenList = new ArrayList<String>();//自身及所有子孙节点
        selfAndChildrenList.add(resId);
        selfAndChildrenList.addAll(childrenResList);
        List<String> resIdList = new ArrayList<String>();//自身及所有子孙节点及所有祖节点
        resIdList.addAll(selfAndChildrenList);
        resIdList.addAll(parentList);
        frameworkDao.batchDelete("deleteSysUserResByResId", selfAndChildrenList);

        if (userIdList.size() > 0) {
            List<Map<String, String>> batchDeleteList = new ArrayList<Map<String, String>>();
            for (String userId : userIdList) {
                for (String resId1 : parentList) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("userId", userId);
                    map.put("resId", resId1);
                    batchDeleteList.add(map);
                }
            }
            frameworkDao.batchDelete("deleteSysUserResByResIdAndUserId", batchDeleteList);
        }

        //再批量插入新关系
        List<SysUserRes> sysRUserResList = new ArrayList<SysUserRes>();
        for (String userId : userIdList) {
            for (String aResId : resIdList) {
            	SysUserRes dfSysRUserRes1 = new SysUserRes();
                dfSysRUserRes1.setResId(aResId);
                dfSysRUserRes1.setUserId(userId);
                dfSysRUserRes1.setCreateBy(createBy);
                dfSysRUserRes1.setCreateTime(now);
                sysRUserResList.add(dfSysRUserRes1);
            }
        }
        if (sysRUserResList.size() > 0) {
            frameworkDao.batchInsert("insertSysUserRes", sysRUserResList);
        }
	}

}