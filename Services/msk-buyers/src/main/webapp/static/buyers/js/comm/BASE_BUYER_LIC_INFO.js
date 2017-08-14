/**
 * 买家详细信息
 * Created by marshall on 16/3/9.
 */
var baseBuyerLic = {
    licEditButtonId:"BUYERLIC_EDIT",
    licSaveButtonId:"BUYERLIC_SAVE",
    filePicButtonId:"BUYERLIC_UPLOAD",
    formId:"licEditForm",
    initDataGrid: function () {
        this.bindEditButton();
        this.bindSaveButton();
    },
    bindEditButton:function(){
        $("#"+baseBuyerLic.licEditButtonId).click(function(){
            $("#licShowTable").css("display","none");
            $("#licEditTable").css("display","");
            $("#licEditForm").css("height","250px");
        });
    },
    bindSaveButton:function(){
        $("#"+baseBuyerLic.licSaveButtonId).click(function(){
            var number=/^\d+$/; //整数
            var licNumber=$("#licNumber").val();
            var legalLicNumber=$("#legalLicNumber").val();
            var legalName=$("#legalName").val();
            var legalLicType = $("#legalLicType option:selected").text();
            var licName = $("#licName option:selected").text();
/*            if(licName == "--请选择--"){
                $.alertMessage.info("请选择营业执照类型 ！");
                return false;
            }*/
/*            if(legalLicType == "--请选择--"){
                $.alertMessage.info("请选择法定代表人证件类型 ！");
                return false;
            }*/
             if(licNumber!=""){
                 if(!number.test(licNumber)){
                 $.alertMessage.info("营业执照号码为数字！");
                 return false;
                 }else if(licNumber.length>20){
                 $.alertMessage.info("营业执照号码长度不能超过20位！");
                 return false;
                 }
             }
             if(legalLicNumber!=""){
                 if(!number.test(legalLicNumber)){
                 $.alertMessage.info("法定代表人证件号码为数字！");
                 return false;
                 }else if(legalLicNumber.length>20){
                 $.alertMessage.info("法定代表人证件号码长度不能超过20位！");
                 return false;
                 }
             }
             if(legalName!=""){
                 if(legalName.length>20){
                 $.alertMessage.info("法定代表人姓名长度不能超过20位！");
                 return false;
                 }
             }
            $("#licDes").html("");
            var licDes = $("#licName option:selected").text();
            $("#licDes").val(licDes);
            $("#legalLicDes").html("");
            var legalLicDes = $("#legalLicType option:selected").text();
            $("#legalLicDes").val(legalLicDes);
            $("#" + baseBuyerLic.formId).attr("action",Main.contextPath+"/by/baseBuyerLicInfo/update/");
            var formData = getFormData($("#" + baseBuyerLic.formId));
            $("#main-content").postUrl($("#" + baseBuyerLic.formId).attr("action"),formData,function(data){
                if(data == "S"){
                    $("#"+baseBuyerLic.filePicButtonId).click();
                }else{
                    $.alertMessage.info("证照信息保存失败！");
                }
            },{refreshHtml:false});
        });
    }
}

var callBack = function(data){
    var fileServerId = eval(data);
    var buyerId = $("#buyerId").val();
    var picLicensePath = fileServerId.busLicPicName;
    var picOrgStructurePath = fileServerId.orgCertificatePicName;
    var picTaxRegistrationPath = fileServerId.taxCertificatePicName;
    var picFoodCirculationPath = fileServerId.foodCertificatePicName;
    var picCertPath = fileServerId.legalCertificatePicName;
    if(picLicensePath == undefined){
        picLicensePath = "";
    }
    if(picOrgStructurePath == undefined){
        picOrgStructurePath = "";
    }
    if(picTaxRegistrationPath == undefined){
        picTaxRegistrationPath = "";
    }
    if(picFoodCirculationPath == undefined){
        picFoodCirculationPath = "";
    }
    if(picCertPath == undefined){
        picCertPath = "";
    }

    $("#main-content").postUrl(Main.contextPath + "/by/baseBuyerLicInfo/saveFileServerId", {
        buyerId:buyerId,
        picLicensePath:picLicensePath,
        picOrgStructurePath:picOrgStructurePath,
        picTaxRegistrationPath:picTaxRegistrationPath,
        picFoodCirculationPath:picFoodCirculationPath,
        picCertPath:picCertPath
    },function(data) {
        if(data == "F"){
            $.alertMessage.info("证照信息保存失败!");
        }
    });
};

$(document).ready(function () {
    // 初始化调用
    baseBuyerLic.initDataGrid();
});