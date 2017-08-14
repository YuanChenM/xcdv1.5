/**
 * 标准档案卡列表JS
 * 
 * @author jiang_nan
 */
var PD141113 = {
	PD141113Grid : null,
	returnButtonId: "PD141113_RETURN",
	newButtonId:"PD141113_NEW",
	initDataGrid : function() {
		PD141113.PD141113Grid = $('#PD141113_Grid').grid({
			actionHandler : PD141113.actionHandler
		});
		this.bindRuturnButton();
	},
	
	actionHandler : function(rowdata, coltype, row, col) {
		/** 包裝 */
		if (coltype == "detail") {
			$('#main-content').postUrl(Main.contextPath + "/PD141111/init/", {
				standardId : rowdata.pdStdId,
				breedCode : BREED_CODE,
				classesCode : CLASSES_CODE,
				featureCode :　FEATURE_CODE,
				yesOrNo : INFO
			});
		}
		/** 质量标准 */
		if (coltype == "check") {
			$('#main-content').postUrl(Main.contextPath + "/PD141105/init/", {
				classesName : rowdata.classesName,
				breedName : rowdata.breedName,
				standardId : rowdata.pdStdId,
				breedCode : BREED_CODE,
				classesCode : CLASSES_CODE,
			　　featureCode :　FEATURE_CODE,
				yesOrNo : INFO
			});
		}
		/** 技术标准 */
		if (coltype == "edit") {
			$('#main-content').postUrl(Main.contextPath + "/PD141107/init", {
				classesCode : CLASSES_CODE,
				classesName : rowdata.classesName,
				breedCode : BREED_CODE,
				breedName : rowdata.breedName,
				pdStdId : rowdata.pdStdId,
				featureCode :　FEATURE_CODE,
				yesOrNo : INFO
			});
		}
	},
	bindSaveButton : function(){
		$("#"+PD141113.newButtonId).click(function(){
			$.alertMessage.info("本功能尚未提供!")
		});
	},
	bindRuturnButton: function () {
		$("#" + PD141113.returnButtonId).click(function () {
			$('#main-content').postUrl(Main.contextPath + "/PD141101/init",{classesCode:CLASSES_CODE,breedCode:BREED_CODE,featureCode:FEATURE_CODE});
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	PD141113.initDataGrid();
	PD141113.bindSaveButton(); 
});