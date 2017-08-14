/**
 * 画面跳转
 */
function pageTranfer(pageNo){
    localStorage.enterFlg = "edit";
    if(pageNo == 1){
        window.location = "BY12120501.html";
    }else if(pageNo == 2){
        window.location = "BY121207.html";
    }else if(pageNo == 3){
        window.location = "BY121233.html";
    }else if(pageNo == 4){
        window.location = "BY121209.html";
    }
}
//返回前一个画面
function returnPage(){
    window.location = "BY121210.html";
}