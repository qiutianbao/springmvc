/**
 * Created with IntelliJ IDEA.
 * User: folger.qin
 * Date: 12-6-14
 * Time: 上午10:23
 */
Nav = {
    ajaxData:[                                                          //有初始数据
    ],
    id:'leftNav',
    options:{
        selectLevel1Id:'', //menuid
        selectLevel2Id:0, //从0开始的列表数
        selectLevel3Id:0, //从0开始的列表数
        oldMenuId:'menu0', //默认上一个选中的的项目
        url:'', //数据请求地址                           必填
        isFirstOpen:false                                //是否第一项是打开的 (是否出下级菜单)
    },
    currMenuId:'',
    init:function (id, options) {
        this.id = id || this.id;
        if (options.data != undefined && options.data.length != 0) {
            this.ajaxData = options.data;
            delete options['data'];
        }
        $.extend(true, this.options, options);
//        this.selectLevel1Id=this.options.selectLevel1Id;
//        this.selectLevel2Id=this.options.selectLevel2Id;
//        this.oldMenuId =  this.options.oldMenuId;
//        if(typeof document.body.style.maxHeight === "undefined") {
//            document.execCommand("BackgroundImageCache",false,true);
//        }
        this.getNavData();
    },
    selectVal:function () {                                                      // 此options已经去除level1,level2
        var _self = this;
        if (undefined != this.options.selectLevel1Id && this.options.selectLevel1Id != '') {
            $('#' + this.options.selectLevel1Id).trigger('click');
            if (isNaN(parseInt(this.options.selectLevel2Id))) {
                this.options.selectLevel2Id = 0;
            }
            var opNum = this.options.selectLevel1Id.substr(4);
            var jsonList = this.ajaxData[opNum];
            var json = jsonList[this.options.selectLevel2Id];
            if (json) {
                if (json.navVOs != undefined && $.isArray(json.navVOs)) {
                    if (isNaN(parseInt(this.options.selectLevel3Id))) {
                        this.options.selectLevel3Id = 0;
                    }
                    json = json.navVOs[this.options.selectLevel3Id ];
                }
                tab.addTabItem({ tabid:json.id, text:json.text, url:json.url });
                _self.addSubTitle();
            }

        }
    },
    initBindMenu:function () {
        var _self = this;
        /*! 开启IE6 CSS背景图片缓存 */
        try {
            document.execCommand('BackgroundImageCache', false, true);
        } catch (e) {
        }
        ;
//        $('a[id^="menu"]').click(function(e){
        $('div .fx_menu >ul').on('click', 'a[id^="menu"]', function (e) {
            _self.currMenuId = this.id;
            log.debug('selectChange start:' + new Date().getTime());
            _self.selectChange();
            log.debug('selectChange end:' + new Date().getTime());
//            var opNum= _self.currMenuId.substr(4);
            var opNum;
//                $('#leftNav').attr('title',$(this).text());
            var menus = $('a[id^="menu"]');
            for (var i = 0; menus.length; i++) {
                if (menus[i].id == _self.currMenuId) {
                    opNum = i;
                    break;
                }
            }
            log.debug('refreshLeftNav start:' + new Date().getTime());
            _self.refreshLeftNav(opNum);
            log.debug('refreshLeftNav end:' + new Date().getTime());
            _self.addSubTitle();
            log.debug('addSubTitle end:' + new Date().getTime());
//                if(tab){
//                    tab.removeAll();// add by willson.qian                                       //用来在切换的时候关掉原来的不同模块内容
//                }
//            if (e) //停止事件冒泡
//                e.stopPropagation();
//            else
//                window.event.cancelBubble = true;
//            e.stopPropagation();
            e.preventDefault();
        });
//        $('#'+this.id).ligerAccordion();

        if ($('a[id^="menu"]').html() == undefined) {                                                           //如果没有上级菜单也就是只有左面菜单则默认第一个
            _self.refreshLeftNav(0)
        }
        this.selectVal();
    },
    getNavData:function () {
        var _self = this;
        if (!this.ajaxData || this.ajaxData.length == 0) {
            $.ajax({
                url:this.options.url,
                type:'POST',
                dataType:'json',
                success:function (responseData) {
                    if (responseData.success) {
                        if ($.isArray(responseData.data[0]) && responseData.data[0].length != 0) {
                            _self.ajaxData = responseData.data[0];
                            _self.initBindMenu();
                        }
                    } else {
                        alert('菜单没有正确加载,请重新刷新网页!');
                    }
                }});
        } else {                                                    //有初始值
            _self.initBindMenu();
        }
    },
    setSelectLevelVal:function (level1, level2, level3) {
        this.options.selectLevel1Id = level1;
        this.options.selectLevel2Id = level2;
        this.options.selectLevel3Id = level3;
    },
    setSelectValAndToPage:function (options) {
        this.setSelectLevelVal(options.level1, options.level2);
        delete options.level1;
        delete options.level2;
        this.selectVal();
    },
    goToInPage:function (options) {
        if (options && !$.isEmptyObject(options)) {
            f_addTab(options.id, options.text, options.url);
        }
    },
    addSubTitle:function () {
        var span = $('.l-layout-header').children('span');
        if (span.size() == 1) {                                                                                    //找到新加的删除
            span.remove();
        }
        $('.l-layout-header').prepend('<span>' + $('#' + this.currMenuId).text() + '</span>');
    },
    selectChange:function () {
        $('#' + this.currMenuId).parent().addClass('selected');
        $('#' + this.options.oldMenuId).parent().removeClass('selected');
        this.options.oldMenuId = this.currMenuId;
    },
    refreshLeftNav:function (opNum) {
        var _self = this;
        var json = this.ajaxData[opNum];
//            alert(JSON.stringify(json)) ;
        if ($.isArray(json) && json.length == 0) {
            $('#' + this.id).empty();
        } else if (typeof json[0] == 'object') {
//                $('#leftNav').show().attr('title',$('#'+_self.currMenuId).text());
//                var innerHtml = $('.l-layout-header').html();
//                if(json.length !=0){
            this.redrawLeftNav(json);
//                }
        } else if (typeof json[0] == 'string') {
            //原来是直接打开的，现在改成跳转到新页面
            if (json.length == 3) {
                $('#' + this.id).hide();
                f_addTab(json[0], json[1], json[2]);
            }
            //直接跳转到指定页面
            if (json.length == 1) {
                this.redirectPage(json[0]);
            }
        }
    },
    redrawLeftNav:function (json) {
//        $('#'+this.id).empty();
        log.debug('redrawLeftNav1:' + new Date().getTime());
        if (!json || ($.isArray(json) && json.length == 0))return;
//         var html='';
        var arr = [];
        for (var i = 0; i < json.length; i++) {
            var currNav = json[i];
//            var tmpl;
            if (currNav.openWinType != 0 && currNav.openWinType != undefined) {                                                        //打开新窗口
                if (currNav.openWinType == 1) {                                                               //win
                    arr.push('<a class="l-accordion-header" id="' + currNav.id + '" href="javascript:Nav.openPageMe(\'' + currNav.url + '\',\'' + currNav.text + '\',\'' + currNav.width + '\',\'' + currNav.height + '\')"><span class="l-accordion-header-inner">' + currNav.text + '</span></a>');
                } else if (currNav.openWinType == 2) {                                                     //dialog
                    arr.push('<a class="l-accordion-header" id="' + currNav.id + '" href="javascript:Nav.openDialog(\'' + currNav.url + '\',\'' + currNav.text + '\',\'' + currNav.width + '\',\'' + currNav.height + '\')"><span class="l-accordion-header-inner">' + currNav.text + '</span></a>');
                }
            } else {
                if (currNav['navVOs'] != undefined && $.isArray(currNav['navVOs']) && currNav['navVOs'].length > 0) {
                    arr.push('<div id="' + currNav.id + '" title="' + currNav.text + '"><ul>');
                    for (var j = 0; j < currNav['navVOs'].length; j++) {
                        var childNav = currNav['navVOs'][j];
                        arr.push('<li style="height:26px; line-height:26px; padding-left:20px;border: 1px solid white; cursor:pointer;" onclick="javascript:f_addTab(\'' + childNav.id + '\',\'' + childNav.text + '\',\'' + childNav.url + '\')">' + childNav.text + '</li>');
                    }
                    arr.push('</ul></div><span style="clear:both;"></span>');
                } else {
                    arr.push('<a class="l-accordion-header" id="' + currNav.id + '" href="javascript:f_addTab(\'' + currNav.id + '\',\'' + currNav.text + '\',\'' + currNav.url + '\')"><span class="l-accordion-header-inner">' + currNav.text + '</span></a>');
                }
            }
//            html+=tmpl;
        }
//        $('#'+this.id).html(arr.join(''));
        document.getElementById(this.id).innerHTML = arr.join('')
        log.debug('redrawLeftNav2:' + new Date().getTime());
        if (this.accordion == undefined) {
            $('#' + this.id).ligerAccordion({ "isFirstOpen":this.options.isFirstOpen});
            this.accordion = $('#' + this.id).ligerGetAccordionManager();
        } else {
            this.accordion._render();
        }
        log.debug('redrawLeftNav3:' + new Date().getTime());
    },
    redirectPage:function (url) {
        location.assign(url);
    },
    /**
     * 在新窗口打开页面
     * @param url       页面URL
     * @param title     页面标题
     * @param width     页面宽度
     * @param height    页面高度
     */
    openPageMe:function (url, title, width, height) {
        window.open(url, title, "alwaysRaised=yes," +
            "depended=no," +
            height == null ? "" : "height=" + height + "," +
            width == null ? "" : "width=" + width + "," +
            "location=no," +
            "resizable=yes," +
            "toolbar=no," +
            "scrollbars=yes," +
            "fullscreen=1");
    },
    openDialog:function (url, title, width, height) {
        $.dialog({
            id:'openNavDialog',
            title:title,
            width:width + 'px',
            height:height + 'px',
            lock:true,
            resize:false,
            max:false,
            min:false,
            fixed:true,
            content:"url:" + url
        });
    }
}