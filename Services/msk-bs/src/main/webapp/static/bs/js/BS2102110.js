var $List_Grid;
var BS2102110 = {
    formId: "BS2102110Form",
    init: function () {
        this.bindConfirmButton();
        this.changeSelect();
        this.queryData();
        $List_Grid = $('#BS2102110_list_grid').grid({
        });
    },
    bindConfirmButton : function(){
        $("#BS2102110_SAVE").click(function(){
            var selectValue = $List_Grid.getChoiceOne();
            BS2101107.addBuyerCode(selectValue.slCode,selectValue.slCodeDis,selectValue.slContact);
            $.pdialog.close();
        });
    },
    changeSelect:function(){
        var province = $('#provinceList');
        var citySelect = $('#citySelect');
        province.change(function () {
            $("#citySelect").find("option").not(":first").remove();
            $("#districtSelect").find("option").not(":first").remove();
            var provinceCode =$("#provinceList option:selected").val();
            if (provinceCode != 0) {
                $('#main-content').postUrl(Main.contextPath + '/BS2102110/findCity', {provinceCode: provinceCode},
                    function (data) {
                        $.each(data, function (i, item) {
                            citySelect.append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                        });
                    }, {refreshHtml: false});
            }
        });
        citySelect.change(function () {
            var district = $('#districtSelect');
            $("#districtSelect").find("option").not(":first").remove();
            var cityCode =$("#citySelect option:selected").val();
            if (cityCode != 0) {
                $('#main-content').postUrl(Main.contextPath + '/BS2102110/findDistrict', {cityCode: cityCode},
                    function (data) {
                        $.each(data, function (i, item) {
                            district.append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
                        });
                    }, {refreshHtml: false});
            }
        });
    },
    queryData: function () {
        $("#" + BS2102110.searchData).click(function () {
            formData = getFormData($("#" + BS2102110.formId));
            $('#main-content').postUrl(
                $("#" + BS2102110.formId).attr("action"), formData, function () {
                    $List_Grid.fnDraw();
                }, {refreshHtml: false});
        });
    }
};
$(document).ready(function () {
    // 初始化调用
    BS2102110.init();
});
