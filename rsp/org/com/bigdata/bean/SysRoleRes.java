package com.bigdata.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色资源关系表
 * @version 
 * @created 2013-07-29 22:21:35
 * @author jameson.fang
 */
public class SysRoleRes {
	/**
	 *  角色ID
	 */
	private String roleId;
	/**
	 *  资源ID
	 */
	private String resId;
	/**
	 *  启动标示符
	 */
	private String flag;
	/**
	 *  创建时间
	 */
	private java.util.Date createTime;
	/**
	 *  创建人
	 */
	private String createBy;
	/**
	 *  
	 */
	private String udf1;
	/**
	 *  
	 */
	private String udf2;
	/**
	 *  
	 */
	private String udf3;
	/**
	 *  
	 */
	private String udf4;
	/**
	 *  
	 */
	private String udf5;
	/**
	 *  
	 */
	private String udf6;
	
	
	private String roleCode;
	private String  roleName;
	private String  resName;
	
	
	private List<String> roleIdsList = new ArrayList<String>();
	private String roleIdsStr;
	
	/*private String roleIds;
	
	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}*/
	
	public String getRoleIdsStr() {
		return roleIdsStr;
	}

	public void setRoleIdsStr(String roleIdsStr) {
		this.roleIdsStr = roleIdsStr;
	}

	public List<String> getRoleIdsList() {
		return roleIdsList;
	}

	public void setRoleIdsList(List<String> roleIdsList) {
		this.roleIdsList = roleIdsList;
	}

	/**
	 * 资源类型
	 */
	private String  resType;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	/**
	 * 角色ID
	 *
	 * @param roleId
	 */
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}
	
    /**
     * 角色ID
     *
     * @return
     */	
    public String getRoleId(){
    	return roleId;
    }
	
	/**
	 * 资源ID
	 *
	 * @param resId
	 */
	public void setResId(String resId){
		this.resId = resId;
	}
	
    /**
     * 资源ID
     *
     * @return
     */	
    public String getResId(){
    	return resId;
    }
	
	/**
	 * 启动标示符
	 *
	 * @param flag
	 */
	public void setFlag(String flag){
		this.flag = flag;
	}
	
    /**
     * 启动标示符
     *
     * @return
     */	
    public String getFlag(){
    	return flag;
    }
	
	/**
	 * 创建时间
	 *
	 * @param createTime
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	
    /**
     * 创建时间
     *
     * @return
     */	
    public java.util.Date getCreateTime(){
    	return createTime;
    }
	
	/**
	 * 创建人
	 *
	 * @param createBy
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	
    /**
     * 创建人
     *
     * @return
     */	
    public String getCreateBy(){
    	return createBy;
    }
	
	/**
	 * 
	 *
	 * @param udf1
	 */
	public void setUdf1(String udf1){
		this.udf1 = udf1;
	}
	
    /**
     * 
     *
     * @return
     */	
    public String getUdf1(){
    	return udf1;
    }
	
	/**
	 * 
	 *
	 * @param udf2
	 */
	public void setUdf2(String udf2){
		this.udf2 = udf2;
	}
	
    /**
     * 
     *
     * @return
     */	
    public String getUdf2(){
    	return udf2;
    }
	
	/**
	 * 
	 *
	 * @param udf3
	 */
	public void setUdf3(String udf3){
		this.udf3 = udf3;
	}
	
    /**
     * 
     *
     * @return
     */	
    public String getUdf3(){
    	return udf3;
    }
	
	/**
	 * 
	 *
	 * @param udf4
	 */
	public void setUdf4(String udf4){
		this.udf4 = udf4;
	}
	
    /**
     * 
     *
     * @return
     */	
    public String getUdf4(){
    	return udf4;
    }
	
	/**
	 * 
	 *
	 * @param udf5
	 */
	public void setUdf5(String udf5){
		this.udf5 = udf5;
	}
	
    /**
     * 
     *
     * @return
     */	
    public String getUdf5(){
    	return udf5;
    }
	
	/**
	 * 
	 *
	 * @param udf6
	 */
	public void setUdf6(String udf6){
		this.udf6 = udf6;
	}
	
    /**
     * 
     *
     * @return
     */	
    public String getUdf6(){
    	return udf6;
    }
	
}