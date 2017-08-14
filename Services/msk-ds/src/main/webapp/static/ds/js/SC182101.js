/**
 * 实际入力信息JS
 * Created by yuan_chen on 16/1/13.
 */
var SC182101 = {
    SC182101Grid: null,
    startTime:"[name=filterMap\\[\\'deliveryReceiveStockTimeStart\\'\\]Start]",
    endTime:"[name=filterMap\\[\\'deliveryReceiveStockTimeEnd\\'\\]]",
    initDataGrid: function () {
        SC182101.SC182101Grid = $('#SC182101_Grid').grid({
            actionHandler:SC182101.actionHandler,
            fnRowCallback: SC182101.rowCallback,
            fnDrawCallback :SC182101.drawCallback


        });
        this.exportOrder();
        this.bindDatePicber(SC182101.startTime);
        this.bindDatePicber(SC182101.endTime);
        this.closeDate();
    },
    actionHandler:function(rowdata,coltype,row,col){
        if(coltype=="edit"){
            $('#main-content').postUrl(Main.contextPath + "/SC182102/init/true", rowdata)
            // Main.detailWindow(Main.contextPath + "/SC182102/init/true", rowdata, "发货入库单明细")
        };
        if(coltype=="delete"){
            $.alertMessage.confirm("你确定要删除这条数据吗？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SC182101/delete", rowdata);
            });
        }

    },

    bindDatePicber: function (orderTimeId) {

        $(orderTimeId).change(function () {
            var str = $(orderTimeId).val();
            if (str.length >= 8) {
                // 判断年月日的格式2010-01-01
                var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
                if (reg.test(str)) {
                    if (orderTimeId.endsWith("End\\'\\]]")) {
                        var startTime = $(SC182101.startTime).val();
                        var endTime = $(orderTimeId).val();
                        if (startTime != null && startTime != 'underfined' && startTime != '' && startTime > endTime) {
                            $(orderTimeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                    if (orderTimeId.endsWith("Start]")) {
                        var startTime = $(orderTimeId).val();
                        var endTime = $(SC182101.endTime).val();
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
    /**  #1765添加千分位 modify by renyi on 2016/8/30 start */
    formatNumber:function(num,cent,isThousand){
        num = num.toString().replace(/\$|\,/g,'');
        if(isNaN(num))num = "0";
        // 获取符号(正/负数)
        sign = (num == (num = Math.abs(num)));

        num = Math.floor(num*Math.pow(10,cent)+0.50000000001);  // 把指定的小数位先转换成整数.多余的小数位四舍五入
        cents = num%Math.pow(10,cent);              // 求出小数位数值
        num = Math.floor(num/Math.pow(10,cent)).toString();   // 求出整数位数值
        cents = cents.toString();               // 把小数位转换成字符串,以便求小数位长度

        // 补足小数位到指定的位数
        while(cents.length<cent)
            cents = "0" + cents;

        if(isThousand) {
            // 对整数部分进行千分位格式化.
            for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
                num = num.substring(0,num.length-(4*i+3))+','+ num.substring(num.length-(4*i+3));
        }

        if (cent > 0)return (((sign)?'':'-') + num + '.' + cents);
        else
            return (((sign)?'':'-') + num);


    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    drawCallback : function(){
        var dataList = SC182101.SC182101Grid.fnGetData();
        $('td.datetime').each(function(e){
           if ($(this).text() === 'NaN-aN-aN aN:aN:aN') {
               $(this).text('');
           }
        });
        if(dataList.length == 0){
            $("#currentsendPlanNum").html("");
            $("#currentsendActualNum").html("");
            $("#currentreceiveNum").html("");
            $("#currentsendPlanQty").html("");
            $("#currentsendActualQty").html("");
            $("#currentrecriveQty").html("");
            $("#currentdifferNum").html("");
            $("#currentdifferQty").html("");
            $("#totalsendPlanNum").html("");
            $("#totalsendActualNum").html("");
            $("#totalreceiveNum").html("");
            $("#totalsendPlanQty").html("");
            $("#totalsendActualQty").html("");
            $("#totalrecriveQty").html("");
            $("#totaldifferNum").html("");
            $("#totaldifferQty").html("");
        }

    },

    rowCallback: function(tr, data) {
        if(tr._DT_RowIndex == 0){

            $("#currentsendPlanNum").html(SC182101.formatNumber(data.currentSendPlanNum,0,1));
            $("#currentsendActualNum").html(SC182101.formatNumber(data.currentSendActualNum,0,1));
            $("#currentreceiveNum").html(SC182101.formatNumber(data.currentReceiveNum,0,1));
            $("#currentsendPlanQty").html(SC182101.formatNumber(data.currentSendPlanQty,2,1));
            $("#currentsendActualQty").html(SC182101.formatNumber(data.currentSendActualQty,2,1));
            $("#currentrecriveQty").html(SC182101.formatNumber(data.currentRecriveQty,2,1));
            $("#currentdifferNum").html(SC182101.formatNumber(data.currentDifferNum,0,1));
            $("#currentdifferQty").html(SC182101.formatNumber(data.currentDifferQty,2,1));
            $("#totalsendPlanNum").html(SC182101.formatNumber(data.totalSendPlanNum,0,1));
            $("#totalsendActualNum").html(SC182101.formatNumber(data.totalSendActualNum,0,1));
            $("#totalreceiveNum").html(SC182101.formatNumber(data.totalReceiveNum,0,1));
            $("#totalsendPlanQty").html(SC182101.formatNumber(data.totalSendPlanQty,2,1));
            $("#totalsendActualQty").html(SC182101.formatNumber(data.totalSendActualQty,2,1));
            $("#totalrecriveQty").html(SC182101.formatNumber(data.totalRecriveQty,2,1));
            $("#totaldifferNum").html(SC182101.formatNumber(data.totalDifferNum,0,1));
            $("#totaldifferQty").html(SC182101.formatNumber(data.totalDifferQty,2,1));

        }
    },
    /**  #1765添加千分位 modify by renyi on 2016/8/30 end */

    exportOrder: function() {
        $("#SC182101_EXPORTORDER").click(function() {
            var param = new Object();
            param["userType"] = $("#userType").text();
            param["crtId"] = $("#crtId").text();
            param["deliveryStockId"] = $("[name=filterMap\\[\\'deliveryStockId\\'\\]]").val();
            param["distMonth"] = $("[name=filterMap\\[\\'distMonth\\'\\]]").val();
            param["lgcsName"] = $("[name=filterMap\\[\\'lgcsName\\'\\]]").val();
            param["suppName"] = $("[name=filterMap\\[\\'suppName\\'\\]]").val();
            param["deliveryStockStatus"] = $("[name=filterMap\\[\\'deliveryStockStatus\\'\\]]").val();
            param["deliveryReceiveStockTimeStart"] = $("[name=filterMap\\[\\'deliveryReceiveStockTimeStart\\'\\]Start]").val();
            param["deliveryReceiveStockTimeEnd"] = $("[name=filterMap\\[\\'deliveryReceiveStockTimeEnd\\'\\]]").val();
            param["sourceFlg"] = $("[name=filterMap\\[\\'sourceFlg\\'\\]]").val();
            var now = new Date();
            var year = now.getFullYear();
            var month = now.getMonth() < 9 ? "0" + (now.getMonth() + 1) : (now.getMonth()+1);
            var day = now.getDate();
            var date = year + "-" + month + "-" + day;
            downloadAsync("reportTemp182101", "SC182101Logic", "ds", "DeliveryStock_" + date + ".xlsx", param);
       })
    }

}
$(document).ready(function () {
    // 初始化调用
    SC182101.initDataGrid();

    var $grid = $("#SC182101_Grid tbody");
    $grid.after("<tr role='row' style='height: 26px;'>" +
        "<td style='border-left: 1px solid #b1b1b1;border-top: 1px solid #b1b1b1;'></td>" +
        "<td style='border-top: 1px solid #b1b1b1;'></td>" +
        "<td style='border-top: 1px solid #b1b1b1;'></td>" +
        "<td style='border-top: 1px solid #b1b1b1;'></td>" +
        "<td style='border-top: 1px solid #b1b1b1;'></td>" +
        "<td style='border-top: 1px solid #b1b1b1;'></td>" +
        "<td style='border-top: 1px solid #b1b1b1;'></td>" +
        "<td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA'>当前页合计:</td>" +
        "<td class='text'  name='currentsendPlanNum' id='currentsendPlanNum' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='currentsendActualNum' id='currentsendActualNum' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='currentreceiveNum' id='currentreceiveNum' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='currentsendPlanQty' id='currentsendPlanQty' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='currentsendActualQty' id='currentsendActualQty' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='currentrecriveQty' id='currentrecriveQty' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='currentdifferNum' id='currentdifferNum' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='currentdifferQty' id='currentdifferQty' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td style='border-top: 1px solid #b1b1b1;'></td	>" +
        "</tr>"+
        "<tr role='row' style='height: 26px'>" +
        "<td style='border-left: 1px solid #b1b1b1;'></td>" +
        "<td></td>" +
        "<td></td>" +
        "<td></td>" +
        "<td></td>" +
        "<td></td>" +
        "<td></td>" +
        "<td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA'>总合计:</td>" +
        "<td class='text'  name='totalsendPlanNum' id='totalsendPlanNum' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='totalsendActualNum' id='totalsendActualNum' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='totalreceiveNum' id='totalreceiveNum' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='totalsendPlanQty' id='totalsendPlanQty' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='totalsendActualQty' id='totalsendActualQty' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='totalrecriveQty' id='totalrecriveQty' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='totaldifferNum' id='totaldifferNum' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td class='text' name='totaldifferQty' id='totaldifferQty' style='border: 1px solid #b1b1b1;text-align: right'></td>" +
        "<td></td>" +
        "</tr>");
        $("[name=deliveryStockStatus]").width(90);
        $("[name=sourceFlg]").width(120);
    $(SC182101.startTime).attr("readonly","true");
    $(SC182101.endTime).attr("readonly","true");
});
