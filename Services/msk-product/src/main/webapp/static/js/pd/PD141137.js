/**
 * 产品基础数据列表JS
 *
 * @author xhy
 */
var PD141137 = {
	init : function() {
		PD141137.clickForData();
		$( "#pd141137accordion" ).accordion({ heightStyle: "content" });
		$('.classTree').treegrid();
	},
	clickForData:function(){
		$("a[name='zhuCeNeed']").click(function(){
			var value = $(this).attr("class").match(/\d+/g).toString();
			var url=$("#divZhuCe"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#pd141137accordion").accordion({ heightStyle: "fill" });
				$("#pd141137accordion").accordion("refresh");
				$("#zhuCeImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divZhuCe"+value).css("display","block");
			}else{
				$("#zhuCeImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divZhuCe"+value).css("display","none");
			}
		});
		$("a[name='jinZhiNeed']").click(function(){
			var value = $(this).attr("class").match(/\d+/g).toString();
			var url=$("#divJinZhi"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#pd141137accordion").accordion({ heightStyle: "fill" });
				$("#pd141137accordion").accordion("refresh");
				$("#jinZhiImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divJinZhi"+value).css("display","block");
			}else{
				$("#jinZhiImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divJinZhi"+value).css("display","none");
			}
		});
		$("a[name='shiXiaoNeed']").click(function(){
			var value = $(this).attr("class").match(/\d+/g).toString();
			var url=$("#divShiXiao"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#pd141137accordion").accordion({ heightStyle: "fill" });
				$("#pd141137accordion").accordion("refresh");
				$("#shiXiaoImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divShiXiao"+value).css("display","block");
			}else{
				$("#shiXiaoImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divShiXiao"+value).css("display","none");
			}
		});
		$("a[name='shangXianNeed']").click(function(){
			var value = $(this).attr("class").match(/\d+/g).toString();
			var url=$("#divShangXianNeed"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#pd141137accordion").accordion({ heightStyle: "fill" });
				$("#pd141137accordion").accordion("refresh");
				$("#shangXianNeedImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divShangXianNeed"+value).css("display","block");
			}else{
				$("#shangXianNeedImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divShangXianNeed"+value).css("display","none");
			}
		});
		$("a[name='xiaXianNeed']").click(function(){
			var value = $(this).attr("class").match(/\d+/g).toString();
			var url=$("#divXiaXianNeed"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#pd141137accordion").accordion({ heightStyle: "fill" });
				$("#pd141137accordion").accordion("refresh");
				$("#xiaXianNeedImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divXiaXianNeed"+value).css("display","block");
			}else{
				$("#xiaXianNeedImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divXiaXianNeed"+value).css("display","none");
			}
		});
		$("a[name='blacklistNeed']").click(function(){
			var value = $(this).attr("class").match(/\d+/g).toString();
			var url=$("#divBlacklist"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#pd141137accordion").accordion({ heightStyle: "fill" });
				$("#pd141137accordion").accordion("refresh");
				$("#blacklistImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divBlacklist"+value).css("display","block");
			}else{
				$("#blacklistImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divBlacklist"+value).css("display","none");
			}
		});
		$("a[name='productName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#productDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#productImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#productDiv"+value).css("display","block");
			}else{
				$("#productImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#productDiv"+value).css("display","none");
			}
		});
		$("a[name='numberNeed']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#divnumber"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#numberImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divnumber"+value).css("display","block");
			}else{
				$("#numberImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divnumber"+value).css("display","none");
			}
		});
		$("a[name='duanHuoNeed']").click(function(){
			var value = $(this).attr("class").match(/\d+/g).toString();
			var url=$("#divBreakGoods"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#breakImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divBreakGoods"+value).css("display","block");
			}else{
				$("#breakImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divBreakGoods"+value).css("display","none");
			}
		});
	},

};
$(document).ready(function() {
	// 初始化调用
	PD141137.init();
});
