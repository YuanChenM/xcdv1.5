/**
 * 批发市场定性定级审批
 * Created by zhou_yajun on 2016/7/11.
 */


var BY121411 = {
    saveAndSyncButton : "BY121411_SAVEANDSYNC",
    saveNotSyncButton : "BY121411_SAVENOTSYNC",
    searchButton : "BY121411_SEARCH",
    formId : "BY121411SaveForm",
    initDataGrid : function () {
        BY121411.BY121411_Grid = $("#BY121411_Grid").grid({
            actionHandler :BY121411.actionHandler
        });
        this.standardShow();
        this.dataSave();
    },
    standardShow: function(){
        $("#" + BY121411.searchButton).click(function(){
            $.pdialog.open("菜场定性定级判定标准", Main.contextPath + "/BY1214111/init",{
                width:500,
                height:500
            });
        });
    },
    dataSave: function () {
        $("#" + BY121411.saveAndSyncButton).click(function () {
            if ($("#BY121411SaveForm").validateForm()) {
                $("#syncFlag").val("1");
                BY121411.approveSave();
            }
        });

        $("#" + BY121411.saveNotSyncButton).click(function () {
            if ($("#BY121411SaveForm").validateForm()) {
                $("#syncFlag").val("0");
                BY121411.approveSave();
            }
        });
    },
    approveSave: function () {
        var marketNatureName = $("#marketNature option:selected").text();
        var lgcsAreaCode = $("#lgcsAreaName").attr("lgcsAreaCode")
        var cityCode = $("#cityName").attr("cityCode")
        var districtCode = $("#districtName").attr("districtCode")
        //菜场编码处理
        var marketCode = $("#marketCode").val();
        var lgcs = marketCode.substring(0, 2);
        var city = marketCode.substring(2, 5);
        var district = marketCode.substring(5, 7);

        if (lgcs != lgcsAreaCode) {
            $.alertMessage.confirm("输入的菜场编码与物流区编码不匹配", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if (city != cityCode) {
            $.alertMessage.confirm("输入的菜场编码与地区编码不匹配", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if (district != districtCode) {
            $.alertMessage.confirm("输入的菜场编码与区县编码不匹配", function () {
                $.alertMessage.close();
            });
            return false;
        }

        if (marketNatureName == "--请选择--") {
            $.alertMessage.info("菜场性质不能为空 !");
            return false;
        }
        $("#marketNatureName").val(marketNatureName);
        var formData = getFormData($("#" + BY121411.formId));
        $('#main-content').postUrl($("#" + BY121411.formId).attr("action"), formData,function(data){
            if(data=="1"){
                $.alertMessage.info("输入的菜场编码已存在", function () {
                    $.alertMessage.close();
                });
                return false;
            }

        },{refreshHtml: false});
    }
}

$(document).ready(function(){
    BY121411.initDataGrid();
})