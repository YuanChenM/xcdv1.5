//保存初期化从db获取到的数据
var researchStdSftList;
//记录当前显示的是第几项
var currentItem = -1;
$(function(){
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = localStorage.breedCode;
    var breedName = localStorage.breedName;
    $("#breedStandard").text(breedName + "-品种安全标准");

    var flickerAPI = url+'//by/researchStdSft/findList';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode}};
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
                researchStdSftList = data.result;
                showNexItemInfo();
            }
        },
        error:function(){
            alert("error");
        }
    });
});
//当前项数据保存到DB，并显示下一项数据
function nextItem(){
    var id = researchStdSftList[currentItem].id;
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = "01";
    var updId = localStorage.accessAccountName;
    var standardId = researchStdSftList[currentItem].standardId;
    var sftStdItemId = researchStdSftList[currentItem].sftStdItemId;
    var sftStdItemName = researchStdSftList[currentItem].sftStdItemName;
    var okVal = researchStdSftList[currentItem].okVal;
    var okValAgree = 0;
    if($("#okValAgree").is(":checked")){
        okValAgree = 1;
    }
    var ngVal = researchStdSftList[currentItem].ngVal;
    var remark = "";
    if(researchStdSftList[currentItem].remark){
        remark = researchStdSftList[currentItem].remark;
    }
    var description = $("#description").val();
    var isResearch = "1";

    var flickerAPI = url+'//by/researchStdSft/update';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"id":id,"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"standardId":standardId,"sftStdItemId":sftStdItemId,"sftStdItemName":sftStdItemName,"okVal":okVal,"okValAgree":okValAgree,"ngVal":ngVal,"remark":remark,"description":description,"isResearch":isResearch,"updId":updId}};
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
                if(currentItem == researchStdSftList.length - 1){
                    window.location = "BY121225.html";
                }else{
                    showNexItemInfo();
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
}
function showNexItemInfo(){
    $('html, body').animate({scrollTop:0}, 0);
    currentItem = currentItem + 1;
    if(researchStdSftList.length > 0){
        //指标内容
        $("#sftStdItemName").html("<span style='padding-left:25px;'>"+researchStdSftList[currentItem].sftStdItemName+"</span>");
        //指标编码
        $("#sftStdItemId").val(researchStdSftList[currentItem].sftStdItemId);
        //合格状态描述
        $("#okVal").val(researchStdSftList[currentItem].okVal);
        if(researchStdSftList[currentItem].okValAgree == 1){
            $("#okValAgree").attr("checked",true).checkboxradio("refresh");
        }else{
            $("#okValAgree").attr("checked",false).checkboxradio("refresh");
        }
        //不合格状态描述
        $("#ngVal").val(researchStdSftList[currentItem].ngVal);
        //备注
        if(researchStdSftList[currentItem].remark == undefined || researchStdSftList[currentItem].remark == null){

        }else{
            $("#remark").text(researchStdSftList[currentItem].remark);
        }
        //调研描述
        $("#description").val(researchStdSftList[currentItem].description);
        //当最后一项调研结束时，开始调研下一个选项卡
        if(currentItem == researchStdSftList.length - 1){
            $("#nextItem").text("储存运输标准");
        }else{
            $("#nextItem").text("下一项");
        }
    }
}
function returnPage(){
    window.location = "BY121216.html";
}
function skip(){
    if(currentItem == researchStdSftList.length - 1){
        window.location = "BY121225.html";
    }else{
        showNexItemInfo();
    }
}