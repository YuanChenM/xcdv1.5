$(function(){
	var ssoHref = window.location.href;
	var indexFlag = ssoHref.indexOf(".html");
	if(indexFlag != -1){
		var intPos = ssoHref.indexOf("?");
		if(intPos == -1){
			window.location = "BY121201.html";
		}
		var strRight = ssoHref.substr(intPos + 1);
		var arrTmpFlg = strRight.indexOf("&");
		if(arrTmpFlg == -1){
			window.location = "BY121201.html";
		}
		var arrTmp = strRight.split("&");
		if(arrTmp.length != 2){
			window.location = "BY121201.html";
		}
		var interfaceToken = arrTmp[0].split("=")[1];
		var accountName = arrTmp[1].split("=")[1];
	}
	var flickerAPI = url+'/by/sso/login';
	var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"interfaceToken":interfaceToken,"accountName": accountName}};
	$.ajax({
		type : "POST",
		async:false,
		url:flickerAPI,
		timeout:60,
		dataType:'json',
		contentType:"application/json",
		data:JSON.stringify(paramData),
		success:function(data){
			if(data.status == "F"){
				setTimeout(function(){
					window.location = "BY121201.html"
				},3000);
			}else{
				localStorage.accessAccountName = accountName;
				setTimeout(function(){
					window.location = "BY121203.html"
				},3000);
			}
		},
		error:function(){
			alert("error");
		}
	});
});