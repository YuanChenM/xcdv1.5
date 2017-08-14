/**
 * 卖家产品信息及状态审核JS
 *
 * @author gyh
 */
var $List_Grid;
var SL241127 = {
	formId: 'SL241127Form',
	init: function () {
		$List_Grid = $('#SL241127_list_grid').grid({
			fnRowCallback: SL241127.rowCallback,
			actionHandler: SL241127.actionHandler
		});
	},

	rowCallback: function (tr, data) {
		var $td = $(tr).children('td').eq(7);
		$td.html("");
		//卫生标准定级
		if (data.status == '1') {
			$td.html("<select id='check" + data.slPdId + "'><option value=''></option><option value='1' selected='selected'>申请中</option><option value='2'>论证中</option><option value='3'>禁止准入</option><option value='4'>试销</option><option value='5'>正式上线</option><option value='6'>下线</option><option value='7'>黑名单</option></select>");
		} else if (data.status == '2') {
			$td.html("<select id='check" + data.slPdId + "'><option value=''></option><option value='1'>申请中</option><option value='2' selected='selected'>论证中</option><option value='3'>禁止准入</option><option value='4'>试销</option><option value='5'>正式上线</option><option value='6'>下线</option><option value='7'>黑名单</option></select>");
		} else if (data.status == '3') {
			$td.html("<select id='check" + data.slPdId + "'><option value=''></option><option value='1'>申请中</option><option value='2'>论证中</option><option value='3' selected='selected'>禁止准入</option><option value='4'>试销</option><option value='5'>正式上线</option><option value='6'>下线</option><option value='7'>黑名单</option></select>");
		} else if (data.status == '4') {
			$td.html("<select id='check" + data.slPdId + "'><option value=''></option><option value='1'>申请中</option><option value='2'>论证中</option><option value='3'>禁止准入</option><option value='4' selected='selected'>试销</option><option value='5'>正式上线</option><option value='6'>下线</option><option value='7'>黑名单</option></select>");
		}else if (data.status == '5') {
			$td.html("<select id='check" + data.slPdId + "'><option value=''></option><option value='1'>申请中</option><option value='2'>论证中</option><option value='3'>禁止准入</option><option value='4'>试销</option><option value='5' selected='selected'>正式上线</option><option value='6'>下线</option><option value='7'>黑名单</option></select>");
		}else if (data.status == '6') {
			$td.html("<select id='check" + data.slPdId + "'><option value=''></option><option value='1'>申请中</option><option value='2'>论证中</option><option value='3'>禁止准入</option><option value='4'>试销</option><option value='5'>正式上线</option><option value='6' selected='selected'>下线</option><option value='7'>黑名单</option></select>");
		}else if (data.status == '7') {
			$td.html("<select id='check" + data.slPdId + "'><option value=''></option><option value='1'>申请中</option><option value='2'>论证中</option><option value='3'>禁止准入</option><option value='4'>试销</option><option value='5'>正式上线</option><option value='6'>下线</option><option value='7' selected='selected'>黑名单</option></select>");
		}else{
			$td.html("<select id='check" + data.slPdId + "'><option value='' selected='selected'></option><option value='1'>申请中</option><option value='2'>论证中</option><option value='3'>禁止准入</option><option value='4'>试销</option><option value='5'>正式上线</option><option value='6'>下线</option><option value='7'>黑名单</option></select>");
		}
	},
	actionHandler: function (rowdata, coltype, row, col) {
		/** 操作按钮 */
		//更改产品状态
		if (coltype == 'save') {
			rowdata.status=$('#check'+rowdata.slPdId).val();
			$('#main-content').postUrl(Main.contextPath + "/SL241116/upSlPdStatus", rowdata,function(data){
				if(data!='1'){
					$.alertMessage.info("状态更新失败,"+data);
					return;
				}
				$.alertMessage.info("状态更新成功!");
				$List_Grid.fnDraw();
			},{refreshHtml:false});
		}
		//包装
		if (coltype == 'detail') {
			$('#main-content').postUrl(Main.contextPath + "/SL241119/init", rowdata);
		}
		//技术
		if (coltype == 'check') {
			rowdata.slShowName = slShowName;
			$('#main-content').postUrl(Main.contextPath + "/SL241118/init", rowdata);
		}
		//卫生
		if (coltype == 'edit') {
			rowdata.slShowName = slShowName;
			$('#main-content').postUrl(Main.contextPath + "/SL241117/init", rowdata);
		}
		//其他
		if (coltype == 'audit') {
			Main.detailWindow(Main.contextPath + "/SL241122/init/2", rowdata, "产品标准档案卡");
			//$('#main-content').postUrl(Main.contextPath + "/SL241122/init/2", rowdata);
			//$.pdialog.open("产品标准", Main.contextPath + "/SL241122/init/2", {
			//	width: 800,
			//	height: 600
			//}, rowdata);
		}
		//产品品种图片
		if (coltype == 'search') {
			//if(rowdata.qltAuditDate) {
			//	var qltAuditDate = new Date(rowdata.qltAuditDate);
			//	rowdata.qltAuditDate = qltAuditDate.format('yyyy-MM-dd hh:mm:ss');
			//}
			//if(rowdata.qltMonitorDate) {
			//	var qltMonitorDate = new Date(rowdata.qltMonitorDate);
			//	rowdata.qltMonitorDate = qltMonitorDate.format('yyyy-MM-dd hh:mm:ss');
			//}
			//if(rowdata.tncAuditDate) {
			//	var tncAuditDate = new Date(rowdata.tncAuditDate);
			//	rowdata.tncAuditDate = tncAuditDate.format('yyyy-MM-dd hh:mm:ss');
			//}
			//if(rowdata.tncMonitorDate) {
			//	var tncMonitorDate = new Date(rowdata.tncMonitorDate);
			//	rowdata.tncMonitorDate = tncMonitorDate.format('yyyy-MM-dd hh:mm:ss');
			//}
			//rowdata.slShowName = slShowName;

			// zhang_chi  20160701 meger代码
			var imageDate={};
			//imageDate.slShowName = slShowName;
			imageDate.prodEpId=rowdata.prodEpId;
			imageDate.pdClassesCode=rowdata.pdClassesCode;
			imageDate.machiningCode=rowdata.machiningCode;
			imageDate.pdBreedCode=rowdata.pdBreedCode;
			$.pdialog.open(rowdata.pdClassesName + rowdata.pdBreedName + "图片信息", Main.contextPath + "/SL241116/showImage", {
				width: 700,
				height: 500
			}, imageDate);
		}
	}
}
$(document).ready(function () {
	// 初始化调用
	SL241127.init();
});

