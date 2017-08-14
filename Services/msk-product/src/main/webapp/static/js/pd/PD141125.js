/**
 *产品类别维护和产品品种列表JS
 *@author gyh
 */
var PD141125 = {
	formId : "PD141125Form",
	saveButtonId : "PD141125_SAVE",
	closeButtonId: "PD141125_Back",
	init : function() {
		this.bindSavebutton();
	},
	/*/!*绑定离焦点校验时间*!/
	bindOnBlur : function() {
		$("input[name='normsLength'],input[name='normsHeight'],input[name='normsVolume'],input[name='normsWidth']").blur(function() {
			var value = this.value;
			if(/[^0-9.]/g.test(value)){
				$.alertMessage.info('只能输入数值');
				this.value = "";
			}
		});
	},*/
	bindSavebutton : function() {
		$("#" + PD141125.saveButtonId).click(function() {
			PD141125.saveData();
		});
		$("#" + PD141125.closeButtonId).click(function() {
			$.pdialog.close();
		});
	},
	saveData : function() {
		var validator = mainValidation($("#" + PD141125.formId));
		var isValid = validator.form();
		if (isValid) {
			$.alertMessage.confirm("你确定要保存当前数据吗？", function() {
				$.alertMessage.close();
				formData = getFormData($("#" +PD141125.formId));
				$('#main-content').postUrl(
					$("#" + PD141125.formId).attr("action"), formData,function() {
						$.alertMessage.info("数据保存成功!");
					},{refreshHtml:false});
			});
		}
	}
}
$(document).ready(function() {
	// 初始化调用
	PD141125.init();
});