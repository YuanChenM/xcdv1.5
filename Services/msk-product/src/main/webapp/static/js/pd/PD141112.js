/**
 *产品类别维护和产品品种列表JS
 *@author gyh 
 */
var PD141112 = {
	formId : "PD141112Form",
	saveButtonId : "PD141112_SAVE",
	closeButtonId: "PD141112_Back",
	init : function() {
		this.bindSavebutton();
		this.bindOnBlur();
	},
	/*绑定离焦点校验时间*/
	bindOnBlur : function() {
		$("input[name='normsLength'],input[name='normsHeight'],input[name='normsVolume'],input[name='normsWidth']").blur(function() {
			var value = this.value;
			if(/[^0-9.]/g.test(value)){
				$.alertMessage.info('只能输入数值');
				this.value = "";
			}
		});
	},
	bindSavebutton : function() {
		$("#" + PD141112.saveButtonId).click(function() {
			PD141112.saveData();
		});
		$("#" + PD141112.closeButtonId).click(function() {
			$.pdialog.close();
		});
	},
	saveData : function() {
		var validator = mainValidation($("#" + PD141112.formId));
		var isValid = validator.form();
		if (isValid) {
			$.alertMessage.confirm("你确定要保存当前数据吗？", function() {
				$.alertMessage.close();
				formData = getFormData($("#" +PD141112.formId));
				$('#main-content').postUrl(
						$("#" + PD141112.formId).attr("action"), formData,function() {
							$.pdialog.close();
							$('#main-content').postUrl(Main.contextPath + "/PD141111/init",{standardId:formData.standardId});
						});
			});
		}
	}
}
$(document).ready(function() {
	// 初始化调用
	PD141112.init();
});