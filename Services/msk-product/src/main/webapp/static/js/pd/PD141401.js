/**
 * 卖家一览
 *
 * @author xhy
 */

var PD141401 = {
    formId: "pd141401FormId",
    init: function () {
        $List_Grid = $('#PD141401_list_grid').grid({
            actionHandler: PD141401.actionHandler
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        /** 操作按钮 */
        if (coltype == "detail") {
            $('#main-content').postUrl(Main.contextPath + "/PD141402/init",{slCode:rowdata.slCode,slCodeDis:rowdata.slCodeDis});
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    PD141401.init();
});
