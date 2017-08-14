/**
 *
 * Created ssc wu_honglei on 16/8/9.
 */


var SSC1130801 = {
    formId:"SSC1130801Form",
    init: function () {
        this.bindSaveButton();
        this.bindShowReEpInfo();
        this.bindShowPEpInfo();
        this.checkFreightSettleMethod();
        this.bindSelectChange();
    },
    bindSaveButton:function(){
        $("#SSC1130801_SAVE" ).click(function(){
            if(!SSC1130801.checkSSC1130801Data()){
                return;
            }
            $.alertMessage.confirm("你确定要保存当前数据吗？",function () {
                $.alertMessage.close();
                formData = getFormData($("#" + SSC1130801.formId));
                $('#main-content').postUrl(Main.contextPath + "/SSC11308/saveOrModifyPaymentInfo",formData,
                    function (data) {
                        $.pdialog.close("paymentInfoDialog");
                        $("#headBreadCrumb").hide();
                    }
                );
            });
        });

    },
    bindShowReEpInfo : function () {
        $("#chooseReEpInfo").click(function () {
            $.pdialog.open("选择企业信息", Main.contextPath + "/SSC1130803/init", {width: 1200, height: 500}, {
                "supplierInputId":"receiving",
                "bankInputId":"receivingBank",
                "bankAccountInputId":"receivingAccount"
            },"chooseEpDialog");
        })

    },

    bindShowPEpInfo : function () {
        $("#choosePEpInfo").click(function () {
            $.pdialog.open("选择企业信息", Main.contextPath + "/SSC1130803/init", {width: 1200, height: 500}, {
                "supplierInputId":"payer",
                "bankInputId":"payerBank",
                "bankAccountInputId":"paterAccount"
            },"chooseEpDialog");
        })
    },
    checkSSC1130801Data: function () {
        var number = /^\d{0,18}(\.\d{1,2})?$/;

        var amountOP = $("#amountOP").val();
        var receiving = $("#receiving").val();
        var receivingBank = $("#receivingBank").val();
        var receivingAccount = $("#receivingAccount").val();
        var payer = $("#payer").val();
        var payerBank = $("#payerBank").val();
        var paterAccount = $("#paterAccount").val();
        var remarkOP = $("#remarkOP").val();

        var amount = $("#amount").val();
        var paymentRequestId = $("#paymentRequestId").val();


        if(!SSCCommon.isMoney(amountOP)){
            $.alertMessage.info("支付金额不能为0或空，且格式为：整数位最多15位，小数位最多2位！");
            return false;
        }


        //获得付款单下，已经支付金额
        $('#main-content').postUrl(Main.contextPath + "/SSC1130801/search",{
            "paymentRequestId":paymentRequestId,
            "delFlg":0
        },function(data){
            if(data!=null){
                data = data.data;
                var infoAmount=0.00;
                for(var i=0;i<data.length;i++ ){
                    infoAmount = SSCCommon.add(infoAmount,parseFloat(data[i].amount));
                }
                var thisAmount = SSCCommon.subtract(amount,infoAmount);
                if(parseFloat(amountOP)>parseFloat(thisAmount)){
                    $.alertMessage.info("支付金额不能超过"+SSCCommon.formatMoney(thisAmount) + "(元)!");
                    return false;
                }
            }

        }, {refreshHtml: false});



        if(receiving==""){
            $.alertMessage.info("受款单位全称不能为空!");
            return false;
        }
        if(receiving.length>=100){
            $.alertMessage.info("受款单位全称字数不能超过100!");
            return false;
        }

        if(receivingBank.length>=100){
            $.alertMessage.info("受款银行字数不能超过100!");
            return false;
        }
        if(receivingAccount==""){
            $.alertMessage.info("受款银行账号不能为空!");
            return false;
        }
        if(receivingAccount.length>=100){
            $.alertMessage.info(" 受款银行账号字数不能超过100");
            return false;
        }

        if(payer==""){
            $.alertMessage.info("付款单位全称不能为空!");
            return false;
        }

        if(payer.length>=100){
            $.alertMessage.info("付款单位全称字数不能超过100");
            return false;
        }

        if(payerBank.length>=100){
            $.alertMessage.info("支付银行字数不能超过100");
            return false;
        }
        if(paterAccount.length>=100){
            $.alertMessage.info("支付银行账号字数不能超过100");
            return false;
        }
        if(remarkOP.length>=100){
            $.alertMessage.info("备注字数不能超过100");
            return false;
        }
        return true;

    },
    checkFreightSettleMethod:function(){
        var deliveryId=$("#deliveryId0801").val();
        var type=$("#paymentType").val();
        if(type==1){
            //发货订单id不为空时，传入发货订单id,判断返回运费结算方式是否是到岸价（1），是到岸价就将运费单选框置灰
            if(deliveryId!=null&&deliveryId!=""){
                //获得运费结算方式
                $('#main-content').postUrl(Main.contextPath + "/SSC11308/checkFreightSettleMethod",{
                    "deliveryId":deliveryId
                },function(data){
                    if(data!=null){
                        if(data==1){
                            document.all["freight"].disabled = true;
                        }
                    }
                }, {refreshHtml: false});
            }
        }else{
            $("#subjectId").html('');
            $("#subjectValueId").html('');
        }

    },
    bindSelectChange:function(){
        $('#paidType').change(function(){
            var index=window.document.getElementById("paidType").selectedIndex;
            var selectVal=window.document.getElementById("paidType").options[index].value;
            if(selectVal!=2){
                document.getElementById("payerBank").disabled=true;
                document.getElementById("receivingBank").disabled=true;
                document.getElementById("payerBank").value="";
                document.getElementById("receivingBank").value="";
            }
            if(selectVal==2){
                document.getElementById("payerBank").disabled=false;
                document.getElementById("receivingBank").disabled=false;
            }
        })
    }
}


$(document).ready(function () {
    // 初始化调用
    SSC1130801.init();

});

