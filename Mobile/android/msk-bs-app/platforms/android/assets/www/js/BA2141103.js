/**
 * Created by yang_chunyan on 2016/7/12.
 */
var backUrl = "BA2141101.html";
var BA2141103 ={
    /**确定按钮点击事件*/
    bindSureBtn:function(){
        $('#sure').bind("touchstart",function(){
            var url = ConstantDef.getHouseAcountLoginServerUrl();
            var slTel = $("#login").val();
            var accountPsd = $("#pass").val();
            var data = {
                param: {
                    slTel:slTel,
                    accountPsd:accountPsd
                }
            };
            console.log(data);
            HttpClient.post(url,data,function(data){
                if(data.status == "F"){
                    BA2141103.setMessageDivStyle(data.message);
                }else{
                    console.info(data.result);
                    localStorage.accessAccountName = slTel;
                    localStorage.houseCode = data.result.houseCode;
                    localStorage.houseName = data.result.houseShowName;
                    localStorage.accessType = 2;
                    localStorage.sellerCode = data.result.slCode;
                    localStorage.lgcsCode = data.result.vlgcsAreaCode;//管家物流区code
                    localStorage.provinceCode = data.result.vprovinceCode;//管家虚拟省code
                    localStorage.cityCode = data.result.vcityCode;// 城市选择时需要显示当前所在城市
                    window.location = "BA2141105.html";
                }
            },function(data){
                webToast("操作失败","middle");
            });
        })
    },
    /**手机号码onchange事件*/
    bindLoginTxt:function(){
        $('#login').bind("onchange",function() {
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
    bindBsLogin : function(){
        $('#bsLogin').bind("touchstart",function(){
            window.location.href = 'BA2141102.html';
        })
        $(".forgetPw").bind("touchstart",function(){
            window.location.href = "BA2141192.html?accessType=2";
        })
    },
    bindFh:function(){
        $('#fanhui').bind("touchstart",function(){
            window.location.href = backUrl;
        })
    }
}

jQuery(document).ready(function() {
    BA2141103.bindSureBtn();
    BA2141103.bindLoginTxt();
    BA2141103.bindBsLogin();
    BA2141103.bindFh();
});
