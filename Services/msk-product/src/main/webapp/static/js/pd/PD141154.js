/**
 *标准档案卡列表JS
 *@author pxg
 */
var PD141154 = {
    PD141154Grid: null,
    formId:"PD141154Id",
    initDataGrid: function () {
        PD141154.PD141154Grid = $('#pd141154_grid').grid({
            actionHandler: PD141154.actionHandler
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        /** 产品包装 */
        if (coltype == "detail"){
            var divId="<input type='hidden' name='filterMap[tcProviderId]' value='"+rowdata.tcProviderId+"'/>";
            $("#" + PD141154.formId).append(divId);
            formData = getFormData($("#" + PD141154.formId));
            $('#main-content').postUrl(Main.contextPath + "/PD141155/init",formData);
        }
    }
}
$(document).ready(function () {
    //初始化调用
    PD141154.initDataGrid();
});