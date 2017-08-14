/**
 * Created by yang_yang on 2016/8/26.
 */

var SSC1130602 = {
    contractSelect: $("#delivery_select"),
    contractData: null,
    init: function(){
       // 监听input框
       // 调用SSC11324的查询接口-获取已生效状态的合同列表
        $('#main-content').postUrl(Main.contextPath + '/SSC11324/searchContractForInvoice',{isOrder: true},function(data){
            if(data.contractFlag == "F"){
                $.alertMessage.info("无数据");
            }else{
                //使用jQueryUi的Autocomplete插件生成类似于百度搜索框的自动匹配模糊查询
                $( "#tags" ).autocomplete({
                    source: data.contractList
                });
            }
        },{refreshHtml:false},Main.hiddenHeader);
        this.bindConfirmButton();
    },
    bindConfirmButton:function(){
        $("#SSC1130602_CONFIRM").click(function() {
            var contractCode = $("#tags").val().split("(")[0].trim();
            if($("#tags").val().split("(")[1] != null && $("#tags").val().split("(")[1] != undefined
                && $("#tags").val().split("(")[1] != "") {
                var contractName = $("#tags").val().split("(")[1].split(")")[0].trim();
            }
            //查询输入的合同是否存在
            $('#main-content').postUrl(Main.contextPath + '/SSC11324/ContractFindInvoiceRequestDetailExist',
                {contractCode: contractCode,contractName:contractName,isOrder:true}, function (data) {
                    if(data.status == "S"){
                        //新建发货订单
                        if($("#deliveryId").val() == ""){
                            $("#contractId").val(data.result.contractId);
                            $("#contractCode").val(data.result.contractCode);
                            $("#contractCodeHref").text(data.result.contractCode);
                            $.pdialog.close();
                        }else{
                            //关联发货订单，发货确认单，预入库通知单,差异单，付款申请，资金池
                            $('#main-content').postUrl(Main.contextPath + '/SSC11306/relevanceContract', {
                                deliveryId:$("#deliveryId").val(),
                                contractId:data.result.contractId,contractCode: data.result.contractCode,
                                ver:$("#ver").val()
                            },function(data){
                                $.alertMessage.info("操作成功");
                                $("#headBreadCrumb").hide();
                            });
                            $.pdialog.close();
                        }
                    }else {
                        //发货订单关联合同只取未删除，合同状态不为7：待核销，8：已结案 的数据
                        $.alertMessage.info("无法关联已废弃，待核销及已结案的合同,请重新选择");
                    }
                },{refreshHtml:false});
        });
    }
}
$(document).ready(function(){
    SSC1130602.init();
})