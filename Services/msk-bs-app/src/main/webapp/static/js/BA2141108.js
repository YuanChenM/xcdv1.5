/**
 * Created by yang_chunyan on 2016/7/13.
 */

var shopCarCommon = [];
function loadShoppingCar(){
    var buyersId =localStorage.houseCode;
    var buyersType =localStorage.accessType;
    var flickerAPI = url+'api/bs/find/orderDetail';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param":{"buyersId": buyersId,"buyersType":buyersType,"status":"0"}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'JSON',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            shopCarCommon = data.result;
            for(var i = 0;i<shopCarCommon.length;i++){
                var obj = $("ul li:eq(0)"); ///document.getElementById("list").getElementsByTagName("li");
                var cloneObj = obj.clone();
                cloneObj.show();
                var breedName = shopCarCommon[i].breedName;
                var pdName = shopCarCommon[i].pdName;
                var price = shopCarCommon[i].oldPrice;
                var pdNum = shopCarCommon[i].pdNum;
                var pdCode = shopCarCommon[i].pdCode;
                cloneObj.find("div.breed")[0].innerText = breedName;
                cloneObj.find("span[name='neirong']")[0].innerHTML = pdName;
                cloneObj.find("input[name='price']")[0].value = price;
                cloneObj.find("span[name='price']")[0].innerText = "￥" + price;
                cloneObj.find("input[name='pdCode']")[0].value = pdCode;
                cloneObj.find(".spinner").spinner(null,pdNum);
                //cloneObj.removeClass("display");
                cloneObj.appendTo("#list");
            }
        },
        error:function(){
            alert("error");
        }
    });
    $(".check").click(function(){
        if($(this).hasClass("on")){
            $(this).removeClass("on");
        }else{
            $(this).addClass("on");
        }
        checkBoxEvent();
    });

    $(".checkAll").click(function(){
        var isCheck = $(this).hasClass("on");
        if(isCheck){
            $(this).removeClass("on");
        }else{
            $(this).addClass("on");
        }
        $(".check").each(function(){
            if(isCheck){
                $(this).removeClass("on");
            }else{
                $(this).addClass("on");
            }
        });
        checkBoxEvent();
    });
}

var checkBoxEvent = function(){
    var price = 0;
    var number = 0;
    $(".check").each(function(){
        if($(this).hasClass("on")){
            var num = $(this).parent().parent().parent().find(".product").find("input[name='num']")[0].value;
            var pri = this.children[0].value;
            if(pri){
                price = price + parseInt(pri);
                number = number + parseInt(num);
            }
        }
    });
    $("#price").html("￥" + price);
    $("#num").html(number);
}

function buyProduct(){
        var obj = [];
        $(".check").each(function(){
            if($(this).hasClass("on")){
                var id = this.children[1].value;
                obj.push(id);
            }
        });
        if(obj.length>0){
            var buyersId =localStorage.houseCode;
            var buyersType =localStorage.accessType;
            var flickerAPI = url+'api/bs/updateOrder';
            var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param":{"buyersId": buyersId,"buyersType":buyersType,"modifyStatus":"1","productCodeList":obj,"status":"0"}};
            $.ajax({
                type : "POST",
                async:false,
                url:flickerAPI,
                timeout:60,
                dataType:'JSON',
                contentType:"application/json",
                data:JSON.stringify(paramData),
                success:function(data){
                    if(data.status == 'S'){
                        window.location = 'BA2141110.html';
                    }
                },
                error:function(){
                    alert("error");
                }
            });
        }
    }