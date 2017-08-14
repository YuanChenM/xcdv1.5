/**
 * 买手店列表JS
 *
 * @author cx
 */
var $List_Grid;
var BS2101103 = {
    formId: "bs2101103FormId",
    init: function () {
        $List_Grid = $('#bs2101103_list_grid').grid({
            actionHandler: BS2101103.actionHandler
        });
        this.changeSelect();
        this.queryData();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        /** 操作按钮 */

        if (coltype == 'check') {
            $.alertMessage.confirm("该操作，会移除当前会员与冻品管家的绑定关系，你确定要解除当前会员吗？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BS2101103/relievePelation/", {
                    slCode: rowdata.slCode,
                    houseCode: rowdata.houseCode,
                    buyerId: rowdata.buyerId
                }, function () {
                    $List_Grid.fnDraw();
                    $.alertMessage.info("解除成功!");
                }, {refreshHtml: false});
            })
        }
        if (coltype == 'edit') {
            $.alertMessage.confirm("是否确定将该锁定期买家申请为专属买家？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BS2101103/buildPelation", {
                    slCode: rowdata.slCode,
                    houseCode: rowdata.houseCode,
                    buyerId: rowdata.buyerId
                }, function () {
                    $List_Grid.fnDraw();
                }, {refreshHtml: false});
            })
        }
    },
    changeSelect: function () {
        var citySelect = $('#city_select');
        var districtSelect = $('#district_select');
        $('#lgcsArea_select').change(function () {
            $("#city_select").find("option").not(":first").remove();
            $("#district_select").find("option").not(":first").remove();
            var lgcsAreaCode = $('#lgcsArea_select').val();
            if (lgcsAreaCode != 0) {
                $('#main-content').postUrl(Main.contextPath + '/BS2101103/findCity', {lgcsAreaCode: lgcsAreaCode},
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
                $('#main-content').postUrl(Main.contextPath + '/BS2101103/findDistrict', {cityCode: cityCode}, function (data2) {
                    $.each(data2, function (i, item) {
                        districtSelect.append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
                    });
                }, {refreshHtml: false})
            }
        });
    },
    queryData: function () {
        $("#" + BS2101103.searchData).click(function () {
            formData = getFormData($("#" + BS2101103.formId));
            FormUtils.setFormValue(BS2101103.formId, "BS2101103");
            $List_Grid.fnDraw();

        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BS2101103.init();
    $(function () {
        $("a").each(function () {
            $(this).attr("href", encodeURI($(this).attr("href")))
        })
    })
});
