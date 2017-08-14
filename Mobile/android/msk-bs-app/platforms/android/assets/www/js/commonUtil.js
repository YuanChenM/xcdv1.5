/**
 * Created by ni_shaotang on 2016/10/13.
 */

var commonUtil = {
    /**
     * 获取url参数
     * @type {{QueryString: Function}}
     */
    QueryString: function (val) {
        var uri = window.location.search;
        var re = new RegExp("" + val + "=([^&?]*)", "ig");
        return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
    },
    /**
     * 获取订单类型名称
     * @type {{orderStatus: Function}}
     */
    orderStatus: function (orderStatus) {
        switch (orderStatus) {
            case 1:
                return "新建";
            case 2:
                return "待付款";
            case 3:
                return "已付款";
            case 4:
                return "待审核";
            case 5:
                return "已审核";
            case 6:
                return "待分销";
            case 7:
                return "分销中";
            case 8:
                return "已确认";
            case 9:
                return "待发货";
            case 10:
                return "部分发货";
            case 11:
                return "部分收货";
            case 12:
                return "全部发货";
            case 13:
                return "全部收货";
            case 14:
                return "分销失败";
            default : "未知";
        }
    },
    /**
     * 对空数据值处理
     * @type {{checkNull: Function}}
     */
    checkNull: function (val) {
        if (val == null || val.length == 0) {
            return "";
        }else{
            if(val == "null" || val =="undefined"){
                return "";
            }else{
                return val;
            }
        }
    },
    /**
     * 订单类型处理
     * @type {{orderType: Function}}
     *  1:正常订单 2:非正常订单 3:促销订单
     */
    orderType : function(val){
        switch (val) {
            case 1:
                return "正常订单";
            case 2:
                return "非正常订单";
            case 3:
                return "促销订单";
            default : "正常订单";
        }
    },
    /**
     * 订单类型处理
     * @type {{orderType: Function}}
     *  1:正常订单 2:非正常订单 3:促销订单
     */
    orderSource : function(val){
        switch (val) {
            case 1:
                return "神农客";
            case 2:
                return "美侍客";
            case 3:
                return "微商城";
            case 4:
                return "买手APP";
            case 3:
                return "新微商城";
            default : "买手APP";
        }
    },
    /**
     * 付款类型处理
     * @type {{paymentType: Function}}
     *  1:在线支付,2:线下支付
     */
    paymentType : function(val){
        switch (val) {
            case 1:
                return "在线支付";
            case 2:
                return "线下支付";
            default : "其他渠道";
        }
    },
    /**
     * 是否处理
     * @type {{paymentType: Function}}
     *  1:是
     */
    isNoType : function(val){
        switch (val) {
            case 1:
                return "是";
            default : "否";
        }
    }

}
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}