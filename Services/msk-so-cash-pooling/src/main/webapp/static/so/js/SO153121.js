/**
 * Created by wu_honglei .
 */

var $List_Grid;
var SO153121 = {
    formId: "SO153121Form",
    createTimeStart: "#createTimeStart",
    createTimeEnd: "#createTimeEnd",
    tranTimeStart: "#tranTimeStart",
    tranTimeEnd: "#tranTimeEnd",
    orderAmountStart: "#orderAmountStart",
    orderAmountEnd: "#orderAmountEnd",
    init: function () {
        $List_Grid = $('#SO153121_list_grid').grid({
            actionHandler: SO153121.actionHandler,
            linkHandler: SO153121.linkHandler,
            can_abolish: SO153121.canAbolish,
            fnRowCallback: SO153121.rowCallback,
            fnDrawCallback: SO153121.drawCallback
        });
        FormUtils.init(SO153121.formId, "SO153121");
        this.bindDatePicber(SO153121.createTimeStart);
        this.bindDatePicber(SO153121.createTimeEnd);
        this.bindDatePicber(SO153121.tranTimeStart);
        this.bindDatePicber(SO153121.tranTimeEnd);
        this.bindCheckNums(SO153121.orderAmountStart);
        this.bindCheckNums(SO153121.orderAmountEnd);
        this.exportData();
    },
    bindCheckNums: function (nums) {
        $(nums).change(function () {
            var reg = /^\d+(.\d+)?$/;
            var value = $(nums).val().trim();
            if (value != '' && value != 'underfined') {
                if (reg.test(value)) {
                    $(nums).val(Number(value).toFixed(2));
                } else {
                    $(nums).val("");
                    $.alertMessage.info("请输入正确的数值");
                }
            }
        })
    },

    formatNumber: function (num, cent, isThousand) {
        num = num.toString().replace(/\$|\,/g, '');
        if (isNaN(num))num = "0";
        // 获取符号(正/负数)
        sign = (num == (num = Math.abs(num)));

        num = Math.floor(num * Math.pow(10, cent) + 0.50000000001);  // 把指定的小数位先转换成整数.多余的小数位四舍五入
        cents = num % Math.pow(10, cent);              // 求出小数位数值
        num = Math.floor(num / Math.pow(10, cent)).toString();   // 求出整数位数值
        cents = cents.toString();               // 把小数位转换成字符串,以便求小数位长度

        // 补足小数位到指定的位数
        while (cents.length < cent)
            cents = "0" + cents;

        if (isThousand) {
            // 对整数部分进行千分位格式化.
            for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
                num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
        }

        if (cent > 0)return (((sign) ? '' : '-') + num + '.' + cents);
        else
            return (((sign) ? '' : '-') + num);


    },

    drawCallback: function () {
        var dataList = $List_Grid.fnGetData();
        if (dataList.length == 0) {
            $("#currentOrderAmount").html("");
            $("#currentActualDue").html("");
            $("#currentActualPaid").html("");
            $("#currentActualReceiveable").html("");
            $("#currentBalance").html("");
            $("#currentActualReceived").html("");
            $("#currentReliefAmount").html("");
            $("#totalOrderAmount").html("");
            $("#totalActualDue").html("");
            $("#totalActualPaid").html("");
            $("#totalActualReceiveable").html("");
            $("#totalBalance").html("");
            $("#totalActualReceived").html("");
            $("#totalReliefAmount").html("");
        }
        /**  add for Bug#999 at 2016/09/08 by ren_yi Start */
        // filter中下拉框宽度的设置
        var code = 1;
        $("#SO153121_list_grid").find("thead.filterHeader > tr > td.select > span.ui-selectmenu-button, thead.filterHeader > tr > td.code > span.ui-selectmenu-button").each(function () {
            if(code == 1) {
                $(this).width(90);
                return false;
            }
        });
        /**  add for Bug#999 at 2016/09/08 by ren_yi end */
    },

    rowCallback: function (tr, data) {
        if (tr._DT_RowIndex == 0) {
            $("#currentOrderAmount").html(SO153121.formatNumber(data.currentOrderAmount, 2, 1));
            $("#currentActualDue").html(SO153121.formatNumber(data.currentActualDue, 2, 1));
            $("#currentActualPaid").html(SO153121.formatNumber(data.currentActualPaid, 2, 1));
            $("#currentActualReceiveable").html(SO153121.formatNumber(data.currentActualReceiveable, 2, 1));
            $("#currentBalance").html(SO153121.formatNumber(data.currentBalance, 2, 1));
            $("#currentActualReceived").html(SO153121.formatNumber(data.currentActualReceived, 2, 1));
            $("#currentReliefAmount").html(SO153121.formatNumber(data.currentReliefAmount, 2, 1));
            $("#totalOrderAmount").html(SO153121.formatNumber(data.totalOrderAmount, 2, 1));
            $("#totalActualDue").html(SO153121.formatNumber(data.totalActualDue, 2, 1));
            $("#totalActualPaid").html(SO153121.formatNumber(data.totalActualPaid, 2, 1));
            $("#totalActualReceiveable").html(SO153121.formatNumber(data.totalActualReceiveable, 2, 1));
            $("#totalBalance").html(SO153121.formatNumber(data.totalBalance, 2, 1));
            $("#totalActualReceived").html(SO153121.formatNumber(data.totalActualReceived, 2, 1));
            $("#totalReliefAmount").html(SO153121.formatNumber(data.totalReliefAmount, 2, 1));


        }
    },

    bindDatePicber: function (orderTimeId) {
        $(orderTimeId).datepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            changeMonth: true,
            changeYear: true
        });
        $(orderTimeId).change(function () {
            var str = $(orderTimeId).val();
            if (str.length >= 8) {
                // 判断年月日的格式2010-01-01
                var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
                if (reg.test(str)) {
                    if (orderTimeId.endsWith("End")) {
                        var startTime = $(orderTimeId.substring(0, orderTimeId.length - 3) + "Start").val();
                        var endTime = $(orderTimeId).val();
                        if (startTime != null && startTime != 'underfined' && startTime != '' && startTime > endTime) {
                            $(orderTimeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                    if (orderTimeId.endsWith("Start")) {
                        var startTime = $(orderTimeId).val();
                        var endTime = $(orderTimeId.substring(0, orderTimeId.length - 5) + "End").val();
                        if (endTime != null && endTime != 'underfined' && endTime != '' && startTime > endTime) {
                            $(orderTimeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                } else {
                    $(orderTimeId).val("");
                }
            } else {
                $(orderTimeId).val("");
            }
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        var roleFlag = rowdata.roleFlag;
        if (coltype == 'detail') {
            if (roleFlag == '0') {
                //买家
                $('#main-content').postUrl(Main.contextPath + "/SO153102/init/", {
                    buyerBillId: rowdata.buyerBillId,
                    srcPage: 'SO153121',
                    /** 添加买家资金池详细列表更新功能 modify by lihuiqian on 2016/8/31 start */
                    ver: rowdata.ver
                    /** 添加买家资金池详细列表更新功能 modify by lihuiqian on 2016/8/31 end */
                });
            } else if (roleFlag == '1') {
                //卖家
                $('#main-content').postUrl(Main.contextPath + "/SO153112/search/", {
                    sellerBillId: rowdata.sellerBillId,
                    srcPage: 'SO153121',
                    /** 添加买家资金池详细列表更新功能 modify by lihuiqian on 2016/8/31 start */
                    ver: rowdata.ver
                    /** 添加买家资金池详细列表更新功能 modify by lihuiqian on 2016/8/31 end */
                });
            }
        } else if (coltype == 'edit') {
            if (roleFlag == '0') {
                //卖家减免金额
                $.pdialog.open("费用调整", Main.contextPath + "/SO153103/init?buyerBillId=" + rowdata.buyerBillId + "&&transCode=" + rowdata.transCode + "&&ver=" + rowdata.ver + "&&srcPage=SO153121", {
                    resizable: false,
                    width: 350,
                    height: 300
                });
            } else if (roleFlag == '1') {
                //卖家减免金额
                $.pdialog.open("费用调整", Main.contextPath + "/SO153115/init?sellerBillId=" + rowdata.sellerBillId + "&&ver=" + rowdata.ver + "&&srcPage=SO153121", {
                    resizable: false,
                    width: 350,
                    height: 300
                });
            }
        } else if (coltype == 'check') {
            if (roleFlag == '0') {
                //支付金额
                $.pdialog.open("支付调整", Main.contextPath + "/SO153104/init?buyerBillId=" + rowdata.buyerBillId + "&&ver=" + rowdata.ver + "&&srcPage=SO153121", {
                    resizable: false,
                    width: 400,
                    height: 350
                });
            } else if (roleFlag == '1') {
                //支付金额
                $.pdialog.open("支付", Main.contextPath + "/SO153116/init?sellerBillId=" + rowdata.sellerBillId + "&&ver=" + rowdata.ver + "&&srcPage=SO153121", {
                    resizable: false,
                    width: 400,
                    height: 350
                });
            }
        } else {
            var createTimeStart = $('#createTimeStart').val();
            var createTimeEnd = $('#createTimeEnd').val();
            var tranTimeStart = $('#tranTimeStart').val();
            var tranTimeEnd = $('#tranTimeEnd').val();
            var orderAmountStart = $('#orderAmountStart').val();
            var orderAmountEnd = $('#orderAmountEnd').val();
            //根据条件检索
            $('#main-content').postUrl(Main.contextPath + "/SO153121/search/", {
                createTimeStart: createTimeStart,
                createTimeEnd: createTimeEnd,
                tranTimeStart: tranTimeStart,
                tranTimeEnd: tranTimeEnd,
                orderAmountStart: orderAmountStart,
                orderAmountEnd: orderAmountEnd
            });
        }
    },
    linkHandler: function (rowdata, coltype, row, col) {
        $('#main-content').postUrl(Main.contextPath + "/SO153113/init/", {
            sellerBillId: rowdata.sellerBillId,
            srcPage: 'SO153121'
        });
    },
    canAbolish: function (rowdata) {
        var transFlg = rowdata.transFlg;
        if (transFlg == "1") {
            return false;
        }
        return true;
    },
    exportData: function () {
        $("#SO153121_EXPORT").click(function () {
            //$("#SO153121Form").attr("action", Main.contextPath + "/SO153121/dataExport");
            //$("#SO153121Form").submit();
            //$("#SO153121Form").attr("action", Main.contextPath + "/SO153121/search");
            var param = new Object();
            param["orderAmountStart"] = $("#orderAmountStart").val();
            param["orderAmountEnd"] = $("#orderAmountEnd").val();
            param["createTimeStart"] = $("#createTimeStart").val();
            param["createTimeEnd"] = $("#createTimeEnd").val();
            param["tranTimeStart"] = $("#tranTimeStart").val();
            param["tranTimeEnd"] = $("#tranTimeEnd").val();
            param["businessMainCode"] = $("[name=filterMap\\[\\'businessMainCode\\'\\]]").val();
            param["businessMainName"] = $("[name=filterMap\\[\\'businessMainName\\'\\]]").val();
            param["businessAssistantCode"] = $("[name=filterMap\\[\\'businessAssistantCode\\'\\]]").val();
            param["businessAssistantName"] = $("[name=filterMap\\[\\'businessAssistantName\\'\\]]").val();
            param["transCode"] = $("[name=filterMap\\[\\'transCode\\'\\]]").val();
            param["paidType"] = $("[name=filterMap\\[\\'paidType\\'\\]]").val();
            param["settlementStatus"] = $("[name=filterMap\\[\\'settlementStatus\\'\\]]").val();
            var now = new Date();
            var year = now.getFullYear();
            var month = now.getMonth() < 9 ? "0" + (now.getMonth() + 1) : (now.getMonth()+1);
            var day = now.getDate();
            var date = year + "-" + month + "-" + day;
            downloadAsync("reportTemp153121", "SO153121Logic", "so-cp", "BS_" + date + ".xlsx", param);
        });
    }
};

$(document).ready(function () {
    // 初始化调用
    SO153121.init();


    var $grid = $("#SO153121_list_grid tbody");
    $grid.after("<tr role='row' style='height: 26px;'>" +
    "<td style='border-left: 1px solid #b1b1b1;border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +

    "<td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA'>当前页合计:</td>" +
    "<td class='text'  name='currentOrderAmount' id='currentOrderAmount' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='currentActualDue' id='currentActualDue' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='currentActualPaid' id='currentActualPaid' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='currentActualReceiveable' id='currentActualReceiveable' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='currentActualReceived' id='currentActualReceived' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='currentReliefAmount' id='currentReliefAmount' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='currentBalance' id='currentBalance' style='border: 1px solid #b1b1b1;text-align: right'></td>" +

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
    "<td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA'>总合计:</td>" +
    "<td class='text'  name='totalOrderAmount' id='totalOrderAmount' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalActualDue' id='totalActualDue' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalActualPaid' id='totalActualPaid' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalActualReceiveable' id='totalActualReceiveable' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalActualReceived' id='totalActualReceived' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalReliefAmount' id='totalReliefAmount' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalBalance' id='totalBalance' style='border: 1px solid #b1b1b1;text-align: right'></td>" +

    "<td></td>" +
    "<td></td>" +
    "</tr>");

});
