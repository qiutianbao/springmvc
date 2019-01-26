/**
  *   前台组件
  **/
(function($) {
    $.fn.extend({
        dfupload : function(options) {
            var defaults = {
                unique_names : true,
                vpnModel :['10.100.0.236','10.100.0.237','10.100.0.238'],
                url : '',
                flash_swf_url : '',
                vpnIp :'',
				runtimes:'flash'
            };
            var flag = 0;
            var options = $.extend(defaults, options);
			var flag = false;
			for(var i =0,len = options.vpnModel.length; i<len ; i++){
				if(options.vpnModel[i] === options.vpnIp){
					flag = true;
					break;
				}
			}

			if(flag){
            	$(this).pluploadQueue({
					// General settings
					runtimes : 'html4',
					url : options.url,
					unique_names : options.unique_names,
					filters :options.filters,
			
					resize : options.resize ,
					init : {
						FileUploaded: function(up, file, info) {
							var responseVal = info.response;
							var result =  eval('(' + responseVal + ')');  
							file.rid = result.rid;
						}
					}
				});
            }else{
				$(this).pluploadQueue({
					// General settings
					runtimes : options.runtimes,
					url : options.url,
					max_file_size : options.max_file_size,
					chunk_size : options.chunk_size,
					unique_names : options.unique_names,
					filters :options.filters,
			
					resize : options.resize ,
			
					flash_swf_url : options.flash_swf_url,
					init : {
						FileUploaded: function(up, file, info) {
							var responseVal = info.response;
							var result =  eval('(' + responseVal + ')');  
							file.rid = result.rid;
						}
					}
				});
			}
			
			return this.each(function(){
				
			});
        }
    });
})(jQuery);