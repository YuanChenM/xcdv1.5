/**
 * 供应商待报价产品一览
 */
var $List_Grid;
var SP171109 = {
    searchButtonId: "SP171109_SEARCH",
    formId: "SP171109Form",
    init: function () {
        $List_Grid = $('#SP171109_grid').grid({
            actionHandler: SP171109.actionHandler,
            can_abolish: SP171109.canAbolish
        });
        this.linkage();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "edit") {
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  Start */
            Main.detailWindow(Main.contextPath + "/SP171110/init", rowdata, "供应商价格申报详细");
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  end */
        }
    },

    linkage: function () {
        $("#pricePeriod").change(function () {
            var demandLimitedDateShow = $("#pricePeriod").find("option:selected").attr("showName");
            $("#demandLimitedDateShow").html(demandLimitedDateShow);
            var demandStartDate = $("#pricePeriod").find("option:selected").attr("StartDate");
            $("#priceStartDate").val(demandStartDate);
            var demandEndDate = $("#pricePeriod").find("option:selected").attr("EndDate");
            $("#priceEndDate").val(demandEndDate);
            var isNewPrice = $("#pricePeriod").find("option:selected").attr("isNewPrice");
            $("#isNewPrice").val(isNewPrice);
            FormUtils.setFormValue(SP171109.formId, "SP171109");
            $List_Grid.fnDraw();
        });
    },
    canAbolish: function (rowdata) {
        var viewFlg = $("#viewFlg").val();
        if ((viewFlg == 1 || rowdata.stockQty > 0) && rowdata.isNewPrice == 1) {
            return true;
        }
        return false;
    }
}

$(document).ready(function () {
    // 初始化调用
    SP171109.init();
});
