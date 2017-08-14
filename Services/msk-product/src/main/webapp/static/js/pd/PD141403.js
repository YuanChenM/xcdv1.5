/**
 * 卖家一览
 *
 * @author xhy
 */

var PD141403 = {
    formId: "PD141403FormId",
    newButtonId: "PD141403_NEW",
    init: function () {
        $List_Grid = $('#pd141403_list_grid').grid({
            actionHandler: PD141403.actionHandler
        });
        this.newData();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        /** 操作按钮 */
        if (coltype == "detail") {
            $('#main-content').postUrl(Main.contextPath + "/PD141404/init", rowdata);
        }
    },
    newData: function () {
        $("#" + PD141403.newButtonId).click(function () {
            $('#main-content').postUrl(Main.contextPath + "/PD141404/init",
                {
                    pdClassesName: PD_CLASSES_NAME,
                    machiningName: MACHINING_NAME,
                    pdBreedName: PD_BREED_NAME,
                    pdFeatureName: PD_FEATURE_NAME,
                    weightName: WEIGHT_NAME,
                    slId: SL_CODE,
                    slCodeDis: SL_CODE_DIS,
                    slPdId: SL_PD_ID,
                    standardId: STANDARD_ID,
                    showDate: SHOW_DATE,
                    classestreeCode:CLASSESTREE_CODE
                });
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    PD141403.init();
});
