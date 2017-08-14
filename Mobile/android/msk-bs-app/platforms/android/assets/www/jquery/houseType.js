var ths;
var provinceID;
var cityID;
var districtID;
var typeSet = {
    SelCity:function(obj,e) {
        var winId = obj.id;
          provinceID = winId + "hprovince";
         cityID = winId + "hcity";
          districtID = winId + "hdistrict";
         ths = obj;
        var dal="";
        if(winId=="208"){
            dal = '<div class="_citys"><span title="关闭" id="cColse" >×</span><ul id="_citysheng" class="_citys0"><li class="citySel">一级分类</li><li>二级分类</li></ul><div id="_citys0" class="_citys1"></div><div style="display:none" id="_citys1" class="_citys1"></div><div style="display:none" id="_citys2" class="_citys1"></div></div>';
        }else {
             dal = '<div class="_citys"><span title="关闭" id="cColse" >×</span><ul id="_citysheng" class="_citys0"><li class="citySel">一级分类</li><li>二级分类</li><li>三级分类</li></ul><div id="_citys0" class="_citys1"></div><div style="display:none" id="_citys1" class="_citys1"></div><div style="display:none" id="_citys2" class="_citys1"></div></div>';
        }
        Iput.show({ id: ths, event: e, content: dal,width:"470"});
        $("#cColse").bind("touchstart",function () {
            Iput.colse();
        });
        // 208-管家
        if(winId=="208"){
            typeSet.queryHouseType();
        }else{
            // 获取买手一级分类
            typeSet.queryBsOneClass();
        }

        typeSet.bindCitySheng();
    },

    /**
     * 查询管家一级分类
     */
    queryHouseType:function(){
        var houseType=[];
        var data = {
            param:{
                typeLever: 0,
                parentTypeCode :1
            }
        };
        var url = ConstantDef.getFindHouseTypeServerUrl();
        HttpClient.post(url,data,function(data){
            if(data.status == "F"){
                webToast("失败","middle");
            }else{
                var houseTypeResult = data.result;
                for (var i = 0, len = houseTypeResult.length; i < len; i++) {
                    houseType.push('<a data-level="0"  style="width:auto"  id="' + houseTypeResult[i]['typeCode'] + '" data-name="' + houseTypeResult[i]['typeName'] + '">' + houseTypeResult[i]['typeName'] + '</a>');
                }
                $("#_citys0").append(houseType.join(""));
                $("#_citys0 a").click(function (){
                    $(this).addClass("AreaS");
                    var houseOneClassCode =$(this)[0].id;
                    var houseOneClassName = $(this).data("name");
                    // 获取管家二级分类
                    typeSet.queryHouseTwoType(houseOneClassCode,houseOneClassName);
                });
            }
        },function(data){
            webToast("操作失败","middle");
        });
    },

    /**
     * 查询管家二级分类
     */
    queryHouseTwoType:function(houseOneClassCode,houseOneClassName){
        var data = {
            param:{
                typeLever: 1,
                parentTypeCode :houseOneClassCode
            }
        };
        var url = ConstantDef.getFindHouseTypeServerUrl();
        HttpClient.post(url,data,function(data){
            if(data.status == "F"){
                webToast("失败","middle");
            }else{
                var tb_houseTwoClass="";
                var tb_houseTwoClassData = data.result;
                // 买手二级分类信息
                for (var j = 0, clen = tb_houseTwoClassData.length; j < clen; j++) {
                    tb_houseTwoClass += '<a data-level="1" style="width:auto" id="' + tb_houseTwoClassData[j]['typeCode'] + '" data-name="' + tb_houseTwoClassData[j]['typeName'] + '" title="' + tb_houseTwoClassData[j]['typeName'] + '">' + tb_houseTwoClassData[j]['typeName'] + '</a>'
                }
                $("#_citysheng li").removeClass("citySel");
                $("#_citysheng li:eq(1)").addClass("citySel");
                $("#_citys1 a").remove();
                $("#_citys1").append(tb_houseTwoClass);
                $("._citys1").hide();
                $("._citys1:eq(1)").show();
                $("#_citys0 a,#_citys1 a,#_citys2 a").removeClass("AreaS");
                if (document.getElementById(provinceID) == null) {
                    var hcitys = $('<input>', {
                        type: 'hidden',
                        name: "hprovince",
                        title: houseOneClassCode,
                        id: provinceID,
                        val: houseOneClassName
                    });
                    $(ths.getElementsByClassName("wl_input")[0]).after(hcitys);
                }
                else {
                    $("#" + provinceID).val(houseOneClassName);
                    $("#" + provinceID).attr("title", houseOneClassCode);
                }
                $("#_citys1 a").click(function (){
                    $("#_citys1 a,#_citys2 a").removeClass("AreaS");
                    $(this).addClass("AreaS");
                    var houseTwoClassName =  $(this).data("name");
                    if (document.getElementById(cityID) == null) {
                        var hcitys = $('<input>', {
                            type: 'hidden',
                            name: "hcity",
                            title: $(this)[0].id,
                            id: cityID,
                            val: houseTwoClassName
                        });
                        $(ths.getElementsByClassName("wl_input")[0]).after(hcitys);
                    }
                    else {
                        $("#" + cityID).attr("title", $(this)[0].id);
                        $("#" + cityID).val(houseTwoClassName);
                    }
                    ths.getElementsByClassName("wl_input")[0].value = houseTwoClassName;
                    Iput.colse();
                });
            }
        });
    },

    /**
     * 获取买手一级分类
     */
    queryBsOneClass:function(){
        var tb_bsOneClass = [];
        var url = ConstantDef.getFindBsTypeServerUrl();
        var data = {param:{typeLever: "0"}};
        HttpClient.post(url,data,function(data){
            if(data.status == "F"){
                webToast("失败","middle");
            }else{
                var b = data.result;
                for (var i = 0, len = b.length; i < len; i++) {
                    tb_bsOneClass.push('<a data-level="0"  style="width:auto" id="' + b[i]['typeCode'] + '" data-name="' + b[i]['typeName'] + '">' + b[i]['typeName'] + '</a>');
                }
                $("#_citys0").append(tb_bsOneClass.join(""));
                $("#_citys0 a").click(function (){
                    // 买手一级分类码
                    var bsOneClassCode =$(this)[0].id;
                    var bsOneClassName = $(this).data("name");
                    $(this).addClass("AreaS");
                    // 获取买手二级分类
                    typeSet.queryBsTwoClass(bsOneClassCode,bsOneClassName);
                });
            }
        });
    },
    /**
     * 获取买手二级分类
     * @param bsOneClassCode
     * @param bsOneClassName
     */
    queryBsTwoClass:function(bsOneClassCode,bsOneClassName){
        var url = ConstantDef.getFindBsTypeServerUrl();
        var paramData = {param: {typeLever: "1", parentTypeCode :bsOneClassCode}};
        HttpClient.post(url,paramData,function(data){
            if(data.status == "F"){
               webToast("失败","middle");
            }else{
                var tb_bsTwoClass="";
                var tb_bsTwoClassData = data.result;
                // 买手二级分类信息
                for (var j = 0, clen = tb_bsTwoClassData.length; j < clen; j++) {
                    tb_bsTwoClass += '<a data-level="1" style="width:auto" id="' + tb_bsTwoClassData[j]['typeCode'] + '" data-name="' + tb_bsTwoClassData[j]['typeName'] + '" title="' + tb_bsTwoClassData[j]['typeName'] + '">' + tb_bsTwoClassData[j]['typeName'] + '</a>'
                }
                $("#_citysheng li").removeClass("citySel");
                $("#_citysheng li:eq(1)").addClass("citySel");
                $("#_citys1 a").remove();
                $("#_citys1").append(tb_bsTwoClass);
                $("._citys1").hide();
                $("._citys1:eq(1)").show();
                $("#_citys0 a,#_citys1 a,#_citys2 a").removeClass("AreaS");
                if (document.getElementById(provinceID) == null) {
                    var hcitys = $('<input>', {
                        type: 'hidden',
                        name: "hprovince",
                        title: bsOneClassCode,
                        id: provinceID,
                        val: bsOneClassName
                    });
                    $(ths.getElementsByClassName("wl_input")[0]).after(hcitys);
                }
                else {
                    $("#" + provinceID).val(bsOneClassName);
                    $("#" + provinceID).attr("title", bsOneClassCode);
                }
                $("#_citys1 a").click(function (){
                    $("#_citys1 a,#_citys2 a").removeClass("AreaS");
                    $(this).addClass("AreaS");
                    var bsTwoClassName =  $(this).data("name");
                    if (document.getElementById(cityID) == null) {
                        var hcitys = $('<input>', {
                            type: 'hidden',
                            name: "hcity",
                            title: $(this)[0].id,
                            id: cityID,
                            val: bsTwoClassName
                        });
                        $(ths.getElementsByClassName("wl_input")[0]).after(hcitys);
                    }
                    else {
                        $("#" + cityID).attr("title", $(this)[0].id);
                        $("#" + cityID).val(bsTwoClassName);
                    }
                    var bc = $("#" + provinceID).val();
                    var bsTwoClassCode = $(this)[0].id;
                    // 获取买手三级分类
                    typeSet.queryBsThreeClass(bsTwoClassCode);
                });
            }
        });
    },
    /**
     * 获取买手三级分类
     * @param bsTwoClassCode
     */
    queryBsThreeClass:function(bsTwoClassCode){
        var url = ConstantDef.getFindBsTypeServerUrl();
        var paramData = {param: {typeLever: "2", parentTypeCode :bsTwoClassCode}};
        HttpClient.post(url,paramData,function(data){
            if(data.status == "F"){
               webToast("失败","middle");
            }else{
                var e =  data.result;
                var district="";
                for (var j = 0, clen = e.length; j < clen; j++) {
                    district += '<a data-level="1" style="width:auto" id="' + e[j]['typeCode'] + '" data-name="' + e[j]['typeName'] + '" title="' + e[j]['typeName'] + '">' + e[j]['typeName'] + '</a>'
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
                    var bsThreeClassName = $(this).data("name");
                    if (document.getElementById(districtID) == null) {
                        var hcitys = $('<input>', {
                            type: 'hidden',
                            name: "hdistrict",
                            title: $(this)[0].id,
                            id: districtID,
                            val: bsThreeClassName
                        });
                        $(ths.getElementsByClassName("wl_input")[0]).after(hcitys);
                    }
                    else {
                        $("#" + districtID).val(bsThreeClassName);
                        $("#" + districtID).attr("title", $(this)[0].id);
                    }
                    var bc = $("#" + provinceID).val();
                    var bp = $("#" + cityID).val();
                    ths.getElementsByClassName("wl_input")[0].value = $(this).data("name");
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
