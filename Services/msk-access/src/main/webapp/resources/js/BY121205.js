/**
 * 买家注册
 */
function buyerRegister(){
    var flickerAPI = url+'/by/account/register';
    var phoneNumber = $("#phoneNumber").val();
    var lgcsAreaCode = localStorage.lgcsAreaCode;
    var cityCode = localStorage.cityCode;
    var districtCode = localStorage.districtCode;
    localStorage.telNo = phoneNumber;
    if(phoneNumber.length !=11){
        setMessageDivStyle("请输入正确的手机号码。");
        return;
    }
    var paramData = {"siteCode": "101", "auth": "", "loginid": "", "param": {"telNo": phoneNumber,"lgcsAreaCode":lgcsAreaCode,"cityCode":cityCode,"districtCode":districtCode,"registerSource":"1","updId":localStorage.accessAccountName}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if(data.status == "F"){
                setMessageDivStyle(data.message);
            }else{
                localStorage.buyerId = data.result.buyerId;
                localStorage.enterFlg = "register";
                window.location = "BY12120501.html";
            }
        },
        error: function(){
            alert("error");
        }
    });
}
//设置messageDiv样式
function setMessageDivStyle(message){
    $("#message").css("display","block");
    $("#message").text(message);
}
//返回前一个画面
function returnPage(){
    window.location = "BY121204.html";
}