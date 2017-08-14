/**
 * Created by fjm on 2016/1/30.
 */
var SL241103009 = {

    formId : "SL241103009Form",
    saveButtonId : "SL241103009_SAVE",
    addButtonId : "SL241103009_ADD",
    init : function(){
        this.bindSavebutton();
        this.bindAddbutton();
    },
    bindAddbutton : function(){

        $("#"+SL241103009.addButtonId).click(function(){
                $.pdialog.open("添加电商团队成员",Main.contextPath+"/SL2411030091/init");
            }
        )
    },
    bindSavebutton : function() {
        $("#" + SL241103009.saveButtonId).click(function() {
            SL241103009.uploadData();
        });
    },
    uploadData : function() {
        if ($("#" + SL241103009.formId).validateForm()) {
            var $uploadFile = $("#SL241103009Form");

            // 添加 jsp_epId  jsp_slCode
            var jsp_epId = $("input#jsp_epId").val();
            var jsp_slCode = $("input#jsp_slCode").val();
            var jspEpId = "<input type='hidden' id='jspSL241103009_epId' name='jspSL241103009_epId' value = '" + jsp_epId + "' />";
            var jspSlCode = "<input type='hidden' id='jspSL241103009_slCode' name='jspSL241103009_slCode' value = '" + jsp_slCode + "' />";
            $uploadFile.append(jspEpId);
            $uploadFile.append(jspSlCode);
            $.core.uploadForm($uploadFile, true);
            // 去除 jsp_epId  jsp_slCode
            $("input[name='jspSL241103009_epId']").remove();
            $("input[name='jspSL241103009_slCode']").remove();
        }
    }
}

$(document).ready(function() {
    SL241103009.init();
});

