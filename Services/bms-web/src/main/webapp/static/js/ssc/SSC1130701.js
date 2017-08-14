/**
 * Created by wu_honglei on 2016/7/5.
 */
var $List_Grid;
var SSC1130701 = {
    saveButtonId:"SSC1130701_SAVE",
    formId:"SSC1130701Form",
    init : function() {
        $List_Grid = $("#SSC1130701_list_grid").grid({
            actionHandler:SSC1130701.actionHandler
        })
        this.bindSaveButton();
        this.bindPaymentTypeRadio();
    },

    actionHandler: function (rowdata,coltype,row,col) {
        $('#main-content').postUrl( Main.contextPath + "/SSC1130701/search",{
            "paymentCode":$("#paymentCode").val()
        });
    },
    // 绑定新增按钮
    bindSaveButton: function(){
        $("#" + SSC1130701.saveButtonId).click(function () {

                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    formData = getFormData($("#" + SSC1130701.formId));
                    $('#main-content').postUrl(Main.contextPath + "/SSC11307/update",formData,
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
    SSC1130701.init();
})