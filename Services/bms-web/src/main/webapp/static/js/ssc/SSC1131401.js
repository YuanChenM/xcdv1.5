/**
 * Created by wang_shuai on 2016/7/4.
 */

var SSC11314 = {
    deliverySelect: $("#delivery_select"),
    deliveryData: null,

    init: function () {
        //初始化加载所有发货单
        $('#main-content').postUrl(Main.contextPath + '/SSC11314/searchChooseDelivery', null,
            function (data) {

                SSC11314.deliveryData = data;
                $.map(data, function (item, key) {
                    SSC11314.deliverySelect.append("<option value='" + key + "'>" + key + "</option>");
                });
            }, {refreshHtml: false});

        //监听input框
        $("#tags").bind('input propertychange', function () {
            if ($("#tags").val() == "") {
                $('#main-content').postUrl(Main.contextPath + '/SSC11314/searchChooseDelivery', null,
                    function (data) {
                        SSC11314.deliveryData = data;
                        //清空select框
                        SSC11314.deliverySelect.html("");
                        SSC11314.deliverySelect.append("<option value='0'>请选择</option>");
                        $.map(data, function (item, key) {
                            SSC11314.deliverySelect.append("<option value='" + key + "'>" + key + "</option>");
                        });
                    }, {refreshHtml: false});
            }
        });

        $('#main-content').postUrl(Main.contextPath + '/SSC11314/searchChooseContract/1', null, function (data) {
            if (data.contractFlag == "F") {
                //$.alertMessage.info("无数据");
            } else {
                //使用jQueryUi的Autocomplete插件生成类似于百度搜索框的自动匹配模糊查询
                $("#tags").autocomplete({
                    source: data.contractList
                });
                //根据合同联动生成对应的发货单下拉框
                $("#tags").autocomplete({
                    // ui对象仅有一个item属性，表示当前被选中的菜单项对应的数据源对象
                    select: function (event, ui) {
                        //清空select框
                        SSC11314.deliverySelect.html("");
                        SSC11314.deliverySelect.append("<option value='0'>请选择</option>");
                        //联动生成select框
                        var contractCode = ui.item.value.split("(")[0];
                        $('#main-content').postUrl(Main.contextPath + '/SSC11314/searchChooseDelivery', {contractCode: contractCode},
                            function (data2) {
                                SSC11314.deliveryData = data2;
                                $.map(data2, function (item, key) {
                                    SSC11314.deliverySelect.append("<option value='" + key + "'>" + key + "</option>");
                                });

                            }, {refreshHtml: false});
                    }
                });
            }
        }, {refreshHtml: false});

        /*$("#chooseContractInfo").click(function () {
            $.pdialog.open("选择合同信息", Main.contextPath + "/SSC1130301/init", {width: 1200, height: 500}, {
                "contractNameId":"tags",
                "contractCodeId":"contractCode",
                "contractStatusStr":"4,5,6,7",
                "isPaymentRequest":"isPaymentRequest"
            },"chooseEpDialog");
        })*/

        $("#chooseDeliveryOrder").click(function () {

            $.pdialog.open("选择发货订单", Main.contextPath + "/SSC11305/chooseDeliveryOrder", {width: 1200, height: 500}, {
                "deliveryCodeId":"delivery_select",//需要赋值deliveryCode的标签Id
                "isDeliveryConfirm":"true",//未生成发货确认单
                "delFlg":0,//未删除
                "deliveryStatus":4,//发货订单状态双方已确认
                "contractCode":$("#contractCode").val(),//关联的合同code
                "isPaymentRequest":"false"//是否判断未生成付款申请
            },"chooseDeliveryOrderDialog");
        });

        /*$("#chooseVerification").click(function () {
            $.pdialog.open("选择核销单", Main.contextPath + "/SSC11321/chooseVerification", {width: 1200, height: 500}, {
                "verificationCodeId":"verificationCode",//需要赋值verificationCode的标签Id
                "isPaymentRequest":"true",//是否判断未生成付款申请
                "delFlg":0,//未删除
                "status":3,//核销单状态已确认
                "verificationType":0//核销处理办法为退款
            },"chooseVerificationDialog");
        });*/

        this.bindConfirmButton();
        this.changeSelectDelivery();
    },

    bindConfirmButton: function () {
        $("#SSC1131401_CONFIRM").click(function () {
            var contractCode = $("#tags").val().split("(")[0].trim();
            /*var deliveryCode = SSC11314.deliverySelect.find("option:selected").val().trim();
            if (deliveryCode == "0" || deliveryCode == null) {
                $.alertMessage.info("请选择发货订单！");
                return;
            }*/
            var deliveryCode = SSC11314.deliverySelect.val();
            if (deliveryCode == "" || deliveryCode == null) {
                $.alertMessage.info("请选择发货订单！");
                return;
            }

            /*var contractBasic = SSC11314.deliveryData[deliveryCode];
            var contractCodeCompare = contractBasic.contractCode == null ? "" : contractBasic.contractCode;
            if (contractCode.length > 0 && contractCodeCompare.length == 0) {
                $.alertMessage.info("您选择的发货订单没有关联的合同！");
                $("#tags").val("");
                return;
            }
            if (contractCode != contractCodeCompare) {
                $.alertMessage.info("该合同和发货订单不匹配！");
                return;
            }*/

            $('#main-content').postUrl(Main.contextPath + '/SSC11314/insertDeliveryConfirm', {
                contractCode: contractCode,
                deliveryCode: deliveryCode
            }, function (data) {
                if (data == "") {
                    $.alertMessage.info(data + "操作失败");
                } else {
                    Main.detailWindow(Main.contextPath + "/SSC11315/init/1", {
                        deliveryCode: deliveryCode,
                        deliveryConfirmId: data
                    }, "订单发货确认单详细");
                    $.pdialog.close();

                    /*$('#main-content').postUrl(Main.contextPath + '/SSC11315/init/1', {
                        deliveryCode: deliveryCode,
                        deliveryConfirmId: data
                    });
                    $.alertMessage.info("新建成功");
                    $.pdialog.close();*/
                }
            }, {refreshHtml: false});
        });
    },

    /**
     * 如果有合同编码加载合同名称
     * @param data
     */
    changeSelectDelivery: function () {
        SSC11314.deliverySelect.change(function () {
            var deliveryCode = SSC11314.deliverySelect.find("option:selected").val().trim();
            $('#tags').val("");
            if (deliveryCode != "0" && deliveryCode != null) {
                var contractBasic = SSC11314.deliveryData[deliveryCode];
                var contractName = contractBasic.contractName == null ? "" : contractBasic.contractName;
                if (contractBasic.contractCode != null && contractBasic.contractCode != '') {
                    $('#tags').val(contractBasic.contractCode + "(" + contractName + ")");
                }
            }
        });
    }
};

$(document).ready(function () {
    SSC11314.init();
});