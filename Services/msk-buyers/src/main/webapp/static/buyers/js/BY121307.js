/**
 * 买家详细信息
 * Created by marshall on 16/3/9.
 */

var BY121307 = {
    BY121307Grid: null,
    buyerId: $("#buyerId").val(),
    addButton: "BY121307_ADD",
    saveButton: "BY121307_SAVE",
    fileUploadButton: "BY121307_UPLOAD",
    formId: "BY121307Form",

    initDataGrid: function () {
        BY121307.BY121307Grid = $('#BY121307_Grid').grid({
            actionHandler: BY121307.actionHandler
        });
        $(".buyerClaId").css("display", "none");
        this.bindSelectChange();
        this.bindAddButton();
        this.bindSaveButton();
        this.keyDownEvent();
        //this.buyerPdCla();
        this.checkMarket();

    },

    redundance: function () {
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
        $("#paymentTypeName").html("");
        var paymentTypeName = $("#paymentType option:selected").text();
        $("#paymentTypeName").val(paymentTypeName);
        $("#marketingsStatusName").html("");
        var marketingsStatusName = $("#marketingsStatus option:selected").text();
        $("#marketingsStatusName").val(marketingsStatusName);


        $("#licDes").html("");

        var licDes = $("#licName option:selected").text();
        $("#licDes").val(licDes);
        $("#legalLicDes").html("");
        var legalLicDes = $("#legalLicType option:selected").text();
        $("#legalLicDes").val(legalLicDes);
    },
    bindSelectChange: function () {
        $("#licName").prepend("<option value='' selected>--请选择--</option>");
        $("#legalLicType").prepend("<option value='' selected>--请选择--</option>");

        $("#lgcsAreaCode").change(function () {
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            if (lgcsAreaCode == "") {
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
                BY121307.findMarket(superiorType, lgcsAreaCode, cityCode, '');
            }
        });
        $("#districtCode").change(function () {
            var superiorType = $("#superiorType option:selected").val();
            var buyerTypeId = $("#buyerTypeId option:selected").val();
            if (superiorType == '02') {
                var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
                var cityCode = $("#cityCode option:selected").val();
                var districtCode = $("#districtCode option:selected").val();
                if (districtCode == "") {
                    $("#superiorId").html("");
                    $("#superiorId").append("<option value=''>--请选择--</option>");
                    return false;
                }
                $("#superiorQua").val("");
                BY121307.findMarket(superiorType, lgcsAreaCode, cityCode, districtCode);
            }
            if (superiorType == '05' && buyerTypeId == "01") {
                var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
                var cityCode = $("#cityCode option:selected").val();
                var districtCode = $("#districtCode option:selected").val();
                if (districtCode == "") {
                    $("#superiorId").html("");
                    $("#superiorId").append("<option value=''>--请选择--</option>");
                    return false;
                }
                $("#superiorQua").val("");
                BY121307.findMarket(superiorType, lgcsAreaCode, cityCode, districtCode);
            }
        });
        $("#superiorType").change(function () {
            var superiorType = $("#superiorType option:selected").val();
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            var cityCode = $("#cityCode option:selected").val();
            var districtCode = $("#districtCode option:selected").val();
            BY121307.findMarket(superiorType, lgcsAreaCode, cityCode, districtCode);

            BY121307.HideOrShow();
            BY121307.secondTypeHideOrShow();
            BY121307.secondTypeSec(superiorType);
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
            } else if (superiorType == "02" || superiorType == "05") {
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
                if (buyerType == "01") {
                    $(".isFoodMarket").attr("checked", true);
                    $(".isFoodMarket").attr("disabled", true);
                    BY121307.checkMarket();
                }

            } else {
                $('#isFoodMarket').hide();
            }
        });
    },
    findMarket: function (superiorType, lgcsAreaCode, cityCode, districtCode) {
        $("#superiorDec").css("display", "block");
        $("#superiorId").css("display", "block");
        $("#superiorQuaDec").css("display", "block");
        $("#superiorQua").css("display", "block");
        debugger;
        if (superiorType == "01") {
            $("#superiorDec").html("<span style='color:red;display: inline; vertical-align: sub'>*&nbsp;</span>批发市场:");
            $("#superiorQuaDec").html("<span style='color:red ;display: inline; vertical-align: sub'>*&nbsp;</span>批发市场等级:");
            $("#superiorQua").val("");

            $(".td_css").removeAttr("style");
            $(".buyerClaId").css("display", "none");
            //$("#buyerPdMac").css("display","block");
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
            $(".buyerClaId").removeAttr("style");
            $(".td_css").css("display", "none");
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
            $(".buyerClaId").removeAttr("style");
            $(".td_css").css("display", "none");
            //$("#buyerPdMac").css("display","none");
            $("#superiorDec").css("display", "none");
            $("#superiorId").css("display", "none");
            $("#superiorQuaDec").css("display", "none");
            $("#superiorQua").css("display", "none");
        }
    },
    bindAddButton: function () {
        $("#" + BY121307.addButton).click(function () {
            var editType = "BY121307Add";
            $.pdialog.open("买家雇员信息新增画面", Main.contextPath + "/BY12130401/init/" + editType, {
                    width: 450,
                    height: 500
                },
                {"buyerId": BY121307.buyerId}
            )
        });
    },
    bindSaveButton: function () {
        $("#" + BY121307.saveButton).click(function () {
            if ($("#BY121307Form").validateForm()) {
                //电话、手机号码
                var regPhone = /^1[34578]\d{9}$/;
                var number = /^\d+$/; //整数
                var re = /^\d+(\.\d{1,2})?$/;//整数，二位小数
                var positiveInteger = /^([1-9]\d*|[0]{1,1})$/;//正整数
                var storeArea = $("#storeArea").val();
                var employeesNum = $("#employeesNum").val();
                var licNumber = $("#licNumber").val();
                var legalLicNumber = $("#legalLicNumber").val();
                var legalName = $("#legalName").val();
                var busiTel = $("#busiTel").val();
                var buyerWebsite = $("#buyerWebsite").val();
                if (busiTel != "") {
                    if (!regPhone.test(busiTel) && !busiTel.isPhone()) {
                        $.alertMessage.info("营业电话输入有误，请查证！", function () {
                            $.alertMessage.close();
                        });
                        return false;
                    }
                }
                /* if(employeesNum!=""){
                 if(!employeesNum.isInteger()){
                 $.alertMessage.info("员工人数只能为正整数！");
                 return false;
                 }
                 if(!employeesNum.isIntegerRange(-1,2147483648)){
                 $.alertMessage.info("员工人数超出范围限制！");
                 return false;
                 }
                 }*/
                /*            if(licNumber!=""){
                 if(!licNumber.isInteger()){
                 $.alertMessage.info("营业执照号码为数字！");
                 return false;
                 }

                 }*/
                /*            if(legalLicNumber!=""){
                 if(!legalLicNumber.isInteger()){
                 $.alertMessage.info("法定代表人证件号码为数字！");
                 return false;
                 }

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
                if ($("input[type='checkbox']").is(':checked') == false) {
                    $.alertMessage.confirm("产品分类不能为空,请选择!");
                    return false;
                }
                var superiorType = $("#superiorType option:selected").val();
                //参数校验
                var errorText = BY121307.checkData(superiorType);

                if (errorText != "") {
                    $.alertMessage.confirm(errorText, function () {
                        $.alertMessage.close();
                    });
                    return false;
                }

                BY121307.redundance();
                var formData = getFormData($("#" + BY121307.formId));
                debugger;
                $('#main-content').postUrl($("#" + BY121307.formId).attr("action"), formData, function (data) {
                    if (data == 'S') {
                        $("#" + BY121307.fileUploadButton).click();
                    } else if (data == 'F') {
                        $.alertMessage.info("买家信息保存失败！");
                    } else {
                        $.alertMessage.info(data);
                    }
                }, {refreshHtml: false});
            }

        });
    },
    keyDownEvent: function () {
        /*        $("#employeesNum").keydown(function(){
         var keyCode = event.keyCode;
         if ((keyCode >= 48 && keyCode <= 57))
         {
         event.returnValue = true;
         } else {
         event.returnValue = false;
         }
         });*/
    },
    buyerPdCla: function () {

        $(".buyerCk").click(function () {
            var superiorType = $("#superiorType option:selected").val();
            var classesCode = $(this).val();

            var editType = "add";

            if (superiorType == "01") {
                if ($(this).is(":checked")) {
                    var machiningCode = $("#buyerPdMac" + classesCode).val();

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
        })
        $(".buyerCk_a").click(function () {


            var editType = "add";

            var classesCode = $(this).prev().prev().val();
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
    },
    HideOrShow: function () {

        var superiorType = $("#superiorType option:selected").val();
        if (superiorType == "01") {
            $(".buyerCk_a").each(function () {

                $(this).show();

            });
            $(".pd01").each(function () {

                $(this).hide()
            })
        } else {
            $(".buyerCk_a").each(function () {
                $(this).hide();
            });
            $(".pd01").each(function () {
                $(this).show()
            })
        }

    },
    secondTypeHideOrShow: function () {
        var superiorType = $("#superiorType option:selected").val();
        if (superiorType == "06" || superiorType == "05") {
            $("#secondType").show();
            $("#secondTypeSelect").show();
        } else {
            $("#secondType").hide();
            $("#secondTypeSelect").hide();
        }
    },
    checkData: function (superiorType) {

        var lgcsAreaCode = $("#lgcsAreaCode").val();
        var cityCode = $().val("#cityCode");
        var districtCode = $("#districtCode").val();
        var superiorId = $("#superiorId").val();
        //二级分类
        var buyerTypeId = $("#buyerTypeId").val();

        var storeArea = $("#storeArea").val();

        var errorText = "";
        if (superiorType == '') {
            return errorText = "请选择买家类型";
        }
        //校验买家类型
        if (superiorType == '01') {
            errorText = BY121307.checkCommon(lgcsAreaCode, cityCode, districtCode);
            if (superiorId == "") {
                errorText = "请选择批发市场";
            }
        } else if (superiorType == '02') {
            errorText = BY121307.checkCommon(lgcsAreaCode, cityCode, districtCode);
            if (superiorId == "") {
                errorText = "请选择菜场";
            }
            //document.getElementById()
            //todo
        } else if (superiorType == '06' || superiorType == '05') {
            errorText = BY121307.checkCommon(lgcsAreaCode, cityCode, districtCode);
            if (buyerTypeId == "") {
                errorText = "请选择二级类型";
            }
        } else {
            errorText = BY121307.checkCommon(lgcsAreaCode, cityCode, districtCode);
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
        var isFoodMarket = $(".isFoodMarket").is(":checked");
        if (isFoodMarket) {
            //$(".isFoodMarket").click(function(){
            if ($("#superiorType option:selected").val() == '05') {
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

}

var BY121307CallBack = function (data) {
    var fileServerId = eval(data);
    var buyerId = $("#buyerId").val();
    var picLicensePath = fileServerId.busLicPicName;
    var picOrgStructurePath = fileServerId.orgCertificatePicName;
    var picTaxRegistrationPath = fileServerId.taxCertificatePicName;
    var picFoodCirculationPath = fileServerId.foodCertificatePicName;
    var picCertPath = fileServerId.legalCertificatePicName;
    if (picLicensePath == undefined) {
        picLicensePath = "";
    }
    if (picOrgStructurePath == undefined) {
        picOrgStructurePath = "";
    }
    if (picTaxRegistrationPath == undefined) {
        picTaxRegistrationPath = "";
    }
    if (picFoodCirculationPath == undefined) {
        picFoodCirculationPath = "";
    }
    if (picCertPath == undefined) {
        picCertPath = "";
    }

    $("#main-content").postUrl(Main.contextPath + "/BY121307/saveFileServerId", {
        buyerId: buyerId,
        picLicensePath: picLicensePath,
        picOrgStructurePath: picOrgStructurePath,
        picTaxRegistrationPath: picTaxRegistrationPath,
        picFoodCirculationPath: picFoodCirculationPath,
        picCertPath: picCertPath
    }, function (data) {
        if (data == "F") {
            $.alertMessage.info("证照信息保存失败!");
        }
    });
};


$(document).ready(function () {
    // 初始化调用
    BY121307.initDataGrid();
    BY121307.HideOrShow();
    BY121307.secondTypeHideOrShow();
    checkClaCheckBoxs();
    checkMacCheckBoxs();

});
function checkClaCheckBoxs() {

    $("input[name='buyerPdCla']").click(function () {
        $('#checkTable').find('input[type=checkbox]').not(this).attr("checked", false);
    })


};

function checkMacCheckBoxs() {
    $("input[name='buyerPdMac']").click(function () {
        //var bylClassCode = $(this).attr("bylClassCode");
        $('#checkTable').find('input[type=checkbox]').not(this).attr("checked", false);
        //$('#buyerPdCla' + bylClassCode).prop('checked', true);
    })
}