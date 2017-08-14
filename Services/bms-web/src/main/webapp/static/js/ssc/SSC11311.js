/**
 * 生产商入库差异单
 * Created by xia_xiaojie on 2016/7/4.
 */

/**
 * 声明对象
 */
var $Differ_List;
var SSC11311 = {
    formId: "SSC11311_form",

    /**
     * 生产商入库差异单页面初始化
     */
    init: function() {
        this.initDifferList();
        this.initDatePicker();
        this.clickSearchButton();
    },

    initDatePicker: function() {
        this.closeDatePicker();
        this.bindDatePicker($("input[name*='etd']"));
        this.bindDatePicker($("input[name*='eta']"));
        this.bindDatePicker($("input[name*='deliveryDate']"));
        this.bindDatePicker($("input[name*='arriveDate']"));
    },

    bindDatePicker: function(timeId) {
        $(timeId).datepicker({
            dateFormat: "yy-mm-dd",
            closeText: "清除",
            showButtonPanel: true
        });
        $(timeId).attr("readonly","readonly");
    },

    closeDatePicker: function() {
        $(document).on("click", "button.ui-datepicker-close", function() {
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },

    initDifferList: function() {
        $Differ_List = $("#SSC11311_differ_list").grid({
            actionHandler: SSC11311.actionHandler
        });
    },

    /**
     * 点击行末按钮触发事件
     */
    actionHandler: function(rowdata, coltype, row, col) {
        //跳转到入库差异单详情页面
        if ("detail" == coltype) {
            var deliveryPreIntoCode = rowdata.deliveryPreIntoCode;
            deliveryPreIntoCode = deliveryPreIntoCode.replaceAll("<br />", ",");
            var etd = rowdata.etd;
            etd = etd.replaceAll("<br />", ",");
            var eta = rowdata.eta;
            eta = eta.replaceAll("<br />", ",");
            var deliveryDate = rowdata.deliveryDate;
            deliveryDate = deliveryDate.replaceAll("<br />", ",");
            var arriveDate = rowdata.arriveDate;
            arriveDate = arriveDate.replaceAll("<br />", ",");

            Main.detailWindow(Main.contextPath + "/SSC11312/show",  {
                differId: rowdata.differId,
                differCode:rowdata.differCode,
                contractId: rowdata.contractId,
                contractCode: rowdata.contractCode,
                deliveryId:rowdata.deliveryId,
                deliveryCode: rowdata.deliveryCode,
                deliveryPreIntoId: rowdata.deliveryPreIntoId,
                deliveryPreIntoCode: deliveryPreIntoCode,
                etd: etd,
                eta: eta,
                deliveryDate: deliveryDate,
                arriveDate: arriveDate,
                confirmStatus: rowdata.confirmStatus
            }, "生产商入库差异单详细");
        }
    },

    /**
     * 点击查询按钮，刷新差异单列表
     */
    clickSearchButton: function() {
        $("#SSC11311_SEARCH").click(function() {
            FormUtils.setFormValue(SSC11311.formId, "SSC11311");
            $Differ_List.fnDraw();
        });
    }
};

/**
 * 初始页面
 */
$(document).ready(function() {
    SSC11311.init();
});
