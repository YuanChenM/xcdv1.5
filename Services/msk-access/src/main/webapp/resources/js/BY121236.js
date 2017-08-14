/**
 * Created by tao_zhifa on 2016/8/3.
 */
var markTools = [];
$(function(){
    if(localStorage.enterFlg == "eidt" || localStorage.enterFlg == "view"){
        $("#titleDiv").text("买家信息编辑 - 管控工具");
        $("#saveButtonDiv").text("保存");
    }else{
        $("#titleDiv").text("新买家注册 - 管控工具");
        $("#saveButtonDiv").text("下一步 6/8");
    }
    // 获取买家收货时间段
    var receiveTime = [];
    var flickerAPI = url + '/by/common/allTimesList';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param":{}};
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


    // 获取管控工具

    flickerAPI = url + '/by/getMarketingToolValues';
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
                markTools = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });

    var timeFirst =$("#timeFirst");
    timeFirst.html("");
    timeFirst.append("<option value=''>最早时间</option>")
    for(var i=0;i< receiveTime.length;i++){
        timeFirst.append("<option value="+receiveTime[i] + " >" + receiveTime[i]+"</option>");
        timeFirst.selectmenu("refresh")
    }

    var timeSecond =$("#timeSecond");
    timeSecond.html("");
    timeSecond.append("<option value=''>最晚时间</option>")
    for(var i=0;i< receiveTime.length;i++){
        timeSecond.append("<option value="+receiveTime[i] + " >" + receiveTime[i]+"</option>");
        timeSecond.selectmenu("refresh")
    }
    var telMarketingStartTime ;
    var telMarketingEndTime ;
    //管控工具显示
    if (markTools.length > 0) {
        var receivePayStr = "";
        receivePayStr = receivePayStr + "<table style='width:80%;'><tr>";
        for (var i = 0; i < markTools.length; i++) {
            if (markTools[i].isChecked == '1') {
                receivePayStr = receivePayStr + "<td style='width:10%;'><input name='receivePay" + markTools[i].marketingTools + "' id='payMethod" + markTools[i].marketingTools + "' type='checkbox' checked='checked'></td>";

                receivePayStr = receivePayStr + "<td style='width:15%;'><label for='receivePay" + markTools[i].setMarketingToolsName + "'>" + markTools[i].setMarketingToolsName + "</label></td>";
            } else {
                receivePayStr = receivePayStr + "<td style='width:10%;'><input name='receivePay" + markTools[i].marketingTools + "' id='payMethod" + markTools[i].marketingTools + "' type='checkbox' ></td>";
                receivePayStr = receivePayStr + "<td style='width:15%;'><label for='receivePay" + markTools[i].setMarketingToolsName + "'>" + markTools[i].setMarketingToolsName + "</label></td>";
            }

            telMarketingStartTime = markTools[i].telMarketingStartTime;
            telMarketingEndTime = markTools[i].telMarketingEndTime;
        }
        receivePayStr = receivePayStr + "</tr></table>";

        $("#MarketingTools").append(receivePayStr);
        $("#MarketingTools").trigger("create");
        $("input[type='checkbox']").checkboxradio("refresh");
    }
    $("#timeFirst").val(telMarketingStartTime);
    $("#timeSecond").val(telMarketingEndTime);
    $("#timeFirst").selectmenu("refresh");
    $("#timeSecond").selectmenu("refresh");


    /*var toolDatas= "";
    var flickerAPI = url + '/by/findToolToBuyerId';
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
                if(data.result != null){
                    toolDatas = data.result;
                    var tools = [];
                    tools = toolDatas.marketingTools.split(",");
                    for(var i=0;i<tools.length;i++){
                        if(tools[i]=="1"){
                            $("#phone").attr("checked","true");
                        }
                        if(tools[i]=="2"){
                            $("#wechat").attr("checked","true");
                        }
                        if(tools[i]=="3"){
                            $("#qq").attr("checked","true");
                        }
                    }
                    $("#timeFirst").val(toolDatas.telMarketingStartTime);
                    $("#timeSecond").val(toolDatas.telMarketingEndTime);

                    $("#timeFirst").selectmenu("refresh");
                    $("#timeSecond").selectmenu("refresh");
                    $("#phone").checkboxradio("refresh");
                    $("#wechat").checkboxradio("refresh");
                    $("#qq").checkboxradio("refresh");
                }
            }
        },
        error: function () {
            alert("error");
        }
    });
*/

});

//保存数据
function receiveAddrSave(){

        if($("#timeSecond option:selected").text() != null){
            if($("#timeFirst option:selected").text() > $("#timeSecond option:selected").text()){
                $("#errorMessage").css("display", "block");
                $("#errorMessage").text("最早时间大于最晚时间");
                return false;
            }
        }else
        if($("#timeFirst option:selected").text() != null){
            if($("#timeFirst option:selected").text() > $("#timeSecond option:selected").text()){
                $("#errorMessage").css("display", "block");
                $("#errorMessage").text("最早时间大于最晚时间");
                return false;
            }
        }
        if($("#timeFirst option:selected").text() == null){
            $("#errorMessage").css("display", "block");
            $("#errorMessage").text("最早时间为空");
            return false;
        }
    if($("#timeFirst option:selected").text() == "最早时间"){
        $("#timeFirst option:selected").text("");

    }
    if($("#timeSecond option:selected").text() == "最晚时间"){
        $("#timeSecond option:selected").text("");

    }

    //保存管理工具和时间
    var statTime = $("#timeFirst option:selected").text();
    var endTime = $("#timeSecond option:selected").text();
    var tool ="";

    for (var i = 0; i < markTools.length; i++) {
        if($("#payMethod" + markTools[i].marketingTools).is(":checked")){
            if(tool == ''){
                tool = markTools[i].marketingTools
            }else{
                tool = tool + "," + markTools[i].marketingTools;
            }
        }
    }
    var flickerAPI = url + '/by/saveTOOLToDataBase';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId": localStorage.buyerId,"marketingTools":tool,"telMarketingStartTime":statTime,"telMarketingEndTime":endTime  }};
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
                window.location = "BY121237.html";
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
        window.location = "BY121235.html";
    } else {
        window.location = "BY121235.html";
    }
}