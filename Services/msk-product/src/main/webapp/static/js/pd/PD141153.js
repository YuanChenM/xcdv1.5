/**
 *产品类别维护和产品品种列表JS
 *@author gyh
 */
var PD141153 = {
    formId: "PD141153Form",
    saveButtonId: "PD141153_SAVE",
    closeButtonId: "PD141153_Back",
    init: function () {
        this.bindSavebutton();
        this.bindOnBlur();
        this.bindHe();
    },
    bindHe :function(){
        $("a[name='PD141153']").bind("click", function() {
            var normsLength = $("#normsLength").val();
            var normsHeight = $("#normsHeight").val();
            var normsWidth = $("#normsWidth").val();
            var he = (Number(normsLength*normsHeight*normsWidth)).toFixed(2);
            $("#normsVolume").val(he);
            $("#normsVolume").html(he);
        });
    },
    /*绑定离焦点校验时间*/
    bindOnBlur: function () {
        $("input[name='normsLength'],input[name='normsHeight'],input[name='normsVolume'],input[name='normsWidth'],input[name='netweightInner'],input[name='netweightOut'],input[name='grossweightOut']").blur(function () {
            var value = this.value;
            if (/[^0-9.]/g.test(value)) {
                $.alertMessage.info('只能输入数值');
                this.value = "";
            }
        });
    },

    bindSavebutton: function () {
        $("#" + PD141153.saveButtonId).click(function () {
            PD141153.saveData();
        });
        $("#" + PD141153.closeButtonId).click(function () {
            $.pdialog.close();
        });
    },
    saveData: function () {
        var validator = mainValidation($("#" + PD141153.formId));
        var isValid = validator.form();
        if (isValid) {
            $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                $.alertMessage.close();
                formData = getFormData($("#" + PD141153.formId));
                $('#main-content').postUrl(
                    $("#" + PD141153.formId).attr("action"), formData, function () {
                        $.alertMessage.info("数据保存成功!");
                    },{refreshHtml:false});
            });
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    PD141153.init();
});