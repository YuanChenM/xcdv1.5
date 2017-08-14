function loginButtonClick(){
	var account = $("#accountName").val();
	var password = $("#password").val();
	var flickerAPI = url+'/by/access/login';
	var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"accountName": account, "accountPwd":password}};
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
				setMessageDivStyle(data.message);
			}else{
				localStorage.accessAccountName = account;
				window.location = "BY121203.html";
			}
		},
		error:function(){
			alert("error");
		}
	});
}

//设置messageDiv样式
function setMessageDivStyle(message){
	$("#message").css("border","1px #FF0000 solid");
	$("#message").css("color","#FF0000");
	$("#message").css("display","block");
	$("#message").text(message);
}
//返回前一画面
function returnPage(){
	window.location = "BY121201.html"
}