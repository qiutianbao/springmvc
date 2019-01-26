package com.bigdata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.bigdata.bean.SysDept;
import com.bigdata.service.SysDeptService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigdata.bean.SysDept;
import com.wave.dao.FrameworkDao;
import com.wave.service.BaseService;
import com.bigdata.service.SysDeptService;
import com.bigdata.util.CommonUtil;
import com.bigdata.util.JsonUtils;

/**
 * 部门
 * @todo    
 * @version 
 * @created 2013-09-10 23:00:18
 * @author  jameson.fang
 * 
 */
@Component(value="sysDeptService")
public class SysDeptServiceImpl extends BaseService implements SysDeptService {
	private static final Logger logger = Logger.getLogger(SysDeptServiceImpl.class);
	
	@Autowired
	private FrameworkDao frameworkDao;

	public int insertSysDept(SysDept sysDept) throws SQLException {
		Integer primaryKey = (Integer)frameworkDao.insert("insertSysDept", sysDept);
		if (primaryKey != null) {
			return primaryKey.intValue();
		}
		return 0;
	}


	public int deleteSysDept(Map parameterMap) throws SQLException {
		return frameworkDao.delete("deleteSysDept",parameterMap);
	}


	public int updateSysDept(SysDept sysDept) throws SQLException {
		return frameworkDao.update("updateSysDept",sysDept);
	}

	public List searchSysDeptByParams(Map parameterMap) throws SQLException {
		List sysDeptList = (List) frameworkDao.queryForList(
			"searchSysDeptByParams", parameterMap);
		return sysDeptList;
	}

	public SysDept searchSysDeptById(String id) throws SQLException {
		HashMap parameterMap = new HashMap();
		parameterMap.put("deptId", id);
		List sysDeptList = searchSysDeptByParams(parameterMap);
		if (sysDeptList != null && sysDeptList.size() > 0) {
			return (SysDept)sysDeptList.get(0);
		}
		return new SysDept();
	}
	
	public List searchAllSysDept() throws SQLException {
		return searchSysDeptByParams(new HashMap());
	}


	@Override
	public String searchDeptInfoFormInitiallyOpen() throws SQLException {
		List<String> deptIds = frameworkDao.queryForList("searchDeptInfoFormInitiallyOpen");
		StringBuilder sbInitiallyOpen = new StringBuilder();
        boolean first = true;
        for (String s : deptIds) {
            if (first) {
                first = false;
            } else {
                sbInitiallyOpen.append(",");
            }
            sbInitiallyOpen.append(s);
        }
        return sbInitiallyOpen.toString();
	}


	@Override
	public String searchDeptInfoByDeptParIdToJson(String deptParId) throws SQLException {
		return searchDeptInfoByDeptParId(deptParId, "Y");
	}


	@Override
	public String searchDeptInfoByDeptParId(String deptParId, String deptStatus)
			throws SQLException {
		List<SysDept> sysDeptInfoList = new ArrayList<SysDept>();
        //设定虚根节点
        if ("0".equals(deptParId)) {
        	SysDept rootDept = new SysDept();
            rootDept.setDeptId("-1");
            rootDept.setDeptAbbrName("temp1"); //虚节点
            rootDept.setDeptName("temp1");
            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("deptParentId", "-1");
            rootDept.setCountSub(this.searchSysDeptCount(parameterMap));
            sysDeptInfoList.add(rootDept);
        } else {
            sysDeptInfoList = searchSysDeptsByParentId(deptParId, deptStatus);
        }

        return treeDataParseIntoJson(sysDeptInfoList);
	}


	/**
     * 将查询的结果转化为树型展示数据
     *
     * @param sysDeptInfoList
     * @return
     */
    private String treeDataParseIntoJson(List<SysDept> sysDeptInfoList) {
        StringBuffer result = new StringBuffer();
        for (int i = 0, size = sysDeptInfoList.size(); i < size; i++) {
        	SysDept dept = sysDeptInfoList.get(i);
            if (i == 0) {
                result.append("[");
            }
            result.append("{").append("\"data\":\"").append(dept.getDeptAbbrName()).append("\",").append("\"attr\":").append(JsonUtils.object2json(dept)).append(",").append("\"children\":[],").append("\"state\":" + ((dept.getCountSub() != 0) ? "\"closed\"" : "\"opened\"")).append("}");
            if (i == sysDeptInfoList.size() - 1) {
                result.append("]");
            } else {
                result.append(",");
            }
        }
        return result.toString();
    }


	private int searchSysDeptCount(Map<String, String> parameterMap) throws SQLException {
		List<SysDept> listDept = this.searchSysDeptByParams(parameterMap);
		return listDept.size();
	}
	
	private List<SysDept> searchSysDeptsByParentId(String deptParId, String deptStatus) throws SQLException {
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("deptParentId", deptParId);
        parameterMap.put("deptStatus", deptStatus);
        List<SysDept> childDeptList = frameworkDao.queryForList("searchSysDeptsByParentId", parameterMap);
        return childDeptList;
    }


	@Override
	public String searchDeptByParentIdJson(HttpServletRequest request) throws SQLException {
		String deptParentId = request.getParameter("deptId");
		List<SysDept> sysDeptList = searchSysDeptsByParentId(deptParentId,null);
        return treeDataParseIntoJson(sysDeptList);
	}
	

}