<%@ page contentType="text/html; charset=UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <!--- 初始化 --->
    <jsp:include page="../base/init.jsp"></jsp:include>

</head>
<body>
<div class="box">
<!--- 页眉 --->
<jsp:include page="../base/header.jsp"></jsp:include>

<table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#f8f8f8" >
    <tr>
        <td class="font_12_18 td1" >

            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:20px;">
                <tr>
                    <td width="180">&nbsp;</td>
                    <td id="t" align="center" class="font_black_18">${rptInfo.rpt_title}</td>
                    <td width="150" align="right">

                        <a  class="ajaxbox6"  href="addBookmark">[收藏]</a>


                        <a href="javascript:window.print()">[打印 ]</a>
                        <a id="downloadAttaches" href="<%=request.getContextPath()%>/rpt/rptFileDownload?id=${rptInfo.id}">[下载 ]</a>
                        <a href="index" target="_blank">[搜索]</a>
                    </td>
                    <td width="30">&nbsp;</td>
                </tr>
            </table>
            <hr />
            <table width="100%" border="0" cellspacing="0" cellpadding="0"><tbody>
                <tr>
                    <td id="zy" colspan="2" align="center" class="font_blue">信息来源：安信证券&nbsp;&nbsp;日期：${rptInfo.rpt_date}&nbsp;&nbsp;作者：${rptInfo.rpt_authors}</td>
                </tr>
                <tr><td colspan="2">&nbsp;</td></tr>
                <tr><td colspan="2">&nbsp;</td></tr>
                <tr>
                    <td colspan="2">
                        <p id="ty" style="padding:0px 20px;"><b>报告类别：</b>${rptInfo.rpt_type}</p></td>
                </tr>
                <tr><td colspan="2">&nbsp;</td></tr>

                <tr>
                    <td colspan="2"><p id="cp" style="padding:0px 20px;"><b>相关公司：</b>${rptInfo.secu_sht}</p></td>
                </tr>
                <tr><td colspan="2">&nbsp;</td></tr>

                <tr>
                    <td colspan="2">
                        <p id="ds" style="padding:0px 20px;"><b>相关行业：</b>${rptInfo.rpt_indu}</p></td>
                </tr>
                <tr><td colspan="2">&nbsp;</td></tr>
                <tr>
                    <td colspan="2"><p id="sc" style="padding:0px 20px;"><b>报告打分：</b>0</p></td>
                </tr>
                <tr><td colspan="2">&nbsp;</td></tr>
                <tr>
                    <td colspan="2">
                        <p id="ct" style="padding:0px 20px;word-wrap:break-word;width:700px">
                            ${rptInfo.rpt_summary}
                        </p>
                    </td>
                </tr>
                <tr><td colspan="2">&nbsp;</td></tr>

                <tr><td colspan="2" style="padding-left:30px;">
                    <span id="reportdown">
                        <a href="<%=request.getContextPath()%>/rpt/rptFileDownload?id=${rptInfo.id}">
                            <img src="<%=request.getContextPath()%>/images/attach.gif" width="9" height="15">${rptInfo.attach_name}</a><br></span>
                </td></tr>


                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>

                </tbody></table>
            <!-- 报告评论/回评 -->
            <div style="clear:both;padding:20px;">
                <!-- 已有评论 -->
                <table id="tab" width="100%" border="0" cellspacing="0" cellpadding="0">

                </table>
            </div>

            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td  align="left" class="font_gray">
                        声明：本研究报告由安信证券股份有限公司研究中心撰写,研究报告中所提供的信息仅供参考。报告根据国际和行业通行的准则，以合法渠道获得这些信息，尽可能保证可靠、准确和完整，但并不保证报告所述信息的准确性和完整性。
                        本报告不能作为投资研究决策的依据，不能作为道义的、责任的和法律的依据或者凭证，无论是否已经明示或者暗示。安信证券股份有限公司研究中心将随时补充、更正和修订有关信息，但不保证及时发布。对于本报告所提供信息所
                        导致的任何直接的或者间接的投资盈亏后果不承担任何责任。本公司及其关联机构可能会持有报告中涉及公司发行的证券并进行交易，并提供或争取提供投资银行或财务顾问服务。
                        本报告版权仅为安信证券股份有限公司研究中心所有，未经书面许可，任何机构和个人不得以任何形式翻版、复制和发布。如引用发布，需注明出处为安信证券研究中心，且不得对本报告进行有悖原意的引用、删节和修改。
                        安信证券股份有限公司研究中心对于本免责申明条款具有修改权和最终解释权。
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<!--- 页脚 --->
<jsp:include page="../base/footer.jsp"></jsp:include>
</div>
</body>
</html>
