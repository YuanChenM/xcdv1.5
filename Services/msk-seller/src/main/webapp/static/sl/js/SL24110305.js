/**
 *JS
 *@author pxg
 */
var SL24110305 = {
	initDataGrid : function(){
		this.loadImages();
	},
	loadImages:function(){
		var systemSay=$("a[name='systemSay']");
		MainUtils.loadImageManys(systemSay);
	}
}
$(document).ready(function() {
	//初始化调用
	SL24110305.initDataGrid();
});