/**
 * Created by liuyan on 2016/7/14.
 */
var $List_Grid;
var SSC1131701 = {
    formId:"SSC1131701Form",
    inputDate:"#inputDate",
    init: function () {
        $List_Grid = $('#SSC1131701_list_grid').grid({
        });
        this.bindDateTimePicker(SSC1131701.inputDate);
        this.bindTestButton();
        this.closeDate();
    },
    bindDateTimePicker : function(timeId){
        $(timeId).datetimepicker({
            timeFormat: "HH:mm:ss",
            dateFormat: "yy-mm-dd",
            showButtonPanel: true,
            showHour: true,  // 显示小时
            showMinute: true,  // 显示分
            showSecond: true, //显示秒
            closeText: "清除",
            hourText: '时',
            minuteText: '分',
            secondText: '秒',
            timeText:"时间",
            currentText:"当前时间"
        });
        $(timeId).attr("readonly","readonly");

    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._curInst.input.val("");
        });
    },
    bindTestButton: function(){
        $("#SSC1131701_SAVE").click(function () {
            var deliveryStockId = $("#deliveryStockId").val();
            var deliveryPreIntoId = $("#deliveryPreIntoId").val();
            var ver = $("#ver").val();
            var stockMemo = $("#stockMemo").val();
            var inputDate = $("#inputDate").val();
            if (inputDate.length ==0) {
                $.alertMessage.info("实际入库时间必填！");
                return;
            }
            var datas = $List_Grid.getChangeData();
            if (datas.length == 0) {
                $.alertMessage.info("入库数量没有修改！");
                return;
            }
            var productList = {};
            for(var i = 0; i< datas.length; i++ ) {
                var obj = datas[i];
                productList[i] = {
                    "pdCode":obj.pdCode,
                    "receiveActualNum": obj.productRecvBox
                };
            }
            var jsonStr = JSON.stringify(productList);
            var param = {
                "deliveryStockId": deliveryStockId,
                "deliveryPreIntoId": deliveryPreIntoId,
                "ver": ver,
                "stockMemo": stockMemo,
                "inputDate": inputDate,
                "tempProductList":jsonStr
            };
            $('#main-content').postUrl(Main.contextPath + "/SSC11317/updatePreInto", param,function(data) {
                $.pdialog.close();
                $.alertMessage.info("入库成功");
                $("#main-content").postUrl(Main.contextPath + "/SSC11317/init/1", {
                    deliveryPreIntoCode: deliveryStockId
                },Main.hiddenHeader);
            },{refreshHtml:false});
        });
    }
}


$(document).ready(function () {
    // 初始化调用
    SSC1131701.init();
});