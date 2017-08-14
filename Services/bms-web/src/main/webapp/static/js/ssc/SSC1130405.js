/**
 * Created by peng_hao on 2016/7/11.
 */

var SSC1130405 = {
    init: function(){
        this.bindConfirmButton();
    },
    bindConfirmButton:function(){
        $("#SSC1130405_CONFIRM").click(function(){
            var contractId = $("#contractIdTemp").val();
            var selectStr = $("#eta_select option:selected").val();
            if(selectStr == "0" || selectStr== ""){
                $.alertMessage.info("请选择到货日期");
                return;
            }
            var array = selectStr.split(',');
            var etaStr = array[0];
            var batchCode = array[1];
            if(etaStr == "0" || etaStr== ""){
                $.alertMessage.info("到货日期为空");
                return;
            }
            if(batchCode == "0" || batchCode== ""){
                $.alertMessage.info("无车次信息");
                return;
            }

            $('#main-content').postUrl(Main.contextPath + '/SSC11304/creatDeliveryOrder',{contractId:contractId,etaStr:etaStr,deliveryBatch: batchCode},
                function(data){
                    if(data.status != "S"){
                        $.alertMessage.info(data.message);
                    }else{
                        Main.detailWindow(Main.contextPath + "/SSC11306/init",{ "deliveryId":data.result}, "发货订单详细");
                        $.pdialog.close();
                    }
                },{refreshHtml:false});

        });
    }
}
$(document).ready(function(){
    SSC1130405.init();
})