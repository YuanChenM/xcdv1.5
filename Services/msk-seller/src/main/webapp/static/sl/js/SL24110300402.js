/**
 * Created by fjm on 2016/1/27.
 */
var SL24110300402 = {

    formId : "SL24110300402Form",
    saveButtonId : "SL24110300402_SAVE",
    backButtonId : "SL24110300402_BACK",

    init : function(){
        this.bindSavebutton();
        this.bindBackbutton();
    },

    bindSavebutton : function() {
        $("#" + SL24110300402.saveButtonId).click(function() {
            SL24110300402.saveData();
        });
    },
    bindBackbutton : function() {
        $("#" + SL24110300402.backButtonId).click(function() {
            $.pdialog.close();
        });
    },
    saveData : function() {
        if($("#" + SL24110300402.formId).validateForm()) {
            var $uploadFile = $("#SL24110300402Form");

            // 添加 jsp_epId
            var jsp_epId = $("input#jsp_epId").val();
            var jspEpId = "<input type='hidden' id='jspSL24110300402_epId' name='jspSL24110300402_epId' value = '" + jsp_epId + "' />";
            $uploadFile.append(jspEpId);
            $.core.uploadForm($uploadFile, true);
            // 去除 jsp_epId
            $("input[name='jspSL24110300402_epId']").remove();
        }
    }
}

$(document).ready(function() {
    SL24110300402.init();
});
