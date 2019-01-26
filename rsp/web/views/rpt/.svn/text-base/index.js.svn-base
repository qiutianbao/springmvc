$(function () {
    //初始化按钮事件
    var fromHotRptIndex = $.trim($("#fromHotRptIndex").val());
    initButtonHandler() ;

    var url =ctx+'/rpt/listRpt?fromHotRptIndex='+fromHotRptIndex;
    fromHotRptIndex = unescape(fromHotRptIndex);
    //fromHotRptIndex+="<script></script>"
    var regex=/^([A-Za-z0-9+\s])*$/;
    if(!regex.test(fromHotRptIndex)){
        window.location.href=cxt+"/login";
    }

    $("#divRptList").ligerGrid({
        url: url, //表格数据查询
        dataType : 'json',//数据类型，
        columns: [
            {
                display: '附件',
                hide:false,
                align: 'center',
                width:'4%',
                render:function(row){
                    var url="../rpt/rptFileDownload?id="+ row.requestid;
                    var imageUrl="../images/attach.gif";
                    return "<span id='reportdown'><a href='"+url+"'><img  width='9' height='15' src='"+imageUrl+"'/></a><br></span>";
                }
            },{
                display: '标题',
                name: 'rpt_title',
                dbName:'RPT_TITLE',
                align: 'center',
                hide:false ,
                width:'20%',
                render:function(row){
                    return "<a href='javascript:void(0)' class='gridlinkc' onclick='itemclick(\"rptDetail\",\""+row.requestid+"\")'  style='cursor:pointer;'>"+row.rpt_title+"</a>";
                }
            },{
                display: '日期',
                name: 'rpt_date',
                dbName:'RPT_DATE',
                align: 'center',
                hide:false ,
                width:'10%'
            },{
                display: '研究机构',
                name: '',
                dbName:'',
                align: 'center',
                hide:false ,
                width:'10%',
                render:function(row){
                    return "安信证券";
                }
            },{
                display: '报告类型',
                name: 'rpt_type',
                dbName:'RPT_TYPE',
                align: 'center',
                hide:false ,
                width:'10%'
            },{
                display: '公司',
                name: 'secu_sht',
                dbName:'SECU_SHT',
                align: 'center',
                hide:false ,
                width:'10%'
            },{
                display: '行业',
                name: 'rpt_indu',
                dbName:'RPT_INDU',
                align: 'center',
                hide:false ,
                width:'10%'
            },{
                display: '作者',
                name: 'rpt_authors',
                dbName:'RPT_AUTHORS',
                align: 'center',
                hide:false ,
                width:'10%'
            },{
                display: '浏览',
                name: 'counter_see',
                dbName:'COUNTER_SEE',
                align: 'center',
                hide:false ,
                width:'5%'
            },{
                display: '下载',
                name: 'counter_download',
                dbName:'COUNTER_DOWNLOAD',
                align: 'left',
                hide:false ,
                width:'5%'
            }],
        dataAction: 'server',
        usePager:true ,//是否分页
        isScroll: true,
        showTableToggleBtn:false,
        pageSizeOptions: [5,10, 20, 30, 40, 50, 100],//可选择设定的每页结果数
        sortName:'RPT_DATE',//默认排序
        sortOrder:'desc',
        pagesize: 20,//每页的结果数
        showTitle: false,
        resizable:true,//不可修改table大小
        width: "96%",
        height: "80%"
    });
});

var childWindow;
var interval;


function resetData(){
    document.getElementById("beginPublishDate").value='';
    document.getElementById("endPublishDate").value='';
    document.getElementById("industry").value='';
    document.getElementById("inputRelatedProduct").value='';
    document.getElementById("inputAuthor").value='';
}


//URL跳转
function jumpUrl(url){
    if(url){
        if(childWindow && childWindow.open){//子窗口存在
            childWindow.focus();
        }
        childWindow = window.open(url);
        interval= window.setInterval(childClosed(),1000);
    }else{
        alert("非法URL，请仔细检查后再点击！")
    }
}

function jumpUrlFromTemplProbleFZR(url){
    if(url){
        $.dialog({
            id:'ProcessFZR_',
            title:"分配网站负责人",
            width:"580px",
            height:"480px",
            resize:false,
            max:false,
            min:false,
            fixed:true,
            content:"url:"+url
        });


    }else{
        alert("非法URL，请仔细检查后再点击！")
    }
}


function jumpUrlFromOperateTemplProble(templId,type,templName){
    if(type=='update'){
        if(templId){
            $.dialog({
                id:'forwardUpdateProcess',
                title:"更新网站信息",
                width:"800px",
                height:"400px",
                resize:false,
                max:false,
                min:false,
                fixed:true,
                content:"url:templProblemAction.do?method=forwardUpdateProcess&templId="+templId
            });
        }else{
            alert("非法URL，请仔细检查后再点击！")
        }
    }else{
        if(confirm('您确定删除['+templName+']吗？')){
            $.post("templProblemAction.do?method=deleteProcessByTemplId",{templId:templId},function(data){
                if(data=='success'){
                    alert("操作成功");
                    reloadPaperList();
                }
            });
        }
    }
}


//子窗口事件侦听
var childClosed=function whenChildClosed(){
    return function(){
        if(childWindow && childWindow.closed){
            window.clearInterval(interval);
            childWindow=undefined;
            reloadPaperList();
        }
    }
}

//表格刷新
function reloadPaperList(){
    $("#divRptList").ligerGetGridManager().loadData() ;
}

//初始化按钮事件
function initButtonHandler(){
    //查询按钮
    $("#searchButton").unbind('click') ;
    $("#searchButton").bind('click', searchButtonHandler);
}


/**
 * 操作
 * @param type
 * @param gid
 */
function itemclick(type,requestid){
    if(type=='fileDown'){
        top.window.open(ctx+"/rpt/rptFileDownload?id="+requestid);
    }else{
        top.window.open(ctx+"/rpt/rptDetail?id="+requestid);
    }
    /*
    switch (type) {
        case "sp"://审批
            childWindow = top.window.open(ctx+"/"+url);
            interval= window.setInterval(childClosed(),1000);
            break;
        default:
            alert("请选操作!!");
            break;
    }*/
}


//关闭按钮事件处理
function closeButtonHandler(){
    window.self.close() ;
}

//刷新父页面
function refreshParentPage(){
    window.parent.reload(true) ;
}

//查询按钮事件处理
function searchButtonHandler(){
    var gridManager = $("#divRptList").ligerGetGridManager();
    if(!gridManager){
        return ;
    }
//    initSelectResult() ;//初始化数据
    gridManager.setOptions({
        parms: [
            { name: 'rpt_date_begin', value: $.trim($("#beginPublishDate").val())},
            { name: 'rpt_date_end', value: $.trim($("#endPublishDate").val())},
            { name: 'rpt_authors', value: $.trim($("#inputAuthor").val())},
            { name: 'rpt_indu', value: $.trim($("#industry").val())},
            { name: 'rpt_stk_name_code', value: $.trim($("#inputRelatedProduct").val())}
        ]
    });

    gridManager.changePage('first');
    gridManager.loadData(true);
}

//初始化下拉框数据
function initSelectResult(){
//  alert( $("select").size())//?????????????
    $("select").each(function(key, val){
        var selectvalue = "" ;
        var selectname = $(this).attr("name") ;
        var endIndex = selectname.indexOf("Select") ;
        if(endIndex && selectname)
            selectvalue = selectname.substring(0, endIndex) ;
        if(selectname && selectvalue){
            $("#" + selectvalue).val($("#" + selectname).val())
        }
    }) ;
}

//新增按钮事件处理      弹出界面
function addButtonHandler(){
    //新增按钮事件处理      弹出界面
    window.open(ctx+'/flow/processControl/forwardAddProcess');
}
