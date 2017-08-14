/**
 * Created by wang_shuai on 2016/5/20.
 */
var SP17119201 = {

    init: function () {
        SP17119201.checkWayInfo();
        if ($("#flag").val() == null || $("#flag").val() == "") {
            $("#wayCode").val($("#maxCode").val());
        }


    },
    bindConfirmButton: function () {
        if (SP17119201.checkResult()) {
            var json = {};// 创建json 对象
            json[0] = {
                "wayCode": $("#wayCode").val(),
                "wayName": $("#wayName").val(),
                "waygradeStart": $("#startSup").val(),
                "waygradeEnd": "0",
                "waygradeCode": "0",
                "waygradeName": "超级大宗订单",
                "sellWayCode": "4",
                "waygradePercent": $("#balance0").val()
            };
            json[1] = {
                "wayCode": $("#wayCode").val(),
                "wayName": $("#wayName").val(),
                "waygradeStart": $("#start1").val(),
                <!--Modif for Bug#3345 at 2016/10/14 by ni_shaotang Start-->
                "waygradeEnd": $("#end1").val(),
                <!--Modif for Bug#3345 at 2016/10/14 by ni_shaotang End-->
                "waygradeCode": "1",
                "waygradeName": "大宗订单1级",
                "sellWayCode": "3",
                "waygradePercent": $("#balance1").val()
            };
            json[2] = {
                "wayCode": $("#wayCode").val(),
                "wayName": $("#wayName").val(),
                "waygradeStart": $("#start2").val(),
                "waygradeEnd": $("#end2").val(),
                "waygradeCode": "2",
                "waygradeName": "大宗订单2级",
                "sellWayCode": "3",
                "waygradePercent": $("#balance2").val()
            };
            json[3] = {
                "wayCode": $("#wayCode").val(),
                "wayName": $("#wayName").val(),
                "waygradeStart": $("#start3").val(),
                "waygradeEnd": $("#end3").val(),
                "waygradeCode": "3",
                "waygradeName": "大额订单3级",
                "sellWayCode": "2",
                "waygradePercent": $("#balance3").val()
            };
            json[4] = {
                "wayCode": $("#wayCode").val(),
                "wayName": $("#wayName").val(),
                "waygradeStart": $("#start4").val(),
                "waygradeEnd": $("#end4").val(),
                "waygradeCode": "4",
                "waygradeName": "大额订单4级",
                "sellWayCode": "2",
                "waygradePercent": $("#balance4").val()
            };
            json[5] = {
                "wayCode": $("#wayCode").val(),
                "wayName": $("#wayName").val(),
                "waygradeStart": $("#start5").val(),
                "waygradeEnd": $("#end5").val(),
                "waygradeCode": "5",
                "waygradeName": "大额订单5级",
                "sellWayCode": "2",
                "waygradePercent": $("#balance5").val()
            };
            json[6] = {
                "wayCode": $("#wayCode").val(),
                "wayName": $("#wayName").val(),
                "waygradeStart": $("#start6").val(),
                "waygradeEnd": $("#end6").val(),
                "waygradeCode": "6",
                "waygradeName": "大额订单6级",
                "sellWayCode": "1",
                "waygradePercent": $("#balance6").val()
            };
            json[7] = {
                "wayCode": $("#wayCode").val(),
                "wayName": $("#wayName").val(),
                "waygradeStart": $("#start7").val(),
                "waygradeEnd": $("#end7").val(),
                "waygradeCode": "7",
                "waygradeName": "标准订单7级",
                "sellWayCode": "1",
                "waygradePercent": $("#balance7").val()
            };
            json[8] = {
                "wayCode": $("#wayCode").val(),
                "wayName": $("#wayName").val(),
                "waygradeStart": $("#start8").val(),
                "waygradeEnd": $("#end8").val(),
                "waygradeCode": "8",
                "waygradeName": "标准订单8级",
                "sellWayCode": "1",
                "waygradePercent": $("#balance8").val()
            };
            json[9] = {
                "wayCode": $("#wayCode").val(),
                "wayName": $("#wayName").val(),
                "waygradeStart": $("#start9").val(),
                "waygradeEnd": $("#end9").val(),
                "waygradeCode": "9",
                "waygradeName": "标准订单9级",
                "sellWayCode": "1",
                "waygradePercent": $("#balance9").val()
            };
            var jsonStr = JSON.stringify(json);//  转成jsonStr
            if ($("#saveOrUpFlag").val() == null || $("#saveOrUpFlag").val() == "") {
                $('#main-content').postUrl(Main.contextPath + "/SP17119201/save", {"paramList": jsonStr}, function () {
                    $.alertMessage.info("保存成功");
                    $('#main-content').postUrl(Main.contextPath + "/SP171192/init");
                }, {refreshHtml: false});
            } else {
                $('#main-content').postUrl(Main.contextPath + "/SP17119201/update", {"paramList": jsonStr}, function () {
                    $.alertMessage.info("保存成功");
                    $('#main-content').postUrl(Main.contextPath + "/SP171192/init");
                }, {refreshHtml: false});
            }
            $.pdialog.close();
        }

    },
    checkWayInfo: function () {
        $("#SP17119201_CONFIRM").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/SP17119201/checkWayInfo", {
                    "wayName": $("#wayName").val(),
                    "wayCode": $("#wayCode").val()
                },
                function (data) {
                    if (data > 0) {
                        $.alertMessage.info("营销状态名称不能重复");
                        return false;
                    } else {
                        SP17119201.bindConfirmButton();
                    }
                }, {refreshHtml: false});
        });
    },
    validate: function (value) {
        if (!/^\d+$/.test(value)) {
            return true;
        }
        return false;
    },
    validateFolat: function (value) {
        if (!/^[0-9]+\.{0,1}[0-9]{0,6}$/.test(value)) {
            return true;
        }
        return false;
    },
    validateBig: function (start, end) {

        if (start == "" || end == "") {
            return true;
        }
        var startVal = parseInt(start);
        var endVal = parseInt(end);
        if (startVal > endVal) {
            return true;
        }
        return false;
    },
    validateSup: function (start, end, sup) {
        var startVal = parseInt(start);
        var endVal = parseInt(end);
        var sup = parseInt(sup);
        if (startVal > sup || endVal > sup) {
            return true;
        }
    },
    checkResult: function () {
        if ($("#wayName").val() == "") {
            $.alertMessage.info("请填写通道名称");
            return false;
        } else if ($("#startSup").val() == "") {
            $.alertMessage.info("请填写超级订单!");
            return false;
        } else if (SP17119201.validate($("#startSup").val())) {
            $.alertMessage.info("请填写超级订单的值不能为非整数!");
            return false;
        } else if (SP17119201.validateFolat($("#balance0").val())) {
            $.alertMessage.info("请填写超级订单其平衡系数不能为非数字!");
            return false;
        } else if ($("#start1").val() == "") {
            $.alertMessage.info("请填写1级订单!");
            return false;
        } else if (SP17119201.validate($("#start1").val())) {
            $.alertMessage.info("请填写1级订单的值不能为非整数!");
            return false;
        } else if (SP17119201.validateFolat($("#balance1").val())) {
            $.alertMessage.info("请填写1订单其平衡系数不能为非数字!");
            return false;
        } else if (SP17119201.validateBig($("#end1").val(), $("#startSup").val())) {
            $.alertMessage.info("1订单不能大于超级订单的值!");
            return false;
        }
        <!--Modif for Bug#3345 at 2016/10/14 by ni_shaotang Start-->
        var start1 = $("#start1").val();
        var end1 = $("#end1").val();
        var blance1 = $("#balance1").val();
        if (SP17119201.result(start1, end1, blance1, 1)) {
            var start2 = $("#start2").val();
            var end2 = $("#end2").val();
            var blance2 = $("#balance2").val();
            if (SP17119201.result(start2, end2, blance2, 2)) {
                var start3 = $("#start3").val();
                var end3 = $("#end3").val();
                var blance3 = $("#balance3").val();
                SP17119201.result(start3, end3, blance3, 3);
                if (SP17119201.result(start3, end3, blance3, 3)) {
                    var start4 = $("#start4").val();
                    var end4 = $("#end4").val();
                    var blance4 = $("#balance4").val();
                    if (SP17119201.result(start4, end4, blance4, 4)) {
                        var start5 = $("#start5").val();
                        var end5 = $("#end5").val();
                        var blance5 = $("#balance5").val();
                        if (SP17119201.result(start5, end5, blance5, 5)) {
                            var start6 = $("#start6").val();
                            var end6 = $("#end6").val();
                            var blance6 = $("#balance6").val();
                            if (SP17119201.result(start6, end6, blance6, 6)) {
                                var start7 = $("#start7").val();
                                var end7 = $("#end7").val();
                                var blance7 = $("#balance7").val();
                                if (SP17119201.result(start7, end7, blance7, 7)) {
                                    var start8 = $("#start8").val();
                                    var end8 = $("#end8").val();
                                    var blance8 = $("#balance8").val();
                                    if (SP17119201.result(start8, end8, blance8, 8)) {
                                        var start9 = $("#start9").val();
                                        var end9 = $("#end9").val();
                                        var blance9 = $("#balance9").val();
                                        if (SP17119201.result(start9, end9, blance9, 9)) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        <!--Modif for Bug#3345 at 2016/10/14 by ni_shaotang End-->
    },
    result: function (start, end, balance, i) {
        if (start == "" || end == "") {
            $.alertMessage.info("" + i + "级订单的上下限不能为空")
            return false;
        } else if ((start != "") || (end != "")) {
            if ((SP17119201.validate(start)) || (SP17119201.validate(end))) {
                $.alertMessage.info("" + i + "级订单的上下限不能为非整数!");
                return false;
            } else if (SP17119201.validateFolat(balance)) {
                $.alertMessage.info("" + i + "级订单的平衡系数不能为非数字!");
                return false;
            } else if (SP17119201.validateBig(start, end)) {
                $.alertMessage.info("" + i + "级订单的下限不能大于上限!");
                return false;
            } else if (SP17119201.validateSup(start, end, $("#startSup").val())) {
                $.alertMessage.info("" + i + "级订单上下限值不能超过超级订单值");
                return false;
            }
            <!--Modif for Bug#3345 at 2016/10/14 by ni_shaotang Start-->
            else
            //验证是否比上小
            if (i < 2) {
                if (end >= parseInt($("#startSup").val())) {
                    $.alertMessage.info("" + i + "级订单上限值必须小于超级订单值1");
                    return false;
                }
            } else if (i > 8) {
                if(end >= parseInt($("#start8").val())){
                    $.alertMessage.info("" + i + "级订单上限值必须小于8级下限订单值");
                    return false;
                }
            } else {
                if(end >= parseInt($("#start"+(i-1)).val())){
                    $.alertMessage.info("" + i + "级订单上限值必须小于"+(i-1)+"级下限订单值");
                    return false;
                }
            }

            <!--Modif for Bug#3345 at 2016/10/14 by ni_shaotang End-->
        }
        return true;
    }
}

$(document).ready(function () {
    //初始化调用
    SP17119201.init();
});