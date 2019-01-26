/**
 *  二级级联
 * @todo
 * @version
 * @created  12-4-13 上午10:08
 * @author jameson.fang
 *
 */
//jQuery.reportType = {
//    default:{
//
//    },
//    init:function(optons){
//        $.extend(this.default,optons);
//        alert('json:'+JSON.stringify(this.default));
//    }
//}
//jQuery.fn.extend(
//    {
//        reportType : jQuery.reportType.init
//    }
//);
Linkage  = function(options){
    this.defOption={
        length:2,                                                                                                  //长度层级
        level1:{
            attr:{
                id:'level1',                                                                                       //select的属性
                name:'level1',
                'class':'selectWidth140'
            },
            selectValue:'',                                                                                          //默认被选择
            wrapId:'wrap1',                                                                                  //第一级的被插入id
            url:'sysDictAction.do?method=showLevelJson',                          //请求路径
            data:{},                                                                                                 //默认请求
            hide:false,                                                                                               //是否在没查到的情况下隐藏
            showDefSelectOption:true,                                                                //显示请选择
            readOnly:false,
            callbackFn:function(key,value){}                                                      //回调函数 在成功后调用key:选项值 value:文本显示
        },
        level2:{
            attr:{
                id:'level2',
                name:'level2',
                'class':'selectWidth140'
            },
            selectValue:'',
            wrapId:'wrap2',                                                                                  //第二级的被插入id
            url:'sysDictAction.do?method=showLevelJson',
            data:{},
            hide:false,
            showDefSelectOption:true,                                                                //显示请选择
            readOnly:false,
            callbackFn:function(key,value){}
        },
        level3:{
            attr:{
                id:'level3',
                name:'level3',
                'class':'selectWidth140'
            },
            selectValue:'',
            wrapId:'wrap3',                                                                                  //第三级的被插入id
            url:'sysDictAction.do?method=showLevelJson',
            data:{},
            hide:false,
            showDefSelectOption:true,                                                                //显示请选择
            readOnly:false,
            callbackFn:function(key,value){}
        },
        level4:{
            attr:{
                id:'level4',
                name:'level4',
                'class':'selectWidth140'
            },
            selectValue:'',
            wrapId:'wrap4',                                                                                  //第三级的被插入id
            url:'sysDictAction.do?method=showLevelJson',
            data:{},
            hide:false,
            showDefSelectOption:true,                                                                //显示请选择
            readOnly:false,
            callbackFn:function(key,value){}
        }
    };
    this.cache={};
    this.result={
        level1:{
            key:'',
            value:''
        },
        level2:{
            key:'',
            value:''
        },
        level3:{
            key:'',
            value:''
        },
        level4:{
            key:'',
            value:''
        }
    }
    this.init(options)
}
Linkage.prototype = {
    init:function (options) {
        var _self = this;
        top.log.info('init');
        $.extend(true, this.defOption, options);
        $.ajax({
            url:this.defOption.level1.url,
            data:this.defOption.level1.data,
            type:'POST',
            dataType:'json',
            success:function (responseData) {
                _self.drawLevel1Select(responseData.data[0]);
            }
        });
    },
    drawLevel1Select:function (data) {
        var _self = this;
        this.cache = data;
        $('<select>').attr(this.defOption.level1.attr).appendTo('#' + this.defOption.level1.wrapId);
        var level1Id = this.defOption.level1.attr.id;
        if(this.defOption.level1.showDefSelectOption){
            $('#'+level1Id).append('<option value="">-请选择-</option>');
        }
        if(this.defOption.level1.hide&&this.cache.length<1){
            $('#'+this.defOption.level1.attr.id).val('').hide();
        }
        for (var i = 0; i < this.cache.length; i++) {
            if (this.cache[i]['key'] != undefined || this.cache[i]['value'] != undefined) {
                $('<option value="' + this.cache[i]['key'] + '">' + this.cache[i]['value'] + '</option>').appendTo('#' + level1Id);
            }
        }
        this.initBindLevel1();

        //initLevel2
        if(this.defOption.length>1){
            $('<select>').attr(this.defOption.level2.attr).appendTo('#' + this.defOption.level2.wrapId);
            if(this.defOption.level2.showDefSelectOption){
                 $('#'+this.defOption.level2.attr.id).append('<option value="">-请选择-</option>');
            }
            if(this.defOption.level2.hide){
                $('#'+this.defOption.level2.attr.id).val('').hide();
            }
            this.initBindLevel2();

        }
        //initLevel3
        if(this.defOption.length>2){
            $('<select>').attr(this.defOption.level3.attr).appendTo('#' + this.defOption.level3.wrapId);
            if(this.defOption.level3.showDefSelectOption){
                $('#'+this.defOption.level3.attr.id).append('<option value="">-请选择-</option>');
            }
            if(this.defOption.level3.hide){
                $('#'+this.defOption.level3.attr.id).val('').hide();
            }
            this.initBindLevel3();
        }
        //initLevel4
        if(this.defOption.length>3){
            $('<select>').attr(this.defOption.level4.attr).appendTo('#' + this.defOption.level4.wrapId);
            if(this.defOption.level4.showDefSelectOption){
                $('#'+this.defOption.level4.attr.id).append('<option value="">-请选择-</option>');
            }
            if(this.defOption.level4.hide){
                $('#'+this.defOption.level4.attr.id).val('').hide();
            }
            this.initBindLevel4();
        }
        if($.browser.msie && $.browser.version=="6.0"&& !$.support.style) {
            setTimeout(function(){
                top.log.info('level1');
                $('#'+_self.defOption.level1.attr.id).children().each(function () {
                    if (this.value == _self.defOption.level1.selectValue) {
                        this.selected = "true";
                    }
                });
                $('#'+_self.defOption.level1.attr.id).trigger("change");
                _self.defOption.level1.selectValue='';
            },1);
        }else{
            $('#'+this.defOption.level1.attr.id).val(this.defOption.level1.selectValue).trigger("change");
            this.defOption.level1.selectValue='';
        }
    },
    initBindLevel1:function () {
        var _self = this;
        var level=_self.defOption.level1;
        if(level.readOnly){                                   //如果为只读方式
            if($('#'+level.attr.id+'_readOnly').val()!=undefined){                   //能找到
                $('#'+level.attr.id+'_readOnly').next().remove();
                $('#'+level.attr.id+'_readOnly').remove();
                $('#'+level.attr.id).show();
            }
        }
        $('#' + this.defOption.level1.attr.id).change(function(){
            top.log.info('load initBindLevel1');
            var key = $(this).val();
            var val = $(this).find("option:selected").text();
            _self.result.level1['key']=key;
            _self.result.level1['value']=val;
            if(key!=''){
//                $('#'+_self.defOption.level2.attr.id).remove();                                    //删除自己
                level.callbackFn(_self.result.level1.key,_self.result.level1.value);                    //一级选择回调
                if(level.readOnly){
//                    $('#'+_self.defOption.level1.attr.id).remove();
//                    $('#'+level.attr.id).val(key);
                    $('#'+level.attr.id+' option[value="'+key+'"]').attr("selected", true);
                    $('#'+level.attr.id).val('').hide();
                    $('<input type="hidden">').val(key).attr(level.attr).attr('id',$('#'+level.attr.id).attr('id')+'_readonly').appendTo('#' + level.wrapId);
                    $('#' +level.wrapId).append('<span>&nbsp;&nbsp;'+val+'&nbsp;&nbsp;</span>');
                }
                if(_self.defOption.length<2)return;                             //一级 没有联动
                //清空数据
                if(_self.defOption.level2.showDefSelectOption){                                                     //需要请选择
                    var $op2=$('#'+_self.defOption.level2.attr.id+' option:first');
                    $('#'+_self.defOption.level2.attr.id).empty().append($op2);
                }else{
                    $('#'+_self.defOption.level2.attr.id).empty();
                }
                if(_self.defOption.level2.hide){
                    $('#'+_self.defOption.level2.attr.id).val('').hide();
                }
                if(_self.defOption.level3.showDefSelectOption){
                    var $op3=$('#'+_self.defOption.level3.attr.id+' option:first');
                    $('#'+_self.defOption.level3.attr.id).empty().append($op3);
                }else{
                    $('#'+_self.defOption.level3.attr.id).empty();
                }
                if(_self.defOption.level3.hide){
                    $('#'+_self.defOption.level3.attr.id).val('').hide();
                }
                if(_self.defOption.level4.showDefSelectOption){
                    var $op4=$('#'+_self.defOption.level4.attr.id+' option:first');
                    $('#'+_self.defOption.level4.attr.id).empty().append($op4);
                }else{
                    $('#'+_self.defOption.level4.attr.id).empty();
                }
                if(_self.defOption.level4.hide){
                    $('#'+_self.defOption.level4.attr.id).val('').hide();
                }

                _self.cache[_self.result.level1.key]= _self.cache[_self.result.level1.key]||[];
                if(_self.cache[_self.result.level1.key]['level1List']!=undefined){
                    if(_self.cache[_self.result.level1.key]['level1List'].length>0){
                        _self.redrawLevel2Select();
                    }
                }else{
                    _self.defOption.level1.data['key']  =key;
                    $.ajax({
                        url:_self.defOption.level2.url,
                        data:_self.defOption.level1.data,
                        type:'POST',
                        dataType:'json',
                        success:function (responseData) {
                            _self.cache[_self.result.level1.key]['level1List'] = responseData.data[0];
                            if(_self.cache[_self.result.level1.key]['level1List'].length>0){
                                _self.redrawLevel2Select();
                            }
                        }
                    });
                }
            }else{
                if(_self.defOption.level2.hide){
                    $('#'+_self.defOption.level2.attr.id).val('').hide();
                }
                if(_self.defOption.level3.hide){
                    $('#'+_self.defOption.level3.attr.id).val('').hide();
                }
                if(_self.defOption.level4.hide){
                    $('#'+_self.defOption.level4.attr.id).val('').hide();
                }
            }
        });
    },
    redrawLevel2Select:function(){
        var _self = this;
        var data = this.cache[this.result.level1.key]['level1List'];
        if(data.length>0){
            $('#' + this.defOption.level2.attr.id).show();
        }else{
            if(!this.defOption.level2.hide){
                $('#' + this.defOption.level2.attr.id).val('').hide();
            }
        }
        for (var i = 0; i < data.length; i++) {
            if (data[i]['key'] != undefined || data[i]['value'] != undefined) {
                $('<option value="' + data[i]['key'] + '">' + data[i]['value'] + '</option>').appendTo('#' + this.defOption.level2.attr.id);
            }
        }
        if(this.defOption.level2.selectValue!=''){
//            top.log.info('rLevel2Select');
            if($.browser.msie && $.browser.version=="6.0"&& !$.support.style) {
                setTimeout(function(){
                    $('#'+_self.defOption.level2.attr.id).children().each(function () {
                        if (this.value == _self.defOption.level2.selectValue) {
                            this.selected = "true";
                        }
                    });
                    $('#'+_self.defOption.level2.attr.id).trigger("change");
                    _self.defOption.level2.selectValue='';
                },1);
            }else{
                $('#'+this.defOption.level2.attr.id).val(this.defOption.level2.selectValue).trigger("change");
                this.defOption.level2.selectValue='';
            }
        }
    },
    initBindLevel2:function(){
        var _self = this;
        var level = this.defOption.level2;
        if(level.attr.readOnly){                                   //如果为只读方式
            if($('#'+level.attr.id+'_readOnly').val()!=undefined){                   //能找到
                $('#'+level.attr.id+'_readOnly').next().remove();
                $('#'+level.attr.id+'_readOnly').remove();
                $('#'+level.attr.id).show();
            }
        }
        $('#' + level.attr.id).change(function(){
//            top.log.info('level2 load');
            var key = $(this).val();
            var val = $(this).find("option:selected").text();
            _self.result.level2['key']=key;
            _self.result.level2['value']=val;
            if(key!=''){
//                $('#'+_self.defOption.level2.attr.id).remove();                                    //删除自己
                level.callbackFn(key,val);                                           //一级选择回调
                if(level.readOnly){
//                    $('#'+_self.defOption.level1.attr.id).remove();
                    $('#'+level.attr.id+' option[value="'+key+'"]').attr("selected", true);
                    $('#'+level.attr.id).val('').hide();
                    $('<input type="hidden">').val(key).attr(level.attr).attr('id',$('#'+level.attr.id).attr('id')+'_readonly').appendTo('#' + level.wrapId);
                    $('#' +level.wrapId).append('<span>&nbsp;&nbsp;'+val+'&nbsp;&nbsp;</span>');
                }
                if(_self.defOption.length<3)return;                             //二级联动
                if(_self.defOption.level3.showDefSelectOption){                                                       //保留第三级
                    var $op3=$('#'+_self.defOption.level3.attr.id+' option:first');
                    $('#'+_self.defOption.level3.attr.id).empty().append($op3);
                }else{
                    $('#'+_self.defOption.level3.attr.id).empty();
                }
                if(_self.defOption.level3.hide){
                    $('#'+_self.defOption.level3.attr.id).val('').hide();
                }
                if(_self.defOption.level4.showDefSelectOption){                                                       //保留第四级
                    var $op4=$('#'+_self.defOption.level4.attr.id+' option:first');
                    $('#'+_self.defOption.level4.attr.id).empty().append($op4);
                }else{
                    $('#'+_self.defOption.level4.attr.id).empty();
                }
                if(_self.defOption.level4.hide){
                    $('#'+_self.defOption.level4.attr.id).val('').hide();
                }
//                $('#'+_self.defOption.level3.attr.id).empty();
                _self.cache[_self.result.level2.key]= _self.cache[_self.result.level2.key]||[];
                if(_self.cache[_self.result.level2.key]['level2List']!=undefined){
                    if(_self.cache[_self.result.level2.key]['level2List'].length>0){
                        _self.redrawLevel3Select();
                    }
                }else{
                    _self.defOption.level2.data['key']  =key;
                    $.ajax({
                        url:_self.defOption.level3.url,
                        data:_self.defOption.level2.data,
                        type:'POST',
                        dataType:'json',
                        success:function (responseData) {
                            _self.cache[_self.result.level2.key]['level2List'] = responseData.data[0];
                            if(_self.cache[_self.result.level2.key]['level2List'].length>0){
                                _self.redrawLevel3Select();
                            }
                        }
                    });
                }
            }else{
                if(_self.defOption.level3.hide){
                    $('#'+_self.defOption.level3.attr.id).val('').hide();
                }
                if(_self.defOption.level4.hide){
                    $('#'+_self.defOption.level4.attr.id).val('').hide();
                }
            }
        });
    },
    redrawLevel3Select:function(){
        var _self = this;
        var data = this.cache[this.result.level2.key]['level2List'];
        if(data.length>0){
            $('#' + this.defOption.level3.attr.id).show();
        }else{
            if(!this.defOption.level3.hide){
                $('#' + this.defOption.level3.attr.id).val('').hide();
            }
        }
        for (var i = 0; i < data.length; i++) {
            if (data[i]['key'] != undefined || data[i]['value'] != undefined) {
                $('<option value="' + data[i]['key'] + '">' + data[i]['value'] + '</option>').appendTo('#' + this.defOption.level3.attr.id);
            }
        }
        if(this.defOption.level3.selectValue!=''){
//            top.log.info('rLevel3Select');
            if($.browser.msie && $.browser.version=="6.0"&& !$.support.style) {
                setTimeout(function(){
                    $('#'+_self.defOption.level3.attr.id).children().each(function () {
                        if (this.value == _self.defOption.level3.selectValue) {
                            this.selected = "true";
                        }
                    });
                    $('#'+_self.defOption.level3.attr.id).trigger("change");
                    _self.defOption.level3.selectValue='';
                },1);
            }else{
                $('#'+this.defOption.level3.attr.id).val(this.defOption.level3.selectValue).trigger("change");
                this.defOption.level3.selectValue='';
            }
        }
    },
    initBindLevel3:function(){
        var _self = this;
        var level = this.defOption.level3;
        if(level.attr.readOnly){                                   //如果为只读方式
            if($('#'+level.attr.id+'_readOnly').val()!=undefined){                   //能找到
                $('#'+level.attr.id+'_readOnly').next().remove();
                $('#'+level.attr.id+'_readOnly').remove();
                $('#'+level.attr.id).show();
            }
        }
        $('#' + level.attr.id).change(function(){
//            top.log.info('level2 load');
            var key = $(this).val();
            var val = $(this).find("option:selected").text();
            _self.result.level3['key']=key;
            _self.result.level3['value']=val;
            if(key!=''){
//                $('#'+_self.defOption.level2.attr.id).remove();                                    //删除自己
                level.callbackFn(key,val);                                           //一级选择回调
                if(level.readOnly){
//                    $('#'+_self.defOption.level1.attr.id).remove();
                    $('#'+level.attr.id+' option[value="'+key+'"]').attr("selected", true);
                    $('#'+level.attr.id).val('').hide();
                    $('<input type="hidden">').val(key).attr(level.attr).attr('id',$('#'+level.attr.id).attr('id')+'_readonly').appendTo('#' + level.wrapId);
                    $('#' +level.wrapId).append('<span>&nbsp;&nbsp;'+val+'&nbsp;&nbsp;</span>');
                }
                if(_self.defOption.length<4)return;                             //二级联动
                if(_self.defOption.level4.showDefSelectOption){                                                       //保留第四级
                    var $op4=$('#'+_self.defOption.level4.attr.id+' option:first');
                    $('#'+_self.defOption.level4.attr.id).empty().append($op4);
                }else{
                    $('#'+_self.defOption.level4.attr.id).empty();
                }
                if(_self.defOption.level4.hide){
                    $('#'+_self.defOption.level4.attr.id).val('').hide();
                }
//                $('#'+_self.defOption.level3.attr.id).empty();
                _self.cache[_self.result.level3.key]= _self.cache[_self.result.level3.key]||[];
                if(_self.cache[_self.result.level3.key]['level3List']!=undefined){
                    if(_self.cache[_self.result.level3.key]['level3List'].length>0){
                        _self.redrawLevel4Select();
                    }
                }else{
                    _self.defOption.level3.data['key']  =key;
                    $.ajax({
                        url:_self.defOption.level4.url,
                        data:_self.defOption.level3.data,
                        type:'POST',
                        dataType:'json',
                        success:function (responseData) {
                            _self.cache[_self.result.level3.key]['level3List'] = responseData.data[0];
                            if(_self.cache[_self.result.level3.key]['level3List'].length>0){
                                _self.redrawLevel4Select();
                            }
                        }
                    });
                }
            }else{
                if(_self.defOption.level4.hide){
                    $('#'+_self.defOption.level4.attr.id).val('').hide();
                }
            }
        });
    },
    redrawLevel4Select:function(){
//        var $op2=$('#'+this.defOption.level3.attr.id+' option:first');    //清空第三级
//        $('#'+this.defOption.level3.attr.id).empty().append($op2);
        var _self = this;
        var data = this.cache[this.result.level3.key]['level3List'];
        if(data.length>0){
            $('#' + this.defOption.level4.attr.id).show();
        }else{
            if(!this.defOption.level4.hide){
                $('#' + this.defOption.level4.attr.id).val('').hide();
            }
        }
        for (var i = 0; i < data.length; i++) {
            if (data[i]['key'] != undefined || data[i]['value'] != undefined) {
                $('<option value="' + data[i]['key'] + '">' + data[i]['value'] + '</option>').appendTo('#' + this.defOption.level4.attr.id);
            }
        }
        if(this.defOption.level4.selectValue!=''){
            if($.browser.msie && $.browser.version=="6.0"&& !$.support.style) {
                setTimeout(function(){
                    $('#'+_self.defOption.level4.attr.id).children().each(function () {
                        if (this.value == _self.defOption.level4.selectValue) {
                            this.selected = "true";
                        }
                    });
                    $('#'+_self.defOption.level4.attr.id).trigger("change");
                    _self.defOption.level4.selectValue='';
                },1);
            }else{
                $('#'+this.defOption.level4.attr.id).val(this.defOption.level4.selectValue).trigger("change");
                this.defOption.level4.selectValue='';
            }
        }
    },
    initBindLevel4:function(){
        var _self = this;
        var level = this.defOption.level4;
        if(level.attr.readOnly){                                   //如果为只读方式
            if($('#'+level.attr.id+'_readOnly').val()!=undefined){                   //能找到
                $('#'+level.attr.id+'_readOnly').next().remove();
                $('#'+level.attr.id+'_readOnly').remove();
                $('#'+level.attr.id).show();
            }
        }
        $('#' + level.attr.id).change(function(){
            var key = $(this).val();
            var val = $(this).find("option:selected").text();
            _self.result.level4['key']=key;
            _self.result.level4['value']=val;
            if(key!=''){
//                $('#'+_self.defOption.level2.attr.id).remove();                                    //删除自己
                level.callbackFn(key,val);                                           //一级选择回调
                if(level.readOnly){
//                    $('#'+_self.defOption.level1.attr.id).remove();
                    $('#'+level.attr.id+' option[value="'+key+'"]').attr("selected", true);
                    $('#'+level.attr.id).val('').hide();
                    $('<input type="hidden">').val(key).attr(level.attr).attr('id',$('#'+level.attr.id).attr('id')+'_readonly').appendTo('#' + level.wrapId);
                    $('#' +level.wrapId).append('<span>&nbsp;&nbsp;'+val+'&nbsp;&nbsp;</span>');
                }
            }
        });
    }
}