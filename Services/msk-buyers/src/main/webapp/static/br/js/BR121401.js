var $List_Grid;
var BR121401 = {
    searchButtonId: "BR121401_SEARCH",
    formId: "BR121401Form",
    fileStartTime: "#fileStartTime",
    fileEndTime: "#fileEndTime",
    init: function () {
        $List_Grid = $('#BR121401_list_grid').grid({
            actionHandler: BR121401.actionHandler,
            fnRowCallback: BR121401.fnRowHandler,
            can_generate: BR121401.canGenerate,
            can_download: BR121401.canDownload
        });
        this.bindDatePicber(fileStartTime);
        this.bindDatePicber(fileEndTime);
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
            showDay: false,
            prevText: '上月',         // 前选按钮提示
            nextText: '下月',         // 后选按钮提示
            changeYear: true,          // 年下拉菜单
            changeMonth: true,             // 月下拉菜单
            showButtonPanel: true,         // 显示按钮面板
            showMonthAfterYear: true,  // 月份显示在年后面
            currentText: "本月",         // 当前日期按钮提示文字
            closeText: "确定",           // 关闭按钮提示文字
            dateFormat: "yy-mm",       // 日期格式
            onClose: function (dateText, inst) {// 关闭事件   ui-datepicker-calendar
                var month = $("#ui-datepicker-div .ui-datepicker-month").val();
                var year = $("#ui-datepicker-div .ui-datepicker-year").val();
                $(this).datepicker('setDate', new Date(year, month, 1));
            }
        });
    },

    //绑定查询按钮
    searchData: function () {
        $("#" + BR121401.searchButtonId).click(function () {
            var fileStartTime = $("#fileStartTime").val() + " 00:00:00";
            var fileEndTime = $("#fileEndTime").val() + " 23:59:59";
            if(fileStartTime =="" && fileEndTime==""){
                FormUtils.setFormValue(BR121401.formId, "BR121401");
                $List_Grid.fnDraw();
            }else if(fileStartTime !="" && fileEndTime !=""){
                FormUtils.setFormValue(BR121401.formId, "BR121401");
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
    fnRowHandler : function(tr,rowdata){
        var $td = $(tr).children('td').eq(3);
        $td.html(downloadExcel(rowdata));
    },
    //绑定下载按钮
    actionHandler: function (rowdata, coltype, row, col) {
    },
    canGenerate: function(rowdata){
        if(rowdata.fileStatus == '0'){
            return true;
        }
        return false;
    },
    canDownload: function(rowData){
        if(rowData.fileStatus == '1'){
            return true;
        }
        return false;
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
            $('#main-content').postUrl(Main.contextPath + "/BR121401/lgcsAreaChange/" + lgcsAreaCode, null,
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

        // 城市变更
        $("#cityCode").change(function () {
            var cityCode = $("#cityCode option:selected").val();
            if (cityCode == "") {
                $("#districtCode").html("");
                $("#districtCode").append("<option value=''>请选择</option>");
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/BR121401/cityChange/" + cityCode, null,
                function (data) {
                    $("#districtCode").html("");
                    $("#districtCode").append("<option value=''>请选择</option>");
                    $.each(data, function (i, item) {
                        $("#districtCode").append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
                    });
                },{refreshHtml:false});
        });

        //一级分类变更
        $("#classesCode").change(function () {
            var classesCode = $("#classesCode option:selected").val();
            if (classesCode == "") {
                $("#machiningCode").html("");
                $("#machiningCode").append("<option value=''>-----请选择-----</option>");
                return ;
            }
            $('#main-content').postUrl(Main.contextPath + "/BR121401/classesChange/" + classesCode, null,
                function (data) {
                    $("#machiningCode").html("");
                    $("#machiningCode").append("<option value=''>-----请选择-----</option>");
                    $.each(data, function (i, item) {
                        $("#machiningCode").append("<option value='" + item.machiningCode + "'>" + item.machiningName + "</option>");
                    });
                }, {refreshHtml: false});
        });
    }

}
$(document).ready(function () {
    // 初始化调用
    BR121401.init();
});