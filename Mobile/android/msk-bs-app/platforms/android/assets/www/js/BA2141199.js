/**
 * Created by zhu_kai1 on 2016/9/5.
 */
var backUrl='BA2141105.html';
var BA2141199 = {
    init: function(){
        if(localStorage.accessType == ''){
            window.location.href = 'BA2141101.html';
            return;
        }
        BA2141199.bindFh();
        BA2141199.loginOut();
    },
    /**返回事件绑定*/
    bindFh:function(){
        $(".fanhui").bind("touchstart",function(){
            window.location.href = backUrl;
        })
        $("#about").bind("touchstart",function(){
            window.location.href = 'BA2141190.html';
        })
        $("#chooseCity").bind("touchstart",function(){
            window.location = "BA2141191.html";
        });
    },
    QueryString: function (val) {
        var uri = window.location.search;
        var svalue =uri.match(new RegExp("[\?\&]" + val + "=([^\&]*)(\&?)","i"));
        return svalue ? svalue[1] : svalue;
    },
    loginOut:function(){
        $(".loginOut_contain").bind("touchstart",function(){
            // 2-管家，3-买手
           var accessType =localStorage.accessType;
            localStorage.accessType = "";
            if(accessType==2){
                window.location = "BA2141103.html";
            }else{
                window.location = "BA2141102.html";
                localStorage.accessTypeTow = "";
            }
        });
    }
}

window.onload = window.setTimeout(BA2141199.init, 200);



