var lgcsAreaCode;
var cityCode;
var districtCode;
/**
 * 画面初期显示
 */
$(function(){
	getLocation();
});
/**
 * 获取地理位置
 */
function getLocation() {
	//检查浏览器是否支持地理位置获取
	if (navigator.geolocation) {
		//若支持地理位置获取,成功调用showPosition(),失败调用showError
		// alert("正在努力获取位置...");
		var config = { enableHighAccuracy: true, timeout: 5000, maximumAge: 30000 };
		navigator.geolocation.getCurrentPosition(showPosition, showError, config);
	} else {
		setMessageDivStyle(false);
	}
}
/**
 * 获取地址位置成功
 */
function showPosition(position) {
//获得经度纬度
	var x = position.coords.latitude;
	var y = position.coords.longitude;
	//配置Baidu Geocoding API
	var url = "http://api.map.baidu.com/geocoder/v2/?ak=C93b5178d7a8ebdb830b9b557abce78b" +
		"&callback=renderReverse" +
		"&location=" + x + "," + y +
		"&output=json" +
		"&pois=0";
	$.ajax({
		type: "GET",
		dataType: "jsonp",
		url: url,
		success: function (json) {
			if (json == null || typeof (json) == "undefined") {
				return;
			}
			if (json.status != "0") {
				return;
			}
			setAddress(json.result.addressComponent);
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			setMessageDivStyle(false);
		}
	});
}
/**
 * 设置地址
 */
function setAddress(json) {
	//省
	var province = json.province;
	//市
	var city = json.city;
	//区
	var district = json.district;

	var position;
	if(province.indexOf("市")>0){
		position = city + district;
	} else {
		position = province + "," + city + "," + district;
	}
	//localStorage.localPosition = position;
	setMessageDivStyle(true);
	getLocalLgcsArea(city,district);
}
/**
 * 根据定位获取当前位置
 * 然后获取当前位置属于的物流区
 */
function getLocalLgcsArea(cityName,districtName){
	var flickerAPI = url+'/by/getCodesFromNames';
	var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"cityName": cityName, "districtName": districtName}};
	$.ajax({
		type: "POST",
		async: false,
		url: flickerAPI,
		timeout: 60,
		dataType: 'json',
		contentType: "application/json",
		data: JSON.stringify(paramData),
		success: function (data) {
			if(data.status = "S"){
				lgcsAreaCode = data.result.lgcsAreaCode;
				cityCode = data.result.cityCode;
				districtCode = data.result.districtCode;
				getLogsArea(lgcsAreaCode);
				getCity(lgcsAreaCode,cityCode);
				getDistrict(cityCode,districtCode);
			}
		},
		error: function(){
			alert("error");
		}
	});
}
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
			var logsArea = $("#s_logsArea");
			//var citys = data.result.citys;
			var city = $("#s_city");
			var district = $("#s_district");
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
			logsArea.selectmenu("refresh");
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
 * 物流区下拉框选择
 */
function logsChange() {
	var lgcsAreaCode = $("#s_logsArea option:selected").val();
	getCity(lgcsAreaCode,null);

}
/**
 * 城市下拉框选择
 */
function cityChange() {
	var cityCode = $("#s_city option:selected").val();
	getDistrict(cityCode,null);
}
//开始按钮点击
function researchStart(){
	var lgcsAreaCode = $("#s_logsArea option:selected").val();
	var lgcsAreaName = $("#s_logsArea option:selected").text();
	var cityCode = $("#s_city option:selected").val();
	var cityName = $("#s_city option:selected").text();
	var districtCode = $("#s_district option:selected").val();
	var districtName = $("#s_district option:selected").text();

	localStorage.lgcsAreaCode = lgcsAreaCode;
	localStorage.lgcsAreaName = lgcsAreaName;
	localStorage.cityCode = cityCode;
	localStorage.cityName = cityName;
	localStorage.districtCode = districtCode;
	localStorage.districtName = districtName;

	window.location = "BY12120301.html";
}
//设置messageDiv样式
function setMessageDivStyle(flag){
	if(flag){
		$("#message").css("border","1px #AAD979 solid");
		$("#message").css("color","#AAD979");
		$("#message").css("display","block");
		$("#message").text("系统已自动定位");
	}else{
		$("#message").css("border","1px #FF0000 solid");
		$("#message").css("color","#FF0000");
		$("#message").css("display","block");
		$("#message").text("系统自动定位失败");
		getLogsArea(null);
	}
}
/**
 * 获取地址位置失败[暂不处理]
 */
function showError(error) {
	switch (error.code) {
		case error.PERMISSION_DENIED:
			setMessageDivStyle(false);
			break;
		case error.POSITION_UNAVAILABLE:
			setMessageDivStyle(false);
			break;
		case error.TIMEOUT:
			setMessageDivStyle(false);
			break;
		case error.UNKNOWN_ERROR:
			setMessageDivStyle(false);
			break;
	}
}
//返回前一个画面
function returnPage(){
	window.location = "BY121202.html";
}