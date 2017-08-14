var ResetAccountPwd = {
    ResetAccountPwdGrid: null,
    accountResetButtonId:"ACCOUNT_RESET",
    CancelButtonId:"ACCOUNT_CANCEL",
    formId:"resetAccountFrom",
    initDataGrid: function () {
        this.bindCancelButton();
        this.bindAccountResetButton();
    },
    bindCancelButton:function(){
        $("#"+ResetAccountPwd.CancelButtonId).click(function(){
            $.pdialog.close();
        });
    },
    bindAccountResetButton:function(){
        $("#" + ResetAccountPwd.accountResetButtonId).click(function(){
            if($("#resetAccountFrom").validateForm()){
            var telNewNo= $("#telNewNo").val();
            var regPhone=/^1[34578]\d{9}$/;
             if(!regPhone.test(telNewNo)){
                 $.alertMessage.info("输入的手机号格式错误");
                 return false;
             }
            var formData = getFormData($("#" + ResetAccountPwd.formId));
            var url = Main.contextPath+"/by/baseBuyerBasicInfo/reset/accountPwd/";
            $('#main-content').postUrl(url, formData, function(data){
                if(data =="0"){
                    $.alertMessage.warn("该账号或手机号已存在");
                }else if(data == '-1'){
                    $.alertMessage.warn("该账号已被其他买家名称使用");
                }else{
                    $("#accountName").val($("#accountNewName").val());
                    $("#accountPass").val($("#accountNewPass").val());
                    $("#telNo").val($("#telNewNo").val());
                    $.pdialog.close();
                }
            }, {refreshHtml:false});
            }
        });
    },
    changePassWord:function(){
        var accountVal= $("#accountNewName").val();
        var passWord = accountVal.substring(accountVal.length-8,accountVal.length);
        $("#accountNewPass").val(passWord);
    }
}
$(document).ready(function () {
    // 初始化调用
    ResetAccountPwd.initDataGrid();
});