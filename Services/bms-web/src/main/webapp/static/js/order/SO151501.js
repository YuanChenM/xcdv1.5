/**
 * 订单列表JS
 *
 * @author rwf
 */
var $List_Grid;
var SO151501 = {
    searchButtonId: "SO151501_SEARCH",
    formId: "SO151501Form",
    orderTimeStart: "#orderTimeStart",
    orderTimeEnd: "#orderTimeEnd",
    init: function () {
        $List_Grid = $('#SO251101_list_grid').grid({
            actionHandler: SO151501.actionHandler,
            iDisplayLength: 20,
            fnRowCallback: SO151501.rowCallback,
            fnDrawCallback: SO151501.drawCallback
        });
        $("#districtCode").selectmenu({width: "140px"});
        $("#orderSource").selectmenu({width: "140px"});
        $("#orderType").selectmenu({width: "140px"});
       $("#checkbox-subOrderStatus").attr("style", "width:140px;border-color: #888888;border-style:solid;border-width: 1px;"); //  设置宽度 边框线
        this.bindDatePicber(SO151501.orderTimeStart);
        this.bindDatePicber(SO151501.orderTimeEnd);
        SO151501.closeDate();
      //  FormUtils.init(SO151501.formId, "SO151501");
        this.bindSearchButton();
        this.enterSearchData();
        this.exportData();
        this.exportOrder();
        $("#checkbox-subOrderStatus").checkboxSelect();
    },
    drawCallback: function () {
        var dataList = $List_Grid.fnGetData();
        if (dataList.length == 0) {
            $("#totalQty").html("");
            $("#currentPageQty").html("");
            $("#currentPageAmount").html("");
            $("#totalAmount").html("");
        }
    },

    rowCallback: function (tr, data) {
        if (tr._DT_RowIndex == 0) {
            var totalQtyFmtMoney = data.totalQty + '';
            $("#totalQty").html(totalQtyFmtMoney.replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,'));
            var currentPageQtyFmtMoney = data.currentPageQty + '';
            $("#currentPageQty").html(currentPageQtyFmtMoney.replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,'));
            var currentPageAmountFmtMoney= SO151501.fmtMoney(data.currentPageAmount,2);
            $("#currentPageAmount").html(currentPageAmountFmtMoney);
            var totalAmountFmtMoney= SO151501.fmtMoney(data.totalAmount,2);
            $("#totalAmount").html(totalAmountFmtMoney);
        }
    },
    bindDatePicber: function (orderTimeId) {
        $(orderTimeId).datepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            changeMonth: true,
            changeYear: true,
            closeText: '清空',
            onSelect: function (dateText, inst) {
                if (inst.id == "orderTimeStart" && $(SO151501.orderTimeEnd).val() != "") {
                    if ($(SO151501.orderTimeStart).val() > $(SO151501.orderTimeEnd).val()) {
                        $(SO151501.orderTimeStart).val("");
                        $.alertMessage.info("开始时间不应大于结束时间!");
                    }
                }
                if (inst.id == "orderTimeEnd" && $(SO151501.orderTimeStart).val() != "") {
                    if ($(SO151501.orderTimeStart).val() > $(SO151501.orderTimeEnd).val()) {
                        $(SO151501.orderTimeEnd).val("");
                        $.alertMessage.info("开始时间不应大于结束时间!");
                    }
                }
            },
            onClose: function (dateText, inst) {
                var reg = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
                if (!reg.test(dateText) && dateText != "") {
                    $.alertMessage.info("请按YYYY-MM-DD格式输入时间！");
                    if (inst.id == "orderTimeEnd") {
                        $(SO151501.orderTimeEnd).val("");
                    }
                    if (inst.id == "orderTimeStart") {
                        $(SO151501.orderTimeStart).val("");
                    }
                }
            }
        });
    },
    // 绑定按钮
    bindSearchButton: function () {
        $("#" + SO151501.searchButtonId).click(function () {
            FormUtils.setFormValue(SO151501.formId, "SO151501");
            $List_Grid.fnDraw()
        });
    },
    closeDate: function () {
        $(document).on("click", "button.ui-datepicker-close", function () {
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },

    searchData: function () {
        $("#" + SO151501.searchButtonId).click(function () {
            FormUtils.setFormValue(SO151501.formId, "SO151501");
            $List_Grid.fnDraw()
        });
    },
    exportData: function () {
        $("#SO151501_EXPORT").click(function () {
            var title = "orderAndDetail-" + SO151501.getDate();
            var param = SO151501.getParam();
            downloadAsync("orderDetailTemp", "SO151501ExportOrderAndDetailService", "so-order", title + ".xlsx", param);
        });
    },
    enterSearchData: function () {
        //绑定回车键
        document.onkeydown = function enterDown(e) {
            //兼容火狐,chrome和ie的事件对象
            e = e || event;
            if (e.keyCode == 13) {
                FormUtils.setFormValue(SO151501.formId, "SO151501");
                $List_Grid.fnDraw()
            }
            return;
        }
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "edit") {
            formData = getFormData($("#" + SO151501.formId));
            var data = new Object();
            Main.detailWindow(Main.contextPath + "/SO151502/init/" + rowdata.orderId + "/" + rowdata.subOrderId, data, "订单详细信息");
        }
        // 发货单详细信息页面迁移
        if (coltype == "check") {
            formData = getFormData($("#" + SO151501.formId));
            var data = new Object();
            Main.detailWindow(Main.contextPath + "/SO151510/init/" + rowdata.orderId + "/" + rowdata.subOrderId, data, "发货单详细信息");
          //  $('#main-content').postUrl(Main.contextPath + "/SO151510/init/" + rowdata.orderId);
        }
    },
    getParam: function () {
        var orderId = $("#orderId").val();
        var orderCode = $("#orderCode").val();
        var orderTimeStart = $("#orderTimeStart").val();
        var orderTimeEnd = $("#orderTimeEnd").val();
        var buyerCode = $("#buyerCode").val();
        var buyerName = $("#buyerName").val();
        var returnFlg = $("#returnFlg").val();
        var splitDeliveryFlg = $("#splitDeliveryFlg").val();
        var needInvoice = $("#needInvoice").val();
        var districtName = $("#districtName").val();
        var status = $("#status").val();
        var orderSource = $("#orderSource").val();
        var orderType = $("#orderType").val();
        var orderStatus = $("#orderStatus").val();
        var param = new Object();
        param['orderId'] = orderId;
        param['orderCode'] = orderCode;
        param['orderTimeStart'] = orderTimeStart;
        param['orderTimeEnd'] = orderTimeEnd;
        param['buyerCode'] = buyerCode;
        param['buyerName'] = buyerName;
        param['returnFlg'] = returnFlg;
        param['splitDeliveryFlg'] = splitDeliveryFlg;
        param['needInvoice'] = needInvoice;
        param['districtName'] = districtName;
        param['status'] = status;
        param['orderSource'] = orderSource;
        param['orderType'] = orderType;
        param['orderStatus'] = orderStatus;
        return param;
    },

    getDate: function () {//  获取时间  格式：
        var date = new Date();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + month + strDate + date.getHours() + date.getMinutes() + date.getSeconds();
        return currentdate;
    },
    exportOrder: function () {
        $("#SO151501_EXPORTORDER").click(function () {
            var title = "order-" + SO151501.getDate();
            var param = SO151501.getParam();
            downloadAsync("orderTemp", "SO151501ExportOrderService", "so-order", title + ".xlsx", param);
        });
    },

    fmtMoney: function (s, n) {// 格式化金额，调用：fmtMoney("12345.675910", 3)，返回12,345.676
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    if (isNaN(s) || ((s + "").replace(/\s/g, "")) == "") {
        return "";
    }
    n = n > 0 && n <= 20 ? n : 2;
    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
    t = "";
    for (i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    return t.split("").reverse().join("") + "." + r;
}

}


$(document).ready(function () {
    SO151501.init();
    //console.log(${districtList});
   // $.core.sleep(2000);
    $List_Grid.fnDraw();
    var $grid = $("#SO251101_list_grid tbody");
    $grid.after("<tr role='row' style='height: 26px'>" +
    "<td style='border-left: 1px solid #b1b1b1;border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA'>当前页合计:</td>" +
    "<td class='number' name='currentPageQty' id='currentPageQty' style='border: 1px solid #b1b1b1;padding-right:10px'></td>" +
    "<td class='money' name='currentPageAmount' id='currentPageAmount' style='border: 1px solid #b1b1b1;padding-right:10px'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td	>" +
    "<td style='border-top: 1px solid #b1b1b1;border-right: 1px solid #b1b1b1;'></td>" +
    "</tr>" +
    "<tr role='row' style='height: 26px'>" +
    "<td style='border-left: 1px solid #b1b1b1;'></td>" +
    "<td></td>" +
    "<td></td>" +
    "<td></td>" +
    "<td></td>" +
    "<td></td>" +
    "<td></td>" +
    "<td></td>" +
    "<td></td>" +
    "<td></td>" +
    "<td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA'>总合计:</td>" +
    "<td class='number' name='totalQty' id='totalQty' style='border: 1px solid #b1b1b1;padding-right:10px'></td>" +
    "<td class='money' name='totalAmount' id='totalAmount' style='border: 1px solid #b1b1b1;padding-right:10px'></td>" +
    "<td></td>" +
    "<td></td>" +
    "</tr>");

});
