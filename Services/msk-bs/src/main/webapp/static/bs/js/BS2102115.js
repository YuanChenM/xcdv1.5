/**
 * 买手店列表JS
 *
 * @author cx
 */

var BS2102115 = {
    baseInfo: "baseInfo",
    init: function () {
        this.bindToPage();
    },
    // 绑定按钮
    bindToPage: function () {

        $(".td_house_table").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/BS2102109/init");
        });

        $(".td_houseGroup_table").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/BS2102106/initExport");
        });
    }


}

$(document).ready(function () {
    // 初始化调用
    BS2102115.init();
});
