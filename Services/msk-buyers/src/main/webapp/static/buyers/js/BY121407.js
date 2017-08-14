/**
 * 菜场一览
 * Created by tao_zhifa on 2016/7/8.
 */


var BY121407 = {
    BY121407_Grid : null,
    saveButtonId : "BY121407_SAVE" ,
    findButton :"BY121407_SELECT",
    initDataGrid : function () {
        BY121407.BY121407_Grid = $("#BY121407_Grid").grid({
            actionHandler :BY121407.actionHandler
        });
        this.addButtonSave();
        this.bindSelectChange();
    },
    bindSelectChange : function () {
        var lgcsAreaCode ="";
        var cityCode = "";
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
                    $.each(data,function(i,item){
                        $("#cityCode").append("<option value='" + item.cityCode + "'>"+ item.cityName+ "</option>");
                    });
                },{refreshHtml:false});
        });

        $("#"+BY121407.findButton).click(function(){
            var lgcsAreaName =$("#lgcsAreaCode option:selected").text();
            var cityName = $("#cityCode option:selected").text();
            var marketNature = $("#marketNature option:selected").text();
            var marketStatus = $("#marketStatus option:selected").text();
            var marketName = $("#marketName").val();
            var marketAddr = $("#marketAddr").val();
            var marketCode = $("#marketCode").val();

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
            if(marketStatus != "--请选择--"){
                $("marketStatus").val("");
            }else{
                $("marketStatus").val(marketStatus);
            }
            if(marketNature != "--请选择--"){
                $("marketNature").val("");
            }else{
                $("marketNature").val(marketNature);
            }

            var formData = getFormData($("#selectBuyerList"));
            $('#main-content').postUrl(Main.contextPath + "/BY121407/search",formData,
                function(){
                    BY121407.BY121407_Grid.fnDraw();
                },{refreshHtml:false});

        });
    },
    actionHandler : function(rowdata,coltype,row,col){
        //跳转菜场调研报告一览页面
        if(coltype == "detail"){
            $('#main-content').postUrl(Main.contextPath + "/BY121408/init/" ,{ marketId :rowdata.marketId, type:"detail"},{refreshHtml: false});
        }
        //审批跳转菜场定性定级审批页面
        if(coltype == "repair"){
            $('#main-content').postUrl(Main.contextPath + "/BY121411/init/" + rowdata.marketId ,null,{refreshHtml: false});
        }
        //跳转编辑菜场信息
        if(coltype == "edit"){
            $('#main-content').postUrl(Main.contextPath + "/BY121409/init/" + rowdata.marketId, null,{refreshHtml: false});
        }
        if(coltype == "delete"){
            //删除菜场
            $.alertMessage.confirm("你确定要删除这条数据吗？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BY121407/delete/" + rowdata.marketId);
            });
        }
    },
    addButtonSave : function () {
        $("#" + BY121407.saveButtonId).click(function () {
            //跳转到新增页面
            $('#main-content').postUrl(Main.contextPath + "/BY121409/init/add", null,{refreshHtml: false});
        });
    }

}

$(document).ready(function(){
    BY121407.initDataGrid();
})

document.onkeydown = function(e){
    var e = e || window.event;
    if(e.keyCode == 13) {
        $("#"+BY121407.findButton).click();
    }
};