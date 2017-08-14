/**
 * Created by zhu_kai1 on 2016/7/18.
 */

$(function(){
    /**买手code，获取登录的买手code**/
    //var slcode = localStorage.slCode;
        /**管家code获取登录的管家code**/
    //var houseCode = localStorage.houseCode;
    var houseCode = 43;
    var slCode= '6220500101';
    if(houseCode !=null && houseCode !=''){
        slCode = getSlCode(houseCode);
    }
    if(slCode !=null && slCode !=''){
        getStockInfo(slCode);
    }
});

/**查询卖家可用库存**/
function getStockInfo(slCode){
    var flickerAPI = url+'msk-stock/api/so/queryStockBySeller/list';
    var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {"pdList": [{"slCode":slCode}]}};
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
                var length = data.result.pdStockList.length;
                var table="<table>"
                var title =table+"<tr><td>物流区</td><td>产品编码</td><td>名称</td><td>可用库存</td><td>库存数量</td></tr>"
                var td="";
                for(var i=0;i<length;i++){
                    var pdStock = data.result.pdStockList[i];
                    td+="<tr><td>"+pdStock.lgcsName+"</td><td>"+pdStock.pdCode+"</td><td>"+pdStock.pdName+"</td><td>"+pdStock.enabledStockQty+"</td><td>"+pdStock.stockQty+"</td></tr>";
                }
                var tr = title+td+"</table>";
                $("#tableData").append(tr);
            }
        },
        error:function(){
            alert("error");
        }
    });
}


/**通过管家Code查询对应的买手**/
function getSlCode(houseCode){
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
               slCode =   data.result.slCode;
            }
        },
        error:function(){
            alert("error");
        }
    });
    return slCode;
}