//保存初期化从db获取到的数据
var researchStdTspList;
//记录当前显示的是第几项
var currentItem = -1;
$(function(){
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = localStorage.breedCode;
    var breedName = localStorage.breedName;
    $("#breedStandard").text(breedName + "-储存运输标准");

    var flickerAPI = url+'//by/researchStdTsp/findList';
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
                researchStdTspList = data.result;
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
    var id = researchStdTspList[currentItem].id;
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = "01";
    var updId = localStorage.accessAccountName;
    var standardId = researchStdTspList[currentItem].standardId;
    var tspStdItemId = researchStdTspList[currentItem].tspStdItemId;
    var tspStdItemName = researchStdTspList[currentItem].tspStdItemName;
    var okVal = researchStdTspList[currentItem].okVal;
    var okValAgree = 0;
    if($("#okValAgree").is(":checked")){
        okValAgree = 1;
    }
    var ngVal = researchStdTspList[currentItem].ngVal;
    var remark = "";
    if(researchStdTspList[currentItem].remark){
        remark = researchStdTspList[currentItem].remark;
    }
    var description = $("#description").val();
    var isResearch = "1";

    var flickerAPI = url+'//by/researchStdTsp/update';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"id":id,"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"standardId":standardId,"tspStdItemId":tspStdItemId,"tspStdItemName":tspStdItemName,"okVal":okVal,"okValAgree":okValAgree,"ngVal":ngVal,"remark":remark,"description":description,"isResearch":isResearch,"updId":updId}};
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
                if(currentItem == researchStdTspList.length - 1){
                    window.location = "BY121226.html";
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
    if(researchStdTspList.length > 0){
        //指标内容
        $("#tspStdItemName").html("<span style='padding-left:25px;'>"+researchStdTspList[currentItem].tspStdItemName+"</span>");
        //指标编码
        $("#tspStdItemId").val(researchStdTspList[currentItem].tspStdItemId);
        //合格状态描述
        $("#okVal").val(researchStdTspList[currentItem].okVal);
        if(researchStdTspList[currentItem].okValAgree == 1){
            $("#okValAgree").attr("checked",true).checkboxradio("refresh");
        }else{
            $("#okValAgree").attr("checked",false).checkboxradio("refresh");
        }
        //不合格状态描述
        $("#ngVal").val(researchStdTspList[currentItem].ngVal);
        //备注
        if(researchStdTspList[currentItem].remark == undefined || researchStdTspList[currentItem].remark == null){

        }else{
            $("#remark").text(researchStdTspList[currentItem].remark);
        }
        //调研描述
        $("#description").val(researchStdTspList[currentItem].description);
        //当最后一项调研结束时，开始调研下一个选项卡
        if(currentItem == researchStdTspList.length - 1){
            $("#nextItem").text("品种包装标准");
        }else{
            $("#nextItem").text("下一项");
        }
    }
}
function returnPage(){
    window.location = "BY121216.html";
}
function skip(){
    if(currentItem == researchStdTspList.length - 1){
        window.location = "BY121226.html";
    }else{
        showNexItemInfo();
    }
}