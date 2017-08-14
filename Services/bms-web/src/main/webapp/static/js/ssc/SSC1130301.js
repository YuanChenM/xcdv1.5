var SSC1130301 = {
    listGrid : null,
    thisRowData: null,
    thisRowIndex:null,
    formId:"SSC1130301Form",
    init: function () {
        SSC1130301.listGrid = $("#SSC1130301_list_grid").grid({
            actionHandler:SSC1130301.actionHandler,
            editCellOnBlurHandler :SSC1130301.cellOnBlurHandler
        })
        this.bindConfirmButton();
        this.bindDatePicker($(":input[name*='contractActDateStr']"));
        this.closeDate();
    },
    cellOnBlurHandler : function($comp){
        //获得TD
        var $td = $comp.parent();
        //获得TR
        var $tr = $td.parent();
        //获得所有行
        var $trs = $tr.parent();
        //获得当前行
        SSC1130301.thisRowIndex = $trs.children().index($tr);
        //获得当前行数据
        var num=$comp.val();
        SSC1130301.thisRowData = SSC1130301.listGrid.fnGetData(SSC1130301.thisRowIndex);
    },
    bindDatePicker : function(timeId){
        $(timeId).datepicker({
            dateFormat: "yy-mm-dd",
            closeText: "清除",
            showButtonPanel: true

        });
        $(timeId).attr("readonly","readonly");



    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    //处理函数,
    bindConfirmButton:function(){
        $("#SSC1130301_CONFIRM" ).click(function(){
            var selectEpOne = SSC1130301.listGrid.getChoiceOne();
            
            var contractIdId =$("#contractIdId").val();
            var contractNameId =$("#contractNameId").val();
            var contractCodeId =$("#contractCodeId").val();
            var purchaserNameId =$("#purchaserNameId").val();
            var supplierNameId =$("#supplierNameId").val();
            var bidProjectNoId =$("#bidProjectNoId").val();
            var contractStatusId =$("#contractStatusId").val();
            var isRelationContract =$("#isRelationContract").val();
            var contractAmountId =$("#contractAmountId").val();
            if(contractIdId!=""&&contractIdId!=null&&contractIdId!=undefined){
                $("#"+contractIdId).val(selectEpOne.contractId);
            }
            if(contractNameId!=""&&contractNameId!=null&&contractNameId!=undefined&&contractNameId=="tags"){
                $("#"+contractNameId).val(selectEpOne.contractCode+"("+selectEpOne.contractName+")");
            }
            if(contractNameId!=""&&contractNameId!=null&&contractNameId!=undefined&&contractNameId!="tags"){
                $("#"+contractNameId).val(selectEpOne.contractName);
            }
            if(contractCodeId!=""&&contractCodeId!=null&&contractCodeId!=undefined){
                $("#"+contractCodeId).val(selectEpOne.contractCode);
                $("#"+contractAmountId).val(selectEpOne.contractAmount);
                loadContract();
            }
            if(purchaserNameId!=""&&purchaserNameId!=null&&purchaserNameId!=undefined){
                $("#"+purchaserNameId).val(selectEpOne.purchaserName);
            }
            if(supplierNameId!=""&&supplierNameId!=null&&supplierNameId!=undefined){
                $("#"+supplierNameId).val(selectEpOne.supplierName);
            }
            if(bidProjectNoId!=""&&bidProjectNoId!=null&&bidProjectNoId!=undefined){
                $("#"+bidProjectNoId).val(selectEpOne.bidProjectNo);
            }
            if(contractStatusId!=""&&contractStatusId!=null&&contractStatusId!=undefined){
                $("#"+contractStatusId).val(selectEpOne.contractStatus);
            }

            if(isRelationContract!=""&&isRelationContract!=null&&isRelationContract!=undefined&&isRelationContract=="true"){
                //查询输入的合同是否存在
                $('#main-content').postUrl(Main.contextPath + '/SSC11324/ContractFindInvoiceRequestDetailExist',
                {contractCode: selectEpOne.contractCode,contractName:selectEpOne.contractName,isOrder:true}, function (data) {
                        if(data.status == "S"){
                            //新建发货订单
                            if($("#deliveryId").val() == ""){
                                $("#contractId").val(data.result.contractId);
                                $("#contractCode").val(data.result.contractCode);
                                $("#contractCodeHref").text(data.result.contractCode);
                                //$.pdialog.close();
                            }else{
                                //关联发货订单，发货确认单，预入库通知单,差异单，付款申请，资金池
                                $('#main-content').postUrl(Main.contextPath + '/SSC11306/relevanceContract', {
                                    deliveryId:$("#deliveryId").val(),
                                    contractId:data.result.contractId,contractCode: data.result.contractCode,
                                    ver:$("#ver").val()
                                },function(data){
                                    $.alertMessage.info("操作成功");
                                    $("#headBreadCrumb").hide();
                                });
                                //$.pdialog.close();
                            }
                        }else {
                            //发货订单关联合同只取未删除，合同状态不为7：待核销，8：已结案 的数据
                            $.alertMessage.info("无法关联已废弃，待核销及已结案的合同,请重新选择");
                        }
                },{refreshHtml:false});
            }
            $.pdialog.close("chooseEpDialog");
        });
    }

}



function loadContract(){
    var selectEpOne = SSC1130301.listGrid.getChoiceOne();
    var contractId = selectEpOne.contractId;
    $("#contractId").val(contractId);
    var contractCode = selectEpOne.contractCode;
    $("#contractCode").val(contractCode);
    var supplierId =selectEpOne.supplierId;
    $("#supplierId").val(supplierId);
    var supplierName = selectEpOne.supplierName;
    $("#supplierName").val(supplierName);
    var purchaserId = selectEpOne.purchaserId;
    $("#purchaserId").val(purchaserId);
    var purchaserName = selectEpOne.purchaserName;
    $("#purchaserName").val(purchaserName);
    var purchaserCode = selectEpOne.purchaserCode;
    $("#purchaserCode").val(purchaserCode);
    plusContractAmount(contractId);

}


function plusContractAmount(contractId){
    $('#main-content').postUrl(Main.contextPath + "/SSC11308/getContractOrderPD",{"contractId":contractId},function(data){
        //合同总金额
        var  contractFirstAmount = 0.00;
        var contractTotalAmount = 0.00;
        if(data!=null){
            for(var i=0;i<data.length;i++ ){
                var productValue = parseFloat(data[i].productValue);
                //var downPayment = (parseFloat(data[i].downPayment))/100;
                contractTotalAmount = contractTotalAmount + productValue;
                contractFirstAmount = contractFirstAmount +(parseFloat(data[i].paymentAmount));
            }
        }
        //合同总金额
        $("#contractTotalAmount").val(contractTotalAmount.toFixed(2));
        //合同预付款总金额
        $("#contractFirstAmount").val(contractFirstAmount.toFixed(2));

    }, {refreshHtml: false});

}


$(document).ready(function () {
    // 初始化调用
    SSC1130301.init();

});


