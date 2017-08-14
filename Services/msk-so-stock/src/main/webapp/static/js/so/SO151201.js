/**
 * 分销正常库存JS
 */
var $List_Grid;
var SO151201 = {
    searchButtonId: "SO151201_SEARCH",
    formId: "SO151201Form",
    init: function () {
        $List_Grid = $('#SO251201_list_grid').grid({
            actionHandler: SO151201.actionHandler
            /*fnDrawCallback :SO151401.fnDrawCallback*/
        });
        this.searchData();
    },
    searchData: function () {
        $("#" + SO151201.searchButtonId).click(function () {
            FormUtils.setFormValue(SO151201.formId, "SO151201");
            $List_Grid.fnDraw()
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "save") {
            $('#main-content').postUrl(Main.contextPath + "/SO151201/save/", {
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
    SO151201.init();
});