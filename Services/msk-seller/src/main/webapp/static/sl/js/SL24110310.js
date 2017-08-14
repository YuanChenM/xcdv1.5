/**
 * JS
 *
 * @author hyx
 */
var $List_Grid;
var SL24110310 = {
	isLoadData : false,
	initSL24110310Grid : function(){
		if(!this.isLoadData){
			this.initDataGrid();
		}
		this.isLoadData = true;
	},
	initDataGrid : function() {
		$List_Grid = $('#SL24110310_grid').grid({
			actionHandler : SL24110310.actionHandler
		});
	},
	actionHandler : function(rowdata, coltype, row, col) {
		/** 操作按钮 */
		if (col == 5) {
			var url = "http://121.196.237.87/msk-image/sl/89082/ecteam01.png";
			$.alertMessage.info("暂无图片");
			/*$.alertMessage.info("<img src='"+url+"' height='590' width='550'>");*/
		}
		if (col == 6) {
			var epId=rowdata.producerEpId;
			var url = Main.contextPath + "/SL241103/init/"+epId+"/"+rowdata.slCode+"/";
			$('#main-content').postUrl(url,{flg:1});
			/*$.pdialog.open("卖家列表", url, {
				resizable : false,
				width : 1000,
				height : 600
			}, {
				epId : rowdata.producerEpId,
				slCode : rowdata.slCode
			});*/
		}
	}
}

$(document).ready(function() {
	// 初始化调用
	//SL24110310.initDataGrid();
	// $( "#accordionSL24110310" ).accordion({ heightStyle: "content" });
});