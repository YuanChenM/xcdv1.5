/**
 * 产品基础数据列表JS
 *
 * @author fjm
 */
var PD141152 = {
    saveButtonId: "PD141152_SAVE",
    formId: "PD141152Form",
    init: function () {
        $('.tree').treegrid();
        this.bindSaveButton();
    },
    bindSaveButton: function () {
        $("#" + PD141152.saveButtonId).click(function () {
            var validator = mainValidation($("#" + PD141152.formId));
            var isValid = validator.form();
            if (isValid) {
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    formData = getFormData($("#" + PD141152.formId));
                    $('#main-content').postUrl($("#" + PD141152.formId).attr("action"), formData, function () {
                        $.alertMessage.info("数据保存成功!");
                    },{refreshHtml:false});
                });
            }
        });
    },
}
$(document).ready(function() {
    // 初始化调用
    PD141152.init();
});
