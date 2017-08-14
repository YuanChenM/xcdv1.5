/**
 * Created by yang_chunyan on 2016/7/13.
 */
var shopCarCommon = [];
var forNum=0;
$("#messageLoading").modal();
$('#messageLoading').addClass('animated zoomIn');
var BA2141109 = {
    /**加载数据*/
    loadShoppingCar: function () {
        //var buyersId =1;
        var buyersId = localStorage.houseCode;
        var buyersType = localStorage.accessType;
        var url = ConstantDef.getFindOrderDetailServerUrl();
        var data = {
            param: {
                buyersId: buyersId,
                buyersType: buyersType,
                lgcsCode:localStorage.lgcsCode,
                status: "0"
            }
        };
        console.info(url);
        HttpClient.post(url, data, function (data) {
            $('.loader-inner').addClass('animated zoomOut');
            $("#messageLoading").addClass('animated zoomOut');
            $("#simplemodal-overlay").remove();
            if (data.status == "S") {
                shopCarCommon = data.result;
                for (var i = 0; i < shopCarCommon.length; i++) {
                    forNum+=1;
                    var carId = shopCarCommon[0].carId;
                    $("#carId").val(carId);
                    var obj = $("ul li:eq(0)"); ///document.getElementById("list").getElementsByTagName("li");
                    var cloneObj = obj.clone(true);
                    cloneObj.show();
                    var breedName = shopCarCommon[i].breedName;
                    var pdCode = shopCarCommon[i].pdCode;
                    var pdName = shopCarCommon[i].pdName;
                    var price = shopCarCommon[i].oldPrice;
                    var pdNum = shopCarCommon[i].pdNum;
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

                    var carDetailId = shopCarCommon[i].carDetailId;
                    var isOffTheShelf = shopCarCommon[i].isOffTheShelf;
                    cloneObj.find("div.breed")[0].innerText = breedName;
                    cloneObj.find("p[name='neirong']")[0].innerHTML = ppName;
                    cloneObj.find("p[name='weight']")[0].innerHTML = pdName.substr(indexPackage);
                    cloneObj.find("input[name='carDetailId']")[0].value = carDetailId;
                    cloneObj.find("input[name='pdCode']")[0].value = pdCode;
                    if (isOffTheShelf == "1") {
                        cloneObj.find("span[name='price']")[0].innerText = "已下架";
                        //cloneObj.find("div.shownum")[0].hidden = true;

                    } else {
                        cloneObj.find("span[name='price']")[0].innerText = "￥" + price;
                    }
                    cloneObj.find("input[name='carDetailId']")[0].value = carDetailId;
                    cloneObj.find("input[name='pdNum']")[0].value = pdNum;
                    cloneObj.find("input[name='num']")[0].value = pdNum;
                    cloneObj.appendTo("#list");
                }
                if (shopCarCommon.length >= 4) {
                    $("#list").append("<li style='height: 250px;'></li>");
                }
            } else {
                webToast(data.message, "middle");
            }
        }, function (data) {
            webToast("操作失败", "middle");
            setTimeout(function(){
                $('.loader-inner').addClass('animated zoomOut');
                $("#messageLoading").addClass('animated zoomOut');
                $("#simplemodal-overlay").remove();
            },800)
        });
        //BA2141109.bindCheckAll();
    },

    bindFh: function () {
        $("#return").bind("touchstart", function () {
            window.location.href = 'BA2141108.html'
        })
    },

    bindFinish: function () {
        $("#finish").bind("touchstart", function () {
            var carId = $("#carId").val();
            var obj = [];
            var ids = "";
            var flag = false;
            $(".check").each(function () {
                var o = new Object();
                //if($(this).hasClass("on")){
                var preNum = this.children[1].value;
                var num = $(this).parent().parent().parent().find(".product").find("input[name='num']")[0].value;
                console.info(num);
                if (!num || parseInt(num) < 1) {
                    flag = true;
                }
                if (preNum != num) {
                    var id = this.children[0].value;
                    if (ids) {
                        ids += "," + id;
                    } else {
                        ids += id;
                    }
                    o.carDetailId = id;
                    o.carId = carId;
                    o.pdNum = num;
                    if (id != '') {
                        obj.push(o);
                    }
                }
                //}
            });
            if (flag) {
                webToast("宝贝数量不能少于1", "middle");
                return false;
            }
            if (obj == '') {
                window.location = 'BA2141108.html';
            } else {
                var url = ConstantDef.getUpdateOrderServerUrl();
                var data = {
                    param: {
                        productList: obj
                    }
                };
                HttpClient.post(url, data, function (data) {
                    console.info(data.status);
                    if (data.status == "S") {
                        webToast("修改成功", "middle");
                        window.location = 'BA2141108.html';
                    } else {
                        webToast(data.message, "middle");
                    }
                }, function (data) {
                    webToast("操作失败", "middle");
                });
            }
        })
    },
    /**选择事件绑定*/
    bindCheck: function () {
        $(".check").bind("touchstart", function () {
            if ($(this).hasClass("on")) {
                $(this).removeClass("on");
            } else {
                $(this).addClass("on");
            }
        })
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
        });
    },
    /**删除购物车绑定事件*/
    deleteProduct: function () {
        $("#delete").bind("touchstart", function () {
            var carId = $("#carId").val();
            var obj = [];
            $(".check").each(function () {
                if ($(this).hasClass("on")) {
                    var id = this.children[0].value;
                    if (id != '') {
                        obj.push(id);
                    }
                }
            });
            console.info(obj);
            if (obj == '') {
                webToast("请选择要删除的商品", "middle");
            } else {
                popTipShow.confirm(' ', '是否确定要删除', ['确 定', '取 消'],
                    function (e) {
                        //callback 处理按钮事件
                        var button = $(e.target).attr('class');
                        if (button == 'ok') {
                            //按下确定按钮执行的操作
                            var buyersId = localStorage.houseCode;
                            var buyersType = localStorage.accessType;
                            var url = ConstantDef.getUpdateOrderServerUrl();
                            var data = {
                                param: {
                                    buyersId: buyersId,
                                    removeReason: "2",
                                    buyersType: buyersType,
                                    carID: carId,
                                    carDetailID: obj,
                                    status: "0"
                                }
                            };
                            HttpClient.post(url, data, function (data) {
                                if (data.status == "S") {
                                    $("ul>li").not(":first").remove();
                                    BA2141109.loadShoppingCar();
                                } else {
                                    webToast(data.message, "middle");
                                }
                            }, function (data) {
                                webToast("操作失败", "middle");
                            });
                            this.hide();
                        }
                        if (button == 'cancel') {
                            //按下取消按钮执行的操作
                            this.hide();
                        }
                    }
                );
            }
        })
    }, bindIndex: function () {
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
                window.location.href = "BA2141121.html";
            } else if (accessType == 2) {
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
    getMaxStockQty: function (pdCode, input, val) {
        var qty = 0;
        //return qty;
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
                pdCodeList: [pdCode],
                //platformType : 1,
                lgcsCode: localStorage.lgcsCode,
                sellerType: sellerType,
                slCode: slCode
            }
        };
        HttpClient.post(url, data, function (data) {
            if (data.status == "S") {
                var product = data.result;
                if ('' != product) {
                    qty = product[0].stockCnt;
                }
                if (val != 0 && val >= qty) {
                    input.val(qty);
                    webToast("不能再加啦,超出库存啦", "middle");
                    return;
                }
            } else {
                //input.val(0);
                webToast("该产品没有库存啦", "middle");
            }
        }, function (data) {
            webToast("操作失败", "middle");
        });
    },
    myinit: function () {
        $(".wan-spinner-3").WanSpinner({inputWidth: 46});
        BA2141109.bindIndex();
        BA2141109.loadShoppingCar();
        BA2141109.bindCheck();
        BA2141109.deleteProduct();
        BA2141109.bindFh();
        BA2141109.bindFinish();
        BA2141109.bindCheckAll();
        BA2141109.keyboardShow();
        BA2141109.keyboardHide();
        document.addEventListener("backbutton", eventBackButton, false); //返回键
    },
    keyboardShow:function() {
        $('input[type="text"],textarea').focus(function(){
            //  var a=document.body.offsetHeight;
            // var b=screen.height;
            // alert(a+" "+b);
            setTimeout(function(){
                var viewHeight=document.documentElement.clientHeight-144;
                $(".main").css("height",viewHeight+"px");
            },400)
        })
    },
    keyboardHide:function(){
        var resizeNum=1;
        $(window).resize(function() {
            if(resizeNum % 2==0){
                var mainHeight = forNum * 127;
                $('input[type="text"],textarea').blur();
                $(".main").removeClass("keyboardShow");
                $(".main").css("height", mainHeight + "px");
                $(".main").css("display", "block");
            }
            resizeNum+=1;
        })
    }

}


function eventBackButton() {
    var flag = true;
    var isFocus = $("input").is(":focus");
    if (isFocus && flag) {
        document.removeEventListener("backbutton", eventBackButton, false); //注销返回键
        //3秒后重新注册
        var intervalID = window.setInterval(
            function () {
                window.clearInterval(intervalID);
                document.addEventListener("backbutton", eventBackButton, false); //返回键
            },
            3000);
        flag = false;
        //$("input").blur();
    } else {
        window.location.href = 'javascript:history.go(-1);';
    }
}

window.onload = window.setTimeout(BA2141109.myinit, 100);

