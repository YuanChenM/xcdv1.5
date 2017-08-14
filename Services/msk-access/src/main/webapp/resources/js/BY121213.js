$(function(){
    // 获取买家经营产品类别选项
    var pdClassesCommon=[];
    var flickerAPI = url+'/by/common/pdClass';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {}};
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
                pdClassesCommon = data.result;
            }
        },
        error:function(){
            alert("error");
        }
    });
    //产品类别下拉框设值
    $("#pdClasses").append("<option>请选择</option>");
    if(pdClassesCommon.length > 0){
        for(var i = 0;i < pdClassesCommon.length;i++){
            $("#pdClasses").append("<option value='"+pdClassesCommon[i].classesCode+"'>"+pdClassesCommon[i].classesName+"</option>");
        }
        $("#pdClasses").selectmenu("refresh");
    }

    //产品类别下拉框值变更
    $("#pdClasses").change(function(){
        // 获取买家经营产品类别选项
        var pdMachiningCommon=[];
        var flickerAPI = url+'/by/common/pdMachining';
        var classesCode = $("#pdClasses option:selected").val();
        $("#pdMachining").html("");
        var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"classesCode":classesCode}};
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
                    pdMachiningCommon = data.result;
                }
            },
            error:function(){
                alert("error");
            }
        });
        $("#pdMachining").append("<option>请选择</option>");
        if(pdMachiningCommon.length > 0){
            for(var i = 0;i < pdMachiningCommon.length;i++){
                $("#pdMachining").append("<option value='"+pdMachiningCommon[i].machiningCode+"'>"+pdMachiningCommon[i].machiningName+"</option>");
            }
        }
        $("#pdMachining").selectmenu("refresh");
        $("#totalBreed").text("共有0种品种");
    });
    //产品类别下拉框值变更
    $("#pdMachining").change(function(){
        //将选择的产品分类放到本地存储
        var pdClassesCode = $("#pdClasses option:selected").val();
        var pdClassesName = $("#pdClasses option:selected").text();
        var pdMachiningCode = $("#pdMachining option:selected").val();
        var pdMachiningName = $("#pdMachining option:selected").text();
        localStorage.pdClassesCode = pdClassesCode;
        localStorage.pdClassesName = pdClassesName;
        localStorage.pdMachiningCode = pdMachiningCode;
        localStorage.pdMachiningName = pdMachiningName;

        // 获取获取品种
        var pdBreedCommon=[];
        var flickerAPI = url+'/by/common/pdBreed';
        var classesCode = $("#pdClasses option:selected").val();
        var machiningCode = $("#pdMachining option:selected").val();
        var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"classesCode":classesCode,"machiningCode":machiningCode}};
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
                    pdBreedCommon = data.result;
                }
            },
            error:function(){
                alert("error");
            }
        });
        $("#totalBreed").text("共有"+pdBreedCommon.length+"种品种");
    });

});

function breedSelect(){
    window.location = "BY121214.html";
}
function returnPage(){
    window.location = "BY121212.html";
}