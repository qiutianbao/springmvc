/**
 * @desc:    大附件上传插件
 * @time:    2012-02-15    
 */
(function($) {
    $.fn.extend({
        dfUploadFile : function(options) {
            var defaults = {
                cid : 'cid',
                userId:'null userid',         //用户ID，必须设置
                _executionId:'null related id',    //关联ID,必须设置
                deleteUrl:"waveUploadAction.do?method=delete",//删除文件时路径
                uploadUrl:'waveUploadAction.do?method=upload',////文件上传时路径
                downloadUrl:'waveUploadAction.do?method=download',////文件下载时路径
                
                max_file_size:'50mb', //上传的最大附件大小
                flash_swf_url:'jscomponent/plupload/js/plupload.flash.swf',
                flash_width:480,//Flash的宽度
                flash_height:210,//Flash的高度度
                editstate:false,//编辑状态， 是否出现CheckBox
                
                //弹出层设置
                width:580,          //弹出层宽度
                height:290,         //弹出层高度
                title:'附件上传',   //弹出层标题
                maxBtn:false,       
                minBtn:false,
                iconTitle:false,
                btnBar:false,
                autoSize:false,
                cover:true,
                rang:true,
                resize:false, 
                callBackMethod : null,  //回调函数
                
                //回传值的方式， 默认为"", 传值为“value”,URL跳转为“url”, 
                //              拼接页面为“html”,返回拼接层为"div", 回调函数为"callFunc"
                //              tab下面的层 “tabdiv”   
                restyle:"",         
                //回传值
                
                //URL跳转
                
                //页面
                
                //拼接层
                divId:"divId", //返回的层ID
                callAjax:"callAjax",//Ajax请求数据，并解析成表格数据 
                parentDivId:"parentDivId",//父层ID
                
                //tab层
                tabDivId : "tabDivId",
                
                //回调函数
//                callFunc:"",
                
                //底部工具条, 确定，取消按钮
                uploaderHtml: "<div style=\"padding:5px;\">" +
                		          "<div id=\"uploadFile\" style=\"height: 190px; \"></div>" +
                                  '<div style="text-align: center; height: 20px; width: 100%;">' +
                                        '<button id="qd" class="button12" style="width: 60px; height: 23px; margin: 20px 10px 0;">' +
                                            '<img src="images/Enter.gif" />' +
                                                '确定' +
                                        '</button>' +
                                        '<button id="cancel" class="button12" style="width:60px;height:23px;">' +
                                        '<img src="images/Cancel.gif" />' +
                                            '取消' +
                                        '</button>' +
                                   '</div>' +
                                '</div>'
            };

            var options = $.extend(defaults, options);
            var uploaderDiv = new $.dialog({ 
                    id:'uploaderDiv',
                    width: options.width,
                    height:options.height,
                    title:options.title,
                    maxBtn:options.maxBtn,
                    minBtn:options.minBtn,
                    iconTitle:options.iconTitle,
                    btnBar:options.btnBar,
                    autoSize:options.autoSize,
                    cover:options.cover,
                    rang:options.rang,
                    resize:options.resize, 
                    html:options.uploaderHtml,
                    onbeforeInit:options.onbeforeInit,
                    
                    dgOnLoad:function(){
                    	_initUploaderDiv(this, options);
                        //删除按钮事件处理
                        function delFile(id){
                            $.post(deleteUrl,{id:id},function(data){
                                if(data.indexOf("1")>-1){
                                    alert('删除成功！');
                                }
                            });
                        }
                        
                        //取消按钮事件处理
                        $("#cancel").click(function(){
                            if(confirm('您确定取消上传吗？'))
                                uploaderDiv.cancel();
                        });
                        
                        //确定按钮事件处理
                        $("#qd").click(function(){
                            var size = $("#uploadFile_count").val();
                            if( size > 0){
                            	//回传值的方式， 默认为"", 传值为“value”,URL跳转为“url”, 
                                 //              拼接页面为“html”,返回拼接层为"div", 回调函数为"callFunc"      
                            	var style = options.restyle;
                            	switch(style){
                            		case 'value':{//传值
                            		          returnValue() ;
                            		          break ;
                            		      }
                            		case 'url':{//URL跳转
                            		          returnUrl() ;
                            		          break ;
                            		      }
                            		case 'html':{//拼接页面
                            		          returnHtml() ;
                            		          break ;
                            		      }
                            		case 'div':{//拼接层
                            		           returnDiv() ;
                            		          break ;
                            		      }
                            		case 'callFunc':{//回调函数
                            		          returnCallFunc(options)  ;
                            		          break ;
                            		      }
                            		case 'json':{//返回Json字符串
                            		          returnJson(options)  ;
                            		          break ;
                            		      }
                            		case 'tabdiv':{//返回tabdiv下面的层
                            		          returnTabDiv(options)  ;
                            		          break ;
                            		      }
                            		default:{
                            		          //不做任何处理
                            		          break ;
                            		      }
                            	}
                            }
                            uploaderDiv.cancel();
                        });
                        
                        //传值
                        function returnValue(){
                        	
                        }
                        
                        //URL跳转
                        function returnUrl(){
                        	
                        }
                        
                        //拼接页面
                        function returnHtml(){
                        	
                        }
                        
                        //拼接层
                        function returnDiv(){
                        	//返回层
                                var tempDisId = "#" + item.uploadFile.displayid;
                                var tempValId = "#" + item.uploadFile.valueid;
                                var uploadHtml = '';
                                var tempValues = '';
                                if($(tempDisId).html() == ''){
                                    uploadHtml += '<div id="attrDiv">';
                                }
                                for(var i = 0 ; i< size ;i++){
                                    var tempValue = "input[name='uploadFile_"+i+"_tmpname']";
                                    var tempName = "input[name='uploadFile_"+i+"_name']";
                                    uploadHtml += '<div>' +
                                                      '<a href="'+options.downloadUrl+'&id='
                                                            + $(tempValue).val()+'" target="_blank" >'
                                                                + $(tempName).val()
                                                    + '</a>' +
                                                        '<span onclick="delFile(\''+$(tempValue).val()+'\')" title="删除">' +
                                                            '&nbsp;&nbsp;&nbsp;&nbsp;' +
                                                      '</span>' +
                                                  '</div>';
                                    tempValues += $(tempValue).val() ;
                                    if( i != size -1)
                                        tempValues += ",";
                                }
                                if($(tempDisId).html() == ''){
                                    uploadHtml += '</div>';
                                }else{
                                    tempDisId = "#attrDiv" ; 
                                }
                                $(tempDisId).append(uploadHtml);
                                if($(tempValId).val()!=''){
                                    $(tempValId).val($(tempValId).val() + "," + tempValues); 
                                }
                                $(".icon08").attr("require","false");
                        }
                        
                        //回调函数
                        function returnCallFunc(options){
                        	var callFunc = options.callFunc ;
                        	if( callFunc && typeof(callFunc) == "function"){//返回函数不为空
                        		callFunc() ;
                        	}
                        }
                        
                        //返回Json数据
                        function returnJson(options){
                        	var size = $("#uploadFile_count").val();
                        	var jsonBuffer = "{\"total\":\"" + size + "\", " +
                        			"\"data\":       }" ;
                            if( size > 0){
                                for(var i = 0 ; i< size ;i++){
                                    var tempValue = "input[name='uploadFile_"+i+"_tmpname']";
                                    var tempName = "input[name='uploadFile_"+i+"_name']";
                                    if(i == 0){
                                    	jsonBuffer += "[{" ;
                                    }
                                    
                                	jsonBuffer += "\"executionId\": \"" + options._executionId + "\"," +  //关联ID
                                                    "\"id\":\"" + $(tempValue).val() + "\","  + //附件ID
                                                    "\"attachName\":\"" + $(tempName).val() + "\","//附件名
                                                    "\"dowloadUrl\":\"" + options.downloadUrl + "&id=" + $(tempValue).val()  + "\"}" ; //附件下载URL
	                                                             
                                    
                                    if( i != size -1){
                                        jsonBuffer += "]" ;
                                    }else{
                                    	jsonBuffer += "," ;
                                    }
                                }
                        }
                        
                        return jsonBuffer ;
                        }
                        
                        //返回Tab下面的层
                        function returnTabDiv(options){
                        	var executionId = options._executionId ;//关联ID
                        	var parentDivId = options.parentDivId ;//父层ID
                        	var editstate = options.editstate ;//编辑状态
                        	var callAjax = options.callAjax ;
                            if( callAjax && typeof(callAjax) == "function"){//返回函数不为空
                                callAjax(executionId, parentDivId, editstate) ;
                            }
                        }
                    }
            });
            
            $("#" + options.cid).unbind("click");
            $("#" + options.cid).bind("click", function(){
	            uploaderDiv.ShowDialog();
            });
            
            return this.each(function(){
                
            });
        }
        
    });
})(jQuery);

            
//初始化附件上传
var _initUploaderDiv = function(obj, options){
    var _executionId = $.trim(options._executionId);
    var userId = $.trim(options.userId);
    if( _executionId == undefined || _executionId == '' ){
        alert('附件上传时获取不到本记录ID');
        obj.cancel();
//      return;
    }else if(!userId){
    	alert('附件上传时获取不到操作人ID');
        obj.cancel();
    }
    var uploadUrl = options.uploadUrl + '&executionId='+_executionId+'&userId='+userId; 
    $("#uploadFile").pluploadQueue({
        runtimes : 'flash',
        url : uploadUrl,
        max_file_size : options.max_file_size ,
        unique_names : true,
        // Resize images on clientside if we can
        resize : {width : options.flash_width, height : options.flash_height, quality : 90},
         /*Specify what files to browse for
        filters : [
            {title : "Image files", extensions : "jpg,gif,png"},
            {title : "Zip files", extensions : "zip"}
        ],*/
        // Flash settings
        flash_swf_url : options.flash_swf_url,
        init : {
            FileUploaded: function(up, file, info) {
                var responseVal = info.response;
                if(responseVal == undefined){
                    alert('上传失败！');
                }else{
                    var result =  eval('(' + responseVal + ')');
                    if(result.rid == undefined){
                        alert('上传失败！');
                    }else{
                        file.rid = result.rid;
                    }
                }
            }
        }
    });
};


/**
 * @desc    tab回调函数通过Ajax请求并解析数据
 * @param   executionId         关联ID
 * @param   parentDivId         父层控件ID
 */
var callAjax = function(executionId, parentDivId, editstate){
    var tableHtml = "" ;
    $.getJSON("waveUploadAction.do?method=searchAttachByExecutetionId&date=" + new Date() ,{
        executionId:executionId
        }, function(data){
           var totalCount = data.total ;
           var totalSize = data.totalSize ;
           $("#attachCount").html(totalCount) ;
           
           var upload = data.upload  ;
           tableHtml += "<table class=\"table_ys mt10\" border=\"0\" cellpadding=\"0\"" +
                            "align=\"center\" cellspacing=\"0\" width=\"96.7%\"" +
                            "style=\"font-size: 12px; margin: 6px; border-collapse: collapse\">" +
                                "<tr valign=\"top\">" +
                                    "<th class=\"jishu  boldBig\" width=\"15%\" valign=\"middle\" align=\"center\" nowrap=\"nowrap\"" +
                                            "style=\"padding: 0 5px; border-right: 1px solid #CCCCCC; border-bottom: 1px solid #CCCCCC;min-width:100px;\">" ;
             if(editstate){
                 tableHtml +=              "<input id=\"allCheck\" type=\"checkbox\" name=\"\" value=\"\" style=\"margin-left:84px;\"/>" + 
                                           "<a id=\"attDeleteButton\" href=\"javascript:void(0)\"" +
                 		                            " class=\"icon09\" style=\"margin-left:20px;\" title=\"删除\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</a>"  
             }else{
             	 tableHtml +=             "类别（普通附件）"  
             }
            tableHtml +=           "</th>" +
                                    "<td class=\"jishu  boldBig\" width=\"45%\" valign=\"middle\"  align=\"center\"  nowrap=\"nowrap\">" +
                                        "附件（共<span id=\"attachCount\">"  + totalCount + "</span>个）" +
                                    "</td>" +
                                    "<td class=\"jishu  boldBig\" width=\"10%\" valign=\"middle\" align=\"center\" nowrap=\"nowrap\">" +
                                        "大小（共<span id=\"attachSize\">" + totalSize + "</span>KB）" +
                                    "</td>" +
                                    "<td class=\"jishu  boldBig\" width=\"10%\" valign=\"middle\" align=\"center\" nowrap=\"nowrap\">" +
                                        "创建人" +
                                    "</td>" +
                                    "<td class=\"jishu boldBig\" width=\"10%\" valign=\"middle\" align=\"center\" nowrap=\"nowrap\">" +
                                        "创建时间" +
                                    "</td>" +
                                "</tr>" ;
           for(var index in upload){       
                var attach = upload[index] ;
                tableHtml +=           "<tr>" +
                                            "<td  align=\"center\" nowrap=\"nowrap\">" ;
                if(editstate){
                        tableHtml +=            "<input type=\"checkbox\" name=\"\" value=\"" + attach.id + "\"/>"
                }else{
                          tableHtml +=           "<img src=\"images/top6.gif\">"  ;
                }
                tableHtml +=                "</td>" +
                                            "<td nowrap=\"nowrap\">" +
                                                "<a href=\"waveUploadAction.do?method=download&id=" + attach.id + "\"" +
                                                   " target=\"_blank\">" + attach.attachName + "</a>" +
                                            "</td> " +
                                            "<td nowrap=\"nowrap\" align=\"center\">" + 
                                                attach.attachSizeKB + "KB" +
                                            "</td>" +
                                            "<td nowrap=\"nowrap\" align=\"center\">" + 
                                                attach.createrName  +
                                            "</td>" +
                                            "<td nowrap=\"nowrap\" align=\"center\">" +
                                                attach.createTimeStr +
                                            "</td>" +
                                        "</tr> " ;
               }
               tableHtml +=         "</table>" ;
               if(parentDivId && $("#" + parentDivId)){
                   $("#" + parentDivId).html(tableHtml) ;
                   if(initDeleteButtonHandler && typeof(initDeleteButtonHandler) == "function"){
                   	    initDeleteButtonHandler(executionId, parentDivId, editstate) ;
                   }
                   if(initChekBoxHandler && typeof(initChekBoxHandler) == "function"){
                   	    initChekBoxHandler() ;
                   }
                   
               }else{
               	    alert("无法获取父ID，请先设置父层ID!")
               }
        }) ;
        
        
}

//删除按钮事件处理
var initDeleteButtonHandler = function(executionId, parentDivId, editstate){
	//删除按钮事件绑定
	$("#attDeleteButton").unbind("click");
    $("#attDeleteButton").bind("click", 
        {executionId:executionId, parentDivId:parentDivId, editstate:editstate}, 
        deleteAttach);
}

//删除附件
var deleteAttach = function(event){
	var ids = "" ;
	$(":checkbox").each(function(key, val){
		if($(this) && $(this).val() && $(this).attr("checked")){
			ids += $(this).val() + "," ;
		}
	}) ;
    
    if(ids){
	$.post("waveUploadAction.do?method=delete",{id:ids},function(data){
        if(data.indexOf("1")>-1){
        	if(callAjax && typeof(callAjax) == "function"){
                callAjax(event.data.executionId, event.data.parentDivId, event.data.editstate) ;
             }
            alert("删除成功！");
        }else{
        	alert("操作失败，请联系系统管理员！") ;
        }
    });
    }else{
    	alert("请至少选择一个附件，再作删除操作！");
    }
}

//选择框点击事件处理
var initChekBoxHandler = function(){
	var allCheckBoxs = $(":checkbox").not("#allCheck").size() ;
	
	//总选框事件处理
	$("#allCheck").unbind("click");
	$("#allCheck").bind("click", function(){
		var isChecked = $(this).attr("checked") ;//点击变成的状态
		$(":checkbox").not("#allCheck").each(function(key, val){
			if($(this) && $(this).attr("checked") != isChecked){
				if(isChecked){
					$(this).attr("checked", true) ;
				}else{
					$(this).removeAttr("checked") ;
				}
			}
		}) ;
	});
	
	//分选框事件处理
	$(":checkbox").not("#allCheck").each(function(key, val){
		$(this).unbind('click') ;
	    $(this).bind('click', function(){
	    	var checkedSize = $("input:checked").not("#allCheck").size() ;
	    	if(allCheckBoxs > 0){
	    		if(allCheckBoxs == checkedSize){
	    			$("#allCheck").attr("checked", true) ;
	    		}else{
	    			 $("#allCheck").removeAttr("checked") ;
	    		}
	    	}
	    }) ;
	}) ;
	
}
