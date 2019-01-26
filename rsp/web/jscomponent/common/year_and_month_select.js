YearAndMonthSelect = function (options) {
    this.defOptions = {
        year:{                                                                                                           //年份的参数
            wrapId:'wrapYear', // 被插入id
            attr:{                                                                                                //select的属性
                id:'year',
                name:'year',
                'class':'selectWidth80'
            },
            start:'2012', //开始年份
            end:'', //结束年份,如果没值为当前年份
            sort:'desc', //排序  desc,或asc
            hide:false, //隐藏
            selectValue:'', //默认为第一项 ,current  为当前年份
            showDefSelectOption:true                                                                //显示请选择
        },
        month:{                                                                                                    //月份的参数
            wrapId:'month', // 被插入id
            attr:{                                                                                            //select的属性
                id:'month',
                name:'month',
                'class':'selectWidth80'
            },
            sort:'asc', //排序   desc,或asc
            hide:true, //隐藏
            selectValue:'', //默认写数字1-12 , current  为当前月份
            showDefSelectOption:true                                                                 //显示请选择
        }
    };
    this.init(options);
}

YearAndMonthSelect.prototype = {
    init:function (options) {
        $.extend(true, this.defOptions, options);
        if (!this.defOptions.year.hide) {
            this.initYear();
        }
        if (!this.defOptions.month.hide) {
            this.initMonth();
        }
    },
    initYear:function () {
        var year = this.defOptions.year;
        $('<select>').attr(year.attr).appendTo('#' + year.wrapId);
        var yearId = year.attr.id;
        if (year.showDefSelectOption) {
            $('#' + yearId).append('<option value="">-请选择-</option>');
        }
        if (year.end == '') {
            var d = new Date();
            year.end = parseInt(d.getFullYear());
        }
        year.start = parseInt(year.start);
        if (year.selectValue == 'current') {
            var date = new Date();
            year.selectValue = date.getFullYear();
        }
        if (year.sort == 'desc') {
            for (var i = year.end; i >= year.start; i--) {
                var opt = '<option value="' + i + '"';
                if (i == year.selectValue) {
                    opt += ' selected="selected" ';
                }
                opt += ' >' + i + '</option>';
                $('#' + yearId).append(opt);
            }
        } else {
            for (var i = year.start; i <= year.end; i++) {
                var opt = '<option value="' + i + '"';
                if (i == year.selectValue) {
                    opt += ' selected="selected" ';
                }
                opt += ' >' + i + '</option>';
                $('#' + yearId).append(opt);
            }
        }
    },
    initMonth:function () {
        var month = this.defOptions.month;
        $('<select>').attr(month.attr).appendTo('#' + month.wrapId);
        var monthId = month.attr.id;
        if (month.showDefSelectOption) {
            $('#' + monthId).append('<option value="">-请选择-</option>');
        }
        if (month.selectValue == 'current') {
            var date = new Date();
            month.selectValue = date.getMonth() + 1;
        }
        if (month.sort == 'desc') {
            for (var i = 12; i >= 1; i--) {
                var opt = '<option value="' + i + '"';
                if (i == month.selectValue) {
                    opt += ' selected="selected" ';
                }
                opt += ' >' + i + '</option>';
                $('#' + monthId).append(opt);
            }
        } else {
            for (var i = 1; i <= 12; i++) {
                var opt = '<option value="' + i + '"';
                if (i == month.selectValue) {
                    opt += ' selected="selected" ';
                }
                opt += ' >' + i + '</option>';
                $('#' + monthId).append(opt);
            }
        }
    }
}