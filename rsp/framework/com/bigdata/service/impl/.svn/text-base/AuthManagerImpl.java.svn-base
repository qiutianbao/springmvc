package com.bigdata.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bigdata.exception.BDRuntimeException;
import com.bigdata.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.bigdata.bean.SysResource;
import com.bigdata.bean.SysRole;
import com.bigdata.bean.SysRoleRes;
import com.bigdata.bean.SysUser;
import com.bigdata.bean.SysUserRes;

@Component(value="authManager")
public class AuthManagerImpl implements AuthManager {
	/**
     * 资源类型-菜单
     */
    public static Integer RES_TYPE_MENU = 1;

    @Autowired
    private SysUserService sysUserService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysResourceService sysResourceService;
	
	@Autowired
	private SysRoleResService sysRoleResService;
	
	@Autowired
	private SysUserResService sysUserResService;
	
	@Override
	@Cacheable(value = "resCache", key = "methodName")
	public List<SysResource> getAllMenu() throws SQLException{
		//1)资源 按等级排序 且只查找菜单
        Map<String, String> parameterMap = new HashMap<String, String>();
        parameterMap.put("orderBy", "RES_LEVEL, RES_SEQ_NUM");
        //parameterMap.put("resType", String.valueOf(RES_TYPE_MENU));
        List<SysResource> dfSysRResList = this.sysResourceService.searchSysResourceByParams(parameterMap);//此查询是树的宽度优先遍历
        List<SysRoleRes> dfSysRRoleResList = this.sysRoleResService.searchAllSysRoleRes();
        List<SysUserRes> dfSysRUserResList = this.sysUserResService.searchAllSysUserRes();
        List<SysRole> dfSysRRoleList = this.sysRoleService.searchAllSysRole();
        //2-1)把res-role关系存入 resList
        for (SysRoleRes dfSysRRoleRes : dfSysRRoleResList) {
            String resId = dfSysRRoleRes.getResId();
            String roleId = dfSysRRoleRes.getRoleId();
            String roleCode = null;
            for (SysRole dfSysRRole : dfSysRRoleList) {
                if (dfSysRRole.getRoleId().equals(roleId)) {
                    roleCode = dfSysRRole.getRoleCode();
                }
            }
            //2-1-1)查找对应的res
            for (SysResource dfSysRRes : dfSysRResList) {
                if (dfSysRRes.getResId().equalsIgnoreCase(resId)) {
                    // 2-1-2)把roleId存入
                    dfSysRRes.getRoleIdList().add(roleId);
                    dfSysRRes.getRoleCodeList().add(roleCode);                           //add by folger.qin 需要用户code作判断
                }
            }
        }

        //2-2)把res-user关系存入 resList
        for (SysUserRes dfSysRUserRes : dfSysRUserResList) {
            String resId = dfSysRUserRes.getResId();
            String userId = dfSysRUserRes.getUserId();
            //2-1-1)查找对应的res
            for (SysResource dfSysRRes : dfSysRResList) {
                if (dfSysRRes.getResId().equalsIgnoreCase(resId)) {
                    // 2-1-2)把roleId存入
                    dfSysRRes.getUserIdList().add(userId);
                }
            }
        }

        //3)开始拼装返回值
        SysResource retDfSysRRes = dfSysRResList.get(0);//跟节点
        int size = dfSysRResList.size();
        for (int i = 1; i < size; i++) {
        	SysResource dfSysRRes = dfSysRResList.get(i);
            String parentId = dfSysRRes.getResParentId();
            int level = dfSysRRes.getResLevel();
            // 3-1 查找parent
            if (parentId.equalsIgnoreCase(retDfSysRRes.getResId())) {
                retDfSysRRes.getSysResourceList().add(dfSysRRes);
            } else {
            	SysResource parentDfSysRRes = null;
            	//if(!"-2".equals(retDfSysRRes.getResParentId())){
	                parentDfSysRRes = findDirectParentDfSysRRes(retDfSysRRes, parentId);
	                if (null == parentDfSysRRes) {
	                    throw new SQLException("未查找到父节点");
	                }
            	
	                // 3-2 添加到parent的子菜单列表
	                parentDfSysRRes.getSysResourceList().add(dfSysRRes);
            	//}
            }
        }
        return retDfSysRRes.getSysResourceList();
        //return dfSysRResList;
	}

	private SysResource findDirectParentDfSysRRes(SysResource retDfSysRRes,
			String parentId) {
		for (SysResource son : retDfSysRRes.getSysResourceList()) {
            if (son.getResId().equalsIgnoreCase(parentId)) {
                return son;
            } else if (son.getSysResourceList().size() > 0) {
                SysResource retDfSysRRes22 = findDirectParentDfSysRRes(son, parentId);
                if (retDfSysRRes22 != null) {
                    return retDfSysRRes22;
                }
            }
        }
        return null;
	}

	@Override
	public List<String> getRoleCodeListByUserId(String userId)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Cacheable(value = "resCache", key = "methodName")
	public Map<String, List<String>> getAllResource() throws BDRuntimeException {
		/*//取到所有资源列表
        List<SysResource> allResList = this.sysResourceService.searchAllSysResource();
        //返回Map
        Map<String, List<String>> retMap = new HashMap<String, List<String>>(allResList.size());
        for (SysResource sysResource : allResList) {
        	String resId = sysResource.getResId();
            String resUrl = sysResource.getResUrl();
            if (StringUtils.isNotBlank(resUrl)) { //如果非空的话
            	//如果是默认资源的话
                if (sysResource.getMenuDefault().equalsIgnoreCase("Y")) {

                }else{
                	List<String> roleIdAndUserIdList = new ArrayList<String>();
                    //取和该资源关联的角色列表
                    Map<String, String> parameterMap = new HashMap<String, String>();
                    parameterMap.put("resId", resId);
                    List<SysRoleRes> sysRoleResList = this.sysRoleResService.searchSysRoleResByParams(parameterMap);
                    for (SysRoleRes dfSysRRoleRes : sysRoleResList) {
                        roleIdAndUserIdList.add(dfSysRRoleRes.getRoleId());
                    }
                    //取和该资源关联的用户列表
                    parameterMap = new HashMap<String, String>();
                    parameterMap.put("resId", resId);
                    List<SysUserRes> sysUserResList = this.sysUserResService.searchSysUserResByParams(parameterMap);
                    for (SysUserRes sysUserRes : sysUserResList) {
                        roleIdAndUserIdList.add(sysUserRes.getUserId());
                    }
                    retMap.put(resId + "," + resUrl, roleIdAndUserIdList);
                }
            }
        }*/
        Map<String, List<String>> retMap = new HashMap<String, List<String>>();
        List<String> roleIdAndUserIdList = new ArrayList<String>();
        try {
            List<SysUser> listAllUser = this.sysUserService.searchAllSysUser();
            for(SysUser user:listAllUser){
                roleIdAndUserIdList.add(user.getUserId());
                roleIdAndUserIdList.add("canSeeRole");
            }
            //retMap.put("canResId,/listRpt",roleIdAndUserIdList);
            return retMap;
        } catch (BDRuntimeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw  new BDRuntimeException("AuthManagerImpl.getAllResource()获取所有人员失败！");
        }

	}

	@Override
	@CacheEvict(value = "resCache", allEntries = true)
	public void cleanCache() {
		
	}

	@Override
	public List<SysRole> getRolesByUserId(String userId) throws SQLException {
		//List<String> roleIdList = this.sysRoleService.searchRoleIdListByUserId(userId);
        List<SysRole> retList = new ArrayList<SysRole>();
        /*for (String roleId : roleIdList) {
            retList.add(this.sysRoleService.searchSysRoleById(roleId));
        }*/
        SysRole sysRole =new SysRole();
        sysRole.setRoleId("canSeeRole");
        retList.add(sysRole);
        return retList;
	}

	@Override
	public List<SysUser> searchDfSysRUsersByRoleId(String roleId)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
