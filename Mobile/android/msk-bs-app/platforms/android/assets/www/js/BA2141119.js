/**
 * Created by zhu_kai1 on 2016/7/18.
 */
var houseCode;
var slCode;
var vlgcsCode;
var accessType;
var backUrl="";
$(function(){
    vlgcsCode =  localStorage.lgcsCode;
    /**管家code获取登录的管家code**/
    houseCode = localStorage.houseCode;
    accessType =localStorage.accessType;  // 2-管家，3-买手
    if((houseCode !=null && houseCode !='') && accessType ==2){
        slCode =  localStorage.sellerCode;
        setTimeout("BA2141119.getStockInfo()",100);
    }
    if(accessType == 3){
        /**买手code，获取登录的买手code**/
        slCode = localStorage.slCode;
        setTimeout("BA2141119.getPlatformStockInfo()",100);
    }
    BA2141119.bindFh();
    if (accessType == 2) {
        backUrl = 'BA2141201.html'
    } else {
        backUrl = 'BA2141121.html'
    }
});
var BA2141119 = {

    /**返回事件绑定*/
    bindFh: function () {
        $(".head-back").bind("touchstart", function () {
            if (accessType == 2) {
                window.location.href = backUrl;
            } else {
                window.location.href = backUrl;
            }
        })
    },
    /**查询卖家可用库存**/
    getStockInfo: function () {
        var url = ConstantDef.getFindProductStockUrl();
        var paramData = {
            param: {
                "slCode": slCode,
                "platformType": 1,
                "sellerType": 2,
                "lgcsCode": vlgcsCode
            }
        };
        HttpClient.post(url, paramData, function (data) {
            if (data.status == "F") {
                webToast("没有对应的库存信息", "middle");
            } else {
                var stock = data.result;
                for (var i = 0; i < stock.length; i++) {
                    var obj = $("ul:eq(0)");
                    var pdStock = stock[i];
                    var cloneObj = obj.clone();
                    cloneObj.show();
                    cloneObj.find("span.con")[0].innerText = pdStock.lgcsName;
                    cloneObj.find("span.con")[1].innerText = pdStock.pdCode;
                   /* cloneObj.find("span.con")[2].innerText = pdStock.pdName;*/
                    cloneObj.find("span.con")[2].innerText = pdStock.stockCnt;
                    /*cloneObj.find("span.con")[4].innerText = pdStock.onhandQty;*/
                    cloneObj.appendTo(".kcxx-list");
                }

            }
        }, function (data) {
            webToast("操作失败", "middle");
        });
    },
    /**查询平台可用库存**/
    getPlatformStockInfo: function () {
        var url = ConstantDef.getFindProductStockUrl();
        var paramData = {
            param: {
                slCode: "0000000",
                platformType: 1,
                sellerType: 1,
                lgcsCode: vlgcsCode
            }
        };
        console.log(JSON.stringify(paramData));
        HttpClient.post(url, paramData, function (data) {
            if (data.status == "F") {
                webToast("没有对应的库存信息", "middle");
            } else {
                var stock = data.result;
                for (var i = 0; i < stock.length; i++) {
                    var obj =  $("ul:eq(0)");
                    var pdStock = stock[i];
                    var cloneObj = obj.clone();
                    cloneObj.show();
                    cloneObj.find("span.con")[0].innerText = pdStock.lgcsName;
                    cloneObj.find("span.con")[1].innerText = pdStock.pdCode;
                  /*  cloneObj.find("span.con")[2].innerText = pdStock.pdName;*/
                    cloneObj.find("span.con")[2].innerText = pdStock.stockCnt;
                    /*cloneObj.find("span.con")[4].innerText = pdStock.onhandQty;*/
                    cloneObj.appendTo(".kcxx-list");
                }
            }
        }, function (data) {
            webToast("操作失败", "middle");
        });
    }
}