/**
 * Created by fjm on 2016/1/27.
 */
var SL24110109 = {
    formId : "SL24110109Form",
    saveButtonId : "SL24110109_UPDATE",
    backButtonId : "SL24110109_BACK",
    deleteButtonId : "SL24110109_DELETE",
    addButtonId : "SL24110109_ADD",

    init : function(){
        this.bindUpdatebutton();
        this.bindBackbutton();
        this.bindDeleteButton();
        this.bindAddbutton();

    },
    bindAddbutton : function () {
        $("#" + SL24110109.addButtonId).click(function(){
            $.pdialog.open("电商成员添加",Main.contextPath + "/SL2411030091/init",{width:400,height:400});
        });
    },
    bindDeleteButton : function(){
        for(var i=0;i<ecTeamSize;i++){
            (function(i){
                $("#SL24110109_DELETE"+i).click(function() {
                    var validator = mainValidation($("#SL24110109Form"+i));
                    var isValid = validator.form();
                    if (isValid) {
                        formData = getFormData($("#SL24110109Form"+i));
                        $('#main-content').postUrl(Main.contextPath+ "/SL24110109/delete",formData,function() {
                            //Modified by xia_xiaojie on 2016/6/23. Modified start.
                            //$('#main-content').postUrl(Main.contextPath + "/SL241101/init");
                            $('#main-content').postUrl(Main.contextPath + "/SL241101/initShow");
                            //Modified end.
                        });
                    }
                });
            })(i);
        }
    },

    bindUpdatebutton : function() {
        $("#" + SL24110109.updateButtonId).click(function() {
            SL24110109.updateData();
        });
    },
    bindBackbutton : function() {
        for(var i=0;i<ecTeamSize;i++){+
            (function(i){
                $("#SL24110109_UPDATE"+i).click(function() {
                    if($("#SL24110109Form"+i).validateForm()) {
                        formData = getFormData($("#SL24110109Form"+i));
                        var $uploadFile = $("#SL24110109Form"+i);
                        $.core.uploadForm($uploadFile, true);
                    }
                });
            })(i);
        }
    },
    updateData : function() {
        var validator = mainValidation($("#" + SL24110109.formId));
        var isValid = validator.form();
        if (isValid) {

            formData = getFormData($("#" + SL24110109.formId));
            $('#main-content').postUrl(
                $("#" + SL24110109.formId).attr("action"),
                formData,
                function() {
                    $('#main-content').postUrl(Main.contextPath + "/SL241101/init");
                });
        }
    }
}

$(document).ready(function() {
    SL24110109.init();
});
