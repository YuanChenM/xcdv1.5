/**
 * 产品基础数据列表JS
 *
 * @author pxg
 */
var PD141130 = {
	orderTimeStart:"#orderTimeStart",
	orderTimeEnd:"#orderTimeEnd",
	PD141130Grid: null,
	init: function () {
		PD141130.PD141130Grid = $('#pd141130_grid').grid({
			actionHandler:PD141130.actionHandler
		});
		this.bindDatePicber(PD141130.orderTimeStart);
		this.bindDatePicber(PD141130.orderTimeEnd);
	},
	actionHandler:function(rowdata,coltype,row,col){
		if(coltype=="detail"){
			$('#main-content').postUrl( Main.contextPath + '/PD141131/init',{tcBuyinvestListId:rowdata.tcBuyinvestListId});
		}
	},
	bindDatePicber: function(orderTimeId){
		$(orderTimeId).datepicker({
			showButtonPanel: true,
			dateFormat:'yy-mm-dd',
			changeMonth: true,
			changeYear: true,
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	PD141130.init();
});
