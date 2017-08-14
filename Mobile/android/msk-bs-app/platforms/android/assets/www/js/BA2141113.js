/**
 * Created by zhu_kai1 on 2016/7/13.
 */
var slRecbookId="";
var accessType="";
var backUrl="";
$(function(){
    accessType =localStorage.accessType;
    slRecbookId = BA2141113.QueryString("slRecbookId");
    if(null !=slRecbookId && slRecbookId !=''){
        setTimeout("BA2141113.selectAddress(slRecbookId)",100);
        $(".span-sure").html("保存");
    }
    setTimeout("BA2141113.bindCity()",200);
    BA2141113.bindFh();
    BA2141113.insertAddress();
    if(accessType == 3){
        $("#u19_input").val(localStorage.slContact);
        $("#u24_input").val(localStorage.slTel);
        document.getElementById("u44_input").required = false;
        $("#addressDeatil").attr("hidden","true");
    }
    var  isBuy = BA2141113.QueryString("isBuy");
    var priceCycle = BA2141113.QueryString("priceCycle");
    if(null !=isBuy && isBuy !=''){
        var productCode = BA2141113.QueryString("productCode");
        var productNum = BA2141113.QueryString("productNum");
        var productPrice = BA2141113.QueryString("productPrice");
        var pdName = BA2141113.QueryString("pdName");
        backUrl = "BA2141112.html?isBuy=1&productCode=" + productCode + "&productNum=" + productNum + "&productPrice=" + productPrice+"&priceCycle="+priceCycle+"&pdName="+pdName;
    }else{
        var carID = BA2141113.QueryString("carId");
        var carDetailIds = BA2141113.QueryString("carDetailIds");
        backUrl = "BA2141112.html?carID="+carID+"&carDetailIds="+carDetailIds+"&priceCycle="+priceCycle;
    }
});


var BA2141113 = {
    chooseHtml:function(){
        var  isBuy = BA2141113.QueryString("isBuy");
        var priceCycle = BA2141113.QueryString("priceCycle");
        if(null !=isBuy && isBuy !=''){
            var productCode = BA2141113.QueryString("productCode");
            var productNum = BA2141113.QueryString("productNum");
            var productPrice = BA2141113.QueryString("productPrice");
            var pdName = BA2141113.QueryString("pdName");
            window.location.href = "BA2141112.html?isBuy=1&productCode=" + productCode + "&productNum=" + productNum + "&productPrice=" + productPrice+"&priceCycle="+priceCycle+"&pdName="+pdName;
        }else{
            var carID = BA2141113.QueryString("carId");
            var carDetailIds = BA2141113.QueryString("carDetailIds");
            window.location.href = "BA2141112.html?carID="+carID+"&carDetailIds="+carDetailIds+"&priceCycle="+priceCycle;
        }
    },
    QueryString: function (val) {
        var uri = window.location.search;
        var svalue =uri.match(new RegExp("[\?\&]" + val + "=([^\&]*)(\&?)","i"));
        return svalue ? svalue[1] : svalue;
    },
    /**返回事件绑定*/
    bindFh:function(){
        $(".head-back").bind("touchstart",function(){
            BA2141113.chooseHtml();
        })
    },
    selectAddress:function(slRecbookId) {
        var url = ConstantDef.getFindHouseBookServerUrl();
        var paramData = {param: {"slRecbookId": slRecbookId,"accessType":accessType}};
        HttpClient.post(url,paramData,function(data){
            if (data.status == "F") {
                webToast("查询收货地址失败","middle");
            } else {
                var length = data.result.length;
                var city = $("#city");
                for (var i = 0; i < length; i++) {
                    var result = data.result[i];
                    $("#u19_input").val(result.buyerName);
                    $("#u24_input").val(result.telNum);
                    $("#u44_input").val(result.address);
                    $("#city").val(result.provinceName + "-" + result.cityName + "-" + result.districtName);
                    var hprovinces = $('<input>', {
                        type: 'hidden',
                        name: "hprovince",
                        title: result.provinceCode,
                        id: "areahprovince",
                        val: result.provinceName
                    });
                    city.after(hprovinces);
                    var hcitys = $('<input>', {
                        type: 'hidden',
                        name: "hcity",
                        title: result.cityCode,
                        id: "areahcity",
                        val: result.cityName
                    });
                    city.after(hcitys);
                    var hdistrict = $('<input>', {
                        type: 'hidden',
                        name: "hdistrict",
                        title: result.districtCode,
                        id: "areahdistrict",
                        val: result.districtName
                    });
                    city.after(hdistrict);
                }
            }
        },function(data){
            webToast("操作失败","middle");
        });
    },
    insertAddress:function() {
        $("#addressForm").html5Validate(function () {
            var buyerName = $("#u19_input").val();
            var telNum = $("#u24_input").val();
            var address = $("#u44_input").val();
            var provinceCode = $("#areahprovince")[0].title;
            var provinceName = $("#areahprovince").val();
            var cityCode = $("#areahcity")[0].title;
            var cityName = $("#areahcity").val();
            var districtCode = $("#areahdistrict")[0].title;
            var districtName = $("#areahdistrict").val();
            var url = "";
            var fullAddress =provinceName + cityName + districtName + address;
            var paramData;
            var houseCode = localStorage.houseCode;
            if (slRecbookId != null && slRecbookId != '') {
                // 编辑
                url = ConstantDef.getFindModifyHouseBookServerUrl();
                if(accessType == 2){
                    paramData = {
                        param: {
                            "accessType":accessType,
                            "slRecbookId": slRecbookId,
                            "houseCode": houseCode,
                            "buyerName": buyerName,
                            "telNum": telNum,
                            "provinceCode": provinceCode,
                            "cityCode": cityCode,
                            "districtCode": districtCode,
                            "address": address,
                            "provinceName":provinceName,
                            "cityName":cityName,
                            "districtName":districtName,
                            "fullAddress": fullAddress
                        }
                    };
                }else{
                    paramData = {
                        param: {
                            "accessType":accessType,
                            "slRecbookId": slRecbookId,
                            "buyerName": buyerName,
                            "telNum": telNum,
                            "provinceCode": provinceCode,
                            "cityCode": cityCode,
                            "districtCode": districtCode,
                            "address": address,
                            "provinceName":provinceName,
                            "cityName":cityName,
                            "districtName":districtName,
                            "fullAddress": fullAddress
                        }
                    };
                }
            } else {
                // 新增
                url = ConstantDef.getFindAddHouseBookServerUrl();
                if(accessType == 2){
                    paramData = {
                        param: {
                            "accessType":accessType,
                            "houseCode": houseCode,
                            "buyerName": buyerName,
                            "telNum": telNum,
                            "provinceCode": provinceCode,
                            "cityCode": cityCode,
                            "districtCode": districtCode,
                            "address": address,
                            "provinceName":provinceName,
                            "cityName":cityName,
                            "districtName":districtName,
                            "fullAddress": fullAddress
                        }
                    };
                }else{
                    paramData = {
                        param: {
                            "slCode": localStorage.slCode,
                            "accessType":accessType,
                            "buyerName": buyerName,
                            "telNum": telNum,
                            "provinceCode": provinceCode,
                            "cityCode": cityCode,
                            "districtCode": districtCode,
                            "address": address,
                            "provinceName":provinceName,
                            "cityName":cityName,
                            "districtName":districtName,
                            "fullAddress": fullAddress
                        }
                    };
                }
            }
            console.log("url:" + url + "");
            console.log("paramData:" + JSON.stringify(paramData) + "");
            HttpClient.post(url, paramData, function (data) {
                if (data.status == "F") {
                    webToast("添加收获地址失败","middle");
                } else {
                    BA2141113.chooseHtml();
                }
            },function(data){
                webToast("操作失败","middle");
            });
            /*});*/
        });
    },
    bindCity:function(){
        $(".lgcsClass").bind("touchstart",function(){
            var provinceCode =  localStorage.provinceCode;
            citySet.SelCity(this,event,provinceCode);
        })
    }
}
