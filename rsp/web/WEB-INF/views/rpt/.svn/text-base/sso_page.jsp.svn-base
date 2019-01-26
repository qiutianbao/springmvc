<%
    String userName_sso= request.getParameter("userName_sso");
    String password_sso= request.getParameter("password_sso");
%>
<script language="JavaScript" type="text/JavaScript">
    window.onload = function(){
        document.loginForm.submit();
    }
</script>
<form name="loginForm" action="<%=request.getContextPath()%>/org/user/logon" method="post" >
    <input id="userName" name="userName" type="hidden"  value="<%=userName_sso%>" />
    <input id="password" name="password" type="hidden"  value="<%=password_sso%>" />
</form>