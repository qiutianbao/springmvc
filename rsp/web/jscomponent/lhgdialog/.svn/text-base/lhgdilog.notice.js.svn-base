/**
 * Created with IntelliJ IDEA.
 * User: folger.qin
 * Date: 12-8-22
 * Time: 上午9:39
 * To change this template use File | Settings | File Templates.
 */
/* 扩展窗口外部方法 */
$.dialog.notice = function( options )
{
    var opts = options || {},
        api, aConfig, hide, wrap, top,
        duration = opts.duration || 800;

    var config = {
        id: 'Notice',
        left: '100%',
        top: '100%',
        fixed: true,
        drag: true,
        resize: false,
        max : false,
        min : false,
        noFocus:true,                                       //表示初始化时是否执行,true不执行
        init: function(here){
            api = this;
            aConfig = api.config;
            wrap = api.DOM.wrap;
            top = parseInt(wrap[0].style.top);
            hide = top + wrap[0].offsetHeight;

            wrap.css('top', hide + 'px')
                .animate({top: top + 'px'}, duration, function(){
                    opts.init && opts.init.call(api, here);
                });
        },
        focus: function(here){
            this.time();
            return true;
        },
        close: function(here){
            wrap.animate({top: hide + 'px'}, duration, function(){
                opts.close && opts.close.call(this, here);
                aConfig.close = $.noop;
                api.close();
            });
            return false;
        }
    };

    for(var i in opts)
    {
        if( config[i] === undefined ) config[i] = opts[i];
    }

    return $.dialog( config );
};
