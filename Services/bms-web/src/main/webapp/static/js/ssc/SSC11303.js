/**
 * Created by tao_zhifa on 2016/6/28.
 */
var $Contract_List;
var SSC11303 = {
    formId: "SSC11303_form",

    init: function() {
        this.initContractList();
        this.initDatePicker();
        this.clickAddButton();
        this.clickSearchButton();
    },

    initDatePicker: function() {
        this.closeDatePicker();
        this.bindDatePicker($("input[name*='contractActDateStr']"));
    },

    bindDatePicker: function(timeId) {
        $(timeId).datepicker({
            dateFormat: "yy-mm-dd",
            closeText: "清除",
            showButtonPanel: true
        });
        $(timeId).attr("readonly", "readonly");
    },

    closeDatePicker: function() {
        $(document).on("click", "button.ui-datepicker-close", function() {
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },

    initContractList: function() {
        $Contract_List = $("#SCC11303_contract_list").grid({
            actionHandler: SSC11303.actionHandler,
            can_delete: SSC11303.canAbolish,
            can_abolish: SSC11303.canAbolish
        })
    },

    actionHandler: function(rowdata, coltype, row, col) {
        if (coltype == "detail") {
            //合同详细
            Main.detailWindow(Main.contextPath + "/SSC11304/init", {
                contractId: rowdata.contractId
            }, "合同详细");
        }
        else if (coltype == "audit") {
            //付款一览
            Main.detailWindow(Main.contextPath + "/SSC11307/init", {
                contractCode: rowdata.contractCode,
                navigation: "contract"
            }, "付款申请一览");
        }
        else if (coltype == "delete") {
            $('#main-content').postUrl(Main.contextPath + "/SSC11303/checkBeforeDeleting", {
                contractId: rowdata.contractId
            }, function (data) {
                if (data == "HT_DELETABLE") {
                    $.alertMessage.confirm("确定要删除这条数据吗？", function () {
                        $.alertMessage.close();
                        $('#main-content').postUrl(Main.contextPath + "/SSC11303/deleteContractBasic", {
                            contractId: rowdata.contractId,
                            ver: rowdata.ver
                        }, function (data) {
                            if ("S" == data) {
                                $("#main-content").postUrl(Main.contextPath + "/SSC11303/init");
                                $.alertMessage.info("删除成功！");
                            }
                        }, {refreshHtml: false});
                    });
                }
                else if (data == "HX_EXIST") {
                    $.alertMessage.info("请先删除该合同对应的核销单，再删除合同！");
                }
                else if (data == "FK_EXIST") {
                    $.alertMessage.info("请先删除该合同对应的付款申请单，再删除合同！");
                }
                else if (data == "FH_EXIST") {
                    $.alertMessage.info("请先删除该合同对应的发货订单，再删除合同！");
                }
            }, {refreshHtml: false});
        }
    },

    canAbolish: function(rowdata) {
        var Status = rowdata.contractStatus;
        if (Status == "9") {
            return false;
        }
        return true;
    },

    clickAddButton: function() {
        $("#SSC11303_ADD").click(function () {
            Main.detailWindow(Main.contextPath + "/SSC11304/init", {}, "合同详细");
        });
    },

    /**
     * 点击查询按钮，刷新合同列表
     */
    clickSearchButton: function() {
        $("#SSC11303_SEARCH").click(function() {
            FormUtils.setFormValue(SSC11303.formId, "SSC11303");
            $Contract_List.fnDraw();
        });
    }
};

$(document).ready(function() {
    SSC11303.init();
});