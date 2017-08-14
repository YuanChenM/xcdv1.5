/**
 * Created by ni_shaotang on 2016/10/10.
 */
var backUrl = 'BA2141121.html';
var orderListCommon = [];
var BA2141122 = {
    init: function
        () {
        BA2141122.bindCheck();
        BA2141122.loadHouses();
    }
    ,
    loadHouses: function () {
        var url = ConstantDef.getFindBssDetailUrl();
        var slCode = localStorage.slCode;
        if (slCode == "" || slCode == null) {
            window.location.href = 'BA2141101.html';
            return;
        }
        var data = {
            "siteCode":207,
            "auth":slCode,
            "loginId":slCode,
            "param": {
                "sellerCode": slCode,
                "pageCount": 100,
                "pageNo": 0,
                "paging": true
            }
        };
        HttpClient.post(url, data, function (data) {
            orderListCommon = data.result.orders;
            var str = "";
            var copyOrderInfo = $("#orderInfo").html();
            for (var i = 0; i < orderListCommon.length; i++) {
                str += copyOrderInfo.replace("orderId", orderListCommon[i].orderId).replace("orderId", orderListCommon[i].orderId)
                    .replace("orderAmount", orderListCommon[i].orderAmount).replace("orderCode", orderListCommon[i].orderCode)
                    .replace("orderTime", BA2141122.getLocalTime(orderListCommon[i].orderTime))
                    .replace("houseName", commonUtil.checkNull(orderListCommon[i].sellers))
                    .replace("buyersName", orderListCommon[i].buyersName)
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
        window.location.href = "BA2141123.html?orderId=" + orderId+"&orderCode="+orderCode;
    },
    getLocalTime: function (date) {
        console.log(date);
        return new Date(parseInt(date)).Format("yyyy-MM-dd hh:mm");
    }
}
//页面入口

window.onload = window.setTimeout(BA2141122.init, 500);