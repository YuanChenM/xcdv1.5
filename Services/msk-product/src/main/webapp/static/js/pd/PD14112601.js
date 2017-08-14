/**
 * 产品基础数据列表JS
 *
 * @author pxg
 */
var PD14112601 = {
	search:"PD14112601_search",
	searchDataId:"PD14112601SearchData",
	divId:null,
	classestreeCode:null,
	init : function() {
		PD14112601.clickForData();
		PD14112601.editOrAdd();
		$(".group-accordion").accordion({ heightStyle: "'auto'" });
		$('.classTree').treegrid();
},
	clickForData:function(){
		$("a[name='marketRegisterName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			PD14112601.classestreeCode=$(this).attr("value");
			var url=$("#marketRegisterDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#pd14112601accordion").accordion({ heightStyle: "fill" });
				$("#pd14112601accordion").accordion("refresh");
				$("#marketRegisterImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#marketRegisterDiv"+value).css("display","block");
			}else{
				$("#marketRegisterImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#marketRegisterDiv"+value).css("display","none");
			}
		});
		$("a[name='noMarketRegisterName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#noMarketRegisterDiv"+value).css("display");
			PD14112601.classestreeCode=$(this).attr("value");
			PD14112601.divId="noMarketRegisterDiv"+value;
			if(url.indexOf("none")!=-1){
				$("#pd14112601accordion").accordion({ heightStyle: "fill" });
				$("#pd14112601accordion").accordion("refresh");
				$("#noMarketRegisterImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#noMarketRegisterDiv"+value).css("display","block");
			}else{
				$("#noMarketRegisterImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#noMarketRegisterDiv"+value).css("display","none");
			}
		});
		$("a[name='sellerName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#sellerDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#sellerImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#sellerDiv"+value).css("display","block");
			}else{
				$("#sellerImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#sellerDiv"+value).css("display","none");
			}
		});
		$("a[name='sellerLineName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#sellerLineDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#sellerLineImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#sellerLineDiv"+value).css("display","block");
			}else{
				$("#sellerLineImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#sellerLineDiv"+value).css("display","none");
			}
		});
		$("a[name='oneLineName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#oneLineDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#oneLineImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#oneLineDiv"+value).css("display","block");
			}else{
				$("#oneLineImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#oneLineDiv"+value).css("display","none");
			}
		});
		$("a[name='contentName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#contentDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#contentImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#contentDiv"+value).css("display","block");
			}else{
				$("#contentImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#contentDiv"+value).css("display","none");
			}
		});
		$("a[name='oneLineOemName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#oneLineOemDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#oneLineOemImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#oneLineOemDiv"+value).css("display","block");
			}else{
				$("#oneLineOemImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#oneLineOemDiv"+value).css("display","none");
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
		$("a[name='packingName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#packingDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#packingImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#packingDiv"+value).css("display","block");
			}else{
				$("#packingImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#packingDiv"+value).css("display","none");
			}
		});
		$("a[name='tcForbidName']").click(function(){
			var value=$(this).attr("class").match(/\d+/g).toString();
			var url=$("#tcForbidDiv"+value).css("display");
			if(url.indexOf("none")!=-1){
				$("#tcForbidImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_close.png");
				$("#tcForbidDiv"+value).css("display","block");
			}else{
				$("#tcForbidImg"+value).attr('src',Main.contextPath +"/static/core/images/action/details_open.png");
				$("#tcForbidDiv"+value).css("display","none");
			}
		});
	},
	editOrAdd:function(){
		$(".cellButtonaddTwo").click(function(){
			var classestreeCode=$(this).attr("addvalue");
			var getDivId="noMarketRegisterDiv"+classestreeCode;
			$.pdialog.open("新增市场需求审核注册值", Main.contextPath + "/PD141128/init", {
				width: 320,
				height: 180
			},
				{
					classestreeCode:classestreeCode,
					getDivId:getDivId
				}
			);
		});
		$(".cellButtoneditTwo").click(function(){
			var updateValue=$(this).attr("updateValue");
			var updateCode=$(this).attr("updateCode");
			var getDivId="#noMarketRegisterDiv"+updateCode;
				$('#main-content').postUrl(Main.contextPath+"/PD14112601/updateData",{getDivId:getDivId,classestreeCode:updateCode,updateValue:updateValue},function(data){
						var div="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
						div+="<tr style='background:#DBDBDB'><td align='center'>在线处理</td><td align='center'>操作<a class='cellButtonaddTwo' title='添加' addValue='"+updateCode+"' href='javascript:void(0);' col='10' ><img src='"+Main.contextPath+"/static/core/images/action/add.png' style='width:13px;height:13px'></a></td></tr>";
					$(data).each(function(i){
						if(this.featureFlg==0){
							div+="<tr><td align='center'>"+this.featureName+"</td><td width='10px'> <a class='cellButtoneditTwo' title='修改' updateCode='"+updateCode+"' updateValue='"+this.tcOnlineId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/zhuce.png' style='width:13px;height:13px'></a> <a class='cellButtondeleteTwo' title='删除' deleteCode='"+updateCode+"'  deleteValue='"+this.tcOnlineId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/delete.png' style='width:13px;height:13px'></a> </td></tr>";
						}
					});
					$(getDivId).html(div);
					var divId2=getDivId.replace("#noM","");
					var div2="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
					div2+="<tr style='background:#DBDBDB'><td align='center'>已注册</td></tr>";
					$(data).each(function(i){
						if(this.featureFlg==1){
							div2+="<tr><td align='center'>"+this.featureName+"</td></tr>";
						}
					});
					$("#m"+divId2).html(div2);
					PD14112601.editOrAdd();
				},{refreshHtml:false});
		});
		$(".cellButtondeleteTwo").click(function(){
			var deleteValue=$(this).attr("deleteValue");
			var deleteCode=$(this).attr("deleteCode");
			$.alertMessage.confirm("你确定要删除当前数据吗？", function() {
				$.alertMessage.close();
				var getDivId="noMarketRegisterDiv"+deleteCode;
				$('#main-content').postUrl(Main.contextPath+"/PD14112601/deleteData",{getDivId:getDivId,classestreeCode:deleteCode,deleteValue:deleteValue},function(data){
					var div="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>在线处理</td><td align='center'>操作<a class='cellButtonaddTwo' id='add' title='添加' addValue='"+deleteCode+"' href='javascript:void(0);' col='10' ><img src='"+Main.contextPath+"/static/core/images/action/add.png' style='width:13px;height:13px'></a></td></tr>";
					$(data).each(function(i){
						if(this.featureFlg==0){
							div+="<tr><td align='center'>"+this.featureName+"</td><td width='10px'> <a class='cellButtoneditTwo' title='修改' updateCode='"+deleteCode+"' updateValue='"+this.tcOnlineId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/zhuce.png' style='width:13px;height:13px'></a> <a class='cellButtondeleteTwo' title='删除' deleteCode='"+deleteCode+"' deleteValue='"+this.tcOnlineId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/delete.png' style='width:13px;height:13px'></a> </td></tr>";
						}
					});
					$("#"+getDivId).html(div);
					PD14112601.editOrAdd();
				},{refreshHtml:false});
			});
		});
	    $(".buttondeleteTwo").click(function(){
			var deleteId=$(this).attr("deleteId");
			var deleteClassCode=$(this).attr("deleteClassCode");
			var divLineId="sellerLineDiv"+deleteClassCode;
			$.alertMessage.confirm("你确定要删除当前数据吗？", function() {
				$.alertMessage.close();
				$('#main-content').postUrl(Main.contextPath+"/PD14112601/deleteProvider",{deleteId:deleteId,deleteClassCode:deleteClassCode},function(data){
					var div="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>在线处理</td><td align='center'>操作</td></tr>";
					$(data).each(function(i){
						if(this.featureFlg==0){
							div+="<tr><td align='center'>"+this.featureName+"</td><td width='10px'> <a class='buttoneditTwo' title='修改' editClassCode='"+deleteClassCode+"' editId='"+this.tcProviderId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/zhuce.png' style='width:13px;height:13px'></a> <a class='buttondeleteTwo' title='删除' deleteClassCode='"+deleteClassCode+"' deleteId='"+this.tcProviderId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/delete.png' style='width:13px;height:13px'></a> </td></tr>";
						}
					});
					$("#"+divLineId).html(div);
					PD14112601.editOrAdd();
				},{refreshHtml:false});
			});
		});

		$(".buttoneditTwo").click(function(){
			var editId=$(this).attr("editId");
			var editClassCode=$(this).attr("editClassCode");
			var getDivId="#sellerLineDiv"+editClassCode;
			$('#main-content').postUrl(Main.contextPath+"/PD14112601/updateProvider",{editId:editId,editClassCode:editClassCode},function(data){
				var div="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
				div+="<tr style='background:#DBDBDB'><td align='center'>在线处理</td><td align='center'>操作</td></tr>";
				$(data).each(function(i){
					if(this.featureFlg==0){
						div+="<tr><td align='center'>"+this.featureName+"</td><td width='10px'> <a class='buttoneditTwo' title='修改' editClassCode='"+editClassCode+"' editId='"+this.tcProviderId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/zhuce.png' style='width:13px;height:13px'></a> <a class='buttondeleteTwo' title='删除' deleteClassCode='"+editClassCode+"' deleteId='"+this.tcProviderId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/delete.png' style='width:13px;height:13px'></a> </td></tr>";
					}
				});
				$(getDivId).html(div);
				var divId2=getDivId.replace("Line","");
				var div2="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
				div2+="<tr style='background:#DBDBDB'><td align='center'>已注册</td></tr>";
				$(data).each(function(i){
					if(this.featureFlg==1){
						div2+="<tr><td align='center'>"+this.featureName+"</td></tr>";
					}
				});
				$(divId2).html(div2);
				PD14112601.editOrAdd();
			},{refreshHtml:false});
		});

		$(".oneLinebuttonadd").click(function(){
			var classestreeCode=$(this).attr("addOnLinevalue");
			var getDivId="oneLineDiv"+classestreeCode;
			$.pdialog.open("新增正式上线值", Main.contextPath + "/PD141127/init", {
					width: 320,
					height: 180
				},
				{
					classestreeCode:classestreeCode,
					getDivId:getDivId
				}
			);
		});

		$(".oneLinebuttondelete").click(function(){
			var onLinedeleteId=$(this).attr("onLinedeleteId");
			var onLinedeleteClassCode=$(this).attr("onLinedeleteClassCode");
			var divOnLineId="oneLineDiv"+onLinedeleteClassCode;
			$.alertMessage.confirm("你确定要删除当前数据吗？", function() {
				$.alertMessage.close();
				$('#main-content').postUrl(Main.contextPath+"/PD14112601/deleteOnLine",{onLinedeleteId:onLinedeleteId,onLinedeleteClassCode:onLinedeleteClassCode},function(data){
					var div="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>正式上线</td><td align='center'>操作<a class='oneLinebuttonadd' title='添加' addOnLinevalue='"+onLinedeleteClassCode+"' href='javascript:void(0);' col='10' ><img src='"+Main.contextPath+"/static/core/images/action/add.png' style='width:13px;height:13px'></a></td></tr>";
					$(data).each(function(i){
						div+="<tr><td align='center'>"+this.featureName+"</td><td width='10px'> <a class='oneLinebuttondelete' title='删除' onLinedeleteClassCode='"+onLinedeleteClassCode+"' onLinedeleteId='"+this.tcOnlineId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/delete.png' style='width:13px;height:13px'></a> </td></tr>";
					});
					$("#"+divOnLineId).html(div);
					PD14112601.editOrAdd();
				},{refreshHtml:false});
			});
		});
		$(".buttonContentAdd").click(function(){
			var classestreeCode=$(this).attr("addvalue");
			var getDivId="contentDiv"+classestreeCode;
			$.pdialog.open("新增标准目录值", Main.contextPath + "/PD14112801/init", {
					width: 320,
					height: 180
				},
				{
					classestreeCode:classestreeCode,
					getDivId:getDivId
				}
			);
		});

		$(".buttonContentDelete").click(function(){
			var contentDeleteId=$(this).attr("contentDeleteId");
			var contentDeleteClassCode=$(this).attr("contentDeleteClassCode");
			var divcontentId="contentDiv"+contentDeleteClassCode;
			$.alertMessage.confirm("你确定要删除当前数据吗？", function() {
				$.alertMessage.close();
				$('#main-content').postUrl(Main.contextPath+"/PD14112601/deleteProviderContent",{contentDeleteId:contentDeleteId,contentDeleteClassCode:contentDeleteClassCode},function(data){
					var div="<table class='tree dataTable no-footer' style='min-width:250px' width='250px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>标准目录</td> <td align='center'>产品销售对象</td> <td align='center'>产品加工方向</td> <td align='center'>操作<a class='buttonContentAdd' addvalue='"+contentDeleteClassCode+"' title='添加' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/add.png' style='width:13px;height:13px'></a></td></tr>";
					$(data).each(function(i){
						div+="<tr><td align='center'>"+this.featureName+"</td><td align='center'>"+this.salesTarget+"</td><td align='center'>"+this.machiningWay+"</td><td width='10px'> <a class='buttonContentDelete' title='删除' contentDeleteClassCode='"+contentDeleteClassCode+"' contentDeleteId='"+this.tcContentId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/delete.png' style='width:13px;height:13px'></a> </td></tr>";
					});
					$("#"+divcontentId).html(div);
					PD14112601.editOrAdd();
				},{refreshHtml:false});
			});
		});

		$(".oneLineOembuttonAdd").click(function(){
			var classestreeCode=$(this).attr("oneLineOemaddCode");
			var getDivId="oneLineOemDiv"+classestreeCode;
			$.pdialog.open("新增OEM正式上线值", Main.contextPath + "/PD14112701/init", {
					width: 320,
					height: 180
				},
				{
					classestreeCode:classestreeCode,
					getDivId:getDivId
				}
			);
		});

		$(".oneLineOembuttonDelete").click(function(){
			var oneLineOemdeleteId=$(this).attr("oneLineOemdeleteId");
			var oneLineOemdeleteClassCode=$(this).attr("oneLineOemdeleteClassCode");
			var divOneLineOemId="oneLineOemDiv"+oneLineOemdeleteClassCode;
			$.alertMessage.confirm("你确定要删除当前数据吗？", function() {
				$.alertMessage.close();
				$('#main-content').postUrl(Main.contextPath+"/PD14112601/deleteProviderOem",{oneLineOemdeleteId:oneLineOemdeleteId,oneLineOemdeleteClassCode:oneLineOemdeleteClassCode},function(data){
					var div="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>OEM正式上线值</td><td align='center'>操作<a class='oneLineOembuttonAdd' title='添加' oneLineOemaddCode='"+oneLineOemdeleteClassCode+"' href='javascript:void(0);' col='10' ><img src='"+Main.contextPath+"/static/core/images/action/add.png' style='width:13px;height:13px'></a></td></tr>";
					$(data).each(function(i){
						div+="<tr><td align='center'>"+this.featureName+"</td><td width='10px'> <a class='oneLineOembuttonDelete' title='删除' oneLineOemdeleteId='"+this.tcOemId+"' oneLineOemdeleteClassCode='"+oneLineOemdeleteClassCode+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/delete.png' style='width:13px;height:13px'></a> </td></tr>";
					});
					$("#"+divOneLineOemId).html(div);
					PD14112601.editOrAdd();
				},{refreshHtml:false});
			});
		});
		$(".tcForBidButtondelete").click(function(){
			var tcForBidDeleteValue=$(this).attr("tcForBidDeleteValue");
			var tcForBidDeleteCode=$(this).attr("tcForBidDeleteCode");
			var divLineId="tcForbidDiv"+tcForBidDeleteCode;
			$.alertMessage.confirm("你确定要删除当前数据吗？", function() {
				$.alertMessage.close();
				$('#main-content').postUrl(Main.contextPath+"/PD14112601/deleteTcForbid",{deleteId:tcForBidDeleteValue,deleteClassCode:tcForBidDeleteCode},function(data){
					var div="<table class='tree dataTable no-footer' style='min-width:150px' width='150px' showAddBtn='true'>";
					div+="<tr style='background:#DBDBDB'><td align='center'>禁止准入产品</td><td align='center'>操作<a class='tcForBidButtonadd' title='添加' tcForBidaddCode='"+tcForBidDeleteCode+"' href='javascript:void(0);' col='10' ><img src='"+Main.contextPath+"/static/core/images/action/add.png' style='width:13px;height:13px'></a></td></tr>";
					$(data).each(function(i){
						div+="<tr><td align='center'>"+this.featureName+"</td><td><a class='tcForBidButtondelete' title='删除' tcForBidDeleteCode='"+tcForBidDeleteCode+"' tcForBidDeleteValue='"+this.tcForbidId+"' href='javascript:void(0);' col='10'><img src='"+Main.contextPath+"/static/core/images/action/delete.png' style='width:13px;height:13px'></a> </td></tr>";
					});
					$("#"+divLineId).html(div);
					PD14112601.editOrAdd();
				},{refreshHtml:false});
			});
		});
		$(".tcForBidButtonadd").click(function(){
			var classestreeCode=$(this).attr("tcForBidaddCode");
			var getDivId="tcForbidDiv"+classestreeCode;
			$.pdialog.open("新增禁止准入产品", Main.contextPath + "/PD141129/init", {
					width: 320,
					height: 180
				},
				{
					classestreeCode:classestreeCode,
					getDivId:getDivId
				}
			);
		});
	}
}
$(document).ready(function() {
	// 初始化调用
	PD14112601.init();
});
