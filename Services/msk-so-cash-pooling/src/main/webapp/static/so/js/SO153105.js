var SO153105 = {
    formId: "so153105Param",
    saveButtonId: "SO153105_SAVE",
    backButtonId: "SO153105_BACK",
    operateTimeT: "#operateTime",
    init: function () {
        this.bindSavebutton();
        this.bindBackbutton();
        this.bindDatePicber(SO153105.operateTimeT);
        this.changeSelect();
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
        $("#" + SO153105.saveButtonId).click(function () {
            SO153105.uploadData();
        });
    },
    bindBackbutton: function () {
        $("#" + SO153105.backButtonId).click(function () {
            $.pdialog.close();
        });
    },
    uploadData: function () {
        $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
            $.alertMessage.close();
            var formData = getFormData($("#" + SO153105.formId));
            var count = 0;
            // Modify for Bug#1652 by li_huiqian at 2016/9/8 start
            // 必须check
            $("#SO153103DataGrid>tbody>tr").each(function () {
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
            //// 参考号 验证
            //var referenceCode = formData.referenceCode;
            //if ((refundType == '0' || refundType == '1') && referenceCode == '') {
            //    count++;
            //    $("#referenceCode").css('background', 'yellow');
            //} else {
            //    $("#referenceCode").css('background', 'none');
            //}
            //// 供应商 验证
            //var suppCode = formData.suppCode;
            //if ((refundType == '0' || refundType == '1' || refundType == '3') && suppCode == '') {
            //    count++;
            //    $("#suppCode").css('background', 'yellow');
            //    $("#suppCodeName").css('background', 'yellow');
            //} else {
            //    $("#suppCode").css('background', 'none');
            //    $("#suppCodeName").css('background', 'none');
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
            //if ((refundType != '' && refundType != '2') && reliefAmount == '') {
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
                var fromUrl = $("#" + SO153105.formId).attr("action");
/** 改善 #3254 Modified by li_huiqian on 2016/10/19 start */
                $('').postUrl(
/** 改善 #3254 Modified by li_huiqian on 2016/10/19 end */
                    fromUrl, formData, function () {
                        $.alertMessage.info("保存成功", function () {
                            var srcPage = $("#srcPage").val();
                            var buyerBillId = $("#buyerBillId").val();
                            var ver = parseInt($("#ver").val());
                            $.alertMessage.close();
                            $.pdialog.close();
                            var fromUrls = fromUrl.split("/");
                            var goFormId = fromUrls[fromUrls.length - 1];
                            $('#main-content').postUrl(Main.contextPath + "/SO153102/init/", {
                                buyerBillId: buyerBillId,
                                srcPage: srcPage,
                                ver: ver + 1
                            });
                        });
                    });
            }
        });
    },
    changeSelect: function () {
        // Moidfy for Bug#1652 by li_huiqian at 2016/9/8 start
        // 费用调整类型变更
        $('#refundType').change(function () {
            var refundType = $('#refundType').val();
            var refundTypeLabel = $("#refundType").parent().prev();
            var reliefAmountLabel = $("#reliefAmount").parent().prev();
            var suppCodeLabel = $("#suppCode").parent().prev();
            var suppCodeNameLabel = $("#suppCodeName").parent().prev();
            var referenceCodeLabel = $("#referenceCode").parent().prev();
            var operateTimeLabel = $("#operateTime").parent().prev();
            $("#SO153105DataGrid>tbody>tr").each(function () {
                // 重置必须输入标志
                $(this).children("td:eq(0)").removeClass("required");
            });
            refundTypeLabel.addClass("required");
            switch (refundType) {
                case "0":
                case "1":
                    reliefAmountLabel.addClass("required");
                    suppCodeLabel.addClass("required");
                    suppCodeNameLabel.addClass("required");
                    referenceCodeLabel.addClass("required");
                    operateTimeLabel.addClass("required");
                    break;
                case "2":
                    operateTimeLabel.addClass("required");
                    break;
                case "3":
                    reliefAmountLabel.addClass("required");
                    suppCodeLabel.addClass("required");
                    suppCodeNameLabel.addClass("required");
                    operateTimeLabel.addClass("required");
                    break;
                case "4":
                    reliefAmountLabel.addClass("required");
                    operateTimeLabel.addClass("required");
                    break;
                default : // case "":
                    break;
            }
            // 减免金额控制
            if (refundType == '2') {
                $("#reliefAmount").val("");
                $("#reliefAmount").attr('disabled', true);
            } else {
                $("#reliefAmount").attr('disabled', false);
            }
            // 供应商控制
            if (refundType != '0' && refundType != '1' && refundType != '3') {
                $("#suppCode").val("");
                $("#suppCodeName").val("");
                $("#suppCode").attr('disabled', true);
                $("#suppCodeName").attr('disabled', true);
            } else {
                $("#suppCode").attr('disabled', false);
                $("#suppCodeName").attr('disabled', false);
            }
        });
        $('#refundType').change();
        // Moidfy for Bug#1652 by li_huiqian at 2016/9/8 start
        // 供应商变更
        $('#suppCode').change(function () {
            var suppCode = $('#suppCode').val();
            var suppCodes = suppCode.split("__");
            $('#suppCodeName').val(suppCodes[2]);
            $('#suppCodeName').attr("title", suppCodes[2]);
        });
    }
}

$(document).ready(function () {
    SO153105.init();
});

