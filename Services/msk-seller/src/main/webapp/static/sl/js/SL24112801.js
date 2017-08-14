/**
 * 产品基础数据列表JS
 *
 * @author pxg
 */
var SL24112801 = {
	search:"SL24112801_search",
	searchDataId:"SL24112801SearchData",
	divId:null,
	classestreeCode:null,
	SL24112801Grid: null,
	init : function() {
		SL24112801.clickForData();
		$(".group-accordion").accordion({ heightStyle: "'auto'" });
		$('.classTree').treegrid();
},
	clickForData:function(){
		$("a[name='marketRegisterName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			SL24112801.classestreeCode=$(this).attr("value");
			var url=$("#marketRegisterDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#SL24112801accordion").accordion({ heightStyle: "fill" });
				$("#SL24112801accordion").accordion("refresh");
				$("#marketRegisterImg"+value).attr('src',Main.contextPath +"/static/images/action/details_close.png");
				$("#marketRegisterDiv"+value).css("display","block");
			}else{
				$("#marketRegisterImg"+value).attr('src',Main.contextPath +"/static/images/action/details_open.png");
				$("#marketRegisterDiv"+value).css("display","none");
			}
		});
		$("a[name='noMarketRegisterName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#noMarketRegisterDiv"+value).css("display");
			SL24112801.classestreeCode=$(this).attr("value");
			SL24112801.divId="noMarketRegisterDiv"+value;
			if(url.indexOf("none")!=-1){
				$("#SL24112801accordion").accordion({ heightStyle: "fill" });
				$("#SL24112801accordion").accordion("refresh");
				$("#noMarketRegisterImg"+value).attr('src',Main.contextPath +"/static/images/action/details_close.png");
				$("#noMarketRegisterDiv"+value).css("display","block");
			}else{
				$("#noMarketRegisterImg"+value).attr('src',Main.contextPath +"/static/images/action/details_open.png");
				$("#noMarketRegisterDiv"+value).css("display","none");
			}
		});
		$("a[name='sellerName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#sellerDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#sellerImg"+value).attr('src',Main.contextPath +"/static/images/action/details_close.png");
				$("#sellerDiv"+value).css("display","block");
			}else{
				$("#sellerImg"+value).attr('src',Main.contextPath +"/static/images/action/details_open.png");
				$("#sellerDiv"+value).css("display","none");
			}
		});
		$("a[name='sellerLineName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#sellerLineDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#sellerLineImg"+value).attr('src',Main.contextPath +"/static/images/action/details_close.png");
				$("#sellerLineDiv"+value).css("display","block");
			}else{
				$("#sellerLineImg"+value).attr('src',Main.contextPath +"/static/images/action/details_open.png");
				$("#sellerLineDiv"+value).css("display","none");
			}
		});
		$("a[name='oneLineName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#oneLineDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#oneLineImg"+value).attr('src',Main.contextPath +"/static/images/action/details_close.png");
				$("#oneLineDiv"+value).css("display","block");
			}else{
				$("#oneLineImg"+value).attr('src',Main.contextPath +"/static/images/action/details_open.png");
				$("#oneLineDiv"+value).css("display","none");
			}
		});
		$("a[name='contentName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#contentDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#contentImg"+value).attr('src',Main.contextPath +"/static/images/action/details_close.png");
				$("#contentDiv"+value).css("display","block");
			}else{
				$("#contentImg"+value).attr('src',Main.contextPath +"/static/images/action/details_open.png");
				$("#contentDiv"+value).css("display","none");
			}
		});
		$("a[name='oneLineOemName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#oneLineOemDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#oneLineOemImg"+value).attr('src',Main.contextPath +"/static/images/action/details_close.png");
				$("#oneLineOemDiv"+value).css("display","block");
			}else{
				$("#oneLineOemImg"+value).attr('src',Main.contextPath +"/static/images/action/details_open.png");
				$("#oneLineOemDiv"+value).css("display","none");
			}
		});
		$("a[name='productName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#productDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#productImg"+value).attr('src',Main.contextPath +"/static/images/action/details_close.png");
				$("#productDiv"+value).css("display","block");
			}else{         
				$("#productImg"+value).attr('src',Main.contextPath +"/static/images/action/details_open.png");
				$("#productDiv"+value).css("display","none");
			}
		});
		$("a[name='packingName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#packingDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#packingImg"+value).attr('src',Main.contextPath +"/static/images/action/details_close.png");
				$("#packingDiv"+value).css("display","block");
			}else{
				$("#packingImg"+value).attr('src',Main.contextPath +"/static/images/action/details_open.png");
				$("#packingDiv"+value).css("display","none");
			}
		});
		$("a[name='tcForbidName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#tcForbidDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#tcForbidImg"+value).attr('src',Main.contextPath +"/static/images/action/details_close.png");
				$("#tcForbidDiv"+value).css("display","block");
			}else{
				$("#tcForbidImg"+value).attr('src',Main.contextPath +"/static/images/action/details_open.png");
				$("#tcForbidDiv"+value).css("display","none");
			}
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	SL24112801.init();
});
