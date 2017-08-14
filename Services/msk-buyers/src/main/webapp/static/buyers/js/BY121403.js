/**
 * 批发市场新增
 * Created by zhou_yajun on 2016/7/8.
 */


var BY121403 = {
    BY121403_Grid: null,
    BY121403_Grid2: null,
    saveUApproveButton: "BY121403_SAVEUAPPROVE",
    saveUApproveButton2: "BY121403_SAVEUAPPROVE2",
    saveTargetButton: "BY121403_TARGETADD",
    saveNoTargetAddButton: "BY121403_NOTARGETADD",
    saveNoTargetSynchButton: "BY121403_NOTARGETSYNCH",

    initDataGrid: function () {
        BY121403.BY121403_Grid = $("#BY121403_Grid").grid({
            fnRowCallback: BY121403.rowCallback,
            fnDrawCallback: BY121403.drawCallback
        });
        BY121403.BY121403_Grid2 = $("#BY121403_Grid2").grid({
            fnRowCallback: BY121403.rowCallback2,
            fnDrawCallback: BY121403.drawCallback2
        });
        this.changeHandler();
        this.marketSaveHandler();
        this.marketByAddHandler();
        this.marketTagHandler();
        this.marketNoTagHandler();
    },
    marketTagHandler: function () {
        $("#" + BY121403.saveTargetButton).click(function () {
            var changeData = BY121403.BY121403_Grid.getChangeData();// 获取改动的数据对象  是数组
            if (changeData.length == 0) {
                $.alertMessage.confirm("请先填写金额再保存数据！", function () {
                    $.alertMessage.close();
                });
                return false;
            } else {
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    var reg = /^\d{1,8}(\.\d{1,2})?$/;
                    $.alertMessage.close();
                    var json = {};// 创建json 对象
                    for (i = 0; i < changeData.length; i++) {//  把数组的对象封装到json
                        if (changeData[i].annualTurnover.length >= 12) {
                            $.alertMessage.info("数值不能大于10位!", function () {
                                $.alertMessage.close();
                            });
                            return false;
                        }
                        else if (isNaN(changeData[i].annualTurnover)) {
                            $.alertMessage.info("金额输入不合法!", function () {
                                $.alertMessage.close();
                            });
                            return false;
                        } else if (!reg.test(changeData[i].annualTurnover)) {
                            $.alertMessage.info("金额输入不合法!", function () {
                                $.alertMessage.close();
                            });
                            return false;
                        }
                        else if (changeData[i].annualTurnover != "") {
                            json[i] = changeData[i];
                        }
                    }
                    if (json == null || json == '') {
                        $.alertMessage.info("请输入金额数量!")
                        return false;
                    } else {
                        var jsonStr = JSON.stringify(json);//  转成jsonStr
                        $('#main-content').postUrl(Main.contextPath + "/BY121403/marketSave", {"jsonStr": jsonStr}, function (data) {
                            $.alertMessage.info("保存成功。");
                            BY121403.BY121403_Grid.fnDraw();
                        }, {refreshHtml: false});
                    }
                })
            }
        });
    },
    marketNoTagHandler: function () {
        $("#" + BY121403.saveNoTargetAddButton).click(function () {
            var changeData = BY121403.BY121403_Grid2.getChangeData();// 获取改动的数据对象  是数组
            if (changeData.length == 0) {
                $.alertMessage.confirm("请先填写金额再保存数据！", function () {
                    $.alertMessage.close();
                });
                return false;
            } else {
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    var reg = /^\d{1,8}(\.\d{1,2})?$/;
                    var json = {};// 创建json 对象
                    for (i = 0; i < changeData.length; i++) {//  把数组的对象封装到json
                        if (changeData[i].annualTurnover.length >= 12) {
                            $.alertMessage.info("数值不能大于10位!", function () {
                                $.alertMessage.close();
                            });
                            return false;
                        } else if (isNaN(changeData[i].annualTurnover)) {
                            $.alertMessage.info("金额输入不合法!", function () {
                                $.alertMessage.close();
                            });
                            return false;
                        } else if (!reg.test(changeData[i].annualTurnover)) {
                            $.alertMessage.info("金额输入不合法!", function () {
                                $.alertMessage.close();
                            });
                            return false;
                        }
                        else if (changeData[i].annualTurnover != "") {
                            json[i] = changeData[i];
                        }
                    }
                    if (json == null || json == '') {
                        $.alertMessage.info("请输入金额数量!")
                        return false;
                    } else {
                        var jsonStr = JSON.stringify(json);//  转成jsonStr
                        $('#main-content').postUrl(Main.contextPath + "/BY121403/marketSave", {"jsonStr": jsonStr}, function (data) {
                            $.alertMessage.info("保存成功。");
                            BY121403.BY121403_Grid2.fnDraw();
                        }, {refreshHtml: false});
                    }
                })
            }
        });
        $("#" + BY121403.saveNoTargetSynchButton).click(function () {
            // 目标买家信息同步
            var marketId = $("#marketId").val();
            $('#main-content').postUrl(Main.contextPath + "/BY121403/synchMarket/" + marketId, null,
                function (data) {
                    if (data != null) {
                        $("#maxClassBuyerType").val(data.maxClassBuyerType);
                        $("#maxClassBuyerNum").val(data.maxClassBuyerNum);
                        $("#targetBuyer").val(data.targetBuyer);
                    }
                    $.alertMessage.info("买家信息同步成功");
                    BY121403.BY121403_Grid.fnDraw();
                    BY121403.BY121403_Grid2.fnDraw();
                    $("#synchButton").attr("style", "display:none");
                }, {refreshHtml: false});
        })
    },
    //物流区变更获取相应的城市信息
    changeHandler: function () {
        $("#lgcsAreaCode").change(function () {
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            $('#main-content').postUrl(Main.contextPath + "/BY121403/lgcsAreaChange/" + lgcsAreaCode, null,
                function (data) {
                    $("#cityCode").html("");
                    $.each(data, function (i, item) {
                        $("#cityCode").append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                    });
                }, {refreshHtml: false});
        });
        $('#marketName').click(function () {
            var marketId = $("#flag").val();
            if (marketId == "add") {
                $.pdialog.open("批发市场选择", Main.contextPath + "/BY121403/marketInfoChooseInit", {
                    width: "70%",
                    top: "400px",
                    height: 500
                }, {
                    marketCode: $('#marketCode').val()
                });
            }
        });
    },
    //批发市场基本信息保存
    marketSaveHandler: function () {
        $("#" + BY121403.saveUApproveButton).click(function () {
                if ($("#BY121403SaveForm").validateForm()) {
                    
                    var marketId = $("#marketId").val();
                   if(!BY121403.saveCheack()){
                       return false;
                   }
                    $("#marketStatus").val("0");
                    BY121403.basicInfoSave();
                    if (flag == "add") {
                        $("#main-content").postUrl(Main.contextPath + "/BY121401/init");
                    }
                }
            }
        );
        $("#" + BY121403.saveUApproveButton2).click(function () {
                if ($("#BY121403SaveForm").validateForm()) {
                    if(!BY121403.saveCheack()){
                        return false;
                    }
                    $("#main-content").postUrl(Main.contextPath + "/BY121403/regExistence", {
                        marketId: $("#marketId").val()
                    }, function (data) {
                        if (data == "S") {
                            $("#existenceFlg").val("1");
                            $.alertMessage.confirm("该市场已存在审批状态，更新审批？", function () {
                                BY121403.basicInfoSave();
                                if (flag == "add") {
                                    $("#main-content").postUrl(Main.contextPath + "/BY121401/init");
                                }
                            })
                        } else {
                            $("#existenceFlg").val("0");
                            BY121403.basicInfoSave();
                            if (flag == "add") {
                                $("#main-content").postUrl(Main.contextPath + "/BY121401/init");
                            }
                        }
                    }, {refreshHtml: false});
                }
            }
        );
    },
    //新增批发市场下的买家
    marketByAddHandler: function () {
        $("#" + BY121403.addButton).click(function () {
            var marketId = $("#marketId").val();
            var id = 0;
            if (marketId == "add") {
                $.alertMessage.info("请先新增批发市场基本信息。", function () {
                    $.alertMessage.close();
                });
                return false;
            } else {
                $.pdialog.open("批发市场商户信息", Main.contextPath + "/BY121404/init/" + marketId + "/" + id, {
                        width: 350,
                        top: "400px",
                        height: 500
                    }
                )
            }
        });
    },

    //批发市场基本信息保存
    basicInfoSave: function () {
        var formData = getFormData($("#BY121403SaveForm"));
        $('#main-content').postUrl($("#BY121403SaveForm").attr("action"), formData, function (data) {
            if (data.errorMessage != null && data.errorMessage != "") {
                $.alertMessage.info(data.errorMessage, function () {
                    $.alertMessage.close()
                });
                return false;
            } else {
                $.alertMessage.info("保存成功!",
                    function () {
                        $.alertMessage.close()
                    }
                );
            }
        }, {refreshHtml: false});
    },
    saveCheack:function(){
        var flag = $("#flag").val();
        var areaCovered = $("#areaCovered").val();
        var targetAnnualTurnover = $("#targetAnnualTurnover").val();
        var reg = /^\d{1,8}(\.\d{1,2})?$/;
        var marketName = $("#marketName").val();
        var marketAddr = $("#marketAddr").val();
        var researchPhase = $("#researchPhase option:selected").text();// 获取市场调研类型
        if (researchPhase == "--请选择--") {
            $.alertMessage.info("请选择市场调研类型 ！");
            return false;
        }
        if (marketName == "") {
            $.alertMessage.info("请填写批发市场名称。", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if (marketAddr == "") {
            $.alertMessage.info("请填写批发市场详细地址。", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if (areaCovered != null && areaCovered != '') {
            if (isNaN(Number(areaCovered))) {
                $.alertMessage.info("占地面积请输入数字!", function () {
                    $.alertMessage.close();
                });
                return false;
            }
            if (!reg.test(areaCovered)) {
                $.alertMessage.info("占地面积输入错误!", function () {
                    $.alertMessage.close();
                });
                return false;
            }
        }
        if (targetAnnualTurnover != null && targetAnnualTurnover != '') {
    /*        if (isNaN(Number(targetAnnualTurnover))) {
                $.alertMessage.info("本批发市场年销售额输入错误!", function () {
                    $.alertMessage.close();
                });
                return false;
            }*/
            if (!reg.test(targetAnnualTurnover)) {
                $.alertMessage.info("本批发市场年销售额输入错误!", function () {
                    $.alertMessage.close();
                });
                return false;
            }
        }
        return true;

    },

    //简单必须项check
    showErrorMessage: function () {
        var marketName = $("#marketName").val();
        var marketAddr = $("#marketAddr").val();
        if (marketName == "") {
            $.alertMessage.info("请填写批发市场名称。", function () {
                $.alertMessage.close();
            });
            return false;
        }
        if (marketAddr == "") {
            $.alertMessage.info("请填写批发市场详细地址。", function () {
                $.alertMessage.close();
            });
            return false;
        }
        return true;
    },
    drawCallback: function () {
        var dataList = BY121403.BY121403_Grid.fnGetData();
        if (dataList.length == 0) {
            $("#currentNumber").html("");
            $("#currentAmount").html("");
            $("#totalNumber").html("");
            $("#totalAmount").html("");
        }
    },
    drawCallback2: function () {
        var dataList = BY121403.BY121403_Grid2.fnGetData();
        if (dataList.length == 0) {
            $("#currentNumber2").html("");
            $("#currentAmount2").html("");
            $("#totalNumber2").html("");
            $("#totalAmount2").html("");
        }
    },
    rowCallback: function (tr, data) {
        if (tr._DT_RowIndex == 0) {
            $("#currentNumber").html(data.currentNumber);
            $("#currentAmount").html(fmoney(data.currentAmount,2));
            $("#totalNumber").html(data.totalNumber);
            $("#totalAmount").html(fmoney(data.totalAmount,2));
        }
    },
    rowCallback2: function (tr, data) {
        if (tr._DT_RowIndex == 0) {
            $("#currentNumber2").html(data.currentNumber);
            $("#currentAmount2").html(fmoney(data.currentAmount,2));
            $("#totalNumber2").html(data.totalNumber);
            $("#totalAmount2").html(fmoney(data.totalAmount,2));
        }
    },
    initTable: function () {
        var $grid = $("#BY121403_Grid tbody");
        var $grid2 = $("#BY121403_Grid2 tbody");
        $grid.after("<tr role='row' style='height: 26px;'>" +
            "  <td style='border-left: 1px solid #b1b1b1;border-top: 1px solid #b1b1b1;' rowspan='2' colspan='2'></td>" +
            "  <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA;text-align: right'>当前页合计:</td>" +
            "  <td class='text'  name='currentNumber' id='currentNumber' style='border: 1px solid #b1b1b1;text-align: left'></td>" +
            "  <td class='text'  name='currentAmount' id='currentAmount' style='border: 1px solid #b1b1b1;text-align: left'></td>" +
            "</tr>" +
            "<tr role='row' style='height: 26px;'>" +
            "  <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA;text-align: right'>总合计:</td>" +
            "  <td class='text'  name='totalNumber' id='totalNumber' style='border: 1px solid #b1b1b1;text-align: left'></td>" +
            "  <td class='text'  name='totalAmount' id='totalAmount' style='border: 1px solid #b1b1b1;text-align: left'></td>" +
            "</tr>");

        $grid2.after("<tr role='row' style='height: 26px;'>" +
            "  <td style='border-left: 1px solid #b1b1b1;border-top: 1px solid #b1b1b1;' rowspan='2' ></td>" +
            "  <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA;text-align: right'>当前页合计:</td>" +
            "  <td class='text'  name='currentNumber2' id='currentNumber2' style='border: 1px solid #b1b1b1;text-align: left'></td>" +
            "  <td class='text'  name='currentAmount2' id='currentAmount2' style='border: 1px solid #b1b1b1;text-align: left'></td>" +
            "</tr>" +
            "<tr role='row' style='height: 26px;'>" +
            "  <td class='text' style='border: 1px solid #b1b1b1; background-color: #F5FFFA;text-align: right'>总合计:</td>" +
            "  <td class='text'  name='totalNumber2' id='totalNumber2' style='border: 1px solid #b1b1b1;text-align: left'></td>" +
            "  <td class='text'  name='totalAmount2' id='totalAmount2' style='border: 1px solid #b1b1b1;text-align: left'></td>" +
            "</tr>");
    }
}
$(document).ready(function () {
    BY121403.initDataGrid();
   /* BY121403.initTable();*/
})