<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>

<html><head><title>出错页面</title>
</head>

<body>
<table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td >
            <br>
            <br><br><br><br><br><br>

            <div align="center">
                <img src="<%=request.getContextPath()%>/images/cancel.gif">
                <font size="2" style="color: red">
                    <B>对不起！没有发现您请求的页面！<strong>Errr is 404.</strong><br><br>【如有疑问请联系管理员】。</B>
                </font><br><br>
                <font size="2"><B>点这里返回主页面：<a href="<%=request.getContextPath()%>/rpt/index">Go To Home Page.</a></B></font>
            </div>
        </td>
    </tr>
</table>
</body>
</html>