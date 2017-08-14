/**
 * 买家订单汇总列表JS
 * author:yuan_zhifei
 * createDate:2016-07-05
 */
var $List_Grid;
var BY121308 = {
    init: function () {
        $List_Grid = $('#BY121308_list_grid').grid({
            actionHandler: BY121308.actionHandler
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {

    }

}

$(document).ready(function () {
    // 初始化调用
    BY121308.init();
});