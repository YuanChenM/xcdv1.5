/**
 * 菜场商户新增
 * Created by zhou_yajun on 2016/7/13.
 */


var BY121410 = {
    saveButton: "BY121410_SAVE",
    initDataGrid: function () {
        this.marketSaveHandler();
    },
    //批发市场商户信息保存
    marketSaveHandler: function () {
        $("#" + BY121410.saveButton).click(function () {
            if ($("#BY121410SaveForm").validateForm()) {
                if (!BY121410.showMessage()) {
                    return false;
                }
                var merchantType = $(".merchantType option:selected").val();
                if(merchantType == ""){
                    $.alertMessage.info("商户类型不能为空 !");
                    return false;
                }
                var formData = getFormData($("#BY121410SaveForm"));
                $('#main-content').postUrl($("#BY121410SaveForm").attr("action"), formData, function (data) {
                    $.pdialog.close();
                    if (data == "add") {
                        $.alertMessage.info("商户信息新增保存成功。", function () {
                            $.alertMessage.close();
                            BY121409.BY121409_Grid.fnDraw();
                        });
                    } else if (data == "modify") {
                        $.alertMessage.info("商户信息编缉保存成功。", function () {
                            $.alertMessage.close();
                            BY121409.BY121409_Grid.fnDraw();
                        });
                    }
                }, {refreshHtml: false});

            }
        });
    },
    //简单必须项check
    showMessage: function () {
        var regDecimal = /^\d{1,8}(\.\d{1,2})?$/;
        var merchantName = $("#merchantName").val();
        var totalMerchant = $("#totalMerchant").val();
        var annualTurnover = $("#annualTurnover").val();
        if (!regDecimal.test(annualTurnover) && annualTurnover != '') {
            $.alertMessage.info("年销售额输入错误", function () {
                $.alertMessage.close();
            });
            return false;
        }
        return true;
    }
}

$(document).ready(function () {
    BY121410.initDataGrid();
})