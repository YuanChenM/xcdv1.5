/**
 * 菜场定性定级各阶段一览表JS
 * author:yuan_zhifei
 * createDate:2016-07-13
 */
var $List_Grid;
var BY121412 = {
    init: function () {
        $List_Grid = $('#BY121412_list_grid').grid({
            actionHandler: BY121412.actionHandler
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {

    }

}

$(document).ready(function () {
    // 初始化调用
    BY121412.init();
});