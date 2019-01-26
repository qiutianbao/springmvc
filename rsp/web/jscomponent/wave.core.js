/**
 * SDC开发平台核心JavaScript库 
 * Dependencies jQuery.js
 */
(function(){
	if (typeof jQuery === 'undefined') {// check jquery lib
		alert('need to import jquery.js');
		return;
	}
	//what is wave?
	window.wave = {
		contextPath:"",
		debug:true,
		theme:"wave",
		local:"zh_CN",
		locale:"zh_CN",
		localText:{},
		text:function(key){return  wave.localText[key] || '';},
		imported:{},
		core:{},
		onLoad:function(fn){$(fn);},//wave.onLoad = jQuery.domReady
		trim: function(str){return $.trim(str);},//wave.trim= jQuery.trim
		/**
	     * 事件的绑定
	     * @param {Element|String} src页面的元素或id
	     * @param {string} event 事件名
	     * @param {function} listener 回调函数,this为事件源，会传入一个事件对象为参数，如果不传就是出发该元素的事件
	     */
		event:function(src, event, listener){
	        var eventSrc = wave.getElement(src);
	        if (eventSrc) {
	        	if(listener){
	        		$(eventSrc).bind(event.replace('on', ''), listener);
	        	}else{
	        		$(eventSrc).trigger(event.replace('on', ''));
	        	}
	        }
	    },
	    /**
	     * 解除对事件的绑定
	     * @param {Element|String} src页面的元素或id
	     * @param {string} event 事件名
	     * @param {function} listener 回调函数,this为事件源，
	     */
	    unbind: function(src, event, listener){
	        $(wave.getElement(src)).unbind(event.replace('on', ''), listener);
	    },
	    /**
	     * 通过id查找对象
	     * @param {string} id
	     */
	    id :function(id){
	        return document.getElementById(id);
	    },
	    /**
	     * 通过name查找对象
	     * @param {string} name
	     */
	    name : function(name){
	        return document.getElementsByName(name);
	    },
	    /**
	     * 通过标签查找对象
	     * @param {string} tag
	     */
	    tag : function(tag){
	        return document.getElementsByTagName(tag);
	    },
	    /**
	     * 建立新的页面元素
	     * @param {string} tag 标签
	     * @param {Element} parent 可选，创建的元素加入该元素末尾
	     */
	    create : function(tag, parent){
	        var element = document.createElement(tag);
	        if (parent) 
	            parent.appendChild(element);
	        return element;
	    },
	    /**
	     * 简单的在下方模拟控制台，输出调试信息，替代alert，不要再页面加载时使用，应在页面加载完毕后使用
	     * @param {string} str
	     */
	    console : function(str){
	        if (!wave.debug) 
	            return;
	        var console = wave.id('wave.console');
	        if (!console) 
	            console = wave.create('div', wave.tag('body')[0]);
	        console.style['position'] = 'absolute';
	        console.id = 'wave.console';
	        console.style['bottom'] = '0px';
	        console.style['left'] = '20px';
	        console.style['color'] = 'red';
	        console.innerHTML += str + '<br>';
	    },
	    /**
	     * 简单的序列化将object转换为string
	     */
	    serialize : function(obj){
	        var str = ['{'];
	        if (typeof obj === 'object') {
	            for (e in obj) {
	                if (obj.hasOwnProperty(e)) {
	                    str.push('"');
	                    str.push(e);
	                    str.push('":"');
	                    str.push(obj[e] || '');
	                    str.push('"');
	                    str.push(',');
	                }
	            }
	            if (str.length > 1) 
	                str.pop();
	        }
	        str.push('}');
	        return str.join('');
	    },
	    isTrue : function(value){
	        return (value == true || value == "1" || value == 1 ||
	        value == "true" ||
	        value == "yes" ||
	        value == "y" ||
	        value == "TRUE" ||
	        value == "YES" ||
	        value == "Y");
	    },
	    /**
	     * 如果传入的是string则当做id查找元素否则返回自己，
	     * 给其他的函数使用
	     * @param {Object} element
	     */
	   getElement : function(element){
	        return typeof element === 'string' ? wave.id(element) : element;
	    },
	    lockPage : function(){
	        $.blockUI({message:(arguments.length==1?arguments[0]:wave.localText["processing"])});
	        window.waveLockFlag = true;
	    },
	    unlockPage : function(){
	        $.unblockUI();
	        window.waveLockFlag = false;
	    },
	    ajax : function(options){
	        $.ajax({
	            url: options.url,
	            data: options.data,
	            dataType: "json",
	            type: 'POST',
	            success: function(result){
	            	result=eval(result);
	                if (result.hasError) {
	                    alert(result.errorMsg);//提示错误消息，自动关闭
	                    return;
	                }
	                if(typeof(options.success)=='function')options.success(result.result);
	            },
	            error: function(request, textStatus){
	                alert(request.responseText);
	            }
	        });
	        
	    },
	    /**
	     * form data ->json object
	     */
	    serializeForm: function(formid){
	    	//可编辑表格中的:input去除
	    	 var exclueSelector=[];
	    	 try{
				var grids= $("#"+formid+" .datagrid table[id]").each(function(i,o){
					var gridid= o.id;
					if($("#"+gridid).data("datagrid").options.editable){
						exclueSelector.push("#"+gridid+" :input");
					}
				});
	    	 }catch(e){}
			
			var o = {};
	        $("#" + formid + " :input").not($(exclueSelector.join(","))).each(function(){
	            var n = $(this).attr("name");
	            if(n){
		            if(this.type=="checkbox"){
		            	if(!o[n]){o[n]=[];}
		            	if(this.checked){
		            		o[n].push(this.value);
		            	}
		            }else if(this.type=="radio"){
		            	if(!o[n]){o[n]="";}
		            	if(this.checked){
		            		o[n]=this.value;
		            	}
		            }else if (this.tagName=="select"){
		            	o[n]=this.options[this.selectedIndex].value;
		            }else{
		            	o[n]=this.value;
		            	if(this.type=="text" && this.getAttribute("numberformat")){//金额输入字段，去掉,
		            		o[n]=this.value.replace(/\,/gi,"");
		            	}
		            }
	            }
	        });
	        //join array value
	        for(var k in o){
	        	if(o[k].constructor && o[k].constructor==Array){
	        		o[k]=o[k].join(",");
	        	}
	        }
	        return o;
	    },
	    setChanged : function(flag){
	        if (flag == "1") {
	            $(document.body).data("_changed", "1");
	            $("#needsave").show();
	        }
	        else {
	            $(document.body).data("_changed", "0");
	            $("#needsave").hide();
	        }
	    },
	    getChanged : function(){
	        return $(document.body).data("_changed") || "0";
	    },
	    clone : function(jsonObj){
	        var buf;
	        if (jsonObj instanceof Array) {
	            buf = [];
	            var i = jsonObj.length;
	            while (i--) {
	                buf[i] = wave.clone(jsonObj[i]);
	            }
	            return buf;
	        }
	        else 
	            if (jsonObj instanceof Object) {
	                buf = {};
	                for (var k in jsonObj) {
	                    buf[k] = wave.clone(jsonObj[k]);
	                }
	                return buf;
	            }
	            else {
	                return jsonObj;
	            }
	    },
	    /**
	     * 格式化数字
	     * @param {String} number 数字或者字符串
	     * @param {Boolean} comma 是否显示小数点左边的逗号 默认是false
	     * @param {int} fixed 小数点后面的精度，默认是2
	     * @param scale {int} (非必须,默认为20) 小数点前面长度
	     */
	    formatNumber : function(number, comma, fixed,scale){
	        comma = (comma.toString()=="true");
	        scale = typeof(arguments[3])!="undefined"?parseInt(arguments[3],10):16;//default 16
	        fixed = typeof(fixed)!="undefined"?parseInt(fixed,10):2;
	        if(null!=number) number= number.toString();
	        if (number == "") { 
	        	return "";
	        }else {
	            if (isNaN(number)) {
	                return "";
	            }
	            else {
	            	var flag ="";
	            	
	                number = parseFloat(number).toFixed(fixed).toString();
	                if((new RegExp("^[\\-\\+]")).test(number)){//以 + -开头
	            		flag = number.substring(0,1);
	            		number= number.substr(1);
	            	}
	                number =number.replace( new RegExp("^([\\+\\-]?[0-9]{"+scale+","+scale+"})[^\.]*"),"$1");
	                var dotIndex = number.lastIndexOf(".");
	                var arr = number.split("");
	                if(dotIndex==-1){dotIndex= arr.length;}
	                var resultarr = [];
	                for (var i = 0; i < arr.length; i++) {
	                    resultarr.push(arr[i]);
	                    if (dotIndex > (i + 1) && (dotIndex - i) % 3 == 1) 
	                    	if(comma){
	                    		resultarr.push(",");
	                    	}
	                }
	                return flag+resultarr.join("");
	            }
	        }
	    },
	    /**
	     * 初始化数字输入
	     * inputId  {String} 文本框的ID
	     * comma  {Boolean} 是否需要,
	     * fixed {int} 小数点后面精度
	     * scale {int} (非必须,默认为20) 小数点前面长度
	     */
	    initMoneyInput:function(inputId,comma,fixed,scale){
	    	var oInput = document.getElementById(inputId);
	    	scale = typeof(arguments[3])!="undefined"?parseInt(arguments[3],10):16;//default 16
	    	oInput.setAttribute("rel", "MoneyInput");
	    	oInput.style.textAlign="right";
	    	//格式化当前值
	    	oInput.value = oInput.value.replace(/\,/gi,"");
	    	oInput.value=wave.formatNumber(oInput.value,comma,fixed,scale);
	    	oInput.setAttribute("previousValue",oInput.value);
	    	
	    	wave.event(inputId,"blur",function (e){
	  			this.value= wave.formatNumber(this.value,comma,fixed,scale);
	  			var attrPreviousValue=document.getElementById(inputId).getAttribute("previousValue");
	  			var previousValue=typeof(attrPreviousValue)!="undefined"?attrPreviousValue:"";
	  			if(previousValue!=this.value){
	  				$(this).change();
	  				if($(this).parents(".dataForm").length>0){
	  					wave.setChanged("1");
	  				}
	  			}
	  		});
			wave.event(inputId,"focus",function (e){
				oInput.setAttribute("previousValue",oInput.value);
				oInput.value= oInput.value.replace(/\,/gi,"");
	  		});
			//oInput may  deleted before form submitting
	  		$("#"+inputId).parents("form").bind("submit",function (){
	  			if(oInput) oInput.value= oInput.value.replace(/\,/gi,""); 
		  	}).bind("beforesave",function (){
		  		if(oInput) oInput.value= oInput.value.replace(/\,/gi,"");
			}).bind("aftersave",function (){
				if(oInput) oInput.value= wave.formatNumber(oInput.value.replace(/\,/gi,""),comma,fixed,scale);
			});
			$("#"+inputId).attr("numberformat",comma+","+fixed+","+scale);//在inputId上做一个记号
			//输入限制
			wave.event(inputId,"keydown",function (e){
				var kc = e.keyCode;
				//增加小键盘的数字96-105和.键---110
				//数字  ← → . - + [backspace] [tab] [delete] [home] [end] [enter] 允许输入
				if((kc>=48 && kc<=57) || (kc>=96 && kc<=105) ||kc==37||kc==110 || kc==39 || kc==190 || kc==189 || kc==107 || kc==109 || kc==187 || kc==8 || kc==9 || kc ==46 || kc==36 || kc ==35  || kc ==13 || e.ctrlKey || e.altKey || e.metaKey ){
					
					var oInput = this;
					
					var hasSelectAll = false;
					var cursorPosotion= -2;//光标当前位置
					if(typeof(oInput.selectionStart)=="number") {
						cursorPosotion = oInput.selectionStart;
						hasSelectAll= (oInput.value.substring(oInput.selectionStart,oInput.selectionEnd)==oInput.value);
					}else{
						var rng=document.selection.createRange();
						hasSelectAll= (rng.text ==oInput.value);
						rng.moveStart("character",-oInput.value.length);
						cursorPosotion=rng.text.length;
					}
					if(hasSelectAll){//全选
						oInput.value="";
						if(kc==8){return false;}
					}
					//+ - 只能在最左边输入 不可以重复输入
					if((kc==189 || kc==187 || kc==107 || kc==109) &&  (cursorPosotion!=0 || (new RegExp("^[\\-\\+]")).test(this.value) ) ){
						return false;
					}
					
					//.不能重复输入 不能在+-之前 如果fixed==0 不能输入.--110是小键盘的.
					if((kc==190 || kc==110 )&& (fixed==0 || this.value.indexOf(".")!=-1 || (cursorPosotion==0 && (new RegExp("^[\\-\\+]")).test(this.value) ))){
						return false;
					}
					//数字：小数点前面不能超过scale位 小数点后面不能超过fixed位--增加小键盘的数字96-105
					var r = this.value.indexOf(".")!=-1 ?this.value.indexOf("."):this.value.length;
					if(((kc>=48 && kc<=57)||(kc>=96 && kc<=105)) && 
							( (this.value.replace(/(\.[0-9]{0,}$)|(^[\+\-])/gi,"").length>=scale   && cursorPosotion<=r ) ||
								(this.value.indexOf(".")!=-1 && cursorPosotion>this.value.indexOf(".") && this.value.substr(this.value.indexOf(".")+1).length>=fixed  )
							) ){
						return false;
					}
					return true;
				}else{
					return false;
				}
	  		});
			
			//粘贴格式化
			wave.event(inputId,"paste",function (e){
				window.setTimeout(function(){
					var oInput = document.getElementById(inputId);
					var v = oInput.value;
					var f = oInput.getAttribute("numberformat").split(",");
					document.getElementById(inputId).value= wave.formatNumber(v,false,f[1],f[2]) ;
				},0);
			});
	    },
	    /**
	     * 获取自定义控件
	     * @param {String} paramId 控件ID
	     */
	    getComp : function(paramId){
	        return window._c ? window._c[paramId] : null;
	    },
	    
	    /**
	     * 表单赋值
	     * @param {String} name  表单name
	     * @param {String/Array} v  值如果是复选框，此值可以为字符串数组,其他为字符串
	     * @param {String} [非必须] 指定form的id
	     */
	    setFormValue : function(name, v, formId){
	    	var ctx = formId?$("#"+formId):$(document.body);
	    	//textbox 
	        var $input = $(":input[name=" + name + "][type!='radio'][type!='checkbox']",ctx);
	        $input.val(v);
	        //money input
	        if($input.attr("numberformat")){
	        	var f = $input.attr("numberformat").split(",");
	        	$input.val(wave.formatNumber(v,f[0],f[1],f[2]));
	        }
	        if($input.length==0){
		        try{//v 拼接css selector 可能产生异常
			        //checkbox
			        var $checkboxes = $(":checkbox[name=" + name + "]",ctx);
			        if($checkboxes.length>0){
			        	$checkboxes.filter("[value=" + v + "]").attr("checked", true);
			            if(v && v.toString().split(",").length>1){// like "1,2"
			            	v= v.toString().split(",");
			            }
			            if (v && v.push) {// array checkbox
			                $.each(v, function(i, o){
			                	$checkboxes.filter("[value=" + o + "]").attr("checked", true);
			                });
			            }
			        }
			        //radio
			        // * in selector cased death recursion
			        if(v.indexOf("*")==-1){
			        	$(":radio[name=" + name + "][value=" + v + "]",ctx).attr("checked", true);
			        }
		        }catch(e){}
	        }
	    },
	    /**
	     * 获取表单属性
	     * @param {String}  name 表单name
	     * @param {String} formId  [非必须]指定form ID
	     */
	    getFormValue : function(name, formId){
	    	var ctx = formId?$("#"+formId):$(document.body);
	    	//non checkbox & radio
	        var v = $(":input:not(:checkbox):not(:radio)[name=" + name + "]",ctx).val();
	        if (v) {
	            return v;
	        } else {//radio or checkbox 
	            v = $(":radio[name=" + name + "][checked]",ctx).val();
	            if (v) {
	                return v;
	            }else {//checkbox
	                var result = [];
	                $(":checkbox[name=" + name + "][checked]",ctx).each(function(){
	                    result.push(this.value);
	                });
	                return result.join(",");
	            }
	        }
	    },
	    /**
	     * 表单重置
	     */
	    resetForm : function(formid){
	        $("#" + formid + " :input[type!='radio'][type!='checkbox']:not(.sysBtn)").val("");
	    },
	    
	    /**
	     *	表单加载数据
	     *	@ formid 表单ID
	     *	@ data  表单数据 
	     */
	    loadFormData: function (formid,data){
	    	for (var f in data){
				wave.setFormValue(f,data[f],formid);
			}
	    },
	    /**
	     * 下面的恶心得让人吐的commonExit方法
	     */
	    commonExit:function (url){
			if (url == null || typeof(url) == "undefined"){
		    	url = wave.contextPath+"/sys/MainMess.do";
			}
			if (window.name == "main"){
				    window.location.href =url;
			}else{ //弹出式
				    window.close();
			}
	    }
	};
	//wave basic
	/**
	 * _checkLeave:check unsaved data when leaving page
	 */
	window._checkLeave=function (){
  		var e =window.event?window.event:_checkLeave.caller.arguments[0];
  		if($(document.body).data("_changed")=="1"){e.returnValue=wave.localText.unsaveddata;}
  	};
  	$(function (){
  		//wave aliases
  		if(typeof(JSON.stringify)=='function'){wave.stringify= JSON.stringify;}
  		if(typeof($.extend)=='function'){wave.extend= $.extend;}
  		
  		if( window.openDatabase && (!window.opera)){//chrome,safari
  			window.onbeforeunload =function (){
  				if($(document.body).data("_changed")=="1"){
  					return wave.localText.unsaveddata;
  				}
  			};
  		}
  		if(window.opera){//oprea
  			//TODO : waiting opera support onbeforeunload ,maybe 100 years :()
  		}
  		//IE 6 不缓存背景图片BUG
  		if(navigator.userAgent.toLowerCase().indexOf("msie 6")!=-1){
  			try{
  				document.execCommand("BackgroundImageCache", false, true);
  			}catch(e){}
  		}
		//所有ajax请求设置不缓存
  		$.ajaxSetup({ cache: false,dataType :'json'});
  		
			
		//页面加载 _changed=0
		$(document.body).data("_changed","0");
			 
  	});//end domstart
})();
