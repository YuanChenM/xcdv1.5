/**
 * Created by ni_shaotang on 2016/7/14.
 */

function loginButtonClick(){
    var slTel = $("#login").val();
    var accountPsd = $("#pass").val();
    console.log(slTel);
    console.log(accountPsd);
    var flickerAPI = url+'api/ba/houseAccount/login';
    var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {"slTel": slTel, "accountPsd":accountPsd}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'JSON',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "F"){
                setMessageDivStyle(data.message);
            }else{
                localStorage.accessAccountName = slTel;
                localStorage.houseCode = data.result.slAccount;
                localStorage.accessType = 2;
                window.location = "BA2141105.html";
            }
        },
        error:function(){
            alert("error");
        }
    });
}

//设置messageDiv样式
function setMessageDivStyle(message){
    $(".message span")[0].innerHTML = message;
    $(".message").removeAttr("style");
}

function addClass(){
    if(!$(".message")[0].hasAttribute("style")){
        $(".message").css("display","none");
    }
}
//返回前一画面
function returnPage(){
    window.location = "BA2141101.html"
}
