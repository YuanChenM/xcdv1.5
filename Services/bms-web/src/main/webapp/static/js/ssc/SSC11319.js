/**
 * 资金池一览JS
 *
 * @author yang_yang
 */
var $List_Grid;
var SSC11319 = {
    init: function () {
        $List_Grid = $('#SSC11319_list_grid').grid({
            linkHandler: SSC11319.linkHandler,
            actionHandler: SSC11319.actionHandler
        });
        SSC11319.closeDate();
        this.bindButton();
        this.bindDatePicker($("#remitTimeStart"));
        this.bindDatePicker($("#remitTimeEnd"));
        Main.hlLeftMainMenu("SSC11319");
    },
    bindDatePicker : function(timeId){
        $(timeId).datepicker({
            showButtonPanel: true,
            dateFormat: "yy-mm-dd",
            closeText: '清除'
        });
        $(timeId).attr("readonly","readonly");
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    linkHandler: function (rowdata, colname, row, col) {
        if (colname == "paymentStr") {
            //0 预付款 1 进度款 2 余款
            if(rowdata.paymentType == 0){
                Main.detailWindow(Main.contextPath + "/SSC11304/show/" , {
                    "contractId": rowdata.contractId,
                    "paymentId": rowdata.paymentId
                },"合同详细");
            }
            if(rowdata.paymentType == 1){
                Main.detailWindow(Main.contextPath + "/SSC11306/show/" , {
                    "deliveryId": rowdata.deliveryId,
                    "paymentId": rowdata.paymentId
                },"发货订单详细");
            }
            if(rowdata.paymentType == 2){
                Main.detailWindow(Main.contextPath + "/SSC11322/init/" , {
                    "paymentId": rowdata.verificationId,
                    "verificationId": rowdata.verificationId
                },"核销单详细");
            }
        }
        if (colname == "contractCode") {
            Main.detailWindow(Main.contextPath + "/SSC11304/show/", {
                "contractId": rowdata.contractId,
                "paymentId": rowdata.paymentId
            },"合同详细");
        }
    },
    actionHandler: function (rowdata, coltype, row, col) {
        //跳转详情页面
        if(coltype == "detail"){
            Main.detailWindow(Main.contextPath + "/SSC11320/init/" , {
               "paymentId":rowdata.paymentId
            },"资金池详细");
        }
        //删除
        if (coltype == "delete") {
            $('#main-content').postUrl(Main.contextPath + "/SSC11319/findDeliveryOrder" ,{
                deliveryCode:rowdata.deliveryCode
            },function(data) {
                if(data.recordsTotal==0){
                    $.alertMessage.confirm("确定要删除这条数据吗？", function() {
                        $.alertMessage.close();
                        $('#main-content').postUrl(Main.contextPath + "/SSC11319/delete" ,{
                            deliveryId:rowdata.deliveryId,
                            delFlg:1,
                            deliveryStatus:9
                        },function(data) {
                            $.alertMessage.info("删除成功");
                        });
                    });
                }else{
                    $.alertMessage.info("该合同已生成确认发货订单，无法删除！");
                }
            },{refreshHtml:false});
        }
    },
    bindButton: function(){
        //查询
        $("#SSC11319_SEARCH").click(function () {
            if($("#remitTimeStart").val() != "" && $("#remitTimeEnd").val() != "" && $("#remitTimeStart").val() > $("#remitTimeEnd").val()){
                $.alertMessage.info("交易开始时间不能大于交易结束时间");
                return;
            }
            if($("#contractAmountStart").val() != "" && $("#contractAmountEnd").val() != "" &&
                SSCCommon.fgt($("#contractAmountStart").val(),$("#contractAmountEnd").val())){
                $.alertMessage.info("最小合同总金额不能大于最大合同总金额");
                return;
            }

            FormUtils.setFormValue("SSC11319Form", "SSC11319");
            $List_Grid.fnDraw();
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SSC11319.init();
});

