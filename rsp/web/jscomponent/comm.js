//显示左侧菜单，url为左侧菜单的地址
//在左侧菜单中初始化右侧内容地址
function showLeftMenu(url){
	$('#leftContent').css('width','20%');
	$('#rightContent').css('width','80%');
	$('#leftFrame').attr("src",url) ;
}
//显示左侧菜单和右侧内容
//lefUrl=左侧菜单地址
//rightUrl为右侧内容url
function showLeftAndRight(leftUrl,rightUrl){
	$('#leftContent').css('width','20%'); 
	$('#rightContent').css('width','80%');
	$('#leftFrame').attr("src",leftUrl) ;
	$('#rightFrame').attr("src",rightUrl) ;
}
//显示右侧内容
//rightUrl为右侧内容地址
function showRight(rightUrl){
	$('#leftContent').css('width',"0%");
	$('#rightContent').css('width','100%');
	$('#rightFrame').attr("src",rightUrl) ;
}
function showRightContent(url){
	$('#rightFrame').attr("src",url) ;
}
function changeIframeContent(pid){
	var url =  'pdmProjectBaseinfoAction.do?method=forwardIndex&pid='+pid +"&time="+new Date().getTime();
    $('#rightFrame').attr("src",url);
 }
function secBoard(n) {
	var menu = document.getElementById('leftmenu').getElementsByTagName('li');
	var main = document.getElementById('menucont').getElementsByTagName('li');
	for (i = 0; i < menu.length; i++) {
		menu[i].className = "sec1";
	}
	menu[n].className = "sec2";
	for (i = 0; i < main.length; i++) {
		main[i].style.display = "none";
	}
	main[n].style.display = "block";
}

function showPdmKbm(dictid){
	var url = "pdmKbmAction.do?method=serchPdmKbmListByCId&CId="+dictid+"&ime="+new Date().getTime();
		$('#rightFrame').attr("src",url);
}
function showMsg(msg,time,wid,hei){
	/*
	var dg = new J.dialog({ 
		id:new Date().getTime(),  		//id
		title:'',	//标题
		width:wid,		//宽度
		height:hei,		//高度
		html:'<h2 style="font-size:16px;font-color:black;margin:10px">'+msg+'</h2>',		//显示内容
		cover:true ,	//遮罩
		iconTitle:false,//显示标题图标
		resize: false ,	//改变大小
		btnBar:false,	//显示按钮栏
		autoSize:true, 	//自动改变大小
		timer:time==null?2:time	,		//定时关闭
		fixed:true,
		rang:true
	});
    dg.ShowDialog();*/
    alert(msg);
}
var dlg  ; //全局的对话框组件
function showDialogByURL(title,url,height,width){
	$.ligerDialog.open({title:title,url:url,height:height,width:width});
}

var showBtnDiv = function(obj){
	$(obj).addClass("blackBorder");
}
var hidBtnDiv = function(obj){
	$(obj).removeClass("blackBorder");
}

/**
 * 在新窗口打开页面
 * @param url       页面URL  
 * @param title     页面标题
 * @param width     页面宽度
 * @param height    页面高度
 */
function openPage(urlString,titleString,width,height){
	var currentWidth=window.screen.width-10;
	var currentHeight=window.screen.height-120;
	if(width!=null){
		currentWidth=width;
	}
	if(height!=null){
		currentHeight=height;
	}
	return window.open(urlString,titleString,"height="+currentHeight+", width="+currentWidth+", toolbar= no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=yes,top=0,left=0");
}