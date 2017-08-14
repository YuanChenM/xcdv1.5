/**
 * 菜场新增
 * Created by zhou_yajun on 2016/7/13.
 */
var BY121409 = {
    BY121409_Grid: null,
    saveUApproveButton: "BY121409_SAVEUAPPROVE",
    saveApproveButton: "BY121409_SAVEAPPROVE",
    addButton: "BY121409_ADD",
    initDataGrid: function () {
        BY121409.BY121409_Grid = $("#BY121409_Grid").grid({
            actionHandler: BY121409.actionHandler
        });
        this.changeHandler();
        this.marketSaveHandler();
        this.marketByAddHandler();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "edit") {
            $.pdialog.open("菜场商户信息", Main.contextPath + "/BY121410/init/" + rowdata.marketId + "/" + rowdata.id, {
                    width: 350,
                    height: 350
                }
            )
        }

        if (coltype == "delete") {
            $.alertMessage.confirm("请确认要删除商户信息吗？", function () {
                $('#main-content').postUrl(Main.contextPath + "/BY121410/delete/" + rowdata.id, rowdata, function (data) {
                    if (data == "delete") {
                        $.alertMessage.close();
                        BY121409.BY121409_Grid.fnDraw();
                    }
                }, {refreshHtml: false})
            });
        }
    },
    changeHandler: function () {
        //物流区变更获取相应的城市信息
        $("#lgcsAreaCode").change(function () {
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            if (lgcsAreaCode == "") {
                $("#cityCode").html("");
                $("#cityCode").append("<option value=''>--请选择--</option>");
                $("#districtCode").html("");
                $("#districtCode").append("<option value=''>--请选择--</option>");
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/BY121409/lgcsAreaChange/" + lgcsAreaCode, null,
                function (data) {
                    $("#cityCode").html("");
                    $("#cityCode").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#cityCode").append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                    });

                    //根据第一个城市编码获取城市下的区县
                    $("#districtCode").html("");
                    $('#main-content').postUrl(Main.contextPath + "/BY121409/cityChange/" + data[0].cityCode, null,
                        function (data) {
                            $("#districtCode").append("<option value=''>--请选择--</option>");
                            $.each(data, function (i, item) {
                                $("#districtCode").append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
                            });
                        }, {refreshHtml: false});

                }, {refreshHtml: false});
        });
        //城市变更获取相应的区县信息
        $("#cityCode").change(function () {
            var cityCode = $("#cityCode option:selected").val();

            if (cityCode == "") {
                $("#districtCode").html("");
                $("#districtCode").append("<option value=''>--请选择--</option>");
                return false;

            }
            $("#districtCode").html("");
            $('#main-content').postUrl(Main.contextPath + "/BY121409/cityChange/" + cityCode, null,
                function (data) {
                    $("#districtCode").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#districtCode").append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
                    });
                }, {refreshHtml: false});
        });
    },
    //菜场基本信息保存
    marketSaveHandler: function () {
        $("#" + BY121409.saveUApproveButton).click(function () {
                if ($("#BY121409SaveForm").validateForm()) {
                    if (!BY121409.showErrorMessage()) {
                        return false;
                    }
                    var marketName = $("#marketName").val();
                    var merchantTotalNo = $("#merchantTotalNo").val();
                    var targetBuyer = $("#targetBuyer").val();
                    var targetAnnualTurnover = $("#targetAnnualTurnover").val();
                    var ntargetBuyer = $("#ntargetBuyer").val();
                    var ntargetAnnualTurnover = $("#ntargetAnnualTurnover").val();
                    var reg = /^\d{1,8}(\.\d{1,2})?$/;
                    if (!reg.test(targetAnnualTurnover) && targetAnnualTurnover != '') {
                        $.alertMessage.warn("目标买家年交易额输入有错误，请查证!");
                        return false;
                    } else if (!reg.test(ntargetAnnualTurnover) && ntargetAnnualTurnover != '') {
                        $.alertMessage.warn("非目标买家年交易额输入错误，请查证!");
                        return false;
                    } else {
                        $("#marketStatus").val("0");
                        BY121409.basicInfoSave();
                    }

                }
            }
        );
        $("#" + BY121409.saveApproveButton).click(function () {
                if ($("#BY121409SaveForm").validateForm()) {
                    if (!BY121409.showErrorMessage()) {
                        return false;
                    }
                    var marketName = $("#marketName").val();
                    var merchantTotalNo = $("#merchantTotalNo").val();
                    var targetBuyer = $("#targetBuyer").val();
                    var targetAnnualTurnover = $("#targetAnnualTurnover").val();
                    var ntargetBuyer = $("#ntargetBuyer").val();
                    var ntargetAnnualTurnover = $("#ntargetAnnualTurnover").val();
                    var reg = /^\d{1,8}(\.\d{1,2})?$/;
                    if (!reg.test(targetAnnualTurnover) && targetAnnualTurnover != '') {
                        $.alertMessage.warn("目标买家年交易额输入错误，请查证!")
                        return false;
                    } else if (!reg.test(ntargetAnnualTurnover) && ntargetAnnualTurnover != '') {
                        $.alertMessage.warn("非目标买家年交易额输入有错误，请查证!")
                        return false;
                    } else {
                        $("#marketStatus").val("1");
                        BY121409.basicInfoSave();
                    }

                }

            }
        );
    },
    //新增菜场下的买家
    marketByAddHandler: function () {
        $("#" + BY121409.addButton).click(function () {
            var marketId = $("#marketId").val();
            var id = 0;
            if (marketId == "add") {
                $.alertMessage.info("请先新增菜场基本信息。", function () {
                    $.alertMessage.close();
                });
            } else {
                $.pdialog.open("菜场商户信息", Main.contextPath + "/BY121410/init/" + marketId + "/" + id, {
                        width: 350,
                        height: 350
                    }
                )
            }
        });
    },
    //菜场基本信息保存
    basicInfoSave: function () {
        var lgcsAreaName = $("#lgcsAreaCode option:selected").text();
        var cityName = $("#cityCode option:selected").text();
        var districtName = $("#districtCode option:selected").text();
        $("#lgcsAreaName").val(lgcsAreaName);
        $("#cityName").val(cityName);
        $("#districtName").val(districtName);
        /*if (!BY121409.showErrorMessage()) {
         return false;
         }*/
        var formData = getFormData($("#BY121409SaveForm"));

        $('#main-content').postUrl($("#BY121409SaveForm").attr("action"), formData, function (data) {
            if (data.errorMessage != null && data.errorMessage != "") {
                $.alertMessage.info(data.errorMessage, function () {
                    $.alertMessage.close();
                });
                return false;
            }
            ;
            if (data.actionType == "modify") {
                $("#oldResearchPhase").val(data.researchPhase);
                $.alertMessage.info("菜场基本信息编缉保存成功。", function () {
                    $.alertMessage.close();
                });
            } else {
                $("#marketId").val(data.marketId);
                $("#byMarkerId").val(data.marketId);
                $("#oldResearchPhase").val(data.researchPhase);
                $.alertMessage.info("菜场基本信息新增保存成功。", function () {
                    $.alertMessage.close();
                });
            }
        }, {refreshHtml: false});
    },

    showErrorMessage: function () {
        var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
        var cityCode = $("#cityCode option:selected").val();
        var districtCode = $("#districtCode option:selected").val();
        var radiationRange = $("#radiationRange option:selected").val();
        var researchPhase = $("#researchPhase option:selected").val();
        if (lgcsAreaCode == "") {
            $.alertMessage.info("物流区不能为空 !");
            return false;
        }
        if (cityCode == "") {
            $.alertMessage.info("地区不能为空 !");

            return false;
        }
        if (districtCode == "") {
            $.alertMessage.info("区县不能为空 !");
            return false;
        }

        if (radiationRange == "") {
            $.alertMessage.info("辐射范围不能为空 !");
            return false;
        }
        if (researchPhase == "") {
            $.alertMessage.info("市场调研阶段不能为空 !");
            return false;
        }
        return true;


    }

}

$(document).ready(function () {
    BY121409.initDataGrid();
})