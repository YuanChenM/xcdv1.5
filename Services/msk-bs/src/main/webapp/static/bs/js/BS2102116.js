/**
 * 新增工作经历
 *
 * @author cx
 */

var BS2102116 = {
    formId: "BS2102116FormId",
    addWorkButtonId: "BS2102116_SAVE",
    init: function () {
        this.bindDatePicber(".dataTime");
        this.addWorkExperience();
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
        this.closeDate();
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    },

    bindDatePicber: function (time) {
        $(time).datepicker({
            prevText: '上月',         // 前选按钮提示
            nextText: '下月',         // 后选按钮提示
            changeYear: true,          // 年下拉菜单
            changeMonth: true,             // 月下拉菜单
            showButtonPanel: true,         // 显示按钮面板
            showMonthAfterYear: true,  // 月份显示在年后面
            currentText: "本月",         // 当前日期按钮提示文字
            closeText: 'Clear',           // 关闭按钮提示文字
            dateFormat: "yy-mm-dd",    // 日期格式
            maxDate: new Date(),
            yearRange: "-50:+0"
        });
    },
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    // 绑定按钮
    addWorkExperience: function () {

        $("#" + BS2102116.addWorkButtonId).click(function () {
            var slCode = $("#slCode").val();
            var houseCode = $("#houseCode").val();
            //获取工作经历数据
            var workArray = new Array();
            var work = {};
            var workStart = $("#workStart").val();
            var workEnd = $("#workEnd").val();
            var workComp = $("#workComp").val();
            var workStation = $("#workStation").val();
            var workPosition = $("#workPosition").val();
            var workId = $("#workId").val();
            if(workComp.length > 50){
                $.alertMessage.info("工作单位不能多于50个字");
                return false;
            }
            if(workStation.length > 50){
                $.alertMessage.info("工作岗位不能多于50个字");
                return false;
            }
            if(workPosition.length > 50){
                $.alertMessage.info("工作职位不能多于50个字");
                return false;
            }
            if (workStart || workEnd || workComp || workStation || workPosition) {
                work["workId"] = workId;
                work["workStart"] = workStart;
                work["workEnd"] = workEnd;
                work["workComp"] = workComp;
                work["workStation"] = workStation;
                work["workPosition"] = workPosition;
                work["slCode"] = slCode;
                work["houseCode"] = houseCode;
                workArray[0] = work;
            } else {
                $.alertMessage.info("请填写内容");
                return false;
            }
            var houseAccount = {
                slCode: slCode,
                houseCode: houseCode
            };
            var param = {
                houseWorkList: workArray,
                houseAccount: houseAccount
            };
            //save
            $('#main-content').postUrl(Main.contextPath + "/BS2102113/save", JSON.stringify(param), function (data) {
                if (data > 0) {
                    $.alertMessage.info("保存成功!");
                    BS2102119.refresh();
                    $.pdialog.close("work");
                } else {
                    $.alertMessage.info("保存失败!");
                }
            }, {refreshHtml: false, dataType: "json", contentType: 'application/json;charset=utf-8'});
        });


    }


}

$(document).ready(function () {
    // 初始化调用
    BS2102116.init();
});
