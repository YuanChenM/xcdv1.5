/**
 * 买手店列表JS
 *
 * @author cx
 */
var BS2102123 = {
    formId: "bs2102123FormId",
    saveDate:"BS2102123_SAVE",
    init: function () {
        this.changeSelect();
        this.bindSaveButton();
    },

    actionHandler: function (rowdata, coltype, row, col) {

    },
    changeSelect: function () {
        var citySelect = $('#citySel1');
        var machiningSel = $("#machiningCode_sel1");
        $('#wl_select1').change(function () {
            $("#citySel1").find("option").not(":first").remove();
             var lgcsAreaCode = $('#wl_select1').val();
             var lgcsAreaName = $('#wl_select1').find("option:selected").text();
            $("#lgcsAreaName1").val(lgcsAreaName);
            if (lgcsAreaCode != null&&lgcsAreaCode!="") {
             $('#main-content').postUrl(Main.contextPath + '/BS2102123/findCityLst', {lgcsAreaCode: lgcsAreaCode},
             function (data) {
             $.each(data, function (i, item) {
             citySelect.append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
             });
             }, {refreshHtml: false});
            }
        });
        $("#classesCode_sel1").change(function () {
            $("#machiningCode_sel1").find("option").not(":first").remove();
            var classesCode = $("#classesCode_sel1").val();
            var classesName =  $("#classesCode_sel1").find("option:selected").text();
            $("#classesName").val(classesName);
            if(classesCode != null && classesCode != ""){
                $('#main-content').postUrl(Main.contextPath + '/BS2102123/findPdMachiningLst', {classesCode: classesCode},
                    function (data) {
                        $.each(data, function (i, item) {
                            machiningSel.append("<option value='" + item.machiningCodeU + "'>" + item.machiningNameU + "</option>");
                        });
                    }, {refreshHtml: false});
            }
        });
        $("#buyerType_sel1").change(function () {
            var buyerTypeName = $('#buyerType_sel1').find("option:selected").text();
            $("#buyerTypeName").val(buyerTypeName);
        });
        $("#machiningCode_sel1").change(function () {
            var machiningName = $('#machiningCode_sel1').find("option:selected").text();
            $("#machiningName").val(machiningName);
        });
        $("#citySel1").change(function () {
            var cityName = $('#citySel1').find("option:selected").text();
            $("#cityName").val(cityName);
        });
    },
    // 绑定按钮
    bindSaveButton: function () {
        $("#BS2102123_SAVE").click(function () {
            BS2102123.newData();
        });
    },

    newData: function () {
        var validator = mainValidation($("#" + BS2102123.formId));
        var isValid = validator.form();
        if($("#hkGroupName").val()==null || $("#hkGroupName").val() == "" ){
            $('#hkGroupName_sp').html('<font style="color:red">请填写管家组名称!</font>');
            return;
        }
        else{
            $('#hkGroupName_sp').html("");
        }
        if($("#wl_select1").val()==0){
            $('#lgcsAreaCode_sp').html('<font style="color:red">请选择物流区!</font>');
            return;
        }
        else{
            $('#lgcsAreaCode_sp').html("");
        }
        if($("#citySel1").val()==0){
            $('#cityCode_sp').html('<font style="color:red">请选择地区!</font>');
            return;
        }
        else{
            $('#cityCode_sp').html("");
        }
        if($("#buyerType_sel1").val()==0){
            $('#buyerType_sp').html('<font style="color:red">请选择买家类型!</font>');
            return;
        }
        else{
            $('#buyerType_sp').html("");
        }
        if($("#classesCode_sel1").val()==0){
            $('#classesCode_sp').html('<font style="color:red">请选择一级分类!</font>');
            return;
        }
        else{
            $('#classesCode_sp').html("");
        }
        if($("#machiningCode_sel1").val()==0){
            $('#machiningCodeU_sp').html('<font style="color:red">请选择二级分类!</font>');
            return;
        }
        else{
            $('#machiningCodeU_sp').html("");
        }
        formData = getFormData($("#" + BS2102123.formId));
        $.post(Main.contextPath + "/BS2102123/save",formData,function(data){
            if(data == "S"){
                $.alertMessage.info("数据已保存");
            }
            else{
                $.alertMessage.info("冻品管家组已经存在！");
            }
            $('#main-content').postUrl(Main.contextPath + "/BS2102104/init",{

            });
            $.pdialog.close();
        });
    }

}
$(document).ready(function () {
    // 初始化调用
    BS2102123.init();
});