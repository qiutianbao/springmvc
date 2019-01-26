package com.bigdata.service;

import com.bigdata.bean.SysResource;
import com.bigdata.bean.SysRole;
import com.bigdata.bean.SysUser;
import com.bigdata.exception.BDRuntimeException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AuthManager {
    /**
     * 取得所有菜单
     *
     * @return
     * @throws java.sql.SQLException
     */
    List<SysResource> getAllMenu() throws SQLException;

    /**
     * 根据userId取到roleCode列表
     *
     * @param userId
     * @return
     * @throws java.sql.SQLException
     */
    List<String> getRoleCodeListByUserId(String userId) throws SQLException;

    /**
     * 获取所有资源的缓存Map
     *
     * @return Map<String,List<String>> key为(res_id,res_url)(资源id,资源url) ， values为角色id或人员id
     * @throws java.sql.SQLException
     */
    Map<String, List<String>> getAllResource() throws  BDRuntimeException;

    /**
     * 清除缓存方法
     */
    void cleanCache();

    /**
     * 通过用户ID查询出角色bean列表
     *
     * @param userId 用户ID
     * @return 角色bean列表
     * @throws java.sql.SQLException
     */
    List<SysRole> getRolesByUserId(String userId) throws SQLException;

    /**
     * 通过角色ID查询出用户bean列表
     *
     * @param roleId 角色Id
     * @return 用户bean列表
     * @throws java.sql.SQLException
     */
    List<SysUser> searchDfSysRUsersByRoleId(String roleId) throws SQLException;
}
