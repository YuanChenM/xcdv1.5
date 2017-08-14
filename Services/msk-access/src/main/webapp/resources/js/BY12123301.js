/**
 * Created by tao_zhifa on 2016/8/1.
 */

$(function() {

    var id = request.QueryString("id");
    if(id != null){
        var flickerAPI= url + "/by/deliveryAddr/queryById"
        var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param":{"filterMap":{"id":id}}
        };
        $.ajax({
            type: "POST",
            async: false,
            url: flickerAPI,
            timeout: 60,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(paramData),
            success: function (data) {
                if (data.status == "S") {
                    var lists = data.result;
                    getProvice(null);
                    getCity(lists.provinceCode,lists.cityCode);
                    getDistrict(lists.cityCode,lists.districtCode);
                    $("#s_province").val(lists.provinceCode);
                    $("#s_city").val(lists.cityCode);
                    $("#s_district").val(lists.districtCode);
                    //$("#s_province").find("option[value='"+lists.provinceCode+"']").attr("selected",true);
                    //$("#s_city").find("option[text='"+lists.cityName+"']").attr("selected",true);
                    //$("#s_district").find("option[text='"+lists.districtName+"']").attr("selected",true)
                    $("#deliveryAddr").val(lists.deliveryAddr);
                    $("#referenceAddr").val(lists.referenceAddr);
                    $("#manageAddr").val(lists.manageAddr);
                    $("#recPerName").val(lists.recPerName);
                    $("#recPerTel").val(lists.recPerTel);
                    $("#recPerWechat").val(lists.recPerWechat);
                    $("#recPerQq").val(lists.recPerQq);
                    if(lists.isDefault == "1"){
                        $("#isDefault").attr("checked","true");
                    }
                    $("#s_province").selectmenu("refresh");
                    $("#s_city").selectmenu("refresh");
                    $("#s_district").selectmenu("refresh");
                    $("#isDefault").checkboxradio("refresh");
                }
            },
            error: function () {
                alert("error");
            }
        });
    }
    getProvice(null);
});

var request={
    QueryString : function(val)
    {var uri = window.location.search;
        var re = new RegExp("" +val+ "=([^\&\?]*)", "ig");
        return ((uri.match(re))?(uri.match(re)[0].substr(val.length+1)):null); }
}
//买家收货地址保存
function receiveAddrSave() {
    var id = "";

    var deliveryAddr = $("#deliveryAddr").val();
    if (deliveryAddr == "") {
        $("#errorMessage").css("display", "block");
        $("#errorMessage").text("请填写买家配送详细地址");
        return false;
    }
    var recPerName = $("#recPerName").val();
    if (recPerName == "") {
        $("#errorMessage").css("display", "block");
        $("#errorMessage").text("收货联系人");
        return false;
    }
    var recPerTel = $("#recPerTel").val();
    if (recPerTel == "") {
        $("#errorMessage").css("display", "block");
        $("#errorMessage").text("联系电话");
        return false;
    }
    var s_province = $("#s_province option:selected").text();
    if (s_province == "") {
        $("#errorMessage").css("display", "block");
        $("#errorMessage").text("请选择省份");
        return false;
    }
    var s_city = $("#s_city option:selected").text();
    if (s_city == "") {
        $("#errorMessage").css("display", "block");
        $("#errorMessage").text("请选择市");
        return false;
    }
    var s_district = $("#s_district option:selected").text();
    if (s_district == "") {
        $("#errorMessage").css("display", "block");
        $("#errorMessage").text("请选择区(县)");
        return false;
    }

    var id = request.QueryString("id");
    var provinceCode =$("#s_province option:selected").val();
    var cityCode = $("#s_city option:selected").val();
    var districtCode = $("#s_district option:selected").val();
    var referenceAddr = $("#referenceAddr").val();
    var manageAddr = $("#manageAddr").val();
    var recPerWechat = $("#recPerWechat").val();
    var recPerQq = $("#recPerQq").val();
    var isDefault = $("#isDefault");
    if(isDefault.is(":checked")==true){
        isDefault = 1;
    }else{
        isDefault = 0;
    }

    var flickerAPI= url + "/by/deliveryAddr/update"
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param":
    {
        "id":id,
        "buyerId": localStorage.buyerId,
        "provinceCode":provinceCode,
        "provinceName":s_province,
        "cityCode":cityCode,
        "cityName":s_city,
        "districtCode":districtCode,
        "districtName":s_district,
        "deliveryAddr":deliveryAddr,
        "referenceAddr":referenceAddr,
        "manageAddr":manageAddr,
        "recPerName":recPerName,
        "recPerTel":recPerTel,
        "recPerWechat":recPerWechat,
        "recPerQq":recPerQq,
        "isDefault":isDefault
    }};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                window.location = "BY121233.html";
            }
        },
        error: function () {
            alert("error");
        }
    });
}
//获得所有省份信息
function getProvice(provinceCode){
    var flickerAPI = url+"/by/getProvinceBean";
    var paramData =  {"client": "abcd","auth": "xxxx","loginid": "a124","param":{}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            var provinces = data.result.provinceList;
            var province  = $("#s_province");
            var city = $("#s_city");
            if(provinceCode == null){
                province.html();
                province.append("<option value=''>请选择</option>")
                city.html();
                city.append("<option value=''>请选择</option>");
                city.selectmenu("refresh");
            }
            for(var i=0;i< provinces.length;i++){
                if(provinceCode != null && provinceCode == provinces[i].provinceCode){
                    province.append("<option value="+provinces[i].provinceCode + "selected>" + provinces[i].provinceName+"</option>");
                }else{
                    province.append("<option value="+provinces[i].provinceCode + ">" + provinces[i].provinceName+"</option>");
                }
                province.selectmenu("refresh")
            }

        },
        error: function () {
            alert("error");
        }
    });
}
//获得物流区下的城市信息
function getCity(provinceCode,cityCode){
    var flickerAPI = url+'/by/getCitys';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"flag":1,"provinceCode":provinceCode}};
    $.ajax({

        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            var citys = data.result.cityList;
            var city = $("#s_city");
            var district = $("#s_district");
            if(cityCode == null){
                city.html("");
                city.append("<option value=''>请选择</option>");
                district.html("");
                district.append("<option value=''>请选择</option>");
                district.selectmenu("refresh");
            }
            for(var i=0; i< citys.length; i++){
                if(cityCode != null && cityCode == citys[i].cityCode){
                    city.append("<option value="+citys[i].cityCode+" selected>"+citys[i].cityName+"</option>");
                }else{
                    city.append("<option value="+citys[i].cityCode+">"+citys[i].cityName+"</option>");
                }
                city.selectmenu("refresh");

            }
        },
        error:function(){
            alert("error");
        }
    });
}
//获得城市下的区信息
function getDistrict(cityCode,districtCode){
    var flickerAPI = url+'/by/getDistricts';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"cityCode":cityCode}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            var districts = data.result.districtList;
            var district = $("#s_district");
            if(districtCode == null){
                district.html("");
                district.append("<option value=''>请选择</option>");
            }
            for(var i=0; i< districts.length; i++){
                if(districtCode != null && districtCode == districts[i].districtCode){
                    district.append("<option value="+districts[i].districtCode+" selected>"+districts[i].districtName+"</option>");
                }else{
                    district.append("<option value="+districts[i].districtCode+">"+districts[i].districtName+"</option>");
                }
                district.selectmenu("refresh");
            }
        },
        error:function(){
            alert("error");
        }
    });
}

/**
 * 城市下拉框选择
 */
function cityChange() {
    var cityCode = $("#s_city option:selected").val();
    getDistrict(cityCode,null);
}

function proChange(){
    var provinceCode = $("#s_province option:selected").val();
    getCity(provinceCode,null);
}
//返回前一个画面
function returnPage(){
    window.location = "BY121233.html";
}