/**
 *标准档案卡列表JS
 *@author jiang_nan
 */
var PD141111 = {
    newButtonId: "PD141111_NEW",
    returnButtonId: "PD141111_RETURN",
    PD141111Grid: null,
    classesCode: '',
    breedCode: '',
    featureCode: '',
    initDataGrid: function () {
        PD141111.PD141111Grid = $('#pd141111_grid').grid({
            actionHandler: PD141111.actionHandler
        });
        this.bindSearchButton();
        this.bindRuturnButton();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        /** 产品包装 */
        if (coltype == "delete") {
            $.alertMessage.confirm("确定删除当前数据吗？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl( Main.contextPath + '/PD141112/close',{standardId: rowdata.standardId,
                        normsCode: rowdata.normsCode}
                   );
            });
        }
        if (coltype == "edit") {
            var url = Main.contextPath + "/PD141112/init";
            $.pdialog.open("产品包装编辑", url, {width: 750, height: 410}, {
                standardId: rowdata.standardId,
                normsCode: rowdata.normsCode
            })
        }
    },
    bindSearchButton: function () {
        $("#" + PD141111.newButtonId).click(function () {
            PD141111.newData();
        });
    },
    newData: function () {
        $.pdialog.open("新增产品包装", Main.contextPath + "/PD141112/init", {
            width: 750,
            height: 380
        }, {
            standardId: $("#standardIds").val()
        });
    },
    bindRuturnButton: function () {
        $("#" + PD141111.returnButtonId).click(function () {
            $('#main-content').postUrl(Main.contextPath + "/PD141113/init",{"filterMap['breedCode']":BREED_CODE,"filterMap['classesCode']":CLASSES_CODE});
        });
    }
}
$(document).ready(function () {
    //初始化调用
    PD141111.initDataGrid();
});