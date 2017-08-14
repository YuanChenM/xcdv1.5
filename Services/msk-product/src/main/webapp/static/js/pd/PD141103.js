/**
 * 产品品种维护JS
 * 
 * @author gyh
 */
var PD141103 = {
	formId : "PD141103Form",
	saveButtonId : "PD141103_SAVE",
	backButtonId : "PD141103_BACK",

	init : function() {
		this.bindSavebutton();
		this.changeSelect();
	},
	bindSavebutton : function() {
		$("#" + PD141103.saveButtonId).click(function() {
			PD141103.saveData();
		});
		$("#" + PD141103.backButtonId).click(function() {
			$('#main-content').postUrl(Main.contextPath + "/PD141101/init/",{breedCode:BREED_CODE,classesCode:CLASSES_CODE});
			$.pdialog.close();
		});
	},
	saveData : function() {
		var validator = mainValidation($("#" + PD141103.formId));
		var isValid = validator.form();
		var isNull = $("#breedName").val();
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
				formData = getFormData($("#" + PD141103.formId));
				$('#main-content').postUrl(
						$("#" + PD141103.formId).attr("action"),
						formData,
						function() {
							$('#main-content').postUrl(
									Main.contextPath + "/PD141101/init/",{breedCode:BREED_CODE,classesCode:CLASSES_CODE});
							$.pdialog.close();
						});
			});
		}
	},
	/*下拉框改变事件*/
	changeSelect : function(){
		var level2=$('#level2');
		var level3=$('#level3');
		var level4=$('#level4');
		var level5=$('#level5');
		level2.change(function(){
			var level2Val=level2.val();
			$('#main-content').postUrl(Main.contextPath + "/PD141103/findListTree",{'filterMap[classesCode]':level2Val},function(data){
				level3.html('');
				level4.html('');
				level5.html('');
				level3.append("<option value=''>请选择</option>");
				$.each(data, function(i, item) {
					level3.append("<option value='" + item.classestreeCode + "'>"+ item.levelName+ "</option>");
				});
			},{refreshHtml:false});
		});
		level3.change(function(){
			var level3Val=level3.val();
			$('#main-content').postUrl(Main.contextPath + "/PD141103/findListTree",{'filterMap[classesCode]':level3Val},function(data){
				level4.html('');
				level5.html('');
				level4.append("<option value=''>请选择</option>");
				$.each(data, function(i, item) {
					level4.append("<option value='" + item.classestreeCode + "'>"+ item.levelName+ "</option>");
				});
			},{refreshHtml:false});
		});
		level4.change(function(){
			var level4Val=level4.val();
			$('#main-content').postUrl(Main.contextPath + "/PD141103/findListTree",{'filterMap[classesCode]':level4Val},function(data){
				level5.html('');
				level5.append("<option value=''>请选择</option>");
				$.each(data, function(i, item) {
					level5.append("<option value='" + item.classestreeCode + "'>"+ item.levelName+ "</option>");
				});
			},{refreshHtml:false});

		});
	}

};
$(document).ready(function() {
	// 初始化调用
	PD141103.init();
});