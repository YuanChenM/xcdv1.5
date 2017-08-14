/**
 * Created by ni_shaotang on 2016/9/27.
 */
var backUrl = 'BA2141121.html';
var orderListCommon = [];
var BA2141117 = {
    init: function
        () {
        BA2141117.bindCheck();
        BA2141117.loadHouses();
    }
    ,
    loadHouses: function () {
        var url = ConstantDef.getFindBssgDetailUrl();
        var slCode = localStorage.slCode;
        var slCodeDis = localStorage.slCodeDis;
        if (slCode == "" || slCode == null) {
            window.location.href = 'BA2141101.html';
            return;
        }
        var data = {
            "siteCode":207,
            "auth":slCode,
            "loginId":slCode,
            "param": {
                "buyersId": slCode,
                "buyersCode": slCodeDis,
                "orderSource": "4",
                "pageCount": 100,
                "searchType": 100,
                "pageNo": 0,
                "paging": true
            }
        };
        console.log(url);
        console.log(slCode);
        HttpClient.post(url, data, function (data) {
            orderListCommon = data.result.orders;
            var str = "";
            var copyOrderInfo = $("#orderInfo").html();
            for (var i = 0; i < orderListCommon.length; i++) {
                str += copyOrderInfo.replace("orderId", orderListCommon[i].orderId).replace("orderId", orderListCommon[i].orderId)
                    .replace("orderAmount", orderListCommon[i].orderAmount).replace("orderCode", orderListCommon[i].orderCode)
                    .replace("orderTime", BA2141117.getLocalTime(orderListCommon[i].orderTime))
                    .replace("orderStatus", commonUtil.orderStatus(orderListCommon[i].orderStatus));
            }
            $("#orderList").html(str);
        }, function (data) {
            webToast("失败", "middle");
        });
    }
    ,
    bindCheck: function () {
        $("#fanhui").bind("touchstart", function () {
            window.location.href = backUrl;
        });
    },
    jumpInfo: function (orderId,orderCode) {
        window.location.href = "BA2141118.html?orderId="+orderId+"&orderCode="+orderCode;
    },
    getLocalTime: function (date) {
        console.log(date);
        return new Date(parseInt(date)).Format("yyyy-MM-dd hh:mm");

    }
}
//页面入口

window.onload = window.setTimeout(BA2141117.init, 500);