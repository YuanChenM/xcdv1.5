/**
 * 买家资金池管理
 */
var SO153101 = {
    SO153101Grid: null,
    formId: "SO153101Form",
    orderTimeStart: "#orderTimeStart",
    orderTimeEnd: "#orderTimeEnd",
    init: function () {
        $List_Grid = $('#SO153101_list_grid').grid({
            actionHandler: SO153101.actionHandler,
            can_abolish: SO153101.canAbolish,
            fnRowCallback: SO153101.rowCallback,
            fnDrawCallback: SO153101.drawCallback
        });
        FormUtils.init(SO153101.formId, "SO153101");
        this.bindDatePicber(SO153101.orderTimeStart);
        this.bindDatePicber(SO153101.orderTimeEnd);
        this.exportOrder();
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
            $("#currentOrder").html("");
            $("#currentActual").html("");
            $("#currentPaid").html("");
            $("#currentRelief").html("");
            $("#currentBalance").html("");
            $("#totalOrder").html("");
            $("#totalActual").html("");
            $("#totalPaid").html("");
            $("#totalRelief").html("");
            $("#totalBalance").html("");
        }
        /**  add for Bug#999 at 2016/09/08 by ren_yi Start */
        // filter中下拉框宽度的设置
        var code = 1;
       $("#SO153101_list_grid").find("thead.filterHeader > tr > td.select > span.ui-selectmenu-button, thead.filterHeader > tr > td.code > span.ui-selectmenu-button").each(function () {
                if(code == 1) {
                    $(this).width(145);
                    code++;
                }else if(code == 2){
                    $(this).width(90);
                    return false;
                }
        });
        /**  add for Bug#999 at 2016/09/08 by ren_yi end */
    },

    rowCallback: function (tr, data) {
        if (tr._DT_RowIndex == 0) {
            $("#currentOrder").html(SO153101.formatNumber(data.currentOrder, 2, 1));
            $("#currentActual").html(SO153101.formatNumber(data.currentActual, 2, 1));
            $("#currentPaid").html(SO153101.formatNumber(data.currentPaid, 2, 1));
            $("#currentRelief").html(SO153101.formatNumber(data.currentRelief, 2, 1));
            $("#currentBalance").html(SO153101.formatNumber(data.currentBalance, 2, 1));
            $("#totalOrder").html(SO153101.formatNumber(data.totalOrder, 2, 1));
            $("#totalActual").html(SO153101.formatNumber(data.totalActual, 2, 1));
            $("#totalPaid").html(SO153101.formatNumber(data.totalPaid, 2, 1));
            $("#totalRelief").html(SO153101.formatNumber(data.totalRelief, 2, 1));
            $("#totalBalance").html(SO153101.formatNumber(data.totalBalance, 2, 1));

        }
    },

    exportOrder: function () {
        $("#SO153101_EXPORTORDER").click(function () {
            var param = new Object();
            param["timeStart"] = $("#orderTimeStart").val();
            param["timeEnd"] = $("#orderTimeEnd").val();
            param["businessAssistantCode"] = $("[name=filterMap\\[\\'businessAssistantCode\\'\\]]").val();
            param["businessAssistantName"] = $("[name=filterMap\\[\\'businessAssistantName\\'\\]]").val();
            param["bsName"] = $("[name=filterMap\\[\\'bsName\\'\\]]").val();
            param["transCode"] = $("[name=filterMap\\[\\'transCode\\'\\]]").val();
            param["orderIdStr"] = $("[name=filterMap\\[\\'orderIdStr\\'\\]]").val();
            param["paymentType"] = $("[name=filterMap\\[\\'paymentType\\'\\]]").val();
            param["paidType"] = $("[name=filterMap\\[\\'paidType\\'\\]]").val();
            param["settlementStatus"] = $("[name=filterMap\\[\\'settlementStatus\\'\\]]").val();
            var now = new Date();
            var year = now.getFullYear();
            var month = now.getMonth() < 9 ? "0" + (now.getMonth() + 1) : (now.getMonth()+1);
            var day = now.getDate();
            var date = year + "-" + month + "-" + day;
            downloadAsync("reportTemp153101", "SO153101Logic", "so-cp", "BuyerCashPool_" + date + ".xlsx", param);
        });
    },

    bindDatePicber: function (timeId) {
        $(timeId).datepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            changeMonth: true,
            changeYear: true
        });
        $(timeId).change(function () {
            var str = $(timeId).val();
            if (str.length >= 8) {
                // 判断年月日的格式2010-01-01
                var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
                if (reg.test(str)) {
                    if (timeId.endsWith("End")) {
                        var startTime = $(timeId.substring(0, timeId.length - 3) + "Start").val();
                        var endTime = $(timeId).val();
                        if (startTime != null && startTime != 'underfined' && startTime != '' && startTime > endTime) {
                            $(timeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                    if (timeId.endsWith("Start")) {
                        var startTime = $(timeId).val();
                        var endTime = $(timeId.substring(0, timeId.length - 5) + "End").val();
                        if (endTime != null && endTime != 'underfined' && endTime != '' && startTime > endTime) {
                            $(timeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                } else {
                    $(timeId).val("");
                }
            } else {
                $(timeId).val("");
            }
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == 'detail') {
            //详情
            $('#main-content').postUrl(Main.contextPath + "/SO153102/init/", {
                //alert(rowdata.buyerBillId);
                buyerBillId: rowdata.buyerBillId,
                srcPage: 'SO153101',
                /** 添加买家资金池详细列表更新功能 modify by lihuiqian on 2016/8/31 start */
                ver: rowdata.ver
                /** 添加买家资金池详细列表更新功能 modify by lihuiqian on 2016/8/31 end */
            });
        } else if (coltype == 'edit') {
            //减免金额
            $.pdialog.open("费用调整", Main.contextPath + "/SO153103/init?buyerBillId=" + rowdata.buyerBillId + "&&ver=" + rowdata.ver + "&&transCode=" + rowdata.transCode + "&&srcPage=SO153101", {
                resizable: false,
                width: 350,
                height: 300
            });
        } else if (coltype == 'check') {
            //支付金额
            $.pdialog.open("支付", Main.contextPath + "/SO153104/init?buyerBillId=" + rowdata.buyerBillId + "&&ver=" + rowdata.ver + "&&srcPage=SO153101", {
                resizable: false,
                width: 400,
                height: 350
            });
        }
    },
    canAbolish: function (rowdata) {
        var transFlg = rowdata.transFlg;
        if (transFlg == "0") {
            return true;
        }
        return false;
    }
}
$(document).ready(function () {
    // 初始化调用
    SO153101.init();

    //$.core.sleep(3000);

    var $grid = $("#SO153101_list_grid tbody");
    $grid.after("<tr role='row' style='height: 26px;'>" +
    "<td style='border-left: 1px solid #b1b1b1;border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA'>当前页合计:</td>" +
    "<td class='text'  name='currentOrder' id='currentOrder' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='currentActual' id='currentActual' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='currentPaid' id='currentPaid' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='currentRelief' id='currentRelief' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
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
    "<td></td>" +
    "<td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA'>总合计:</td>" +
    "<td class='text' name='totalOrder' id='totalOrder' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalActual' id='totalActual' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalPaid' id='totalPaid' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalRelief' id='totalRelief' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalBalance' id='totalBalance' style='border: 1px solid #b1b1b1;text-align: right'></td>" +

    "<td></td>" +
    "<td></td>" +
    "</tr>");

});
