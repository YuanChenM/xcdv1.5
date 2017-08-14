/**
 *技术标准设置JS
 *@author yuan_chen 
 */
var PD141107={
		saveButtonId:"PD141107_SAVE",
	    returnButtonId: "PD141107_RETURN",
		formId:"PD141107Form",
		init : function(){
			$('.tree').treegrid(); 
			this.bindSaveButton();
			this.bindRuturnButton();
			this.checkButton();
			this.allSelectClick();
		},
	//checkBox选中事件
	checkButton: function () {
		$("input[name='checkArray']").click(function () {
			var index = $(this).attr("isCheck");
			var input1 = $("#input1_" + index);
			var input2 = $("#input2_" + index);
			var input3 = $("#input3_" + index);
			if (this.checked) {
				input1.removeAttr("readonly");
				input2.removeAttr("readonly");
				input3.removeAttr("readonly");
			} else {
				input1.attr("readonly","readonly");
				input2.attr("readonly","readonly");
				input3.attr("readonly","readonly");
			}
		});
	},
	//全选
	allSelectClick: function () {
		$("#allCheck").click(function () {
			if(this.checked){
				$("input[name='checkArray']").each(function() {
					this.checked = true;
					var index = $(this).attr("isCheck");
					var input1 = $("#input1_" + index);
					var input2 = $("#input2_" + index);
					var input3 = $("#input3_" + index);
						input1.removeAttr("readonly");
						input2.removeAttr("readonly");
						input3.removeAttr("readonly");
				});
			}else{
				$("input[name='checkArray']").each(function () {
					this.checked = false;
					var index = $(this).attr("isCheck");
					var input1 = $("#input1_" + index);
					var input2 = $("#input2_" + index);
					var input3 = $("#input3_" + index);
						input1.attr("readonly","readonly");
						input2.attr("readonly","readonly");
						input3.attr("readonly","readonly");
				});
			}
		});
	},
		bindSaveButton:function(){
			$("#"+PD141107.saveButtonId).click(function(){
				var validator = mainValidation($("#"+PD141107.formId));
				var isValid = validator.form();
				if(isValid){
					$.alertMessage.confirm("你确定要保存当前数据吗？", function() {
						$.alertMessage.close();
						formData = getFormData($("#"+PD141107.formId));
						var $checkArray={};
						$("input[name='checkArray']").each(function(index){
							if(this.checked){
								$(this).val(0);
								$checkArray[index]="1";
							}else{
								$(this).attr("checked","checked");
								$(this).val(1);
								$checkArray[index]="0";
							}
						});
						formData.checkArray=$checkArray;
						$('#main-content').postUrl($("#"+PD141107.formId).attr("action"), formData);
					});
				}
			});
		},
	bindRuturnButton: function () {
		$("#" + PD141107.returnButtonId).click(function () {
			$('#main-content').postUrl(Main.contextPath + "/PD141113/init",{"filterMap['breedCode']":BREED_CODE,"filterMap['classesCode']":CLASSES_CODE,"filterMap['featureCode']":FEATURE_CODE});
		});
	}
}
$(document).ready(function() {
	//初始化调用
	PD141107.init();
	
});
