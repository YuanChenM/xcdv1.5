/**
 * Created by wu_honglei on 2016/7/4.
 */
var $List_Grid;
var SSC11307 = {
    addButtonId:"SSC11307_ADD",
    formId:"SSC11307Form",
    init : function() {
        $List_Grid = $("#SSC11307_list_grid").grid({
            actionHandler:SSC11307.actionHandler,
            linkHandler: SSC11307.linkHandler,
            can_abolish: SSC11307.canAbolish
        })
        this.closeDate();
        this.bindAddButton();
        this.bindDatePicker($(":input[name*='remitTimeStr']"));
        //Main.hlLeftMainMenu("SSC11307");
        this.bindSearchButton();
    },

    bindDatePicker : function(timeId){
        $(timeId).datepicker({
            //timeFormat: "HH:mm:ss",
            dateFormat: "yy-mm-dd",
            closeText: "清除",
            showButtonPanel: true
            /*showButtonPanel: true,
            showHour: true,  // 显示小时
            showMinute: true,  // 显示分
            showSecond: true, //显示秒
            closeText: '确定',
            hourText: '时',
            minuteText: '分',
            secondText: '秒',
            timeText:"时间",
            currentText:"当前时间"*/
        });
        $(timeId).attr("readonly","readonly");

    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    linkHandler: function (rowdata, colname, row, col) {
        if (colname == "deliveryCode") {
            Main.detailWindow(Main.contextPath + "/SSC11306/show/",  {
                "deliveryId": rowdata.deliveryId,
                "paymentRequestId": rowdata.paymentRequestId
            }, "发货订单详细");
        }
        if (colname == "contractCode") {
            Main.detailWindow(Main.contextPath + "/SSC11304/show/",  {
                "contractId": rowdata.contractId,
                "paymentRequestId": rowdata.paymentRequestId
            }, "合同详细");
        }
    },

    actionHandler: function (rowdata,coltype,row,col) {
        
        var payedStatus = rowdata.payedStatus;
        if(coltype=='detail'){
            //从已删除数据进入详细页面
            if(payedStatus=="9"){
                Main.detailWindow(Main.contextPath + "/SSC11308/init/0",  {
                    paymentRequestId:rowdata.paymentRequestId,
                    delFlg:1
                }, "付款申请");
            }else{
                 Main.detailWindow(Main.contextPath + "/SSC11308/init/0",  {
                    paymentRequestId:rowdata.paymentRequestId,
                    delFlg:0
                }, "付款申请");
            }
        }

        if(coltype == "delete"){
            //判读审核审批状态
            var status=rowdata.auditingStatus;
            if(status==0) {
                $.alertMessage.confirm("确定要删除这条数据吗？", function() {
                    $.alertMessage.close();
                    $('#main-content').postUrl(Main.contextPath + "/SSC11307/delete", {
                        paymentRequestId: rowdata.paymentRequestId,
                        ver:rowdata.ver
                    },function(data) {
                        $.alertMessage.info("删除成功");
                    });
                })
            }else{
                $.alertMessage.info("该付款单已审批或审核，无法删除！");
            }
        }


    },
    // 绑定新增按钮
    bindAddButton: function(){
        $("#" + SSC11307.addButtonId).click(function () {
            $.pdialog.open("新建付款申请", Main.contextPath + "/SSC1130802/init", {width: 900, height: 300}, {
                contractCode:$("#contractCode").val()
            });

        });
    },
    //如果状态为已删除，就不显示删除按钮
    canAbolish: function (rowdata) {
        var payedStatus = rowdata.payedStatus;
        if (payedStatus == "9") {
            return false;
        }
        return true;
    },

    bindSearchButton:function(){
        //查询
        $("#SSC11307_SEARCH").click(function () {
            FormUtils.setFormValue("SSC11307Form", "SSC11307");
            $List_Grid.fnDraw();
        });
    }
}
$(document).ready(function(){
    SSC11307.init();
})