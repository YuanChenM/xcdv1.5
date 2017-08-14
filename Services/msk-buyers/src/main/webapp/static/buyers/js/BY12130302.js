/**
 * 买家列表JS
 * Created by marshall on 16/3/9.
 */
var BY12130302 = {
    BY12130302Grid: null,
    initDataGrid: function () {
        BY12130302.BY12130302Grid = $('#BY12130302_Grid').grid({

        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BY12130302.initDataGrid();
});