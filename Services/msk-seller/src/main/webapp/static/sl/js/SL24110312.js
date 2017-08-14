/**
 *JS
 *@author puxigui
 */
var SL24110312 = {
    initDataGrid: function () {
        this.loadImages();
    },
    loadImages:function(){
        var testName=$("a[name='testName']");
        MainUtils.loadImageManys(testName);
    }
}
$(document).ready(function () {
    //初始化调用
    SL24110312.initDataGrid();
});