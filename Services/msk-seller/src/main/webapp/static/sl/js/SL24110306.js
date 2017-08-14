/**
 *JS
 *@author pxg
 */
var SL24110306 = {
	initDataGrid : function(){
		this.loadImages();
	},
	loadImages:function(){
		var epTeam=$("a[name='epTeam']");
		MainUtils.loadImageManys(epTeam);
	}
}
$(document).ready(function() {
	//初始化调用
	SL24110306.initDataGrid();
});