//保存初期化从db获取到的数据
var researchStdTncList;
//记录当前显示的是第几项
var currentItem = -1;
$(function(){
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = localStorage.breedCode;
    var breedName = localStorage.breedName;
    $("#breedStandard").text(breedName + "-加工质量标准");

    var flickerAPI = url+'//by/researchStdTnc/findList';
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
                researchStdTncList = data.result;
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
    var id = researchStdTncList[currentItem].id;
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = "01";
    var updId = localStorage.accessAccountName;
    var standardId = researchStdTncList[currentItem].standardId;
    var tncStdItemId = researchStdTncList[currentItem].tncStdItemId;
    var tncStdItemName = researchStdTncList[currentItem].tncStdItemName;
    var stdVal1 = researchStdTncList[currentItem].stdVal1;
    var stdVal2 = researchStdTncList[currentItem].stdVal2;
    var stdVal3 = researchStdTncList[currentItem].stdVal3;
    var remark = "";
    if(researchStdTncList[currentItem].remark){
        remark = researchStdTncList[currentItem].remark;
    }
    var description = $("#description").val();
    var isResearch = "1";

    var flickerAPI = url+'//by/researchStdTnc/update';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"id":id,"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"standardId":standardId,"tncStdItemId":tncStdItemId,"tncStdItemName":tncStdItemName,"stdVal1":stdVal1,"stdVal2":stdVal2,"stdVal3":stdVal3,"remark":remark,"description":description,"isResearch":isResearch,"updId":updId}};
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
                if(currentItem == researchStdTncList.length - 1){
                    window.location = "BY121222.html";
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
    if(researchStdTncList.length > 0){
        //指标内容
        $("#tncStdItemName").html("<span style='padding-left:25px;'>"+researchStdTncList[currentItem].tncStdItemName+"</span>");
        //指标编码
        $("#tncStdItemId").val(researchStdTncList[currentItem].tncStdItemId);
        //A1级质量标准(准入日)
        $("#stdVal1").val(researchStdTncList[currentItem].stdVal1);
        //A2级质量标准(准入日)
        $("#stdVal2").val(researchStdTncList[currentItem].stdVal2);
        //A3级质量标准(准入日)
        $("#stdVal3").val(researchStdTncList[currentItem].stdVal3);
        //备注
        if(researchStdTncList[currentItem].remark == undefined || researchStdTncList[currentItem].remark == null){

        }else{
            $("#remark").text(researchStdTncList[currentItem].remark);
        }
        //调研描述
        $("#description").val(researchStdTncList[currentItem].description);

        //当最后一项调研结束时，开始调研下一个选项卡
        if(currentItem == researchStdTncList.length - 1){
            $("#nextItem").text("产品饲养标准");
        }else{
            $("#nextItem").text("下一项");
        }
    }
}
function returnPage(){
    window.location = "BY121216.html";
}
function skip(){
    if(currentItem == researchStdTncList.length - 1){
        window.location = "BY121222.html";
    }else{
        showNexItemInfo();
    }
}