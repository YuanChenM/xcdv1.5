//保存初期化从db获取到的数据
var researchStdGnqList;
//记录当前显示的是第几项
var currentItem = -1;
$(function(){
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = localStorage.breedCode;
    var breedName = localStorage.breedName;
    $("#breedStandard").text(breedName + "-通用质量标准");

    var flickerAPI = url+'//by/researchStdGnq/findList';
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
                researchStdGnqList = data.result;
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
    var id = researchStdGnqList[currentItem].id;
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = "01";
    var updId = localStorage.accessAccountName;
    var standardId = researchStdGnqList[currentItem].standardId;
    var gnqStdItemId = researchStdGnqList[currentItem].gnqStdItemId;
    var gnqStdItemName = researchStdGnqList[currentItem].gnqStdItemName;
    var okVal = researchStdGnqList[currentItem].okVal;
    var okValAgree = 0;
    if($("#okValAgree").is(":checked")){
        okValAgree = 1;
    }
    var ngVal = researchStdGnqList[currentItem].ngVal;
    var remark = "";
    if(researchStdGnqList[currentItem].remark){
        remark = researchStdGnqList[currentItem].remark;
    }
    var description = $("#description").val();
    var isResearch = "1";

    var flickerAPI = url+'//by/researchStdGnq/update';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"id":id,"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"standardId":standardId,"gnqStdItemId":gnqStdItemId,"gnqStdItemName":gnqStdItemName,"okVal":okVal,"okValAgree":okValAgree,"ngVal":ngVal,"remark":remark,"description":description,"isResearch":isResearch,"updId":updId}};
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
                if(currentItem == researchStdGnqList.length - 1){
                    window.location = "BY121224.html";
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
    if(researchStdGnqList.length > 0){
        //指标内容
        $("#gnqStdItemName").html("<span style='padding-left:25px;'>"+researchStdGnqList[currentItem].gnqStdItemName+"</span>");
        //指标编码
        $("#gnqStdItemId").val(researchStdGnqList[currentItem].gnqStdItemId);
        //合格状态描述
        $("#okVal").val(researchStdGnqList[currentItem].okVal);
        if(researchStdGnqList[currentItem].okValAgree == 1){
            $("#okValAgree").attr("checked",true).checkboxradio("refresh");
        }else{
            $("#okValAgree").attr("checked",false).checkboxradio("refresh");
        }
        //不合格状态描述
        $("#ngVal").val(researchStdGnqList[currentItem].ngVal);
        //备注
        if(researchStdGnqList[currentItem].remark == undefined || researchStdGnqList[currentItem].remark == null){

        }else{
            $("#remark").text(researchStdGnqList[currentItem].remark);
        }
        //调研描述
        $("#description").val(researchStdGnqList[currentItem].description);

        //当最后一项调研结束时，开始调研下一个选项卡
        if(currentItem == researchStdGnqList.length - 1){
            $("#nextItem").text("品种安全标准");
        }else{
            $("#nextItem").text("下一项");
        }
    }
}
function returnPage(){
    window.location = "BY121216.html";
}
function skip(){
    if(currentItem == researchStdGnqList.length - 1){
        window.location = "BY121224.html";
    }else{
        showNexItemInfo();
    }
}