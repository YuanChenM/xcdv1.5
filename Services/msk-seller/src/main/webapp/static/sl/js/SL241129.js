/**
 *标准档案卡列表JS
 *@author gyh
 */
var SL241129 = {
    SL241129Grid: null,
    formId:"SL241129Id",
    initDataGrid: function () {
        SL241129.SL241129Grid = $('#SL241129_grid').grid({
            fnRowCallback: SL241129.rowCallback,
            actionHandler: SL241129.actionHandler
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        /** 包装申请信息 */
        if (coltype == "detail"){
            $.pdialog.open("包装申请信息", Main.contextPath + "/SL241129/showNorms", {
                width: 800,
                height: 300
            }, rowdata);
        }
    },
    rowCallback: function (tr, data) {
        if(data.weightCode==null||data.weightCode==''){
            var $td = $(tr).children('td').eq(14);
            $td.html("");
        }
    }
}
$(document).ready(function () {
    //初始化调用
    SL241129.initDataGrid();
});