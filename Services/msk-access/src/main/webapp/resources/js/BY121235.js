/**
 * Created by tao_zhifa on 2016/8/3.
 */

//买家类型
var salesCommon = [];
//产品类别
var pdClassesCommon = [];
//产品二级类别
var pdMachiningCommom = [];
//营销状态
var mrketingsStatus;

$(function () {

    if(localStorage.enterFlg == "eidt" || localStorage.enterFlg == "view"){
        $("#titleDiv").text("买家信息编辑 - 买家池归属设置");
        $("#saveButtonDiv").text("保存");
    }else{
        $("#titleDiv").text("新买家注册 - 买家池归属设置");
        $("#saveButtonDiv").text("下一步 5/8");
    }
    // 获取买家经营产品类别选项
    var flickerAPI = url + '/by/common/SuperiorType';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId":localStorage.buyerId}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                salesCommon = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });

    // 获取买家经营产品类别选项
    var flickerAPI = url + '/by/common/pdClass';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                pdClassesCommon = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });
    // 获取买家经营产品二级类别选项
    var flickerAPI = url + '/by/common/pdMachining';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                pdMachiningCommom = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });

    // 获取营销状态
    var flickerAPI = url + '/by/common/findmMrketingsStatusValue';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId":localStorage.buyerId}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                mrketingsStatus = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });

    //根据买家类型判断是否查询二级类别
    if(salesCommon.superiorType != "01" ){
        var pdClassesList = '';
        pdClassesList = pdClassesList + "<table id='checkTable'>";
        for(var i=0;i<pdClassesCommon.length;i++){
            pdClassesList = pdClassesList + "<tr><td><input type='checkbox' name='pdClasses' id='pdClasses"+pdClassesCommon[i].classesCode+"'></td>";
            pdClassesList = pdClassesList +  "<td><label for='pdClasses"+pdClassesCommon[i].classesCode+"'>"+pdClassesCommon[i].classesName+"</label></td></tr>";
        }
        pdClassesList = pdClassesList + "</table>";
    }
    if(salesCommon.superiorType == "01"){
        var pdClassesList = '';
        pdClassesList = pdClassesList + "<table id='checkTable'>";
        for(var i=0;i<pdClassesCommon.length;i++){
            pdClassesList = pdClassesList + "<tr><td>"+pdClassesCommon[i].classesName+"</td></tr><tr>";
            for(var j=0;j<pdMachiningCommom.length;j++){
                if(pdMachiningCommom[j].classesCode == pdClassesCommon[i].classesCode){
                    pdClassesList = pdClassesList + "<td><input type='checkbox' name='pdMachin' id='pdMachin"+pdMachiningCommom[j].machiningCode+pdClassesCommon[i].classesCode+"' pdMachiningCode='"+pdMachiningCommom[j].machiningCode +"'pdClassCode='" +pdClassesCommon[i].classesCode+ "'>";
                    pdClassesList = pdClassesList +  "<label for='pdMachin"+pdMachiningCommom[j].machiningCode+pdClassesCommon[i].classesCode+"'>"+pdMachiningCommom[j].machiningName+"</label></td>";
                }
            }
            pdClassesList = pdClassesList + "</tr>";
        }
        pdClassesList = pdClassesList + "</table>";
    }
    $("#pdClassesDiv").append(pdClassesList);
    $("#pdClassesDiv").trigger("create");
    $("input[type='checkbox']").checkboxradio("refresh");
    checkMacCheckBoxs();
    checkClaCheckBoxs();
    // 获取买家产品类别
    var pdClasses = [];
    var flickerAPI = url + '/by/pdClassification/findList';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId": localStorage.buyerId}};
    $.ajax({
        type: "POST",
        async: false,
        url: flickerAPI,
        timeout: 60,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(paramData),
        success: function (data) {
            if (data.status == "S") {
                pdClasses = data.result;
            }
        },
        error: function () {
            alert("error");
        }
    });

    if(salesCommon.superiorType != "01" ){
        if (pdClasses.length > 0) {
            for (var i = 0; i < pdClasses.length; i++) {
                $("#pdClasses" + pdClasses[i].classCode).attr("checked", true).checkboxradio("refresh");
                if(mrketingsStatus.marketingsStatus != "01" && mrketingsStatus.marketingsStatus != "02"){
                    $("input[type='checkbox']").attr("disabled",true).checkboxradio("refresh");
                }
            }
        }
    }
    if(salesCommon.superiorType == "01"){
        if (pdClasses.length > 0) {
            for (var i = 0; i < pdClasses.length; i++) {
                if (!isNaN(pdClasses[i].machiningCode)) {
                        $("#pdClasses" + pdClasses[i].classCode).attr("checked", true).checkboxradio("refresh");
                        $("#pdMachin" + pdClasses[i].machiningCode + pdClasses[i].classCode).attr("checked", true).checkboxradio("refresh");
                        if(mrketingsStatus.marketingsStatus != "01"  && mrketingsStatus.marketingsStatus != "02"){
                            $("input[type='checkbox']").attr("disabled",true).checkboxradio("refresh");
                        }
                    }
                }
            }
    };


});


//保存数据
function buyerReceiveInfo() {
    //获取选择的产品类别并更新数据库
    var pdClassesData = [];
    var countClaSelected = 0;
    if(salesCommon.superiorType != "01" ){
        for (var i = 0; i < pdClassesCommon.length; i++) {
            if ($("#pdClasses" + pdClassesCommon[i].classesCode).is(":checked")==true) {
                var classesCode = pdClassesCommon[i].classesCode;
                var classesName = pdClassesCommon[i].classesName;
                var pdClassesStr = {'buyerId': localStorage.buyerId, 'classCode': classesCode, 'className': classesName};
                pdClassesData.push(pdClassesStr);
            } else {
                countClaSelected = countClaSelected + 1;
            }
        }
    }
    if(salesCommon.superiorType == "01"){
        var classesCode ="";
        var classesName = "";
        var machiningCode = "";
        var pdClassesStr="";
        for (var i = 0; i < pdClassesCommon.length; i++) {
            for (var j = 0; j < pdMachiningCommom.length; j++) {
                if ($("#pdMachin" + pdMachiningCommom[j].machiningCode+pdClassesCommon[i].classesCode).is(":checked") == true) {
                    classesCode = pdClassesCommon[i].classesCode;
                    classesName = pdClassesCommon[i].classesName;
                    machiningCode = pdMachiningCommom[j].machiningCode;
                    pdClassesStr = {'buyerId': localStorage.buyerId, 'classCode': classesCode, 'className': classesName,"machiningCode":machiningCode};
                } else {
                    countClaSelected = countClaSelected + 1;
                }
            }
        }
        pdClassesData.push(pdClassesStr);
    }


    if (countClaSelected == pdClassesCommon.length) {
        $("#message").css("display", "block");
        $("#message").text("请至少选择一个销售产品");
        return;
    } else {
        var flickerAPI = url + '/by/pdClassification/update';
        var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": pdClassesData};
        $.ajax({
            type: "POST",
            async: false,
            url: flickerAPI,
            timeout: 60,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(paramData),
            success: function (data) {
                setTimeout(function () {
                    if (data.status == "S") {
                        window.location = "BY121236.html";
                    } else {
                        alert(data.message);
                    }
                }, 0);
            },
            error: function () {
                alert("请选择产品信息");
            }
        });
    }
}

//返回前一个画面
function returnPage() {
    if (localStorage.enterFlg == "edit") {
        window.location = "BY121234.html";
    } else {
        window.location = "BY121234.html";
    }
}

function checkClaCheckBoxs() {

    $("input[name='pdClasses']").click(function(){
        $('#checkTable').find('input[type=checkbox]').not(this).attr("checked", false);
        $("input[type='checkbox']").checkboxradio("refresh");
    })
};

function checkMacCheckBoxs() {
    $("input[name='pdMachin']").click(function(){
        var PdClassCode = $(this).attr("pdClassCode");
        //var pdMachiningCode =  $(this).attr("pdMachiningCode");
        $('#checkTable').find('input[type=checkbox]').not(this).attr("checked", false);
        //$('#pdClasses'+PdClassCode).prop('checked',true);
        $("input[type='checkbox']").checkboxradio("refresh");
    })

}
