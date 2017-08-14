var SSC1132101 = {
    listGrid : null,
    init: function () {
        SSC1132101.listGrid = $("#SSC1132101_list_grid").grid({
        })
        this.bindConfirmButton();
    },
    bindConfirmButton:function(){
        $("#SSC1132101_CONFIRM" ).click(function(){

            var selectVerificationOne = SSC1132101.listGrid.getChoiceOne();

            var verificationCodeId = $("#verificationCodeId").val();
            var verificationAmountId = $("#verificationAmountId").val();

            if(verificationCodeId!=""&&verificationCodeId!=null&&verificationCodeId!=undefined){
                $("#"+verificationCodeId).val(selectVerificationOne.verificationCode);
                loadVerification();
            }
            $.pdialog.close("chooseVerificationDialog");

        });
    }

}


function loadVerification(){
    var selectVerificationOne = SSC1132101.listGrid.getChoiceOne();

    var verificationId = selectVerificationOne.verificationId;
    $("#verificationId").val(verificationId);

    var verificationCode = selectVerificationOne.verificationCode;
    $("#verificationCode").val(verificationCode);

    var verificationAmount = selectVerificationOne.verificationAmount;


    var contractId = selectVerificationOne.contractId;
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



$(document).ready(function () {
    // 初始化调用
    SSC1132101.init();

});


