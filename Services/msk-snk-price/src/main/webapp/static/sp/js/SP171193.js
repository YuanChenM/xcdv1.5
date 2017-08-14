/**
 * Created by wang_shuai on 2016/5/17.
 */

var SP171193 = {
    LISTGRID : null,
    THISROWDATA : null,
    THISROWINDEX : null,
    init: function () {

        //var salePlatform = $('#salePlatform').val();
        //if(salePlatform == 1 || salePlatform == 4 || salePlatform == 5){
        //    $("#SP171193Form").attr("action", "SP171193/search2");
        //}else{
        //    $("#SP171193Form").attr("action", "SP171193/searchSp2");
        //}

        SP171193.LISTGRID = $('#SP171193Grid').grid({
            fnRowCallback : SP171193.rowCallback,
            editCellOnBlurHandler :SP171193.cellOnBlurHandler
        });
        SP171193.bindConfirmButton();
    },
    cellOnBlurHandler : function($comp){
        //获得TD
        var $td = $comp.parent();
        //获得TR
        var $tr = $td.parent();
        //获得所有行
        var $trs = $tr.parent();
        //获得当前行
        SP171193.THISROWINDEX = $trs.children().index($tr);
        //获得当前行数据
        var num=$comp.val();
        SP171193.THISROWDATA = SP171193.LISTGRID.fnGetData(SP171193.THISROWINDEX);
        //$("#hiddenDiv").postUrl(Main.contextPath+"/SP171193/loadPrice",{pdCode:SP171193.THISROWDATA.pdCode,orderNum:num}, SP171193.loadPriceCallback,{refreshHtml:false});
    },
    loadPriceCallback : function(data){
        if(data==null||data==''){
            $.alertMessage.error("当前产品没有价盘信息");
            return;
        }


    },
    rowCallback : function(tr,data){
    },
    bindConfirmButton : function(){
        $("#SP171193_CONFIRM").click(function(){
            //debugger;
            var selectValue = SP171193.LISTGRID.getChoiceOne();
            SP171191.setPar(selectValue.wayCode,selectValue.wayName);
            $.pdialog.close();
        });
    }
}

$(document).ready(function() {
    //初始化调用
    SP171193.init();
});

