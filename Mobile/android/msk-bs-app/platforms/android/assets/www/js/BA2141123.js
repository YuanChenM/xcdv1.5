/**
 * Created by ni_shaotang on 2016/10/13.
 */
var backUrl = 'BA2141122.html';
var orderListCommon = [];
var orderDetCommon = [];
var BA2141123 = {

    init: function () {

        $("#fanhui").bind("touchstart", function () {
            window.location.href = backUrl;
        });
        BA2141123.loadHouses();

        var mySwiper = new Swiper('.swiper-container',{
            loop: false,
            autoHeight:true,
            onSlideChangeStart: function(swiper){
                $(".pr-menu a").removeClass("on");
                $("#item"+swiper.activeIndex).addClass("on");
            }
        });
        BA2141123.bindSelect(mySwiper);
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
    loadHouses: function () {
        var url = ConstantDef.getFindBssDetailUrl();
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
        HttpClient.post(url, data, function (data) {
            orderListCommon = data.result.orders;
            var str = "";
            if (null != orderListCommon && orderListCommon.length > 0) {
                var orders = orderListCommon[0];
                $("#districtCode").html(orders.districtCode);
                $("#orderSource").html(commonUtil.orderSource(orders.orderSource));
                $("#orderType").html(commonUtil.orderType(orders.orderType));
                $("#paymentType").html(commonUtil.paymentType(orders.paymentType));
                $("#orderAmount").html(orders.orderAmount);
                $("#invoiceFlg").html(commonUtil.isNoType(orders.invoiceFlg));
                $("#sellers").html(orders.sellers);
                $("#orderTaker").html(orders.orderTaker);
                $("#orderTime").html(BA2141123.getLocalTime(orders.orderTime));
                $("#orderStatus").html(commonUtil.orderStatus(orders.orderStatus));
                $("#buyerType").html(orders.buyerType);

                var orderBuyer = orders.receiveInfo;
                $("#buyersCode").html(orders.buyersCode);
                $("#buyersName").html(orders.buyersName);
                $("#receiverName").html(orderBuyer.receiverName);
                $("#receiverTel").html(orderBuyer.receiverTel);
                $("#receiverProvince").html(orderBuyer.receiverProvince);
                $("#receiverCity").html(orderBuyer.receiverCity);
                $("#receiverDistrict").html(orderBuyer.receiverDistrict);
                $("#receiverAddr").html(orderBuyer.receiverAddr);

                $("#receiveTime").html(orderBuyer.receiveTime);
                $("#receiveEarliest").html(orderBuyer.receiveEarliest);
                $("#receiveLast").html(orderBuyer.receiveLast);
                $("#vicFlg").html(orders.deliveryReq.vicFlg);

                orderDetCommon = orders.orderDetail;
                var detCopy = $("#detInfo").html();
                for (var i = 0; i < orderDetCommon.length; i++) {
                    var pdCode = commonUtil.checkNull(orderDetCommon[i].pdCode);
                    if (pdCode.length == 0) {
                        continue;
                    }
                    str += detCopy.replace("pdCode", pdCode)
                        .replace("pdName", commonUtil.checkNull(orderDetCommon[i].pdName))
                        .replace("normsName", commonUtil.checkNull(orderDetCommon[i].normsName))
                        .replace("weight", commonUtil.checkNull(orderDetCommon[i].weight))
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
    },
    jumpInfo: function (divId) {
        $("#orderBasic").hide();
        $("#orderBuyer").hide();
        $("#orderDistribution").hide();
        $("#orderDetailed").hide();
        $("#" + divId).show();
    },
    getLocalTime: function (date) {
        return new Date(parseInt(date)).Format("yyyy-MM-dd hh:mm");

    }
}
//页面入口

window.onload = window.setTimeout(BA2141123.init, 500);