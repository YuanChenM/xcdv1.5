/**
 * 产品加工类型维护JS
 *
 * @author yuan_chen
 */

var PD14112301 = {
    formId: "PD14112301Form",
    onClickB: "classesOption",
    saveButtonId: "PD14112301_SAVE",
    closeButtonId: "PD14112301_BACK",
    init: function () {
        this.bindSavebutton();
    },
    bindSavebutton: function () {
        $("#" + PD14112301.saveButtonId).click(function () {
            PD14112301.saveData();
        });
        $("#" + PD14112301.closeButtonId).click(function () {
            $.pdialog.close();
        });
    },
    saveData: function () {
        var validator = mainValidation($("#" + PD14112301.formId));
        var isValid = validator.form();
        if (isValid) {
            $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                $.alertMessage.close();
                formData = getFormData($("#" + PD14112301.formId));
                $('#main-content').postUrl(
                    $("#" + PD14112301.formId).attr("action"), formData, function () {
                        $.pdialog.close();
                        $List_Grid.fnDraw();
                    },{refreshHtml:false});
            });
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    PD14112301.init();
});
