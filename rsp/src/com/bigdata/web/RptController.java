package com.bigdata.web;

import com.bigdata.bean.RptInfo;
import com.bigdata.bean.SysUser;
import com.bigdata.common.FileDownService;
import com.bigdata.exception.BDRuntimeException;
import com.bigdata.service.RptInfoService;
import com.bigdata.util.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 首页控制器
 */
@Controller
@RequestMapping("/rpt")
public class RptController {

    @Resource
    private RptInfoService rptInfoService;

    @Resource
    private FileDownService fileDownService;

    @RequestMapping(value = "/rptDetail")
    public String rptDetail(Model model,HttpServletRequest request,HttpSession session) throws BDRuntimeException{

        String id = request.getParameter("id");

        Map parameterMap=new HashMap();
        //记录查看数量UF_M_REPORTCOUNTER
        SysUser currentUser=UserUtil.getUserFromSession(session);
        parameterMap.put("id",id);
        parameterMap.put("userId",currentUser.getUserId());
        parameterMap.put("operatetype","web_counter_see");
        rptInfoService.insertReportCounter(parameterMap);

        RptInfo rptInfo=rptInfoService.searchRptInfoById(id);
        model.addAttribute("rptInfo",rptInfo);
        return "/rpt/rptDetail";

    }

    @RequestMapping(value = "/rptFileDownload")
    public void rptFileDownload(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws BDRuntimeException {
        String id = request.getParameter("id");
        try {
            Map parameterMap=new HashMap();
            //记录下载数量UF_M_REPORTCOUNTER
            SysUser currentUser=UserUtil.getUserFromSession(session);
            parameterMap.put("id",id);
            parameterMap.put("userId",currentUser.getUserId());
            parameterMap.put("operatetype","web_counter_download");
            rptInfoService.insertReportCounter(parameterMap);

            //获取报告基本信息
            RptInfo rptInfo=rptInfoService.searchRptInfoById(id);
            String attach_dir=rptInfo.getAttach_dir();

            //附件路径地址
            String filePathDir =this.fileDownService.downloadFile(attach_dir);

            InputStream file = null;
            byte data[] = new byte[1024];
            int byteread;
            File thefile = new File(filePathDir);

            file = new BufferedInputStream(new FileInputStream(thefile));

            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/octet-stream");
            try {
                response.setHeader("content-disposition","attachment; filename="+ new String(rptInfo.getAttach_name().getBytes("gb2312"), "ISO8859_1"));
            } catch (Exception ecode) {
            }
            while ((byteread = file.read(data)) != -1) {
                out.write(data, 0, byteread);
                out.flush();
            }
            file.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new BDRuntimeException("报告下载出错！Error:"+e.getMessage());
        }
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "/rpt/index";
    }

    @RequestMapping(value = "/sso_page")
    public String sso_page(HttpServletRequest request) {
        return "rpt/sso_page";
    }


    @RequestMapping(value = "/hotRptIndex")
    public String hotRptIndex(HttpServletRequest request) {
        request.setAttribute("fromHotRptIndex","1");
        return "/rpt/hotRptIndex";
    }

    @RequestMapping(value = "/listRpt")
    @ResponseBody
    public String listRpt(HttpServletRequest request, HttpServletResponse response)
            throws BDRuntimeException {
        try{
            int page = Integer.valueOf(request.getParameter("page"));
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            int pagesize = Integer.valueOf(request.getParameter("pagesize"));


            Map<String, String> paramterMap = getParameterMap(request);
            String jsonStringRs = rptInfoService.searchRptInfoByParams(paramterMap, sortName, sortOrder, page, pagesize);
            return jsonStringRs;
        }catch (Exception e){
            e.printStackTrace();
            throw  new BDRuntimeException("执行listRpt方法失败>>>>>"+e.getMessage());
        }
    }

    /***
     * 参数处理
     * @param request
     * @return
     */
    private Map<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String> parameterMap = new HashMap<String, String>();

        String fromHotRptIndex=request.getParameter("fromHotRptIndex");
        //查询参数
        String rpt_date_begin = request.getParameter("rpt_date_begin");
        String rpt_date_end = request.getParameter("rpt_date_end");
        String rpt_authors = request.getParameter("rpt_authors");
        String rpt_indu = request.getParameter("rpt_indu");
        String rpt_stk_name_code = request.getParameter("rpt_stk_name_code");

        if (rpt_date_begin != null && !"".equals(rpt_date_begin)&& !"undefined".equals(rpt_date_begin))
            parameterMap.put("rpt_date_begin", rpt_date_begin );

        if (rpt_date_end != null && !"".equals(rpt_date_end)&& !"undefined".equals(rpt_date_end))
            parameterMap.put("rpt_date_end",rpt_date_end );

        if (rpt_authors != null && !"".equals(rpt_authors)&& !"undefined".equals(rpt_authors))
            parameterMap.put("rpt_authors", "%" + rpt_authors + "%");

        if (rpt_indu != null && !"".equals(rpt_indu)&& !"undefined".equals(rpt_indu))
            parameterMap.put("rpt_indu", "%" + rpt_indu + "%");

        if (rpt_stk_name_code != null && !"".equals(rpt_stk_name_code)&& !"undefined".equals(rpt_stk_name_code))
            parameterMap.put("rpt_stk_name_code", "%" + rpt_stk_name_code + "%");

        if (fromHotRptIndex != null && !"".equals(fromHotRptIndex)&& !"undefined".equals(fromHotRptIndex))
            parameterMap.put("fromHotRptIndex", fromHotRptIndex);
        else
            parameterMap.put("contact_id", UserUtil.getUserFromSession(request.getSession()).getUserId());
        return parameterMap;
    }

}
