/**
 * Created by fjm on 2016/1/30.
 */
var SL24110301201 = {
    formId:"SL24110301201Form",
    saveButton:"SL24110301201_SAVE",
    init : function(){
        this.saveData();
    },
    saveData:function(){
        $("#"+SL24110301201.saveButton).click(function() {
            var $uploadFile = $("#SL24110301201Form");
            // 添加 jsp_epId2
            var jsp_epIdUp2 = $("input#jsp_epId2").val();
            var jspEpIdUp2  =  "<input type='hidden' id='jsp_epIdUp2' name='jsp_epIdUp2' value = '"+jsp_epIdUp2+"' />";
            $uploadFile.append(jspEpIdUp2);
            $.core.uploadForm($uploadFile, true);
            // 去除 jsp_epId2
            $("input[name='jsp_epIdUp2']").remove();
        });
    }
}

$(document).ready(function() {
    SL24110301201.init();
});

