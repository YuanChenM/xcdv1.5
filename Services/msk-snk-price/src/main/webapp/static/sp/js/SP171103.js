/**
 * Created by xu_wei
 */

var $List_Grid;
var SP171103 = {
    formId:"SP171103Form",
    /*searchButtonId:"SP171103_SEARCH",*/
    init : function() {
        $List_Grid = $('#SP171103_list_grid').grid({
            actionHandler:SP171103.actionHandler,
            can_abolish: SP171103.canAbolish
        });
        this.linkage();
    },
    canAbolish: function (rowdata){
        if(rowdata.isConfirm != '0'){
            return true;
        }
        return false;
    },
    linkage: function () {
        $("#demandYearMonth").change(function () {
            var demandLimitedDateShow = $("#demandYearMonth").find("option:selected").attr("showName");
            $("#demandLimitedDateShow").html(demandLimitedDateShow);
            var demandStartDate = $("#demandYearMonth").find("option:selected").attr("StartDate");
            $("#demandStartDate").val(demandStartDate);
            var demandEndDate = $("#demandYearMonth").find("option:selected").attr("EndDate");
            $("#demandEndDate").val(demandEndDate);

            FormUtils.setFormValue(SP171103.formId, "SP171103");
            $List_Grid.fnDraw();
        });
    },
    bindSearchButtonId : function(){
        $("#"+SP171103.searchButtonId).click(function(){
            FormUtils.setFormValue(SP171103.formId, "SP171103");
            $List_Grid.fnDraw()
        });
    },

    actionHandler:function(rowdata,coltype,row,col){
        if(coltype == "check")
        {
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  Start */
            var demandYearMonth = $("#demandYearMonth").find("option:selected").val();
            var demandYearMonthShow = $("#demandYearMonth").find("option:selected").text();
            var demandLimitedDateShow = $('#demandLimitedDateShow').text();
            var nowMonth = $("#nowMonth").val();
            var data = new Object();
            data['demandId'] = rowdata.demandId,
            data['lgcsCode'] = rowdata.lgcsCode,
            data['lgcsName'] = rowdata.lgcsName,
            data['pdCode'] = rowdata.pdCode,
            data['pdName'] = rowdata.pdName,
            data['breedName'] = rowdata.breedName,
            data['classesName'] = rowdata.classesName,
            data['machiningName'] = rowdata.machiningName,
            data['featureName'] = rowdata.featureName,
            data['weightName'] = rowdata.weightName,
            data['gradeCode'] = rowdata.gradeCode,
            data['isConfirm'] = rowdata.isConfirm,
            data['demandLimitedDateShow'] = demandLimitedDateShow,
            data['demandYearMonthShow'] = demandYearMonthShow,
            data['demandYearMonth'] = demandYearMonth,
            data['nowMonth'] =nowMonth
            Main.detailWindow(Main.contextPath + "/SP171104/init/", data, "采购员用供应商数量申报详细");
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  end */
        };
    }
}
$(document).ready(function() {
    // 初始化调用
    SP171103.init();
});
