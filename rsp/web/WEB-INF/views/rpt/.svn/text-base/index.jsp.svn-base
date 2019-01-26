<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>安信证券报告下载网站</title>
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
                <a href="${cxt}/rpt/hotRptIndex" class="Menu">热点报告</a>
                <a href="${cxt}/rpt/index" class="current"  class="Menu">我的订阅</a>
                <a href="${cxt}/org/user/changePwdPage" class="Menu">修改密码</a>
                <!-- <a href="report-subscription-direct.do"  class="Menu">个人设置</a> -->
            </div>
            <div style="clear:both;"></div>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="bg9">
                <tr><td height="8" colspan="6"></td></tr>
                <tr>
                    <td align="left">开始日期：</td>
                    <td align="left">
                        <input name="beginPublishDate" id="beginPublishDate" type="text"   onclick="WdatePicker()" title="请单击选择开始时间" value=""/>
                    </td>
                    <td align="left">结束日期：</td>
                    <td align="left">
                        <input name="endPublishDate" id="endPublishDate" type="text"  onclick="WdatePicker()" title="请单击选择结束时间" value=""/>
                    </td>
                    <td align="left">报告作者：</td>
                    <td align="left"><input name="inputAuthor" id= "inputAuthor" type="text" class="inputHint authorSelector" title="作者名称：支持名称和拼音首字母搜索"/><input name="authorId" id="authorId" type="hidden"/></td>
                </tr>
                <tr>
                    <td height="5" colspan="6"></td>
                </tr>
                <tr>
                    <td  align="left">
                        行业代码：
                    </td>
                    <td align="left">
                        <input name="industry" id="industry" type="text" class="inputHint industrySelector" title="行业代码：支持代码、名称和拼音字母搜索"/><input name="industryId" id="industryId" type="hidden"/>
                    </td>
                    <td  align="left">
                        公司代码：
                    </td>
                    <td  align="left">
                        <input name="inputRelatedProduct"  id= "inputRelatedProduct" type="text" class="inputHint productSelector" title="股票代码：支持代码和拼音首字母搜索"/><input name="relatedProductId"  id="relatedProductId" type="hidden" />
                    </td>
                    <td colspan="2" style="padding-right:60px;">
                        <a href="#" class="btn1" style="float:right;" onclick="resetData()">重 置</a>
                        <a href="#" class="btn1" style="float:right;" id="searchButton" name="searchButton">查 询</a>
                    </td>
                </tr>
                <tr><td height="8" colspan="6" align="center"><font color="red"></font></td></tr>
            </table>
            <div id="divRptList" ></div>
        </td>
    </tr>
</table>
<jsp:include page="../base/footer.jsp"></jsp:include>
</div>
</body>
</html>
