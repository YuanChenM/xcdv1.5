/**
 * Created by tao_zhifa on 2016/8/1.
 */
//收获地址
var receiveAddrArray = [];

$(function(){
    if(localStorage.enterFlg == "eidt" || localStorage.enterFlg == "view"){
        $("#titleDiv").text("买家信息编辑 - 收货信息");
        $("#saveButtonDiv").text("保存");
    }else{
        $("#titleDiv").text("新买家注册 - 收货信息");
        $("#saveButtonDiv").text("下一步 3/8");
    }

    //获取买家收货地址
    var flickerAPI = url + '/by/deliveryAddr/query';
    var paramData = {"client": "abcd", "auth": "xxxx", "loginid": "a124", "param": {"buyerId": localStorage.buyerId,"pageCount":10,"startNo":0}};
    $.ajax({
        type:"POST",
        async: false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success: function (data) {
            if(data.status == "S"){
                receiveAddrArray = data.result.brOBuyerInfoList;
            }
        },
        error: function () {
            alert("error")
        }
    });
    //收货地址显示
    if (receiveAddrArray.length > 0) {
        var receiveAddrStr = "";

        for (var i = 0; i < receiveAddrArray.length; i++) {
            receiveAddrStr = receiveAddrStr + "<div id = '" + receiveAddrArray[i].id + "'style='width: 80%;margin-left:35px;background-color:#F6F8FA;color:#CECECE;text-align:left;text-shadow:none;font-size: 17px;line-height:45px;' onclick='receiveAddrModify(this)'>";
            receiveAddrStr = receiveAddrStr + "<td>" + receiveAddrArray[i].provinceName+"省</td>,";
            receiveAddrStr = receiveAddrStr + "<td>" + receiveAddrArray[i].cityName+"市</td>,";
            receiveAddrStr = receiveAddrStr + "<td>" + receiveAddrArray[i].districtName+"</td><br>";
            receiveAddrStr = receiveAddrStr + "<td>地址：" + receiveAddrArray[i].deliveryAddr+"</td><br>";
            receiveAddrStr = receiveAddrStr + "<td>收货联系人：" + receiveAddrArray[i].recPerName+"</td>&emsp;";
            receiveAddrStr = receiveAddrStr + "<td>联系电话：" + receiveAddrArray[i].recPerTel+"</td>&emsp;";
            if(receiveAddrArray[i].isDefault == "1"){
                receiveAddrStr = receiveAddrStr + "<td>默认地址</td>&emsp;";
            }

            receiveAddrStr = receiveAddrStr + "</div><br>";
        }
        $("#receiveAddrDiv").append(receiveAddrStr);

    }
});

//买家收货地址编辑/新增
function receiveAddrModify(obj) {
    if (obj.id == "receiveAddrAdd") {
        $("#enterFlag").val("add");
        $("#header").text("买家收货地址新增");
        var id = 1;
        window.location = "BY12123301.html";
    } else {
        var addr = obj.innerText.substring(5, obj.innerText.length);
        $("#header").text("买家收货地址修改");
        $("#receiveAddr").val(addr);
        var id = obj.id;
        window.location = "BY12123301.html?id="+id;
    };
}

function buyerReceiveInfo(){
    window.location = "BY121234.html";
}

//返回前一个画面
function returnPage() {
    if (localStorage.enterFlg == "edit") {
        window.location = "BY121211.html";
    } else {
        window.location = "BY121207.html";
    }
}