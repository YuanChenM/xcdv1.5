var SSC1130503 = {
    listGrid : null,
    init: function () {
        SSC1130503.listGrid = $("#SSC1130503_list_grid").grid({
        })
        this.bindConfirmButton();
        this.bindDatePicker($(":input[name*='etaStr']"));
        this.closeDate();
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
    bindConfirmButton:function(){
        $("#SSC1130503_CONFIRM" ).click(function(){

            var selectDeliveryOrderOne = SSC1130503.listGrid.getChoiceOne();

            var deliveryCodeId = $("#deliveryCodeId").val();
            if(deliveryCodeId!=""&&deliveryCodeId!=null&&deliveryCodeId!=undefined){
                $("#"+deliveryCodeId).val(selectDeliveryOrderOne.deliveryCode);
                var deliveryId=selectDeliveryOrderOne.deliveryId;
                //$("#delivery_select").val(selectDeliveryOrderOne.deliveryCode);
                loadDelivery();
            }


            var deliveryCode = $("#delivery_select").val();
            $('#tags').val("");
            if (deliveryCode != "" && deliveryCode != null) {
                var contractBasic = SSC11314.deliveryData[deliveryCode];
                var contractName = contractBasic.contractName == null ? "" : contractBasic.contractName;
                if (contractBasic.contractCode != null && contractBasic.contractCode != '') {
                    $('#tags').val(contractBasic.contractCode + "(" + contractName + ")");
                }
            }

            $.pdialog.close("chooseDeliveryOrderDialog");

        });
    }

}


function loadDelivery(){
    //运输实际发生额
    var selectDeliveryOrderOne = SSC1130503.listGrid.getChoiceOne();
    var transportCost = selectDeliveryOrderOne.transportCost;
    if(transportCost=='' || transportCost==null){
        transportCost = 0.00;
    }
    $("#transportAmount").val(transportCost);

    var deliveryId =  selectDeliveryOrderOne.deliveryId;
    $("#deliveryId").val(deliveryId);

    var deliveryCode =selectDeliveryOrderOne.deliveryCode;
    $("#deliveryCode").val(deliveryCode);

    var supplierId = selectDeliveryOrderOne.supplierId;
    $("#supplierId").val(supplierId);
    var supplierName = selectDeliveryOrderOne.supplierName;
    $("#supplierName").val(supplierName);
    var purchaserId = selectDeliveryOrderOne.purchaserId;
    $("#purchaserId").val(purchaserId);
    var purchaserName = selectDeliveryOrderOne.purchaserName;
    $("#purchaserName").val(purchaserName);

    //本次发货订单总金额
    var deliverAmount = selectDeliveryOrderOne.amount;
    if(deliverAmount==''){
        deliverAmount=0.00;
    }
    $("#deliverTotalAmount").val(deliverAmount);


    //预付款按比例已支付金额
    $('#main-content').postUrl(Main.contextPath + "/SSC11308/findDeliveryPDList",{"deliveryId":deliveryId},function(data){
        $("#paidDownPaymentPercentage").val(data);
    }, {refreshHtml: false});


    //var contractCode = $("#deliverList").find("option:selected").attr("contractCode");
    //
    var contractId =  selectDeliveryOrderOne.contractId;
    $("#contractId").val(contractId);
    //if(contractId!=""){
    //    $("#contractId").val(contractId);
    //    contractCode
    //    $("#contractCode").val(contractCode);
    //    $("#contractList").val(contractCode);
    //
    //    $('#main-content').postUrl(Main.contextPath + "/SSC11308/getContractOrderPD",{"contractId":contractId},function(data){
    //        if(data!=null){
    //            for(var i=0;i<data.length;i++ ){
    //                var contractFirstAmount = 0;
    //                var contractTotalAmount = 0;
    //
    //                var productValue = data[i].productValue;
    //                var downPayment =  data[i].downPayment/100;
    //                contractFirstAmount =downPayment*productValue;
    //                //合同预付款总金额
    //                $("#contractFirstAmount").val(contractFirstAmount);
    //
    //                //合同总金额
    //                contractTotalAmount = contractTotalAmount + productValue;
    //                $("#contractTotalAmount").val(contractTotalAmount);
    //
    //                $("#supplierId").val(data[i].supplierId);
    //                $("#supplierCode").val(data[i].supplierCode);
    //                $("#purchaserId").val(data[i].purchaserId);
    //                $("#purchaserName").val(data[i].purchaserName);
    //                $("#purchaserCode").val(data[i].purchaserCode);
    //
    //            }
    //        }
    //
    //
    //    }, {refreshHtml: false});
    //
    //}

}

$(document).ready(function () {
    // 初始化调用
    SSC1130503.init();

});


