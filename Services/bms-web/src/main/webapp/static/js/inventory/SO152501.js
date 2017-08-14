/**
 * 分销正常库存JS
 */
var $List_Grid;
var SO152501 = {
    searchButtonId: "SO152501_SEARCH",
    formId: "SO152501Form",
    init: function () {
        $List_Grid = $('#SO251201_list_grid').grid({
            actionHandler: SO152501.actionHandler
            /*fnDrawCallback :SO151401.fnDrawCallback*/
        });
        this.searchData();
        this.exportOrder();
    },
    searchData: function () {
        $("#" + SO152501.searchButtonId).click(function () {
            FormUtils.setFormValue(SO152501.formId, "SO152501");
            $List_Grid.fnDraw()
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "save") {
            $('#main-content').postUrl(Main.contextPath + "/SO152501/save/", {
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
    },
    getDate: function () {//  获取时间  格式：
        var date = new Date();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + month + strDate + date.getHours() + date.getMinutes() + date.getSeconds();
        return currentdate;
    },
    exportOrder:function(){
        $("#SO152501_EXPORTORDER").click(function () {
            var title="supplierInvList-" + SO152501.getDate()
            var param="";
            downloadAsync("supplierInvTemp","SO152501ExportInvService","msk-inv",title+".xlsx",param);
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SO152501.init();
});