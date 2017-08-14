/**
 * 产品基础数据列表JS
 *
 * @author pxg
 */
var PD141128 = {
	formId:"PD141128Form",
	saveButton:"PD141128_save",
	backButton:"PD141128_back",
	init : function() {
		PD141128.saveData();
	},
	saveData:function(){
		$("#"+PD141128.saveButton).click(function(){
			formData = getFormData($("#" +PD141128.formId));
			var classesCode=formData.breedCode;
			$('#main-content').postUrl(
				$("#" + PD141128.formId).attr("action"), formData,function(data) {
					if(data[0].message==1){
						$.alertMessage.info("记录已存在请重新输入!");
						return;
					}
					$.pdialog.close();
					var divId="#"+data[0].getDivId;
					var div="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>在线处理</td><td align='center'>操作<a class='cellButtonaddTwo' title='添加' addvalue='"+classesCode+"' href='javascript:void(0);' col='10' ><img src='"+Main.contextPath+"/static/core/images/action/add.png' style='width:13px;height:13px'></a></td></tr>";
					$(data).each(function(i){
						div+="<tr>";
						if(this.featureFlg==0){
							div+="<td align='center'>"+this.featureName+"</td><td width='10px'> <a class='cellButtoneditTwo' title='修改' updateCode='"+classesCode+"' updateValue='"+this.tcOnlineId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/zhuce.png' style='width:13px;height:13px'></a> <a class='cellButtondeleteTwo' id='delete' title='删除' deleteCode='"+classesCode+"' deleteValue='"+this.tcOnlineId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/delete.png' style='width:13px;height:13px'></a> </td></tr>";
						}
					});
					$(divId).html(div);
					PD14112601.editOrAdd();
				},{refreshHtml:false});
		});
		$("#"+PD141128.backButton).click(function(){
			$.pdialog.close();
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	PD141128.init();
});
