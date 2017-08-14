/**
 * Created by zhu_kai1 on 2016/7/13.
 */
var provinceData;
var cityDta;
var districtData;
var slRecbookId="";
$(function(){
      slRecbookId = request.QueryString("slRecbookId");
    if(null !=slRecbookId && slRecbookId !=''){
        selectAddress(slRecbookId);
        $("#add_address_").html("保存");
    }
});

var request = {
    QueryString: function (val) {
        var uri = window.location.search;
        var re = new RegExp("" + val + "=([^&?]*)", "ig");
        return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
    }
}

/**获取管家地址信息**/
function selectAddress(slRecbookId){
    var flickerAPI = url+'msk-bs/api/bs/search/houseBook';
    var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {"slRecbookId": slRecbookId}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'JSON',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "F"){
                alert("失败");
            }else{
                var length = data.result.length;
                var city = $("#city");
                for(var i=0;i<length;i++){
                    var result =  data.result[i];
                    $("#u19_input").val(result.buyerName);
                    $("#u24_input").val(result.telNum);
                    $("#u44_input").val(result.address);
                    $("#city").val(result.provinceName+"-"+result.cityName+"-"+result.districtName);
                    var hprovinces = $('<input>', {type: 'hidden', name: "hprovince", title:result.provinceCode, id: "hprovince", val: result.provinceName});
                    city.after(hprovinces);
                    var hcitys = $('<input>', {type: 'hidden', name: "hcity", title: result.cityCode, id: "hcity", val: result.cityName});
                    city.after(hcitys);
                    var hdistrict = $('<input>', {type: 'hidden', name: "hdistrict", title:result.districtCode, id: "hdistrict", val: result.districtName});
                    city.after(hdistrict);
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
}


// 获取省份
function getProvince(){
    var flickerAPI = url+'msk-district/api/district/query/province';
    var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'JSON',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "F"){
                alert("失败");
            }else{
                provinceData= data.result.provinceList;
            }
        },
        error:function(){
            alert("error");
        }
    });
}


// 获取城市
function getCity(provinceCode){
    var flickerAPI = url+'msk-district/api/district/query/city';
    var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {'provinceCode':provinceCode}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'JSON',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "F"){
                alert("失败");
            }else{
                cityDta = data.result.cityList;
            }
        },
        error:function(){
            alert("error");
        }
    });
}


// 获取区县
function getDistrict(cityCode){
    var flickerAPI = url+'msk-district/api/district/query/district';
    var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {'cityCode':cityCode}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'JSON',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "F"){
                alert("失败");
            }else{
                districtData =  data.result.districtList;
            }
        },
        error:function(){
            alert("error");
        }
    });
}


function insertAddress(){
    var buyerName = $("#u19_input").val();
    var telNum = $("#u24_input").val();
    var address = $("#u44_input").val();
    var provinceCode = $("#hprovince")[0].title;
    var provinceName = $("#hprovince").val();
    var cityCode = $("#hcity")[0].title;
    var cityName = $("#hcity").val();
    var districtCode = $("#hdistrict")[0].title;
    var districtName = $("#hdistrict").val();
    var flickerAPI ="";
    var fullAddress= provinceName+cityName+districtName+address;
    var  paramData;
    if(slRecbookId != null && slRecbookId !=''){
        // 编辑
        flickerAPI =url+'msk-bs/api/bs/modify/houseBook';
        paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {'slRecbookId':slRecbookId,'houseCode':houseCode,'buyerName': buyerName,'telNum':telNum,'provinceCode':provinceCode,'cityCode':cityCode,'districtCode':districtCode,'address':address,'fullAddress':fullAddress}};
    }else{
        // 新增
        var houseCode = localStorage.houseCode;
        flickerAPI = url+'msk-bs/api/bs/add/houseBook';
         paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {'houseCode':houseCode,'buyerName': buyerName,'telNum':telNum,'provinceCode':provinceCode,'cityCode':cityCode,'districtCode':districtCode,'address':address,'fullAddress':fullAddress}};
    }
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'JSON',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "F"){
                alert("失败");
            }else{
                window.location = "BA2141112.html";
            }
        },
        error:function(){
            alert("error");
        }
    });
}

$("#city").click(function (e) {
    SelCity(this,e);
});