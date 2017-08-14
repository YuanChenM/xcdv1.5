var $List_Grid;
var BY121327 = {
    searchButtonId: "BY121327_SEARCH",
    formId: "BY121327Form",
    fileStartTime: "#fileStartTime",
    fileEndTime: "#fileEndTime",
    init: function () {
        $List_Grid = $('#BY121327_list_grid').grid({

        });
    },
    


}
$(document).ready(function () {
    // 初始化调用
    BY121327.init();
});