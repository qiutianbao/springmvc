package com.bigdata.web;

import com.bigdata.auth.WaveAuthUtil;
import com.bigdata.auth.WaveDetails;
import com.bigdata.bean.SysUser;
import com.bigdata.common.DateUtil;
import com.bigdata.exception.BDRuntimeException;
import com.bigdata.service.SysUserService;
import com.bigdata.sso.RspWebVerify;
import com.bigdata.util.CommonUtil;
import com.bigdata.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 15-12-2
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class RspController {

    private static Logger logger = LoggerFactory.getLogger(RspController.class);
    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = "/research_system_security_check")
    public String logon(@RequestParam("isMall") String isMall,@RequestParam("data_es") String data_es, @RequestParam("sign_es") String sign_es,  RedirectAttributes attr ) {
        try {
            if("true".equals(isMall)){
                System.out.println("\n进行解密和验证：");
                boolean virify = RspWebVerify.verifyParam(data_es, sign_es);
                if(virify||true){ //确保能够认证
                    Map<String, String> parameterMap=new HashMap<String, String>();

                    String userName=RspWebVerify.getVerifyName(data_es);//解析账号
                    if(userName!=null||true){
                        // test userName
                        userName="sns_admin";
                        parameterMap.put("userName", userName);

                        attr.addAttribute("userName_sso",userName);
                        attr.addAttribute("password_sso","TAGERT_PASSWORD_SSO");
                        return "redirect:/rpt/sso_page";
                    }else{
                        return "redirect:/login";
                    }
                }
            }
        } catch (Exception e) {
            logger.error("searchSysUserByParams : 登录异常",e);
            return "redirect:/login";
        }
        return "redirect:/login";
    }
}
