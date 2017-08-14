jQuery(document).ready(function() {
    if(backUrl == "destroy"){
        onDeviceReady();
    }else {
        document.addEventListener("backbutton", eventBacksButton, false); //返回键
    }
});

var readyToEnd = false;//准备退出

function backmenuEvent() {
    $('#animationSandbox').css("bottom", '80px');
    //$('#animationSandbox').css("background-color",'#919191');
    $('#animationSandbox').addClass('bounceInUp' + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
        $(this).removeClass();
    });

    if (readyToEnd) navigator.app.exitApp();
    else {
        readyToEnd = true;
        $('#animationSandbox').css("display", '');
        //webToast("再按一次退出程序","middle");
        setTimeout(function () {//2s后自动隐藏提示和将重置准备退出操作
            $('#animationSandbox').css("display", 'none');
            //document.getElementById('exitNote').style.display = 'none';
            readyToEnd = false;
        }, 2000);
    }
}
function onDeviceReady() {
    document.addEventListener('backbutton', backmenuEvent, false);
    document.addEventListener('deviceready', onDeviceReady, false);
}



function eventBacksButton(){
    var flag = true;
    var isFocus=$("input").is(":focus");
    if(isFocus && flag){
        document.removeEventListener("backbutton", eventBacksButton, false); //注销返回键

        //3秒后重新注册
        var intervalID = window.setInterval(
            function() {
                window.clearInterval(intervalID);
                document.addEventListener("backbutton", eventBacksButton, false); //返回键
            },
            3000);
        flag = false;
        //$("input").blur();
    }else{
        if(backUrl){
            window.location.href = backUrl;
        }
    }
}