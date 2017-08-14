/**
 * Created by tao_zhifa on 2016/8/3.
 */
//销售对象
var salesTargetCommon = [];

$(function(){

    if(localStorage.enterFlg == "eidt" || localStorage.enterFlg == "view"){
        $("#titleDiv").text("买家信息编辑 - 买家销售对象设置");
        $("#saveButtonDiv").text("保存");
    }else{
        $("#titleDiv").text("新买家注册 - 买家销售对象");
        $("#saveButtonDiv").text("下一步 7/8");
    }

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
});

//保存数据
function buyerReceiveInfo() {
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
                if (data.status == "S") {
                    if (localStorage.enterFlg == "edit") {
                        window.location = "BY121211.html";
                    } else {
                        window.location = "BY121209.html";
                    }
                }
            },
            error: function () {
                alert("error");
            }
        });
    }

}
//返回前一个画面
function returnPage() {
    if (localStorage.enterFlg == "edit") {
        window.location = "BY121236.html";
    } else {
        window.location = "BY121236.html";
    }
}