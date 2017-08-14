/**
 *JS
 *@author pxg
 */
var SL24110303 = {
	initDataGrid : function(){
		this.loadImages();
	},
	loadImages:function(){
		var honorName=$("a[name='honorName']");
		MainUtils.loadImageManys(honorName);
		var workHouseName=$("a[name='workHouseName']");
		MainUtils.loadImageManys(workHouseName);
	}
}
$(document).ready(function() {
	//初始化调用
	SL24110303.initDataGrid();
});