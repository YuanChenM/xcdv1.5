$(function(){
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdClassesName = localStorage.pdClassesName;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var pdMachiningName = localStorage.pdMachiningName;
   $("#selectedPdClass").text("产品分类:" + pdClassesName + pdMachiningName);
    //已调研品种
    var flickerAPI = url+'//by/research/findList';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode}};
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
                var breedList = data.result;
                $("#breedInfoDiv").html("");
                $("#breedOtherInfoDiv").html("");
                if(breedList != null && breedList.length < 5){
                    $("#breedOtherOpen").css("display","none");
                    $("#totalBreedCount").text(breedList.length+"种");
                    for(var i = 0; i < breedList.length; i++){
                        $("#breedInfoDiv").append(getBreedStr(breedList[i]));
                    }
                }else if(breedList != null) {
                    $("#totalBreedCount").text(breedList.length + "种");
                    $("#breedOtherOpen").css("display","block");
                    for(var i = 0; i < 5; i++){
                        $("#breedInfoDiv").append(getBreedStr(breedList[i]));
                    }
                    for(var i = 5; i< breedList.length; i++){
                        $("#breedOtherInfoDiv").append(getBreedStr(breedList[i]));
                    }
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
    //新品种
    var flickerAPI = url+'//by/research/findNewList';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode}};
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
                var breedNewList = data.result;
                $("#newBreedInfoDiv").html("");
                $("#newbreedOtherInfoDiv").html("");
                if(breedNewList != null && breedNewList.length < 5){
                    $("#newBreedOtherOpen").css("display","none");
                    $("#newBreedCount").text(breedNewList.length+"种");
                    for(var i = 0; i < breedNewList.length; i++){
                        $("#newBreedInfoDiv").append(getBreedStr(breedNewList[i]));
                    }
                }else if(breedNewList != null) {
                    $("#newBreedCount").text(breedNewList.length + "种");
                    $("#newBreedOtherOpen").css("display","block");
                    for(var i = 0; i < 5; i++){
                        $("#newBreedInfoDiv").append(getBreedStr(breedNewList[i]));
                    }
                    for(var i = 5; i< breedNewList.length; i++){
                        $("#newBreedInfoDiv").append(getBreedStr(breedNewList[i]));
                    }
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
});
//已注册产品
function getBreedStr(breed){
    var str = "";
    if(breed.isStandard == "0"){
        str = str + "<div class='ui-grid-a' onclick='chooseBreedInfo(null,\""+breed.breedName+"\","+breed.isStandard+")' style='width: 80%;margin-left:30px;'>";
    }else{
        str = str + "<div class='ui-grid-a' onclick='chooseBreedInfo(\""+breed.breedCode+"\",\""+breed.breedName+"\","+breed.isStandard+")' style='width: 80%;margin-left:30px;'>";
    }
    str = str + "<div class='ui-block-a' style='width:70%;text-align:left;'><div class='listDiv' style='margin-left:0px;'><span style='padding-left:20px;'>"+breed.breedName+"</span></div></div>";
    if(breed.researchStatus == "0"){
        str = str + "<div class='ui-block-b' style='width:30%;text-align:right;'><div class='listDiv' style='margin-left:0px;'><span style='padding-right:20px;'>未调研</span></div></div>";
    }else if(breed.researchStatus == "1"){
        str = str + "<div class='ui-block-b' style='width:30%;text-align:right;'><div class='listDiv' style='margin-left:0px;'><span style='padding-right:20px;'>已调研</span></div></div>";
    }else if(breed.researchStatus == "2"){
        str = str + "<div class='ui-block-b' style='width:30%;text-align:right;'><div class='listDiv' style='margin-left:0px;'><span style='padding-right:20px;'>调研中</span></div></div>";
    }
    str = str + "</div>";
    return str;
}
//已注册产品展开更多
function breedOtherOpen(){
    if($("#breedOtherOpen").text() == "展开更多"){
        $("#breedOtherOpen").html("<span style='padding-left:20px;'>收起</span>");
    }else{
        $("#breedOtherOpen").html("<span style='padding-left:20px;'>展开更多</span>");
    }
    $("#breedOtherInfoDiv").slideToggle();
}
//新注册产品展开更多
function newBreedOtherOpen(){
    if($("#newBreedOtherOpen").text() == "展开更多"){
        $("#newBreedOtherOpen").html("<span style='padding-left:20px;'>收起</span>");
    }else{
        $("#newBreedOtherOpen").html("<span style='padding-left:20px;'>展开更多</span>");
    }
    $("#newbreedOtherInfoDiv").slideToggle();
}
//点击选中规格跳转选择调研卡
function chooseBreedInfo(breedCode,breedName,isStandard){
    if(breedCode != null){
        localStorage.breedCode = breedCode;
    }else if(localStorage.breedCode){
        localStorage.removeItem("breedCode");
    }
    localStorage.breedName = breedName;
    localStorage.isStandard = isStandard;
    window.location = "BY121216.html";
}

//添加新调研品种
function addNewBreed(){
    window.location = "BY121215.html";
}
function returnPage(){
    window.location = "BY121213.html";
}