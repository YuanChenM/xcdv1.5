/**
 * 价盘内容显示设置
 *
 * @author ni_shaotang
 */

var SP171196 = {
    SP171196Grid: null,
    formId: "SP171196Form",
    detailRows: [],
    trIndex: 0,
    initDataGrid: function () {
        SP171196.SP171196Grid = $('#SP171196_Grid').grid({
            //actionHandler: SP171196.actionHandler
        });
        SP171196.saveView();
        SP171196.bindDatePicber("#startDate");
        SP171196.bindDatePicber("#endDate");
    },
    formSearch: function () {
        SP171196.SP171196Grid.fnDraw();
        return false;
    },
    saveView: function () {
        $("#SP171196_SAVE").click(function () {
            var detailData = SP171196.SP171196Grid.fnGetData();
            var lgcsSlId = "";//数据列表唯一标志：lgcsCode+slCode+viewId/viewId为空取0
            for (var i = 0; i < detailData.length; i++) {
                if (lgcsSlId.length > 0) {
                    lgcsSlId += ":";
                }
                lgcsSlId += detailData[i]['lgcsCode'] + "," + detailData[i]['slCode'];
            }
            $('#slCode').val(lgcsSlId);
            var formData = getFormData($("#" + SP171196.formId));
            $('#main-content').postUrl(Main.contextPath + "/SP171196/save", formData, function (data) {
                $.alertMessage.info("修改已提交");
            });
        }),
            $("#SP171196_ISDECLARE").click(function () {
                var priceAble = $("#priceAble option:selected").val();
                $('#main-content').postUrl(Main.contextPath + "/SP171196/updateDeclare/" + priceAble, {}, function (data) {
                    $.alertMessage.info("修改已提交");
                });
            }),
            $("#SP171196_DECLARESAVE").click(function () {
                var startDeclare = $("#startDeclare").val();
                var endDeclare = $("#endDeclare").val();
                if (startDeclare > endDeclare) {
                    $.alertMessage.info("开始日期不能大于日期");
                    return;
                }
                var formData = {startTime:startDeclare,endTime:endDeclare};
                $('#main-content').postUrl(Main.contextPath + "/SP171196/saveDeclare/", formData, function (data) {
                    $.alertMessage.info("修改已提交");
                });
            }),
            $("#SP171196_PRICESAVE").click(function () {
                var priceUpdate = $("#priceUpdate").val();
                var startDate = $("#startDate").val();
                var endDate = $("#endDate").val();
                if(!priceUpdate){
                    $.alertMessage.info("请选择价盘周期");
                    return;
                }
                if(!startDate){
                    $.alertMessage.info("请选择开始时间");
                    return;
                }
                if(!endDate){
                    $.alertMessage.info("请选择结束时间");
                    return;
                }
                if (startDate > endDate) {
                    $.alertMessage.info("开始时间超过大于结束时间");
                    return;
                }
                var formData = {"pricecycleCode":priceUpdate,"startDateStr":startDate,"endDateStr":endDate};
                $('#main-content').postUrl(Main.contextPath + "/SP171196/savePrice/", formData, function (data) {
                    $.alertMessage.info("设置已提交");
                });
            }),
            $("#priceUpdate").change(function () {
                var startDate = $("#priceUpdate").find("option:selected").attr("startdate");
                $("#startDate").val(startDate);
                var endDate = $("#priceUpdate").find("option:selected").attr("enddate");
                $("#endDate").val(endDate);
            })
    },
    bindDatePicber: function (priceDate) {
        $(priceDate).datetimepicker({
            prevText: '上月',         // 前选按钮提示
            nextText: '下月',         // 后选按钮提示
            changeYear: true,          // 年下拉菜单
            changeMonth: true,             // 月下拉菜单
            showButtonPanel: true,         // 显示按钮面板
            showMonthAfterYear: true,  // 月份显示在年后面
            showHour: true,  // 显示小时
            showMinute: true,  // 显示分
            timeFormat: 'HH:mm',//时间格式
            stepHour: 1,//小时步长
            stepMinute: 1,  //分步长
            currentText: "本月",         // 当前日期按钮提示文字
            closeText: "确定",           // 关闭按钮提示文字
            dateFormat: "yy-mm-dd",       // 日期格式
            timeText:"时间",//
            hourText:"时",//
            minuteText:"分",//
            onClose: function (dateText, inst) {// 关闭事件
            }
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SP171196.initDataGrid();
});

