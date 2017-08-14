/**
 * Created by guan_zhongheng on 2016/10/8.
 */


var SL24111501 = {
    // 绑定按钮
    bindSearchButton: function () {
        $("#SL24111501_DOWNLOAD").click(function () {
            debugger;
            var str = "";
            $("input[name='checkbox']:checked").each(function () {
                if ($(this).prop("checked")) {
                    str += $(this).val() + ",";
                }
            })
            var param = new Object();
            var slCodeDis = $("#slCodeDis").val();
            var slCode = $("#slCode").val();
            var slShowName = $("#slShowName").val();
            param["slAccount"] = $("#slAccount").val();
            param["slCode"] = slCode;
            param["slChoosed"] = str;
            var dates = new Date();
            //var time = dates.getYear() + "" + (dates.getMonth() + 1) + "" + dates.getDay() + "" + dates.getHours()
            //    + "" + dates.getMinutes() + "" + dates.getSeconds();
            var time = dates.format("yyyyMMddhhmmssS");
            downloadAsync("slSellerTemplate", "sl24111501Logic", "msk-seller", slCodeDis + slShowName + time +".xlsx", param);
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SL24111501.bindSearchButton();
});

