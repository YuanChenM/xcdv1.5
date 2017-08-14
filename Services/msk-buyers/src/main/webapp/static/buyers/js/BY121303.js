/**
 * 买家列表JS
 * Created by marshall on 16/3/9.
 */
var BY121303 = {
    BY121303Grid: null,
    addButton:"BY121303_ADD",
    accountButton:"BY121303_ACCOUNT",
    findButton :"BY121303_SELECT",
    initDataGrid: function () {
        this.addHandler();
        this.showHandler();
        this.accountHandler();
        this.bindSelectChange();
        this.bindMarket();
        BY121303.BY121303Grid = $('#BY121303_Grid').grid({
            //linkHandler: BY121303.linkHandler,
            actionHandler: BY121303.actionHandler,
            can_viewPool: BY121303.canViewPool,
            can_viewHk: BY121303.canViewHk
        });
    },

    bindSelectChange : function () {
        var lgcsAreaCode ="";
        var cityCode = "";
        var superiorType = "";
        var districtCode ="";
        $("#lgcsAreaCode").change(function(){
            lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            if(lgcsAreaCode == ""){
                $("#cityCode").html("");
                $("#cityCode").append("<option value=''>--请选择--</option>");
                $("#districtCode").html("");
                $("#districtCode").append("<option value=''>--请选择--</option>");
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/lgcsAreaChange/" + lgcsAreaCode,null,
                function(data){
                    $("#cityCode").html("");
                    $("#cityCode").append("<option value=''>--请选择--</option>");
                    $("#districtCode").html("");
                    $("#districtCode").append("<option value=''>--请选择--</option>");
                    $.each(data,function(i,item){
                        $("#cityCode").append("<option value='" + item.cityCode + "'>"+ item.cityName+ "</option>");
                    });
                },{refreshHtml:false});
        });
        $("#cityCode").change(function(){
            cityCode = $("#cityCode option:selected").val();
            if(cityCode == ""){
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/cityChange/" + cityCode,null,
                function(data){
                    $("#districtCode").html("");
                    $("#districtCode").append("<option value=''>--请选择--</option>");
                    $.each(data,function(i,item){
                        $("#districtCode").append("<option value='" + item.districtCode + "'>"+ item.districtName+ "</option>");
                    });
                },{refreshHtml:false});
             superiorType = $("#superiorType option:selected").val();
            if(superiorType == '01'){
                 lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
                 cityCode = $("#cityCode option:selected").val();
                $("#superiorQua").val("");
                BY121303.bindMarket(superiorType,lgcsAreaCode,cityCode,'');
            }
        });
        $("#districtCode").change(function(){
            superiorType = $("#superiorType option:selected").val();
            if(superiorType == '02'){
                var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
                var cityCode = $("#cityCode option:selected").val();
                var districtCode = $("#districtCode option:selected").val();
                if(districtCode == ""){
                    return false;
                }
                BY121303.bindMarket(superiorType,lgcsAreaCode,cityCode,districtCode);
            }
        });
        $("#superiorType").change(function(){
            superiorType = $("#superiorType option:selected").val();
            if(superiorType == ""){
                return false;
            }
            lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            cityCode = $("#cityCode option:selected").val();
            districtCode = $("#districtCode option:selected").val();
            BY121303.bindMarket(superiorType,lgcsAreaCode,cityCode,districtCode);
        });

        $("#"+BY121303.findButton).click(function(){
            var superiorId = $("#superiorId").val();
            var lgcsAreaName =$("#lgcsAreaCode option:selected").text();
            var cityName = $("#cityCode option:selected").text();
            var superiorName = $("#superiorType option:selected").text();
            var districtName =$("#districtCode option:selected").text();
            var marketName = $("#marketId option:selected").text();
            var marketingsStatusName = $("#marketStatus option:selected").text();
            var accountName = $("#accountName").val();
            var telNo = $("#telNo").val();
            var buyerCode = $("#buyerCode").val();
            var storeNo = $("#storeNo").val();
            var buyerName = $("#buyerName").val();
            var bossName = $("#bossName").val();
            if(superiorName == "--请选择--"){
                $("#superiorName").val("");
            }else{
                $("#superiorName").val(superiorName);
            }
            if(lgcsAreaName == "--请选择--"){
                $("#lgcsAreaName").val("");
            }else{
                $("#lgcsAreaName").val(lgcsAreaName);
            }

            if(cityName == "--请选择--"){
                $("#cityName").val("");
            }else{
                $("#cityName").val(cityName);
            }
            if(districtName == "--请选择--"){
                $("#districtName").val("");
            }else{
                $("#districtName").val(districtName);
            }
            if(marketName == "--请选择--"){
                $("#marketName").val("");
            }else{
                $("#marketName").val(marketName);
            }
            if(marketingsStatusName == "--请选择--"){
                $("#marketingsStatusName").val("");
            }else{
                $("#marketingsStatusName").val(marketingsStatusName);
            }


            var formData = getFormData($("#selectBuyerList"));

            $('#main-content').postUrl(Main.contextPath + "/BY121303/search/"+superiorId,formData,
                function(){
                    BY121303.BY121303Grid.fnDraw();
                },{refreshHtml:false});

        });
    },
    bindMarket :function(superiorType,lgcsAreaCode,cityCode,districtCode){
        var superiorType = $("#superiorType option:selected").val();
        if(superiorType == ""){
            $("#marketId").html();
            $("#marketId").append("<option value=''>--请选择--</option>");
            return;
        }
        if(superiorType == "01"){
            $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/findMarketTermialList/",{
                    "lgcsAreaCode":lgcsAreaCode,
                    "cityCode":cityCode,
                    "districtCode":''
                },
                function(data){
                    $("#marketId").html("");
                    $("#marketId").append("<option value=''>--请选择--</option>");
                    $.each(data,function(i,item){
                        $("#marketId").append("<option value='" + item.terMarketId + "'>"+ item.marketName+ "</option>");
                    });
                },{refreshHtml:false});
        }else if(superiorType == "02" ||(( superiorType=='05'))){
            $('#main-content').postUrl(Main.contextPath + "/by/baseBuyerBasicInfo/findMarketFoodList/",{
                    "lgcsAreaCode":lgcsAreaCode,
                    "cityCode":cityCode,
                    "districtCode":districtCode
                },
                function(data){
                    $("#marketId").html("");
                    $("#marketId").append("<option value=''>--请选择--</option>");
                    $.each(data,function(i,item){
                        $("#marketId").append("<option value='" + item.fodMarketId + "'>"+ item.marketName+ "</option>");
                    });
                },{refreshHtml:false});
        }
    },
    canViewPool: function(rowdata){
        if(rowdata.marketingsStatus != "31" && rowdata.marketingsStatus != ""){
            return true;
        }
        return false;
    },
    canViewHk: function(rowdata){
        if(rowdata.marketingsStatus != "31" && rowdata.marketingsStatus != ""){
            return true;
        }
        return false;
    },
    //linkHandler: function (rowdata, coltype, row, col) {
    //    $('#main-content').postUrl(Main.contextPath + "/BY121304/init/" + rowdata.buyerId);
    //},
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "detail") {
            //跳转到买家总控页面
            $('#main-content').postUrl(Main.contextPath + "/BY121313/init/" + rowdata.buyerId);
        }
        if (coltype == "edit") {
            //跳转到详细页面,修改买家信息
            $('#main-content').postUrl(Main.contextPath + "/BY121304/init/" + rowdata.buyerId);
        }
        if(coltype == "delete"){
            $.alertMessage.confirm("你确定要删除该买家信息吗？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BY121303/delete/" + rowdata.buyerId,null,function () {
                    BY121303.BY121303Grid.fnDraw();
                }, {refreshHtml: false});
            });
        }
        if(coltype == "audit" && col == 11){
            $.pdialog.open("所属买家池查看画面", Main.contextPath + "/BY12130301/init/" + rowdata.buyerId, {
                    width: 750,
                    height: 300
                }
            )
        }
        if(coltype == "audit" && col == 12){
            $.pdialog.open("冻品管家查看画面", Main.contextPath + "/BY12130302/init/" + rowdata.buyerId + "/" + rowdata.marketingsStatus, {
                    width: 800,
                    height: 200
                }
            )
        }
    },
    addHandler: function(){
        $("#" + BY121303.addButton).click(function(){
            var telNo = $("#insertTelNo").val();
      /*      var reg = new RegExp("^[0-9]*$");*/
            var regPhone=/^1[34578]\d{9}$/;
            if(telNo == ""){
                $.alertMessage.confirm("请输入买家手机号", function () {
                    $.alertMessage.close();
                });
                return false;
            }else if(!regPhone.test(telNo)){
                $.alertMessage.confirm("您输入的手机号码有误，请查证！", function () {
                    $.alertMessage.close();
                });
                return false;
            }
         /*   if(!reg.test(telNo)){
                $.alertMessage.confirm("请输入数字买家手机号", function () {
                    $.alertMessage.close();
                });
                return false;
            }*/
            $('#main-content').postUrl(Main.contextPath + "/BY121303/register/" + telNo);
        });
    },
    accountHandler:function(){
        $("#" + BY121303.accountButton).click(function(){
            $('#main-content').postUrl(Main.contextPath + "/BY121327/init/");

        })
    },
    showHandler: function(){
        var message = $("#message").val();
        if(message && message != ""){
            $.alertMessage.confirm(message, function () {
                $.alertMessage.close();
            });
            return false;
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    BY121303.initDataGrid();
});

document.onkeydown = function(e){
    var e = e || window.event;
    if(e.keyCode == 13) {
        $("#"+BY121303.findButton).click();
    }
};