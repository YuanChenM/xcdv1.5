/**
 *质量标准设置JS
 *@author jiang_nan
 */
var SL241118 = {
	saveButtonId: "SL241118_SAVE",
	formId: "SL241118Form",
	init: function () {
		$('.tree').treegrid();
		this.bindSaveButton();
		this.checkButton();
	},
	//checkBox选中事件
	checkButton: function () {
		$("input[name='checkArray']").click(function () {
			var index = $(this).attr("isCheck");
			var input1 = $("#input_" + index);
			var $tr=$('#tr_1').children('td').eq(4);
			if (this.checked) {
				input1.removeAttr("readonly");
				//$($("input[type='radio']")[0]).removeAttr("checked");
				//$($("input[type='radio']")[1]).removeAttr("checked");
				$tr.html("论证中");
			} else {
				input1.attr("readonly", "readonly");
				input1.val(input1[0].defaultValue);
				$tr.html("");
			}
		});
	},
	bindSaveButton: function () {
		$("#" + SL241118.saveButtonId).click(function () {
			var validator = mainValidation($("#" + SL241118.formId));
			var isValid = validator.form();
			if (isValid) {
				$.alertMessage.confirm("你确定要保存当前数据吗？", function () {
					$.alertMessage.close();
					formData = getFormData($("#" + SL241118.formId));
					if(formData.agreeFlg==null){
						$.alertMessage.info("请选择等级!");
						return;
					}
					var $check ={};
					$("input[name='checkArray']").each(function (index) {
						if (this.checked) {
							$(this).val(0);
							$check[index]="0";
						} else {
							$(this).val(1);
							$check[index]="1";
						}
					});
					formData.checkArray=$check;
					formData.standardId=$('#standardId').val();
					$('#main-content').postUrl($("#" + SL241118.formId).attr("action"), formData, function () {
						$('#main-content').postUrl(Main.contextPath + "/SL241116/init/"+formData.slCode,{enterMark:"init"},Main.hiddenHeader);
						//$('#main-content').postUrl(Main.contextPath + "/SL241116/init/"+formData.slCode);
					},{refreshHtml:false});
					$("#headBreadCrumb").hide();
				});
			}
		});
	}
}
$(document).ready(function () {
	//初始化调用
	SL241118.init();
});
