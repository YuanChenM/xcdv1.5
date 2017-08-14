/**
 * 生产商入库单列表JS
 *
 * @author liu_yan2 2016-07-04
 */
var $List_Grid;
var SSC11309 = {
    formId:"SSC11309Form",
    init: function () {
        $List_Grid = $('#SSC11309_list_grid').grid({
            actionHandler: SSC11309.actionHandler
        });
    },
    /*跳转到详细页面*/
    actionHandler:function(rowdata,coltype,row,col){
        if (coltype == "detail") {
            $('#main-content').postUrl(Main.contextPath + "/SSC11310/init/" + $('#type').val(), {
                "intoId": rowdata.intoId,
                "intoCode": rowdata.intoCode,
                "supplierName": rowdata.supplierName,
                "purchaserName": rowdata.purchaserName,
                "arriveWarehouse": rowdata.arriveWarehouse,
                "deliveryWarehouse": rowdata.deliveryWarehouse,
                "contractCode": rowdata.contractCode,
                "contractName": rowdata.contractName,
                "expectArriveDate": rowdata.expectArriveDate,
                "realArriveDate": rowdata.realArriveDate,
                "deliveryCode": rowdata.deliveryCode,
                "intoType": rowdata.intoType,
                "remark": rowdata.remark
            });
        }
    }
}


$(document).ready(function () {
    // 初始化调用
    SSC11309.init();
});

