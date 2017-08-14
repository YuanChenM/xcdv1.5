/**
 * Created by ni_shaotang on 2016/7/20.
 */
var pdFeatureCommon = [];
var pdWeightCommon = [];
var pdGradeCommon = [];
var pdPriceWayCommon = [];
var pdFeature = "01";
var pdWeight = "01";
var pdGrade = "2";
/**
 * 获取url参数
 * @type {{QueryString: Function}}
 */
var request = {
    QueryString: function (val) {
        var uri = window.location.search;
        var re = new RegExp("" + val + "=([^&?]*)", "ig");
        return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
    }
}
/**
 * 加载产品阶梯通道
 */
function loadPdPriceWay() {
    var pdCode = request.QueryString("pdCode") + pdFeature + pdWeight + pdGrade;
    var flickerAPI = url + 'api/ba/pdPriceWay';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": pdCode};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'JSON',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            $("#pdPriceWay").html("");
            if (data.status == "S") {
                pdPriceWayCommon = data.result;
                var pdStr = "";
                var copyPriceWay = $("#disPrice").html();
                for (var i = 0; i < pdPriceWayCommon.length; i++) {
                    pdStr += copyPriceWay.replace("boxName", pdPriceWayCommon[i].boxName).replace("priceOfKg", pdPriceWayCommon[i].priceOfKg).replace("priceOfBox", pdPriceWayCommon[i].priceOfBox);
                }
                $("#pdPriceWay").html(pdStr);
            }
        },
        error: function () {
            alert("error");
        }
    });

}
/**
 * 加载产品特征
 */
function loadPdFeature() {
    var pdCode = request.QueryString("pdCode") + pdFeature + pdWeight + pdGrade;
    var flickerAPI = url + 'api/ba/productFeature';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": pdCode};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'JSON',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            pdFeatureCommon = data.result;
            var pdStr = "";
            $("#pdBreed").html(data.returnCode);
            var copyFeature = $("#disFeature").html();
            for (var i = 0; i < pdFeatureCommon.length; i++) {
                pdStr += copyFeature.replace("copyFeature", pdFeatureCommon[i].featureName).replace("featureCode", pdFeatureCommon[i].featureCode).replace("featureCode", pdFeatureCommon[i].featureCode);
            }
            $("#pdFeature").html(pdStr);
        },
        error: function () {
            alert("error");
        }
    });

}
/**
 * 加载产品净重
 */
function loadPdWeight() {
    var pdCode = request.QueryString("pdCode") + pdFeature + pdWeight + pdGrade;
    var flickerAPI = url + 'api/ba/productWeight';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": pdCode};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'JSON',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            pdWeightCommon = data.result;
            var pdStr = "";
            var copyWeight = $("#disWeight").html();
            for (var i = 0; i < pdWeightCommon.length; i++) {
                pdStr += copyWeight.replace("copyWeight", pdWeightCommon[i].weightName).replace("weightCode", pdWeightCommon[i].weightCode).replace("weightCode", pdWeightCommon[i].weightCode);
            }
            $("#pdWeight").html(pdStr);
        },
        error: function () {
            alert("error");
        }
    });
}
/**
 * 加载产品等级
 */
function loadPdGrade() {
    var flickerAPI = url + 'api/ba/productGrade';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124"};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'JSON',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            pdGradeCommon = data.result;
            var pdStr = "";
            var copyGrade = $("#disGrade").html();
            for (var i = 0; i < pdGradeCommon.length; i++) {
                pdStr += copyGrade.replace("copyGrade", pdGradeCommon[i].gradeName).replace("gradeCode", pdGradeCommon[i].gradeCode).replace("gradeCode", pdGradeCommon[i].gradeCode);
            }
            $("#pdGrade").html(pdStr);
        },
        error: function () {
            alert("error");
        }
    });
}
function checkFeature(featureCode) {
    pdFeature = featureCode;
    $("#pdFeature div").removeClass('spanCheck');
    $("#pdFeature div").addClass('spanNoCheck');
    $("#feature" + featureCode).removeClass("spanNoCheck");
    $("#feature" + featureCode).addClass("spanCheck");
    //加载价盘阶梯
    loadPdPriceWay();
    //加载产品净重
    loadPdWeight();
}
function checkWeight(weightCode) {
    pdWeight = weightCode;
    $("#pdWeight div").removeClass('spanCheck');
    $("#pdWeight div").addClass('spanNoCheck');
    $("#weight" + weightCode).removeClass("spanNoCheck");
    $("#weight" + weightCode).addClass("spanCheck");
    //加载价盘阶梯
    loadPdPriceWay();
}
function checkGrade(gradeCode) {
    pdGrade = gradeCode;
    $("#pdGrade div").removeClass('spanCheck');
    $("#pdGrade div").addClass('spanNoCheck');
    $("#grade" + gradeCode).removeClass("spanNoCheck");
    $("#grade" + gradeCode).addClass("spanCheck");
    //加载价盘阶梯
    loadPdPriceWay();
}
function addCar() {
    var flickerAPI = url + 'api/ba/addCar';
    var pdNum = $("#pdNum").val();
    var pdBreed = $("#pdBreed").html();
    var isSaleNum = parseInt($("#isSaleNum").html());
    var slTel = localStorage.accessAccountName;
    var buyersType = localStorage.accessType;
    if (!slTel) {
        alert("登录失效，请重新登录");
        return;
    }
    if (isNaN(pdNum)) {
        alert("请输入购买数量");
        return;
    }
    if (pdNum > isSaleNum) {
        alert("购买数量过多，请重新输入");
        return;
    }
    if (pdNum > isSaleNum) {
        alert("购买数量过多，请重新输入");
        return;
    }
    if (pdPriceWayCommon.length < 1) {
        alert("该产品的阶梯价格不存在，请选择其它产品");
        return;
    }
    var pdCode = request.QueryString("pdCode") + pdFeature + pdWeight + pdGrade;
    var param = {
        "slTel": slTel,
        "buyersType": buyersType,
        "pdCode": pdCode,
        "pdNum": pdNum,
        "pdName": pdBreed,
        "priceWay": pdPriceWayCommon
    };
    var paramData = {"siteCode": "abcd", "auth": "xxxx", "loginId": "a124", "param": param};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'JSON',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            alert(data.message);
        },
        error: function () {
            alert("error");
        }
    });
}

function getOldPrice() {
    var pdNum = parseInt($("#pdNum").val());
    var str = "";
    for (var i = 0; i < pdPriceWayCommon.length; i++) {
        if (pdPriceWayCommon[i].orderLevel != 1) {
            if (parseInt(pdPriceWayCommon[i].boxMin) <= pdNum && pdNum <= parseInt(pdPriceWayCommon[i].boxMax)) {
                str = pdPriceWayCommon[i].priceOfKg + "元/kg " + pdPriceWayCommon[i].priceOfBox + " 元/箱";
            }
        } else {
            if (parseInt(pdPriceWayCommon[i].boxMin) <= pdNum) {
                str = pdPriceWayCommon[i].priceOfKg + "元/kg " + pdPriceWayCommon[i].priceOfBox + " 元/箱";
            }
        }
        $("#showPriceWay").html(str);
    }
}
//页面入口
$(function () {
    //加载价盘阶梯
    loadPdPriceWay();
    //加载产品特征
    loadPdFeature();
    //加载产品净重
    loadPdWeight();
    //加载产品等级
    loadPdGrade();

});