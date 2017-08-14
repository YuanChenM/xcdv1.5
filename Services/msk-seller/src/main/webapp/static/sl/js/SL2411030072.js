/**
 * Created by fjm on 2016/1/26.
 */
var SL2411030072 = {

    formId : "SL2411030072Form",
    saveButtonId : "SL2411030072_SAVE",
    addButtonId : "SL2411030072_ADD",
    init : function(){
        this.bindSavebutton();
        this.bindAddbutton();
        this.bindDatePicber('#certDate');
        this.closeDate();
    },
    bindDatePicber : function(orderTimeId){
        $(orderTimeId).datepicker({
            showButtonPanel: true,
            dateFormat:'yy-mm-dd',
            changeMonth: true,
            changeYear: true,
            closeText: 'Clear'
        });
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    bindAddbutton : function () {
        $("#" + SL2411030072.addButtonId).click(function(){
            $.pdialog.open("品牌添加",Main.contextPath + "/SL24110300721/init");
        });
    },
    bindSavebutton : function() {
        $("#" + SL2411030072.saveButtonId).click(function() {
            SL2411030072.uploadData();
        });
    },
    uploadData : function(){
        var $uploadFile = $("#SL2411030072Form");
        var certDate=$("#certDate").val();
        if(certDate==null || certDate==""){
            $("#certDate").val($("#certDate").val()+"0000-00-00 00:00:00");
        }else {
            $("#certDate").val($("#certDate").val() + " 00:00:00");
        }
        $.core.uploadForm($uploadFile, true);
    }
}

$(document).ready(function() {
    SL2411030072.init();
});

