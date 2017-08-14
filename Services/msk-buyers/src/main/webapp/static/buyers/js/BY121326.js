var BY121326 = {
    generateButtonId :"BY121326_CREATE" ,
    formId:"BY121326Form",
    fileStartTime: "#fileStartTime",
    fileEndTime: "#fileEndTime",
    initDataGrid:function(){
        this.bindDatePicber(BY121326.fileStartTime);
        this.bindDatePicber(BY121326.fileEndTime);
        this.bindSelectChange();
        this.createExcel();
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
                // 判断年月日的格式2010-01-01
                var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
                if (reg.test(str)) {
                    if (time == "#fileEndTime") {
                        var startTime = $("#fileStartTime").val();
                        var endTime = $(time).val();
                        if (startTime != null && startTime != 'underfined' && startTime != '' && startTime > endTime) {
                            $(time).val("");
                            $.alertMessage.info("输入正确的时间范围");
                            return false;
                        }
                    }
                    if (time == "#fileStartTime") {
                        var startTime = $(time).val();
                        var endTime = $("#fileEndTime").val();
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
//excel报表判断 下载excel
createExcel:function (){
    $("#" + BY121326.generateButtonId).click(function(){
        var fileStartTime= $("#fileStartTime").val();
        var fileEndTime= $("#fileEndTime").val();
        var lgcsAreaCode=$("#lgcsAreaCode").val();
        var cityCode = $("#cityCode").val();
        var buyerId=$('#buyerId').val();
        var excel = "BY121326";
        var logic = "buyerRegisterPoolLogic";
        var module = "by";
        var fileName = "分销买家营销期冻品管家营销信息管控表.xlsx";
        var param = new Object();
        param['buyerId'] = buyerId;
        param['fileStartTime'] = $(BY121326.fileStartTime).val();
        param['fileEndTime'] = $(BY121326.fileEndTime).val();
        param['lgcsAreaCode'] = $("#lgcsAreaCode option:selected").val();
        param['cityCode'] = $("#cityCode option:selected").val();


        var startTime= $("#fileStartTime").val();
        var endTime= $("#fileEndTime").val();

        if(startTime =="" && endTime==""){
            downloadAsync(excel, logic, module,fileName, param);
            $List_Grid.fnDraw();
        }else if(startTime !="" && endTime !=""){
            downloadAsync(excel, logic, module,fileName, param);
            $List_Grid.fnDraw();
        }else{
            if(startTime =="" && endTime !=""){
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
                $("#cityCode").append("<option value=''>--请选择--</option>");
                $("#districtCode").html("");
                $("#districtCode").append("<option value=''>--请选择--</option>");
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/BY121326/lgcsAreaChange/" + lgcsAreaCode, null,
                function (data) {
                    $("#cityCode").html("");
                    $("#cityCode").append("<option value=''>--请选择--</option>");
                    $("#districtCode").html("");
                    $("#districtCode").append("<option value=''>--请选择--</option>");
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
                $("#machiningCode").append("<option value=''>--请选择--</option>");
                return ;
            }
            $('#main-content').postUrl(Main.contextPath + "/BY121326/classesChange/" + classesCode, null,
                function (data) {
                    $("#machiningCode").html("");
                    $("#machiningCode").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#machiningCode").append("<option value='" + item.machiningCode + "'>" + item.machiningName + "</option>");
                    });
                }, {refreshHtml: false});
        });
    }
};
$(document).ready(function () {
    // 初始化调用
    BY121326.initDataGrid();
})