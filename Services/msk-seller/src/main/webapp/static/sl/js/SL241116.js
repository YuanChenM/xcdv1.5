/**
 * 卖家信息列表JS
 *
 * @author gyh
 */
var $List_Grid;
var SL241116 = {
    addButton: 'PD141116_ADD',
    newButton: 'PD141116_NEW',
    listButton: 'PD141116_LIST',
    formId: 'SL241116Form',
    uploadFormId: 'SL241116UploadForm',
    init: function () {
        $List_Grid = $('#SL241116_list_grid').grid({
            fnRowCallback: SL241116.rowCallback,
            actionHandler: SL241116.actionHandler
        });
        this.changeSelect();
        this.bindButton();
    },

    rowCallback: function (tr, data) {
        var $td = $(tr).children('td').eq(11);
        $td.html("");
        //卫生标准定级
        if (data.status == '1') {
            $td.html("<select id='check" + (data.slPdId + data.slPdArtNo) + "'><option value=''></option><option value='1' selected='selected'>申请中</option><option value='2'>论证中</option><option value='3'>禁止准入</option><option value='4'>试销</option><option value='5'>正式上线</option><option value='6'>下线</option><option value='7'>黑名单</option></select>");
        } else if (data.status == '2') {
            $td.html("<select id='check" + (data.slPdId + data.slPdArtNo) + "'><option value=''></option><option value='1'>申请中</option><option value='2' selected='selected'>论证中</option><option value='3'>禁止准入</option><option value='4'>试销</option><option value='5'>正式上线</option><option value='6'>下线</option><option value='7'>黑名单</option></select>");
        } else if (data.status == '3') {
            $td.html("<select id='check" + (data.slPdId + data.slPdArtNo) + "'><option value=''></option><option value='1'>申请中</option><option value='2'>论证中</option><option value='3' selected='selected'>禁止准入</option><option value='4'>试销</option><option value='5'>正式上线</option><option value='6'>下线</option><option value='7'>黑名单</option></select>");
        } else if (data.status == '4') {
            $td.html("<select id='check" + (data.slPdId + data.slPdArtNo) + "'><option value=''></option><option value='1'>申请中</option><option value='2'>论证中</option><option value='3'>禁止准入</option><option value='4' selected='selected'>试销</option><option value='5'>正式上线</option><option value='6'>下线</option><option value='7'>黑名单</option></select>");
        } else if (data.status == '5') {
            $td.html("<select id='check" + (data.slPdId + data.slPdArtNo) + "'><option value=''></option><option value='1'>申请中</option><option value='2'>论证中</option><option value='3'>禁止准入</option><option value='4'>试销</option><option value='5' selected='selected'>正式上线</option><option value='6'>下线</option><option value='7'>黑名单</option></select>");
        } else if (data.status == '6') {
            $td.html("<select id='check" + (data.slPdId + data.slPdArtNo) + "'><option value=''></option><option value='1'>申请中</option><option value='2'>论证中</option><option value='3'>禁止准入</option><option value='4'>试销</option><option value='5'>正式上线</option><option value='6' selected='selected'>下线</option><option value='7'>黑名单</option></select>");
        } else if (data.status == '7') {
            $td.html("<select id='check" + (data.slPdId + data.slPdArtNo) + "'><option value=''></option><option value='1'>申请中</option><option value='2'>论证中</option><option value='3'>禁止准入</option><option value='4'>试销</option><option value='5'>正式上线</option><option value='6'>下线</option><option value='7' selected='selected'>黑名单</option></select>");
        } else {
            $td.html("<select id='check" + (data.slPdId + data.slPdArtNo) + "'><option value='' selected='selected'></option><option value='1'>申请中</option><option value='2'>论证中</option><option value='3'>禁止准入</option><option value='4'>试销</option><option value='5'>正式上线</option><option value='6'>下线</option><option value='7'>黑名单</option></select>");
        }
    },
    bindButton: function () {
        $('#' + SL241116.addButton).click(function () {
            //SL241116.addSlInfo();
            SL241116.addBasicSlInfo();
            var $uploadFile = $("#" + SL241116.uploadFormId);
            $.core.uploadForm($uploadFile, true);
        });
        $('#' + SL241116.newButton).click(function () {
            $('#main-content').postUrl(
                Main.contextPath + "/SL241130/init", {slCode: slCode, slShowName: slShowName});
        });
        $('#' + SL241116.listButton).click(function () {
            $('#main-content').postUrl(
                Main.contextPath + "/SL241129/init", {slCode: slCode});
        });
    },
    addBasicSlInfo:function (){
        var brandEpName = $("#brandId option:selected").text();// 获取市场调研类型
        $("#brandEpName").val(brandEpName);

        var pdClassesName = $("#pdClasses option:selected").text();// 获取市场调研类型
        $("#pdClassesName").val(pdClassesName);

        var machiningName = $("#pdMachining option:selected").text();// 获取市场调研类型
        $("#machiningName").val(machiningName);

        var pdBreedName = $("#pdBreed option:selected").text();// 获取市场调研类型
        $("#pdBreedName").val(pdBreedName);

        var pdFeatureName = $("#pdFeature option:selected").text();// 获取市场调研类型
        $("#pdFeatureName").val(pdFeatureName);

        var weightName = $("#pdWeight option:selected").text();// 获取市场调研类型
        $("#weightName").val(weightName);
    },
    addSlInfo: function () {
        var validator = mainValidation($("#" + SL241116.formId));
        var isValid = validator.form();
        if (isValid) {
            $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                $.alertMessage.close();
                formData = getFormData($("#" + SL241116.formId));
                $('#main-content').postUrl(
                    Main.contextPath + "/SL241116/save",
                    formData,
                    function () {
                        $List_Grid.fnDraw();
                    });
            });
        }
    },
    changeSelect: function () {
        var pdClasses = $('#pdClasses');
        var pdMachining = $('#pdMachining');
        var pdBreed = $('#pdBreed');
        var pdFeature = $('#pdFeature');
        var pdWeight = $('#pdWeight');
        //根据产品类别查询产品二级分类
        pdClasses.change(function () {
            var pdClassesVal = pdClasses.val();
            var pdMachiningVal = pdMachining.val();
            if (pdClassesVal == '') {
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/SL241116/findPdMachining", {'filterMap[classesCode]': pdClassesVal}, function (data) {
                //$('#classesName').val(pdClasses.find("option:selected").text());
                pdMachining.html('');
                pdMachining.append("<option value=''>请选择</option>");
                pdBreed.html('');
                pdBreed.append("<option value=''>请选择</option>");
                pdFeature.html('');
                pdFeature.append("<option value=''>请选择</option>");
                pdWeight.html('');
                pdWeight.append("<option value=''>请选择</option>");
                $.each(data, function (i, item) {
                    pdMachining.append("<option value='" + item.machiningCode + "'>" + item.machiningName + "</option>");
                });
            }, {refreshHtml: false});
        });
        pdMachining.change(function () {
            var pdClassesVal = pdClasses.val();
            var pdMachiningVal = pdMachining.val();
            var pdBreedVal = pdBreed.val();
            if (pdMachiningVal == '') {
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/SL241116/findBreed", {
                'filterMap[classesCode]': pdClassesVal,
                'filterMap[machiningCode]': pdMachiningVal
            }, function (data) {
                //$('#classesName').val(pdClasses.find("option:selected").text());
                pdBreed.html('');
                pdBreed.append("<option value=''>请选择</option>");
                pdFeature.html('');
                pdFeature.append("<option value=''>请选择</option>");
                pdWeight.html('');
                pdWeight.append("<option value=''>请选择</option>");
                $.each(data, function (i, item) {
                    pdBreed.append("<option value='" + item.breedCode + "'>" + item.breedName + "</option>");
                });
            }, {refreshHtml: false});
        });
        pdBreed.change(function () {
            var pdClassesVal = pdClasses.val();
            var pdMachiningVal = pdMachining.val();
            var pdBreedVal = pdBreed.val();
            var pdFeatureVal = pdFeature.val();
            if (pdBreedVal == '') {
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/SL241116/findFeature", {
                'filterMap[classesCode]': pdClassesVal,
                'filterMap[machiningCode]': pdMachiningVal,
                'filterMap[breedCode]': pdBreedVal
            }, function (data) {
                //$('#breedName').val(pdBreed.find("option:selected").text());
                pdFeature.html('');
                pdFeature.append("<option value=''>请选择</option>");
                pdWeight.html('');
                pdWeight.append("<option value=''>请选择</option>");
                $.each(data, function (i, item) {
                    pdFeature.append("<option value='" + item.featureCode + "'>" + item.featureName + "</option>");
                });
            }, {refreshHtml: false});
        });
        pdFeature.change(function () {
            var pdClassesVal = pdClasses.val();
            var pdMachiningVal = pdMachining.val();
            var pdBreedVal = pdBreed.val();
            var pdFeatureVal = pdFeature.val();
            var pdWeightVal = pdWeight.val();
            if (pdFeatureVal == '') {
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/SL241116/findPdWeight", {
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
        //更改产品状态
        if (coltype == 'save') {
            rowdata.status = $('#check' + (rowdata.slPdId + rowdata.slPdArtNo)).val();
            $('#main-content').postUrl(Main.contextPath + "/SL241116/upSlPdStatus", rowdata, function (data) {
                if (data != '1') {
                    $.alertMessage.info("状态更新失败," + data);
                    return;
                }
                $.alertMessage.info("状态更新成功!");
                $List_Grid.fnDraw();
            }, {refreshHtml: false});
        }
        //包装
        if (coltype == 'detail') {
            $('#main-content').postUrl(Main.contextPath + "/SL241119/init", rowdata);
        }
        //技术
        if (coltype == 'check') {
            rowdata.slShowName = slShowName;
            $('#main-content').postUrl(Main.contextPath + "/SL241118/init", rowdata);
        }
        //卫生
        if (coltype == 'edit') {
            rowdata.slShowName = slShowName;
            $('#main-content').postUrl(Main.contextPath + "/SL241117/init", rowdata);
        }
        //其他
        if (coltype == 'audit') {
            rowdata.slShowName = slShowName;
            $('#main-content').postUrl(Main.contextPath + "/SL241122/init/1", rowdata);
        }
        //产品品种图片
        if (coltype == 'search') {
            if(rowdata.qltAuditDate) {
                var qltAuditDate = new Date(rowdata.qltAuditDate);
                rowdata.qltAuditDate = qltAuditDate.format('yyyy-MM-dd hh:mm:ss');
            }
            if(rowdata.qltMonitorDate) {
                var qltMonitorDate = new Date(rowdata.qltMonitorDate);
                rowdata.qltMonitorDate = qltMonitorDate.format('yyyy-MM-dd hh:mm:ss');
            }
            if(rowdata.tncAuditDate) {
                var tncAuditDate = new Date(rowdata.tncAuditDate);
                rowdata.tncAuditDate = tncAuditDate.format('yyyy-MM-dd hh:mm:ss');
            }
            if(rowdata.tncMonitorDate) {
                var tncMonitorDate = new Date(rowdata.tncMonitorDate);
                rowdata.tncMonitorDate = tncMonitorDate.format('yyyy-MM-dd hh:mm:ss');
            }
            rowdata.slShowName = slShowName;
            $.pdialog.open(rowdata.pdClassesName + rowdata.pdBreedName + "图片信息", Main.contextPath + "/SL241116/showImage", {
                width: 700,
                height: 500
            }, rowdata);
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    SL241116.init();
});

