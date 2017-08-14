/**
 * 买家列表JS
 * Created by marshall on 16/3/9.
 */
var BY12130301 = {
    BY12130301Grid: null,
    initDataGrid: function () {
        BY12130301.BY12130301Grid = $('#BY12130301_Grid').grid({

        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BY12130301.initDataGrid();
});