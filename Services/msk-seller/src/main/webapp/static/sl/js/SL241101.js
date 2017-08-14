/**
 * 卖家审批审核列表JS
 * 
 * @author gyh
 */
var $List_Grid;
var SL241101 = {
	newButtonId : "SL241101_NEW",
	formId:"sl241101FormId",
	init : function() {
		$List_Grid = $('#sl241101_list_grid').grid({
			actionHandler:SL241101.actionHandler,
			linkHandler:SL241101.linkHandler
		});
		this.bindSearchButton();
	},
	// 绑定按钮
	bindSearchButton : function() {
		$("#" + SL241101.newButtonId).click(function() {
			SL241101.newData();
		});
	},
	newData : function() {
		formData = getFormData($("#" +SL241101.formId));
		$('#main-content').postUrl(Main.contextPath + "/SL241103000/init/",{slCode:formData.slCode,epId:formData.epId});
	},
	actionHandler:function(rowdata,coltype,row,col){

		/** 操作按钮 */
		//if(col==9){
		//	$('#main-content').postUrl(Main.contextPath + "/SL241103/init/"+rowdata.epId+"/"+rowdata.slCode+"/");
		//}
		if(col==10){
			var data = new Object();
			data['slCode'] = rowdata.slCode;
			data['slShowName'] = rowdata.slShowName;
			Main.detailWindow(Main.contextPath + "/SL241105/init/check/1", data, "卖家加工质量标准");
			//$('#main-content').postUrl(Main.contextPath + "/SL241105/init/check/1",{slCode:rowdata.slCode,slShowName:rowdata.slShowName});
		}
		if(col==9){
			var data = new Object();
			data['slCode'] = rowdata.slCode;
			data['slShowName'] = rowdata.slShowName;
			Main.detailWindow(Main.contextPath + "/SL241105/init/check/2", data, "卖家加工技术标准");
			//$('#main-content').postUrl(Main.contextPath + "/SL241105/init/check/2",{slCode:rowdata.slCode,slShowName:rowdata.slShowName});
		}
		if(col==11){
			var data = new Object();
			Main.detailWindow(Main.contextPath + "/SL241127/init/"+rowdata.slCode, data, "产品信息及状态审核");
			//$('#main-content').postUrl(Main.contextPath + "/SL241127/init/"+rowdata.slCode);
		}
	},
	linkHandler: function (rowdata, coltype, row, col) {
		//var data = new Object();
		//Main.detailWindow(Main.contextPath + "/SL241103/init/"+rowdata.epId+"/"+rowdata.slCode, data, "卖家企业信息详情");
		$('#main-content').postUrl(Main.contextPath + "/SL241103/init/"+rowdata.epId+"/"+rowdata.slCode+"/",{
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	SL241101.init();
});
