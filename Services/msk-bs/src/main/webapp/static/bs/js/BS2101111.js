
var BS2101111 = {
	formId: "bs2101111FormId",
	saveButtonId: "BS2101111_NEW",
	init: function () {
		$List_Grid = $('#bs2101111_list_grid').grid({
		});
		this.changeSelect();
		this.changeSelect1();
	},
	changeSelect: function () {
		var citySelect = $('#city_select');
		var districtSelect = $('#district_select');
		$('#province_select').change(function () {
			$("#city_select").find("option").not(":first").remove();
			$("#district_select").find("option").not(":first").remove();
			var provinceCode = $('#province_select').val();
			if (provinceCode != 0) {
				$('#main-content').postUrl(Main.contextPath + '/BS2101105/findCity', {provinceCode: provinceCode},
					function (data) {
						$.each(data, function (i, item) {
							citySelect.append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
						});
					}, {refreshHtml: false});
			}
		});
		$('#city_select').change(function () {
			$("#district_select").find("option").not(":first").remove();
			var cityCode = $("#city_select").val();
			if (cityCode != 0) {
				$('#main-content').postUrl(Main.contextPath + '/BS2101105/findDistrict', {cityCode: cityCode}, function (data2) {
					$.each(data2, function (i, item) {
						districtSelect.append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
					});
				}, {refreshHtml: false})
			}
		});
	},
	changeSelect1: function () {
		var citySelect = $('#city_select1');
		var districtSelect = $('#district_select1');
		$('#province_select1').change(function () {
			$("#city_select1").find("option").not(":first").remove();
			$("#district_select1").find("option").not(":first").remove();
			var provinceCode = $('#province_select1').val();
			if (provinceCode != 0) {
				$('#main-content').postUrl(Main.contextPath + '/BS2101105/findCity', {provinceCode: provinceCode},
					function (data) {
						$.each(data, function (i, item) {
							citySelect.append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
						});
					}, {refreshHtml: false});
			}
		});
		$('#city_select1').change(function () {
			$("#district_select1").find("option").not(":first").remove();
			var cityCode = $("#city_select1").val();
			if (cityCode != 0) {
				$('#main-content').postUrl(Main.contextPath + '/BS2101105/findDistrict', {cityCode: cityCode}, function (data2) {
					$.each(data2, function (i, item) {
						districtSelect.append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
					});
				}, {refreshHtml: false})
			}
		});
	},
	//暂时无效按钮
	detailData: function () {
		$("#BS2101111_return" ).click(function () {
		});
	}
}
$(document).ready(function () {
	// 初始化调用
	BS2101111.init();
	$(function () {
		$("a").each(function () {
			$(this).attr("href",encodeURI($(this).attr("href")))
		})
	})
});
