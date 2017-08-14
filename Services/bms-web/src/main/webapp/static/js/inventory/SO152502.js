/**
 * 分销正常库存JS
 */
var $List_Grid;
var SO152502 = {
    searchButtonId: "SO152502_SEARCH",
    formId: "SO152502Form",
    init: function () {
        $List_Grid = $('#SO152502_list_grid').grid({
            actionHandler: SO152502.actionHandler
            /*fnDrawCallback :SO151401.fnDrawCallback*/
        });
        this.searchData();
        this.exportOrder();
    },
    searchData: function () {
        $("#" + SO152502.searchButtonId).click(function () {
            FormUtils.setFormValue(SO152502.formId, "SO152502");
            $List_Grid.fnDraw()
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "save") {
            $('#main-content').postUrl(Main.contextPath + "/SO152502/save/", {
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
            var title="sellerInvList-" + SO152502.getDate()
            var param="";
            downloadAsync("sellerInvTemp","SO152502ExportInvService","msk-inv",title+".xlsx",param);
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SO152502.init();
});