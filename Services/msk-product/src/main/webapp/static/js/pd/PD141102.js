/**
 * 产品类别维护JS
 * 
 * @author gyh
 */
var PD141102 = {
	formId : "PD141102Form",
	saveButtonId : "PD141102_SAVE",
	backButtonId : "PD141102_BACK",
	init : function() {
		this.bindSavebutton();
	},
	bindSavebutton : function() {
		$("#" + PD141102.saveButtonId).click(function() {
			PD141102.saveData();
		});
		$("#" + PD141102.backButtonId).click(function() {
			$('#main-content').postUrl(Main.contextPath + "/PD141101/init/");
			$.pdialog.close();
		});
	},
	saveData : function() {
		var validator = mainValidation($("#" + PD141102.formId));
		var isValid = validator.form();
		if (isValid) {
			$.alertMessage.confirm("你确定要保存当前数据吗？", function() {
				$.alertMessage.close();
				formData = getFormData($("#" + PD141102.formId));
				$('#main-content').postUrl(
						$("#" + PD141102.formId).attr("action"),
						formData,
						function() {
							$('#main-content').postUrl(
									Main.contextPath + "/PD141101/init/");
							$.pdialog.close();
						});
			});
		}
	}
};
$(document).ready(function() {
	// 初始化调用
	PD141102.init();
});