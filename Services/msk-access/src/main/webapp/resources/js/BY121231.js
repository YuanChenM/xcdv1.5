$(function(){
    // 获取菜场类型下拉框
    var marketType;
    var flickerAPI = url+'/by/common/master';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"constantType": "MarketType"}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            marketType = data.result;
        },
        error:function(){
            alert("error");
        }
    });

    // 获取菜场地段类型下拉框
    var marketSectionType;
    var flickerAPI = url+'/by/common/master';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"constantType": "MarketSectionType"}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            marketSectionType = data.result;
        },
        error:function(){
            alert("error");
        }
    });

    // 获取菜场规模类型下拉框
    var marketSizeType;
    var flickerAPI = url+'/by/common/master';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"constantType": "MarketSizeType"}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            marketSizeType = data.result;
        },
        error:function(){
            alert("error");
        }
    });

    // 显示买家的详细信息
    var flickerAPI = url+'/by/condition/findMarketFood';
    var fodMarketId = localStorage.fodMarketId;
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"fodMarketId": fodMarketId}};
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
            debugger;
            var byMarketFood = result[0];
            $("#marketCode").val(byMarketFood.marketCode);
            $("#marketName").val(byMarketFood.marketName);
            $("#marketAddr").val(byMarketFood.marketAddr);
            var marketType =  $("#marketType");
/*            // 生成菜场类型下拉框
            var marketTypeSelect =  $("#marketType");
            marketTypeSelect.html("");
            for(var i = 0; i< marketType.length; i++){
                if(marketType[i].constantValue == byMarketFood.marketType){
                    marketTypeSelect.append("<option selected value="+marketType[i].constantValue+">"+marketType[i].constantName + "</option>")
                } else {
                    marketTypeSelect.append("<option value="+marketType[i].constantValue+">"+marketType[i].constantName + "</option>")
                }
            }
            marketTypeSelect.selectmenu("refresh");*/
            for(var i = 0; i< result.length; i++){
                if(result[i].marketType == byMarketFood.marketType){
                    $("#marketType").val(result[i].marketTypeName);
                }
            }
            // 生成地段类型下拉框
            var sectionTypeSelect =  $("#sectionType");
/*            sectionTypeSelect.html("");
            for(var i = 0; i< marketSectionType.length; i++){
                if(marketSectionType[i].constantValue == byMarketFood.sectionType){
                    sectionTypeSelect.append("<option selected value="+marketSectionType[i].constantValue+">"+marketSectionType[i].constantName + "</option>")
                } else {
                    sectionTypeSelect.append("<option value="+marketSectionType[i].constantValue+">"+marketSectionType[i].constantName + "</option>")
                }
            }

            sectionTypeSelect.selectmenu("refresh");*/
            for(var i = 0; i< result.length; i++){
                if(result[i].sectionType == byMarketFood.sectionType){
                    $("#sectionType").val(result[i].sectionTypeName);
                }
            }
/*            // 生成规模类型下拉框
            var sizeTypeSelect =  $("#sizeType");
            sizeTypeSelect.html("");
            for(var i = 0; i< marketSizeType.length; i++){
                if(marketSizeType[i].constantValue == byMarketFood.sizeType){
                    sizeTypeSelect.append("<option selected value="+marketSizeType[i].constantValue+">"+marketSizeType[i].constantName + "</option>")
                } else {
                    sizeTypeSelect.append("<option value="+marketSizeType[i].constantValue+">"+marketSizeType[i].constantName + "</option>")
                }
            }
            sizeTypeSelect.selectmenu("refresh");*/

            // 物流区,区，县
            getLogsArea(byMarketFood.lgcsAreaCode);
            getCity(byMarketFood.lgcsAreaCode, byMarketFood.cityCode);
            getDistrict(byMarketFood.cityCode, byMarketFood.districtCode)
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
            /*var city = $("#cityCode");
            var district = $("#districtCode");
            if(lgcsAreaCode == null){
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
            var citys = data.result.cityList;
            //var city = $("#cityCode");
            /*var district = $("#districtCode");
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
            var districts = data.result.districtList;
/*            var district = $("#districtCode");
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
            }*/
            for(var i=0; i< districts.length; i++){
                if(districtCode != null && districtCode == districts[i].districtCode){
                    $("#districtCode").val(districts[i].districtCode);
                }
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
    window.location = "BY12120402.html";
}

//更新菜场的详细信息
function byMarketFoodUpdate(){
    var flickerAPI = url + '/by/byMarketFood/update';
    var fodMarketId = localStorage.fodMarketId;
    var marketCode = $("#marketCode").val();
    var marketName = $("#marketName").val();
    var marketAddr = $("#marketAddr").val();
    var marketType = $("#marketType option:selected").val();
    var marketTypeName = $("#marketType option:selected").text();
    var sectionType = $("#sectionType option:selected").val();
    var sectionTypeName = $("#sectionType option:selected").text();
    var sizeType = $("#sizeType option:selected").val();
    var sizeTypeName = $("#marketType option:selected").text();
    var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
    var cityCode = $("#cityCode option:selected").val();
    var districtCode = $("#districtCode option:selected").val();
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"fodMarketId": fodMarketId, "marketCode":marketCode,"marketName":marketName, "marketAddr":marketAddr, "marketType":marketType, "marketTypeName":marketTypeName, "sectionType":sectionType, "sectionTypeName":sectionTypeName, "sizeType":sizeType, "sizeTypeName":sizeTypeName, "lgcsAreaCode": lgcsAreaCode, "cityCode":cityCode, "districtCode":districtCode}};
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