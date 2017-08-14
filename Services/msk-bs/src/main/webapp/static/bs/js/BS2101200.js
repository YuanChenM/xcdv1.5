
var $List_Grid;
var BS2101200 = {
    formId: "bs2101200FormId",
    init: function () {
        $List_Grid = $('#bs2101200_list_grid').grid({
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BS2101200.init();
});
