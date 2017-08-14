/**
 * 产品基础数据列表JS
 *
 * @author pxg
 */
var PD141129 = {
	formId:"PD141129Form",
	saveButton:"PD141129_save",
	backButton:"PD141129_back",
	init : function() {
		PD141129.saveData();
	},
	saveData:function(){
		$("#"+PD141129.saveButton).click(function(){
			formData = getFormData($("#" +PD141129.formId));
			var classesCode=formData.breedCode;
			$('#main-content').postUrl(
				$("#" + PD141129.formId).attr("action"), formData,function(data) {
					if(data[0].message==1){
						$.alertMessage.info("记录已存在请重新输入!");
						return;
					}
					$.pdialog.close();
					var divId="#"+data[0].getDivId;
					var div="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>禁止准入产品</td><td align='center'>操作<a class='tcForBidButtonadd' title='添加' tcForBidaddCode='"+classesCode+"' href='javascript:void(0);' col='10' ><img src='"+Main.contextPath+"/static/core/images/action/add.png' style='width:13px;height:13px'></a></td></tr>";
					$(data).each(function(i){
						div+="<tr><td align='center'>"+this.featureName+"</td><td width='10px'> <a class='tcForBidButtondelete' title='删除' tcForBidDeleteValue='"+this.tcOemId+"' tcForBidDeleteCode='"+classesCode+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/delete.png' style='width:13px;height:13px'></a> </td></tr>";
					});
					$(divId).html(div);
					PD14112601.editOrAdd();
				},{refreshHtml:false});
		});
		$("#"+PD141129.backButton).click(function(){
			$.pdialog.close();
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	PD141129.init();
});
