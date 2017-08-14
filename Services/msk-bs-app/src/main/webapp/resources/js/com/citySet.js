function SelCity(obj,e) {
    var ths = obj;
    var dal = '<div class="_citys"><span title="关闭" id="cColse" >×</span><ul id="_citysheng" class="_citys0"><li class="citySel">省份</li><li>城市</li><li>区县</li></ul><div id="_citys0" class="_citys1"></div><div style="display:none" id="_citys1" class="_citys1"></div><div style="display:none" id="_citys2" class="_citys1"></div></div>';
    Iput.show({ id: ths, event: e, content: dal,width:"470"});
    $("#cColse").click(function () {
        Iput.colse();
    });
    var tb_province = [];
    // 获取省份信息
    getProvince();
    var b = provinceData;
    for (var i = 0, len = b.length; i < len; i++) {
        tb_province.push('<a data-level="0" id="' + b[i]['provinceCode'] + '" data-name="' + b[i]['provinceName'] + '">' + b[i]['provinceName'] + '</a>');
    }
    $("#_citys0").append(tb_province.join(""));
    $("#_citys0 a").click(function () {
        // 获取城市
        var g = getCitys($(this));
        $("#_citys1 a").remove();
        $("#_citys1").append(g);
        $("._citys1").hide();
        $("._citys1:eq(1)").show();
        $("#_citys0 a,#_citys1 a,#_citys2 a").removeClass("AreaS");
        $(this).addClass("AreaS");
        var lev = $(this).data("name");
        ths.value = $(this).data("name");
        if (document.getElementById("hprovince") == null) {
            var hcitys = $('<input>', {
                type: 'hidden',
                name: "hprovince",
                title: $(this)[0].id,
                id: "hprovince",
                val: lev
            });
            $(ths).after(hcitys);
        }
        else {
            $("#hprovince").val(lev);
            $("#hprovince").attr("title", $(this)[0].id);
        }
        $("#_citys1 a").click(function () {
            $("#_citys1 a,#_citys2 a").removeClass("AreaS");
            $(this).addClass("AreaS");
            var lev =  $(this).data("name");
            if (document.getElementById("hcity") == null) {
                var hcitys = $('<input>', {
                    type: 'hidden',
                    name: "hcity",
                    title: $(this)[0].id,
                    id: "hcity",
                    val: lev
                });
                $(ths).after(hcitys);
            }
            else {
                $("#hcity").attr("title", $(this)[0].id);
                $("#hcity").val(lev);
            }
            var bc = $("#hprovince").val();
            ths.value = bc+ "-" + $(this).data("name");

            var ar = getArea($(this));

            $("#_citys2 a").remove();
            $("#_citys2").append(ar);
            $("._citys1").hide();
            $("._citys1:eq(2)").show();

            $("#_citys2 a").click(function () {
                $("#_citys2 a").removeClass("AreaS");
                $(this).addClass("AreaS");
                var lev = $(this).data("name");
                if (document.getElementById("hdistrict") == null) {
                    var hcitys = $('<input>', {
                        type: 'hidden',
                        name: "hdistrict",
                        title: $(this)[0].id,
                        id: "hdistrict",
                        val: lev
                    });
                    $(ths).after(hcitys);
                }
                else {
                    $("#hdistrict").val(lev);
                    $("#hdistrict").attr("title", $(this)[0].id);
                }
                var bc = $("#hprovince").val();
                var bp = $("#hcity").val();
                ths.value = bc + "-" + bp + "-" + $(this).data("name");
                Iput.colse();
            });

        });
    });
    $("#_citysheng li").click(function () {
        $("#_citysheng li").removeClass("citySel");
        $(this).addClass("citySel");
        var s = $("#_citysheng li").index(this);
        $("._citys1").hide();
        $("._citys1:eq(" + s + ")").show();
    });
}

function getCitys(obj) {
    // c为省份code
    var c = obj[0].id;
    // 获取城市信息
    getCity(c);
    var f;
    var g = '';
    // cityDta 城市信息
    for (var j = 0, clen = cityDta.length; j < clen; j++) {
        g += '<a data-level="1" id="' + cityDta[j]['cityCode'] + '" data-name="' + cityDta[j]['cityName'] + '" title="' + cityDta[j]['cityName'] + '">' + cityDta[j]['cityName'] + '</a>'
    }
    $("#_citysheng li").removeClass("citySel");
    $("#_citysheng li:eq(1)").addClass("citySel");
    return g;
}
function getArea(obj) {
    var c = obj[0].id;
    getDistrict(c);
    var e = districtData;
    var g = '';
    for (var j = 0, clen = e.length; j < clen; j++) {
        g += '<a data-level="1" id="' + e[j]['districtCode'] + '" data-name="' + e[j]['districtName'] + '" title="' + e[j]['districtName'] + '">' + e[j]['districtName'] + '</a>'
    }

    $("#_citysheng li").removeClass("citySel");
    $("#_citysheng li:eq(2)").addClass("citySel");
    return g;
}