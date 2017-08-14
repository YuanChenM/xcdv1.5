/**
 * Created by yang_chunyan on 2016/7/12.
 */
var backUrl = 'BA2141104.html';
var BA2141120Flag = false;
var BA2141120={
    /**确定按钮点击事件*/
    bindNextBtn:function(){
        if(BA2141120Flag){
                console.info("touchstart");
                var slAccount = decodeURI(commonUtil.QueryString("slAccount"));
                var psd = commonUtil.QueryString("passWord");
                var tel = $("#tel").val();
                var name = $("#name").val();
                var sex = $("input[name='sex']:checked").val();
                var idCard = $("#idCard").val();
                //var bsMainClass = $("#bsMainClass").val();
                var houseType = $("#thdistrict")[0].title;
                //var bsSecondClass = $("#bsSecondClass").val();
                var provinceCode = '';//$("#rhprovince")[0].title;
                var provinceName = '';//$("#rhprovince").val();
                var cityCode = '';//$("#rhcity")[0].title;
                var cityName = '';//$("#rhcity").val();
                var districtCode = '';//$("#rhdistrict")[0].title;
                var districtName = '';//$("#rhdistrict").val();
                if ($("#rhprovince")[0]) {
                    provinceCode = $("#rhprovince")[0].title;
                    provinceName = $("#rhprovince").val();
                }
                if ($("#rhcity")[0]) {
                    cityCode = $("#rhcity")[0].title;
                    cityName = $("#rhcity").val();
                }
                if ($("#rhdistrict")[0]) {
                    districtCode = $("#rhdistrict")[0].title;
                    districtName = $("#rhdistrict").val();
                }
                var wechat = $("#wechat").val();
                var QQ = $("#QQ").val();
                var accountName = $("#accountName").val();
                var bankName = $("#bank").val();
                var detailAddr = $("#street").val();
                var accountInfo = $("#accountInfo").val();
                var cardType = (accountName != '' || bankName  != '' || accountInfo  != '') ? $("input[name='cardType']:checked").val() : null;
                var url = ConstantDef.getNewOrUpdateBsInfoServerUrl();
                var data = {
                    param: {
                        siteCode: 'abcd',
                        auth: 'xxxx',
                        loginId: 'a124',
                        slAccount:{
                            slAccount:slAccount,
                            slTel:tel,
                            accountPsd:psd,
                            slShowName:name,
                            slContact:name,
                            fromFlg:"2",
                            authStatus:"2"
                        },
                        slSeller:{
                            slAccount:slAccount,
                            memo1:wechat,
                            memo2:QQ,
                            memo7:idCard,
                            memo8:houseType,
                            memeo9:detailAddr,
                            //slMainClass:bsMainClass,
                            //memo8:bsSecondClass,
                            provinceCode:provinceCode,
                            cityCode:cityCode,
                            districtCode:districtCode,
                            provinceName:provinceName,
                            cityName:cityName,
                            districtName:districtName,
                            slConFlg:"1",
                            slMainClass:"1",//卖家类型
                            snkFlg:"1",
                            selfFlg:"1",
                            agentFlg:"1",
                            oemFlg:"1",
                            buyerFlg:"1",
                            fromFlg:"2",
                            memo6:"1",
                            authStatus:"2"
                        },
                        slBuyerShop:{
                            slIdcard:idCard,
                            slSort:1,
                            slAddress:provinceName + cityName + districtName + detailAddr
                        },
                        slBsBankaccount:{
                            accountName:accountName,
                            bankName:bankName,
                            bankNo:accountInfo,
                            cardType:cardType
                            //bankTel:tel
                        }
                    }
                };
                console.log(data);
                HttpClient.post(url,data,function(data){
                    if(data.status == "S"){
                        webToast("保存成功","middle");
                        window.location = "BA2141102.html";
                    }else{
                        webToast("保存失败","middle");
                    }
                },function(data){
                    webToast("操作失败","middle");
                });
        }
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
    bindFh:function(){
        $('#fanhui').bind("touchstart",function(){
            window.location.href = 'BA2141104.html';
        })
    },
    /**选择事件绑定*/
    bindCheck:function(){
        $(".checkSex").on("touchstart",function(){
            if($(this).hasClass("on")){
                $(this).removeClass("on");
                if($(this).attr("name")=='male'){
                    $(this).next().addClass("on");
                }else{
                    $(this).prev().addClass("on");
                }

            }else{
                $(this).addClass("on");
                if($(this).attr("name")=='male') {
                    $(this).next().removeClass("on");
                }else{
                    $(this).prev().removeClass("on");
                }
            }
        })
    },
    bindSelectBsType:function(levelCode,select,roleType){
        console.info(levelCode);
        console.info(roleType);
        var parentCode = "";
        if(levelCode == "1" && roleType == "1"){
            parentCode = $("#category option:selected").val();
        }else if(levelCode == "1" && roleType == "0"){
            parentCode = $("#bsMainClass option:selected").val();
        }
        var data = {
            param:{
                typeLever: levelCode,
                parentTypeCode :parentCode
            }
        };
        var url = ConstantDef.getFindHouseTypeServerUrl();
        if(roleType == "0")
            url = ConstantDef.getFindBsTypeServerUrl();
        HttpClient.post(url,data,function(data){
            //var option = "<option value=''>请选择<option>";
            var obj=document.getElementById(select);
            obj.options.add(new Option("请选择",""));
            console.info(data.result.length);
            if(data && data.result.length > 0){
                for(var i=0;i<data.result.length;i++){
                    obj.options.add(new Option(data.result[i].typeName,data.result[i].typeCode));
                }
            }
        },function(data){
            webToast("操作失败","middle");
        });
    },
    bindCity:function(){
        $(".address-js").bind("touchstart",function(){
            citySet.SelCity(this,event);
        });
        $(".type-js").bind("touchstart",function(){
            typeSet.SelCity(this,event);
        })
    },
    myinit:function(){
        BA2141120.bindNextBtn();
        setTimeout("BA2141120.bindCity()",200);
        BA2141120.bindFh();
        $("#bsForm").html5Validate(function () {
        });
        document.addEventListener("backbutton", eventBackButton, false); //返回键
        //BA2141120.bindSelectBsType("0","bsMainClass","0")//买手一级分类
        //$("#bsMainClass").on("change",function(){
        //    var obj=document.getElementById("bsSecondClass");
        //    obj.options.length=0;
        //    BA2141120.bindSelectBsType("1","bsSecondClass","0")//买手二级分类
        //})
    },
    saveData:function(){
        BA2141120Flag = false;
        BA2141208Flag = true;
    }
}

window.onload=window.setTimeout(BA2141120.myinit,200);


document.addEventListener("backbutton", eventBackButton, false);

function eventBackButton(e) {
    e.preventDefault();
    var flag = true;
    var isFocus=$("input").is(":focus");
    if($(".bigCion").css("display")=="flex"||$(".gad").css("display")=="block"){
        $(".bigCion").css("display","none");
        $(".gad").css("display","none");
    }else if(isFocus && flag){
        document.removeEventListener("backbutton", eventBackButton, false); //注销返回键
        //3秒后重新注册
        var intervalID = window.setInterval(
            function() {
                window.clearInterval(intervalID);
                document.addEventListener("backbutton", eventBackButton, false); //返回键
            },
            3000);
        flag = false;
        //$("input").blur();
    }else{
        window.location.href = backUrl;
    }
}
