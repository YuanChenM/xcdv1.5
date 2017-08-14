/**
 * Created by zhang_qiang1 on 2016/6/28.
 */

var SSC1130402 = {

    init: function() {
        this.bindDatePicker("#expectArriveDate");
        this.bindSaveButton();
        this.automaticCalculation();
        this.formatByComma();
    },

    bindDatePicker: function(timeId) {// 时间框渲染
        $(timeId).datepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            changeMonth: true,
            closeText: "清除",
            changeYear: true
        });
        $(timeId).attr("readonly", "readonly");
    },

    formatByComma: function() {
        $("td[id^='leftBoxes']").each(function() {
            var leftBoxes = $.trim($(this).text());
            $(this).text(fmoney(leftBoxes, 0));
        });
    },

    /** 在分配箱数文本框中输入箱数后，自动计算分配吨数和总吨数 */
    automaticCalculation: function() {
        $("input[id^='boxes_']").change(function() {
            var planBoxes = $.trim($(this).val());
            var id = $(this).attr("id");
            var productCode = id.substring(id.indexOf("_") + 1);

            var ton;
            if (SSCCommon.NATURAL_NUMBER.test(planBoxes)) {
                var weight = $("#weight_" + productCode).val();
                ton = SSCCommon.multiply(planBoxes, SSCCommon.divide(weight, 1000));
                ton = fmoney(ton, 4);
            }
            else {
                ton = "";
            }
            $("#ton_" + productCode).text(ton);

            //合计
            var totalTons = 0;
            $("td[id^='ton_']").each(function() {
                var t = SSCCommon.replaceComma($(this).text());
                if ($.trim(t).length > 0) {
                    totalTons = SSCCommon.add(totalTons, t);
                }
            });
            $("#totalTons").text(fmoney(totalTons, 4));
        });
    },

    bindSaveButton: function() {
        $("#SSC1130402_SAVE").click(function() {
            if (SSC1130402.validateFormData()) {
                SSC1130402.saveFormData();
            }
        });
    },

    /** 保存前，校验表单数据 */
    validateFormData: function() {
        var productCodes = document.getElementsByName("productCode");
        var len = productCodes.length;
        if (0 == len) {
            $.alertMessage.info("请在合同订单中添加产品，再新增交货期计划！");
            return false;
        }

        var count = 0;
        for (var i=0; i<len; ++i) {
            var productCode = productCodes[i].value;
            var leftBoxes = $("#leftBoxes" + productCode).html();
            leftBoxes = SSCCommon.replaceComma(leftBoxes);

            if (leftBoxes != 0) {
                break;
            }
            else {
                ++count;
            }
        }
        if (count == len) {
            $.alertMessage.info("合同订单中的产品已分配完，不需要再分配！");
            return false;
        }

        var uncheckedElts = [];
        var checkedElts = [];
        for (var i=0; i<len; ++i) {
            var node = productCodes[i];
            if (node.checked) {
                checkedElts.push(node);
            }
            else {
                uncheckedElts.push(node);
            }
        }
        if (0 == checkedElts.length) {
            $.alertMessage.info("请至少选择一个产品！");
            return false;
        }

        for (var i in uncheckedElts) {
            var productCode = uncheckedElts[i].value;
            var planBoxes = document.getElementById("boxes_" + productCode).value;
            planBoxes = $.trim(planBoxes);

            if (planBoxes.length > 0) {
                $.alertMessage.info("请不要只输入分配箱数，不勾选该产品！");
                return false;
            }
        }

        for (var i in checkedElts) {
            var productCode = checkedElts[i].value;
            var leftBoxes = $("#leftBoxes"+productCode).html();
            var planBoxes = $("#boxes_"+productCode).val();
            leftBoxes = SSCCommon.replaceComma(leftBoxes);
            planBoxes = $.trim(planBoxes);

            if (!SSCCommon.POSITIVE_INTEGER.test(planBoxes)) {
                $.alertMessage.info("分配箱数不能为空，且只能为大于0的整数！");
                return false;
            }
            else if (parseInt(leftBoxes) < parseInt(planBoxes)) {
                $.alertMessage.info("分配箱数不能大于待分配箱数！");
                return false;
            }
            else {
                $("#boxes_" + productCode).val(planBoxes);
            }
        }

        var expectArriveDate = $.trim($("#expectArriveDate").val());
        if (expectArriveDate.length == 0) {
            $.alertMessage.info("到货日期不能为空！");
            return false;
        }

        var remark = $("#remark").val();
        if (remark.length > 100) {
            $.alertMessage.info("备注不要超过100个字！");
            return false;
        }
        return true;
    },


    /**
     * 保存交货期计划
     */
    saveFormData: function() {
        var planDate = $("#expectArriveDate").val();
        planDate = planDate.replaceAll("-", "/");
        var dps = $List_Delivery_Plan_Grid.fnGetData();
        var flag = false;

        for (var i in dps) {
            var expectArriveDate = dps[i].arriveDateStr;
            if (planDate == expectArriveDate) {
                flag = true;
                break;
            }
        }

        var tip;
        if (flag) {
            tip = planDate + "交货期计划已存在，是否继续添加？";
        }
        else {
            tip = "确定保存当前数据吗？";
        }

        $.alertMessage.confirm(tip, function() {
            $.alertMessage.close();
            var contractId = $("#contractId").val();
            var expectArriveDate = $("#expectArriveDate").val();
            var remark = $("#remark").val();

            var deliveryPlans = [];
            $("input[name='productCode']:checked").each(function() {
                var productCode = $(this).val();
                var productName = $("#name_" + productCode).val();
                var weight = $("#weight_" + productCode).val();
                var boxes = $("#boxes_" + productCode).val();

                var deliveryPlan = {
                    contractId: contractId,
                    expectArriveDate: expectArriveDate,
                    remark: remark,
                    productCode: productCode,
                    productName: productName,
                    boxes: boxes,
                    weight: weight
                };
                deliveryPlans.push(deliveryPlan);
            });

            $('#main-content').postUrl($("#SSC1130402Form").attr("action"), {jsonStr: JSON.stringify(deliveryPlans)}, function(data) {
                if (data == "S") {
                    $.pdialog.close();
                    $("#main-content").postUrl(Main.contextPath + "/SSC11304/init", {contractId: contractId}, function() {
                        $("#headBreadCrumb").hide();
                        SSC11304.initTabs(2);
                    });
                } else {
                    $.alertMessage.info("操作失败！");
                }
            }, {refreshHtml: false});
        });
    }
};


$(document).ready(function() {
    SSC1130402.init();
});