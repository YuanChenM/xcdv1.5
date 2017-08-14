/**
 * Created by wang_shuai on 2016/5/20.
 */
var SP171192 = {
    SP171192Grid : null,
    formId : "SP171192Form",
    THISROWDATA : null,
    THISROWINDEX : null,
    init: function () {
        SP171192.SP171192Grid = $('#SP171192_Grid').grid({
            fnRowCallback : SP171192.rowCallback,
            editCellOnBlurHandler :SP171192.cellOnBlurHandler,
            actionHandler : SP171192.actionHandler
        });
        SP171192.bindConfirmButton();
    },
    cellOnBlurHandler : function($comp){
        //获得TD
        var $td = $comp.parent();
        //获得TR
        var $tr = $td.parent();
        //获得所有行
        var $trs = $tr.parent();
        //获得当前行
        SP171192.THISROWINDEX = $trs.children().index($tr);
        //获得当前行数据
        var num=$comp.val();
        SP171192.THISROWDATA = SP171192.LISTGRID.fnGetData(SP171192.THISROWINDEX);

    },
    rowCallback : function(tr,data){
    },
    bindConfirmButton : function(){
        $("#SP171192_CONFIRM").click(function(){
            $.pdialog.open("添加需求等级标准", Main.contextPath + "/SP17119201/searchMaxCode", {width: "70%"},
                {
                });

        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        /** 操作按钮 */

        if (coltype == "edit") {

            $.pdialog.open("编辑需求等级标准", Main.contextPath + "/SP17119201/updateInit", {width: "70%"},
                {
                    wayCode:rowdata.wayCode,
                    wayName:rowdata.wayName,
                    supOrder:rowdata.supOrder,
                    supBlance:rowdata.supBlance,
                    order1:rowdata.order1,
                    blance1:rowdata.blance1,
                    order2:rowdata.order2,
                    blance2:rowdata.blance2,
                    order3:rowdata.order3,
                    blance3:rowdata.blance3,
                    order4:rowdata.order4,
                    blance4:rowdata.blance4,
                    order5:rowdata.order5,
                    blance5:rowdata.blance5,
                    order6:rowdata.order6,
                    blance6:rowdata.blance6,
                    order7:rowdata.order7,
                    blance7:rowdata.blance7,
                    order8:rowdata.order8,
                    blance8:rowdata.blance8,
                    order9:rowdata.order9,
                    blance9:rowdata.blance9
                });

        }else if(coltype == "close"){
                var wayCode = rowdata.wayCode;
                $.alertMessage.confirm("你确定要删除当前数据吗？", function() {
                    $('#main-content').postUrl(Main.contextPath + "/SP171192/del",{wayCode:wayCode},function(){
                        $.alertMessage.info("删除成功");
                    });
                });
        }
    }
}

$(document).ready(function() {
    //初始化调用
    SP171192.init();
});