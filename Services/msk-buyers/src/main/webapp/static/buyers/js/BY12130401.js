/**
 * 买家详细信息
 * Created by marshall on 16/3/9.
 */
var BY12130401 = {
    BY12130401Grid: null,
    saveButtonId:"BY12130401_SAVE",
    fileUploadButtonId:"BY12130401_UPLOAD",
    picChooseButtonId:"file_busCardPicId",
    formId:"BY12130401Form",
    initDataGrid: function () {
        this.bindSaveButton();
        this.picChooseButton();
    },
    bindSaveButton:function(){
        $("#" + BY12130401.saveButtonId).click(function(){
            if($("#"+BY12130401.formId).validateForm()){
            var regPhone=/^1[34578]\d{9}$/;
            var employeeName = $("#employeeName").val();
            var employeeName2 = $("#employeeType option:selected").text();

                var employeeType = $("#employeeType option:selected").val();


            var employeeTel = $("#employeeTel").val();
            if(!regPhone.test(employeeTel)){
                $.alertMessage.info("手机号码输入有误", function () {
                    $.alertMessage.close();
                });
                return false;
            }
            if(employeeName == ""){
                $.alertMessage.confirm("员工姓名不能为空", function () {
                    $.alertMessage.close();
                });
                return false;
            }
           if(employeeType=="--请选择--"){
               $.alertMessage.confirm("员工类型不能为空", function () {
                   $.alertMessage.close();
               });
               return false;
           }


            $("#employeeTypeName").html("");
            var employeeTypeName = $("#employeeType option:selected").text();
            $("#employeeTypeName").val(employeeTypeName);
            var validator = mainValidation($("#" + BY12130401.formId));
            var isValid = validator.form();
            if (isValid) {
                $("#" + BY12130401.fileUploadButtonId).click();
            };
       /*     if (isValid) {
                var $uploadFile = $("#" + BY12130401.formId);
                $.core.uploadForm($uploadFile, true);
            };*/
          }

        });
    },
    picChooseButton:function(){
        $("#" + BY12130401.picChooseButtonId).click(function(){
            if(!($("#busCardFlg").is(":checked"))){
                $("#errorMessage").text("请先勾选有无名片照");
                return false;
            }else{
                $("#errorMessage").text("");
            }
        })
    }
}

var callBack = function(data){
    var fileServerId = eval(data);
    var buyerId = $("#buyerId").val();
    var editType = $("#editType").val();
    var busCardPicPath = fileServerId.busCardPicName;
    if(busCardPicPath == undefined){
        busCardPicPath = "";
    }
    $("#busCardPicPath").val(busCardPicPath);
    var formData = getFormData($("#" + BY12130401.formId));
    $("#main-content").postUrl($("#" + BY12130401.formId).attr("action"),formData,function(data){
        if(data == "S"){
            if(editType == "BY121307Add"){
                $.pdialog.close();
                BY121307.BY121307Grid.fnDraw();
            }else{
                $.pdialog.close();
                $('#main-content').postUrl(Main.contextPath+"/BY121304/init/"+buyerId,null,function(){
                });
            }
        }else if(data == "I"){
            $.alertMessage.info("老板已存在！", function () {
                $.alertMessage.close();
            });
        }else if(data == "T"){
            $.alertMessage.confirm("同类员工姓名相同，是否继续添加？", function () {
                $("#isFlag").val("true");
                var formAdd = getFormData($("#" + BY12130401.formId));
                $("#main-content").postUrl($("#" + BY12130401.formId).attr("action"),formAdd,function(data){
                    if(data == "S"){
                        if(editType == "BY121307Add"){
                            $.pdialog.close();
                            BY121307.BY121307Grid.fnDraw();
                        }else{
                            $.pdialog.close();
                            $('#main-content').postUrl(Main.contextPath+"/BY121304/init/"+buyerId,null,function(){
                            });
                        }
                    }else if(data == "I"){
                        $.alertMessage.info("老板已存在！", function () {
                            $.alertMessage.close();
                        });
                    }else{
                        $.alertMessage.info("更新失败！", function () {
                            $.alertMessage.close();
                        });
                    }
                },{refreshHtml:false});
                $.alertMessage.close();
            })
        }else{
            $.alertMessage.info("更新失败！", function () {
                $.alertMessage.close();
            });
        }
    },{refreshHtml:false});
};

$(document).ready(function () {
    // 初始化调用
    BY12130401.initDataGrid();
});