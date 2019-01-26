package com.bigdata.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户与资源关系
 * @version 
 * @created 2013-07-29 22:38:01
 * @author jameson.fang
 */
public class SysUserRes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *  用户ID
	 */
	private String userId;
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

	private String nickname;
	private String userName;
	private String resType;
	private String resName;
	

	private List<String> userIdsList = new ArrayList<String>();

	public List<String> getUserIdsList() {
		return userIdsList;
	}

	public void setUserIdsList(List<String> userIdsList) {
		this.userIdsList = userIdsList;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	/**
	 * 用户ID
	 *
	 * @param userId
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
    /**
     * 用户ID
     *
     * @return
     */	
    public String getUserId(){
    	return userId;
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