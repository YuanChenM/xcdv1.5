$(function(){
    var localLgcsArea = localStorage.lgcsAreaName + "物流区所有批发市场";
    var localCity = localStorage.cityName + "市所有批发市场";
    var localDistrict = localStorage.cityName + "市" + localStorage.districtName + "所有批发市场";
    $("#currentLgcsAreaLabel").text(localLgcsArea);
    $("#currentCityLabel").text(localCity);
    $("#currentDistrictLabel").text(localDistrict);
});
/**
 * 批发市场查询画面
 */
function searchButtonClick(){
    // 根据条件查询买家信息
    var flickerAPI = url+'/by/condition/findMarketTerminal';
    var conditionText = $("#searchParam").val();
    var lgcsAreaCode = "";
    var cityCode = "";
    var districtCode = "";
    if($("#currentLgcsArea").is(":checked")){
        lgcsAreaCode = localStorage.lgcsAreaCode;
    }
    if($("#currentCity").is(":checked")){
        lgcsAreaCode = localStorage.lgcsAreaCode;
        cityCode = localStorage.cityCode;
    }
    if($("#currentDistrict").is(":checked")){
        lgcsAreaCode = localStorage.lgcsAreaCode;
        cityCode = localStorage.cityCode;
        districtCode = localStorage.districtCode;
    }
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"marketName": conditionText,"lgcsAreaCode":lgcsAreaCode,"cityCode":cityCode,"districtCode":districtCode}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            $("#marketTerminalDiv").html("");
            $("#marketTerminalOtherDiv").html("");
            $("#totalCount").text("0家");
            $("#marketTerminalOther").css("display","none");
            var marketTerminalList = data.result;
            if(marketTerminalList != null ){
                $("#totalCount").text(marketTerminalList.length+"家");
                if(marketTerminalList.length <= 5){
                    for(var i = 0; i < marketTerminalList.length; i++){
                        $("#marketTerminalDiv").append(getMarketInfoStr(marketTerminalList[i].terMarketId,marketTerminalList[i].marketName));
                    }
                }else{
                    $("#marketTerminalOther").css("display","block");
                    for(var i = 0; i < 5; i++){
                        $("#marketTerminalDiv").append(getMarketInfoStr(marketTerminalList[i].terMarketId,marketTerminalList[i].marketName));
                    }
                    for(var i = 5; i< marketTerminalList.length; i++){
                        $("#marketTerminalOtherDiv").append(getMarketInfoStr(marketTerminalList[i].terMarketId,marketTerminalList[i].marketName));
                    }
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
}

function marketTerminalOther(){
    if($("#marketTerminalOther").text() == "展开更多"){
        $("#marketTerminalOther").html("<span style='padding-left:20px;'>收起</span>");
    }else{
        $("#marketTerminalOther").html("<span style='padding-left:20px;'>展开更多</span>");
    }
    $("#marketTerminalOtherDiv").slideToggle();
}
//选中批发市场点击
function chooseTerminalClick(obj){
    var id = obj.id;
    var terMarketId = id.split("_")[0];
    localStorage.terMarketId = terMarketId;
    localStorage.removeItem("fodMarketId");
    window.location = "BY121232.html";
}
//批发市场编辑
function marketTerminalEdit(obj){
    var id = obj.id;
    var terMarketId = id.split("_")[0];
    localStorage.terMarketId = terMarketId;
    localStorage.removeItem("fodMarketId");
    window.location = "BY121230.html";
}
//显示批发市场信息
function getMarketInfoStr(terMarketId,marketName){
    var str = "<div class='ui-grid-a' style='width: 80%;margin-left:25px;'>";
    str = str + "<div class='ui-block-a' style='width: 80%;' ><div id='"+terMarketId+"_1' onclick='chooseTerminalClick(this)' style='margin-left:0px;' class='listDiv'><span style='padding-left:20px;'>"+marketName+"</span></div></div>";
    str = str + "<div class='ui-block-b' style='width: 20%;' ><div id='"+terMarketId+"_2' onclick='marketTerminalEdit(this)' style='margin-left:0px;text-align: center;' class='listDiv'>查看</div></div>";
    str = str + "</div>";
    return str;
}
//返回前一个画面
function returnPage(){
    window.location = "BY12120301.html";
}