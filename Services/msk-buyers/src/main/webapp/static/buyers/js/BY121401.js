/**
 * 批发市场一览
 * Created by tao_zhifa on 2016/7/7.
 */


var BY121401 = {
    BY121401_Grid : null,
    saveButtonId : "BY121401_SAVE" ,
    findButton :"BY121401_SELECT",
    initDataGrid : function () {
        BY121401.BY121401_Grid = $("#BY121401_Grid").grid({
            actionHandler :BY121401.actionHandler
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

        $("#"+BY121401.findButton).click(function(){
            var lgcsAreaName =$("#lgcsAreaCode option:selected").text();
            var cityName = $("#cityCode option:selected").text();
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

            var formData = getFormData($("#selectBuyerList"));
            $('#main-content').postUrl(Main.contextPath + "/BY121401/search",formData,
                function(){
                    BY121401.BY121401_Grid.fnDraw();
                },{refreshHtml:false});

        });
    },
    actionHandler : function(rowdata,coltype,row,col){
        //跳转批发市场调研报告一览页面
        if(coltype == "detail"){
            $('#main-content').postUrl(Main.contextPath + "/BY121402/init/" ,{ marketId :rowdata.marketId, type:"detail"},{refreshHtml: false});
        }
        //审批跳转批发市场定性定级审批页面
        if(coltype == "repair"){
            $('#main-content').postUrl(Main.contextPath + "/BY121405/init/"+rowdata.marketId ,null,{refreshHtml: false});
        }
        //跳转编辑批发市场信息
        if(coltype == "edit"){
            $('#main-content').postUrl(Main.contextPath + "/BY121403/init/"+rowdata.marketId ,null,{refreshHtml: false});
        }
        if(coltype == "delete"){
            //删除批发市场
            $.alertMessage.confirm("你确定要删除这条数据吗？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BY121401/delete/" + rowdata.marketId );
            });
        }
        if(coltype == "search"){
            //跳转到批发市场先期调查表
            $('#main-content').postUrl(Main.contextPath + "/BY121413/init/" + rowdata.marketId );
        }
    },
    addButtonSave : function () {
        $("#" + BY121401.saveButtonId).click(function () {
            //跳转到新增页面
            $('#main-content').postUrl(Main.contextPath + "/BY121403/init/add", null,{refreshHtml: false});
        });
    }

}

$(document).ready(function(){
    BY121401.initDataGrid();
})

document.onkeydown = function(e){
    var e = e || window.event;
    if(e.keyCode == 13) {
        $("#"+BY121401.findButton).click();
    }
};