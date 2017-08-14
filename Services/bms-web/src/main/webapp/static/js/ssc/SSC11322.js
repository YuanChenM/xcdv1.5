/*
 * 核销单详情
 * Created by xia_xiaojie on 2016/8/10.
 */

var SSC11322 = {

    /**
     * 输入实际需支付的运费
     */
    changeFreightDealInput: function() {
        $("input[id^='freightDeal']").change(function() {
            var freightDeal = SSCCommon.clearComma($(this).val());
            if (!SSCCommon.isMoneyGe0(freightDeal)) {
                $.alertMessage.info("实需付款不能为空，且格式为：整数位最多15位，小数位最多2位！");
                return;
            }
            else {
                var inputId = $(this).attr("id");
                var index = inputId.substring(inputId.lastIndexOf("l") + 1);
                var inputName = "verificationDetails[" + index + "].freightActualPaid";

                freightDeal = SSCCommon.roundFixed(freightDeal, 2);
                $(this).val(SSCCommon.formatMoney(freightDeal));
                $("input[name='" + inputName + "']").val(freightDeal);

                //联动修改金额差
                var freightPaid = SSCCommon.clearComma($("#freightPaid" + index).text());
                var freightDiff = SSCCommon.subtract(freightDeal, freightPaid);
                freightDiff = SSCCommon.roundFixed(freightDiff, 2);
                $("#freightDiff" + index).text(SSCCommon.formatMoney(freightDiff));

                //联动修改实需付款合计
                var totalFreightDeal = 0;
                $("input[name$='freightActualPaid']").each(function() {
                    var deal = $.trim($(this).val());
                    totalFreightDeal = SSCCommon.add(totalFreightDeal, deal);
                });
                totalFreightDeal = SSCCommon.roundFixed(totalFreightDeal, 2);
                $("#totalFreightDeal").text(SSCCommon.formatMoney(totalFreightDeal));

                //联动修改运费金额差合计
                var totalFreightDiff = 0;
                $("td[id^='freightDiff']").each(function() {
                    var diff = SSCCommon.clearComma($(this).text());
                    totalFreightDiff = SSCCommon.add(totalFreightDiff, diff);
                });
                totalFreightDiff = SSCCommon.roundFixed(totalFreightDiff, 2);

                var totalFreightDiffStr = SSCCommon.formatMoney(totalFreightDiff);
                $("#totalFreightDiff").text(totalFreightDiffStr);
                $("#transportCostDiff").text(totalFreightDiffStr);

                //联动修改核销结果
                var firstAmountPaid = SSCCommon.clearComma($("#firstAmountPaid").text());
                var totalValueDiff = SSCCommon.clearComma($("#productValueDiff").text());
                var verificationAmount = SSCCommon.subtract(SSCCommon.add(totalValueDiff, totalFreightDiff), firstAmountPaid);

                verificationAmount = SSCCommon.roundFixed(verificationAmount, 2);
                var verificationAmountStr = SSCCommon.formatMoney(verificationAmount);
                var i_verificationAmount = parseInt(verificationAmount);

                var verificationResult;
                if (0 == i_verificationAmount) {
                    verificationResult = "无";
                }
                else if (i_verificationAmount > 0) {
                    verificationResult = "甲方应支付乙方" + verificationAmountStr + "元";
                }
                else if (i_verificationAmount < 0) {
                    verificationResult = "乙方应支付甲方" + verificationAmountStr.replaceAll("-", "") + "元";
                }

                $("#verificationAmountStr").text(verificationAmountStr);
                $("input[name='verificationAmount']").val(verificationAmount);  //保存在数据库的值
                $("#verificationResult").text(verificationResult);
            }
        });
    },

    /**
     * 点击甲乙方确认按钮，更新核销单审核状态
     */
    clickConfirmButton: function() {
        //甲方确认
        $("#SSC11322_PURCHASER_CONFIRM").click(function() {
            var purchaserConfirmStatus = $.trim($("input[name='purchaserConfirmStatus']:checked").val());
            if (purchaserConfirmStatus.length <= 0) {
                $.alertMessage.info("请甲方选择同意或不同意！");
                return;
            }
            if (1 == purchaserConfirmStatus) {
                var purchaserConfirmRemark = $("#purchaserConfirmRemark").val();
                if ($.trim(purchaserConfirmRemark).length <= 0) {
                    $.alertMessage.info("请甲方说明不同意的理由！");
                    return;
                }
                if (purchaserConfirmRemark.length > 100) {
                    $.alertMessage.info("不同意的理由不能超过100个字符！");
                    return;
                }
            }

            var auditStatus = $("input[name='auditStatus']").val();
            if (0 == auditStatus) {
                auditStatus = 1;
            }
            else if (2 == auditStatus) {
                auditStatus = -1;
            }
            SSC11322.auditVerification(auditStatus);
        });

        //乙方确认
        $("#SSC11322_SUPPLIER_CONFIRM").click(function() {
            var supplierConfirmStatus = $.trim($("input[name='supplierConfirmStatus']:checked").val());
            if (supplierConfirmStatus.length <= 0) {
                $.alertMessage.info("请乙方选择同意或不同意！");
                return;
            }
            if (1 == supplierConfirmStatus) {
                var supplierConfirmRemark = $("#supplierConfirmRemark").val();
                if ($.trim(supplierConfirmRemark).length <= 0) {
                    $.alertMessage.info("请乙方说明不同意的理由！");
                    return;
                }
                if (supplierConfirmRemark.length > 100) {
                    $.alertMessage.info("不同意的理由不能超过100个字符！");
                    return;
                }
            }

            var auditStatus = $("input[name='auditStatus']").val();
            if (0 == auditStatus) {
                auditStatus = 2;
            }
            else if (1 == auditStatus) {
                auditStatus = -2;
            }
            SSC11322.auditVerification(auditStatus);
        });
    },

    /**
     * 更新核销单审核状态
     */
    auditVerification: function(auditStatus) {
        var verificationId = $("input[name='verificationId']").val();
        var purchaserConfirmStatus = $("input[name='purchaserConfirmStatus']:checked").val();
        var supplierConfirmStatus = $("input[name='supplierConfirmStatus']:checked").val();
        var purchaserConfirmRemark = $.trim($("#purchaserConfirmRemark").val());
        var supplierConfirmRemark = $.trim($("#supplierConfirmRemark").val());
        var ver = $("input[name='ver']").val();

        var status;
        if (0 == purchaserConfirmStatus && 0 == supplierConfirmStatus && auditStatus < 0) {
            status = 3;
        }
        else {
            status = 2;
        }

        $("#main-content").postUrl(Main.contextPath + "/SSC11321/auditVerification", {
            verificationId: verificationId,
            status: status,
            auditStatus: auditStatus,
            purchaserConfirmStatus: purchaserConfirmStatus,
            supplierConfirmStatus: supplierConfirmStatus,
            purchaserConfirmRemark: purchaserConfirmRemark,
            supplierConfirmRemark: supplierConfirmRemark,
            ver: ver
        }, function(resp) {
            if ("S" == resp) {
                $("#main-content").postUrl(Main.contextPath + "/SSC11322/init", {verificationId: verificationId},Main.hiddenHeader);
                $.alertMessage.info("审核成功！");
            }
            else {
                $.alertMessage.info("审核失败！");
            }
        }, {refreshHtml: false});
    },

    /**
     * 点击差异单编号超链接，弹出差异单详情对话框
     */
    clickDifferCodeLink: function(differId) {
        $.pdialog.open("差异单详情", Main.contextPath + "/SSC11322/diff", {width: "80%"}, {differId: differId});
    },

    /**
     * 点击保存按钮，新增或更新核销单
     */
    clickSaveButton: function() {
        $("#SSC11322_VERIFICATION_SAVE").click(function() {
            if (SSC11322.validateFormData()) {
                $.alertMessage.confirm("请在合同产品全部收货和相应款项全部收款后，再生成核销单，确定保存吗？", function() {
                    $.alertMessage.close();
                    SSC11322.saveOrUpdateVerification();
                });
            }
        });
    },

    /**
     * 新增或更新前，校验表单数据
     */
    validateFormData: function() {
        var verificationId = $.trim($("input[name='verificationId']").val());
        if (verificationId.length > 0) {
            var verificationType = $("input[name='verificationType']:checked").val();
            var flag = 0;
            $("input[name$='freightActualPaid']").each(function() {
                var freightDeal = $.trim($(this).val());
                if (!SSCCommon.isMoneyGe0(freightDeal)) {
                    flag = 1;
                    return false;
                }
                if (SSCCommon.fgt(freightDeal, 0) && $.trim(verificationType).length <= 0) {
                    flag = 2;
                    return false;
                }
            });
            if (1 == flag) {
                $.alertMessage.info("实需付款不能为空，且格式为：整数位最多15位，小数位最多2位！");
                return false;
            }
            else if (2 == flag) {
                $.alertMessage.info("实需付款已修改，请选择核销处理办法！");
                return false;
            }

            var verificationAmount = $("#verificationAmountStr").text();
            if (0 == parseInt(verificationAmount) && 2 != verificationType) {
                $.alertMessage.info("合计金额差为0，核销处理办法请选择其它！");
                return false;
            }
            if (2 == verificationType) {
                var remark = $("textarea[name='remark']").val();
                if ($.trim(remark).length <= 0) {
                    $.alertMessage.info("其它核销处理办法，具体描述不能为空！");
                    return false;
                }
                if (remark.length > 100) {
                    $.alertMessage.info("具体描述不能超过100个字符！");
                    return false;
                }
            }
        }

        var verificationRemark = $("#verificationRemark").val();
        if (verificationRemark.length > 100) {
            $.alertMessage.info("备注不能超过100个字符！");
            return false;
        }
        return true;
    },

    /**
     * 新增或更新核销单及其详情
     */
    saveOrUpdateVerification: function() {
        var formdata = getFormData($("#SSC11322_form"));
        $("#main-content").postUrl(Main.contextPath + "/SSC11322/saveOrUpdateVerification", formdata, function(resp) {
            if ("S" == resp) {
                $("#main-content").postUrl(Main.contextPath + "/SSC11322/reload", {contractId: formdata.contractId},Main.hiddenHeader);
                $.alertMessage.info("操作成功！");
            }
        }, {refreshHtml: false});
    },

    /**
     * 点击申请付款按钮，跳转到付款详情页面
     */
    clickPaymentRequestButton: function() {
        $("#SSC11322_PAYMENT_REQUEST").click(function() {
            var verificationId = $("input[name='verificationId']").val();
            var verificationCode = $("input[name='verificationCode']").val();
            Main.detailWindow(Main.contextPath + "/SSC11308/init/3", {
                verificationId: verificationId,
                verificationCode: verificationCode
            },"付款申请");
        });
    },

    /**
     * 点击合同结案按钮，更新合同状态
     */
    clickContractCloseButton: function() {
        $("#SSC11322_CONTRACT_CLOSE").click(function() {
            $.alertMessage.confirm("确定合同结案吗？", function() {
                $.alertMessage.close();
                var contractId = $("input[name='contractId']").val();
                var verificationType = $("input[name='verificationType']:checked").val();

                if (0 == verificationType) {
                    $("#main-content").postUrl(Main.contextPath + "/SSC11308/assertAllBalancePaid", {contractId: contractId}, function(resp) {
                        if ("all_paid" == resp) {
                            SSC11322.closeContract();
                        }
                        else {
                            $.alertMessage.info("请先足额付款，再关闭合同！");
                        }
                    }, {refreshHtml: false});
                }
                else {
                    SSC11322.closeContract();
                }
            });
        });
    },
    /**
     * 点击发票按钮，跳转到发票详情页面
     */
    clickInvoiceRequestButton: function() {
        $("#SSC11322_INVOICE_REQUEST").click(function() {
            var contractId = $("input[name='contractId']").val();
            var contractCode = $("input[name='contractCode']").val();
            $.pdialog.open("发票申请一览",Main.contextPath + "/SSC11323/show", {
                width: 900,
                height: 600
            }, {
                contractId: contractId,
                contractCode: contractCode
            });
        });
    },
    closeContract: function() {
        var contractId = $("input[name='contractId']").val();
        var verificationId = $("input[name='verificationId']").val();
        var ver = $("input[name='ver']").val();

        $("#main-content").postUrl(Main.contextPath + "/SSC11322/closeContract", {
            contractId: contractId,
            verificationId: verificationId,
            ver: ver
        }, function(resp) {
            if ("S" == resp) {
                $("#main-content").postUrl(Main.contextPath + "/SSC11322/init", {verificationId: verificationId},Main.hiddenHeader);
                $.alertMessage.info("合同结案成功！");
            }
            else {
                $.alertMessage.info("合同结案失败！");
            }
        }, {refreshHtml: false});
    },

    clickVerificationTypeRadio: function() {
        $("input[name='verificationType']").click(function() {
            var type = $("input[name='verificationType']:checked").val();
            var $remark = $("#remark");
            if (2 != type) {
                $remark.attr("disabled", true);
                $remark.val("");
            }
            else {
                $remark.attr("disabled", false);
            }
        });
    },

    newLine: function() {
        $("td[id^='intoStoreCode']").each(function() {
            var verificationId = $("input[name='verificationId']").val();
            var intoStoreCode = $(this).text();
            var intoStoreCodes = intoStoreCode.split(",");

            var str = "";
            if ($.trim(verificationId).length > 0) {
                var intoStoreId = $(this).attr("intoStoreId");
                var intoStoreIds = intoStoreId.split(",");

                for (var i in intoStoreCodes) {
                    str += "<a href='javascript:SSC11322.clickIntoStoreCodeLink(" + intoStoreIds[i] + ");'>" + intoStoreCodes[i] + "</a><br />"
                }
            }
            else {
                for (var i in intoStoreCodes) {
                    str += intoStoreCodes[i] + "<br />"
                }
            }
            str = str.substring(0, str.lastIndexOf("<br />"));
            $(this).html(str);
        });
    },

    /**
     * 点击合同编号超链接
     */
    clickContractCodeLink: function(contractId) {
        var verificationId = $("input[name='verificationId']").val();
        Main.detailWindow(Main.contextPath + "/SSC11304/show", {
            contractId: contractId,
            verificationId: verificationId
        },"合同详细");
    },

    clickDeliveryCodeLink: function(deliveryId) {
        var verificationId = $("input[name='verificationId']").val();
        Main.detailWindow(Main.contextPath + "/SSC11306/show", {
            deliveryId: deliveryId,
            verificationId: verificationId
        },"发货订单详细");
    },

    clickIntoStoreCodeLink: function(intoStoreId) {
        var verificationId = $("input[name='verificationId']").val();
        Main.detailWindow(Main.contextPath + "/SSC11317/init/3", {
            deliveryPreIntoId: intoStoreId,
            verificationId: verificationId
        },"预入库通知单详细");
    }

};

$(document).ready(function() {
    SSC11322.newLine();
    SSC11322.clickVerificationTypeRadio();
    SSC11322.changeFreightDealInput();
    SSC11322.clickSaveButton();
    SSC11322.clickConfirmButton();
    SSC11322.clickPaymentRequestButton();
    SSC11322.clickContractCloseButton();
    SSC11322.clickInvoiceRequestButton();
    SSCCommon.disableEnterKey();        //必须放倒数第二行
    Main.hlLeftMainMenu("SSC11321");    //必须放最后一行
    //展示格式化后的金额
    SSCCommon.showFormatMoney();
});

