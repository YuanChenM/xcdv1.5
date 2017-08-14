var BY121414 = {
    buyerListReportButtonId:"BY121414_BUYER_LIST_REPORT",
    buyerListReportDivId: "buyerListReport",
    init: function () {
       if($("#"+BY121414.buyerListReportButtonId)){
            BY121414.buttonBackImage(BY121414.buyerListReportDivId,'',"buyerInfoReport"+".png");
        }
    },

    buttonBackImage: function(buttonDiv,url,picName) {
        if(buttonDiv == "buyerListReport"){
        var excel = "BY121414";
        var logic = "BY121414Report";
        var module = "msk-buyers";
        var fileName = "买家基本信息管控表.xlsx";
        $("#" + buttonDiv).html("<a onclick='BY121414.bindComplete(\""+excel+"\",\""+logic+"\",\""+module+"\",\""+fileName+"\")'><img src='"+Main.contextPath+"/static/buyers/img/"+picName+"'/></a>");
        }
      },
    bindComplete:function(excel,logic,module,fileName){
        var param = new Object();
        downloadAsync(excel, logic, module,fileName, param);
    }
}
$(document).ready(function () {
    // 初始化调用
    BY121414.init();
});