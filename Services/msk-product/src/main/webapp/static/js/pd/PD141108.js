/**
 *实际技术值录入JS
 *@author yuan_chen 
 */
var PD141108 = {
		searchButtonId:"PD141108_Search",
		searchForm : "PD141108SearchForm",
		saveButtonId:"PD141108_SAVE",
		saveForm : "PD141108SaveForm",
		init : function(){
			$('.tree').treegrid(); 
			this.bindSearchButton();
			this.bindSaveButton();
		},
		bindSearchButton : function(){
			$("#"+PD141108.searchButtonId).click(function(){
				formData = getFormData($("#"+PD141108.searchForm));
				$('#main-content').postUrl($("#"+PD141108.searchForm).attr("action"), formData);
			});
		},
		bindSaveButton : function(){
			$("#"+PD141108.saveButtonId).click(function(){
				var validator = mainValidation($("#"+PD141108.saveForm));
				var isValid = validator.form();
				if(isValid){
					$.alertMessage.confirm("你确定要保存当前数据吗？", function() {
						$.alertMessage.close();
						formData = getFormData($("#"+PD141108.saveForm));
						$('#main-content').postUrl($("#"+PD141108.saveForm).attr("action"), formData);
					});
				}
			});
		}
}
$(document).ready(function() {
	//初始化调用
	PD141108.init();
});


