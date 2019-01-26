$(document).ready( function() {

	//输入提示
	$('.inputHint').coolinput( {
		blurClass : 'inputHint',
		activeClass : 'inputActive',
		source : 'title'
	});

	//日历控件
	$('.datePick').datepick( {
		dateFormat: 'yy年m月d日',
		minDate : new Date(1990, 1 - 1, 26),
		maxDate : new Date(),
		closeAtTop: false
	});
	
	function findValue(row) {
		return row[2];
	}
	//

   //报告作者输入自动完成
	 $("input#inputAuthor").autocomplete({
		    source: function(request, response) {
		        $.ajax({
		        	type: "POST",
		            url: "security/authors",
		            dataType: "xml",
		            data: {
		                maxRows: 10,
		                inputWords: request.term
		              },
		            success: function(xml) {
		            	var myArr =[];
		            	var security = "security\\:";
		            	var users = $(xml).find(security+"user");
		            	if(users.val() == null ) {
		            		security = "";
		            		users = $(xml).find(security+"user");
		            	}
		            	users.each(function(){
		               		myArr.push($(this).find(security+"name").text()+"  "+$(this).find(security+"pin-yin").text()+","+$(this).find(security+"id").text());
		            	});
		                var mydata = $.map(myArr, function(item) {
		                    return {
		                        value: item.split(",")[0],
		                        id: item.split(",")[1]
		                    };
		                }); 
		                var results = $.ui.autocomplete.filter(mydata, request.term);
		                response(results.slice(0, 10));
		            }
		        });
		    },
		    select: function(event, ui) {
		    	$("input#authorId").val(ui.item.id);
		      },
		    minLength: 1,
		    autoFocus:true
		});

	   //行业代码输入自动完成
	 $("input#industry").autocomplete({
		    source: function(request, response) {
		        $.ajax({
		        	type: "POST",
		            url: "finance/industries",
		            dataType: "xml",
		            data: {
		                maxRows: 10,
		                inputWords: request.term
		              },
		            success: function(xml) {
		            	var myArr =[];
		            	var finance = "finance\\:";
		            	var industries = $(xml).find(finance+"industry");
		            	if(industries.val() == null ) {
		            		finance = "";
		            		industries = $(xml).find(finance+"industry");
		            	}
		            	industries.each(function(){
		               		myArr.push($(this).find(finance+"code").text()+" "+$(this).find(finance+"name")[0].childNodes[0].nodeValue+","+$(this).find(finance+"id").text());
		            	});
		                var mydata = $.map(myArr, function(item) {
		                    return {
		                        label: item.split(",")[0],
		                        id: item.split(",")[1]
		                    };
		                }); 
		                var results = $.ui.autocomplete.filter(mydata, request.term);
		                response(results.slice(0, 10));
		            }
		        });
		    },
		    select: function(event, ui) {
		    	$("input#industryId").val(ui.item.id);
		      },
		    minLength: 1,
		    autoFocus:true
		});

	 
	   //公司代码输入自动完成
	 $("input#inputRelatedProduct").autocomplete({
		    source: function(request, response) {
		        $.ajax({
		        	type: "POST",
		            url: "finance/stocks",
		            dataType: "xml",
		            data: {
		                maxRows: 10,
		                inputWords: request.term
		              },
		            success: function(xml) {
		            	var myArr =[];
		            	var finance = "finance\\:";
		            	var stocks = $(xml).find(finance+"stock");
		            	if(stocks.val() == null ) {
		            		finance = "";
		            		stocks = $(xml).find(finance+"stock");
		            	}
		            	stocks.each(function(){
		               		myArr.push($(this).find(finance+"stock-code").text()+"   "+$(this).find(finance+"name").text()+"  "+$(this).find(finance+"pin-yin").text()+","+$(this).find(finance+"id").text());
		            	});
		                var mydata = $.map(myArr, function(item) {
		                    return {
		                        value: item.split(",")[0],
		                        id: item.split(",")[1]
		                    };
		                }); 
		                var results = $.ui.autocomplete.filter(mydata, request.term);
		                response(results.slice(0, 10));
		            }
		        });
		    },
		    select: function(event, ui) {
		    	$("input#relatedProductId").val(ui.item.id);
		      },
		    minLength: 1,
		    autoFocus:true
		});
	 	
	 	//将报告列表XML文件转化为HTML列表
		function parseXMLtoList(xml){
			var rowElement = $("#dataRows").empty();
		    $(xml).find("report").each(function(){
		         var id = $($(this).find("id").toArray()[0]).text();
		         var industry_id = $(this).find("industry-id").text();
		         var report_date = $(this).find("report-date").text();
		         var second_title = $(this).find("second-title").text();
		         var stock_code = $(this).find("stock-code").text();
		         var title = $(this).find("title").text();
		         var rowElement = $("#dataRows").append("<tr></tr>")
		         $(rowElement).append("<td bgcolor='#FFFFFF'><a class='ajaxbox3' href=reports/"+ id +"/download><img src='http://192.168.1.35:8080/site/images/attach.gif' width='9' height='15' /></a></td>");
		         $(rowElement).append("<td bgcolor='#FFFFFF'><a href=reports/"+ id + "/detail target='_blank'> " + title + "</a> </td>");
		         $(rowElement).append("<td bgcolor='#FFFFFF'>" + report_date + "</td>");
		         $(rowElement).append("<td bgcolor='#FFFFFF'><a href=#>报告类型</a></td>");
		         $(rowElement).append("<td bgcolor='#FFFFFF'><a href=#>公司</a></td>");
		         $(rowElement).append("<td bgcolor='#FFFFFF'><a href=#>行业ID是" + industry_id + "</a></td>");
		         $(rowElement).append("<td bgcolor='#FFFFFF'><a href=#>作者名</a></td>");
		         $(rowElement).append("<td bgcolor='#FFFFFF'>0</td>");
		         $(rowElement).append("<td bgcolor='#FFFFFF'>0</td>");
		    });
		}
	//显示DOM节点
	$('.showButton').live("click",function () {
		var selector = '#' + $(this).attr('id')  + 'Target';
//		window.alert('selector need show: ' + selector);
		if ($(selector).hasClass('shown')) {
			return;
		}

//		window.alert('selector to show: ' + selector);
//		$(selector).show('slow');
		$(selector).show();
		$(selector).addClass('shown');
    });

	//隐藏DOM节点
	$('.hideButton').click(function () {
		var selector = '#' + $(this).attr('id') + 'Target';
//		window.alert('selector need hide: ' + selector);
		if (!($(selector).hasClass('shown'))) {
			return;
		}

//		window.alert('selector to hide: ' + selector);
//		$(selector).hide('slow');
		$(selector).hide();
		$(selector).removeClass('shown');
    });
	
	//AJAX弹出窗口效果
	$("a.ajaxbox").fancybox({
		'zoomSpeedIn': 0, 
		'zoomSpeedOut': 0, 
		'overlayShow': false,
		'frameWidth': 320,
		'frameHeight': 240,
		'hideOnContentClick': false
	});
	
	//AJAX弹出窗口效果，关闭后主页面自动刷新
	$("a.ajaxbox2").fancybox({
		'speedIn': 0, 
		'speedOut': 0, 
		'overlayShow': false,
		'width': 300,
		'height': 130,
		'hideOnContentClick': false,
		'onClose': function() {
			var location = window.location;
			window.location = location;
		}
	});
	
	$("a.ajaxbox3").fancybox({
		'speedIn': 0, 
		'speedOut': 0, 
		'overlayShow': false,
		'width': 300,
		'height': 130,
		'hideOnContentClick': false
	}			
	);
	
	//处理书签添加与取消
    $("a.ajaxbox6").live("click",function(){
    	$.ajax({
			url:$(this).attr("href"),
			success:function(response){
				var displayValue = "<div style='height:90px;width:200px;text-align:center;'>书签操作成功！ESC或关闭返回</div>";
				if("false" == response) 
					displayValue = "<div style='height:90px;width:200px;text-align:center;'>书签操作失败！ESC或关闭返回</div>";
				 $.fancybox({
				     content : displayValue,
					 onClosed: function() {
						var theLocation = window.location.href;
						var index = theLocation.indexOf("#");
						if(index > 0) {
							theLocation = theLocation.substring(0,index);
						}
						window.location = theLocation;
						}
					  });
			}
	    });
	return false;
	});

    // bind 'myForm' and provide a simple callback function 
    $('.ajaxForm').ajaxForm(function() { 
        alert("成功！"); 
    }); 
});

function clearTextHintOnSubmit(form) {
	var inputs = form.getElementsByTagName('input');
	for (i = 0; i < inputs.length; i++) {
		var input = inputs[i];
		if (input.title != 'undefined' && input.value == input.title) {
			input.value = '';
		}
	}
}

function del() {
	var msg = "您真的确定要删除吗？\n\n请确认！";
	if (confirm(msg)==true){
		return true;
	}else{
		return false;
	}
}