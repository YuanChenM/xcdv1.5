/**
 * 卖家产品信息及状态审核JS
 *
 * @author gyh
 */
var $List_Grid;
var SSC11329 = {
    formId: 'SSC11329Form',
    searchButtonId: "SSC11329_SEARCH",
    init: function () {
        $("#slTncGradeCode").prepend("<option value=''>--请选择--</option>");
        $("#slTncGradeCode").val("");
        $("#status").prepend("<option value=''>--请选择--</option>");
        $("#status").val("");
        $List_Grid = $('#SSC11329_list_grid').grid({
            actionHandler: SSC11329.actionHandler
        });
        this.bindSearchButton();
    },
    bindSearchButton: function () {
        $("#" + SSC11329.searchButtonId).click(function () {
            FormUtils.setFormValue(SSC11329.formId, "SSC11329");
            $List_Grid.fnDraw()
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        //其他
        if (coltype == 'audit') {
            $.pdialog.open("卖家产品标准档案卡", Main.contextPath + "/SSC11330/getSlPdStandards", {width: "90%", height:800},{
                "slCode": $('#slCode').val().trim(),
                "prodEpId":  rowdata.prodEpId,
                "brandEpId":  rowdata.brandEpId,
                "brandId":  rowdata.brandId,
                "pdClassesCode":  rowdata.pdClassesCode,
                "machiningCode":  rowdata.machiningCode,
                "pdBreedCode":  rowdata.pdBreedCode,
                "pdFeatureCode":  rowdata.pdFeatureCode,
                "weightCode":  rowdata.weightCode
            });
        }
        //产品品种图片
        if (coltype == 'search') {

            var imageDate={};
            //imageDate.slShowName = slShowName;
            imageDate.prodEpId=rowdata.prodEpId;
            imageDate.pdClassesCode=rowdata.pdClassesCode;
            imageDate.machiningCode=rowdata.machiningCode;
            imageDate.pdBreedCode=rowdata.pdBreedCode;
            $.pdialog.open(rowdata.pdClassesName + rowdata.pdBreedName + "图片信息", Main.contextPath + "/SSC11329/showImage", {
                width: 700,
                height: 500
            }, imageDate);
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    SSC11329.init();
});

