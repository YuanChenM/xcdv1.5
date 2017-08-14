/**
 * Created by ni_shaotang on 2016/9/27.
 */
var backUrl = 'BA2141117.html';
var orderListCommon = [];
var orderDetCommon = [];
var BA2141118 = {
    init: function () {
        $("#fanhui").bind("touchstart", function () {
            window.location.href = backUrl;
        });
        BA2141118.loadHouses();
    }
    ,
    loadHouses: function () {
        var url = ConstantDef.getFindBssgDetailUrl();
        var slCode = localStorage.slCode;
        var data = {
            "siteCode": 207,
            "auth": slCode,
            "loginId": slCode,
            "param": {
                "orders": [{
                    "orderId": commonUtil.QueryString("orderId"),
                    "orderCode": commonUtil.QueryString("orderCode")
                }]
            }
        };
        console.log(url);
        HttpClient.post(url, data, function (data) {
            orderListCommon = data.result.orders;
            if (orderListCommon.length > 0) {
                var str = "";
                orderDetCommon = orderListCommon[0].orderDetail;
                var detCopy = $("#detInfo").html();
                for (var i = 0; i < orderDetCommon.length; i++) {
                    var pdCode = commonUtil.checkNull(orderDetCommon[i].pdCode);
                    if (pdCode.length == 0) {
                        continue;
                    }
                    str += detCopy.replace("pdCode", pdCode)
                        .replace("pdName", commonUtil.checkNull(orderDetCommon[i].pdName))
                        .replace("normsName", commonUtil.checkNull(orderDetCommon[i].normsName))
                        .replace("weightName", commonUtil.checkNull(orderDetCommon[i].weight))
                        .replace("pdGradeName", commonUtil.checkNull(orderDetCommon[i].pdGradeName))
                        .replace("pdTotalPrice", orderDetCommon[i].pdPrice * orderDetCommon[i].orderQty)
                        .replace("sendQty", orderDetCommon[i].sendQty)
                        .replace("receiveQty", commonUtil.checkNull(orderDetCommon[i].receiveQty))
                        .replace("cancelQty", orderDetCommon[i].cancelQty)
                        .replace("returnQty", orderDetCommon[i].returnQty)
                        .replace("rejectionQty", orderDetCommon[i].rejectionQty)
                        .replace("pdPrice", orderDetCommon[i].pdPrice).
                        replace("orderQty", orderDetCommon[i].orderQty);
                }
                $("#orderDetailedInfo").html(str);
            }
        }, function (data) {
            webToast("失败", "middle");
        });
    }
}
//页面入口

window.onload = window.setTimeout(BA2141118.init, 500);