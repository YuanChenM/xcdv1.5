var buyerId = localStorage.buyerId;
var pdClassesCode = localStorage.pdClassesCode;
var pdClassesName = localStorage.pdClassesName;
var pdMachiningCode = localStorage.pdMachiningCode;
var pdMachiningName = localStorage.pdMachiningName;
var breedName = localStorage.breedName;

$(function(){
    //当前选择的产品
    $("#currentSelectedPd").text(pdClassesName + pdMachiningName + breedName);
    var breedCode = "";
    var saleName = breedName;
    if(localStorage.breedCode){
        breedCode = localStorage.breedCode;
        saleName = "";
    }
    var flickerAPI = url+'//by/researchBrand/findList';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"saleName":saleName}};
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
                if(data.result.length > 0){
                    setData(data.result);
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
});
//删除品牌
function deleteBrand(productId,categoryId){
    var productId = productId;
    var categoryId = categoryId;

    var breedCode = "";
    var saleName = breedName;
    if(localStorage.breedCode){
        breedCode = localStorage.breedCode;
        saleName = "";
    }

    var flickerAPI = url+'//by/researchBrand/delete';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"saleName":saleName,"productId":productId,"categoryId":categoryId,"updId":localStorage.accessAccountName}};
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
                setData(data.result);
            }
        },
        error:function(){
            alert("error");
        }
    });
}

function setData(resultList){
    $("#pdBrandList").html("");
    for(var i = 0;i < resultList.length;i++){
        var str = "<div class='ui-grid-b' style='border:1px #CCCCCC solid;font-size:17px;background-color:#F6F8FA;color:#CECECE;height:150px;width: 100%;'>";
        str = str + "<div class='ui-block-a' style='width:45%;height:100%;'>";
        if(resultList[i].picturePath != undefined && resultList[i].picturePath != null){
            str = str + "<Img src='"+resultList[i].picturePath+"' style='height:100%;width:120px;'/>";
        }else{
            str = str + "<Img style='height:100%;width:120px;'/>";
        }
        str = str + "</div>";
        str = str + "<div class='ui-block-b' style='width:35%;height:100%;'>";
        str = str + "<div style='margin-top:10px;'>产品名:</div>";
        str = str + "<div style='margin-top:10px;margin-left:10px;'>"+breedName+"</div>";
        str = str + "<div style='margin-top:10px;'>品牌名:</div>";
        str = str + "<div style='margin-top:10px;margin-left:10px;'>"+resultList[i].brandName+"</div>";
        str = str + "</div>";
        str = str + "<div id='deleteFlg' class='ui-block-c' style='width:20%;text-align: center;height:150px;line-height:150px;background-color: #FF0000;color:#FFFFFF;text-shadow:none;' onclick='deleteBrand(\""+resultList[i].productId+"\","+resultList[i].categoryId+")'>删除</div>";
        str = str + "</div>";

        $("#pdBrandList").append(str);
    }
}

function addNewBrand(){
    window.location = "BY121229.html";
}
function returnPage(){
    window.location = "BY121216.html";
}