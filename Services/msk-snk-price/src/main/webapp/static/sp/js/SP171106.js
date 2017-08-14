var $List_Grid;
var SP171106Form = {
    SP171106Grid: null,
    formId: "SP171106Form",
    searchId: "SP171106Search",
    init: function () {
        if ($("#canSetFlg").val() == "false") {
            $("#SP171106_SAVE").attr("disabled", true);
            $("#gradeCode").attr("disabled", true);
            $("#applyNum").attr("disabled", true);
            $("#remark").attr("disabled", true);
        }
        SP171106Form.bindSave();
        SP171106Form.bindOther();
        $List_Grid = $('#SP171106_list_grid').grid({});
        FormUtils.init(SP171106Form.searchId, "SP171106");
    },
    bindSave: function () {
        $("#SP171106_SAVE").click(function () {
            var $SP171106Form = $("#SP171106Form");
            var applyNum = $("#applyNum").val();
            var reg = /^[0-9]+(\.[0-9]+)?$/;
            if (applyNum == "") {
                $.alertMessage.warn("请输入申请数量");
                return;
            } else if (!reg.test(applyNum)) {
                $.alertMessage.warn("申请数量格式错误");
                return;
            } else if (applyNum.length > 9) {
                $.alertMessage.warn("申请数量过长错误");
                return;
            }
            $("#pdCode").val($("#pdTypeCode").val() + $("#gradeCode").val());
            // 有修改数量或者备考时，追加履历
            if ($("#applyNumHidden").val() != $("#applyNum").val() || $("#remark").val() != "") {
                $("#updateHisFlg").val("1");
            } else {
                $("#updateHisFlg").val("0");
            }
            var formData = getFormData($("#" + SP171106Form.formId));
            $('#main-content').postUrl(Main.contextPath + "/SP171106/save", formData, function (data) {
                $.alertMessage.info("修改已提交");
            });
        });
    },
    bindOther: function () {
        $("#SP171106_OTHER").click(function () {
            var pdCode = $("#pdTypeCode").val() + $("#gradeCode").val();
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  Start */
            var data = new Object();
            data['slCode'] = $("#slCode").val(),
            data['pdCode'] = pdCode,
            data['lgcsCode'] =  $("#lgcsCode").val(),
            data['demandId'] = $("#demandId").val(),
            data['demandYearmonth'] = $("#demandYearMonth").val()
            Main.detailWindow(Main.contextPath + "/SP171107/init/", data, "其他供应商数量申报详细");
            /** Modfiy:  一览页面到详情页面打开方式横展开   2016/10/11   BY  zhukai1  end */
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SP171106Form.init();
});