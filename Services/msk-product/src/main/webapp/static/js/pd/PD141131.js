/**
 * 产品基础数据列表JS
 *
 * @author pxg
 */
var PD141131 = {
	orderTime:"#orderTime",
	init : function() {
		PD141131.clickForData();
		PD141131.linkage();
		PD141131.editOrAdd();
		this.bindDatePicber(PD141131.orderTime);
		$( "#pd141131accordion" ).accordion({ heightStyle: "content" });
	},
	clickForData:function(){
		$("#shenBtn").click(function(){
			var url=$("#divshen").css("display");
			if(url.indexOf("none")!=-1){
				$("#shenImg").attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divshen").css("display","block");
			}else{
				$("#shenImg").attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divshen").css("display","none");
			}
		});
		$("#buyerBtn").click(function(){
			var url=$("#divbuyer").css("display");
			if(url.indexOf("none")!=-1){
				$("#buyerImg").attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divbuyer").css("display","block");
			}else{
				$("#buyerImg").attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divbuyer").css("display","none");
			}
		});
		$("#shangBtn").click(function(){
			var url=$("#divshang").css("display");
			if(url.indexOf("none")!=-1){
				$("#shangImg").attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divshang").css("display","block");
			}else{
				$("#shangImg").attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divshang").css("display","none");
			}
		});
		$("#objectBtn").click(function(){
			var url=$("#divObject").css("display");
			if(url.indexOf("none")!=-1){
				$("#objectImg").attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divObject").css("display","block");
			}else{
				$("#objectImg").attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divObject").css("display","none");
			}
		});
		$("#directionBtn").click(function(){
			var url=$("#divDirection").css("display");
			if(url.indexOf("none")!=-1){
				$("#directionImg").attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divDirection").css("display","block");
			}else{
				$("#directionImg").attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divDirection").css("display","none");
			}
		});
	},
	linkage:function(){
		$("#oneClass").change(function(){
			$("#twoClass").find("option").not(":first").remove();
			$("#threeClass").find("option").not(":first").remove();
			$("#fourClass").find("option").not(":first").remove();
			$("#fiveClass").find("option").not(":first").remove();
			var classestreeCode=$("#oneClass").val();
			var level="2";
			$('#main-content').postUrl(Main.contextPath + "/PD141131/query",{classestreeCode:classestreeCode,level:level},function(data){
				$(data).each(function(i,val){
					$("#twoClass").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
				});
			},{refreshHtml:false});
		});
		$("#twoClass").change(function(){
			$("#threeClass").find("option").not(":first").remove();
			$("#fourClass").find("option").not(":first").remove();
			$("#fiveClass").find("option").not(":first").remove();
			var classestreeCode=$("#twoClass").val();
			var level="3";
			$('#main-content').postUrl(Main.contextPath + "/PD141131/query",{classestreeCode:classestreeCode,level:level},function(data){
				$(data).each(function(i,val){
					$("#threeClass").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
				});
			},{refreshHtml:false});
		});
		$("#threeClass").change(function(){
			$("#fourClass").find("option").not(":first").remove();
			$("#fiveClass").find("option").not(":first").remove();
			var classestreeCode=$("#threeClass").val();
			var level="4";
			$('#main-content').postUrl(Main.contextPath + "/PD141131/query",{classestreeCode:classestreeCode,level:level},function(data){
				$(data).each(function(i,val){
					$("#fourClass").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
				});
			},{refreshHtml:false});
		});
		$("#fourClass").change(function(){
			$("#fiveClass").find("option").not(":first").remove();
			var classestreeCode=$("#fourClass").val();
			var level="5";
			$('#main-content').postUrl(Main.contextPath + "/PD141131/query",{classestreeCode:classestreeCode,level:level},function(data){
				$(data).each(function(i,val){
					$("#fiveClass").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
				});
			},{refreshHtml:false});
		});
	},
	editOrAdd:function(){
		$(".buyerButtonadd").click(function(){
			$.pdialog.open("新增销售产品", Main.contextPath + "/PD141132/init", {
				width: 320,
				height: 180
			});
		});
		$(".buyerButtonedit").click(function(){
			$.pdialog.open("修改销售产品", Main.contextPath + "/PD141132/init", {
				width: 320,
				height: 180
			}, {
				product:"130-150g",
				brand:"西餐",
				local:"加拿大"
			 });
		});
		$(".buyerButtondelete").click(function(){
				$.alertMessage.confirm("你确定要删除当前数据吗？", function() {
					$.alertMessage.close();
				});
		});
		$(".objectButtonadd").click(function(){
			$.pdialog.open("新增产品销售对象", Main.contextPath + "/PD141133/init", {
				width: 320,
				height: 180
			});
		});
		$(".objectButtonedit").click(function(){
			$.pdialog.open("修改产品销售对象", Main.contextPath + "/PD141133/init", {
				width: 320,
				height: 180
			}, {
				standard:"130-150g",
				survey:"130-150g"
			});
		});
		$(".objectButtondelete").click(function(){
			$.alertMessage.confirm("你确定要删除当前数据吗？", function() {
				$.alertMessage.close();
			});
		});
		$(".directionButtonadd").click(function(){
			$.pdialog.open("新增产品加工方向", Main.contextPath + "/PD141134/init", {
				width: 320,
				height: 180
			});
		});
		$(".directionButtonedit").click(function(){
			$.pdialog.open("修改产品加工方向", Main.contextPath + "/PD141134/init", {
				width: 320,
				height: 180
			}, {
				standard:"130-150g",
				survey:"130-150g"
			});
		});
		$(".directionButtondelete").click(function(){
			$.alertMessage.confirm("你确定要删除当前数据吗？", function() {
				$.alertMessage.close();
			});
		});
	},
	bindDatePicber: function(orderTimeId){
	$(orderTimeId).datepicker({
		showButtonPanel: true,
		dateFormat:'yy-mm-dd',
		changeMonth: true,
		changeYear: true,
	});
}
}
$(document).ready(function() {
	// 初始化调用
	PD141131.init();
});
