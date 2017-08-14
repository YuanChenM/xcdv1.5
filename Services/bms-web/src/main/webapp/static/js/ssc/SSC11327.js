/**
 * Created by peng_hao on 2016/8/31.
 */
var $List_Grid;
var SSC11327 = {
	formId:"SSC11327Form",
	searchButtonId: "SSC11327_SEARCH",
	init : function() {
		$("#slMainClass").prepend("<option value=''>--请选择--</option>");
		$("#slMainClass").val("");
		$List_Grid = $("#SSC11327_list_grid").grid({
			actionHandler:SSC11327.actionHandler,
			linkHandler: SSC11327.linkHandler
		})
		this.bindSearchButton();
	},
	bindSearchButton: function () {
		$("#" + SSC11327.searchButtonId).click(function () {
			FormUtils.setFormValue(SSC11327.formId, "SSC11327");
			$List_Grid.fnDraw()
		});
	},
	//跳转企业详细信息页面
	linkHandler: function (rowdata) {
		$.pdialog.open("企业详细信息", Main.contextPath + "/SSC11328/searchSlDetailInfo/", {
			width: "80%",
			height: 780
		},{
			slAccount:rowdata.slAccount,
			slCode:rowdata.slCode
		},"epDialog1");
	},

	actionHandler: function (rowdata, coltype, row, col) {
		if(coltype=='detail'){
			/*$('#main-content').postUrl( Main.contextPath + "/SSC11329/init/",{
				slCode:rowdata.slCode
			});*/
			Main.detailWindow(Main.contextPath + "/SSC11329/init/",  {
				slCode:rowdata.slCode
			}, "企业产品信息");
		}
	}
}
$(document).ready(function(){
	SSC11327.init();
})