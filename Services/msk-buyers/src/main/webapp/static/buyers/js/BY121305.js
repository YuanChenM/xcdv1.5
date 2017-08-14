/**
 * 批发市场详细信息
 * Created by zhouling on 16/04/12.
 */
var BY121305 = {
    saveButtonId:"BY121305_SAVE",
    formId:"by121305Form",
    initDataGrid: function () {
        this.bindSaveButton();
        this.bindSelectChange();
    },
    bindSaveButton: function () {
        $("#" + BY121305.saveButtonId).click(function () {
            if ($("#by121305Form").validateForm()) {
                var marketLevel = $("#marketLevel").val();
                var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
                var cityCode = $("#cityCode option:selected").val();
                var marketCode = $("#marketCode").val();
                //分类编码  01 23 456 789
                var fenLeiCode = marketCode.substring(0, 2);
                var district = marketCode.substring(2, 4);
                var city = marketCode.substring(4, 7);
                if (fenLeiCode != "01") {
                    $.alertMessage.confirm("输入的批发市场编码以01开头", function () {
                        $.alertMessage.close();
                    });
                    return false;
                }
                if (district != lgcsAreaCode) {
                    $.alertMessage.confirm("输入的批发市场编码与选择的物流区编码不匹配", function () {
                        $.alertMessage.close();
                    });
                    return false;
                }
                if (city != cityCode) {
                    $.alertMessage.confirm("输入的批发市场编码与选择的城市编码不匹配", function () {
                        $.alertMessage.close();
                    });
                    return false;
                }


                if (marketLevel == "") {
                    $.alertMessage.confirm("请选择批发市场级别", function () {
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
                // 设置批发市场级别名称
                var marketLevelName = $("#marketLevel option:selected").text();
                $("#marketLevelName").val(marketLevelName);
                // 设置物流区名称
                var lgcsAreaName = $("#lgcsAreaCode option:selected").text();
                $("#lgcsAreaName").val(lgcsAreaName);
                // 设置城市名称
                var cityName = $("#cityCode option:selected").text();
                $("#cityName").val(cityName);
                var formData = getFormData($("#" + BY121305.formId));
                $('#main-content').postUrl($("#" + BY121305.formId).attr("action"), formData,function(data){
                    if(data=="S"){
                        $('#main-content').postUrl(Main.contextPath + "/BY121301/init/");
                    }else if(data=="1"){
                        $.alertMessage.confirm("输入的批发市场编码已存在", function () {
                            $.alertMessage.close();
                        });
                        return false;
                    }
                }, {refreshHtml: false});

            }
        });
    },

    bindSelectChange:function(){

        $("#lgcsAreaCode").change(function(){
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            if(lgcsAreaCode == ""){
                return false;
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
    }
}
$(document).ready(function () {
    // 初始化调用
    BY121305.initDataGrid();
});