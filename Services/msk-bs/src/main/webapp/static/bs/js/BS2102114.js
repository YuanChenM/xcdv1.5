/**
 * 买手店列表JS
 *
 * @author ren_qiang
 */
var BS2102114 = {
    formId: "BS2102114Form",
    saveDataButtonId: "BS2102114_SAVE",
    init: function () {
        this.saveData();
    },

    stopFormSubmit: function () {
        $("#" + BS2102114.formId).submit(function (e) {
            e.stopPropagation();
            return false;
        })
    },

    saveData: function () {
        $("#" + BS2102114.saveDataButtonId).click(function () {
            var slCode = $("#slCode").val();
            var houseCode =$("#houseCode").val();
            var houseStar = $("#houseStar").val();
            var validYearMonth = $("#validYearMonth").val();
            if (!houseStar) {
                $.alertMessage.info("星级不能为空");
                return false;
            }
            if(!/^[0-9]\d?(\.\d)?$/.test(houseStar)){
                $.alertMessage.info("星级只能输入0-100保留1位小数的数字");
                return false;
            }

            var param = {
                slCode :  slCode,
                houseCode : houseCode,
                houseStar : houseStar,
                validYearMonth : validYearMonth
            }
            $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl($("#" + BS2102114.formId).attr("action"), param, function (data) {
                    $.alertMessage.info("修改已提交");
                    if (data > 0) {
                        $.alertMessage.info("保存成功!");
                    }else {
                        $.alertMessage.info("保存失败!");
                    }
                }, {refreshHtml: false});
            })
        });

    }

};

$(document).ready(function () {
    // 初始化调用
    BS2102114.init();
});
