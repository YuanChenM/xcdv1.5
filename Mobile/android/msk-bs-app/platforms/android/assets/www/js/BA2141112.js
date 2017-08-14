/**
 * Created by zhu_kai1 on 2016/7/13.
 */
var accessType="";
var backUrl="";
jQuery(document).ready(function (){
    accessType =localStorage.accessType;
    setTimeout("BA2141112.selectAddInfo()",300);
    BA2141112.bindFh();
    BA2141112.bindReturnAdd();
    var  isBuy = BA2141112.QueryString("isBuy");
    var priceCycle = BA2141112.QueryString("priceCycle");
    if(null !=isBuy && isBuy !=''){
        var productCode = BA2141112.QueryString("productCode");
        var productNum = BA2141112.QueryString("productNum");
        var productPrice = BA2141112.QueryString("productPrice");
        var pdName = BA2141112.QueryString("pdName");
        backUrl = "BA2141110.html?isBuy=1&productCode=" + productCode + "&productNum=" + productNum + "&productPrice=" + productPrice+"&priceCycle="+priceCycle+"&pdName="+pdName;
    }else{
        var carID = BA2141112.QueryString("carId");
        var carDetailIds = BA2141112.QueryString("carDetailIds");
        backUrl = "BA2141110.html?carId="+carID+"&carDetailIds="+carDetailIds+"&priceCycle="+priceCycle;
    }
});

var BA2141112 = {
    chooseHtml:function(){
        var  isBuy = BA2141112.QueryString("isBuy");
        var priceCycle = BA2141112.QueryString("priceCycle");
        if(null !=isBuy && isBuy !=''){
            var productCode = BA2141112.QueryString("productCode");
            var productNum = BA2141112.QueryString("productNum");
            var productPrice = BA2141112.QueryString("productPrice");
            var pdName = BA2141112.QueryString("pdName");
            window.location.href = "BA2141110.html?isBuy=1&productCode=" + productCode + "&productNum=" + productNum + "&productPrice=" + productPrice+"&priceCycle="+priceCycle+"&pdName="+pdName;
        }else{
            var carID = BA2141112.QueryString("carId");
            var carDetailIds = BA2141112.QueryString("carDetailIds");
            window.location.href = "BA2141110.html?carId="+carID+"&carDetailIds="+carDetailIds+"&priceCycle="+priceCycle;
        }
    },
    /**返回事件绑定*/
    bindFh:function(){
        $(".head-back").bind("touchstart",function(){
            BA2141112.chooseHtml();
        })
    },
    QueryString: function (val) {
        var uri = window.location.search;
        var svalue =uri.match(new RegExp("[\?\&]" + val + "=([^\&]*)(\&?)","i"));
        return svalue ? svalue[1] : svalue;
    },
    selectAddInfo:function(){
        var  url = ConstantDef.getFindHouseBookServerUrl();
        var paramData="";
        if(accessType == 2){
            paramData = {param: {"accessType":accessType,"houseCode": localStorage.houseCode}};
        }else{
            paramData = {param: {"accessType":accessType,"slCode": localStorage.slCode}};
        }

        HttpClient.post(url,paramData,function(data){
            if(data.status == "F"){
                webToast("操作失败","middle");
            }else{
                var length = data.result.length;
                var obj = $("ul li:eq(0)");
                for(var i=0;i<length;i++){
                    var result =  data.result[i];
                    var id = result.slRecbookId;
                    var cloneObj = obj.clone();
                    cloneObj.show();
                    var buyerName = result.buyerName;
                    var telNum = result.telNum;
                    var fullAddress = result.fullAddress;
                    console.log(buyerName+","+telNum+","+fullAddress)
                    cloneObj.find("span.name")[0].innerText = buyerName;
                    cloneObj.find("span.phone")[0].innerText = telNum;
                    cloneObj.find("span.text")[0].innerText = fullAddress;
                     cloneObj.find(".full_info").attr("id","full_info"+i);
                    cloneObj.find(".edit_container").attr("id","edit"+i);
                    cloneObj.find(".del_container").attr("id","del"+i);
                    cloneObj.appendTo("#addresses");
                    var orderObj = document.getElementById("full_info"+i);
                    (function(id){
                        orderObj.addEventListener("touchstart", function (event) {
                            BA2141112.bindReturnOrderInfo(id);
                        },false);
                    })(id);

                    var editObj =  document.getElementById("edit"+i);
                    (function(id){
                        editObj.addEventListener("touchstart", function (event) {
                            BA2141112.bindReturnEditManagement(id);
                        },false);
                    })(id);
                    var delObj = document.getElementById("del"+i);
                    (function(id){
                        delObj.addEventListener("touchstart", function (event) {
                            BA2141112.modifyAddress(id);
                        },false);
                    })(id);
                }
            }
        },function(data){
            webToast("操作失败","middle");
        });
    },
bindReturnEditManagement:function(slRecbookId){
    // 跳转到编辑地址页面
    if(null !=slRecbookId && slRecbookId !=''){
        var  isBuy = BA2141112.QueryString("isBuy");
        var priceCycle = BA2141112.QueryString("priceCycle");
        if(null !=isBuy && isBuy !=''){
            var productCode = BA2141112.QueryString("productCode");
            var productNum = BA2141112.QueryString("productNum");
            var productPrice = BA2141112.QueryString("productPrice");
            var pdName = BA2141112.QueryString("pdName");
            window.location.href = "BA2141113.html?slRecbookId="+slRecbookId+"&isBuy=1&productCode=" + productCode + "&productNum=" + productNum + "&productPrice=" + productPrice+"&priceCycle="+priceCycle+"&pdName="+pdName;
        }else{
            var carID = BA2141112.QueryString("carId");
            var carDetailIds = BA2141112.QueryString("carDetailIds");
            window.location.href ="BA2141113.html?slRecbookId="+slRecbookId+"&carID="+carID+"&carDetailIds="+carDetailIds+"&priceCycle="+priceCycle;
        }
    }
},
    bindReturnAdd:function(){
        //新增
        $(".all").bind("touchstart",function(){
            var  isBuy = BA2141112.QueryString("isBuy");
            var priceCycle = BA2141112.QueryString("priceCycle");
            if(null !=isBuy && isBuy !=''){
                var productCode = BA2141112.QueryString("productCode");
                var productNum = BA2141112.QueryString("productNum");
                var productPrice = BA2141112.QueryString("productPrice");
                var pdName = BA2141112.QueryString("pdName");
                window.location.href = "BA2141113.html?isBuy=1&productCode=" + productCode + "&productNum=" + productNum + "&productPrice=" + productPrice+"&priceCycle="+priceCycle+"&pdName="+pdName;
            }else{
                var carID = BA2141112.QueryString("carId");
                var carDetailIds = BA2141112.QueryString("carDetailIds");
                window.location = "BA2141113.html?carID="+carID+"&carDetailIds="+carDetailIds+"&priceCycle="+priceCycle;
            }
        });
    },
    bindReturnOrderInfo:function(slRecbookId){
         BA2141112.returnOrderChoose(slRecbookId);
    },
    modifyAddress:function (slRecbookId){
            var url = ConstantDef.getDeteleHouseBookServerUrl();
            var paramData = {param: {"accessType":accessType,slRecbookId: slRecbookId,updId:'admin'}};
            popTipShow.confirm('地址删除','确定删除收货地址?此操作不可逆！',['确 定','取 消'],
                function(e){
                    //callback 处理按钮事件
                    var button = $(e.target).attr('class');
                    if(button == 'ok'){
                        //按下确定按钮执行的操作
                        HttpClient.post(url,paramData,function(data){
                            if(data.status == "F"){
                                webToast("删除失败","middle");
                            }else{
                                var  isBuy = BA2141112.QueryString("isBuy");
                                var priceCycle = BA2141112.QueryString("priceCycle");
                                if(null !=isBuy && isBuy !=''){
                                    var productCode = BA2141112.QueryString("productCode");
                                    var productNum = BA2141112.QueryString("productNum");
                                    var productPrice = BA2141112.QueryString("productPrice");
                                    var pdName = BA2141112.QueryString("pdName");
                                    window.location.href = "BA2141112.html?isBuy=1&productCode=" + productCode + "&productNum=" + productNum + "&productPrice=" + productPrice+"&priceCycle="+priceCycle+"&pdName="+pdName;
                                }else{
                                    var carID = BA2141112.QueryString("carId");
                                    var carDetailIds = BA2141112.QueryString("carDetailIds");
                                    window.location.href = "BA2141112.html?carId="+carID+"&carDetailIds="+carDetailIds+"&priceCycle="+priceCycle;
                                }
                            }
                        },function(data){
                            webToast("操作失败","middle");
                        });
                        this.hide();
                    }
                    if(button == 'cancel') {
                        //按下取消按钮执行的操作
                        this.hide();
                    }
                }
            );
      },
    returnOrderChoose:function(slRecbookId){
        var  isBuy = BA2141112.QueryString("isBuy");
        var priceCycle = BA2141112.QueryString("priceCycle");
        if(null !=isBuy && isBuy !=''){
            var productCode = BA2141112.QueryString("productCode");
            var productNum = BA2141112.QueryString("productNum");
            var productPrice = BA2141112.QueryString("productPrice");
            var pdName = BA2141112.QueryString("pdName");
            window.location.href = "BA2141110.html?slRecbookId="+slRecbookId+"&isBuy=1&productCode=" + productCode + "&productNum=" + productNum + "&productPrice=" + productPrice+"&priceCycle="+priceCycle+"&pdName="+pdName;
        }else{
            var carID = BA2141112.QueryString("carId");
            var carDetailIds = BA2141112.QueryString("carDetailIds");
            window.location.href = "BA2141110.html?slRecbookId="+slRecbookId+"&carID="+carID+"&carDetailIds="+carDetailIds+"&priceCycle="+priceCycle;
        }
    }
}





