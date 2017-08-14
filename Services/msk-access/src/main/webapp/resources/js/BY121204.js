/**
 * 买家查询画面
 */
function searchButtonClick(){
    // 根据条件查询买家信息
    var flickerAPI = url+'/by/condition/findBuyers';
    var conditionText = $("#searchParam").val();
    var lgcsAreaCode = localStorage.lgcsAreaCode;
    var cityCode = localStorage.cityCode;
    var districtCode = localStorage.districtCode;
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerName": conditionText,"lgcsAreaCode":lgcsAreaCode,"cityCode":cityCode,"districtCode":districtCode}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            $("#buyerDiv").html("");
            $("#buyerOtherDiv").html("");
            $("#totalCount").text("0家");
            $("#buyerOther").css("display","none");
            var buyerList = data.result;
            if(buyerList != null && buyerList.length < 5){
                $("#totalCount").text(buyerList.length+"家");
                for(var i = 0; i < buyerList.length; i++){
                    var buyerStr = buyerList[i].buyerId + ":" + buyerList[i].telNo;
                    $("#buyerDiv").append(" <div id ='" + buyerStr + "' onclick='chooseBuyerClick(this)' style='width: 80%;' class='listDiv'><span style='padding-left:20px;'>"+buyerList[i].buyerName+"</span></div>");
                }
            }else if(buyerList != null) {
                $("#totalCount").text(buyerList.length + "家");
                $("#buyerOther").css("display","block");
                for(var i = 0; i < 5; i++){
                    var buyerStr = buyerList[i].buyerId + ":" + buyerList[i].telNo;
                    $("#buyerDiv").append(" <div  id ='"+buyerStr+"' onclick='chooseBuyerClick(this)' style='width: 80%;' class='listDiv'><span style='padding-left:20px;'>"+buyerList[i].buyerName+"</span></div>");
                }
                for(var i = 5; i< buyerList.length; i++){
                    var buyerStr = buyerList[i].buyerId + ":" + buyerList[i].telNo;
                    $("#buyerOtherDiv").append(" <div  id ='"+buyerStr+"' onclick='chooseBuyerClick(this)' style='width: 80%;' class='listDiv'><span style='padding-left:20px;'>"+buyerList[i].buyerName+"</span></div>");
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
}

function buyerOther(){
    if($("#buyerOther").text() == "展开更多"){
        $("#buyerOther").html("<span style='padding-left:20px;'>收起</span>");
    }else{
        $("#buyerOther").html("<span style='padding-left:20px;'>展开更多</span>");
    }
    $("#buyerOtherDiv").slideToggle();
}

function registerButtonClick(){
    window.location = "BY121205.html";
}

function chooseBuyerClick(buyerStr){
    var local = buyerStr.id.split(":");
    localStorage.buyerId = local[0];
    localStorage.telNo = local[1];
    localStorage.searchPage = 1;
    window.location = "BY121210.html";
}
//返回前一个画面
function returnPage(){
    window.location = "BY12120301.html";
}