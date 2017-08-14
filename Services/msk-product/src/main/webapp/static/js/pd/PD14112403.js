/**
 * 产品品种维护JS
 * 
 * @author xhy
 */
var PD14112403 = {
	formId : "PD14112403Form",
	saveButtonId : "PD14112403_SAVE",
	backButtonId : "PD14112403_BACK",
	init : function() {
		this.bindSavebutton();
	},
	bindSavebutton : function() {
		$("#" + PD14112403.saveButtonId).click(function() {
			PD14112403.saveData();
		});
		$("#" + PD14112403.backButtonId).click(function() {
			$.pdialog.close();
		});
	},
	saveData : function() {
		var validator = mainValidation($("#" + PD14112403.formId));
		var isValid = validator.form();

		var isNull = $("#classestreeName4").val();
		if(/^\s*$/.test(isNull)){
			$.alertMessage.info("输入框值不能为空!");
			return false;
		}
		/*if(!(/^[a-zA-z0-9\u4E00-\u9FA5]*$/.test($.trim(isNull)))){
			$.alertMessage.info("输入框中只能输入字符,数字,字母!");
			return false;
		}*/
		if (isValid) {
			$.alertMessage.confirm("你确定要保存当前数据吗？", function() {
				$.alertMessage.close();
				formData = getFormData($("#" + PD14112403.formId));
				$('#PD14112406Id').postUrl(
						$("#" + PD14112403.formId).attr("action"),
						formData,
						function(data) {
							/** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
							if(data=='1'){
								PD14112406.initJsp();
								$.alertMessage.info("数据操作成功!");
								/** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
								$.pdialog.close();
							}else{
								$.alertMessage.info("数据异常,请修改后添加!");
							}
							/** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  End */
						});
			});
		}
	}
};
$(document).ready(function() {
	// 初始化调用
	PD14112403.init();
});