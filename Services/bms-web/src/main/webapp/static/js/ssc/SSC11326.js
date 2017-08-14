/**
 * Created by wang_shuai on 2016/7/4.
 */

var SSC11326 = {
    formId:"SSC11326Form",
    realProduceStartDate:"#realProduceStartDateStr",
    realProduceEndDate:"#realProduceEndDateStr",
    realContractList: null,
    init: function(){
        this.loadContract(); //加载合同单号
        this.bindSaveButton(); //保存
        this.bindViewButton(); //查看生产商生产计划
        this.bindSearchButton();//查询合同生效日期及最迟发货日期
        this.bindDateTimePicker(SSC11326.realProduceStartDate);
        this.bindDateTimePicker(SSC11326.realProduceEndDate);
        this.closeDate();
        this.bindShowContractInfo();
    },

    bindShowContractInfo:function(){
        $("#chooseContractInfo").click(function () {
            $.pdialog.open("选择合同", Main.contextPath + "/SSC1130301/init", {width: 1200, height: 500}, {
                "contractNameId":"tags",
                "contractIdId":"contractId",
                "contractStatusStr":"4,5,6,7,8"
            },"chooseEpDialog");
        });
    },

    bindDateTimePicker : function(timeId){
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
    bindSaveButton: function() {
        $("#SSC11326_SAVE").click(function () {
            var formData = getFormData($("#" + SSC11326.formId));
            if (formData.realProduceStartDateStr.length == 0) {
                $.alertMessage.info("生产商计划开始日期不能为空");
                return false;
            }
            if (formData.realProduceEndDateStr.length == 0) {
                $.alertMessage.info("生产商计划结束日期不能为空");
                return false;
            }
            if (Date.parse(formData.realProduceStartDateStr) > Date.parse(formData.realProduceEndDateStr)) {
                $.alertMessage.info("生产商计划开始日期 不能大于 生产商计划结束日期！");
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/SSC11326/update", formData, function (data) {
                if (data == "S") {
                    $.alertMessage.info("操作成功!");
                    $('#ver').val(formData.ver + 1);
                } else {
                    $.alertMessage.warn("操作失败!");
                }
            }, {refreshHtml: false});
        });
    },
    bindViewButton: function() {
        $("#SSC11326_VIEW").click(function () {
            var formData = getFormData($("#" + SSC11326.formId));
            $('#main-content').postUrl(Main.contextPath + "/SSC11326/detail", {contractCode: formData.contractCode});
        });
    },
    bindSearchButton: function() {
        $("#SSC11326_SEARCH").click(function () {
            var contractStr = $("#tags").val().trim();
            var contractCode = $("#tags").val().split("(")[0].trim();
            if (contractStr == '') {
                $.alertMessage.info("请填写合同编码");
                return;
            }
            var isRealContract = false;
            for(var i = 0; i<SSC11326.realContractList.length; i++) {
                var contract = SSC11326.realContractList[i];
                var realContractCode = contract.split("(")[0];
                if (contractStr == contract) {
                    isRealContract = true;
                    break;
                }
                if (contractCode == realContractCode) {
                    isRealContract = true;
                    break;
                }
            }
            if (isRealContract) {
                $('#main-content').postUrl(Main.contextPath + "/SSC11326/search", {contractCode: contractCode});
            } else {
                $.alertMessage.info("合同编码不正确或者该合同还未生效");
                $('.group-accordion').hide();
            }
        });
    },
    loadContract: function() {
        $('#main-content').postUrl(Main.contextPath + '/SSC11314/searchChooseContract/2',null,function(data){
            if(data.contractFlag == "F"){
                $.alertMessage.info("无数据");
            }else{
                SSC11326.realContractList = data.contractList;
                //使用jQueryUi的Autocomplete插件生成类似于百度搜索框的自动匹配模糊查询
                $( "#tags" ).autocomplete({
                    source: data.contractList
                });
            }
        },{refreshHtml:false});
    }
}
$(document).ready(function(){
    $.when(SSC11326.init()).done(function(){
        Main.hlLeftMainMenu("SSC11326")
    });
})