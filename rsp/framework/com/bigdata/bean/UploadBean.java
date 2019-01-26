package com.bigdata.bean;

import java.io.Serializable;

import com.bigdata.util.MathUtils;


/***
 * 为做统一上传附件使用
 * @author Jameson
 *
 */
public class UploadBean implements Serializable{
	/**
	 * serialVersionUID
	 * long
	 * UploadBean.java
	 * 2011-5-16 下午02:32:39
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 附件名称
	 */
	private String attachName;
	
	/**
	 * 流程实例ID
	 */
	private String executionId;
	
	/**
	 *  流程实例ID
	 * @return
	 */
	public String getExecutionId() {
		return executionId;
	}
	
	/**
	 * 流程实例ID
	 * @param executionId
	 */
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	/**
	 * 附件大小(单位:KB)
	 */
	private float attachSizeKB;
	/**
	 * 附件大小(单位:MB)
	 */
	private float attachSizeMB;
	/**
	 * 附件大小(单位:GB)
	 */
	private float attachSizeG;
	
	/**
	 * 改变后的文件名称
	 */
	private String changeAttachName;
	
	/**
	 * 上传文件路径
	 */
	private String uploadDir;
	
	/**
	 * 人员ID
	 */
	private String userId;
	
	/**
	 * 获取人员ID
	 * @return
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * 设置人员ID
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 附件名称
	 * @return
	 */
	public String getAttachName() {
		return attachName;
	}
	
	/**
	 * 附件名称
	 * @return
	 */
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	
	
	/**
	 * 改变后的文件名称
	 */
	public String getChangeAttachName() {
		return changeAttachName;
	}
	
	/**
	 * 改变后的文件名称
	 */
	public void setChangeAttachName(String changeAttachName) {
		this.changeAttachName = changeAttachName;
	}
	
	/**
	 * 附件路径
	 */
	public String getUploadDir() {
		return uploadDir;
	}
	
	/**
	 * 附件路径
	 */
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	
	/**
	 * 附件大小(单位:KB)
	 */
	public float getAttachSizeKB() {
		return attachSizeKB;
	}
	
	/**
	 * 附件大小(单位:KB)
	 */
	public void setAttachSizeKB(long attachSizeKB) {
		this.attachSizeKB = attachSizeKB;
		this.attachSizeMB = MathUtils.formatFloat("0.0000", attachSizeKB/(1024f));
		this.attachSizeG = MathUtils.formatFloat("0.0000", attachSizeKB/(1024f)/(1024f));
	}
	
	/**
	 * 附件大小(单位:MB)
	 */
	public float getAttachSizeMB() {
		return this.attachSizeMB;
	}
	
	/**
	 * 附件大小(单位:G)
	 */
	public float getAttachSizeG() {
		return this.attachSizeG;
	}
}
