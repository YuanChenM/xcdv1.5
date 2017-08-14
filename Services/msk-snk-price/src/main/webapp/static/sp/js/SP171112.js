/**
 * 价盘查询JS
 * Created by zhouling on 16/05/04.
 */
var $List_Grid;
var SP171112 = {
    SP171112Grid: null,
    initDataGrid: function () {
        $List_Grid = $('#SP171112_Grid').grid({
            actionHandler: SP171112.actionHandler

        });
        this.bindDatePicber();
        this.changeSelect();
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
        this.closeDate();
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "detail") {
            // 跳转到价盘详情
            var priceDate = $("#priceDate").val();
            var pricecycle = $("#pricecycle").val();
            var pricePeriod = priceDate.substr(0,2) + priceDate.substr(3,4) + pricecycle;

            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  Start */
                var data = new Object();
                data['lgcdCode'] = rowdata.lgcdCode;
                data['pdCode'] = rowdata.pdCode;
                data['pricePeriod'] = rowdata.pricePeriod;
                data['lgcsName'] = rowdata.lgcsName;
                data['pdName'] = rowdata.pdName;
                data['wayCode'] = rowdata.wayCode;
                Main.detailWindow(Main.contextPath + "/SP171113/init/", data, "价盘详情");
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  end */
        }
        if (coltype == "search") {
            var lastPricePeriodTime = $("#lastPricePeriodTime").text();
            var pricePeriod =  $("#pricePeriod").val();
            var lastPricePeriod =  $("#lastPricePeriod").val();
            var pricePeriodStart =  $("#pricePeriodStart").val();
            var pricePeriodEnd =  $("#pricePeriodEnd").val();

            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  Start */
            var data = new Object();
            data['lgcdCode'] = rowdata.lgcdCode;
            data['pricePeriod'] =pricePeriod;
            data['pdCode'] = rowdata.pdCode;
            data['pageType'] = "1";
            Main.detailWindow(Main.contextPath + "/SP171111/init", data, "其他供应商申报价格一览");
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  end */
        }
        /** ADD:  3363 价盘一览中添加删除功能   2016/10/18   BY  zhukai1  Start */
        if (coltype == "delete") {
            $.alertMessage.confirm("是否删除该价盘数据？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SP171112/deletePricePeriod", rowdata, function(){
                    FormUtils.setFormValue(SP171112.formId, "SP171112");
                    $List_Grid.fnDraw();
                },{refreshHtml:false});
            });
        }
        /** ADD:  3363 价盘一览中添加删除功能   2016/10/18   BY  zhukai1  end */
    },
    bindDatePicber : function(){
        $("#priceDate").datepicker({
            showDay:false,
            prevText: '上月',         // 前选按钮提示
            nextText: '下月',         // 后选按钮提示
            changeYear: true,          // 年下拉菜单
            changeMonth: true,             // 月下拉菜单
            showButtonPanel: true,         // 显示按钮面板
            showMonthAfterYear: true,  // 月份显示在年后面
            currentText: "本月",         // 当前日期按钮提示文字
            closeText: 'Clear',           // 关闭按钮提示文字
            dateFormat: "y-mm",       // 日期格式
            //minDate:new Date(),			//最小日期
            onClose: function(dateText, inst) {// 关闭事件
                var month = $("#ui-datepicker-div .ui-datepicker-month").val();
                var year = $("#ui-datepicker-div .ui-datepicker-year").val();
                $(this).datepicker('setDate', new Date(year, month, 1));
                var priceDate = $("#priceDate").val();
                var pricecycle = $("#pricecycle").val();
                var pricePeriod = priceDate.substr(0,2) + priceDate.substr(3,4) + pricecycle;
                $("#pricePeriod").val(pricePeriod);
                SP171112.dateChange(pricePeriod);
                FormUtils.setFormValue(SP171112.formId, "SP171112");
                $List_Grid.fnDraw();
            }
        });

    },
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    changeSelect:function(){
        $("#pricecycle").change(function(){
            var priceDate = $("#priceDate").val();
            var pricecycle = $("#pricecycle").val();
            var pricePeriod = priceDate.substr(0,2) + priceDate.substr(3,4) + pricecycle;
            $("#pricePeriod").val(pricePeriod);
            SP171112.dateChange(pricePeriod);
            FormUtils.setFormValue(SP171112.formId, "SP171112");
            $List_Grid.fnDraw();
        });
    },

    dateChange:function(pricePeriod){
            $("#main-content").postUrl(Main.contextPath + "/SP171112/getLastDate",{"pricePeriod":pricePeriod}, function (data) {
                    $("#lastPricePeriodTime").text(data);
            }, {refreshHtml: false});
    }
}


$(document).ready(function () {
    // 初始化调用
    SP171112.initDataGrid();
});