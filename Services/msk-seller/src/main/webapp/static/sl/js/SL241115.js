/**
 * 卖家信息列表JS
 * 
 * @author gyh
 */
var $List_Grid;
var SL241115 = {
	newButtonId : "SL241115_NEW",
	downButtonId : "SL241115_DOWN",
	init : function() {
		$List_Grid = $('#sl241101_list_grid').grid({
			actionHandler:SL241115.actionHandler,
			linkHandler:SL241115.linkHandler
		});
		this.bindSearchButton();
	},
	// 绑定按钮
	bindSearchButton : function() {
		$("#" + SL241115.newButtonId).click(function() {
			SL241115.newData();
		});
		$("#" + SL241115.downButtonId).click(function() {
			var param = new Object();
			param["slCodeDis"] = $("[name=filterMap\\[\\'slCodeDis\\'\\]]").val();
			param["slShowName"] = $("[name=filterMap\\[\\'slShowName\\'\\]]").val();
			param["slMainClass"] = $("[name=filterMap\\[\\'slMainClass\\'\\]]").val();
			param["slContact"] = $("[name=filterMap\\[\\'slContact\\'\\]]").val();
			param["slTel"] = $("[name=filterMap\\[\\'slTel\\'\\]]").val();
			param["cityName"] = $("[name=filterMap\\[\\'cityName\\'\\]]").val();
			param["lgcsAreaName"] = $("[name=filterMap\\[\\'lgcsAreaName\\'\\]]").val();
			var dates = new Date();
			var dataStr = dates.format("yyyyMMddhhmmssS");
			downloadAsync("SL241115Report", "sl241115Logic", "msk-seller", "卖家信息列表_" + dataStr+ ".xlsx", param);
		});
	},
	newData : function() {
		formData = getFormData($("#" +SL241115.formId));
		$('#main-content').postUrl(Main.contextPath + "/SL241103000/init/",{slCode:formData.slCode,epId:formData.epId});
	},
	actionHandler:function(rowdata,coltype,row,col){
		/** 操作按钮 */
		/**查看卖家产品信息*/
		if(coltype=='detail'){
			var data = new Object();
			Main.detailWindow(Main.contextPath + "/SL241116/init/"+rowdata.slCode, data, "卖家产品信息维护");
			//$('#main-content').postUrl(Main.contextPath + "/SL241116/init/"+rowdata.slCode);
		}
		//else if(coltype == 'edit'){
		//	$('#main-content').postUrl(Main.contextPath + "/SL24110100/init/",{slCode:rowdata.slCode,epId:rowdata.epId,slAccount:rowdata.slAccount});
		//}
		else if(coltype == 'search'){
			var str = "卖家ID："+rowdata.slCode+"<br/>"+"卖家编码："+rowdata.slCodeDis+"<br/>"+"账号："+rowdata.slAccount+"<br/>"+"密码："+rowdata.accountPsd+"<br/>"+"手机号："+rowdata.slTel+"<br/>";
			$.alertMessage.info(str, function() {
				$.alertMessage.close();
			});
		}else if(coltype == 'insured'){
			var data = new Object();
			data['slCode'] = rowdata.slCode;
			Main.detailWindow(Main.contextPath + "/SL241132/init", data, "卖家货号列表");
			//$('#main-content').postUrl(Main.contextPath + "/SL241132/init",{slCode:rowdata.slCode});
		}else if(coltype == 'download'){
			$.pdialog.open("企业信息下载", Main.contextPath + "/SL24110100/downChooseInit", {
				width: 700,
				height: 500
			}, rowdata);
		}
	},
	linkHandler: function (rowdata, coltype, row, col) {
		//var data = new Object();
		//data['slCode'] = rowdata.slCode;
		//data['epId'] = rowdata.epId;
		//data['slAccount'] = rowdata.slAccount;
		//Main.detailWindow(Main.contextPath + "/SL24110100/init", data, "卖家企业信息修改");
		$('#main-content').postUrl(Main.contextPath + "/SL24110100/init/",{
			slCode:rowdata.slCode,epId:rowdata.epId,slAccount:rowdata.slAccount
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	SL241115.init();
});
