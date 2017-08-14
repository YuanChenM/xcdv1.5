var BY121324 = {
    searchButtonId:"BY121322_SEARCH",
    generateButtonId :"BY121324_CREATE" ,
    formId:"BY121322Form",
    fileStartTime: "#fileStartTime",
    fileEndTime: "#fileEndTime",
    initDataGrid:function(){
        this.bindDatePicber(BY121324.fileStartTime);
        this.bindDatePicber(BY121324.fileEndTime);
        this.bindSelectChange();
        this.createExcel();
        this.searchData();
        this.closeDate();
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    bindDatePicber: function (time) {
        $(time).datepicker({
            prevText: '上月',         // 前选按钮提示
            nextText: '下月',         // 后选按钮提示
            changeYear: true,          // 年下拉菜单
            changeMonth: true,             // 月下拉菜单
            showButtonPanel: true,         // 显示按钮面板
            showMonthAfterYear: true,  // 月份显示在年后面
            currentText: "Today",
            closeText: "Clear",
            dateFormat: "yy-mm-dd"    // 日期格式
        });
        $(time).change(function () {
            var str = $(time).val();
            if (str.length >= 8) {
                // 判断年月日的格式2011-01-01
                var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
                if (reg.test(str)) {
                    if (time.endsWith("End")) {
                        var startTime = $(time.substring(0, time.length - 3) + "StartTime").val();
                        var endTime = $(time).val();
                        if (startTime != null && startTime != 'underfined' && startTime != '' && startTime > endTime) {
                            $(time).val("");
                            $.alertMessage.info("输入正确的时间范围");
                            return false;
                        }
                    }
                    if (time.endsWith("Start")) {
                        var startTime = $(time).val();
                        var endTime = $(time.substring(0, time.length - 5) + "EndTime").val();
                        if (endTime != null && endTime != 'underfined' && endTime != '' && startTime > endTime) {
                            $(time).val("");
                            $.alertMessage.info("输入正确的时间范围");
                            return false;
                        }
                    }
                } else {
                    $(time).val("");
                }
            } else {
                $(time).val("");
            }
        });
    },
//excel报表判断 生成列表
createExcel:function (){
    $("#" + BY121324.generateButtonId).click(function(){
        var fileStartTime= $("#fileStartTime").val();
        var fileEndTime= $("#fileEndTime").val();
        var lgcsAreaCode=$("#lgcsAreaCode").val();
        var cityCode = $("#cityCode").val();
        var buyerId=$('#buyerId').val();
        if(fileStartTime==""){
            $.alertMessage.info("报表开始时间不能为空!");
            return false;
        }else if(fileEndTime==""){
            $.alertMessage.info("报表结束时间不能为空!");
            return false;
        }else if(fileStartTime>fileEndTime){
            $.alertMessage.info("报表开始时间不能大于结束时间!");
            return false;
        }else if(lgcsAreaCode==""){
            $.alertMessage.info("请选择物流区!");
            return false;
        }else if(cityCode==""){
            $.alertMessage.info("请选择地区!");
            return false;
        }
        $('#main-content').postUrl(Main.contextPath + "/BY121324/createExcel", {
            "fileStartTime" : fileStartTime,
            "fileEndTime" : fileEndTime,
            "lgcsAreaCode" : lgcsAreaCode,
            "cityCode" : cityCode,
            "buyerId":buyerId
        }, function (data) {
            if(data == '0'){
                $.alertMessage.info("报表已经下载!");
            }
        }, {refreshHtml: false});
    });
},
    //绑定查询按钮
    searchData: function () {
        $("#" + BY121324.searchButtonId).click(function () {
            var fileStartTime= $("#fileStartTime").val()  + " 00:00:00";
            var fileEndTime= $("#fileEndTime").val() +  " 23:59:59";
            if(fileStartTime =="" && fileEndTime==""){
                FormUtils.setFormValue(BY121324.formId, "BY121324");
                $List_Grid.fnDraw();
            }else if(fileStartTime !="" && fileEndTime !=""){
                FormUtils.setFormValue(BY121324.formId, "BY121324");
                $List_Grid.fnDraw();
            }else{
                if(fileStartTime =="" && fileEndTime !=""){
                    $.alertMessage.warn("请输入起始时间 !");
                    return false;
                }else{
                    $.alertMessage.warn("请输入结束时间 !");
                    return false;
                }
            }
        });
    },
    bindSelectChange: function () {
        // 物流区变更
        $("#lgcsAreaCode").change(function () {
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            if (lgcsAreaCode == "") {
                $("#cityCode").html("");
                $("#cityCode").append("<option value=''>请选择</option>");
                $("#districtCode").html("");
                $("#districtCode").append("<option value=''>请选择</option>");
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/BY121324/lgcsAreaChange/" + lgcsAreaCode, null,
                function (data) {
                    $("#cityCode").html("");
                    $("#cityCode").append("<option value=''>请选择</option>");
                    $("#districtCode").html("");
                    $("#districtCode").append("<option value=''>请选择</option>");
                    $.each(data, function (i, item) {
                        $("#cityCode").append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                    });
                }, {refreshHtml: false});
        });

        //一级分类变更
        $("#classesCode").change(function () {
            var classesCode = $("#classesCode option:selected").val();
            if (classesCode == "") {
                $("#machiningCode").html("");
                $("#machiningCode").append("<option value=''>-----请选择-----</option>");
                return ;
            }
            $('#main-content').postUrl(Main.contextPath + "/BY121324/classesChange/" + classesCode, null,
                function (data) {
                    $("#machiningCode").html("");
                    $("#machiningCode").append("<option value=''>-----请选择-----</option>");
                    $.each(data, function (i, item) {
                        $("#machiningCode").append("<option value='" + item.machiningCode + "'>" + item.machiningName + "</option>");
                    });
                }, {refreshHtml: false});
        });
    }
};
$(document).ready(function () {
    // 初始化调用
    BY121324.initDataGrid();
})