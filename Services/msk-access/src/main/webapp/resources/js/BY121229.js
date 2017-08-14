var pdClassesCode = localStorage.pdClassesCode;
var pdMachiningCode = localStorage.pdMachiningCode;
var brandPicPath = "";
var breedCode = "";
var saleName = localStorage.breedName;
$(function(){
    if(localStorage.breedCode){
        breedCode = localStorage.breedCode;
        saleName = "";
    }
    $("#breedName").text(saleName);
});
/*
 * 证件照片上传
 */
function upload() {
    //获取上传照片的名称
    var path = $("#importFile").val();
    var pos = path.lastIndexOf('\\');
    var result = path.substring(pos + 1, path.length);
    //设置证照的路径
    brandPicPath = pictureUrl + localStorage.buyerId + "/" + result;
    var $form = $("#uploadForm");
    $form.attr("action",uploadPath);
    $form.attr("target", "hidden_frame");
    var actionTypeHid = "<input type='hidden' id='actionType' name='actionType' value='uploadCommon'/>";
    $form.append(actionTypeHid);
    // 买家ID
    var buyerIdHid = $("#buyerId").val();
    if (!buyerIdHid) {
        var buyerId = "<input type='hidden' id='buyerId' name='buyerId' value='" + localStorage.buyerId + "'/>";
        $form.append(buyerId);
    }
}

function fileUpload(){
    $("#uploadForm").ajaxSubmit(function(message) {
        setTimeout(function(){
            $("#brandPic").html("<img src='"+brandPicPath+"' style='width: 270;height: 200px;'>");
        },0);
    });
    return false;
}

function brandSave(){
    if(brandPicPath ==""){
        setMessageDivStyle("请上传第三方品牌照片！");
        return;
    }
    var brandName = $("#brandName").val();
    var flickerAPI = url+'//by/researchBrand/update';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId":localStorage.buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"breedCode":breedCode,"saleName":saleName,"brandName":brandName,"picturePath":brandPicPath,"updId":localStorage.accessAccountName}};
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
                window.location = "BY121228.html";
            }
        },
        error:function(){
            alert("error");
        }
    });
}


function returnPage(){
    window.location = "BY121228.html";
}

//设置messageDiv样式
function setMessageDivStyle(message){
    $("#message").css("border","1px #FF0000 solid");
    $("#message").css("color","#FF0000");
    $("#message").css("display","block");
    $("#message").text(message);
}