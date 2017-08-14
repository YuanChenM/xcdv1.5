/**
 * 产品基础数据列表JS
 *
 * @author xhy
 */
var PD142102 = {
    search: "PD142102_search",
    PD142102Grid: null,
    init: function () {
         $("#PD14210201Id").postUrl(Main.contextPath + "/PD14210201/init");
        $("a[name='classesCode']").bind("click", function() {
            $('#PD14210201Id').postUrl(Main.contextPath + "/PD14210201/init" ,{classesCode: $(this).attr("selectLogistics")});
        });
    },
}
$(document).ready(function () {
    // 初始化调用
    PD142102.init();
});
