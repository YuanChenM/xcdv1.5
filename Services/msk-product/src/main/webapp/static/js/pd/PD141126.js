/**
 * 产品基础数据列表JS
 *
 * @author pxg
 */
var PD141126 = {
	search:"PD141126_SEARCH",
	searchDataId:"PD141126SearchData",
	init : function() {
		PD141126.linkage();
		PD141126.searchData();
		$("#PD14112601Id").postUrl(Main.contextPath+"/PD14112601/init");
},
	linkage:function(){
		$("#oneClass").change(function(){
			$("#twoClass").find("option").not(":first").remove();
			$("#threeClass").find("option").not(":first").remove();
			var classestreeCode=$("#oneClass").val();
			var treeLevel="2";
			$('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
				$(data).each(function(i,val){
					$("#twoClass").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
				});
			},{refreshHtml:false});
		});
		$("#twoClass").change(function(){
			$("#threeClass").find("option").not(":first").remove();
			var classestreeCode=$("#twoClass").val();
			var treeLevel="3";
			$('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
				$(data).each(function(i,val){
					$("#threeClass").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
				});
			},{refreshHtml:false});
		});
	},
	searchData:function(){
		$("#"+PD141126.search).click(function(){
			var twoSelectValue=$("#twoClass").val();
			if(twoSelectValue==0){
				alert("第二级分类不能为空，请选择!");
			}else{
				formData = getFormData($("#" + PD141126.searchDataId));
				var classCode=formData.classCode[2];
				if(classCode!=0){
					$("#PD14112601Id").postUrl(Main.contextPath+"/PD14112601/queryData",{classCodeThree:classCode});
				}
				else{
					$("#PD14112601Id").postUrl(Main.contextPath+"/PD14112601/queryData",{classCodeTwo:formData.classCode[1]});
				}
			}
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	PD141126.init();
});
