/**
 * 产品基础数据列表JS
 *
 * @author xhy
 */
var PD142101 = {
    search: "PD142101_search",
    PD142101Grid: null,
    init: function () {
         $("#PD14210101Id").postUrl(Main.contextPath + "/PD14210101/init");
        $("a[name='classesCode']").bind("click", function() {
            $('#PD14210101Id').postUrl(Main.contextPath + "/PD14210101/init" ,{classesCode: $(this).attr("selectLogistics")});
        });
    }
};
$(document).ready(function () {
    // 初始化调用
    PD142101.init();
});
