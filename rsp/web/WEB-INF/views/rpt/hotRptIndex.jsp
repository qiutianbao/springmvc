<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>热点报告</title>
    <jsp:include page="../base/init.jsp"></jsp:include>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/views/rpt/index.js"></script>
</head>
<body>
<div class="box">

<jsp:include page="../base/header.jsp"></jsp:include>


<table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#f8f8f8" >
    <tr>
        <td  align="center" class="td1">
            <div class="bg2">
                <a href="${cxt}/rpt/hotRptIndex" class="current" class="Menu">热点报告</a>
                <a href="${cxt}/rpt/index" class="Menu">我的订阅</a>
                <a href="${cxt}/org/user/changePwdPage" class="Menu">修改密码</a>
                <!-- <a href="report-subscription-direct.do"  class="Menu">个人设置</a> -->
                <input id="fromHotRptIndex" name="fromHotRptIndex" type="hidden" value="true"/>
            </div>
            <div style="clear:both;"></div>
            <div id="divRptList" ></div>
        </td>
    </tr>
</table>
<jsp:include page="../base/footer.jsp"></jsp:include>
</div>
</body>
</html>
