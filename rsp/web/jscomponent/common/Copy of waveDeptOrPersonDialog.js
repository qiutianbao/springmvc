(function ($) {
    $.extend($.fn, {
        waveDeptOrPersonDialog:function (options) {
            var $this, url, urlArr = [], settings, optionDialog, defaults = {
                id:'id',
                deptUrl:'url:dept/display', //部门选择的action
                personUrl:'url:org/user/display', //人员选择的action
                width:"800px",
                height:"500px",
                title:'部门选择',
                openAll:false, //是否全展开
                link:true,
                isMulti:false, //是否多选:true/false
                min:-1, // 最少选几个,其中-1为无效标志
                max:-1, //最多选几个,其中-1为无效标志   如果设置了min,max,则isMulti失效
                displayId:'dId',
                valueId:'valueId',
                callBackMethod:null,
                isDeptOrPerson:true, //true代表默认为部门
                rootDeptId:'-1', //默认的部门根节点
                onlyRootDept:false, //只显示该部门以下人员,只是默认打开?
                displayPId:"pId", //展示ID
                valuePId:"valudPId", //存放值的ID
                canChooseNull:false, //可选择0项吗? 默认false
                framesNo:"", //要返回值的弹出窗的在frames中的序号,默认为空
                framesNoIE:1 //IE framesNo
            };
            if (!!options.title) {
                defaults.title = options.title;
            } else {
                defaults.title = options.isDeptOrPerson ? '部门选择' : '人员选择';
            }
            settings = $.extend({}, defaults, options);//扩展参数
            //选人的时候覆盖掉传过来的宽度高度
            if (!settings.isDeptOrPerson) {
                settings.width = "700px";
                settings.height = "430px";
            }
            //处理一下min/max
            settings.min = window.parseInt(settings.min);
            settings.max = window.parseInt(settings.max);
            urlArr.push(settings.isDeptOrPerson ? settings.deptUrl : settings.personUrl);
            urlArr.push("?displayId=");
            urlArr.push(settings.displayId);
            urlArr.push("&valueId=");
            urlArr.push(settings.valueId);
            urlArr.push("&sdFlag=");
            urlArr.push(settings.isMulti ? 'Y' : 'N');
            urlArr.push("&displayPId=");
            urlArr.push(settings.displayPId);
            urlArr.push("&valuePId=");
            urlArr.push(settings.valuePId);
            urlArr.push("&framesNo=");
            urlArr.push(settings.framesNo);
            urlArr.push("&framesNoIE=");
            urlArr.push(settings.framesNoIE);
            urlArr.push("&canChooseNull=");
            urlArr.push(settings.canChooseNull);
            // 选择人员的部门跟节点
            urlArr.push("&rootDeptId=");
            urlArr.push(settings.rootDeptId);
            urlArr.push("&onlyRootDept=");
            urlArr.push(settings.onlyRootDept);
            urlArr.push("&callBackMethodName=");
            urlArr.push(settings.callBackMethodName);
            urlArr.push("&openAll=");
            urlArr.push(settings.openAll);
            urlArr.push("&min=");
            urlArr.push(settings.min);
            urlArr.push("&max=");
            urlArr.push(settings.max);
            url = urlArr.join('');

            optionDialog = {
                id:settings.id, // iframe name or id
                cache:false, // 是否缓存窗口内容页
                content:url, //内容,其中url:开头的为加载页面
                width:settings.width, // 内容宽度
                height:settings.height, // 内容高度
                title:settings.title, // 标题
                //// old
//                maxBtn:false,
//                minBtn:false,
//                iconTitle:false,
//                btnBar:false,
//                autoSize:false, // no use
//                rang:true, // no use
                //// new
                max:false, // 是否显示最大化按钮
                min:false, // 是否显示最小化按钮
                icon:null, // 消息图标名称
                button:null, // 自定义按钮
                //// same:
                resize:false, // 是否允许用户调节尺寸
                lock:true // 是否锁屏
            };

            if (settings.W) {
                $this = settings.W.$;
                optionDialog.parent = settings.dg;
                if (settings.dataInit) {
                    optionDialog.init = function () {
                        var arr = [], dataTmp = settings.dataInit();//数组
                        // this.content 为窗口内容页value02.html页面的window对象
                        $.each(dataTmp, function (i, e) {
                            arr.push('<option value="' + e.id + '">' + e.name + '</option>');
                        });
                        $(this.content.document.getElementById('empSelected')).append(arr.join(''));
                    };
                    optionDialog.close = function () {
                        api.zindex();//关闭本窗口后置顶父窗口
                    };
                } else {
                    optionDialog.close = function () {
                        settings.callBackMethod();
                        api.zindex();//关闭本窗口后置顶父窗口
                    };
                }
            } else {
                $this = $(this);
                optionDialog.parent = this;
                if (settings.dataInit) {
                    optionDialog.init = function () {
                        var arr = [], dataTmp = settings.dataInit();//数组
                        $.each(dataTmp, function (i, e) {
                            arr.push('<option value="' + e.id + '">' + e.name + '</option>');
                        });
                        $(this.content.document.getElementById('empSelected')).append(arr.join(''));
                    };
                } else {
                    optionDialog.close = settings.callBackMethod;
                }
            }
            $this.dialog(optionDialog);

            return this.each(function () {
            });
        }
    });
})(jQuery);