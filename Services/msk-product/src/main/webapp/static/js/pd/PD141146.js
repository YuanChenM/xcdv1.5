/**
 * 产品基础数据列表JS
 *
 * @author xhy
 */
var PD141146 = {
	saveButtonId: "PD141146_SAVE",
	formId: "PD141146Form",
	init: function () {
		this.bindSaveButton();
	},
	bindSaveButton: function () {
		$("#" + PD141146.saveButtonId).click(function () {
			var validator = mainValidation($("#" + PD141146.formId));
			var isValid = validator.form();
			if (isValid) {
				$.alertMessage.confirm("你确定要保存当前数据吗？", function () {
					$.alertMessage.close();
					formData = getFormData($("#" + PD141146.formId));
					$('#main-content').postUrl($("#" + PD141146.formId).attr("action"), formData, function () {
						$.alertMessage.info("数据保存成功!");
					},{refreshHtml:false});
				});
			}
		});
	},
}
$(document).ready(function() {
	// 初始化调用
	PD141146.init();
});
