/**
 * Created by fjm on 2016/1/26.
 */
var SL241103008 = {

    formId : "SL241103008Form",
    saveButtonId : "SL241103008_SAVE",
    addButtonId : "SL241103008_ADD",
    init : function(){
        this.bindSavebutton();
        this.bindAddbutton();
        $('#SL241103008accordion').on( "accordionactivate", function( event, ui ) {
            if(ui.newHeader.length==1){
                $("#initSL241103008").postUrl(Main.contextPath+"/SL241103008/init",null,function(data){
                    //Modified by xia_xiaojie on 2016/6/21. Modified start.
                    /*if(null!=data){
                        var selectData='';
                        $(data).each(function(i,val){
                            selectData+="<option value='"+val.constantValue+"'>"+val.constantName+"</option>";
                        });
                        $("#memberDuties").find("option").not(":first").remove();
                        $("#memberDuties").append(selectData);
                    }*/
                    //Modified end.
                },{refreshHtml:false });
            }
        } );
    },
    bindAddbutton : function () {
        $("#" + SL241103008.addButtonId).click(function(){
            $.pdialog.open("团队成员添加",Main.contextPath + "/SL2411030081/init");
        });
    },
    bindSavebutton : function() {
        $("#" + SL241103008.saveButtonId).click(function() {
            SL241103008.uploadData();
        });
    },
    uploadData : function() {
        if ($("#" + SL241103008.formId).validateForm()) {
            var $uploadFile = $("#SL241103008Form");

            // 添加 jsp_epId
            var jsp_epId = $("input#jsp_epId").val();
            var jspEpId = "<input type='hidden' id='jspSL241103008_epId' name='jspSL241103008_epId' value = '" + jsp_epId + "' />";
            $uploadFile.append(jspEpId);
            $.core.uploadForm($uploadFile, true);
            // 去除 jsp_epId
            $("input[name='jspSL241103008_epId']").remove();
        }
    }
}

$(document).ready(function() {
    SL241103008.init();
});
