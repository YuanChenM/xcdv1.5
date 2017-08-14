/**
 * Created by wu_honglei .
 */

var $List_Grid;
var SO153112 = {
    formId: "SO153112Form",
    deleteFormId: "SO153112DeleteForm",
    init: function () {
        $List_Grid = $('#SO153112_list_grid').grid({
            actionHandler: SO153112.actionHandler,
            /*添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 start*/
            formatNumber: SO153112.formatNumber()
            /*添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 end*/
        });
        FormUtils.init(SO153112.formId, "SO153112");
        this.bindComplete();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        //if(coltype=='detail'){
        //    //详情
        //    $('#main-content').postUrl(Main.contextPath + "/SO153112/init/",{
        //        sellerBillId:rowdata.sellerBillId
        //    });
        //}
    },
    /*添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 start*/
	/** Bug #3252 Modify by li_huiqian on 2016/10/12 start */
    formatNumber: function () {//千分位格式化
        $("#totalPaidAmount").text(SO153112.formatRMB($("#totalPaidAmount").text()));
        $("#totalRefundAmount").text(SO153112.formatRMB($("#totalRefundAmount").text()));
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
    /*添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 end*/

    bindComplete: function () {
        $("#SO153112_PRINT").click(function () {

            var dform = $("<form>");   //定义一个form表单
            var sellerBillId = $('#sellerBillId1').val();
            var srcPage = $('#srcPage').val();
            var callbackParamJson = "{\"sellerBillId\":\"" + sellerBillId + "\"," + "\"srcPage\":\"" + srcPage + "\"}";
            var callBackParamName = "callBackParam";
            dform.append('<input type="text" name="' + callBackParamName + '" value=' + callbackParamJson + '>');
            dform.attr('style', 'display:none');   //在form表单中添加查询参数
            dform.attr('target', 'newWin');
            dform.attr('method', 'post');
            dform.attr('action', Main.contextPath + "/SOPdfPrint/sellerPdfPrint/");
            $('body').append(dform);  //将表单放置在web中
            window.open("about:blank", "newWin", "");//newWin 是上面form的target
            dform.submit();
        });
    },
    /* 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 start */
    editRunning: function (index) {
        var tr = $("#SO153112_running>tbody>tr:eq(" + index + ")");
        var runningId = tr.data("runningid");
        var sellerBillId = $('#sellerBillId').val();
        var ver = $('#ver').val();
        $.pdialog.open("更新支付明细", Main.contextPath + "/SO153118/init?runningId=" + runningId + "&&sellerBillId=" + sellerBillId + "&&ver=" + ver + "&&srcPage=" + $("#srcPage").val(), {
            resizable: false,
            width: 350,
            height: 300
        });
    },
    deleteRunning: function (index) {
        var tr = $("#SO153112_running>tbody>tr:eq(" + index + ")");
        $("#runningId").val(tr.data("runningid"));
        var srcPage = $("#srcPage").val();
        var sellerBillId = $("#sellerBillId").val();
        var ver = parseInt($("#ver").val());
        $.alertMessage.confirm("你确定要删除当前数据吗？", function () {
            $.alertMessage.close();
            var formUrl = $("#" + SO153112.deleteFormId).attr("action") + "/deleteRunning";
            var formData = getFormData($("#" + SO153112.deleteFormId));
/** 改善 #3254 Modified by li_huiqian on 2016/10/19 start */
            $("").postUrl(formUrl, formData, function () {
/** 改善 #3254 Modified by li_huiqian on 2016/10/19 end */
                $.alertMessage.info("删除成功", function () {
                    $.alertMessage.close();
                    $('#main-content').postUrl(Main.contextPath + "/SO153112/search/", {
                        sellerBillId: sellerBillId,
                        srcPage: srcPage,
                        ver: ver + 1
                    });
                });
            });
        });
    },
    editRefund: function (index) {
        var tr = $("#SO153112_refund>tbody>tr:eq(" + index + ")");
        var id = tr.data("id");
        var tb = tr.data("tb");
        var sellerBillId = $('#sellerBillId').val();
        var ver = $('#ver').val();
        var transCode = tr.data("transcode");
        $.pdialog.open("更新应付款明细", Main.contextPath + "/SO153117/init?id=" + id + "&&tb=" + tb + "&&sellerBillId=" + sellerBillId + "&&transCode=" + transCode + "&&ver=" + ver + "&&srcPage=" + $("#srcPage").val(), {
            resizable: false,
            width: 350,
            height: 300
        });
    },
    deleteRefund: function (index) {
        var tr = $("#SO153112_refund>tbody>tr:eq(" + index + ")");
        $("#id").val(tr.data("id"));
        $("#tb").val(tr.data("tb"));
        var srcPage = $("#srcPage").val();
        var sellerBillId = $("#sellerBillId").val();
        var ver = parseInt($("#ver").val());
        $.alertMessage.confirm("你确定要删除当前数据吗？", function () {
            $.alertMessage.close();
            var formUrl = $("#" + SO153112.deleteFormId).attr("action") + "/deleteRefund";
            var formData = getFormData($("#" + SO153112.deleteFormId));
/** 改善 #3254 Modified by li_huiqian on 2016/10/19 start */
            $("").postUrl(formUrl, formData, function () {
/** 改善 #3254 Modified by li_huiqian on 2016/10/19 end */
                $.alertMessage.info("删除成功", function () {
                    $.alertMessage.close();
                    $('#main-content').postUrl(Main.contextPath + "/SO153112/search/", {
                        sellerBillId: sellerBillId,
                        srcPage: srcPage,
                        ver: ver + 1
                    });
                });
            });
        });
    }
    /* 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 end */
}
$(document).ready(function () {
    // 初始化调用
    SO153112.init();
});
