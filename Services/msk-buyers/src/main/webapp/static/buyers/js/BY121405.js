/**
 * 批发市场定性定级审批
 * Created by zhou_yajun on 2016/7/11.
 */


var BY121405 = {
    saveAndSyncButton : "BY121405_SAVEANDSYNC",
    saveNotSyncButton : "BY121405_SAVENOTSYNC",
    searchButton : "BY121405_search",
    formId : "BY121405SaveForm",
    initDataGrid : function () {
        BY121405.BY121405_Grid = $("#BY121405_Grid").grid({
            fnRowCallback: BY121405.rowCallback
        });
        BY121405.BY121403_Grid2 = $("#BY121405_Grid2").grid({
            fnRowCallback: BY121405.rowCallback2
        });

        this.standardShow();
        this.dataSave();
    },
    standardShow: function(){
        $("#" + BY121405.searchButton).click(function(){
            $.pdialog.open("批发市场定性定级判定标准", Main.contextPath + "/BY1214051/init",{
                width:550,
                height:600
            });
        });
    },
    dataSave: function(){
        $("#" + BY121405.saveAndSyncButton).click(function () {
            if ($("#BY121405SaveForm").validateForm()) {
                if (!BY121405.cheackData()) {
                    return false;
                }
                $("#syncFlag").val("1");
                BY121405.approveSave();
            }
        });

        $("#" + BY121405.saveNotSyncButton).click(function () {
            if ($("#BY121405SaveForm").validateForm()) {
                if(!BY121405.cheackData()){
                    return false;
                }
                $("#syncFlag").val("0");
                BY121405.approveSave();
            }

        });
    },
    approveSave: function(){
        var marketNatureName = $("#marketNature option:selected").text();
        $("#marketNatureName").val(marketNatureName);
        var marketLevelName = $("#marketLevel option:selected").text();
        $("#marketLevelName").val(marketLevelName);
        var formData = getFormData($("#" + BY121405.formId));
        $('#main-content').postUrl($("#" + BY121405.formId).attr("action"), formData,function(data){
         /*   if(data.errorMessage != null && data.errorMessage != ""){
                $.alertMessage.info(data.errorMessage, function () {
                    $.alertMessage.close();
                });
                return false;
            }else*/
            if(data=="1"){
                $.alertMessage.info("输入的批发市场编码已存在", function () {
                    $.alertMessage.close();
                });
                return false;
            }
        },{refreshHtml:false});
    },
    rowCallback: function(tr, data) {
        if(tr._DT_RowIndex == 0){
            $("#currentNumber").html(data.currentNumber);
            $("#currentAmount").html(fmoney(data.currentAmount,2));
            $("#totalNumber").html(data.totalNumber);
            $("#totalAmount").html(fmoney(data.totalAmount,2));
        }
    },
    rowCallback2: function(tr, data) {
        if(tr._DT_RowIndex == 0){
            $("#currentNumber2").html(data.currentNumber);
            $("#currentAmount2").html(fmoney(data.currentAmount,2));
            $("#totalNumber2").html(data.totalNumber);
            $("#totalAmount2").html(fmoney(data.totalAmount,2));
        }
    },
    cheackData: function () {
        var marketNature = $("#marketNature option:selected").val();
        var marketLevel = $("#marketLevel option:selected").val();

        var lgcsAreaCode=$("#lgcsAreaCode").val();
        var cityCode=$("#cityCode").val();
        var marketCode = $("#marketCode").val();
        //处理批发市场编码
        var fenLeiCode = marketCode.substring(0, 2);
        var district = marketCode.substring(2, 4);
        var city = marketCode.substring(4, 7);
        if(fenLeiCode!="01"){
            $.alertMessage.confirm("输入的批发市场编码以01开头", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if (district != lgcsAreaCode) {
            $.alertMessage.confirm("输入的批发市场编码与物流区编码不匹配", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if (city != cityCode) {
            $.alertMessage.confirm("输入的批发市场编码与城市编码不匹配", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if (marketNature == "") {
            $.alertMessage.info("批发市场性质不能为空 !");
            return false;
        }
        if (marketLevel == "") {
            $.alertMessage.info("批发市场等级不能为空 !");
            return false;
        }
        return true;
    },
    initTable : function () {
        var $grid = $("#BY121405_Grid tbody");
        var $grid2 = $("#BY121405_Grid2 tbody");
        $grid.after("<tr role='row' style='height: 26px;'>" +
        "  <td style='border-left: 1px solid #b1b1b1;border-top: 1px solid #b1b1b1;' rowspan='2' colspan='2'></td>" +
        "  <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA;text-align: right'>当前页合计 :&nbsp;</td>" +
        "  <td class='text'  name='currentNumber' id='currentNumber' style='border: 1px solid #b1b1b1;text-align: left'></td>" +
        "  <td class='text'  name='currentAmount' id='currentAmount' style='border: 1px solid #b1b1b1;text-align: right;padding-right: 10px;padding-left: 4px;'></td>" +
        "</tr>" +
        "<tr role='row' style='height: 26px;'>" +
        "  <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA;text-align: right'>总合计 :&nbsp;</td>" +
        "  <td class='text'  name='totalNumber' id='totalNumber' style='border: 1px solid #b1b1b1;text-align: left'></td>" +
        "  <td class='text'  name='totalAmount' id='totalAmount' style='border: 1px solid #b1b1b1;text-align: right;padding-right: 10px;padding-left: 4px;'></td>" +
        "</tr>");

        $grid2.after("<tr role='row' style='height: 26px;'>" +
        "  <td style='border-left: 1px solid #b1b1b1;border-top: 1px solid #b1b1b1;' rowspan='2' ></td>" +
        "  <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA;text-align: right'>当前页合计 :&nbsp;</td>" +
        "  <td class='text'  name='currentNumber2' id='currentNumber2' style='border: 1px solid #b1b1b1;text-align: left'></td>" +
        "  <td class='text'  name='currentAmount2' id='currentAmount2' style='border: 1px solid #b1b1b1;text-align: right ;padding-right: 10px;padding-left: 4px;'></td>" +
        "</tr>" +
        "<tr role='row' style='height: 26px;'>" +
        "  <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA;text-align: right'>总合计 :&nbsp;</td>" +
        "  <td class='text'  name='totalNumber2' id='totalNumber2' style='border: 1px solid #b1b1b1;text-align: left'></td>" +
        "  <td class='text'  name='totalAmount2' id='totalAmount2' style='border: 1px solid #b1b1b1;text-align: right;padding-right: 10px;padding-left: 4px;'></td>" +
        "</tr>");
    }
}

$(document).ready(function(){
    $("input[name='merchantName']").val("");
    BY121405.initDataGrid();
    BY121405.initTable();
})