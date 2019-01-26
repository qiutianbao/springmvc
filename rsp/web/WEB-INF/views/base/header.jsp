<%@ page import="com.bigdata.common.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.bigdata.util.UserUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15-11-25
  Time: 下午6:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#d2d3d0"  class="bg3">
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="bg5">${sysUser.nickname}&nbsp&nbsp<a href="${cxt}/j_spring_security_logout">退出</a><br />
                        <div id="currentTime"></div>本次登录时间:<%=UserUtil.getUserFromSession(session)!=null?UserUtil.getUserFromSession(session).getLoginDateTime():""%></td>
                    <td width="55" align="left" valign="top">&nbsp;</td>
                    <td class="bg6"><img src="<%=request.getContextPath()%>/images/call.gif" width="22" height="22" align="absmiddle" />客服热线：95517<br />
                        <div id="currentTime"></div><%=DateUtil.formatDate(new Date(),DateUtil.DATE_PATTERN_CH)%> <span id="sp_week"></span></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<script>
    function dateToWeek(){
        var date=new Date();
        var weekNum=date.getDay();
        var week=null;
        if(weekNum==0)
        {
            week="星期天";
        }
        else if(weekNum==1){
            week="星期一";
        }
        else if(weekNum==2){
            week="星期二";
        }
        else if(weekNum==3){
            week="星期三";
        }
        else if(weekNum==4){
            week="星期四";
        }
        else if(weekNum==5){
            week="星期五";
        }
        else if(weekNum==6){
            week="星期六";
        }
        document.getElementById("sp_week").innerHTML=week;
    }
    dateToWeek();
</script>

