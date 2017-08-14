/**
 * 菜场详细信息编辑
 * Created by zhouling on 16/04/13.
 */
var BY121306 = {
    saveButtonId:"BY121306_SAVE",
    formId:"by121306Form",
    initDataGrid: function () {
        this.bindSaveButton();
        this.bindSelectChange();
    },
    bindSaveButton: function () {
        $("#" + BY121306.saveButtonId).click(function () {
            if ($("#by121306Form").validateForm()) {
                var marketType = $("#marketType").val();
                var sectionType = $("#sectionType").val();
                var sizeType = $("#sizeType").val();
                var lgcsAreaCode = $("#lgcsAreaCode").val();
                var cityCode = $("#cityCode").val();
                var districtCode = $("#districtCode").val();
                //买家编码  01 234 56 789
                var marketCode = $("#marketCode").val();
                var lgcs= marketCode.substring(0, 2);
                var city= marketCode.substring(2, 5);
                var district= marketCode.substring(5, 7);
                if(lgcs!=lgcsAreaCode){
                    $.alertMessage.confirm("输入的菜场编码与所选物流区编码不匹配", function () {
                        $.alertMessage.close();
                    });
                    return false;
                }

                if(city!=cityCode){
                    $.alertMessage.confirm("输入的菜场编码与所选地区编码不匹配", function () {
                        $.alertMessage.close();
                    });
                    return false;
                }
                if(district!=districtCode){
                    $.alertMessage.confirm("输入的菜场编码与所选区县编码不匹配", function () {
                        $.alertMessage.close();
                    });
                    return false;
                }

                if (marketType == "") {
                    $.alertMessage.confirm("请选择菜场类型", function () {
                        $.alertMessage.close();
                    });
                    return false;
                }
                if (sectionType == "") {
                    $.alertMessage.confirm("请选择地段类型", function () {
                        $.alertMessage.close();
                    });
                    return false;
                }
                if (lgcsAreaCode == "") {
                    $.alertMessage.confirm("请选择物流区", function () {
                        $.alertMessage.close();
                    });
                    return false;
                }
                if (cityCode == "") {
                    $.alertMessage.confirm("请选择地区(城市)", function () {
                        $.alertMessage.close();
                    });
                    return false;
                }
                if (districtCode == "") {
                    $.alertMessage.confirm("请选择区(县)", function () {
                        $.alertMessage.close();
                    });
                    return false;
                }
                // 设置菜场类型名称
                var marketTypeName = $("#marketType option:selected").text();
                $("#marketTypeName").val(marketTypeName);
                // 设置地段类型名称
                var sectionTypeName = $("#sectionType option:selected").text();
                $("#sectionTypeName").val(sectionTypeName);
                // 设置物流区名称
                var lgcsAreaName = $("#lgcsAreaCode option:selected").text();
                $("#lgcsAreaName").val(lgcsAreaName);
                // 设置城市名称
                var cityName = $("#cityCode option:selected").text();
                $("#cityName").val(cityName);
                // 设置地区名称
                var districtName = $("#districtCode option:selected").text();
                $("#districtName").val(districtName);
                var formData = getFormData($("#" + BY121306.formId));
                $('#main-content').postUrl($("#" + BY121306.formId).attr("action"), formData,  function (data) {
                    if (data == "S") {
                        $('#main-content').postUrl(Main.contextPath + "/BY121302/init/");
                    } else if(data == "1"){
                        $.alertMessage.confirm("输入的菜场编码已存在", function () {
                            $.alertMessage.close();
                        });
                        return false;
                    }
                }, {refreshHtml: false});

            }

        });
    },

    bindSelectChange:function(){
        // 物流区变更
        $("#lgcsAreaCode").change(function(){
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            if(lgcsAreaCode == ""){
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/lgcsAreaChange/" + lgcsAreaCode,null,
                function(data){
                    $("#cityCode").html("");
                    $("#cityCode").append("<option value=''>--请选择--</option>");
                    $("#districtCode").html("");
                    $("#districtCode").append("<option value=''>--请选择--</option>");
                    $.each(data,function(i,item){
                        $("#cityCode").append("<option value='" + item.cityCode + "'>"+ item.cityName+ "</option>");
                    });
                },{refreshHtml:false});
        });

        // 城市变更
        $("#cityCode").change(function(){
            var cityCode = $("#cityCode option:selected").val();
            $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/cityChange/" + cityCode,null,
                function(data){
                    $("#districtCode").html("");
                    $("#districtCode").append("<option value=''>--请选择--</option>");
                    $.each(data,function(i,item){
                        $("#districtCode").append("<option value='" + item.districtCode + "'>"+ item.districtName+ "</option>");
                    });
                },{refreshHtml:false});
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BY121306.initDataGrid();
});