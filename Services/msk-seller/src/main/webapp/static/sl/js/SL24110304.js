/**
 *JS
 *@author pxg
 */
var SL24110304 = {
	initDataGrid : function(){
		this.loadImages();
	},
	loadImages:function(){
		var workshopName=$("a[name='workshopName']");
		MainUtils.loadImageManys(workshopName);
		var capacityName=$("a[name='capacityName']");
		MainUtils.loadImageManys(capacityName);
	}
}
$(document).ready(function() {
	//初始化调用
	SL24110304.initDataGrid();
});