var SO153142 = {
    formId: "SO153142Param",
    saveButtonId: "SO153142_SAVE",
    backButtonId: "SO153142_BACK",
    operateTimeT: "#operateTime",
    init: function () {
        this.bindSavebutton();
        this.bindBackbutton();
        this.bindDatePicber(SO153142.operateTimeT)
    },
    bindDatePicber: function (timeId) {
        $(timeId).datepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            changeMonth: true,
            changeYear: true
        });
    },
    bindSavebutton: function () {
        $("#" + SO153142.saveButtonId).click(function () {
            SO153142.uploadData();
        });
    },
    bindBackbutton: function () {
        $("#" + SO153142.backButtonId).click(function () {
            $.pdialog.close();
        });
    },
    uploadData: function () {
        $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
            $.alertMessage.close();
            var formData = getFormData($("#" + SO153142.formId));
            var count = 0;
            // 支付类型 验证
            var paidType = formData.paidType;
            if (paidType == '') {
                count++;
                $("#paidType").css('background', 'yellow');
            } else {
                $("#paidType").css('background', 'none');
            }
            // 期初金额 验证
            var periodAmount = formData.periodAmount;
            if (periodAmount == '') {
                count++;
                $("#periodAmount").css('background', 'yellow');
            } else {
                $("#periodAmount").css('background', 'none');
            }
            // 支付流水号 验证
            var paidSeq = formData.paidSeq;
            if (paidSeq == '') {
                count++;
                $("#paidSeq").css('background', 'yellow');
            } else {
                $("#paidSeq").css('background', 'none');
            }
            // 发生日期 验证
            var operateTime = formData.operateTime;
            if (operateTime == '') {
                count++;
                $("#operateTime").css('background', 'yellow');
            } else {
                $("#operateTime").css('background', 'none');
            }
            // 必填为空的 提示
            if (count > 0) {
                $.alertMessage.info("必填项不能为空");
            } else {
                var fromUrl = $("#" + SO153142.formId).attr("action");
                $('#main-content').postUrl(
                    fromUrl, formData, function () {
                        $.alertMessage.info("保存成功", function () {
                            $.alertMessage.close();
                            $.pdialog.close();
                            $('#main-content').postUrl(Main.contextPath + "/SO153141/init/", {});
                        });
                    });
            }
        });
    }
}

$(document).ready(function () {
    SO153142.init();
});

