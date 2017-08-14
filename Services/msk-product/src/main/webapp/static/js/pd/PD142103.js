/**
 * 产品基础数据列表JS
 *
 * @author pxg
 */
var PD142103 = {
	search:"PD142103_SEARCH",
	PD142103Grid:"null",
	formId:"PD142103SearchData",
	init:function(){
		PD142103.PD142103Grid = $('#PD142103_grid').grid();
		this.searchData();
		this.queryTwoClass($('#classCode').val());
	},
	searchData:function(){
		$("a[class='oneClassCode']").click(function(){
			$('#classCode').val($(this).attr("value"));
			PD142103.queryTwoClass($('#classCode').val());
			PD142103.PD142103Grid.fnDraw();
		});
		$("#"+PD142103.search).click(function(){
			PD142103.PD142103Grid.fnDraw();
		});
	},
	queryTwoClass:function(data){
		$("#main-content").postUrl(Main.contextPath + "/PD142103/queryTwoClass",{classCode:data},function(datas){
			$("#machiningName").html("");
			if(null!=datas && 0<datas.length){
				var selectData="<option value=''>请选择</option>";
				$(datas).each(function(i,val){
					selectData+="<option value='"+val.levelName+"'>"+val.levelName+"</option>";
					$("#machiningName").html(selectData);
				});
			}else{
				$("#machiningName").html("<option value=''>请选择</option>");
			}
		},{refreshHtml:false});
	}
}
$(document).ready(function() {
	// 初始化调用
	PD142103.init();
});
