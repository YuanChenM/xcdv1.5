/**
 * Created by fjm on 2016/1/27.
 */
var SL241103006 = {

    formId : "SL241103006Form",
    saveButtonId : "SL241103006_SAVE",
    backButtonId : "SL241103006_BACK",

    init : function(){
        this.bindSavebutton();
        this.bindBackbutton();
    },

    bindSavebutton : function() {
        $("#" + SL241103006.saveButtonId).click(function() {
            SL241103006.saveData();
        });
    },
    bindBackbutton : function() {
        $("#" + SL241103006.backButtonId).click(function() {
            $.pdialog.close();
        });
    },
    saveData : function() {
        if ($("#" + SL241103006.formId).validateForm()) {
            var $uploadFile = $("#SL241103006Form");

            // 添加 jsp_epId
            var jsp_epId = $("input#jsp_epId").val();
            var jspEpId = "<input type='hidden' id='jspSL241103006_epId' name='jspSL241103006_epId' value = '" + jsp_epId + "' />";
            $uploadFile.append(jspEpId);
            $.core.uploadForm($uploadFile, true);
            // 去除 jsp_epId
            $("input[name='jspSL241103006_epId']").remove();
        }
    }
}

$(document).ready(function() {
    SL241103006.init();
});
