/**
 *质量标准设置JS
 *@author jiang_nan
 */
var PD141105 = {
    saveButtonId: "PD141105_SAVE",
    returnButtonId: "PD141105_RETURN",
    formId: "PD141105Form",
    init: function () {
        $('.tree').treegrid();
        this.bindSaveButton();
        this.bindRuturnButton();
        this.checkButton();
        this.allSelectClick();
    },
    //checkBox选中事件
    checkButton: function () {
        $("input[name='checks']").click(function () {
            var index = $(this).attr("isCheck");
            var input1 = $("#input1_" + index);
            var input2 = $("#input2_" + index);
            var input3 = $("#input3_" + index);
            var input4 = $("#input4_" + index);
            if (this.checked) {
                input1.removeAttr("readonly");
                input2.removeAttr("readonly");
                input3.removeAttr("readonly");
                input4.removeAttr("readonly");
            } else {
                input1.attr("readonly", "readonly");
                input2.attr("readonly", "readonly");
                input3.attr("readonly", "readonly");
                input4.attr("readonly", "readonly");
            }
        });
    },
    //全选
    allSelectClick: function () {
        $("input[type='checkbox']").click(function () {
            if(this.attributes.topone.value=="true"){
                var checkName=this.name;
                if(this.checked){
                    $("input[checkFlg="+checkName+"]").each(function() {
                        this.checked = true;
                        var index = $(this).attr("isCheck");
                        var input1 = $("#input1_" + index);
                        var input2 = $("#input2_" + index);
                        var input3 = $("#input3_" + index);
                        var input4 = $("#input4_" + index);
                        input1.removeAttr("readonly");
                        input2.removeAttr("readonly");
                        input3.removeAttr("readonly");
                        input4.removeAttr("readonly");
                    });
                }else{
                    $("input[checkFlg="+checkName+"]").each(function () {
                        this.checked = false;
                        var index = $(this).attr("isCheck");
                        var input1 = $("#input1_" + index);
                        var input2 = $("#input2_" + index);
                        var input3 = $("#input3_" + index);
                        var input4 = $("#input4_" + index);
                        input1.attr("readonly","readonly");
                        input2.attr("readonly","readonly");
                        input3.attr("readonly","readonly");
                        input4.attr("readonly","readonly");
                    });
                }
            }

        });
    },
    bindSaveButton: function () {
        $("#" + PD141105.saveButtonId).click(function () {
            var validator = mainValidation($("#" + PD141105.formId));
            var isValid = validator.form();
            if (isValid) {
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    var $check ={};
                    $("input[name='checks']").each(function (index) {
                        if (this.checked) {
                            $(this).val(0);
                            $check[index]="0";
                        } else {
                            $(this).attr("checked", "checked");
                            $(this).val(1);
                            $check[index]="1";
                        }
                    });
                    formData = getFormData($("#" + PD141105.formId));
                    formData.checks=$check;
                    $('#main-content').postUrl($("#" + PD141105.formId).attr("action"), formData, function () {
                        $('#main-content').postUrl(Main.contextPath + "/PD141105/init", {
                            standardId: formData.standardId,
                            classesName: formData.classesName,
                            breedName: formData.breedName
                        });
                    });
                });
            }
        });
    },
    bindRuturnButton: function () {
        $("#" + PD141105.returnButtonId).click(function () {
            $('#main-content').postUrl(Main.contextPath + "/PD141113/init",{"filterMap['breedCode']":BREED_CODE,"filterMap['classesCode']":CLASSES_CODE});
        });
    }
}
$(document).ready(function () {
    //初始化调用
    PD141105.init();
});
