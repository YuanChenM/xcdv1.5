/**
 * 产品品种维护JS
 *
 * @author gyh
 */
var PD14112404 = {
    formId: "PD14112404Form",
    saveButtonId: "PD14112404_SAVE",
    backButtonId: "PD14112404_BACK",
    init: function () {
        this.bindSavebutton();
        this.selectChange();
        this.changeCheckBox();
        this.changeCheckBox2();
        this.changeCheckBox3();
    },
    bindSavebutton: function () {
        $("#" + PD14112404.saveButtonId).click(function () {
            PD14112404.saveData();
        });
        $("#" + PD14112404.backButtonId).click(function () {
            $.pdialog.close();
        });
    },
    selectChange: function () {
        $("#selectWeight").change(function () {
                var name = $("#selectWeight option:selected").text();
                if ($(this).value != '0') {
                    $("input[name='weightName']").val(name);
                }
                if (/^[0-9]+.?[0-9]*$/.test(name) ){
                    $("#showText").text("(kg)");
                }else{
                    $("#showText").text("");
                }
            }
        )
    },
    changeCheckBox: function () {
        $(".selectWeight").click(function () {
            var ids = $(this).attr("class");
            if ($(this).prop("checked")) {
                $(":checkbox").not($(this)).removeAttr("checked");
                $('#' + ids).removeAttr('disabled');
                $('#classestreeName5').prop('disabled', 'disabled');
                $('#classestreeName5').val("");
                $('#copyCodeId').prop('disabled', 'disabled');
                $('#copyCodeId').val("");
                $('#copyCodeName').prop('disabled', 'disabled');
                $('#copyCodeName').val("");
                $('#copyCodeVal').prop('disabled', 'disabled');
                $('#copyCodeVal').val("");
            } else {
                $(":checkbox").removeAttr("checked");
                $('#' + ids).prop('disabled', 'disabled');
                $('#classestreeName5').prop('disabled', 'disabled');
                $('#copyCodeId').prop('disabled', 'disabled');
                $('#copyCodeName').prop('disabled', 'disabled');
                $('#copyCodeVal').prop('disabled', 'disabled');
                $("#selectWeight option:first").prop("selected", 'selected');
            }
        });
    },
    changeCheckBox2: function () {
        $(".classestreeName5").click(function () {
            var ids = $(this).attr("class");
            if ($(this).prop("checked")) {
                $(":checkbox").not($(this)).removeAttr("checked");
                $('#' + ids).removeAttr('disabled');
                $('#selectWeight').prop('disabled', 'disabled');
                $("#selectWeight option:first").prop("selected", 'selected');
                $('#copyCodeId').prop('disabled', 'disabled');
                $('#copyCodeId').val("");
                $('#copyCodeName').prop('disabled', 'disabled');
                $('#copyCodeName').val("");
            } else {
                $(":checkbox").removeAttr("checked");
                $('#' + ids).prop('disabled', 'disabled');
                $('#selectWeight').prop('disabled', 'disabled');
                $('#copyCodeId').prop('disabled', 'disabled');
                $('#copyCodeName').prop('disabled', 'disabled');
                $('#copyCodeId').val("")
                $('#copyCodeName').val("")
                $('#copyCodeVal').val("");
                $('#copyCodeName').prop('disabled', 'disabled');
                $("#selectWeight option:first").prop("selected", 'selected');
            }
        });
    },
    changeCheckBox3: function () {
        $(".copyCode").click(function () {
            if ($(this).prop("checked")) {
                $(":checkbox").not($(this)).removeAttr("checked");
                $('#copyCodeId').removeAttr('disabled');
                $('#copyCodeName').removeAttr('disabled');
                $('#copyCodeVal').removeAttr('disabled');
                $('#selectWeight').prop('disabled', 'disabled');
                $("#selectWeight option:first").prop("selected", 'selected');
                $('#classestreeName5').prop('disabled', 'disabled');
                $('#classestreeName5').val("");
            } else {
                $(":checkbox").removeAttr("checked");
                $('#copyCodeId').prop('disabled', 'disabled');
                $('#copyCodeId').val("");
                $('#copyCodeVal').prop('disabled', 'disabled');
                $('#copyCodeVal').val("");
                $('#copyCodeName').prop('disabled', 'disabled');
                $('#copyCodeName').val("");
                $('#selectWeight').prop('disabled', 'disabled');
                $('#classestreeName5').prop('disabled', 'disabled');
            }
        });
    },
    saveData: function () {
        var validator = mainValidation($("#" + PD14112404.formId));
        var isValid = validator.form();

        var isNull = $("#classestreeName5").val();
        var selectValue = $("#selectWeight").val();
        var codeId = $("#copyCodeId").val();
        var codeVal = $("#copyCodeVal").val();
        var codeName = $("#copyCodeName").val();
        if ((isNull == null || isNull == '') && selectValue == '0' && (codeId == null || codeId == '') && (codeName == null || codeName == '')) {
            $.alertMessage.info("请选择新增类型!");
            return false;
        }
        /*if(selectValue=='0'){
         if(/^\s*$/.test(isNull)){
         $.alertMessage.info("输入框值不能为空!");
         return false;
         }
         }*/
        /*if(isNull!=null && isNull!=''){
         $("#selectWeight").val('0');
         if (/[^0-9.]/g.test(isNull)) {
         $.alertMessage.info('只能输入数值');
         return false;
         }
         }*/
        if (isNull == null || isNull == '' && selectValue == '0') {
            if (codeId == null || codeId == '' && codeName == null || codeName == '') {
                $.alertMessage.info("新增抄码数据不能为空!");
                return false;
            } else if (!/^[0-9][1-9]{1}$/.test(codeId)) {
                $.alertMessage.info('抄码只能输入两位数值!');
                return false;
            }else if(codeVal!=null&&codeVal!=''){
                if (/[^0-9.]/g.test(codeVal)) {
                    $.alertMessage.info('新增净重只能输入数值!');
                    return false;
                }
            }
        }

        if (isValid) {
            $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                $.alertMessage.close();
                formData = getFormData($("#" + PD14112404.formId));
                $('#PD14112406Id').postUrl(
                    $("#" + PD14112404.formId).attr("action"),
                    formData,
                    function (data) {

                        /** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
                        if(data=='1'){
                            PD14112406.initJsp();
                            $.alertMessage.info("数据操作成功!");
                            /** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
                            $.pdialog.close();
                        }else{
                            $.alertMessage.info("数据异常,请修改后添加!");
                        }
                        /** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  End */
                    });
            });
        }
    }
};
$(document).ready(function () {
    // 初始化调用
    PD14112404.init();
});