/**
 * Created by wu_honglei on 2016/7/5.
 */
var $List_Grid;
var SSC11308 = {
    addButtonId:"SSC11308_ADD",
    approvalButtonId:"SSC11308_APPROVAL",
    auditingButtonId:"SSC11308_AUDITING",
    saveButtonId:"SSC11308_SAVE",
    accountingDate:"#accountingDateStr",
    formId:"SSC11308Form",
    init : function() {
        $List_Grid = $("#SSC11308_list_grid").grid({
            actionHandler: SSC11308.actionHandler,
            linkHandler: SSC11308.linkHandler

        })
        this.closeDate();
        this.bindSaveButton();
        this.bindDatePicker(SSC11308.accountingDate);
        this.bindAddButton();
        this.bindApprovalButton();
        this.bindAuditingButton();
        this.hiddenRadio();
        this.bindShowEpInfo();
        this.bindContractDetailLink();
        this.bindDeliveryDetailLink();
        this.bindInitFormatMoney();
        this.bindInitPaymentType();
        this.bindInputBlur();
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        var auditingStatus = $("#auditingStatus").val();

        //已支付的,无法再次支付
        var status = rowdata.status;
        if(status==2){
            $.alertMessage.info("已支付,无法操作!");
            return;
        }
        var paymentRequestId = $("#paymentRequestId").val();
        var type = $("#type").val();

        if(coltype=="save"){
            if(auditingStatus!= 2 ){
                $.alertMessage.info("未审批/审核,无法操作!");
                return;
            }

            $.alertMessage.confirm("你确定要支付吗？",function () {

                var payAmount = rowdata.amount;           //本次支付金额
                //支付
                $('#main-content').postUrl(Main.contextPath + "/SSC11308/saveOrModifyPaymentInfo",{
                    paymentId:rowdata.paymentId,
                    status:2,
                    paymentRequestId:paymentRequestId,
                    amount:payAmount,
                    type:type,
                    ver:rowdata.ver
                },function (data) {
                    $.alertMessage.info("操作成功!");
                    $("#headBreadCrumb").hide();
                });
            })
        }
        if(coltype=="delete"){
            //删除

            $.alertMessage.confirm("确定删除这条数据吗？",function () {
                $('#main-content').postUrl(Main.contextPath + "/SSC11308/saveOrModifyPaymentInfo",{
                    paymentRequestId:paymentRequestId,
                    paymentId:rowdata.paymentId,
                    delFlg:1,
                    ver:rowdata.ver,
                    type:type
                },function () {
                    $.alertMessage.info("操作成功!");
                    $("#headBreadCrumb").hide();
                });
            })
        }
    },

    hiddenRadio:function(){
        var  auditStatus = $("#auditingStatus").val();
        if (auditStatus == "0"){
            $("input[name='auditingFlag']").attr("disabled","disabled");
            $("input[name='auditingRemark']").attr("disabled","disabled");

        } else if(auditStatus == "1"){
            $("input[name='approvalFlag']").attr("disabled","disabled");
            $("input[name='approvalRemark']").attr("disabled","disabled");
        }else{
            $("input[name='auditingFlag']").attr("disabled","disabled");
            $("input[name='approvalFlag']").attr("disabled","disabled");
            $("input[name='auditingRemark']").attr("disabled","disabled");
            $("input[name='approvalRemark']").attr("disabled","disabled");
        }
    },

    bindDatePicker : function(time){
        $(time).datepicker({
            showButtonPanel: true,
            dateFormat:'yy-mm-dd',
            changeMonth: true,
            closeText: "清除",
            changeYear: true
        });
        $(time).attr("readonly","readonly");

    },

    bindShowEpInfo:function(){
        $("#chooseEpInfo").click(function () {
            $.pdialog.open("选择企业信息", Main.contextPath + "/SSC1130803/init", {width: 1200, height: 500}, {
                "supplierInputId":"supplierName",
                "bankInputId":"supplierBank",
                "bankAccountInputId":"supplierAccount"
            },"chooseEpDialog");
        })
    },

    // 绑定新增按钮
    bindSaveButton: function(){
        $("#" + SSC11308.saveButtonId).click(function () {

            //var auditingStatus = $("#auditingStatus").val();
            //if(auditingStatus=="2"){
            //    $.alertMessage.info("已审核,无法操作!");
            //    return;
            //}
            //if(auditingStatus=="1"){
            //    $.alertMessage.info("已审批,无法操作!");
            //    return;
            //}

            if(!SSC11308.bindCheckSSC11308Data()){
                return;
            }
            $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                $.alertMessage.close();
                formData = getFormData($("#" + SSC11308.formId));
                if(auditingStatus=="0"||auditingStatus==""||auditingStatus==null||auditingStatus==undefined||auditingStatus=="3"||auditingStatus=="4"){
                    formData.approvalFlag="0";
                    formData.auditingFlag="0";
                }
                $('#main-content').postUrl(Main.contextPath + "/SSC11308/saveOrModifyPaymentRequest", formData ,function(){
                    $("#headBreadCrumb").hide();
                    $List_Grid.fnDraw();
                    $.alertMessage.info("操作成功!");
                });

            });
        });
    },

    //添加付款记录
    bindAddButton: function(){
        $("#" + SSC11308.addButtonId).click(function () {
            var paymentRequestId = $("#paymentRequestId").val();
            if(paymentRequestId== ""){
                $.alertMessage.info("数据未保存,无法添加支付管控单！");
                return;
            }
            var auditingStatus = $("#auditingStatus").val();
            if(auditingStatus=="2"){
                $.alertMessage.info("已审核,无法操作!");
                return;
            }
            var deliveryId = $("#deliveryId").val();
            var paymentType = $("#paymentType").val();

            var allowApplyAmount = $("#allowApplyAmount").val();

            if(allowApplyAmount <= 0){
                $.alertMessage.info("支付管控记录已添加完成!");
                return;
            }

            $.pdialog.open("新增支付管控单", Main.contextPath + "/SSC1130801/init", {width: 1000, height: 300}, {
                paymentRequestId:paymentRequestId,
                deliveryId:deliveryId,
                paymentType:paymentType,
                paymentRequestCode:$("#paymentRequestCode").val(),
                receiving:$("#supplierName").val(),
                receivingBank :$("#supplierBank").val(),
                receivingAccount:$("#supplierAccount").val(),
                type:$("#type").val()
            },"paymentInfoDialog");
        });
    },

    //审批
    bindApprovalButton: function(){
        $("#" + SSC11308.approvalButtonId).click(function () {
            var paymentRequestId = $("#paymentRequestId").val();
            var type = $("#type").val();
            var auditingStatus = $("#auditingStatus").val();
            var allData = $List_Grid.fnGetData();

            var approvalRemark = $("#approvalRemark").val();
            if(approvalRemark.length>100){
                $.alertMessage.info("审批备注不能超过100个字!");
                return false;
            }

            if(auditingStatus=="1"){
                $.alertMessage.info("不要重复审批");
                return;
            }

            if(auditingStatus=="2"){
                $.alertMessage.info("已审核,无法审批");
                return;
            }
            if(auditingStatus=="3"){
                $.alertMessage.info("审批未通过,等待修改后审批");
                return;
            }
            if(auditingStatus=="4"){
                $.alertMessage.info("审核未通过,等待修改后审批");
                return;
            }
            if(allData.length==0){
                $.alertMessage.info("请添加付款记录");
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/SSC11308/approvalPayment",{
                paymentRequestId:paymentRequestId,
                type:type,
                ver:$("#ver").val(),
                approvalFlag:$("input[name='approvalFlag']:checked").val(),
                approvalRemark:$("#approvalRemark").val(),
                flag:0
            },function (){
                $("#headBreadCrumb").hide();
                $List_Grid.fnDraw();
                $.alertMessage.info("操作成功!");
            });

        });
    },

    //审核
    bindAuditingButton:function(){
        $("#" + SSC11308.auditingButtonId).click(function () {
            var paymentRequestId = $("#paymentRequestId").val();
            var type = $("#type").val();
            var auditingStatus = $("#auditingStatus").val();
            var allData = $List_Grid.fnGetData();

            var auditingRemark = $("#auditingRemark").val();
            if(auditingRemark.length>100){
                $.alertMessage.info("审核备注不能超过100个字!");
                return false;
            }

            if(auditingStatus=="0"){
                $.alertMessage.info("没有审批,无法审核");
                return;
            }
            if(auditingStatus=="2"){
                $.alertMessage.info("已审核,无法审批");
                return;
            }
            if(auditingStatus=="3"){
                $.alertMessage.info("审批未通过,等待修改后审批");
                return;
            }
            if(auditingStatus=="4"){
                $.alertMessage.info("审核未通过,等待修改后审批");
                return;
            }
            if(auditingStatus=="2"){
                $.alertMessage.info("不要重复审核");
                return;
            }
            if(allData.length==0){
                $.alertMessage.info("请添加付款记录");
                return;
            }

            $('#main-content').postUrl(Main.contextPath + "/SSC11308/approvalPayment",{
                paymentRequestId:paymentRequestId,
                type:type,
                auditingFlag:$("input[name='auditingFlag']:checked").val(),
                auditingRemark:$("#auditingRemark").val(),
                ver:$("#ver").val(),
                flag:1
            },function (){
                $("#headBreadCrumb").hide();
                $List_Grid.fnDraw();
                $.alertMessage.info("操作成功!");
            });

        });
    },

    //绑定合同详情超链接
    bindContractDetailLink: function () {
        $("#showContractDetail").click(function () {
            Main.detailWindow(Main.contextPath + "/SSC11304/show",  {
                "contractId":$("#contractId").val(),
                "paymentRequestId":$("#paymentRequestId").val(),
                "type":$("#type").val()
            }, "合同详细");
        })
    },

    //绑定发货订单详情超链接
    bindDeliveryDetailLink: function () {
        $("#showDeliveryDetail").click(function () {
            Main.detailWindow(Main.contextPath + "/SSC11306/show/",  {
                "deliveryId":$("#deliveryId").val(),
                "paymentRequestDetailId":$("#paymentRequestId").val()
            }, "发货订单详细");
        })
    },

    //千分位显示金钱
    bindInitFormatMoney:function(){
        var auditingStatus = $("#auditingStatus").val();
        var payedStatus = $("#payedStatus").val();
        if(auditingStatus == 2 || payedStatus == 9 ){

            //订单合同总金额(元)
            var totalAmountTd = $("#paymentInfoTable tr:eq(1) td:eq(1)");
            if(totalAmountTd.text()!=""){
                $(totalAmountTd).text(SSC11308.formatMoney(totalAmountTd.text()));
            }

            //订单合同预付款金额(元)
            var firstAmountTd = $("#paymentInfoTable tr:eq(1) td:eq(2)");
            if(firstAmountTd.text()!=""){
                $(firstAmountTd).text(SSC11308.formatMoney(firstAmountTd.text()));
            }

            //本次发货订单总金额(元)
            var  deliverAmountTd = $("#paymentInfoTable tr:eq(2) td:eq(1)");
            if(deliverAmountTd.text()!=""){
                $(deliverAmountTd).text(SSC11308.formatMoney(deliverAmountTd.text()));
            }

            //预付款按比例已支付金额(元)
            var  paidDownPaymentAmountTd = $("#paymentInfoTable tr:eq(2) td:eq(2)");
            if(paidDownPaymentAmountTd.text()!=""){
                $(paidDownPaymentAmountTd).text(SSC11308.formatMoney(paidDownPaymentAmountTd.text()));
            }

            //本次运输费用实际发生额(元)
            var  transportAmountTd = $("#paymentInfoTable tr:eq(3) td:eq(1)");
            if(transportAmountTd.text()!=""){
                $(transportAmountTd).text(SSC11308.formatMoney(transportAmountTd.text()));
            }

            //包材使用费实际发生额(元)
            var  packageAmountTd = $("#paymentInfoTable tr:eq(3) td:eq(2)");
            if(packageAmountTd.text()!=""){
                $(packageAmountTd).text(SSC11308.formatMoney(packageAmountTd.text()));
            }

            //核销金额(元)
            var  verificationAmountTd = $("#paymentInfoTable tr:eq(4) td:eq(1)");
            if(verificationAmountTd.text()!=""){
                $(verificationAmountTd).text(SSC11308.formatMoney(verificationAmountTd.text()));
            }

            //本次申请金额(元)
            var  amountTd = $("#paymentInfoTable tr:eq(6) td:eq(0)");
            if(amountTd.text()!=""){
                $(amountTd).text(SSC11308.formatMoney(amountTd.text()));
            }
        }
    },

    //保存时，校验数据
    bindCheckSSC11308Data:function(){
        var paymentRequestName = $("#paymentRequestName").val();
        if(paymentRequestName==""){
            $.alertMessage.info("付款申请名称不能为空!");
            return false;
        }
        if(paymentRequestName.length>100){
            $.alertMessage.info("付款申请长度不能超过100个字!");
            return false;
        }


        var supplierName = $("#supplierName").val();
        if(supplierName==""){
            $.alertMessage.info("受款主体不能为空!");
            return false;
        }
        if(supplierName.length>100){
            $.alertMessage.info("受款主体长度不能超过100个字!");
            return false;
        }

        var supplierBank = $("#supplierBank").val();
        if(supplierBank==""){
            $.alertMessage.info("开户银行不能为空!");
            return false;
        }
        if(supplierBank.length>100){
            $.alertMessage.info("开户银行长度不能超过100个字!");
            return false;
        }

        var supplierAccount = $("#supplierAccount").val();
        if(supplierAccount==""){
            $.alertMessage.info("银行账号不能为空!");
            return false;
        }
        if(supplierAccount.length>100){
            $.alertMessage.info("银行账号长度不能超过100个字!");
            return false;
        }


        var amount = $("#amount").val();
        if(!SSCCommon.MONEY_REG.test(amount)){
            $.alertMessage.info("本次申请金额不能为空或0，且格式为：整数位最多15位，小数位最多2位！");
            return false;
        }


        var applicant = $("#applicant").val();
        if(applicant==""){
            $.alertMessage.info("请款发起人不能为空!");
            return false;
        }
        if(applicant.length>20){
            $.alertMessage.info("请款人不能超过20个字!");
            return false;
        }

        var paymentMethod = $("#paymentMethod").val();
        if(paymentMethod.length>30){
            $.alertMessage.info("付款约定不能超过30个字!");
            return false;
        }

        var remark = $("#remark").val();
        if(remark.length>30){
            $.alertMessage.info("备注不能超过30个字!");
            return false;
        }

        var accountingDateStr = $("#accountingDateStr").val();
        if(accountingDateStr==""){
            $.alertMessage.info("请款时间不能为空!");
            return false;
        }

        var paymentType = $("input[name='paymentTypeShow']:checked").val();


        if(paymentType==0){
            //预付款
            var contractTotalAmount = $("#contractTotalAmount").val();
            if(!SSCCommon.isMoney(contractTotalAmount)){
                $.alertMessage.info("订单合同总金额不能为0或空，且格式为：整数位最多15位，小数位最多2位！");
                return false;
            }

            var result = SSCCommon.subtract($("#amount").val(),$("#contractFirstAmount").val());
            if(result>0){
                $.alertMessage.info("本次申请金额不能大于订单合同预付款金额!");
                return false;
            }

        }else if(paymentType==1){
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

            var result = SSCCommon.subtract($("#deliverTotalAmount").val(),$("#paidDownPaymentPercentage").val());
            if(parseInt($("#amount").val())>result){
                var maxAlertMoney = SSCCommon.subtract($("#deliverTotalAmount").val(),$("#paidDownPaymentPercentage").val());
                parseFloat(maxAlertMoney).toFixed(2)
                $.alertMessage.info("本次申请金额不能超过"+SSCCommon.formatMoney(maxAlertMoney)+"元!");
                return false;
            }

        }else if(paymentType==2){
            var verificationAmount = $("#verificationAmount").val();
            if(!SSCCommon.isMoney(verificationAmount)){
                $.alertMessage.info("核销金额不能为0或空，且格式为：整数位最多15位，小数位最多2位！");
                return false;
            }

            var result = SSCCommon.subtract($("#amount").val(),$("#verificationAmount").val());
            if(result>0){
                $.alertMessage.info("本次申请金额不能大于核销金额!");
                return false;
            }

        }

        return true;
    },

    //调用共同类 格式化金钱
    formatMoney:function(num){
        if(num!='' && num!=undefined ){
            return SSCCommon.formatMoney(num);
        }
    },

    //初始化页面时，隐藏发货订单相关信息
    hideDelivery:function(){
        $("#deliverTotalAmountShow").attr("disabled",true);
        $("#transportAmountShow").attr("disabled",true);
        $("#packageAmountShow").attr("disabled",true);
    },

    //初始化页面时，隐藏合同相关信息
    hideContract:function(){
        $("#contractTotalAmountShow").attr("disabled",true);
        $("#contractFirstAmountShow").attr("disabled",true);
    },

    //初始化页面时，隐藏核销相关信息
    hideVerification: function () {
        $("#verificationAmountShow").attr("disabled",true);
    },

    //格式化合同相关金额
    formatContractMoney:function(){
        var  contractTotalAmountShow = $("#contractTotalAmount").val();
        if(contractTotalAmountShow!=''){
            $("#contractTotalAmountShow").val(SSC11308.formatMoney(contractTotalAmountShow));
        }
        var  contractFirstAmountShow = $("#contractFirstAmount").val();
        if(contractFirstAmountShow!=''){
            $("#contractFirstAmountShow").val(SSC11308.formatMoney(contractFirstAmountShow));
        }
    },

    //格式化发货订单相关金额
    formatDeliveryMoney:function(){

        var  deliverTotalAmountShow= $("#deliverTotalAmount").val();
        if(deliverTotalAmountShow!=''){
            $("#deliverTotalAmountShow").val(SSC11308.formatMoney(deliverTotalAmountShow));
        }
        var  paidDownPaymentPercentageShowFormat= $("#paidDownPaymentPercentage").val();
        if(paidDownPaymentPercentageShowFormat!=''){
            $("#paidDownPaymentPercentageShow").val(SSC11308.formatMoney(paidDownPaymentPercentageShowFormat));
        }

        var  transportAmountShow= $("#transportAmount").val();
        if(transportAmountShow!=''){
            $("#transportAmountShow").val(SSC11308.formatMoney(transportAmountShow));
        }

        var  packageAmountShow= $("#packageAmount").val();
        if(packageAmountShow!=''){
            $("#packageAmountShow").val(SSC11308.formatMoney(packageAmountShow));
        }
    },

    //格式化核销相关金额
    formatVerificationMoney:function(){
        var  verificationAmountShow= $("#verificationAmount").val();
        if(verificationAmountShow!=''){
            $("#verificationAmountShow").val(SSC11308.formatMoney(verificationAmountShow));
        }
    },


    //根据付款类型 隐藏显示付款按钮
    bindInitPaymentType:function(){

        var amountShow = $("#amount").val();
        if(amountShow!=''){
            $("#amountShow").val(SSC11308.formatMoney(amountShow));
        }
        // 初始化调用
        var paymentType = $("#paymentType").val();
        if(paymentType==0){//首付款
            SSC11308.hideDelivery();
            SSC11308.hideVerification();
            SSC11308.formatContractMoney();
        }else if(paymentType==1){//进度款
            SSC11308.hideContract();
            SSC11308.hideVerification();
            SSC11308.formatDeliveryMoney();
        }else if(paymentType==2){//余款
            SSC11308.hideDelivery();
            SSC11308.hideContract();
            SSC11308.formatVerificationMoney()
        }
        var allowApplyAmount = $("#allowApplyAmount").val();
        if(allowApplyAmount!=''){
            $("#allowApplyAmountLabel").html(SSC11308.formatMoney(allowApplyAmount));
        }else{
            $("#allowApplyAmountLabel").html(0.00);
        }
        var totalApplyAmount = $("#totalApplyAmount").val();
        if(totalApplyAmount!=''){
            $("#totalApplyAmountLabel").html(SSC11308.formatMoney(totalApplyAmount));
        }
    },


    bindInputBlur:function(){

        $("#amountShow").blur(function(){
            SSC11308.bindBlur("amountShow","amount")
        })

        var paymentType = $("#paymentType").val();
        if(paymentType==0){
           //首付款
            $("#contractTotalAmountShow").blur(function(){
                SSC11308.bindBlur("contractTotalAmountShow","contractTotalAmount")
            });
            $("#contractFirstAmountShow").blur(function(){
                SSC11308.bindBlur("contractFirstAmountShow","contractFirstAmount")
            });

        }else if(paymentType==1){
            //进度款
            $("#deliverTotalAmountShow").blur(function(){
                SSC11308.bindBlur("deliverTotalAmountShow","deliverTotalAmount")
            });

            $("#transportAmountShow").blur(function(){
                SSC11308.bindBlur("transportAmountShow","transportAmount")
            });

            $("#packageAmountShow").blur(function(){
                SSC11308.bindBlur("packageAmountShow","packageAmount")
            });


        }else if(paymentType==2){
            //余款
            $("#verificationAmountShow").blur(function(){
                SSC11308.bindBlur("verificationAmountShow","verificationAmount")
            })
        }
    },

    bindBlur:function(showInputId,hiddenInputId){
        var money =  $("#"+showInputId).val()
        if(money!=''){
            $("#"+showInputId).val(SSCCommon.formatMoney(money))
            money = SSCCommon.clearComma(money);
            $("#"+hiddenInputId).val(money)
        }
    }
}



$(document).ready(function(){
    SSC11308.init();
})

