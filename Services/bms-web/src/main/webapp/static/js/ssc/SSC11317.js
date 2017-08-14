/**
 * Created by wang_shuai on 2016/7/8.
 */
var $List_Grid;
var SSC11317 = {
    formId:"SSC11317Form",
    arriveDate:"#arriveDate",
    init: function () {
        $List_Grid = $('#SSC11317_list_grid').grid({
            fnRowCallback: SSC11317.rowCallback
        });
        this.bindSaveButton();
        this.bindDepartureButton();
        this.bindTestButton();
        //this.bindDatePicber(SSC11317.arriveDate);
        //this.bindUploadButton();
        //this.bindDownloadButton();
    },
    bindSaveButton:function(){
        $("#SSC11317_SAVE").click(function(){
            //debugger;
            //var arriveDate = $("#arriveDate").val();
            //var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
            //if(!reg.test(arriveDate)){
            //    $.alertMessage.info("请按YYYY-MM-DD形式输入日期！");
            //    return;
            //}
            var sendRemark = $("#sendRemark").val();
            if(!checked()){
                return false;
            }
            if ($("#licPlateNumber").val() == "" || $("#vehicleType").val() == "" || $("#driverName").val() == "" || $("#driverTel").val() == "") {
                $.alertMessage.info("运输车辆及运输单位执行人信息不能为空");
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + '/SSC11317/save',{
                    licPlateNumber:$("#licPlateNumber").val(),
                    vehicleType:$("#vehicleType").val(),
                    driverName:$("#driverName").val(),
                    driverTel:$("#driverTel").val(),
                    deliveryResponsible:$("#deliveryResponsible").val(),
                    deliveryResponsibleTel:$("#deliveryResponsibleTel").val(),
                    deliveryExecutor:$("#deliveryExecutor").val(),
                    deliveryExecutorTel:$("#deliveryExecutorTel").val(),
                    trafficCompanyName:$("#trafficCompanyName").val(),
                    trafficCompanyResponsible:$("#trafficCompanyResponsible").val(),
                    trafficCompanyResponsibleTel:$("#trafficCompanyResponsibleTel").val(),
                    trafficCompanyExecutor:$("#trafficCompanyExecutor").val(),
                    trafficCompanyExecutorTel:$("#trafficCompanyExecutorTel").val(),
                    warehouseKeeper:$("#warehouseKeeper").val(),
                    warehouseKeeperTel:$("#warehouseKeeperTel").val(),
                    accepter:$("#accepter").val(),
                    accepterTel:$("#accepterTel").val(),
                    sendRemark:$("#sendRemark").val(),
                    deliveryPreIntoId:$("#deliveryPreIntoId").val(),
                    deliveryPreIntoCode:$("#deliveryPreIntoCode").text(),
                    ver:$("#ver").val()
                },
                function(data){
                    if(data == "S"){
                        $.alertMessage.info("操作成功");}
                    $("#headBreadCrumb").hide();
                },{refreshHtml:false});
        });
    },
    bindDepartureButton:function(){
        $("#SSC11317_DEPARTURE").click(function(){
            var startReceiptDate = $("#eta").text();
            if(startReceiptDate.length > 0) {
                startReceiptDate += " 00:00:00";
            }
            if ($("#licPlateNumber").val() == "" || $("#vehicleType").val() == "" || $("#driverName").val() == "" || $("#driverTel").val() == "") {
                $.alertMessage.info("车辆及驾驶员信息不能为空");
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + '/SSC11317/departure',{
                    deliveryPreIntoId:$("#deliveryPreIntoId").val(),
                    ver:$("#ver").val()
                },
                function(data){
                    //debugger;
                    //alert($("#deliveryPreIntoId").text());
                    if(data == "S"){
                        //$.alertMessage.info("确认发车成功");
                        $("#main-content").postUrl(Main.contextPath + "/SSC11317/init/1", {
                            deliveryPreIntoId:$("#deliveryPreIntoId").val()
                        },Main.hiddenHeader);
                        $.alertMessage.info("发车成功");
                    }else{
                        //$.alertMessage.info("确认发车失败");
                        $.alertMessage.info("发车失败");

                    }
                },{refreshHtml:false});
        });
    },
    bindTestButton: function() {
        $("#SSC11317_TEST").click(function(){
            $.pdialog.open("模拟产品入库", Main.contextPath + "/SSC11317/showTest", {width: "80%"}, {
                "deliveryPreIntoCode": $('#deliveryPreIntoCode').text().trim(),
                "ver": $('#ver').val(),
                "deliveryPreIntoId": $('#deliveryPreIntoId').val()
            });
        });
    },
    //绑定上传按钮
    bindUploadButton: function () {
        $("#SSC11317_UPLOAD").click(function () {
            var uploadFile = $("#SSC11317Upload");
            $.core.uploadForm(uploadFile,true);
        });
    },
    //获取下载url
    getDownLoadUrl:function (downLoadId) {
        var url = $("#downLoadUrl").val() + $("#" + downLoadId + "").attr("name").replace(",","/") + "/" + $("#" + downLoadId + "").val();
        return url;
    },
    //绑定下载按钮
    bindDownloadButton: function () {
        $("#SSC11317_DOWNLOAD1").click(function () {
            window.open(SSC11317.getDownLoadUrl("download1"),"blank");
        });
        $("#SSC11317_DOWNLOAD2").click(function () {
            window.open(SSC11317.getDownLoadUrl("download2"),"blank");
            //$.core.downloadUrl(Main.contextPath + "/SSC11317/fileDownLoad",{"fId":$("#download2").attr("name"),"fileName":$("#download2").val()});
        });
        $("#SSC11317_DOWNLOAD3").click(function () {
            window.open(SSC11317.getDownLoadUrl("download3"),"blank");
            //$.core.downloadUrl(Main.contextPath + "/SSC11317/fileDownLoad",{"fId":$("#download3").attr("name"),"fileName":$("#download3").val()});
        });
        $("#SSC11317_DOWNLOAD4").click(function () {
            window.open(SSC11317.getDownLoadUrl("download4"),"blank");
            //$.core.downloadUrl(Main.contextPath + "/SSC11317/fileDownLoad",{"fId":$("#download4").attr("name"),"fileName":$("#download4").val()});
        });
    },
    //bindDatePicber : function(timeId){
    //    $(timeId).datepicker({
    //        showButtonPanel: true,
    //        dateFormat:'yy-mm-dd',
    //        changeMonth: true,
    //        changeYear: true
    //    });
    //}
    /**
     * 绑定回调函数
     * @param tr
     * @param data
     */
    rowCallback: function(tr, data) {
        var totalProductPlanBox = SSCCommon.clearComma($("#totalProductPlanBox").html());
        if(totalProductPlanBox==''){
            totalProductPlanBox = 0;
        }
        $("#totalProductPlanBox").html(SSCCommon.add(parseFloat(totalProductPlanBox),parseFloat(data.productPlanBox)));

        var totalProductPlanWeight =SSCCommon.clearComma($("#totalProductPlanWeight").html());
        if(totalProductPlanWeight==''){
            totalProductPlanWeight = 0;
        }
        $("#totalProductPlanWeight").html(SSCCommon.add(parseFloat(totalProductPlanWeight),parseFloat(data.productPlanWeight)));

        var totalProductRecvBox = SSCCommon.clearComma($("#totalProductRecvBox").html());
        if(totalProductRecvBox==''){
            totalProductRecvBox = 0;
        }
        $("#totalProductRecvBox").html(SSCCommon.add(parseFloat(totalProductRecvBox),parseFloat(data.productRecvBox)));

        var totalProductRecvWeight = SSCCommon.clearComma($("#totalProductRecvWeight").html());
        if(totalProductRecvWeight==''){
            totalProductRecvWeight = 0;
        }
        $("#totalProductRecvWeight").html(SSCCommon.add(parseFloat(totalProductRecvWeight),parseFloat(data.productRecvWeight)));

        obj = document.getElementsByName("weight");
        for (i = 0; i < obj.length; i++) {
            if (SSCCommon.MONEY_REG.test(obj[i].innerHTML.trim())) {
                var str=obj[i].innerHTML;
                var rs= fmoney(obj[i].innerHTML,4);
                obj[i].innerHTML =rs;
            }
        }
        obj = document.getElementsByName("Box");
        for (i = 0; i < obj.length; i++) {
            if (SSCCommon.MONEY_REG.test(obj[i].innerHTML.trim())) {
                var rs= fmoney(obj[i].innerHTML,0);
                obj[i].innerHTML =rs;
            }
        }
    }

}
/**
 * 跳转到确认单详细页面
 * @param data
 */
function goToDeliveryConfirmDetail(deliveryConfirmCode)
{
    Main.detailWindow(Main.contextPath + "/SSC11315/init/2",  {
        deliveryConfirmId:$("#deliveryConfirmId").val(),
        deliveryConfirmCode:deliveryConfirmCode,
        deliveryPreIntoId:$("#deliveryPreIntoId").val()
    }, "订单发货确认单详细");
}
//上传回调函数
var callback = function(data){

    if($("#businessFileName").find("a").text() == "" && $("#quarantineFileName").find("a").text() == "" && $("#inventoryFileName").find("a").text() == ""
        && $("#reportFileName").find("a").text() == ""){
        $.alertMessage.info("请选择文件后再上传");
        return;
    }
    //解析json
    var obj = eval(data);

    $("#main-content").postUrl(Main.contextPath + "/SSC11317/saveFileInfo", {
        deliveryPreIntoId:$("#deliveryPreIntoId").val(),
        ver:$("#ver").val(),
        businessFileId:obj.businessFileName,
        businessFileName:$("#businessFileName").find("a").text(),
        quarantineFileId:obj.quarantineFileName,
        quarantineFileName:$("#quarantineFileName").find("a").text(),
        inventoryFileId:obj.inventoryFileName,
        inventoryFileName:$("#inventoryFileName").find("a").text(),
        reportFileId:obj.reportFileName,
        reportFileName:$("#reportFileName").find("a").text()
    },function(data) {
        if(data == "S"){
            $.alertMessage.info("上传成功");

            $("#main-content").postUrl(Main.contextPath + "/SSC11317/init/1", {
                deliveryPreIntoId:$("#deliveryPreIntoId").val()
            },Main.hiddenHeader);
        }else{
            $.alertMessage.info("上传失败");

        }
    },{refreshHtml:false});

}
$(document).ready(function () {
    // 初始化调用
    SSC11317.init();

});




function checked(){
    if($("#deliveryResponsibleTel").val()!=""&&!SSCCommon.PHONE_REG.test($("#deliveryResponsibleTel").val())){
        $.alertMessage.info("请输入正确的发货责任人联系方式");
        return false;
    }
    if($("#deliveryExecutorTel").val()!=""&&!SSCCommon.PHONE_REG.test($("#deliveryExecutorTel").val())){
        $.alertMessage.info("请输入正确的发货执行人联系方式");
        return false;
    }
    if($("#trafficCompanyResponsibleTel").val()!=""&&!SSCCommon.PHONE_REG.test($("#trafficCompanyResponsibleTel").val())){
        $.alertMessage.info("请输入正确的运输单位责任人联系方式");
        return false;
    }
    if($("#driverTel").val()!=""&&!SSCCommon.PHONE_REG.test($("#driverTel").val())){
        $.alertMessage.info("请输入正确的运输单位执行人联系方式");
        return false;
    }
    if($("#warehouseKeeperTel").val()!=""&&!SSCCommon.PHONE_REG.test($("#warehouseKeeperTel").val())){
        $.alertMessage.info("请输入正确的仓库责任人联系方式");
        return false;
    }
    if($("#accepterTel").val()!=""&&!SSCCommon.PHONE_REG.test($("#accepterTel").val())){
        $.alertMessage.info("请输入正确的验收责任人联系方式");
        return false;
    }

    return true;
}
