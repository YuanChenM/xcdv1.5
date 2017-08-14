/**
 * 分销正常库存JS
 */
var $List_Grid;
var SO151202 = {
    searchButtonId: "SO151202_SEARCH",
    formId: "SO151202Form",
    init: function () {
        $List_Grid = $('#SO151202_list_grid').grid({
            actionHandler: SO151202.actionHandler
            /*fnDrawCallback :SO151401.fnDrawCallback*/
        });
        this.searchData();
    },
    searchData: function () {
        $("#" + SO151202.searchButtonId).click(function () {
            FormUtils.setFormValue(SO151202.formId, "SO151202");
            $List_Grid.fnDraw()
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "save") {
            $('#main-content').postUrl(Main.contextPath + "/SO151202/save/", {
                stockId: rowdata.stockId,
                stockQty: rowdata.stockQty
            },function(data){
                if(data == "S") {
                    $.alertMessage.info("库存量修改成功!");
                } else {
                    $.alertMessage.warn("该数据可能已被删除,请刷新页面再试!");
                }
            },{refreshHtml:false});
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    SO151202.init();
});