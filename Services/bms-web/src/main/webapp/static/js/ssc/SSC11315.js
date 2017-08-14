/**
 * Created by sun_jiaju on 2016/7/4.
 */

var $List_Grid;
var SSC11315 = {
    confrimButtonId: "SSC11315_CONFIRM",
    savepreButtonId: "SSC11315_SAVEPRE",
    formId: "SSC11315Form",
    etd: "#etd",
    eta: "#eta",
    flag: null,

    init: function () {
        $List_Grid = $('#SSC11315_list_grid').grid({
            paging: false,
            actionHandler: SSC11315.actionHandler,
            editCellOnBlurHandler: SSC11315.editCellOnBlurHandler,
            can_abolish: SSC11315.canAbolish
        });
        this.closeDate();
        this.bindConfirmButton();
        this.bindDatePicber(SSC11315.etd);
        this.bindDatePicber(SSC11315.eta);
        this.PDConfirmBindButton();
        this.WHConfirmBindButton();
        this.purchaserConfirmBindButton();
        this.bindButton();
        this.viewProductModificationHistory();
        this.control();
        SSCCommon.showFormatMoney();

    },
    canAbolish: function (rowdata) {
        if ($("#deliveryConfirmStatus").val() == '1') {
            return true;
        }
        return false;
    },
    bindConfirmButton: function () {
        $("#" + SSC11315.confrimButtonId).click(function () {
            if (SSC11315.flag != 1) {
                $.alertMessage.info("请编辑后再保存");
                return;
            }

            $.alertMessage.confirm("你确定要修改这条数据吗？", function () {
                $.alertMessage.close();
                var notes = 100; //文本长度
                var etd = $("#etd").val();
                var eta = $("#eta").val();
                var reg = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
                var byConfirmReason = $("#byConfirmReason").val();
                var whConfirmReason = $("#whConfirmReason").val();
                var pdConfirmReason = $("#pdConfirmReason").val();
                if (byConfirmReason.length > notes) {
                    $.alertMessage.info("原因长度超过100字符，请重新输入");
                    return false;
                }
                if (whConfirmReason.length > notes) {
                    $.alertMessage.info("原因长度超过100字符，请重新输入");
                    return false;
                }
                if (pdConfirmReason.length > notes) {
                    $.alertMessage.info("原因长度超过100字符，请重新输入");
                    return false;
                }
                if (!reg.test(etd) || !reg.test(eta)) {
                    $.alertMessage.info("请按YYYY-MM-DD格式输入发货时间、到货时间！");
                    return;
                }
                if (etd > eta) {
                    $.alertMessage.info("到货日期应大于发货时间!");
                    return;
                }
                var formData = getFormData($("#" + SSC11315.formId));
                formData.byConfirmStatus = 0;
                formData.whConfirmStatus = 0;
                formData.pdConfirmStatus = 0;
                formData.deliveryConfirmStatus = 1;
                $('#main-content').postUrl(Main.contextPath + "/SSC11315/save", formData, Main.hiddenHeader);

            });
        });
    },
    bindDatePicber: function (timeId) {
        $(timeId).datepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            changeMonth: true,
            closeText: "清除",
            changeYear: true
        });
        $(timeId).attr("readonly", "readonly");
    },

    closeDate: function () {
        $(document).on("click", "button.ui-datepicker-close", function () {
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    control: function () {
        // 根据发货确认单状态制御画面
        if ($("input[id='whConfirmStatus']").val() == "2" || $("input[id='pdConfirmStatus']").val() == "2" || $("input[id='byConfirmStatus']").val() == "2") {
            $("input[name='pdConfirmStatus']").attr("disabled", true);
            $("input[name='byConfirmStatus']").attr("disabled", true);
            $("input[name='whConfirmStatus']").attr("disabled", true);
            $("#byConfirmReason").attr("disabled", true);
            $("#whConfirmReason").attr("disabled", true);
            $("#pdConfirmReason").attr("disabled", true);
            SSC11315.flag = 3;
        }


        //if (deliveryConfirmStatus == "1"){
        //    $("input[name='whConfirmStatus']").attr("disabled",true);
        //    $("input[name='pdConfirmStatus']").attr("disabled",true);
        //    $("#whConfirmReason").attr("disabled",true);
        //    $("#pdConfirmReason").attr("disabled",true);
        //} else if(deliveryConfirmStatus == "2"){
        //    $("input[name='byConfirmStatus']").attr("disabled",true);
        //    $("input[name='pdConfirmStatus']").attr("disabled",true);
        //    $("#byConfirmReason").attr("disabled",true);
        //    $("#pdConfirmReason").attr("disabled",true);
        //} else if (deliveryConfirmStatus == "3"){
        //    $("input[name='byConfirmStatus']").attr("disabled",true);
        //    $("input[name='whConfirmStatus']").attr("disabled",true);
        //    $("#byConfirmReason").attr("disabled",true);
        //    $("#whConfirmReason").attr("disabled",true);
        //} else{
        //    //$(":input").attr("disabled",true);
        //    $("input[name='byConfirmStatus']").attr("disabled",true);
        //    $("input[name='whConfirmStatus']").attr("disabled",true);
        //    $("input[name='pdConfirmStatus']").attr("disabled",true);
        //    $("#byConfirmReason").attr("disabled",true);
        //    $("#whConfirmReason").attr("disabled",true);
        //    $("#pdConfirmReason").attr("disabled",true);
        //    $("#etd").attr("disabled",true);
        //    $("#eta").attr("disabled",true);
        //}
    },
    editCellOnBlurHandler : function($comp){
        //获得TD
        var $td = $comp.parent();
        //获得TR
        var $tr = $td.parent();
        //获得所有行
        var $trs = $tr.parent();
        //获得当前行
        SSC11315.THISROWINDEX = $trs.children().index($tr);
        SSC11315.THISROWDATA = $List_Grid.fnGetData(SSC11315.THISROWINDEX);
        //获得当前列数据
        var num = SSCCommon.clearComma($comp.val());
        //获得当前列name
        var name = $td.context.name;
        if(name == "productConfirmBox"){
            //获取净重
            var weight = SSC11315.THISROWDATA["weightVal"];

            if(SSCCommon.isMoney(num)){
                if (SSCCommon.POSITIVE_INTEGER.test(num) && num <= SSCCommon.INT11) {
                    //赋值
                    SSC11315.THISROWDATA["productQua"] = SSCCommon.roundFixed(SSCCommon.multiply(weight ,num),4);
                    SSC11315.THISROWDATA["productValue"] = SSCCommon.roundFixed(SSCCommon.multiply(SSC11315.THISROWDATA["productQua"] ,SSC11315.THISROWDATA["settkementStandardPrice"]),2);
                }
            }

        }
        if(name == "settkementStandardPrice"){
            if (SSCCommon.isMoney(num)) {
                SSC11315.THISROWDATA["productValue"] = SSCCommon.roundFixed(SSCCommon.multiply(SSC11315.THISROWDATA["productQua"] ,num),2);
            }
        }

        //计算合计
        var allData = $List_Grid.fnGetData();
        var sumProductBox = 0.0;
        var sumProductQua = 0.0;
        var sumProductValue = 0.0;
        for (var i = 0; i < allData.length; i++) {
            if (name == "productConfirmBox") {
                if (SSC11315.THISROWINDEX == i) {
                    var numformate=SSCCommon.clearComma(num);
                    sumProductBox = SSCCommon.add(sumProductBox, numformate);
                } else {
                    sumProductBox = SSCCommon.add(sumProductBox, SSCCommon.clearComma(parseFloat(allData[i]["productConfirmBox"])));
                }
            }
            if (name == "settkementStandardPrice") {
                sumProductBox = SSCCommon.add(sumProductBox,SSCCommon.clearComma(parseFloat(allData[i]["productConfirmBox"])));
            }
            if (name == "remark") {
                sumProductBox = SSCCommon.add(sumProductBox, SSCCommon.clearComma(parseFloat(allData[i]["productConfirmBox"])));
            }
            sumProductQua = SSCCommon.add(sumProductQua, parseFloat(allData[i]["productQua"]));
            sumProductValue = SSCCommon.add(sumProductValue, parseFloat(allData[i]["productValue"]));
        }
        if(SSCCommon.isMoney(sumProductBox)){
            $("#allProductConfirmBox").html(fmoney(sumProductBox,0));
        }
        if(SSCCommon.isMoney(sumProductQua)){
            $("#allProductQua").html(fmoney(sumProductQua,4));
        }
        if(SSCCommon.isMoney(sumProductValue)){
            $("#allProductValue").html(fmoney(sumProductValue,2));
        }
        //sumProductValue = formatMoney(sumProductValue);

    },

    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "save") {
            var changeData = $List_Grid.getChangeData();
            if (changeData.length == 0) {
                $.alertMessage.info("请编辑后再保存");
                return;
            }
            for (var i = 0; i < changeData.length; i++) {
                var productConfirmBox = SSCCommon.clearComma(changeData[i]["productConfirmBox"]);
                if (!SSCCommon.POSITIVE_INTEGER.test(productConfirmBox)) {
                    $.alertMessage.info("发货箱数不能为空或0，且必须是大于0的整数！");
                    return;
                }
                if (productConfirmBox > SSCCommon.INT11) {
                    $.alertMessage.info("箱数不能超过99999999！");
                    return;
                }

                var productQua = changeData[i]["productQua"];
                if (!SSCCommon.WEIGHT_REG.test(productQua)) {
                    $.alertMessage.info("发货数量格式错误（整数位最多12位，小数位最多4位）！");
                    return;
                }

                var settkementStandardPrice = SSCCommon.clearComma(changeData[i]["settkementStandardPrice"]);
                if (!SSCCommon.isMoney(settkementStandardPrice)) {
                    $.alertMessage.info("结算标准价不能为空或0，且格式为：整数位最多15位，小数位最多2位！");
                    return;
                }

                var productValue = changeData[i]["productValue"];
                if (!SSCCommon.MONEY_REG.test(productValue)) {
                    $.alertMessage.info("货值格式错误（整数位最多15位，小数位最多2位）！");
                    return;
                }
            }

            $.alertMessage.confirm("你确定要修改这条数据吗？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SSC11315/update", {
                    detailId: rowdata.detailId,
                    ver: rowdata.ver,
                    productConfirmBox: SSCCommon.clearComma(rowdata.productConfirmBox),
                    productQua: rowdata.productQua,
                    productValue: rowdata.productValue,
                    settkementStandardPrice:SSCCommon.clearComma(rowdata.settkementStandardPrice),
                    deliveryConfirmId: $("#deliveryConfirmId").val(),
                    deliveryConfirmCode: $("#deliveryConfirmCode").val(),
                    contractName: $("#contractName").val(),
                    byConfirmStatus: "0",
                    whConfirmStatus: "0",
                    pdConfirmStatus: "0",
                    deliveryConfirmBasicVer: $("#ver").val(),
                    deliveryConfirmStatus: "1"
                }, Main.hiddenHeader);
            });
        }
    },

    purchaserConfirmBindButton: function () {
        //生成预入库单
        $("#SSC11315_PURCHASERCONFIRM").click(function () {

                if (SSC11315.flag == 1) {
                    $.alertMessage.info("数据已修改,请保存后再确认");
                    return;
                }
                if (SSC11315.flag == 3) {
                    $.alertMessage.info("有一方以上不同意,请修改后再重新确认");
                    return;
                }
                var deliveryConfirmStatus = 1;
                if ($("input[name='byConfirmStatus']:checked").val() == 1 && $("input[name='whConfirmStatus']:checked").val() == 1 && $("input[name='pdConfirmStatus']:checked").val() == 1) {
                    if ($("#pdConfirmStatus").val() == 1 && $("#whConfirmStatus").val() == 1) {
                        deliveryConfirmStatus = 4;
                    }
                }

                $('#main-content').postUrl(Main.contextPath + "/SSC11315/save", {
                    deliveryConfirmCode: $("#deliveryConfirmCode").val(),
                    byConfirmStatus: $("input[name='byConfirmStatus']:checked").val(),
                    deliveryConfirmId: $("#deliveryConfirmId").val(),
                    ver: $("#ver").val(),
                    deliveryConfirmStatus: deliveryConfirmStatus,
                    byConfirmReason: $("#byConfirmReason").val()
                }, Main.hiddenHeader);
            }
        )
    },

    WHConfirmBindButton: function () {
        //生成预入库单
        $("#SSC11315_WHCONFIRM").click(function () {
                if (SSC11315.flag == 1) {
                    $.alertMessage.info("数据已修改,请保存后再确认");
                    return;
                }
                if (SSC11315.flag == 3) {
                    $.alertMessage.info("有一方以上不同意,请修改后再重新确认");
                    return;
                }
                var deliveryConfirmStatus = 1;
                if ($("input[name='byConfirmStatus']:checked").val() == 1 && $("input[name='whConfirmStatus']:checked").val() == 1 && $("input[name='pdConfirmStatus']:checked").val() == 1) {
                    if ($("#pdConfirmStatus").val() == 1 && $("#byConfirmStatus").val() == 1) {
                        deliveryConfirmStatus = 4;
                    }
                }

                $('#main-content').postUrl(Main.contextPath + "/SSC11315/save", {
                    deliveryConfirmCode: $("#deliveryConfirmCode").val(),
                    whConfirmStatus: $("input[name='whConfirmStatus']:checked").val(),
                    deliveryConfirmId: $("#deliveryConfirmId").val(),
                    ver: $("#ver").val(),
                    deliveryConfirmStatus: deliveryConfirmStatus,
                    whConfirmReason: $("#whConfirmReason").val()
                }, Main.hiddenHeader);
            }
        )
    },

    PDConfirmBindButton: function () {
        //生成预入库单
        $("#SSC11315_PDCONFIRM").click(function () {
                if (SSC11315.flag == 1) {
                    $.alertMessage.info("数据已修改,请保存后再确认");
                    return;
                }
                if (SSC11315.flag == 3) {
                    $.alertMessage.info("有一方以上不同意,请修改后再重新确认");
                    return;
                }
                var deliveryConfirmStatus = 1;
                if ($("input[name='byConfirmStatus']:checked").val() == 1 && $("input[name='whConfirmStatus']:checked").val() == 1 && $("input[name='pdConfirmStatus']:checked").val() == 1) {
                    if ($("#whConfirmStatus").val() == 1 && $("#byConfirmStatus").val() == 1) {
                        deliveryConfirmStatus = 4;

                    }
                }

                $('#main-content').postUrl(Main.contextPath + "/SSC11315/save", {
                    deliveryConfirmCode: $("#deliveryConfirmCode").val(),
                    pdConfirmStatus: $("input[name='pdConfirmStatus']:checked").val(),
                    deliveryConfirmId: $("#deliveryConfirmId").val(),
                    ver: $("#ver").val(),
                    deliveryConfirmStatus: deliveryConfirmStatus,
                    pdConfirmReason: $("#pdConfirmReason").val()
                }, Main.hiddenHeader);
            }
        )
    },

    bindButton: function () {
        //生成预入库单
        $("#" + SSC11315.savepreButtonId).click(function () {
            $('#main-content').postUrl(Main.contextPath + "/SSC11315/checkPdPlanBox", {
                    deliveryConfirmId: $("#deliveryConfirmId").val(),
                    deliveryConfirmCode: $("#deliveryConfirmCode").val()
                },
                function (data) {
                    if (data == "F") {
                        $.alertMessage.info("该确认单的预入库单已经全部生成！");
                    } else {
                        $.pdialog.open("生成预入库通知单", Main.contextPath + "/SSC11315/chooseConfirmPds", {width: "50%"}, {
                            deliveryConfirmId: $("#deliveryConfirmId").val(),
                            deliveryConfirmCode: $("#deliveryConfirmCode").val(),
                            deliveryBatch: $("#deliveryBatch").val()
                        });
                    }
                }, {refreshHtml: false});
        });
        //再修改
        $("#SSC11315_EDIT").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/SSC11316/findPreInto", {
                deliveryConfirmId: $("#deliveryConfirmId").val()
            }, function (data) {
                //1 没有生成预入库通知单  2 已发车  3  未发车
                if (data.flag == 1) {
                    $.alertMessage.confirm("再修改需要三方再次确认，是否修改?", function () {
                        $.alertMessage.close();

                        //用于标识再修改
                        $("#deliveryConfirmStatus").val(5);

                        var formData = getFormData($("#" + SSC11315.formId));
                        formData.deliveryConfirmStatus = "1";
                        formData.byConfirmStatus = 0;
                        formData.whConfirmStatus = 0;
                        formData.pdConfirmStatus = 0;
                        $('#main-content').postUrl(Main.contextPath + "/SSC11315/save", formData, Main.hiddenHeader);
                    })
                } else if (data.flag == 2) {
                    $.alertMessage.info("该发货确认单已发车,不允许再修改");
                } else if (data.flag == 3) {
                    var list = data.preIntoCodeList;
                    var showStr = "";
                    for (var i = 0; i < list.length; i++) {
                        if (i < list.length - 1) {
                            showStr += list[i] + ",";
                        } else {
                            showStr += list[i];
                        }
                    }
                    $.alertMessage.info("请先删除对应的预入库单：" + showStr + ",再修改");
                }
            }, {refreshHtml: false});
        });
    },

    /**
     * 点击查看三方确认履历按钮，弹出产品修改历史对话框
     */
    viewProductModificationHistory: function () {
        $("#SSC11315_HISTORY").click(function () {
            var deliveryConfirmId = $("#deliveryConfirmId").val();
            $.pdialog.open("三方确认履历", Main.contextPath + "/SSC1131502/view", {width: "80%"}, {deliveryConfirmId: deliveryConfirmId});
        });
    },
    onchange: function () {
        SSC11315.flag = 1;
    }
};

/**
 * 跳转到发货订单详细页面（页面显示发货订单Code,传入发货订单Id,传发货确认单Code是为了让下一个页面识别）
 * @param data
 */
function goToDeliveryOrderDetail(deliveryId) {
    Main.detailWindow(Main.contextPath + "/SSC11306/show", {
        deliveryConfirmCode: $("#deliveryConfirmCode").val(),
        deliveryId: deliveryId
    }, "发货订单详细");

    /*$('#main-content').postUrl(Main.contextPath + '/SSC11315/show', {
        deliveryConfirmCode: $("#deliveryConfirmCode").val(),
        deliveryId: deliveryId
    }, function () {
        Main.hlLeftMainMenu("SSC11305");
    });*/
}




$(document).ready(function () {
    SSC11315.init();
});