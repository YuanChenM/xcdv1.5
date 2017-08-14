var SL2411030081 = {

    formId: "SL2411030081Form",
    saveButtonId: "SL2411030081_SAVE",
    backButtonId: "SL2411030081_BACK",

    init: function () {
        this.bindSavebutton();
        this.bindBackbutton();
    },
    bindSavebutton: function () {
        $("#" + SL2411030081.saveButtonId).click(function () {
            SL2411030081.uploadData();
        });
    },
    bindBackbutton: function () {
        $("#" + SL2411030081.backButtonId).click(function () {
            $.pdialog.close();
        });
    },
    uploadData : function() {
        if ($("#" + SL2411030081.formId).validateForm()) {
            var $uploadFile = $("#SL2411030081Form");
            // 添加 jsp_epId2
            var jsp_epIdUp2 = $("input#jsp_epId2").val();
            var jspEpIdUp2 = "<input type='hidden' id='jsp_epIdUp2' name='jsp_epIdUp2' value = '" + jsp_epIdUp2 + "' />";
            $uploadFile.append(jspEpIdUp2);
            $.core.uploadForm($uploadFile, true);
            // 去除 jsp_epId2
            $("input[name='jsp_epIdUp2']").remove();
        }
    }
}

$(document).ready(function () {
    SL2411030081.init();
});

