
var SP171195 = {
    formId:"SP171195Form",
    saveButtonId:"SP171195_SAVE",
    init: function () {
        SP171195.bindSaveButton();
    },
    bindSaveButton : function(){
        $("#" + SP171195.saveButtonId).click(function () {
            formData = getFormData($("#" + SP171195.formId));
            // validator
            var lgcsCode = $("#lgcsCode").val();
            var securityRatio = $("#securityRatio").val();
            var sellForecastRatio = $("#sellForecastRatio").val();
            var reg = /^[0-9]+([.]{1}[0-9]+){0,1}$/;
            var message = "";
            // 物流区验证
            if (lgcsCode == 0) {
                message += "请选择物流区"+"<br>";
            }
            // 库存平衡系数验证
            if (securityRatio == "") {
                message += "请输入下月度库存平衡系数"+"<br>";
            } else if (!reg.test(securityRatio)){
                message += "下月度库存平衡系数格式不正确"+"<br>";
            } else if (parseFloat(securityRatio) > 1.3 || parseFloat(securityRatio) < 0.7) {
                message += "下月度库存平衡系数范围超出"+"<br>";
            }
            // 销量平衡系数验证
            if (sellForecastRatio == "") {
                message += "请输入下月度销量平衡系数"+"<br>";
            } else if (!reg.test(sellForecastRatio)){
                message += "下月度销量平衡系数格式不正确"+"<br>";
            } else if (parseFloat(sellForecastRatio) > 1.1 || parseFloat(sellForecastRatio) < 0.9){
                message += "下月度销量平衡系数范围超出"+"<br>";
            }
            if (message != ""){
                $.alertMessage.warn(message);
                return;
            }
            $('#main-content').postUrl($("#" + SP171195.formId).attr("action"),formData,function () {
                $.alertMessage.info("该区域需求预测系数设定完成!");
            });
        });
    }
}
$(document).ready(function() {
    // 初始化调用
    SP171195.init();
    //$.core.sleep(1000);
});
