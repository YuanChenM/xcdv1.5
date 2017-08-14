/**
 *JS
 *@author puxigui
 */
var SL24110307 = {
    initDataGrid: function () {
        this.loadImages();
    },
    loadImages:function(){
        var teamMainName=$("a[name='teamMainName']");
        MainUtils.loadImageManys(teamMainName);
        var teamOtherName=$("a[name='teamOtherName']");
        MainUtils.loadImageManys(teamOtherName);
    }
}
$(document).ready(function () {
    //初始化调用
    SL24110307.initDataGrid();
});