/**
 * 产品基础数据列表JS
 *
 * @author gyh
 */
var PD141101 = {
	$List_Grid : '',
	formId : "PD141101Form",
	searchButtonId : "PD141101_SEARCH",
	newButtonId : "PD141101_NEW",
	modifyButtonId : "PD141101_MODIFY",
	deleteButtonId : "PD141101_DELETE",
	removeButtonId : "PD141101_REMOVE",
	treeGrid : $('.tree'),
	tType : '',
	classesCode : '',
	breedCode : '',
	featureCode : '',
	classestreeCode : '',
	init : function() {
		$("a[name='PD141113']").bind("click", function() {
			$('#main-content').postUrl(Main.contextPath + "/PD141113/init/", {
				'filterMap[classesCode]' : $(this).attr("classesCode"),
				'filterMap[breedCode]' : $(this).attr("breedCode"),
				'filterMap[featureCode]' : $(this).attr("featureCode"),
				classesCode : CLASSES_CODE,
				breedCode : BREED_CODE ,
				featureCode : FEATURE_CODE
			});
		});
		this.bindSearchButton();
		// 将单击事件绑定到tr标签
		$('.tree').find("tr").bind(
			"click",
			function() {
				PD141101.tType = $(this).children('td').eq(0).attr("name");
				var tName0=$(this).children('td').eq(0).attr("value");
				var tName1=$(this).children('td').eq(1).attr("value");
				var tName2=$(this).children('td').eq(2).attr("value");

				if (PD141101.tType == 'classes') {
					PD141101.classesCode = tName0
				} else if (PD141101.tType == 'breed') {
					PD141101.breedCode = tName0
					PD141101.classesCode = tName1;
					PD141101.classestreeCode = tName2;
				} else if (PD141101.tType == 'feature') {
					PD141101.featureCode = tName0;
					PD141101.classesCode = tName1;
					PD141101.breedCode = tName2;
				}
				$('.tree').find("tr").removeClass("row_active");
				jQuery(this).addClass("row_active");
			});
		$('.tree').treegrid();
	},
	// 绑定按钮
	bindSearchButton : function() {
		$("#" + PD141101.searchButtonId).click(function() {
			PD141101.searchData();
		});
		$("#" + PD141101.newButtonId).click(function() {
			PD141101.newData();
		});
		$("#" + PD141101.modifyButtonId).click(function() {
			PD141101.modifyData();
		});
		$("#" + PD141101.deleteButtonId).click(function() {
			PD141101.deleteData();
		});
		$("#" + PD141101.removeButtonId).click(function() {
			PD141101.removeData();
		});
	},
	modifyData : function() {
		if(PD141101.tType == ''){
			$.alertMessage.info("请选择列表项目!");
		}
		if (PD141101.tType == 'classes') {
			$.alertMessage.info("该类别无法修改!");
			/*$.pdialog.open("修改产品类别", Main.contextPath + "/PD141102/init", {
				width : 600,
				height : 400
			}, {
				pk : PD141101.classesCode,
				breedCode : PD141101.breedCode
			});*/
		} else if (PD141101.tType == 'breed') {
			$.pdialog.open("修改产品品种", Main.contextPath + "/PD141103/init", {
				width : 600,
				height : 400
			}, {
				classesCode : PD141101.classesCode,
				breedCode : PD141101.breedCode,
			/*	featureCode : PD141101.featureCode*/
				classestreeCode : PD141101.classestreeCode
			});
		} else if (PD141101.tType == 'feature') {
			$.pdialog.open("修改产品特征", Main.contextPath + "/PD141104/init", {
				width : 600,
				height : 400
			}, {
				classesCode : PD141101.classesCode,
				breedCode : PD141101.breedCode,
				featureCode : PD141101.featureCode
			});
		}
	},
	deleteData : function() {
		if(PD141101.tType == ''){
			$.alertMessage.info("请选择列表项目!");
		}
		if (PD141101.tType == 'classes') {
			$.alertMessage.info("该类别无法删除!");
			/*$('#main-content').postUrl(
				Main.contextPath + '/PD141101/init/delete',
				{
					classesCode : PD141101.classesCode
				},
				function() {
					$('#main-content').postUrl(
						Main.contextPath + "/PD141101/init/");
				});*/
		} else if (PD141101.tType == 'breed') {
			$.alertMessage.confirm("你确定要删除这条数据吗？", function () {
				$.alertMessage.close();
			$('#main-content').postUrl(
				Main.contextPath + '/PD141102/init/delete',
				{
					classesCode : PD141101.classesCode,
					breedCode : PD141101.breedCode
				},
				function() {
					$('#main-content').postUrl(
						Main.contextPath + "/PD141101/init/");
					$.alertMessage.info("删除成功！")
				});
			});
		} else if (PD141101.tType == 'feature') {
			$.alertMessage.confirm("你确定要删除这条数据吗？", function () {
				$.alertMessage.close();
			$('#main-content').postUrl(
				Main.contextPath + '/PD141104/init/delete',
				{
					classesCode : PD141101.classesCode,
					breedCode : PD141101.breedCode,
					featureCode : PD141101.featureCode
				},
				function() {
					$('#main-content').postUrl(
						Main.contextPath + "/PD141101/init/");
					$.alertMessage.info("删除成功！")
				}

			);
			});
		}
	},
	removeData : function() {
		if(PD141101.tType == ''){
			$.alertMessage.info("请选择列表项目!");
		}
		if (PD141101.tType == 'classes') {
			$.alertMessage.info("该类别无法废弃!");
			/*$('#main-content').postUrl(
				Main.contextPath + '/PD141101/init/close',
				{
					classesCode : PD141101.classesCode
				},
				function() {
					$('#main-content').postUrl(
						Main.contextPath + "/PD141101/init/");
				});*/
		} else if (PD141101.tType == 'breed') {
			$('#main-content').postUrl(
				Main.contextPath + '/PD141102/init/close',
				{
					classesCode : PD141101.classesCode,
					breedCode : PD141101.breedCode
				},
				function() {
					$('#main-content').postUrl(
						Main.contextPath + "/PD141101/init/");
				});
		} else if (PD141101.tType == 'feature') {
			$('#main-content').postUrl(
				Main.contextPath + '/PD141104/init/close',
				{
					classesCode : PD141101.classesCode,
					breedCode : PD141101.breedCode,
					featureCode : PD141101.featureCode
				},
				function() {
					$('#main-content').postUrl(
						Main.contextPath + "/PD141101/init/");
				});
		}
	},
	searchData : function() {
		var formData = getFormData($("#" + PD141101.formId));
		$('#main-content').postUrl($("#" + PD141101.formId).attr("action"),
			formData);
	},
	newData : function() {
		if(PD141101.tType == ''){
			$.alertMessage.info("请选择列表项目!");
		}
		if (PD141101.tType == 'newClasses') {
			/*$.pdialog.open("新增产品类别", Main.contextPath + "/PD141102/init", {
				width : 600,
				height : 400
			});*/
			$.alertMessage.info("该类别无法新增!");
		} else if (PD141101.tType == 'classes') {
			$.pdialog.open("新增产品品种", Main.contextPath + "/PD141103/init", {
				width : 600,
				height : 400
			}, {
				classesCode : PD141101.classesCode
			});
		} else if (PD141101.tType == 'breed') {
			$.pdialog.open("新增产品特征", Main.contextPath + "/PD141104/init", {
				width : 600,
				height : 400
			}, {
				classesCode : PD141101.classesCode,
				breedCode : PD141101.breedCode
			});
		} else if (PD141101.tType == 'feature') {
			$.alertMessage.confirm("特征目录不能新增！");
		}
	}
}
$(document).ready(function() {
	// 初始化调用
	PD141101.init();
});
