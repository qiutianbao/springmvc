package com.bigdata.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.annotation.Resource;

import com.bigdata.exception.BDRuntimeException;
import com.bigdata.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bigdata.bean.SysUpload;
import com.bigdata.bean.SysUser;
import com.wave.dao.FrameworkDao;
import com.bigdata.exception.BDRuntimeException;
import com.wave.service.BaseService;
import com.bigdata.service.SysUserService;
import com.bigdata.util.CommonUtil;
import com.wave.util.KeyGen;
import com.bigdata.util.MsgJsonUtil;

/**
 * 系统用户
 * @todo    
 * @version 
 * @created 2013-07-27 02:33:46
 * @author  jameson.fang
 * 
 */
@Repository(value="sysUserService")
public class SysUserServiceImpl extends BaseService implements SysUserService {
	
	@Resource
	private FrameworkDao frameworkDao;
	
	private static final Logger logger = Logger.getLogger(SysUserServiceImpl.class);

	public int insertSysUser(SysUser sysUser) throws BDRuntimeException {
		try {
			Integer primaryKey = (Integer)frameworkDao.insert("insertSysUser", sysUser);
			if (primaryKey != null) {
				return primaryKey.intValue();
			}
		} catch (SQLException e) {
			logger.error("SysUserServiceImpl.insertSysUser",e);
			throw new BDRuntimeException("SysUserServiceImpl.insertSysUser" , e);
		}
		
		return 0;
	}


	public int deleteSysUser(Map parameterMap) throws BDRuntimeException {
		try {
			return frameworkDao.delete("deleteSysUser",parameterMap);
		}catch (SQLException e) {
			logger.error("SysUserServiceImpl.deleteSysUser",e);
			throw new BDRuntimeException("SysUserServiceImpl.deleteSysUser" , e);
		}
		
	}

	public int updateSysUser(SysUser sysUser) throws BDRuntimeException {
		
		try {
			return frameworkDao.update("updateSysUser",sysUser);
		}catch (SQLException e) {
			logger.error("SysUserServiceImpl.updateSysUser",e);
			throw new BDRuntimeException("SysUserServiceImpl.updateSysUser" , e);
		}
	}

	public List searchSysUserByParams(Map parameterMap) throws BDRuntimeException {
		try {
			 List sysUserList = (List) frameworkDao.queryForList("searchSysUserByParams", parameterMap);
			 return sysUserList;
		} catch (SQLException e) {
			logger.error("SysUserServiceImpl.searchSysUserByParams",e);
			throw new BDRuntimeException("SysUserServiceImpl.searchSysUserByParams" , e);
		}
		
	}

	public SysUser searchSysUserById(String id) throws BDRuntimeException {
		HashMap parameterMap = new HashMap();
		parameterMap.put("userId", id);
		List sysUserList = searchSysUserByParams(parameterMap);
		if (sysUserList != null && sysUserList.size() > 0) {
			return (SysUser)sysUserList.get(0);
		}
		return new SysUser();
	}

    @Override
    public List<SysUser> searchAllSysUser() throws BDRuntimeException {
        List sysUserList = searchSysUserByParams(new HashMap());
        return sysUserList;
    }

    private void initMap(Map map, String sortName, String sortOrder, int page,
			int size) {
		int start = (page - 1) * size; // 其实位置
		int end = start + size;// 结束位置

		map.put("start", start);
		map.put("end", end);

		if (null != sortName && !"".equals(sortName)) {
			map.put("orderBy", sortName + " " + sortOrder);
		}
	}
	
	@Override
	public String listSysUserJsonStr(Map<String, String> parameterMap,
			String sortName, String sortOrder, int pages, int size) throws BDRuntimeException {
		try {
			initMap(parameterMap, sortName, sortOrder, pages, size);
			List<SysUser> sysUserList = frameworkDao.queryForList("searchSysUserByParamsByPage", parameterMap); 
			String total = (String) frameworkDao.queryForObject(
					"searchSysUserByParamsByPageCount", parameterMap);
			return CommonUtil.list2FlexigridJson(pages + "", sysUserList, total);
		} catch (SQLException e) {
			logger.error("SysUserServiceImpl.listSysUserJsonStr", e);
			throw new BDRuntimeException("SysUserServiceImpl.listSysUserJsonStr",e);
		}
	}


	@Override
	public String insertSysUser(SysUpload bean, String userId,String executionId) {
		
		String msg = "" ;
		SysUpload sysUpload = new SysUpload();
		sysUpload.setId(KeyGen.nextUUID());
		sysUpload.setAttachName(bean.getAttachName());
		sysUpload.setAttachSize(bean.getAttachSize());
		sysUpload.setChangeAttachName(bean.getChangeAttachName());
		sysUpload.setUploadDir(bean.getUploadDir());
		sysUpload.setCreater(userId) ;
		sysUpload.setCreateTime(new Date());
		sysUpload.setIsValid("Y");
		sysUpload.setExecutionId(executionId);
		try {
			frameworkDao.insert("insertDfWfUpload", sysUpload);
			msg = MsgJsonUtil.spellMsgJson(sysUpload.getId());
		} catch (SQLException e) {
			logger.error("附件添加到数据中出错",e);
			msg = MsgJsonUtil.spellMsgJson();
		}
		return msg ;
	}


	@Override
	public List<String> searchRoleIdListByUserId(String userId) throws SQLException {
		// 取得对应的角色列表
        return frameworkDao.queryForList("searchRoleIdListByUserId", userId);
	}


	@Override
	public List<String> searchRoleCodeListByUserId(String userId) throws SQLException {
		return frameworkDao.queryForList("searchRoleCodeListByUserId", userId);
	}


	@Override
	public List<SysUser> searchSysUserListByResId(String resId) throws SQLException {
		return frameworkDao.queryForList("searchSysUserListByResId", resId); 
	}


	@Override
	public String searchSysUserInfoByDeptIdToJson(String deptId) throws SQLException {
		List<SysUser> personsList = (List<SysUser>) frameworkDao.queryForList("searchSysUserInfoByDeptId", deptId);
        return personObjParseIntoJson(personsList);
	}
	
	/**
     * 将人员集合转化成JSON格式
     *
     * @param personsList
     * @return
     */
    private String personObjParseIntoJson(List<SysUser> personsList) {
        StringBuffer sb = new StringBuffer();
        if (personsList != null && personsList.size() > 0) {
            sb.append("[");
            for (int i = 0, size = personsList.size(); i < size; i++) {
            	SysUser b = personsList.get(i);
                String workNo = b.getUserName();
                if (StringUtils.isBlank(workNo)) {
                    workNo = "";
                }
                String mc = b.getNickname() + "[" + workNo + "]";
                if (i == size - 1) {
                    sb.append("{\"empId\":\"").append(b.getUserId()).append("\",\"name\":\"").append(mc).append("\"}]");
                } else if (i == 0) {
                    sb.append("{").append("\"empId\":\"").append(b.getUserId()).append("\",\"name\":\"").append(mc).append("\"},");
                } else
                    sb.append("{\"empId\":\"").append(b.getUserId()).append("\",\"name\":\"").append(mc).append("\"},");
            }
            logger.info(sb.toString());
        }
        return sb.toString();
    }


	@Override
	public String searchPersonSplitSign(String personGh) throws BDRuntimeException {
		String EGhOrEMc = "('" + StringUtils.replace(personGh, ",", "','")+ "')";
		List<String> personsList = null;
		try {
			personsList = (List<String>) frameworkDao.queryForList("searchPersonBySplitSign", EGhOrEMc);
		} catch (SQLException e) {
			logger.error("SysUserService.searchPersonSplitSign" , e);
			throw new BDRuntimeException("SysUserService.searchPersonSplitSign" , e);
		}
		return personsList.toString();
	}

    @Override
    public int updateSysUser(Map<String, String> parameterMap, String userType) throws BDRuntimeException {
        try {
            if("1".equals(userType)){
               return frameworkDao.update("updateSysUserToSys",parameterMap);
            }else{
               return frameworkDao.update("updateSysUserToContact",parameterMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new BDRuntimeException("更新用户登录密码失败。"+e.getMessage());
        }

    }

}