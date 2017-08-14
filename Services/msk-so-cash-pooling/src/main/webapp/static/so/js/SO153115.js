var SO153115 = {
    formId: "SO153115Param",
    saveButtonId: "SO153115_SAVE",
    backButtonId: "SO153115_BACK",
    operateTimeT: "#operateTime",
    init: function () {
        this.bindSavebutton();
        this.bindBackbutton();
        this.bindDatePicber(SO153115.operateTimeT);
    },
    bindDatePicber: function (timeId) {
        $(timeId).datetimepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            timeFormat: 'HH:mm:ss',
            changeMonth: true,
            changeYear: true,
            showSecond: true,
            timeText: '时间',
            hourText: '小时',
            minuteText: '分钟',
            secondText: '秒钟',
            currentText: '现在时间',
            closeText: '关闭'
        });
        $(timeId).change(function () {
            var str = $(timeId).val();
            if (str.length = 19) {
                // 判断年月日的格式2010-01-01 15:12:10
                var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01]) (0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/;
                if (!reg.test(str)) {
                    $(timeId).val("");
                }
            } else {
                $(timeId).val("");
            }
        });
    },
    bindSavebutton: function () {
        $("#" + SO153115.saveButtonId).click(function () {
            SO153115.uploadData();
        });
    },
    bindBackbutton: function () {
        $("#" + SO153115.backButtonId).click(function () {
            $.pdialog.close();
        });
    },
    uploadData: function () {
        $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
            $.alertMessage.close();
            var formData = getFormData($("#" + SO153115.formId));
            var count = 0;
            // Modify for Bug#1652 by li_huiqian at 2016/9/8 start
            // 必须check
            $("#SO153115DataGrid>tbody>tr").each(function () {
                // 重置输出框颜色
                if ($(this).children("td:eq(0)").hasClass("left")) {
                    $(this).children("td:eq(1)").children("select,input").css("background", "");
                }
                // 如果有必须输入的标志
                if ($(this).children("td:eq(0)").hasClass("required")) {
                    // 检测是否有值
                    var input = $(this).children("td:eq(1)").children("select,input");
                    if (input.val() === "") {
                        input.css("background", "yellow");
                        count++;
                    }
                }
            });
            //// 费用调整类型 验证
            //var refundType = formData.refundType;
            //if (refundType == '') {
            //    count++;
            //    $("#refundType").css('background', 'yellow');
            //} else {
            //    $("#refundType").css('background', 'none');
            //}
            //// 发生日期 验证
            //var operateTime = formData.operateTime;
            //if (operateTime == '') {
            //    count++;
            //    $("#operateTime").css('background', 'yellow');
            //} else {
            //    $("#operateTime").css('background', 'none');
            //}
            //// 减免金额
            //var reliefAmount = formData.reliefAmount;
            //if (reliefAmount == '') {
            //    count++;
            //    $("#reliefAmount").css('background', 'yellow');
            //} else {
            //    $("#reliefAmount").css('background', 'none');
            //}
            // Modify for Bug#1652 by li_huiqian at 2016/9/8 end
            // 必填为空的 提示
            if (count > 0) {
                $.alertMessage.info("必填项不能为空");
            } else {
                var fromUrl = $("#" + SO153115.formId).attr("action");
                $('#main-content').postUrl(
                    fromUrl, formData, function () {
                        $.alertMessage.info("保存成功", function () {
                            $.alertMessage.close();
                            $.pdialog.close();
                            var fromUrls = fromUrl.split("/");
                            var goFormId = fromUrls[fromUrls.length - 1];
                            $('#main-content').postUrl(Main.contextPath + "/" + goFormId + "/init/", {});
                        });
                    });
            }
        });
    }
}

$(document).ready(function () {
    SO153115.init();
});

