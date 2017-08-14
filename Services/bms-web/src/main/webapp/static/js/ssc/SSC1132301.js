/**
 * Created by ding_guangjian on 2016/8/3.
 */

var SSC11323 = {
    contractSelect: $("#delivery_select"),
    contractData: null,

    init: function () {
        //监听input框
        // 调用SSC11314的查询接口
        $('#main-content').postUrl(Main.contextPath + '/SSC11324/searchContractForInvoice', null, function (data) {
            if (data.contractFlag == "F") {
                $.alertMessage.info("无数据");
            } else {
                //使用jQueryUi的Autocomplete插件生成类似于百度搜索框的自动匹配模糊查询
                $("#tags").autocomplete({
                    source: data.contractList
                });
            }
        }, {refreshHtml: false});
        this.bindConfirmButton();
        SSC11323.bindShowContractInfo();
    },
    bindShowContractInfo:function(){
        $("#chooseContractInfo").click(function () {
            $.pdialog.open("选择合同信息", Main.contextPath + "/SSC1130301/init", {width: 1200, height: 500}, {
                "contractNameId":"tags",
                "contractStatusStr":"4,5,6,7",
                "isPaymentRequest":"isPaymentRequest"
            },"chooseEpDialog");
        })
    },
    bindConfirmButton: function () {
        $("#SSC1132301_CONFIRM").click(function () {
            var contractCode = $("#tags").val().split("(")[0].trim();
            if ($("#tags").val().split("(")[1] != null && $("#tags").val().split("(")[1] != undefined && $("#tags").val().split("(")[1] != "") {
                var contractName = $("#tags").val().split("(")[1].split(")")[0].trim();
            }
            $('#main-content').postUrl(Main.contextPath + '/SSC11324/ContractFindInvoiceRequestDetailExist', {
                contractCode: contractCode
                //,contractName: contractName
            }, function (data) {
                if (data.status == "S") {
                    Main.detailWindow(Main.contextPath + "/SSC11324/init/1", {
                        contractCode: contractCode
                        //,contractName: contractName
                    }, "发票申请详细");
                    $.pdialog.close();

                    /*$('#main-content').postUrl(Main.contextPath + '/SSC11324/init/1', {
                        contractCode: contractCode, contractName: contractName
                    });
                    $.alertMessage.info("新建成功");*/
                } else {
                    $.alertMessage.info("没有这条数据,请重新选择");
                }
            }, {refreshHtml: false});
        });
    }
};

$(document).ready(function () {
    SSC11323.init();
});