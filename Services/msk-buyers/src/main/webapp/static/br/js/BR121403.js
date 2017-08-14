var $List_Grid;
var BR121403 = {
    init: function () {
        $List_Grid = $('#BR121403_list_grid').grid({
            actionHandler: BR121403.actionHandler,
            linkHandler: BR121403.linkHandler
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
    },
    linkHandler:function(rowdata, coltype, row, col){
        $('#main-content').postUrl(Main.contextPath + "/BR121404/init/" , {
            buyerId:rowdata.buyerId,
            buyerName:rowdata.buyerName,
            buyerCode:rowdata.buyerCode,
            marketName:rowdata.marketName
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BR121403.init();
});

