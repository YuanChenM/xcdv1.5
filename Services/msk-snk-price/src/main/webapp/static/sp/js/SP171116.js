/**
 * 供应商待报价产品一览
 */
var $List_Grid;
var SP171116 = {
    searchButtonId: "SP171116_SEARCH",
    formId: "SP171116Form",
    init: function () {
        $List_Grid = $('#SP171116_grid').grid({
            actionHandler: SP171116.actionHandler
        });
        this.bindDatePicber('#priceDate');
        this.closeDate();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "edit") {
            var priceDate = $("#priceDate").val();
            /** Modfiy:  Bug#2560 : 真实生产过程中会经常新增加产品，但是目前不支持对新产品进行当期报价   2016/9/14   BY  zhukai1  Start */
            var dateArr = priceDate.split("-");
            rowdata.pricePeriod=dateArr[0]+dateArr[1]+$("#pricecycle").val();
            /** Modfiy:  Bug#2560 : 真实生产过程中会经常新增加产品，但是目前不支持对新产品进行当期报价   2016/9/14   BY  zhukai1  end */
            rowdata.pricePeriodStart = $("#pricePeriodStart").val();
            rowdata.pricePeriodEnd = $("#pricePeriodEnd").val();
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  Start */
            Main.detailWindow(Main.contextPath + "/SP171117/init", rowdata, "价格申报详细");
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  end */
        }
    },
    bindDatePicber: function (priceDate) {
        $(priceDate).datepicker({
            prevText: '上月',         // 前选按钮提示
            nextText: '下月',         // 后选按钮提示
            changeYear: true,          // 年下拉菜单
            changeMonth: true,             // 月下拉菜单
            showButtonPanel: true,         // 显示按钮面板
            showMonthAfterYear: true,  // 月份显示在年后面
            currentText: "本月",         // 当前日期按钮提示文字
            closeText: 'Clear',           // 关闭按钮提示文字
            dateFormat: "y-mm",       // 日期格式
            //minDate: new Date(),			//最小日期
            onClose: function (dateText, inst) {// 关闭事件
                var month = $("#ui-datepicker-div .ui-datepicker-month").val();
                var year = $("#ui-datepicker-div .ui-datepicker-year").val();
                $(this).datepicker('setDate', new Date(year, month, 1));
            }
        });
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    }
}

$(document).ready(function () {
    // 初始化调用
    SP171116.init();
});
