/**
 * Created by fjm on 2016/1/27.
 */
var SL24110106 = {
    updateButtonId : "SL24110106_UPDATE",
    init : function(){
        this.bindUpdatebutton();
    },

    bindUpdatebutton : function() {
        $("#" + SL24110106.updateButtonId).click(function() {
            SL24110106.updateData();
        });
    },
    updateData : function() {
        if ($("#SL24110106Form").validateForm()) {
            var $uploadFile = $("#SL24110106Form");
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
    SL24110106.init();
});
