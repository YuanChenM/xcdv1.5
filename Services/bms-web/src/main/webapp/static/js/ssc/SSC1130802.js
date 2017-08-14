/**
 *
 * Created ssc wu_honglei on 16/8/9.
 */


var SSC1130802 = {
    formId:"SSC1130802Form",
    init: function () {
        this.bindSaveButton();
        //this.bindContractSelectChange();
       // this.bindDeliverSelectChange();
        //this.bindVerificationSelectChange();
        this.bindPaymentTypeButton();
        this.bindShowContractInfo();
        this.bindShowDeliveryInfo();
        this.bindShowVerificationInfo();
    },

    bindShowContractInfo:function(){
        $("#chooseContractInfo").click(function () {
            $.pdialog.open("选择合同", Main.contextPath + "/SSC1130301/init", {width: 1200, height: 500}, {
                "contractCodeId":"contractCode2",
                "contractIdId":"contractId",
                "contractStatusStr":"4",
                "isPaymentRequest":"true"
            },"chooseEpDialog");
        });
    },

    bindShowDeliveryInfo:function(){
        $("#chooseDeliveryInfo").click(function () {
            $.pdialog.open("选择发货订单", Main.contextPath + "/SSC11305/chooseDeliveryOrder", {width: 1200, height: 500}, {
                "deliveryCodeId":"deliveryCode2",//需要赋值deliveryCode的标签Id
                "isDeliveryConfirm":"false",//未生成发货确认单
                "delFlg":0,//未删除
                "deliveryStatus":4,//发货订单状态双方已确认
                //"contractCode":$("#contractCode").val(),//关联的合同code
                "isPaymentRequest":"true"//是否判断未生成付款申请
            },"chooseDeliveryOrderDialog");
        })
    },

    bindShowVerificationInfo:function(){
        $("#chooseVerificationInfo").click(function () {
            $.pdialog.open("选择核销单", Main.contextPath + "/SSC11321/chooseVerification", {width: 1200, height: 500}, {
                "verificationCodeId":"verificationCode2",//需要赋值verificationCode的标签Id
                "isPaymentRequest":"true",//是否判断未生成付款申请
                "verificationType":0,//核销处理办法为退款
                "delFlg":0,//未删除
                "status":3//核销单状态已确认
            },"chooseVerificationDialog");
        });
    },







bindSaveButton:function(){
        $("#SSC1130802_SAVE" ).click(function(){
            if(!checkSSC1130802Data()){
                return;
            }

            var amount=0.00;
            var paymentType = $("input[name='paymentType']:checked").val();
            if(paymentType==0){
                amount = $("#contractFirstAmount").val();//合同预付款金额=本次申请金额
            }else if(paymentType==1){
                var tempPaidDownPaymentPercentage = $("#paidDownPaymentPercentage").val();
                if(tempPaidDownPaymentPercentage==""){
                    tempPaidDownPaymentPercentage = 0.00;
                }
                var deliverTotalAmount = $("#deliverTotalAmount").val();
                amount = SSCCommon.subtract(parseFloat(deliverTotalAmount),parseFloat(tempPaidDownPaymentPercentage));
            }else if(paymentType==2){
                amount = $("#verificationAmount").val();//合同预付款金额=核销金额
            }

            $.alertMessage.confirm("你确定要保存当前数据吗？",function () {
                $.alertMessage.close();
                Main.detailWindow(Main.contextPath + "/SSC11308/init/0",  {
                    contractId:$("#contractId").val(),
                    contractCode:$("#contractCode").val(),
                    supplierId:$("#supplierId").val(),
                    supplierName:$("#supplierName").val(),
                    supplierCode:$("#supplierCode").val(),
                    purchaserId:$("#purchaserId").val(),
                    purchaserName:$("#purchaserName").val(),
                    purchaserCode:$("#purchaserCode").val(),
                    deliveryId:$("#deliveryId").val(),
                    deliveryCode:$("#deliveryCode").val(),
                    paymentType:paymentType,
                    contractTotalAmount:$("#contractTotalAmount").val(),
                    contractFirstAmount:$("#contractFirstAmount").val(),
                    deliverTotalAmount :$("#deliverTotalAmount").val(),
                    supportTotalAmount:$("#supportTotalAmount").val(),
                    transportAmount:$("#transportAmount").val(),
                    verificationId:$("#verificationId").val(),
                    verificationCode:$("#verificationCode").val(),
                    verificationAmount:$("#verificationAmount").val(),
                    packageAmount:$("#packageAmount").val(),
                    amount:amount,
                    allowApplyAmount:amount,
                    paidDownPaymentPercentage:$("#paidDownPaymentPercentage").val(),
                    moneyFlag:$("#moneyFlag").val()
                }, "付款申请");
                $.pdialog.close();
             });
        })
    },

    //合同下拉列表
    bindContractSelectChange:function(){
        $('#contractList').change(function(){
            loadContractList();
        })
    },
    //发货订单列表
    bindDeliverSelectChange:function(){
        $('#deliverList').change(function(){
            loadDeliveryList();
        })
    },
    bindVerificationSelectChange:function(){
        $('#verificationList').change(function(){
            loadVerificationList();
        })
    },
    //付款类型
    bindPaymentTypeButton:function(){
        $(".paymentTypeRadio").click(function(){
            var paymentType = $(this).val();
            /**0:预付款 1:进度款 2:余款*/
            if(paymentType==0){
                showContract();
                hideDelivery();
                hideVerification();
            }else if(paymentType==1){
                showDelivery();
                hideContract();
                hideVerification();
            }else if(paymentType==2){
                hideContract();
                hideDelivery();
                showVerification();
            }
        });
    }
}

function checkSSC1130802Data(){

    var paymentType = $("input[name='paymentType']:checked").val();
    if(paymentType==0){
        //预付款
        if($("#contractCode2").select().val()==""){
            $.alertMessage.info("请选择合同!");
            return false;
        }

        var contractTotalAmount = $("#contractTotalAmount").val();

        if(!SSCCommon.isMoney(contractTotalAmount)){
            $.alertMessage.info("订单合同总金额不能为0或空，且格式为：整数位最多15位，小数位最多2位！");
            return false;
        }


    }else if(paymentType==1){
        //进度款
        if($("#deliveryCode2").select().val()==""){
            $.alertMessage.info("请选择发货订单!");
            return false;
        }

        var deliverTotalAmount = $("#deliverTotalAmount").val();
        if(!SSCCommon.isMoney(deliverTotalAmount)){
            $.alertMessage.info("本次发货订单总金额不能为0或空，且格式为：整数位最多15位，小数位最多2位！");
            return false;
        }

        var transportAmount = $("#transportAmount").val();
        if(transportAmount!=""){
            if(!SSCCommon.MONEY_REG.test(transportAmount)){
                $.alertMessage.info("本次运输费用实际发生额格式为：整数位最多15位，小数位最多2位！");
                return false;
            }
        }

        var packageAmount = $("#packageAmount").val();
        if(packageAmount!=""){
            if(!SSCCommon.MONEY_REG.test(packageAmount)){
                $.alertMessage.info("包材使用费实际发生额格式为：整数位最多15位，小数位最多2位！");
                return false;
            }
        }

    }else if(paymentType==2){
        //余款
        if($("#verificationCode2").select().val()==""){
            $.alertMessage.info("请选择核销单!");
            return false;
        }

        var verificationAmount = $("#verificationAmount").val();
        if(!SSCCommon.isMoney(verificationAmount)){
            $.alertMessage.info("核销金额不能为0或空，且格式为：整数位最多15位，小数位最多2位！");
            return false;
        }
    }
    return true;
}

function hideDelivery(){
    $("#deliveryCode2").attr("disabled",true);
    $("#deliverTotalAmount").attr("disabled",true);
    $("#transportAmount").attr("disabled",true);
    $("#packageAmount").attr("disabled",true);

    $("#deliveryCode2").val("");
    $("#deliverTotalAmount").val("");
    $("#transportAmount").val("");
    $("#packageAmount").val("");
    $("#paidDownPaymentPercentage").val("");
    $("#deliveryDiv").hide();
}
function showDelivery(){
    $("#deliveryCode2").removeAttr("disabled")
    $("#deliverTotalAmount").removeAttr("disabled");
    $("#transportAmount").removeAttr("disabled");
    $("#packageAmount").removeAttr("disabled");

    $("#deliveryDiv").show();
}
function hideContract(){
    $("#contractCode2").attr("disabled",true);

    $("#contractTotalAmount").attr("disabled",true);
    $("#contractTotalAmount").val("");
    $("#contractFirstAmount").val("");
    $("#contractId").val("");
    $("#contractCode").val("");
    $("#supplierId").val("");
    $("#supplierCode").val("");
    $("#purchaserId").val("");
    $("#purchaserName").val("");
    $("#purchaserCode").val("");
    $("#contractCode2").val("");
    $("#contractDiv").hide();

}
function showContract(){
    $("#contractCode2").removeAttr("disabled");
    $("#contractTotalAmount").removeAttr("disabled");

    $("#contractDiv").show();
}

function showVerification(){
    $("#verificationCode2").removeAttr("disabled");
    $("#verificationAmount").removeAttr("disabled");
    $("#verificationDiv").show();

}

function hideVerification(){
    $("#verificationCode2").attr("disabled",true);
    $("#verificationAmount").attr("disabled",true);
    $("#verificationDiv").hide();
    $("#verificationCode2").val("");
    $("#verificationAmount").val("");
}

function loadContractList(){

  var contractId = $("#contractList").find("option:selected").attr("contractId");
    $("#contractId").val(contractId);

    var contractCode = $("#contractList").find("option:selected").attr("contractCode");
    $("#contractCode").val(contractCode);
    var supplierId = $("#contractList").find("option:selected").attr("supplierId");
    $("#supplierId").val(supplierId);
    var supplierName = $("#contractList").find("option:selected").attr("supplierName");
    $("#supplierName").val(supplierName);
    var purchaserId = $("#contractList").find("option:selected").attr("purchaserId");
    $("#purchaserId").val(purchaserId);
    var purchaserName = $("#contractList").find("option:selected").attr("purchaserName");
    $("#purchaserName").val(purchaserName);
    var purchaserCode = $("#contractList").find("option:selected").attr("purchaserCode");
    $("#purchaserCode").val(purchaserCode);

    var contractId = $("#contractId")
    plusContractAmount(contractId);

}

function loadDeliveryList(){
    //运输实际发生额
    var transportCost = $("#deliverList").find("option:selected").attr("transportCost");
    if(transportCost==''){
        transportCost = 0.00;
    }
    $("#transportAmount").val(transportCost);

    var deliveryId = $("#deliverList").find("option:selected").attr("deliveryId");
    $("#deliveryId").val(deliveryId);

    var deliveryCode = $("#deliverList").find("option:selected").attr("deliveryCode");
    $("#deliveryCode").val(deliveryCode);

    var supplierId = $("#deliverList").find("option:selected").attr("supplierId");
    $("#supplierId").val(supplierId);
    var supplierName = $("#deliverList").find("option:selected").attr("supplierName");
    $("#supplierName").val(supplierName);
    var purchaserId = $("#deliverList").find("option:selected").attr("purchaserId");
    $("#purchaserId").val(purchaserId);
    var purchaserName = $("#deliverList").find("option:selected").attr("purchaserName");
    $("#purchaserName").val(purchaserName);

    //本次发货订单总金额
    var deliverAmount = $("#deliverList").find("option:selected").attr("amount");
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
    var contractId = $("#deliverList").find("option:selected").attr("contractId");
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


function loadVerificationList(){

    var verificationId = $("#verificationList").find("option:selected").attr("verificationId");
    $("#verificationId").val(verificationId);

    var verificationCode = $("#verificationList").find("option:selected").attr("verificationCode");
    $("#verificationCode").val(verificationCode);

    var verificationAmount = $("#verificationList").find("option:selected").attr("verificationAmount");


    var contractId = $("#verificationList").find("option:selected").attr("contractId");
    $("#contractId").val(contractId);

    if(contractId!=''){
        //调用合同接口，查询供应商
        $('#main-content').postUrl(Main.contextPath + "/SSC11308/getContractList",{"contractId":contractId},function(data){
                    if(data!=null){
                        if(verificationAmount<0){
                            //如果是负数，应该是乙方支付甲方
                            $("#supplierId").val(data[0].purchaserId);
                            $("#supplierName").val(data[0].purchaserName);
                            $("#purchaserId").val( data[0].supplierId);
                            $("#purchaserName").val(data[0].supplierName);
                            $("#moneyFlag").val(false);
                        }else{
                            //如果是正数，应该是甲方支付乙方
                            $("#supplierId").val(data[0].supplierId);
                            $("#supplierName").val(data[0].supplierName);
                            $("#purchaserId").val(data[0].purchaserId);
                            $("#purchaserName").val(data[0].purchaserName);
                        }
                    }

                }, {refreshHtml: false});
    }

    $("#verificationAmount").val(Math.abs(verificationAmount));

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
    var paymentType = $("#paymentType").val();
    if(paymentType==0){
        hideDelivery();
        hideVerification();
    }else if(paymentType==1){
        hideContract();
        hideVerification();
    }else if(paymentType==2){
        hideContract();
        hideDelivery();
    }
    SSC1130802.init();
})