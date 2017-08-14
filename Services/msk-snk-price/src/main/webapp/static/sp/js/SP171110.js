/**
 * 供应商价格申报详细JS
 *
 * @author zhouling
 */
var isExeed = "0";
var SP171110 = {
    saveButtonId: "SP171110_SAVE",
    formId: "SP171110Form",
    init: function () {
        this.changeSelect();
        this.bindSavebutton();
        this.bindOther();
    },
    changeSelect: function () {
        //价格设置监听事件
        var defaultWayGrade = $('#defaultWayGrade').val();//可编辑通道
        var priceInput = $('#price' + defaultWayGrade);
        var wayGradePercent = $('#wayGradePercent').val();//可编辑通道平衡系数
        priceInput.keyup(function () {
            var priceValue5 = parseFloat(priceInput.val());
            var downPrice = parseFloat($('#downPrice').val());
            var upPrice = parseFloat($('#upPrice').val());
            if (isNaN(priceValue5)) {
                priceValue5 = 0;
            }
            //判断报价金额是否超出上下限
            if ("" != downPrice && "" != upPrice && 0 != downPrice && 0 != upPrice) {
                if (parseFloat(upPrice) < parseFloat(priceValue5) || parseFloat(priceValue5) < parseFloat(downPrice)) {
                    priceInput.css("background-color", "#ff0000");
                    isExeed = "1";
                } else {
                    priceInput.css("background-color", "#ffffff");
                    isExeed = "0";
                }
            } else {
                priceInput.css("background-color", "#ffffff");
                isExeed = "0";
            }
            if (isNaN(priceValue5)) {
                priceValue5 = 0;
            }
            priceValue5 = parseFloat(priceValue5);
            for (var i = 0; i < 10; i++) {
                if (i != defaultWayGrade) {
                    var trId = i + 1;
                    var price = 0;
                    if (i == 0 || i == 1 || i == 4 || i == 7) {
                        price = (parseFloat($('#SP171110Table tr:eq(' + trId + ') td:nth-child(4)').html()) * priceValue5 / wayGradePercent).toFixed(2);//该列平衡系数/100*输入价格/输入价格平衡系数*100
                        $('#SP171110Table tr:eq(' + trId + ') td:nth-child(6)').html(price);
                    } else {
                        price = (parseFloat($('#SP171110Table tr:eq(' + trId + ') td:nth-child(3)').html()) * priceValue5 / wayGradePercent).toFixed(2);//该列平衡系数/100*输入价格/输入价格平衡系数*100
                        $('#SP171110Table tr:eq(' + trId + ') td:nth-child(5)').html(price);
                    }
                    $('#price' + i).val(price);
                }
            }
        });
        priceInput.mouseup(function () {
            var priceValue5 = parseFloat(priceInput.val());
            var downPrice = parseFloat($('#downPrice').val());
            var upPrice = parseFloat($('#upPrice').val());
            if (isNaN(priceValue5)) {
                priceValue5 = 0;
            }
            //判断报价金额是否超出上下限
            if ("" != downPrice && "" != upPrice && 0 != downPrice && 0 != upPrice) {
                if (parseFloat(upPrice) < parseFloat(priceValue5) || parseFloat(priceValue5) < parseFloat(downPrice)) {
                    priceInput.css("background-color", "#ff0000");
                    isExeed = "1";
                } else {
                    priceInput.css("background-color", "#ffffff");
                    isExeed = "0";
                }
            } else {
                priceInput.css("background-color", "#ffffff");
                isExeed = "0";
            }
            if (isNaN(priceValue5)) {
                priceValue5 = 0;
            }
            priceValue5 = parseFloat(priceValue5);
            for (var i = 0; i < 10; i++) {
                if (i != defaultWayGrade) {
                    var trId = i + 1;
                    var price = 0;
                    if (i == 0 || i == 1 || i == 4 || i == 7) {
                        price = (parseFloat($('#SP171110Table tr:eq(' + trId + ') td:nth-child(4)').html()) * priceValue5 / wayGradePercent).toFixed(2);//该列平衡系数/100*输入价格/输入价格平衡系数*100
                        $('#SP171110Table tr:eq(' + trId + ') td:nth-child(6)').html(price);
                    } else {
                        price = (parseFloat($('#SP171110Table tr:eq(' + trId + ') td:nth-child(3)').html()) * priceValue5 / wayGradePercent).toFixed(2);//该列平衡系数/100*输入价格/输入价格平衡系数*100
                        $('#SP171110Table tr:eq(' + trId + ') td:nth-child(5)').html(price);
                    }
                    $('#price' + i).val(price);
                }
            }
        });
    },
    bindOther: function () {
        $("#SP171110_OTHER").click(function () {
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  Start */
            var data = new Object();
            data['slCode'] = $("#sellerCode").val(),
                data['pdCode'] = $("#pdCode").val(),
                data['lgcsCode'] = $("#lgcsCode").val(),
                data['pricePeriod'] = $("#pricePeriod").val(),
                data['pageType'] = "0"
            Main.detailWindow(Main.contextPath + "/SP171111/init/", data, "供应商数量申报详细");
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  end */
        });
    },

    bindSavebutton: function () {
        $("#" + SP171110.saveButtonId).click(function () {

            if(!$("input[type='checkbox']").is(':checked')){
                $.alertMessage.warn("请选择参与的分销通道");
                return;
            }
            var validator = mainValidation($("#" + SP171110.formId));
            var isValid = validator.form();
            var defaultWayGrade = $('#defaultWayGrade').val();//可编辑通道
            var priceValue5 = $('#price' + defaultWayGrade).val();
            if (priceValue5.length > 9) {
                $.alertMessage.warn("申报价格过大");
                return;
            }
            if (isValid) {
                var message = "你确定要保存当前数据吗？";
                if (isExeed == "1") {
                    message = "当前报价超出上下限价，是无效报价。你确定要保存当前数据吗？";
                }
                $.alertMessage.confirm(message, function () {
                    $.alertMessage.close();
                    var formData = getFormData($("#" + SP171110.formId));
                    $('#main-content').postUrl($("#" + SP171110.formId).attr("action"), formData, function (data) {
                        $('#main-content').postUrl(Main.contextPath + "/SP171110/init/", formData)
                    }, {refreshHtml: false});
                });
            }
        });
    }
};
$(document).ready(function () {
    // 初始化调用
    SP171110.init();
});
