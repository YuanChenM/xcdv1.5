/**
 * Created by zhu_kai1 on 2016/9/5.
 */
var backUrl = 'BA2141199.html';
var BA2141190 = {
    init: function () {
        if (localStorage.accessType == '') {
            window.location.href = 'BA2141101.html';
            return;
        }
        BA2141190.bindFh();
        BA2141190.loadIntroduce();
    },
    /**返回事件绑定*/
    bindFh: function () {
        $(".fanhui").bind("touchstart", function () {
            window.location.href = backUrl;
        })
    },
    loadIntroduce: function () {
        var url = ConstantDef.getCheckLoginTypeUrl();
        var data = {
            param: ""
        };
        HttpClient.post(url, data, function (data) {
            if (data.returnCode == 'S') {
                $("#version").html(data.result.versionsCode);
                $("#introduce").html(data.result.remark);
            }
        })
    },
    checkVersions: function () {
        var url = ConstantDef.getCheckLoginTypeUrl();
        var data = {
            param: ""
        };
        HttpClient.post(url, data, function (data) {
            if (data.returnCode == 'S') {
                var version = data.result.versionsCode;
                if (version != ConstantDef.getVersion()) {
                    webToast("发现新版本，请下载最新版本", "middle");
                }else{
                    webToast("当前版本为最新版本", "middle");
                }
            }else{
                webToast(data.message, "middle");
            }
        })
    }
}

window.onload = window.setTimeout(BA2141190.init, 200);



