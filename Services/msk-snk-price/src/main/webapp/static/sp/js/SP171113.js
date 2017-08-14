/**
 * 价盘详情JS
 *
 */
var SP171113 = {
    saveButtonId: "SP171113_SAVE",
    formId: "SP171113Form",
    init: function () {
        this.changeSelect();
        this.bindSavebutton();
    },
    changeSelect: function () {
        //价格设置监听事件
        $("input[name='wayGradePriceArray']").keydown(function (evt) {
            if (evt.keyCode == 13 || evt.keyCode == 37 || evt.keyCode == 39) {
                //获取下标值
                var indexNum = $(this).attr('indexNum');
                var priceValue = parseFloat($(this).val());
                if (isNaN(priceValue)) {
                    priceValue = 0;
                }
                priceValue = parseFloat(priceValue);
                var theNumber;
                //根据下标值得出平衡系数
                if (indexNum == 1 || indexNum == 4 || indexNum == 7) {
                    theNumber = parseFloat($('#SP171113Table tr:eq(' + indexNum + ') td:nth-child(3)').html());
                }else{
                    theNumber = parseFloat($('#SP171113Table tr:eq(' + indexNum + ') td:nth-child(2)').html());
                }

                for (var i = 0; i < 10; i++) {
                    var trId = i + 1;
                    var price = 0;
                    //该列平衡系数*输入价格/输入价格平衡系数
                    if (i == 0 || i == 3 || i == 6) {
                        price = (parseFloat($('#SP171113Table tr:eq(' + trId + ') td:nth-child(3)').html()) * priceValue / theNumber).toFixed(2);
                        $('#SP171113Table tr:eq(' + trId + ') td:nth-child(5)').html(price);
                    } else {
                        price = (parseFloat($('#SP171113Table tr:eq(' + trId + ') td:nth-child(2)').html()) * priceValue / theNumber).toFixed(2);
                        $('#SP171113Table tr:eq(' + trId + ') td:nth-child(4)').html(price);
                    }
                    $('#wayGradePrice' + trId).val(price);

                }
            }
        });
    },
    bindSavebutton: function () {
        $("#" + SP171113.saveButtonId).click(function () {
            var validator = mainValidation($("#" + SP171113.formId));
            var isValid = validator.form();
            if (isValid) {
                var message = "你确定要保存当前数据吗？";
                $.alertMessage.confirm(message, function () {
                    $.alertMessage.close();
                    var formData = getFormData($("#" + SP171113.formId));
                    $('#main-content').postUrl($("#" + SP171113.formId).attr("action"), formData, function (data) {
                    }, {refreshHtml: false});
                });
            }
        });
    }
};
$(document).ready(function () {
    // 初始化调用
    SP171113.init();
});
