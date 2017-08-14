/**
 *卖家产品技术标准档案卡审核JS
 *@author yuan_chen 
 */
var SL241108 = {
		Examine1ButtonId:"SL241108_Examine1",
		Examine2ButtonId:"SL241108_Examine2",
		Examine3ButtonId:"SL241108_Examine3",
		Examine4ButtonId:"SL241108_Examine4",
		ConfirmButtonId:"SL241108_Confirm",
		UnConfirmButtonId:"SL241108_UnConfirm",
		ReturnButtonId:"SL241108_Return",
		examineForm : "SL241108ExamineForm",
		init : function(){
			$('.tree').treegrid(); 
			this.bindExamine();
			this.bindConfirm();
		},
		bindExamine : function(){
			$("#"+SL241108.Examine1ButtonId).click(function(){
				$("#flag").value = "1";
				formData = getFormData($("#"+SL241108.examineForm));
				$('#main-content').postUrl(Main.contextPath + '/SL241108/examine', formData);
			});
			$("#"+SL241108.Examine2ButtonId).click(function(){
				$("#flag").value = "2";
				formData = getFormData($("#"+SL241108.examineForm));
				$('#main-content').postUrl(Main.contextPath + '/SL241108/examine', formData);
			});
			$("#"+SL241108.Examine3ButtonId).click(function(){
				$("#flag").value = "3";
				formData = getFormData($("#"+SL241108.examineForm));
				$('#main-content').postUrl(Main.contextPath + '/SL241108/examine', formData);
			});
			$("#"+SL241108.Examine4ButtonId).click(function(){
				$("#flag").value = "4";
				formData = getFormData($("#"+SL241108.examineForm));
				$('#main-content').postUrl(Main.contextPath + '/SL241108/examine', formData);
			});
		},
		bindConfirm : function(){
			$("#"+SL241108.ConfirmButtonId).click(function(){
				$("#flag").value = "1";
				formData = getFormData($("#"+SL241108.examineForm));
				$('#main-content').postUrl(Main.contextPath + '/SL241108/confirm', formData);
			});
			$("#"+SL241108.UnConfirmButtonId).click(function(){
				$("#flag").value = "2";
				formData = getFormData($("#"+SL241108.examineForm));
				$('#main-content').postUrl(Main.contextPath + '/SL241108/confirm', formData);
			});
		}
}
$(document).ready(function() {
	//初始化调用
	SL241108.init();
});


