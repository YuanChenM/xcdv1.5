/**
 * Created by peng_hao on 2016/4/29.
 */

var SP171102 = {
	init: function () {
		SP171102.bindSaveOrder();
		numChangeCommon();
	},


	bindSaveOrder: function () {
		$("#SP171102_SAVE").click(function () {
			var publishTotalNum = $("#publishTotalNum").val();
			var gradeRatio1 = $("#gradeRatio1").val();
			var gradeRatio2 = $("#gradeRatio2").val();
			var gradeRatio3 = $("#gradeRatio3").val();
			if(publishTotalNum==""){
				$.alertMessage.warn("请填写需求总量");
				return;
			}
			if(publishTotalNum.length>9) {
				$.alertMessage.warn("需求总量的值超出范围");
				return;
			}
			if(gradeRatio1==""){
				$.alertMessage.warn("请填写A1比例");
				return;
			}
			if(gradeRatio2==""){
				$.alertMessage.warn("请填写A2比例");
				return;
			}
			if(gradeRatio3==""){
				$.alertMessage.warn("请填写A3比例");
				return;
			}
			var total=parseInt(gradeRatio1)+parseInt(gradeRatio2)+parseInt(gradeRatio3);
			if(total!=100){
				$.alertMessage.warn("比例错误，请重新填写！");
				return;
			}

			$.alertMessage.confirm("你确定要保存当前数据吗？", function () {
				$.alertMessage.close();
				var gread = document.getElementById("greadList").children;
				var greadSize = gread.length;
				$("#greadSize").val(greadSize);
				var $SP171102Form = $("#SP171102Form");
				//var formData = $SP171102Form.serializeArray();
				formData = getFormData($SP171102Form);
				$('#main-content').postUrl($("#SP171102Form").attr("action"), formData,function() {
					$.alertMessage.info("数据已保存");
				});
			});
		});
		/** Modfiy:  一览页面到详情页面打开方式横展开,将返回按钮注释   2016/10/11   BY  zhukai1  Start */
		/*$("#SP171102_BACK").click(function () {
			$('#main-content').postUrl(Main.contextPath + "/SP171101/init/",{demandYearmonth:publishYm.value}, Main.hiddenHeader);
		});*/
		/** Modfiy:  一览页面到详情页面打开方式横展开,将返回按钮注释   2016/10/11   BY  zhukai1  end */
	}
}


function numChangeCommon(){
	var publishTotalNum = $("#publishTotalNum").val();
	var gradeRatio1 = $("#gradeRatio1").val();
	var gradeRatio2 = $("#gradeRatio2").val();
	var gradeRatio3 = $("#gradeRatio3").val();
	var way1A1 = $("#way1A1").val();
	var way1A2 = $("#way1A2").val();
	var way1A3 = $("#way1A3").val();
	var way1Total = $("#way1Total").val();

	var way2A1 = $("#way2A1").val();
	var way2A2 = $("#way2A2").val();
	var way2A3 = $("#way2A3").val();
	var way2Total = $("#way2Total").val();

	var way3A1 = $("#way3A1").val();
	var way3A2 = $("#way3A2").val();
	var way3A3 = $("#way3A3").val();
	var way3Total = $("#way3Total").val();

	var A1Total = $("#A1Total").val();
	var A2Total = $("#A2Total").val();
	var A3Total = $("#A3Total").val();
	var ratioTypeDetailVal1 = $("#ratioTypeDetailVal1").val();
	var ratioTypeDetailVal2 = $("#ratioTypeDetailVal2").val();
	var ratioTypeDetailVal3 = $("#ratioTypeDetailVal3").val();
	var totalNum = $("#totalNum").val();
	if(publishTotalNum!="" && publishTotalNum!=0){
		$("#gradeRatio11").val(gradeRatio1);
		$("#gradeRatio22").val(gradeRatio2);
		$("#gradeRatio33").val(gradeRatio3);
		var way1A1Temp=publishTotalNum*gradeRatio1/100*ratioTypeDetailVal1/100;
		$("#way1A1").val(Math.round(way1A1Temp*100)/100);
		var way1A2Temp=publishTotalNum*gradeRatio2/100*ratioTypeDetailVal1/100;
		$("#way1A2").val( Math.round(way1A2Temp*100)/100);
		var way1A3Temp=publishTotalNum*gradeRatio3/100*ratioTypeDetailVal1/100;
		$("#way1A3").val(Math.round(way1A3Temp*100)/100);
		var way1TotalTemp=way1A1Temp+way1A2Temp+way1A3Temp
		$("#way1Total").val(Math.round(way1TotalTemp*100)/100);
		var way2A1Temp=publishTotalNum*gradeRatio1/100*ratioTypeDetailVal2/100;
		$("#way2A1").val(Math.round(way2A1Temp*100)/100);
		var way2A2Temp=publishTotalNum*gradeRatio2/100*ratioTypeDetailVal2/100;
		$("#way2A2").val(Math.round(way2A2Temp*100)/100);
		var way2A3Temp=publishTotalNum*gradeRatio3/100*ratioTypeDetailVal2/100;
		$("#way2A3").val(Math.round(way2A3Temp*100)/100);
		var way2TotalTemp=way2A1Temp+way2A2Temp+way2A3Temp
		$("#way2Total").val(Math.round(way2TotalTemp*100)/100);
		var way3A1Temp=publishTotalNum*gradeRatio1/100*ratioTypeDetailVal3/100;
		$("#way3A1").val(Math.round(way3A1Temp*100)/100);
		var way3A2Temp=publishTotalNum*gradeRatio2/100*ratioTypeDetailVal3/100;
		$("#way3A2").val(Math.round(way3A2Temp*100)/100);
		var way3A3Temp=publishTotalNum*gradeRatio3/100*ratioTypeDetailVal3/100;
		$("#way3A3").val(Math.round(way3A3Temp*100)/100);
		var way3TotalTemp=way3A1Temp+way3A2Temp+way3A3Temp
		$("#way3Total").val(Math.round(way3TotalTemp*100)/100);
		var A1TotalTemp=way1A1Temp+way2A1Temp+way3A1Temp;
		$("#A1Total").val(Math.round(A1TotalTemp*100)/100);
		var A2TotalTemp=way1A2Temp+way2A2Temp+way3A2Temp;
		$("#A2Total").val(Math.round(A2TotalTemp*100)/100);
		var A3TotalTemp=way1A3Temp+way2A3Temp+way3A3Temp;
		$("#A3Total").val(Math.round(A3TotalTemp*100)/100);
		var totalNumTemp=A1TotalTemp+A2TotalTemp+A3TotalTemp;
		$("#totalNum").val(Math.round(totalNumTemp*100)/100);
	}else{
		$("#way1A1").val("");
		$("#way1A2").val("");
		$("#way1A3").val("");
		$("#way1Total").val("");
		$("#way2A1").val("");
		$("#way2A2").val("");
		$("#way2A3").val("");
		$("#way2Total").val("");
		$("#way3A1").val("");
		$("#way3A2").val("");
		$("#way3A3").val("");
		$("#way3Total").val("");
		$("#A2Total").val("");
		$("#A2Total").val("");
		$("#A2Total").val("");
		$("#totalNum").val("");
	}
}
function numChangeTotalNum(){
	var publishTotalNum = $("#publishTotalNum").val();
	var reg = /^[1-9]\d*$/;
	var flag=$("#canSetFlg").val();
	if(flag=="Y") {
		if(publishTotalNum != ""){
			if(!reg.test(publishTotalNum)){
				$.alertMessage.warn("请输入正整数");
				$("#publishTotalNum").val("");
				return;
			}
		}
	}
	numChangeCommon();
}
function numChange1(){
	var publishTotalNum = $("#publishTotalNum").val();
	var gradeRatio1 = $("#gradeRatio1").val();
	var reg = /^([0-9]\d?|100)$/ ;
	var flag=$("#canSetFlg").val();
	if(flag=="Y") {
		if(gradeRatio1!=""){
			if(publishTotalNum!=""){
				if(!reg.test(gradeRatio1)){
					$.alertMessage.warn("请输入1到100之间的正整数");
					$("#gradeRatio1").val("");
					return;
				}
			}else{
				$.alertMessage.warn("请先输入需求总量");
				$("#gradeRatio1").val("");
				return;
			}
		}
	}
	numChangeCommon();
}

function numChange2(){
	var publishTotalNum = $("#publishTotalNum").val();
	var gradeRatio2 = $("#gradeRatio2").val();
	var gradeRatio1 = $("#gradeRatio1").val();
	var reg = /^([0-9]\d?|100)$/ ;
	var flag=$("#canSetFlg").val();
	if(flag=="Y") {
		if (gradeRatio2 != "") {
			if (publishTotalNum != "" && gradeRatio1 != "") {
				if (!reg.test(gradeRatio2)) {
					$.alertMessage.warn("请输入1到100之间的正整数");
					$("#gradeRatio2").val("");
					return;
				}
			} else {
				$.alertMessage.warn("请先输入需求总量或A1比例");
				$("#gradeRatio2").val("");
				return;
			}
		}
	}
	numChangeCommon();
}
function numChange3(){
	var publishTotalNum = $("#publishTotalNum").val();
	var gradeRatio1 = $("#gradeRatio1").val();
	var gradeRatio2 = $("#gradeRatio2").val();
	var gradeRatio3 = $("#gradeRatio3").val();
	var reg = /^([0-9]\d?|100)$/ ;
	var flag=$("#canSetFlg").val();
	if(flag=="Y") {
		if(gradeRatio3!=""){
			if(publishTotalNum!=""&&gradeRatio1!=""&&gradeRatio2!=""){
				if(!reg.test(gradeRatio2)){
					$.alertMessage.warn("请输入1到100之间的正整数");
					$("#gradeRatio3").val("");
					return;
				}
			}else{
				$.alertMessage.warn("请先输入需求总量,A1比例或者A2比例");
				$("#gradeRatio3").val("");
				return;
			}
		}
	}
	numChangeCommon();
}

$(document).ready(function() {
	//初始化调用
	SP171102.init();
});
