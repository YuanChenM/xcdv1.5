/**
 * 画面跳转
 */
function pageTranfer(pageNo){
	if(pageNo == 1){
		window.location = "BY121204.html";
	}else if(pageNo == 2){
		window.location = "BY12120401.html";
	}else if(pageNo == 3){
		window.location = "BY12120402.html";
	}
}
//返回前一个画面
function returnPage(){
	window.location = "BY121203.html";
}