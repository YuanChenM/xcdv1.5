/**
 * 供应商待申报产品一览
 */
var $List_Grid;
var SP171105 = {
    searchButtonId: "SP171105_SEARCH",
    formId: "SP171105Form",
    init: function () {
        $List_Grid = $('#SP171105_grid').grid({
            actionHandler: SP171105.actionHandler,
            can_abolish: SP171105.canAbolish
        });
        this.linkage();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "edit") {
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  Start */
            Main.detailWindow(Main.contextPath + "/SP171106/init", rowdata, "供应商数量申报详细");
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  end */
        }
    },
    canAbolish: function (rowdata) {
        if (rowdata.isConfirm != 3 && rowdata.publishNum > 0) {
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
            FormUtils.setFormValue(SP171105.formId, "SP171105");
            $List_Grid.fnDraw();
        });
    }
}


$(document).ready(function () {
    // 初始化调用
    SP171105.init();
});