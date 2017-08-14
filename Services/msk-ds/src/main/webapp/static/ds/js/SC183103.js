/**
 * 实际录入JS
 * Created by zhou_yajun.
 */
var SC183103 = {
    saveButtonId: "SC183103_SAVE",
    formId:"SC183103Form",
    initDataGrid: function () {
        SC183103.bindSaveButton();
        SC183103.bindInputChange();
        SC183103.bindSelectChange();
        SC183103.clearComma();
        SC183103.formatNumber();
    },
    bindInputChange:function(){
        //Modify for 3487 at 2016/10/27 by likai Start
        // $("input[id *= 'changeNum']").change(function(){
        $("input[id *= 'changeBoxes']").change(function(){
            var id = this.id;
            var productCode = id.substring(11,id.length);
            var inputBoxes =$("#" + id).val();
            var pdOutNw = Number($("#pdOutNw" + productCode).val().toString());
            if ((!/^-?[1-9]\d*$/.test(inputBoxes) && inputBoxes != '0') || Number($("#"+id).val().toString()).toFixed(0) > 99999999999999) {
                $("#"+id).val(0);
                $.alertMessage.info("调整箱数必须为整数,最大长度14!");
            }
            var changeBoxes = Number($("#"+id).val().toString());
            //Modify for 3077 at 2016/11/01 by likai Start
            var changeNum = ((changeBoxes * pdOutNw * 1000) / 1000).toFixed(2);
            var changeBeforeNumPoints = $("#changeBeforeNum"+productCode).val();
            var changeBeforeNum = Number(SC183103.clearComma(changeBeforeNumPoints.trim()));
            var changeOverNum = ((changeNum * 1000 + changeBeforeNum * 1000) / 1000).toFixed(2);
            var changeNumPoints = SC183103.formatNumber(changeNum, 2 ,1);
            var changeOverNumPoints = SC183103.formatNumber(changeOverNum, 2 ,1);
            $("#changeOverNum"+productCode).val(changeOverNumPoints);
            $("#changeNum"+productCode).val(changeNumPoints);

            //Modify for 3077 at 2016/11/01 by likai End
            if (changeOverNum > 99999999999999.99){
                $.alertMessage.info("调整后的值的长度不能大于14(可带两位小数)!");
                $("#"+id).val("0");
                $("#changeOverNum"+productCode).val(changeBeforeNumPoints);
                $("#changeNum" + productCode).val("0.00");
            }
            /**改变合计的值*/
            var sumChange=0;
            var sumChangeOverNum=0;
            var sumChangeBoxes = 0;
            $("input[id *= 'changeNum']").each(function(){
                var clearSumChange = SC183103.clearComma($(this).val().trim());
                sumChange = (sumChange * 1000 + Number(clearSumChange.toString()) * 1000) / 1000;
            });
            $("input[id *= 'changeOverNum']").each(function(){
                var clearSumChangeOverNum = SC183103.clearComma($(this).val().trim());
                sumChangeOverNum =(sumChangeOverNum * 1000 + Number(clearSumChangeOverNum.toString()) * 1000) / 1000;
            });
            $("input[id *= 'changeBoxes']").each(function () {
                sumChangeBoxes += Number($(this).val());
            });
            var sumChangePoints = SC183103.formatNumber(sumChange, 2 ,1);
            var sumChangeOverPoints = SC183103.formatNumber(sumChangeOverNum, 2 ,1);
            $("#sumChangeNum").val(sumChangePoints);
            $("#sumChangeOverNum").val(sumChangeOverPoints);
            $("#sumChangeBoxes").val(sumChangeBoxes);
            //Modify for 3487 at 2016/10/27 by likai End
        });
        $("a[name='SC183103A']").bind("click", function() {
            $.pdialog.close();
            $('#main-content').postUrl(Main.contextPath + "/SC183104/init/");
        });
    },
    bindSaveButton:function(){
        $("#"+SC183103.saveButtonId).bind("click", function() {
            // var validator = mainValidation($("#" + SC183103.formId));
            // var isValid = validator.form();
            if ($("#" + SC183103.formId).validateForm()) {
                formData = getFormData($("#" + SC183103.formId));
                var formSize = $("input[name *= 'changeNum']").size();
                for (var i=0;i<formSize;i++){
                    formData["productParamList["+i+"].changeBeforeNum"] = SC183103.clearComma(formData["productParamList["+i+"].changeBeforeNum"]);
                    formData["productParamList["+i+"].changeOverNum"] = SC183103.clearComma(formData["productParamList["+i+"].changeOverNum"]);
                    formData["productParamList["+i+"].changeNum"] = SC183103.clearComma(formData["productParamList["+i+"].changeNum"]);
                }
                var changeBoxes = formData.changeBoxes;
                var saveFlg = false;
                if (isNaN(changeBoxes)) {
                    for (var key in changeBoxes) {
                        if (changeBoxes[key] != '0'){
                            saveFlg = true;
                            break;
                        }
                    }
                } else if (changeBoxes != 0){
                    saveFlg = true;
                }
                if (saveFlg) {
                    $('#main-content').postUrl($("#" + SC183103.formId).attr("action"), formData, function(data){
                            $.pdialog.close();
                            if(data =="1"){
                                $('#main-content').postUrl(Main.contextPath + "/SC181102/init/",
                                    {
                                        distMonth:$("#distMonth").val(),
                                        areaCode:$("#areaCode").val(),
                                        supplierCode:$("#supplierCode").val(),
                                        productName:$("#productName").val()
                                    });
                            } else {                               
			    	            SC183104.SC183104Grid.fnDraw(1);
                            }
                        },
                        {refreshHtml:false});
                }else {
                    $.alertMessage.info("请输入调整箱数!");
                    return;
                }
            }
        });
    },
    //Add for 3077 at 2016/11/01 by likai Start
    formatNumber:function(num,cent,isThousand){
        if(isNaN(num))num = "0";
        // 获取符号(正/负数)
        sign = (num == (num = Math.abs(num)));

        num = Math.floor(num*Math.pow(10,cent)+0.50000000001);  // 把指定的小数位先转换成整数.多余的小数位四舍五入
        cents = num%Math.pow(10,cent);              // 求出小数位数值
        num = Math.floor(num/Math.pow(10,cent)).toString();   // 求出整数位数值
        cents = cents.toString();               // 把小数位转换成字符串,以便求小数位长度

        // 补足小数位到指定的位数
        while(cents.length<cent)
            cents = "0" + cents;

        if(isThousand) {
            // 对整数部分进行千分位格式化.
            for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
                num = num.substring(0,num.length-(4*i+3))+','+ num.substring(num.length-(4*i+3));
        }

        if (cent > 0)return (((sign)?'':'-') + num + '.' + cents);
        else
            return (((sign)?'':'-') + num);


    },
    /**
     * 清除千分位中的逗号
     */
    clearComma: function(moneyStr) {
        moneyStr = $.trim(moneyStr);
        if (moneyStr.length <= 0) {
            return "0.00";
        }
        return moneyStr.replaceAll(",", "");
    },
    //Add for 3077 at 2016/11/01 by likai End
    bindSelectChange:function(){
        $("#planType").change(function(){
            $('#main-content').postUrl(Main.contextPath + "/SC183103/selectChange/",
                {
                    distMonth:$("#distMonth").val(),
                    areaCode:$("#areaCode").val(),
                    areaName:$("#areaCode option:selected").text(),
                    supplierCode:$("#supplierCode").val(),
                    supplierName:$("#supplierCode option:selected").text(),
                    productName:$("#productFullName").val(),
                    entryMark:$("#entryMark").val(),
                    planType:$("#planType").val(),
                    currentHalfCode:$("#halfCode").val(),
                    adjustDate:$("#adjustDate").val()
                },function(data){
                    var SC183103Table = $("#SC183103Table");
                    SC183103Table.html('');
                    var strHtml = "";
                    strHtml = strHtml + "<thead>";
                    strHtml = strHtml + "<tr style='background:#DBDBDB'><th>规格</th><th>调整前值</th><th>调整值</th> <th>调整后值</th></tr>";
                    strHtml = strHtml + "</thead>";
                    strHtml = strHtml + "<tbody>";
                    $.each(data, function(i, item) {
                        var planChangeProductList = item.planChangeProductList;
                        for(var j=0;j<planChangeProductList.length;j++){
                            var productName = planChangeProductList[j].productName;
                            var productCode = planChangeProductList[j].productCode;
                            var normsCode = planChangeProductList[j].normsCode;
                            var adjustDate = planChangeProductList[j].adjustDateOld;
                            var changeBeforeNum = planChangeProductList[j].changeBeforeNum;
                            var changeOverNum = planChangeProductList[j].changeOverNum;
                            strHtml = strHtml + "<tr>";
                            strHtml = strHtml + "<td style='background:#DBDBDB'>"+productName;
                            strHtml = strHtml + "<input type='hidden' name='productParamList["+j+"].productCode' id='productCode"+productCode+"' value='"+productCode+"'/>";
                            strHtml = strHtml + "<input type='hidden' name='productParamList["+j+"].normsCode' id='normsCode"+productCode+"' value='"+normsCode+"'/>";
                            strHtml = strHtml + "<input type='hidden' name='productParamList["+j+"].adjustDate' id='adjustDate"+productCode+"' value='"+adjustDate+"'/>";
                            strHtml = strHtml + "</td>"
                            strHtml = strHtml + "<td><input type='text' readonly='readonly' style='background:#DBDBDB' name='productParamList["+j+"].changeBeforeNum' id='changeBeforeNum"+productCode+"' value='"+changeBeforeNum+"'/></td>";
                            strHtml = strHtml + "<td><input type='text' name='productParamList["+j+"].changeNum' id='changeNum"+productCode+"' value='0.00'/></td>";
                            strHtml = strHtml + "<td><input type='text' readonly='readonly' style='background:#DBDBDB' name='productParamList["+j+"].changeOverNum' id='changeOverNum"+productCode+"' value='"+changeBeforeNum+"'/></td>";
                            strHtml = strHtml + "</tr>";
                        }
                        strHtml = strHtml + "<tr>";
                        strHtml = strHtml + "<td style='background:#DBDBDB'>合计</td>";
                        strHtml = strHtml + "<td><input type='text' name='sumChangeBeforeNum' id='sumChangeBeforeNum' value='"+item.sumChangeBeforeNum+"' readonly='readonly' style='background:#DBDBDB'/></td>";
                        strHtml = strHtml + "<td><input type='text' name='sumChangeNum' id='sumChangeNum' value='0.00'  readonly='readonly'  style='background:#DBDBDB'/></td>";
                        strHtml = strHtml + "<td><input type='text' name='sumChangeOverNum' id='sumChangeOverNum' value='"+item.sumChangeBeforeNum+"' readonly='readonly' style='background:#DBDBDB'/></td>";
                        strHtml = strHtml + "</tr>";
                        strHtml = strHtml + "</tbody>";
                        SC183103Table.html(strHtml);
                    });
                    SC183103.bindInputChange();
                },{refreshHtml:false}
            );
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    SC183103.initDataGrid();
});
