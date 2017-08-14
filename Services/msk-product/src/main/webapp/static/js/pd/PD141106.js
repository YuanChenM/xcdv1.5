/**
 *实际质量值录入JS
 *@author jiang_nan 
 */
var PD141106 = {
		searchButtonId:"PD141106_Search",
		searchForm : "PD141106SearchForm",
		saveButtonId:"PD141106_SAVE",
		saveForm : "PD141106SaveForm",
		init : function(){
			$('.tree').treegrid(); 
			this.bindSearchButton();
			this.bindSaveButton();
		},
		bindSearchButton : function(){
			$("#"+PD141106.searchButtonId).click(function(){
				formData = getFormData($("#"+PD141106.searchForm));
				$('#main-content').postUrl(Main.contextPath+"/realitymeasure/check",formData,function(data){
					var flag = data;
					if(flag=="true"){
						$.alertMessage.info("当前品种没有标准档案卡信息,请先创建档案卡信息");
					}else{
						$('#main-content').postUrl($("#"+PD141106.searchForm).attr("action"), formData);
					}
				},{refreshHtml:false});
				
				
			});
		},
		bindSaveButton : function(){
			$("#"+PD141106.saveButtonId).click(function(){
				var validator = mainValidation($("#"+PD141106.saveForm));
				var isValid = validator.form();
				if(isValid){
					$.alertMessage.confirm("你确定要保存当前数据吗？", function() {
						$.alertMessage.close();
						formData = getFormData($("#"+PD141106.saveForm));
						$('#main-content').postUrl($("#"+PD141106.saveForm).attr("action"), formData);
					});
				}
			});
		}
}
$(document).ready(function() {
	//初始化调用
	PD141106.init();
});


