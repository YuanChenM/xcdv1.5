/**
 * Created by ni_shaotang on 2016/7/13.
 */
var backUrl ='BA2141121.html';
var houseListCommon = [];
$("#messageLoading").modal();
$("#messageLoading").show();
$('#messageLoading').addClass('animated zoomIn');
var BA2141202 = {
    init: function () {
        BA2141202.bindCheck();
        BA2141202.loadHouses();
    },
    loadHouses: function () {
        var url = ConstantDef.getSearchHouseListUrl();
        var slCode = localStorage.slCode;
        if (slCode == "" || slCode == null) {
            window.location.href = 'BA2141101.html';
            return;
        }
        var data = {"param": {"slCode": slCode}};
        console.log(url);
        console.log(slCode);
        HttpClient.post(url, data, function (data) {
            houseListCommon = data.result;
            var str = "";
            var starNum = 0;
            var copyHouseInfo = $("#houseInfo").html();
            var star_on = $("#star_on").html();
            var star_un = $("#star_un").html();
            $("#simplemodal-container").hide();
            $('.loader-inner').addClass('animated zoomOut');
            $("#messageLoading").addClass('animated zoomOut');
            $("#simplemodal-overlay").remove();
            for (var i = 0; i < houseListCommon.length; i++) {
                starNum = houseListCommon[i].houseStar;
                var starHtml = "";
                for (var j = 0; j < 5; j++) {
                    if (j < starNum) {
                        starHtml+=star_on;
                    }else{
                        starHtml+=star_un;
                    }
                }

                str += copyHouseInfo.replace("houseContact", houseListCommon[i].houseContact).replace("slIdcard", houseListCommon[i].slIdcard)
                    .replace("vhouseAddress", houseListCommon[i].vhouseAddress).replace("cityName", houseListCommon[i].cityName)
                    .replace("houseTel", houseListCommon[i].houseTel).replace("houseStar", starHtml)
                    .replace("buyerNum", houseListCommon[i].buyerNum).replace("houseCode", houseListCommon[i].houseCode);
            }
            $("#houseList").html(str);
        }, function (data) {
            webToast("失败", "middle");
            setTimeout(function(){
                $("#simplemodal-container").hide();
                $('.loader-inner').addClass('animated zoomOut');
                $("#messageLoading").addClass('animated zoomOut');
                $("#simplemodal-overlay").remove();
            },800)
        });
    },
    bindCheck: function () {
        $("#fanhui").bind("touchstart", function () {
            window.location.href = backUrl;
        });
    },
    jumpInfo: function (houseCode) {
        window.location.href = "BA2141203.html?houseCode=" + houseCode;
    }
}

//页面入口

window.onload = window.setTimeout(BA2141202.init, 500);