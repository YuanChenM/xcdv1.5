/**
 * Created by fjm on 2016/1/27.
 */
var SL24110300502 = {

    formId : "SL24110300502Form",
    saveButtonId : "SL24110300502_SAVE",
    backButtonId : "SL24110300502_BACK",

    init : function(){
        this.bindSavebutton();
        this.bindBackbutton();
    },

    bindSavebutton : function() {
        $("#" + SL24110300502.saveButtonId).click(function() {
            SL24110300502.saveData();
        });
    },
    bindBackbutton : function() {
        $("#" + SL24110300502.backButtonId).click(function() {
            $.pdialog.close();
        });
    },
    saveData : function() {
        if ($("#" + SL24110300502.formId).validateForm()) {
            var $uploadFile = $("#SL24110300502Form");

            // 添加 jsp_epId
            var jsp_epId = $("input#jsp_epId").val();
            var jspEpId = "<input type='hidden' id='jspSL24110300502_epId' name='jspSL24110300502_epId' value = '" + jsp_epId + "' />";
            $uploadFile.append(jspEpId);
            $.core.uploadForm($uploadFile, true);
            // 去除 jsp_epId
            $("input[name='jspSL24110300502_epId']").remove();
        }
    }
}

$(document).ready(function() {
    SL24110300502.init();
});
