/**
 *
 * Created ssc zhao_chen1 on 16/07/5.
 */

var SSC1130203 = {
    formId:"ssc1130203Form",
    initDataGrid: function () {
        this.bindSaveButton();
        this.bindBlurButton();

    },

    bindSaveButton:function(){
        $("#SSC1130203_SAVE" ).click(function(){
            var bidId = $("#bidId").val();
            //获取产品信息
            var pdSelectObj =$("#pdDesc").find("option:selected");
            var pdName = pdSelectObj.val();
            var weightVal = pdSelectObj.attr("weightVal");
            var pdClassesCode = pdSelectObj.attr("pdClassesCode");
            var pdClassesName = pdSelectObj.attr("pdClassesName");
            var machiningName = pdSelectObj.attr("machiningName");
            var machiningCode = pdSelectObj.attr("machiningCode");
            var pdBreedCode = pdSelectObj.attr("pdBreedCode");
            var pdBreedName = pdSelectObj.attr("pdBreedName");
            var pdFeatureCode = pdSelectObj.attr("pdFeatureCode");
            var pdFeatureName = pdSelectObj.attr("pdFeatureName");
            var weightCode = pdSelectObj.attr("weightCode");
            var weightName = pdSelectObj.attr("weightName");
            var slTncGradeCode = pdSelectObj.attr("slTncGradeCode");
            var slTncGradeName = pdSelectObj.attr("slTncGradeName");
            var normsCode = pdSelectObj.attr("normsCode");
            var normsOut = pdSelectObj.attr("normsOut");
            var standardId = pdSelectObj.attr("standardId");
            var pdCode = pdSelectObj.attr("pdCode");
            var brandEpId = pdSelectObj.attr("brandEpId");
            var brandId = pdSelectObj.attr("brandId");
            var brandName = pdSelectObj.attr("brandName");

            var productBox = $("#productBox").val();
            var remark = $("#remark").val();
            var settkementStandardPrice = $("#settkementStandardPrice").val();
            var fobIncludePackage = $("#fobIncludePackage").val();
            var packageCost = $("#packageCost").val();
            var fobFreePackage = $("#fobFreePackage").val();
            var trunkFreight = $("#trunkFreight").val();
            var cif = $("#cif").val();

            if(!SSC1130203.validateData()){
                return;
            }

            var products = $List_Grid.fnGetData();
            var flag = false;
            var productId;
            for (var i in products) {
                if (products[i].pdCode == pdCode) {
                    flag = true;
                    productId = products[i].detailId;
                    break;
                }
            }

            var message;
            var detailId;
            if (flag) {
                message = "当前产品已存在，是否替换该产品信息？";
                detailId = productId;
            }else {
                message = "你确定要保存当前数据吗？";
            }

            $.alertMessage.confirm(message, function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SSC1130203/insertBidProductDetail/",{
                    detailId:detailId,
                    bidId:bidId,
                    pdDesc:pdName,
                    weightVal:weightVal,
                    pdCode:pdCode,
                    classesCode:pdClassesCode,
                    classesName:pdClassesName,
                    machiningName:machiningName,
                    machiningCode:machiningCode,
                    breedCode:pdBreedCode,
                    breedName:pdBreedName,
                    featureCode:pdFeatureCode,
                    featureName:pdFeatureName,
                    weightCode:weightCode,
                    weightName:weightName,
                    gradeCode:slTncGradeCode,
                    gradeName:slTncGradeName,
                    normsCode:normsCode,
                    normsName:normsOut,
                    productBox:SSCCommon.replaceComma(productBox),
                    fobFreePackage:SSCCommon.replaceComma(fobFreePackage),
                    packageCost:SSCCommon.replaceComma(packageCost),
                    fobIncludePackage:SSCCommon.replaceComma(fobIncludePackage),
                    trunkFreight:SSCCommon.replaceComma(trunkFreight),
                    cif:SSCCommon.replaceComma(cif),
                    settkementStandardPrice:SSCCommon.replaceComma(settkementStandardPrice),
                    remark:remark,
                    brandEpId: brandEpId,
                    brandId: brandId,
                    brandName: brandName
                },function(data2){
                    if(data2 != "F"){
                        $('#main-content').postUrl(Main.contextPath + "/SSC11302/insertBidBasicInfo/",{
                            bidId:bidId,
                            bidProjectNo: $("#bidProjectNo").val(),
                            bidStatus:'0',
                            type:$("#type").val()
                        },function(data3){
                            $('#main-content').postUrl(Main.contextPath + "/SSC11302/init/"+$("#type").val() , {
                                bidId:bidId
                            },Main.hiddenHeader);
                        }, {refreshHtml: false})
                        $("#bid").val(data2);
                        $.pdialog.close();
                    }
                },{refreshHtml: false})

            });
        });

        $('#pdDesc').change(function(){
            var weightVal = $("#pdDesc").find("option:selected").attr("weightVal");
            $("#weightVal").val(weightVal);
            SSC1130203.plusPrice();
        })
    },
    bindBlurButton:function(){
        $('#productBox').blur(function () {
            var num = SSCCommon.replaceComma(this.value);
            if(SSCCommon.NATURAL_NUMBER.test(num)){
                SSC1130203.plusPrice();
                $('#productBox').val(fmoney(num,0));
            }
        });
        $('#settkementStandardPrice').blur(function () {
                var num = SSCCommon.replaceComma(this.value);
                if(SSCCommon.NATURAL_NUMBER.test(num)){
                    SSC1130203.plusPrice();
                    $('#settkementStandardPrice').val(fmoney(num,2));
                }
        });

        $('#fobFreePackage').blur(function () {
            if(this.value!='' && SSCCommon.FORMAT_MONEY_REG.test(this.value) ){
                $('#fobFreePackage').val(fmoney(this.value,2));
            }
        });

        $('#packageCost').blur(function () {
            if(this.value!='' && SSCCommon.FORMAT_MONEY_REG.test(this.value) ){
                $('#packageCost').val(fmoney(this.value,2));
            }

        });

        $('#fobIncludePackage').blur(function () {
            if(this.value!='' && SSCCommon.FORMAT_MONEY_REG.test(this.value) ){
                $('#fobIncludePackage').val(fmoney(this.value,2));
            }
        });

        $('#trunkFreight').blur(function () {
            if(this.value!='' && SSCCommon.FORMAT_MONEY_REG.test(this.value) ){
                $('#trunkFreight').val(fmoney(this.value,2));
            }
        });

        $('#cif').blur(function () {
            if(this.value!='' && SSCCommon.FORMAT_MONEY_REG.test(this.value) ){
                $('#cif').val(fmoney(this.value,2));
            }
        });
    },
    plusPrice:function(){
        var productQua = 0;
        var productValue = 0;
        var productBox = SSCCommon.clearComma($("#productBox").val());
        productBox = productBox == "" ? 0 : productBox;
        var weightVal = $("#weightVal").val();
        weightVal = weightVal == "" ? 0 : weightVal;
        var settkementStandardPrice = SSCCommon.clearComma($("#settkementStandardPrice").val());
        settkementStandardPrice = settkementStandardPrice == "" ? 0 : settkementStandardPrice;
        if(SSCCommon.POSITIVE_INTEGER.test(productBox)){
            productQua = SSCCommon.divide(SSCCommon.multiply(productBox,weightVal),1000);
            $("#productQua").val(fmoney(productQua,4));
        }
        if(SSCCommon.POSITIVE_INTEGER.test(productBox) && SSCCommon.isMoney(settkementStandardPrice)){
            productValue = SSCCommon.multiply(SSCCommon.multiply(productBox,weightVal),settkementStandardPrice);
            $("#productValue").val(fmoney(productValue,2));
        }
    },

    validateData:function(){
        var pdSelectObj =$("#pdDesc").find("option:selected");
        var productBox = SSCCommon.replaceComma($("#productBox").val());
        var remark = $("#remark").val();
        var settkementStandardPrice = SSCCommon.replaceComma($("#settkementStandardPrice").val());
        var pdName = pdSelectObj.val();

        if(pdName==undefined || pdName==""){
            $.alertMessage.info("请选择产品！");
            return false;
        }
        if (!SSCCommon.POSITIVE_INTEGER.test(productBox)) {
            $.alertMessage.info("箱数不能为空，且必须是大于0的整数！");
            return false;
        }
        if(productBox > SSCCommon.INT11){
            $.alertMessage.info("箱数不能超过99999999！");
            return false;
        }

        var fobFreePackage = $("#fobFreePackage").val();
        if(fobFreePackage!=''){
            fobFreePackage = SSCCommon.clearComma(fobFreePackage);
            if(!SSCCommon.MONEY_REG.test(fobFreePackage)){// > 999999999999999999.99
                $.alertMessage.info("不含包装离岸价格式为：整数位最多15位，小数位最多2位！");
                return false;
            }
        }

        var packageCost = $("#packageCost").val();
        if(packageCost!=''){
            packageCost = SSCCommon.clearComma(packageCost);
            if(!SSCCommon.MONEY_REG.test(packageCost)){// > 999999999999999999.99
                $.alertMessage.info("包材成本格式为：整数位最多15位，小数位最多2位！");
                return false;
            }
        }

        var fobIncludePackage = $("#fobIncludePackage").val();
        if(fobIncludePackage!=''){
            fobIncludePackage = SSCCommon.clearComma(fobIncludePackage);
            if(!SSCCommon.MONEY_REG.test(fobIncludePackage)){// > 999999999999999999.99
                $.alertMessage.info("含离岸包装价格式为：整数位最多15位，小数位最多2位！");
                return false;
            }
        }

        var trunkFreight = $("#trunkFreight").val();
        if(trunkFreight != ''){
            trunkFreight = SSCCommon.clearComma(trunkFreight);
            if(!SSCCommon.MONEY_REG.test(trunkFreight)){// > 999999999999999999.99
                $.alertMessage.info("干线运费格式为：整数位最多15位，小数位最多2位！");
                return false;
            }
        }

        var cif = $("#cif").val();
        if(cif != ''){
            cif = SSCCommon.clearComma(cif);
            if(!SSCCommon.MONEY_REG.test(cif)){// > 999999999999999999.99
                $.alertMessage.info("到岸价格式为：整数位最多15位，小数位最多2位！");
                return false;
            }
        }
        if(!SSCCommon.isMoney(settkementStandardPrice)){// > 999999999999999999.99
            $.alertMessage.info("本次结算标准价不能为空或0，且格式为：整数位最多15位，小数位最多2位！");
            return false;
        }
        var productQua = SSCCommon.clearComma($("#productQua").val());
        if(!SSCCommon.WEIGHT_REG.test(productQua)){
            $.alertMessage.info("产品重量格式错误（整数位最多16位，小数位最多4位）！");
            return false;
        }
        var productValue = SSCCommon.clearComma($("#productValue").val())
        if(!SSCCommon.MONEY_REG.test(productValue)){
            $.alertMessage.info("货值格式错误（整数位最多15位，小数位最多2位）！");
            return false;
        }
        return true;
    },


    bindBlur:function(showInputId,hiddenInputId){
        var money =  $("#"+showInputId).val()
        if(money!=''){
            $("#"+showInputId).val(fmoney(money,2))
            money = SSCCommon.replaceComma(money);
            $("#"+hiddenInputId).val(money)
        }
    }
}

$(document).ready(function () {
    // 初始化调用
    SSC1130203.initDataGrid();
});