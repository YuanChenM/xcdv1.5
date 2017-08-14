/**
 *JS
 *@author ph
 */
var $List_Grid_EP_Sellers;
var $List_Grid_EP_Producers;
var SSC11328 = {
	producesGrid1:"SSC11328_list_grid_epProducers1",
	sellersGrid1:"SSC11328_list_grid_epSellers1",
	producesGrid2:"SSC11328_list_grid_epProducers2",
	sellersGrid2:"SSC11328_list_grid_epSellers2",
	init:function(){
		this.loadImages();
		//窗口1显示，窗口2不显示
		if ($('#epDialog1').is(":visible") && !$('#epDialog2').is(":visible")) {
			$('#epDialog1').find("#"+ SSC11328.producesGrid2).hide();
			$('#epDialog1').find("#"+ SSC11328.sellersGrid2).hide();
			//绑定生产商数据
			if ($('#epDialog1').find("#"+ SSC11328.producesGrid1).is(":visible")) {
				$List_Grid_EP_Producers = $('#epDialog1').find("#"+ SSC11328.producesGrid1).grid({
					actionHandler : SSC11328.actionHandler
				});
			}
			//绑定卖家数据
			if ($('#epDialog1').find("#"+ SSC11328.sellersGrid1).is(":visible")) {
				$List_Grid_EP_Sellers = $('#epDialog1').find("#"+ SSC11328.sellersGrid1).grid({
					actionHandler : SSC11328.actionHandler
				});
			}
		}
		//窗口1显示，窗口2显示
		else if ($('#epDialog1').is(":visible") && $('#epDialog2').is(":visible")) {
			$('#epDialog2').find("#"+ SSC11328.producesGrid1).hide();
			$('#epDialog2').find("#"+ SSC11328.sellersGrid1).hide();
			//绑定生产商数据
			if ($('#epDialog2').find("#"+ SSC11328.producesGrid2).is(":visible")) {
				$List_Grid_EP_Producers = $('#epDialog2').find("#"+ SSC11328.producesGrid2).grid({
					actionHandler : SSC11328.actionHandler
				});
			}
			//绑定卖家数据
			if ($('#epDialog2').find("#"+ SSC11328.sellersGrid2).is(":visible")) {
				$List_Grid_EP_Sellers = $('#epDialog2').find("#"+ SSC11328.sellersGrid2).grid({
					actionHandler : SSC11328.actionHandler
				});
			}
		}

	},
	actionHandler : function(rowdata, coltype) {
		/** 操作按钮 */
		if (coltype == "search") {
			var url = "http://121.196.237.87/msk-image/sl/89082/ecteam01.png";
			$.alertMessage.info("暂无图片");
			/*$.alertMessage.info("<img src='"+url+"' height='590' width='550'>");*/
		}
		if (coltype == "refresh") {
			$.pdialog.open("企业详细信息", Main.contextPath + "/SSC11328/searchSlDetailInfo/", {
				width: "80%",
				height: 780
			},{
				slAccount:rowdata.slAccount,
				slCode:rowdata.slCode
			},"epDialog2");
		}
	},

	loadImages:function(){
		/*var licPath=$("#licPathId").attr("href");
		 var taxPath=$("#taxPathId").attr("href");
		 var orgNoPath=$("#orgNoPathId").attr("href");
		 var balPath=$("#balPathId").attr("href");
		 var licTypePath=$("#licTypePathId").attr("href");*/
		var imgSrc=$("a[name='imgSrc']");
		var fdlPath=$(".fdlPathId").attr("href");
		var authPath=$(".authPathId").attr("href");
		var oemAuthPath=$(".oemAuthPathId").attr("href");

		var arrayList = ["png","jpg","jpeg","bmp","gif"];
		$(arrayList).each(function(i,val){
			$(imgSrc).each(function(j,src){
				var pathdata=$(this).attr("href");
				var srcPath=pathdata+val;
				var aTag = $(this);
				var img = new Image();
				img.onload = function() {
					aTag.html("<img src='"+srcPath+"' title='"+srcPath+"' height='150px' width='250px'/>");
					aTag.attr("href",srcPath);
					aTag.attr("src",srcPath);
					aTag.attr("title",srcPath);
				}
				img.src= srcPath;
			});
			var img = new Image();
			var fdlPathUrl=fdlPath+val;
			img.onload = function() {
				$(".fdlPathId").html("<img src='"+fdlPathUrl+"' height='150px' width='250px'/>");
				$(".fdlPathId").attr("href",srcPath);
				$(".fdlPathId").attr("src",srcPath);
			}
			img.src= fdlPathUrl;
			var authPathUrl=authPath+val;
			img.onload = function() {
				$(".authPathId").html("<img src='"+authPathUrl+"' height='150px' width='250px'/>");
				$(".authPathId").attr("href",srcPath);
				$(".authPathId").attr("src",srcPath);
			}
			img.src= authPathUrl;
			var oemAuthPathUrl=oemAuthPath+val;
			img.onload = function() {
				$(".oemAuthPathId").html("<img src='"+oemAuthPathUrl+"' height='150px' width='250px'/>");
				$(".oemAuthPathId").attr("href",srcPath);
				$(".oemAuthPathId").attr("src",srcPath);
			}
			img.src= oemAuthPathUrl;

			/* var licPathImg=licPath+val;
			 var taxPathImg=taxPath+val;
			 var img = new Image();
			 img.onload = function() {
			 alert("图片加载成功");
			 $("#licPathId").html("<img src='"+path+"'/>");
			 }*/
			/*img.src = licPathImg;
			 img.src= taxPathImg;*/
		});
	}

}
$(document).ready(function() {
	//初始化调用
	SSC11328.init();
});