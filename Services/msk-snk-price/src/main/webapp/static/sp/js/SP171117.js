/**
 * 供应商价格申报详细JS
 *
 * @author zhouling
 */
var isExeed = "0";
var SP171117 = {
    saveButtonId: "SP171117_SAVE",
    formId: "SP171117Form",
    init: function () {
        this.changeSelect();
        this.bindSavebutton();
    },
    changeSelect: function () {
        //价格设置监听事件
        var defaultWayGrade = $('#defaultWayGrade').val();//可编辑通道
        var priceInput = $('#price' + defaultWayGrade);
        var wayGradePercent = $('#wayGradePercent').val();//可编辑通道平衡系数
        priceInput.change(function () {
            var priceValue5 = parseFloat(priceInput.val());
            var downPrice = parseFloat($('#downPrice').val());
            var upPrice = parseFloat($('#upPrice').val());
            priceInput.val(priceValue5);
            if (isNaN(priceValue5)) {
                priceValue5 = 0;
                priceInput.val(priceValue5);
            }
            //判断报价金额是否超出上下限
            if ("" != downPrice && "" != upPrice && 0 != downPrice && 0 != upPrice) {
                if (parseFloat(upPrice) < parseFloat(priceValue5) || parseFloat(priceValue5) < parseFloat(downPrice)) {
                    priceInput.css("background-color", "#ff0000");
                    isExeed = "1";
                    $.alertMessage.confirm("当前报价超出上下限价，是无效报价。你确定要保留当前数据吗？", function () {
                        $.alertMessage.close();
                    },function(){
                        priceInput.val("");
                        priceInput.focus();
                        $.alertMessage.close();
                    })
                    priceInput.focus();
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
                    var tempPrice=0;
                    if (i == 0 || i == 1 || i == 4 || i == 7) {
                      var  price = (parseFloat($('#SP171117Table tr:eq(' + trId + ') td:nth-child(4)').html()) * priceValue5 / wayGradePercent);//该列平衡系数/100*输入价格/输入价格平衡系数*100
                        tempPrice = Math.round(price*10000)/10000;// 保留4位小数点
                        price = Math.round(price*100)/100;// 保留2位小数点
                        if(isNaN(price)){
                            price = 0;
                        }
                        if(isNaN(tempPrice)){
                            tempPrice =0
                        }
                        $('#SP171117Table tr:eq(' + trId + ') td:nth-child(6)').html(price);
                    } else {
                       var price = (parseFloat($('#SP171117Table tr:eq(' + trId + ') td:nth-child(3)').html()) * priceValue5 / wayGradePercent);//该列平衡系数/100*输入价格/输入价格平衡系数*100
                        tempPrice = Math.round(price*10000)/10000;// 保留4位小数点
                        price = Math.round(price*100)/100;// 保留2位小数点
                        if(isNaN(price)){
                            price = 0;
                        }
                        if(isNaN(tempPrice)){
                            tempPrice =0
                        }
                        $('#SP171117Table tr:eq(' + trId + ') td:nth-child(5)').html(price);
                    }
                    $('#price' + i).val(price);
                    $('#tempPrice' + i).val(tempPrice);
                }else{
                    var val= parseFloat(priceInput.val())
                     var editGrade =   Math.round(val*10000)/10000;
                    $('#tempPrice'+ i).val(editGrade);
                }
            }
        });
    },
    bindSavebutton: function () {
        $("#" + SP171117.saveButtonId).click(function () {
            var defaultWayGrade = $('#defaultWayGrade').val();//可编辑通道
            var priceValue5 = $('#price' + defaultWayGrade).val();
            if(!$("input[type='checkbox']").is(':checked')){
                $.alertMessage.warn("请选择参与的分销通道");
                return;
            }
            if(priceValue5 ==""){
                $.alertMessage.warn("请填写第"+defaultWayGrade+"通道的价格");
                return;
            }
            var validator = mainValidation($("#" + SP171117.formId));
            var isValid = validator.form();

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
                    $("#overDataFlag").val(1);
                    var formData = getFormData($("#" + SP171117.formId));
                    $('#main-content').postUrl($("#" + SP171117.formId).attr("action"), formData, function (data) {
                        $('#main-content').postUrl(Main.contextPath + "/SP171117/init/", formData)

                    }, {refreshHtml: false});
                });
            }
        });
    }
};
$(document).ready(function () {
    // 初始化调用
    SP171117.init();
});
