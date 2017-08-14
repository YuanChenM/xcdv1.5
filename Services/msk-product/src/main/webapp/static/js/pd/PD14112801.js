/**
 * 产品基础数据列表JS
 *
 * @author pxg
 */
var PD14112801 = {
	formId:"PD14112801Form",
	saveButton:"PD14112801_SAVE",
	backButton:"PD14112801_BACK",
	init : function() {
		PD14112801.saveData();
	},
	saveData:function(){
		$("#"+PD14112801.saveButton).click(function(){
			formData = getFormData($("#" +PD14112801.formId));
			var classesCode=formData.breedCode;
			$('#main-content').postUrl(
				$("#" + PD14112801.formId).attr("action"), formData,function(data) {
					if(data[0].message==1){
						$.alertMessage.info("记录已存在请重新输入!");
						return;
					}
					$.pdialog.close();
					var divId="#"+data[0].getDivId;
					var div="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>标准目录</td> <td align='center'>产品销售对象</td> <td align='center'>产品加工方向</td> <td align='center'>操作<a class='buttonContentAdd' addvalue='"+classesCode+"' title='添加' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/add.png' style='width:13px;height:13px'></a></td></tr>";
					$(data).each(function(i){
						div+="<tr><td align='center'>"+this.featureName+"</td><td align='center'>"+this.salesTarget+"</td><td align='center'>"+this.machiningWay+"</td><td width='10px'> <a class='buttonContentDelete' title='删除' contentDeleteClassCode='"+classesCode+"' contentDeleteId='"+this.tcContentId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/delete.png' style='width:13px;height:13px'></a> </td></tr>";
					});
					$(divId).html(div);
					PD14112601.editOrAdd();
				},{refreshHtml:false});
		});
		$("#"+PD14112801.backButton).click(function(){
			$.pdialog.close();
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	PD14112801.init();
});
