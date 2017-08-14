/**
 * Created by fjm on 2016/1/28.
 */

var SL24110101 = {

    formId : "SL24110101Form",
    saveButtonId : "SL24110101_SAVE",

    init : function(){
        this.bindSavebutton();
        this.passwordCheck();
    },

    bindSavebutton : function() {
        $("#" + SL24110101.saveButtonId).click(function() {
            SL24110101.saveData();
        });
    },
    saveData : function() {
        if($("#" + SL24110101.formId).validateForm()) {
            formData = getFormData($("#" + SL24110101.formId));
            var $uploadFile = $("#SL24110101Form");
            $.core.uploadForm($uploadFile, true);
        };
    },
    passwordCheck: function () {
        $("#accountPsd").html("******");
    }
}

$(document).ready(function() {
    SL24110101.init();
});


