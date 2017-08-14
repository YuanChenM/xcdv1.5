/**
 * Created by yang_chunyan on 2016/7/13.
 */
var shopCarCommon = [];
var dataCount = 0;
$("#messageLoading").modal();
$("#messageLoading").show();
$('#messageLoading').addClass('animated zoomIn');
var BA2141108 = {
    loadData: function () {
        var buyersId = localStorage.houseCode;
        var buyersType = localStorage.accessType;
        var url = ConstantDef.getFindOrderDetailServerUrl();
        console.info(url);
        var data = {
            param: {
                buyersId: buyersId,
                buyersType: buyersType,
                lgcsCode:localStorage.lgcsCode,
                status: "0"
            }
        };
        console.info(data);
        HttpClient.post(url, data, function (data) {
            $("#simplemodal-container").hide();
            $('.loader-inner').addClass('animated zoomOut');
            $("#messageLoading").addClass('animated zoomOut');
            $("#simplemodal-overlay").remove();
            console.log(data);
            if(data.result.length==0){
                $(".main").html(" <div style='position: absolute;top: 30%;left: 50%;margin-top: -75px;margin-left: -75px'> " +
                    "<img style='width: 150px'  src='image/shopping_cart.png'> " +
                    "<div style='margin-top: 20px;font-size: 1.5rem;text-align: center;font-family:'微软雅黑''>您的购物车空空如也</div> " +
                    "</div>");
            } else if (data.status == "S") {
                var myDate1 = new Date();
                console.info("over================================" + myDate1.getSeconds());
                shopCarCommon = data.result;
                //data.result.pdInfo[0].pdName
                //alert(JSON.stringify(data.result));
                var obj = $("ul li:eq(0)"); ///document.getElementById("list").getElementsByTagName("li");
                dataCount = shopCarCommon.length;
                for (var i = 0; i < shopCarCommon.length; i++) {
                    var carId = shopCarCommon[0].carId;
                    $("#carId").val(carId);
                    var cloneObj = obj.clone(true);
                    cloneObj.show();
                    var breedName = data.result[i].breedName;
                    //.split("/")[2]
                    var pdName = shopCarCommon[i].pdName;
                    var pdCode = shopCarCommon[i].pdCode;
                    var price = shopCarCommon[i].oldPrice;
                    var pdNum = shopCarCommon[i].pdNum;
                    var priceCycle = shopCarCommon[i].priceCycle;
                    if (!breedName) {
                        breedName = pdName.split("|")[0];
                        pdName = "产品特征：" + pdName.split("|")[1] + "包装净重:" + pdName.split("|")[2] + "产品等级：" + pdName.split("|")[3]
                    }
                    var ppName = "";
                    var indexPackage = pdName.indexOf("产品等级");
                    ppName = pdName.substr(0, indexPackage);
                    //if (ppName.length > 25) {
                        var indexgrade = ppName.indexOf("包装净重");
                        var featrue = '';
                        featrue = ppName.substring(0, indexgrade);

                        var gradeIn = 23 - featrue.length;
                        if (gradeIn >= 15) {
                            featrue += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + ppName.substring(indexgrade);
                        } else {
                            featrue += "&nbsp;&nbsp;&nbsp;&nbsp;" + ppName.substring(indexgrade);
                        }
                        ppName = featrue;
                   // }
                    //ppName += pdName.substr(indexPackage);
                    // pdName = ppName;
                    var carDetailId = shopCarCommon[i].carDetailId;
                    var isOffTheShelf = shopCarCommon[i].isOffTheShelf;
                    cloneObj.find("div.breed")[0].innerText = breedName;
                    cloneObj.find("p[name='neirong']")[0].innerHTML = ppName;
                    cloneObj.find("p[name='weight']")[0].innerHTML = pdName.substr(indexPackage);
                    if (isOffTheShelf == "1") {
                        cloneObj.find("span[name='price']")[0].innerText = "已下架";
                        cloneObj.find("a.check")[0].hidden = true;
                        //cloneObj.find("div.num")[0].hidden = true;

                    } else {
                        cloneObj.find("span[name='price']")[0].innerText = "￥" + price;
                        cloneObj.find("input[name='priceCycle']")[0].value = priceCycle;
                        //cloneObj.find("a.check")[0].disabled
                    }

                    cloneObj.find("div.shownum")[0].innerHTML = "x " + pdNum;
                    cloneObj.find("input[name='price']")[0].value = price;
                    cloneObj.find("input[name='carDetailId']")[0].value = carDetailId;
                    cloneObj.find("input[name='pdNum']")[0].value = pdNum;
                    cloneObj.find("input[name='pdCode']")[0].value = pdCode;
                    cloneObj.find("input[name='breedName']")[0].value = breedName;
                    //cloneObj.find(".spinner").spinner(null,pdNum);
                    cloneObj.appendTo("#list");
                }

                $("ul li:first").remove();
                if (shopCarCommon.length >= 4) {
                    $("#list").append("<li style='height: 250px;'></li>");
                }
            }else {
                /*alert(data.message);*/
                webToast("查询购物车明细失败", "middle");
            }
            var myDate2 = new Date();
            console.info("end================================" + myDate2.getSeconds());
        }, function (data) {
           $("#messageLoadingText").text("操作失败");
            setTimeout(function () {
                $("#simplemodal-container").hide();
                $('.loader-inner').addClass('animated zoomOut');
                $("#messageLoading").addClass('animated zoomOut');
                $("#simplemodal-overlay").remove();
            },2000);
        });
    },
    /**返回事件绑定*/
    bindFh: function () {
        $("#BA2141109_return").bind("touchstart", function () {
            eventBackButton();
        })
    },
    /**选择事件绑定*/
    bindCheck: function () {
        $(".check").on("touchstart", function () {
            if ($(this).hasClass("on")) {
                $(this).removeClass("on");
                $("#checkAll").removeClass("on");
            } else {
                var i=1;
                $(".check").each(function () {
                    if ($(this).hasClass("on")) {
                        i++;
                    }
                });
                $(this).addClass("on");
                if(i==dataCount){
                    $("#checkAll").addClass("on");
                }
            }
            BA2141108.checkBoxEvent();
        })
    },
    /**编辑事件绑定*/
    bindEdit: function () {
        $("#edit").bind("touchstart", function () {
            window.location.href = 'BA2141109.html'
        });
    },
    /**全选事件绑定*/
    bindCheckAll: function () {
        $(".checkAll").bind("touchstart", function () {
            var isCheck = $(this).hasClass("on");
            if (isCheck) {
                $(this).removeClass("on");
                $(".check").removeClass("on");
            } else {
                $(this).addClass("on");
                $(".check").addClass("on");
            }
            BA2141108.checkBoxEvent();
        });
    },
    checkBoxEvent: function () {
        var price = 0;
        var number = 0;
        $(".check").each(function () {
            if ($(this).hasClass("on")) {
                var num = this.children[2].value;
                //var num = $(this).parent().parent().parent().find(".product").find("input[name='num']")[0].value;
                var pri = this.children[0].value;
                if (pri) {
                    price = Number(price) + Number(pri) * parseInt(num);
                    number = number + parseInt(num);
                }
            }
        });
        $("#price").html("￥" + parseFloat(price).toFixed(2));
        $("#num").html(number);
    },
    bindBuyBtn: function () {
        $("#buy").bind("touchstart", function () {
            var num = $(".choosed").find("span[id='num']")[0].innerHTML;
            if (num == 0) {
                webToast("请选择商品", "middle");
                return;
            }
            var carId = $("#carId").val();
            var obj = [];
            var ids = "";
            var priceCycle = '';
            var pdCodes = '';
            $(".check").each(function () {
                if ($(this).hasClass("on")) {
                    var o = new Object();
                    var code = this.children[4].value;
                    priceCycle = this.children[3].value;
                    var num = this.children[2].value;
                    //var num = $(this).parent().parent().parent().find(".product").find("input[name='num']")[0].value;
                    var id = this.children[1].value;
                    if (ids) {
                        ids += "," + id;
                        pdCodes += "," + code;
                    } else {
                        ids += id;
                        pdCodes += code;
                    }
                    o.carDetailId = id;
                    o.carId = carId;
                    o.pdNum = num;
                    if (id != '') {
                        obj.push(o);
                    }
                }
            });
            if (obj == '') {
                webToast("请选择商品", "middle");
            } else {
                var url = ConstantDef.getFindProductStockUrl();
                var accessType = localStorage.accessType;
                var slCode = "0000000";
                var sellerType = 1;
                if (accessType == 2) {
                    slCode = localStorage.sellerCode;
                    sellerType = 2;
                }
                var data = {
                    param: {
                        pdCodeList: pdCodes.split(","),
                        lgcsCode: localStorage.lgcsCode,
                        sellerType: sellerType,
                        slCode: slCode
                    }
                };
                HttpClient.post(url, data, function (data) {
                    if (data.status == "S") {
                        var exist = false;
                        var product = data.result;
                        if ('' != product) {
                            var i = 0;
                            $(".check").each(function () {
                                if($(this).hasClass("on")){
                                    var num = this.children[2].value;
                                    if (product.length > i && this.children[4].value == product[i].pdCode  && num > product[i].stockCnt) {
                                        var breedName = this.children[5].value;
                                        exist = true;
                                        webToast(breedName + "库存不足", "middle");
                                        return false;
                                    }
                                    i++;
                                }
                            })
                        }
                        if(!exist)
                            window.location = 'BA2141110.html?carId=' + carId + "&carDetailIds=" + ids + "&priceCycle=" + priceCycle;
                    }
                }, function (data) {
                    webToast("操作失败", "middle");
                });
            }
        })
    },
    bindIndex: function () {
        $("#index").bind("touchstart", function () {
            $("#car").removeClass("current");
            window.setTimeout($(this).addClass("current"), 200);
            window.location.href = "BA2141105.html";
        })
        $("#car").bind("touchstart", function () {
            window.location.href = "BA2141108.html";
        })
        $("#center").bind("touchstart", function () {
            $("#car").removeClass("current");
            window.setTimeout($(this).addClass("current"), 200);
            var accessType = localStorage.accessType;
            if (accessType == 3) {
                localStorage.html = "BA2141121.html";
                window.location.href = "BA2141121.html";
            } else if (accessType == 2) {
                localStorage.html = "BA2141201.html";
                window.location.href = "BA2141201.html";
            } else {
                window.location.href = 'BA2141101.html';
            }
        })
        $("#setting").bind("touchstart", function () {
            $("#car").removeClass("current");
            window.setTimeout($(this).addClass("current"), 300);
            window.location.href = "BA2141199.html";
        })
    },
    myinit: function () {
        BA2141108.bindIndex();
        BA2141108.bindCheck();
        BA2141108.bindCheckAll();
        BA2141108.loadData();
        BA2141108.bindBuyBtn();
        BA2141108.bindEdit();
        BA2141108.bindFh();
        document.addEventListener("backbutton", eventBackButton, false); //返回键
    }
}

//页面入口
function eventBackButton(){
    if(localStorage.html != null){
        window.location.href = localStorage.html;
    }else{
        window.location.href = 'BA2141105.html';
    }
}
var myDate = new Date();
console.info("start================================" + myDate.getSeconds());
window.onload = window.setTimeout(BA2141108.myinit, 200);

