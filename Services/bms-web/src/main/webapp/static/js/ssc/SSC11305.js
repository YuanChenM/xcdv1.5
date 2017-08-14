var $List_Grid;
var SSC11305 = {
    addButtonId:"SSC11305_ADD",
    formId:"SSC11305Form",
    init: function () {
        $List_Grid = $('#SSC11305_list_grid').grid({
            actionHandler: SSC11305.actionHandler,
            can_abolish: SSC11305.canAbolish
        });
        this.closeDate();
        this.bindButton();
        this.bindDatePicker($(":input[name*='etaStr']"));
        Main.hlLeftMainMenu("SSC11305");
        $("input").keypress(function(e) {
            if (13 == e.keyCode) {
                FormUtils.setFormValue("SSC11305Form", "SSC11305");
                $List_Grid.fnDraw();
            }
        });
    },
    bindDatePicker : function(timeId){
        $(timeId).datepicker({
            dateFormat: "yy-mm-dd",
            closeText: "清除",
            showButtonPanel: true
        });
        $(timeId).attr("readonly","readonly");
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        //跳转详情页面
        if(coltype == "detail"){
            Main.detailWindow(Main.contextPath + "/SSC11306/init/",  {
               "deliveryId": rowdata.deliveryId
            }, "发货订单详细");
        }
        //删除
        if (coltype == "delete") {
            $('#main-content').postUrl(Main.contextPath + "/SSC11305/findDeliveryConfirmInfo" ,{
                deliveryCode:rowdata.deliveryCode
            },function(data) {
                if(data.recordsTotal==0){
                    $('#main-content').postUrl(Main.contextPath + "/SSC11307/search" ,{
                        deliveryId:rowdata.deliveryId
                    },function(data) {
                        if(data.recordsTotal==0){
                            $.alertMessage.confirm("确定要删除这条数据吗？", function() {
                                $.alertMessage.close();
                                $('#main-content').postUrl(Main.contextPath + "/SSC11305/delete" ,{
                                    deliveryId:rowdata.deliveryId,
                                    ver:rowdata.ver
                                },function(data) {
                                    $.alertMessage.info("删除成功");
                                });
                            });
                        }else if(data.data[0].auditingStatus==0){
                            var paymentRequestId=data.data[0].paymentRequestId;
                            $.alertMessage.confirm("确定要删除这条数据吗？", function() {
                                $.alertMessage.close();
                                $('#main-content').postUrl(Main.contextPath + "/SSC11305/delete" ,{
                                    deliveryId:rowdata.deliveryId
                                },function(data) {
                                    $('#main-content').postUrl(Main.contextPath + "/SSC11307/delete" ,{
                                        paymentRequestId:paymentRequestId
                                    },function(data) {
                                        $.alertMessage.info("删除成功");
                                        $('#main-content').postUrl(Main.contextPath + "/SSC11305/init")
                                        //控制左侧菜单栏是否选中
                                        $(".menu_content .sub_menu .ui-menu-item[selected=selected]").each(function() {
                                            $(this).removeAttr("selected");
                                        });
                                        $(".menu_content .sub_menu .ui-menu-item[href*='SSC11305']").attr("selected", "selected");
                                    });
                                });
                            }),{refreshHtml:false};
                        }else{
                            $.alertMessage.info("该发货订单已生成付款申请且付款申请已审批，无法删除！");
                        }
                    },{refreshHtml:false});
                }else{
                    $.alertMessage.info("该发货订单已生成发货确认订单，无法删除！");
                }
            },{refreshHtml:false});
        }
    },
    //如果状态为已删除，就不显示删除按钮
    canAbolish: function (rowdata) {
        var deliveryStatus = rowdata.deliveryStatus;
        if (deliveryStatus == "9") {
            return false;
        }
        return true;
    },
    // 绑定按钮
    bindButton: function(){
        //新增
        $("#" + SSC11305.addButtonId).click(function () {
            Main.detailWindow(Main.contextPath + "/SSC11306/init/",{},"发货订单详细");
        });
        //查询
        $("#SSC11305_SEARCH").click(function () {
            FormUtils.setFormValue("SSC11305Form", "SSC11305");
            $List_Grid.fnDraw();
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SSC11305.init();
});

