/**
 * Created by wang_shuai on 2016/8/3.
 */
var SO251107 = {

    init : function() {

        var returnId = $("#returnId").val();
        var orderId = $("#orderId").val();
        $("#baseReturnOrder").postUrl(Main.contextPath+"/so/returnOrder/init/"+returnId);
        $("#returnBuyers").postUrl(Main.contextPath+"/so/buyers/init/"+orderId,null,null,{async:false});
        $("img[name='SO251107']").bind("click", function() {
            $.pdialog.open("退货单修改信息",Main.contextPath + "/so/SO15150701/init/",{resizable:false, width:350, height:200},{returnDetailId:$(this).attr("returnDetailId"),pdCode:$(this).attr("pdCode"),pdName:$(this).attr("pdName"),returnQty:$(this).attr("returnQty"),returnId:returnId,orderId:orderId});
        });

    }
}
$(document).ready(function() {
    // 初始化调用
    SO251107.init();
    $("#returnBuyers").children("div").children("h3").children("label").text("退货人信息");

});