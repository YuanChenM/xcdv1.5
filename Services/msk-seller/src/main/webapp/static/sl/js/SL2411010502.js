/**
 * Created by fjm on 2016/1/27.
 */
var SL2411010502 = {

    formId : "SL2411010502Form",
    updateButtonId : "SL2411010502_UPDATE",
    init : function(){
        this.bindUpdatebutton();
    },
    bindUpdatebutton : function() {
        $("#" + SL2411010502.updateButtonId).click(function() {
            SL2411010502.updateData();
        });
    },
    updateData : function() {
        if($("#" + SL2411010502.formId).validateForm()) {
            var $uploadFile = $("#SL2411010502Form");
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
    SL2411010502.init();
});
