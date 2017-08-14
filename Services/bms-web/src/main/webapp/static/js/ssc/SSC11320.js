/**
 * Created by peng_hao on 2016/8/9.
 */
var $List_Grid;
var SSC11320 = {
    formId:"SSC11320Form",
    init : function() {
        $List_Grid = $("#SSC11320_list_grid").grid({
        })
        Main.hlLeftMainMenu("SSC11319");
        formatNum();
    },

    /**
     * 点击合同信息超链接
     */
    clickContractCodeLink: function() {
        Main.detailWindow(Main.contextPath + "/SSC11304/show/" , {
            "contractId": $("#contractId").val(),
            "paymentDetailId": $("#paymentId").val()
        },"合同详细");
    },

    /**
     * 点击发货信息超链接
     */
    clickDeliveryCodeLink: function() {
        Main.detailWindow(Main.contextPath + "/SSC11306/show/" , {
            "deliveryId": $("#deliveryId").val(),
            "paymentDetailId": $("#paymentId").val()
        },"发货订单详细");
    },

    /**
     * 点击核销单信息超链接
     */
    clickVerificationLink: function() {
        Main.detailWindow(Main.contextPath + "/SSC11322/init/" , {
            "verificationId": $("#verificationId").val(),
            "paymentDetailId": $("#paymentId").val()
        },"核销单详细");
    }

}
//从页面取值格式化金钱
function  formatNum() {
    var contractTotalAmount = $("#contractTotalAmount").text();
    var totalAmount = $("#totalAmount").text();
    var amount = $("#amount").text();
    var accumulateAmount = $("#accumulateAmount").text();
    var remainAmount = $("#remainAmount").text();
    $("#contractTotalAmount").html(formatMoney(contractTotalAmount));
    $("#totalAmount").html(formatMoney(totalAmount));
    $("#amount").html(formatMoney(amount));
    $("#accumulateAmount").html(formatMoney(accumulateAmount));
    $("#remainAmount").html(formatMoney(remainAmount));
}

//格式化金钱
function formatMoney(num){
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
        num = num.substring(0,num.length-(4*i+3))+','+
        num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}
$(document).ready(function(){
    SSC11320.init();
})