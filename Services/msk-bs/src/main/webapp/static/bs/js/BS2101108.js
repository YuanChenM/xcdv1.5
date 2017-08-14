/**
 * 买手囤货同盟申请JS
 *
 * @author gyh
 */
var $List_Grid;
var BS2101108 = {
    formId: "BS2101108FormId",
    searchId: "BS2101108_SEARCH",
    applyId: "BS2101108_APPLY",
    init: function () {
        $List_Grid = $('#BS2101108_list_grid').grid({
            actionHandler: BS2101108.actionHandler
        });
        this.bindSelect();
        this.bindButton();
    },
    bindButton: function () {
        $("#" + BS2101108.searchId).click(function () {
            $.pdialog.open("买手列表", Main.contextPath + "/BS2101101/init", {
                width: 1100,
                height: 550
            }, {applyStatus: 1, slCode: slCode});
        });
        $("#" + BS2101108.applyId).click(function () {

        });
    },
    bindSelect: function () {
        var pdClasses = $('#pdClasses');
        var pdMachining = $('#pdMachining');
        var pdBreed = $('#pdBreed');
        var pdFeature = $('#pdFeature');
        var pdWeight = $('#pdWeight');
        var pdGrade = $('#pdGrade');
        //根据产品类别查询产品二级分类
        pdClasses.change(function () {
            pdBreed.html('');
            pdBreed.append("<option value=''>请选择</option>");
            pdFeature.html('');
            pdFeature.append("<option value=''>请选择</option>");
            pdWeight.html('');
            pdWeight.append("<option value=''>请选择</option>");
            pdGrade.val('');
            var pdClassesVal = pdClasses.val();
            if (pdClassesVal == '') {
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/BS2101108/findPdMachining", {'filterMap[classesCode]': pdClassesVal}, function (data) {
                pdMachining.html('');
                pdMachining.append("<option value=''>请选择</option>");
                $.each(data, function (i, item) {
                    pdMachining.append("<option value='" + item.machiningCode + "'>" + item.machiningName + "</option>");

                });
            }, {refreshHtml: false});
        });
        //根据产品二级分类查询品种
        pdMachining.change(function () {
            pdFeature.html('');
            pdFeature.append("<option value=''>请选择</option>");
            pdWeight.html('');
            pdWeight.append("<option value=''>请选择</option>");
            pdGrade.val('');
            var pdClassesVal = pdClasses.val();
            var pdMachiningVal = pdMachining.val();
            if (pdMachiningVal == '') {
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/BS2101108/findBreed", {
                'filterMap[classesCode]': pdClassesVal,
                'filterMap[machiningCode]': pdMachiningVal
            }, function (data) {
                pdBreed.html('');
                pdBreed.append("<option value=''>请选择</option>");
                $.each(data, function (i, item) {
                    pdBreed.append("<option value='" + item.breedCode + "'>" + item.breedName + "</option>");
                });
            }, {refreshHtml: false});
        });
        //根据产品品种查询特征
        pdBreed.change(function () {
            pdWeight.html('');
            pdWeight.append("<option value=''>请选择</option>");
            pdGrade.val('');
            var pdClassesVal = pdClasses.val();
            var pdMachiningVal = pdMachining.val();
            var pdBreedVal = pdBreed.val();
            if (pdBreedVal == '') {
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/BS2101108/findFeature", {
                'filterMap[classesCode]': pdClassesVal,
                'filterMap[machiningCode]': pdMachiningVal,
                'filterMap[breedCode]': pdBreedVal
            }, function (data) {
                pdFeature.html('');
                pdFeature.append("<option value=''>请选择</option>");
                $.each(data, function (i, item) {
                    pdFeature.append("<option value='" + item.featureCode + "'>" + item.featureName + "</option>");
                });
            }, {refreshHtml: false});
        });
        //根据产品特征查询外包装净重
        pdFeature.change(function () {
            pdGrade.val('');
            var pdClassesVal = pdClasses.val();
            var pdMachiningVal = pdMachining.val();
            var pdBreedVal = pdBreed.val();
            var pdFeatureVal = pdFeature.val();
            if (pdFeatureVal == '') {
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/BS2101108/findPdWeight", {
                'filterMap[classesCode]': pdClassesVal,
                'filterMap[machiningCode]': pdMachiningVal,
                'filterMap[breedCode]': pdBreedVal,
                'filterMap[featureCode]': pdFeatureVal
            }, function (data) {
                pdWeight.html('');
                pdWeight.append("<option value=''>请选择</option>");
                $.each(data, function (i, item) {
                    pdWeight.append("<option value='" + item.weightCode + "'>" + item.weightName + "</option>");
                });
            }, {refreshHtml: false});
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        /** 操作按钮 */
        if (col == 10) {
            $('#main-content').postUrl(Main.contextPath + "/BS2101102/init/" + rowdata.slCode + "/" + rowdata.slCodeDis, {slContact: rowdata.slContact});
        }
        if (col == 11) {
            $('#main-content').postUrl(Main.contextPath + "/BS2101105/init/", {
                slAccount: rowdata.slAccount,
                accountPsd: rowdata.accountPsd,
                slContact: rowdata.slContact,
                slTel: rowdata.slTel
            });
        }
        if (coltype == 'audit') {
            $('#main-content').postUrl(Main.contextPath + "/BS2101108/init", rowdata);
        }
    },
    queryData: function () {
        formData = getFormData($("#" + BS2101108.formId));
        $('#main-content').postUrl(
            $("#" + BS2101108.formId).attr("action"), formData, function () {
                $List_Grid.fnDraw();
            }, {refreshHtml: false});
    },
    //暂时无效按钮
    detailData: function () {
        $("#BS2101108_add").click(function () {
        });
    },
    //暂时无效按钮
    detailData: function () {
        $("#BS2101108_reje").click(function () {
        });
    },
    //暂时无效按钮
    detailData: function () {
        $("#BS2101108_del" ).click(function () {
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BS2101108.init();
});
