/**
 * Created by wang_shuai on 2016/7/4.
 */

var SSC1132601 = {
    realProduceStartDate:"#realProduceStartDateStr",
    realProduceEndDate:"#realProduceEndDateStr",
    init: function(){
        this.bindDeliveryInputTimerPicker(); //绑定运输期日期控件
        this.bindSaveProduceButton();//保存或生产期产品管控
        this.bindSaveReadyGoButton();///保存或更新待运期产品管控
        this.bindSaveDeliveryButton();//保存或更新运输期产品管控
        this.bindShowSaveButton();//如果日期大于当前日期，保存按钮不显示
        this.bindPageStyle();//页面样式微调
        this.closeDate();
    },
    bindInputBlur:function(obj){
        var num = $(obj).val().trim();
        if(num.length == 0) {
            return;
        }
        num = SSCCommon.clearComma(num);
        if (!SSCCommon.PRODUCE_WEIGHT_REG.test(num)) {
            $.alertMessage.info("日生产量格式错误（整数位最多9位，小数位最多2位）！");
            return;
        }
        $(obj).val(SSCCommon.formatMoney($(obj).val()));
    },
    bindPageStyle: function() {
        $("#produceTable :input[type='text']").each(function(){
            $(this).addClass("num_input");
        });
        $("#readyGoTable :input[type='text']").each(function(){
            $(this).addClass("num_input");
        });
    },
    goContractDetailPage: function () {
        Main.detailWindow(Main.contextPath+"/SSC11304/show",{
            contractId: $('#contractId').val().trim(),
            contractCode: $('#contractCode').val().trim()
        },"合同详细");
    },
    bindShowSaveButton : function() {
        if ($("#produceTable :input[type='text']").length ==0) {
            $("#produceTable tr:last").hide();
        }
        if ($("#readyGoTable :input[type='text']").length ==0) {
            $("#readyGoTable tr:last").hide();
        }
    },
    bindDateTimePicker : function(timeId){
        $(timeId).datetimepicker({
            timeFormat: "HH:mm:ss",
            dateFormat: "yy-mm-dd",
            showButtonPanel: true,
            showHour: true,  // 显示小时
            showMinute: true,  // 显示分
            showSecond: true, //显示秒
            //Modif for Bug#2577 at 2016/09/09 by wu_honglei Start
            closeText: '清除',
            //Modif for Bug#2577 at 2016/09/09 by wu_honglei End
            hourText: '时',
            minuteText: '分',
            secondText: '秒',
            timeText:"时间",
            currentText:"现在"
        });
        $(timeId).attr("readonly","readonly");

    },
    bindSaveProduceButton: function() {
        $("#SSC1132601_SAVEPRODUCE").click(function () {
            SSC1132601.bindSaveControlButton(1, 'produceTable');
        });
    },
    bindSaveReadyGoButton: function() {
        $("#SSC1132601_SAVEREADYGO").click(function () {
            SSC1132601.bindSaveControlButton(2, 'readyGoTable');
        });
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._curInst.input.val("");
        });
    },
    bindSaveDeliveryButton: function() {
        $("#SSC1132601_SAVEDELIVERY").click(function () {
            var contractId = $('#contractId').val().trim();
            var contractCode= $('#contractCode').val().trim();
            var ssc1132601RsBeans= {};
            $("#deliveryTable tr:gt(0):not(:last)").each(function(i){
                var planArriveDateStr = $(this).find(":input[name='planArriveDateStr']").val();
                var realArriveDateStr = $(this).find(":input[name='realArriveDateStr']").val();
                var planChooseVehicleDateStr = $(this).find(":input[name='planChooseVehicleDateStr']").val();
                var realChooseVehicleDateStr = $(this).find(":input[name='realChooseVehicleDateStr']").val();
                var planIntoVehicleDateStr = $(this).find(":input[name='planIntoVehicleDateStr']").val();
                var realIntoVehicleDateStr = $(this).find(":input[name='realIntoVehicleDateStr']").val();
                var planOffDateStr = $(this).find(":input[name='planOffDateStr']").val();
                var realOffDateStr = $(this).find(":input[name='realOffDateStr']").val();
                ssc1132601RsBeans[i] = {};
                ssc1132601RsBeans[i].id = $(this).find(":input[name='id']").val();
                ssc1132601RsBeans[i].ver = $(this).find(":input[name='ver']").val();
                ssc1132601RsBeans[i].deliveryBatch = $(this).find(":input[name='deliveryBatch']").val();
                ssc1132601RsBeans[i].contractId = contractId;
                ssc1132601RsBeans[i].contractCode = contractCode;
                if (planArriveDateStr.length >0 ){
                    ssc1132601RsBeans[i].planArriveDateStr = planArriveDateStr;
                }
                if (realArriveDateStr.length >0 ){
                    ssc1132601RsBeans[i].realArriveDateStr = realArriveDateStr;
                }
                if (planChooseVehicleDateStr.length >0 ){
                    ssc1132601RsBeans[i].planChooseVehicleDateStr = planChooseVehicleDateStr;
                }
                if (realChooseVehicleDateStr.length >0 ){
                    ssc1132601RsBeans[i].realChooseVehicleDateStr = realChooseVehicleDateStr;
                }
                if (planIntoVehicleDateStr.length >0 ){
                    ssc1132601RsBeans[i].planIntoVehicleDateStr = planIntoVehicleDateStr;
                }
                if (realIntoVehicleDateStr.length >0 ){
                    ssc1132601RsBeans[i].realIntoVehicleDateStr = realIntoVehicleDateStr;
                }
                if (planOffDateStr.length >0 ){
                    ssc1132601RsBeans[i].planOffDateStr = planOffDateStr;
                }
                if (realOffDateStr.length >0 ){
                    ssc1132601RsBeans[i].realOffDateStr = realOffDateStr;
                }
            });
            var jsonStr = JSON.stringify(ssc1132601RsBeans);
            $('#main-content').postUrl(Main.contextPath + "/SSC11326/batchSaveOrUpdateDelivery", {
                jsonStr: jsonStr,
                contractCode:contractCode
            },function(data) {
                $.alertMessage.info("操作成功");
            });
        });

    },
    bindDeliveryInputTimerPicker: function() {
        $("#deliveryTable :input[readonly]").each(function(i){
            SSC1132601.bindDateTimePicker($(this));
        });
    },
    bindSaveControlButton: function(type, table) {
        if(SSC1132601.checkProduceForm(table)) {
        var ssc11326RsBeans = {};
        $("#" + table + " :input[name='planNum']").each(function (i) {
            var produce = $(this).attr("produce");
            var produceDatas = produce.split('/');
            var planNum = $(this).val().trim();
            var realNum = $("#" + table + " :input[produce = '" + produce + "'][name='realNum']").val().trim();
            if (realNum.length > 0) {
                realNum = SSCCommon.clearComma(realNum);
            }
            if (planNum.length > 0) {
                planNum = SSCCommon.clearComma(planNum);
            }
            ssc11326RsBeans[i] = {
                ver: produceDatas[3],
                id: produceDatas[2],
                detailId: produceDatas[1],
                produceDateStr: produceDatas[0],
                planNum: planNum == '' ? null : planNum,
                realNum: realNum == '' ? null : realNum,
                type: type
            }
        });
        var jsonStr = JSON.stringify(ssc11326RsBeans);
        var contractCode = $('#contractCode').val().trim();
        $('#main-content').postUrl(Main.contextPath + "/SSC11326/batchSaveOrUpdate", {
            jsonStr: jsonStr,
            contractCode: contractCode
        }, function (data) {
            $.alertMessage.info("操作成功");
        });
        }
    },
    checkProduceForm: function(table) {
        //验证日生产量
        $("#"+table+" :input[type='text']").each(function(){
            var num = $(this).val().trim();
            if(num.length == 0) {
                return;
            }
            num = SSCCommon.clearComma(num);
            if (!SSCCommon.PRODUCE_WEIGHT_REG.test(num)) {
                $.alertMessage.info("日生产量格式错误（整数位最多9位，小数位最多2位）！");
                e.preventDefault();
            }
        });
        return true;
    }
}
$(document).ready(function(){
    $.when(SSC1132601.init()).done(function(){
        Main.hlLeftMainMenu("SSC11326")
    });
})