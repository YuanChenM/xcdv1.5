var SSC11312 = {
    init: function() {
        this.splitIntoStoreCodes();
        this.confirm();
        this.exportToExcel();
    },

    /**
     * 拆分多个入库单号，给每个单号加上超链接
     */
    splitIntoStoreCodes: function() {
        var deliveryPreIntoId = $("#deliveryPreIntoId").val();
        var deliveryPreIntoCode = $("#deliveryPreIntoCode").val();

        if (deliveryPreIntoCode.indexOf(",") != -1) {
            var intoStoreIds = deliveryPreIntoId.split(",");
            var intoStoreCodes = deliveryPreIntoCode.split(",");

            var html = "";
            for (var i in intoStoreCodes) {
                html += "<a href='javascript:SSC11312.clickIntoStoreCodeLink(" + intoStoreIds[i] + ");'>" + intoStoreCodes[i] + "</a>,&nbsp;";
            }
            $("#into_code_td").html(html.substring(0, html.lastIndexOf(",")));
        }
        else {
            $("#into_code_td").html("<a href='javascript:SSC11312.clickIntoStoreCodeLink(" + deliveryPreIntoId + ");'>" + deliveryPreIntoCode + "</a>");
        }
    },

    /**
     * 点击合同编号超链接
     */
    clickContractCodeLink: function() {
        Main.detailWindow(Main.contextPath + "/SSC11304/show",  {
            contractId: $("#contractId").val(),
            differId: $("#differId").val()
        }, "合同详细");
    },

    /**
     * 点击发货订号超链接
     */
    clickDeliveryCodeLink: function() {
        Main.detailWindow(Main.contextPath + "/SSC11306/show",  {
            deliveryId: $("#deliveryId").val(),
            differId: $("#differId").val()
        }, "发货订单详细");
    },

    /**
     * 点击入库单号超链接
     */
    clickIntoStoreCodeLink: function(deliveryPreIntoId) {
        Main.detailWindow(Main.contextPath + "/SSC11317/init/2",  {
            deliveryPreIntoId: deliveryPreIntoId,
            differId: $("#differId").val()
        }, "预入库通知单详细");
    },

    exportToExcel: function() {
        $("#SSC11312_EXPORT").click(function () {
            var status = $("#confirmStatus").val();
            if (1 != status) {
                $.alertMessage.info("请先确认差异单，再导出数据！");
                return;
            }

            var differCode = $("#differCode").val();
            var contractCode = $("#contractCode").val();
            var deliveryCode = $("#deliveryCode").val();
            var differId = $("#differId").val();

            var dform = $("<form>");   //定义一个form表单
            dform.append('<input type="text" name="differCode" value="' + differCode + '">')
            dform.append('<input type="text" name="contractCode" value="' + contractCode + '">')
            dform.append('<input type="text" name="deliveryCode" value="' + deliveryCode + '">')
            dform.append('<input type="text" name="differId" value="' + differId + '">')
            dform.attr('style', 'display:none');   //在form表单中添加查询参数
            dform.attr('target', '');
            dform.attr('method', 'post');
            dform.attr('action', Main.contextPath + "/SSC11318/export");
            $('body').append(dform);  //将表单放置在web中
            dform.submit();
        });
    },

    /**
     * 将差异单状态更新为已确认
     */
    confirm: function() {
        $("#SSC11312_CONFIRM").click(function() {
            var differId = $("#differId").val();
            $("#main-content").postUrl(Main.contextPath + "/SSC11311/confirm", {differId: differId}, function(data) {
                if (data == "S") {
                    $("#main-content").postUrl(Main.contextPath + "/SSC11312/show", {
                        differId: differId
                    }, Main.hiddenHeader);
                    $("#status_td").html("已确认");
                    $.alertMessage.info("确认成功！");
                }
                else {
                    $.alertMessage.info("确认失败！");
                }
            }, {refreshHtml: false});
        });
    }
};

$(document).ready(function () {
    $.when(SSC11312.init()).done(function(){
        //Main.hlLeftMainMenu("SSC11311")
    });
});
