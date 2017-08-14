/**
 * Created by ni_shaotang on 2016/7/13.
 */
var backUrl = 'BA2141105.html';
var BA2141121 = {
    init: function () {
        BA2141121.bindIndex();
    },
    bindIndex: function () {
        $("#slName").text(localStorage.slContact);
        $("[id^=btn]").bind("touchstart", function () {
            $("[id^=btn]").removeClass("current");
            $(this).addClass("current");
            if ($(this)[0].id == "btnaddHouse") {
                window.location.href = "BA2141207.html";
            } else if ($(this)[0].id == "btnhouseList") {
                window.location.href = "BA2141202.html";
            } else if ($(this)[0].id == "btnsaveOrder") {
                window.location.href = "BA2141117.html";
            } else if ($(this)[0].id == "btnsaleOrder") {
                window.location.href = "BA2141122.html";
            }
        })
        $("#index").bind("touchstart", function () {
            $("#center").removeClass("current");
            window.setTimeout($(this).addClass("current"), 200);
            window.location.href = "BA2141105.html";
        });
        $("#center").bind("touchstart", function () {
            var accessType = localStorage.accessType;
            if (accessType == 3) {
                window.location.href = "BA2141121.html";
            } else if (accessType == 2) {
                window.location.href = "BA2141201.html";
            } else {
                window.location.href = 'BA214101.html';
            }
        });
        window.setTimeout($(this).addClass("current"), 200);
        $("#car").bind("touchstart", function () {
            $("#index").removeClass("current");
            window.setTimeout($(this).addClass("current"), 200);
            localStorage.html = "BA2141121.html";
            window.location.href = "BA2141108.html";
        })
        $("#setting").bind("touchstart", function () {
            $("#center").removeClass("current");
            window.setTimeout($(this).addClass("current"), 300);
            window.location.href = "BA2141199.html";
        })
        $("#btnstockInfo").bind("touchstart", function () {
            window.location.href = "BA2141119.html";
        })
    }
}
//页面入口

window.onload = window.setTimeout(BA2141121.init, 200);
