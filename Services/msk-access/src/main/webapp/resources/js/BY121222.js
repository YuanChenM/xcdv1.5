//保存初期化从db获取到的数据
var researchStdFedList;
//记录当前显示的是第几项
var currentItem = -1;
$(function(){
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = localStorage.breedCode;
    var breedName = localStorage.breedName;
    $("#breedStandard").text(breedName + "-产品饲养标准");

    var flickerAPI = url+'//by/researchStdFed/findList';
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
                researchStdFedList = data.result;
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
    var id = researchStdFedList[currentItem].id;
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = "01";
    var updId = localStorage.accessAccountName;
    var standardId = researchStdFedList[currentItem].standardId;
    var fedStdItemId = researchStdFedList[currentItem].fedStdItemId;
    var fedStdItemName = researchStdFedList[currentItem].fedStdItemName;
    var goodVal = researchStdFedList[currentItem].goodVal;
    var goodValAgree = 0;
    if($("#goodValAgree").is(":checked")){
        goodValAgree = 1;
    }
    var normalVal = researchStdFedList[currentItem].normalVal;
    var normalValAgree = 0;
    if($("#normalValAgree").is(":checked")){
        normalValAgree = 1;
    }
    var badVal = researchStdFedList[currentItem].badVal;
    var badValAgree = 0;
    if($("#badValAgree").is(":checked")){
        badValAgree = 1;
    }
    var remark = "";
    if(researchStdFedList[currentItem].remark){
        remark = researchStdFedList[currentItem].remark;
    }
    var description = $("#description").val();
    var isResearch = "1";

    var flickerAPI = url+'//by/researchStdFed/update';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"id":id,"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"standardId":standardId,"fedStdItemId":fedStdItemId,"fedStdItemName":fedStdItemName,"goodVal":goodVal,"goodValAgree":goodValAgree,"normalVal":normalVal,"normalValAgree":normalValAgree,"badVal":badVal,"badValAgree":badValAgree,"remark":remark,"description":description,"isResearch":isResearch,"updId":updId}};
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
                if(currentItem == researchStdFedList.length - 1){
                    window.location = "BY121223.html";
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
    if(researchStdFedList.length > 0){
        //指标内容
        $("#fedStdItemName").html("<span style='padding-left:25px;'>"+researchStdFedList[currentItem].fedStdItemName+"</span>");
        //指标编码
        $("#fedStdItemId").val(researchStdFedList[currentItem].fedStdItemId);
        //优良
        $("#goodVal").val(researchStdFedList[currentItem].goodVal);
        if(researchStdFedList[currentItem].goodValAgree == 1){
            $("#goodValAgree").attr("checked",true).checkboxradio("refresh");
        }else{
            $("#goodValAgree").attr("checked",false).checkboxradio("refresh");
        }
        //一般
        $("#normalVal").val(researchStdFedList[currentItem].normalVal);
        if(researchStdFedList[currentItem].normalValAgree == 1){
            $("#normalValAgree").attr("checked",true).checkboxradio("refresh");
        }else{
            $("#normalValAgree").attr("checked",false).checkboxradio("refresh");
        }
        //差
        $("#badVal").val(researchStdFedList[currentItem].badVal);
        if(researchStdFedList[currentItem].badValAgree == 1){
            $("#badValAgree").attr("checked",true).checkboxradio("refresh");
        }else{
            $("#badValAgree").attr("checked",false).checkboxradio("refresh");
        }
        //备注
        if(researchStdFedList[currentItem].remark == undefined || researchStdFedList[currentItem].remark == null){

        }else{
            $("#remark").text(researchStdFedList[currentItem].remark);
        }
        //调研描述
        $("#description").val(researchStdFedList[currentItem].description);

        //当最后一项调研结束时，开始调研下一个选项卡
        if(currentItem == researchStdFedList.length - 1){
            $("#nextItem").text("通用质量标准");
        }else{
            $("#nextItem").text("下一项");
        }
    }
}
function returnPage(){
    window.location = "BY121216.html";
}
function skip(){
    if(currentItem == researchStdFedList.length - 1){
        window.location = "BY121223.html";
    }else{
        showNexItemInfo();
    }
}