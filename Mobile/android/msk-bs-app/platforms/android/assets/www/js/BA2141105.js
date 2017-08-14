/**
 * Created by yang_chunyan on 2016/7/13.
 */
var myScroll,
    pullDownEl,
    pullDownOffset,
    pullUpEl,
    pullUpOffset,
    generatedCount = 0,
    pageNo=2;
    noImage = $("#no-image")[0].src;
$("#messageLoading").modal();
$("#messageLoading").show();
$('#messageLoading').addClass('animated zoomIn');

var BA2141105 = {
    isFinish: false,
    press: 0,
    intoHtml: function () {
        if (!localStorage.accessType) {
            window.location.href = 'BA2141101.html';
            return false;
        }
        return true;
    },
    toDecimal:function (x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return;
    }
    f = Math.round(x*100)/100;
    return f;
},
toHtml:function(){
           $(".li-js").bind("click", function () {
               var pdCode = this.id;
               localStorage.html = 'BA2141105.html';
               window.location.href = "BA2141107.html?pdCode=" + pdCode;
           })
    },
    /**商品列表*/
    bindPd: function () {
        $('#product').bind("touchstart", function () {
            window.location.href = 'BA2141106.html';
        })
    },
    /**囤货列表*/
    bindTh: function () {
        $('#save').bind("touchstart", function () {
            window.location.href = 'BA2141117.html';
        })
    },
    /**委托订单***/
    bindWt:function(){
    $('#weituo').bind("touchstart", function (){
         window.location.href = "BA2141114.html?fromUrl=BA2141105.html";
     })
   },
    /**库存信息*/
    bindKc: function () {
        $('#stock').bind("touchstart", function () {
            window.location.href = 'BA2141117.html';
        })
    },
    bindIndex: function () {
        $("#index").bind("touchstart", function () {
            window.location.href = "BA2141105.html";
        })

        $("#car").bind("touchstart", function () {
            $("#index").removeClass("current");
            window.setTimeout($(this).addClass("current"), 200);
            localStorage.html = "BA2141105.html";
            window.location.href = "BA2141108.html";
        })

        //个人中心点击
        $("#center").bind("touchstart", function () {
            localStorage.html = "BA2141105.html";
            $("#index").removeClass("current");
            window.setTimeout($(this).addClass("current"), 200);
            var accessType = localStorage.accessType;
            if (accessType == 3) {
                window.location.href = "BA2141121.html";
            } else if (accessType == 2) {
                window.location.href = "BA2141201.html";
            } else {
                localStorage.accessTypeTwo = 5;
                window.location.href = 'BA2141101.html';
            }
        })

        //设置点击
        $("#setting").bind("touchstart", function () {
            $("#index").removeClass("current");
            window.setTimeout($(this).addClass("current"), 300);
            window.location.href = "BA2141199.html";
        })
    },
    getSlider: function () {
        var url = ConstantDef.getSliderInfoUrl();
        var paramData = {
            "siteCode": 1,
            "auth": "MSK00001",
            "loginId": "msk01",
            "param": {}
        };
        HttpClient.post(url, paramData, function (data) {
            $("#simplemodal-container").hide();
            $("#messageLoading").addClass('animated zoomOut');
            $("#simplemodal-overlay").remove();
            var html = "";
            //关闭加载提示框
            if (data.result.scrollImgs.length != 0) {
                for (var i = 0; i < data.result.scrollImgs.length; i++) {
                    var scrollImg = data.result.scrollImgs[i].scrollImg;
                    if(scrollImg != ''){
                        scrollImg += "?height=document.body.clientHeight/4&width=document.body.clientWidth";
                    }
                    html += "<div class='swiper-slide '><img src='" + data.result.scrollImgs[i].scrollImg + "'></div>";
                }
                //先撑开模块高度
                $(".swiper-container").css("height", data.result.imgSetting.modelHeight + "vh");
                //网容器添加元素
                $(".swiper-wrapper").html(html);

                var mySwiper = new Swiper('.swiper-container', {
                    pagination: '.swiper-pagination',
                    loop: false,
                    grabCursor: true,
                    autoplay: 2500,
                    observer: true,//修改swiper自己或子元素时，自动初始化swiper
                    observeParents: true//修改swiper的父元素时，自动初始化swiper
                })
            }
        }, function (data, e) {
            $("#simplemodal-container").hide();
            $("#messageLoading").addClass('animated zoomOut');
            $("#simplemodal-overlay").remove();
            webToast("操作失败", "middle");
        })
    },
    pullDownAction:function () {
        setTimeout(function() { // <-- Simulate network congestion, remove setTimeout from production!
            var el, li, i;
            el = document.getElementById('thelist');

            for(i = 0; i < 5; i++) {
                li = document.createElement('li');
                li.innerText = 'Generated row ' + (++generatedCount);
                //el.insertBefore(li, el.childNodes[0]);
            }
            document.getElementById("pullUp").style.display = "";
            //刷新当前窗口 把minScrollY重新变成-51  那么pullDown就会缩回去  看起来就是下拉 pullDown元素 出现一秒 之后消失
            myScroll.refresh();
        }, 1000);
   },
    pullUpAction: function () {
        setTimeout(function() {
            var el, li, i;
            var url = ConstantDef.getFloorInfoUrl();
            var paramData = {
                "siteCode": 1,
                "auth": "MSK00001",
                "loginId": "msk01",
                "param": {
                    "pageCount":2,
                    "pageNo":pageNo,
                    "paging":"true",
                    "lgcsCode":localStorage['lgcsCode']
                }
            };
            el = document.getElementById('floorMain');
            HttpClient.post(url, paramData, function (data) {
                var html = "";
                //关闭加载提示框
                for (var i = 0; i < data.result.floorList.length; i++) {
                    var liHtml = "";
                    for (var j = 0; j < data.result.floorList[i].products.length; j++) {
                        if (j < 6) {
                            var coverImg = data.result.floorList[i].products[j].coverImg;
                            console.info(coverImg);
                            if (coverImg == '') {
                                coverImg = noImage;
                            } else {
                                coverImg += "?height=120&width=120";
                            }
                            var pdLabel = '';
                            if ('' != data.result.floorList[i].products[j].pdLabel)
                                pdLabel = "<span class='label-js'>" + data.result.floorList[i].products[j].pdLabel + "</span>";

                            var price = "<span>已下架</span>";
                            if ('' != data.result.floorList[i].products[j].advertisePrice) {
                                var pri = data.result.floorList[i].products[j].advertisePrice;
                                price = "大宗价:<span>" + BA2141105.toDecimal(pri) + "元/箱" + "</span>";
                            }
                            liHtml += "<li class='li-js' id='" + data.result.floorList[i].products[j].pdCode + "'>" +
                            "<img src='" + coverImg + "'> " +
                            "<div class='detail'><div class='label'><p>" + data.result.floorList[i].products[j].breedName +
                            pdLabel + "</div>" +
                            "<input value=" + data.result.floorList[i].products[j].featureName + "/><input value=" + data.result.floorList[i].products[j].weightName + "><input value=" + data.result.floorList[i].products[j].gradeName + ">" +
                            "<div class='price'><label><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></label><label>" + price + "</label></div></div>" +
                            "</li>";
                        }
                    }
                    html += " <div class='pr-list'>" +
                    " <div class='til'>" +
                    "<input class='floorColor' style='background-color:" + data.result.floorList[i].color + ";display: inline-block;width: 7px;height: 14px;margin-top: auto;margin-bottom: auto;margin-left:2px;'/>" +
                    " <span>" + data.result.floorList[i].floorName + "</span> " +
                    "<a href='javascript:void(0);'>更多</a>" +
                    " </div>" +
                    " <ul id='thelist'>" +
                    liHtml
                    " </ul>" +
                    " </div>";
                }
                //网容器添加元素
                $(".floorMain").append(html);
                pageNo++;
                myScroll.refresh();
                BA2141105.toHtml();
            }, function (data, e) {
                webToast("操作失败", "middle");
            })

        }, 1000);
    },
    loaded:function () {
        pullDownEl = document.getElementById('pullDown');
        pullDownOffset = pullDownEl.offsetHeight;
        pullUpEl = document.getElementById('pullUp');
        pullUpOffset = 10;
        //pullUpOffset = pullUpEl.offsetHeight;
        myScroll = new iScroll('wrapper', {
            useTransition: true,
            //设置iscroll已经滚动的基准值
            topOffset: pullDownOffset,
            scrollbarClass:"myScrollbar",
            onRefresh: function() {
                if(pullDownEl.className.match('loading')) {
                    pullDownEl.className = '';
                    pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新';
                }
                if(pullUpEl.className.match('loading')) {
                    pullUpEl.className = '';
                    pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉刷新';
                }

                document.getElementById("pullUp").style.display = "none";
                //document.getElementById("show").innerHTML = "onRefresh: up[" + pullUpEl.className + "],down[" + pullDownEl.className + "],Y[" + this.y + "],maxScrollY[" + this.maxScrollY + "],minScrollY[" + this.minScrollY + "],scrollerH[" + this.scrollerH + "],wrapperH[" + this.wrapperH + "]";
            },
            //当wrapper被滑动时触发的事件
            onScrollMove: function() {
                //document.getElementById("show").innerHTML = "onScrollMove: up[" + pullUpEl.className + "],down[" + pullDownEl.className + "],Y[" + this.y + "],maxScrollY[" + this.maxScrollY + "],minScrollY[" + this.minScrollY + "],scrollerH[" + this.scrollerH + "],wrapperH[" + this.wrapperH + "]";
                //如果this.y大于0 代表已经下拉到可以刷新的地步了
                if(this.y > 0) {
                    //加类名flip 应该是改变箭头的方向
                    pullDownEl.className = 'flip';
                    //获取第一个class等于pullDownLabel的元素  改变他的文本内容
                    pullDownEl.querySelector('.pullDownLabel').innerHTML = '正在刷新';
                    //设置y的最小值等于0  也就代表吧pulldown露出来
                    this.minScrollY = 0;
                }
                //当下拉到可以刷新的程度但是手指没有离开屏幕 并放回到this.y小于0的时候
                if(this.y < 0 && pullDownEl.className.match('flip')) {
                    //还原箭头方向
                    pullDownEl.className = '';
                    pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新';
                    this.minScrollY = -pullDownOffset;
                }

                if(this.scrollerH < this.wrapperH && this.y < (this.minScrollY - pullUpOffset) || this.scrollerH > this.wrapperH && this.y < (this.maxScrollY - pullUpOffset)) {
                    document.getElementById("pullUp").style.display = "";
                    pullUpEl.className = 'flip';
                    pullUpEl.querySelector('.pullUpLabel').innerHTML = '正在刷新';
                }
                if(this.scrollerH < this.wrapperH && this.y > (this.minScrollY - pullUpOffset) && pullUpEl.className.match('flip') || this.scrollerH > this.wrapperH && this.y > (this.maxScrollY - pullUpOffset) && pullUpEl.className.match('flip')) {
                    document.getElementById("pullUp").style.display = "none";
                    pullUpEl.className = '';
                    pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载更多';
                }
            },
            //滚动完成后的回调
            onScrollEnd: function() {
                //document.getElementById("show").innerHTML = "onScrollEnd: up[" + pullUpEl.className + "],down[" + pullDownEl.className + "],Y[" + this.y + "],maxScrollY[" + this.maxScrollY + "],minScrollY[" + this.minScrollY + "],scrollerH[" + this.scrollerH + "],wrapperH[" + this.wrapperH + "]";
                if(pullDownEl.className.match('flip')) {
                    pullDownEl.className = 'loading';
                    pullDownEl.querySelector('.pullDownLabel').innerHTML = '正在加载';
                    BA2141105.pullDownAction(); // Execute custom function (ajax call?)
                }
                if(pullUpEl.className.match('flip')) {
                    pullUpEl.className = 'loading';
                    pullUpEl.querySelector('.pullUpLabel').innerHTML = '正在加载';
                    BA2141105.pullUpAction(); // Execute custom function (ajax call?)
                }
            }
        });
        //setTimeout(function () { document.getElementById('wrapper').style.left = '0'; }, 800);
    },
    trigger:function(){
        document.addEventListener('touchmove', function(e) {
            e.preventDefault();
        }, false);
       /* //它在DOM加载之后及资源加载之前被触发
        document.addEventListener('DOMContentLoaded', function() {
            setTimeout(loaded, 200);
        }, false);*/
    },
    firstFloor:function(){
        var url = ConstantDef.getFloorInfoUrl();
        var paramData = {
            "siteCode": 1,
            "auth": "MSK00001",
            "loginId": "msk01",
            "param": {
                "pageCount":2,
                "pageNo":1,
                "paging":"true",
                "lgcsCode":localStorage['lgcsCode']
            }
        };
        BA2141105.httpPlugin(url,paramData);
    },
    httpPlugin:function(url,paramData){
        HttpClient.post(url, paramData, function (data) {

            var html = "";
            //关闭加载提示框
            for (var i = 0; i < data.result.floorList.length; i++) {
                var liHtml="";
                for(var j=0;j<data.result.floorList[i].products.length;j++){
                    if(j < 6) {
                        var coverImg = data.result.floorList[i].products[j].coverImg;
                        console.info(coverImg);
                        if (coverImg == '') {
                            coverImg = noImage;
                        } else {
                            coverImg += "?height=120&width=120";
                        }
                        var pdLabel = '';
                        if('' != data.result.floorList[i].products[j].pdLabel)
                            pdLabel = "<span class='label-js'>" + data.result.floorList[i].products[j].pdLabel + "</span>";

                        var price = "<span>已下架</span>";
                        if('' != data.result.floorList[i].products[j].advertisePrice){
                            var pri = data.result.floorList[i].products[j].advertisePrice;
                            price = "大宗价:<span>" + BA2141105.toDecimal(pri) + "元/箱" + "</span>";
                        }
                        liHtml += "<li class='li-js' id='" + data.result.floorList[i].products[j].pdCode + "'>" +
                        "<img src='" + coverImg + "'> " +
                        "<div class='detail'><div class='label'><p>" + data.result.floorList[i].products[j].breedName +
                        pdLabel + "</div>" +
                        "<input value=" + data.result.floorList[i].products[j].featureName + "><input value=" + data.result.floorList[i].products[j].weightName + "><input value=" + data.result.floorList[i].products[j].gradeName + ">" +
                        "<div class='price'><label><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></label><label>" + price + "</label></div></div>" +
                        "</li>";
                    }
                }
                html += " <div class='pr-list'>" +
                    " <div class='til'>" +
                    "<input class='floorColor' style='background-color:"+data.result.floorList[i].color+";display: inline-block;width: 7px;height: 14px;margin-top: auto;margin-bottom: auto;margin-left:2px;'/>"+
                    " <span>"+ data.result.floorList[i].floorName + "</span> " +
                    "<a href='javascript:void(0);'>更多</a>" +
                    " </div>" +
                    " <ul id='thelist'>" +
                    liHtml
                " </ul>" +
                " </div>";
            }
            //网容器添加元素
            $(".floorMain").html(html);

            BA2141105.toHtml();
        }, function (data, e) {
            webToast("操作失败", "middle");
        })
    },
    init: function () {
        $('#animationSandbox').css("display", 'none');
        BA2141105.bindPd();
       /* BA2141105.bindTh();
        BA2141105.bindKc();*/
        BA2141105.bindIndex();
        BA2141105.getSlider();
        BA2141105.trigger();
        BA2141105.loaded();
        BA2141105.pullDownAction();
        BA2141105.pullUpAction();
        BA2141105.firstFloor();
        var accessType =localStorage.accessType;
        // 首页 管家有委托订单，买手没有
        if(accessType==2){
            BA2141105.bindWt();
        }else{
            $("#weituo").removeClass("icon03-usable");
            $("#weituo").addClass("icon03");
        }
        onDeviceReady();
    }
}

window.onload = window.setTimeout(BA2141105.init, 200);


var readyToEnd = false;//准备退出
document.addEventListener('deviceready', onDeviceReady, false);
function backmenuEvent() {
    $('#animationSandbox').css("bottom", '80px');
    //$('#animationSandbox').css("background-color",'#919191');
    $('#animationSandbox').addClass('bounceInUp' + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
        $(this).removeClass();
    });

    if (readyToEnd) navigator.app.exitApp();
    else {
        readyToEnd = true;
        $('#animationSandbox').css("display", '');
        //webToast("再按一次退出程序","middle");
        setTimeout(function () {//2s后自动隐藏提示和将重置准备退出操作
            $('#animationSandbox').css("display", 'none');
            //document.getElementById('exitNote').style.display = 'none';
            readyToEnd = false;
        }, 2000);
    }
}
function onDeviceReady() {
    document.addEventListener('backbutton', backmenuEvent, false);
}

