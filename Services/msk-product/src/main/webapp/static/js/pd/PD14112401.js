/**
 * 产品品种维护JS
 * 
 * @author gyh
 */
var PD14112401 = {
	formId : "PD14112401Form",
	saveButtonId : "PD14112401_SAVE",
	backButtonId : "PD14112401_BACK",
	init : function() {
		this.bindSavebutton();
	},
	bindSavebutton : function() {
		$("#" + PD14112401.saveButtonId).click(function() {
			PD14112401.saveData();
		});
		$("#" + PD14112401.backButtonId).click(function() {
			$.pdialog.close();
		});
	},
	saveData : function() {
		var validator = mainValidation($("#" + PD14112401.formId));
		var isValid = validator.form();

		var isNull = $("#classestreeName2").val();
		if(/^\s*$/.test(isNull)){
			$.alertMessage.info("输入框值不能为空!");
			return false;
		}
		if(!(/^[a-zA-z0-9\u4E00-\u9FA5]*$/.test($.trim(isNull)))){
			$.alertMessage.info("输入框中只能输入字符,数字,字母!");
			return false;
		}
		if (isValid) {
			$.alertMessage.confirm("你确定要保存当前数据吗？", function() {
				$.alertMessage.close();
				formData = getFormData($("#" + PD14112401.formId));
				$('#PD14112406Id').postUrl(
						$("#" + PD14112401.formId).attr("action"),
						formData,
						function(data) {
							/*$('#main-content').postUrl(
									Main.contextPath + "/PD141124/init/",{classesCode:formData.classestreeCode1});*/

							/** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
							$.pdialog.close();
							if(data=='1'){
								//PD14112406.initParam();
								PD14112406.initJsp();
								$.alertMessage.info("数据操作成功!");
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
	PD14112401.init();
});