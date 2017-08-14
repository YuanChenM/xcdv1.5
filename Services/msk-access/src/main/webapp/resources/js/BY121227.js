var buyerId = localStorage.buyerId;
//产品类别编码
var pdClassesCode = localStorage.pdClassesCode;
//产品加工编码
var pdMachiningCode = localStorage.pdMachiningCode;
//产品品种编码
var breedCode = localStorage.breedCode;
//产品品种名
var breedName = localStorage.breedName;
//产品标准ID
var standardId = localStorage.standardId;
//包装规格编码
var normsCode = localStorage.normsCode;
$(function(){
    $("#breedStandard").text(breedName + "-品种包装标准");
    //获取产品包装规格标准数据
    var flickerAPI = url+'//by/researchStdNor/findList';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId":buyerId,"standardId":standardId,"normsCode":normsCode}};
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
                    $("#normName").html("<span style='padding-left:25px;'>"+data.result[0].normsOut+"</span>");
                    //单个产品规格净重
                    $("#normsSuttle").val(data.result[0].normsSuttle);
                    //单个产品规格净重误差范围
                    $("#normsError").val(data.result[0].normsError);
                    //内包装净重/个数
                    $("#normsNumber").val(data.result[0].normsNumber);
                    //内包装尺寸
                    $("#normsSize").val(data.result[0].normsSize);
                    //内包装材质及技术标准
                    $("#normsTexture").val(data.result[0].normsTexture);
                    //外包装规格
                    $("#normsOut").val(data.result[0].normsOut);
                    //外包装净重/毛重
                    $("#normsKg").val(data.result[0].normsKg);
                    //外包装尺寸
                    $("#normsOutSize").val(data.result[0].normsOutSize);
                    //外包装材质及技术标准
                    $("#normsOutTexture").val(data.result[0].normsOutTexture);
                    if(data.result[0].normsSuttleR){
                        //单个产品规格净重
                        $("#normsSuttleR").val(data.result[0].normsSuttleR);
                    }
                    if(data.result[0].normsErrorR){
                        //单个产品规格净重误差范围
                        $("#normsErrorR").val(data.result[0].normsErrorR);
                    }
                    if(data.result[0].normsNumberR){
                        //内包装净重/个数
                        $("#normsNumberR").val(data.result[0].normsNumberR);
                    }
                    if(data.result[0].normsSizeR){
                        //内包装尺寸
                        $("#normsSizeR").val(data.result[0].normsSizeR);
                    }
                    if(data.result[0].normsTextureR){
                        //内包装材质及技术标准
                        $("#normsTextureR").val(data.result[0].normsTextureR);
                    }
                    if(data.result[0].normsOutR){
                        //外包装规格
                        $("#normsOutR").val(data.result[0].normsOutR);
                    }
                    if(data.result[0].normsKgR){
                        //外包装净重/毛重
                        $("#normsKgR").val(data.result[0].normsKgR);
                    }
                    if(data.result[0].normsOutSizeR){
                        //外包装尺寸
                        $("#normsOutSizeR").val(data.result[0].normsOutSizeR);
                    }
                    if(data.result[0].normsOutTextureR){
                        //外包装材质及技术标准
                        $("#normsOutTextureR").val(data.result[0].normsOutTextureR);
                    }
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
});
//提交调研数据
function normsResearchSumit(){
    //标准数据
    //单个产品规格净重
    var normsSuttle = $("#normsSuttle").val();
    //单个产品规格净重误差范围
    var normsError = $("#normsError").val();
    //内包装净重/个数
    var normsNumber = $("#normsNumber").val();
    //内包装尺寸
    var normsSize = $("#normsSize").val();
    //内包装材质及技术标准
    var normsTexture = $("#normsTexture").val();
    //外包装规格
    var normsOut = $("#normsOut").val();
    //外包装净重/毛重
    var normsKg = $("#normsKg").val();
    //外包装尺寸
    var normsOutSize = $("#normsOutSize").val();
    //外包装材质及技术标准
    var normsOutTexture = $("#normsOutTexture").val();
    //调研数据
    //单个产品规格净重调研值
    var normsSuttleR = $("#normsSuttleR").val();
    //单个产品规格净重误差范围调研值
    var normsErrorR = $("#normsErrorR").val();
    //内包装净重/个数调研值
    var normsNumberR = $("#normsNumberR").val();
    //内包装尺寸调研值
    var normsSizeR = $("#normsSizeR").val();
    //内包装材质及技术标准调研值
    var normsTextureR = $("#normsTextureR").val();
    //外包装规格调研值
    var normsOutR = $("#normsOutR").val();
    //外包装净重/毛重调研值
    var normsKgR = $("#normsKgR").val();
    //外包装尺寸调研值
    var normsOutSizeR = $("#normsOutSizeR").val();
    //外包装材质及技术标准调研值
    var normsOutTextureR = $("#normsOutTextureR").val();
    //保存调研数据
    var flickerAPI = url+'//by/researchStdNor/update';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"standardId":standardId,"normsCode":normsCode,"normsSuttle":normsSuttle,"normsError":normsError,"normsNumber":normsNumber,"normsSize":normsSize,"normsTexture":normsTexture,"normsOut":normsOut,"normsKg":normsKg,"normsOutSize":normsOutSize,"normsOutTexture":normsOutTexture,"normsSuttleR":normsSuttleR,"normsErrorR":normsErrorR,"normsNumberR":normsNumberR,"normsSizeR":normsSizeR,"normsTextureR":normsTextureR,"normsOutR":normsOutR,"normsKgR":normsKgR,"normsOutSizeR":normsOutSizeR,"normsOutTextureR":normsOutTextureR,"isResearch":"1","updId":localStorage.accessAccountName}};
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
                window.location = "BY121226.html";
            }
        },
        error:function(){
            alert("error");
        }
    });
}
function returnPage(){
    window.location = "BY121216.html";
}
function skip(){
    window.location = "BY121226.html";
}