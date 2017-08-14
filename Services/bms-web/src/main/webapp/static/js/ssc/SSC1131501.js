/**
 * Created by liu_yan2 on 2016/7/25.
 */

var SSC1131501 = {
    formId : "SSC1131501Form",
    saveButtonId : "SSC1131501_SAVE",
    pdCodes : [],
    productPlanBoxes:[],
    init: function(){
        this.bindSavebutton();
        this.setTableWidth();
    },
    setTableWidth: function() {
        var width = $("#pdialogId .group-accordion").width();
        $("#pdialogId").find("table").each(function(index){
            $(this).width(width);
        });
    },
    bindSavebutton: function(){
        $("#" + SSC1131501.saveButtonId).click(function() {
            // 验证车辆信息填写，待装车产品是否正确
            if(SSC1131501.checkVehicle() && SSC1131501.checkBox()){
                var formData = getFormData($("#" +SSC1131501.formId));
                if(!SSCCommon.PHONE_REG.test($("#driverTel").val())){
                    $.alertMessage.info("请输入正确的运输单位执行人联系方式");
                    return;
                }

                $.alertMessage.confirm("你确定要保存当前数据吗？", function() {
                    $.alertMessage.close();
                    $('#main-content').postUrl( $("#" + SSC1131501.formId).attr("action"), {
                        pdCodesStr: SSC1131501.pdCodes.join(","),
                        productPlanBoxesStr: SSC1131501.productPlanBoxes.join(","),
                        driverName: formData["driverName"],
                        driverTel: formData["driverTel"],
                        licPlateNumber: formData["licPlateNumber"],
                        vehicleType: formData["vehicleType"],
                        deliveryConfirmId: $('#deliveryConfirmId').val()
                    }, function(data) {
                        if(data.status == "S") {
                            Main.detailWindow(Main.contextPath + "/SSC11317/init/1", {deliveryPreIntoId: data.result.deliveryPreIntoId}, "预入库通知单详细");
                            //$.alertMessage.info("操作成功!");
                            $.pdialog.close();
                            /*$('#main-content').postUrl(Main.contextPath + '/SSC11317/init/1', {deliveryPreIntoId: data.result.deliveryPreIntoId},function(){
                                Main.hlLeftMainMenu("SSC11316");
                            });*/
                        } else {
                            $.alertMessage.warn("请刷新页面再试!");
                        }
                    },{refreshHtml:false});
                });
            }
        });
    },
    checkBox:function(){ //验证填写的箱数
        var $pdCodes = $('input:checkbox[name=pdCode]:checked');
        var $productPlanBoxes = []; //不为空的计划箱数
        $('input[name=productPlanBox]').each(
            function(){
                var item = $(this).val().trim();
                if(item.length >0)  {
                    $productPlanBoxes.push(this);
                }
        });
        if($pdCodes.length ==0) {
            $.alertMessage.info("必须选择一个产品！");
            return false;
        } else if($productPlanBoxes.length ==0) {
            $.alertMessage.info("分配箱数不能为空！");
            return false;
        } else if ($productPlanBoxes.length > $pdCodes.length) {
            $.alertMessage.info("请不要只输入分配箱数，不勾选该产品！");
            return false;
        }
        var flag = true;
        $pdCodes.each(function(i){
            var pdCode = $(this).val(); // 产品code
            var productPlanBox = $("#box_"+pdCode).val().trim(); // 产品对应的 箱数
            var maxBox = $("#maxbox_"+pdCode).val(); //待分配产品箱数
            if(!SSC1131501.checkNum(productPlanBox)){// 如果不是数字
                $.alertMessage.info("分配箱数必须是正整数！");
                flag = false;
                e.preventDefault();
            } else if (Number(maxBox) < Number(productPlanBox)) {
                $.alertMessage.info("分配箱数不能大于待分配箱数！");
                flag = false;
                e.preventDefault();
            } else {
                SSC1131501.productPlanBoxes.push(productPlanBox);
                SSC1131501.pdCodes.push(pdCode);
            }
        });
        return flag;
    } ,
    checkNum:function(value){//  验证正整数
        var reg = /^[1-9]\d*$/;
        if(reg.test(value)) {
            return true;
        }else{
            return false;
        }
    },
    checkVehicle:function(){
        var validator = ($("#" + SSC1131501.formId)).validate({
            rules:{
                driverName: {
                    required:true,
                    maxlength:100
                },
                driverTel:{
                    required:true,
                    maxlength:100
                },
                licPlateNumber:{
                    required:true,
                    maxlength:100
                },
                vehicleType:{
                    required:true,
                    maxlength:100
                }
            },
            messages:{
                driverName: {
                    //Modif for Bug#2584 at 2016/09/09 by yy Start
                    required: " <font color='red'>*</font>",
                    //Modif for Bug#2584 at 2016/09/09 by yy End
                    maxlength:"最多输入100个字符"
                },
                driverTel: {
                    //Modif for Bug#2584 at 2016/09/09 by yy Start
                    required: " <font color='red'>*</font>",
                    //Modif for Bug#2584 at 2016/09/09 by yy End
                    maxlength:"最多输入100个字符"
                },
                licPlateNumber: {
                    //Modif for Bug#2584 at 2016/09/09 by yy Start
                    required: " <font color='red'>*</font>",
                    //Modif for Bug#2584 at 2016/09/09 by yy End
                    maxlength:"最多输入100个字符"
                },
                vehicleType:{
                    //Modif for Bug#2584 at 2016/09/09 by yy Start
                    required: " <font color='red'>*</font>",
                    //Modif for Bug#2584 at 2016/09/09 by yy End
                    maxlength:"最多输入100个字符"
                }
            }
        });
        var isValid = validator.form();
        return isValid;
    }
};
$(document).ready(function(){
    SSC1131501.init();
});