/**
 * 预入库通知单
 * Created by xia_xiaojie on 2016/7/8.
 */

/**
 * 声明对象
 */
var $List_Grid;
var SSC11316 = {
    /**
     * 预入库通知单页面初始化
     */
    init: function() {
        $List_Grid=$("#SSC11316_list_grid").grid({
            actionHandler: SSC11316.actionHandler,
            can_delete: SSC11316.canDelete
        });
        this.closeDate();
        this.bindDatePicker($(":input[name*='expectDeliveryDate']"));
        this.bindDatePicker($(":input[name*='expectArriveDate']"));
        SSC11316.bindButton();
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
    canDelete: function (rowdata){
        if(rowdata.productRecvStatus == 9){
            return false;
        }
        return true;
    },
    /**
     * 预入库通知单列表行末详情按钮触发事件
     */
    actionHandler: function(rowdata, coltype, row, col) {
        //跳转到预入库通知单详情页面
        if ("detail" == coltype) {
            Main.detailWindow(Main.contextPath + "/SSC11317/init/1",  {
                deliveryPreIntoId: rowdata.deliveryPreIntoId
            }, "预入库通知单详细");
        }
        if (coltype == "delete") {

            if(rowdata.productRecvStatus!="0"){
                $.alertMessage.info("货物已发出,不能删除");
                return;
            }

            $.alertMessage.confirm("确定要删除这条数据吗？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SSC11316/delete" ,{
                    deliveryPreIntoId:rowdata.deliveryPreIntoId,
                    ver:rowdata.ver
                },function(data) {
                    if(data=="S"){
                        $.alertMessage.info("删除成功");
                        $('#main-content').postUrl(Main.contextPath + "/SSC11316/init")
                    }
                });
            });
        }
    },
    bindButton: function(){
        //查询
        $("#SSC11316_SEARCH").click(function () {
            if($("#expectDeliveryDate").val() != "" && $("#expectArriveDate").val() != "" && $("#expectDeliveryDate").val() > $("#expectArriveDate").val()){
                $.alertMessage.info("预计发货日期不能大于预计到货日期");
                return;
            }
            FormUtils.setFormValue("SSC11316Form", "SSC11316");
            $List_Grid.fnDraw();
        });
    }
};

/**
 * 初始页面
 */
$(document).ready(function() {
    SSC11316.init();
});
