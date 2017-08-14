//收获时间
var receiveTimeCommon = [];
//销售对象
var salesTargetCommon = [];
//产品类别
var pdClassesCommon = [];
//产品二级类别
var pdMachiningCommom = [];
//收获地址
var receiveAddrArray = [];

$(function () {

    if (localStorage.enterFlg == "edit" || localStorage.enterFlg == "view") {
        $("#titleDiv").text("买家信息编辑 - 收货信息");
        $("#saveButtonDiv").text("保存");
    } else {
        $("#titleDiv").text("新买家注册 - 收货信息");
        $("#saveButtonDiv").text("下一步 3/4");
    }

    // 获取收货时间选项
    var flickerAPI = url + '/by/common/master';
    var paramData = {
        "client": "abcd",
        "auth": "xxxx",
        "loginid": "a124",
        "param": {"constantType": "ReceivePeriodType"}
    };
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                receiveTimeCommon = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });
    // 获取买家销售对象选项
    var flickerAPI = url + '/by/common/master';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"constantType": "SalesTarget"}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                salesTargetCommon = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });
    // 获取买家经营产品类别选项
    var flickerAPI = url + '/by/common/pdClass';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                pdClassesCommon = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });
    // 获取买家经营产品二级类别选项
    var flickerAPI = url + '/by/common/pdMachining';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                pdMachiningCommom = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });
    // 获取买家收货地址
    var flickerAPI = url + '/by/receiveAddr/findList';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId": localStorage.buyerId}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                receiveAddrArray = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });
    // 获取买家收货时间段
    var receiveTime = [];
    var flickerAPI = url + '/by/receiveTime/findList';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId": localStorage.buyerId}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                receiveTime = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });
    // 获取买家销售对象
    var salesTarget = [];
    var flickerAPI = url + '/by/salesTarget/findList';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId": localStorage.buyerId}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                salesTarget = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });
    // 获取买家产品类别
    var pdClasses = [];
    var flickerAPI = url + '/by/pdClassification/findList';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId": localStorage.buyerId}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                pdClasses = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });

    //收货地址显示
    if (receiveAddrArray.length > 0) {
        var receiveAddrStr = "";
        for (var i = 0; i < receiveAddrArray.length; i++) {
            receiveAddrStr = receiveAddrStr + "<div id = '" + receiveAddrArray[i].id + "'style='width: 80%;margin-left:35px;background-color:#F6F8FA;color:#CECECE;text-align:left;text-shadow:none;font-size: 17px;;height:40px;line-height:45px;' onclick='receiveAddrModify(this)'>";
            receiveAddrStr = receiveAddrStr + "收货地址：" + receiveAddrArray[i].receiveAddr;
            receiveAddrStr = receiveAddrStr + "</div>";
        }
        $("#receiveAddrDiv").append(receiveAddrStr);
    }

    //收货时间显示
    if (receiveTimeCommon.length > 0) {
        var receiveTimeStr = "";
        for (var i = 0; i < receiveTimeCommon.length; i++) {
            receiveTimeStr = receiveTimeStr + "<div>";
            receiveTimeStr = receiveTimeStr + "<input name='receiveTime" + receiveTimeCommon[i].constantValue + "' id='receiveTime" + receiveTimeCommon[i].constantValue + "' type='checkbox'>";
            receiveTimeStr = receiveTimeStr + "<label for='receiveTime" + receiveTimeCommon[i].constantValue + "'>" + receiveTimeCommon[i].constantName + "</label>";
            receiveTimeStr = receiveTimeStr + "</div>";
        }
        $("#receiveTimeDiv").append(receiveTimeStr);
        $("#receiveTimeDiv").trigger("create");
        $("input[type='checkbox']").checkboxradio("refresh");
    }
    if (receiveTime.length > 0) {
        for (var i = 0; i < receiveTime.length; i++) {
            $("#receiveTime" + receiveTime[i].recPerType).attr("checked", true).checkboxradio("refresh");
        }
    }

    //销售对象展示
    if (salesTargetCommon.length > 0) {
        var salesTargetStr = "";
        for (var i = 0; i < salesTargetCommon.length; i++) {
            salesTargetStr = salesTargetStr + "<div>";
            salesTargetStr = salesTargetStr + "<input name='salesTarget" + salesTargetCommon[i].constantValue + "' id='salesTarget" + salesTargetCommon[i].constantValue + "' type='checkbox'>";
            salesTargetStr = salesTargetStr + "<label for='salesTarget" + salesTargetCommon[i].constantValue + "'>" + salesTargetCommon[i].constantName + "</label>";
            salesTargetStr = salesTargetStr + "</div>";
        }
        $("#salesTargetDiv").append(salesTargetStr);
        $("#salesTargetDiv").trigger("create");
        $("input[type='checkbox']").checkboxradio("refresh");
    }
    if (salesTarget.length > 0) {
        for (var i = 0; i < salesTarget.length; i++) {
            $("#salesTarget" + salesTarget[i].salesTargetType).attr("checked", true).checkboxradio("refresh");
        }
    }

    //产品分类展示
    if (pdClassesCommon.length > 0) {
        var pdClassesStr = "";
        for (var i = 0; i < pdClassesCommon.length; i++) {
            var pdClassesCode = pdClassesCommon[i].classesCode;

            pdClassesStr = pdClassesStr + "<div><input name='pdClasses" + pdClassesCode + "' id='pdClasses" + pdClassesCode + "' type='checkbox'>";
            pdClassesStr = pdClassesStr + "<label for='pdClasses" + pdClassesCode + "'>" + pdClassesCommon[i].classesName + "</label>";
            pdClassesStr = pdClassesStr + "<div data-role=\"collapsible\">";
            pdClassesStr = pdClassesStr + "<h4>" + pdClassesCommon[i].classesName + "</h4>";
            for (var j = 0; j < pdMachiningCommom.length; j++) {
                var machiningCode = pdMachiningCommom[j].machiningCode;
                var classesCode = pdMachiningCommom[j].classesCode;
                if (pdClassesCode == classesCode) {
                    pdClassesStr = pdClassesStr + "<p><input name='machining" + classesCode + machiningCode + "' id='machining" + classesCode + machiningCode + "' type='checkbox'>";
                    pdClassesStr = pdClassesStr + "<label for='machining" + classesCode + machiningCode + "'>" + pdMachiningCommom[j].machiningName + "</label></p>";
                }
            }
            pdClassesStr = pdClassesStr + "</div></div>";
        }
        $("#pdClassesDiv").append(pdClassesStr);
        $("#pdClassesDiv").trigger("create");
        $("input[type='checkbox']").checkboxradio("refresh");
    }
    if (pdClasses.length > 0) {
        for (var i = 0; i < pdClasses.length; i++) {
            $("#pdClasses" + pdClasses[i].classCode).attr("checked", true).checkboxradio("refresh");
            if (!isNaN(pdClasses[i].machiningCodeU)) {
                var machinings = pdClasses[i].machiningCodeU.split("");
                for (var j = 0; j < machinings.length; j++) {
                    $("#machining" + pdClasses[i].classCode + machinings[j]).attr("checked", true).checkboxradio("refresh");
                }
            }
        }
    }
});
//保存数据
function buyerReceiveInfo() {
    if (receiveAddrArray.length == 0) {
        $("#message").css("display", "block");
        $("#message").text("请至少添加一个收货地址");
        return;
    }
//获取选择的收货时间并更新数据库
    var receiveTimeModifyData = [];
    var countRecSelected = 0;
    for (var i = 0; i < receiveTimeCommon.length; i++) {
        if ($("#receiveTime" + receiveTimeCommon[i].constantValue).is(":checked")) {
            var recPerType = receiveTimeCommon[i].constantValue;
            var timeDescribe = receiveTimeCommon[i].constantName;
            var receiveTimeModifyStr = {
                'buyerId': localStorage.buyerId,
                'recPerType': recPerType,
                'timeDescribe': timeDescribe
            };
            receiveTimeModifyData.push(receiveTimeModifyStr);
        } else {
            countRecSelected = countRecSelected + 1;
            var receiveTimeModifyStr = {'buyerId': localStorage.buyerId, 'recPerType': "", 'timeDescribe': ""};
            receiveTimeModifyData.push(receiveTimeModifyStr);
        }
    }
    if (countRecSelected == receiveTimeCommon.length) {
        $("#message").css("display", "block");
        $("#message").text("请至少选择一个收货时间段");
        return;
    } else {
        //更新买家收货时间
        var flickerAPI = url + '/by/receiveTime/update';
        var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": receiveTimeModifyData};
        $.ajax({
            type: "POST",
            async: false,
            url: flickerAPI,
            timeout: 60,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(paramData),
            success: function (data) {
                if (data.status == "F") {
                    alert(data.message);
                }
            },
            error: function () {
                alert("error");
            }
        });
    }
    //获取选择的销售对象并更新数据库
    var salesTargetModifyData = [];
    var countTargetSelected = 0;
    for (var i = 0; i < salesTargetCommon.length; i++) {
        if ($("#salesTarget" + salesTargetCommon[i].constantValue).is(":checked")) {
            var salesTargetType = salesTargetCommon[i].constantValue;
            var salesTargetName = salesTargetCommon[i].constantName;
            var salesTargetModifyStr = {
                'buyerId': localStorage.buyerId,
                'salesTargetType': salesTargetType,
                'salesTargetName': salesTargetName
            };
            salesTargetModifyData.push(salesTargetModifyStr);
        } else {
            countTargetSelected = countTargetSelected + 1;
            var salesTargetModifyStr = {'buyerId': localStorage.buyerId, 'salesTargetType': "", 'salesTargetName': ""};
            salesTargetModifyData.push(salesTargetModifyStr);
        }
    }
    if (countTargetSelected == salesTargetCommon.length) {
        $("#message").css("display", "block");
        $("#message").text("请至少选择一个销售对象");
        return;
    } else {
        //更新买家销售对象
        var flickerAPI = url + '/by/salesTarget/update';
        var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": salesTargetModifyData};
        $.ajax({
            type: "POST",
            async: false,
            url: flickerAPI,
            timeout: 60,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(paramData),
            success: function (data) {
                if (data.status == "F") {
                    alert(data.message);
                }
            },
            error: function () {
                alert("error");
            }
        });
    }
    //获取选择的产品类别并更新数据库
    var pdClassesData = [];
    var countClaSelected = 0;
    for (var i = 0; i < pdClassesCommon.length; i++) {
        if ($("#pdClasses" + pdClassesCommon[i].classesCode).is(":checked")) {
            var classesCode = pdClassesCommon[i].classesCode;
            var classesName = pdClassesCommon[i].classesName;
            var machining = "";
                for (var j = 0; j < pdMachiningCommom.length; j++) {
                    var machiningCode = pdMachiningCommom[j].machiningCode;
                    var pdClassesCode = pdMachiningCommom[j].classesCode;
                    if (pdClassesCode == classesCode) {
                    if ($("#machining" + classesCode + machiningCode).is(":checked")) {
                        machining += machiningCode;
                    }
                }
            }
            var pdClassesStr = {'buyerId': localStorage.buyerId, 'classCode': classesCode, 'className': classesName,"machiningCodeU":machining};
            pdClassesData.push(pdClassesStr);
        } else {
            countClaSelected = countClaSelected + 1;
        }
    }
    if (countClaSelected == pdClassesCommon.length) {
        $("#message").css("display", "block");
        $("#message").text("请至少选择一个销售产品");
        return;
    } else {
        var flickerAPI = url + '/by/pdClassification/update';
        var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": pdClassesData};
        $.ajax({
            type: "POST",
            async: false,
            url: flickerAPI,
            timeout: 60,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(paramData),
            success: function (data) {
                setTimeout(function () {
                    if (data.status == "S") {
                        if (localStorage.enterFlg == "edit") {
                            window.location = "BY121211.html";
                        } else {
                            window.location = "BY121209.html";
                        }
                    } else {
                        alert(data.message);
                    }
                }, 0);
            },
            error: function () {
                alert("error");
            }
        });
    }
}
//买家收货地址编辑/新增
function receiveAddrModify(obj) {
    if (obj.id == "receiveAddrAdd") {
        $("#enterFlag").val("add");
        $("#header").text("买家收货地址新增");
    } else {
        var addr = obj.innerText.substring(5, obj.innerText.length);
        $("#header").text("买家收货地址修改");
        $("#receiveAddr").val(addr);
        $("#receiveId").val(obj.id);
        $("#enterFlag").val("modify");
    }
    $("#backGroundDiv").css("display", "block");
    $('#receiveAddrEditDiv').css("display", "block");
}
//隐藏买家收货地址变更DIV
function receiveDivNone() {
    $("#backGroundDiv").css("display", "none");
    $("#receiveAddrEditDiv").css("display", "none");
}
//买家收货地址保存
function receiveAddrSave() {
    var flickerAPI;
    var paramData;
    var id = "";
    var receiveAddr;
    if ($("#receiveId").val()) {
        id = $("#receiveId").val();
    }
    receiveAddr = $("#receiveAddr").val();
    if (receiveAddr == "") {
        $("#errorMessage").css("display", "block");
        $("#errorMessage").text("请输入买家收货地址");
        return false;
    }
    if ($("#enterFlag").val() == "modify") {
        flickerAPI = url + '/by/receiveAddr/update';
        paramData = {
            "client": "abcd",
            "auth": "xxxx",
            "loginid": "a124",
            "param": [{"id": id, "buyerId": localStorage.buyerId, "receiveAddr": receiveAddr}]
        };
    } else {
        flickerAPI = url + '/by/receiveAddr/phoneSave';
        paramData = {
            "client": "abcd",
            "auth": "xxxx",
            "loginid": "a124",
            "param": {"buyerId": localStorage.buyerId, "receiveAddr": receiveAddr}
        };
    }

    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                if (id != "") {
                    $('#' + id).attr("name", receiveAddr);
                    $('#' + id).text("收货地址：" + receiveAddr);
                } else {
                    var receiveAddrStr = "<div id = '" + data.result.id + "'style='width: 80%;margin-left:35px;background-color:#F6F8FA;color:#CECECE;text-align:left;text-shadow:none;font-size: 17px;;height:40px;line-height:45px;'onclick='receiveAddrModify(this)'>";
                    receiveAddrStr = receiveAddrStr + "收货地址：" + data.result.receiveAddr;
                    receiveAddrStr = receiveAddrStr + "</div>";
                    $("#receiveAddrDiv").append(receiveAddrStr);
                    receiveAddrArray.push(data.result.receiveAddr);
                }
                $("#receiveAddr").val("");
                $("#receiveId").val("");
                $("#enterFlag").val("");
                receiveDivNone();
            }
        },
        error: function () {
            alert("error");
        }
    });
}
//返回前一个画面
function returnPage() {
    if (localStorage.enterFlg == "edit") {
        window.location = "BY121211.html";
    } else {
        window.location = "BY121207.html";
    }
}