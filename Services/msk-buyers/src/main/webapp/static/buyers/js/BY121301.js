/**
 * 批发市场列表JS
 * Created by marshall on 16/3/9.
 */
var BY121301 = {
    saveButtonId:"BY121301_NEW",
    findButton :"BY121301_SELECT",
    BY121301Grid: null,
    initDataGrid: function () {
        BY121301.BY121301Grid = $('#BY121301_Grid').grid({
            actionHandler: BY121301.actionHandler,
            linkHandler: BY121301.linkHandler
        });
        this.bindSaveButton();
        this.bindSelectChange();
    },
    bindSelectChange : function () {

        $("#lgcsAreaCode").change(function(){
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            if(lgcsAreaCode == ""){
                $("#cityCode").html("");
                $("#cityCode").append("<option value=''>--请选择--</option>");
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


        $("#"+BY121301.findButton).click(function () {
            var lgcsAreaName =$("#lgcsAreaCode option:selected").text();
            var cityName = $("#cityCode option:selected").text();
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
            var formData = getFormData($("#selectBuyerList"));
            $('#main-content').postUrl(Main.contextPath + "/BY121301/search",formData,
                function(){
                    BY121301.BY121301Grid.fnDraw();
                },{refreshHtml:false});
        });

    },
    linkHandler: function (rowdata, coltype, row, col) {
        $('#main-content').postUrl(Main.contextPath + "/BY121303/init/1/" + rowdata.terMarketId);
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "edit") {
            //跳转到详细页面,修改批发市场信息
            $('#main-content').postUrl(Main.contextPath + "/BY121305/init/", {terMarketId: rowdata.terMarketId, type:"edit"},{refreshHtml: false});
        }
        if (coltype == "delete") {
            //删除批发市场
            $.alertMessage.confirm("你确定要删除这条数据吗？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BY121301/delete/" + rowdata.terMarketId);
            });
        }
    },
    bindSaveButton:function(){
                $("#" + BY121301.saveButtonId).click(function () {
            //跳转到详细页面,增加批发市场信息
            $('#main-content').postUrl(Main.contextPath + "/BY121305/init/", {type:"add"},{refreshHtml: false});
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BY121301.initDataGrid();
});

document.onkeydown = function(e){
    var e = e || window.event;
    if(e.keyCode == 13) {
        $("#"+BY121301.findButton).click();
    }
};