/**
 * 产品基础数据列表JS
 *
 * @author pxg
 */
var PD141127 = {
	formId:"PD141127Form",
	saveButton:"PD141127_save",
	backButton:"PD141127_back",
	init : function() {
		PD141127.saveData();
	},
	saveData:function(){
		$("#"+PD141127.saveButton).click(function(){
			formData = getFormData($("#" +PD141127.formId));
			var classesCode=formData.breedCode;
			$('#main-content').postUrl(
				$("#" + PD141127.formId).attr("action"), formData,function(data) {
					if(data[0].message==1){
						$.alertMessage.info("记录已存在请重新输入!");
						return;
					}
					$.pdialog.close();
					var divId="#"+data[0].getDivId;
					var div="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>正式上线</td><td align='center'>操作<a class='oneLinebuttonadd' title='添加' addOnLinevalue='"+classesCode+"' href='javascript:void(0);' col='10' ><img src='"+Main.contextPath+"/static/core/images/action/add.png' style='width:13px;height:13px'></a></td></tr>";
					$(data).each(function(i){
						div+="<tr><td align='center'>"+this.featureName+"</td><td width='10px'> <a class='oneLinebuttondelete' title='删除' onLinedeleteClassCode='"+classesCode+"' onLinedeleteId='"+this.tcOnlineId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/delete.png' style='width:13px;height:13px'></a> </td></tr>";
					});
					$(divId).html(div);
					PD14112601.editOrAdd();
				},{refreshHtml:false});
		});
		$("#"+PD141127.backButton).click(function(){
			$.pdialog.close();
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	PD141127.init();
});
