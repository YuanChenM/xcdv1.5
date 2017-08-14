/**
 * Created by zhu_kai1 on 2016/7/13.
 */
$(function(){
    var houseCode = localStorage.houseCode;
    var flag = request.QueryString("flag");
    selectAddInfo(houseCode,flag);
});

var request = {
    QueryString: function (val) {
        var uri = window.location.search;
        var re = new RegExp("" + val + "=([^&?]*)", "ig");
        return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
    }
}

function selectAddInfo(houseCode,flag){
    var flickerAPI = url+'msk-bs/api/bs/search/houseBook';
    var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {"houseCode": houseCode}};
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
                alert("失败");
            }else{
                var length = data.result.length;
                var obj = $("ul li:eq(0)");
                for(var i=0;i<length;i++){
                    var result =  data.result[i];
                    var id = result.slRecbookId;
                        var cloneObj = obj.clone();
                        console.log(cloneObj);
                        cloneObj.show();
                        var buyerName = result.buyerName;
                        var telNum = result.telNum;
                        var fullAddress = result.fullAddress;
                        console.log(buyerName+","+telNum+","+fullAddress)
                        cloneObj.find("span.featureClass")[0].innerText = buyerName;
                        cloneObj.find("span.telNum")[0].innerText = telNum;
                        cloneObj.find("span.fullAddr")[0].innerText = fullAddress;
                        cloneObj.find("a.edit12").attr("onclick","returnAddManagement("+id+")");
                        cloneObj.find("a.delete12").attr("onclick","modifyAddress("+id+")");
                        cloneObj.appendTo("#addresses");
                }
            }
        },
        error:function(){
            alert("error");
        }
    });
}

function returnAddManagement(slRecbookId){
    // 跳转到编辑地址页面
    if(null !=slRecbookId && slRecbookId !=''){
        window.location.href ="BA2141113.html?slRecbookId="+slRecbookId;
    }else{
        //新增
        window.location = "BA2141113.html"
    }
}

function returnOrderInfo(slRecbookId){
    // 跳转到订单页面
    window.location.href = "BA2141110.html?slRecbookId="+slRecbookId;
}


function modifyAddress(slRecbookId){
    var flickerAPI = url+'msk-bs/api/bs/delete/houseBook';
    var paramData = {"siteCode": "abcd","auth": "xxxx","loginId": "a124","param": {"slRecbookId": slRecbookId,'updId':'admin'}};
    var message = "确定删除收货地址?此操作不可逆！";
    if(confirm(message)){
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
                    alert("失败");
                }else{
                    window.location = "BA2141112.html";
                }
            },
            error:function(){
                alert("error");
            }
        });
    }
}



