/**
 *标准档案卡列表JS
 *@author jiang_nan
 */
var SL241119 = {
    newButtonId: "SL241119_SAVE",
    SL241119Grid: null,
    initDataGrid: function () {
        SL241119.SL241119Grid = $('#SL241119_grid').grid({
            actionHandler: SL241119.actionHandler
        });
        this.bindSearchButton();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        /** 产品包装 */
        if (coltype == "delete") {
            $.alertMessage.confirm("确定删除当前数据吗？", function () {
                $.alertMessage.close();
                //formData = getFormData($("#"+PD141107.formId));
                //$('#main-content').postUrl($("#"+PD141107.formId).attr("action"), formData);
            });
        }
        if (coltype == "edit") {
            var url = Main.contextPath + "/PD141112/init";
            $.pdialog.open("产品包装编辑", url, {width: 750, height: 410}, {
                standardId: rowdata.standardId,
                normsCode: rowdata.normsCode
            })
        }
    },
    bindSearchButton: function () {
        $("#" + SL241119.newButtonId).click(function () {
            SL241119.newData();
        });
    },
    newData: function () {
        $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
            $.alertMessage.close();
            var checks=SL241119.SL241119Grid.getChoiceData();
            var checkNorms={};
            $.each(checks,function(i,data){
                checkNorms[i]=data.normsCode;
            });
            var formData = getFormData($("#SL241119Form"));
            formData.normsArray=checkNorms;
            $('#main-content').postUrl(Main.contextPath + "/SL241119/save",formData, function () {
                $('#main-content').postUrl(Main.contextPath + "/SL241116/init/"+$('#slCode').val(),{enterMark:"init"},Main.hiddenHeader);
                //$('#main-content').postUrl(Main.contextPath + "/SL241116/init/"+$('#slCode').val());
            },{refreshHtml:false});
            $("#headBreadCrumb").hide();
        });
    }
}
$(document).ready(function () {
    //初始化调用
    SL241119.initDataGrid();
});