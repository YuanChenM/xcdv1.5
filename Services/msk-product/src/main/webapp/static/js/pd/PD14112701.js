/**
 * 产品基础数据列表JS
 *
 * @author pxg
 */
var PD14112701 = {
	formId:"PD14112701Form",
	saveButton:"PD14112701_save",
	backButton:"PD14112701_back",
	init : function() {
		PD14112701.saveData();
	},
	saveData:function(){
		$("#"+PD14112701.saveButton).click(function(){
			formData = getFormData($("#" +PD14112701.formId));
			var classesCode=formData.breedCode;
			$('#main-content').postUrl(
				$("#" + PD14112701.formId).attr("action"), formData,function(data) {
					if(data[0].message==1){
						$.alertMessage.info("记录已存在请重新输入!");
						return;
					}
					$.pdialog.close();
					var divId="#"+data[0].getDivId;
					var div="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>OEM上线产品</td><td align='center'>操作<a class='oneLineOembuttonAdd' title='添加' oneLineOemaddCode='"+classesCode+"' href='javascript:void(0);' col='10' ><img src='"+Main.contextPath+"/static/core/images/action/add.png' style='width:13px;height:13px'></a></td></tr>";
					$(data).each(function(i){
						div+="<tr><td align='center'>"+this.featureName+"</td><td width='10px'> <a class='oneLineOembuttonDelete' title='删除' oneLineOemdeleteId='"+this.tcOemId+"' oneLineOemdeleteClassCode='"+classesCode+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/delete.png' style='width:13px;height:13px'></a> </td></tr>";
					});
					$(divId).html(div);
					PD14112601.editOrAdd();
				},{refreshHtml:false});
		});
		$("#"+PD14112701.backButton).click(function(){
			$.pdialog.close();
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	PD14112701.init();
});
