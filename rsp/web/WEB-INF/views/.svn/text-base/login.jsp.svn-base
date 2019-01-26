<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15-11-25
  Time: 下午1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    String loginCount=session.getAttribute("LOGIN_TOTAL_COUNT")==null?"0":session.getAttribute("LOGIN_TOTAL_COUNT").toString();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>报告下载网站</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/css/style-more.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/css/thickbox.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" media="all" type="text/css" href="<%=request.getContextPath()%>/css/login.css" />

    <style type="text/css"> @import "<%=request.getContextPath()%>/css/redmond.datepick.css";  </style>
    <script src="<%=request.getContextPath()%>/scripts/jquery-1.7.2.min.js" type="text/javascript" ></script>

    <script src="<%=request.getContextPath()%>/jscomponent/md5.js" type="text/javascript" ></script>

    <script type="text/javascript">
        $(function(){
           $("#codeimg").bind("click", function(event){
                var rd = Math.random()*10000;
                if(event.srcElement && event.srcElement.id === "codeimg")
                    $("#codeimg").attr("src",'<%=request.getContextPath() %>/random/getRandomCode?rand='+rd);
                if(event.target && event.target.id === "codeimg"){//firefox
                    $("#codeimg").attr("src",'<%=request.getContextPath() %>/random/getRandomCode?rand='+rd);
                }
            });
        });

        function login() {


            if('<%=loginCount%>'>5){
               alert("您登陆过于频繁！已大于5次！");
               return false;
            }


            var u = document.getElementById("userName");
            var p = document.getElementById("password");
            if(u!=null && u.value == ""){
                alert('请输入用户名！');
                return;
            }
            if(p!=null && p.value == ""){
                alert('请输入密码！');
                return;
            }
            var a = document.getElementById("authcode");
            if(a!=null && a.value == ""){
                alert('请输入验证码！');
                $("#codeimg").click();
                return;
            }

            //密码md5加密，防止传送过程中别截取可能
            p.value=hex_md5(p.value);
            document.loginForm.submit();
        }

        function keylogin(et){
            if(et.keyCode){
                if (et.keyCode==13){
                    if(et.srcElement){
                        if(et.srcElement.name=='userName' ){
                            login();
                        }
                    }
                    if(et.target){
                        if(et.target.name=='password' ){
                            login();
                        }
                    }

                }
            }else{
                if (et.which==13){
                    login();
                }
            }
        }
    </script>
</head>
<body onkeyup="keylogin(event)">
<form name="loginForm" action="<%=request.getContextPath()%>/org/user/logon" method="post" >
    <div class="login">
        <div class="top"></div>
        <div class="bottom">
            <div id="warning" class="warning" ></div>
            <div class="main">
                <div class="info">
                    <div class="infoline">
                        <div class="user">用户名：</div>
                        <div class="userinput"><input id="userName" name="userName" type="text" class="inputActive" /></div>
                    </div>
                    <div class="infoline">
                        <div class="password"><span class="word">密</span>码：</div>
                        <div class="passwordinput"><input id="password" name="password" type="password" class="inputActive" onpaste="return  false" oncopy="return false" /></div>
                    </div>
                    <div class="infoline">
                        <div class="code">验证码：</div>
                        <div class="userinput" >
                            <input type="text" name="authcode" id="authcode" class="inputActive"/>
                        </div>

                    </div>
                </div>
                <div class="button">
                    <div class="buttonimage"><a href="#"><img src="<%=request.getContextPath()%>/images/login.jpg" onclick="login()" class="buttonimage"/></a></div>
                    <div class="buttonimage"><img id='codeimg' src='<%=request.getContextPath() %>/random/getRandomCode?rand=<%=Math.random()*10000 %> ' class="buttonimage" /></div>
                </div>
                <!--<div class="massage">
                    <div class="massageline">忘记密码？找回密码</div>
                    <div class="massageline">问题反馈：<a herf=""anxin@jsvest.com</div>
                    <div class="">看不清楚？请单击图片刷新</div>
                </div>-->
            </div>
            <div class="beian">
                <span class="Eword">Copyright&nbsp;&nbsp; ©&nbsp;&nbsp;2012</span> 安信证券股份有限公司 <span class="Eword">ESSENCE SECURITIES Co. Ltd. </span><br/>
                [ 粤<span class="Eword">ICP</span>备<span class="Eword">07046349</span>号 广东省通信管理局]
            </div>
        </div>
    </div>
</form>
</body>
</html>