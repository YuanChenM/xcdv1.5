$(function(){
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = "";
    if(localStorage.breedCode){
        breedCode = localStorage.breedCode;
    }
    var isStandard = localStorage.isStandard;

    var flickerAPI = url+'//by/research/findResearchTypeList';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"isStandard":isStandard}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "S"){
                var researchList = data.result;
                if(researchList.length > 0){
                    for(var i = 0;i < researchList.length; i++){
                        if(researchList[i].researchType == "1"){
                            $("#classesResearch").append(researchStr(researchList[i]));
                        }else{
                            $("#standardDes").css("display","block");
                            $("#standardDiv").append(researchStr(researchList[i]));
                        }
                    }
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
});

function researchStr(research){
    var str = "";
    if(research.researchStatus == "3"){
        //str = str + "<div class='ui-grid-a' style='width: 100%;height:40px;line-height: 45px;background-color:#52C2E9;color: #FFFFFF;text-align:center;text-shadow:none;font-size: 20px;margin-top:5px;' onclick='pageTransfer("+research.researchType+")'>";
        str = str + "<div class='ui-grid-a' style='width: 100%;height:40px;line-height: 45px;background-color:#CCCCCC;color: #000000;text-align:center;text-shadow:none;font-size: 20px;margin-top:5px;'>";
    }else{
        str = str + "<div class='ui-grid-a'style='width: 100%;height:40px;line-height: 45px;background-color:#52C2E9;color: #FFFFFF;text-align:center;text-shadow:none;font-size: 20px;margin-top:5px;' onclick='pageTransfer("+research.researchType+")'>";
    }
    str = str + "<div class='ui-block-a' style='width:70%;text-align:left;'><span style='padding-left:20px;'>"+research.researchTypeName+"</span></div>";
    if(research.researchStatus == "0"){
        str = str + "<div class='ui-block-b' style='width:30%;text-align:right;'><span style='padding-right:20px;'>未调研</span></div>";
    }else if(research.researchStatus == "1"){
        str = str + "<div class='ui-block-b' style='width:30%;text-align:right;'><span style='padding-right:20px;'>已调研</span></div>";
    }else if(research.researchStatus == "2"){
        str = str + "<div class='ui-block-b' style='width:30%;text-align:right;'><span style='padding-right:20px;'>调研中</span></div>";
    }else if(research.researchStatus == "3"){
        str = str + "<div class='ui-block-b' style='width:30%;text-align:right;'><span style='padding-right:20px;'>不调研</span></div>";
    }
    str = str + "</div>";
    return str;
}

//页面跳转
function pageTransfer(pageNum){
    if(pageNum == 1){
        //产品品类调研
        window.location = "BY121217.html";
    }else if(pageNum == 2){
        //原种种源标准调研
        window.location = "BY121219.html";
    }else if(pageNum == 3){
        //加工技术标准调研
        window.location = "BY121220.html";
    }else if(pageNum == 4){
        //加工质量标准调研
        window.location = "BY121221.html";
    }else if(pageNum == 5){
        //产品饲养标准调研
        window.location = "BY121222.html";
    }else if(pageNum == 6){
        //通用质量标准调研
        window.location = "BY121223.html";
    }else if(pageNum == 7){
        //品种安全标准调研
        window.location = "BY121224.html";
    }else if(pageNum == 8){
        //储存运输标准调研
        window.location = "BY121225.html";
    }else if(pageNum == 9){
        //品种包装标准调研
        window.location = "BY121226.html";
    }else if(pageNum == 10){
        //第三方品牌调研
        window.location = "BY121228.html";
    }
}

function returnPage(){
    window.location = "BY121214.html";
}