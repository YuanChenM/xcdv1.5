/**
 * 买家信息总控画面JS
 * Created by marshall on 16/7/5.
 */
var BY121313 = {
    buyerId:"",
    marketingsStatus:"",
    //买家基本信息管控
    basicButtonId:"BY121313_BUYER_BASIC",
    basicDivId: "byBasicInfo",
    //买家编码管控
    byCodeButtonId: "BY121313_BUYERCODE_DETAIL",
    byCodeDivId: "byCodeControl",
    //买家池归属管控
    byPoolButtonId: "BY121313_BUYER_POOL",
    byPoolDivId: "byPoolAttribution",
    //买家上线状态管控
    marketingStatusButtonId: "BY121313_MARKETING_STATUS",
    marketingStatusDivId: "marketingStatus",
    //买家配送信息管控
    byDeliveryButtonId: "BY121313_DELIVERY_DETAIL",
    byDeliveryDivId: "deliveryInfo",
    //营销期买家冻品管家管控
    byMarketingHouseButtonId: "BY121313_MARKETINGHOUSE_DETAIL",
    byMarketingHouseDivId: "marketingHousekeeper",
    //销售期买家冻品管家管控
    bySaleHouseButtonId: "BY121313_SALE_DETAIL",
    bySaleHouseDivId: "saleHousekeeper",
    //买家订单汇总管控
    byOrderButtonId: "BY121313_ORDER",
    byOrderDivId: "orderSummary",
    //买家已够产品目录管控
    byPdPoolCatalogButtonId: "BY121313_PDPOOL_CATALOG",
    byPdPoolCatalogDivId: "purPdCatalog",
    //买家销售预测管控
    byForecastButtonId:"BY121313_FORECAST",
    byForecastDivId:"saleForecast",
    //买家商城账号
    byShoppingAccountInfo:"BY121313_SHOPPING_MALL",
    byShoppingAccountDivId:"shoppingAccount",
    //买家微商城账号
    byMicroAccountInfo:"BY121313_MICRO_MALL",
    byMicroAccountDivId:"microAccount",
    //买家报表管理
    byReportManage:"BY121313_REPORT_MANAGE",
    byReportManageDivId:"reportManage",
    //买家营销工具管控
    byMarketingTool:"BY121313_MARKETING_TOOL",
    byMarketingToolDivId:"marketingTool",
    //买家会员卡管控
    byMemberCard:"BY121313_MEMBER_CARD",
    byMemberCardDivId:"memberCard",
    //买家下单方式汇总管控
    byOrderMethod:"BY121313_ORDER_METHOD",
    byOrderMethodDivId:"orderMethod",
    //买家注册信息总控表
    byBuyerInfoReport:"BY121313_BUYER_INFO_REPORT",
    byBuyerInfoReportDiv:"buyerInfoReport",
    //冻品管家总控表
    byFrozenProductManagerReport:"BY121313_FROZEN_PRODUCT_REPORT",
    byFrozenProductManagerReportDiv:"frozenProductManagerReport",
    //分销买家销售期基本信息管控表
    bySalePeriodBasicReport:"BY121313_SALE_PERIOD_REPORT",
    bySalePeriodBasicReportDiv:"salePeriodBasicReport",
    init: function () {
        BY121313.buyerId = $("#buyerId").val();
        BY121313.marketingsStatus = $("#marketingsStatus").val();
        if($("#"+BY121313.basicButtonId)){
            var url = Main.contextPath + "/BY121304/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.basicDivId,url,BY121313.basicDivId+".png");
        }
        if($("#"+BY121313.byCodeButtonId)){
            var url = Main.contextPath + "/BY121313/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.byCodeDivId,url,BY121313.byCodeDivId+".png");
        }
        if($("#"+BY121313.byPoolButtonId)){
            var url = Main.contextPath + "/BY121310/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.byPoolDivId,url,BY121313.byPoolDivId+".png");
        }
        if($("#"+BY121313.marketingStatusButtonId)){
            var url = Main.contextPath + "/BY121315/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.marketingStatusDivId,url,BY121313.marketingStatusDivId+".png");
        }
        if($("#"+BY121313.byDeliveryButtonId)){
            var url = Main.contextPath + "/BY121314/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.byDeliveryDivId,url,BY121313.byDeliveryDivId+".png");
        }
        if($("#"+BY121313.byMarketingHouseButtonId)){

            var url = Main.contextPath + "/BY121322/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.byMarketingHouseDivId,url,BY121313.byMarketingHouseDivId+".png");
        }
        if($("#"+BY121313.bySaleHouseButtonId)){
            var url = Main.contextPath + "/BY121323/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.bySaleHouseDivId,url,BY121313.bySaleHouseDivId+".png");
        }
        if($("#"+BY121313.byOrderButtonId)){
            //var url = Main.contextPath + "/BY121308/init/"+buyerId;
            var url="";
            BY121313.buttonBackImage(BY121313.byOrderDivId,url,BY121313.byOrderDivId+".png");
        }
        if($("#"+BY121313.byPdPoolCatalogButtonId)){
            //var url = Main.contextPath + "/BY121309/init/"+buyerId;
            var url="";
            BY121313.buttonBackImage(BY121313.byPdPoolCatalogDivId,url,BY121313.byPdPoolCatalogDivId+".png");
        }
        if($("#"+BY121313.byForecastButtonId)){
            var url = "";
            BY121313.buttonBackImage(BY121313.byForecastDivId,url,BY121313.byForecastDivId+".png");
        }
        if($("#"+BY121313.byShoppingAccountInfo)){
            var url = Main.contextPath + "/BY121316/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.byShoppingAccountDivId,url,BY121313.byShoppingAccountDivId+".png");
        }
        if($("#"+BY121313.byMicroAccountInfo)){
            var url = Main.contextPath + "/BY121317/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.byMicroAccountDivId,url,BY121313.byMicroAccountDivId+".png");
        }
        if($("#"+BY121313.byReportManage)){
            var url= Main.contextPath + "/BY121318/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.byReportManageDivId,url,BY121313.byReportManageDivId+".png");
        }
        if($("#"+BY121313.byMarketingTool)){
            var url = Main.contextPath + "/BY121319/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.byMarketingToolDivId,url,BY121313.byMarketingToolDivId+".png");
        }
        if($("#"+BY121313.byMemberCard)){
            var url = Main.contextPath + "/BY121320/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.byMemberCardDivId,url,BY121313.byMemberCardDivId+".png");
        }
        if($("#"+BY121313.byOrderMethod)){
            var url = Main.contextPath + "/BY121321/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.byOrderMethodDivId,url,BY121313.byOrderMethodDivId+".png");
        }
        if($("#"+BY121313.byBuyerInfoReport)){
            BY121313.buttonBackImage(BY121313.byBuyerInfoReportDiv,'',BY121313.byBuyerInfoReportDiv+".png");
        }
        if($("#"+BY121313.byFrozenProductManagerReport)){
            var url = "";
            BY121313.buttonBackImage(BY121313.byFrozenProductManagerReportDiv,url,BY121313.byFrozenProductManagerReportDiv+".png");
        }
        if($("#"+BY121313.bySalePeriodBasicReport)){
            var url = Main.contextPath + "/BY121325/init/"+BY121313.buyerId;
            BY121313.buttonBackImage(BY121313.bySalePeriodBasicReportDiv,url,BY121313.bySalePeriodBasicReportDiv+".png");
        }

        BY121313.buttonTransfer();

    },
    buttonBackImage: function(buttonDiv,url,picName) {
        //买家注册信息总控表
        if(buttonDiv == "buyerInfoReport"){
            var excel = "BY121324";
            var logic = "buyerRegisterReportLogic";
            var module = "by";
            var fileName = "分销买家注册信息总控表.xlsx";
            var buyerId = BY121313.buyerId;
            $("#" + buttonDiv).html("<a onclick='BY121313.bindComplete(\""+excel+"\",\""+logic+"\",\""+module+"\",\""+fileName+"\",\""+buyerId+"\")'><img src='"+Main.contextPath+"/static/buyers/img/"+picName+"'/></a>");
        }else if(buttonDiv == "frozenProductManagerReport"){
            $("#" + buttonDiv).html("<a onclick='BY121313.bindDialogPage(\""+BY121313.buyerId+"\",\""+buttonDiv+"\")'><img src='"+Main.contextPath+"/static/buyers/img/"+picName+"'/></a>");
        }else if(buttonDiv == "salePeriodBasicReport"){
            $("#" + buttonDiv).html("<a onclick='BY121313.bindDialogPage(\""+BY121313.buyerId+"\",\""+buttonDiv+"\")'><img src='"+Main.contextPath+"/static/buyers/img/"+picName+"'/></a>");
        }else{
            $("#" + buttonDiv).html("<a class='buttonImg' id='"+url+"' href='javascript:void(0);'><img src='"+Main.contextPath+"/static/buyers/img/"+picName+"'/></a>");
        }
    },
    buttonDisableImage: function(buttonDiv,picName){
        $("#" + buttonDiv).html("<a href='javascript:void(0);'><img src='"+Main.contextPath+"/static/buyers/img/"+picName+"'/></a>");
    },
    buttonTransfer: function(){
        $('a.buttonImg').click(function(){
            if(this.id != ""){
                $("#main-content").postUrl(this.id);
            }else{
                $.alertMessage.info("系统升级中，该功能暂未开放！");
                return false;
            }
        });
    },
    bindComplete:function(excel,logic,module,fileName,buyerId){
        var param = new Object();
        param['buyerId'] = buyerId;
        downloadAsync(excel, logic, module,fileName, param);
    },
    bindDialogPage: function(buyerId,buttonDiv){
        if(buttonDiv == "frozenProductManagerReport"){
            $.pdialog.open("分销买家营销期冻品管家营销信息管控表", Main.contextPath + "/BY121326/init/" + buyerId, {
                    width: 400,
                    height: 500
                }
            )
        }else if(buttonDiv == "salePeriodBasicReport"){
            $.pdialog.open("销售期分销买家销售基础信息管控表", Main.contextPath + "/BY121325/init/" + buyerId, {
                    width: 400,
                    height: 500
                }
            )
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    BY121313.init();
});