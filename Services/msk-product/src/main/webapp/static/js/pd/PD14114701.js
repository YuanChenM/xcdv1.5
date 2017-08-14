/**
 * 产品原种饲养标准指标JS
 *
 * @author FJM
 */
var PD14114701 = {
    saveButtonId: "PD14114701_SAVE",
    formId: "PD14114701Form",
    formId2: "PD14114701Form",
    init: function () {
        this.bindSaveButton();
       // this.bindSaveImg();
    },
   /* bindSaveImg: function () {
        $("#Button1").click(function () {
            debugger;
            formData = getFormData($("#" + PD14114701.formId2));
        var $uploadFile = $("#PD141147ImgForm");
        $.core.uploadForm($uploadFile, true);  })
    },*/
    bindSaveButton: function () {
        $("#" + PD14114701.saveButtonId).click(function () {
            debugger;
            var validator = mainValidation($("#" + PD14114701.formId));
            var isValid = validator.form();
            if (isValid) {
                debugger;
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    formData = getFormData($("#" + PD14114701.formId));
                    var $uploadFile = $("#PD14114701Form");
                    $.core.uploadForm($uploadFile, true);
                    /*$('#main-content').postUrl($("#" + PD14114701.formId).attr("action"), formData, function () {
                        $.alertMessage.info("数据保存成功!");
                    },{refreshHtml:false});*/
                });
            }
        });
    },

}
$(document).ready(function() {
    // 初始化调用
    PD14114701.init();
});
