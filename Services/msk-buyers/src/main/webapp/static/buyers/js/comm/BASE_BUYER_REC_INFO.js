/**
 * 买家详细信息
 * Created by marshall on 16/3/9.
 */
var baseBuyerRec = {
    recEditButtonId:"BUYERREC_EDIT",
    recSaveButtonId:"BUYERREC_SAVE",
    formId:"recEditForm",
    initDataGrid: function () {
        this.bindEditButton();
        this.bindSaveButton();
    },
    bindEditButton:function(){
        $("#"+baseBuyerRec.recEditButtonId).click(function(){
            $("#recShowTable").css("display","none");
            $("#recEditTable").css("display","");
        });
    },
    bindSaveButton:function(){
        $("#"+baseBuyerRec.recSaveButtonId).click(function(){

            $($("#" + baseBuyerRec.formId)).attr("action",Main.contextPath+"/by/baseBuyerRecInfo/update/");
            var validator = mainValidation($("#" + baseBuyerRec.formId));
            var isValid = validator.form();

            if (isValid) {
                var formData = getFormData($("#" + baseBuyerRec.formId));
                $('#main-content').postUrl($("#" + baseBuyerRec.formId).attr("action"),formData,{refreshHtml:false});
            };
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    baseBuyerRec.initDataGrid();
});