/**
 * 产品基础数据列表JS
 *
 * @author xhy
 */
var PD14114902 = {
	saveButtonId: "PD14114902_SAVE",
	formId: "PD14114902Form",
	init : function() {
		this.bindDatePicber('#proFixDateShow');
		this.bindSaveButton();
		/**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
		this.closeDate();
		/**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
	},
	bindDatePicber: function (orderTimeId) {
		$(orderTimeId).datepicker({
			showButtonPanel: true,
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			changeYear: true,
			/**Add: 横展开添加日期清除按钮 2016/09/28   BY  任强  Start */
			closeText:'Clear'
			/**Add: 横展开添加日期清除按钮 2016/09/28   BY  任强  End */
		});
	},
	/**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
	closeDate : function(){
		$(document).on("click","button.ui-datepicker-close",function(){
			$.datepicker._clearDate($.datepicker._curInst.input);
		});
	},
	/**Add: 横展开添加日期清除按钮 2016/09/End   BY  任强  Start */
	bindSaveButton: function () {
		$("#" + PD14114902.saveButtonId).click(function () {
			var validator = mainValidation($("#" + PD14114902.formId));
			var isValid = validator.form();
			formDatas = getFormData($("#" + PD14114902.formId));
			/*结束日期*/
			if(formDatas.keyId!=null){
				var endTime = formDatas.proFixDateString;
				if(/^\s*$/.test(endTime)){
					$.alertMessage.info("结束日期不能为空!");
					return false;
				}

				var startTime = $("#proRaiseDateShow").val();
				var start=new Date(startTime.replace("-", "/").replace("-", "/"));
				var end=new Date(endTime.replace("-", "/").replace("-", "/"));
				if(start>=end){
					$.alertMessage.info("开始日期不能大于等于结束时间!");
					return false;
				}
			}
			if (isValid) {
				$.alertMessage.confirm("你确定要保存当前数据吗？", function () {
					$.alertMessage.close();
					formData = getFormData($("#" + PD14114902.formId));
					$('#main-content').postUrl(
						$("#" + PD14114902.formId).attr("action"),
						formData,
						function(data) {
							$.pdialog.close();
							var divId="#"+data[0].getDivName;
							var value = parseInt(divId.replace(/[^0-9]/ig,""));
							var div="<div margin-right:0px;width:350px' id='divArgProNeed"+value+"' >";
							div+="<table class='tree dataTable no-footer' style='min-width:250px' width='250px' showAddBtn='true'>";
							div+="<tr style='background:#DBDBDB'><td align='center'>供应商编码</td><td align='center'>内容</td><td align='center'>提出日</td><td align='center'>结案日</td><td align='center'>操作</td></tr>";
							$(data).each(function(i){
								if(this.discussStatus== "0"){
									div+="<tr><td align='center'>"+this.slPdId+"</td><td align='center'>"+this.tncStdVal+"</td><td align='center'>"+this.proRaiseDateShow+"</td><td align='center'>"+this.proFixDateShow+"</td><td width='10px'><a class='argTncProJan' keyId='"+this.keyId+"' fixDate='"+this.proFixDateShow+"' raiseDate='"+this.proRaiseDateShow+"' tncStdItemId='"+this.tncStdItemId+"' needId="+value+" title='结案日' href='javascript:void(0);' col='10'><img border='0px' src='"+Main.contextPath+"/static/core/images/action/jiean.png' style='width:13px;height:13px'><a class='argTncProJin' keyId='"+this.keyId+"' tncStdItemId='"+this.tncStdItemId+"' needId="+value+" title='禁止' href='javascript:void(0);' col='10'><img border='0px' src='"+Main.contextPath+"/static/core/images/action/jinzhizhunren.png' style='width:13px;height:13px'></a></a></td></tr>";
								}else{
									div+="<tr><td align='center'>"+this.slPdId+"</td><td align='center'>"+this.tncStdVal+"</td><td align='center'>"+this.proRaiseDateShow+"</td><td align='center'>"+this.proFixDateShow+"</td><td width='10px'></td></tr>";
								}
							});
							$(divId).html(div);
							PD141149.marAdd();
						},{refreshHtml:false});
				});
			}
		});
	},
}
$(document).ready(function() {
	// 初始化调用
	PD14114902.init();
});
