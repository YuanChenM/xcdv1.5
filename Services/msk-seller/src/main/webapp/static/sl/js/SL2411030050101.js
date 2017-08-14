/**
 * Created by fjm on 2016/1/27.
 */
var SL2411030050101 = {

    formId : "SL2411030050101Form",
    saveButtonId : "SL2411030050101_SAVE",
    backButtonId : "SL2411030050101_BACK",

    init : function(){
        this.bindSavebutton();
        this.bindBackbutton();
    },

    bindSavebutton : function() {
        $("#" + SL2411030050101.saveButtonId).click(function() {
            SL2411030050101.saveData();
        });
    },
    bindBackbutton : function() {
        $("#" + SL2411030050101.backButtonId).click(function() {
            $.pdialog.close();
        });
    },
    saveData : function() {
        if($("#" + SL2411030050101.formId).validateForm()) {
            var $uploadFile = $("#SL2411030050101Form");
            // 添加 jsp_epId2
            var jsp_epIdUp2 = $("input#jsp_epId2").val();
            var jspEpIdUp2 = "<input type='hidden' id='jsp_epIdUp2' name='jsp_epIdUp2' value = '" + jsp_epIdUp2 + "' />";
            $uploadFile.append(jspEpIdUp2);
            $.core.uploadForm($uploadFile, true);
            // 去除 jsp_epId
            $("input[name='jsp_epIdUp2']").remove();
        }
    }
}

$(document).ready(function() {
    SL2411030050101.init();
});
