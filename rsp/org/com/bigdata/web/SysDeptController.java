package com.bigdata.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata.exception.BDRuntimeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigdata.bean.SysDept;
import com.bigdata.exception.BDRuntimeException;
import com.bigdata.service.SysDeptService;



@Controller
@RequestMapping("/org/sysDept")
public class SysDeptController {
	
	@Autowired
	private SysDeptService sysDeptService;
	
	@RequestMapping(value = "/forwardDeptManager")
	public String forwardDeptManager(){
		return "/org/sysDept/forwardDeptManager";
	}
	
	/***
	 * 部门查看视图跳转
	 * @param request
	 * @return
	 * @throws java.sql.SQLException
	 */
	@RequestMapping(value = "/forwardDeptView")
	public String forwardDeptView(HttpServletRequest request) throws SQLException{
		String deptId = request.getParameter("deptId");
		SysDept sysDept=sysDeptService.searchSysDeptById(deptId);
		SysDept PSysDept=sysDeptService.searchSysDeptById(sysDept.getDeptParentId());
		sysDept.setDeptParentName(PSysDept.getDeptName());
		request.setAttribute("sysDept", sysDept);
		
		return "/org/sysDept/forwardDeptView";
	}
	
	/***
	 * 部门管理编辑【跳转】
	 * @param request
	 * @param response
	 * @return
	 * @throws com.bigdata.exception.BDRuntimeException
	 * @throws java.sql.SQLException
	 */
	@RequestMapping(value = "/forwardUpdateDeptByDeptId")
	public String forwardUpdateDeptByDeptId(HttpServletRequest request, HttpServletResponse response) throws BDRuntimeException, SQLException{
		String deptId = request.getParameter("deptId");
		if (StringUtils.isBlank(deptId)) {
            throw new BDRuntimeException("部门deptId都为空!");
        }
		SysDept sysDept = sysDeptService.searchSysDeptById(deptId);
        request.setAttribute("sysDept", sysDept);
		return "/org/sysDept/forwardUpdateDeptByDeptId";
	}
	
	
	@RequestMapping(value = "/updateSysDept")
	public String updateSysResoruce(@ModelAttribute("sysDept") SysDept sysDept, HttpServletRequest request, HttpServletResponse response) throws BDRuntimeException, SQLException{
		String deptId = sysDept.getDeptId();
		if (StringUtils.isBlank(deptId)) {
            throw new BDRuntimeException("部门deptId都为空!");
        }
		sysDeptService.updateSysDept(sysDept);
		return "/org/sysDept/forwardDeptView";
	}
	
	
	@RequestMapping(value = "/searchDeptByParentIdJson")
	public @ResponseBody String searchDeptByParentIdJson(HttpServletRequest request) throws SQLException {
		return sysDeptService.searchDeptByParentIdJson(request);
	}
	
	/**
	 * 新建部门跳转
	 * @param request
	 * @param response
	 * @return
	 * @throws com.bigdata.exception.BDRuntimeException
	 * @throws java.sql.SQLException
	 */
	@RequestMapping(value = "/forwardInsertDeptByDeptParentId")
	public String forwardInsertDeptByDeptParentId(HttpServletRequest request, HttpServletResponse response) throws BDRuntimeException, SQLException{
		String deptParentId = request.getParameter("deptParentId");
		if (StringUtils.isBlank(deptParentId)) {
            throw new BDRuntimeException("部门父id都为空!");
        }
		SysDept superSysDept = sysDeptService.searchSysDeptById(deptParentId);
		SysDept sysDept=new SysDept();
		sysDept.setDeptParentId(deptParentId);
		sysDept.setDeptParentName(superSysDept.getDeptName());
		sysDept.setDeptLevel(superSysDept.getDeptLevel()+1);
        request.setAttribute("sysDept", sysDept);
        
		return "/org/sysDept/forwardInsertDeptByDeptParentId";
	}
	
}
