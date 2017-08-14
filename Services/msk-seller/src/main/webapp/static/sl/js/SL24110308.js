/**
 *JS
 *@author puxigui
 */
var SL24110308 = {
    initDataGrid: function () {
        this.loadImages();
    },
    loadImages:function(){
        var brandBook=$("a[name='brandBook']");
        MainUtils.loadImageManys(brandBook);
        var normsPhoto=$("a[name='normsPhoto']");
        MainUtils.loadImageManys(normsPhoto);
        var brandHonor=$("a[name='brandHonor']");
        MainUtils.loadImageManys(brandHonor);
    }
}
$(document).ready(function () {
    //初始化调用
    SL24110308.initDataGrid();
});