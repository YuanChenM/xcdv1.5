/**
 *JS
 *@author puxigui
 */
var $List_Grid;
var SL24110309 = {
	isLoadData : false,
	initSL24110309Grid : function(){
		if(!this.isLoadData){
			this.initDataGrid();
		}
		this.isLoadData = true;
	},
	initDataGrid : function(){
		$List_Grid = $('#SL24110309_grid').grid({
			actionHandler:SL24110309.actionHandler,
			resultFooter:false
		});
	},

	actionHandler:function(rowdata,coltype,row,col){
		/** 操作按钮 */
		if(col==5){
			/*var url = Main.contextPath + "/images/images03/5.png";*/
			var url = "http://121.196.237.87/msk-image/sl/89082/ecteam01.png";
			$.alertMessage.info("暂无图片");
			/*$.alertMessage.info("<img src='"+url+"'>");*/
		}
		if(col==6){
			/*$.pdialog.open("卖家列表", url, {resizable:false, width: 750, height: 380},{epId:rowdata.producerEpId,slCode:rowdata.slCode});*/
			var epId=rowdata.producerEpId;
			var url = Main.contextPath + "/SL241103/init/"+epId+"/"+rowdata.slCode+"/";
			$('#main-content').postUrl(url,{flg:1});
		}
	},
	showImage:function(tr,data){
		//检索后，为每个单元格设定css
		$(tr).find('td:eq(3)').find('input:eq(4)').attr('coltype','edit-red');
	}

}

$(document).ready(function() {
	//初始化调用
	//$.core.sleep(5000)
	//SL24110309.initDataGrid();
	$( "#accordionSL24110309" ).accordion({ heightStyle: "content" });
});