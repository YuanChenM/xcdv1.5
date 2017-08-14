/**
 * 支付一览
 *
 */
var SO153151 = {
    SO153151Grid: null,
    searchButtonId: "SO153151_SEARCH",
    formId: "SO153151Form",
    init: function () {
        $List_Grid = $('#SO153151_list_grid').grid({
            fnRowCallback: SO153151.rowCallback,
            fnDrawCallback: SO153151.drawCallback
        });
        this.bindDatePicber("#paidTimeStart");
        this.bindDatePicber("#paidTimeEnd");
        /**  页面添加导出按钮 modify by renyi on 2016/8/10 start */
        this.export();
        /**  页面添加导出按钮 modify by renyi on 2016/8/10 end */

    },
    /**  页面添加导出按钮 modify by renyi on 2016/8/10 start */
    export:function(){
        $("#SO153151_EXPORT").click(function () {
            //$("#SO153151Form").attr("action", Main.contextPath + "/SO153151/EXPORT");
            //$("#SO153151Form").submit();
            //$("#SO153151Form").attr("action", Main.contextPath + "/SO153151/search");

            var param = new Object();
            param["paidTimeStart"] = $("#paidTimeStart").val();
            param["paidTimeEnd"] = $("#paidTimeEnd").val();
            param["transCode"] = $("[name=filterMap\\[\\'transCode\\'\\]]").val();
            param["refundCode"] = $("[name=filterMap\\[\\'refundCode\\'\\]]").val();
            param["paidSeq"] = $("[name=filterMap\\[\\'paidSeq\\'\\]]").val();
            param["amountType"] = $("[name=filterMap\\[\\'amountType\\'\\]]").val();
            param["paidType"] = $("[name=filterMap\\[\\'paidType\\'\\]]").val();
            param["remark"] = $("[name=filterMap\\[\\'remark\\'\\]]").val();
            var now = new Date();
            var year = now.getFullYear();
            var month = now.getMonth() < 9 ? "0" + (now.getMonth() + 1) : (now.getMonth()+1);
            var day = now.getDate();
            var date = year + "-" + month + "-" + day;
            downloadAsync("reportTemp153151", "SO153151Logic", "so-cp", "Payment_" + date + ".xlsx", param);
        });
    },
    /**  页面添加导出按钮 modify by renyi on 2016/8/10 end */
    bindDatePicber: function (timeId) {
        $(timeId).datepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            changeMonth: true,
            changeYear: true
        });
        $(timeId).change(function(){
            var str = $(timeId).val();
            if(str.length >= 8){
                // 判断年月日的格式2010-01-01
                var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
                if(reg.test(str)){
                    if(timeId.endsWith("End")){
                        var startTime = $(timeId.substring(0,timeId.length-3) + "Start").val();
                        var endTime = $(timeId).val();
                        if(startTime != null && startTime != 'underfined' && startTime != '' && startTime>endTime){
                            $(timeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                    if(timeId.endsWith("Start")){
                        var startTime = $(timeId).val();
                        var endTime = $(timeId.substring(0,timeId.length-5) + "End").val();
                        if(endTime != null && endTime != 'underfined'&& endTime != '' && startTime>endTime){
                            $(timeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                }else{
                    $(timeId).val("");
                }
            }else{
                $(timeId).val("");
            }
        });
    },
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
    drawCallback : function() {
        var dataList = $List_Grid.fnGetData();
        if(dataList.length == 0) {
            $("#currentPaid").html("");
            $("#totalPaid").html("");
        }
    },
    rowCallback: function(tr, data) {
        if(tr._DT_RowIndex == 0) {
            $("#currentPaid").html(SO153151.formatNumber(data.currentPaid,2,1));
            $("#totalPaid").html(SO153151.formatNumber(data.totalPaid,2,1));
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    SO153151.init();
});
