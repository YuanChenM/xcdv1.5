/**
 *标准档案卡列表JS
 *@author jiang_nan 
 */
var PD141115 = {
		PD141115Grid : null,
		initDataGrid : function(){
			PD141115.PD141115Grid = $('#PD141115_Grid').grid({
				actionHandler:PD141115.actionHandler
			});
		},
		actionHandler:function(rowdata,coltype,row,col){
			/** 质量标准 */
			if(coltype=="check"){
				$('#main-content').postUrl(Main.contextPath + "/PD141106/init/" + rowdata.pdRltMsrId);
			}
			/**技术标准*/
			if(coltype=="edit"){
				$('#main-content').postUrl(Main.contextPath + "/PD141108/init/" + rowdata.pdRltMsrId);
			}
		},
}
$(document).ready(function() {
	//初始化调用
	PD141115.initDataGrid();
});