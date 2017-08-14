$(function(){
    var flickerAPI = url+'/by/buyerId/findBasicInfo';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId": localStorage.buyerId}};
    var basicInfo;
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if(data.status == "S"){
                basicInfo = data.result;
                //显示基本信息
                $("#title").text(basicInfo.buyerName);
                $("#storeNo").text(basicInfo.storeNo);
                $("#phoneNo").text(basicInfo.busiTel);
                $("#address").text(basicInfo.buyerAddr);
                $("#salesClasses").text(basicInfo.sellerObject);
                $("#status").text(basicInfo.marketingsStatusName);
            }
        },
        error: function(){
            alert("error");
        }
    });
});

/**
 * 维护资料
 */
function infoModify(){
    window.location = "BY121211.html";
}

/**
 * 密码重置
 */
function passwordReset(){
    var flickerAPI = url+'/by/password/reset';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId": localStorage.buyerId, "telNo":localStorage.telNo}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if(data.status == "S"){
                setMessageDivStyle("密码重置成功！");
            } else{
                setMessageDivStyle("密码重置失败！");
            }
        },
        error: function(){
            alert("密码重置失败！");
        }
    });
}
//返回前一个画面
function returnPage(){
    if(localStorage.searchPage = 1){
        window.location = "BY121204.html";
    }else{
        window.location = "BY121209.html";
    }
}
//产品调研
function productResearch(){
    window.location = "BY121212.html";
}

//设置messageDiv样式
function setMessageDivStyle(message){
    $("#message").css("border","1px #FF0000 solid");
    $("#message").css("color","#FF0000");
    $("#message").css("display","block");
    $("#message").text(message);
}