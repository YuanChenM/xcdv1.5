/**
 * Created by fjm on 2016/1/27.
 */
var SL24110300501 = {

    formId: "SL24110300501Form",
    saveButtonId: "SL24110300501_SAVE",

    init: function () {
        this.bindSavebutton();
    },

    bindSavebutton: function () {
        $("#" + SL24110300501.saveButtonId).click(function () {
            SL24110300501.saveData();
        });
    },
    saveData: function () {
        if ($("#" + SL24110300501.formId).validateForm()) {
            var $uploadFile = $("#SL24110300501Form");

            // 添加 jsp_epId
            var jsp_epId = $("input#jsp_epId").val();
            var jspEpId = "<input type='hidden' id='jspSL24110300501_epId' name='jspSL24110300501_epId' value = '" + jsp_epId + "' />";
            $uploadFile.append(jspEpId);
            $.core.uploadForm($uploadFile, true);
            // 去除 jsp_epId
            $("input[name='jspSL24110300501_epId']").remove();
        }
    }
}

$(document).ready(function () {
    SL24110300501.init();
});
