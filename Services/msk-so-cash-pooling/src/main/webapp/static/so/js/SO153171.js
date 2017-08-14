/**
 * 账套设置
 *
 */
var SO153171Upload = {
    init: function () {
        FormUtils.init(SO153171Upload.formId, "SO153171Upload");
        this.exportData();

    },
    /*改善bug1968 modify by renyi on 2016/8/29 start*/
    backFunc: function () {
        $('#main-content').postUrl(Main.contextPath + "/SO153171/init");
    },
    /*改善bug1968 modify by renyi on 2016/8/29 end*/

    exportData: function (rowdata, coltype) {

        $("#SO153171_EXPORT").click(function () {
            var param = new Object();
             /*add for Bug #2425 at 2016/09/06 by ren_yi  start*/
            param["lastPeriodEndStart"] = $("#lastPeriodEndStart").val();
            param["lastPeriodEndEnd"] = $("#lastPeriodEndEnd").val();
            param["periodStart"] = $("#periodStart").val();
            param["periodEnd"] = $("#periodEnd").val();
            param["commDateStart"] = $("#commDateStart").val();
            param["commDateEnd"] = $("#commDateEnd").val();
            param["userNo"] = $("[name=filterMap\\[\\'userNo\\'\\]]").val();
            param["userRole"] = $("[name=filterMap\\[\\'userRole\\'\\]]").val();
            param["userName"] = $("[name=filterMap\\[\\'userName\\'\\]]").val();
            /*add for Bug #2425 at 2016/09/06 by ren_yi  end*/
            var now = new Date();
            var year = now.getFullYear();
            var month = now.getMonth() < 9 ? "0" + (now.getMonth() + 1) : (now.getMonth()+1);
            var day = now.getDate();
            var date = year + "-" + month + "-" + day;
            downloadAsync("reportTemp", "SO153171Logic", "so", "paymantdays_" + date + ".xlsx", param);
        });
        /*改善bug1968 modify by renyi on 2016/8/25 start*/
        $("#SO153171_UPLOAD").click(function () {
            var file = $("#importFile").val();
            if (file == '') {
                alert("请选择文件");
            } else {
                var fileExt = file.substring(file.lastIndexOf("."), file.length)
                fileExt = fileExt.toLowerCase()
                if (fileExt == '.xls' || fileExt == '.xlsx') {
                    var $uploadFile = $("#SO153171UploadForm");
                    var loginId = $("#userId").val();
                    MainUtils.uploadExcelFileWithFunc(Main.contextPath + "/comm/excel/SO153171UploadLogic/upload", "file", {"userId": loginId}, SO153171Upload.backFunc);
                } else {
                    alert("请上传excel文件");
                }
            }
        });
        /*改善bug1968 modify by renyi on 2016/8/25 end*/

    }
}

var SO153171 = {
    SO153171Grid: null,
    searchButtonId: "SO153171_SEARCH",
    formId: "SO153171Form",
    commDateStart: "#commDateStart",
    commDateEnd: "#commDateEnd",
    lastPeriodEndStart: "#lastPeriodEndStart",
    lastPeriodEndEnd: "#lastPeriodEndEnd",
    periodStart: "#periodStart",
    periodEnd: "#periodEnd",
    init: function () {
        SO153171.SO153171Grid = $('#SO153171_list_grid').grid({
            actionHandler: SO153171.actionHandler
        });
        FormUtils.init(SO153171.formId, "SO153171");
        this.bindDatePicber(SO153171.commDateStart);
        this.bindDatePicber(SO153171.commDateEnd);
        this.bindDatePicber(SO153171.lastPeriodEndStart);
        this.bindDatePicber(SO153171.lastPeriodEndEnd);
        this.bindCheckNums(SO153171.periodStart);
        this.bindCheckNums(SO153171.periodEnd);
    },
    bindCheckNums: function (nums) {
        $(nums).change(function () {
            var reg = /^\d+$/;
            if (reg.test($(nums).val().trim())) {
                $(nums).val($(nums).val().trim());
            } else {
                $(nums).val("");
                $.alertMessage.info("周期请输入非负整数");
            }
        })
    },
    bindDatePicber: function (orderTimeId) {
        $(orderTimeId).datepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            changeMonth: true,
            changeYear: true
        });
        $(orderTimeId).change(function () {
            var str = $(orderTimeId).val();
            if (str.length >= 8) {
                // 判断年月日的格式2010-01-01
                var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
                if (reg.test(str)) {
                    if (orderTimeId.endsWith("End")) {
                        var startTime = $(orderTimeId.substring(0, orderTimeId.length - 3) + "Start").val();
                        var endTime = $(orderTimeId).val();
                        if (startTime != null && startTime != 'underfined' && startTime != '' && startTime > endTime) {
                            $(orderTimeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                    if (orderTimeId.endsWith("Start")) {
                        var startTime = $(orderTimeId).val();
                        var endTime = $(orderTimeId.substring(0, orderTimeId.length - 5) + "End").val();
                        if (endTime != null && endTime != 'underfined' && endTime != '' && startTime > endTime) {
                            $(orderTimeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                } else {
                    $(orderTimeId).val("");
                }
            } else {
                $(orderTimeId).val("");
            }
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        //查询
        var commDateStart = $('#commDateStart').val();
        var commDateEnd = $('#commDateEnd').val();
        var lastPeriodEndStart = $('#lastPeriodEndStart').val();
        var commDateEnd = $('#commDateEnd').val();
        var periodStart = $('#periodStart').val();
        var periodEnd = $('#periodEnd').val();

        $('#main-content').postUrl(Main.contextPath + "/SO153171/search/", {
            commDateStart: commDateStart,
            commDateEnd: commDateEnd,
            lastPeriodEndStart: lastPeriodEndStart,
            commDateEnd: commDateEnd,
            periodStart: periodStart,
            periodEnd: periodEnd
        })

    }
}
$(document).ready(function () {
    // 初始化调用
    SO153171.init();
    SO153171Upload.init();
});
