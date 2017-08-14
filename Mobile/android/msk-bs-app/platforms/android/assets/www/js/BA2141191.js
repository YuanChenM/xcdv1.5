/**
 * Created by zhu_kai1 on 2016/9/28.
 */
var backUrl='BA2141199.html';
jQuery(document).ready(function () {
    BA2141191.returnBack();
    BA2141191.searchServiceCity();
    setTimeout("BA2141191.getOpenServiceCityList()",100);
    setTimeout("BA2141191.cityPsition()",100);
});

var BA2141191={

    returnBack:function(){
        $(".head-back").bind("touchstart",function(){
            window.location.href =backUrl;
        })
    },
    /**获取城市列表**/
    getOpenServiceCityList:function(CityName){
        $("#cityInfo").find("li").not(":first").remove();
        var url =ConstantDef.getChooseCityUrl();
        var paramData="";
        if((CityName !=''&& null !=CityName)){
            // 首字母缩写模糊查询
            if(/^[A-Za-z]+$/.test(CityName)){
                console.log("城市拼音缩写："+CityName+"");
                paramData ={
                    param: {
                        "cityOrderBy":"1",
                        "openServiceFlg": "1",
                        "filterMap":{"shortSpell":CityName}
                    }
                };
            }else{
                // 检索城市名称
                console.log("城市："+CityName+"");
                paramData ={
                    param: {
                        "cityOrderBy":"1",
                        "openServiceFlg": "1",
                        "filterMap":{"cityName":CityName}
                    }
                };
            }
            console.log(JSON.stringify(paramData));
        }else{
            // 查询所有的城市信息
            paramData ={
                param: {
                    "cityOrderBy":"1",
                    "openServiceFlg": "1"
                }
            };
        }
        HttpClient.post(url,paramData,function(data){
            if(data.status == "F"){
                webToast("没有查询到对应城市","middle");
            }else{
                var cityList = data.result;
                for(var i= 0;i<cityList.length;i++){
                    var obj = $("ul li:eq(0)");
                    var cloneObj = obj.clone();
                    cloneObj.show();
                    var cityName = cityList[i].cityName;
                    var cityCode = cityList[i].cityCode;
                    var lgcsCode = cityList[i].lgcsAreaCode;
                    var provinceCode = cityList[i].provinceCode;
                    cloneObj.find("span.right")[0].innerText = cityName;
                    cloneObj.attr("data-lgcsCode",lgcsCode);
                    cloneObj.attr("data-cityCode",cityCode);
                    cloneObj.attr("data-provinceCode",provinceCode);
                    cloneObj.appendTo("#cityInfo");
                }
                $("#cityInfo").find(".detailClass").each(function(){
                    $(this).on("touchstart",function(){
                        var lgcsCode = $(this).attr("data-lgcsCode");
                        var provinceCode = $(this).attr("data-provinceCode");
                        var cityCode = $(this).attr("data-cityCode");
                        localStorage.lgcsCode = lgcsCode ;
                        localStorage.provinceCode = provinceCode;
                        localStorage.cityCode =cityCode;
                        window.location.href = 'BA2141105.html'
                    });
                });
            }
        },function(data){
            webToast("操作失败","middle");
        });
    },
    /**
     * 搜索城市
     * @returns {boolean}
     */
    searchServiceCity:function(){
        $("#searchBtn").off("touchstart");
        $("#searchBtn").on("touchstart",function(){
            var cityName =  $("#searchCity").val();
            BA2141191.getOpenServiceCityList(cityName);
        });
        return false;
    },
    /**
     * 获取当前城市名称
     */
    cityPsition:function(){
        var url =ConstantDef.getChooseCityUrl();
        var cityCodesArr=[];
        cityCodesArr.push(localStorage.cityCode);
        var  paramData ={
            param: {
                "cityCodes":cityCodesArr
            }
        };
        HttpClient.post(url,paramData,function(data){
            if(data.status == "F"){
                webToast("当前城市不存在","middle");
            }else{
                var cityList = data.result;
                for(var i= 0;i<cityList.length;i++){
                    var cityName = cityList[i].cityName;
                     $(".cityName").html(cityName);
                }
            }
        },function(data){
            webToast("操作失败","middle");
        });
    }

}



