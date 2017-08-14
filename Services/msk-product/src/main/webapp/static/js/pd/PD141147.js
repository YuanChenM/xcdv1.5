/**
 * 产品原种饲养标准指标JS
 *
 * @author zhou_ling
 */
var PD141147 = {
	saveButtonId: "PD141147_SAVE",
	formId: "PD141147Form",
	init: function () {
		this.bindSaveButton();
	},
	bindSaveButton: function () {
		$("#" + PD141147.saveButtonId).click(function () {
			var validator = mainValidation($("#" + PD141147.formId));
			var isValid = validator.form();
			if (isValid) {
				$.alertMessage.confirm("你确定要保存当前数据吗？", function () {
					$.alertMessage.close();
					formData = getFormData($("#" + PD141147.formId));
					$('#main-content').postUrl($("#" + PD141147.formId).attr("action"), formData, function () {
						$.alertMessage.info("数据保存成功!");
					},{refreshHtml:false});
				});
			}
		});
	},
}
$(document).ready(function() {
	// 初始化调用
	PD141147.init();
});
