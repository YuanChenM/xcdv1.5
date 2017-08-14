/**
 * 订单发货详情
 */

var SO151510 = {
    initDataGrid : function(){
        var ORDER_ID=$('#orderId').val();
        var SUBORDER_ID=$("#subOrderId").val();
        $("#baseorder").postUrl(Main.contextPath+"/so/baseorder/init/"+ORDER_ID+"/" +SUBORDER_ID);
        $("#orderbuyers").postUrl(Main.contextPath+"/so/buyers/init/"+ORDER_ID);
        $("#basedelivery").postUrl(Main.contextPath+"/so/delivery/init/"+ORDER_ID);
        $("#actualdelivery").postUrl(Main.contextPath+"/so/delivery/actual/init/"+ORDER_ID+"/" +SUBORDER_ID);
        $("img[name='cancel']").bind("click", function() {
            var shipId = $(this).attr("shipId");
            var shipStatus = $(this).attr("shipStatus");
            $.alertMessage.confirm("是否确定取消该发货单？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath+"/SO151510/cancel",{shipId:shipId, orderId:$("#orderId").val(),subOrderId:$("#subOrderId").val()},function(){
                });
            });
        });
        $('.tree').treegrid();
    }
}
$(document).ready(function() {
    // 初始化调用
    SO151510.initDataGrid();
});