package com.bigdata.service;

import com.bigdata.bean.RptInfo;
import com.bigdata.exception.BDRuntimeException;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 15-11-26
 * Time: 上午11:43
 * To change this template use File | Settings | File Templates.
 */
public interface RptInfoService {

    String searchRptInfoByParams(Map<String, String> paramterMap, String sortName, String sortOrder, int page, int pagesize) throws BDRuntimeException;

    RptInfo searchRptInfoById(String id) throws BDRuntimeException;

    void insertReportCounter(Map parameterMap) throws BDRuntimeException;

}
