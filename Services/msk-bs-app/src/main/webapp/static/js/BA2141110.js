/**
 * Created by zhu_kai1 on 2016/7/18.
 */

var buyersId="";
var buyersCode="";
var buyersName="";
var buyersType="";
var receiverProvince="";
var receiverCity="";
var receiverDistrict="";
var tempProducts=[];
var productCode=[];
var districtCode="";
var houseCode="";
var paymentType =2; // 1-在线支付 2-线下支付
var accessType="";
$(document).ready(function () {
    /**初始化默认合计**/
    BA2141110.accounPrice(0,0);
     houseCode = localStorage.houseCode;
    accessType =localStorage.accessType;
    var slRecbookId = BA2141110.QueryString("slRecbookId");
    if(slRecbookId !=null && slRecbookId !=''){
        BA2141110.findHouseAddress(slRecbookId);
    }
    BA2141110.findOrderDetail(houseCode,accessType);
});

var BA2141110 ={
    QueryString: function (val) {
        var uri = window.location.search;
        var re = new RegExp("" + val + "=([^&?]*)", "ig");
        return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
    },

    findHouseAddress :function  (slRecbookId){
    var flickerAPI = url+'msk-bs/api/bs/find/HouseBookByRecbookId';
    var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {"slRecbookId": slRecbookId}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'JSON',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "F"){
                alert("error");
            }else{
                var result = data.result;
                $(".buyer").text(result.buyerName);
                $(".tum").text(result.telNum);
                $(".address").text(result.fullAddress);
                $(".add_address").hide();
                $("#hiddiv").show();
                receiverProvince = result.provinceCode;
                receiverCity = result.cityCode;
                receiverDistrict = result.districtCode;
            }
        },
        error:function(){
            alert("error");
        }
    });
},
/**获取订单详情列表**/
findOrderDetail :function (houseCode,accessType){
    // 获取当前登录者的id
    var flickerAPI = url + 'msk-bs/api/bs/find/orderDetail';
    var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {"buyersId": houseCode,"buyersType":accessType,"status":"1"}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'JSON',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "F"){
                alert("error");
            }else{
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
                    cloneObj.find("div.breed")[0].innerText = breedName;
                    cloneObj.find("span.featureClass")[0].innerText = featureName;
                    cloneObj.find("span.gradeClass")[0].innerText = gradeName;
                    cloneObj.find("span.normsClass")[0].innerText = weightName;
                    cloneObj.find("span.unitPrice")[0].innerText = price;
                    cloneObj.find("span.num")[0].innerText = num;
                    cloneObj.appendTo("#productList");
                    priceSum+=parseFloat(price*num);
                    totalSum +=parseInt(num);
                    var products = {"pdCode":data.result[i].pdCode,"pdName":breedName,"orderPrice":price,"orderQty":num}
                    tempProducts.push(products);
                    productCode.push(data.result[i].pdCode);
                }
                BA2141110.accounPrice(priceSum,totalSum);
            }
        },
        error:function(){
            alert("error");
        }
    });
},
    accounPrice :function (priceSum,totalSum){
        var yunFei = parseFloat($(".yunFei").text());
        var sumPrice = parseFloat(priceSum+yunFei);
        $(".total").find("span.price")[0].innerText =Math.round(priceSum*100)/100;
        $(".total").find("span.freight")[0].innerText =yunFei;
        $(".total").find("span.sum")[0].innerText =Math.round(sumPrice*100)/100;
        // 共计多少件商品、总价格
        $(".totalNum").find("span.number")[0].innerText =totalSum;
        $(".totalNum").find("span.totalPrice")[0].innerText =Math.round(sumPrice*100)/100;
    },

/**跳转到地址新增页面**/
tapAddress :function (){
    window.location = "BA2141112.html?flag=fromOrder";
},
returnTo:function(){
    window.location = "BA2141109.html";
},
/**货到付款checkbox**/
changeImg :function(){
    if(paymentType ==2){
        $(".chooseImg").attr("src", "resources/images/choose2.png");
        paymentType=1;
    }else{
        $(".chooseImg").attr("src", "resources/images/choose1.png");
        paymentType=2;
    }
},

/**校验买家是否存在**/
checkBuyerInfo :function(){
    var flickerAPI = url+'msk-buyers/api/by/basicInfo/queryByTel';
    var tel =$(".tum").text();
    var boolean = false;
    var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {"telNo": tel}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'JSON',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "F"){
                alert("没有该用户，请先帮其注册");
            }else{
                var result = data.result;
                boolean = true;
                buyersId = result.buyerId;
                buyersCode=result.buyerCode;
                buyersName = result.buyerName;
                buyersType = result.superiorType;
            }
        },
        error:function(){
            alert("error");
        }
    });
    return boolean;
},
    /**提交订单**/
    commitOrder:function (){
        BA2141110.getSlCode(houseCode);
        // TODO 此处考虑到跨域
    if(districtCode!=null && districtCode !=''){
        var orderAmount = $(".price").text();
        var receiverName = $(".buyer").text();
        var receiverTel =$(".tum").text();
        var receiverAddr = $(".address").text();
        var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124",
            "param": {"orderTaker":houseCode,"orderTakerType":2,"districtCode":districtCode,"buyersId":buyersId, "buyersCode":buyersCode,"buyersName":buyersName,
                "buyersType":buyersType,"sellerCode":"0000000","sellerName":"神农客平台","orderAmount":orderAmount,"paymentType":paymentType,
                "receiverInfo":{"receiverName":receiverName,"receiverTel":receiverTel,"receiverProvince":receiverProvince,"receiverCity":receiverCity,
                    "receiverDistrict":receiverDistrict,"receiverAddr":receiverAddr},
                "products":tempProducts}};
        var flickerAPI =url+'msk-web/api/v1/so/sdo/distribution/create';
        if(BA2141110.checkBuyerInfo()){
            $.ajax({
                type : "POST",
                type : "GET",
                async:false,
                url:flickerAPI,
                timeout:60,
                dataType:'json',
                contentType:"application/json",
                data:JSON.stringify(paramData),
                dataType:'jsonp',
                success:function(data){
                    if(data.status == "F"){
                        alert("error");
                    }else{
                        BA2141110.modifyOrder(houseCode,productCode,accessType);
                        $(".add_address").show();
                        $("#hiddiv").hide();
                    }
                },
                error:function(){
                    alert("error");
                }
            });
        }
    }
},

/**根据当前管家所属买手所在的区域的code**/
getSlCode:function (houseCode){
    var flickerAPI = url+'msk-bs/api/bs/searchSlCode';
    var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {"houseCode":houseCode}};
    var slCode="";
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'JSON',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "F"){
                alert("error");
            }else{
                districtCode =   data.result.lgcsAreaCode;
            }
        },
        error:function(){
            alert("error");
        }
    });
    return districtCode;
},

/**更新购物车明细信息**/
modifyOrder :function (houseCode,productCode,accessType){
    var flickerAPI = url+'msk-bs/api/bs/updateOrder';
    var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {"buyersId":houseCode,"buyersType":accessType,"productCodeList":productCode,"status":"1","removeReason":"1"}};
    var slCode="";
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'JSON',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "F"){
                alert("error");
            }else{
                // 提交订单成功
                window.location = "BA2141110.html";
            }
        },
        error:function(){
            alert("error");
        }
    });
}

}