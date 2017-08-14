//保存初期化从db获取到的数据
var researchStdMctList;
//记录当前显示的是第几项
var currentItem = -1;
$(function(){
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = localStorage.breedCode;
    var breedName = localStorage.breedName;
    $("#breedStandard").text(breedName + "-加工技术标准");

    var flickerAPI = url+'//by/researchStdMct/findList';
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
                researchStdMctList = data.result;
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
    var id = researchStdMctList[currentItem].id;
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = "01";
    var updId = localStorage.accessAccountName;
    var standardId = researchStdMctList[currentItem].standardId;
    var mctStdItemId = researchStdMctList[currentItem].mctStdItemId;
    var mctStdItemName = researchStdMctList[currentItem].mctStdItemName;
    var okVal = researchStdMctList[currentItem].okVal;
    var ngVal = researchStdMctList[currentItem].ngVal;
    var remark = "";
    if(researchStdMctList[currentItem].remark){
        remark = researchStdMctList[currentItem].remark;
    }
    var description = $("#description").val();
    var isResearch = "1";

    var flickerAPI = url+'//by/researchStdMct/update';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"id":id,"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"standardId":standardId,"mctStdItemId":mctStdItemId,"mctStdItemName":mctStdItemName,"okVal":okVal,"ngVal":ngVal,"remark":remark,"description":description,"isResearch":isResearch,"updId":updId}};
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
                if(currentItem == researchStdMctList.length - 1){
                    window.location = "BY121221.html";
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
    if(researchStdMctList.length > 0){
        //指标内容
        $("#orgStdItemName").html("<span style='padding-left:25px;'>"+researchStdMctList[currentItem].mctStdItemName+"</span>");
        //指标编码
        $("#orgStdItemId").val(researchStdMctList[currentItem].mctStdItemId);
        //合格描述
        $("#okVal").val(researchStdMctList[currentItem].okVal);
        //不合格描述
        $("#ngVal").val(researchStdMctList[currentItem].ngVal);
        //备注
        if(researchStdMctList[currentItem].remark == undefined || researchStdMctList[currentItem].remark == null){

        }else{
            $("#remark").text(researchStdMctList[currentItem].remark);
        }
        //调研描述
        $("#description").val(researchStdMctList[currentItem].description);

        //当最后一项调研结束时，开始调研下一个选项卡
        if(currentItem == researchStdMctList.length - 1){
            $("#nextItem").text("加工质量标准");
        }else{
            $("#nextItem").text("下一项");
        }
    }
}
function returnPage(){
    window.location = "BY121216.html";
}
function skip(){
    if(currentItem == researchStdMctList.length - 1){
        window.location = "BY121221.html";
    }else{
        showNexItemInfo();
    }
}