/**
 * Created by fjm on 2016/1/27.
 */
var SL241101030101 = {
    saveButtonId : "SL241101030101_SAVE",
    backButtonId : "SL241101030101_BACK",
    init: function () {
        this.bindSavebutton();
        this.bindBackbutton();
    },
    bindSavebutton : function() {
        $("#" + SL241101030101.saveButtonId).click(function() {
            SL241101030101.saveData();
        });
    },
    bindBackbutton : function() {
        $("#" + SL241101030101.backButtonId).click(function() {
            $.pdialog.close();
        });
    },
    saveData : function() {
        if ($("#SL241101030101Form").validateForm()) {
            var $uploadFile = $("#SL241101030101Form");
            // 添加 jsp_epId
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
    SL241101030101.init();
});


