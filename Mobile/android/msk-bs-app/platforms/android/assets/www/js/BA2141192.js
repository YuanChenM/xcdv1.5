/**
 * Created by peng_wei on 2016/10/25.
 */
var messCode = "";
var imgCode = "";
var tel = "";
var countdown = 10;
var backUrl = 'BA2141103.html';
var BA2141192 = {
    init: function () {
        if(commonUtil.QueryString("accessType") == "3"){
            backUrl = 'BA2141102.html';
        }
        $('#img').bind("touchstart", function () {
            BA2141192.loadImage();
        })
        $('#updatePwd').bind("touchstart", function () {
            BA2141192.updatePwd();
        })
        $('#break').bind("touchstart", function () {
                window.location.href = backUrl;
        })
        $('#sendMessage').bind("click", function () {
            var url = ConstantDef.getHouseMessCodeUrl();
            if (commonUtil.QueryString("accessType") == "3") {
                url = ConstantDef.getBuyerMessCodeUrl();
            }
            var slTel = $("#mobile").val();
            if (slTel == "") {
                webToast("手机号码不能为空", "middle");
                $("#mobile").focus();
                return false;
            }
            if (!slTel.match(/^(((13[0-9]{1})|159|153)+\d{8})$/)) {
                webToast("手机号码格式不正确", "middle");
                return false;
            }
            var data = {
                param: slTel
            };
            $(this).css("disabled", "disabled");
            HttpClient.post(url, data, function (data) {
                if (data.status == "F") {
                    webToast(data.message, "middle");
                } else {
                    webToast(data.message + ",验证码为：" + data.result, "middle");
                    messCode = data.result;
                    tel = slTel;
                    BA2141192.setTime();
                }
            }, function (data) {
                webToast("操作失败", "middle");
            });
        })
    },
    loadImage: function () {
        var chars = ['2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
        var res = "";
        for (var i = 0; i < 4; i++) {
            var id = Math.ceil(Math.random() * (chars.length));
            res += chars[id];
        }
        var timestamp = Date.parse(new Date());
        var url = ConstantDef.getCheckImageCodeUrl() + "?code=" + res;
        $("#img").attr("src", url);
        imgCode = res;
    },
    updatePwd: function () {
        var url="";
        // 3-买手
        if (commonUtil.QueryString("accessType") == "3") {
              url = ConstantDef.getUpdateBuyerPasswordUrl();
        }else{
              url = ConstantDef.getUpdateHousePasswordUrl();
        }

        var slTel = $("#mobile").val();
        var imageCode = $("#imgCode").val();
        var messageCode = $("#messageCode").val();
        var password = $("#password").val();
        var newPassword = $("#newPassword").val();
        if (slTel == "") {
            webToast("手机号码不能为空", "middle");
            $("#mobile").focus();
            return false;
        }
        if (!slTel.match(/^(((13[0-9]{1})|159|153)+\d{8})$/)) {
            webToast("手机号码格式不正确", "middle");
            return false;
        }

        if (imgCode == "") {
            webToast("验证码不能为空", "middle");
            return false;
        }
        if (imageCode.toLowerCase() != imgCode.toLowerCase()) {
            console.log(imageCode.toLowerCase() + "====" + imgCode.toLowerCase());
            webToast("验证码输入不正确", "middle");
            return false;
        }
        if (messageCode == "") {
            webToast("短信验证码不能为空", "middle");
            return false;
        }
        if (messageCode != messCode) {
            console.log(messageCode + "====" + messCode);
            webToast("短信验证码输入不正确", "middle");
            return false;
        }
        if (slTel != tel) {
            webToast("手机号码与验证码不匹配", "middle");
            return false;
        }
        if (password == "") {
            webToast("新密码不能为空", "middle");
            return false;
        }
        if (password.length > 100) {
            webToast("新密码输入过长", "middle");
            return false;
        }
        if (newPassword == "") {
            webToast("第二次输入密码不能为空", "middle");
            return false;
        }
        if (password != newPassword) {
            webToast("两次密码不相同", "middle");
            return false;
        }
        var data = {
            param: {
                slTel: slTel,
                messCode: messageCode,
                imgCode: imageCode,
                password: password
            }
        };
        HttpClient.post(url, data, function (data) {
            if (data.status == "F") {
                webToast(data.message, "middle");
            } else {
                webToast(data.message, "middle");
                setTimeout(function () {
                    window.location.href = backUrl
                }, 2000);
            }
        }, function (data) {
            webToast("操作失败", "middle");
        });
    },
    setTime: function () {
        var val = $("#sendMessage");
        if (countdown == 0) {
            val.attr("disabled", false);
            val.val("获取短信验证码");
            val.css('background-color', '#8ec320');
            countdown = 10;
        } else {
            val.attr("disabled", true);
            val.val("重新发送(" + countdown + "s)");
            countdown--;
            val.css('background-color', '#DDDDDD');
            setTimeout(function () {
                BA2141192.setTime(val)
            }, 1000);
        }
    }
}
jQuery(document).ready(function () {
    BA2141192.init();
    BA2141192.loadImage();
})