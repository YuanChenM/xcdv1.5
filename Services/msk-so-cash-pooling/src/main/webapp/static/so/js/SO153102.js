/**
 * 买家订单详情展示
 */
var SO153102 = {
    SO153102Grid: null,
    formId: "SO153102Form",
    init: function () {
        $List_Grid = $('#SO153102_list_grid').grid({
            /*添加买家资金池详细页面合计 modify by renyi on 2016/8/12 start*/
            formatNumber: SO153102.formatNumber()
            /*添加买家资金池详细页面合计 modify by renyi on 2016/8/12 end*/
            /*            fnRowCallback: SO153102.rowCallback,
             fnDrawCallback :SO153102.drawCallback*/
        });
        this.bindComplete();
    },
    /*添加买家资金池详细页面合计 modify by renyi on 2016/8/12 start*/
	/** Bug #3252 Modify by li_huiqian on 2016/10/12 start */
    formatNumber: function () {//千分位格式化
        $("#totalPaidAmount").text(SO153102.formatRMB($("#totalPaidAmount").text()));
        $("#totalRefundAmount").text(SO153102.formatRMB($("#totalRefundAmount").text()));
    }, 
    /** 人民币金额格式化 数字->人民币 */
    formatRMB: function (value) {
        // 去除金额中的 , ¥ ￥
        value = value.toString().replace(/\,|¥|￥/g, '');
        // 判断是否为合法的数字
        value = isNaN(value) || value === "" ? "0" : value;
        // 四舍五入，保留两位小数
        value = parseFloat(value).toFixed(2);
        // 千分位切分
        value = value.toString().replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,');
        return value;
    },
	/** Bug #3252 Modify by li_huiqian on 2016/10/12 end */
    /*添加买家资金池详细页面合计 modify by renyi on 2016/8/12 end*/
    bindComplete: function () {
        $("#SO153102_PRINT").click(function () {
            var dform = $("<form>");   //定义一个form表单
            var buyerBillId = $('#buyerBillId').val()
            var srcPage = $('#srcPage').val();
            var callbackParamJson = "{\"buyerBillId\":\"" + buyerBillId + "\"," + "\"srcPage\":\"" + srcPage + "\"}";
            var callBackParamName = "callBackParam";
            dform.append('<input type="text" name="' + callBackParamName + '" value=' + callbackParamJson + '>');
            dform.attr('style', 'display:none');   //在form表单中添加查询参数
            dform.attr('target', 'newWin');
            dform.attr('method', 'post');
            dform.attr('action', Main.contextPath + "/SOPdfPrint/pdfPrint/");
            $('body').append(dform);  //将表单放置在web中
            window.open("about:blank", "newWin", "");//newWin 是上面form的target
            dform.submit();
        });
    },
    /* 添加买家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 start */
    editRunning: function (index) {
        var tr = $("[id='\#SO153102_list_grid']>tbody>tr:eq(" + index + ")");
        var runningId = tr.data("runningid");
        var buyerBillId = $('#buyerBillId').val();
        var ver = $('#ver').val();
        $.pdialog.open("更新支付明细", Main.contextPath + "/SO153106/init?runningId=" + runningId + "&&buyerBillId=" + buyerBillId + "&&ver=" + ver + "&&srcPage=" + $("#srcPage").val(), {
            resizable: false,
            width: 350,
            height: 300
        });
    },
    deleteRunning: function (index) {
        var tr = $("[id='\#SO153102_list_grid']>tbody>tr:eq(" + index + ")");
        $("#runningId").val(tr.data("runningid"));
        var srcPage = $("#srcPage").val();
        var buyerBillId = $("#buyerBillId").val();
        var ver = parseInt($("#ver").val());
        $.alertMessage.confirm("你确定要删除当前数据吗？", function () {
            $.alertMessage.close();
            var formUrl = $("#" + SO153102.formId).attr("action") + "/deleteRunning";
            var formData = getFormData($("#" + SO153102.formId));
/** 改善 #3254 Modified by li_huiqian on 2016/10/19 start */
            $("").postUrl(formUrl, formData, function () {
/** 改善 #3254 Modified by li_huiqian on 2016/10/19 end */
                $.alertMessage.info("删除成功", function () {
                    $.alertMessage.close();
                    $('#main-content').postUrl(Main.contextPath + "/SO153102/init/", {
                        buyerBillId: buyerBillId,
                        srcPage: srcPage,
                        ver: ver + 1
                    });
                });
            });
        });
    },
    editRefund: function (index) {
        var tr = $("[id='\#SO153102_grid']>tbody>tr:eq(" + index + ")");
        var refundId = tr.data("refundid");
        var buyerBillId = $('#buyerBillId').val();
        var ver = $('#ver').val();
        var transCode = $("#transCode").val();
        $.pdialog.open("更新应退款明细", Main.contextPath + "/SO153105/init?refundId=" + refundId + "&&buyerBillId=" + buyerBillId + "&&ver=" + ver + "&&transCode=" + transCode + "&&srcPage=SO153102", {
            resizable: false,
            width: 350,
            height: 300
        });
    },
    deleteRefund: function (index) {
        var tr = $("[id='\#SO153102_grid']>tbody>tr:eq(" + index + ")");
        $("#refundId").val(tr.data("refundid"));
        var srcPage = $("#srcPage").val();
        var buyerBillId = $("#buyerBillId").val();
        var ver = parseInt($("#ver").val());
        $.alertMessage.confirm("你确定要删除当前数据吗？", function () {
            $.alertMessage.close();
            var formUrl = $("#" + SO153102.formId).attr("action") + "/deleteRefund";
            var formData = getFormData($("#" + SO153102.formId));
/** 改善 #3254 Modified by li_huiqian on 2016/10/19 start */
            $("").postUrl(formUrl, formData, function () {
/** 改善 #3254 Modified by li_huiqian on 2016/10/19 end */
                $.alertMessage.info("删除成功", function () {
                    $.alertMessage.close();
                    $('#main-content').postUrl(Main.contextPath + "/SO153102/init/", {
                        buyerBillId: buyerBillId,
                        srcPage: srcPage,
                        ver: ver + 1
                    });
                });
            });
        });
    }
    /* 添加买家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 end */
}
$(document).ready(function () {
    // 初始化调用
    SO153102.init();
});
