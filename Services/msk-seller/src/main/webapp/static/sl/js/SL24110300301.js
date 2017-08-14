/**
 * Created by fjm on 2016/1/27.
 */
var SL24110300301 = {
    saveButtonId : "SL24110300301_SAVE",
    backButtonId : "SL24110300301_BACK",
    init: function () {
        this.bindSavebutton();
        this.bindBackbutton();
    },
    bindSavebutton : function() {
        $("#" + SL24110300301.saveButtonId).click(function() {
            SL24110300301.saveData();
        });
    },
    bindBackbutton : function() {
        $("#" + SL24110300301.backButtonId).click(function() {
            $.pdialog.close();
        });
    },
    saveData : function() {
        var $uploadFile = $("#SL24110300301Form");
        $.core.uploadForm($uploadFile,true);
    }
}
$(document).ready(function() {
    SL24110300301.init();
});


