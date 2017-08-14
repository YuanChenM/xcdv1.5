/**
 * Created by zhang_qiang1 on 2016/6/28.
 */

var SSC1130404 = {

    init: function () {
        this.bindSaveButton();
        this.changeSelect();
    },

    bindSaveButton: function() {
        $("#SSC1130404_SAVE").click(function() {
            if (SSC1130404.validateFormData()) {
                var packages = $List_Contract_Package_Grid.fnGetData();
                var formdata = getFormData($("#SSC1130404Form"));
                var productId = formdata.detailId;

                var flag = false;
                var packageId;
                for (var i in packages) {
                    if (packages[i].detailId == productId) {
                        flag = true;
                        packageId = packages[i].packageDetailId;
                        break;
                    }
                }

                if (flag) {
                    $.alertMessage.confirm("当前产品的包材已存在，是否替换该产品的包材信息？", function() {
                        $.alertMessage.close();
                        formdata.packageDetailId = packageId;
                        var url = Main.contextPath + "/SSC1130404/package/update";
                        SSC1130404.saveOrUpdateData(url, formdata);
                    });
                }
                else {
                    $.alertMessage.confirm("确定保存当前数据吗？", function() {
                        $.alertMessage.close();
                        var url = Main.contextPath + "/SSC1130404/package/add";
                        SSC1130404.saveOrUpdateData(url, formdata);
                    });
                }
            }
        });
    },

    saveOrUpdateData: function (url, formData) {
        $('#main-content').postUrl(url, formData, function (data) {
            if (data == "S") {
                $.pdialog.close();
                var contractId = $("#contractId").val();
                $("#main-content").postUrl(Main.contextPath + "/SSC11304/init", {contractId: contractId}, function() {
                    $("#headBreadCrumb").hide();
                    SSC11304.initTabs(1);
                });
            } else {
                $.alertMessage.info("操作失败！");
            }
        }, {refreshHtml: false})
    },

    /** 保存前，校验表单数据 */
    validateFormData: function () {
        var product = $.trim($("#product").val());
        if (product.length <= 0) {
            $.alertMessage.info("请先选择产品！");
            return false;
        }

        var cartonQua = $.trim($("#cartonQua").val());
        if (cartonQua.length <= 0) {
            $.alertMessage.info("(本地订单包材信息)纸箱质量标准不能为空！");
            return false;
        }

        var cartonSpec = $.trim($("#cartonSpec").val());
        if (cartonSpec.length <= 0) {
            $.alertMessage.info("(本地订单包材信息)纸箱规格不能为空！");
            return false;
        }

        var innerBagQua = $.trim($("#innerBagQua").val());
        if (innerBagQua.length <= 0) {
            $.alertMessage.info("(本地订单包材信息)内袋质量标准不能为空！");
            return false;
        }

        var innerBagSpec = $.trim($("#innerBagSpec").val());
        if (innerBagSpec.length <= 0) {
            $.alertMessage.info("(本地订单包材信息)内袋规格不能为空！");
            return false;
        }

        var cartonUseNum = $.trim($("#cartonUseNum").val());
        if (!SSCCommon.NATURAL_NUMBER.test(cartonUseNum)) {
            $.alertMessage.info("本次纸箱需求量不能为空，且只能为整数！");
            return false;
        }
        else if (parseInt(cartonUseNum) > SSCCommon.INT11) {
            $.alertMessage.info("本次纸箱需求量不能超过99999999！");
            return false;
        }

        var innerBagUseNum = $.trim($("#innerBagUseNum").val());
        if (!SSCCommon.NATURAL_NUMBER.test(innerBagUseNum)) {
            $.alertMessage.info("本次内袋需求量不能为空，且只能为整数！");
            return false;
        }
        else if (parseInt(innerBagUseNum) > SSCCommon.INT11) {
            $.alertMessage.info("本次内袋需求量不能超过99999999！");
            return false;
        }
        return true;
    },

    /**
     * 加载包装标准
     * @param data
     */
    changeSelect: function () {
        $('#product').change(function () {
            var detailId = $('#product').find('option:selected').val();
            if (detailId.length == 0) {
                $('#cartonQuaSta').val("");
                $('#cartonSpecSta').val("");
                $('#innerBagQuaSta').val("");
                $('#innerBagSpecSta').val("");
                $('#cartonQua').val("");
                $('#cartonSpec').val("");
                $('#innerBagQua').val("");
                $('#innerBagSpec').val("");
                return;
            }
            var pdCode = $('#pd_' + detailId).val();
            var pdCodeTemp = pdCode.split(',');
            var param = {
                classesCode: pdCodeTemp[0],
                machiningCode: pdCodeTemp[1],
                breedCode: pdCodeTemp[2],
                featureCode: pdCodeTemp[3],
                weightCode: pdCodeTemp[4]
            };
            $('#main-content').postUrl(Main.contextPath + '/SSC11304/findProductPackage', param, function (data) {
                $('#cartonQuaSta').val(data.cartonQuaSta);
                $('#cartonSpecSta').val(data.cartonSpecSta);
                $('#innerBagQuaSta').val(data.innerBagQuaSta);
                $('#innerBagSpecSta').val(data.innerBagSpecSta);
                $('#cartonQua').val(data.cartonQua);
                $('#cartonSpec').val(data.cartonSpec);
                $('#innerBagQua').val(data.innerBagQua);
                $('#innerBagSpec').val(data.innerBagSpec);

            }, {refreshHtml: false});
        });
    }
};

$(document).ready(function () {
    SSC1130404.init();
});