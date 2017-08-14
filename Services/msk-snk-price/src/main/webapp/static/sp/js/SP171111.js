
var SP171111 = {
    SP171111Grid: null,
    formId : "SP171111Form",
    detailRows: [],
    trIndex: 0,
    initDataGrid: function () {
        SP171111.SP171111Grid = $('#SP171111_list_grid').grid({
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SP171111.initDataGrid();
});