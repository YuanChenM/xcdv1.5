var NUM = {
	"NUM_ZERO" : 0,
	"NUM_ONE" : 1,
	"NUM_TWO" : 2,
	"NUM_THREE" : 3,
	"NUM_FOUR" : 4,
	"NUM_FIVE" : 5,
	"NUM_SIX" : 6,
	"NUM_SEVEN" : 7,
	"NUM_EIGHT" : 8,
	"NUM_NINE" : 9,
	"NUM_TEN" : 10
}
/**
 * 产品基础数据列表JS
 *
 * @author gyh
 */
var SP171151 = {
	$List_Grid: '',
	formId: "SP171151Form",
	saveButtonId: "SP171151_SAVE",
	initDataGrid: function () {
		SP171151.bindSaveButton();
	},
	bindSaveButton: function () {
		$("#" + SP171151.saveButtonId).click(function () {
			var flag = true;
			$('#SP171151Tbody').find('tr').each(function () {
				$(this).find('td').each(function () {
					$(this).find("input").each(function(){
						var color = $(this).css("background-color");
						if(color == 'rgb(255, 0, 0)')
						{
							flag = false;
							return;
						}
					})
					if(!flag)
					{
						return;
					}
				});
				if(!flag)
				{
					return;
				}
			});

			if(!flag)
			{
				alert("请检查参数是否填写正确！！！");
				return;
			}

			var $SP171151Form = $("#SP171151Form");
			var formData = $SP171151Form.serializeArray();//getFormData($("#SO151408Form"));
			$('#main-content').postUrl($("#SP171151Form").attr("action"),formData);
		});
	}
}

/**
 *等级标准(箱下限修改)
 * @param index
 */
function wayGradeStartChange()
{
	for(var i = NUM.NUM_ZERO;i <= NUM.NUM_NINE;i++)
	{
		if(!wayGradeStartChangeIndex(i))
		{
			alert("修改不符合规则！！！");
			$("#wayGradeStart" + i).css("background-color","#FF0000");
			return ;
		}
		$("#wayGradeStart" + i).css("background-color","");
	}
}

function wayGradeStartChangeIndex(index)
{
	var wayGradeStart = $("#wayGradeStart" + index).val();
	var reg = /^[1-9]\d*$/;

	if(!reg.test(wayGradeStart))
	{
		return false;
	}

	wayGradeStart = parseInt(wayGradeStart);

	if(index == NUM.NUM_ZERO)
	{
		wayGradeEnd = $("#wayGradeEnd" + index).val();
		if(wayGradeEnd < wayGradeStart)
		{
			return false;
		}
	}

	var beforeStandMaxIndex = index - NUM.NUM_ONE;
	if(index >= NUM.NUM_ZERO)
	{
		var beforewayGradeStart = parseInt($("#wayGradeStart" + beforeStandMaxIndex).val());
		if(beforewayGradeStart <= wayGradeStart)
		{
			return false;
		}
	}

	if(index == NUM.NUM_NINE)
	{
		return true;
	}

	var wayGradeEndIndex = index + NUM.NUM_ONE;

	var lastwayGradeStart = parseInt($("#wayGradeStart" + wayGradeEndIndex).val());


	if(wayGradeStart <= lastwayGradeStart)
	{
		return false;
	}

	var wayGradeEnd = wayGradeStart - NUM.NUM_ONE;
	$("#wayGradeEnd" + wayGradeEndIndex).val(wayGradeEnd);
	return true;
}

$(document).ready(function () {
	// 初始化调用
	SP171151.initDataGrid();
});


/**
 * 本期报价变更
 * @param index
 */
function wayGradePriceChange(index)
{
	var wayGradePrice = $("#wayGradePrice" + index).val();

	var reg = /^\d+(\.\d{1,2})?$/;

	if(!reg.test(wayGradePrice))
	{
		alert("请输入正确的数！！！");
		$("#wayGradePrice" + index).css("background-color","#FF0000");
		return;
	}
	var level5wayGradePrice =$("#wayGradePrice5").val()

	if(!reg.test(level5wayGradePrice))
	{
		alert("请输入正确的数！！！");
		$("#wayGradePrice5" + index).css("background-color","#FF0000");
		return;
	}

	level5wayGradePrice = parseFloat(level5wayGradePrice);
	wayGradePrice = parseFloat(wayGradePrice);

	if(index == NUM.NUM_FIVE )
	{
		editEverywayGradePrice(wayGradePrice);
		$("#wayGradePrice" + index).css("background-color","#AA758B");
		$("#currentCoefficient" + index).css("background-color","#AA758B");
		return ;
	}

	if(level5wayGradePrice == NUM.NUM_ZERO)
	{
		alert("请输入正确的数！！！");
		$("#wayGradePrice" + index).css("background-color","#FF0000");
		return;
	}

	$("#wayGradePrice" + index).css("background-color","#FFFFFF");
	$("#currentCoefficient" + index).css("background-color","#FFFFFF");

	var level5CurrentCoefficient = parseInt($("#currentCoefficient5").val());
	var currentCoefficient = Math.round((wayGradePrice/level5wayGradePrice)*level5CurrentCoefficient);

	$("#currentCoefficient" + index).val(currentCoefficient);
}

/**
 * 本期价盘等级系数变更
 * @param index
 */
function currentCoefficientChange(index)
{
	//5级报价
	var level5wayGradePrice = parseFloat($("#wayGradePrice5").val());

	//本期系数
	var currentCoefficient = $("#currentCoefficient" + index).val();

	var reg = /^[1-9]\d*$/;
	if(!reg.test(currentCoefficient))
	{
		alert("修改不符合规则！！！");
		$("#currentCoefficient" + index).css("background-color","#FF0000");
		return ;
	}
	currentCoefficient = parseInt(currentCoefficient);

	var level5CurrentCoefficient = parseInt($("#currentCoefficient5").val());
	//本期报价
	var wayGradePrice = (parseFloat(level5wayGradePrice)*(parseFloat(currentCoefficient)/level5CurrentCoefficient)).toFixed(2);

	if(wayGradePrice > 9999999)
	{
		alert("修改不符合规则！！！");
		$("#currentCoefficient" + index).css("background-color","#FF0000");
		return ;
	}

	$("#currentCoefficient" + index).css("background-color","#FFFFFF");
	$("#wayGradePrice" + index).css("background-color","#FFFFFF");
	$("#wayGradePrice" + index).val(wayGradePrice);
}

function editEverywayGradePrice(wayGradePrice)
{
	var level5CurrentCoefficient = parseInt($("#currentCoefficient5").val());

	for(var i = NUM.NUM_ZERO ;i < NUM.NUM_TEN ; i++)
	{
		var currentCoefficient = parseInt($("#currentCoefficient" + i).val());

		$("#wayGradePrice" + i).css("background-color","#FFFFFF");
		$("#currentCoefficient" + i).css("background-color","#FFFFFF");
		var price = parseFloat((wayGradePrice/level5CurrentCoefficient)*currentCoefficient).toFixed(2) ;
		$("#wayGradePrice"+ i).val(price);
		if(i != NUM.NUM_FIVE)
		{
			wayGradePriceChange(i);
		}
	}
}
function isValidChange(index)
{
	if($("#Valid" + index).attr("checked"))
	{
		$("#Valid" + index).removeAttr("checked");
		$("#isValid" + index).val(NUM.NUM_ZERO);
		$("#wayGradeName" + index).css("background-color","#DBDBDB");
		$("#wayGradeCodeTd" + index).css("background-color","#DBDBDB");
	}
	else
	{
		$("#Valid" + index).attr("checked","checked");
		$("#isValid" + index).val(NUM.NUM_ONE);
		$("#wayGradeName" + index).css("background-color","#AA7428");
		$("#wayGradeCodeTd" + index).css("background-color","#AA7428");
	}
}


function disCountChange(index)
{
	var disCount = $("#disCount" + index).val();

	var reg = /^\d+(\.\d{1,2})?$/;
	if(!reg.test(disCount))
	{
		$("#disCount" + index).css("background-color","#FF0000");
		alert("请输入正确的数！！！");
		return ;
	}
	disCount = parseFloat(disCount);
	if(disCount <= NUM.NUM_ZERO || disCount >= NUM.NUM_SIX)
	{
		$("#disCount" + index).css("background-color","#FF0000");
		alert("请输入正确的数！！！");
		return ;
	}

	$("#disCount" + index).css("background-color","#FFFFFF");
}