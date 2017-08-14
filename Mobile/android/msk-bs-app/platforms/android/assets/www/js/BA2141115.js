/**
 * Created by zhu_kai1 on 2016/9/28.
 */
var orderId;
var orderCode;
var backUrl = "";
jQuery(document).ready(function () {
    setTimeout("BA2141115.getOrderDetail()",100);
    BA2141115.returnBack();
    orderId = BA2141115.QueryString("orderId");
    orderCode = BA2141115.QueryString("orderCode");
    backUrl = "BA2141114.html?fromUrl="+BA2141115.QueryString("fromUrl");
    alert(backUrl);
});

var BA2141115={

    initTabHeight : function () {
        $(".swiper-slide").each(function () {
            BA2141115.setHeight($(this));
        })
    },

    returnBack:function(){
        $(".head-back").bind("touchstart",function(){
            window.location.href = backUrl;
        })
    },
    //菜单类别选择
    bindSelect : function (mySwiper) {
        $(".pr-menu a").each(function (index) {
            $(this).bind("touchstart",function () {
                mySwiper.slideTo(index, 0, false);
                $(".pr-menu a").removeClass("on");
                $(this).addClass("on");
            })
        })
    },

    QueryString: function (val) {
        var uri = window.location.search;
        var svalue =uri.match(new RegExp("[\?\&]" + val + "=([^\&]*)(\&?)","i"));
        return svalue ? svalue[1] : svalue;
    },

    checkUndefined : function (obj) {
        if(obj){
            return obj;
        }
        return "";
    },


getOrderDetail : function () {
        var detailUrl = ConstantDef.getBsFindSdoByDetailUrl();
        var orders = new Array();
        var order = new Object();
        order.orderId = orderId;
        order.orderCode = orderCode;
        orders.push(order);
        var paramData = {
            "siteCode":207,
            "auth":"MSK00001",
            "loginId":"msk01",
            param: {
                "orders":orders
            }
        };
        HttpClient.post(detailUrl,paramData,function(data){
            if(data.status == "F"){
                webToast("查询订单详情失败","middle");
            }else{
                var orders = data.result.orders;
                var detail = orders[0];
                //订单信息--begin
                var orderInfo = $(".wtxq-list ul:eq(0) li span");
                orderInfo[0].innerText = BA2141115.checkUndefined(detail.districtName);
                orderInfo[1].innerText  = BA2141115.checkUndefined(detail.orderSourceName);
                orderInfo[2].innerText  = BA2141115.checkUndefined(detail.orderTypeName);
                orderInfo[3].innerText  = BA2141115.checkUndefined(detail.paymentTypeName);
                orderInfo[4].innerText  = BA2141115.checkUndefined(detail.orderAmount);
                if(detail.invoiceFlg){
                    if(detail.invoiceFlg == 1){
                        orderInfo[5].innerText  = "是";
                    }else {
                        orderInfo[5].innerText  = "否";
                    }
                }
                orderInfo[6].innerText  = BA2141115.checkUndefined(detail.sellers);
                orderInfo[7].innerText  = BA2141115.checkUndefined(detail.orderTaker);
                if(detail.orderTime){
                    orderInfo[8].innerText  = new Date(detail.orderTime).Format("yyyy-MM-dd hh:mm:ss");
                }
                var orderStatus = ["新建","待付款","已付款","待审核","已审核","待分销",
                    "分销中","已确认","待发货","部分发货","部分收货","全部发货","全部收货","分销失败"];
                if(detail.orderStatus && detail.orderStatus > 0 && detail.orderStatus <= orderStatus.length){
                    orderInfo[9].innerText = orderStatus[detail.orderStatus -1];
                }
                //订单信息--end
                //买家信息--begin
                var buyerInfo = $(".wtxq-list ul:eq(1) li span");
                buyerInfo[0].innerText = BA2141115.checkUndefined(detail.buyersCode);
                buyerInfo[1].innerText = BA2141115.checkUndefined(detail.buyersName);
                buyerInfo[2].innerText = BA2141115.checkUndefined(detail.buyerType);
                buyerInfo[3].innerText = BA2141115.checkUndefined(detail.receiveInfo.receiverName);
                buyerInfo[4].innerText = BA2141115.checkUndefined(detail.receiveInfo.receiverTel);
                buyerInfo[5].innerText = BA2141115.checkUndefined(detail.receiveInfo.receiverProvince);
                buyerInfo[6].innerText = BA2141115.checkUndefined(detail.receiveInfo.receiverCity);
                buyerInfo[7].innerText = BA2141115.checkUndefined(detail.receiveInfo.receiverDistrict);
                buyerInfo[8].innerText = BA2141115.checkUndefined(detail.receiveInfo.receiverAddr);
                //买家信息--end
                //配送信息--begin
                var distractInfo = $(".wtxq-list ul:eq(2) li span");
                distractInfo[0].innerText = BA2141115.checkUndefined(detail.receiveInfo.receiveTime);
                distractInfo[1].innerText = BA2141115.checkUndefined(detail.receiveInfo.receiveEarliest);
                distractInfo[2].innerText = BA2141115.checkUndefined(detail.receiveInfo.receiveLast);
                if(detail.orderSendTime){
                    distractInfo[3].innerText  = new Date(detail.orderSendTime).Format("yyyy-MM-dd hh:mm:ss");
                }


                if(detail.deliveryReq.vicFlg){
                    if(detail.deliveryReq.vicFlg == 1){
                        distractInfo[4].innerText = "需要";
                    }else {
                        distractInfo[4].innerText = "不需要";
                    }
                }
                //配送信息--end
                //订单明细--begin
                var orderDetail = detail.orderDetail;

                for(var i=0;i<orderDetail.length;i++){
                    var cloneLi = $("#product ul li");
                    var cloneObj;
                    if(orderDetail && orderDetail.length > 0){
                        cloneObj = cloneLi.clone();
                    }
                    cloneObj.find("span")[0].innerText = BA2141115.checkUndefined(orderDetail[i].pdCode);
                    cloneObj.find("span")[1].innerText = BA2141115.checkUndefined(orderDetail[i].pdName);
                    cloneObj.find("span")[2].innerText = BA2141115.checkUndefined(orderDetail[i].normsName);
                    cloneObj.find("span")[3].innerText = BA2141115.checkUndefined(orderDetail[i].weightName);
                    cloneObj.find("span")[4].innerText = BA2141115.checkUndefined(orderDetail[i].pdGradeName);
                    cloneObj.find("span")[5].innerText = BA2141115.checkUndefined(orderDetail[i].pdPrice);
                    cloneObj.find("span")[6].innerText = BA2141115.checkUndefined(orderDetail[i].pdTotalPrice);
                    cloneObj.find("span")[7].innerText = BA2141115.checkUndefined(orderDetail[i].orderQty);
                    cloneObj.find("span")[8].innerText = BA2141115.checkUndefined(orderDetail[i].sendQty);
                    cloneObj.find("span")[9].innerText = BA2141115.checkUndefined(orderDetail[i].cancelQty);
                    cloneObj.find("span")[10].innerText = BA2141115.checkUndefined(orderDetail[i].returnQty);
                    cloneObj.find("span")[11].innerText = BA2141115.checkUndefined(orderDetail[i].rejectionQty);
                    $(".wtxq-list ul:eq(3)").append(cloneObj);
                    if(i != orderDetail.length -1){
                        $(".wtxq-list ul:eq(3) li:last-child").css("border-bottom", "10px solid #e6e6e6");
                    }
                }
                //订单明细--end
            }
            BA2141115.initTool();
        },function(){
            webToast("操作失败","middle");
            BA2141115.initTool();
        });

    },
    initTool:function () {
        setTimeout("BA2141115.initTabHeight()",500);
        setTimeout("BA2141115.initSwiper()",500);
    },

    initSwiper : function () {
        var mySwiper = new Swiper('.swiper-container',{
            loop: false,
            autoHeight:true,
            onSlideChangeStart: function(swiper){
                $(".pr-menu a").removeClass("on");
                $("#item"+swiper.activeIndex).addClass("on");
            }
        });
        BA2141115.bindSelect(mySwiper);
    },

    setHeight : function (obj) {
        var height = obj.height();
        var totalHeight = $("body").height();
        var head = $(".wapp-head").height();
        var menu = $(".pr-menu").height();
        totalHeight = totalHeight - head - menu;
        if(height < totalHeight){
            obj.css("height",totalHeight+"px");
        }
    }



}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) {
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