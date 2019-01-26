/**
 *
 * @todo
 * @version
 * @created  12-3-20 上午9:40
 * @author jameson.fang
 *
 */
String.prototype.startsWith = function (str) {
    return (this.match("^" + str) == str);
}

String.prototype.toBool = function () {
    if (this == 'true' || this == '1') {
        return true;
    } else {
        return false;
    }
}
String.prototype.trimName = function () {
    if (this == undefined || this == '') {
        return '';
    }
    return this.split('|')[0].substring(1);
}
String.prototype.toMoneyFixed = function (n) {
    if (this != undefined && this != '' && !isNaN(this)) {
        return parseFloat(this).toFixed(n);
    } else {
        return this;
    }
}
Date.prototype.pattern = function (fmt) {
    var o = {
        "M+":this.getMonth() + 1, //月份
        "d+":this.getDate(), //日
        "h+":this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
        "H+":this.getHours(), //小时
        "m+":this.getMinutes(), //分
        "s+":this.getSeconds(), //秒
        "q+":Math.floor((this.getMonth() + 3) / 3), //季度
        "S":this.getMilliseconds() //毫秒
    };
    var week = {
        "0":"\u65e5",
        "1":"\u4e00",
        "2":"\u4e8c",
        "3":"\u4e09",
        "4":"\u56db",
        "5":"\u4e94",
        "6":"\u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "") + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};
String.prototype.dateFormat = function (pattern) {
    return (new Date(Date.parse(this.replace(/-/g, "/")))).pattern(pattern);
};
String.prototype.formatDate = function (year, month, date) {
    month = month + 1;
    if (month < 10) month = '0' + month;
    if (date < 10) date = '0' + date;
    return year + '-' + month + '-' + date;
};