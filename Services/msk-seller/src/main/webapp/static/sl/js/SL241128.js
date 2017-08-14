/**
 * 产品基础数据列表JS
 *
 * @author pxg
 */
var SL241128 = {
	search:"SL241128_search",
	searchDataId:"SL241128SearchData",
	init : function() {
		SL241128.linkage();
		SL241128.searchData();
		$("#SL24112801Id").postUrl(Main.contextPath+"/SL24112801/init");
},
	linkage:function(){
		$("#oneClass").change(function(){
			$("#twoClass").find("option").not(":first").remove();
			$("#threeClass").find("option").not(":first").remove();
			var classestreeCode=$("#oneClass").val();
			var treeLevel="2";
			$('#main-content').postUrl(Main.contextPath + "/SL241128/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
				$(data).each(function(i,val){
					$("#twoClass").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
				});
			},{refreshHtml:false});
		});
		$("#twoClass").change(function(){
			$("#threeClass").find("option").not(":first").remove();
			var classestreeCode=$("#twoClass").val();
			var treeLevel="3";
			$('#main-content').postUrl(Main.contextPath + "/SL241128/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
				$(data).each(function(i,val){
					$("#threeClass").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
				});
			},{refreshHtml:false});
		});
	},
	searchData:function(){
		$("#"+SL241128.search).click(function(){
			var twoSelectValue=$("#twoClass").val();
			if(twoSelectValue==0){
				$.alertMessage.info("第二级分类不能为空，请选择!");
			}else{
				formData = getFormData($("#" + SL241128.searchDataId));
				var classCode=formData.classCode[2];
				if(classCode!=0){
					$("#SL24112801Id").postUrl(Main.contextPath+"/SL24112801/queryData",{classCodeThree:classCode});
				}
				else{
					$("#SL24112801Id").postUrl(Main.contextPath+"/SL24112801/queryData",{classCodeTwo:formData.classCode[1]});
				}
			}
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	SL241128.init();
});
