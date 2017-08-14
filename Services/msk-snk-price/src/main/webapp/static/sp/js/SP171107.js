
var SP171107 = {
    SP171107Grid: null,
    formId : "SP171107Form",
    detailRows: [],
    trIndex: 0,
    initDataGrid: function () {
        SP171107.SP171107Grid = $('#SP171107_list_grid').grid({
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SP171107.initDataGrid();
});