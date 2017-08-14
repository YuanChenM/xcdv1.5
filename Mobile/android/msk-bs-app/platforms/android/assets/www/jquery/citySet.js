var provinceID="";
var cityID="";
var districtID="";
var ths="";
var citySet = {
    SelCity:function(obj,e,provinceCode) {
    var winId = obj.id;
        console.info(winId);
        provinceID = winId + "hprovince";
         cityID = winId + "hcity";
         districtID = winId + "hdistrict";
        ths = obj;
    var dal = '<div class="_citys"><span title="关闭" id="cColse" >×</span><ul id="_citysheng" class="_citys0"><li class="citySel">省份</li><li>城市</li><li>区县</li></ul><div id="_citys0" class="_citys1"></div><div style="display:none" id="_citys1" class="_citys1"></div><div style="display:none" id="_citys2" class="_citys1"></div></div>';
    Iput.show({ id: ths, event: e, content: dal,width:"470"});
    $("#cColse").click(function () {
        Iput.colse();
    });

    citySet.getProvince(provinceCode);

    citySet.bindCitySheng();
},
    // 获取省份信息
    getProvince:function(provinceCode){
        var tb_province = [];
        // 获取省份信息
        var url = ConstantDef.FindProvinceServerUrl();
        if(provinceCode){
            // 收货地址处校验买家地址只能选择管家所属的省份。
            var data = {param:{provinceCode:provinceCode,isLoadCity:"0",isLoadDistrict:"0"}};
        }else{
            var data = {param:{}};
        }
        HttpClient.post(url,data,function(data){
            if(data.status == "F"){
                alert("失败");
            }else{
                var b = data.result.provinceList;
                for (var i = 0, len = b.length; i < len; i++) {
                    tb_province.push('<a data-level="0" id="' + b[i]['provinceCode'] + '" data-name="' + b[i]['provinceName'] + '">' + b[i]['provinceName'] + '</a>');
                }
                $("#_citys0").append(tb_province.join(""));
                $("#_citys0 a").click(function (){
                    // 获取城市
                    var provinceCode =$(this)[0].id;
                    var provinceName = $(this).data("name");
                    $(this).addClass("AreaS");
                    citySet.getCity(provinceCode,provinceName);
                });
            }
        });
    },

    // 获取城市信息
    getCity:function(provinceCode,provinceName){
        // 获取城市信息
        var cityUrl = ConstantDef.getFindDistrictCityServerUrl();
        var cityParamData = {param: {provinceCode:provinceCode}};
        HttpClient.post(cityUrl,cityParamData,function(data){
            if(data.status == "F"){
                alert("失败");
            }else{
                var city="";
                var cityDta = data.result.cityList;
                // cityDta 城市信息
                for (var j = 0, clen = cityDta.length; j < clen; j++) {
                    city += '<a data-level="1" id="' + cityDta[j]['cityCode'] + '" data-name="' + cityDta[j]['cityName'] + '" title="' + cityDta[j]['cityName'] + '">' + cityDta[j]['cityName'] + '</a>'
                }
                $("#_citysheng li").removeClass("citySel");
                $("#_citysheng li:eq(1)").addClass("citySel");
                $("#_citys1 a").remove();
                $("#_citys1").append(city);
                $("._citys1").hide();
                $("._citys1:eq(1)").show();
                $("#_citys0 a,#_citys1 a,#_citys2 a").removeClass("AreaS");
                //ths.getElementsByClassName("wl_input")[0].value = provinceName;
                if (document.getElementById(provinceID) == null) {
                    var hcitys = $('<input>', {
                        type: 'hidden',
                        name: "hprovince",
                        title: provinceCode,
                        id: provinceID,
                        val: provinceName
                    });
                    $(ths.getElementsByClassName("wl_input")[0]).after(hcitys);
                }
                else {
                    $("#" + provinceID).val(provinceName);
                    $("#" + provinceID).attr("title", provinceCode);
                }
                $("#_citys1 a").click(function (){
                    $("#_citys1 a,#_citys2 a").removeClass("AreaS");
                    $(this).addClass("AreaS");
                    var cityName =  $(this).data("name");
                    if (document.getElementById(cityID) == null) {
                        var hcitys = $('<input>', {
                            type: 'hidden',
                            name: "hcity",
                            title: $(this)[0].id,
                            id: cityID,
                            val: cityName
                        });
                        $(ths.getElementsByClassName("wl_input")[0]).after(hcitys);
                    }
                    else {
                        $("#" + cityID).attr("title", $(this)[0].id);
                        $("#" + cityID).val(cityName);
                    }
                    var bc = $("#" + provinceID).val();
                    //ths.getElementsByClassName("wl_input")[0].value = bc+ "-" + $(this).data("name");

                    var cityCode = $(this)[0].id;
                    citySet.getDistrict(cityCode);
                });
            }
        });
    },
    // 获取县信息
    getDistrict:function(cityCode){
        // 获取县信息
        var districtUrl = ConstantDef.getFindDistrictServerUrl();
        var districtParamData = {param: {cityCode:cityCode}};
        HttpClient.post(districtUrl,districtParamData,function(data){
            if(data.status == "F"){
                alert("失败");
            }else{
                var e =  data.result.districtList;
                var district="";
                for (var j = 0, clen = e.length; j < clen; j++) {
                    district += '<a data-level="1" id="' + e[j]['districtCode'] + '" data-name="' + e[j]['districtName'] + '" title="' + e[j]['districtName'] + '">' + e[j]['districtName'] + '</a>'
                }
                $("#_citysheng li").removeClass("citySel");
                $("#_citysheng li:eq(2)").addClass("citySel");
                $("#_citys2 a").remove();
                $("#_citys2").append(district);
                $("._citys1").hide();
                $("._citys1:eq(2)").show();

                $("#_citys2 a").click(function () {
                    $("#_citys2 a").removeClass("AreaS");
                    $(this).addClass("AreaS");
                    var districtName = $(this).data("name");
                    if (document.getElementById(districtID) == null) {
                        var hcitys = $('<input>', {
                            type: 'hidden',
                            name: "hdistrict",
                            title: $(this)[0].id,
                            id: districtID,
                            val: districtName
                        });
                        $(ths.getElementsByClassName("wl_input")[0]).after(hcitys);
                    }
                    else {
                        $("#" + districtID).val(districtName);
                        $("#" + districtID).attr("title", $(this)[0].id);
                    }
                    var bc = $("#" + provinceID).val();
                    var bp = $("#" + cityID).val();
                    if(bc==null || bc==''){
                        // 省份没有获取值
                        ths.getElementsByClassName("wl_input")[0].value="";
                    }else if(bp==null || bp==''){
                        // 区没有获取值
                        ths.getElementsByClassName("wl_input")[0].value="";
                    }else{
                        ths.getElementsByClassName("wl_input")[0].value = bc + "-" + bp + "-" + $(this).data("name");
                    }
                    Iput.colse();
                });
            }
        });
    },
    bindCitySheng:function(){
        $("#_citysheng li").click(function () {
            $("#_citysheng li").removeClass("citySel");
            $(this).addClass("citySel");
            var s = $("#_citysheng li").index(this);
            $("._citys1").hide();
            $("._citys1:eq(" + s + ")").show();
        });
    }
}
