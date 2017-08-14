var SP171104Form = {
    SP171104Grid: null,
    formId: "SP171104Form",
    searchId: "SP171104Search",
    init: function () {
        if ($("#canSetFlg").val() == "false") {
            $("#isConfirm").attr("disabled", true);
            $("#passNum").attr("disabled", true);
            $("#remark").attr("disabled", true);
        }
        $("#isConfirm option[value='0']").remove();
        var isConfirmVal = $('#isConfigHidden').val();
        $('#isConfirm').val(isConfirmVal);
        SP171104Form.bindSave();
        SP171104Form.bindSearchSell();
        $List_Grid = $('#SP171104_list_grid').grid({});
        FormUtils.init(SP171104Form.searchId, "SP171104");
    },
    bindSave: function () {
        $("#SP171104_SAVE").click(function () {
            var $SP171104Form = $("#SP171104Form");
            var passNum = $("#passNum").val();
            var applyNum = $("#applyNum").val();
            var isConfirm = $("#isConfirm").val();
            var reg = /^[0-9]+(\.[0-9]+)?$/;
            if (passNum == "") {
                $.alertMessage.warn("请输入建议数量");
                return;
            } else if (!reg.test(passNum)) {
                $.alertMessage.warn("建议数量格式错误");
                return;
            } else if (passNum.length > 9) {
                $.alertMessage.warn("申请数量过长错误");
                return;
            }
            // 有修改数量或者备考时，追加履历
            if ($("#passNumHidden").val() != $("#passNum").val() || $("#remark").val() != "") {
                $("#updateFlg").val("1");
            } else {
                $("#updateFlg").val("0");
            }
            //申报数量和建议数量不相等并且状态为未确认
            if (applyNum != passNum && isConfirm == 1) {
                $.alertMessage.confirm("建议数量和申报数量不一致，是否驳回？", function () {
                    $("#isConfirm").val(2);//设置状态为驳回
                    formData = getFormData($("#" + SP171104Form.formId));
                    /** Modfiy:  一览页面到详情页面打开方式横展开，不需要刷新全页面，不然导航栏就会显示出来   2016/10/11   BY  zhukai1  Start */
                    $('#main-content').postUrl(Main.contextPath + "/SP171104/save", formData, function () {
                        $.alertMessage.info("修改已提交");
                    },{refreshHtml:false});
                    /** Modfiy:  一览页面到详情页面打开方式横展开 不需要刷新全页面，不然导航栏就会显示出来   2016/10/11   BY  zhukai1  end */
                });
            }else{
                formData = getFormData($("#" + SP171104Form.formId));
                /** Modfiy:  一览页面到详情页面打开方式横展开，不需要刷新全页面，不然导航栏就会显示出来   2016/10/11   BY  zhukai1  Start */
                $('#main-content').postUrl(Main.contextPath + "/SP171104/save", formData, function () {
                    $.alertMessage.info("修改已提交");
                },{refreshHtml:false});
                /** Modfiy:  一览页面到详情页面打开方式横展开 不需要刷新全页面，不然导航栏就会显示出来   2016/10/11   BY  zhukai1  end */
            }
        });
    },
    bindSearchSell: function () {
        $("#SP171104_SEARCH").click(function () {
            $.pdialog.open("", Main.contextPath + "/SP171104/searchSeller", {
                    resizable: false,
                    width: 700,
                    height: 400
                },
                {
                    lgcsCode: $("#lgcsCode").val(),
                    isConfirm: $("#isConfigHidden").val(),
                    demandYearMonth: $("#nowMonth").val()
                }, "sp171115");
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SP171104Form.init();

});