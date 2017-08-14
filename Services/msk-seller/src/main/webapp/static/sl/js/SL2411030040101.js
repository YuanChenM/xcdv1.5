/**
 * Created by fjm on 2016/1/27.
 */
var SL2411030040101 = {

    formId : "SL2411030040101Form",
    saveButtonId : "SL2411030040101_SAVE",
    backButtonId : "SL2411030040101_BACK",

    init : function(){
        this.bindSavebutton();
        this.bindBackbutton();
        this.bindDatePicber('#addcertDate');
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
    bindSavebutton : function() {
        $("#" + SL2411030040101.saveButtonId).click(function() {
            SL2411030040101.saveData();
        });
    },
    bindBackbutton : function() {
        $("#" + SL2411030040101.backButtonId).click(function() {
            $.pdialog.close();
        });
    },
    saveData : function() {
        if($("#" + SL2411030040101.formId).validateForm()) {
            var addcertDate = $("#addcertDate").val();
            if(addcertDate=="" || addcertDate==null){
                //$("input[name='certDate']").remove();
                $.alertMessage.info("荣誉证书发证日期不能为空");
                return;
                //$("#addcertDate").val($("#addcertDate").val() + "0000-00-00 00:00:00");
            }else {
                $("#addcertDateTemp").val($("#addcertDate").val() + " 00:00:00");
            }
            formData = getFormData($("#" + SL2411030040101.formId));
            var $uploadFile = $("#SL2411030040101Form");

            // 添加 jsp_epId2
            var jsp_epIdUp2 = $("input#jsp_epId2").val();
            var jspEpIdUp2  =  "<input type='hidden' id='jsp_epIdUp2' name='jsp_epIdUp2' value = '"+jsp_epIdUp2+"' />";
            $uploadFile.append(jspEpIdUp2);
            $.core.uploadForm($uploadFile, true);
            // 去除 jsp_epId
            $("input[name='jsp_epIdUp2']").remove();
        };
    }
}

$(document).ready(function() {
    SL2411030040101.init();
});
