package com.bigdata.service;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import com.bigdata.bean.SysUpload;


/**
 * 文件上传
 * @todo    
 * @version 
 * @created 2013-08-02 10:30:46
 * @author  jameson.fang
 */
public interface SysUploadService {

    int insertSysUpload(SysUpload sysUpload) throws SQLException;

	int deleteSysUpload(Map parameterMap) throws SQLException;

	
	int updateSysUpload(SysUpload sysUpload) throws SQLException;
	
	
	List searchSysUploadByParams(Map parameterMap) throws SQLException;

	
	SysUpload searchSysUploadById(String id) throws SQLException;
	
	
	List searchAllSysUpload() throws SQLException;

}