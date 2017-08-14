
var SC182211 = {
    saveButtonId: "SC182211_SAVE",
    formId: "SC182211Form",
    initDataGrid: function () {
        this.bindDatePicber("#priceDate");
        this.closeDate();
        this.changeClassData();
        this.saveData();
    },
    bindDatePicber: function(manuDate){
        $(manuDate).datepicker({
            prevText: '上月',         // 前选按钮提示
            nextText: '下月',         // 后选按钮提示
            changeYear: true,          // 年下拉菜单
            changeMonth: true,             // 月下拉菜单
            showButtonPanel: true,         // 显示按钮面板
            showMonthAfterYear: true,  // 月份显示在年后面
            currentText: "本月",         // 当前日期按钮提示文字
            closeText: "清除",           // 关闭按钮提示文字
            dateFormat: "y-mm"       // 日期格式
            /*minDate:new Date(),			//最小日期*/
            // onClose: function(dateText, inst) {// 关闭事件
            //     var month = $("#ui-datepicker-div .ui-datepicker-month").val();
            //     var year = $("#ui-datepicker-div .ui-datepicker-year").val();
            //     $(this).datepicker('setDate', new Date(year, month, 1));
            // }
        });
    },
    //Add for 3464 at 2016/11/01 by likai Start
    closeDate:function () {
        $(document).on("click", "button.ui-datepicker-close", function () {
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    //Add for 3464 at 2016/11/01 by likai End
    changeClassData:function(){
        $("#slCode").change(function(){
            $("#pdClassesCode").find("option").not(":first").remove();
            $("#machiningCode").find("option").not(":first").remove();
            $("#pdBreedCode").find("option").not(":first").remove();
            $("#pdFeatureCode").find("option").not(":first").remove();
            $("#normsCode").find("option").not(":first").remove();
            $("#weightCode").find("option").not(":first").remove();
            $("#slPdArtno").find("option").not(":first").remove();
            $("#brandId").find("option").not(":first").remove();
            $("#prodEpId").find("option").not(":first").remove();
            $("#groupCode").find("option").not(":first").remove();
            $("#salePlatform").find("option").not(":first").remove();
            $("#half").find("option").not(":first").remove();
            $("#priceDate").val("");
            formData = getFormData($("#" + SC182211.formId));
            if(null!=$("#slCode").val() && ''!=$("#slCode").val()){
                $("#main-content").postUrl(Main.contextPath + "/SC182211/queryClass",{slCode:$("#slCode").val(),treeLevel:1},function(data){
                    if(null!=data && data.length>=1){
                        $(data).each(function(i,val){
                            $("#pdClassesCode").append("<option value='"+val.levelCode+"'>"+val.levelName+"</option>").find("option").eq(i+1).attr("valuetwo",val.classestreeCode);
                        });
                    };
                    $("#groupCode").append("<option value='1'>A1</option><option value='2'>A2</option><option value='3'>A3</option>");
                    $("#salePlatform").append("<option value='1'>神农客</option><option value='2'>美侍客</option>");
                    $("#half").append("<option value='1'>1-5日</option><option value='2'>6-10日</option><option value='3'>11-15日</option><option value='4'>16-20日</option>" +
                        "<option value='5'>21-25日</option><option value='6'>26日-月底</option>");
                },{refreshHtml:false});
            }
        });
        $("#pdClassesCode").change(function(){
            $("#machiningCode").find("option").not(":first").remove();
            $("#pdBreedCode").find("option").not(":first").remove();
            $("#pdFeatureCode").find("option").not(":first").remove();
            $("#weightCode").find("option").not(":first").remove();
            $("#slPdArtno").find("option").not(":first").remove();
            $("#normsCode").find("option").not(":first").remove();
            $("#prodEpId").find("option").not(":first").remove();
            $("#brandId").find("option").not(":first").remove();
            if(null!=$("#pdClassesCode").val() && ''!=$("#pdClassesCode").val()) {
                $("#main-content").postUrl(Main.contextPath + "/SC182211/queryClass", {
                    parentCode: $("#pdClassesCode").find("option:selected").attr("valuetwo"),
                    classCode: $("#pdClassesCode").val(),
                    slCode: $("#slCode").val(),
                    treeLevel: 2
                }, function (data) {
                    if (null != data && data.length >= 1) {
                        $(data).each(function (i, val) {
                            $("#machiningCode").append("<option value='" + val.levelCode + "'>" + val.levelName + "</option>").find("option").eq(i + 1).attr("valuetwo", val.classestreeCode);
                        });
                    }
                }, {refreshHtml: false});
            }
        });
        $("#machiningCode").change(function(){
            $("#pdBreedCode").find("option").not(":first").remove();
            $("#pdFeatureCode").find("option").not(":first").remove();
            $("#weightCode").find("option").not(":first").remove();
            $("#slPdArtno").find("option").not(":first").remove();
            $("#normsCode").find("option").not(":first").remove();
            $("#brandId").find("option").not(":first").remove();
            $("#prodEpId").find("option").not(":first").remove();
            if(null!=$("#machiningCode").val() && ''!=$("#machiningCode").val()) {
                $("#main-content").postUrl(Main.contextPath + "/SC182211/queryClass", {
                    parentCode: $("#machiningCode").find("option:selected").attr("valuetwo"),
                    classCode: $("#machiningCode").val(),
                    slCode: $("#slCode").val(),
                    treeLevel: 3
                }, function (data) {
                    if (null != data && data.length >= 1) {
                        $(data).each(function (i, val) {
                            $("#pdBreedCode").append("<option value='" + val.levelCode + "'>" + val.levelName + "</option>").find("option").eq(i + 1).attr("valuetwo", val.classestreeCode);
                        });
                    }
                }, {refreshHtml: false});
            }
        });
        $("#pdBreedCode").change(function(){
            $("#pdFeatureCode").find("option").not(":first").remove();
            $("#weightCode").find("option").not(":first").remove();
            $("#slPdArtno").find("option").not(":first").remove();
            $("#normsCode").find("option").not(":first").remove();
            $("#prodEpId").find("option").not(":first").remove();
            $("#brandId").find("option").not(":first").remove();
            if(null!=$("#pdBreedCode").val() && ''!=$("#pdBreedCode").val()) {
                $("#main-content").postUrl(Main.contextPath + "/SC182211/queryClass", {
                    parentCode: $("#pdBreedCode").find("option:selected").attr("valuetwo"),
                    classCode: $("#pdBreedCode").val(),
                    slCode: $("#slCode").val(),
                    treeLevel: 4
                }, function (data) {
                    if (null != data && data.length >= 1) {
                        $(data).each(function (i, val) {
                            $("#pdFeatureCode").append("<option value='" + val.levelCode + "'>" + val.levelName + "</option>").find("option").eq(i + 1).attr("valuetwo", val.classestreeCode);
                        });
                    }
                }, {refreshHtml: false});
            }
        });
        $("#pdFeatureCode").change(function(){
            $("#weightCode").find("option").not(":first").remove();
            $("#slPdArtno").find("option").not(":first").remove();
            $("#normsCode").find("option").not(":first").remove();
            $("#prodEpId").find("option").not(":first").remove();
            $("#brandId").find("option").not(":first").remove();
            formData = getFormData($("#" + SC182211.formId));
            if(null!=$("#pdFeatureCode").val() && ''!=$("#pdFeatureCode").val()) {
                $("#main-content").postUrl(Main.contextPath + "/SC182211/queryClass", {
                    parentCode: $("#pdFeatureCode").find("option:selected").attr("valuetwo"),
                    classCode: $("#pdFeatureCode").val(),
                    slCode: $("#slCode").val(),
                    treeLevel: 5
                }, function (data) {
                    if (null != data && data.length >= 1) {
                        $(data).each(function (i, val) {
                            $("#weightCode").append("<option value='" + val.levelCode + "'>" + val.levelName + "</option>").find("option").eq(i + 1).attr("valuetwo", val.classestreeCode);
                        });
                    }
                }, {refreshHtml: false});
            }
        });
        $("#weightCode").change(function(){
            $("#slPdArtno").find("option").not(":first").remove();
            $("#normsCode").find("option").not(":first").remove();
            $("#prodEpId").find("option").not(":first").remove();
            $("#brandId").find("option").not(":first").remove();
            formData = getFormData($("#" + SC182211.formId));
            if(null!=$("#weightCode").val() && ''!=$("#weightCode").val()) {
                $("#main-content").postUrl(Main.contextPath + "/SC182211/querySlPropEp", formData, function (data) {
                    if (null != data && data.length >= 1) {
                        $(data).each(function (i, val) {
                            $("#prodEpId").append("<option value='" + val.slCodeManufacture + "'>" + val.epName + "</option>").find("option").eq(i + 1).attr("valuetwo", val.prodEpId);
                        });
                    }
                }, {refreshHtml: false});
                $("#main-content").postUrl(Main.contextPath + "/SC182211/queryNormsOut",formData,function(data){
                    if(null!=data && data.length>=1){
                        $(data).each(function(i,val){
                            $("#normsCode").append("<option value='"+val.normsCode+"'>"+val.normsOut+"</option>");
                        });
                    }
                },{refreshHtml:false});
                $("#main-content").postUrl(Main.contextPath + "/SC182211/querySlBrand", formData, function (data) {
                    if (null != data && data.length >= 1) {
                        $(data).each(function (i, val) {
                            $("#brandId").append("<option value='" + val.brandId + "'>" + val.brandName + "</option>").find("option").eq(i+1).attr("valuetwo",val.brandClass);;
                        });
                    }
                }, {refreshHtml: false});
            }
        });
        $("#salePlatform").change(function(){
            $("#slPdArtno").find("option").not(":first").remove();
            if(null!=$("#salePlatform").val() && ''!=$("#salePlatform").val()) {
                formData = getFormData($("#" + SC182211.formId));
                $("#main-content").postUrl(Main.contextPath + "/SC182211/querySlPdArtno", formData, function (data) {
                    if (null != data && data.length >= 1) {
                        $(data).each(function (i, val) {
                            $("#slPdArtno").append("<option value='" + val.slPdArtno + "'>" + val.slPdArtno + "</option>");
                        });
                    }
                }, {refreshHtml: false});
            }
        });
    },
    saveData:function(){
        $("#SC182211_SAVE").click(function(){
            if(''!=$("#slCode").val()  &&''!=$("#lgcsCode").val() && ''!=$("#pdClassesCode").val() && ''!=$("#machiningCode").val() && ''!=$("#pdBreedCode").val() && ''!=$("#pdFeatureCode").val() && ''!=$("#groupCode").val() && ''!=$("#prodEpId").val() && ''!=$("#salePlatform").val() && ''!=$("#brandId").val() && ''!=$("#priceDate").val() && ''!=$("#half").val() && ''!=$("#pageNumber").val() && ''!=$("#normsCode").val() && ''!=$("#slPdArtno").val() && ''!=$("#weightCode").val()){

                if(!isNaN($("#number").val()) && !isNaN($("#numberTwo").val())) {
                    var first = pad($("#number").val(), 5);
                    var second = pad($("#numberTwo").val(), 5);
                    $("#number").val(first);
                    $("#numberTwo").val(second);
                }else {
                    $.alertMessage.info("流水号不合法!");
                    return;
                };
                formData = getFormData($("#" + SC182211.formId));
                formData.slCodeName=$("#slCode").find("option:selected").text();
                formData.pdClassesName=$("#pdClassesCode").find("option:selected").text();
                formData.machiningName=$("#machiningCode").find("option:selected").text();
                formData.pdBreedName=$("#pdBreedCode").find("option:selected").text();
                formData.pdFeatureName=$("#pdFeatureCode").find("option:selected").text();
                formData.weightName=$("#weightCode").find("option:selected").text();
                formData.groupName=$("#groupCode").find("option:selected").text();
                formData.normsOut=$("#normsCode").find("option:selected").text();
                formData.slCodeManufacture=$("#prodEpId").find("option:selected").attr("value");
                formData.epName=$("#prodEpId").find("option:selected").text();
                formData.brandName=$("#brandId").find("option:selected").text();
                formData.slCodeDis=$("#slCode").find("option:selected").attr("valuetwo");
                formData.brandClass=$("#brandId").find("option:selected").attr("valuetwo");
                formData.slPdArtno=$("#slPdArtno").find("option:selected").text();
                formData.salePlatform=$("#salePlatform").find("option:selected").attr("value");
                formData.groupCode=$("#groupCode").find("option:selected").attr("value");
                formData.lgcsAreaCode=$("#lgcsCode").find("option:selected").val();
                formData.lgcsAreaName=$("#lgcsCode").find("option:selected").text();


                if($("#number").val()>$("#numberTwo").val()){
                    $.alertMessage.info("批次号第一位不能大于第二位");
                }else{
                    $("#main-content").postUrl(Main.contextPath + "/SC182211/saveData", formData,function(data) {
                        if (null != data || data.length >= 1) {
                            $.alertMessage.info("数据生成完毕，请导出数据文件后打印!");
                        }
                    }, {refreshHtml: false});
                }

            }else{
                $.alertMessage.info("选项不能为空,请检查!");
            }
        });

        $("#SC182211_EXPORT").click(function(){
            var dform = $("<form>");   //定义一个form表单
            dform.attr('style', 'display:none');   //在form表单中添加查询参数
            dform.attr('target', '');
            dform.attr('method', 'post');
            dform.attr('action', Main.contextPath +"/SC182211/dataExport");
            $('body').append(dform);  //将表单放置在web中
            dform.submit();
        });

        $("#SC182211_DELETE").click(function(){
            $("#main-content").postUrl(Main.contextPath + "/SC182211/dataDelete", null, function (data) {
                if (null != data && data>= 1) {
                    $.alertMessage.info("数据清除成功!");
                }
            }, {refreshHtml: false});
        });

        $("#SC182211_SEARCH").click(function(){
            formData = getFormData($("#" + SC182211.formId));
            formData.slCodeDis=$("#slCode").find("option:selected").attr("valuetwo");
            if(''!=$("#slCode").val() && ''!=$("#pdClassesCode").val() && ''!=$("#machiningCode").val() && ''!=$("#pdBreedCode").val() && ''!=$("#pdFeatureCode").val() && ''!=$("#groupCode").val() && ''!=$("#prodEpId").val() && ''!=$("#platform").val() && ''!=$("#brandId").val() && ''!=$("#priceDate").val() && ''!=$("#half").val() && ''!=$("#pageNumber").val() && ''!=$("#normsCode").val() && ''!=$("#slPdArtno").val() && ''!=$("#weightCode").val()) {
                $("#main-content").postUrl(Main.contextPath + "/SC182211/dataSearch", formData, function (data) {
                    if (null != data && data.length >= 1) {
                        $.alertMessage.info(data);
                    } else {
                        $.alertMessage.info("暂无数据!");
                    }
                }, {refreshHtml: false});
            }else{
                $.alertMessage.info("选项不能为空,请检查!");
            }
        });

        $("#SC182211_SEARCHLOT").click(
            function(){
                formData = getFormData($("#" + SC182211.formId));
                $("#main-content").postUrl(Main.contextPath + "/SC182212/init");
            }
        );

        $("#SC182211_SEARCHSERIAL").click(function(){
            formData = getFormData($("#" + SC182211.formId));
            $("#main-content").postUrl(Main.contextPath + "/SC182213/init");
        });
        function pad(num, n) {
            var len = num.toString().length;
            while(len < n) {
                num = "0" + num;
                len++;
            }
            return num;
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    SC182211.initDataGrid();
});