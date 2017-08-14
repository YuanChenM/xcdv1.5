/**
 * Created by zhu_kai1 on 2016/9/28.
 */
var backUrl = "";
jQuery(document).ready(function () {
    setTimeout("BA2141114.getOrderList()",100);
    backUrl =commonUtil.QueryString("fromUrl");
    BA2141114.returnBack();
});

var BA2141114={

    returnBack:function(){
        $(".head-back").bind("touchstart",function(){
            window.location.href = backUrl;
        })
    },
    /**查看订单明细**/
    viewDetail:function(orderId,orderCode){
           window.location.href = 'BA2141115.html?fromUrl='+backUrl+'&orderId='+orderId+"&orderCode="+orderCode;
    },



    /**获取委托订单列表**/
    getOrderList:function(){
        var orderUrl = ConstantDef.getBsFindSdoByDetailUrl();
        var paramData = {
            "siteCode":207,
            "auth":"MSK00001",
            "loginId":"msk01",
            param: {
                "orderTaker":localStorage.houseCode
            }
        };
        HttpClient.post(orderUrl,paramData,function(data){
            if(data.status == "F"){
                webToast("查询订单列表失败","middle");
            }else{
                var orders = data.result.orders;
                for(var i= 0;i<orders.length;i++){
                    var obj = $("ul:eq(0)");
                    var cloneObj = obj.clone();
                    cloneObj.show();
                    var orderId = orders[i].orderId;
                    var orderCode = orders[i].orderCode;
                    cloneObj.find("span.right")[0].innerText = orderId;
                    cloneObj.find("span.right")[1].innerText = new Date(orders[i].orderTime).Format("yyyy-MM-dd hh:mm:ss");
                    cloneObj.find("span.right")[2].innerText =orders[i].orderAmount;
                    cloneObj.find("span.right")[3].innerText = orders[i].buyersName;
                    cloneObj.find("span.right")[4].innerText =commonUtil.orderStatus(orders[i].orderStatus);
                    cloneObj.find(".detailClass").attr("id","detail"+i);
                    cloneObj.find(".detailClass").attr("data-orderId",orderId);
                    cloneObj.find(".detailClass").attr("data-orderCode",orderCode);
                    cloneObj.appendTo(".lbxx-list");
                }
                $(".lbxx-list").find(".wapp-footer").each(function () {
                    $(this).on("touchstart",function () {
                        var orderId = $(this).find(".detailClass").attr("data-orderId");
                        var orderCode = $(this).find(".detailClass").attr("data-orderCode");
                        BA2141114.viewDetail(orderId,orderCode);
                    })
                })


            }
        },function(data){
            webToast("操作失败","middle");
        });

    }
}