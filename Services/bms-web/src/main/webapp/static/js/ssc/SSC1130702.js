/**
 * Created by wu_honglei on 2016/7/5.
 */
var $List_Grid;
var SSC1130702 = {
    saveButtonId:"SSC1130702_SAVE",
    formId:"SSC1130702Form",
    init : function() {
        this.bindSaveButton();
        this.bindPaymentTypeRadio();
    },
    // 绑定新增按钮
    bindSaveButton: function(){
        $("#" + SSC1130702.saveButtonId).click(function () {
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    formData = getFormData($("#" + SSC1130702.formId));
                    $('#main-content').postUrl(Main.contextPath + "/SSC11307/save",formData,
                    function () {
                        $List_Grid.fnDraw();
                    });
                });
        });

    },

    bindPaymentTypeRadio:function(){
        $("input[name='paymentType']").click(function(){
            if($(this).val()==1){
                $("#firstMoneyTr1").show();
                $("#firstMoneyTr2").show();
                $("#firstMoneyTr3").show();
            }else if($(this).val()==0){
                $("#firstMoneyTr1").hide();
                $("#firstMoneyTr2").hide();
                $("#firstMoneyTr3").hide();
                $("#deliveryTotalAmount").val("");
                $("#transportMoney").val("");
                $("#packageTotalAmount").val("");
            }
        });
    }

}
$(document).ready(function(){
    SSC1130702.init();
})