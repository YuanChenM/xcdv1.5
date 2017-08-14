/**
 * 产品基础数据列表JS
 *
 * @author xhy
 */
var PD14114601 = {
    saveButtonId: "PD14114601_SAVE",
    formId: "PD14114601Form",
    init: function () {
        this.bindSaveButton();
      /*  this.clickFile();*/
    },
    bindSaveButton: function () {
        $("#" + PD14114601.saveButtonId).click(function () {
            var validator = mainValidation($("#" + PD14114601.formId));
            var isValid = validator.form();
            if (isValid) {
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    var $uploadFile = $("#" + PD14114601.formId);
                    $.core.uploadForm($uploadFile, true);
                });
            }
        });
    },
   /* clickFile :　function(){
        $("input[type='file']").bind("change",(function(){
            var thisName = $(this).attr("name");
        }));
    }*/
}
$(document).ready(function () {
    // 初始化调用
    PD14114601.init();
});
