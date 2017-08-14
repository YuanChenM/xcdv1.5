/**
 * 产品基础数据列表JS
 *
 * @author pxg
 */

var PD141149 = {
	saveButtonId: "PD141149_SAVE",
	formId: "PD141149Form",
	standardId:"",
	init: function () {
		$('.tree').treegrid();
		this.bindSaveButton();
		this.clickForData();
		this.marAdd();
		PD141149.standardId = $("input[name='standardId']").attr("value");
		$("#pd141149accordion").accordion({ heightStyle: "content" });
	},
	bindSaveButton: function () {
		$("#" + PD141149.saveButtonId).click(function () {
			var validator = mainValidation($("#" + PD141149.formId));
			var isValid = validator.form();
			if (isValid) {
				$.alertMessage.confirm("你确定要保存当前数据吗？", function () {
					$.alertMessage.close();
					formData = getFormData($("#" + PD141149.formId));
					$('#main-content').postUrl($("#" + PD141149.formId).attr("action"), formData, function(date) {
						if(date='flag'){
							$.alertMessage.info("数据操作成功!");
						}
					},{refreshHtml:false});
				});
			}
		});
	},
	clickForData:function(){
		$("a[name='argMarNeedBtn']").click(function(){
			var value = $(this).attr("class").match(/\d+/g).toString();
			var url=$("#divArgMarNeed"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#pd141149accordion").accordion({ heightStyle: "fill" });
				$("#pd141149accordion").accordion("refresh");
				$("#argMarNeedImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divArgMarNeed"+value).css("display","block");
			}else{
				$("#argMarNeedImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divArgMarNeed"+value).css("display","none");
			}
		});

		$("a[name='argProNeedBtn']").click(function(){
			var value = $(this).attr("class").match(/\d+/g).toString();
			var url=$("#divArgProNeed"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#pd141149accordion").accordion({ heightStyle: "fill" });
				$("#pd141149accordion").accordion("refresh");
				$("#argProNeedImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divArgProNeed"+value).css("display","block");
			}else{
				$("#argProNeedImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divArgProNeed"+value).css("display","none");
			}
		});

		/*市场禁止日	*/
		$("a[name='argMarNeedNoBtn']").click(function(){
			var value = $(this).attr("class").match(/\d+/g).toString();
			var url=$("#divArgMarNoNeed"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#argMarNeedNoImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divArgMarNoNeed"+value).css("display","block");
			}else{
				$("#argMarNeedNoImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divArgMarNoNeed"+value).css("display","none");
			}
		});
		/*供应商禁止日	*/
		$("a[name='argProNeedNoBtn']").click(function(){
			var value = $(this).attr("class").match(/\d+/g).toString();
			var url=$("#divArgProNoNeed"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#argProNeedNoImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#divArgProNoNeed"+value).css("display","block");
			}else{
				$("#argProNeedNoImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#divArgProNoNeed"+value).css("display","none");
			}
		});
	},
	marAdd:function(){
		/*添加*/
		$(".argTncMar").click(function(){
			var tncStdItemIdAdd =  $(this).attr("tncStdItemId");
			var needId =  $(this).attr("needId");
			var getDivId="divArgMarNeed"+needId;
			$.pdialog.open("新增市场需求审核注册值", Main.contextPath + "/pd14114901/init", {
				width: 320,
				height: 180
			},{
				standardId:PD141149.standardId,
				tncStdItemId:tncStdItemIdAdd,
				getDivName:getDivId
			});
		});
		/*结案日*/
		$(".argTncMarJan").click(function(){
			var keyId =  $(this).attr("keyId");
			var fixDate =  $(this).attr("fixDate");
			var raiseDate =  $(this).attr("raiseDate");
			var tncStdItemIdAdd =  $(this).attr("tncStdItemId");
			var needId =  $(this).attr("needId");
			var getDivId="divArgMarNeed"+needId;
			$.pdialog.open("设置市场需求结案日", Main.contextPath + "/pd14114901/init", {
				width: 320,
				height: 180
			}, {
				keyId:keyId,
				standardId:PD141149.standardId,
				tncStdItemId:tncStdItemIdAdd,
				fixDateShow:fixDate,
				getDivName:getDivId,
				raiseDateShow:raiseDate
			});
		});

		/*禁用*/
		$(".argTncMarJin").click(function(){
			var keyId =  $(this).attr("keyId");
			var tncStdItemIdAdd =  $(this).attr("tncStdItemId");
			var needId =  $(this).attr("needId");
			var getDivId="divArgMarNeed"+needId;
			var getNoDivId ="divArgMarNoNeed"+needId;
			$.alertMessage.confirm("你确定要设为禁用数据吗？", function() {
				$.alertMessage.close();
				$('#main-content').postUrl(Main.contextPath + "/pd14114901/forbid",{keyId:keyId,standardId:PD141149.standardId,
					tncStdItemId:tncStdItemIdAdd,getDivName:getDivId,getDivJin:getNoDivId},function(data){
					var divId="#"+getDivId;
					var value = parseInt(divId.replace(/[^0-9]/ig,""));
					var div="<div margin-right:0px;width:300px' id='divArgMarNeed"+value+"' >";
					div+="<table class='tree dataTable no-footer' style='min-width:250px' width='250px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>内容 <a class='argTncMar' tncStdItemId='"+value+"' id='add' title='添加' href='javascript:void(0);' col='10'><img border='0px' src='"+Main.contextPath+"/static/core/images/action/add.png' style='width:13px;height:13px'></a></td><td align='center'>提出日</td><td align='center'>结案日</td><td align='center'>操作</td></tr>";
					var divIdJin="#"+getNoDivId;
					var divJin ="<div margin-right:0px;width:260px' id='divArgMarNoNeed"+value+"'>";
					divJin+=" <table class='tree dataTable no-footer' style='min-width:250px' width='250px' showAddBtn='true'>";
					divJin+=" <tr style='background:#DBDBDB'><td align='center'>内容</td><td align='center'>禁止准入日</td></tr>";
					$(data.listMar).each(function(i){
						if(this.discussStatus== "0"){
							div+="<tr><td align='center'>"+this.tncStdVal+"</td><td align='center'>"+this.raiseDateShow+"</td><td align='center'>"+this.fixDateShow+"</td><td width='10px'><a class='argTncMarJan' keyId='"+this.keyId+"' tncStdItemId='"+this.tncStdItemId+"' fixDate='"+this.fixDateShow+"' raiseDate='"+this.raiseDateShow+"' needId="+value+" title='结案日' href='javascript:void(0);' col='10'><img border='0px' src='"+Main.contextPath+"/static/core/images/action/jiean.png' style='width:13px;height:13px'></a><a class='argTncMarJin' keyId='"+this.keyId+"' tncStdItemId='"+this.tncStdItemId+"' needId="+value+" title='禁止' href='javascript:void(0);' col='10'><img border='0px' src='"+Main.contextPath+"/static/core/images/action/jinzhizhunren.png' style='width:13px;height:13px'></a></td></tr>";
						}else{
							div+="<tr><td align='center'>"+this.tncStdVal+"</td><td align='center'>"+this.raiseDateShow+"</td><td align='center'>"+this.fixDateShow+"</td><td width='10px'></td></tr>";
						}
					});

					$(data.listMarJin).each(function(i){
						divJin+="<tr><td align='center'>"+this.tncStdVal+"</td><td align='center'>"+this.fixDateShow+"</td></tr>";
					});
					$(divId).html(div);
					$(divIdJin).html(divJin);
					PD141149.marAdd();
				},{refreshHtml:false},function(){
					alert("禁用失败")
				});

			});
		});

		/*供应商结案日	*/
		$(".argTncProJan").click(function(){
			var keyId =  $(this).attr("keyId");
			var fixDate =  $(this).attr("fixDate");
			var raiseDate =  $(this).attr("raiseDate");
			var needId =  $(this).attr("needId");
			var getDivId="divArgProNeed"+needId;
			var tncStdItemIdAdd =  $(this).attr("tncStdItemId");
			$.pdialog.open("设置市场需求结案日", Main.contextPath + "/pd14114902/init", {
				width: 320,
				height: 180
			}, {
				keyId:keyId,
				proFixDateShow:fixDate,
				proRaiseDateShow:raiseDate,
				standardId:PD141149.standardId,
				tncStdItemId:tncStdItemIdAdd,
				getDivName:getDivId
			});
		});
		/*禁用*/
		$(".argTncProJin").click(function(){
			var keyId =  $(this).attr("keyId");
			var tncStdItemIdAdd =  $(this).attr("tncStdItemId");
			var needId =  $(this).attr("needId");
			var getDivId="divArgProNeed"+needId;
			var getDivIdProNo="divArgProNoNeed"+needId;
			$.alertMessage.confirm("你确定要设为禁用数据吗？", function() {
				$.alertMessage.close();
				$('#main-content').postUrl(Main.contextPath + "/pd14114902/forbid",{keyId:keyId,getDivName:getDivId,
					standardId:PD141149.standardId,tncStdItemId:tncStdItemIdAdd,getNoProName:getDivIdProNo},function(data){
					var divId="#"+getDivId;
					var div="<div style='margin-left:10px;margin-right:0px;width:350px' id='divArgProNeed"+needId+"' >";
					div+="<table class='tree dataTable no-footer' style='min-width:250px' width='250px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>供应商编码</td><td align='center'>内容</td><td align='center'>提出日</td><td align='center'>结案日</td><td align='center'>操作</td></tr>";
					$(data.pdTncProList).each(function(i){
						if(this.discussStatus== "0"){
							div+="<tr><td align='center'>"+this.slPdId+"</td><td align='center'>"+this.tncStdVal+"</td><td align='center'>"+this.proRaiseDateShow+"</td><td align='center'>"+this.proFixDateShow+"</td><td width='10px'><a class='argTncProJan' keyId='"+this.keyId+"' fixDate='"+this.proFixDateShow+"' raiseDate='"+this.proRaiseDateShow+"' tncStdItemId='"+this.tncStdItemId+"' needId="+needId+" title='结案日' href='javascript:void(0);' col='10'><img border='0px' src='"+Main.contextPath+"/static/core/images/action/jiean.png' style='width:13px;height:13px'><a class='argTncProJin' keyId='"+this.keyId+"' tncStdItemId='"+this.tncStdItemId+"' needId="+needId+" title='禁止' href='javascript:void(0);' col='10'><img border='0px' src='"+Main.contextPath+"/static/core/images/action/jinzhizhunren.png' style='width:13px;height:13px'></a></a></td></tr>";
						}else{
							div+="<tr><td align='center'>"+this.slPdId+"</td><td align='center'>"+this.tncStdVal+"</td><td align='center'>"+this.proRaiseDateShow+"</td><td align='center'>"+this.proFixDateShow+"</td><td width='10px'></td></tr>";
						}
					});

					var divNoPro= "#"+getDivIdProNo;
					var divNo ="<div style='margin-left:20px;margin-right:0px;width:260px' id='divArgProNoNeed"+needId+"'>";
					divNo+="<table class='tree dataTable no-footer' style='min-width:250px' width='250px' showAddBtn='true'>";
					divNo+=" <tr style='background:#DBDBDB'><td align='center'>生产商编码</td><td align='center'>内容</td><td align='center'>禁止准入日</td></tr>";
					$(data.pdTncProNoList).each(function(i){
							divNo+="<tr><td align='center'>"+this.slPdId+"</td><td align='center'>"+this.tncStdVal+"</td><td align='center'>"+this.proFixDateShow+"</td></tr>";
					});
					$(divId).html(div);
					$(divNoPro).html(divNo);
					PD141149.marAdd();
				},{refreshHtml:false},function(){
					alert("禁用失败")
				});

			});
		});

	}
	}

$(document).ready(function() {
	// 初始化调用
	PD141149.init();
});
