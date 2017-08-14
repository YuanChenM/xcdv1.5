var $List_Grid;
var BS2102102 = {
    formId: "BS2102102Form",
    searchButtonId: 'BS2102102_SEARCH',
    saveButtonId: 'BS2102102_SAVE',
    init: function () {
        this.bindSearch();
        this.keyEnter();
        this.bindSave();
        this.changeSelect();
        $List_Grid = $('#BS2102102_list_grid').grid({
            actionHandler: BS2102102.actionHandler
        });
    },
    changeSelect: function () {
        var selectlgcsAreaCode = $("#vlgcsAreaCode");
        selectlgcsAreaCode.change(function () {
            var lgcsAreaCode = $(this).val();
            $("#vcityCode").html("<option value=''>请选择</option>");
            $('#main-content').postUrl(Main.contextPath + '/BS2102102/findCity', {vlgcsAreaCode: lgcsAreaCode},
                function (data) {
                    $.each(data, function (i, item) {
                        $("#vcityCode").append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                    });
                }, {refreshHtml: false});
        });
        var selectLeverOne = $("#cateCode");
        selectLeverOne.change(function () {
            var parentTypeCode = $(this).val();
            $("#subCode").html("<option value=''>请选择</option>");
            if (parentTypeCode == 0) {
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + '/BS2102103/findLeverTwo', {parentTypeCode: parentTypeCode},
                function (data) {
                    $.each(data, function (i, item) {
                        $("#subCode").append("<option value='" + item.typeCode + "'>" + item.typeName + "</option>");
                    });
                }, {refreshHtml: false});
        });
    },
    bindSearch: function () {
        $("#" + BS2102102.searchButtonId).click(function () {
            var formData = getFormData($("#" + BS2102102.formId));
            $List_Grid.fnDraw();
        });
    },

    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "check") {
            $('#main-content').postUrl(Main.contextPath + "/BS2102103/init/", {
                "slCode": rowdata.slCode,
                "houseCode": rowdata.houseCode,
                "houseShowName": rowdata.houseShowName,
                "flag1": rowdata.flag1,
                "houseTel": rowdata.houseTel,
                "wechat": rowdata.wechat,
                "houseGreade": rowdata.houseGreade,
                "houseStar": rowdata.houseStar,
                "vhouseAddress": rowdata.vhouseAddress,
                "lgcsAreaCode": rowdata.vlgcsAreaCode,
                "lgcsAreaName": rowdata.vlgcsAreaName,
                "vcityCode": rowdata.vcityCode,
                "vprovinceCode": rowdata.vprovinceCode,
                "vdistrictCode": rowdata.vdistrictCode,
                "validYearMonth" : rowdata.validYearMonth
            });
        }
        if (coltype == "edit") {
            rowdata.flagNum = 3;
            $('#main-content').postUrl(Main.contextPath + "/BS2101107/init/", rowdata);
        }
        if (coltype == "detail") {
            $('#main-content').postUrl(Main.contextPath + "/BS2102112/init/", rowdata);
        }
        if (coltype == "delete") {
            $.alertMessage.confirm("确定要删除该冻品管家数据吗？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BS2102102/delete/",
                    rowdata, function () {
                            $List_Grid.fnDraw();
                    }, {refreshHtml: false});
            });
        }
    },
    bindSave: function () {
        $("#" + BS2102102.saveButtonId).click(function () {
            $('#main-content').postUrl(Main.contextPath + "/BS2101107/init/", {flagNum: 2}, function () {
            });
        });
    },
    keyEnter: function () {
        document.onkeydown = function (e) {
            //捕捉回车事件
            var ev = (typeof event != 'undefined') ? window.event : e;
            if (ev.keyCode == 13) {
                var formData = getFormData($("#" + BS2102102.formId));
                FormUtils.setFormValue(BS2102102.formId, "BS2102102");
                $List_Grid.fnDraw();
            }
        }
    }
};
$(document).ready(function () {
    // 初始化调用
    BS2102102.init();
});