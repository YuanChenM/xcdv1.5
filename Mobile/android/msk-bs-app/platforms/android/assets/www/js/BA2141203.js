/**
 * Created by ni_shaotang on 2016/7/19.
 */
var houseListCommon = [];
var cityInfoCommon = [];
var backUrl = 'BA2141202.html';
var houseCode = "";
$("#messageLoading").modal();
$("#messageLoading").show();
$('#messageLoading').addClass('animated zoomIn');
var BA2141203 = {
    init: function () {
        BA2141203.bindCheck();
        BA2141203.loadHouses();
        document.addEventListener("backbutton", eventBackButton, false); //返回键
    },
    /**
     *
     * @param cityCode城市编码
     * @param districtCode地区编码
     * @param divId 所在层id
     * @param allCityId显示城市输入框id
     * @param isReadOnly 是否使用插件
     */
    loadCityInfo: function (cityCode, districtCode, divId, allCityId, isReadOnly) {
        var url = ConstantDef.GetDistrictList();

        var data = {
            "param": {
                "composeCodes": [cityCode + "" + districtCode]
            }
        };
        HttpClient.post(url, data, function (data) {
                cityInfoCommon = data.result;
                $("#simplemodal-container").hide();
                $('.loader-inner').addClass('animated zoomOut');
                $("#messageLoading").addClass('animated zoomOut');
                $("#simplemodal-overlay").remove();
                if (cityInfoCommon.length > 0) {
                    var cityInfo = cityInfoCommon[0];
                    $("#" + allCityId).val(cityInfo.provinceName + "-" + cityInfo.cityName + "-" + cityInfo.districtName);
                    if (isReadOnly) {
                        $("#rhprovince").attr("title", cityInfo.provinceCode);
                        $("#rhcity").attr("title", cityInfo.cityCode);
                        $("#rhdistrict").attr("title", cityInfo.districtCode);
                    }
                }
            },
            function (data) {
                webToast("失败", "middle");
                setTimeout(function () {
                    $("#simplemodal-container").hide();
                    $('.loader-inner').addClass('animated zoomOut');
                    $("#messageLoading").addClass('animated zoomOut');
                    $("#simplemodal-overlay").remove();
                }, 800)
            }
        )
        ;
    },
    loadHouses: function () {
        var url = ConstantDef.getSearchHouseListUrl();
        houseCode = commonUtil.QueryString("houseCode");
        var slCode = localStorage.slCode;
        var data = {"param": {"houseCode": houseCode, "slCode": slCode}};
        HttpClient.post(url, data, function (data) {
            houseListCommon = data.result;
            if (houseListCommon.length > 0) {
                $("#slCode").val(houseListCommon[0].slCode);
                $("#houseCode").val(houseListCommon[0].houseCode);
                $("#houseAccount").html(houseListCommon[0].houseAccount);
                $("#houseShowName").val(houseListCommon[0].houseShowName);
                $("#houseContact").html(houseListCommon[0].houseContact);
                $("#houseTel").html(houseListCommon[0].houseTel);
                $("#accountPsd").val(houseListCommon[0].accountPsd);
                $("#slIdcard").html(houseListCommon[0].slIdcard);
                $("#wechat").val(houseListCommon[0].wechat);
                $("#qq").val(houseListCommon[0].qq);
                $("#email").val(houseListCommon[0].email);
                $("#fixedTel").val(houseListCommon[0].fixedTel);
                $("#fax").val(houseListCommon[0].fax);

                BA2141203.loadCityInfo(houseListCommon[0].cityCode, houseListCommon[0].districtCode, "r", "cityAll", true);
                BA2141203.loadCityInfo(houseListCommon[0].vcityCode, houseListCommon[0].vdistrictCode, "r", "vCityAll", false);

                $("#houseAddress").val(houseListCommon[0].houseAddress);
                $("#vhouseAddress").html(houseListCommon[0].vhouseAddress);
                $("#houseCategoryName").html(houseListCommon[0].houseCategoryName);
            }
        }, function (data) {
            webToast("失败", "middle");
        });
    },
    bindCheck: function () {
        $("#fanhui").bind("touchstart", function () {
            window.location.href = backUrl;
        });
        $("#introduce").bind("touchstart", function () {
            var houseCode = $("#houseCode").val();
            window.location.href = 'BA21412031.html?houseCode=' + houseCode;
        });
        $(".address-js").bind("touchstart", function () {
            citySet.SelCity(this, event);
        });
        $("#saveInfo").bind("touchstart", function () {
            var houseShowName = $("#houseShowName").val();
            var houseAccount = $("#houseAccount").html();
            var accountPsd = $("#accountPsd").val();
            var wechat = $("#wechat").val();
            var qq = $("#qq").val();
            var email = $("#email").val();
            var fixedTel = $("#fixedTel").val();
            var fax = $("#fax").val();
            var province = $("#province").val();
            var city = $("#city").val();
            var district = $("#district").val();
            var houseAddress = $("#houseAddress").val();
            var slCode = $("#slCode").val();
            var houseCode = $("#houseCode").val();

            var houseTel = $("#houseTel").html();

            var provinceCode = $("#rhprovince")[0].title;
            var cityCode = $("#rhcity")[0].title;
            var districtCode = $("#rhdistrict")[0].title;

            var url = ConstantDef.getSaveHouseInfoUrlUrl();
            var data = {
                "param": {
                    "slCode": slCode,
                    "houseCode": houseCode,
                    "houseAccount": houseAccount,
                    "houseTel": houseTel,
                    "houseShowName": houseShowName,
                    "accountPsd": accountPsd,
                    "wechat": wechat,
                    "qq": qq,
                    "email": email,
                    "fixedTel": fixedTel,
                    "fax": fax,
                    "provinceCode": provinceCode,
                    "cityCode": cityCode,
                    "districtCode": districtCode,
                    "houseAddress": houseAddress
                }
            };
            HttpClient.post(url, data, function (data) {
                webToast(data.message, "middle");
            })
        });
    }
}
//页面入口

window.onload = window.setTimeout(BA2141203.init, 500);

function eventBackButton() {
    var flag = true;
    var isFocus = $("input").is(":focus");
    if (isFocus && flag) {
        document.removeEventListener("backbutton", eventBackButton, false); //注销返回键
        //3秒后重新注册
        var intervalID = window.setInterval(
            function () {
                window.clearInterval(intervalID);
                document.addEventListener("backbutton", eventBackButton, false); //返回键
            },
            3000);
        flag = false;
        //$("input").blur();
    } else {
        window.location.href = 'BA2141202.html';
    }
}