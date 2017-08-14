/**
 * Created by fjm on 2016/1/27.
 */
var SL24110300401 = {

    formId : "SL24110300401Form",
    saveButtonId : "SL24110300401_SAVE",
    addButtonId : "SL24110300401_ADD",

    init : function(){
        this.bindSavebutton();
        //this.bindAddbutton();
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
    bindSavebutton : function() {
        $("#" + SL24110300401.saveButtonId).click(function() {
            SL24110300401.saveData();
        });
    },
    saveData : function() {
        if($("#" + SL24110300401.formId).validateForm()){
            var certDate = $("#certDate").val();
            if(certDate=="" || certDate==null){
                //$("input[name='certDate']").remove();
                $.alertMessage.info("荣誉证书发证日期不能为空");
                return;
                //$("#certDate").val($("#certDate").val() + "0000-00-00 00:00:00");
            }else {
                $("#certDateTemp").val($("#certDate").val() + " 00:00:00");
            }
            formData = getFormData($("#" + SL24110300401.formId));
            var $uploadFile = $("#SL24110300401Form");

            // 添加 jsp_epId
            var jsp_epId = $("input#jsp_epId").val();
            var jspEpId  = "<input type='hidden' id='jspSL24110300401_epId' name='jspSL24110300401_epId' value = '"+jsp_epId+"' />";
            $uploadFile.append(jspEpId);
            $.core.uploadForm($uploadFile, true);
            // 去除 jsp_epId
            $("input[name='jspSL24110300401_epId']").remove();
        };
    }
}

$(document).ready(function() {
    SL24110300401.init();
});
