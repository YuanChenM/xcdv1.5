/**
 * 核销单一览JS
 *
 * @author yang_yang
 */
var $List_Grid;
var SSC11321 = {

    init: function () {
        $List_Grid = $('#SSC11321_list_grid').grid({
            linkHandler: SSC11321.linkHandler,
            actionHandler: SSC11321.actionHandler,
            deletable: SSC11321.isDeletable
        });
        this.bindButton();
        this.bindDatePicker($(":input[name*='verificationDateStr']"));
        Main.hlLeftMainMenu("SSC11321");
        this.closeDate();
        $("input").keypress(function(e) {
            if (13 == e.keyCode) {
                FormUtils.setFormValue("SSC11321Form", "SSC11321");
                $List_Grid.fnDraw();
            }
        });
    },

    isDeletable: function(rowdata) {
        if (5 == rowdata.status) {
            return false;
        }
        return true;
    },

    bindDatePicker: function (timeId) {
        $(timeId).datepicker({
            dateFormat: "yy-mm-dd",
            closeText: "清除",
            showButtonPanel: true
        });
        $(timeId).attr("readonly", "readonly");
    },

    closeDate: function () {
        $(document).on("click", "button.ui-datepicker-close", function () {
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },

    linkHandler: function (rowdata, colname, row, col) {
        if (colname == "contractCode") {
            Main.detailWindow(Main.contextPath + "/SSC11304/show", {
                contractId: rowdata.contractId,
                verificationId: rowdata.verificationId
            }, "合同详细");
        }
    },

    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "detail") {
            Main.detailWindow(Main.contextPath + "/SSC11322/init", {verificationId: rowdata.verificationId}, "核销单详细");
        }
        else if (coltype == "delete") {
            if (rowdata.status >= 3) {
                $.alertMessage.info("双方已确认，无法删除核销单！");
                return;
            }

            $.alertMessage.confirm("确定要删除这条数据吗？", function () {
                $.alertMessage.close();
                $("#main-content").postUrl(Main.contextPath + "/SSC11321/delete", {
                    verificationId: rowdata.verificationId,
                    contractId: rowdata.contractId,
                    contractStatus: rowdata.contractStatus,
                    ver: rowdata.ver
                }, function (resp) {
                    if ("S" == resp) {
                        $("#main-content").postUrl(Main.contextPath + "/SSC11321/init");
                        $.alertMessage.info("删除成功！");
                    }
                    else {
                        $.alertMessage.info("删除失败！");
                    }
                });
            });
        }
    },

    // 绑定按钮
    bindButton: function () {
        //新增
        $("#" + SSC11321.addButtonId).click(function () {
            $('#main-content').postUrl(Main.contextPath + "/SSC11306/init/");
        });
        //查询
        $("#SSC11321_SEARCH").click(function () {
            FormUtils.setFormValue("SSC11321Form", "SSC11321");
            $List_Grid.fnDraw();
        });
    }
};

$(document).ready(function () {
    // 初始化调用
    SSC11321.init();
});

