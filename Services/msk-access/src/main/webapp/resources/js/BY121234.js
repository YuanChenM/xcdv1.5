/**
 * Created by tao_zhifa on 2016/8/2.
 */
//收获时间
var receiveTimeCommon = [];
//支付方式
var rpaymentCommon = [];
//存时间段数据
var earliestRecTime = [];


$(function (){
    if(localStorage.enterFlg == "eidt" || localStorage.enterFlg == "view"){
        $("#titleDiv").text("买家信息编辑 - 时间信息");
        $("#saveButtonDiv").text("保存");
    }else{
        $("#titleDiv").text("新买家注册 - 时间信息");
        $("#saveButtonDiv").text("下一步 4/8");
    }

    // 获取收货时间选项
    var flickerAPI = url + '/by/common/timesList';
    var paramData = {
        "client": "abcd",
        "auth": "xxxx",
        "loginid": "a124",
        "param": {"buyerId": localStorage.buyerId}
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
                receiveTimeCommon = data.result.recTimeList;
                earliestRecTime = data.result.earliestRecTime;
            }
        },
        error: function () {
            alert("error");
        }
    });


    // 获取买家支付方式
    flickerAPI = url + '/by/common/payList';
    paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId": localStorage.buyerId}};
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
                rpaymentCommon = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });

    //支付方式显示
    if (rpaymentCommon.length > 0) {
        var receivePayStr = "";
        receivePayStr = receivePayStr + "<table style='width:100%;'><tr>";
        for (var i = 0; i < rpaymentCommon.length;i++) {
            if(rpaymentCommon[i].isPayChecked =='1'){
                receivePayStr = receivePayStr + "<td style='width:10%;'><input name='receivePay" + rpaymentCommon[i].payMethod + "' id='payMethod" + rpaymentCommon[i].payMethod + "' type='checkbox' checked='checked'></td>";
                receivePayStr = receivePayStr + "<td style='width:15%;'><label for='receivePay" + rpaymentCommon[i].payMethod + "'>" + rpaymentCommon[i].payMethodName  + "</label></td>";
            }else{
                receivePayStr = receivePayStr + "<td style='width:10%;'><input name='receivePay" + rpaymentCommon[i].payMethod + "' id='payMethod" + rpaymentCommon[i].payMethod + "' type='checkbox' ></td>";
                receivePayStr = receivePayStr + "<td style='width:15%;'><label for='receivePay" + rpaymentCommon[i].payMethod + "'>" + rpaymentCommon[i].payMethodName  + "</label></td>";
            }
        }
        receivePayStr = receivePayStr + "</tr></table>";

        $("#receivePayDiv").append(receivePayStr);
        $("#receivePayDiv").trigger("create");
        $("input[type='checkbox']").checkboxradio("refresh");
    }

    // 获取买家收货时间段
    var receiveTime = [];
    flickerAPI = url + '/by/receiveTime/findList';
    paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId": localStorage.buyerId}};
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

    //收货时间显示
    if (receiveTimeCommon.length > 0) {
        var receiveTimeStr = "";
        for (var i = 0; i < receiveTimeCommon.length;) {
            receiveTimeStr = receiveTimeStr + "<div style='width: 100%;'><table><tr>";
            receiveTimeStr = receiveTimeStr + "<td style='width: 40%'> <input name='receiveTime' id='receiveTime" + receiveTimeCommon[i].recPerType + "' type='radio' onclick='bindCheckButton(\""+receiveTimeCommon[i].timeDescribe+"\")'  > ";
            receiveTimeStr = receiveTimeStr + "<label for='receiveTime" + receiveTimeCommon[i].recPerType + "'>" + receiveTimeCommon[i].timeDescribe + "</label></td>";
            i++;
            receiveTimeStr = receiveTimeStr + "<td style='width: 40%'> <input name='receiveTime' id='receiveTime" + receiveTimeCommon[i].recPerType + "' type='radio' onclick='bindCheckButton(\""+receiveTimeCommon[i].timeDescribe+"\")'  > ";
            receiveTimeStr = receiveTimeStr + "<label for='receiveTime" + receiveTimeCommon[i].recPerType + "'>" + receiveTimeCommon[i].timeDescribe + "</label></td>";
            i++;
            receiveTimeStr = receiveTimeStr + "</tr></table></div>";
        }
        $("#receiveTimeDiv").append(receiveTimeStr);
        $("#receiveTimeDiv").trigger("create");
        $("input[type='checkbox']").checkboxradio("refresh");
    }
    if (receiveTime.length > 0) {
        for (var i = 0; i < receiveTime.length; i++) {
            $("#receiveTime" + receiveTime[i].recPerType).attr("checked", true).checkboxradio("refresh");
        }
        //最早收货时间
        if(earliestRecTime.length >0){
            var TheEarliest =$("#TheEarliest");
            TheEarliest.html("");
            for(var i=0;i< earliestRecTime.length;i++){
                TheEarliest.append("<option value="+earliestRecTime[i] + ">" + earliestRecTime[i]+"</option>");
                TheEarliest.selectmenu("refresh")
            }
        }

        //最晚收货时间
        if(earliestRecTime.length >0){
            var TheLatest = $("#TheLatest");
            TheLatest.html("");
            for (var i = 0; i < earliestRecTime.length; i++) {
                TheLatest.append("<option value=" + earliestRecTime[i] + ">" + earliestRecTime[i] + "</option>");
                TheLatest.selectmenu("refresh")
            };
        }
    }




    // 通过buyerId查询值是否存在
    flickerAPI = url + '/by/findRecTimeByBuyerId';
    paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId": localStorage.buyerId}};
    var recDatas = "";
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
                if(data.result != null){
                    recDatas = data.result;
                    for(var i=0;i< receiveTimeCommon.length;i++){
                        if(receiveTimeCommon[i].habitRecTime == recDatas.habitRecTime){
                            $("#receiveTime" + receiveTimeCommon[i].recPerType).attr("checked","true");
                            $("#receiveTime" + receiveTimeCommon[i].recPerType).checkboxradio("refresh");
                            bindCheckButton(receiveTimeCommon[i].timeDescribe);
                        }
                    }
                    $("#TheLatest").val(recDatas.latestRecTime);
                    $("#TheEarliest").val(recDatas.earliestRecTime);
                    $("#TheLatest").selectmenu("refresh");
                    $("#TheEarliest").selectmenu("refresh");
                }
            }
        },
        error: function () {
            alert("error");
        }
    });



});

//保存数据
function buyerReceiveInfo() {

        if($("#TheEarliest option:selected").text() != "24:00"){
            if( $("#TheEarliest option:selected").text() > $("#TheLatest option:selected").text()){
                $("#errorMessage").css("display", "block");
                $("#errorMessage").text("最早时间大于最晚时间");
                return false;
            }else{
                $("#errorMessage").css("display", "none");
            }
        }


        if($("#TheEarliest option:selected").text() != "24:00"){
            if($("#TheEarliest option:selected").text() != null){
                if($("#TheEarliest option:selected").text() > $("#TheLatest option:selected").text()){
                    $("#errorMessage").css("display", "block");
                    $("#errorMessage").text("最早时间大于最晚时间");
                    return false;
                }
            }
        }
        if(($("#TheEarliest option:selected").text() == "00:30" || $("#TheEarliest option:selected").text() == "01:00") && $("#TheLatest option:selected").text()=="24:00"){
            $("#errorMessage").css("display", "block");
            $("#errorMessage").text("最早时间大于最晚时间");
            return false;
        }
        if($("#TheEarliest option:selected").text() == null){
            $("#errorMessage").css("display", "block");
            $("#errorMessage").text("最早时间为空");
            return false;
        } else {
            $("#errorMessage").css("display", "none");
        }

//获取选择的收货时间并更新数据库
    var countRecSelected = 0;
    var habitRecTime = null;
    for (var i = 0; i < receiveTimeCommon.length; i++) {
        if ($("#receiveTime" + receiveTimeCommon[i].recPerType).is(":checked") ==true) {
            var recPerType = receiveTimeCommon[i].recPerType;
            var timeDescribe = receiveTimeCommon[i].timeDescribe;
            habitRecTime = receiveTimeCommon[i].habitRecTime;
            var receiveTimeModifyStr = {
                'buyerId': localStorage.buyerId,
                'recPerType': recPerType,
                'timeDescribe': timeDescribe
            };

        }
    }
    var payType ="";
    for(var j =0;j<rpaymentCommon.length;j++){
        if ($("#payMethod" + rpaymentCommon[j].payMethod).is(":checked") ==true) {
            if(payType != ""){
                payType = payType+","+  rpaymentCommon[j].payMethod;
            }else{
                payType = payType + rpaymentCommon[j].payMethod;
            }
        }
    }
    var deliveryTimeAndPayModifyStr ={
        'buyerId': localStorage.buyerId,
        'paymentType':payType,
        'earliestRecTime':$("#TheEarliest option:selected").text(),
        'latestRecTime':$("#TheLatest option:selected").text(),
        'habitRecTime':habitRecTime
    }
    if (countRecSelected == receiveTimeCommon.length) {
        $("#message").css("display", "block");
        $("#message").text("请至少选择一个收货时间段");
        return;
    } else {
/*        //更新买家收货时间
        var flickerAPI = url + '/by/receiveTime/update';
        var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": receiveTimeModifyStr};
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
        });*/
        //更新买家收货时间进入base info
        var flickerAPI = url + '/by/deliveryTimeAndPay/update';
        var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": deliveryTimeAndPayModifyStr};
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
                    window.location = "BY121235.html";
                }
            },
            error: function () {
                alert("error");
            }
        });
    }
}

//买家习惯时间下拉菜单
 function bindCheckButton(timeDescribe){

     var flickerAPI = url + '/by/common/oneTime';
     paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"timeDescribe": timeDescribe}};
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
                 earliestRecTime = data.result;
                 // 最晚收货时间
                 var TheLatest = $("#TheLatest");
                 TheLatest.html("");
                    for (var i = 0; i < earliestRecTime.length; i++) {
                        TheLatest.append("<option value=" + earliestRecTime[i] + ">" + earliestRecTime[i] + "</option>");
                        TheLatest.selectmenu("refresh")
                    };

                 //最早收货时间
                 var TheEarliest =$("#TheEarliest");
                 TheEarliest.html("");
                 for(var i=0;i< earliestRecTime.length;i++){
                     TheEarliest.append("<option value="+earliestRecTime[i] + ">" + earliestRecTime[i]+"</option>");
                     TheEarliest.selectmenu("refresh")
                 }
                 TheLatest.val(earliestRecTime[earliestRecTime.length-1]);
                 TheLatest.selectmenu("refresh")
             };
         },
         error: function () {
             alert("error");
         }
     });
 }

//返回前一个画面
function returnPage() {
    window.location = "BY121233.html";
}