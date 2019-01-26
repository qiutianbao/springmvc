<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15-11-25
  Time: 下午5:27
  To change this template use File | Settings | File Templates.
--%>

<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/fn.tld" prefix="fn"%>
<%@taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/style-more.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/thickbox.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" media="all" type="text/css" href="${ctx}/css/login.css" />

<style type="text/css">
    @import "${ctx}/css/redmond.datepick.css";
</style>
<script src="${ctx }/jscomponent/common/jquery-1.7.2.min.js" type="text/javascript" ></script>

<script type='text/javascript' src='${ctx}/scripts/jquery.coolinput.js'></script>
<script type='text/javascript' src='${ctx}/scripts/jquery.datepick.min.js'></script>
<script type='text/javascript' src='${ctx}/scripts/jquery.datepick-zh-CN.js'></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.bgiframe.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.dimensions.min.js"></script>
<script type='text/javascript' src='${ctx}/scripts/jquery.form.js'></script>
<%--<link href="${ctx}/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/css/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" />--%>
<script src="/scripts/jquery-ui.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.mousewheel-3.0.4.pack.js"></script>


<link href="${ctx }/jscomponent/ligerUI/skins/Aqua/css/ligerui-all.css" type="text/css"  rel="stylesheet" />
<script src="${ctx }/jscomponent/comm.js" type="text/javascript" ></script>
<script src="${ctx }/jscomponent/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="${ctx }/jscomponent/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${ctx }/jscomponent/ligerUI/js/plugins/ligerMenu.js" type="text/javascript"></script>
<script src="${ctx }/jscomponent/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
<script src="${ctx }/jscomponent/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="${ctx }/jscomponent/ligerUI/js/plugins/ligerAccordion.js" type="text/javascript"></script>
<script src="${ctx }/jscomponent/ligerUI/js/plugins/ligerMenuBar.js" type="text/javascript"></script>
<script src="${ctx }/jscomponent/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="${ctx }/jscomponent/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${ctx }/jscomponent/ligerUI/js/plugins/ligerModal.js" type="text/javascript"></script>

<script src="${ctx }/jscomponent/md5.js" type="text/javascript"></script>

<!-- datePicker start-->
<script type="text/javascript" src="${ctx }/jscomponent/datePicker/WdatePicker.js"></script>
<!-- datePicker end-->

<script type="text/javascript">
    var ctx = '${ctx}';
</script>
<%
request.setAttribute("cxt",request.getContextPath());
%>