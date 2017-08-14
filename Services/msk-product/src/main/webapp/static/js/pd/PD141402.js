/**
 * 卖家一览
 *
 * @author xhy
 */

var PD141402 = {
    formId: "pd141402FormId",
    init: function () {
        $List_Grid = $('#pd141402_list_grid').grid({
            actionHandler: PD141402.actionHandler
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        /** 操作按钮 */
        if (coltype == "detail") {
            $('#main-content').postUrl(Main.contextPath + "/PD141403/init",rowdata);
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    PD141402.init();
});
