/**
 * 买手店修改JS
 *
 * @author cx
 */
var BS2101105 = {
    formId: "BS2101105Form",
    updateButtonId: "BS2101105_UPDATE",

    init: function () {
        $("#slSellerPage").postUrl(Main.contextPath + "/BS2101106/init", {
            slIdcard:slIdcard,
            slAccount:slAccount,
            slCode:slCode,
            slCodeDis:slCodeDis,
            slConFlg:slConFlg,
            flag1:flag1,
            shopQua:shopQua,
            agentType:agentType,
            distribution:distribution,
            demesne:demesne,
            registerSource:registerSource,
            memo1:memo1,
            memo2:memo2,
            memo3:memo3,
            memo4:memo4,
            memo5:memo5,
            memo6:memo6,
            memo7:memo7,
            memo9:memo9,
            memo15:memo15,
            slAddress:slAddress,
            lat:lat,
            lon:lon,
            lgcsAreaCode:lgcsAreaCode,
            provinceCode:provinceCode,
            cityCode:cityCode,
            districtCode:districtCode,
            flagNum:flagNum,
            memo8:memo8,
            lgcsAreaName:lgcsAreaName
        });
        $("#slShopInfo").postUrl(Main.contextPath + "/BS2101110/init", {
            slCode:slCode,
            shopId:shopId,
            shopName: shopName,
            shopLogo: shopLogo,
            managingCharact1: $("#managingCharact1").text(),
            managingCharact2: $("#managingCharact2").text(),
            managingCharact3: $("#managingCharact3").text(),
            flagNum:flagNum
        });
        this.bindSavebutton();
    },

    bindSavebutton : function() {
        $("#" + BS2101105.updateButtonId).click(function() {
            var type ="";
            if(flagNum==1){
                //flagNum 为新增
                type=1;
            }else{
                type=2;
            }
            $("#bsAccount").postUrl(Main.contextPath + "/BS2101105/checkBuyerByTel", {
                slTel:$("input[name='slTel']").val(),
                slAccount:$("input[name='slAccount']").val(),
                slContact:$("input[name='slContact']").val(),
                type:type
            },function(data){
                if(data ==0){
                    BS2101105.saveData();
                }else if(data=='-1'){
                    $.alertMessage.info("该买手账号与其他买手账号或名称或手机相同！");
                }else if(data == '-2'){
                    $.alertMessage.info("该买手名称与其他买手账号或名称或手机相同！");
                }else if(data == '-3'){
                    $.alertMessage.info("该买手手机与其他买手账号或名称或手机相同！");
                }else if(data == '-4'){
                    $.alertMessage.info("该买手手机号已被其他冻品管家使用！");
                }
            }, {refreshHtml: false});

        });
    },
    saveData : function() {
        var validator = mainValidation($("#" + BS2101105.formId));
        var isValid = validator.form();
        if(!accountTest() && flagNum=='1'){
            return;
        }
        if(!pwdTest()){
            return;
        }
        if(!slContactTest()){
            return;
        }
        if(!phone()){
            return;
        }
        if (isValid) {
            formData = getFormData($("#" + BS2101105.formId));
            var $uploadFile = $("#BS2101105Form");
            $.core.uploadForm($uploadFile, true);
        };
    }
}
$(document).ready(function () {
    // 初始化调用
    BS2101105.init();
    $(function () {
        $("a").each(function () {
            $(this).attr("href",encodeURI($(this).attr("href")));
        })
    })
});

function accountTest(){
    var name = $('#slAccount1').val();
    if(name==null || name==""){
        $('#account_sp').html('<font style="color:red">账号不能为空！</font>');
        return false;
    }
    if(name.length < 6){
        $('#account_sp').html('<font style="color:red">账号长度应大于6个字符！</font>');
        return false;
    }
    if(name.length > 20){
        $('#account_sp').html('<font style="color:red">账号长度过长！</font>');
        return false;
    }
    if(/^[\\w\\-－＿[0-9]\u4e00-\u9fa5\uFF21-\uFF3A\uFF41-\uFF5A]+$/.test(name)){
        $('#account_sp').html('<font style="color:red">账号输入不合法！</font>');
        return false;
    }
    $('#account_sp').html('');
    return true;
}

function slAccountTest(){
    var name = $('#slAccount').val();
    if(name==null || name==""){
        $('#slAccount_sp').html('<font style="color:red">账号不能为空！</font>');
        return false;
    }
    if(name.length < 6){
        $('#slAccount_sp').html('<font style="color:red">账号长度应大于6个字符！</font>');
        return false;
    }
    if(name.length > 20){
        $('#slAccount_sp').html('<font style="color:red">账号长度过长！</font>');
        return false;
    }
    if(/^[\\w\\-－＿[0-9]\u4e00-\u9fa5\uFF21-\uFF3A\uFF41-\uFF5A]+$/.test(name)){
        $('#slAccount_sp').html('<font style="color:red">账号输入不合法()！</font>');
        return false;
    }
    $('#slAccount_sp').html('');
    return true;
}

function pwdTest(){
    var pwd = $('#accountPsd').val();
    if(pwd==null || pwd==""){
        $('#pwd_sp').html('<font style="color:red">密码不能为空！</font>');
        return false;
    }
    if(pwd.length<6){
        $('#pwd_sp').html('<font style="color:red">密码长度应大于6个字符！</font>');
        return false;
    }
    if(!/^[A-Za-z0-9]+$/.test(pwd)){
        $('#pwd_sp').html('<font style="color:red">密码输入不合法(6-11位，只包含英文、数字、下划线)！</font>');
        return false;
    }
    $('#pwd_sp').html('');
    return true;
}

function slContactTest(){
    var slContact = $('#slContact').val();
    if(slContact==null || slContact==""){
        $('#slContact_sp').html('<font style="color:red">用户姓名不能为空！</font>');
        return false;
    }
    $('#slContact_sp').html('');
    return true;
}

function phone(){
    var slTel =$("input[name='slTel']").val();
    if(slTel==null || slTel==""){
        $('#phone_sp').html('<font style="color:red">手机号码不能为空！</font>');
        return false;
    }
    if(!(/^1[3|4|5|7|8]\d{9}$/.test(slTel))){
        $('#phone_sp').html('<font style="color:red">请输入正确的手机号！</font>');
        return false;
    }
    $('#phone_sp').html('');
    return true;
}