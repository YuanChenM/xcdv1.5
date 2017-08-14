var $List_GridIsTarget;
var $List_GridNonTarget;
var BY121413 = {
    init: function () {
        $List_GridIsTarget = $('#BY12141301_list_grid').grid({
            actionHandler: BY121413.isTargetActionHandler
        });

        $List_GridNonTarget = $('#BR12141302_list_grid').grid({
            actionHandler: BY121413.nonTargetActionHandler
        });
        this.isTargetBindAddBtn();
        this.nonTargetBindAddBtn();
    },
    //目标市场table操作
    isTargetActionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "edit") {
            //目标市场买家标志  目标买家=1
            var isTargetMerchant = "1";
            //编辑按钮时候,店铺号是否可编辑,0不可编辑
            var isChecked = "0";
            $.pdialog.open("批发市场目标买家编辑页面", Main.contextPath + "/BY12141301/init/", {width: "25%"}, {
                isChecked: isChecked,
                isTargetMerchant: isTargetMerchant,
                marketId: rowdata.marketId,
                storeId:rowdata.storeId,
                remark: rowdata.remark,
                buyerStoreNo: rowdata.buyerStoreNo
            });
        }

        if (coltype == "delete") {
            $.alertMessage.confirm("确定删除当前数据吗？", function () {
                $('#main-content').postUrl(Main.contextPath + "/BY121413/delete/",{
                    buyerStoreNo:rowdata.buyerStoreNo,
                    marketId: rowdata.marketId,
                    storeId:rowdata.storeId
                }, function (data) {
                    if (data == "S") {
                        $.alertMessage.info("删除成功!");
                        $.alertMessage.close();
                        $List_GridIsTarget.fnDraw();
                    } else {
                        $.alertMessage.warn("删除失败");
                        $.alertMessage.close();
                    }
                }, {refreshHtml: false});

            })
        }

    },
    //非目标市场table操作
    nonTargetActionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "edit") {
            var isTargetMerchant = "0";
            var isChecked = "0";
            $.pdialog.open("批发市场非目标买家编辑页面", Main.contextPath + "/BY12141301/init/", {width: "25%"}, {
                isChecked: isChecked,
                remark: rowdata.remark,
                isTargetMerchant: isTargetMerchant,
                storeId:rowdata.storeId,
                marketId: rowdata.marketId,
                buyerStoreNo: rowdata.buyerStoreNo
            });
        }

        if (coltype == "delete") {
            $.alertMessage.confirm("确定删除当前数据吗？", function () {
                $('#main-content').postUrl(Main.contextPath + "/BY121413/delete/" ,{
                    buyerStoreNo:rowdata.buyerStoreNo,
                    marketId: rowdata.marketId,
                    storeId:rowdata.storeId
                }, function (data) {
                    if (data == "S") {
                        $.alertMessage.info("删除成功!");
                        $.alertMessage.close();
                        $List_GridNonTarget.fnDraw();
                    } else {
                        $.alertMessage.warn("删除失败");
                        $.alertMessage.close();
                    }
                }, {refreshHtml: false});

            })
        }

    },
    isTargetBindAddBtn: function () {
        var addFlg = "1";
        var marketId = $("#marketId").val();
        var isTargetMerchant = "1";
        $("#BY121413_TARGET_ADD").click(function () {
            $.pdialog.open("批发市场目标买家新增页面", Main.contextPath + "/BY12141301/init/", {width: "25%"}, {
                addFlg: addFlg,
                isTargetMerchant: isTargetMerchant,
                marketId: marketId
            });
        })
    },

    nonTargetBindAddBtn: function () {
        var addFlg = "1";
        var marketId = $("#marketId").val();
        var isTargetMerchant = "0";
        $("#BY121413_NOTTARGET_ADD").click(function () {
            $.pdialog.open("批发市场非目标买家新增页面", Main.contextPath + "/BY12141301/init/", {width: "25%"}, {
                addFlg: addFlg,
                isTargetMerchant: isTargetMerchant,
                marketId: marketId
            });
        })
    }

}

$(document).ready(function () {
    // 初始化调用
    BY121413.init();
});

document.onkeydown = function(e){
    var e = e || window.event;
    if(e.keyCode == 13) {
        return false;
    }
};

