var $List_Grid;
var BY121316 = {
    init: function () {
        $List_Grid = $('#BY121316_list_grid').grid({
            actionHandler: BY121316.actionHandler
        });
        this.bindAddBtn();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        var settingType = rowdata.settingType;
        var settingStartValue = rowdata.settingStartValue;
        var settingEndValue = rowdata.settingEndValue;
        var settingValue = rowdata.settingValue;
        var reg = /^[1-9]\d*$/;
        if (coltype == "save") {


        }
        if (coltype == "delete") {
            $.alertMessage.confirm("确认删除该条数据么", function () {
                $('#main-content').postUrl(Main.contextPath + "/BY121316/delete/" ,{
                    id:rowdata.id,
                    buyerId:rowdata.buyerId
                      });
                $.alertMessage.close();
                $List_Grid.fnDraw();
            })
        }
    },

    bindAddBtn: function () {

    }

}

$(document).ready(function () {
    // 初始化调用
    BY121316.init();
});

