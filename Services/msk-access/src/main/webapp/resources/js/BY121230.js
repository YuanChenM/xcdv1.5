$(function(){
    // 获取批发市场级别下拉框
    var marketLevelType;
    var flickerAPI = url+'/by/common/master';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"constantType": "MarketLevel"}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            marketLevelType = data.result;
        },
        error:function(){
            alert("error");
        }
    });

    // 显示买家的详细信息
    var flickerAPI = url+'/by/condition/findMarketTerminal';
    var terMarketId = localStorage.terMarketId;
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"terMarketId": terMarketId}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            var result = data.result;
            var byMarketTerminal = result[0];
            $("#marketCode").val(byMarketTerminal.marketCode);
            $("#marketName").val(byMarketTerminal.marketName);
            $("#marketAddr").val(byMarketTerminal.marketAddr);

            var marketLevel =  $("#marketLevel");
            marketLevel.html("");
            // 生成批发市场级别下拉框
/*            for(var i = 0; i< marketLevelType.length; i++){
                if(marketLevelType[i].constantValue == byMarketTerminal.marketLevel){
                    marketLevel.append("<option selected value="+marketLevelType[i].constantValue+">"+marketLevelType[i].constantName + "</option>")
                } else {
                    marketLevel.append("<option value="+marketLevelType[i].constantValue+">"+marketLevelType[i].constantName + "</option>")
                }
            }
            marketLevel.selectmenu("refresh");*/
            for(var i = 0; i< marketLevelType.length; i++){
                if(marketLevelType[i].constantValue == byMarketTerminal.marketLevel){
                    $("#marketLevel").val(marketLevelType[i].constantName);
                }
             }
            // 物流区,区，县
            getLogsArea(byMarketTerminal.lgcsAreaCode);
            getCity(byMarketTerminal.lgcsAreaCode, byMarketTerminal.cityCode);
            getDistrict(byMarketTerminal.cityCode, byMarketTerminal.districtCode);
        },
        error:function(){
            alert("error");
        }
    });
});

//获得所有物流区信息
function getLogsArea(lgcsAreaCode){
    var flickerAPI = url+'/by/getLogisticsAreas';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            var areas = data.result.lgcsAreaList;
            var logsArea = $("#lgcsAreaCode");
            var city = $("#cityCode");
            var district = $("#districtCode");
            /*if(lgcsAreaCode == null){
                logsArea.html("");
                logsArea.append("<option value=''>请选择</option>");
                city.html("");
                city.append("<option value=''>请选择</option>");
                city.selectmenu("refresh");
                district.html("");
                district.append("<option value=''>请选择</option>");
                district.selectmenu("refresh");
            }
            for(var i=0; i< areas.length; i++){
                if(lgcsAreaCode != null && lgcsAreaCode == areas[i].lgcsAreaCode){
                    logsArea.append("<option value="+areas[i].lgcsAreaCode+" selected>"+areas[i].lgcsAreaName+"</option>");
                }else{
                    logsArea.append("<option value="+areas[i].lgcsAreaCode+">"+areas[i].lgcsAreaName+"</option>");
                }
            }
            logsArea.selectmenu("refresh");*/
            for(var i=0; i< areas.length; i++){
                if(lgcsAreaCode != null && lgcsAreaCode == areas[i].lgcsAreaCode){
                    logsArea.append("<option value="+areas[i].lgcsAreaCode+" selected>"+areas[i].lgcsAreaName+"</option>");
                    $("#lgcsAreaCode").val(areas[i].lgcsAreaCode);
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
}

//获得物流区下的城市信息
function getCity(lgcsAreaCode,cityCode){
    var citys = [];
    var flickerAPI = url+'/by/getCitys';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"flag":0,"lgcsAreaCode":lgcsAreaCode}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            citys = data.result.cityList;
            //var city = $("#cityCode");
            //var district = $("#districtCode");
/*            if(cityCode == null){
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
            }*/
            for(var i=0; i< citys.length; i++){
                if(cityCode != null && cityCode == citys[i].cityCode){
                    $("#cityCode").val(citys[i].cityCode);
                }
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
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"prLgcsType":1,"cityCode":cityCode}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            var districts = data.result.districts;
            var district = $("#districtCode");
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
 * 物流区下拉框选择
 */
function logsChange() {
    var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
    getCity(lgcsAreaCode,null);
}
/**
 * 城市下拉框选择
 */
function cityChange() {
    var cityCode = $("#cityCode option:selected").val();
    getDistrict(cityCode,null);
}

//返回前一个画面
function returnPage(){
    window.location = "BY12120401.html";
}

//更新批发市场的详细信息
function byMarketTerminalUpdate(){
    var flickerAPI = url + '/by/byMarketTerminal/update';
    var terMarketId = localStorage.terMarketId;
    var marketCode = $("#marketCode").val();
    var marketName = $("#marketName").val();
    var marketAddr = $("#marketAddr").val();
    var marketLevel = $("#marketLevel option:selected").val();
    var marketLevelName = $("#marketLevel option:selected").text();
    var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
    var cityCode = $("#cityCode option:selected").val();
    var districtCode = $("#districtCode option:selected").val();
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"terMarketId": terMarketId, "marketCode":marketCode,"marketName":marketName, "marketAddr":marketAddr, "marketLevel":marketLevel, "marketLevelName":marketLevelName,"lgcsAreaCode": lgcsAreaCode, "cityCode":cityCode, "districtCode":districtCode}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            setTimeout(function(){
                if(data.status == "S"){
                    setMessageDivStyle(data.message, "errorMessage");
                }else{
                    setMessageDivStyle(data.message, "errorMessage");
                }
            },0);
        },
        error:function(){
            alert("error");
        }
    });
}

//设置messageDiv样式
function setMessageDivStyle(message, id){
    $("#"+id).css("border","1px #FF0000 solid");
    $("#"+id).css("color","#FF0000");
    $("#"+id).css("display","block");
    $("#"+id).text(message);
}