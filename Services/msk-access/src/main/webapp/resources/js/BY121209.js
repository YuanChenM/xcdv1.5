var busCardPicPath = "";
var EmployeeType = [];
$(function(){

    if(localStorage.enterFlg == "edit" || localStorage.enterFlg == "view"){
        $("#titleDiv").text("买家信息编辑 - 雇员信息");
        $("#saveButtonDiv").text("保存");
    }else{
        $("#titleDiv").text("新买家注册 - 雇员信息");
        $("#saveButtonDiv").text("完成");
    }

    //员工类型
    var flickerAPI = url+'/by/common/master';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"constantType": "EmployeeType"}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            EmployeeType = data.result;
        },
        error:function(){
            alert("error");
        }
    });

    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId": localStorage.buyerId}};
    if(findEmployeeInfo(paramData).length > 0){
        var result = findEmployeeInfo(paramData);
        for(var i=0; i<result.length; i++) {
            var employeeType = result[i].employeeType;
            var employeeTypeName;
            if(employeeType == 1){
                employeeTypeName = "老板";
            } else if(employeeType == 2){
                employeeTypeName = "经理/店长";
            }else if(employeeType == 3){
                employeeTypeName = "员工";
            }
            var employeeName = result[i].employeeName;
            var employeeTel = result[i].employeeTel;
            var employeeQq = result[i].employeeQq;
            var employeeWechat = result[i].employeeWechat;
            var busCardFlg = result[i].busCardFlg;
            var busCardValue;
            if(busCardFlg == 0){
                busCardValue = "无";
            }else {
                busCardValue = "有";
            }
            var contactPerson = result[i].contactPerson;
            var contactPersonValue;
            if(contactPerson == 0){
                contactPersonValue = "无";
            }else {
                contactPersonValue = "有";
            }
            var purchase = result[i].purchase;
            var purchaseValue;
            if(purchase == 0){
                purchaseValue = "无";
            }else {
                purchaseValue = "有";
            }
            var receivePerson = result[i].receivePerson;
            var receivePersonValue;
            if(receivePerson == 0){
                receivePersonValue ="无";
            }else {
                receivePersonValue = "有";
            }
            var employeeId =  result[i].id;
            var picturePath = result[i].busCardPicPath;
            $("#employeeInfoDiv").append(employeeStr(employeeId,picturePath,employeeTypeName,employeeName,employeeTel,employeeQq,employeeWechat,contactPersonValue,busCardValue,purchaseValue,receivePersonValue));
        }
    }

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
                $.upload.create("importFile",fileSelectChange,{name:"importFile"});
            }
        },
        error:function(){
            alert("error");
        }
    });
    //add for 修改图片文件上传到服务器 at 2016/09/21 by zhou_yajun end

});

//获取员工信息
function findEmployeeInfo(paramData){
    var flickerAPI = url+'/by/employee/findList';
    var paramData =  paramData;
    var result = [];
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            $("#buyerList").html("");
            if(data.status =="F"){

            }else {
                result = data.result;

            }
        },
        error: function(){
            alert("error");
        }
    });
    return result;
}

//雇员信息编辑/新增
function employeeInfoModify(obj){
    $("#errorMessage").css("display","none");
    $('html, body').animate({scrollTop:0}, 0);
    if(obj.id == "employeeInfoAdd"){
        $("#enterFlag").val("add");
        $("#header").text("雇员信息新增");
        var employeeTypeSelect = $("#employeeType");
        employeeTypeSelect.html("");
        for(var i = 0; i< EmployeeType.length; i++){
            employeeTypeSelect.append("<option value="+EmployeeType[i].constantValue+">"+EmployeeType[i].constantName + "</option>");
        }
        employeeTypeSelect.selectmenu("refresh");
        $("#employeeId").val("");
        $("#employeeName").val("");
        $("#employeeTel").val("");
        $("#employeeQq").val("");
        $("#employeeWechat").val("");
        $("#importFile_Name").val("");
        $("#busCardPicPath").val("");
        $("#busCardFlg").attr("checked",false).checkboxradio("refresh");
        $("#contactPerson").attr("checked",false).checkboxradio("refresh");
        $("#purchase").attr("checked",false).checkboxradio("refresh");
        $("#receivePerson").attr("checked",false).checkboxradio("refresh");
        $("#uploadDiv").css("display","none");
        $("#pictureMessage").css("display","none");
        $("#employeeName").removeAttr("disabled");
    }else {
        $("#header").text("雇员信息修改");
        $("#employeeId").val(obj.id);
        $("#enterFlag").val("modify");
        $("#employeeName").attr("disabled","disabled");
        var paramData =  {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId": localStorage.buyerId, "id":obj.id}};
        if(findEmployeeInfo(paramData).length == 1){
            var employeeInfoData = findEmployeeInfo(paramData)[0];
            var employeeTypeSelect = $("#employeeType");
            employeeTypeSelect.html("");
            for(var i = 0; i< EmployeeType.length; i++){
                if(EmployeeType[i].constantValue == employeeInfoData.employeeType){
                    employeeTypeSelect.append("<option selected value="+EmployeeType[i].constantValue+">"+EmployeeType[i].constantName + "</option>")
                } else {
                    employeeTypeSelect.append("<option value="+EmployeeType[i].constantValue+">"+EmployeeType[i].constantName + "</option>")
                }
            }
            employeeTypeSelect.selectmenu("refresh");
            $("#employeeName").val(employeeInfoData.employeeName);
            $("#employeeTel").val(employeeInfoData.employeeTel);
            $("#employeeQq").val(employeeInfoData.employeeQq);
            $("#employeeWechat").val(employeeInfoData.employeeWechat);
            if(employeeInfoData.busCardFlg == 1){
                $("#busCardFlg").attr("checked",true).checkboxradio("refresh");
                $("#uploadDiv").css("display","block");
            }else {
                $("#busCardFlg").attr("checked",false).checkboxradio("refresh");
                $("#uploadDiv").css("display","none");
                $("#pictureMessage").css("display","none");
            }
            if(employeeInfoData.contactPerson == 1){
                $("#contactPerson").attr("checked",true).checkboxradio("refresh");
            }else {
                $("#contactPerson").attr("checked",false).checkboxradio("refresh");
            }
            if(employeeInfoData.purchase == 1){
                $("#purchase").attr("checked",true).checkboxradio("refresh");
            }else {
                $("#purchase").attr("checked",false).checkboxradio("refresh");
            }
            if(employeeInfoData.receivePerson == 1){
                $("#receivePerson").attr("checked",true).checkboxradio("refresh");
            }else {
                $("#receivePerson").attr("checked",false).checkboxradio("refresh");
            }
        };
    }
    $("#backGroundDiv").css("display","block");
    $('#employeeInfoEditDiv').css("display","block");
}
//隐藏雇员信息DIV
function employeeDivNone(){
    $("#pictureMessage").css("display","none");
    $("#backGroundDiv").css("display","none");
    $("#employeeInfoEditDiv").css("display","none");
}
//名片照是否上传
function displayBusCard(){
    if($('#busCardFlg').is(':checked')	){
        $("#uploadDiv").css("display","block");
    } else {
        $("#uploadDiv").css("display","none");
        $("#pictureMessage").css("display","none");
    }
}

function fileSelectChange(fileLinkId,filePath){
    if(filePath != ""){
        var pos = filePath.lastIndexOf('\\');
        var fileFullName = filePath.substring(pos + 1, filePath.length);
        var name2 = fileFullName.split(".");
        if(name2[1].toLowerCase() != "jpg" && name2[1].toLowerCase() != "png" && name2[1].toLowerCase() != "bmp" && name2[1].toLowerCase() != "jpeg"){
            $("#file_" + fileLinkId).val("");
            $("#" + fileLinkId + "_Name").html("");
            setMessageDivStyle("请上传正确的图片格式。", "errorMessage");
            return false;
        }else{
            setMessageDivDisplay("errorMessage");
            $("#" + fileLinkId + "_Name").html(fileFullName);
        }
    }
}
//保存变更
function employeeInfoSave(){
    fileUpload();
}
function fileUpload(){
    var fileUploadServerUrl = $("#fileUploadServerUrl").val();
    $.upload.process({
        url:fileUploadServerUrl,
        method:'post',
        success: uploadSuccess
    });
}
function uploadSuccess(fid){
    var busCardPicPath = fid["importFile"];
    if(busCardPicPath == undefined || busCardPicPath == null){
        busCardPicPath = "";
    }
    $("#busCardPicPath").val(busCardPicPath);
    employeeBasicInfoSave();
}
function employeeBasicInfoSave(){
    var flickerAPI = "";
    var paramData = "";
    var buyerId = localStorage.buyerId;
    var id = "";
    if($("#employeeId").val()){
        id = $("#employeeId").val();
    }
    var employeeName = $("#employeeName").val();
    if(employeeName == ""){
        var message = "请输入雇员姓名";
        errorDisplay(message);
        return false;
    }
    var employeeTel = $("#employeeTel").val();
    if(employeeTel == ""){
        var message = "请输入雇员手机号";
        errorDisplay(message);
        return false;
    }
    var employeeQq =  $("#employeeQq").val();
    var employeeWechat =  $("#employeeWechat").val();
    var employeeTypeValue = $("#employeeType option:selected").val();
    var employeeTypeText = $("#employeeType option:selected").text();
    var busCardFlgValue;
    var busCardFlgName;
    if($('#busCardFlg').is(':checked')){
        busCardFlgValue = 1;
        busCardFlgName = "有";
    }else {
        busCardFlgValue = 0;
        busCardFlgName = "无";
    }
    var contactPersonValue;
    var contactPersonName;
    if($('#contactPerson').is(':checked')){
        contactPersonValue = 1;
        contactPersonName = "是";
    }else {
        contactPersonValue = 0;
        contactPersonName = "否";
    }
    var purchaseValue;
    var purchaseName;
    if($('#purchase').is(':checked')){
        purchaseValue = 1;
        purchaseName = "是";
    }else {
        purchaseValue = 0;
        purchaseName = "否";
    }
    var receivePersonValue;
    var receivePersonName;
    if($('#receivePerson').is(':checked')){
        receivePersonValue = 1;
        receivePersonName = "是";
    }else {
        receivePersonValue = 0;
        receivePersonName = "否";
    }
    var busCardPicPath = $("#busCardPicPath").val();
    var employeeType = $("#employeeType option:selected").val();
    if(employeeType == 1){
        var bosscounts;
        flickerAPI = url+'/by/findBossCount/search';
        paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param":{"buyerId":buyerId,"employeeType":employeeTypeValue,"employeeStatus":$("#enterFlag").val(),"id": id}}
        $.ajax({
            type: "POST",
            async: false,
            url: flickerAPI,
            timeout: 60,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(paramData),
            success: function (data) {
                if(data.result != 0){
                    bosscounts = data.result;
                }else{
                    bosscounts = 0;
                }
            },
            error: function(){
                alert("error");
            }
        });

        if(bosscounts != 0){
            var message = "雇员更新失败,该店铺老板已存在!";
            errorDisplay(message);
            return false;
        }

    }

    if($("#enterFlag").val() == "modify"){
        flickerAPI = url+'/by/employee/update';
        paramData = paramData = {"siteCode": "101", "auth": "", "loginid": "", "param": [{"id": id, "buyerId":buyerId, "busCardPicPath":busCardPicPath, "employeeType":employeeTypeValue, "employeeName": employeeName, "employeeTel":employeeTel, "employeeQq":employeeQq, "employeeWechat":employeeWechat, "busCardFlg": busCardFlgValue, "contactPerson":contactPersonValue, "purchase":purchaseValue, "receivePerson":receivePersonValue}]};
    }else{
        flickerAPI = url+'/by/employee/phoneSave';
        paramData = {"siteCode": "101", "auth": "", "loginid": "", "param": {"buyerId":buyerId,  "busCardPicPath":busCardPicPath, "employeeType":employeeTypeValue, "employeeName": employeeName, "employeeTel":employeeTel, "employeeQq":employeeQq, "employeeWechat":employeeWechat, "busCardFlg": busCardFlgValue, "contactPerson":contactPersonValue, "purchase":purchaseValue, "receivePerson":receivePersonValue}};
    }

    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if(id != ""){
                var employeeName = $("#employeeName").val();
                var employeeTel = $("#employeeTel").val();
                var employeeQq =  $("#employeeQq").val();
                var employeeWechat =  $("#employeeWechat").val();
                var fileDownLoadServerUrl = $("#fileDownLoadServerUrl").val();
                if(busCardPicPath != ''){
                    $("#pictureDiv" + id).html("<img src='"+fileDownLoadServerUrl + busCardPicPath+"' style='width: 200;height: 200px;'>");
                }
                $("#employeeTypeName"+id).text("雇员类型："+ employeeTypeText);
                $("#employeeName"+id).text("姓名："+ employeeName);
                $("#employeeTel"+id).text("手机号："+ employeeTel);
                $("#employeeQq"+id).text("QQ号："+ employeeQq);
                $("#employeeWechat"+id).text("微信号："+ employeeWechat);
                $("#contactPersonValue"+id).text("是否联络人："+ contactPersonName);
                $("#busCardValue"+id).text("有无名片照："+ busCardFlgName);
                $("#purchaseValue"+id).text("是否采购人："+ purchaseName);
                $("#receivePersonValue"+id).text("是否收货人："+ receivePersonName);
            }else{
                var result = data.result;
                var employeeType = result.employeeType;
                var employeeTypeName;
                if(employeeType == 1){
                    employeeTypeName = "老板";
                } else if(employeeType == 2){
                    employeeTypeName = "经理/店长";
                }else if(employeeType == 3){
                    employeeTypeName = "员工";
                }
                var employeeName = result.employeeName;
                var employeeTel = result.employeeTel;
                var employeeQq = result.employeeQq;
                var employeeWechat = result.employeeWechat;
                var busCardFlg = result.busCardFlg;
                var busCardValue;
                if(busCardFlg == 0){
                    busCardValue = "无";
                }else {
                    busCardValue = "有";
                }
                var contactPerson = result.contactPerson;
                var contactPersonValue;
                if(contactPerson == 0){
                    contactPersonValue = "无";
                }else {
                    contactPersonValue = "有";
                }
                var purchase = result.purchase;
                var purchaseValue;
                if(purchase == 0){
                    purchaseValue = "无";
                }else {
                    purchaseValue = "有";
                }
                var receivePerson = result.receivePerson;
                var receivePersonValue;
                if(receivePerson == 0){
                    receivePersonValue ="无";
                }else {
                    receivePersonValue = "有";
                }
                var employeeId =  result.id;
                var picturePath = result.busCardPicPath;

                $("#employeeInfoDiv").append(employeeStr(employeeId,picturePath,employeeTypeName,employeeName,employeeTel,employeeQq,employeeWechat,contactPersonValue,busCardValue,purchaseValue,receivePersonValue));
            }
            employeeDivNone();
        },
        error: function(){
            alert("error");
        }
    });
}

//雇员信息字符串
function employeeStr(employeeId,picturePath,employeeTypeName,employeeName,employeeTel,employeeQq,employeeWechat,contactPersonValue,busCardValue,purchaseValue,receivePersonValue){
    var str = "<div id = '"+ employeeId +"' style='width:80%;margin-left:30px;' class='ui-grid-a' onclick='employeeInfoModify(this);'>";
    if(picturePath == ''|| picturePath ==undefined) {
        str = str + "<div id='pictureDiv"+employeeId+"' class='ui-block-a' style='width: 40%;height:200px;border:1px #CCCCCC solid;color:#CCCCCC;font-size:17px;'>";
        str = str + "暂无名片照<br/>请选择照片文件上传";
        str = str + "</div>";
    }else{
        str = str + "<div id='pictureDiv"+employeeId+"' class='ui-block-a' style='width: 40%;height:200px;border:1px #CCCCCC solid;color:#CCCCCC;font-size:17px;'>";
        str = str + "<img src='"+picturePath+"' style='width: 200;height: 200px;'>";
        str = str + "</div>";
    }
    str = str + "<div class='ui-block-b' style='width:60%;height:200px;border:1px #CCCCCC solid;font-size:17px;background-color:#F6F8FA;color:#CECECE'>";
    str = str + "<div id='employeeTypeName"+employeeId+"'>雇员类型："+ employeeTypeName+"</div>";
    str = str + "<div id='employeeName"+employeeId+"'>姓名：" + employeeName + "</div>";
    str = str + "<div id='employeeTel"+employeeId+"'>手机号：" + employeeTel + "</div>";
    str = str + "<div id='employeeQq"+employeeId+"'>QQ号：" + employeeQq +"</div>";
    str = str + "<div id='employeeWechat"+employeeId+"'>微信号："+employeeWechat+"</div>";
    str = str + "<div id='busCardValue"+employeeId+"'>有无名片照："+busCardValue+"</div>";
    str = str + "<div id='contactPersonValue"+employeeId+"'>是否联络人："+contactPersonValue+"</div>";
    str = str + "<div id='purchaseValue"+employeeId+"'>是否采购人："+purchaseValue+"</div>";
    str = str + "<div id='receivePersonValue"+employeeId+"'>是否收货人："+receivePersonValue+"</div>";
    str = str + "</div>";
    str = str + "</div>";
    return str;
}

//信息录入结束
function buyerEmployeeInfo(){
    if(localStorage.enterFlg == "edit"){
        window.location = "BY121211.html";
    }else{
        window.location = "BY121210.html";
    }
}
//显示错误信息
function errorDisplay(message){
    $("#errorMessage").css("display","block");
    $("#errorMessage").text(message);
}
//返回前一个画面
function returnPage(){
    if(localStorage.enterFlg == "edit"){
        window.location = "BY121211.html";
    }else{
        window.location = "BY121237.html";
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