/**
 * Created by fjm on 2016/1/30.
 */
var SL2411030090 = {

    formId : "SL2411030090Form",
    saveButtonId : "SL2411030090_SAVE",

    init : function(){
        this.bindSavebutton();

    },

    bindSavebutton : function() {
        $("#" + SL2411030090.saveButtonId).click(function() {
            SL2411030090.saveData();
        });
    },
    saveData : function() {
        var validator = mainValidation($("#" + SL2411030090.formId));
        var isValid = validator.form();
        if (isValid) {
            formData = getFormData($("#" + SL2411030090.formId));
            $('#main-content').postUrl(Main.contextPath+ "/SL2411030090/upload",formData);
        };
    }
}

$(document).ready(function() {
    SL2411030090.init();
});

