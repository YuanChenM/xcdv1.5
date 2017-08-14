/**
 * 产品品种维护JS
 * 
 * @author gyh
 */
var PD14112407 = {
	formId : "PD14112407Form",
	saveButtonId : "PD14112407_SAVE",
	classesCode : '',
	machiningCode : '',
	breedCode : '',
	featureCode : '',
	weightCode : '',
	init : function() {
		//this.initSelect();
		classesCode=PD14112406.classestreeCode1;
		machiningCode=PD14112406.classestreeCode2;
		breedCode =PD14112406.classestreeCode3;
		featureCode=PD14112406.classestreeCode4;
		weightCode=PD14112406.classestreeCode5;
		this.selectChange();
		this.bindSavebutton();
		this.changeCheckBox();
		this.changeCheckBox2();
		this.changeCheckBox3();
	},
	bindSavebutton : function() {
		$("#" + PD14112407.saveButtonId).click(function() {
			PD14112407.saveData();
		});
		$("#" + PD14112407.backButtonId).click(function() {
			$.pdialog.close();
		});
	},
	changeCheckBox: function () {
		$(".codeName").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(".onlyName").removeAttr("checked");
				$('#normsCode').removeAttr('disabled');
				$('#normsOut').removeAttr('disabled');
				$('#onlyName').prop('disabled','disabled');
				$('#onlyName').val("");
			} else {
				//$(".onlyName").removeAttr("checked");
				//$('#' + ids).prop('disabled', 'disabled');
				//$('#normsCode').prop('disabled', 'disabled');
				$('#onlyName').val("");
				$('#normsCode').val("");
				$('#normsOut').val("");
				$('#onlyName').prop('disabled', 'disabled');
				$('#normsCode').prop('disabled', 'disabled');
				$('#normsOut').prop('disabled', 'disabled');
				//$('#normsOut').prop('disabled', 'disabled');
			}
		});
		$(".selectWeight").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(".classestreeName5").removeAttr("checked");
				$(".copyCode").removeAttr("checked");
				//$(":checkbox").not($(this)).removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#classestreeName5').prop('disabled', 'disabled');
				$('#classestreeName5').val("");
				$('#copyCodeId').val("");
				$('#copyCodeName').val("");
				$('#copyCodeVal').val("");
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeVal').prop('disabled', 'disabled');
			} else {
				//$('#' + ids).prop('disabled', 'disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeVal').prop('disabled', 'disabled');
				$("#selectWeight option:first").prop("selected", 'selected');
				$("#selectWeight").prop("disabled", 'disabled');
				$('#classestreeName5').prop('disabled', 'disabled');
			}
		});
		$(".machiningSel").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(".classestreeName2").removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#classestreeName2').val("");
				$('#classestreeName2').prop('disabled', 'disabled');
			} else {
				//$(":checkbox").removeAttr("checked");
				//$('#' + ids).prop('disabled', 'disabled');
				//$("#selectWeight").prop("disabled", 'disabled');
				$("#machiningSel option:first").prop("selected", 'selected');
				$("#machiningSel").prop("disabled", 'disabled');
				$('#classestreeName2').prop('disabled', 'disabled');
			}
		});
		$(".breedSel").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(".classestreeName3").removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#classestreeName3').val("");
				$('#classestreeName3').prop('disabled', 'disabled');
			} else {
				//$(":checkbox").removeAttr("checked");
				//$('#' + ids).prop('disabled', 'disabled');
				$("#breedSel option:first").prop("selected", 'selected');
				$("#breedSel").prop("disabled", 'disabled');
				$('#classestreeName3').prop('disabled', 'disabled');
			}
		});
		$(".featureSel").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(".classestreeName4").removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#classestreeName4').val("");
				$('#classestreeName4').prop('disabled', 'disabled');
			} else {
				//$(":checkbox").removeAttr("checked");
				//$('#' + ids).prop('disabled', 'disabled');
				$("#featureSel option:first").prop("selected", 'selected');
				$("#featureSel").prop("disabled", 'disabled');
				$('#classestreeName4').prop('disabled', 'disabled');
			}
		});
	},
	changeCheckBox2: function () {
		$(".onlyName").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(".codeName").removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#normsCode').val("");
				$('#normsOut').val("");
				$('#normsCode').prop('disabled', 'disabled');
				//$('#onlyName').prop('disabled', 'disabled');
				$('#normsOut').prop('disabled', 'disabled');
			} else {
				//$(":checkbox").removeAttr("checked");
				//$('#' + ids).prop('disabled', 'disabled');
				$('#onlyName').val("");
				$('#normsCode').prop('disabled', 'disabled');
				$('#onlyName').prop('disabled', 'disabled');
				$('#normsOut').prop('disabled', 'disabled');
			}
		});
		$(".classestreeName2").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(".machiningSel").not($(this)).removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$("#machiningSel option:first").prop("selected", 'selected');
				$("#machiningSel").prop("disabled", 'disabled');
			} else {
				//$(":checkbox").removeAttr("checked");
				//$('#' + ids).prop('disabled', 'disabled');
				$("#machiningSel").prop("disabled", 'disabled');
				$('#classestreeName2').val("");
				$('#classestreeName2').prop('disabled', 'disabled');
			}
		});
		$(".classestreeName3").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(".breedSel").removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$("#breedSel option:first").prop("selected", 'selected');
				$("#breedSel").prop("disabled", 'disabled');
			} else {
				//$(":checkbox").removeAttr("checked");
				//$('#' + ids).prop('disabled', 'disabled');
				$("#breedSel").prop("disabled", 'disabled');
				$('#classestreeName3').val("");
				$('#classestreeName3').prop('disabled', 'disabled');
			}
		});
		$(".classestreeName4").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(".featureSel").not($(this)).removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$("#featureSel option:first").prop("selected", 'selected');
				$("#featureSel").prop("disabled", 'disabled');
			} else {
				//$(":checkbox").removeAttr("checked");
				//$('#' + ids).prop('disabled', 'disabled');
				$("#featureSel").prop("disabled", 'disabled');
				$('#classestreeName4').val("");
				$('#classestreeName4').prop('disabled', 'disabled');
			}
		});
		$(".classestreeName5").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(".selectWeight").removeAttr("checked");
				$(".copyCode").removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeVal').prop('disabled', 'disabled');
				$('#copyCodeId').val("");
				$('#copyCodeName').val("");
				$('#copyCodeVal').val("");
				$("#selectWeight option:first").prop("selected", 'selected');
				$("#selectWeight").prop("disabled", 'disabled');
			} else {
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeVal').prop('disabled', 'disabled');
				$('#copyCodeId').val("");
				$('#copyCodeName').val("");
				$('#copyCodeVal').val("");
				$("#selectWeight option:first").prop("selected", 'selected');
				$("#selectWeight").prop("disabled", 'disabled');
				$('#classestreeName5').val("");
				$('#classestreeName5').prop('disabled', 'disabled');
			}
		});
	},
	changeCheckBox3: function () {
		$(".copyCode").click(function () {
			if ($(this).prop("checked")) {
				$(".classestreeName5").removeAttr("checked");
				$(".selectWeight").removeAttr("checked");
				$('#copyCodeId').removeAttr('disabled');
				$('#copyCodeName').removeAttr('disabled');
				$('#copyCodeVal').removeAttr('disabled');
				//$('#copyCodeId').prop('disabled', 'disabled');
				//$('#copyCodeName').prop('disabled', 'disabled');
				$("#selectWeight option:first").prop("selected", 'selected');
				$('#classestreeName5').val("");
				$('#classestreeName5').prop('disabled', 'disabled');
			} else {
				//$(":checkbox").removeAttr("checked");
				//$('#' + ids).prop('disabled', 'disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeVal').prop('disabled', 'disabled');
				$('#copyCodeId').val("");
				$('#copyCodeName').val("");
				$('#copyCodeVal').val("");
				$("#selectWeight").prop("disabled", 'disabled');
				$('#classestreeName5').prop('disabled', 'disabled');
			}
		});
	},
	validateMachining:function(flag){
		var classes = $("#classesSel").val();
		var machiningSel = $("#machiningSel").val();
		var classestreeName2 = $("#classestreeName2").val();
		if(classes == ''){
			$.alertMessage.info("请选择一级类型!");
			return false;
		}
		if(machiningSel == '' && classestreeName2 == '' && !flag){
			$.alertMessage.info("请选择或填写二级类型!");
			return false;
		}
		/*else{
			if($(".classestreeName2").prop("checked") && !(/^[a-zA-z0-9\u4E00-\u9FA5]*$/.test($.trim(classestreeName2)))){
				$.alertMessage.info("二级分类只能输入字符,数字,字母!");
				return false;
			}
		}*/
		return true;
	},
	validateBreed:function(flag){
		var breedSel = $("#breedSel").val();
		var classestreeName3 = $("#classestreeName3").val();
		if(breedSel == '' && classestreeName3 == '' && !flag){
			$.alertMessage.info("请选择或填写品种!");
			return false;
		}
		/*else{
			if($(".classestreeName3").prop("checked") && !(/^[a-zA-z0-9\u4E00-\u9FA5]*$/.test($.trim(classestreeName3)))){
				$.alertMessage.info("品种只能输入字符,数字,字母!");
				return false;
			}
		}*/
		return true;
	},
	validateFeature:function(flag){
		var featureSel = $("#featureSel").val();
		var classestreeName4 = $("#classestreeName4").val();
		if(featureSel == '' && classestreeName4 == '' && !flag){
			$.alertMessage.info("请选择或填写特征!");
			return false;
		}
		/*else{
			if($(".classestreeName4").prop("checked") && !(/^[a-zA-z0-9\u4E00-\u9FA5]*$/.test($.trim(classestreeName4)))){
				$.alertMessage.info("特征分类只能输入字符,数字,字母!");
				return false;
			}
		}*/
		return true;
	},
	validateWeight:function(flag){
		var isNull = $("#classestreeName5").val();
		var selectValue = $("#selectWeight").val();
		var codeId = $("#copyCodeId").val();
		var codeVal = $("#copyCodeVal").val();
		var codeName = $("#copyCodeName").val();
		if ((isNull == null || isNull == '') && selectValue == '0' && (codeId == null || codeId == '') && (codeName == null || codeName == '') && !flag) {
			$.alertMessage.info("请选择净重类型!");
			return false;
		}
		if (isNull == null || isNull == '' && selectValue == '0') {
			if (codeId == null || codeId == '' && codeName == null || codeName == '') {
				$.alertMessage.info("新增抄码数据不能为空!");
				return false;
			} else if (!/^[0-9][1-9]{1}$/.test(codeId)) {
				$.alertMessage.info('抄码只能输入两位数值!');
				return false;
			}else if(codeVal!=null&&codeVal!=''){
				if (/[^0-9.]/g.test(codeVal)) {
					$.alertMessage.info('新增净重只能输入数值!');
					return false;
				}
			}
		}
		return true;
	},
	validateNorms : function(){
		if(!$(".codeName").prop("checked") && !$(".onlyName").prop("checked")){
			$.alertMessage.info("请选择录入方式!");
			return false;
		}else if ($(".codeName").prop("checked")) {
			var isNull1 = $("#normsOut").val();
			var normsCode = $("#normsCode").val();
			if(/^\s*$/.test(normsCode)){
				$.alertMessage.info("输入框值不能为空!");
				return false;
			}
			if(!/^[0-9][1-9]{1}$/.test(normsCode)){
				$.alertMessage.info('包装编码只能输入两位数值!');
				return false;
			}
			if(/^\s*$/.test(isNull1)){
				$.alertMessage.info("包装名称不能为空!");
				return false;
			}
		}else if($(".onlyName").prop("checked")){
			var onlyName = $("#onlyName").val();
			if(/^\s*$/.test(onlyName)){
				$.alertMessage.info("包装名称不能为空!");
				return false;
			}
		}
		return true;
	},
	saveData : function() {
		var validator = mainValidation($("#" + PD14112407.formId));
		var isValid = validator.form();
		var classes = $("#classesSel").val();
		var machiningSel = $("#machiningSel").val();
		var classestreeName2 = $("#classestreeName2").val();
		var breedSel = $("#breedSel").val();
		var classestreeName3 = $("#classestreeName3").val();
		var featureSel = $("#featureSel").val();
		var classestreeName4 = $("#classestreeName4").val();
		var isNull = $("#classestreeName5").val();
		var selectWeight = $("#selectWeight").val();
		var normsOut = $("#normsOut").val();
		var normsCode = $("#normsCode").val();
		var onlyName = $("#onlyName").val();
		var codeId = $("#copyCodeId").val();
		var copyCodeVal = $("#copyCodeVal").val();
		var copyCodeName = $("#copyCodeName").val();
		//净重
		formData = getFormData($("#" + PD14112407.formId));
		formData.saveType = null;
		if('' != classes && ('' != machiningSel || '' != classestreeName2)  && ('' != breedSel || '' != classestreeName3)
			&& ('' != featureSel || '' != classestreeName4) && (('0' != selectWeight || '' != isNull)
			|| ('' != codeId && '' != codeId && '' != codeId)) && (('0' != normsOut && '' != normsCode)
			|| ('' != onlyName))){
			if(this.validateNorms()){
				formData.saveType = 6;
				if ('' != machiningSel){
					formData.classestreeCode2 = machiningSel;
				}else{
					formData.classestreeName2 = classestreeName2;
				}
				if('' != breedSel){
					formData.classestreeCode3 = breedSel;
				}else{
					formData.classestreeName3 = classestreeName3;
				}
				if('' != featureSel){
					formData.classestreeCode4 = featureSel;
				}else{
					formData.classestreeName4 = classestreeName4;
				}
				if('0' != selectWeight){
					formData.classestreeCode5 = selectWeight;
				}else{
					formData.classestreeName5 = isNull;
				}
			}
		}
		else if ('' != isNull || '0' != selectWeight || ('' != codeId && '' != copyCodeVal && '' != copyCodeName)) {
			if(this.validateMachining(false) && this.validateBreed(false) && this.validateFeature(false) && this.validateWeight(true)){
				formData.saveType = 5;
				if ('' != machiningSel){
					formData.classestreeCode2 = machiningSel;
				}else{
					formData.classestreeName2 = classestreeName2;
				}
				if('' != breedSel){
					formData.classestreeCode3 = breedSel;
				}else{
					formData.classestreeName3 = classestreeName3;
				}
				if('' != featureSel){
					formData.classestreeCode4 = featureSel;
				}else{
					formData.classestreeName4 = classestreeName4;
				}
				formData.classestreeName5 = isNull;
			}
		}
		//特征
		else if('' != classestreeName4){
			if(this.validateMachining(false) && this.validateBreed(false) && this.validateFeature(true)){
				formData.saveType = 4;
				if ('' != machiningSel){
					formData.classestreeCode2 = machiningSel;
				}else{
					formData.classestreeName2 = classestreeName2;
				}
				if('' != breedSel){
					formData.classestreeCode3 = breedSel;
				}else{
					formData.classestreeName3 = classestreeName3;
				}
				formData.classestreeName4 = classestreeName4;

			}
		}
		//品种
		else if('' != classestreeName3){
			if(this.validateMachining(false) && this.validateBreed(true)){
				formData.saveType = 3;
				if ('' != machiningSel){
					formData.classestreeCode2 = machiningSel;
				}else{
					formData.classestreeName2 = classestreeName2;
				}

				formData.classestreeName3 = classestreeName3;

			}
		}
		//二级分类
		else if('' != classestreeName2){
			if(this.validateMachining(true)){
				formData.saveType = 2;
				formData.classestreeName2 = classestreeName2;
			}
		}else{
			$.alertMessage.info("请将产品信息填写完整后再进行保存!");
			return;
		}
		if (isValid && formData.saveType != null) {
			$.alertMessage.confirm("你确定要保存当前数据吗？", function() {
				$.alertMessage.close();
				$('#main-content').postUrl(
					$("#" + PD14112407.formId).attr("action"),
					formData,
					function(data) {
						if(data=='1'){
							PD14112406.initJsp();
							$.pdialog.close();
							$.alertMessage.info("数据操作成功!");
						}else{
							$.alertMessage.info("数据异常,请修改后添加!");
						}
						/** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  End */
					});

			});
		}
	},
	selectChange:function(){
		$("#classesSel").change(function(){
			$("#machiningSel").find("option").not(":first").remove();
			$("#breedSel").find("option").not(":first").remove();
			$("#featureSel").find("option").not(":first").remove();
			$("#selectWeight").find("option").not(":first").remove();
			var classestreeCode=$("#classesSel").val();
			var treeLevel="2";
			$('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
				$(data).each(function(i,val){
					$("#machiningSel").append($('<option>',{value:val.levelCode}).text(val.levelName));
				});
			},{refreshHtml:false});
		});
	$("#machiningSel").change(function(){
		$("#breedSel").find("option").not(":first").remove();
		$("#featureSel").find("option").not(":first").remove();
		$("#selectWeight").find("option").not(":first").remove();
		var classestreeCode=$("#classesSel").val() + $("#machiningSel").val();
		var treeLevel="3";
		$('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
			$(data).each(function(i,val){
				$("#breedSel").append($('<option>',{value:val.levelCode}).text(val.levelName));
			});
		},{refreshHtml:false});
	});
	$("#breedSel").change(function(){
		$("#featureSel").find("option").not(":first").remove();
		$("#selectWeight").find("option").not(":first").remove();
		var classestreeCode=$("#classesSel").val() + $("#machiningSel").val() + $("#breedSel").val();
		var treeLevel="4";
		$('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
			$(data).each(function(i,val){
				$("#featureSel").append($('<option>',{value:val.levelCode}).text(val.levelName));
			});
		},{refreshHtml:false});
	});
	$("#featureSel").change(function(){
		$("#selectWeight").find("option").not(":first").remove();
		var length = $("#selectWeight").find("option").not(":first").length;
		//$("#selectWeight").find("option").not(":first").remove();
		var classestreeCode=$("#classesSel").val() + $("#machiningSel").val() + $("#breedSel").val() + $("#featureSel").val();
		var treeLevel="5";
		$('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
			$(data).each(function(i,val){
				$("#selectWeight").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
				if(length == 0){
					$("#selectWeight").append($('<option>',{value:val.levelCode}).text(val.levelName));
				}

			});
		},{refreshHtml:false});
	});
}
};
$(document).ready(function() {
	// 初始化调用
	PD14112407.init();
});