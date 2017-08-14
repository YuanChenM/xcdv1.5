/**
 * 确认订单明细
 */

var SO151502 = {
	initDataGrid : function(){
		var subOrderId=$('#subOrderId').val();
		var orderId=$('#orderId').val();
		$("#baseorder").postUrl(Main.contextPath+"/so/baseorder/init/"+orderId+"/"+subOrderId);
		$("#orderbuyers").postUrl(Main.contextPath+"/so/buyers/init/"+orderId);
		 $("#basedelivery").postUrl(Main.contextPath+"/so/delivery/init/"+orderId);
		 $("#logisticsDetail").postUrl(Main.contextPath+"/so/delivery/actual/init/"+orderId+"/"+subOrderId);
		$("img[name='SO151502']").bind("click", function() {
			$.pdialog.open("订单详细供应商修改信息",Main.contextPath + "/SO151403/init",{resizable:false, width:500, height:500},{orderDetailAvailabilityId:$(this).attr("orderDetailAvailabilityId")});
		});
		$("img[name='orderDetailInfo']").bind("click", function() {
			$.pdialog.open("订单明细修改信息",Main.contextPath + "/SO151404/init",{resizable:false, width:500, height:300},{orderDetailId:$(this).attr("orderDetailId"),orderId:$("#orderId").val()});
		});
		$('.tree').treegrid();
		this.bindComplete();
	},
	bindComplete:function(){
		var orderId=$('#orderId').val();
		var orderCode=$('#orderCode').val();
		var orderType=$('#orderTypeHidden').val();
		var ver=$('#ver').val();

	/*	$("#SO251108_OVER").click(function(){
			$.alertMessage.confirm("是否确定该订单已经完成收货？",function () {
				$.alertMessage.close();
				$('#main-content').postUrl(Main.contextPath+"/SO151502/allReceipt",{orderId:orderId,orderCode:orderCode},{enterMark:"init"},function(){
				});
			});

		});

		$("#SO251108_CHANGE").click(function(){
			$.alertMessage.confirm("是否确定买手已经付款？", function() {
				$.alertMessage.close();
				$('#main-content').postUrl(Main.contextPath+"/SO151502/orderPayment",{orderId:orderId},{enterMark:"init"},function(){
				});
			});
		});

		$("#SO251108_REALDELETE").click(function(){
			$.alertMessage.confirm("订单删除后将无法恢复，是否确定要删除该订单？", function() {
				$.alertMessage.close();
				$('#main-content').postUrl(Main.contextPath+"/SO151502/realDelete",{orderId:orderId},{enterMark:"init"},function(){
				});
			});
		});*/

		$("#SO151502_CANCEL").click(function(){
			var subOrderIds=$("#subOrderIds").val();
			var subOrderId=$('#subOrderId').val();
			var orderId=$('#orderId').val();
			var message="是否确定要取消该订单？";
			if(subOrderIds.length>0){
				message="该订单 关联其他分批订单 id:"+subOrderIds+"是否确定要取消完整订单？"
			}
			$.alertMessage.confirm(message, function() {
				$.alertMessage.close();
				$('#main-content').postUrl(Main.contextPath+"/SO151502/cancel",{orderId:orderId,orderType:orderType,ver:ver},function(resp){
					if ("S" == resp.status) {
						$.alertMessage.info("整单取消成功！");
						$('#main-content').postUrl(Main.contextPath+"/SO151502/init/" + orderId + "/" + subOrderId,{},function(){
							$("#headBreadCrumb").hide();
						});
					}
					else {
						$.alertMessage.info(resp.message);
					}
				}, {refreshHtml: false});
			});
		});
		$("#SO151502_PRINT").click(function(){
			var dform = $("<form>");   //定义一个form表单
			var orderIdVal =  $('#orderId').val();
			var subOrderIdVal =  $('#subOrderId').val();
			var callbackParamJson = "{\"orderId\":\"" + orderIdVal + "\"," + "\"subOrderId\":\"" + subOrderIdVal + "\"}";
			var callBackParamName = "callBackParam";
			dform.append('<input type="text" name="' + callBackParamName + '" value=' + callbackParamJson + '>');
			dform.attr('style', 'display:none');   //在form表单中添加查询参数
			dform.attr('target', 'newWin');
			dform.attr('method', 'post');
			dform.attr('action', Main.contextPath + "/orderPDFPrint/order/");
			$('body').append(dform);  //将表单放置在web中
			window.open("about:blank", "newWin", "");//newWin 是上面form的target
			dform.submit();


	/*		var param = new Object();
			param["orderId"]=$('#orderId').val();
			param["subOrderId"]=$('#subOrderId').val();
			downloadAsync('PDF000002', "SO151502ExportOrderPDFService", "order", "orderDetail.pdf", param,true);*/
		});
	},

	childOrderIdClick:function(tag){
		var orderId=$(tag).attr("value");
		$('#main-content').postUrl(Main.contextPath + "/SO151502/init/"+orderId,{enterMark:"init"});
	}

}
$(document).ready(function() {
	// 初始化调用
	SO151502.initDataGrid();
});