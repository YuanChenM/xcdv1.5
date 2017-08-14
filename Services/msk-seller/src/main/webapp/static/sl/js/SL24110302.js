/**
 *JS
 *@author puxigui 
 */
var SL24110302 = {
	initDataGrid : function(){
		this.loadImages();
	},
	loadImages:function(){
		var imgSrc=$("a[name='imgUrlName']");
		MainUtils.loadImageManys(imgSrc);
	}
}
$(document).ready(function() {
	//初始化调用
	SL24110302.initDataGrid();
});