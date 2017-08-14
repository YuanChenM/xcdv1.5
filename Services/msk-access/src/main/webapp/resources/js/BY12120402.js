$(function(){
    var localLgcsArea = localStorage.lgcsAreaName + "物流区所有菜场";
    var localCity = localStorage.cityName + "市所有菜场";
    var localDistrict = localStorage.cityName + "市" + localStorage.districtName + "所有菜场";
    $("#currentLgcsAreaLabel").text(localLgcsArea);
    $("#currentCityLabel").text(localCity);
    $("#currentDistrictLabel").text(localDistrict);
});
/**
 * 批发市场查询画面
 */
function searchButtonClick(){
    // 根据条件查询买家信息
    var flickerAPI = url+'/by/condition/findMarketFood';
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
            $("#marketFoodDiv").html("");
            $("#marketFoodOtherDiv").html("");
            $("#totalCount").text("0家");
            $("#marketFoodOther").css("display","none");
            var marketFoodList = data.result;
            if(marketFoodList != null ){
                $("#totalCount").text(marketFoodList.length+"家");
                if(marketFoodList.length <= 5){
                    for(var i = 0; i < marketFoodList.length; i++){
                        $("#marketFoodDiv").append(getMarketInfoStr(marketFoodList[i].fodMarketId,marketFoodList[i].marketName));
                    }
                }else{
                    $("#marketFoodOther").css("display","block");
                    for(var i = 0; i < 5; i++){
                        $("#marketFoodDiv").append(getMarketInfoStr(marketFoodList[i].fodMarketId,marketFoodList[i].marketName));
                    }
                    for(var i = 5; i< marketFoodList.length; i++){
                        $("#marketFoodOtherDiv").append(getMarketInfoStr(marketFoodList[i].fodMarketId,marketFoodList[i].marketName));
                    }
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
}

function marketFoodOther(){
    if($("#marketFoodOther").text() == "展开更多"){
        $("#marketFoodOther").html("<span style='padding-left:20px;'>收起</span>");
    }else{
        $("#marketFoodOther").html("<span style='padding-left:20px;'>展开更多</span>");
    }
    $("#marketFoodOtherDiv").slideToggle();
}
//选中菜场点击
function chooseFoodClick(obj){
    var id = obj.id;
    var fodMarketId = id.split("_")[0];
    localStorage.fodMarketId = fodMarketId;
    localStorage.removeItem("terMarketId");
    window.location = "BY121232.html";
}
//菜场编辑
function marketFoodEdit(obj){
    var id = obj.id;
    var fodMarketId = id.split("_")[0];
    localStorage.fodMarketId = fodMarketId;
    localStorage.removeItem("terMarketId");
    window.location = "BY121231.html";
}
//显示菜场信息
function getMarketInfoStr(fodMarketId,marketName){
    var str = "<div class='ui-grid-a' style='width: 80%;margin-left:25px;'>";
    str = str + "<div class='ui-block-a' style='width: 80%;' ><div id='"+fodMarketId+"_1' onclick='chooseFoodClick(this)' style='margin-left:0px;' class='listDiv'><span style='padding-left:20px;'>"+marketName+"</span></div></div>";
    str = str + "<div class='ui-block-b' style='width: 20%;' ><div id='"+fodMarketId+"_2' onclick='marketFoodEdit(this)' style='margin-left:0px;text-align: center;' class='listDiv'>查看</div></div>";
    str = str + "</div>";
    return str;
}
//返回前一个画面
function returnPage(){
    window.location = "BY12120301.html";
}