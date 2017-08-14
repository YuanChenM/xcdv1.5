/**
 *标准档案卡列表JS
 *@author pxg
 */
var PD141155 = {
    PD141155Grid: null,
    YES:"PD141126_YES",
    NO:"PD141126_NO",
    formId:"PD141126SearchData",
    initDataGrid: function () {
        PD141155.saveData();
    },
    saveData:function(){
        $("#"+PD141155.YES).click(function(){
            var classesCode=$("#classesCode").val();
            var classesName=$("#classesName").val();
            var machiningCode=$("#machiningCode").val();
            var machiningName=$("#machiningName").val();
            var breedCode=$("#breedCode").val();
            var breedName=$("#breedName").val();
            var featureCode=$("#featureCode").val();
            var featureName=$("#featureName").val();
            var weightCode=$("#weightCode").val();
            var weightName=$("#weightName").val();
            var normsOut=$("#normsOut").val();
            var normsCode=$("#normsCode").val();
            var check=true;
            if(classesCode!="" &&machiningCode!=""&&breedName!="" &&breedCode==""){
                $('#main-content').postUrl(Main.contextPath + "/PD141155/checkBreed",{classesCode:classesCode,machiningCode:machiningCode,breedName:breedName},function(data){
                    if(data<=0){
                        alert("该品种不存在，请先申报品种!");
                        check=false;
                        return;
                    }
                }, {refreshHtml: false,async:false});
            }if(classesCode!="" &&machiningCode!=""&&breedCode!=""&&featureName!=""&&featureCode==""){
                $('#main-content').postUrl(Main.contextPath + "/PD141155/checkFeature",{classesCode:classesCode,machiningCode:machiningCode,breedCode:breedCode,featureName:featureName},function(data){
                        if(data<=0){
                            alert("该特征不存在，请先申报特征!");
                            check=false;
                            return;
                        }
                }, {refreshHtml: false,async:false});
            }if(classesCode!="" &&machiningCode!=""&&breedCode!=""&&featureCode!=""&&weightName!=""&&weightCode==""){
                $('#main-content').postUrl(Main.contextPath + "/PD141155/checkWeight",{classesCode:classesCode,machiningCode:machiningCode,breedCode:breedCode,featureName:featureName},function(data){
                    if(data<=0) {
                        alert("该净重不存在，请先申报净重!");
                        check=false;
                        return;
                    }
                }, {refreshHtml: false,async:false});
            }
            if(classesCode!="" &&machiningCode!=""&&breedCode!=""&&featureCode!=""&&weightCode!=""&&normsCode==""&&normsOut!=""){
                $('#main-content').postUrl(Main.contextPath + "/PD141155/checkNorms",{classesCode:classesCode,machiningCode:machiningCode,breedCode:breedCode,featureCode:featureCode,weightCode:weightCode,normsOut:normsOut},function(data){
                    if(data<=0) {
                        alert("该包装信息不存在，请先申报包装!");
                        check=false;
                        return;
                    }
                }, {refreshHtml: false,async:false});
            }
            if(check){
                formData = getFormData($("#" + PD141155.formId));
                formData.auditStatus = "1";
                $('#main-content').postUrl(
                    $("#" + PD141155.formId).attr("action"), formData, function () {
                        $('#main-content').postUrl(Main.contextPath + "/PD141154/init", formData);
                    },{refreshHtml: false});
                }
        });
        $("#"+PD141155.NO).click(function(){
            formData = getFormData($("#" + PD141155.formId));
            formData.auditStatus="2";
            $('#main-content').postUrl(
                $("#" + PD141155.formId).attr("action"), formData, function () {
                    $('#main-content').postUrl(Main.contextPath + "/PD141154/init",formData);
                });
        });
    }
}
$(document).ready(function () {
    //初始化调用
    PD141155.initDataGrid();
});