/**
 * Created by ni_shaotang on 2016/7/13.
 */
var backUrl = "BA2141105.html";
var BA2141201 = {
    init: function () {
        BA2141201.bindIndex();
        $("#houseName").html(localStorage.houseName);
    },
    bindIndex: function () {
        $("[id^=bt]").bind("touchstart", function () {
            $("[id^=bt]").removeClass("current");
            $(this).addClass("current");
        })
        $("#index").bind("touchstart", function () {
            $("#center").removeClass("current");
            window.setTimeout($(this).addClass("current"), 200);
            window.location.href = "BA2141105.html";
        });
        $("#addHouse").bind("touchstart", function () {
            window.location.href = "BA2141207.html";
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
        $("#car").bind("touchstart", function () {
            $("#center").removeClass("current");
            window.setTimeout($(this).addClass("current"), 200);
            localStorage.html = "BA2141201.html";
            window.location.href = "BA2141108.html";
        })
        $("#houseList").bind("touchstart", function () {
            window.location.href = "BA2141202.html";
        })
        $("#setting").bind("touchstart", function () {
            $("#center").removeClass("current");
            window.setTimeout($(this).addClass("current"), 300);
            window.location.href = "BA2141199.html";
        })

        $("#bt_stockInfo").bind("touchstart", function () {
            window.location.href = "BA2141119.html";
        })
        $("#bt_delegateOrder").bind("touchstart", function () {
            window.location.href = "BA2141114.html?fromUrl=BA2141201.html";
        })

    }
}

window.onload = window.setTimeout(BA2141201.init, 200);
