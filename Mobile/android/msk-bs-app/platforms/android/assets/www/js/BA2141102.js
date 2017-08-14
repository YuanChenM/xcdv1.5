/**
 * Created by yang_chunyan on 2016/7/12.
 */
var backUrl = "BA2141101.html";
var BA2141102={
    /**确定按钮点击事件*/
    bindSureBtn:function(){
        $('#sure').bind("touchstart",function(){
            var url = ConstantDef.getAcountLoginServerUrl();
            var slTel = $("#loginName").val();
            var accountPsd = $("#passWord").val();
            var data = {
                param: {
                    slTel:slTel,
                    accountPsd:accountPsd
                }
            };
            console.log(data);
            HttpClient.post(url,data,function(data){
                if(data.status == "F"){
                    BA2141102.setMessageDivStyle(data.message);
                }else{
                    localStorage.accessAccountName = slTel;
                    localStorage.houseCode = data.result.slAccount;
                    localStorage.slCode = data.result.slCode;
                    localStorage.slContact = data.result.slContact;
                    localStorage.slCodeDis = data.result.slCodeDis;
                    localStorage.lgcsCode = data.result.lgcsCode;
                    localStorage.cityCode = data.result.cityCode;// 城市选择时需要显示当前所在城市
                    localStorage.provinceCode = data.result.provinceCode;
                    localStorage.slTel = data.result.slTel;
                    localStorage.accessType = 3;
                    console.info(localStorage.slCodeDis);
                    if( localStorage.accessTypeTwo==5){
                        window.location = "BA2141121.html";
                    }else{
                        window.location = "BA2141105.html";
                    }

                }
            },function(data){
                webToast("操作失败","middle");
            });
        })
    },
    /**手机号码onchange事件*/
    bindLoginTxt:function(){
        $('#loginName').bind("onchange",function() {
            if (!$(".message")[0].hasAttribute("style")) {
                $(".message").css("display", "none");
            }
        })
    },
    //设置messageDiv样式
    setMessageDivStyle:function(message){
        $(".message span")[0].innerHTML = message;
        $(".message").removeAttr("style");
    },
    bindGjLogin : function(){
        $('#gjLogin').bind("touchstart",function(){
            window.location.href = 'BA2141103.html';
        })
    },
    bindFh:function(){
        $('#fanhui').bind("touchstart",function(){
            window.location.href = backUrl;
        })
    },
    bindRegister:function(){
        $("#register").bind("touchstart",function(){
            localStorage.html = "BA2141102.html";
            window.location.href = 'BA2141104.html?fromurl=BA2141102.html';
        })
        $(".forgetPw").bind("touchstart",function(){
            window.location.href = "BA2141192.html?accessType=3";
        })
    }

}

//window.onBackKeyDown = function(){alert('oh! you press the back button');}

jQuery(document).ready(function() {
    BA2141102.bindRegister();
    BA2141102.bindSureBtn();
    BA2141102.bindLoginTxt();
    BA2141102.bindGjLogin();
    BA2141102.bindFh();
});
