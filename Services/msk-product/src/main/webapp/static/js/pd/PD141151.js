/**
 * 产品基础数据列表JS
 *
 * @author xhy
 */
var PD141151 = {
	saveButtonId: "PD141151_SAVE",
	formId: "PD141151Form",
	init: function () {
		$('.tree').treegrid();
		this.bindSaveButton();
	},
	bindSaveButton: function () {
		$("#" + PD141151.saveButtonId).click(function () {
			var validator = mainValidation($("#" + PD141151.formId));
			var isValid = validator.form();
			if (isValid) {
				$.alertMessage.confirm("你确定要保存当前数据吗？", function () {
					$.alertMessage.close();
					formData = getFormData($("#" + PD141151.formId));
					$('#main-content').postUrl($("#" + PD141151.formId).attr("action"), formData, function () {
						$.alertMessage.info("数据保存成功!");
					},{refreshHtml:false});
				});
			}
		});
	},
}
$(document).ready(function() {
	// 初始化调用
	PD141151.init();
});
