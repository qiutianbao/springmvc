<%@ page import="com.bigdata.util.DataFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>热点报告</title>
    <jsp:include page="base/init.jsp"></jsp:include>
    <script src="/jscomponent/md5.js" type="text/javascript" ></script>
    <script type="text/javascript">
        function beforeSearchFormSubmit() {
            var pwd = $("#pwd").val();
            var confirmpwd = $("#confirmpwd").val();
            if(!pwd||!confirmpwd){
                alert("密码不能为空!");
                return false;
            }
            if(pwd!=confirmpwd){
                alert("两次密码输入不一致!");
                return false;
            }
            var a = document.getElementById("authcode");
            if(a!=null && a.value == ""){
                alert('请输入验证码！');
                $("#codeimg").click();
                return;
            }

            //密码md5加密，防止传送过程中别截取可能
            $("#pwd").val(hex_md5(pwd));
            $("#confirmpwd").val(hex_md5(confirmpwd));
            document.uptPwdForm.submit();
        }
    </script>
</head>
<body>
<div class="box">

    <jsp:include page="base/header.jsp"></jsp:include>


    <table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#f8f8f8" >
        <tr>
            <td  align="center" class="td1">
                <div class="bg2">
                    <a href="${cxt}/rpt/hotRptIndex" class="Menu">热点报告</a>
                    <a href="${cxt}/rpt/index"  class="Menu">我的订阅</a>
                    <a href="${cxt}/org/user/changePwdPage" class="current"  class="Menu">修改密码</a>
                    <!-- <a href="report-subscription-direct.do"  class="Menu">个人设置</a> -->
                </div>
                <div style="clear:both;"></div>
                <div class="border">
                    <div  class="table_border">

                        <form action="${cxt}/org/user/uptPwd" method="post" id="uptPwdForm" name="uptPwdForm">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="bg9">
                                <tr>
                                    <td align="right">
                                        新密码<span style="color: red;">*</span>：
                                    </td>
                                    <td align="left">
                                        <input name="pwd" id="pwd" type="password" class="inputActive" />
                                    </td>
                                    <td align="right">
                                        确认密码<span style="color: red;">*</span>：
                                    </td>
                                    <td align="left">
                                        <input name="confirmpwd"  id= "confirmpwd" type="password" class="inputActive" />
                                    </td>
                                    <td align="left"></td>
                                    <td colspan="2" align="right" >
                                       <a href="#" class="btn1" onclick="beforeSearchFormSubmit()">确认提交</a>
                                    </td>
                                </tr>

                                <tr>
                                    <td align="right">
                                        验证码：<span style="color: red;">*</span>：
                                    </td>
                                    <td align="left">
                                        <input type="text" name="authcode" id="authcode" class="inputActive"/>
                                        <img id='codeimg' src='<%=request.getContextPath() %>/random/getRandomCode?rand=<%=Math.random()*10000 %> ' />
                                    </td>
                                    <td align="left">

                                    </td>
                                    <td align="left">

                                    </td>
                                    <td align="left"></td>
                                    <td colspan="2" align="right" >
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2" align="center">
                                        <span><font style="color: cornflowerblue"><strong>
                                                <%=request.getAttribute("uptPwdMsg")==null?"":request.getAttribute("uptPwdMsg")%></strong></font></span>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>

            </td>
        </tr>
    </table>
    <jsp:include page="base/footer.jsp"></jsp:include>
</div>
</body>
</html>
