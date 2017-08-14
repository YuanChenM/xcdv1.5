var pdClassesCode = localStorage.pdClassesCode;
var pdClassesName = localStorage.pdClassesName;
var pdMachiningCode = localStorage.pdMachiningCode;
var pdMachiningName = localStorage.pdMachiningName;
var breedCode = localStorage.breedCode;
var breedName = localStorage.breedName;

$(function(){
    //当前选择的产品
    $("#currentSelectedPd").text(pdClassesName + pdMachiningName + breedName);

    //初始化显示规格数据
    if(localStorage.featureCode && localStorage.weightCode){
        getNormsStdList(localStorage.featureCode,localStorage.weightCode);
    }
    //根据产品分类,加工,品种获取特征
    var flickerAPI = url+'//by/common/pdFeature';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode}};
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
                $("#featureCode").append("<option>请选择</option>");
                for(var i = 0;i < data.result.length;i++){
                    if(localStorage.featureCode){
                        if(data.result[i].featureCode == localStorage.featureCode){
                            $("#featureCode").append("<option value='"+data.result[i].featureCode+"' selected>"+data.result[i].featureName+"</option>");
                        }else{
                            $("#featureCode").append("<option value='"+data.result[i].featureCode+"'>"+data.result[i].featureName+"</option>");
                        }
                    }else{
                        $("#featureCode").append("<option value='"+data.result[i].featureCode+"'>"+data.result[i].featureName+"</option>");
                    }
                }
                $("#featureCode").selectmenu("refresh");

                if(localStorage.featureCode){
                    getFeatureCodeList(localStorage.featureCode);
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
    //特征改变
    $("#featureCode").change(function(){
        var featureCode = $("#featureCode option:selected").val();
        getFeatureCodeList(featureCode);
    });
    //净重改变
    $("#weightCode").change(function(){
        var featureCode = $("#featureCode option:selected").val();
        var weightCode = $("#weightCode option:selected").val();
        getNormsStdList(featureCode,weightCode);
    });
});
//根据产品分类,加工,品种,特征获取净重
function getFeatureCodeList(featureCode){
    $("#weightCode").html("");
    var flickerAPI = url+'//by/common/pdWeight';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"featureCode":featureCode}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            $("#weightCode").append("<option>请选择</option>");
            if(data.status == "S"){
                for(var i = 0;i < data.result.length;i++){
                    if(localStorage.weightCode){
                        if(data.result[i].weightCode == localStorage.weightCode){
                            $("#weightCode").append("<option value='"+data.result[i].weightCode+"' selected>"+data.result[i].weightName+"</option>");
                        }else{
                            $("#weightCode").append("<option value='"+data.result[i].weightCode+"'>"+data.result[i].weightName+"</option>");
                        }
                        localStorage.removeItem("weightCode");
                        localStorage.removeItem("featureCode");
                    }else{
                        $("#weightCode").append("<option value='"+data.result[i].weightCode+"'>"+data.result[i].weightName+"</option>");
                    }
                }
            }
            $("#weightCode").selectmenu("refresh");
        },
        error:function(){
            alert("error");
        }
    });
}
//根据产品分类,加工,品种,特征,净重获取产品规格信息
function getNormsStdList(featureCode,weightCode){
    var flickerAPI = url+'//by/common/pdNormsStd';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"featureCode":featureCode,"weightCode":weightCode}};
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
                $("#normsStdDiv").html("");
                $("#normsStdOtherDiv").html("");
                var normsStdList = data.result;
                if(normsStdList != null && normsStdList.length < 5){
                    $("#totalCount").text("共"+normsStdList.length+"个");
                    for(var i = 0; i < normsStdList.length; i++){
                        $("#normsStdDiv").append("<div onclick='chooseNormStdClick("+normsStdList[i].standardId+",\""+normsStdList[i].normsCode+"\")' style='width: 80%;background-color:#F6F8FA;color:#52C2E9;height:40px;line-height:45px;margin-left:30px;text-shadow:none;border:1px #FFFFFF solid'><span style='padding-left:20px;'>"+normsStdList[i].normsOut+"</span></div>");
                    }
                }else if(normsStdList != null) {
                    $("#totalCount").text("共"+normsStdList.length + "个");
                    $("#normsStdOther").css("display","block");
                    for(var i = 0; i < normsStdList.length; i++){
                        var str = "<div onclick='chooseNormStdClick("+normsStdList[i].standardId+",\""+normsStdList[i].normsCode+"\")' style='width: 80%;background-color:#F6F8FA;color:#52C2E9;height:40px;line-height:45px;margin-left:30px;text-shadow:none;border:1px #FFFFFF solid'><span style='padding-left:20px;'>"+normsStdList[i].normsOut+"</span></div>";
                        if(i >= 5){
                            $("#normsStdOtherDiv").append(str);
                        }else{
                            $("#normsStdDiv").append(str);
                        }
                    }
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
}
//展开更多
function normsStdOther(){
    if($("#normsStdOther").text() == "展开更多"){
        $("#normsStdOther").html("<span style='padding-left:20px;'>收起</span>");
    }else{
        $("#normsStdOther").html("<span style='padding-left:20px;'>展开更多</span>");
    }
    $("#normsStdOtherDiv").slideToggle();
}
//点击规格跳转画面
function chooseNormStdClick(standardId,normsCode){
    //产品标准ID
    localStorage.standardId = standardId;
    //包装规格编码
    localStorage.normsCode = normsCode;
    //特征编码
    localStorage.featureCode = $("#featureCode option:selected").val();
    //净重编码
    localStorage.weightCode = $("#weightCode option:selected").val();

    window.location = "BY121227.html";
}
function returnPage(){
    window.location = "BY121216.html";
}