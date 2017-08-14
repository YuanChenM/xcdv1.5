$(function () {

    var localPosition = "当前位置:" + localStorage.cityName + "市" + localStorage.districtName;
    $("#localPosition").text(localPosition);
    if (localStorage.enterFlg == "edit" || localStorage.enterFlg == "view") {
        $("#titleDiv").text("买家信息编辑 - 基础资料");
        $("#saveButtonDiv").text("保存");
    } else {
        $("#titleDiv").text("新买家注册 - 基础资料");
        $("#saveButtonDiv").text("下一步 1/8");
    }
    // 获取支付方式下拉框
    var PaymentMethod;
    var flickerAPI = url + '/by/common/master';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"constantType": "PaymentMethod"}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            PaymentMethod = data.result;
        },
        error: function () {
            alert("error");
        }
    });
    //// 获取上线状态下拉框
    //var MarketingStatus;
    //var flickerAPI = url+'/by/common/master';
    //var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"constantType": "MarketingsStatus"}};
    //$.ajax({
    //    type : "POST",
    //    async:false,
    //    url:flickerAPI,
    //    timeout:60,
    //    dataType:'json',
    //    contentType:"application/json",
    //    data:JSON.stringify(paramData),
    //    success:function(data){
    //        MarketingStatus = data.result;
    //    },
    //    error:function(){
    //        alert("error");
    //    }
    //});

    // 显示买家的详细信息
    var flickerAPI = url + '/by/buyerInfo/findDetail';
    var buyerId = localStorage.buyerId;
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId": buyerId}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            var payType = $("#payType");
            payType.html("");

            //marketingsStatus.html("");
            //if(data.status == "F"){
            //    for(var i = 0; i< PaymentMethod.length; i++){
            //        payType.append("<option value="+PaymentMethod[i].constantValue+">"+PaymentMethod[i].constantName + "</option>")
            //    }
            //    for(var i = 0; i< MarketingStatus.length; i++){
            //        marketingsStatus.append("<option value="+MarketingStatus[i].constantValue+">"+MarketingStatus[i].constantName + "</option>")
            //    }
            //    payType.selectmenu("refresh");
            //    marketingsStatus.selectmenu("refresh");
            //    return;
            //}

            var basicInfo = data.result;
            $("#buyerName").val(basicInfo.buyerName);
            $("#buyerAddr").val(basicInfo.buyerAddr);
            $("#buyerType").val(basicInfo.superiorName);

            findMarketList(basicInfo.superiorType, basicInfo.superiorId, basicInfo.superiorQua);

            //生成支付方式下拉框
            for (var i = 0; i < PaymentMethod.length; i++) {
                if (PaymentMethod[i].constantValue == basicInfo.paymentMethod) {
                    payType.append("<option selected value=" + PaymentMethod[i].constantValue + ">" + PaymentMethod[i].constantName + "</option>")
                } else {
                    payType.append("<option value=" + PaymentMethod[i].constantValue + ">" + PaymentMethod[i].constantName + "</option>")
                }
            }
            payType.selectmenu("refresh");
            $("#buyerWebsite").val(basicInfo.buyerWebsite);
            $("#buyerWechat").val(basicInfo.buyerWechat);
            $("#buyerQq").val(basicInfo.buyerQq);
            $("#buyerSingleWechat").val(basicInfo.buyerSingleWechat);
            $("#storeNo").val(basicInfo.storeNo);
            $("#storeArea").val(basicInfo.storeArea);
            $("#busiTel").val(basicInfo.busiTel);
            $("#employeesNum").val(basicInfo.employeesNum);
            $("#planOrderGap").val(basicInfo.planOrderGap);
            $("#planOrderNum").val(basicInfo.planOrderNum);
            $("#actualOrderGap").val(basicInfo.actualOrderGap);
            $("#actualOrderNum").val(basicInfo.actualOrderNum);
            $("#marketingsStatus").val(basicInfo.marketingsStatus);
            $("#marketingsStatusName").val(basicInfo.marketingsStatusName);
            $("marketType").val(basicInfo.superiorIdName);
            $("marketLevelName").val(basicInfo.superiorQua);

            //生成上线状态下拉框
            /* for(var i = 0; i< MarketingStatus.length; i++){
             if(MarketingStatus[i].constantValue == basicInfo.marketingsStatus){
             marketingsStatus.append("<option selected value="+MarketingStatus[i].constantValue+">"+MarketingStatus[i].constantName + "</option>")
             } else {
             marketingsStatus.append("<option value="+MarketingStatus[i].constantValue+">"+MarketingStatus[i].constantName + "</option>")
             }
             }
             marketingsStatus.selectmenu("refresh");*/
        },
        error: function () {
            alert("error");
        }
    });
    var marketingsStatus = $("#marketingsStatus").val();
    var marketingsStatusName = $("#marketingsStatusName").val();
    var marketingsStatusDiv = $("#marketingsStatusDiv").val();
    /*    if( marketingsStatus == ""){
     $("#marketingsStatusName").hide();
     $("#marketingsStatusDiv").hide();
     }*/

    if (localStorage.enterFlg != "edit" && localStorage.enterFlg != "view") {
        $("#marketingsStatusName").css("display", "none");
        $("#marketingsStatusNameDiv").css("display", "none");
        $("#marketingsStatus").css("display", "none");
        $("#marketingsStatusDiv").css("display", "none");
    }
});


//更新买家基本信息
function buyerBasicInfo() {

    var basicInfo;
    var superiorId;
    var flickerAPIs = url + '/by/buyerInfo/findDetail';
    var buyerId = localStorage.buyerId;
    var paramDatas = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId": buyerId}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPIs,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramDatas),
        success: function (data) {
            basicInfo = data.result;
            superiorId = basicInfo.superiorId;
        },
        error: function () {
            alert("error");
        }
    });

    var flickerAPI = url + '/by/buyerInfo/update';
    var buyerId = localStorage.buyerId;
    var buyerName = $("#buyerName").val();
    /*    if(buyerName == ""){
     var message = "请输入买家全名";
     errorDisplay(message);
     return false;
     }*/
    var storeNo = $("#storeNo").val();
    /*    if(storeNo == ""){
     var message = "请输入买家店铺号";
     errorDisplay(message);
     return false;
     }*/
    var storeArea = $("#storeArea").val();
    var busiTel = $("#busiTel").val();
    /*    if(busiTel == ""){
     var message = "请输入买家店铺营业电话或负责人电话";
     errorDisplay(message);
     return false;
     }*/
    var buyerAddr = $("#buyerAddr").val();
    /*    if(buyerAddr == ""){
     var message = "请输入买家店铺地址";
     errorDisplay(message);
     return false;
     }*/
    var superiorQua = $("#marketLevelName").val();
    var superiorName = $("#buyerType").val();
    var payType = $("#payType option:selected").val();
    var buyerWebsite = $("#buyerWebsite").val();
    var buyerWechat = $("#buyerWechat").val();
    var buyerQq = $("#buyerQq").val();
    var buyerSingleWechat = $("#buyerSingleWechat").val();
    var employeesNum = $("#employeesNum").val();
    var planOrderGap = $("#planOrderGap").val();
    var planOrderNum = $("#planOrderNum").val();
    var actualOrderGap = $("#actualOrderGap").val();
    var actualOrderNum = $("#actualOrderNum").val();
    var marketingsStatus = $("#marketingsStatus").val();
    var marketingsStatusName = $("marketingsStatusName").val();
    var paramData = {
        "client": "abcd",
        "auth": "xxxx",
        "loginid": "a124",
        "param": {
            "buyerId": buyerId,
            "buyerName": buyerName,
            "buyerAddr": buyerAddr,
            "paymentType": payType,
            "buyerWebsite": buyerWebsite,
            "buyerWechat": buyerWechat,
            "buyerSingleWechat": buyerSingleWechat,
            "buyerQq": buyerQq,
            "storeNo": storeNo,
            "storeArea": storeArea,
            "busiTel": busiTel,
            "employeesNum": employeesNum,
            "planOrderGap": planOrderGap,
            "planOrderNum": planOrderNum,
            "actualOrderGap": actualOrderGap,
            "actualOrderNum": actualOrderNum,
            "marketingsStatus": marketingsStatus,
            "superiorName":superiorName,
            "superiorQua":superiorQua,
            "superiorId":superiorId
        }
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
            setTimeout(function () {
                if (data.status == "S") {
                    if (localStorage.enterFlg == "edit") {
                        window.location = "BY121211.html";
                    } else {
                        window.location = "BY121207.html";
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

//查询批发市场数据
function findMarketList(buyerType, marketId, marketLevel) {
    //查询批发市场
    var flickerAPI = "";
    var paramData = "";

    $("#marketName").css("display", "");
    $("#marketTypeDiv").css("display", "");
    $("#marketLevel").css("display", "");
    $("#marketLevelNameDiv").css("display", "");
    $("#marketLevelNameDiv").val("");

    if (buyerType == "01") {
        $("#marketName").text("批发市场");
        $("#marketLevel").text("批发市场等级");
        flickerAPI = url + '/by/condition/findMarketTerminal';
        paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"terMarketId": marketId}};
    } else if (buyerType == "02") {
        $("#marketName").text("选择菜场");
        $("#marketLevel").text("菜场等级");
        flickerAPI = url + '/by/condition/findMarketFood';
        paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"fodMarketId": marketId}};
    } else {
        $("#marketName").css("display", "none");
        $("#marketTypeDiv").css("display", "none");
        $("#marketLevel").css("display", "none");
        $("#marketLevelNameDiv").css("display", "none");
        return false;
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
                marketList = data.result;
                if (marketList.length > 0) {
                    for (var i = 0; i < marketList.length; i++) {
                        if (buyerType == "01") {
                            if (marketList[i].terMarketId == marketId) {
                                $("#marketType").val(marketList[i].marketName);
                                break;
                            }
                        } else if (buyerType == "02") {
                            if (marketList[i].fodMarketId == marketId) {
                                $("#marketType").val(marketList[i].marketName);
                                break;
                            }
                        }
                    }
                    $("#marketLevelName").val(marketLevel);
                }
            }
        },
        error: function () {
            alert("error");
        }
    });
}

//显示错误信息
function errorDisplay(message) {
    $("#errorMessage").css("display", "block");
    $("#errorMessage").text(message);
}
//返回前一个画面
function returnPage() {
    window.location = "BY12120501.html";
}