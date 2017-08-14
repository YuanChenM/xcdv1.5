/**
 * 买家详细信息
 * Created by marshall on 16/3/9.
 */
var baseBuyerBasic = {
    basicEditButtonId: "BY121304_BASIC_EDIT",
    basicSaveButtonId: "BY121304_BASIC_SAVE",
    buyerTypeEditButtonId: "BY121304_BUYERTYPE_EDIT",
    buyerTypeSaveButtonId: "BY121304_BUYERTYPE_SAVE",
    accountSeeButtonId: "BY121304_BASIC_SEEACCOUNT",
    formId: "basicEditForm",
    buyerTypeFormId: "buyerTypeForm",
    initDataGrid: function () {
        this.bindbuyerTypeIdChage();
        this.bindEditButton();
        this.bindAccountSeeButton();
        this.bindSaveButton();
        this.bindSelectChange();
        this.checkMarket();
        this.selectPdMac();
    },
    bindEditButton: function () {
        $("#" + baseBuyerBasic.basicEditButtonId).click(function () {
            $("#showTable").css("display", "none");
            $("#editTable").css("display", "");
        });
        $("#" + baseBuyerBasic.buyerTypeEditButtonId).click(function () {
            $("#buyerTypeShow").css("display", "none");
            $("#buyerTypeEdit").css("display", "");
        });
    },
    bindbuyerTypeIdChage: function () {

        $("#buyerTypeId").change(function () {
            var superiorType = $("#superiorType option:selected").val();
            var buyerType = $("#buyerTypeId option:selected").val();
            if (superiorType == "05") {
                if (buyerType == "01") {
                    $(".isFoodMarket").attr("checked", true);
                    $(".isFoodMarket").attr("disabled", true);
                    BY121307.checkMarket();
                }
            }
        })
    },
    bindAccountSeeButton: function () {
        $("#" + baseBuyerBasic.accountSeeButtonId).click(function () {
            $.pdialog.open("重置账号密码", Main.contextPath + "/by/baseBuyerBasicInfo/initAccountPwd", {
                    resizable: false,
                    width: 500,
                    height: 300
                },
                {
                    buyerId: $("#buyerId").val(),
                    telNo: $("#telNo").val(),
                    accountName: $("#accountName").val(),
                    accountPass: $("#accountPass").val()
                });
        });
    },
    bindSaveButton: function () {
        $("#" + baseBuyerBasic.basicSaveButtonId).click(function () {
            if ($("#basicEditForm").validateForm()) {
                var re = /^\d+(\.\d{1,2})?$/;//整数，二位小数
                var positiveInteger = /^([1-9]\d*|[0]{1,1})$/;//正整数
                var storeArea = $("#storeArea").val();
                var employeesNum = $("#employeesNum").val();
                /*            if(storeArea!=""){
                 if(!re.test(storeArea)){
                 $.alertMessage.info("店铺面积只能为数字或二位小数！");
                 return false;
                 }else if(storeArea.length>10){
                 $.alertMessage.info("店铺面积数字长度不能超过十位！");
                 return false;
                 }
                 }*/
                /*            if(employeesNum!=""){
                 if(!positiveInteger.test(employeesNum)){
                 $.alertMessage.info("员工人数只能为正整数！");
                 return false;
                 }else if(employeesNum.length>9){
                 $.alertMessage.info("员工人数数字长度不能超过9位！");
                 return false;
                 }
                 }*/
                $("#paymentTypeName").html("");
                var paymentTypeName = $("#paymentType option:selected").text();
                $("#paymentTypeName").val(paymentTypeName);
                $("#marketingsStatusName").html("");
                var marketingsStatusName = $("#marketingsStatus option:selected").text();
                $("#marketingsStatusName").val(marketingsStatusName);

                $($("#" + baseBuyerBasic.formId)).attr("action", Main.contextPath + "/by/baseBuyerBasicInfo/buyerBasicUpdate/");
                var validator = mainValidation($("#" + baseBuyerBasic.formId));
                var isValid = validator.form();

                if (isValid) {
                    var formData = getFormData($("#" + baseBuyerBasic.formId));
                    $('#main-content').postUrl($("#" + baseBuyerBasic.formId).attr("action"), formData, function (data) {
                        if (data == 'F') {
                            $.alertMessage.info("该买家名称已存在，不可编辑！");
                        } else if (data == 'T') {
                            $.alertMessage.info("该买家名称和其他账号相同，不可编辑！");
                        }
                    }, {refreshHtml: false});
                }
                ;
            }
        });

        $("#" + baseBuyerBasic.buyerTypeSaveButtonId).click(function () {

            $("#lgcsAreaName").val("");
            var lgcsAreaName = $("#lgcsAreaCode option:selected").text();
            $("#lgcsAreaName").val(lgcsAreaName);
            $("#cityName").val("");
            var cityName = $("#cityCode option:selected").text();
            $("#cityName").val(cityName);
            $("#districtName").val("");
            var districtName = $("#districtCode option:selected").text();
            $("#districtName").val(districtName);
            $("#superiorName").val("");
            var superiorName = $("#superiorType option:selected").text();
            $("#superiorName").val(superiorName);

            $($("#" + baseBuyerBasic.buyerTypeFormId)).attr("action", Main.contextPath + "/by/baseBuyerBasicInfo/buyerTypeUpdate/");
            var validator = mainValidation($("#" + baseBuyerBasic.buyerTypeFormId));
            var isValid = validator.form();

            var superiorType = $("#superiorType option:selected").val();

            var errorText = baseBuyerBasic.checkData(superiorType);

            if (errorText != "") {
                $.alertMessage.confirm(errorText, function () {
                    $.alertMessage.close();
                });
                return false;
            }
            var flag = false;
            /*$("input[type='checkbox']").each(function(index,element){
             if($(element).attr("name") == "buyerPdCla") {
             if ($(element)[0].checked) {
             flag= true;
             }
             }
             });
             if(!flag){
             $.alertMessage.confirm("请选择销售产品", function () {
             $.alertMessage.close();
             });
             return false;
             }*/
            //选择菜场check
            if ($(".isFoodMarket").is(":checked")) {
                if ($("#superiorId option:selected").val() == "") {
                    $.alertMessage.confirm("请选择菜场", function () {
                        $.alertMessage.close();
                    });
                    return false;
                }
            }
            debugger;
            var secondTypeSelect = $("#secondTypeSelect").css("display");
            if (secondTypeSelect == 'none') {
                $("#superiorSubType").val("");
                $("#superiorSubName").val("");
            }
            if (isValid) {
                var formData = getFormData($("#" + baseBuyerBasic.buyerTypeFormId));
                $('#main-content').postUrl($("#" + baseBuyerBasic.buyerTypeFormId).attr("action"), formData, {refreshHtml: false});
            }
            ;

        });
    },
    bindSelectChange: function () {
        $("#lgcsAreaCode").change(function () {
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            if (lgcsAreaCode == "") {
                $("#cityCode").html("");
                $("#cityCode").append("<option value=''>--请选择--</option>");
                $("#districtCode").html("");
                $("#districtCode").append("<option value=''>--请选择--</option>");
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/lgcsAreaChange/" + lgcsAreaCode, null,
                function (data) {
                    $("#cityCode").html("");
                    $("#cityCode").append("<option value=''>--请选择--</option>");
                    $("#districtCode").html("");
                    $("#districtCode").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#cityCode").append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                    });
                }, {refreshHtml: false});
        });

        $("#cityCode").change(function () {
            var cityCode = $("#cityCode option:selected").val();
            if (cityCode == "") {
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/cityChange/" + cityCode, null,
                function (data) {
                    $("#districtCode").html("");
                    $("#districtCode").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#districtCode").append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
                    });
                }, {refreshHtml: false});
            var superiorType = $("#superiorType option:selected").val();
            if (superiorType == '01') {
                var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
                var cityCode = $("#cityCode option:selected").val();
                $("#superiorQua").val("");
                baseBuyerBasic.findMarket(superiorType, lgcsAreaCode, cityCode, '');
            }
        });

        $("#districtCode").change(function () {
            var superiorType = $("#superiorType option:selected").val();
            if (superiorType == '02') {
                var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
                var cityCode = $("#cityCode option:selected").val();
                var districtCode = $("#districtCode option:selected").val();
                if (districtCode == "") {
                    return false;
                }
                $("#superiorQua").val("");
                baseBuyerBasic.findMarket(superiorType, lgcsAreaCode, cityCode, districtCode);
            }
        });
        $("#superiorType").change(function () {
            var superiorType = $("#superiorType option:selected").val();
            if (superiorType == "") {
                return false;
            }
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            var cityCode = $("#cityCode option:selected").val();
            var districtCode = $("#districtCode option:selected").val();
            $("#superiorDec").css("display", "block");
            $("#superiorId").css("display", "block");
            $("#superiorQuaDec").css("display", "block");
            $("#superiorQua").css("display", "block");
            baseBuyerBasic.findMarket(superiorType, lgcsAreaCode, cityCode, districtCode);
            baseBuyerBasic.secondTypeHideOrShow();
            baseBuyerBasic.secondTypeSec(superiorType);
            if (superiorType == '01') {
                $("#distriType").css("display", "");
                $("#otherType").css("display", "none");
            } else {
                $("#distriType").css("display", "none");
                $("#otherType").css("display", "");
            }
        });

        $("#superiorId").change(function () {
            var superiorType = $("#superiorType option:selected").val();
            var superiorId = $("#superiorId option:selected").val();
            $("#superiorQua").val("");
            if (superiorId == "") {
                return false;
            }
            if (superiorType == "01") {
                $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/findMarketTermial/" + superiorId, null,
                    function (data) {
                        if (data != null) {
                            $("#superiorQua").val(data.marketLevelName);
                        }
                    }, {refreshHtml: false});
            } else if (superiorType == "02" || (( superiorType == '05') && $(".isFoodMarket").is(":checked"))) {
                $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/findMarketFood/" + superiorId, null,
                    function (data) {
                        if (data != null) {
                            $("#superiorQua").val(data.marketTypeName);
                        }
                    }, {refreshHtml: false});
            }
        });
        $("#buyerTypeId").change(function () {
            var superiorType = $("#superiorType option:selected").val();
            var buyerType = $("#buyerTypeId option:selected").val();
            $("#superiorSubName").val($("#buyerTypeId option:selected").text());
            if (superiorType == "05" && buyerType == "01") {
                $('#isFoodMarket').show();
            } else {
                $('#isFoodMarket').hide();
            }
        });
    },
    findMarket: function (superiorType, lgcsAreaCode, cityCode, districtCode) {
        if (superiorType == "01") {
            $("#superiorDec").html("<span style='color:red;display: inline; vertical-align: sub'>*&nbsp;</span>批发市场:");
            $("#superiorQuaDec").html("<span style='color:red;display: inline; vertical-align: sub'>*&nbsp;</span>批发市场等级:");
            $("#superiorQua").val("");
            $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/findMarketTermialList/", {
                    "lgcsAreaCode": lgcsAreaCode,
                    "cityCode": cityCode,
                    "districtCode": ''
                },
                function (data) {
                    $("#superiorId").html("");
                    $("#superiorId").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#superiorId").append("<option value='" + item.terMarketId + "'>" + item.marketName + "</option>");
                    });
                }, {refreshHtml: false});
        } else if (superiorType == "02" || (( superiorType == '05') && $(".isFoodMarket").is(":checked"))) {
            $("#superiorDec").html("<span style='color:red;display: inline; vertical-align: sub'>*&nbsp;</span>菜场:");
            $("#superiorQuaDec").html("<span style='color:red;display: inline; vertical-align: sub'>*&nbsp;</span>菜场类型:");
            $("#superiorQua").val("");
            $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/findMarketFoodList/", {
                    "lgcsAreaCode": lgcsAreaCode,
                    "cityCode": cityCode,
                    "districtCode": districtCode
                },
                function (data) {
                    $("#superiorId").html("");
                    $("#superiorId").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#superiorId").append("<option value='" + item.fodMarketId + "'>" + item.marketName + "</option>");
                    });
                }, {refreshHtml: false});
        } else {
            $("#superiorDec").text("");
            $("#superiorId").html("");
            $("#superiorQuaDec").text("");
            $("#superiorQua").val("");
            $("#superiorDec").css("display", "none");
            $("#superiorId").css("display", "none");
            $("#superiorQuaDec").css("display", "none");
            $("#superiorQua").css("display", "none");
        }
    },
    checkData: function (superiorType) {
        var lgcsAreaCode = $("#lgcsAreaCode").val();
        var cityCode = $("#cityCode").val();
        var districtCode = $("#districtCode").val();
        var superiorId = $("#superiorId").val();
        //二级分类
        var buyerTypeId = $("#buyerTypeId").val();

        var errorText = "";
        if (superiorType == "") {
            errorText = "请选择买家类型";
            return errorText;
        }
        //校验买家类型
        if (superiorType == '01') {
            errorText = baseBuyerBasic.checkCommon(lgcsAreaCode, cityCode, districtCode);
            if (superiorId == "") {
                errorText = "请选择批发市场";
            }
        } else if (superiorType == '02') {
            errorText = baseBuyerBasic.checkCommon(lgcsAreaCode, cityCode, districtCode);
            if (superiorId == "") {
                errorText = "请选择菜场";
            }
        } else if (superiorType == '05' || superiorType == '06') {
            errorText = baseBuyerBasic.checkCommon(lgcsAreaCode, cityCode, districtCode);
            if (buyerTypeId == "") {
                errorText = "请选择二级类型";
            }
        } else {
            errorText = baseBuyerBasic.checkCommon(lgcsAreaCode, cityCode, districtCode);
        }

        return errorText;
    },
    checkCommon: function (lgcsAreaCode, cityCode, districtCode) {
        var errorText = "";
        if (lgcsAreaCode == "") {
            errorText = "请选择物流区";
        }
        if (cityCode == "") {
            errorText = "请选择地区(城市)";
        }
        if (districtCode == "") {
            errorText = "请选择区(县)";
        }
        if (superiorDec == "") {
            errorText = "请选择批发市场";
        }

        return errorText;
    },
    checkMarket: function () {
        $(".isFoodMarket").click(function () {
            if ($(this).is(":checked") && $("#superiorType option:selected").val() == '05') {
                $("#superiorDec").html("<span style='color:red;display: inline; vertical-align: sub'>*&nbsp;</span>菜场:");
                $("#superiorQuaDec").html("<span style='color:red;display: inline; vertical-align: sub'>*&nbsp;</span>菜场类型:");
                $("#superiorQua").val("");
                $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/findMarketFoodList/", {
                        "lgcsAreaCode": $("#lgcsAreaCode option:selected").val(),
                        "cityCode": $("#cityCode option:selected").val(),
                        "districtCode": $("#districtCode option:selected").val()
                    },
                    function (data) {
                        $("#superiorId").html("");
                        $("#superiorId").append("<option value=''>--请选择--</option>");
                        $.each(data, function (i, item) {
                            $("#superiorId").append("<option value='" + item.fodMarketId + "'>" + item.marketName + "</option>");
                        });
                    }, {refreshHtml: false});
                $("#superiorDec").css("display", "block");
                $("#superiorId").css("display", "block");
                $("#superiorQuaDec").css("display", "block");
                $("#superiorQua").css("display", "block");
            } else {
                $("#superiorDec").text("");
                $("#superiorId").html("");
                $("#superiorQuaDec").text("");
                $("#superiorQua").val("");
                $("#superiorDec").css("display", "none");
                $("#superiorId").css("display", "none");
                $("#superiorQuaDec").css("display", "none");
                $("#superiorQua").css("display", "none");
            }
        })
    },
    secondTypeHideOrShow: function () {
        var superiorType = $("#superiorType option:selected").val();
        if (superiorType == "05" || superiorType == "06") {
            $("#secondType").show();
            $("#secondTypeSelect").show();
        } else {
            $("#secondType").hide();
            $("#secondTypeSelect").hide();
        }
    },
    secondTypeSec: function (superiorType) {
        //二级分类
        $("#superiorSubName").val();
        $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/findBuyerList/" + superiorType, null,
            function (data) {
                $("#buyerTypeId").html("");
                $("#buyerTypeId").append("<option value=''>--请选择--</option>");
                $.each(data, function (i, item) {
                    $("#buyerTypeId").append("<option value='" + i + "'>" + item + "</option>");
                });
            }, {refreshHtml: false});
        var buyerType = $("#buyerTypeId option:selected").val();
        if (superiorType == "05" && buyerType == "01") {
            $('#isFoodMarket').show();
        } else {
            $('#isFoodMarket').hide();
        }
    },
    selectPdMac: function () {
        $(".selectPdCla").click(function () {
            var superiorType = $("#superiorType").val();
            var classesCode = $(this).val();
            if (superiorType == "01") {
                if ($(this).is(":checked")) {
                    var machiningCode = $("#buyerPdMac" + classesCode).val();
                    var editType = "add";

                    if (machiningCode != "") {
                        editType = "edit";
                    }

                    $.pdialog.open("二级产品信息", Main.contextPath + "/BY121307/searchPdMachin/" + editType, {
                            width: 450,
                            height: 200
                        },
                        {"classesCode": classesCode, "machiningCode": machiningCode}
                    )
                } else {
                    $("#buyerPdMac" + classesCode).val("");
                }
            }
        });

        $(".buyerCk_a").click(function () {

            var editType = "add";
            var classesCode = $(this).prev().val();
            var machiningCode = $("#buyerPdMac" + classesCode).val();

            if (machiningCode != "") {
                editType = "edit";
            }

            $.pdialog.open("二级产品信息", Main.contextPath + "/BY121307/searchPdMachin/" + editType, {
                    width: 450,
                    height: 200
                },
                {"classesCode": classesCode, "machiningCode": machiningCode}
            )

        })
    }
}
$(document).ready(function () {
    // 初始化调用
    baseBuyerBasic.initDataGrid();
    baseBuyerBasic.secondTypeHideOrShow();
});