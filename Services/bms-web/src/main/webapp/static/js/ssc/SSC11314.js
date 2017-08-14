/**
 * Created by wang_shuai on 2016/7/4.
 */
var $List_Grid;
var SSC11314 = {
    init: function () {
        $List_Grid = $("#SCC11314_list_grid").grid({
            actionHandler: SSC11314.actionHandler,
            can_abolish: SSC11314.canAbolish
        });
        this.bindButton();
        this.bindNewButton();
    },
    //如果状态为已删除，就不显示删除按钮
    canAbolish: function (rowdata) {
        var deliveryConfirmStatus = rowdata.deliveryConfirmStatus;
        if (deliveryConfirmStatus == "9") {
            return false;
        }
        return true;
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "detail") {
            Main.detailWindow(Main.contextPath + "/SSC11315/init/1", {
                deliveryConfirmId: rowdata.deliveryConfirmId,
                deliveryConfirmCode: rowdata.deliveryConfirmCode
            }, "订单发货确认单详细");
        }
        if (coltype == "delete") {
            $('#main-content').postUrl(Main.contextPath + "/SSC11316/findPreInto", {
                deliveryConfirmId: rowdata.deliveryConfirmId
            }, function (data) {
                //1 没有生成预入库通知单  2 已发车  3  未发车
                if (data.flag == 1) {
                    $.alertMessage.confirm("确定要删除这条数据吗？", function () {
                        $.alertMessage.close();
                        $('#main-content').postUrl(Main.contextPath + "/SSC11314/delete", {
                            deliveryConfirmCode: rowdata.deliveryConfirmCode,
                            deliveryConfirmId: rowdata.deliveryConfirmId,
                            ver:rowdata.ver
                        }, function (data) {
                            $.alertMessage.info("删除成功");
                        });
                    });
                } else if (data.flag == 2) {
                    $.alertMessage.info("已发车,不允许删除");
                } else if (data.flag == 3) {
                    var list = data.preIntoCodeList;
                    var showStr = "";
                    for (var i = 0; i < list.length; i++) {
                        if (i < list.length - 1) {
                            showStr += list[i] + ",";
                        } else {
                            showStr += list[i];
                        }
                    }

                    $.alertMessage.info("请先删除对应的预入库单：" + showStr);
                }
            }, {refreshHtml: false});
        }
    },
    bindNewButton: function () {
        $("#SSC11314_NEW").click(function () {
            $.pdialog.open("选择发货订单", Main.contextPath + "/SSC11314/chooseInit", {width: 400}, null);
        });
    },

    bindButton: function(){
        //查询
        $("#SSC11314_SEARCH").click(function () {
            FormUtils.setFormValue("SSC11314FormSearch", "SSC11314");
            $List_Grid.fnDraw();
        });
    }
};

$(document).ready(function () {
    SSC11314.init();
});