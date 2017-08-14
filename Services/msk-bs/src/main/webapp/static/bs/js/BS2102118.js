/**
 * 新增培训经历
 *
 * @author cx
 */

var BS2102118 = {
    addTrainButtonId: "BS2102118_SAVE",
    init: function () {
        this.bindDatePicber(".dataTime");
        this.addTrainExperience();
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
    addTrainExperience: function () {

        $("#" + BS2102118.addTrainButtonId).click(function () {
            var slCode = $("#slCode").val();
            var houseCode = $("#houseCode").val();
            //获取培训经历数据
            var trainArray = new Array();
            var train = {};
            var trainStart = $("#trainStart").val();
            var trainEnd = $("#trainEnd").val();
            var trainComp = $("#trainComp").val();
            var trainCertificate = $("#trainCertificate").val();
            var trainId = $("#trainId").val();
            if(trainComp.length > 50){
                $.alertMessage.info("培训机构不能多于50个字");
                return false;
            }
            if(trainCertificate.length > 50){
                $.alertMessage.info("所获证书不能多于50个字");
                return false;
            }
            if (trainStart || trainEnd || trainComp || trainCertificate) {
                train["trainId"] = trainId;
                train["trainStart"] = trainStart;
                train["trainEnd"] = trainEnd;
                train["trainComp"] = trainComp;
                train["trainCertificate"] = trainCertificate;
                train["slCode"] = slCode;
                train["houseCode"] = houseCode;
                trainArray[0] = train;
            }else {
                $.alertMessage.info("请填写内容");
                return false;
            }

            var houseAccount = {
                slCode: slCode,
                houseCode: houseCode
            };
            var param = {
                houseTrainList: trainArray,
                houseAccount: houseAccount
            };
            //save
            $('#main-content').postUrl(Main.contextPath + "/BS2102113/save", JSON.stringify(param), function (data) {
                if (data > 0) {
                    $.alertMessage.info("保存成功!");
                    BS2102121.refresh();
                    $.pdialog.close("train");
                } else {
                    $.alertMessage.info("保存失败!");
                }
            }, {refreshHtml: false, dataType: "json", contentType: 'application/json;charset=utf-8'});
        });


    }


}

$(document).ready(function () {
    // 初始化调用
    BS2102118.init();
});
