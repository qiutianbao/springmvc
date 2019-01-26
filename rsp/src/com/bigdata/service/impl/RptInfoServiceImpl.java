package com.bigdata.service.impl;

import com.bigdata.bean.RptInfo;
import com.bigdata.common.CommonUtil;
import com.bigdata.common.FileDownService;
import com.bigdata.exception.BDRuntimeException;
import com.bigdata.service.RptInfoService;
import com.wave.dao.FrameworkDao;
import com.wave.service.BaseService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 15-11-26
 * Time: 上午11:45
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class RptInfoServiceImpl extends BaseService implements RptInfoService {

    @Resource
    private FrameworkDao frameworkDao;

    @Resource
    private FileDownService fileDownService;


    @Override
    public String searchRptInfoByParams(Map<String, String> paramterMap, String sortName, String sortOrder, int page, int pagesize) throws BDRuntimeException {
        initMap(paramterMap, sortName, sortOrder, page, pagesize);
        try {
            List<RptInfo> rptList = (List<RptInfo>) frameworkDao.queryForList("searchRptInfoByParams", paramterMap);
            String total = (String) frameworkDao.queryForObject("searchRptInfoByParamsCount", paramterMap);
            return CommonUtil.list2FlexigridJson(page + "", rptList, total);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BDRuntimeException("数据库操作失败...................");
        }
    }

    @Override
    public RptInfo searchRptInfoById(String id) throws BDRuntimeException {
        Map parameterMap = new HashMap();
        parameterMap.put("id", id);
        try {
            RptInfo rptInfo= (RptInfo) frameworkDao.queryForObject("searchRptInfoById",parameterMap);
            return rptInfo;
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new BDRuntimeException("获取报告详细信息失败...................");
        }
    }

    @Override
    public void insertReportCounter(Map parameterMap) throws BDRuntimeException {
        try {
            frameworkDao.insert("insertReportCounter",parameterMap);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new BDRuntimeException("更新报告查看数量出错了...................");
        }
    }

    private void initMap(Map<String, String> parameterMap, String sortName,
                         String sortOrder, int page, int size) {
        Integer start = (page - 1) * size; // 其实位置
        Integer end = start + size;// 结束位置
        parameterMap.put("start", start.toString());
        parameterMap.put("end", end.toString());
        if (null != sortName && !"".equals(sortName)) {
            parameterMap.put("orderBy", sortName + " " + sortOrder);
        }

    }
}
