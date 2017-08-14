/**
 * 产品特征维护JS
 * 
 * @author gyh
 */
var PD141104 = {
	formId : "PD141104Form",
	saveButtonId : "PD141104_SAVE",
	backButtonId : "PD141104_BACK",
	init : function() {
		this.bindSavebutton();
	},
	bindSavebutton : function() {
		$("#" + PD141104.saveButtonId).click(function() {
			PD141104.saveData();
		});
		$("#" + PD141104.backButtonId).click(function() {
			$('#main-content').postUrl(Main.contextPath + "/PD141101/init/",{breedCode:BREED_CODE,classesCode:CLASSES_CODE});
			$.pdialog.close();
		});
	},
	saveData : function() {
		var validator = mainValidation($("#" + PD141104.formId));
		var isValid = validator.form();
		var isNull = $("#featureName").val();

		if(/^\s*$/.test(isNull)){
			$.alertMessage.info("输入框值不能为空!");
			return false;
		}
		if (isValid) {
			$.alertMessage.confirm("你确定要保存当前数据吗？", function() {
				$.alertMessage.close();
				formData = getFormData($("#" + PD141104.formId));
				$('#main-content').postUrl(
						$("#" + PD141104.formId).attr("action"),
						formData,
						function() {
							$('#main-content').postUrl(
									Main.contextPath + "/PD141101/init/",{breedCode:BREED_CODE,classesCode:CLASSES_CODE});
							$.pdialog.close();
						});
			});
		}
	}
};
$(document).ready(function() {
	// 初始化调用
	PD141104.init();
});