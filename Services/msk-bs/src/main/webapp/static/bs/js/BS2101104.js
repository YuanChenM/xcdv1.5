/**
 * 买手店列表JS
 *
 * @author cx
 */
var $List_Grid;
var BS2101104 = {
    formId: "BS2101104FormId",
    searchData: "BS2101104_search",
    init: function () {

        $List_Grid = $('#bs2101104_list_grid').grid({
            fnRowCallBack: BS2101104.rowCallback,
            actionHandler: BS2101104.actionHandler,
            can_abolish: BS2101104.canAbolish
        });
        this.changeSelect();
        //this.queryData();
    },

    actionHandler: function (rowdata, coltype, row, col) {
        // 所属买家池
        if (coltype == "audit") {
            $.pdialog.open("买家池", Main.contextPath + "/BS2101200/init",{width: "60%"},{buyerId:rowdata.buyerId});
        }

        var  pageType=$("#hidPageType").val();
        // pageType是从冻品管家列表中跳转过来的
        if(pageType == 1){
            //成为专属买家
            if (col == 11) {
                $.alertMessage.confirm("你确定要申请成为锁定期买家吗？", function () {
                    $.alertMessage.close();
                    $('#main-content').postUrl(Main.contextPath + "/BS2101104/checkHouseAccount",{
                        buyerId: rowdata.buyerId
                    },function(data){
                        if(data > 0){
                            $.alertMessage.error("当前买家已经绑定过冻品管家，不能重复绑定！");
                        }else{
                            $('#main-content').postUrl(Main.contextPath + "/BS2101104/SlApply", {
                                slCode: $("#hidSlCode").val(),
                                houseAccount: $("#hidHouseAccount").val(),
                                houseCode: $("#hidHouseCode").val(),
                                buyerId: rowdata.buyerId,
                                flge: 5
                            }, function () {
                                $List_Grid.fnDraw();
                            }, {refreshHtml: false});
                        }
                    }, {refreshHtml: false});

                });
            }
            //买家申请成为专属会员
            if (col == 12) {
                $.alertMessage.confirm("你确定要申请成为专属会员吗？", function () {
                    $.alertMessage.close();
                    $('#main-content').postUrl(Main.contextPath + "/BS2101104/checkHouseAccount",{
                        buyerId: rowdata.buyerId
                    },function(data){
                        if(data>0){
                            $.alertMessage.error("当前买家已经绑定过冻品管家，不能重复绑定！");
                        }else{
                            $('#main-content').postUrl(Main.contextPath + "/BS2101104/SlApply", {
                                slCode: $("#hidSlCode").val(),
                                houseAccount: $("#hidHouseAccount").val(),
                                houseCode: $("#hidHouseCode").val(),
                                buyerId: rowdata.buyerId,
                                flge: 6
                            }, function () {
                                $List_Grid.fnDraw();
                            }, {refreshHtml: false});
                        }
                    }, {refreshHtml: false});

                });
            }
            //冻品管家申请成为专属会员
            if (col == 13) {
                $.alertMessage.confirm("你确定要申请成为专属会员吗?", function () {
                    $.alertMessage.close();
                    $('#main-content').postUrl(Main.contextPath + "/BS2101104/SlApply", {
                        slCode: $("#hidSlCode").val(),
                        houseAccount: $("#hidHouseAccount").val(),
                        houseCode: $("#hidHouseCode").val(),
                        buyerId: rowdata.buyerId,
                        flge: 7
                    }, function () {
                        $List_Grid.fnDraw();
                    },{refreshHtml: false});
                });
            }
        }else if(pageType == 2){
            // pageType==2是从左侧菜单进入
            if (coltype == "check") {
                $.alertMessage.confirm("确定要解除与管家的关系吗?", function () {
                    $.alertMessage.close();
                    $('#main-content').postUrl(Main.contextPath + "/BS2101104/unbundRelation", {
                        buyerId: rowdata.buyerId
                    }, function (result) {
                        if(result > 0){
                            $.alertMessage.info("解除成功");
                            $List_Grid.fnDraw();
                        }else {
                            $.alertMessage.info("解除失败");
                        }
                    },{refreshHtml: false});
                });
            }
        }

    },

    changeSelect: function () {
        var citySelect = $('#city_select');
        var districtSelect = $('#district_select');
        $('#province_select').change(function () {
            $("#city_select").find("option").not(":first").remove();
            $("#district_select").find("option").not(":first").remove();
            var lgcsAreaCode = $('#province_select').val();
            if (lgcsAreaCode != 0) {
                $('#main-content').postUrl(Main.contextPath + '/BS2101104/findCity', {lgcsAreaCode: lgcsAreaCode},
                    function (data) {
                        $.each(data, function (i, item) {
                            citySelect.append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                        });
                    }, {refreshHtml: false});
            }
        });
        $('#city_select').change(function () {
            $("#district_select").find("option").not(":first").remove();
            var cityCode = $("#city_select").val();
            if (cityCode != 0) {
                $('#main-content').postUrl(Main.contextPath + '/BS2101104/findDistrict', {cityCode: cityCode}, function (data2) {
                    $.each(data2, function (i, item) {
                        districtSelect.append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
                    });
                }, {refreshHtml: false})
            }
        });
    },

    canAbolish: function (rowdata) {
        if (rowdata.bindStatus == 0) {
            return true;
        }
        return false;
    },

    queryData: function () {
        $("#" + BS2101104.searchData).click(function () {
            formData = getFormData($("#" + BS2101104.formId));
            $('#main-content').postUrl(
                $("#" + BS2101104.formId).attr("action"), formData, function () {
                    $List_Grid.fnDraw();
                }, {refreshHtml: false});
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BS2101104.init();
    $(function () {
        $("a").each(function () {
            $(this).attr("href",encodeURI($(this).attr("href")))
        })
    })
});
