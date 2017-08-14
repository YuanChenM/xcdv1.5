/**
 * Created by ni_shaotang on 2016/7/20.
 */
var pdFeatureCommon = [];
var pdWeightCommon = [];
var pdGradeCommon = [];
var pdPriceWayCommon = [];
var pdStockCommon = [];
var pdCodeInfo = "";
var pdFeature = "";
var pdWeight = "";
var pdGrade = "";
var pdFeatureName = "";
var pdWeightName = "";
var pdGradeName = "";
var pdFeatureFlag = true;
var pdWeightFlag = true;
var pdPrice = 0;
var priceCycle = 0;
var backUrl = localStorage.html;
$("#messageLoading").modal();
$("#simplemodal-container").show();
$('#messageLoading').addClass('animated zoomIn');
var BA2141107 = {
    init: function () {
        var pdCode = commonUtil.QueryString("pdCode");
        if (pdCode.length >= 5) {
            pdCodeInfo = pdCode.substring(0, 5);
        }
        if (pdCode.length >= 7) {
            pdFeature = pdCode.substring(5, 7);
        }
        if (pdCode.length >= 9) {
            pdWeight = pdCode.substring(7, 9);
        }
        if (pdCode.length >= 10) {
            pdGrade = pdCode.substring(9, 10);
        }
        console.log(pdGrade);
        //加载产品特征
        BA2141107.getDetailSlider();
        BA2141107.loadPdFeature();
        BA2141107.bindAddCar();
        BA2141107.bindFh();
        BA2141107.bindAddShop();
    },
    getDetailSlider:function(){
        var pdCode=window.location.search.split("=")[1];
        var url=ConstantDef.getDetailSliderInfoUrl();
        var paramData={
            "siteCode":1,
            "auth":"MSK00001",
            "loginId":"msk01",
            "param":{
                "pdCode":pdCode
            }
        };
        HttpClient.post(url,paramData, function (data) {
            console.log(data);

            var html="";
            for(var i=0;i<data.result.pdScrollImgList.length;i++){
                html+="<div class='swiper-slide '><img src='"+data.result.pdScrollImgList[i].pdScrollImg+"'></div>";
            }
            //网容器添加元素
            $(".swiper-wrapper").html(html);
            var mySwiper = new Swiper ('.swiper-container', {
                pagination : '.swiper-pagination',
                loop:false,
                grabCursor: true,
                autoplay: 2500,
                observer: true,//修改swiper自己或子元素时，自动初始化swiper
                observeParents: true//修改swiper的父元素时，自动初始化swiper
            })
        },function(data,e){
            webToast("操作失败","middle");
        })
    },
    /**
     * 加载产品阶梯通道
     */
    loadPdPriceWay: function () {
        var pdCode = pdCodeInfo + pdFeature + pdWeight + pdGrade;
        var url = ConstantDef.getFindPriceWayServerUrl();
        var data = {
            param: pdCode
        };
        pdPriceWayCommon = null;
        HttpClient.post(url, data, function (data) {
            $("#pdPriceWay").html("");
            if (data.status == "S") {
                pdPriceWayCommon = data.result;
                priceCycle = data.returnCode;
                for (var i = 0; i < 9; i++) {
                    var wayId = "way" + (i + 1);
                    var priceId = "price" + (i + 1);
                    var boxId = "box" + (i + 1);
                    if (pdPriceWayCommon.length > i) {
                        $("#" + wayId).html(pdPriceWayCommon[i].boxName);
                        $("#" + priceId).html(pdPriceWayCommon[i].priceOfKg == null ? "" : pdPriceWayCommon[i].priceOfKg);
                        $("#" + boxId).html(pdPriceWayCommon[i].priceOfBox == null ? "" : pdPriceWayCommon[i].priceOfBox);
                    } else {
                        $("#" + wayId).html("");
                        $("#" + priceId).html("");
                        $("#" + boxId).html("");
                    }
                }
            }
            BA2141107.getOldPrice();
        }, function (data) {
            webToast("操作失败", "middle");
        });
    },
    /**
     * 加载产品特征
     */
    loadPdFeature: function () {
        var pdCode = pdCodeInfo + pdFeature + pdWeight + pdGrade;
        var url = ConstantDef.getFindPdFeatureServerUrl();
        var data = {
            param: {
                "lgcsCode": localStorage.lgcsCode,
                "pdCode": pdCode
            }
        };
        HttpClient.post(url, data, function (data) {
            pdFeatureCommon = data.result;
            var pdStr = "";
            $("#pdBreed").html(data.returnCode);
            var copyFeature = $("#disFeature").html();
            var featureShow = $("#featureShow").html();
            var isExistence = false;
            for (var i = 0; i < pdFeatureCommon.length; i++) {
                var fCode = pdFeatureCommon[i].featureCode;
                var fName = pdFeatureCommon[i].featureName;
                if (i == 0 && pdFeature == "") {
                    pdFeature = fCode;
                    pdFeatureName = fName;
                } else if (pdFeature == fCode) {
                    pdFeatureName = fName;
                    isExistence = true;
                }
                if (i == 3) {
                    if (pdFeatureFlag) {
                        pdStr += featureShow;
                        pdFeatureFlag = false;
                        break;
                    }
                }
                pdStr += copyFeature.replace("copyFeature", fName).replace("featureCode", fCode).replace("featureCode", fCode);
            }
            $("#pdFeature").html(pdStr);
            if(!isExistence && pdFeatureCommon.length>0){
                pdFeature = pdFeatureCommon[0].featureCode;
                pdFeatureName = pdFeatureCommon[0].featureName;
            }
            $("#feature" + pdFeature).addClass("on");
            //加载产品净重
            BA2141107.loadPdWeight();
        }, function (data) {
            webToast("操作失败", "middle");
        });
    },

    /**
     * 加载产品净重
     */
    loadPdWeight: function () {
        var pdCode = pdCodeInfo + pdFeature + pdWeight + pdGrade;
        var url = ConstantDef.getFindPdWeightServerUrl();
        var data = {
            param: {
                "lgcsCode": localStorage.lgcsCode,
                "pdCode": pdCode
            }
        };
        HttpClient.post(url, data, function (data) {
            pdWeightCommon = data.result;
            var pdStr = "";
            var copyWeight = $("#disWeight").html();
            var weightShow = $("#weightShow").html();
            var isExistence = false;
            for (var i = 0; i < pdWeightCommon.length; i++) {
                var wCode = pdWeightCommon[i].weightCode;
                var wName = pdWeightCommon[i].weightName;
                if (i == 0 && pdWeight == "") {
                    pdWeight = wCode;
                    pdWeightName = wName;
                } else if (pdWeight == wCode) {
                    pdWeightName = wName;
                    isExistence = true;
                }
                if (i == 3) {
                    if (pdWeightFlag) {
                        pdStr += weightShow;
                        pdWeightFlag = false;
                        break;
                    }
                }
                pdStr += copyWeight.replace("copyWeight", wName).replace("weightCode", wCode).replace("weightCode", wCode);
            }
            $("#pdWeight").html(pdStr);

            if(!isExistence && pdWeightCommon.length>0){
                pdWeight = pdWeightCommon[0].weightCode;
                pdWeightName = pdWeightCommon[0].weightName;
            }
            $("#weight" + pdWeight).addClass("on");
            //加载产品等级
            BA2141107.loadPdGrade();
        }, function (data) {
            webToast("操作失败", "middle");
        });
    },
    /**
     * 加载产品等级
     */
    loadPdGrade: function () {
        var pdCode = pdCodeInfo + pdFeature + pdWeight + pdGrade;
        var url = ConstantDef.getFindPdGradeServerUrl();
        var data = {
            param: {
                "lgcsCode": localStorage.lgcsCode,
                "pdCode": pdCode
            }
        };
        HttpClient.post(url, data, function (data) {
            pdGradeCommon = data.result;
            var pdStr = "";
            var copyGrade = $("#disGrade").html();
            var isExistence = false;
            for (var i = 0; i < pdGradeCommon.length; i++) {
                var gCode = pdGradeCommon[i].gradeCode;
                var gName = pdGradeCommon[i].gradeName;
                console.log(pdGrade);
                if (i == 0 && pdGrade == "") {
                    pdGrade = gCode;
                    pdGradeName = gName;
                } else if (pdGrade == gCode) {
                    pdGradeName = gName;
                    isExistence = true;
                }
                console.log(pdGrade);
                pdStr += copyGrade.replace("copyGrade", gName).replace("gradeCode", gCode).replace("gradeCode", gCode);
            }
            $("#pdGrade").html(pdStr);
            if(!isExistence && pdGradeCommon.length>0){
                pdGrade = pdGradeCommon[0].gradeCode;
                pdGradeName = pdGradeCommon[0].gradeName;
            }
            $("#grade" + pdGrade).addClass("on");
            //加载价盘阶梯
            BA2141107.loadPdPriceWay();
            BA2141107.loadStack();//加载库存
            $("#simplemodal-container").hide();
            $('.loader-inner').addClass('animated zoomOut');
            $("#messageLoading").addClass('animated zoomOut');
            $("#simplemodal-overlay").remove();
        }, function (data) {
            webToast("操作失败", "middle");
        });
    },
    loadStack: function () {//加载库存
        var pdCode = pdCodeInfo + pdFeature + pdWeight + pdGrade;
        var accessType = localStorage.accessType;
        var slCode = "0000000";
        var sellerType = 1;
        var lgcsCode = localStorage.lgcsCode;
        if (accessType == 2) {
            slCode = localStorage.sellerCode;
            sellerType = 2;
        }
        var url = ConstantDef.getFindProductStockUrl();
        var paramData = {
            param: {
                "slCode": slCode,
                "lgcsCode": lgcsCode,
                "sellerType": sellerType,
                "pdCodeList": [pdCode]
            }
        };
        $("#isSaleNum").html(0);
        HttpClient.post(url, paramData, function (data) {
            pdStockCommon = data.result;
            if (pdStockCommon.length > 0) {
                $("#isSaleNum").html(pdStockCommon[0].stockCnt);
            }
        }, function (data) {
            webToast("操作失败", "middle");
        });
    },
    moreFeature: function () {
        var pdStr = "";
        var copyFeature = $("#disFeature").html();
        var featureShow = $("#featureShow").html();
        for (var i = 0; i < pdFeatureCommon.length; i++) {
            if (i == 3 && pdFeatureFlag) {
                break;
            }
            var features = copyFeature.replace("copyFeature", pdFeatureCommon[i].featureName).replace("featureCode", pdFeatureCommon[i].featureCode).replace("featureCode", pdFeatureCommon[i].featureCode);
            if (pdFeatureCommon[i].featureCode == pdFeature) {
                features = features.replace("type", "type on");
            }
            pdStr += features;
        }
        if (pdFeatureFlag) {
            pdStr += featureShow;
            pdFeatureFlag = false;
        } else {
            pdStr += featureShow.replace("更多", "收起");
            pdFeatureFlag = true;
        }
        $("#pdFeature").html(pdStr);
    },
    moreWeight: function () {
        var pdStr = "";
        var copyWeight = $("#disWeight").html();
        var weightShow = $("#weightShow").html();
        for (var i = 0; i < pdWeightCommon.length; i++) {
            if (i == 3 && pdWeightFlag) {
                break;
            }
            var weights = copyWeight.replace("copyWeight", pdWeightCommon[i].weightName).replace("weightCode", pdWeightCommon[i].weightCode).replace("weightCode", pdWeightCommon[i].weightCode);
            if (pdWeightCommon[i].weightCode == pdWeight) {
                weights = weights.replace("type", "type on");
            }
            pdStr += weights;
        }
        if (pdWeightFlag) {
            pdStr += weightShow;
            pdWeightFlag = false;
        } else {
            pdStr += weightShow.replace("更多", "收起");
            pdWeightFlag = true;
        }
        $("#pdWeight").html(pdStr);
    },
    checkFeature: function (featureCode) {
        pdFeature = featureCode;
        pdFeatureName = $("#feature" + featureCode).html();
        $("#pdFeature a").removeClass('on');
        $("#feature" + featureCode).addClass("on");
        //加载产品净重
        BA2141107.loadPdWeight();
    },
    checkWeight: function (weightCode) {
        pdWeight = weightCode;
        pdWeightName = $("#weight" + weightCode).html();
        $("#pdWeight a").removeClass('on');
        $("#weight" + weightCode).addClass("on");
        //加载价盘阶梯
        BA2141107.loadPdGrade();
    },
    checkGrade: function (gradeCode) {
        pdGrade = gradeCode;
        pdGradeName = $("#grade" + gradeCode).html();
        $("#pdGrade a").removeClass('on');
        $("#grade" + gradeCode).addClass("on");
        //加载价盘阶梯
        BA2141107.loadPdPriceWay();
        BA2141107.loadStack();//加载库存
    },
    /**
     * 加入购物车
     */
    bindAddCar: function () {
        $("#addCar").bind("touchstart", function () {
            var url = ConstantDef.getFindAddCarServerUrl();
            var pdNum = $("#pdNum").val();
            var pdBreed = $("#pdBreed").html() + "|" + pdFeatureName + "|" + pdWeightName + "|" + pdGradeName;
            var isSaleNum = parseInt($("#isSaleNum").html());
            var showBoxPrice = $("#showBoxPrice").html();
            var slTel = localStorage.accessAccountName;
            var buyersType = localStorage.accessType;
            if (!slTel) {
                webToast("登录失效，请重新登录", "middle");
                window.location.href = 'BA2141101.html';
                return;
            }
            if (isNaN(pdNum) || pdNum == 0) {
                webToast("请输入购买数量", "middle");
                return;
            }
            if (pdNum > isSaleNum) {
                webToast("购买数量过多，请重新输入", "middle");
                return;
            }
            if (pdPriceWayCommon.length < 1) {
                webToast("该产品的阶梯价格不存在，请选择其它产品", "middle");
                return;
            }
            if (showBoxPrice.length < 1 || showBoxPrice == "-") {
                webToast("当前购买数量不存在价格，请重新输入数量", "middle");
                return;
            }
            if (pdFeature.length == 0) {
                webToast("请选择产品规格", "middle");
                return;
            }
            if (pdWeight.length == 0) {
                webToast("请选择产品净重", "middle");
                return;
            }
            if (pdGrade.length == 0) {
                webToast("请选择产品等级", "middle");
                return;
            }
            var pdCode = pdCodeInfo + pdFeature + pdWeight + pdGrade;
            var paramData = {
                param: {
                    "slTel": slTel,
                    "buyersType": buyersType,
                    "pdCode": pdCode,
                    "pdNum": pdNum,
                    "status": 0,
                    "pdName": encodeURI(pdBreed),
                    "priceWay": pdPriceWayCommon
                }
            };
            HttpClient.post(url, paramData,
                function (data) {
                    webToast(data.message, "middle");
                }, function (data) {
                    webToast("添加购物车成功", "middle");
                });
        })

    },
    /**
     * 购买
     */
    bindAddShop: function () {
        $("#goShop").bind("touchstart", function () {
            var url = ConstantDef.getFindAddCarServerUrl();
            var pdNum = $("#pdNum").val();
            var pdBreed = $("#pdBreed").html();
            var isSaleNum = parseInt($("#isSaleNum").html());
            var showBoxPrice = $("#showBoxPrice").html();
            var slTel = localStorage.accessAccountName;
            var buyersType = localStorage.accessType;
            if (!slTel) {
                webToast("登录失效，请重新登录", "middle");
                window.location.href = 'BA2141101.html';
                return;
            }
            if (isNaN(pdNum) || pdNum == 0) {
                webToast("请输入购买数量", "middle");
                return;
            }
            if (pdNum > isSaleNum) {
                webToast("购买数量过多，请重新输入", "middle");
                return;
            }
            if (pdPriceWayCommon.length < 1) {
                webToast("该产品的阶梯价格不存在，请选择其它产品", "middle");
                return;
            }
            if (showBoxPrice.length < 1 || showBoxPrice == "-") {
                webToast("当前购买数量不存在价格，请重新输入数量", "middle");
                return;
            }
            if (pdFeature.length == 0) {
                webToast("请选择产品规格", "middle");
                return;
            }
            if (pdWeight.length == 0) {
                webToast("请选择产品净重", "middle");
                return;
            }
            if (pdGrade.length == 0) {
                webToast("请选择产品等级", "middle");
                return;
            }
            var pdCode = pdCodeInfo + pdFeature + pdWeight + pdGrade;
            var pdName = $("#pdBreed").html() + "|" + pdFeatureName + "|" + pdWeightName + "|" + pdGradeName;
            window.location.href = "BA2141110.html?isBuy=1&productCode=" + pdCode
            + "&productNum=" + pdNum + "&productPrice=" + pdPrice + "&priceCycle=" + priceCycle + "&pdName=" + pdName;
        })
    },
    updateNum: function (num) {
        var pdNum = parseInt($("#pdNum").val());
        if (isNaN(pdNum)) {
            pdNum = 1;
        }
        num = pdNum + num;
        if (num < 1) {
            num = 1;
        }
        $("#pdNum").val(num);
        BA2141107.getOldPrice();
    },
    setNumDefault: function () {
        if (isNaN(pdNum)) {
            $("#pdNum").val(1);
            BA2141107.getOldPrice();
        }
    },
    getOldPrice: function () {
        var pdNum = parseInt($("#pdNum").val());
        var isSaleNum = parseInt($("#isSaleNum").html());
        var str = "";
        if (isNaN(pdNum)) {
            pdNum = "";
        } else if (pdNum < 1) {
            pdNum = 1;
        }
        if (pdNum > isSaleNum && isSaleNum != 0) {
            pdNum = isSaleNum;
        }
        $("#pdNum").val(pdNum);
        $("#showKgPrice").html("-");
        $("#showBoxPrice").html("-");
        if (pdNum != "") {
            for (var i = 0; i < pdPriceWayCommon.length; i++) {
                if (pdPriceWayCommon[i].orderLevel != 1) {
                    if (parseInt(pdPriceWayCommon[i].boxMin) <= pdNum && pdNum <= parseInt(pdPriceWayCommon[i].boxMax)) {
                        $("#showKgPrice").html(pdPriceWayCommon[i].priceOfKg);
                        $("#showBoxPrice").html(pdPriceWayCommon[i].priceOfBox);
                        pdPrice = pdPriceWayCommon[i].priceOfBox;
                    }
                } else {
                    if (parseInt(pdPriceWayCommon[i].boxMin) <= pdNum) {
                        $("#showKgPrice").html(pdPriceWayCommon[i].priceOfKg);
                        $("#showBoxPrice").html(pdPriceWayCommon[i].priceOfBox);
                        pdPrice = pdPriceWayCommon[i].priceOfBox;
                    }
                }
            }
        }
    },
    bindFh: function () {
        $("#fanhui").bind("touchstart", function () {
            window.location.href = backUrl;
        })
    }
}
window.onload = window.setTimeout(BA2141107.init, 200);