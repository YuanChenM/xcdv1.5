var marketId = "";
var marketFlag = "";
$(function(){
    if(localStorage.terMarketId){
        marketId = localStorage.terMarketId;
        marketFlag = "terminal";
    }else if(localStorage.fodMarketId){
        marketId = localStorage.fodMarketId;
        marketFlag = "food";
    }
    //获取选择的批发市场/菜场信息
    findMarketInfo(marketId,marketFlag);
    //获取批发市场或菜场中的买家信息
    var flickerAPI = url+'/by/buyer/findBuyerByMarketId';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"marketId": marketId}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            var buyerInfoList = data.result;
            showBuyerInfo(buyerInfoList);
        },
        error:function(){
            alert("error");
        }
    });
});
//显示买家信息
function showBuyerInfo(buyerInfoList){
    var buyerInfoDiv = $("#buyerInfoDiv");
    buyerInfoDiv.html("");
    if(buyerInfoList != null && buyerInfoList.length > 0){
        for(var i = 0;i < buyerInfoList.length; i++){
            var str = "<div id='"+buyerInfoList[i].buyerId+"' style='width:80%;margin-left:20px;height:200px;border:1px #CCCCCC solid;font-size:17px;background-color:#F6F8FA;color:#CCCCCC' onclick='buyerInfoModify(this);'>";
            str = str + "<div>买家编码："+ buyerInfoList[i].buyerCode+"</div>";
            str = str + "<div>买家名称："+ buyerInfoList[i].buyerName+"</div>";
            str = str + "<div>买家地址："+ buyerInfoList[i].buyerAddr+"</div>";
            str = str + "<div>买家类型："+ buyerInfoList[i].superiorName+"</div>";
            str = str + "<div>店铺号："+ buyerInfoList[i].storeNo+"</div>";
            str = str + "<div>营业电话："+ buyerInfoList[i].busiTel+"</div>";
            str = str + "<div>在线状态："+ buyerInfoList[i].marketingsStatusName+"</div>";
            str = str + "<div>注册来源："+ buyerInfoList[i].registerSourceName+"</div>";
            str = str + "</div>";
            buyerInfoDiv.append(str);
        }
    }else{
        var str = "<div style='color:#FF0000;text-align:center;font-size:17px;'>暂时没有符合条件的买家信息</div>"
        buyerInfoDiv.append(str);
    }
}
//买家信息编辑
function buyerInfoModify(buyer){
    var buyerId = buyer.id;
    localStorage.buyerId = buyerId;
    localStorage.enterFlg = "view";
    window.location = "BY12120501.html";
}
//获取批发市场或菜场信息
function findMarketInfo(marketId,marketFlag){
    var flickerAPI = "";
    var paramData = "";
    if(marketFlag == "terminal"){
        $("#currentName").text("当前选择的批发市场");
        flickerAPI = url+'/by/condition/findMarketTerminal';
        paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"terMarketId":marketId}};
    }else if(marketFlag == "food"){
        $("#currentName").text("当前选择的菜场");
        flickerAPI = url+'/by/condition/findMarketFood';
        paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"fodMarketId":marketId}};
    }
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "S"){
                var marketList = data.result;
                if(marketList[0].marketLevelName){
                    $("#currentMarket").text(marketList[0].marketName + " " +marketList[0].marketLevelName);
                }else{
                    $("#currentMarket").text(marketList[0].marketName + " " +marketList[0].marketTypeName);
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
}
//返回前画面
function returnPage(){
    if(marketFlag == "terminal"){
        window.location = "BY12120401.html";
    }else{
        window.location = "BY12120402.html";
    }
}