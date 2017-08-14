/**
 * 产品品种维护JS
 * 
 * @author gyh
 */
var PD14112405 = {
	formId : "PD14112405Form",
	saveButtonId : "PD14112405_SAVE",
	init : function() {
		//this.initSelect();
		this.selectChange();
		this.bindSavebutton();
		this.changeCheckBox();
		this.changeCheckBox2();
		this.changeCheckBox3();
	},
	bindSavebutton : function() {
		$("#" + PD14112405.saveButtonId).click(function() {
			PD14112405.saveData();
		});
		$("#" + PD14112405.backButtonId).click(function() {
			$.pdialog.close();
		});
	},
	changeCheckBox: function () {
		$(".codeName").click(function () {
			if ($(this).prop("checked")) {
				$(":checkbox").not($(this)).removeAttr("checked");
				$('#normsCode').removeAttr('disabled');
				$('#normsOut').removeAttr('disabled');
				$('#onlyName').prop('disabled','disabled');
				$('#onlyName').val("");
			} else {
				$(":checkbox").removeAttr("checked");
				$('#normsCode').prop('disabled','disabled');
				$('#normsOut').prop('disabled','disabled');
				$('#onlyName').prop('disabled','disabled');
			}
		});
		$(".selectWeight").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(":checkbox").not($(this)).removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#classestreeName5').prop('disabled', 'disabled');
				$('#classestreeName5').val("");
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeId').val("");
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeName').val("");
				$('#copyCodeVal').prop('disabled', 'disabled');
				$('#copyCodeVal').val("");
			} else {
				$(":checkbox").removeAttr("checked");
				$('#' + ids).prop('disabled', 'disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeVal').prop('disabled', 'disabled');
				$("#selectWeight option:first").prop("selected", 'selected');
				$('#classestreeName2').prop('disabled', 'disabled');
				$('#classestreeName3').prop('disabled', 'disabled');
				$('#classestreeName5').prop('disabled', 'disabled');
				$('#classestreeName4').prop('disabled', 'disabled');
				$('#classestreeName2').prop('disabled', 'disabled');
			}
		});
		$(".machiningSel").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(":checkbox").not($(this)).removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#classestreeName3').prop('disabled', 'disabled');
				$('#classestreeName3').val("");
				$('#classestreeName5').prop('disabled', 'disabled');
				$('#classestreeName5').val("");
				$('#classestreeName4').prop('disabled', 'disabled');
				$('#classestreeName4').val("");
				$('#classestreeName2').prop('disabled', 'disabled');
				$('#classestreeName2').val("");
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeId').val("");
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeName').val("");
				$('#copyCodeVal').prop('disabled', 'disabled');
				$('#copyCodeVal').val("");
			} else {
				$(":checkbox").removeAttr("checked");
				$('#' + ids).prop('disabled', 'disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeVal').prop('disabled', 'disabled');
				$('#classestreeName2').prop('disabled', 'disabled');
				$('#classestreeName3').prop('disabled', 'disabled');
				$('#classestreeName5').prop('disabled', 'disabled');
				$('#classestreeName4').prop('disabled', 'disabled');
				$('#classestreeName2').prop('disabled', 'disabled');
				$("#machiningSel option:first").prop("selected", 'selected');
			}
		});
		$(".breedSel").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(":checkbox").not($(this)).removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#classestreeName3').prop('disabled', 'disabled');
				$('#classestreeName3').val("");
				$('#classestreeName5').prop('disabled', 'disabled');
				$('#classestreeName5').val("");
				$('#classestreeName4').prop('disabled', 'disabled');
				$('#classestreeName4').val("");
				$('#classestreeName2').prop('disabled', 'disabled');
				$('#classestreeName2').val("");
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeId').val("");
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeName').val("");
				$('#copyCodeVal').prop('disabled', 'disabled');
				$('#copyCodeVal').val("");
			} else {
				$(":checkbox").removeAttr("checked");
				$('#' + ids).prop('disabled', 'disabled');
				$('#classestreeName2').prop('disabled', 'disabled');
				$('#classestreeName3').prop('disabled', 'disabled');
				$('#classestreeName5').prop('disabled', 'disabled');
				$('#classestreeName4').prop('disabled', 'disabled');
				$('#classestreeName2').prop('disabled', 'disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeVal').prop('disabled', 'disabled');
				$("#breedSel option:first").prop("selected", 'selected');
			}
		});
		$(".featureSel").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(":checkbox").not($(this)).removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#classestreeName5').prop('disabled', 'disabled');
				$('#classestreeName5').val("");
				$('#classestreeName3').prop('disabled', 'disabled');
				$('#classestreeName3').val("");
				$('#classestreeName4').prop('disabled', 'disabled');
				$('#classestreeName4').val("");
				$('#classestreeName2').prop('disabled', 'disabled');
				$('#classestreeName2').val("");
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeId').val("");
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeName').val("");
				$('#copyCodeVal').prop('disabled', 'disabled');
				$('#copyCodeVal').val("");
			} else {
				$(":checkbox").removeAttr("checked");
				$('#' + ids).prop('disabled', 'disabled');
				$('#classestreeName2').prop('disabled', 'disabled');
				$('#classestreeName3').prop('disabled', 'disabled');
				$('#classestreeName5').prop('disabled', 'disabled');
				$('#classestreeName4').prop('disabled', 'disabled');
				$('#classestreeName2').prop('disabled', 'disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeVal').prop('disabled', 'disabled');
				$("#featureSel option:first").prop("selected", 'selected');
			}
		});
	},
	changeCheckBox2: function () {
		$(".onlyName").click(function () {
			if ($(this).prop("checked")) {
				$(":checkbox").not($(this)).removeAttr("checked");
				$('#normsCode').prop('disabled','disabled');
				$('#normsOut').prop('disabled','disabled');
				$('#onlyName').removeAttr('disabled');
				$('#normsCode').val("");
				$('#normsOut').val("");
			} else {
				$(":checkbox").removeAttr("checked");
				$('#normsCode').prop('disabled','disabled');
				$('#normsOut').prop('disabled','disabled');
				$('#onlyName').prop('disabled','disabled');
				$('#onlyName').val("");
			}
		});
		$(".classestreeName2").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(":checkbox").not($(this)).removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#selectWeight').prop('disabled', 'disabled');
				$("#machiningSel option:first").prop("selected", 'selected');
				$("#machiningSel").prop("disabled", 'disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeId').val("");
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeName').val("");
				$('#classestreeName3').prop('disabled', 'disabled');
				$('#classestreeName3').val("");
				$('#classestreeName4').prop('disabled', 'disabled');
				$('#classestreeName4').val("");
				$('#classestreeName5').prop('disabled', 'disabled');
				$('#classestreeName5').val("");
			} else {
				$(":checkbox").removeAttr("checked");
				$('#' + ids).prop('disabled', 'disabled');
				$('#selectWeight').prop('disabled', 'disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeId').val("")
				$('#copyCodeName').val("")
				$('#copyCodeVal').val("");
				$('#copyCodeName').prop('disabled', 'disabled');
				$("#machiningSel option:first").prop("selected", 'selected');
			}
		});
		$(".classestreeName3").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(":checkbox").not($(this)).removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#selectWeight').prop('disabled', 'disabled');
				$("#breedSel option:first").prop("selected", 'selected');
				$("#breedSel").prop("disabled", 'disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeId').val("");
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeName').val("");
				$('#classestreeName2').prop('disabled', 'disabled');
				$('#classestreeName2').val("");
				$('#classestreeName4').prop('disabled', 'disabled');
				$('#classestreeName4').val("");
				$('#classestreeName5').prop('disabled', 'disabled');
				$('#classestreeName5').val("");
			} else {
				$(":checkbox").removeAttr("checked");
				$('#' + ids).prop('disabled', 'disabled');
				$('#selectWeight').prop('disabled', 'disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeId').val("");
				$('#copyCodeName').val("");
				$('#copyCodeVal').val("");
				$('#copyCodeName').prop('disabled', 'disabled');
				$("#breedSel option:first").prop("selected", 'selected');
			}
		});
		$(".classestreeName4").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(":checkbox").not($(this)).removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#selectWeight').prop('disabled', 'disabled');
				$("#featureSel option:first").prop("selected", 'selected');
				$("#featureSel").prop("disabled", 'disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeId').val("");
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeName').val("");
				$('#classestreeName3').prop('disabled', 'disabled');
				$('#classestreeName3').val("");
				$('#classestreeName2').prop('disabled', 'disabled');
				$('#classestreeName2').val("");
				$('#classestreeName5').prop('disabled', 'disabled');
				$('#classestreeName5').val("");
			} else {
				$(":checkbox").removeAttr("checked");
				$('#' + ids).prop('disabled', 'disabled');
				$('#selectWeight').prop('disabled', 'disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeId').val("")
				$('#copyCodeName').val("")
				$('#copyCodeVal').val("");
				$('#copyCodeName').prop('disabled', 'disabled');
				$("#featureSel option:first").prop("selected", 'selected');
			}
		});
		$(".classestreeName5").click(function () {
			var ids = $(this).attr("class");
			if ($(this).prop("checked")) {
				$(":checkbox").not($(this)).removeAttr("checked");
				$('#' + ids).removeAttr('disabled');
				$('#selectWeight').prop('disabled', 'disabled');
				$("#selectWeight option:first").prop("selected", 'selected');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeId').val("");
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeName').val("");
				$('#classestreeName3').prop('disabled', 'disabled');
				$('#classestreeName3').val("");
				$('#classestreeName4').prop('disabled', 'disabled');
				$('#classestreeName4').val("");
				$('#classestreeName2').prop('disabled', 'disabled');
				$('#classestreeName2').val("");
			} else {
				$(":checkbox").removeAttr("checked");
				$('#' + ids).prop('disabled', 'disabled');
				$('#selectWeight').prop('disabled', 'disabled');
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeId').val("")
				$('#copyCodeName').val("")
				$('#copyCodeVal').val("");
				$('#copyCodeName').prop('disabled', 'disabled');
				$("#selectWeight option:first").prop("selected", 'selected');
			}
		});
	},
	changeCheckBox3: function () {
		$(".copyCode").click(function () {
			if ($(this).prop("checked")) {
				$(":checkbox").not($(this)).removeAttr("checked");
				$('#copyCodeId').removeAttr('disabled');
				$('#copyCodeName').removeAttr('disabled');
				$('#copyCodeVal').removeAttr('disabled');
				$('#selectWeight').prop('disabled', 'disabled');
				$("#selectWeight option:first").prop("selected", 'selected');
				$('#classestreeName5').prop('disabled', 'disabled');
				$('#classestreeName5').val("");
			} else {
				$(":checkbox").removeAttr("checked");
				$('#copyCodeId').prop('disabled', 'disabled');
				$('#copyCodeId').val("");
				$('#copyCodeVal').prop('disabled', 'disabled');
				$('#copyCodeVal').val("");
				$('#copyCodeName').prop('disabled', 'disabled');
				$('#copyCodeName').val("");
				$('#selectWeight').prop('disabled', 'disabled');
				$('#classestreeName5').prop('disabled', 'disabled');
			}
		});
	},
	saveData : function() {
		var validator = mainValidation($("#" + PD14112405.formId));
		var isValid = validator.form();
		if ($(".onlyName").prop("checked")) {
			var thisValue = $("#onlyName").val();
			if(/^\s*$/.test(thisValue)){
				$.alertMessage.info("输入框值不能为空!");
				return false;
			}
		}

		if ($(".codeName").prop("checked")) {
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
				$.alertMessage.info("输入框值不能为空!");
				return false;
			}

		}
		if(!$(".codeName").prop("checked") && !$(".onlyName").prop("checked")){
			$.alertMessage.info("请选择录入方式!");
			return false;
		}

		if (isValid) {
			$.alertMessage.confirm("你确定要保存当前数据吗？", function() {
				$.alertMessage.close();
				formData = getFormData($("#" + PD14112405.formId));
				$('#PD14112406Id').postUrl(
						$("#" + PD14112405.formId).attr("action"),
						formData,
						function(data) {
							if(data=='1'){
								/** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
								PD14112406.initJsp();
								$.alertMessage.info("数据操作成功!");
								$.pdialog.close();
								/*$('#normsCode').val("");
								$('#normsOut').val("");
								$('#onlyName').val("");*/
								/** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  End */
							}else{
								$.alertMessage.info("数据异常,请修改后添加!");
							}
						});
			});
		}
	},
	initSelect:function() {
		var classesCode = $("#classesSel").val();
		var machiningCode = $("#machiningSel").val();
		var breedCode = $("#breedSel").val();
		var featureCode = $("#featureSel").val();
		var weightCode = $("#weightSel").val();
		if (classesCode != '') {
			var treeLevel = "2";
			$('#main-content').postUrl(Main.contextPath + "/PD141126/query", {
				classestreeCode: classesCode,
				treeLevel: treeLevel
			}, function (data) {
				$("#machiningSel option:first").remove();
				$("#machiningSel").append($('<option>', {value: ''}).text("---请选择---"));
				$(data).each(function (i, val) {
					if(machiningCode ==  val.classestreeCode) {
						$("#machiningSel").append($('<option>', {value: val.classestreeCode,selected:"selected"}).text(val.levelName));
					}else{
						$("#machiningSel").append($('<option>', {value: val.classestreeCode}).text(val.levelName));
					}
				});
			}, {refreshHtml: false});
		}
		if (machiningCode != '') {
			var treeLevel = "3";
			//$("#breedSel").find("option").not(":first").remove();
			$('#main-content').postUrl(Main.contextPath + "/PD141126/query", {
				classestreeCode: machiningCode,
				treeLevel: treeLevel
			}, function (data) {
				$("#breedSel option:first").remove();
				$("#breedSel").append($('<option>', {value: ''}).text("---请选择---"));
				$(data).each(function (i, val) {
					if(breedCode ==  val.classestreeCode) {
						$("#breedSel").append($('<option>', {value: val.classestreeCode,selected:"selected"}).text(val.levelName));
					}else{
						$("#breedSel").append($('<option>', {value: val.classestreeCode}).text(val.levelName));
					}
				});
			}, {refreshHtml: false});
		}
		if (breedCode != '') {
			var treeLevel = "4";
			//$("#featureSel").find("option").not(":first").remove();
			$('#main-content').postUrl(Main.contextPath + "/PD141126/query", {
				classestreeCode: breedCode,
				treeLevel: treeLevel
			}, function (data) {
				$("#featureSel option:first").remove();
				$("#featureSel").append($('<option>', {value: ''}).text("---请选择---"));
				$(data).each(function (i, val) {
					if(featureCode ==  val.classestreeCode) {
						$("#featureSel").append($('<option>', {value: val.classestreeCode,selected:"selected"}).text(val.levelName));
					}else{
						$("#featureSel").append($('<option>', {value: val.classestreeCode}).text(val.levelName));
					}
				});
			}, {refreshHtml: false});
		}
		if (featureCode != '') {
			var treeLevel = "5";
			//$("#weightSel").find("option").not(":first").remove();
			$('#main-content').postUrl(Main.contextPath + "/PD141126/query", {
				classestreeCode: featureCode,
				treeLevel: treeLevel
			}, function (data) {
				$("#weightSel option:first").remove();
				$("#weightSel").append($('<option>', {value: ''}).text("---请选择---"));
				$(data).each(function (i, val) {
					if(weightCode ==  val.classestreeCode) {
						$("#weightSel").append($('<option>', {value: val.classestreeCode,selected:"selected"}).text(val.levelName));
					}else{
						$("#weightSel").append($('<option>', {value: val.classestreeCode}).text(val.levelName));
					}
				});
			}, {refreshHtml: false});
		}
	},
	selectChange:function(){
		$("#classesSel").change(function(){
			$("#machiningSel").find("option").not(":first").remove();
			$("#breedSel").find("option").not(":first").remove();
			$("#featureSel").find("option").not(":first").remove();
			$("#weightSel").find("option").not(":first").remove();
			var classestreeCode=$("#classesSel").val();
			var treeLevel="2";
			$('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
				$(data).each(function(i,val){
					$("#machiningSel").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
				});
			},{refreshHtml:false});
		});
	$("#machiningSel").change(function(){
		$("#breedSel").find("option").not(":first").remove();
		$("#featureSel").find("option").not(":first").remove();
		$("#weightSel").find("option").not(":first").remove();
		var classestreeCode=$("#machiningSel").val();
		var treeLevel="3";
		$('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
			$(data).each(function(i,val){
				$("#breedSel").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
			});
		},{refreshHtml:false});
	});
	$("#breedSel").change(function(){
		$("#featureSel").find("option").not(":first").remove();
		$("#weightSel").find("option").not(":first").remove();
		var classestreeCode=$("#breedSel").val();
		var treeLevel="4";
		$('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
			$(data).each(function(i,val){
				$("#featureSel").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
			});
		},{refreshHtml:false});
	});
	$("#featureSel").change(function(){
		$("#weightSel").find("option").not(":first").remove();
		var length = $("#weightSel").find("option").not(":first").length;
		console.log(length);
		console.log($("#weightSel").find("option").not(":first"));
		//$("#selectWeight").find("option").not(":first").remove();
		var classestreeCode=$("#featureSel").val();
		var treeLevel="5";
		$('#main-content').postUrl(Main.contextPath + "/PD141126/query",{classestreeCode:classestreeCode,treeLevel:treeLevel},function(data){
			$(data).each(function(i,val){
				$("#weightSel").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
				if(length == 0){
					$("#selectWeight").append($('<option>',{value:val.classestreeCode}).text(val.levelName));
				}

			});
		},{refreshHtml:false});
	});
}
};
$(document).ready(function() {
	// 初始化调用
	PD14112405.init();
});