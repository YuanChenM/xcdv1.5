/**
 * 卖家产品其他标准JS
 *
 * @author gyh
 */
var SL241122 = {
    saveButtonId: "SL241122_SAVE",
    formId: "SL241122Form",
    init: function () {
        $(".group-accordion").accordion({heightStyle: "center"});
        //保存其他指标值
        this.saveStdInfo();
        //绑定加工技术和加工质量，包装标准
        this.bindTncInfo();
        //绑定radio事件
        this.bindRadio();
        //liu_yan2注释，为了解决页面报Maximum call stack size exceeded 2016-6-30
        //$('.tree').treegrid();
    },
    bindRadio: function () {
        $("input[type='radio']").click(function () {
            var hideInput = $(this).attr("subInfo");
            $('#' + hideInput).val($(this).val());
        });
        $("input[type='checkbox']").click(function () {
            var radioArray = $("input[type='radio']");
            if (this.checked == true) {
                radioArray.each(function (i, data) {
                    var hideInput = $(data).attr("subInfo");
                    console.log(hideInput.indexOf("orgValArray"))
                    if(hideInput.indexOf("orgValArray") != -1||hideInput.indexOf("fedValArray")!= -1||hideInput.indexOf("fedValArray") != -1 ){
                        if (data.defaultValue == 1) {
                            $(data).prop('checked', 'checked');
                            $('#' + hideInput).val(data.defaultValue);
                        }
                    }else{
                        if (data.defaultValue == 2) {
                            $(data).prop('checked', 'checked');
                            $('#' + hideInput).val(data.defaultValue);
                        }
                    }
                });
            } else {
                radioArray.each(function (i, data) {
                    if (data.defaultValue == 0) {
                        $(data).prop('checked', 'checked');
                        var hideInput = $(data).attr("subInfo");
                        $('#' + hideInput).val(data.defaultValue);
                    }
                });
            }
        });
    },
    saveStdInfo: function () {
        $("#" + SL241122.saveButtonId).click(function () {
            var validator = mainValidation($("#" + SL241122.formId));
            var isValid = validator.form();
            if (isValid) {
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    formData = getFormData($("#" + SL241122.formId));
                    //if(formData.checkAgree==null){
                    //	$.alertMessage.info("您好，需要同意神农客标准后，您注册的产品才可参与销售！");
                    //	return;
                    //}
                    $('#main-content').postUrl($("#" + SL241122.formId).attr("action"), formData, function () {
                        $('#main-content').postUrl(Main.contextPath + "/SL241116/init/" + formData.slCode,{enterMark:"init"},Main.hiddenHeader);
                        //$('#main-content').postUrl(Main.contextPath + "/SL241116/init/" + formData.slCode);
                    }, {refreshHtml: false});
                    $("#headBreadCrumb").hide();
                });
            }
        });
    },
    bindTncInfo: function () {
        $('#normsCard').postUrl(Main.contextPath + "/SL241106/init", {
            slCode: getFormData($("#" + SL241122.formId)).slCode,
            prodEpId: $('#prodEpId').val(),
            brandEpId: $('#brandEpId').val(),
            brandId: $('#brandId').val(),
            pdClassesCode: $('#pdClassesCode').val(),
            machiningCode: $('#machiningCode').val(),
            pdBreedCode: $('#pdBreedCode').val()
        });
        $('#main-content').postUrl(Main.contextPath + "/SL241104/findSlPdTncStd", {
            'filterMap[slCode]': getFormData($("#" + SL241122.formId)).slCode,
            'filterMap[slPdId]': getFormData($("#" + SL241122.formId)).slPdId
        }, function (data) {
            data.slTncGradeCode = $('#slTncGradeCode').val();
            data.slQltGradeCode = $('#slQltGradeCode').val();
            $('#qltCard').append(SL24110401.appendTrShow('1', data));
        }, {refreshHtml: false});
        $('#main-content').postUrl(Main.contextPath + "/SL241105/findSlPdQltStd", {
            'filterMap[slCode]': getFormData($("#" + SL241122.formId)).slCode,
            'filterMap[slPdId]': getFormData($("#" + SL241122.formId)).slPdId
        }, function (data) {
            data.slTncGradeCode = $('#slTncGradeCode').val();
            data.slQltGradeCode = $('#slQltGradeCode').val();
            $('#tncCard').append(SL24110501.appendTrShow('1', data));
        }, {refreshHtml: false});
    }
}
$(document).ready(function () {
    // 初始化调用
    SL241122.init();
});
