/**
 * Created by zhang_qiang1 on 2016/6/28.
 */

var SSC1130403 = {

    init: function () {
        this.bindSaveButton();
        this.bindProductAttrCodeSelect();
        this.setProductGradeCodeName();
    },

    bindSaveButton: function() {
        $("#SSC1130403_SAVE").click(function() {
            if (SSC1130403.validateFormData()) {
                var products = $List_Contract_Order_Grid.fnGetData();
                var formdata = getFormData($("#SSC1130403Form"));
                var productCode = formdata.pdCode;

                var flag = false;
                var productId;
                for (var i in products) {
                    if (products[i].pdCode == productCode) {
                        flag = true;
                        productId = products[i].detailId;
                        break;
                    }
                }

                if (flag) {
                    $.alertMessage.confirm("当前产品已存在，是否替换该产品信息？", function() {
                        $.alertMessage.close();
                        formdata.detailId = productId;
                        var url = Main.contextPath + "/SSC1130403/product/update";
                        SSC1130403.saveOrUpdateData(url, formdata);
                    });
                }
                else {
                    $.alertMessage.confirm("确定保存当前数据吗？", function () {
                        $.alertMessage.close();
                        var url = Main.contextPath + "/SSC1130403/product/add";
                        SSC1130403.saveOrUpdateData(url, formdata);
                    });
                }
            }
        });
    },

    /** 选择产品时触发 */
    bindProductAttrCodeSelect: function () {
        $("#productAttrCode").change(function () {
            SSC1130403.setProductGradeCodeName();
        });
    },

    /** 设置产品等级、代码和名称 */
    setProductGradeCodeName: function () {
        var $productCode = $("#productAttrCode").find("option:selected");
        var pdName = $("#productAttrCode").find("option:selected").text().trim();
        $("#pdDesc").val(pdName);
        $("#classesCode").val($productCode.attr("classes_code"));
        $("#classesName").val($productCode.attr("classes_name"));
        $("#machiningCode").val($productCode.attr("machining_code"));
        $("#machiningName").val($productCode.attr("machining_name"));
        $("#breedCode").val($productCode.attr("breed_code"));
        $("#breedName").val($productCode.attr("breed_name"));
        $("#featureCode").val($productCode.attr("feature_code"));
        $("#featureName").val($productCode.attr("feature_name"));
        $("#weightCode").val($productCode.attr("weight_code"));
        $("#weightName").val($productCode.attr("weight_name"));
        $("#normsCode").val($productCode.attr("norms_code"));
        $("#normsOut").val($productCode.attr("norms_out"));
        $("#weightVal").val($productCode.attr("weight_val"));
        $("#gradeCode").val($productCode.attr("grade_code"));
        $("#gradeName").val($productCode.attr("grade_name"));
        $("#brandId").val($productCode.attr("brandId"));
        $("#brandName").val($productCode.attr("brandName"));
        $("#brandEpId").val($productCode.attr("brandEpId"));
    },

    /** 保存前，校验表单数据 */
    validateFormData: function () {
        var $productCode = $.trim($("#productAttrCode").val());
        if (0 == $productCode.length) {
            $.alertMessage.info("请选择产品！");
            return false;
        }

        var productBox = $.trim($("#productBox").val());
        if (!SSCCommon.POSITIVE_INTEGER.test(productBox)) {
            $.alertMessage.info("箱数不能为空，且必须是正整数！");
            return false;
        }
        else if (parseInt(productBox) > SSCCommon.INT11) {
            $.alertMessage.info("箱数不能超过99999999！");
            return false;
        }

        var fobFreePackage = $.trim($("#fobFreePackage").val());
        if (fobFreePackage.length > 0 && !SSCCommon.MONEY_REG.test(fobFreePackage)) {
            $.alertMessage.info("不含包装离岸价格式错误，整数位最多15位，小数位最多2位！");
            return false;
        }

        var packageCost = $.trim($("#packageCost").val());
        if (packageCost.length > 0 && !SSCCommon.MONEY_REG.test(packageCost)) {
            $.alertMessage.info("包材成本格式错误，整数位最多15位，小数位最多2位！");
            return false;
        }

        var fobIncludePackage = $.trim($("#fobIncludePackage").val());
        if (fobIncludePackage.length > 0 && !SSCCommon.MONEY_REG.test(fobIncludePackage)) {
            $.alertMessage.info("含包装离岸价格式错误，整数位最多15位，小数位最多2位！");
            return false;
        }

        var trunkFreight = $.trim($("#trunkFreight").val());
        if (trunkFreight.length > 0 && !SSCCommon.MONEY_REG.test(trunkFreight)) {
            $.alertMessage.info("干线运费格式错误，整数位最多15位，小数位最多2位！");
            return false;
        }

        var cif = $.trim($("#cif").val());
        if (cif.length > 0 && !SSCCommon.MONEY_REG.test(cif)) {
            $.alertMessage.info("到岸价格式错误，整数位最多15位，小数位最多2位！");
            return false;
        }

        var settkementStandardPrice = $.trim($("#settkementStandardPrice").val());
        if (!SSCCommon.isMoney(settkementStandardPrice)) {
            $.alertMessage.info("本次结算标准价不能为空或0，且格式为：整数位最多15位，小数位最多2位！");
            return false;
        }

        var downPayment = $.trim($("#downPayment").val());
        if (!SSCCommon.isPercent(downPayment)) {
            $.alertMessage.info("预付款比例不能为空，且只能为0~100之间的数字！");
            return false;
        }

        var remark = $("#remark").val();
        if (remark.length > 100) {
            $.alertMessage.info("备注不能超过100字符！");
            return false;
        }
        return true;
    },

    saveOrUpdateData: function (url, formData) {
        $('#main-content').postUrl(url, formData, function (data) {
            if (data == "S") {
                $.pdialog.close();
                var contractId = $("#contractId").val();
                $("#main-content").postUrl(Main.contextPath + "/SSC11304/init", {contractId: contractId}, Main.hiddenHeader);
            } else {
                $.alertMessage.info("操作失败！");
            }
        }, {refreshHtml: false});
    }
};

$(document).ready(function () {
    SSC1130403.init();
});