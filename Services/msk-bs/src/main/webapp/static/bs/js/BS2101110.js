var BS2101110 = {
    formId: "BS2101110Form",
    saveButtonId: "BS2101110_SAVE",
    init: function () {
        this.bindSavebutton();
    },
    bindSavebutton: function () {
        $("#" + BS2101110.saveButtonId).click(function () {
            BS2101110.saveData();
        });
    },
    saveData: function () {
        var validator = mainValidation($("#" + BS2101110.formId));
        var isValid = validator.form();
        if (isValid) {
            formData = getFormData($("#" + BS2101110.formId));
            var $uploadFile = $("#BS2101110Form");
            $.core.uploadForm($uploadFile, true);
        };
    },
    setSlCode : function(slCode){
        $("#bsSlCode").val(slCode);
        $.alertMessage.info("保存成功");
        HDF.closeLoadingMask();

    }
}
$(document).ready(function () {
    BS2101110.init();
});


