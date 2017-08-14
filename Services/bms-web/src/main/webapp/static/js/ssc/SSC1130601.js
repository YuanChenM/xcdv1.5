/**
 * 发货订单明细-产品选择
 *
 * @author yang_yang
 */
var SSC1130601 = {
    SSC1130601Grid: null,
    formId:"SSC1130601Form",
    init: function () {
        this.bindButton();
        this.bindProductChange();
        this.inputBlur();
    },
    bindButton:function() {
        $("#SSC1130601_SAVE").click(function(){
            if($("#productCode").val() == ""){
                $.alertMessage.info("请选择产品");
            }else if(!SSCCommon.POSITIVE_INTEGER.test($("#productBox").val())){
                $.alertMessage.info("发货箱数不能为空，且必须是正整数！");
            }else if($("#productBox").val() > SSCCommon.INT11){
                $.alertMessage.info("发货箱数不能超过99999999，请重新输入！");
            }else if(!SSCCommon.WEIGHT_REG.test($("#productQua").val())){
                $.alertMessage.info("发货数量格式错误（整数位最多16位，小数位最多4位）！");
            }else if($("#trunkFreight").val().trim() != "" && !SSCCommon.MONEY_REG.test($("#trunkFreight").val())){
                $.alertMessage.info("干线运费格式错误（整数位最多18位，小数位最多2位）！");
            }else if($("#cif").val().trim() != "" && !SSCCommon.MONEY_REG.test($("#cif").val())){
                $.alertMessage.info("到岸价格式错误（整数位最多18位，小数位最多2位）！");
            }else if(!SSCCommon.isMoney($("#settkementStandardPrice").val())){
                $.alertMessage.info("结算标准价不能为空或0，且格式为：整数位最多18位，小数位最多2位！");
            }else if(!SSCCommon.MONEY_REG.test($("#productValue").val())){
                $.alertMessage.info("货值格式错误（整数位最多18位，小数位最多2位）！");
            }else if($("#remark").val().length > 100){
                $.alertMessage.info("备注长度超过100字符，请重新输入");
            }else{
                /*if($("#settkementStandardPrice").val() - $("#trunkFreight").val() < 0){
                 $.alertMessage.info("干线运费应小于结算标准价");
                 return;
                 }*/
                var allData = $List_Grid.fnGetData();
                //是否添加已存在列表中的数据
                var flag = true;
                for (var i = 0; i < allData.length; i++) {
                    if($("#productCode").val() == allData[i]["pdCode"]){
                        flag = false;
                        $.alertMessage.confirm("该产品已经存在，是否要替换该产品其他信息？", function() {
                            $.alertMessage.close();
                            $("#detailId").val(allData[i]["detailId"]);
                            $("#pdVer").val(allData[i]["ver"]);
                            formData = getFormData($("#SSC1130601Form"));
                            $('#main-content').postUrl(Main.contextPath + "/SSC11306/modifyOrderPdInfo", formData ,function(data) {
                                if(data == "S") {
                                    $('#main-content').postUrl(Main.contextPath + "/SSC11306/init/" , {
                                        "deliveryId": $('#deliveryId').val()
                                    },Main.hiddenHeader);
                                    $.pdialog.close();
                                } else {
                                    $.alertMessage.warn("请刷新页面再试!");
                                }
                            },{refreshHtml:false});
                        });

                        break;
                    }
                }

                if(flag) {
                    //查询未删除的产品数据
                    $("#delFlg").val(0);
                    formData = getFormData($("#SSC1130601Form"));
                    $('#main-content').postUrl(Main.contextPath + "/SSC11306/findOrderPd", formData, function (data) {
                        if (data.detailId != null) {
                            $.alertMessage.warn("该数据已经被添加，请刷新页面再试!");
                        } else {
                            $('#main-content').postUrl(Main.contextPath + "/SSC11306/saveOrderPd", formData, function (data) {
                                if (data == "S") {
                                    $('#main-content').postUrl(Main.contextPath + "/SSC11306/init/", {
                                        "deliveryId": $('#deliveryId').val()
                                    }, Main.hiddenHeader);
                                    $.pdialog.close();
                                } else {
                                    $.alertMessage.warn("请刷新页面再试!");
                                }
                            }, {refreshHtml: false});
                        }
                    }, {refreshHtml: false});
                }

            }
        });
    },
    /** 选择产品时触发 */
    bindProductChange: function() {
        $("#productCode").change(function() {
            /** 设置产品等级、代码和名称 */
            var $productCode = $("#productCode").find("option:selected");

            $("#classesCode").val($productCode.attr("classes_code"));
            $("#classesName").val($productCode.attr("classes_name"));
            $("#machiningCode").val($productCode.attr("machining_code"));
            $("#machiningName").val($productCode.attr("machining_name"));
            $("#breedCode").val($productCode.attr("breed_code"));
            $("#breedName").val($productCode.attr("breed_name"));
            $("#featureCode").val($productCode.attr("feature_code"));
            $("#featureName").val($productCode.attr("feature_name"));
            $("#weightCode").val($productCode.attr("weight_code"));
            $("#weightName").val($productCode.attr("weight_name"));
            $("#gradeCode").val($productCode.attr("grade_code"));
            $("#gradeName").val($productCode.attr("grade_name"));
            $("#normsCode").val($productCode.attr("norms_code"));
            $("#normsName").val($productCode.attr("norms_name"));
            $("#weightVal").val($productCode.attr("weight_val"));
            $("#pdDesc").val($productCode.attr("pd_name"));
            $("#brandId").val($productCode.attr("brandId"));
            $("#brandName").val($productCode.attr("brandName"));
            $("#brandEpId").val($productCode.attr("brandEpId"));

            var productQua = SSCCommon.multiply($("#weightVal").val() , $("#productBox").val());
            $("#productQua").val(SSCCommon.roundFixed(productQua,4));
            $("#productQuaShow").val(fmoney(SSCCommon.roundFixed(productQua,4),4));

            var productValue = SSCCommon.multiply($("#productQua").val() , $("#settkementStandardPrice").val());
            $("#productValue").val(SSCCommon.roundFixed(productValue,2));
            $("#productValueShow").val(SSCCommon.formatMoney(SSCCommon.roundFixed(productValue,2)));

        });
    },
    inputBlur: function(){
        $("#productBoxShow").blur(function(){
            var productBox = SSCCommon.clearComma($("#productBoxShow").val());

            $("#productBox").val(productBox);
            $("#productBoxShow").val(fmoney(productBox,0));

            var productQua = SSCCommon.multiply($("#weightVal").val() , productBox);
            $("#productQua").val(SSCCommon.roundFixed(productQua,4));
            $("#productQuaShow").val(fmoney(SSCCommon.roundFixed(productQua,4),4));
            if($("#settkementStandardPrice").val() != ''){
                var productValue = SSCCommon.multiply($("#productQua").val() , $("#settkementStandardPrice").val());
                $("#productValue").val(SSCCommon.roundFixed(productValue,2));
                $("#productValueShow").val(SSCCommon.formatMoney(SSCCommon.roundFixed(productValue,2)));
            }
        });
        $("#trunkFreightShow").blur(function(){
            var trunkFreight = SSCCommon.clearComma($("#trunkFreightShow").val());

            $("#trunkFreight").val(trunkFreight);
            $("#trunkFreightShow").val(fmoney(trunkFreight,2));
        });
        $("#cifShow").blur(function(){
            var cif = SSCCommon.clearComma($("#cifShow").val());

            $("#cif").val(cif);
            $("#cifShow").val(fmoney(cif,2));
        });
        $("#settkementStandardPriceShow").blur(function(){
            var settkementStandardPrice = SSCCommon.clearComma($("#settkementStandardPriceShow").val());

            $("#settkementStandardPrice").val(settkementStandardPrice);
            $("#settkementStandardPriceShow").val(fmoney(settkementStandardPrice,2));

            var productValue = SSCCommon.multiply($("#productQua").val() , settkementStandardPrice);
            $("#productValue").val(SSCCommon.roundFixed(productValue,2));
            $("#productValueShow").val(SSCCommon.formatMoney(SSCCommon.roundFixed(productValue,2)));
        });
    }
}

$(document).ready(function () {
    // 初始化调用
    SSC1130601.init();
});

