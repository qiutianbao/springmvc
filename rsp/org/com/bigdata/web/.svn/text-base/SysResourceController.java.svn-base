package com.bigdata.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata.bean.SysRoleRes;
import com.bigdata.bean.SysUser;
import com.bigdata.exception.BDRuntimeException;
import com.bigdata.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigdata.auth.WaveAuthUtil;
import com.bigdata.bean.SysResource;
import com.bigdata.bean.SysRole;
import com.bigdata.bean.SysRoleRes;
import com.bigdata.bean.SysUser;
import com.bigdata.bean.SysUserRes;
import com.bigdata.bean.SysUserRole;
import com.bigdata.exception.BDRuntimeException;
import com.bigdata.service.AuthManager;
import com.bigdata.service.SysResourceService;
import com.bigdata.service.SysRoleResService;
import com.bigdata.service.SysRoleService;
import com.bigdata.service.SysUserResService;
import com.bigdata.service.SysUserRoleService;
import com.bigdata.service.SysUserService;
import com.bigdata.util.Constants;
import com.bigdata.util.DataFormat;
import com.bigdata.util.JsonUtils;
import com.bigdata.util.KeyUtils;


@Controller
@RequestMapping("/org/sysRes")
public class SysResourceController extends BaseControl{
	
	@Autowired
	private SysResourceService sysResourceService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysRoleResService sysRoleResService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysUserResService sysUserResService;
	
	@Autowired
	private AuthManager authManager;
	
	
	@RequestMapping(value = "/forwardResManager")
	public String forwardResManager(){
		return "/org/sysRes/forwardResManager";
	}
	
	
	/***
	 * 资源管理编辑【跳转】
	 * @param request
	 * @param response
	 * @return
	 * @throws com.bigdata.exception.BDRuntimeException
	 * @throws java.sql.SQLException
	 */
	@RequestMapping(value = "/cleanResCache")
	public @ResponseBody String cleanResCache(HttpServletRequest request, HttpServletResponse response) throws BDRuntimeException, SQLException{
		authManager.cleanCache();
		return "success";
	}
	
	/***
	 * 资源管理编辑【跳转】
	 * @param request
	 * @param response
	 * @return
	 * @throws com.bigdata.exception.BDRuntimeException
	 * @throws java.sql.SQLException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/forwardUpdateResByResId")
	public String forwardUpdateResByResId(HttpServletRequest request, HttpServletResponse response) throws BDRuntimeException, SQLException{
		String resId = request.getParameter("resId");
		if (StringUtils.isBlank(resId)) {
            throw new BDRuntimeException("资源id都为空!");
        }
		SysResource sysResource = sysResourceService.searchSysResourceById(resId);
        request.setAttribute("sysResource", sysResource);
        
		return "/org/sysRes/forwardUpdateResByResId";
	}
	
	/**
	 * 新建资源跳转
	 * @param request
	 * @param response
	 * @return
	 * @throws com.bigdata.exception.BDRuntimeException
	 * @throws java.sql.SQLException
	 */
	@RequestMapping(value = "/forwardInsertResByResParentId")
	public String forwardInsertResByResParentId(HttpServletRequest request, HttpServletResponse response) throws BDRuntimeException, SQLException{
		String resParentId = request.getParameter("resParentId");
		if (StringUtils.isBlank(resParentId)) {
            throw new BDRuntimeException("资源父id都为空!");
        }
		SysResource superSysResource = sysResourceService.searchSysResourceById(resParentId);
		SysResource sysResource=new SysResource();
		sysResource.setResParentId(resParentId);
		sysResource.setResParentName(superSysResource.getResName());
		sysResource.setResLevel(superSysResource.getResLevel()+1);
        request.setAttribute("sysResource", sysResource);
        
		return "/org/sysRes/forwardInsertResByResParentId";
	}
	
	@RequestMapping(value = "/insertSysResoruce")
	@ResponseBody
	public String insertSysResoruce(@ModelAttribute("sysResource") SysResource sysResource,HttpServletRequest request, HttpServletResponse response) throws BDRuntimeException, SQLException{
		sysResource.setResId(KeyUtils.nextUUID());
		sysResource.setStatus("Y");
		sysResource.setFlag("1");
		sysResource.setCreateBy(WaveAuthUtil.getEmpId());
		sysResource.setCreateTime(new Date());
		sysResourceService.insertSysResource(sysResource);
		return "success";
	}
	
	
	@RequestMapping(value = "/deleteSysRes")
	@ResponseBody
	public String deleteSysRes(String resId,HttpServletRequest request, HttpServletResponse response) throws BDRuntimeException, SQLException{
	    if (StringUtils.isBlank(resId)) {
	        throw new BDRuntimeException("资源id为空!");
	    }
	    sysResourceService.deleteSysResourceByResId(resId);
		return "success";
	}
	
	
	/***
	 * 更新资源
	 * @param request
	 * @param response
	 * @return
	 * @throws com.bigdata.exception.BDRuntimeException
	 * @throws java.sql.SQLException
	 */
	@RequestMapping(value = "/updateSysResoruce")
	public String updateSysResoruce(@ModelAttribute("sysResource") SysResource sysResource,Model model, HttpServletRequest request, HttpServletResponse response) throws BDRuntimeException, SQLException{
		//更新
		String resId = sysResource.getResId();//request.getParameter("resId");
		if (StringUtils.isBlank(resId)) {
            throw new BDRuntimeException("资源id都为空!");
        }
		sysResourceService.updateSysResource(sysResource);
		
		//刷新
		/*sysResource = sysResourceService.searchSysResourceById(resId);
		request.setAttribute("sysResource", sysResource);*/
		model.addAttribute("process",sysResource);
		return "/org/sysRes/forwardResourceView";
	}
	
	
	
	/***
	 * 资源关联角色管理编辑【跳转】
	 * @param request
	 * @param response
	 * @return
	 * @throws com.bigdata.exception.BDRuntimeException
	 * @throws java.sql.SQLException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/forwardUpdateRoleResByResId")
	public String forwardUpdateRoleResByResId(HttpServletRequest request, HttpServletResponse response) throws BDRuntimeException, SQLException{
		String resId = request.getParameter("resId");
		if (StringUtils.isBlank(resId)) {
            throw new BDRuntimeException("资源id都为空!");
        }
		SysResource sysResource = sysResourceService.searchSysResourceById(resId);
        request.setAttribute("sysResource", sysResource);
        
        //取得所有角色列表
        List<SysRole> allSysRoleList = this.sysRoleService.searchAllSysRole();
        ////因为加了缓存,所以先把选中状态清除
        for (SysRole sysRole : allSysRoleList) {
        	sysRole.setIsChecked(false);
        }
        
        //取得该资源相关的所有角色列表
        List<SysRoleRes> sysRoleResList = this.sysRoleResService.searchSysRoleResByResId(resId);
        for (SysRoleRes sysRoleRes : sysRoleResList) {
            String roleId = sysRoleRes.getRoleId();
            for (SysRole sysRole : allSysRoleList) {
                if (sysRole.getRoleId().equalsIgnoreCase(roleId)) {
                	sysRole.setIsChecked(true);
                }
            }
        }
        request.setAttribute("allRoleList", allSysRoleList);
        
		return "/org/sysRes/forwardUpdateRoleResByResId";
	}
	
	/**
	 * 资源直接关联用户编辑【跳转】
	 * @param request
	 * @param response
	 * @return
	 * @throws com.bigdata.exception.BDRuntimeException
	 * @throws java.sql.SQLException
	 */
	@RequestMapping(value = "/forwardUpdateUserResByResId")
	public String forwardUpdateUserResByResId(HttpServletRequest request, HttpServletResponse response) throws BDRuntimeException, SQLException{
		String resId = request.getParameter("resId");
		System.out.println(resId);
		if (StringUtils.isBlank(resId)) {
            throw new BDRuntimeException("资源id都为空!");
        }
		//根据resId查询资源
		SysResource sysResource = sysResourceService.searchSysResourceById(resId);
		
        //查询该资源下相关人员情况
        List<SysUser> sysUserList=sysUserService.searchSysUserListByResId(resId);
        StringBuffer strUserNames=new StringBuffer();
        StringBuffer strUserIds=new StringBuffer();
        for(int i=0;i<sysUserList.size();i++){
        	strUserNames.append(sysUserList.get(i).getNickname()+"["+sysUserList.get(i).getUserName()+"]");
        	strUserIds.append(sysUserList.get(i).getUserId());
        	if(sysUserList.size()-1!=i){
        		strUserNames.append(",");
        		strUserIds.append(",");
        	}
        }
        
        sysResource.setStrUserNames(strUserNames.toString());
        sysResource.setStrUserIds(strUserIds.toString());
        request.setAttribute("sysResource", sysResource);//资源
        
		return "/org/sysRes/forwardUpdateUserResByResId";
	}
	
	/***
	 * 根据资源ID 获取资源管理角色信息，并组装成json 返回
	 * @param request
	 * @return
	 * @throws java.sql.SQLException
	 */
	@RequestMapping(value = "/searchRoleResByResIdJson")
	public @ResponseBody String searchRoleResByResIdJson(HttpServletRequest request) throws SQLException{
		String rsJson=JsonUtils.object2json(sysRoleService.searchSysRoleByResId(request.getParameter("resId")));
		return rsJson;
	}
	
	/**
	 * 根据资源ID 获取资源关联的用户，并组装成json 文件返回
	 * @param request
	 * @return
	 * @throws java.sql.SQLException
	 */
	@RequestMapping(value = "/searchUserResByResIdJson")
	public @ResponseBody String searchUserResByResIdJson(HttpServletRequest request) throws SQLException{
		String rsJson=JsonUtils.object2json(sysUserService.searchSysUserListByResId(request.getParameter("resId")));
		return rsJson;
	}
	
	@RequestMapping(value = "/searchResByParentIdJson")
	public @ResponseBody String searchResByParentIdJson(HttpServletRequest request){
		return sysResourceService.searchResByParentIdJson(request);
	}
	
	/***
	 * 资源详细信息查看页面【跳转】
	 * @param response
	 * @param request
	 * @return
	 * @throws java.sql.SQLException
	 */
	@RequestMapping(value = "/forwardResourceView")
	public String forwardResourceView(HttpServletResponse response , HttpServletRequest request) throws SQLException{
		String resId=request.getParameter("resId");
		SysResource sysResource = sysResourceService.searchSysResourceById(resId);
		
		//查询该资源下相关人员情况
        List<SysUser> sysUserList=sysUserService.searchSysUserListByResId(resId);
        StringBuffer strUserNames=new StringBuffer();
        StringBuffer strUserIds=new StringBuffer();
        for(int i=0;i<sysUserList.size();i++){
        	strUserNames.append(sysUserList.get(i).getNickname());
        	strUserIds.append(sysUserList.get(i).getUserId());
        	if(sysUserList.size()-1!=i){
        		strUserNames.append(",");
        		strUserIds.append(",");
        	}
        }
        //添加人员名称及人员ID，以","分开
        sysResource.setStrUserNames(strUserNames.toString());
        sysResource.setStrUserIds(strUserIds.toString());
        request.setAttribute("sysResource", sysResource);//资源
		
		return "/org/sysRes/forwardResourceView";
	}
	
	
	@RequestMapping(value = "/updateSysRoleResByRoleIds")
	public void updateSysRoleResByRoleIds(@ModelAttribute("sysRoleRes") SysRoleRes sysRoleRes,HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException{
		String[] rolesIds = sysRoleRes.getRoleIdsStr().split(",");
		for(String rolesId : rolesIds){
			if(rolesId!=null&&!"".equals(rolesId))
				sysRoleRes.getRoleIdsList().add(rolesId);
		}
		sysRoleResService.updateSysRoleResByRoleIds(sysRoleRes);
		printJsonAjaxString(response,true,Constants.RETURN_SAVE_SUCCESS);
	}
	
	/***
	 * 更新资源直接关联用户操作
	 * @param response
	 * @param request
	 * @return
	 * @throws java.sql.SQLException
	 */
	@RequestMapping(value = "/updateSysUserResByUserIds")
	public @ResponseBody String updateSysUserResByUserIds(@ModelAttribute("sysUserRes") SysUserRes sysUserRes, HttpServletResponse response , HttpServletRequest request) throws SQLException{
		String userIdsStr=request.getParameter("userIds");
		String[] userIds = DataFormat.formatNullString(userIdsStr).split(",");//将字符串userid转换
		if(userIds.length>0){
			for(String userId:userIds){
				if(userId!=null&&!"".equals(userId.trim()))
					sysUserRes.getUserIdsList().add(userId);
			}
		}
		
		sysUserRes.setCreateBy(WaveAuthUtil.getEmpName());
		sysUserResService.updateSysUserResByUserIds(sysUserRes);
		return "success";
	}
	
}
