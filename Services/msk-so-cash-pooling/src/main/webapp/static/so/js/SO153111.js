/**
 * Created by wu_honglei .
 */
var $List_Grid;
var SO153111 = {
    formId: "SO153111Form",
    orderTimeStart: "#orderTimeStart",
    orderTimeEnd: "#orderTimeEnd",
    init: function () {
        $List_Grid = $('#SO153111_list_grid').grid({
            actionHandler: SO153111.actionHandler,
            linkHandler: SO153111.linkHandler,
            fnRowCallback: SO153111.rowCallback,
            fnDrawCallback: SO153111.drawCallback
        });
        FormUtils.init(SO153111.formId, "SO153111");
        this.bindDatePicber(SO153111.orderTimeStart);
        this.bindDatePicber(SO153111.orderTimeEnd);
        this.exportSeller();
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
    exportSeller: function () {
        $("#SO153111_EXPORTSELLER").click(function () {
            //$("#SO153111Form").attr("action", Main.contextPath + "/SO153111/export");
            //$("#SO153111Form").submit();
            //$("#SO153111Form").attr("action", Main.contextPath + "/SO153111/search");
            var param = new Object();
            param["timeStart"] = $("#orderTimeStart").val();
            param["timeEnd"] = $("#orderTimeEnd").val();
            param["businessMainCode"] = $("[name=filterMap\\[\\'businessMainCode\\'\\]]").val();
            param["businessMainName"] = $("[name=filterMap\\[\\'businessMainName\\'\\]]").val();
            param["paidType"] = $("[name=filterMap\\[\\'paidType\\'\\]]").val();
            param["billType"] = $("[name=filterMap\\[\\'billType\\'\\]]").val();
            param["settlementStatus"] = $("[name=filterMap\\[\\'settlementStatus\\'\\]]").val();
            param["settlementFlg"] = $("[name=filterMap\\[\\'settlementFlg\\'\\]]").val();
            var now = new Date();
            var year = now.getFullYear();
            var month = now.getMonth() < 9 ? "0" + (now.getMonth() + 1) : (now.getMonth()+1);
            var day = now.getDate();
            var date = year + "-" + month + "-" + day;
            downloadAsync("reportTemp153111", "SO153111Logic", "so-cp", "SellerPool_" + date + ".xlsx", param);
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == 'detail') {
            //详情
            $('#main-content').postUrl(Main.contextPath + "/SO153112/search/", {
                /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 start */
                sellerBillId: rowdata.sellerBillId,
                srcPage: 'SO153111',
                ver: rowdata.ver
                /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 start */
            });
        } else if (coltype == 'edit' || coltype == 'check') {
            changeSettlementFlag(rowdata, coltype);
        } else if (coltype == 'export') {
            //减免金额
            $.pdialog.open("费用调整", Main.contextPath + "/SO153115/init?sellerBillId=" + rowdata.sellerBillId + "&&ver=" + rowdata.ver + "&&srcPage=SO153111", {
                resizable: false,
                width: 350,
                height: 300
            });
        } else if (coltype == 'audit') {
            //支付金额
            $.pdialog.open("支付", Main.contextPath + "/SO153116/init?sellerBillId=" + rowdata.sellerBillId + "&&ver=" + rowdata.ver + "&&srcPage=SO153111", {
                resizable: false,
                width: 400,
                height: 350
            });
        } else {
            //查询
            var startTime = $('#orderTimeStart').val();
            var endTime = $('#orderTimeEnd').val();
            $('#main-content').postUrl(Main.contextPath + "/SO153111/init/", {
                startTime: startTime,
                endTime: endTime
            });
        }

    },
    linkHandler: function (rowdata, coltype, row, col) {
        $('#main-content').postUrl(Main.contextPath + "/SO153113/init/", {
            sellerBillId: rowdata.sellerBillId,
            srcPage: 'SO153111'
        });
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
            $("#currentBillAmount").html("");
            $("#currentRealReceiveable").html("");
            $("#currentRealSettlementAmount").html("");
            $("#currentAjustAmount").html("");
            $("#currentUnSettlementAmount").html("");

            $("#totalBillAmount").html("");
            $("#totalRealReceiveable").html("");
            $("#totalRealSettlementAmount").html("");
            $("#totalAjustAmount").html("");
            $("#totalUnSettlementAmount").html("");
        }
        /**  add for Bug#999 at 2016/09/08 by ren_yi Start */
        // filter中下拉框宽度的设置
        var code = 1;
        $("#SO153111_list_grid").find("thead.filterHeader > tr > td.select > span.ui-selectmenu-button, thead.filterHeader > tr > td.code > span.ui-selectmenu-button").each(function () {
            if(code == 1) {
                $(this).width(90);
                code++;
            }else if(code == 2){
                $(this).width(100);
                code++;
            }else if(code == 3){
                $(this).width(20);
                code++;
            }
            else if(code == 4){
                $(this).width(70);
                return false;
            }
        });
        /**  add for Bug#999 at 2016/09/08 by ren_yi end */
    },
    rowCallback: function (tr, data) {
        if (tr._DT_RowIndex == 0) {
            $("#currentBillAmount").html(SO153111.formatNumber(data.currentBillAmount, 2, 1));
            $("#currentRealReceiveable").html(SO153111.formatNumber(data.currentRealReceiveable, 2, 1));
            $("#currentRealSettlementAmount").html(SO153111.formatNumber(data.currentRealSettlementAmount, 2, 1));
            $("#currentAjustAmount").html(SO153111.formatNumber(data.currentAjustAmount, 2, 1));
            $("#currentUnSettlementAmount").html(SO153111.formatNumber(data.currentUnSettlementAmount, 2, 1));
            $("#totalBillAmount").html(SO153111.formatNumber(data.totalBillAmount, 2, 1));
            $("#totalRealReceiveable").html(SO153111.formatNumber(data.totalRealReceiveable, 2, 1));
            $("#totalRealSettlementAmount").html(SO153111.formatNumber(data.totalRealSettlementAmount, 2, 1));
            $("#totalAjustAmount").html(SO153111.formatNumber(data.totalAjustAmount, 2, 1));
            $("#totalUnSettlementAmount").html(SO153111.formatNumber(data.totalUnSettlementAmount, 2, 1));
        }
    }
}

function changeSettlementFlag(rowdata, coltype) {
    var settlementFlag = '';

    settlementFlag = coltype == 'edit' ? '2' : '3';

    $('#main-content').postUrl(Main.contextPath + "/SO153111/changeSettlementFlag/", {
        sellerBillId: rowdata.sellerBillId,
        settlementFlag: settlementFlag
    });

}
$(document).ready(function () {
    // 初始化调用
    SO153111.init();

    var $grid = $("#SO153111_list_grid tbody");
    $grid.after("<tr role='row' style='height: 26px;'>" +
    "<td style='border-left: 1px solid #b1b1b1;border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td>" +
    "<td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA'>当前页合计:</td>" +
    "<td class='money'  name='currentBillAmount' id='currentBillAmount' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='currentRealReceiveable' id='currentRealReceiveable' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='currentRealSettlementAmount' id='currentRealSettlementAmount' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='currentAjustAmount' id='currentAjustAmount' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='currentUnSettlementAmount' id='currentUnSettlementAmount' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td style='border-top: 1px solid #b1b1b1;'></td	>" +
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
    "<td class='text' name='totalBillAmount' id='totalBillAmount' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalRealReceiveable' id='totalRealReceiveable' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalRealSettlementAmount' id='totalRealSettlementAmount' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalAjustAmount' id='totalAjustAmount' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td class='text' name='totalUnSettlementAmount' id='totalUnSettlementAmount' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
    "<td></td>" +
    "<td></td>" +
    "<td></td>" +
    "</tr>");

});
