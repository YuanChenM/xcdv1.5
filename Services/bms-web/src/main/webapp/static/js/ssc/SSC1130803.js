/**
 *
 * Created ssc wu_honglei on 16/8/9.
 */

var SSC1130803 = {
    listGrid : null,
    thisRowData: null,
    thisRowIndex:null,
    formId:"SSC1130803Form",
    init: function () {
        SSC1130803.listGrid = $("#SSC1130803_list_grid").grid({
            actionHandler:SSC1130803.actionHandler,
            editCellOnBlurHandler :SSC1130803.cellOnBlurHandler
        })
        this.bindConfirmButton();
    },
    cellOnBlurHandler : function($comp){
        //获得TD
        var $td = $comp.parent();
        //获得TR
        var $tr = $td.parent();
        //获得所有行
        var $trs = $tr.parent();
        //获得当前行
        SSC1130803.thisRowIndex = $trs.children().index($tr);
        //获得当前行数据
        var num=$comp.val();
        SSC1130803.thisRowData = SSC1130803.listGrid.fnGetData(SSC1130803.thisRowIndex);
    },
    bindConfirmButton:function(){
        $("#SSC1130803_CONFIRM" ).click(function(){

            var selectEpOne = SSC1130803.listGrid.getChoiceOne();

            var supplierInputId =$("#supplierInputId").val();
            var bankInputId =$("#bankInputId").val();
            var bankAccountInputId =$("#bankAccountInputId").val();
    
            $("#"+supplierInputId).val(selectEpOne.epName);
            $("#"+bankInputId).val(selectEpOne.balBank);
            $("#"+bankAccountInputId).val(selectEpOne.balAccount);
            $.pdialog.close("chooseEpDialog");

        });
    }

}


$(document).ready(function () {
    // 初始化调用
    SSC1130803.init();

});


