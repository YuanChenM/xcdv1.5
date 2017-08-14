$(function(){

    if(localStorage.enterFlg == "edit" || localStorage.enterFlg == "view"){
        $("#titleDiv").text("买家信息编辑 - 证照信息");
        $("#saveButtonDiv").text("保存");
    }else{
        $("#titleDiv").text("新买家注册 - 证照信息");
        $("#saveButtonDiv").text("下一步 2/8");
    }

    // 获取企业证照类型下拉框
    var LicType=[];
    var flickerAPI = url+'/by/common/master';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"constantType": "LicType"}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            LicType = data.result;
        },
        error:function(){
            alert("error");
        }
    });

    // 获取个人证照类型下拉框
    var LegalLicType=[];
    var flickerAPI = url+'/by/common/master';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"constantType": "LegalLicType"}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            LegalLicType = data.result;
        },
        error:function(){
            alert("error");
        }
    });

    // 初期显示
    var flickerAPI = url+'/by/licence/find';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId": localStorage.buyerId}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "F"){
                return;
            }
            var licenseInfo = data.result;
            //生成营业执照下拉框
            var licTypeSelect = $("#licType");
            licTypeSelect.html("");
            for(var i = 0; i< LicType.length; i++){
                if(LicType[i].constantValue == licenseInfo.licName){
                    licTypeSelect.append("<option selected value="+LicType[i].constantValue+">"+LicType[i].constantName + "</option>")
                } else {
                    licTypeSelect.append("<option value="+LicType[i].constantValue+">"+LicType[i].constantName + "</option>")
                }
            }
            licTypeSelect.selectmenu("refresh");

            $("#licNumber").val(licenseInfo.licNumber);

            //生成法定代表人下拉框
            var legalLicTypeSelect = $("#legalLicType");
            legalLicTypeSelect.html("");
            for(var i = 0; i< LegalLicType.length; i++){
                if(LegalLicType[i].constantValue == licenseInfo.legalLicType){
                    legalLicTypeSelect.append("<option selected value="+LegalLicType[i].constantValue+">"+LegalLicType[i].constantName + "</option>")
                } else {
                    legalLicTypeSelect.append("<option value="+LegalLicType[i].constantValue+">"+LegalLicType[i].constantName + "</option>")
                }
            }
            legalLicTypeSelect.selectmenu("refresh");

            $("#legalName").val(licenseInfo.legalName);
            $("#legalLicNumber").val(licenseInfo.legalLicNumber);
        },
        error:function(){
            alert("error");
        }
    });

    //add for 修改图片文件上传到服务器 at 2016/09/21 by zhou_yajun start
    var flickerAPI = url+'/by/common/fileServerUrl';
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
            if(data.status == "F"){
                return;
            }else{
                var urlMap = data.result;
                $("#fileUploadServerUrl").val(urlMap["fileUploadServerUrl"]);
                $("#fileDownLoadServerUrl").val(urlMap["fileDownLoadServerUrl"]);

                //初始化创建文件选择控件
                $.upload.create("picLicenseFile",fileSelectChange,{name:"picLicenseFile"});
                $.upload.create("picOrgStructureFile",fileSelectChange,{name:"picOrgStructureFile"});
                $.upload.create("picTaxRegistrationFile",fileSelectChange,{name:"picTaxRegistrationFile"});
                $.upload.create("picFoodCirculationFile",fileSelectChange,{name:"picFoodCirculationFile"});
                $.upload.create("picCertFile",fileSelectChange,{name:"picCertFile"});
            }
        },
        error:function(){
            alert("error");
        }
    });
    //add for 修改图片文件上传到服务器 at 2016/09/21 by zhou_yajun end

    // 图片的地址获取
    var flickerAPI = url+'/by/pictures/find';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId": localStorage.buyerId}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "F"){
                return;
            }
            var picturePath = data.result;
            var fileDownUrl = $("#fileDownLoadServerUrl").val();
            if(picturePath.picLicensePath){
                $("#picLicense").html("<img src='"+fileDownUrl+picturePath.picLicensePath+"' style='width: 270;height: 200px;'>");
            }
            if(picturePath.picOrgStructurePath){
                $("#picOrgStructure").html("<img src='"+fileDownUrl+picturePath.picOrgStructurePath+"' style='width: 270;height: 200px;'>");
            }
            if(picturePath.picTaxRegistrationPath){
                $("#picTaxRegistration").html("<img src='"+fileDownUrl+picturePath.picTaxRegistrationPath+"' style='width: 270;height: 200px;'>");
            }
            if(picturePath.picFoodCirculationPath){
                $("#picFoodCirculation").html("<img src='"+fileDownUrl+picturePath.picFoodCirculationPath+"' style='width: 270;height: 200px;'>");
            }
            if(picturePath.picCertPath){
                $("#picCert").html("<img src='"+fileDownUrl+picturePath.picCertPath+"' style='width: 270;height: 200px;'>");
            }
        },
        error:function(){
            alert("error");
        }
    });
});

function fileUpload(){
    var fileUploadServerUrl = $("#fileUploadServerUrl").val();
    $.upload.process({
        url:fileUploadServerUrl,
        method:'post',
        success: uploadSuccess
    });
}
function fileSelectChange(fileLinkId,filePath){
    if(filePath != ""){
        var pos = filePath.lastIndexOf('\\');
        var fileFullName = filePath.substring(pos + 1, filePath.length);
        var name2 = fileFullName.split(".");
        if(name2[1].toLowerCase() != "jpg" && name2[1].toLowerCase() != "png" && name2[1].toLowerCase() != "bmp" && name2[1].toLowerCase() != "jpeg"){
            $("#file_" + fileLinkId).val("");
            $("#" + fileLinkId + "_Name").html("");
            setMessageDivStyle("请上传正确的图片格式。", fileLinkId + "_Message");
            return false;
        }else{
            setMessageDivDisplay(fileLinkId + "_Message");
            $("#" + fileLinkId + "_Name").html(fileFullName);
        }
    }
}
function uploadSuccess(fid){
    var buyerId = localStorage.buyerId;
    var picLicensePath = fid["picLicenseFile"];
    var picOrgStructurePath = fid["picOrgStructureFile"];
    var picTaxRegistrationPath = fid["picTaxRegistrationFile"];
    var picFoodCirculationPath = fid["picFoodCirculationFile"];
    var picCertPath = fid["picCertFile"];
    if(picLicensePath == undefined || picLicensePath == null){
        picLicensePath = "";
    }
    if(picOrgStructurePath == undefined || picOrgStructurePath == null){
        picOrgStructurePath = "";
    }
    if(picTaxRegistrationPath == undefined || picTaxRegistrationPath == null){
        picTaxRegistrationPath = "";
    }
    if(picFoodCirculationPath == undefined || picFoodCirculationPath == null){
        picFoodCirculationPath = "";
    }
    if(picCertPath == undefined || picCertPath == null){
        picCertPath = "";
    }

     var flickerAPI = url+'/by/pictures/update';
     var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId": buyerId, "picLicensePath":picLicensePath,"picOrgStructurePath":picOrgStructurePath, "picTaxRegistrationPath":picTaxRegistrationPath, "picFoodCirculationPath":picFoodCirculationPath, "picCertPath":picCertPath}};
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
                 if(localStorage.enterFlg == "edit"){
                     window.location = "BY121211.html";
                 }else{
                     window.location = "BY121233.html";
                 }
             }else{
                 alert(data.message);
             }
         },
         error:function(){

         }
     });
}

/**
 * 保存数据
 */
function buyerLicenseInfo(){
    var flickerAPI = url+'/by/licence/update';
    var buyerId = localStorage.buyerId;
    var licType = $("#licType option:selected").val();
    var licNumber = $("#licNumber").val();
    var legalLicType = $("#legalLicType option:selected").val();
    var legalName = $("#legalName").val();
    var legalLicNumber = $("#legalLicNumber").val();
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId": buyerId, "licName":licType,"licNumber":licNumber, "legalLicType":legalLicType, "legalName":legalName, "legalLicNumber":legalLicNumber}};
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
                fileUpload();
            }else{
                alert(data.message);
            }
        },
        error:function(){

        }
    });
}
//返回前一个画面
function returnPage(){
    if(localStorage.enterFlg == "edit"){
        window.location = "BY121211.html";
    }else{
        window.location = "BY121206.html";
    }
}

//设置messageDiv样式
function setMessageDivStyle(message, id){
    $("#"+id).css("border","1px #FF0000 solid");
    $("#"+id).css("color","#FF0000");
    $("#"+id).css("display","block");
    $("#"+id).text(message);
}

function setMessageDivDisplay(id){
    $("#"+id).css("display","none");
    $("#"+id).text("");
}