/**
 * Created by guan_zhongheng on 2016/8/9.
 */
var $List_Grid;
var BY121323 = {
    searchButtonId:"BY121323_SEARCH",
    formId:"BY121323Form",
    startTime: "#startTime",
    endTime: "#endTime",
    initDataGrid:function(){
        $List_Grid = $('#BY121323_list_grid').grid({});
        this.bindDatePicber(BY121323.startTime);
        this.bindDatePicber(BY121323.endTime);
        this.bindSelectChange();
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
            dateFormat: "yy-mm-dd"       // 日期格式
        });
        $(time).change(function () {
            var str = $(time).val();
            if (str.length >= 8) {
                // 判断年月日的格式2010-01-01
                var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
                if (reg.test(str)) {
                    if (time == "#endTime") {
                        var startTime = $("#startTime").val();
                        var endTime = $(time).val();
                        if (startTime != null && startTime != 'underfined' && startTime != '' && startTime > endTime) {
                            $(time).val("");
                            $.alertMessage.info("输入正确的时间范围");
                            return false;
                        }
                    }
                    if (time == "#startTime") {
                        var startTime = $(time).val();
                        var endTime = $("#endTime").val();
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
    //绑定查询按钮
    searchData: function () {
        $("#" + BY121323.searchButtonId).click(function () {
            var startTime= $("#startTime").val();
            var endTime= $("#endTime").val();
            if(startTime =="" && endTime==""){
                FormUtils.setFormValue(BY121323.formId, "BY121323");
                $List_Grid.fnDraw();
            }else if(startTime !="" && endTime !=""){
                FormUtils.setFormValue(BY121323.formId, "BY121323");
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
            $('#main-content').postUrl(Main.contextPath + "/BY121322/lgcsAreaChange/" + lgcsAreaCode, null,
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

        // 城市变更
        //$("#cityCode").change(function () {
        //    var cityCode = $("#cityCode option:selected").val();
        //    if (cityCode == "") {
        //        $("#districtCode").html("");
        //        $("#districtCode").append("<option value=''>请选择</option>");
        //        return;
        //    }
        //    $('#main-content').postUrl(Main.contextPath + "/BY121322/cityChange/" + cityCode, null,
        //        function (data) {
        //            $("#districtCode").html("");
        //            $("#districtCode").append("<option value=''>请选择</option>");
        //            $.each(data, function (i, item) {
        //                $("#districtCode").append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
        //            });
        //        },{refreshHtml:false});
        //});

        //一级分类变更
        $("#classesCode").change(function () {
            var classesCode = $("#classesCode option:selected").val();
            if (classesCode == "") {
                $("#machiningCode").html("");
                $("#machiningCode").append("<option value=''>--请选择--</option>");
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/BY121322/classesChange/" + classesCode, null,
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
    BY121323.initDataGrid();
})