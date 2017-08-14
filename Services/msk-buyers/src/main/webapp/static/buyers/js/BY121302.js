/**
 * 菜场列表JS
 * Created by marshall on 16/3/9.
 */
var BY121302 = {
    saveButtonId:"BY121302_NEW",
    findButton :"BY121302_SELECT",
    BY121302Grid: null,
    initDataGrid: function () {
        BY121302.BY121302Grid = $('#BY121302_Grid').grid({
            actionHandler: BY121302.actionHandler,
            linkHandler: BY121302.linkHandler
        });
        this.bindSaveButton();
        this.bindSelectChange();
    },
    bindSelectChange : function () {
        var lgcsAreaCode ="";
        var cityCode = "";
        var districtCode ="";
        $("#lgcsAreaCode").change(function(){
            lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            if(lgcsAreaCode == ""){
                $("#cityCode").html("");
                $("#cityCode").append("<option value=''>--请选择--</option>");
                $("#districtCode").html("");
                $("#districtCode").append("<option value=''>--请选择--</option>");
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
        $("#cityCode").change(function(){
            cityCode = $("#cityCode option:selected").val();
            if(cityCode == ""){
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/cityChange/" + cityCode,null,
                function(data){
                    $("#districtCode").html("");
                    $("#districtCode").append("<option value=''>--请选择--</option>");
                    $.each(data,function(i,item){
                        $("#districtCode").append("<option value='" + item.districtCode + "'>"+ item.districtName+ "</option>");
                    });
                },{refreshHtml:false});
        });

        $("#"+BY121302.findButton).click(function () {
            var lgcsAreaName =$("#lgcsAreaCode option:selected").text();
            var cityName = $("#cityCode option:selected").text();
            var districtName =$("#districtCode option:selected").text();
            if(lgcsAreaName == "--请选择--"){
                $("#lgcsAreaName").val("");
            }else{
                $("#lgcsAreaName").val(lgcsAreaName);
            }

            if(cityName == "--请选择--"){
                $("#cityName").val("");
            }else{
                $("#cityName").val(cityName);
            }
            if(districtName == "--请选择--"){
                $("#districtName").val("");
            }else{
                $("#districtName").val(districtName);
            }
            var formData = getFormData($("#selectBuyerList"));
            $('#main-content').postUrl(Main.contextPath + "/BY121301/search",formData,
                function(){
                    BY121302.BY121302Grid.fnDraw();
                },{refreshHtml:false});
        });

    },
    linkHandler: function (rowdata, coltype, row, col) {
        $('#main-content').postUrl(Main.contextPath + "/BY121303/init/2/" + rowdata.fodMarketId);
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "edit") {
            //跳转到详细页面,修改菜场信息
            $('#main-content').postUrl(Main.contextPath + "/BY121306/init/", {fodMarketId: rowdata.fodMarketId, type:"edit"},{refreshHtml: false});
        }
        if (coltype == "delete") {
            //删除菜场
            $.alertMessage.confirm("你确定要删除这条数据吗？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BY121302/delete/" + rowdata.fodMarketId);
            });
        }
    },

    bindSaveButton:function(){
        $("#" + BY121302.saveButtonId).click(function () {
            //跳转到详细页面,增加菜场信息
            $('#main-content').postUrl(Main.contextPath + "/BY121306/init/", {type:"add"},{refreshHtml: false});
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BY121302.initDataGrid();
});

document.onkeydown = function(e){
    var e = e || window.event;
    if(e.keyCode == 13) {
        $("#"+BY121302.findButton).click();
    }
};