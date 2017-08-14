/**
 * 买家编码总控
 */
var BY121311 = {
    BY121311Grid: null,
    initDataGrid: function () {
        BY121311.BY121311Grid = $('#BY121311_Grid').grid({
            actionHandler: BY121311.actionHandler
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BY121311.initDataGrid();
});