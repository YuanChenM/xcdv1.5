/**
 * 新增教育经历
 *
 * @author cx
 */

var BS2102117 = {
    addEduButtonId: "BS2102117_SAVE",
    init: function () {
        this.bindDatePicber(".dataTime");
        this.addEduExperience();
        //添加日期清除按钮
        this.closeDate();
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
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    // 绑定按钮
    addEduExperience: function () {
        $("#" + BS2102117.addEduButtonId).click(function () {
            var slCode = $("#slCode").val();
            var houseCode = $("#houseCode").val();
            //获取教育经历数据
            var eduArray = new Array();
            var edu = {};
            var eduStart = $("#eduStart").val();
            var eduEnd = $("#eduEnd").val();
            var eduComp = $("#eduComp").val();
            var eduRecord = $("#eduRecord").val();
            var eduDegree = $("#eduDegree").val();
            var eduId = $("#eduId").val();
            if(eduComp.length > 50){
                $.alertMessage.info("教育单位不能多于50个字");
                return false;
            }
            if(eduRecord.length > 10){
                $.alertMessage.info("学历不能多于10个字");
                return false;
            }
            if(eduDegree.length > 10){
                $.alertMessage.info("学位不能多于10个字");
                return false;
            }
            if (eduStart || eduEnd || eduComp || eduRecord || eduDegree) {
                edu["eduId"] = eduId;
                edu["eduStart"] = eduStart;
                edu["eduEnd"] = eduEnd;
                edu["eduComp"] = eduComp;
                edu["eduRecord"] = eduRecord;
                edu["eduDegree"] = eduDegree;
                edu["slCode"] = slCode;
                edu["houseCode"] = houseCode;
                eduArray[0] = edu;
            }else {
                $.alertMessage.info("请填写内容");
                return false;
            }

            var houseAccount = {
                slCode: slCode,
                houseCode: houseCode
            };
            var param = {
                houseEduList: eduArray,
                houseAccount: houseAccount
            };
            //save
            $('#main-content').postUrl(Main.contextPath + "/BS2102113/save", JSON.stringify(param), function (data) {
                if (data > 0) {
                    $.alertMessage.info("保存成功!");
                    BS2102120.refresh();
                    $.pdialog.close("edu");
                } else {
                    $.alertMessage.info("保存失败!");
                }
            }, {refreshHtml: false, dataType: "json", contentType: 'application/json;charset=utf-8'});
        });
    }
}

$(document).ready(function () {
    // 初始化调用
    BS2102117.init();
});
