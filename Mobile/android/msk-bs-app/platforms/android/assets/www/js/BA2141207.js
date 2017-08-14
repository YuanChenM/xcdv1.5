/**
 * Created by yang_chunyan on 2016/7/12.
 */
var backUrl = 'BA2141121.html';
var BA2141207={
    /**确定按钮点击事件*/
    bindNextBtn:function(){
        $('#next').bind("touchstart",function(){
            console.info("touchstart");
            var url = ConstantDef.getFindHouseAccountServerUrl();
            var loginName = $("#loginName").val();
            var passWord = $("#passWord").val();
            var confirmPassWord = $("#confirmPassWord").val();
            if(BA2141207.validator()){
                 var data = {
                     param: {
                         houseAccount:loginName,houseTel:loginName
                      }
                 };
                 console.log(data);
                 HttpClient.post(url,data,function(data){
                 if(data.status == "S"){
                     if (!$(".message")[0].hasAttribute("style")) {
                         $(".message").css("display", "none");
                     }
                     window.location.href = 'BA2141208.html?slAccount=' + encodeURI(loginName) + "&passWord=" + passWord + "&confirmPassWord" + confirmPassWord;
                 }else{
                     BA2141207.setMessageDivStyle("用户已存在");
                     }
                 },function(data){
                     webToast("操作失败","middle");
                 });

            }
        })
    },
    validator:function(){
        var regPassWord= /[\u4E00-\u9FA5\uF900-\uFA2D]/;///^[u4E00-u9FA5]+$/;
        /*var regLoginName =/^((\w*\d\w*[a-z]\w*)|(\w*[a-z]\w*\d\w*))$/i;*/
        var regLoginName=/^[\u4e00-\u9fa5\w\-_]{6,20}$/i; // 支持中文、数字、下划线
        var loginName = $("#loginName").val();
        var passWord = $("#passWord").val();
        var confirmPassWord = $("#confirmPassWord").val();
        if(!loginName){
            BA2141207.setMessageDivStyle("请输入用户名");
            return false;
        }else if(loginName.length < 6 || loginName.length > 20){
            BA2141207.setMessageDivStyle("用户名长度为6-20个字符");
            return false;
        }
        else if(!passWord){
            BA2141207.setMessageDivStyle("请输入密码");
            return false;
        }else if(passWord.length < 6 || passWord.length > 11){
            BA2141207.setMessageDivStyle("密码长度为6-11个字符");
            return false;
        }else if(!confirmPassWord){
            BA2141207.setMessageDivStyle("请输入确认密码");
            return false;
        }else if(passWord != confirmPassWord){
            BA2141207.setMessageDivStyle("密码不匹配,请重新输入");
            return false;
        }
        else  if(!regLoginName.test(loginName)){
            BA2141207.setMessageDivStyle("用户名格式不正确,请重新输入");
            return false;
        }
        else  if(regPassWord.test(passWord)){
            BA2141207.setMessageDivStyle("密码格式不正确,请重新输入");
            return false;
        }
        else if(!$(".check").hasClass("on")){
            BA2141207.setMessageDivStyle("确定同意并阅读章程");
            return false;
        }
        return true;
    },
    //设置messageDiv样式
    setMessageDivStyle:function(message){
        $(".message span")[0].innerHTML = message;
        $(".message").removeAttr("style");
    },
    /**手机号码onchange事件*/
    bindLoginTxt:function(){
        $('#loginName').bind("onchange",function() {
            if (!$(".message")[0].hasAttribute("style")) {
                $(".message").css("display", "none");
            }
        })
    },
    /**选择事件绑定*/
    bindCheck:function(){
        $(".check").on("touchstart",function(){
            if($(this).hasClass("on")){
                $(this).removeClass("on");
            }else{
                $(this).addClass("on");
            }
            BA2141207.checkBoxEvent();
        })
    },
    bindFh:function(){
        $('#BA2141207_return').bind("touchstart",function(){
            window.location.href = 'BA2141121.html';
        })
    }
}
jQuery(document).ready(function() {
    BA2141207.bindNextBtn();
    BA2141207.bindFh();
    BA2141207.bindCheck();
    $(".check").addClass("on");
    document.addEventListener("backbutton", eventBackButton, false); //返回键
});


function eventBackButton(){
    var flag = true;
    var isFocus=$("input").is(":focus");
    if(isFocus && flag){
        document.removeEventListener("backbutton", eventBackButton, false); //注销返回键
        //3秒后重新注册
        var intervalID = window.setInterval(
            function() {
                window.clearInterval(intervalID);
                document.addEventListener("backbutton", eventBackButton, false); //返回键
            },
            3000);
        flag = false;
    }else{
        window.location.href = backUrl;
    }
}

