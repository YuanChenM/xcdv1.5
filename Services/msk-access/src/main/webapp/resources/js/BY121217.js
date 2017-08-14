var buyerId = localStorage.buyerId;
var pdClassesCode = localStorage.pdClassesCode;
var pdClassesName = localStorage.pdClassesName;
var pdMachiningCode = localStorage.pdMachiningCode;
var pdMachiningName = localStorage.pdMachiningName;
var breedCode = "";
if(localStorage.breedCode){
    breedCode = localStorage.breedCode;
}
var breedName = localStorage.breedName;
var isStandard = localStorage.isStandard;

$(function(){
    //当前选择的产品
    $("#currentSelectedPd").text(pdClassesName + pdMachiningName + breedName);

    if(isStandard == "1"){
        $("#pdFeatureDiv").css("display","block");
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
                    $("#pdFeature").append("<option>请选择</option>");
                    for(var i = 0;i < data.result.length;i++){
                        $("#pdFeature").append("<option value='"+data.result[i].featureCode+"'>"+data.result[i].featureName+"</option>");
                    }
                    $("#pdFeature").append("<option value=''>其他</option>");
                    $("#pdFeature").selectmenu("refresh");
                }
            },
            error:function(){
                alert("error");
            }
        });
        var resultList = findNormsList(breedCode,null,null,isStandard);
        if(resultList.length > 0){
            for(var i = 0;i < resultList.length;i++){
                $("#pdNormsList").append(showBreedInfo(resultList[i]));
            }
        }
        //特征改变
        $("#pdFeature").change(function(){
            var featureCode = $("#pdFeature option:selected").val();
            if(featureCode == "请选择"){
                featureCode = null;
            }
            var resultList = findNormsList(breedCode,null,featureCode,isStandard);
            $("#pdNormsList").html("");
            if(resultList.length > 0){
                for(var i = 0;i < resultList.length;i++){
                    $("#pdNormsList").append(showBreedInfo(resultList[i]));
                }
                $("#addNewFeatureB").text("添加新产品特征");
            }else if(resultList.length == 0){
                if(featureCode != "" && featureCode != null){
                    $("#addNewFeatureB").text("添加为产品特征");
                }else{
                    $("#addNewFeatureB").text("添加新产品特征");
                }
            }
        });
    }else{
        $("#pdFeatureDiv").css("display","none");
        var resultList = findNormsList(null,breedName,null,isStandard);
        if(resultList.length == 0){
            localStorage.removeItem("detailId");
            localStorage.removeItem("featureCode");
            localStorage.removeItem("demandFeature");
            window.location = "BY121218.html";
        }else{
            for(var i = 0;i < resultList.length;i++){
                $("#pdNormsList").append(showBreedInfo(resultList[i]));
            }
        }
    }
});
//获取产品规格信息
function findNormsList(breedCode,breedName,featureCode,isStandard){
    var resultList = [];
    if(breedCode == null){
        breedCode = "";
    }
    var flickerAPI = url+'//by/researchCatDetail/findList';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"saleName":breedName,"featureCode":featureCode,"isStandard":isStandard}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            resultList = data.result;
        },
        error:function(){
            alert("error");
        }
    });
    return resultList;
}
//显示规格信息
function  showBreedInfo(result){
    var str = "<div style='width:100%;' class='listDiv' onclick='breedInfoModify("+result.detailId+");'>";
    str = str + "<div>产品需求规格："+ result.demandFeature+"</div>";
    if(result.hasDemand == "1"){
        str = str + "<div>是否有需求：是</div>";
    }else{
        str = str + "<div>是否有需求：否</div>";
    }
    if(result.demandQty == undefined && result.demandQty == null){
        str = str + "<div>日需求量(箱)：</div>";
    }else{
        str = str + "<div>日需求量(箱)：" + result.demandQty + "</div>";
    }
    if(result.orderQty == undefined && result.orderQty == null){
        str = str + "<div>订货量(箱)：</div>";
    }else{
        str = str + "<div>订货量(箱)：" + result.orderQty + "</div>";
    }
    if(result.hopePrice == undefined && result.hopePrice == null){
        str = str + "<div>希望价格区间(元/箱)：</div>";
    }else{
        str = str + "<div>希望价格区间(元/箱)：" + result.hopePrice + "</div>";
    }
    if(result.delivery == undefined && result.delivery == null){
        str = str + "<div>配送时间：</div>";
    }else{
        str = str + "<div>配送时间：" + result.delivery + "</div>";
    }
    if(result.remark == undefined && result.remark == null){
        str = str + "<div>备注：</div>";
    }else{
        str = str + "<div>备注：" + result.remark + "</div>";
    }
    str = str + "</div>";
    return str;
}
//产品特征新增
function addNewFeature(){
    localStorage.removeItem("detailId");
    if(isStandard == "1"){
        if($("#addNewFeatureB").text() == "添加为产品特征"){
            var featureCode = $("#pdFeature option:selected").val();
            var demandFeature = $("#pdFeature option:selected").text();
            localStorage.featureCode = featureCode;
            localStorage.demandFeature = demandFeature;
        }else{
            localStorage.removeItem("featureCode");
            localStorage.removeItem("demandFeature");
        }
    }else{
        localStorage.removeItem("featureCode");
        localStorage.removeItem("demandFeature");
    }
    window.location = "BY121218.html";
}
//产品特征修改
function breedInfoModify(detailId){
    localStorage.removeItem("featureCode");
    localStorage.removeItem("demandFeature");
    localStorage.detailId = detailId;
    window.location = "BY121218.html";
}
function returnPage(){
    window.location = "BY121216.html";
}