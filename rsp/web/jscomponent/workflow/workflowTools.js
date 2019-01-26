/**
 * 工具条
 */
function delFile(id){
	if(confirm('您确定删除吗？')){
		$.post("waveUploadAction.do?method=delete",{id:id},function(data){
			if(data.indexOf("1")>-1){
				var hrefId = "#"+id;
				$(hrefId).parent().remove();
				alert('删除成功！');
			}
		});
	}
}

//拼接附件的html
function spellFujianHtml (uploadFilesJson){
	var tempFileHtml = '<div id="attrDiv">';
	$.each(uploadFilesJson , function(i , value){
		tempFileHtml += '<div>';
		tempFileHtml += '<a id="'+value.id+'" target="_blank" href="waveUploadAction.do?method=download&amp;id='+value.id+'">';
		tempFileHtml += value.fileName ;
		tempFileHtml += '</a>' ;
		if( value.isControl == true) {
			tempFileHtml += '<span title="删除" onclick="delFile(\''+value.id+'\')">&nbsp;&nbsp;&nbsp;&nbsp;</span>' ;
		}
		tempFileHtml += '</div>';
	});
	tempFileHtml += '</div>' ;
	return tempFileHtml;
}

(function($) {
    $.fn.workflowTools = function(options) {
    	var defaults = {
    		userId : null,// 用户ID
    		formValidatorMethod : null,// 验证方式
    		lcGz : null ,
    		tjNext : null,// 提交下一步处理
    		del : null ,// 删除
    		save : null,// 保存
    		deal : null ,
    		formId : null ,
    		initYmMethod : null,
    		uploader : null,
    		submitFlowUrl : null,
    		helpUrl : null,
    		backForwardUrl : null,
    		uploadDisId : 'fujiandiv',  //默认附件的ID
    		uploadCall : null,           //附件回调函数 
    		yjSd:null//手动添加意见
    	};
    	
    	var options = $.extend(defaults, options);
    	
    	var deptCode = '',roleCode='' ;//部门、角色 编码
		return this.each(function(){
			
			__VALID_TYPE = 1; // 验证类型
			__FORM_TYPE  = 0; // 设置表单类型
			
			__FILL_IDEA = 0 ;// 意见填写弹出层
			__FLOW_CHOOSE = 1;// 路径选择弹出层
			
			$(this).append('<div id="toolBarDiv" class="head_form_cot clearfix">'+
					 '<a class="icon01" href="javascript:void(0)" >关闭</a></div>');// 关闭按钮HTML拼接
	        $(this).attr('class' , 'head_form');
	        var thisObj = $("#toolBarDiv");
	        var htmlVar = '';
	        var formId = '';
	    	var isSecondDept = false;
	    	var isSd=null;
	    	if($("#isSdFj").length>0&&$("#isSdFj").val()!=""){
	    		isSd=$("#isSdFj").val();
	    	}

	        $.getJSON("flow/flowConfigControl/toolBarConfig",{taskId:$("#taskId").val(),isSd:isSd},function(data){
	    		$(data).each(function(i,item){
	    			//alert(item.read)
	    			if(item.read == undefined){
	    				formId = '#'+item.formId;
	    				if(item.hjInfo){
	    					htmlVar = '<a href="javascript:void(0)" class="icon02" >提交下一步处理</a>';// “提交下一步处理”按钮HTML拼接
	    					$(thisObj).append(htmlVar);
	    					flowChosse(item);
	    				}
	    				if(item.deal.isdisplay){
	    					htmlVar = '<a href="javascript:void(0)" class="icon03" require="' +
	    							item.deal.require+'">填写意见</a>';// “填写意见”按钮HTML拼接
	    					$(thisObj).append(htmlVar);
	    					fillIdea(item);
	    				}
	    				if(item.uploadFile.isdisplay){
	    					htmlVar = '<a href="javascript:void(0)" class="icon08" require="'
	    						+ item.uploadFile.require+'">附件</a>';// “附件”按钮HTML拼接
	    					$(thisObj).append(htmlVar);
	    					showUploadDialog(item, options);
	    				}
	    				if(item.save.isdisplay){
	    					// “保存”按钮HTML拼接
	    					htmlVar = '<a href="javascript:void(0)" class="icon07" >保存</a>';
	    					$(thisObj).append(htmlVar);
	    					$('.icon07').click(function(){_saveBussData(item , '1');});
	    					$("input").removeAttr("readonly");
	    					$("select").removeAttr("readonly");
	    					$("textarea").removeAttr("readonly");
	    				}
	    				// “删除”按钮HTML拼接
	    				if(item.del.isdisplay){
	    					htmlVar = '<a href="javascript:void(0)" class="icon09" >删除</a>';
	    					$(thisObj).append(htmlVar);
	    					$('.icon09').click(function(){_deleteProcess();});
	    				}
	    			}else{
	    				if( options.backForwardUrl && _queryString('dfflag') && _queryString('dfflag') != '1'){
	    					window.location.href = options.backForwardUrl+'?taskId='+$("#taskId").val()+'&dataId='+$("#dataId").val()+'&dfflag=1';
	    				}
	    				$("input").attr("disabled" , "disabled");
	    				$("textarea").attr("disabled" , "disabled");
	    				$("select").attr("disabled" , "disabled");
	    			}
	    			
	    			//审批意见显示 
	    			if(item.spyjs!=null&&item.spyjs.length>0){
	    					if($.isFunction(options.yjSd)){
	    						options.yjSd.call(this,item.spyjs) ;
	    					}else{
	    						if($('#spyjs').length>0){
									var tdStr=""; 
									$.each(item.spyjs,function(j,spyje){
										var spellHtml = dealSpan(spyje.clhjName , spyje.clr , spyje.spyj , spyje.spsj);
										tdStr = tdStr + spellHtml; 
									});
									$('#spyjs').html(tdStr); 
							}
    					 }
  					}
    				//“流程跟踪”按钮HTML拼接
    				_initGZ();
    				//流程帮助	
    				_initHelp();
    				//初始化附件
    				_initFj(item);
    				
    				$("#zhezhaoDiv").remove();
    				//_initPzDept(item);
	    		});
	    	});
	        
	        var _queryString=function(key){
	            return (document.location.search.match(new RegExp("(?:^\\?|&)"+key+"=(.*?)(?=&|$)"))||['',null])[1];
	        };
	        
	        //附件上传
	        var _initFj = function(item){
	        	if(item.files != null && item.files.length > 0){
					var fileDivId = '#' + options.uploadDisId ;
					if($.isFunction(options.uploadCall)){
						options.uploadCall.call(this,item.files) ;
					}else{
						var tempFileHtml = spellFujianHtml(item.files);
						/*$.each(item.files , function(i , value){
    						tempFileHtml += '<div>';
    						tempFileHtml += '<a id="'+value.id+'" target="_blank" href="waveUploadAction.do?method=download&amp;id='+value.id+'">';
    						tempFileHtml += value.fileName ;
    						tempFileHtml += '</a>' ;
    						if( value.isControl == true){
    							tempFileHtml += '<span title="删除" onclick="delFile(\''+value.id+'\')">&nbsp;&nbsp;&nbsp;&nbsp;</span>' ;
    						}
    						tempFileHtml += '</div>';
    					});*/
    					$(fileDivId).append(tempFileHtml);
					}
					
				}
	        }
	    	
	    	//流程跟踪
	    	var _initGZ = function(){
	    		htmlVar = '<a href="javascript:void(0)" class="icon06" >流程跟踪</a>';
				$(thisObj).append(htmlVar);
				showGz();
	    	}
	    	//流程帮助
	    	var _initHelp = function(){
	    		htmlVar = '<a href="javascript:void(0)" targer="blank" class="icon05">帮助</a>' ;
				$(thisObj).append(htmlVar);
				$(".icon05").click(function(){
					var forwardUrl = 'flowConfigAction.do?method=help&imageSrc='+encodeURIComponent(encodeURIComponent(options.helpUrl));
					window.open(forwardUrl ,'help');
				});
	    	}

	    	// 删除流程
	    	var _deleteProcess = function(){
	    		var taskId = $("#taskId").val();
	    		$.post('flowConfigAction.do?method=deleteProcess' , {"taskId" : taskId} , function(data){
	    			window.close();
	    		});
	    	}
	    	
	    	var dealSpan = function(clhjName , clr , spyj , spsj){
	    		var tempStr = "<span>[处理环节："+clhjName+"]<br>处理人："+clr+"<br>处理意见："+spyj+"<br>处理时间："+spsj+"</span><hr>"
	    		return tempStr;
	    	};
	    	
	    	var showGz = function(){
	    		$('.icon06').click(function(){
	    			var _GzUrl="flow/flowConfigControl/flowGz"+"?taskId="+ $("#taskId").val();
					var fGzObj = new $.dialog({
					 	id:'fGzObj',
					 	width: 1024,
					 	height:400,
		                title:'流程跟踪',
		                lock: true,
		                resize: false ,
		                max: false ,
		                min:false,
		                fixed: true,
		                content:"url:"+_GzUrl
				});
				});
	    	};
	    	
	    	// 附件上传按钮事件处理
	    	var showUploadDialog = function(item, options){
	    		$('.icon08').click(function(){
	    		var uploaderHtml = '<div style="padding:5px;"><div id="uploadFile" style="height: 200px; "></div>';
	    			uploaderHtml += '<div style="text-align: center; height: 40px; width: 100%;">';
	    			uploaderHtml += '<button id="qd" class="button12" style="width: 60px; height: 23px; margin: 20px 10px 0;"><img src="images/Enter.gif" />确定</button>';
	    			uploaderHtml += '<button id="cancel" class="button12" style="width:60px;height:23px;"><img src="images/Cancel.gif" />取消</button>';
	    			uploaderHtml += '</div></div>';
	    		    var uploaderDiv = new $.dialog({ 
						 	id:'uploaderDiv',
						 	width: 580,
						 	height:310,
						    title:'附件上传',
						    maxBtn:false,
			            	minBtn:false,
			            	iconTitle:false,
			            	btnBar:false,
			            	autoSize:false,
			            	cover:true,
			            	rang:true,
			            	resize:false, 
			            	html:uploaderHtml,
			            	onbeforeInit:true,
			            	dgOnLoad:function(){
			            		_initUploaderDiv(this);
			            		
			            		function delFile(id){
			            			$.post("waveUploadAction.do?method=delete",{id:id},function(data){
			            				if(data.indexOf("1")>-1){
			            					alert('删除成功！');
			            				}
			            			});
			            		}
			            		
			            		$("#cancel").click(function(){
			            			if(confirm('您确定取消上传吗？'))
			            				uploaderDiv.cancel();
			            		});
			            		
			            		// 确定按钮事件处理
			            		$("#qd").click(function(){
			            			var size = $("#uploadFile_count").val();
			            			if( size > 0){
			            				var tempDisId = "#" + options.uploadDisId;
			            				//var tempValId = "#" + item.uploadFile.valueid;
			            				var uploadHtml = '';
			            				//var tempValues = '';
			            				var tempValuesT = $(tempDisId).find('a') ;
			            				if( $(tempValuesT).size() == 0 ){
			            					uploadHtml += '<div id="attrDiv">';
			            				}
			            				for(var i = 0 ; i< size ;i++){
			            					var tempValue = "input[name='uploadFile_"+i+"_tmpname']";
			            					var tempName = "input[name='uploadFile_"+i+"_name']";
			            					uploadHtml += '<div><a id="'+$(tempValue).val()+'" href="waveUploadAction.do?method=download&id='+$(tempValue).val()+'" target="_blank" >'+$(tempName).val()+'</a><span onclick="delFile(\''+$(tempValue).val()+'\')" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;</span></div>';
			            				}
			            				if($(tempValuesT).size() == 0){
			            					uploadHtml += '</div>';
			            				}else{
			            					tempDisId = "#attrDiv" ; 
			            				}
			            				$(tempDisId).append(uploadHtml);
			            				
			            				//if($(tempValId).val()!=''){
			            					//$(tempValId).val($(tempValId).val() + "," + tempValues); 
			            				//}
			            				$(".icon08").attr("require","false");
			            			} 
			            			uploaderDiv.cancel();
			            		});
			            	}
		    		});
		    		uploaderDiv.ShowDialog();
	    		});
	    	};
	    	
	    	// 初始化附件上传
	    	var _initUploaderDiv = function(obj){
	    		var _executionId = $.trim($("#executionId").val());
	    		if( _executionId == undefined || _executionId == '' ){
	    			alert('附件上传时获取不到流程实例ID');
	    			obj.cancel();
	    		}
	    		var _url = 'waveUploadAction.do?method=upload&executionId='+_executionId+'&userId='+options.userid; 
	    		$("#uploadFile").pluploadQueue({
	    			runtimes : 'flash',
	    			url : _url,
	    			max_file_size : '50mb',
	    		    unique_names : true,
	    		    // Resize images on clientside if we can
	    			resize : {width : 480, height : 210, quality : 90},
	    			// Flash settings
	    			flash_swf_url : 'jscomponent/plupload/js/plupload.flash.swf',
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
	    	
	    	// 填写意见
	    	var fillIdea = function(item){
	    		var ideaHtml= '<div class="pathSelectDiv"><span>请填写意见:</span>';
    			ideaHtml += '<span class="selectSpan"><select id="idea" name="idea" class="ideaSelect">';
    			ideaHtml += '<option selected="selected" value="">--请选择--</option>';
    			if(item.deal.ideaconfig[0]){
    				var tempSelectTexts = item.deal.ideaconfig[0].selectText ;
    				if(tempSelectTexts){
    					$.each(tempSelectTexts , function(i,value){
    						ideaHtml += '<option value="'+value.content+'">'+value.content+'</option>';
    					});
    				}
    			}
    			ideaHtml += '</select></span>';
    			ideaHtml += '</div><div><textarea id="content"  class="textareaCss"></textarea></div>';
    			ideaHtml += '<div class="buttonDiv"><button id="qd" class="button12" style="margin: 10px 10px 0px 0px;width:60px;height:23px;"><img src="images/Enter.gif" />确定</button>';
    			ideaHtml += '<button id="cancel" class="button12" style="width:60px;height:23px;"><img src="images/Cancel.gif" />取消</button>';
    			ideaHtml += '</div>';
    			$(".icon03").click(function(){
    				_dialogPathChoose(item ,ideaHtml ,'.icon03', '填写意见' , __FILL_IDEA);
    			});
	    	};
	    	
	    	// 提交下一步
	    	var flowChosse = function(item){
 	    		$(".icon02").click(function(){
	    			var isGo = false;
	    			if($.isFunction(options.formValidatorMethod)){
	    				// 数据提交前执行的操作 2011-04-09 jameson
	    	    		if(options.initYmMethod && $.isFunction(options.initYmMethod)){
	    	    			options.initYmMethod.call( thisObj ) ;
	        			}
	    				if(options.formValidatorMethod.call( thisObj )){
	    					if(item.deal.require){
	    						if($(".icon03").attr("require") == 'true'&&($("#wave_dealcontent").length==0||$.trim($("#wave_dealcontent").val())=="")){
	    							alert('请填写意见!');
	    						}else{
	    							isGo = true;
	    						}
	    					}else{
	    						isGo = true;
	    					}
	    				}
	    			}else{
	    				if(item.deal.require){
    						if($(".icon03").attr("require") == 'true'&&($("#wave_dealcontent").length==0||$.trim($("#wave_dealcontent").val())=="")){
    							alert('请填写意见!');
    						}else{
    							isGo = true;
    						}
    					}else{
    						isGo = true;
    					}
	    			}
	    			
	    			if(isGo){
	    				_saveBussData(item , '0'); //
	    			}
	    		});
	    	};
	    	
	    	/**
			 * flag为0时，为提交下一步处理时保存表单数据，为1时，为直接点击按钮保存数据
			 */
	    	var _saveBussData = function(item , flag){
	    		// 数据提交前执行的操作 2011-03-02 jameson
	    		/*if(options.initYmMethod && $.isFunction(options.initYmMethod)){
	    			options.initYmMethod.call( thisObj ) ;
    			}*/
	    		if($(formId).attr("action") != ''){
	    			
	    			if($('#DF_IS_SAVED').val() != '1'){
	    				var isData=$('#dataId').val();
	    				$.post($(formId).attr("action"), $(formId).serialize() , function(data){
		    				var result = eval(data)[0];
			    			if(result.state == '1'){
			    				var ywMc = result.ywMc;
		    					var tjr = result.tjr;
		    					var tjSj = result.tjSj;
		    					var title = result.title == '' ? $("#title").val():result.title;
		    					var dataId = result.dataId ;
		    					if(title != '' && title != null)
		    						$("#title").val(title);
		    					// var url = result.url == '' ? $("#url").val():result.url;
								if(item && item.hjInfo && item.hjInfo.id && item.hjInfo.id == 'qc'){
									
				    				if(ywMc == '' || tjr == '' || tjSj == ''){
				    					alert('提交出错，请联系管理员!');
				    				}else{
				    					
				    					$(formId).append('<input type="hidden" id="tjr" name="tjr" value="'+tjr+'" />');
				    					$(formId).append('<input type="hidden" id="tjSj" name="tjSj" value="'+tjSj+'" />');
				    					$("#dataId").val(dataId);
				    					var url = result.url + '?dataId=' + dataId;
				    					var taskId = $("#taskId").val();
				    					if(isData==""||isData==null){
				    						_saveCg(item , taskId , ywMc , tjr , tjSj ,title, url ,flag , dataId); // 保存
				    					}else{
				    						_isDipslayDialgChoose(item);
				    					}
				    				}
		    					}else{
									_isDipslayDialgChoose(item);
								}
			    			}else{
			    				alert('提交出错，请联系管理员!');
			    			}
			    		});
	    			}else{
	    				_isDipslayDialgChoose(item);
	    			}
	    		}else{
	    			_isDipslayDialgChoose(item);
	    		}
	    		return true;
	    	};
	    	
	    	// 保存草稿
	    	var _saveCg = function(item , taskId , ywMc , tjr , tjSj ,title, url ,flag ,dataId){
	    		$.post('flow/flowConfigControl/saveCg',{taskId:taskId , ywMc:ywMc , tjr:tjr , tjSj:tjSj , title:title , url:url , dataId:dataId},function(data){
					var tempData = eval(data)[0];
					 if(tempData.clId != null || tempData.clId != '' ){
						 $("#clId").size() == 0 ? $(formId).append('<input type="hidden" id="clId" name="clId" value="'+tempData.clId+'" />'):$("#clId").val(tempData.clId);
						 if(flag == '0')   // 提交下一步
							 _isDipslayDialgChoose(item);
						 if(flag == '1'){   // 保存草稿
							 alert('保存成功！');
							 window.close();
						 }
						$(formId).append('<input type="hidden" value="1" name="DF_IS_SAVED" id="DF_IS_SAVED" />');
					 }else{
						 alert('提交出错，请联系管理员！');
					 }
				});
	    	};
	    	
	    	// 提交下一步时候,判断是否有多个流程节点
	    	var _isDipslayDialgChoose = function(item){
	    		_flowNextChoose(item);
	    		/*$.getJSON('flowConfigAction.do?method=searchDeptCode' , function(data){
	    			if(data == null){
	    				alert('未获取到此人员的部门信息!');
	    				return ;
	    			}
	    			var deptLength = data.length ;
	    			isWriteDfDeptCodeIntoForm();
	    			if(deptLength <= 1){
	    				$.each(data , function(i,obj){
	    					$("#df_dept_code").val(obj.id);
	    				});
	    				_flowNextChoose(item);
	    			}else{
	    				var ljPathsHtml = '<div class="pathSelectDiv"><span>请选择您所处部门：</span>';
						ljPathsHtml += '<select style="width:120px" id="deptList" name="deptList" >';
						var tempPathInfo = ''; 
						$.each(data,function(i,value){
							tempPathInfo += '<option value="'+value.id+'">'+value.deptName+'</option>';
						});
						ljPathsHtml += tempPathInfo;
						ljPathsHtml += '</select>';
						ljPathsHtml += '<div class="buttonDiv"><button id="qd" class="button12" style="margin: 10px 10px 0px 0px; width:60px;height:23px"><img src="images/Enter.gif" />确定</button>';
						ljPathsHtml += '<button id="cancel" class="button12" style="width:60px;height:23px"><img src="images/Cancel.gif" />取消</button>';
						ljPathsHtml += '</div></div>';
						_dialogDeptChoose('选择部门',ljPathsHtml , item);
	    			}
	    		});*/
	    	};
	    	
	    	var isWriteDfDeptCodeIntoForm = function(){
	    		if($("#df_dept_code").size() == 0){
	    			$(formId).append('<input type="hidden" id="df_dept_code" name="df_dept_code" />');
	    		}
	    	};
	    	
	    	// 路径选择
	    	var _flowNextChoose = function(item){
	    		var isSecResDeal = item.hjInfo.isSecResDeal;//是否有二级
	    		if(isSecResDeal){
	    			$.post("flowConfigAction.do?method=isSecResDeal" , 
	    					{
	    						"dfDeptCode":$("#df_dept_code").val()
	    					} , function(data){
	    				var tempResultObj = eval(data);
	    				isSecondDept = eval(tempResultObj.result) ;
	    				_choose(item);
			    	});
	    		}else{
	    			_choose(item);
	    		}
	    	};
	    	
	    	
	    	// 是否需要判断二级弹出路径
	    	var _choose = function (item){
	    		if(item.hjInfo.ljCount>1){
	    			var ljPathsHtml = '<div class="pathSelectDiv"><span>请选择下一处理环节：</span>';
					ljPathsHtml += '<select class="mutiSelect" id="flowPath" name="flowPath" multiple="multiple">';
					var tempPathInfo = ''; 
					$.each(item.hjInfo.ljInfo,function(i,value){
						tempPathInfo += _spellChooseOption(value);
					});
					ljPathsHtml += tempPathInfo;
					ljPathsHtml += '</select>';
					ljPathsHtml += '<div class="buttonDiv"><button id="qd" class="button12" style="margin: 10px 10px 0px 0px;width:60px;height:23px;"><img src="images/Enter.gif" />确定</button>';
					ljPathsHtml += '<button id="cancel" class="button12" style="width:60px;height:23px;"><img src="images/Cancel.gif" />取消</button>';
					ljPathsHtml += '</div></div>';
					_dialogPathChoose(item , ljPathsHtml , '.icon02' ,'选择下一处理环节' , __FLOW_CHOOSE);
				}else{
					var value = item.hjInfo.ljInfo[0]; 
					value = _isSecDeptAndGivenObj(value);
					var ljpd = item.hjInfo.ljpd;
					try{
						_setNextStep( item.hjInfo , value.value , ljpd);
					}catch(e){
						alert('路径配置有问题!'+ e);
					}
				}
	    	}
	    	
	    	// 判断是不是二级部门,如果是则给予赋值
	    	var _isSecDeptAndGivenObj = function(value){
	    		if(isSecondDept){
    				if(value['parent'] != undefined){
    					value = value.parent;
    				}
	    		}
	    		return value;
	    	}
	    	
	    	// 通过json属性来判断是否要在option中添加attrName属性
	    	var _spellChooseOption = function(value){
	    		var tempOptionHtml = '';
	    		if( value != null && value['ljpd'] != undefined){
	    			var ljpdtj = value.ljpd;
	    			value = _isSecDeptAndGivenObj(value);
	    			tempOptionHtml = '<option value="'+value.value+'" attrName="'+ljpdtj+'">'+value.name+'</option>';
	    		}else{
	    			value = _isSecDeptAndGivenObj(value);
	    			tempOptionHtml = '<option value="'+value.value+'">'+value.name+'</option>';
	    		}
	    		return tempOptionHtml;  
	    	}
	    	
	    	var _dialogDeptChoose = function(title, parameterHtml , item){
	    		var flowDiv = new $.dialog({ 
				 	id:'deptDiv',
				 	width: 310,
				 	height:220,
				    title:title,
				    maxBtn:false,
	            	minBtn:false,
	            	iconTitle:false,
	            	btnBar:false,
	            	autoSize:false,
	            	cover:true,
	            	rang:true,
	            	resize:false, 
	            	html:parameterHtml,
	            	onbeforeInit:true,
	            	dgOnLoad:function(){
	            		var _dialogOK = function(){
	            			flowDiv.close();
	            			_flowNextChoose(item);
	            		};
	            		
	                    $("#qd").click(function(){
	                    	var value = $("#deptList").val();
	                    	
	                    	$("#df_dept_code").val(value);
	                    	_dialogOK();
	                    });
	                    $("#cancel").click(function(){
	                    	flowDiv.close();
	                    });
	                }
				});
	    		flowDiv.ShowDialog();
	    	};
	    	
	    	/**
			 * 弹出选择路径对话框
			 */
	    	var _dialogPathChoose = function(item , parameterHtml , selectId ,title ,type ){
	    		var flowDiv = new $.dialog({
				 	id:'flowDiv',
				 	width: 310,
				 	height:220,
	                title:title,
	                lock: true,
	                resize: false ,
	                max: false ,
	                min:false,
	                fixed: true,
	                content:parameterHtml,
	                init:function(){
	            		var _dialogOK = function(){
	            			if(type == __FILL_IDEA){//审批意见
	            				$.getJSON('flow/flowConfigControl/dealIdea' , {"taskId":$("#taskId").val()} , function(data){
	            					var msg = '';
	            					var contentMsg = $.trim($("#content").val());
	            					// $.each(data , function(i,dataObj){
	            						msg += dealSpan(data.hjName , data.userName , contentMsg , data.dataTime);
	            					// });
	            					if($.trim($("#content").val())!= ''){
		                    			$(".icon03").attr("require" , "false");
		                    			if($('#wave_dealcontent').length>0){
		                    				$('#wave_dealcontent').val(contentMsg);
		                    			}else{
		                    				$(formId).append('<input type="hidden" id="wave_dealcontent" name="wave_dealcontent" value="'+contentMsg+'" /> ');
		                    			}
		                    			if($("#currentSpyj").length>0){
		                    				$("#currentSpyj").html(msg);
		                    			}else{
		                    				$("#spyjs").append("<span id='currentSpyj'>"+msg+"</span>");
		                    			}
		                    		}
		                    		flowDiv.close();
	            				}) ;
	                    		
	                    	}else if(type == __FLOW_CHOOSE){//流程走向选择
	                    		if(item['isMulti'] != undefined ){
	                    		}else{
	                    			var tempSelectedOptions = $("#flowPath option:selected");
			                    	if(tempSelectedOptions.size() == 1){
			                    		flowDiv.close();
			                    		var ljpdtj =  tempSelectedOptions.attr("attrName");
//			                    		alert(tempSelectedOptions.attr("attrName")==undefined);
//			                    		alert(tempSelectedOptions.attr("attrName")+"--------"+"undefined");
//			                    		var isSetCondition=$(tempSelectedOptions.attr("attrName"))==null||$(tempSelectedOptions.attr("attrName"))==""||tempSelectedOptions.attr("attrName")=="undefined";
//			                    		 alert(ljpdtj+"000000000--"+tempSelectedOptions.attr("attrName")==undefined);
			                    		 ljpdtj = tempSelectedOptions.attr("attrName")==undefined ? 'isCondition' : ljpdtj ;
			                    		 if(ljpdtj.indexOf(",")>0){
			                    			 $(formId).append('<input type="hidden" name="waveCondition" value="'+ljpdtj + '|' +tempSelectedOptions.val()+'" />');
			                    			 //_submitForm();
			                    		 }else{
			                    			 _setNextStep(item.hjInfo , tempSelectedOptions.val() , ljpdtj );
			                    		 }
			                    		 
			                    	}else{
			                    		alert('请选择一条路径做为下一环节！');
			                    		return ;
			                    	}
	                    		}
	                    	}
	            		};
	            		
	            		if( type == __FILL_IDEA){
	            			$("#idea").change(function(){
                    			$("#content").append($("#idea").val());
                    		});
	            		}
	            		
	                    $("#qd").click(function(){
	                    	_dialogOK();
	                    });
	                    $("#flowPath").dblclick(function(){
	                    	_dialogOK();
	                    });
	                    $("#cancel").click(function(){
	                    	flowDiv.close();
	                    });
	                }
				});
	    	};
	    	
	    	
	    	// 通过type==0 表示设置表单是否可读 type=1表示验证表单
	    	var _formElementsSetAndValidor = function(item , type){
	    		var tempObj = item.form[0].attrs;
	    		if(type == __VALID_TYPE){
	    			if(!_validatorTools(item)){
	    				return false;
	    			}
					return _validatorForm(tempObj);
				}else if(type == __FORM_TYPE){
					_formElementsControl(tempObj);
					return true;
				}
	    	};
	    	
	    	// 将表单中元素设置为disabled
	    	var _formElementsControl = function(tempObj){
	    		$.each(tempObj , function(i,value){
	    			var tempId = _gotExpressIdByFormType(value);
		    		if(value.isRead && (!value.isHidden)){
		    			if(value.type == 'href'){
			    			$(tempId).attr("onclick" ,"return false;");
			    		}else{
			    			$(tempId).attr("disabled" ,"true");
			    		}
		    		}
	    		});
	    		
	    		return true;
	    	};

	    	// 工具条验证
	    	var _validatorTools = function(item){
	    		var msg = '' ;
	    		if(item.deal.isdisplay){
	    			if($(".icon03").attr("require") == "true"){
	    				msg += '请填写意见！\n';
	    			}
	    		}
	    		if(item.uploadFile.isdisplay){
	    			if($(".icon08").attr("require") == "true"){
	    				msg += '请上传附件！\n';
	    			}
	    		}
	    		if(msg != ''){
	    			return false;
	    		}
	    		return true;
	    	};
	    	
	    	var _callBackFormValidator = function (options){
	    		this.call();
	    	};
	    	
	    	
	    	// 设置路径的判读条件值
	    	var _setNextStep = function(hjInfo ,inputValue , inputName){
	    		$(formId).append('<input type="hidden" name="waveCondition" value="'+inputName + '|' +inputValue+'" />');
	    		_pdLjType(hjInfo , inputValue);
	    	};
	    	
	    	// 判断下个环节是否需要选择人员
	    	var _pdLjType = function(hjInfo , inputValue){
	    		$.each( hjInfo.ljInfo , function(i,value){
	    			value = _isSecDeptAndGivenObj(value);
	    			if(value.value == inputValue){
	    				$(formId).append('<input type="hidden" name="hjType" value="'+value.type+'" />');
	    				switch(value.type){
			    			case '0' : // 不出对话框
			    				_submitForm();
			    				break;
			    			case '1' : // 添加对话框
			    				var isNeedSearchAllPeople = value.isSearchAll ? value.isSearchAll : '0' ;
			    				var pzDeptCode = value.deptvalue ? value.deptvalue : '' ;
			    				_openDiv('#df_dept_code', 0 , inputValue , isNeedSearchAllPeople , pzDeptCode);
			    				break;
			    			case '5' : // 指定部门多选人员
			    				var isNeedSearchAllPeople = value.isSearchAll ? value.isSearchAll : '0' ;
			    				var pzDeptCode = value.deptvalue ? value.deptvalue : '' ;
			    				_openDivs('#df_dept_code', 1 , inputValue , isNeedSearchAllPeople , pzDeptCode);
			    				break;
			    			case '2':
			    				var isZnOrFzn = value.isZnOrFzn ? value.isZnOrFzn : '-1' ;//职能非职能
			    				_openDeptDiv(1,isZnOrFzn);
			    				break;
			    			case '4' : // 添加人员对话框
			    				var isNeedSearchAllPeople = value.isSearchAll ? value.isSearchAll : '0' ;
			    				var pzDeptCode = value.deptvalue ? value.deptvalue : '' ;
			    				_openDiv('#df_dept_code', 1 , inputValue , isNeedSearchAllPeople , pzDeptCode);
			    				break;
			    			case '6' : // 添加部门人员选择对话框
			    				var isZnOrFzn = value.isZnOrFzn ? value.isZnOrFzn : '-1' ;//职能非职能
			    				var roleValue = value.roleValue!=null&&value.roleValue!='' ? value.roleValue : '-1' ;//角色ID 
			    				_openGetPersonByDeptDiv(0,isZnOrFzn,roleValue);
			    				break;
		    			};
	    			}
	    		});
	    	};
	    	
	    	// 提交form
	    	var _submitForm = function(){
	    		var submitURL = 'flow/flowConfigControl/flowCompleteAndNextTask';
	    		if(options.submitFlowUrl != null ){
	    			submitURL = options.submitFlowUrl;
	    		}
	    		$(formId).attr('action' , submitURL);
	    		$(formId).attr('method' , 'post');
	    		$.post(submitURL , $(formId).serialize() , function(data){
	    			var resultData = eval(data)[0];
	    			alert(resultData.hjName);
	    			var parameterHtml = '<div style="width:99%;height:20px;"></div>';
	    			if(resultData.result == '0'){
	    				parameterHtml += '<div style="height:80px;text-align: center;">流程处理失败，请联系管理员</div>';
	    			}else{
	    				parameterHtml += '<div style="height:80px;text-align: center"><span style="height: 14px;">文件将发送给以下环节和处理人：</span><br/><div style="line-height: 35px; height: 75px; overflow: auto; margin-top: 5px;">'+  resultData.hjName + '&nbsp;&nbsp;' + resultData.assigner + '</div></div>';
	    			}
	    			parameterHtml += '<div class="buttonDiv" style="padding-left:20px;"><button id="cancel" class="button12" style="margin: 10px 10px 0px 0px;width:60px;height:23px;"><img src="images/Cancel.gif" />关闭</button>';
	    			parameterHtml += '<button id="fanhui" class="button12" style="width:60px;height:23px;"><img src="images/Enter.gif" />返回</button>';
	    			parameterHtml += '</div></div>';
	    			_dialogChooseInfo(parameterHtml , '流程信息');
	    		});
	    	};
	    	
	    	/**
			 * 弹出选择路径对话框
			 */
	    	var _dialogChooseInfo = function( parameterHtml ,title ){
	    		var flowDiv = new $.dialog({ 
				 	id:'flowDiv',
				 	width: 310,
				 	height:220,
	                title:title,
	                lock: true,
	                resize: false ,
	                max: false ,
	                min:false,
	                fixed: true,
	                content:parameterHtml,
	                init:function(){
	                    $("#fanhui").click(function(){
	                    	var taskId = $("#taskId").val();
	                    	var dataId = $("#dataId").val();
	                    	var backForwardUrl = options.backForwardUrl;
	                    	if(backForwardUrl == null){
	                    		alert('backForwardUrl 配置出错！');
	                    		return ;
	                    	}else{
	                    		$(formId).append('<input type="hidden" name="backForwardUrl" value="'+backForwardUrl+'" />');
	                    	}
	                    	$(formId).attr("action" , 'flow/flowConfigControl/forwardBack');
	                    	$(formId).submit();
	                    });
	                    $("#cancel").click(function(){
	                    	flowDiv.close();
	                    	window.parent.opener.reloadPaperList();
	                    	window.close();
	                    	
	                    });
	                }
				});
	    	};
	    	
	    	//人员选择
	    	var _openDiv = function(tempDeptCodeSign ,type , inputValue , isSearchAll , pzDeptCode){
	    		var tempName = type==0?'单选':'多选' ;
	    		var personHtml= '<div class="pathSelectDiv"><span>选择下一环节处理人员（'+tempName+'）:</span>';
	    		personHtml += '</div><div><select id="searchPersonSelect"  class="mutlSelect" multiple="multiple" ></select>';
	    		personHtml += '<select id="searchPersonSelectd"  class="mutlSelect" multiple="multiple" ></select>';
	    		personHtml +='</div>';
	    		personHtml += '<div class="buttonDiv" style="padding-left:110px;"><button id="qd" class="button12" style="margin: 10px 10px 10px 0px;width:60px;height:23px;"><img src="images/Enter.gif" />确定</button>';
	    		personHtml += '<button id="cancel" class="button12" style="width:60px;height:23px;margin: 10px 10px 10px 0px;"><img src="images/Cancel.gif" />取消</button>';
	    		personHtml += '</div>';

	    		var personDiv = new $.dialog({ 
				 	id:'personSearch',
				 	width: 385,
				 	height:336,
				    title:'选择下一环节处理人员',
				    maxBtn:false,
	            	minBtn:false,
	            	iconTitle:false,
	            	btnBar:false,
	            	autoSize:false,
	            	cover:true,
	            	rang:true,
	            	resize:false,
	            	html:personHtml,
	            	onbeforeInit:true,
	            	dgOnLoad:function(){
	            		var deptCode = pzDeptCode != '' ? pzDeptCode : $(tempDeptCodeSign).val();
	            		//var url = isSearchAll?('dfWfRyAction.do?method=searchPersonByDeptCode&deptCode='+deptCode):('dfWfRyAction.do?method=searchPersonByDeptCode&deptCode='+deptCode+'&isSearchAll='+isSearchAll);
	            		$.getJSON('dfWfRyAction.do?method=searchPersonByDeptCode&deptCode='+deptCode+'&isSearchAll='+isSearchAll , function(data){
	            			var optionsHtml = '';
	            			$.each(data,function(i,value){
	            				if(value.EGh != options.userid)
	            					optionsHtml += '<option value="'+value.EGh+'">'+value.EMc+'</option>';
	            			});
	            			$("#searchPersonSelect").append(optionsHtml);
	            		});
	            		
	            		$("#searchPersonSelect").change(function(){
	            			var selectedOptions = $("#searchPersonSelect option:selected");
	            			if($(selectedOptions).size()>0){
		            			if(type == 0 ){ // 单选
		            				$("#searchPersonSelectd option").remove();
		            				var tempAppendOptions = '<option value="'+$(selectedOptions).last().val()+'">'+$(selectedOptions).last().text()+'</option>';
		            				if($("#searchPersonSelectd option[value='"+$(selectedOptions).last().val()+"']").size() == 0){
		            					$("#searchPersonSelectd").append(tempAppendOptions);
		            					$(selectedOptions).removeAttr("selected");
		            				}
		            			}else{
		            				$("#searchPersonSelectd").append($(selectedOptions));
		            			}
	            			}
	            		});
	            		
	            		$("#searchPersonSelectd").change(function(){
	            			$("#searchPersonSelectd option:selected").remove();
	            		});

	                    $("#qd").click(function(){
	                    	var selectedOption = $("#searchPersonSelectd option");
	                    	var size = $(selectedOption).size();
	                    	if(size == 0){
	                    		alert('请选择下一环节处理人员！');
	                    		return ;
	                    	}
	                    	var assingers = '';
	                    	$.each(selectedOption,function(i,v){
	                    		assingers += $(v).val();
	                    		if( i != size-1 )
	                    			assingers += ',';
	                    	});
	                    	
	                    	if($("#waveAssigner").size() == 0){
	                    		var tempAssignerHtml = '<input type="hidden" name="waveAssigner" value="'+assingers+'"  />';
		                    	$(formId).append(tempAssignerHtml);
	                    	}else{
	                    		$("#waveAssigner").val(assingers);
	                    	}
	                    	personDiv.cancel();
	                    	_submitForm();
	                    });
	                    $("#cancel").click(function(){
	                    	personDiv.cancel();
	                    });
	                }
				});
	    		personDiv.ShowDialog();
	    	};
	    	
	    	//针对指定部门人员选择
	    	var _openDivs = function(tempDeptCodeSign ,type , inputValue , isSearchAll , pzDeptCode){
	    		var tempName = type==0?'单选':'多选' ;
	    		var personHtml  = '<div class="pathSelectDiv"><span>选择下一环节处理人员（'+tempName+'）:</span>';
					personHtml += '</div><div><select id="deptSelect" class="mutlSelect" multiple="multiple" >'+
						 		  '</select><select id="searchPersonSelect"  class="mutlSelect" multiple="multiple" ></select>';
					personHtml += '<select id="searchPersonSelectd"  class="mutlSelect" multiple="multiple" ></select>';
					personHtml += '</div>';
					personHtml += '<div class="buttonDiv" style="padding-left:210px;"><button id="qd" class="button12" style="margin: 10px 10px 10px 0px;width:60px;height:23px;"><img src="images/Enter.gif" />确定</button>';
					personHtml += '<button id="cancel" class="button12" style="width:60px;height:23px;margin: 10px 10px 10px 0px;"><img src="images/Cancel.gif" />取消</button>';
					personHtml += '</div>';
	    		var personDiv = new $.dialog({ 
	    			id:'personSearch',
	    			width: 630,
	    			height:336,
	    			title:'选择下一环节处理人员',
	    			maxBtn:false,
	    			minBtn:false,
	    			iconTitle:false,
	    			btnBar:false,
	    			autoSize:false,
	    			cover:true,
	    			rang:true,
	    			resize:false,
	    			html:personHtml,
	    			onbeforeInit:true,
	    			dgOnLoad:function(){
	    				var deptCode = pzDeptCode != '' ? pzDeptCode : $(tempDeptCodeSign).val();
	    				//var url = isSearchAll?('dfWfRyAction.do?method=searchPersonByDeptCode&deptCode='+deptCode):('dfWfRyAction.do?method=searchPersonByDeptCode&deptCode='+deptCode+'&isSearchAll='+isSearchAll);
	    				$.getJSON('dfWfDeptAction.do?method=searchDfWfDeptByIds&ids=' + $("#qbbmids").val() , function(data){
	    					var optionsHtml = '';
	    					$.each(data,function(i,value){
	    						if(value.id != options.userid)
	    							optionsHtml += '<option value="'+value.id+'">'+value.bmMc+'</option>';
	    					});
	    					$("#deptSelect").append(optionsHtml);
	    				});

	    				$("#deptSelect").change(function(){
	    					$("#searchPersonSelect option").remove();
		    				$.getJSON('dfWfRyAction.do?method=searchPersonByDeptCode&deptCode='+$("#deptSelect :selected").val()+'&isSearchAll=1', function(data){
		    					var optionsHtml = '';
		    					$.each(data,function(i,value){
		    						if(value.EGh != options.userid)
		    							optionsHtml += '<option value="'+value.EGh+'">'+value.EMc+'</option>';
		    					});
		    					$("#searchPersonSelect").append(optionsHtml);
		    				});
	    				});
	    					
	    					$("#searchPersonSelect").change(function(){
	    					var selectedOptions = $("#searchPersonSelect option:selected");
	    					if($(selectedOptions).size()>0){
	    						if(type == 0 ){ // 单选
//	    							$("#searchPersonSelectd option").remove();
	    							var tempAppendOptions = '<option value="'+$(selectedOptions).last().val()+'">'+$(selectedOptions).last().text()+'</option>';
	    							if($("#searchPersonSelectd option[value='"+$(selectedOptions).last().val()+"']").size() == 0){
	    								$("#searchPersonSelectd").append(tempAppendOptions);
	    								$(selectedOptions).removeAttr("selected");
	    							}
	    						}else{
	    							$("#searchPersonSelectd").append($(selectedOptions));
	    						}
	    					}
	    				});
	    				
	    				$("#searchPersonSelectd").change(function(){
	    						$("#searchPersonSelect").append($("#searchPersonSelectd option:selected"));
	    				});
	    				
	    				$("#qd").click(function(){
	    					var selectedOption = $("#searchPersonSelectd option");
	    					var size = $(selectedOption).size();
	    					if(size == 0){
	    						alert('请选择下一环节处理人员！');
	    						return ;
	    					}
	    					var assingers = '';
	    					$.each(selectedOption,function(i,v){
	    						assingers += $(v).val();
	    						if( i != size-1 )
	    							assingers += ',';
	    					});
	    					
	    					if($("#waveAssigner").size() == 0){
	    						var tempAssignerHtml = '<input type="hidden" name="waveAssigner" value="'+assingers+'"  />';
	    						$(formId).append(tempAssignerHtml);
	    					}else{
	    						$("#waveAssigner").val(assingers);
	    					}
	    					personDiv.cancel();
	    					_submitForm();
	    				});
	    				$("#cancel").click(function(){
	    					personDiv.cancel();
	    				});
	    			}
	    		});
	    		personDiv.ShowDialog();
	    	};

   //部门选择
   var _openDeptDiv = function(type,isZnOrFzn){
	    		var tempName = type==0?'单选':'多选' ;
	    		var nameTitle = '选择下一环节处理部门（'+tempName+'）';
	    		var personHtml = '<div style="margin-left:8px;margin-top:5px"><div style="width:400px;height: 25px;">'+
								 '<input id="name" type="text" value="在此输入部门名称" style="width: 195px;color: gray" onblur="if(this.value==\'\'){this.value=\'在此输入部门名称\'; this.style.color=\'gray\';}" onfocus="if(this.value==\'在此输入部门名称\'){this.value=\'\' ;this.style.color=\'black\';}" />'+
								 '<button id="searchDept" class="button12" style="margin-left:5px;width: 60px; height: 23px;"><img src="images/search.gif" />查询</button>'+
								 '</div>';
	    		personHtml += '<div id="deptTree" style="width:195px;border:#cccccc solid 1px;height:270px;float:left;overflow:auto;"></div>';
	    		personHtml += '<div style="width:190px;border:1px solid #cccccc ;height:270px;float:left;"><select id="deptSelected"  style="height: 275px;margin: 0 0 0 0;width: 100%;" multiple="multiple" ></select></div>';
	    		personHtml +='</div>';
	    		personHtml += '<div class="buttonDiv" style="padding-left:140px;"><button id="qd" class="button12" style="margin: 10px 10px 10px 0px;width:60px;height:23px;"><img src="images/Enter.gif" />确定</button>';
	    		personHtml += '<button id="cancel" class="button12" style="width:60px;height:23px;margin: 10px 10px 10px 0px;"><img src="images/Cancel.gif" />取消</button>';
	    		personHtml += '</div>';
	    		var personDiv = new $.dialog({ 
	    			id:'personSearch',
	    			width: 440,
	    			height:425,
	    			title:nameTitle,
	    			maxBtn:false,
	    			minBtn:false,
	    			iconTitle:false,
	    			btnBar:false,
	    			autoSize:false,
	    			cover:true,
	    			rang:true,
	    			resize:false,
	    			html:personHtml,
	    			onbeforeInit:true,
	    			dgOnLoad:function(){
	    				$("#deptTree").jstree({
	    					"json_data" :{  
	    					"ajax" : {
	    						cache: false ,
	    						"url" : "dfWfDeptAction.do?method=searchDeptInfoByDeptCode&isZnOrFzn="+isZnOrFzn+"&date="+new Date().getTime(),
	    						"data" : function (n) { 
	    							if(n.attr==undefined){
	    								return {
	    									"operation" : "get_children", 
	    									"id" : "-1"
	    								}; 
	    							}else{
	    								return { 
	    									id : n.attr("id"),
	    									bmMc : n.attr("bmMc")
	    								}; 
	    							}
	    						}
	    					}
	    				},
	    				"themes" : {  
	    			      	    "theme" : "classic",  
	    			      	    "dots" : true,  
	    			      	    "icons" : true 
	    			     },
	    				"plugins" : [ "themes", "json_data","ui" ]
	    				}).bind("select_node.jstree", function(e, data) { //对tree绑定点击事件
	    					var id = data.rslt.obj.attr("id") ;
	    					var name = data.rslt.obj.attr("bmMc") ; 
	    					if(id != 'Z' && id != 'Y' && id != 'Q'){
	    						if(type==0){
	    							$("#deptSelected option").remove();
	    						}
	    						var isadd=true;
	    						 $("#deptSelected option").each(function(){
	    						 	if($(this).val()==id){
	    						 		isadd=false;
	    						 	}
	    						 }); 
	    						if(isadd){
	    							$("#deptSelected").append('<option value="'+id+'">'+name+'</option>');
	    						}
	    					}
	    				});
	    				//选部门select中的选项删除
	    				$("#deptSelected").change(function(){
	    					$(this).find(":selected").remove();
	    				});
	    				$("#cancel").click(function(){
	    					personDiv.cancel();
	    				});
	    				$("#qd").click(function(){
		    			    var displayValue="";
		    			    var selecteds=$("#deptSelected option");
		    			    var size=selecteds.size();
		    			    selecteds.each(function(i){ 
			    					var disValue = $(this).val();
			    					if(i == (size -1)){
			    						displayValue += disValue ; 
			    					}else{
			    						displayValue += disValue + ','; 
			    					}
			    				});
			    				if(displayValue == ''){
			    					alert('请选择部门！');
			    					return ;
			    				}
		                    	var tempAssignerHtml = '<input type="hidden" name="df_parametermap" value="dept:'+displayValue+'"  />';
		                    	$(formId).append(tempAssignerHtml);
		                    	personDiv.cancel();
		                    	_submitForm();
		    				});
	    				$("#searchDept").click(function(){
	    					_searchDept();
	    				});
	    				function _searchDept(){
	    					var deptName = $.trim($('#name').val());
	    					if( deptName == '' || deptName == '在此输入部门名称'){
	    						alert('请输入查询内容!');
	    					}else{
	    						$.getJSON("dfWfDeptAction.do?method=searchDept&date="+new Date().getTime(),{deptName : encodeURIComponent(deptName)}, function(data){
	    							$("#deptSelected option").remove();
	    						    $.each(data, function(i,item){
	    							   $("#deptSelected").append('<option value="'+item.id+'">'+item.bmMc+'</option>');
	    						    });
	    						});
	    					}
	    				}
	    			}
	    		});
	    		
	    		personDiv.ShowDialog();
	    	};
	    	//部门人员选择控件
	    	var _openGetPersonByDeptDiv = function(type,isZnOrFzn,roleValue){
	    		var tempName = type==0?'单选':'多选' ;
	    		var nameTitle = '选择下一环节处理部门（'+tempName+'）';
	    		var personHtml = '<div style="margin-left:8px;margin-top:5px"><div style="width:600px;height: 25px;">'+
	    		'<input id="name" type="text" value="在此输入部门名称" style="width: 195px;color: gray" onblur="if(this.value==\'\'){this.value=\'在此输入部门名称\'; this.style.color=\'gray\';}" onfocus="if(this.value==\'在此输入部门名称\'){this.value=\'\' ;this.style.color=\'black\';}" />'+
	    		'<button id="searchDept" class="button12" style="margin-left:5px;width: 60px; height: 23px;"><img src="images/search.gif" />查询</button>'+
	    		'</div>';
	    		personHtml += '<div id="personTree" style="width:195px;border:#cccccc solid 1px;height:270px;float:left;overflow:auto;"></div>';
	    		personHtml += '<div style="width:400px;border:1px solid #cccccc ;margin-left:2px;height:270px;float:left;"><select id="personSelect" style="height:270px;margin: 0 0 0 0;width: 49%;" multiple="multiple" ></select>';
	    		personHtml += '	<select id="personSelectd" style="height: 270px;margin: 0 0 0 0;width: 49%;" multiple="multiple" ></select></div>';
	    		personHtml +='</div>';
	    		personHtml += '<div class="buttonDiv" style="padding-left:140px;"><button id="qd" class="button12" style="margin: 10px 10px 10px 0px;width:60px;height:23px;"><img src="images/Enter.gif" />确定</button>';
	    		personHtml += '<button id="cancel" class="button12" style="width:60px;height:23px;margin: 10px 10px 10px 0px;"><img src="images/Cancel.gif" />取消</button>';
	    		personHtml += '</div>';
	    		var personDiv = new $.dialog({ 
	    			id:'personSearch',
	    			width: 650,
	    			height:425,
	    			title:nameTitle,
	    			maxBtn:false,
	    			minBtn:false,
	    			iconTitle:false,
	    			btnBar:false,
	    			autoSize:false,
	    			cover:true,
	    			rang:true,
	    			resize:false,
	    			html:personHtml,
	    			onbeforeInit:true,
	    			dgOnLoad:function(){
	    				$("#personTree").jstree({
	    					"json_data" :{  
	    						"ajax" : {
	    							cache: false ,
	    							"url" : "dfWfDeptAction.do?method=searchDeptInfoByDeptCode&isZnOrFzn="+isZnOrFzn+"&date="+new Date().getTime(),
	    							"data" : function (n) { 
	    								if(n.attr==undefined){
	    									return {
	    										"operation" : "get_children", 
	    										"id" : "-1"
	    									}; 
	    								}else{
	    									return { 
	    										id : n.attr("id"),
	    										bmMc : n.attr("bmMc")
	    									}; 
	    								}
	    							}
	    						}
	    					},
	    					"themes" : {  
	    						"theme" : "classic",  
	    						"dots" : true,  
	    						"icons" : true 
	    					},
	    					"plugins" : [ "themes", "json_data","ui" ]
	    				}).bind("select_node.jstree", function(e, data) { //对tree绑定点击事件
	    					var id = data.rslt.obj.attr("id") ;
	    					$("#personSelect option").remove();
	    					$.getJSON("dfWfRyAction.do?method=searchPersonByDeptCode&date="+new Date().getTime(),{deptCode : id,roleValue:roleValue}, function(data){
	    						$("#personSelect option").remove();
	    					    $.each(data, function(i,item){
	    						   $("#personSelect").append('<option value="'+item.EGh+'">'+item.EMc+'</option>');
	    					    });
	    					});
	    				});
	    				$("#personSelect").change(function(){
	    					var personSelectedVar = $("#personSelect option:selected");
	    					if($.trim($("#sdFlag").val()) == 'Y'){
	    						var tempObj = $(personSelectedVar).last();
	    						$(personSelectedVar).removeAttr("selected");
	    						$(tempObj).attr("selected" , "selected");
	    					} 
	    					$.each($("#personSelect option:selected") , function(i){
	    						var tempValue = $(this).val();
	    						var tempOption = '<option value="'+tempValue+'">'+$(this).text()+'</option>';
	    						if($.trim($("#sdFlag").val()) == 'Y'){
	    							$("#personSelectd option").remove();
	    							$("#personSelectd").append(tempOption);
	    						}else{
	    							if($("#personSelectd option[value='"+tempValue+"']").size()==0){
	    								$("#personSelectd").append(tempOption);
	    							}
	    						}
	    						//$(this).attr("selected" ,false);
	    					});
	    				});
	    				
	    				$("#personSelectd").change(function(){
	    					$("#personSelectd option:selected").remove();
	    					$(this).attr("selected" ,false);
	    				});
 
	    				$("#cancel").click(function(){
	    					personDiv.cancel();
	    				});
	    				$("#qd").click(function(){
	    					
	    					var selectedOption = $("#personSelectd option");
	    					var size = $(selectedOption).size();
	    					if(size == 0){
	    						alert('请选择下一环节处理人员！');
	    						return ;
	    					}
	    					var assingers = '';
	    					$.each(selectedOption,function(i,v){
	    						assingers += $(v).val();
	    						if( i != size-1 )
	    							assingers += ',';
	    					});
	    					
	    					if($("#waveAssigner").size() == 0){
	    						var tempAssignerHtml = '<input type="hidden" name="waveAssigner" value="'+assingers+'"  />';
	    						$(formId).append(tempAssignerHtml);
	    					}else{
	    						$("#waveAssigner").val(assingers);
	    					}
	    					personDiv.cancel();
	    					_submitForm();
	    				});
	    				$("#searchDept").click(function(){
	    					_searchDept();
	    				});
	    				function _searchDept(){
	    					var deptName = $.trim($('#name').val());
	    					if( deptName == '' || deptName == '在此输入部门名称'){
	    						alert('请输入查询内容!');
	    					}else{
	    						$.getJSON("dfWfDeptAction.do?method=searchDept&date="+new Date().getTime(),{deptName : encodeURIComponent(deptName)}, function(data){
	    							$("#deptSelected option").remove();
	    							$.each(data, function(i,item){
	    								$("#deptSelected").append('<option value="'+item.id+'">'+item.bmMc+'</option>');
	    							});
	    						});
	    					}
	    				}
	    			}
	    		});
	    		
	    		personDiv.ShowDialog();
	    	};
	    	
	    	$(this).find(".icon01").bind("click",function(){
	    		if(confirm('确定关闭？'))
	    			window.close();
	    	});
	    	
		});
    };
    
})(jQuery);