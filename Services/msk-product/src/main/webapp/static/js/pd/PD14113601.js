/**
 * 产品基础数据列表JS
 *
 * @author xhy
 */
var PD14113601 = {
	search: "PD14113601_search",
	PD141136Grid: null,
	init: function () {
		PD14113601.PD141130Grid = $('#pd14113601_grid').grid({
			actionHandler: PD14113601.actionHandler
		});
	},
	actionHandler: function (rowdata, coltype, row, col) {
		/** 产品包装 */
		if (coltype == "detail") {
				$('#main-content').postUrl( Main.contextPath + '/PD141137/init',rowdata);
		}
	},
}
$(document).ready(function(){
	// 初始化调用
	PD14113601.init();
});
