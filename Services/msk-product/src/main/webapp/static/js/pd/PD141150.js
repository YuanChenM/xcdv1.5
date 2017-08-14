/**
 * 产品基础数据列表JS
 *
 * @author fjm
 */
var PD141150 = {
    saveButtonId: "PD141150_SAVE",
    formId: "PD141150Form",
    init: function () {
        $('.tree').treegrid();
        this.bindSaveButton();
    },
    bindSaveButton: function () {
        $("#" + PD141150.saveButtonId).click(function () {
            var validator = mainValidation($("#" + PD141150.formId));
            var isValid = validator.form();
            if (isValid) {
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    formData = getFormData($("#" + PD141150.formId));
                    $('#main-content').postUrl($("#" + PD141150.formId).attr("action"), formData, function () {
                        $.alertMessage.info("数据保存成功!");
                    },{refreshHtml:false});
                });
            }
        });
    },
}
$(document).ready(function() {
    // 初始化调用
    PD141150.init();
});

