/**
 * Created by yang_chunyan on 2016/7/12.
 */
var backUrl = "destroy";
var BA2141101 = {
    /**买手登录事件*/
    bindBsLoginBtn: function () {
        $(".b01").bind("touchstart", function () {
            BA2141101.checkOpenTime('BA2141102.html');
        })
    },
    /**冻品管家登录事件*/
    bindGjLoginBtn: function () {
        $(".b02").bind("touchstart", function () {
            BA2141101.checkOpenTime('BA2141103.html');
        })
    },
    bindRegister: function () {
        $(".b03").bind("touchstart", function () {
            localStorage.html = "BA2141101.html";
            BA2141101.checkOpenTime('BA2141104.html?fromurl=BA2141101.html');
        })
    },
    checkOpenTime: function (href) {
        var url = ConstantDef.getCheckLoginTypeUrl();
        var data = {
            param: ""
        };
        HttpClient.post(url, data, function (data) {
            var returnCode = data.returnCode;
            if (returnCode != "S") {
                webToast(data.message, "middle");
            } else {
                var version = data.result.versionsCode;
                if (version != ConstantDef.getVersion()) {
                    var ver = version.split(".");
                    var v = ConstantDef.getVersion().split(".");
                    if(ver[0]!=v[0]||ver[1]!=v[1]) {
                        webToast("当前版本过低请重新下载", "middle");
                    }else{
                        popTipShow.confirm('版本过低', '是否更新版本', ['确 定', '取 消'],
                            function (e) {
                                //callback 处理按钮事件
                                var button = $(e.target).attr('class');
                                if (button == 'ok') {
                                    //按下确定按钮执行的操作
                                    this.hide();
                                }
                                if (button == 'cancel') {
                                    var versionsStatus = data.result.versionsStatus;
                                    if(versionsStatus == "1"){
                                        //按下取消按钮执行的操作
                                        window.location.href = href;
                                    }else if(versionsStatus == "2"){
                                        webToast("当前系统维护中", "middle");
                                    }else{
                                        webToast("版本信息错误，请联系管理员", "middle");
                                    }
                                    this.hide();
                                }
                            }
                        );
                    }
                } else {
                    var versionsStatus = data.result.versionsStatus;
                    if(versionsStatus == "1"){
                        //按下取消按钮执行的操作
                        window.location.href = href;
                    }else if(versionsStatus == "2"){
                        webToast("当前系统维护中", "middle");
                    }else{
                        webToast("版本信息错误，请联系管理员", "middle");
                    }
                }
            }
        })
    }
}


jQuery(document).ready(function () {
    BA2141101.bindBsLoginBtn();
    BA2141101.bindGjLoginBtn();
    BA2141101.bindRegister();
    onDeviceReady();
});


document.addEventListener('deviceready', onDeviceReady, false);
function backmenuEvent() {
    navigator.app.exitApp();
}
function onDeviceReady() {
    document.addEventListener('backbutton', backmenuEvent, false);
}