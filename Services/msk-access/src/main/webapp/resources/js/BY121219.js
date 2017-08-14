//保存初期化从db获取到的数据
var researchStdOrgList;
//记录当前显示的是第几项
var currentItem = -1;
$(function(){
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = localStorage.breedCode;
    var breedName = localStorage.breedName;
    $("#breedStandard").text(breedName + "-原种种源标准");

    var flickerAPI = url+'//by/researchStdOrg/findList';
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
                researchStdOrgList = data.result;
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
    var id = researchStdOrgList[currentItem].id;
    var buyerId = localStorage.buyerId;
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var breedCode = "01";
    var updId = localStorage.accessAccountName;
    var standardId = researchStdOrgList[currentItem].standardId;
    var orgStdItemId = researchStdOrgList[currentItem].orgStdItemId;
    var orgStdItemName = researchStdOrgList[currentItem].orgStdItemName;
    var goodVal = researchStdOrgList[currentItem].goodVal;
    var goodValAgree = 0;
    if($("#agreeGoodFlg").is(":checked")){
        goodValAgree = 1;
    }
    var normalVal = researchStdOrgList[currentItem].normalVal;
    var normalValAgree = 0;
    if($("#agreeNormalFlg").is(":checked")){
        normalValAgree = 1;
    }
    var badVal = researchStdOrgList[currentItem].badVal;
    var badValAgree = 0;
    if($("#agreeBadFlg").is(":checked")){
        badValAgree = 1;
    }
    var remark = "";
    if(researchStdOrgList[currentItem].remark){
        remark = researchStdOrgList[currentItem].remark;
    }
    var description = $("#description").val();
    var isResearch = "1";

    var flickerAPI = url+'//by/researchStdOrg/update';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"id":id,"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"standardId":standardId,"orgStdItemId":orgStdItemId,"orgStdItemName":orgStdItemName,"goodVal":goodVal,"goodValAgree":goodValAgree,"normalVal":normalVal,"normalValAgree":normalValAgree,"badVal":badVal,"badValAgree":badValAgree,"remark":remark,"description":description,"isResearch":isResearch,"updId":updId}};
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
                if(currentItem == researchStdOrgList.length - 1){
                    window.location = "BY121220.html";
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
    if(researchStdOrgList.length > 0){
        //指标内容
        $("#orgStdItemName").html("<span style='padding-left:25px;'>"+researchStdOrgList[currentItem].orgStdItemName+"</span>");
        //指标编码
        $("#orgStdItemId").val(researchStdOrgList[currentItem].orgStdItemId);
        //优良
        $("#targetGoodLevel").val(researchStdOrgList[currentItem].goodVal);
        if(researchStdOrgList[currentItem].goodValAgree == 1){
            $("#agreeGoodFlg").attr("checked",true).checkboxradio("refresh");
        }else{
            $("#agreeGoodFlg").attr("checked",false).checkboxradio("refresh");
        }
        //一般
        $("#targetNormalLevel").val(researchStdOrgList[currentItem].normalVal);
        if(researchStdOrgList[currentItem].normalValAgree == 1){
            $("#agreeNormalFlg").attr("checked",true).checkboxradio("refresh");
        }else{
            $("#agreeNormalFlg").attr("checked",false).checkboxradio("refresh");
        }
        //差
        $("#targetBadLevel").val(researchStdOrgList[currentItem].badVal);
        if(researchStdOrgList[currentItem].badValAgree == 1){
            $("#agreeBadFlg").attr("checked",true).checkboxradio("refresh");
        }else{
            $("#agreeBadFlg").attr("checked",false).checkboxradio("refresh");
        }
        //备注
        if(researchStdOrgList[currentItem].remark == undefined || researchStdOrgList[currentItem].remark == null){

        }else{
            $("#remark").text(researchStdOrgList[currentItem].remark);
        }
        //调研描述
        $("#description").val(researchStdOrgList[currentItem].description);

        //当最后一项调研结束时，开始调研下一个选项卡
        if(currentItem == researchStdOrgList.length - 1){
            $("#nextItem").text("加工技术标准");
        }else{
            $("#nextItem").text("下一项");
        }
    }
}

function returnPage(){
    window.location = "BY121216.html";
}
function skip(){
    if(currentItem == researchStdOrgList.length - 1){
        window.location = "BY121220.html";
    }else{
        showNexItemInfo();
    }
}