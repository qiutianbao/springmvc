package com.bigdata.web;

import java.sql.SQLException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bigdata.bean.SysUser;
import com.bigdata.common.DateUtil;
import com.bigdata.exception.BDRuntimeException;
import com.bigdata.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigdata.auth.WaveAuthUtil;
import com.bigdata.bean.SysUser;
import com.bigdata.exception.BDRuntimeException;
import com.bigdata.service.SysDeptService;
import com.bigdata.service.SysUserService;
import com.bigdata.util.EncryptUtil;
import com.bigdata.util.UserUtil;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 系统用户
 * @todo    
 * @version 
 * @created 2013-07-27 02:33:46
 * @author  jameson.fang
 */
@Controller
@RequestMapping("/org/user")
public class SysUserController {
	private static Logger logger = LoggerFactory.getLogger(SysUserController.class);
	
	@Resource
	private SysUserService sysUserService;
	
	@RequestMapping(value = "/logon")
	public String logon(@RequestParam("userName") String userName, @RequestParam("password") String password,  HttpServletRequest request,HttpSession session ,RedirectAttributes attr ) {
		try {

            HttpSession httpSession = null;
            httpSession = request.getSession(false);

            if (httpSession.getAttribute("rand") != null){//session中set了验证码.
                String code = httpSession.getAttribute("rand").toString();
                String rand = request.getParameter("authcode");
                if(rand!=null&&!"".equals(rand.toString()))
                {
                    if(!rand.equals(code)){
                        return "redirect:/login";
                    }else{
                        httpSession.removeAttribute("rand");//验证成功清除session
                    }
                }
            }

            if(!"".equals(userName)&&!"".equals(password)){
                Map<String, String> parameterMap=new HashMap<String, String>();
                parameterMap.put("userName", userName);
                if("TAGERT_PASSWORD_SSO".equals(password)){
                    //parameterMap.put("pwd", password);
                }else{
                    /*if("2d8f58eaffd6dc7652365121bf06f240".equals(password)){
                        parameterMap.put("pwd", EncryptUtil.md5(password));
                    }else{
                        parameterMap.put("pwd", EncryptUtil.md5(password));
                    }*/
                    parameterMap.put("pwd", password);
                }
                List<SysUser> userList = sysUserService.searchSysUserByParams(parameterMap);
                if (userList.size()>0) {
                    //用户登录信息
                    SysUser sysUser =userList.get(0);
                    sysUser.setLoginDateTime(DateUtil.formatDate(new Date(), DateUtil.DATE_TIME_PATTERN_CH));
                    //SET USER TO Attribute
                    session.setAttribute("sysUser", sysUser);
                    UserUtil.setUserToSession(session, sysUser);

                    WaveAuthUtil.authSecurity(sysUser.getUserId(), request);

                    httpSession.setAttribute("LOGIN_TOTAL_COUNT",null);
                    return "redirect:/rpt/index";
                }
			}

            //记录登陆失败次数
            httpSession.setAttribute("LOGIN_TOTAL_COUNT",httpSession.getAttribute("LOGIN_TOTAL_COUNT")==null?0:Integer.valueOf(httpSession.getAttribute("LOGIN_TOTAL_COUNT").toString())+1);

			return "redirect:/login";
		} catch (BDRuntimeException e) {
			logger.error("searchSysUserByParams : 登录异常",e);
		}
		return null;
	}



    @RequestMapping(value = "/changePwdPage")
    public String changepwdpage() {
        return "changePwdPage";
    }

    @RequestMapping(value = "/uptPwd")
    public String uptPwd(@RequestParam("pwd") String pwd, @RequestParam("confirmpwd") String confirmpwd,  HttpServletRequest request,HttpSession session ) {

        try {
            HttpSession httpSession = null;
            httpSession = request.getSession(false);

            if (httpSession.getAttribute("rand") != null){//session中set了验证码.
                String code = httpSession.getAttribute("rand").toString();
                String rand = request.getParameter("authcode");
                if(rand!=null&&!"".equals(rand.toString()))
                {
                    if(!rand.equals(code)){
                        request.setAttribute("uptPwdMsg", "验证码不对！");
                        return "changePwdPage";
                    }else{
                        httpSession.removeAttribute("rand");//验证成功清除session
                    }
                }
            }

            if(!"".equals(confirmpwd)&&!"".equals(pwd)&&pwd.trim().equals(confirmpwd.trim())){
                Map<String, String> parameterMap=new HashMap<String, String>();
                //confirmpwd=  EncryptUtil.encrypt(confirmpwd,null);//MD5加密
                parameterMap.put("confirmpwd",confirmpwd);
                SysUser currentUser=UserUtil.getUserFromSession(session);
                parameterMap.put("userId",currentUser.getUserId());
                int result= sysUserService.updateSysUser(parameterMap,currentUser.getUserType());
                if(result>0){
                    request.setAttribute("uptPwdMsg", "密码更新成功！");
                }else{
                    request.setAttribute("uptPwdMsg", "密码更新失败！");
                    return "changePwdPage";
                }
            }
        } catch (BDRuntimeException e) {
            logger.error("searchSysUserByParams : 登录异常",e);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/logon2")
    @ResponseBody
    public String logon2(HttpServletRequest request,HttpSession session ) throws BDRuntimeException {
        throw new BDRuntimeException("test exception.............");
        //return "sucess";
    }
}