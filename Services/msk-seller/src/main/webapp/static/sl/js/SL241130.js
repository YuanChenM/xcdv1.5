/**
 *申请特性信息js
 *@author gyh
 */
var SL241130 = {
    saveButtonId: "SL241130_SAVE",
    formId: "SL241130Form",
    init: function () {
        $(".group-accordion").accordion({ heightStyle: "center" });
        this.bindSaveButton();
        //类别联动
        this.classesInfo();
        //二级分类联动
        this.machiningInfo();
        //品种联动
        this.breedInfo();
        //特征联动
        this.featureInfo();
        //净重触发事件
        this.weightInfo();
        this.selectButton();
    },
    //select选中事件
    selectButton: function () {
        $("input[name='chooseInfo']").change(function () {
            var chooseInfo = $(this).val();
            if (chooseInfo == '1') {
                $("#SL241130_FORM tr:eq(0)").css('display', '');
                $('#SL241130_FORM tr:eq(0) td:eq(4)').css('display','');
                $('#SL241130_FORM tr:eq(0) td:eq(5)').css('display','');
                $("#SL241130_FORM tr:eq(1)").css('display', 'none');
                $("#SL241130_FORM tr:eq(2)").css('display', 'none');
                $("#SL241130_FORM tr:eq(3)").css('display', 'none');
                $("#SL241130_FORM tr:eq(4)").css('display', 'none');
                $("#SL241130_FORM tr:eq(5)").css('display', 'none');
                $("#SL241130_FORM tr:eq(6)").css('display', 'none');
                $("#SL241130_FORM tr:eq(7)").css('display', 'none');
                $("#SL241130_FORM tr:eq(8)").css('display', 'none');
                $("#SL241130_FORM tr:eq(9)").css('display', 'none');
            }else if (chooseInfo == '2') {
                $("#SL241130_FORM tr:eq(0)").css('display', '');
                $('#SL241130_FORM tr:eq(0) td:eq(4)').css('display','none');
                $('#SL241130_FORM tr:eq(0) td:eq(5)').css('display','none');
                $("#SL241130_FORM tr:eq(1)").css('display', '');
                $('#SL241130_FORM tr:eq(1) td:eq(2)').css('display','');
                $('#SL241130_FORM tr:eq(1) td:eq(3)').css('display','');
                $("#SL241130_FORM tr:eq(2)").css('display', 'none');
                $("#SL241130_FORM tr:eq(3)").css('display', 'none');
                $("#SL241130_FORM tr:eq(4)").css('display', 'none');
                $("#SL241130_FORM tr:eq(5)").css('display', 'none');
                $("#SL241130_FORM tr:eq(6)").css('display', 'none');
                $("#SL241130_FORM tr:eq(7)").css('display', 'none');
                $("#SL241130_FORM tr:eq(8)").css('display', 'none');
                $("#SL241130_FORM tr:eq(9)").css('display', 'none');
            }else if (chooseInfo == '3') {
                $("#SL241130_FORM tr:eq(0)").css('display','' );
                $('#SL241130_FORM tr:eq(0) td:eq(4)').css('display','none');
                $('#SL241130_FORM tr:eq(0) td:eq(5)').css('display','none');
                $("#SL241130_FORM tr:eq(1)").css('display', '');
                $('#SL241130_FORM tr:eq(1) td:eq(2)').css('display','none');
                $('#SL241130_FORM tr:eq(1) td:eq(3)').css('display','none');
                $("#SL241130_FORM tr:eq(2)").css('display', '');
                $('#SL241130_FORM tr:eq(2) td:eq(2)').css('display','');
                $('#SL241130_FORM tr:eq(2) td:eq(3)').css('display','');
                $('#SL241130_FORM tr:eq(2) td:eq(4)').css('display','');
                $('#SL241130_FORM tr:eq(2) td:eq(5)').css('display','');
                $("#SL241130_FORM tr:eq(3)").css('display', 'none');
                $("#SL241130_FORM tr:eq(4)").css('display', 'none');
                $("#SL241130_FORM tr:eq(5)").css('display', 'none');
                $("#SL241130_FORM tr:eq(6)").css('display', 'none');
                $("#SL241130_FORM tr:eq(7)").css('display', 'none');
                $("#SL241130_FORM tr:eq(8)").css('display', 'none');
                $("#SL241130_FORM tr:eq(9)").css('display', 'none');
            }else if (chooseInfo == '4') {
                $("#SL241130_FORM tr:eq(0)").css('display', '');
                $('#SL241130_FORM tr:eq(0) td:eq(4)').css('display','none');
                $('#SL241130_FORM tr:eq(0) td:eq(5)').css('display','none');
                $("#SL241130_FORM tr:eq(1)").css('display', '');
                $('#SL241130_FORM tr:eq(1) td:eq(2)').css('display','none');
                $('#SL241130_FORM tr:eq(1) td:eq(3)').css('display','none');
                $("#SL241130_FORM tr:eq(2)").css('display', '');
                $('#SL241130_FORM tr:eq(2) td:eq(2)').css('display','none');
                $('#SL241130_FORM tr:eq(2) td:eq(3)').css('display','none');
                $('#SL241130_FORM tr:eq(2) td:eq(4)').css('display','none');
                $('#SL241130_FORM tr:eq(2) td:eq(5)').css('display','none');
                $("#SL241130_FORM tr:eq(3)").css('display', '');
                $("#SL241130_FORM tr:eq(4)").css('display', '');
                $("#SL241130_FORM tr:eq(5)").css('display', '');
                $("#SL241130_FORM tr:eq(6)").css('display', '');
                $("#SL241130_FORM tr:eq(7)").css('display', '');
                $("#SL241130_FORM tr:eq(8)").css('display', '');
                $("#SL241130_FORM tr:eq(9)").css('display', '');
            }
        });
    },
    classesInfo:function(){
        var classesCode=$('#classesCode');
        var machiningCode=$('#machiningCode');
        var breedCode=$('#breedCode');
        var featureCode=$('#featureCode');
        var weightCode=$('#weightCode');

        //根据产品类别查询产品二级分类
        classesCode.change(function () {
            var classesCodeVal = classesCode.val();
            if (classesCodeVal == '') {
                return;
            }
            //给类别赋值
            $('#classesName').val(classesCode.find("option:selected").text());
            $('#main-content').postUrl(Main.contextPath + "/SL241116/findPdMachining", {'filterMap[classesCode]': classesCodeVal}, function (data) {
                machiningCode.html('');
                machiningCode.append("<option value=''>请选择</option>");
                breedCode.html('');
                breedCode.append("<option value=''>请选择</option>");
                featureCode.html('');
                featureCode.append("<option value=''>请选择</option>");
                weightCode.html('');
                weightCode.append("<option value=''>请选择</option>");
                $.each(data, function (i, item) {
                    machiningCode.append("<option value='" + item.machiningCode + "'>" + item.machiningName + "</option>");

                });
            }, {refreshHtml: false});
        });
    },
    machiningInfo:function(){
        var classesCode=$('#classesCode');
        var machiningCode=$('#machiningCode');
        var breedCode=$('#breedCode');
        var featureCode=$('#featureCode');
        var weightCode=$('#weightCode');
        //根据二级分类查询品种
        machiningCode.change(function () {
            var classesCodeVal = classesCode.val();
            var machiningCodeVal = machiningCode.val();
            if (machiningCodeVal == '') {
                return;
            }
            //给二级类别赋值
            $('#machiningName').val(machiningCode.find("option:selected").text());
            $('#main-content').postUrl(Main.contextPath + "/SL241116/findBreed", {
                'filterMap[classesCode]': classesCodeVal,
                'filterMap[machiningCode]': machiningCodeVal
            }, function (data) {
                breedCode.html('');
                breedCode.append("<option value=''>请选择</option>");
                featureCode.html('');
                featureCode.append("<option value=''>请选择</option>");
                weightCode.html('');
                weightCode.append("<option value=''>请选择</option>");
                $.each(data, function (i, item) {
                    breedCode.append("<option value='" + item.breedCode + "'>" + item.breedName + "</option>");
                });
            }, {refreshHtml: false});
        });
    },
    //根据品种查询特征
    breedInfo:function(){
        var classesCode=$('#classesCode');
        var machiningCode=$('#machiningCode');
        var breedCode=$('#breedCode');
        var featureCode=$('#featureCode');
        var weightCode=$('#weightCode');
        //根据品种查询特征
        breedCode.change(function () {
            var classesCodeVal = classesCode.val();
            var machiningVal = machiningCode.val();
            var breedCodeVal = breedCode.val();
            if (breedCodeVal == '') {
                return;
            }
            //给品种名称赋值
            $('#breedName').val(breedCode.find("option:selected").text());
            $('#main-content').postUrl(Main.contextPath + "/SL241116/findFeature", {
                'filterMap[classesCode]': classesCodeVal,
                'filterMap[machiningCode]': machiningVal,
                'filterMap[breedCode]': breedCodeVal
            }, function (data) {
                featureCode.html('');
                featureCode.append("<option value=''>请选择</option>");
                weightCode.html('');
                weightCode.append("<option value=''>请选择</option>");
                $.each(data, function (i, item) {
                    featureCode.append("<option value='" + item.featureCode + "'>" + item.featureName + "</option>");
                });
            }, {refreshHtml: false});
        });
    },
    //根据特征查询净重
    featureInfo:function(){
        var classesCode=$('#classesCode');
        var machiningCode=$('#machiningCode');
        var breedCode=$('#breedCode');
        var featureCode=$('#featureCode');
        var weightCode=$('#weightCode');
        //根据品种查询特征
        featureCode.change(function () {
            var classesCodeVal = classesCode.val();
            var machiningVal = machiningCode.val();
            var breedCodeVal = breedCode.val();
            var featureCodeVal = featureCode.val();
            if (featureCodeVal == '') {
                return;
            }
            //给特征名称赋值
            $('#featureName').val(featureCode.find("option:selected").text());
            $('#main-content').postUrl(Main.contextPath + "/SL241116/findPdWeight", {
                'filterMap[classesCode]': classesCodeVal,
                'filterMap[machiningCode]': machiningVal,
                'filterMap[breedCode]': breedCodeVal,
                'filterMap[featureCode]': featureCodeVal
            }, function (data) {
                weightCode.html('');
                weightCode.append("<option value=''>请选择</option>");
                $.each(data, function (i, item) {
                    weightCode.append("<option name='" + item.weightVal + "' value='" + item.weightCode + "'>" + item.weightName + "</option>");
                });
            }, {refreshHtml: false});
        });
    },
    //净重选中
    weightInfo:function(){
        var weightCode=$('#weightCode');
        //根据品种查询特征
        weightCode.change(function () {
            //给特征名称赋值
            $('#weightName').val(weightCode.find("option:selected").text());
            $('#weightVal').val(weightCode.find("option:selected").attr("name"));
        });
    },
    bindSaveButton: function () {
        $("#" + SL241130.saveButtonId).click(function () {
            var validator = mainValidation($("#" + SL241130.formId));
            var isValid = validator.form();
            if (isValid) {
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    formData = getFormData($("#" + SL241130.formId));
                    $('#main-content').postUrl($("#" + SL241130.formId).attr("action"), formData, function (data) {
                        $.alertMessage.info(data);
                    }, {refreshHtml: false});
                });
            }
        });
    }
}
$(document).ready(function () {
    //初始化调用
    SL241130.init();

});
