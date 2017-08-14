var buyerId = localStorage.buyerId;
var pdClassesCode = localStorage.pdClassesCode;
var pdMachiningCode = localStorage.pdMachiningCode;
var breedCode = "";
if(localStorage.breedCode){
    breedCode = localStorage.breedCode;
}
//产品是否在目录中
var isStandard = localStorage.isStandard;

$(function(){
    //新增
    if(localStorage.featureCode){
        $("#demandFeature").val(localStorage.demandFeature);
        $("#demandFeature").attr("readonly","readonly");
    }
    //修改
    if(localStorage.detailId){
        getCategoryData(localStorage.detailId);
    }
});

//是否有需求
function isDemand(){
    if($("#hasDemand").is(":checked")){
        $("#demandQty").removeAttr("readonly");
    }else{
        $("#demandQty").attr("readonly","readonly");
    }
}
//根据选择的特征编码查询调研信息
function getCategoryData(detailId){
    var flickerAPI = url+'//by/researchCatDetail/findList';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"detailId":detailId,"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode}};
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
                if(data.result.length == 1){
                    $("#demandFeature").val(data.result[0].demandFeature);
                    if(data.result[0].hasDemand == "1"){
                        $("#hasDemand").attr("checked",true).checkboxradio("refresh");
                        $("#demandQty").val(data.result[0].demandQty);
                    }else{
                        $("#hasDemand").attr("checked",false).checkboxradio("refresh");
                    }
                    $("#orderQty").val(data.result[0].orderQty);
                    $("#hopePrice").val(data.result[0].hopePrice);
                    $("#delivery").val(data.result[0].delivery);
                    $("#remark").val(data.result[0].remark);
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
}
//产品品类调研数据保存
function categorySubmit(){
    var featureCode = "";
    if(localStorage.featureCode){
        featureCode = localStorage.featureCode;
    }
    var detailId = "";
    if(localStorage.detailId){
        detailId = localStorage.detailId;
    }
    var saleName = "";
    if(breedCode == ""){
        saleName = localStorage.breedName;
    }
    var demandFeature = $("#demandFeature").val();
    var hasDemand = 0;
    if($("#hasDemand").is(":checked")){
        hasDemand = 1;
    }
    var demandQty = $("#demandQty").val();
    var orderQty = $("#orderQty").val();
    var hopePrice = $("#hopePrice").val();
    var delivery = $("#delivery").val();
    var remark = $("#remark").val();
    var flickerAPI = url+'//by/researchCatDetail/update';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"detailId":detailId,"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"saleName":saleName,"featureCode":featureCode,"demandFeature":demandFeature,"hasDemand":hasDemand,"demandQty":demandQty,"orderQty":orderQty,"hopePrice":hopePrice,"delivery":delivery,"remark":remark,"isStandard":isStandard}};
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
                window.location = "BY121217.html";
            }
        },
        error:function(){
            alert("error");
        }
    });
}
//跳过
function skip(){
    window.location = "BY121217.html";
}
function returnPage(){
    window.location = "BY121216.html";
}