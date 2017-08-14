//上传文件回调函数
var callback = function(data){
    //解析json
    var obj = eval(data);
    var invoiceRequestCode=$("#invoiceRequestCode").val();
    var contractCode=$("#contractCode").val();
    var contractId=$("#contractId").val();
    var invoiceRequestId=$("#invoiceRequestId").val();

    if($('#status').val()=='9'){
        $.alertMessage.info("请先申请");
        return;
    }
    if($('#status').val()=='5'){
        $.alertMessage.info("审批未通过,请等待修改后审批");
        return;
    }
    if($('#status').val()=='6'){
        $.alertMessage.info("审核未通过,请等待修改后审批");
        return;
    }
    if($('#status').val()!='3'){
        if($('#status').val()=='4')
        {
            $.alertMessage.info("流程已完成,请不要重复上传");
        }else
        {
            $.alertMessage.info("审核未完成,请开票完成后提交");
        }
        return;
    }
    var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
    if( $("#receiver").val()==""|| $("#receiver").val()==null|| $("#receiver").val().length>100){
        $.alertMessage.info("发票接收人填写错误,请重新填写");
        return;
    }
    if( $("#receiveDate").val()==""|| $("#receiveDate").val()==null){
        $.alertMessage.info("发票接收日期不能为空");
        return;
    }
    if(!reg.test( $("#receiveDate").val())){
        $.alertMessage.info("请按YYYY-MM-DD格式输入申请时间");
        return;
    }
    if($("#invoiceFileName").find("a").text()==""||$("#invoiceFileName").find("a").text()==null||$("#invoiceFileName").find("a").text()==undefined){
        $.alertMessage.info("请选择文件后再上传");
        return;
    }

    var strs= $("#invoiceFileName").find("a").text().split(".");
    if(strs[1]==null||strs[1]==""||strs[1]==undefined){
        $.alertMessage.info("请选择正确的文件格式(png,jpg等)");
        return;
    }
    var strtest = strs[strs.length-1];
    if(!(strtest=="png"||strtest=="jpg"||strtest=="jpeg"||strtest=="gif"||strtest=="bmp"||strtest=="BMP"||strtest=="JPG"||strtest=="PNG"||strtest=="JPEG"||strtest=="GIF")){
        $.alertMessage.info("请选择正确的图片文件格式(png,jpg等)");
        return;
    }
    $('#main-content').postUrl(Main.contextPath+"/SSC11324/saveInvoiceRequestFileInfo",{
        uploadFileName:$("#invoiceFileName").find("a").text(),
        receiver:$("#receiver").val(),
        receiveDateStr:$("#receiveDate").val(),
        contractCode:$("#contractCode").val(),
        contractId:$("#contractId").val(),
        uploadFileId: obj.invoiceFileName,
        ver:$("#ver").val(),
        invoiceRequestId:invoiceRequestId,
        invoiceRequestCode:invoiceRequestCode
    },function(data){
        if(data.status!="S"){
            $.alertMessage.info("上传失败");
        }else{
            $.alertMessage.info("上传成功");
            $("#main-content").postUrl(Main.contextPath + "/SSC11324/init/2", {
                invoiceRequestCode:invoiceRequestCode,
                contractCode:contractCode,
                contractId:contractId,
                invoiceRequestId:invoiceRequestId
            }, Main.hiddenHeader);
        }
    },{refreshHtml:false})

}
var $List_Grid;
var SSC11324={
    formId:"SSC11324Form",
    init: function () {
        SSC11324.bindApprovalButton();
        SSC11324.bindInvoiceButton();
        //SSC11324.bindDownloadButton();
        SSC11324.bindChangeButton();
        SSC11324.bindAuditButton();
        SSC11324.bindDatePicker($('#requestTimeStr'));
        SSC11324.bindDatePicker($('#receiveDate'));
        SSC11324.control();
        SSC11324.selectCntrol();
        //SSC11324.grey();
        SSC11324.changeAmount();
        SSC11324.closeDate();
        SSC11324.bindShowEpInfo();
        SSC11324.bindShowRpInfo();
    },
    bindDatePicker : function(timeId){
        $(timeId).datepicker({
            showButtonPanel: true,
            dateFormat:'yy-mm-dd',
            changeMonth: true,
            changeYear: true,
            closeText: "清除"
        });
        $(timeId).attr("readonly","readonly");
    },
    closeDate: function () {
        $(document).on("click", "button.ui-datepicker-close", function () {
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    bindApplydButton: function () {
            if($('#statusCtr').val()!='9'){
                $.alertMessage.info("请不要重复申请");
                return;
            }
            if(!checked()){
                    return;
                }

            if($("#supplierId").val()==null||$("#supplierId").val()==''||$("#supplierId").val()==undefined){
                $("#supplierId").val(0);
            }

            if($("#purchaserId").val()==null||$("#purchaserId").val()==''||$("#purchaserId").val()==undefined){
                $("#purchaserId").val(0);
            }
            var contractId=$("#contractId").val();
            var contractCode=$("#contractCode").val();
            $('#main-content').postUrl(Main.contextPath+"/SSC11324/insertInvoiceRequest",{
                contractCode:$("#contractCode").val(),
                contractName:$("#contractName").val(),
                contractActDateStr:$("#contractActDateStr").val(),
                contractAmount:$("#contractAmount").val(),
                purchaserName:$("#purchaserName").val(),
                supplierName:$("#supplierName").val(),
                invoiceRequestCode:$("#invoiceRequestCode").val(),//申请号自动生成
                receiving:$("#receiving").val(),
                payer:$("#payer").val(),
                invoiceAmount:SSCCommon.clearComma($("#invoiceAmount").val().trim()),
                invoiceType: $("#invoiceType").find("option:selected").val(),
                //invoiceType:$("input[name='invoiceType']:checked").val(),
                remark:$("#remark").val(),
                requestTimeStr:$("#requestTimeStr").val(),
                receiveDateStr:$("#receiveDate").val(),
                contractId:$("#contractId").val(),
                supplierId:$("#supplierId").val(),
                supplierCode:$("#supplierCode").val(),
                purchaserId:$("#purchaserId").val(),
                purchaserCode:$("#purchaserCode").val(),
                requester:$("#requester").val(),
                invoiceRequestDesc:$("#invoiceRequestDesc").val(),
                receiver:$("#receiver").val(),
                buyerTaxpayerCode:$("#buyerTaxpayerCode").val(),
                receiverTaxpayerCode:$("#receiverTaxpayerCode").val(),
                buyerAddr:$("#buyerAddr").val(),
                sellerAddr:$("#sellerAddr").val(),
                buyerTel:$("#buyerTel").val(),
                sellerTel:$("#sellerTel").val(),
                buyerBank:$("#buyerBank").val(),
                sellerBank:$("#sellerBank").val(),
                buyerAccount:$("#buyerAccount").val(),
                sellerAccount:$("#sellerAccount").val(),
                ver:$("#ver").val(),
                status:'0'//已申请状态
                //invoiceDate:$("#invoiceDate").val()//开票时间没有加入
            }
                ,function(data){
                if(data.status=="S"){
                    $.alertMessage.info("操作成功");
                    $('#main-content').postUrl(Main.contextPath+"/SSC11324/init/2",{
                        invoiceRequestCode: data.result,
                        contractId:contractId,
                        contractCode:contractCode
                    },Main.hiddenHeader);
                }else
                {
                    $.alertMessage.info("申请错误,请重新申请");
                    //$('#main-content').postUrl(Main.contextPath+"/SSC11323/init", Main.hiddenHeader);
                }
            }
            );
            //var uploadFile = $("#SSC11324Upload");
            //$.core.uploadForm(uploadFile,true);
    },
    bindApprovalButton: function () {
        $("#SSC11324_APPROVAL").click(function () {
            if($('#statusCtr').val()=='9'){
                $.alertMessage.info("请先申请");
                return;
            }
            if($('#status').val()=='6'){
                $.alertMessage.info("审核未通过,请等待修改后审批");
                return;
            }
            if($('#status').val()!='0'){
                $.alertMessage.info("请不要重复审批");
                return;
            }

            var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
            if(!reg.test($('#requestTimeStr').val())){
                $.alertMessage.info("请按YYYY-MM-DD格式输入申请时间");
                return;
            }
            if($("#supplierId").val()==null||$("#supplierId").val()==''||$("#supplierId").val()==undefined){
                $("#supplierId").val(0);
            }
            if($("#purchaserId").val()==null||$("#purchaserId").val()==''||$("#purchaserId").val()==undefined){
                $("#purchaserId").val(0);
            }

            $('#main-content').postUrl(Main.contextPath+"/SSC11324/modifyInvoiceRequestUp",{
                contractCode:$("#contractCode").val(),
                contractId:$("#contractId").val(),
                invoiceRequestCode:$("#invoiceRequestCode").val(),
                invoiceRequestId:$("#invoiceRequestId").val(),
                approvalPerson:$("#approvalPerson").val(),
                approvalRemark:$("#approvalRemark").val(),
                auditingRemark:$("#auditingRemark").val(),
                ver:$("#ver").val(),
                status:$("input[name='approvalFlag']:checked").val()//改为已审批状态
                //invoiceDate:$("#invoiceDate").val()//开票时间没有加入
            },function(data){
                if(data.status=="S"){
                    $.alertMessage.info("操作成功");
                    $('#main-content').postUrl(Main.contextPath+"/SSC11324/init/2",{
                        contractCode:data.result.contractCode,
                        contractId:data.result.contractId,
                        invoiceRequestCode:data.result.invoiceRequestCode,
                        invoiceRequestId:data.result.invoiceRequestId
                    }, Main.hiddenHeader);
                }else
                {
                    $.alertMessage.info("审批错误,请重新操作");
                    $('#main-content').postUrl(Main.contextPath+"/SSC11324/init/2",{
                        contractCode:data.result.contractCode,
                        contractId:data.result.contractId,
                        invoiceRequestCode:data.result.invoiceRequestCode,
                        invoiceRequestId:data.result.invoiceRequestId
                    }, Main.hiddenHeader);
            }
            });
        });
    },
    //修改内容
    bindChangeButton: function () {
        $("#SSC11324_CHANGE").click(function () {
            if($('#statusCtr').val()=='9'){
                SSC11324.bindApplydButton();
                return false;
            }
            if($('#status').val()=='3'){
                $.alertMessage.info("发票已完成开票,禁止修改")
                return false;
            }
            if($('#status').val()=='4'){
                $.alertMessage.info("发票申请流程已完成,禁止修改")
                return false;
            }
             if(!checked()){
                 return;
             }
            if($("#supplierId").val()==null||$("#supplierId").val()==''||$("#supplierId").val()==undefined){
                $("#supplierId").val(0);
            }

            if($("#purchaserId").val()==null||$("#purchaserId").val()==''||$("#purchaserId").val()==undefined){
                $("#purchaserId").val(0);
            }

            $('#main-content').postUrl(Main.contextPath+"/SSC11324/modifyInvoiceRequestUp",
                {
                    invoiceRequestId:$("#invoiceRequestId").val(),
                    contractCode:$("#contractCode").val(),
                    contractName:$("#contractName").val(),
                    contractActDateStr:$("#contractActDateStr").val(),
                    contractAmount:$("#contractAmount").val(),
                    purchaserName:$("#purchaserName").val(),
                    supplierName:$("#supplierName").val(),
                    invoiceRequestCode:$("#invoiceRequestCode").val(),
                    receiving:$("#receiving").val(),
                    payer:$("#payer").val(),
                    invoiceAmount:SSCCommon.clearComma($("#invoiceAmount").val().trim()),
                    invoiceType: $("#invoiceType").find("option:selected").val(),
                    //invoiceType:$("input[name='invoiceType']:checked").val(),
                    remark:$("#remark").val(),
                    requestTimeStr:$("#requestTimeStr").val(),
                    receiveDateStr:$("#receiveDate").val(),
                    contractId:$("#contractId").val(),
                    supplierId:$("#supplierId").val(),
                    supplierCode:$("#supplierCode").val(),
                    purchaserId:$("#purchaserId").val(),
                    purchaserCode:$("#purchaserCode").val(),
                    requester:$("#requester").val(),
                    buyerTaxpayerCode:$("#buyerTaxpayerCode").val(),
                    receiverTaxpayerCode:$("#receiverTaxpayerCode").val(),
                    buyerAddr:$("#buyerAddr").val(),
                    sellerAddr:$("#sellerAddr").val(),
                    buyerTel:$("#buyerTel").val(),
                    sellerTel:$("#sellerTel").val(),
                    buyerBank:$("#buyerBank").val(),
                    sellerBank:$("#sellerBank").val(),
                    buyerAccount:$("#buyerAccount").val(),
                    sellerAccount:$("#sellerAccount").val(),
                    invoiceRequestDesc:$("#invoiceRequestDesc").val(),
                    receiver:$("#receiver").val(),
                    ver:$("#ver").val(),
                    status:'0'//已申请状态
                },function(data){
                    if(data.status=="S"){
                        $.alertMessage.info("操作成功");
                        $('#main-content').postUrl(Main.contextPath+"/SSC11324/init/2",{
                            contractCode:data.result.contractCode,
                            contractId:data.result.contractId,
                            invoiceRequestCode:data.result.invoiceRequestCode,
                            invoiceRequestId:data.result.invoiceRequestId
                        }, Main.hiddenHeader);
                    }else
                    {
                        $.alertMessage.info("修改失败,请重新操作");
                        $('#main-content').postUrl(Main.contextPath+"/SSC11324/init/2",{
                            contractCode:data.result.contractCode,
                            contractId:data.result.contractId,
                            invoiceRequestCode:data.result.invoiceRequestCode,
                            invoiceRequestId:data.result.invoiceRequestId
                        }, Main.hiddenHeader);
                    }
                });
        });
    },
    bindAuditButton: function () {
        $("#SSC11324_AUDIT").click(function () {
            if($('#statusCtr').val()=='9'){
                $.alertMessage.info("请先申请");
                return;
            }
            if($('#status').val()=='5'){
                $.alertMessage.info("审批未通过,请等待修改后审批");
                return;
            }
            if($('#status').val()=='6'){
                $.alertMessage.info("审核未通过,请等待修改后审批");
                return;
            }

            if($('#status').val()!='1'){
                if($('#status').val()=='0')
                {
                    $.alertMessage.info("审批未完成,请审批后审核");
                }else
                {
                    $.alertMessage.info("请不要重复审核");
                }
                return;
            }
                var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
                var regs = /^\d+(\.\d+)?$/;

                if($("#supplierId").val()==null||$("#supplierId").val()==''||$("#supplierId").val()==undefined){
                    $("#supplierId").val(0);
                }

                if($("#purchaserId").val()==null||$("#purchaserId").val()==''||$("#purchaserId").val()==undefined){
                    $("#purchaserId").val(0);
                }


            $('#main-content').postUrl(Main.contextPath+"/SSC11324/modifyInvoiceRequestUp",{
                contractCode:$("#contractCode").val(),
                contractId:$("#contractId").val(),
                invoiceRequestCode:$("#invoiceRequestCode").val(),
                invoiceRequestId:$("#invoiceRequestId").val(),
                auditingPerson:$("#auditingPerson").val(),
                approvalRemark:$("#approvalRemark").val(),
                auditingRemark:$("#auditingRemark").val(),
                ver:$("#ver").val(),
                status:$("input[name='auditingFlag']:checked").val()//改为已审核状态
                //invoiceDate:$("#invoiceDate").val()//开票时间没有加入
            },function(data){
                if(data.status=="S"){
                    $.alertMessage.info("操作成功");
                    $('#main-content').postUrl(Main.contextPath+"/SSC11324/init/2",{
                        contractCode:data.result.contractCode,
                        contractId:data.result.contractId,
                        invoiceRequestCode:data.result.invoiceRequestCode,
                        invoiceRequestId:data.result.invoiceRequestId
                    }, Main.hiddenHeader);
                }else
                {
                    $.alertMessage.info("审核发生错误,重新审核");
                    $('#main-content').postUrl(Main.contextPath+"/SSC11324/init/2",{
                        contractCode:data.result.contractCode,
                        contractId:data.result.contractId,
                        invoiceRequestCode:data.result.invoiceRequestCode,
                        invoiceRequestId:data.result.invoiceRequestId
                    }, Main.hiddenHeader);

                }
            });
            //var uploadFile = $("#SSC11324Upload");
            //$.core.uploadForm(uploadFile,true);
        });
    },
    bindInvoiceButton: function () {
        $("#SSC11324_INVOICE").click(function () {
            if($('#statusCtr').val()=='9'){
                $.alertMessage.info("请先申请");
                return;
            }
            if($('#status').val()=='5'){
                $.alertMessage.info("审批未通过,请在审核完成后开票");
                return;
            }
            if($('#status').val()=='6'){
                $.alertMessage.info("审核未通过,请在审核完成后开票");
                return;
            }
            if($('#status').val()!='2'){
                if($('#status').val()=='3')
                {
                    $.alertMessage.info("请不要重复开票");
                }else if($('#status').val()=='4')
                {
                    $.alertMessage.info("请不要重复开票");
                }else
                {
                    $.alertMessage.info("审核未完成,请在审核完成后开票");
                }
                return;
            }
            if($("#supplierId").val()==null||$("#supplierId").val()==''||$("#supplierId").val()==undefined){
                $("#supplierId").val(0);
            }
            if($("#purchaserId").val()==null||$("#purchaserId").val()==''||$("#purchaserId").val()==undefined){
                $("#purchaserId").val(0);
            }
            $('#main-content').postUrl(Main.contextPath+"/SSC11324/modifyInvoiceRequestUp",{
                contractCode:$("#contractCode").val(),
                contractId:$("#contractId").val(),
                invoiceRequestCode:$("#invoiceRequestCode").val(),
                invoiceRequestId:$("#invoiceRequestId").val(),
                ver:$("#ver").val(),
                status:'3'
                //invoiceDate:$("#invoiceDate").val()//开票时间没有加入
            },function(data){
                if(data.status=="S"){
                    $.alertMessage.info("操作成功");
                    $('#main-content').postUrl(Main.contextPath+"/SSC11324/init/2",{
                        contractCode:data.result.contractCode,
                        contractId:data.result.contractId,
                        invoiceRequestCode:data.result.invoiceRequestCode,
                        invoiceRequestId:data.result.invoiceRequestId
                    }, Main.hiddenHeader);
                }else
                {
                    $.alertMessage.info("开票错误,请重新开票");
                    $('#main-content').postUrl(Main.contextPath+"/SSC11324/init/2",{
                        contractCode:data.result.contractCode,
                        contractId:data.result.contractId,
                        invoiceRequestCode:data.result.invoiceRequestCode,
                        invoiceRequestId:data.result.invoiceRequestId
                    }, Main.hiddenHeader);
                }
            });
        });
    },
    //bindUploadInfoButton: function () {
    //    $("#SSC11324_UPLOADINFO").click(function () {
    //        if($('#status').val()=='9'){
    //            $.alertMessage.info("请先申请");
    //            return;
    //        }
    //        if($('#status').val()=='5'){
    //            $.alertMessage.info("审批未通过,请等待修改后审批");
    //            return;
    //        }
    //        if($('#status').val()=='6'){
    //            $.alertMessage.info("审核未通过,请等待修改后审批");
    //            return;
    //        }
    //        if($('#status').val()!='3'){
    //            if($('#status').val()=='4')
    //            {
    //                $.alertMessage.info("流程已完成,请不要重复上传");
    //            }else
    //            {
    //                $.alertMessage.info("审核未完成,请开票完成后提交");
    //            }
    //            return;
    //        }
    //        var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
    //
    //        if( $("#receiver").val()==""|| $("#receiver").val()==null|| $("#receiver").val().length>100){
    //            $.alertMessage.info("发票接收人填写错误,请重新填写");
    //            return;
    //        }
    //        if( $("#receiveDate").val()==""|| $("#receiveDate").val()==null){
    //            $.alertMessage.info("发票接收日期不能为空");
    //            return;
    //        }
    //        if(!reg.test( $("#receiveDate").val())){
    //            $.alertMessage.info("请按YYYY-MM-DD格式输入申请时间");
    //            return;
    //        }
    //        if($("#myfile").val()==null||$("#myfile").val()==""||$("#myfile").val()==undefined)
    //        {
    //            $.alertMessage.info("未选择文件,请选择文件后上传");
    //            return;
    //        }
    //        $('#main-content').postUrl(Main.contextPath+"/SSC11324/modifyInvoiceRequestUp",{
    //            receiver:$("#receiver").val(),
    //            receiveDateStr:$("#receiveDate").val(),
    //            contractCode:$("#contractCode").val(),
    //            contractId:$("#contractId").val(),
    //            invoiceRequestCode:$("#invoiceRequestCode").val()
    //        },function(data){
    //            if(data.status!="F"){
    //                var uploadFile = "#SSC11324Upload";
    //                $.core.uploadForm(uploadFile,true);
    //            }else
    //            {
    //                $.alertMessage.info("文件信息更新失败,请重新提交");
    //            }
    //
    //        },{refreshHtml:false})
    //
    //    });
    //},
    control : function(){
        // 根据发票状态制御画面
        var invoiceStatus = $("#status").val();
        if (invoiceStatus == "0"&&$("#statusCtr").val()!='9'){
            $("input[name='auditingFlag']").attr("disabled","disabled");
            $("input[name='auditingRemark']").attr("disabled","disabled");

        } else if(invoiceStatus == "1"){
            $("input[name='approvalFlag']").attr("disabled","disabled");
            $("input[name='approvalRemark']").attr("disabled","disabled");
        } else
       {
           $("input[name='auditingFlag']").attr("disabled","disabled");
           $("input[name='approvalFlag']").attr("disabled","disabled");
           $("input[name='auditingRemark']").attr("disabled","disabled");
           $("input[name='approvalRemark']").attr("disabled","disabled");
        }
        if(invoiceStatus!="5"&&invoiceStatus!="6"){
            $("#approvalFlag1").prop("checked", true);
            $("#auditingFlag1").prop("checked", true);
        }
    },
    bindDownloadButton: function () {
        $("#SSC11324_DOWNLOAD").click(function () {
            window.open(SSC11324.getDownLoadUrl("download"),"blank");
        });
    },
    bindShowEpInfo:function(){
        $("#chooseEpInfo").click(function () {
            $.pdialog.open("选择企业信息", Main.contextPath + "/SSC1130803/init", {width: 1200, height: 500}, {
                "supplierInputId":"payer",
                "bankInputId":"buyerBank",
                "bankAccountInputId":"buyerAccount"
            },"chooseEpDialog");
        })
    },
    bindShowRpInfo:function(){
        $("#chooseRpInfo").click(function () {
            $.pdialog.open("选择企业信息", Main.contextPath + "/SSC1130803/init", {width: 1200, height: 500}, {
                "supplierInputId":"receiving",
                "bankInputId":"sellerBank",
                "bankAccountInputId":"sellerAccount"
            },"chooseEpDialog");
        })
    },
    //按钮置灰
    //grey:function(){
    //    var checkVal=$("#status").val();
    //
    //    if(checkVal==0&&$("#statusCtr").val()==9){
    //        $("#SSC11324_AUDIT").prop("disabled",true);
    //        $("#SSC11324_APPROVAL").prop("disabled",true);
    //        $("#SSC11324_AUDIT").removeClass("h-button");
    //        $("#SSC11324_APPROVAL").removeClass("h-button");
    //
    //        $("#SSC11324_CHANGE").prop("disabled",true);
    //        $("#SSC11324_INVOICE").prop("disabled",true);
    //        $("#SSC11324_CHANGE").removeClass("h-button");
    //        $("#SSC11324_INVOICE").removeClass("h-button");
    //    }
    //    if(checkVal==6||checkVal==5){
    //        $("#SSC11324_AUDIT").prop("disabled",true);
    //        $("#SSC11324_AUDIT").removeClass("h-button");
    //        $("#SSC11324_APPROVAL").prop("disabled",true);
    //        $("#SSC11324_APPROVAL").removeClass("h-button");
    //        $("#SSC11324_APPLY").prop("disabled",true);
    //        $("#SSC11324_INVOICE").prop("disabled",true);
    //        $("#SSC11324_APPLY").removeClass("h-button");
    //        $("#SSC11324_INVOICE").removeClass("h-button");
    //    }
    //    if(checkVal==0){
    //        $("#SSC11324_AUDIT").prop("disabled",true);
    //        $("#SSC11324_AUDIT").removeClass("h-button");
    //
    //        $("#SSC11324_APPLY").prop("disabled",true);
    //        $("#SSC11324_INVOICE").prop("disabled",true);
    //        $("#SSC11324_APPLY").removeClass("h-button");
    //        $("#SSC11324_INVOICE").removeClass("h-button");
    //    }
    //    if(checkVal==1){
    //        $("#SSC11324_APPROVAL").prop("disabled",true);
    //        $("#SSC11324_APPROVAL").removeClass("h-button");
    //        $("#SSC11324_CHANGE").prop("disabled",true);
    //        $("#SSC11324_CHANGE").removeClass("h-button");
    //
    //        $("#SSC11324_APPLY").prop("disabled",true);
    //        $("#SSC11324_INVOICE").prop("disabled",true);
    //        $("#SSC11324_APPLY").removeClass("h-button");
    //        $("#SSC11324_INVOICE").removeClass("h-button");
    //    }
    //    if(checkVal==2){
    //        $("#SSC11324_APPROVAL").prop("disabled",true);
    //        $("#SSC11324_CHANGE").prop("disabled",true);
    //        $("#SSC11324_APPROVAL").removeClass("h-button");
    //        $("#SSC11324_CHANGE").removeClass("h-button");
    //
    //        $("#SSC11324_APPLY").prop("disabled",true);
    //        $("#SSC11324_AUDIT").prop("disabled",true);
    //        $("#SSC11324_APPLY").removeClass("h-button");
    //        $("#SSC11324_AUDIT").removeClass("h-button");
    //    }
    //    if(checkVal==3){
    //        $("#SSC11324_APPROVAL").prop("disabled",true);
    //        $("#SSC11324_CHANGE").prop("disabled",true);
    //        $("#SSC11324_APPROVAL").removeClass("h-button");
    //        $("#SSC11324_CHANGE").removeClass("h-button");
    //
    //        $("#SSC11324_APPLY").prop("disabled",true);
    //        $("#SSC11324_INVOICE").prop("disabled",true);
    //        $("#SSC11324_APPLY").removeClass("h-button");
    //        $("#SSC11324_INVOICE").removeClass("h-button");
    //        $("#SSC11324_AUDIT").prop("disabled",true);
    //        $("#SSC11324_AUDIT").removeClass("h-button");
    //    }
    //    //$("#SSC11324_AUDIT").prop("disabled",true);
    //    //$("#SSC11324_APPROVAL").prop("disabled",true);
    //    //$("#SSC11324_AUDIT").removeClass("h-button");
    //    //$("#SSC11324_AUDIT").css("background","#BEBEBE");
    //
    //},
    getDownLoadUrl:function (downLoadId) {
        var url = $("#downLoadUrl").val() + $("#" + downLoadId + "").attr("name").replace(",","/") + "/" + $("#" + downLoadId + "").val();
        return url;
    },
    selectCntrol:function(){
        var invoiceStatus = $("#status").val();
        if(invoiceStatus==0){
            if($("#invoiceTypeVal").val()=="1"){
                $("#invoiceType").val(1);
            }
            if($("#invoiceTypeVal").val()=="2"){
                $("#invoiceType").val(2);
            }
            if($("#invoiceTypeVal").val()=="3"){
                $("#invoiceType").val(3);
            }
        }
    },
    //金额加逗号
    changeAmount:function(){

        var values=SSCCommon.clearComma($("#invoiceAmount").val().trim());
        if(SSCCommon.MONEY_REG.test(values));{
            $("#invoiceAmount").val(SSCCommon.formatMoney(values));
        }
    }
    //绑定下载按钮
    //bindUploadButton:function () {
    //    $("#SSC11324_UPLOAD").click(function (){
    //        var uploadFile = "#SSC11324Upload";
    //        $.core.uploadForm(uploadFile,true);
    //    });
    //}

}

$(document).ready(function(){
    SSC11324.init();
    SSCCommon.showFormatMoney();
});

function checked(){
    var regz=/^[0-9]\d*$/;
    var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
    var regs = /^\d{1,10}(?:\.\d{1,2})?$/;

    if($('#payer').val().length==0){
        $.alertMessage.info("请输入付款单位");
        return false;
    }
    if($('#receiving').val().length==0){
        $.alertMessage.info("请输入收款单位");
        return false;
    }
    if($('#buyerTaxpayerCode').val().length==0){
        $.alertMessage.info("请输入购货方纳税人识别号");
        return false;
    }
    if($('#receiverTaxpayerCode').val().length==0){
        $.alertMessage.info("请输入收货方纳税人识别号");
        return false;
    }


    if($('#buyerAddr').val().length==0){
        $.alertMessage.info("请输入购货方地址");
        return false;
    }
    if($('#sellerAddr').val().length==0){
        $.alertMessage.info("请输入销售方地址");
        return false;
    }
    if($('#buyerTel').val().length==0){
        $.alertMessage.info("请输入购货方电话");
        return false;
    }
    if($('#buyerBank').val().length==0){
        $.alertMessage.info("请输入购货方开户银行");
        return false;
    }
    if($('#sellerTel').val().length==0){
        $.alertMessage.info("请输入销售方电话");
        return false;
    }
    if($('#sellerBank').val().length==0){
        $.alertMessage.info("请输入销售方开户银行");
        return false;
    }
    if($('#buyerAccount').val().length==0){
        $.alertMessage.info("请输入购货方账号");
        return false;
    }
    if($('#sellerAccount').val().length==0){
        $.alertMessage.info("请输入销售方账号");
        return false;
    }
    if($('#requester').val().length==0){
        $.alertMessage.info("请输入申请人");
        return false;
    }
    if($('#requestTimeStr').val().length==0){
        $.alertMessage.info("请输入申请时间");
        return false;
    }
    //if($('#payer').val().length>100){
    //    $.alertMessage.info("付款单位长度超出最大限制,请重新输入");
    //    return false;
    //}
    //if($('#receiving').val().length>100){
    //    $.alertMessage.info("收款单位长度超出最大限制,请重新输入");
    //    return false;
    //}
    //if($('#buyerTaxpayerCode').val().length>100){
    //    $.alertMessage.info("购货方纳税人识别号长度超出最大限制,请重新输入");
    //    return false;
    //}
    //if($('#receiverTaxpayerCode').val().length>100){
    //    $.alertMessage.info("收货方纳税人识别号长度超出最大限制,请重新输入");
    //    return false;
    //}
    //if($('#buyerAddr').val().length>100){
    //    $.alertMessage.info("购货方地址长度超出最大限制,请重新输入");
    //    return false;
    //}






    //if(!regz.test($('#sellerAccount').val())){
    //    $.alertMessage.info("请输入正确的销售方银行账号(数字格式)");
    //    document.getElementById('sellerAccount').focus();
    //    return false;
    //}
    //
    //if(!regz.test($('#buyerAccount').val())){
    //    $.alertMessage.info("请输入正确的购货方银行账号(数字格式)");
    //    document.getElementById('buyerAccount').focus();
    //    return false;
    //}
    if(!SSCCommon.PHONE_REG.test($('#buyerTel').val())){
        $.alertMessage.info("请输入正确的购货方电话号码格式");
        document.getElementById('buyerTel').focus();
        return false;
    }
    if(!SSCCommon.PHONE_REG.test($('#sellerTel').val())){
        $.alertMessage.info("请输入正确的销售方电话号码格式");
        document.getElementById('sellerTel').focus();
        return false;
    }
    //if($('#sellerAddr').val().length>100){
    //    $.alertMessage.info("销售方地址长度超出最大限制,请重新输入");
    //    return false;
    //}
    //if($('#buyerTel').val().length>100){
    //    $.alertMessage.info("购货方电话长度超出最大限制,请重新输入");
    //    return false;
    //}
    //if($('#buyerBank').val().length>100){
    //    $.alertMessage.info("购货方开户银行长度超出最大限制,请重新输入");
    //    return false;
    //}
    //if($('#sellerTel').val().length>100){
    //    $.alertMessage.info("销售方电话长度超出最大限制,请重新输入");
    //    return false;
    //}
    //if($('#sellerBank').val().length>100){
    //    $.alertMessage.info("销售方开户银行长度超出最大限制,请重新输入");
    //    return false;
    //}
    //if($('#buyerAccount').val().length>100){
    //    $.alertMessage.info("购货方账号长度超出最大限制,请重新输入");
    //    return false;
    //}
    //if($('#sellerAccount').val().length>100){
    //    $.alertMessage.info("收货方账号长度超出最大限制,请重新输入");
    //    return false;
    //}
    //if($('#requester').val().length>100){
    //    $.alertMessage.info("申请人长度超出最大限制,请重新输入");
    //    return false;
    //}

    //if($('#remark').val().length>100){
    //    $.alertMessage.info("备注长度超过100位,请重新输入");
    //    return false;
    //}
    if(!reg.test($('#requestTimeStr').val())){
        $.alertMessage.info("请按YYYY-MM-DD格式输入申请时间");
        return false;
    }
    if(parseFloat(SSCCommon.clearComma($("#invoiceAmount").val().trim()))>parseFloat(SSCCommon.clearComma($('#remainInvoiceAmount').html().trim()))){
        $.alertMessage.info("申请金额超出可申请范围,请重新输入")
        return false;
    }
    if(!SSCCommon.isMoney(SSCCommon.clearComma($("#invoiceAmount").val().trim()))){
        $.alertMessage.info("发票金额不能为空或0，且格式为：整数位最多15位，小数位最多2位！");
        return false;
    }

    return true;
}