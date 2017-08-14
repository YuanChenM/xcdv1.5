/**
 * Created by wu_honglei .
 */

var $List_Grid;
var SO153113 = {
    formId:"SO153113Form",
    init : function() {
        $List_Grid = $('#SO153113_list_grid').grid({
            actionHandler:SO153113.actionHandler
        });
        //FormUtils.init(SO153113.formId,"SO153113");
    },
    actionHandler:function(rowdata,coltype,row,col){
        if (coltype == "detail") {
            var selChargingId = rowdata.selChargingId;
            $.pdialog.open("卖家交易费用明细详情", Main.contextPath + "/SO153113/detail/?selChargingId="+selChargingId, {
                resizable: false,
                width: 1000,
                height: 220
            });
        }else{
            var sellerBillId = $("#sellerBillId").val();
            $('#main-content').postUrl(Main.contextPath + "/SO153113/search/",{
                sellerBillId:sellerBillId
            });
        }

    }
}
$(document).ready(function() {
    // 初始化调用
    SO153113.init();
});
