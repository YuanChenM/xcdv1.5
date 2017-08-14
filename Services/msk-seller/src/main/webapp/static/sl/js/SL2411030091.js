var SL2411030091 = {

    formId: "SL2411030091Form",
    saveButtonId: "SL2411030091_SAVE",
    backButtonId: "SL2411030091_BACK",

    init: function () {
        this.bindSavebutton();
        this.bindBackbutton();
    },
    bindSavebutton: function () {
        $("#" + SL2411030091.saveButtonId).click(function () {
            SL2411030091.uploadData();
        });
    },
    bindBackbutton: function () {
        $("#" + SL2411030091.backButtonId).click(function () {
            $.pdialog.close();
        });
    },
    uploadData : function(){
        if($("#" + SL2411030091.formId).validateForm()) {
            var $uploadFile = $("#SL2411030091Form");

            // 添加 jsp_epId2  jsp_slCode
            var jsp_epIdUp2 = $("input#jsp_epId2").val();
            var jsp_slCodeUp = $("input#jsp_slCode").val();
            var jspEpIdUp2 = "<input type='hidden' id='jsp_epIdUp2' name='jsp_epIdUp2' value = '" + jsp_epIdUp2 + "' />";
            var jspSlCodeUp = "<input type='hidden' id='jsp_slCodeUp' name='jsp_slCodeUp' value = '" + jsp_slCodeUp + "' />";
            $uploadFile.append(jspEpIdUp2);
            $uploadFile.append(jspSlCodeUp);
            $.core.uploadForm($uploadFile, true);
            // 去除 jsp_epId2  jsp_slCode
            $("input[name='jsp_epIdUp2']").remove();
            $("input[name='jsp_slCodeUp']").remove();
        }
    }
}

$(document).ready(function () {
    SL2411030091.init();
});

