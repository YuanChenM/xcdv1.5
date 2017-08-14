/**
 * 产品加工技术标准指标JS
 *
 * @author xhy
 */
var PD141148 = {
	saveButtonId: "PD141148_SAVE",
	formId: "PD141148Form",
	standardId:"",
	init: function () {
		this.bindSaveButton();
		this.clickForData();
		this.marAdd();
		PD141148.standardId = $("input[name='standardId']").attr("value");
	},
	bindSaveButton: function () {
		$("#" + PD141148.saveButtonId).click(function () {
			var validator = mainValidation($("#" + PD141148.formId));
			var isValid = validator.form();
			if (isValid) {
				$.alertMessage.confirm("你确定要保存当前数据吗？", function () {
					$.alertMessage.close();
					formData = getFormData($("#" + PD141148.formId));
					$('#main-content').postUrl($("#" + PD141148.formId).attr("action"), formData, function () {
						$.alertMessage.info("数据保存成功!");
					},{refreshHtml:false});
				});
			}
		});
	},
	clickForData:function() {
		$("a[name='mctProNeedBtn']").click(function () {
			var value = $(this).attr("class").match(/\d+/g).toString();
			var url = $("#divMctProNeed" + value).css("display");
			if (url.indexOf("none") != -1) {
				$("#pd141148accordion").accordion({heightStyle: "fill"});
				$("#pd141148accordion").accordion("refresh");
				$("#mctProNeedImg" + value).attr('src', Main.contextPath + "/static/core/images/action/details_close.png");
				$("#divMctProNeed" + value).css("display", "block");
			} else {
				$("#mctProNeedImg" + value).attr('src', Main.contextPath + "/static/core/images/action/details_open.png");
				$("#divMctProNeed" + value).css("display", "none");
			}
		});
	},
	marAdd:function() {
		/*产品加工技术结案日*/
		$(".mctProJan").click(function () {
			var needId = $(this).attr("needId");
			var keyId = $(this).attr("keyId");
			var getDivId = "divMctProNeed" + needId;
			var fixDate = $(this).attr("fixDate");
			var raiseDate = $(this).attr("raiseDate");
			var mctStdItemIdAdd = $(this).attr("mctStdItemId");
			$.pdialog.open("修改供应商结案日", Main.contextPath + "/pd14114801/init", {
				width: 320,
				height: 180
			}, {
				getDivName: getDivId,
				raiseDateShow: raiseDate,
				keyId : keyId,
				standardId: PD141148.standardId,
				mctStdItemId: mctStdItemIdAdd
			});
		});
		/*产品加工技术禁止日*/
		$(".mctProJin").click(function () {
			var keyId = $(this).attr("keyId");
			var mctStdItemIdAdd = $(this).attr("mctStdItemId");
			var needId = $(this).attr("needId");
			var getDivId = "divMctProNeed" + needId;
			$.alertMessage.confirm("你确定要设为禁用数据吗？", function() {
				$.alertMessage.close();
				$('#main-content').postUrl(Main.contextPath + "/pd14114801/forbid",{keyId:keyId,getDivName:getDivId,
					standardId:PD141148.standardId,mctStdItemId:mctStdItemIdAdd},function(data){
					var divId="#"+data[0].getDivName;
					var value = parseInt(divId.replace(/[^0-9]/ig,""));
					var div="<div margin-right:0px;width:350px' id='divMctProNeed"+value+"' >";
					div+="<table class='tree dataTable no-footer' style='min-width:250px' width='250px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>供应商编码</td><td align='center'>内容</td><td align='center'>提出日</td><td align='center'>结案日</td><td align='center'>操作</td></tr>";
					$(data).each(function(i){
						if(this.discussStatus== "0"){
							div+="<tr><td align='center'>"+this.slPdId+"</td><td align='center'>"+this.mctStdVal+"</td><td align='center'>"+this.raiseDateShow+"</td><td align='center'>"+this.fixDateShow+"</td><td width='10px'><a class='mctProJan' keyId='"+this.keyId+"' fixDate='"+this.fixDateShow+"' needId='"+value+"' raiseDate='"+this.raiseDateShow+"' mctStdItemId='"+this.mctStdItemId+"' title='结案日' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/jiean.png' style='width:13px;height:13px'></a><a class='mctProJin' keyId='"+this.keyId+"' needId='"+value+"' mctStdItemId='"+this.mctStdItemId+"' title='禁止' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/jinzhizhunren.png' style='width:13px;height:13px'></a></td></tr>";
						}else{
							div+="<tr><td align='center'>"+this.slPdId+"</td><td align='center'>"+this.mctStdVal+"</td><td align='center'>"+this.raiseDateShow+"</td><td align='center'>"+this.fixDateShow+"</td><td></td></tr>";
						}
					});
					$(divId).html(div);
					PD141148.marAdd();
				},{refreshHtml:false},function(){
					alert("禁用失败")
				});
			});
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	PD141148.init();
});
