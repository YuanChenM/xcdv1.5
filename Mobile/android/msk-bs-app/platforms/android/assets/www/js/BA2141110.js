/**
 * Created by zhu_kai1 on 2016/7/18.
 */
var receiverProvince="";
var receiverCity="";
var receiverDistrict="";
var tempProducts=[];
var districtCode="";
var houseCode="";
var accessType="";
var slRecbookId="";
var isBuy="";
var pdCodeArr = [];
var vlgcsCode="";
var backUrl = "";
jQuery(document).ready(function () {
    houseCode = localStorage.houseCode;
    accessType =localStorage.accessType;
    vlgcsCode = localStorage.lgcsCode;
    isBuy = BA2141110.QueryString("isBuy");

    slRecbookId = BA2141110.QueryString("slRecbookId");
    if(slRecbookId !=null && slRecbookId !=''){
        setTimeout("BA2141110.findHouseAddress(slRecbookId)",100);
    }
    BA2141110.bindTapAddress();
    BA2141110.bindFh();
    if(null !=isBuy && isBuy !=''){
        // 若isBuy不为空，表示从产品列表购买的
        setTimeout("BA2141110.findOrderDetailByPd()",100);
        backUrl = 'BA2141107.html?pdCode='+BA2141110.QueryString("productCode");
    }else{
        setTimeout("BA2141110.findOrderDetail()",100);
        backUrl = 'BA2141108.html';
    }
    BA2141110.payWay();
    BA2141110.bindCommitOrder();

});

var BA2141110 ={
    QueryString: function (val) {
        var uri = window.location.search;
        var svalue =uri.match(new RegExp("[\?\&]" + val + "=([^\&]*)(\&?)","i"));
        return svalue ? svalue[1] : svalue;
    },
    /**返回事件绑定*/
    bindFh:function(){
        $(".head-back").bind("touchstart",function(){
            if(null !=isBuy && isBuy !=''){
                // 若isBuy不为空，表示从产品列表购买的
                window.location.href=backUrl;
            }else{
                window.location.href=backUrl;
            }
        })
    },
    findHouseAddress :function  (slRecbookId){
        var url = ConstantDef.getFindHouseBookByRecbookIdServeUrl();
        var paramData ={
            param: {
                "slRecbookId": slRecbookId,
                "accessType":accessType
            }
        };
        HttpClient.post(url,paramData,function(data){
            if(data.status == "F"){
                webToast("查询收货地址失败","middle");
            }else{
                var result = data.result;
                $(".hideBuyer").html(result.buyerName);
                $(".buyer").html("收货人："+result.buyerName);
                $(".tel").html(result.telNum);
                $(".address").html(result.fullAddress);
                $(".prompt").hide();
                $("#hiddiv").show();
                receiverProvince = result.provinceName;
                receiverCity = result.cityName;
                receiverDistrict = result.districtName;
            }
        },function(data){
            webToast("操作失败","middle");
        });
    },
    /**获取订单详情列表**/
    findOrderDetail :function (){
        // 获取当前登录者的id
        var url = ConstantDef.getOrderDetailInfoByCarIDServerUrl();
        var carID = BA2141110.QueryString("carId");
        var carDetailIds = BA2141110.QueryString("carDetailIds");
        var carDetailIdArr = [];
        carDetailIdArr = carDetailIds.split(",");
        var data = {
            param:{
                carID:carID,
                carDetailID:carDetailIdArr,
                lgcsCode:vlgcsCode
            }
        };
        HttpClient.post(url,data,function(data){
            if(data.status == "F"){
                webToast("获取订单列表失败","middle");
            }else{
                var priceCycle =BA2141110.QueryString("priceCycle");
                var result = data.result;
                var priceSum = 0;
                var totalSum=0;
                for(var i=0;i<data.result.length;i++){
                    var obj =$("ul li:eq(0)");
                    var cloneObj = obj.clone();
                    cloneObj.show();
                    var breedName = data.result[i].breedName;
                    var featureName = data.result[i].featureName;
                    var weightName = data.result[i].weightName;
                    var price = data.result[i].oldPrice;
                    var num = data.result[i].pdNum;
                    var gradeName = data.result[i].gradeName;
                    if(data.result[i].isOffTheShelf=="1"){
                        price = "已下架";
                        if(!breedName){
                            breedName = result[i].pdName.split("|")[0];
                            featureName= result[i].pdName.split("|")[1];
                            weightName = result[i].pdName.split("|")[2];
                            gradeName = result[i].pdName.split("|")[3];
                        }
                    }
                    cloneObj.find("h4.breed")[0].innerHTML = breedName;
                    cloneObj.find("p.featureClass")[0].innerText ="产品特征："+ featureName;
                    cloneObj.find("p.gradeClass")[0].innerText ="产品等级："+ gradeName;
                    cloneObj.find("p.normsClass")[0].innerText ="包装净重："+ weightName;
                    cloneObj.find("span.unitPrice")[0].innerText = price;
                    cloneObj.find("span.num")[0].innerText = num;
                    cloneObj.appendTo("#productList");
                    if(data.result[i].isOffTheShelf !="1"){
                        priceSum+=parseFloat(price*num);
                    }
                    totalSum +=parseInt(num);
                    var products = {"pdCode":data.result[i].pdCode,"pdName":breedName,"orderPrice":price,"orderQty":num,"priceCycle":priceCycle}
                    tempProducts.push(products);
                    pdCodeArr.push(data.result[i].pdCode);
                }
                BA2141110.accounPrice(priceSum,totalSum);
            }
        },function(data){
            webToast("操作失败","middle");
        });
    },
    /**获取订单详情列表**/
    findOrderDetailByPd :function (){
        var url = ConstantDef.getOrderDetailByPdInfoUrl();
        var productCode = BA2141110.QueryString("productCode");
        var productNum = BA2141110.QueryString("productNum");
        var productPrice = BA2141110.QueryString("productPrice");
        var data = {
            param:{
                productCode:productCode,
                productNum:productNum,
                productPrice:productPrice,
                lgcsCode:vlgcsCode
            }
        };
        HttpClient.post(url,data,function(data){
            if(data.status == "F"){
                webToast("获取订单列表失败","middle");
            }else{
                var priceCycle =BA2141110.QueryString("priceCycle");
                var pdBreed =decodeURI(BA2141110.QueryString("pdName"));
                var result = data.result;
                var priceSum = 0;
                var totalSum=0;
                    var obj =$("ul li:eq(0)");
                    var cloneObj = obj.clone();
                    cloneObj.show();
                    var breedName = result.breedName;
                    var featureName = result.featureName;
                    var weightName = result.weightName;
                    var gradeName = result.gradeName;
                     var price =result.oldPrice;
                    if(result.isOffTheShelf=="1"){
                        price = "已下架";
                        if(!breedName){
                            breedName = pdBreed.split("|")[0];
                            featureName= pdBreed.split("|")[1];
                            weightName = pdBreed.split("|")[2];
                            gradeName = pdBreed.split("|")[3];
                        }
                    }else{
                        cloneObj.find("h4.breed")[0].innerHTML = breedName;
                        cloneObj.find("p.featureClass")[0].innerText ="产品特征："+ featureName;
                        cloneObj.find("p.gradeClass")[0].innerText ="产品等级："+ gradeName;
                        cloneObj.find("p.normsClass")[0].innerText ="包装净重："+ weightName;
                        cloneObj.find("span.unitPrice")[0].innerText = price;
                        cloneObj.find("span.num")[0].innerText = productNum;
                        cloneObj.appendTo("#productList");
                    }
                    if(result.isOffTheShelf !="1"){
                        priceSum+=parseFloat(price*productNum);
                    }
                    totalSum +=parseInt(productNum);
                    var products = {"pdCode":productCode,"pdName":breedName,"orderPrice":price,"orderQty":productNum,"priceCycle":priceCycle}
                    tempProducts.push(products);
                pdCodeArr.push(productCode);
                BA2141110.accounPrice(priceSum,totalSum);
            }
        },function(data){
            webToast("操作失败","middle");
        });
    },
    accounPrice :function (priceSum,totalSum){
        var yunFei = parseFloat($("#yunFei").html());
        var sumPrice = parseFloat(priceSum+yunFei);
        $("#payable").html(Math.round(priceSum*100)/100);
        // 共计多少件商品、总价格
        $("#number").html(totalSum);
        $("#totalPrice").html(Math.round(sumPrice*100)/100);
    },

    /**跳转到地址新增页面**/
    bindTapAddress :function (){
        $("#addInfo").bind("touchstart",function(){
            var priceCycle = BA2141110.QueryString("priceCycle");
            if(null !=isBuy && isBuy !=''){
                var productCode = BA2141110.QueryString("productCode");
                var productNum = BA2141110.QueryString("productNum");
                var productPrice = BA2141110.QueryString("productPrice");
                var pdName = BA2141110.QueryString("pdName");
                window.location.href = "BA2141112.html?isBuy=1&productCode=" + productCode + "&productNum=" + productNum + "&productPrice=" + productPrice+"&priceCycle="+priceCycle+"&pdName="+pdName;
            }else{
                var carID = BA2141110.QueryString("carId");
                var carDetailIds = BA2141110.QueryString("carDetailIds");
                window.location.href = "BA2141112.html?carId="+carID+"&carDetailIds="+carDetailIds+"&priceCycle="+priceCycle;
            }

        })
    },
    /**支付方式付款**/
    payWay:function(){
        $(".pay-type label").each(function(){
            $(this).bind("touchstart",function(){
                $('label').removeAttr('class');
                $(this).attr('class', 'checked');
            });
        });
    } ,
    /**获取选择支付的方式**/
    labelChecked:function(){
        var paymentType;
        $(".pay-type label").each(function(){
            if($(this).attr("class")=="checked"){
                console.log("付款方式值为"+$(this).attr("data-payType")+"：1-线上支付，2-货到付款。");
                  paymentType =  $(this).attr("data-payType");
            }
        });
        return paymentType;
    } ,

    /**提交订单**/
    bindCommitOrder:function (){
        $(".orderBtn").bind("touchstart",function(){
                var buyer = $(".buyer").html();
                var tel = $(".tel").html();
                var address = $(".address").html();
                if(!buyer && !tel && !address){
                    webToast("请选择收货地址","middle");
                    return false;
                }
                if(!buyer){
                    webToast("收货人不能为空","middle");
                    return false;
                }
                if(!tel){
                    webToast("联系电话不能为空","middle");
                    return false;
                }
                if(!address){
                    webToast("收货地址不能为空","middle");
                    return false;
                }
            if(vlgcsCode!=null && vlgcsCode !=''){
                var errorMsg = "";
                for(var i = 0 ;i<tempProducts.length;i++){
                    if(tempProducts[i].orderPrice == "已下架"){
                        errorMsg+=tempProducts[i].pdName+"产品已下架,不能购买!</br>"
                        continue;
                    }
                }
                if(errorMsg!=''){
                    webToast(errorMsg,"middle");
                    return;
                }else{
                    // 2-管家，3-买手
                    BA2141110.checkMaxStock(accessType);
                }
            }else{
                webToast("管家虚拟物流区不存在","middle");
            }
        })
    },

    /**更新购物车明细信息**/
    modifyOrder :function (){
        var url = ConstantDef.getUpdateOrderServerUrl();
        var carID = BA2141110.QueryString("carId");
        var carDetailIds = BA2141110.QueryString("carDetailIds");
        var carDetailIdArr = [];
        carDetailIdArr = carDetailIds.split(",");
        var paramData = {
            param: {
                carID:carID,
                carDetailID:carDetailIdArr,
                removeReason:"1",
                isOrderFlag:"1",
                modifyStatus:"1"
            }
        };
        HttpClient.post(url,paramData,function(data){
            if(data.status == "F"){
                webToast("购物车明细更新失败","middle");
            }else{
                setTimeout("window.location = 'BA2141105.html'",1000);
            }
        },function(data){
            webToast("操作失败","middle");
        });
    },

    findHouseCode:function(data,districtCode){
        // 根据买家ID查询对应的管家信息
        var url = ConstantDef.findHouseCodeByBuyerId();
        var paramData = {
            param:{
                buyerId:data.result.buyerId
            }
        };
        HttpClient.post(url,paramData,function(data2){
            if(data2.status == "F"){
                webToast("该买家不属于当前管家,不能为其下单","middle");
            }else{
                BA2141110.orderCommit(data,districtCode);
            }
        },function(data2){
            webToast("操作失败","middle");
        });
    },
    checkMaxStock:function(accessType){
        if(accessType==2){
            var stockData = {
                param:{
                    "slCode" : localStorage.sellerCode,
                    "platformType" : 1,
                    "lgcsCode" : vlgcsCode,
                    "sellerType":2,
                    "pdCodeList":pdCodeArr
                }
            };
            // 管家对应的买手的库存
            BA2141110.productStockInfo(stockData,accessType);
        }else{
            var stockData = {
                param:{
                    "slCode" : "0000000",
                    "platformType" : 1,
                    "lgcsCode" : vlgcsCode,
                    "sellerType":1,
                    "pdCodeList":pdCodeArr
                }
            };
            // 对应平台的库存
            BA2141110.productStockInfo(stockData,accessType);
        }
    },
    /**库存**/
    productStockInfo:function(stockData,accessType){
        var stockUrl = ConstantDef.getFindProductStockUrl();
        HttpClient.post(stockUrl,stockData,function(data2){
            if(data2.status == "F"){
                webToast("库存没有查询到数据","middle");
            }else{
                var msg="";
                var stockData = data2.result;
                for(var j = 0 ;j<stockData.length;j++){
                    for(var i = 0 ;i<tempProducts.length;i++){
                        if((tempProducts[i].pdCode==stockData[j].pdCode) && tempProducts[i].orderQty >stockData[j].stockCnt){
                            msg+=tempProducts[i].pdName+"超出可用库存!</br>";
                            continue;
                        }
                    }
                }
                if(msg!=''){
                    webToast(msg,"middle");
                    return;
                }
                if(accessType == 2){
                    BA2141110.checkIsOrNotRegister(vlgcsCode);
                }else{
                    BA2141110.StoreGoods(vlgcsCode);
                }
            }
        },function(data){
            webToast("操作失败","middle");
        });
    },
    checkIsOrNotRegister:function(districtCode){
        // 根据手机号码校验该买家是否已注册
        var buyerUrl = ConstantDef.getFindBuyerByTelServerUrl();
        var buyerData = {
            param:{
                telNo : $(".tel").text()
            }
        };
        HttpClient.post(buyerUrl,buyerData,function(data){
            if(data.status == "F"){
                webToast("该手机号码还未注册","middle");
            }else{
                BA2141110.findHouseCode(data,districtCode);
            }
        },function(data){
            webToast("操作失败","middle");
        });
    },

    /**创建分销订单**/
    orderCommit:function(data,districtCode){
        var result = data.result;
        var orderAmount = $(".price").text();
        var receiverName = $(".hideBuyer").text();
        var receiverTel =$(".tel").text();
        var receiverAddr = $(".address").text();
        var  paymentType =BA2141110.labelChecked();
        var paramData = {siteCode:207,auth:"MSK00001",loginId:"msk01",param: {orderTaker:houseCode,orderTakerType:2,districtCode:districtCode,buyersId:result.buyerId,buyersCode:result.buyerCode,buyersName:result.buyerName,
            buyersType:result.superiorType,sellerCode:"0000000",sellerName:"神农客平台",orderAmount:orderAmount,paymentType:paymentType,
            receiverInfo:{receiverName:receiverName,receiverTel:receiverTel,receiverProvince:receiverProvince,receiverCity:receiverCity,
                receiverDistrict:receiverDistrict,receiverAddr:receiverAddr},
            products:tempProducts}};
        var orderUrl = ConstantDef.getCreateDistributeOrderServerUrl();
        HttpClient.post(orderUrl,paramData,function(data){
            if(data.status == "F"){
                webToast(data.message,"middle");
            }else{
                // 提交订单成功
                webToast("提交成功","middle");
                var isBuy = BA2141110.QueryString("isBuy");
                if(null ==isBuy || isBuy ==''){
                    BA2141110.modifyOrder();
                }else{
                    setTimeout("window.location = 'BA2141105.html'",1000);
                }
            }
        },function(data){
            webToast("操作失败","middle");
        });
    },

    /**创建买手囤货订单**/
    StoreGoods:function(districtCode){
        var orderAmount = $(".price").text();
        var slContact =  localStorage.slContact;
        var slCode=localStorage.slCode;
        var buyerCode = localStorage.slCodeDis;
        var receiverName = $(".hideBuyer").text();
        var receiverTel =$(".tel").text();
        var  paymentType =BA2141110.labelChecked();
        var paramData = {siteCode:207,auth:"MSK00001",loginId:"msk01",param: {districtCode:districtCode,buyersId:slCode,buyersName:slContact,buyersCode:buyerCode,
            buyersType:"9",sellerCode:"0000000",orderAmount:orderAmount,paymentType:paymentType, receiverInfo:{receiverName:receiverName,receiverTel:receiverTel,
                receiverProvince:receiverProvince,receiverCity:receiverCity, receiverDistrict:receiverDistrict}, products:tempProducts}};
        var orderUrl = ConstantDef.createSdoBuyerOrderServerUrl();
        HttpClient.post(orderUrl,paramData,function(data){
            if(data.status == "F"){
                webToast(data.message,"middle");
            }else{
                // 提交订单成功
                webToast("提交成功","middle");
                var isBuy = BA2141110.QueryString("isBuy");
                if(null ==isBuy || isBuy ==''){
                    BA2141110.modifyOrder();
                }else{
                    setTimeout("window.location = 'BA2141105.html'",1000);
                }
            }
        },function(data){
            webToast("操作失败","middle");
        });
    }
}