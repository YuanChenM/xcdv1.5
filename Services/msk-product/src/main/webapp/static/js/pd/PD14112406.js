/**
 * 产品基础数据列表JS
 *
 * @author xhy
 */
var PD14112406 = {
	newButtonId : "PD14112406_NEW",
	modifyButtonId : "PD14112406_MODIFY",
	deleteButtonId : "PD14112406_DELETE",
	fastnewButtonId : "PD14112406_FASTNEW",
	treeGrid : $('.tree'),
	tType : '',
	classestreeCode1 : '',
	classestreeName1 : '',
	classestreeCode2 : '',
	classestreeName2 : '',
	classestreeCode3 : '',
	classestreeName3 : '',
	classestreeCode4 : '',
	classestreeName4 : '',
	classestreeCode5 : '',
	classestreeName5 : '',
	classestreeCode6 : '',
	classestreeName6 : '',
    machiningC:'',
	breedC:'',
	featureC:'',
	weightC:'',
	init : function() {
		this.initCode();
		$("a[name='PD141150']").bind("click", function() {
			var url = Main.contextPath + "/pd141150/init";
			$('#main-content').postUrl(url,{classestreeCode:$(this).attr("classesTreeCode"),breedName:$(this).attr("breedName"),classesCode:CLASSES_CODE,machiningCode:PD14112406.machiningC,breedCode:PD14112406.breedC,featureCode:PD14112406.featureC,weightCode:PD14112406.weightC});
		});

		$("a[name='PD141152']").bind("click", function() {
			var url = Main.contextPath + "/pd141152/init";
			$('#main-content').postUrl(url,{classestreeCode:$(this).attr("classesTreeCode"),breedName:$(this).attr("breedName"),classesCode:CLASSES_CODE,machiningCode:PD14112406.machiningC,breedCode:PD14112406.breedC,featureCode:PD14112406.featureC,weightCode:PD14112406.weightC});
		});

		$("a[name='PD141125']").bind("click", function() {
			var url = Main.contextPath + "/pd141125/init";
			$('#main-content').postUrl(url,{classestreeCode:$(this).attr("classesTreeCode"),breedName:$(this).attr("breedName"),machiningName:$(this).attr("machiningName"),classesCode:CLASSES_CODE,machiningCode:PD14112406.machiningC,breedCode:PD14112406.breedC,featureCode:PD14112406.featureC,weightCode:PD14112406.weightC});
		});

		$("a[name='PD141146']").bind("click", function() {
			var url = Main.contextPath + "/pd141146/init";
			$('#main-content').postUrl(url,{classestreeCode:$(this).attr("classesTreeCode"),breedName:$(this).attr("breedName"),classesCode:CLASSES_CODE,machiningCode:PD14112406.machiningC,breedCode:PD14112406.breedC,featureCode:PD14112406.featureC,weightCode:PD14112406.weightC});
		});

		$("a[name='PD141147']").bind("click", function() {
			var url = Main.contextPath + "/pd141147/init";
			$('#main-content').postUrl(url,{classestreeCode:$(this).attr("classesTreeCode"),breedName:$(this).attr("breedName"),classesCode:CLASSES_CODE,machiningCode:PD14112406.machiningC,breedCode:PD14112406.breedC,featureCode:PD14112406.featureC,weightCode:PD14112406.weightC});
		});
		$("a[name='PD141148']").bind("click", function() {
			var url = Main.contextPath + "/pd141148/init";
			$('#main-content').postUrl(url,{classestreeCode:$(this).attr("classesTreeCode"),breedName:$(this).attr("breedName"),classesCode:CLASSES_CODE,machiningCode:PD14112406.machiningC,breedCode:PD14112406.breedC,featureCode:PD14112406.featureC,weightCode:PD14112406.weightC});

		});
		$("a[name='PD141149']").bind("click", function() {
			var url = Main.contextPath + "/pd141149/init";
			$('#main-content').postUrl(url,{classestreeCode:$(this).attr("classesTreeCode"),breedName:$(this).attr("breedName"),classesCode:CLASSES_CODE,machiningCode:PD14112406.machiningC,breedCode:PD14112406.breedC,featureCode:PD14112406.featureC,weightCode:PD14112406.weightC});

		});
		$("a[name='PD141151']").bind("click", function() {
			var url = Main.contextPath + "/pd141151/init";
			$('#main-content').postUrl(url,{classestreeCode:$(this).attr("classesTreeCode"),breedName:$(this).attr("breedName"),classesCode:CLASSES_CODE,machiningCode:PD14112406.machiningC,breedCode:PD14112406.breedC,featureCode:PD14112406.featureC,weightCode:PD14112406.weightC});

		});
		$("a[name='PD141153']").bind("click", function() {
			var url = Main.contextPath + "/pd141153/init";
			$('#main-content').postUrl(url,{classestreeCode:$(this).attr("classesTreeCode"),breedName:$(this).attr("breedName"),featureName:$(this).attr("featureName"),classesCode:CLASSES_CODE,machiningCode:PD14112406.machiningC,breedCode:PD14112406.breedC,featureCode:PD14112406.featureC,weightCode:PD14112406.weightC});
		});
		$("a[name='PD14114601']").bind("click", function() {
			var url = Main.contextPath + "/pd14114601/init";
			$('#main-content').postUrl(url,{classestreeCode:$(this).attr("classesTreeCode"),breedName:$(this).attr("breedName"),classesCode:CLASSES_CODE,machiningCode:PD14112406.machiningC,breedCode:PD14112406.breedC,featureCode:PD14112406.featureC,weightCode:PD14112406.weightC});
		});

		$("a[name='PD14114701']").bind("click", function() {
			var url = Main.contextPath + "/pd14114701/init";
			$('#main-content').postUrl(url,{classestreeCode:$(this).attr("classesTreeCode"),breedName:$(this).attr("breedName"),classesCode:CLASSES_CODE,machiningCode:PD14112406.machiningC,breedCode:PD14112406.breedC,featureCode:PD14112406.featureC,weightCode:PD14112406.weightC});
		});
		// 将单击事件绑定到tr标签
		$('.tree').find("tr").bind(
			"click",
			function() {
				PD14112406.tType = $(this).children('td').eq(0).attr("name");
				var tName0=$(this).children('td').eq(0).attr("value");
				var tName1=$(this).children('td').eq(1).attr("value");
				var tName2=$(this).children('td').eq(2).attr("value");
				var tName3=$(this).children('td').eq(3).attr("value");
				var tName4=$(this).children('td').eq(4).attr("value");
				var tName5=$(this).children('td').eq(5).attr("value");
				var tName6=$(this).children('td').eq(6).attr("value");
				var tName7=$(this).children('td').eq(7).attr("value");
				var tName8=$(this).children('td').eq(8).attr("value");
				var tName9=$(this).children('td').eq(9).attr("value");
				var tName10=$(this).children('td').eq(10).attr("value");
				var tName11=$(this).children('td').eq(11).attr("value");

				if (PD14112406.tType == 'classes') {
					PD14112406.classestreeCode1 = tName0;
					PD14112406.classestreeName1 = tName1;
				}else if (PD14112406.tType == 'mac') {
					PD14112406.classestreeCode2 = tName0;
					PD14112406.classestreeCode1 = tName1;
					PD14112406.classestreeName1 = tName2;
					PD14112406.classestreeName2 = tName3;
				}else if (PD14112406.tType == 'breed') {
					PD14112406.classestreeCode3 = tName0;
					PD14112406.classestreeCode1 = tName1;
					PD14112406.classestreeName1 = tName2;
					PD14112406.classestreeCode2 = tName3;
					PD14112406.classestreeName2 = tName4;
					PD14112406.classestreeName3 = tName5;
				}else if (PD14112406.tType == 'feature') {
					PD14112406.classestreeCode4 = tName0;
					PD14112406.classestreeCode1 = tName1;
					PD14112406.classestreeName1 = tName2;
					PD14112406.classestreeCode2 = tName3;
					PD14112406.classestreeName2 = tName4;
					PD14112406.classestreeCode3 = tName5;
					PD14112406.classestreeName3 = tName6;
					PD14112406.classestreeName4 = tName7;
				} else if (PD14112406.tType == 'weight') {
					PD14112406.classestreeCode5 = tName0;
					PD14112406.classestreeCode1 = tName1;
					PD14112406.classestreeName1 = tName2;
					PD14112406.classestreeCode2 = tName3;
					PD14112406.classestreeName2 = tName4;
					PD14112406.classestreeCode3 = tName5;
					PD14112406.classestreeName3 = tName6;
					PD14112406.classestreeCode4 = tName7;
					PD14112406.classestreeName4 = tName8;
					PD14112406.classestreeName5 = tName9;
				}else if (PD14112406.tType == 'norms') {
					PD14112406.classestreeCode6 = tName0;
					PD14112406.classestreeCode1 = tName1;
					PD14112406.classestreeName1 = tName2;
					PD14112406.classestreeCode2 = tName3;
					PD14112406.classestreeName2 = tName4;
					PD14112406.classestreeCode3 = tName5;
					PD14112406.classestreeName3 = tName6;
					PD14112406.classestreeCode4 = tName7;
					PD14112406.classestreeName4 = tName8;
					PD14112406.classestreeCode5 = tName9;
					PD14112406.classestreeName5 = tName10;
					PD14112406.classestreeName6 = tName11;
				}
				PD14112406.treeGrid.find("tr").removeClass("row_active"); /*添加点击选中的条目的颜色*/
				jQuery(this).addClass("row_active");
			});
		this.bindSearchButton(); //绑定按钮事件
		PD14112406.treeGrid.treegrid();
	},
	// 绑定按钮
	bindSearchButton : function() {
		$("#" + PD14112406.newButtonId).click(function() {
			PD14112406.newData();  /*新建*/
		});
		$("#" + PD14112406.modifyButtonId).click(function() {
			PD14112406.modifyData(); /*修改*/
		});
		$("#" + PD14112406.deleteButtonId).click(function() {
			PD14112406.deleteData();  /*删除*/
		});
		$("#" + PD14112406.fastnewButtonId).click(function() {
			PD14112406.fastnewData();  /*快速录入*/
		});

	},
	/*添加操作*/
	newData:function(){
		if(PD14112406.tType == ''){
			$.alertMessage.info("请选择列表项目!");
		}
		/*新增二级类别mac*/
		if (PD14112406.tType == 'classes') {
			$.pdialog.open("新增二级类别", Main.contextPath + "/PD14112401/init", {
				width : 600,
				height : 260
			}, {
				classestreeCode1 : PD14112406.classestreeCode1,
				classestreeName1 : PD14112406.classestreeName1
			});
		} else if (PD14112406.tType == 'mac') {
			/*新增三级类别breed*/
			$.pdialog.open("新增三级类别", Main.contextPath + "/PD14112402/init", {
				width : 600,
				height : 260
			}, {
				classestreeCode1 : PD14112406.classestreeCode1,
				classestreeName1 : PD14112406.classestreeName1,
				classestreeCode2 : PD14112406.classestreeCode2,
				classestreeName2 : PD14112406.classestreeName2
			});
		} else if (PD14112406.tType == 'breed') {
			$.pdialog.open("新增四级类别", Main.contextPath + "/PD14112403/init", {
				width : 600,
				height : 270
			}, {
				classestreeCode1 : PD14112406.classestreeCode1,
				classestreeName1 : PD14112406.classestreeName1,
				classestreeCode2 : PD14112406.classestreeCode2,
				classestreeName2 : PD14112406.classestreeName2,
				classestreeCode3 : PD14112406.classestreeCode3,
				classestreeName3 : PD14112406.classestreeName3
			});
		}else if (PD14112406.tType == 'feature') {
			$.pdialog.open("新增五级类别", Main.contextPath + "/PD14112404/init", {
				width : 800,
				height : 400
			}, {
				classestreeCode1 : PD14112406.classestreeCode1,
				classestreeName1 : PD14112406.classestreeName1,
				classestreeCode2 : PD14112406.classestreeCode2,
				classestreeName2 : PD14112406.classestreeName2,
				classestreeCode3 : PD14112406.classestreeCode3,
				classestreeName3 : PD14112406.classestreeName3,
				classestreeCode4 : PD14112406.classestreeCode4,
				classestreeName4 : PD14112406.classestreeName4
			});
		}else if (PD14112406.tType == 'weight') {
			$.pdialog.open("新增产品包装", Main.contextPath + "/PD14112405/init", {
				width : 770,
				height : 400
			}, {
				classestreeCode1 : PD14112406.classestreeCode1,
				classestreeName1 : PD14112406.classestreeName1,
				classestreeCode2 : PD14112406.classestreeCode2,
				classestreeName2 : PD14112406.classestreeName2,
				classestreeCode3 : PD14112406.classestreeCode3,
				classestreeName3 : PD14112406.classestreeName3,
				classestreeCode4 : PD14112406.classestreeCode4,
				classestreeName4 : PD14112406.classestreeName4,
				classestreeCode5 : PD14112406.classestreeCode5,
				classestreeName5 : PD14112406.classestreeName5
			});
		}else if (PD14112406.tType == 'norms') {
			$.alertMessage.confirm("包装目录不能新增！");
		}
	},
	modifyData : function() {
		if(PD14112406.tType == ''){
			$.alertMessage.info("请选择列表项目!");
		}

		if (PD14112406.tType == 'classes') {
			$.alertMessage.confirm("一级目录不能修改！");

		} else if (PD14112406.tType == 'mac') {
			$.pdialog.open("修改二级类别", Main.contextPath + "/PD14112401/init", {
				width : 500,
				height : 260
			}, {
				classestreeCode1 : PD14112406.classestreeCode1,
				classestreeName1 : PD14112406.classestreeName1,
				classestreeCode2 : PD14112406.classestreeCode2,
				classestreeName2 : PD14112406.classestreeName2
			});
		} else if (PD14112406.tType == 'breed') {
			$.pdialog.open("修改三级类别", Main.contextPath + "/PD14112402/init", {
				width : 500,
				height : 270
			}, {
				classestreeCode1 : PD14112406.classestreeCode1,
				classestreeName1 : PD14112406.classestreeName1,
				classestreeCode2 : PD14112406.classestreeCode2,
				classestreeName2 : PD14112406.classestreeName2,
				classestreeCode3 : PD14112406.classestreeCode3,
				classestreeName3 : PD14112406.classestreeName3

			});
		}else if (PD14112406.tType == 'feature') {
			$.pdialog.open("修改四级类别", Main.contextPath + "/PD14112403/init", {
				width : 500,
				height : 300
			}, {
				classestreeCode1 : PD14112406.classestreeCode1,
				classestreeName1 : PD14112406.classestreeName1,
				classestreeCode2 : PD14112406.classestreeCode2,
				classestreeName2 : PD14112406.classestreeName2,
				classestreeCode3 : PD14112406.classestreeCode3,
				classestreeName3 : PD14112406.classestreeName3,
				classestreeCode4 : PD14112406.classestreeCode4,
				classestreeName4 : PD14112406.classestreeName4
			});
		}else if (PD14112406.tType == 'weight') {
			$('#main-content').postUrl(Main.contextPath + "/PD14112404/checkData/",
				{classestreeCode1 : PD14112406.classestreeCode1,
				classestreeCode2 : PD14112406.classestreeCode2,
				classestreeCode3 : PD14112406.classestreeCode3,
				classestreeName5 : PD14112406.classestreeName5},function(data){
					if(data=='1'){
						$.alertMessage.info("该数据已被其他品种使用,无法被修改!");
						return false;
					}else{
						$.pdialog.open("修改五级类别", Main.contextPath + "/PD14112404/init", {
							width : 750,
							height : 320
						}, {
							classestreeCode1 : PD14112406.classestreeCode1,
							classestreeName1 : PD14112406.classestreeName1,
							classestreeCode2 : PD14112406.classestreeCode2,
							classestreeName2 : PD14112406.classestreeName2,
							classestreeCode3 : PD14112406.classestreeCode3,
							classestreeName3 : PD14112406.classestreeName3,
							classestreeCode4 : PD14112406.classestreeCode4,
							classestreeName4 : PD14112406.classestreeName4,
							classestreeCode5 : PD14112406.classestreeCode5,
							classestreeName5 : PD14112406.classestreeName5
						});
					}
				},{refreshHtml:false})
	   }else if (PD14112406.tType == 'norms') {
			$.alertMessage.confirm("六级目录不能修改！");
		}
	},
	/*删除操作*/
	deleteData : function(){
		if(PD14112406.tType == ''){
			$.alertMessage.info("请选择列表项目!");
		}
		/* 删除一级类别*/
		if (PD14112406.tType == 'classes') {
			$.alertMessage.confirm("你确定删除当前数据吗？", function () {
				$.alertMessage.close();
				$('#PD14112406Id').postUrl(Main.contextPath + "/PD141124/delete/",{classestreeCode1 : PD14112406.classestreeCode1},function(data) {
					if(data == '1'){
						$.alertMessage.info("数据删除成功!");
						PD14112406.initJsp();
						//$.post(Main.contextPath + "/PD14112406/init/",{classesCode:CLASSES_CODE,machiningCode:$("#machining").val()});
					}else if(data == '2'){
						$.alertMessage.info("该节点无法被删除,请先删除子节点!");
					}else{
						$.alertMessage.info("数据存在异常,请联系管理员!");
					}
				},{refreshHtml:false});
			});
		} else if (PD14112406.tType == 'mac') {
			/*删除二级类别*/
			$.alertMessage.confirm("你确定删除当前数据吗？", function () {
				$.alertMessage.close();
				$('#PD14112406Id').postUrl(Main.contextPath + "/PD14112401/delete/",
					{classestreeCode1 : PD14112406.classestreeCode1,
					 classestreeCode2 : PD14112406.classestreeCode2
					},function(data) {
					if(data == '1'){
						$.alertMessage.info("数据删除成功!");
						PD14112406.initJsp();
						//$.post(Main.contextPath + "/PD14112406/init/",{classesCode:CLASSES_CODE,machiningCode:$("#machining").val()});
						//PD14112406.treeGrid.treegrid('reload');
					}else if(data == '2'){
						$.alertMessage.info("该节点无法被删除,请先删除子节点!");
					}else{
						$.alertMessage.info("数据存在异常,请联系管理员!");
					}
				},{refreshHtml:false});
			});
		} else if (PD14112406.tType == 'breed') {
			/*删除三级类别*/
			$.alertMessage.confirm("你确定删除当前数据吗？", function () {
				$.alertMessage.close();
				$('#PD14112406Id').postUrl(Main.contextPath + "/PD14112402/delete/",
					{classestreeCode1 : PD14112406.classestreeCode1,
					 classestreeCode2 : PD14112406.classestreeCode2,
					 classestreeCode3 : PD14112406.classestreeCode3
					},function(data) {
						if(data == '1'){
							$.alertMessage.info("数据删除成功!");
							PD14112406.initJsp();
							//$.post(Main.contextPath + "/PD14112406/init/",{classesCode:CLASSES_CODE,machiningCode:$("#machining").val()});
							//PD14112406.treeGrid.treegrid('reload');
						}else if(data == '2'){
							$.alertMessage.info("该节点无法被删除,请先删除子节点!");
						}else{
							$.alertMessage.info("数据存在异常,请联系管理员!");
						}
					},{refreshHtml:false});
			});
		}else if (PD14112406.tType == 'feature') {
			/*删除四级类别*/
			$.alertMessage.confirm("你确定删除当前数据吗？", function () {
				$.alertMessage.close();
				$('#PD14112406Id').postUrl(Main.contextPath + "/PD14112403/delete/",
					{classestreeCode1 : PD14112406.classestreeCode1,
					classestreeCode2 : PD14112406.classestreeCode2,
					classestreeCode3 : PD14112406.classestreeCode3,
					classestreeCode4 : PD14112406.classestreeCode4
					},function(data) {
						if(data == '1'){
							$.alertMessage.info("数据删除成功!");
							PD14112406.initJsp();
							//$.post(Main.contextPath + "/PD14112406/init/",{classesCode:CLASSES_CODE,machiningCode:$("#machining").val()});
						}else if(data == '2'){
							$.alertMessage.info("该节点无法被删除,请先删除子节点!");
						}else{
							$.alertMessage.info("数据存在异常,请联系管理员!");
						}
					},{refreshHtml:false});
			});
		}else if (PD14112406.tType == 'weight') {
			/*删除五级类别*/
			$.alertMessage.confirm("你确定删除当前数据吗？", function () {
				$.alertMessage.close();
				$('#PD14112406Id').postUrl(Main.contextPath + "/PD14112404/delete/",
					{classestreeCode1 : PD14112406.classestreeCode1,
						classestreeCode2 : PD14112406.classestreeCode2,
						classestreeCode3 : PD14112406.classestreeCode3,
						classestreeCode4 : PD14112406.classestreeCode4,
						classestreeCode5 : PD14112406.classestreeCode5
					},function(data) {
						if(data == '1'){
							$.alertMessage.info("数据删除成功!");
							PD14112406.initJsp();
							//PD14112406.treeGrid.treegrid('reload');
							//$.post(Main.contextPath + "/PD14112406/init/",{classesCode:CLASSES_CODE,machiningCode:$("#machining").val()});
						}else if(data == '2'){
							$.alertMessage.info("该节点无法被删除,请先删除子节点!");
						}else{
							$.alertMessage.info("数据存在异常,请联系管理员!");
						}
					},{refreshHtml:false});
			});
		}else if (PD14112406.tType == 'norms') {
			/*删除六级类别*/
			$.alertMessage.confirm("你确定删除当前数据吗？", function () {
				$.alertMessage.close();
				$('#PD14112406Id').postUrl(Main.contextPath + "/PD14112405/delete/",
					{classestreeCode1 : PD14112406.classestreeCode1,
						classestreeCode2 : PD14112406.classestreeCode2,
						classestreeCode3 : PD14112406.classestreeCode3,
						classestreeCode4 : PD14112406.classestreeCode4,
						classestreeCode5 : PD14112406.classestreeCode5,
						classestreeCode6 : PD14112406.classestreeCode6
					},function(data) {
						if(data == '1'){
							$.alertMessage.info("数据删除成功!");
							PD14112406.initJsp();
							//$.post(Main.contextPath + "/PD14112406/init/",{classesCode:CLASSES_CODE,machiningCode:$("#machining").val()});
							//PD14112406.treeGrid.treegrid('reload');
						}else if(data == '2'){
							$.alertMessage.info("该节点无法被删除,请先删除子节点!");
						}else{
							$.alertMessage.info("数据存在异常,请联系管理员!");
						}
					},{refreshHtml:false});
			});
		}
	},
	initJsp:function(){
		var machingCode = PD14112406.machiningC;
		var breedCode = PD14112406.breedC;
		var featureCode = PD14112406.featureC;
		var weightCode = PD14112406.weightC;

		$('#main-content').postUrl(Main.contextPath + "/PD141124/init",
			{classesCode:CLASSES_CODE,machiningCode:machingCode,
				breedCode:breedCode,
				featureCode:featureCode,
				weightCode:weightCode});
	},
	initParam:function(){
		var machingCode = PD14112406.machiningC;
		var breedCode = PD14112406.breedC;
		var featureCode = PD14112406.featureC;
		var weightCode = PD14112406.weightC;
		$('#PD14112406Id').postUrl(Main.contextPath + "/PD14112406/init",
			{classesCode:CLASSES_CODE,machiningCode:machingCode,
				breedCode:breedCode,
				featureCode:featureCode,
				weightCode:weightCode});
	},
	fastnewData:function(){
		$.pdialog.open("快速录入产品信息", Main.contextPath + "/PD14112407/init", {
			width: 770,
			height: 600
		}, {
			classestreeCode1: PD14112406.classestreeCode1,
			classestreeName1: PD14112406.classestreeName1,
			classestreeCode2: PD14112406.classestreeCode2,
			classestreeName2: PD14112406.classestreeName2,
			classestreeCode3: PD14112406.classestreeCode3,
			classestreeName3: PD14112406.classestreeName3,
			classestreeCode4: PD14112406.classestreeCode4,
			classestreeName4: PD14112406.classestreeName4,
			classestreeCode5: PD14112406.classestreeCode5,
			classestreeName5: PD14112406.classestreeName5
		});
	},initCode:function(){
		PD14112406.classestreeCode1 = $("#class").val();
		PD14112406.classestreeCode2 = $("#machining").val();
		PD14112406.classestreeCode3 = $("#breed").val();
		PD14112406.classestreeCode4 = $("#feature").val();
		PD14112406.classestreeCode5 = $("#weight").val();
		PD14112406.classestreeName1 = $("#classname").val();
		PD14112406.classestreeName2 = $("#machiningname").val();
		PD14112406.classestreeName3 = $("#breedname").val();
		PD14112406.classestreeName4 = $("#featurename").val();
		PD14112406.classestreeName5 = $("#weightname").val();
		PD14112406.machiningC = $("#machining").val();
		PD14112406.breedC = $("#breed").val();
		PD14112406.featureC = $("#feature").val();
		PD14112406.weightC = $("#weight").val();
	}
	};
$(document).ready(function() {
	// 初始化调用
	PD14112406.init();
});
