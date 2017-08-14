/**
 * Created by peng_hao on 2016/7/11.
 */

var SSC1130201 = {
    init: function(){
        this.bindConfirmButton();
    },
    bindConfirmButton:function(){
        $("#SSC1130201_CONFIRM").click(function(){
            var deliveryBatch = $("#delivery_select option:selected").val();
            var contractId =  $('#contractId').val();
            if(deliveryBatch == "0" || deliveryBatch==null){
                $.alertMessage.info("请选择发货批次");
            }else{
                $('#main-content').postUrl(Main.contextPath + '/SSC11304/creatDeliveryOrder',{deliveryBatch:deliveryBatch},
                    function(data){
                        $.pdialog.close();
                        if(data != "处理成功"){
                            $.alertMessage.info(data);
                        }else{
                            $.alertMessage.info("新建成功");
                            $('#main-content').postUrl(Main.contextPath + "/SSC11304/init");
                        }
                    },{refreshHtml:false});
            }

        });
    }
}
$(document).ready(function(){
    SSC1130201.init();
})