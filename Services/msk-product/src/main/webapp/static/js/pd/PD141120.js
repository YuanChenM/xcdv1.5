/**
 * 产品品种维护JS
 *
 * @author gyh
 */
var PD141120 = {
    formId: "PD141120Form",
    saveButtonId: "PD141120_SAVE",
    newButtonId: "PD141120_NEW",
    searchButtonId: 'PD141120_SEARCH',
    init: function () {
        this.bindSavebutton();
        this.bindDatePicber('#priceDate');
        this.changeSelect();
        $("#priceDetail").postUrl(Main.contextPath + "/PD141120/initDetail", {"filterMap[wayCode]": "01"});
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
        this.closeDate();
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    },
    changeSelect: function () {
        var pdClasses = $('#pdClasses');
        var pdMachining = $('#pdMachining');
        var pdBreed = $('#pdBreed');
        var pdFeature = $('#pdFeature');
        var pdWeight = $('#pdWeight');
        var pdNorms = $('#pdNorms');


        pdClasses.change(function () {
            pdMachining.html('');
            pdMachining.append("<option value=''>请选择</option>");
            pdBreed.html('');
            pdBreed.append("<option value=''>请选择</option>");
            pdFeature.html('');
            pdFeature.append("<option value=''>请选择</option>");
            pdWeight.html('');
            pdWeight.append("<option value=''>请选择</option>");
            pdNorms.html('');
            pdNorms.append("<option value=''>请选择</option>");

            var pdClassesVal = pdClasses.val();
            var pdMachiningVal = $('#pdMachining').val();
            $('#main-content').postUrl(Main.contextPath + "/PD141120/findMachining", {'filterMap[classesCode]': pdClassesVal}, function (data) {
                $('#classesName').val(pdClasses.find("option:selected").text());
                $.each(data, function (i, item) {
                    if (item.machiningCode === pdMachiningVal) {
                        pdMachining.append("<option selected='selected' value='" + item.machiningCode + "'>" + item.machiningName + "</option>");
                    } else {
                        pdMachining.append("<option value='" + item.machiningCode + "'>" + item.machiningName + "</option>");
                    }
                });
            }, {refreshHtml: false});
        });

        pdMachining.change(function () {
                pdBreed.html('');
                pdBreed.append("<option value=''>请选择</option>");
                pdFeature.html('');
                pdFeature.append("<option value=''>请选择</option>");
                pdWeight.html('');
                pdWeight.append("<option value=''>请选择</option>");
                pdNorms.html('');
                pdNorms.append("<option value=''>请选择</option>");
                //获取input框的值
                var pdClassesVal = pdClasses.val();
                var pdMachiningVal = pdMachining.val();
                var pdBreedVal = pdBreed.val();
                $('#main-content').postUrl(
                    Main.contextPath + "/PD141120/findBreed", {
                        'filterMap[classesCode]': pdClassesVal,
                        'filterMap[machiningCode]': pdMachiningVal
                    }, function (data) {
                        $('#machiningName').val(pdMachining.find("option:selected").text());
                        $.each(data, function (i, item) {
                            if (item.breedCode === pdBreedVal) {
                                pdBreed.append("<option selected='selected' value='" + item.breedCode + "'>" + item.breedName + "</option>");
                            } else {
                                pdBreed.append("<option value='" + item.breedCode + "'>" + item.breedName + "</option>");
                            }

                        });
                    }, {refreshHtml: false});
            }
        );

        pdBreed.change(function () {
            pdFeature.html('');
            pdFeature.append("<option value=''>请选择</option>");
            pdWeight.html('');
            pdWeight.append("<option value=''>请选择</option>");
            pdNorms.html('');
            pdNorms.append("<option value=''>请选择</option>");
            var pdClassesVal = pdClasses.val();
            var pdMachiningVal = pdMachining.val();
            var pdBreedVal = pdBreed.val();
            var pdFeatureVal = pdFeature.val();
            $('#main-content').postUrl(Main.contextPath + "/PD141120/findFeature", {
                'filterMap[classesCode]': pdClassesVal,
                'filterMap[breedCode]': pdBreedVal,
                'filterMap[machiningCode]': pdMachiningVal
            }, function (data) {
                $('#breedName').val(pdBreed.find("option:selected").text());
                $.each(data, function (i, item) {
                    if (item.featureCode === pdFeatureVal) {
                        pdFeature.append("<option selected='selected' value='" + item.featureCode + "'>" + item.featureName + "</option>");
                    } else {
                        pdFeature.append("<option value='" + item.featureCode + "'>" + item.featureName + "</option>");
                    }

                });
            }, {refreshHtml: false});
        });


        pdFeature.change(function () {
            pdWeight.html('');
            pdWeight.append("<option value=''>请选择</option>");
            pdNorms.html('');
            pdNorms.append("<option value=''>请选择</option>");
            var pdClassesVal = pdClasses.val();
            var pdMachiningVal = pdMachining.val();
            var pdBreedVal = pdBreed.val();
            var pdFeatureVal = pdFeature.val();
            var pdWeightVal = pdWeight.val();
            $('#main-content').postUrl(Main.contextPath + "/PD141120/findPdWeight", {
                'filterMap[classesCode]': pdClassesVal,
                'filterMap[machiningCode]': pdMachiningVal,
                'filterMap[breedCode]': pdBreedVal,
                'filterMap[featureCode]': pdFeatureVal
            }, function (data) {
                $('#featureName').val(pdFeature.find("option:selected").text());
                $.each(data, function (i, item) {
                    if (item.weightCode === pdWeightVal) {
                        pdWeight.append("<option selected='selected' value='" + item.weightCode + "'>" + item.weightName + "</option>");
                    } else {
                        pdWeight.append("<option value='" + item.weightCode + "'>" + item.weightName + "</option>");
                    }

                });
            }, {refreshHtml: false});

        });
        pdWeight.change(function () {
            pdNorms.html('');
            pdNorms.append("<option value=''>请选择</option>");
            var pdClassesVal = pdClasses.val();
            var pdMachiningVal = pdMachining.val();
            var pdBreedVal = pdBreed.val();
            var pdFeatureVal = pdFeature.val();
            var pdWeightVal = pdWeight.val();
            var pdNormsVal = pdNorms.val();
            $('#main-content').postUrl(Main.contextPath + "/PD141120/findNorms", {
                'filterMap[classesCode]': pdClassesVal,
                'filterMap[machiningCode]': pdMachiningVal,
                'filterMap[breedCode]': pdBreedVal,
                'filterMap[featureCode]': pdFeatureVal,
                'filterMap[weightCode]': pdWeightVal
            }, function (data) {
                $('#weightName').val(pdWeight.find("option:selected").text());
                $.each(data, function (i, item) {
                    if (item.normsCode === pdNormsVal) {
                        pdNorms.append("<option selected='selected' title='" + item.netweightOut + "' value='" + item.normsCode + "'>" + item.normsOut + "</option>");
                    } else {
                        pdNorms.append("<option title='" + item.netweightOut + "' value='" + item.normsCode + "'>" + item.normsOut + "</option>");
                    }

                });
            }, {refreshHtml: false});

        });

        //设置外包装
        pdNorms.change(function () {
            $('#netweight').val(pdNorms.find("option:selected").attr("title"));
        });
        //通道类型
        var wayCode = $('#wayCode');
        wayCode.change(function () {
            $("#priceDetail").postUrl(Main.contextPath + "/PD141120/initDetail", {"filterMap[wayCode]": wayCode.val()});
        });


        //区域名称
        var logiareaCode = $('#logiareaCode');
        logiareaCode.change(function () {
            $('#logiareaName').val(logiareaCode.find("option:selected").text());
        });
    },
    bindDatePicber: function (priceDate) {
        $(priceDate).datepicker({
            prevText: '上月',         // 前选按钮提示
            nextText: '下月',         // 后选按钮提示
            changeYear: true,          // 年下拉菜单
            changeMonth: true,             // 月下拉菜单
            showButtonPanel: true,         // 显示按钮面板
            showMonthAfterYear: true,  // 月份显示在年后面
            currentText: "本月",         // 当前日期按钮提示文字
            closeText: 'Clear',           // 关闭按钮提示文字
            dateFormat: "y-mm",       // 日期格式
            minDate: new Date(),			//最小日期
            onClose: function (dateText, inst) {// 关闭事件
                var month = $("#ui-datepicker-div .ui-datepicker-month").val();
                var year = $("#ui-datepicker-div .ui-datepicker-year").val();
                $(this).datepicker('setDate', new Date(year, month, 1));
            }
        });
    },
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    bindSavebutton: function () {
        $("#" + PD141120.saveButtonId).click(function () {
            PD141120.saveData();
        });
        $("#" + PD141120.searchButtonId).click(function () {
            var formData = getFormData($("#" + PD141120.formId));
            $('#main-content').postUrl(Main.contextPath + "/PD141199/init",
                formData,
                function (data) {
                    //回调函数
                });
        });
        $("#" + PD141120.newButtonId).click(function () {
            $.alertMessage.confirm("你确定要开始新价盘吗？", function () {
                $('#main-content').postUrl(Main.contextPath + "/PD141120/newPriceprd", null,
                    function () {
                        //回调函数
                        $.alertMessage.info("旧价盘已经处理成功，可以开始新价盘了!");
                    }, {refreshHtml: false});
            });
        });
    },
    saveData: function () {
        $('#pdtName').val($('#pdClasses').find("option:selected").text() + '/' + $('#pdMachining').find("option:selected").text() + '/' + $('#pdBreed').find("option:selected").text() + '/' + $('#pdFeature').find("option:selected").text() + '/' + $('#gradeCode').find("option:selected").text());
        var validator = mainValidation($("#" + PD141120.formId));
        debugger;
        var isValid = validator.form();
        if (isValid) {
            $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                $.alertMessage.close();
                formData = getFormData($("#" + PD141120.formId));
                $('#main-content').postUrl(
                    $("#" + PD141120.formId).attr("action"),
                    formData,
                    function (data) {
                        $.alertMessage.info("保存成功！")
                    }, {refreshHtml: false});
            });
        }
    }
};
$(document).ready(function () {
    // 初始化调用
    PD141120.init();
});