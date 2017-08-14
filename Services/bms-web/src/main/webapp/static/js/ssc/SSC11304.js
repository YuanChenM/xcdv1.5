/**
 * Created by zhang_qiang1 on 2016/6/28.
 */
var $List_Contract_Order_Grid;
var $List_Contract_Package_Grid;
var $List_Delivery_Plan_Grid;
//数据修改标识
var changeFlag = false;
function changeVal(){
    changeFlag = true;
}
var SSC11304 = {
    supplierId: "#supplierSelect",
    purchaserId: "#purchaserSelect",
    businessFormId: "SSC11304ContractBusinesss",
    contractId: $("#contractId").val(),

    bindLoadProEnterprises: function() {
        $("#purchaserSelect").change(function(){
            var slCode=$(this).find("option:selected").val();
            var slMainClass=$(this).find("option:selected").attr("slMainClass");
            $('#main-content').postUrl(Main.contextPath + '/SSC11302/getProEnterprises',{slCode:slCode,slMainClass:slMainClass},function(data){
                $('#supplierSelect option:not(:first)').remove();
                $.each(data, function(i, item) {
                    $('#supplierSelect').append("<option supplierId='" + item.epId + "'supplierName='"+item.epName+"'value='" + item.slCode+ "'>"+ item.epName + "</option>");
                });
            },{refreshHtml:false});
        });
    },

    bindDatePicker: function(timeId) {
        $(timeId).datepicker({
            dateFormat: "yy-mm-dd",
            showButtonPanel: true,
            changeYear: true,
            closeText: "清除",
            changeMonth: true
        });
        $(timeId).attr("readonly", "readonly");
    },

    closeDate: function() {
        $(document).on("click", "button.ui-datepicker-close", function() {
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },

    initDate: function () {
        this.bindDatePicker("#contractActDateStr");
        this.closeDate();
    },

    initTabs: function (index) {
        index = $.trim(index);
        index = (index.length <= 0) ? 0 : index;
        $("#tabs").tabs({active: index});
        $("#tabs").click(function () {
            $(".group-accordion").accordion("refresh");
        });
    },

    initContractOrder: function () {
        $List_Contract_Order_Grid = $("#SCC11304_contractOrder_list_grid").grid({
            paginate: false,
            actionHandler: SSC11304.orderActionHandler,
            fnRowCallback: SSC11304.orderRowCallback,
            editCellOnBlurHandler: SSC11304.orderCellOnBlurHandler,
            fnDrawCallback: SSC11304.orderDrawCallback
        });
    },

    orderActionHandler: function (rowdata, coltype, row, col) {
        //更新产品
        if (coltype == "save") {
            if (SSC11304.validateOrder(rowdata)) {
                $('#main-content').postUrl(Main.contextPath + "/SSC1130403/product/update", {
                    contractId: rowdata.contractId,
                    detailId: rowdata.detailId,
                    productBox: SSCCommon.replaceComma(rowdata.productBox),
                    fobFreePackage: SSCCommon.replaceComma(rowdata.fobFreePackage),
                    packageCost: SSCCommon.replaceComma(rowdata.packageCost),
                    fobIncludePackage: SSCCommon.replaceComma(rowdata.fobIncludePackage),
                    trunkFreight: SSCCommon.replaceComma(rowdata.trunkFreight),
                    cif: SSCCommon.replaceComma(rowdata.cif),
                    settkementStandardPrice: SSCCommon.replaceComma(rowdata.settkementStandardPrice),
                    productValue: SSCCommon.replaceComma(rowdata.productValue),
                    downPayment: rowdata.downPayment,
                    paymentAmount: SSCCommon.replaceComma(rowdata.paymentAmount),
                    weightVal: rowdata.weightVal,
                    remark: rowdata.remark,
                    ver: rowdata.ver
                }, function (data) {
                    if (data == "S") {
                        $("#main-content").postUrl(Main.contextPath + "/SSC11304/init", {contractId: rowdata.contractId}, Main.hiddenHeader);
                    }
                    else {
                        $.alertMessage.info("操作失败！");
                    }
                }, {refreshHtml: false});
            }
        }
        //删除产品
        else if (coltype == "delete") {
            $.alertMessage.confirm("删除此产品，将同时删除该产品的包材和交货期计划，确定删除吗？", function () {
                $.alertMessage.close();
                $("#main-content").postUrl(Main.contextPath + "/SSC1130403/product/delete", {
                    detailId: rowdata.detailId,
                    ver: rowdata.ver
                }, function (data) {
                    if (data == "S") {
                        $("#main-content").postUrl(Main.contextPath + "/SSC11304/init", {contractId: rowdata.contractId}, Main.hiddenHeader);
                    }
                    else {
                        $.alertMessage.info("删除失败！");
                    }
                }, {refreshHtml: false});
            });
        }
        else if (coltype == "audit") {
            $.pdialog.open("产品质量标准", Main.contextPath + "/SSC11302/getPdStandards", {width: "50%", height: 800}, {
                classesCode: rowdata.classesCode,
                machiningCode: rowdata.machiningCode,
                breedCode: rowdata.breedCode
            });
        }
    },

    /**
     * 显示合同订单列表合计
     */
    orderRowCallback: function (row, rowdata) {
        if (0 == row._DT_RowIndex) {
            $("#totalTonnages").text(fmoney(rowdata.totalTonnages, 4));
            $("#totalBoxes").text(fmoney(rowdata.totalBoxes, 0));
            $("#totalValues").text(SSCCommon.formatMoney(rowdata.totalValues));
            $("#totalPayments").text(SSCCommon.formatMoney(rowdata.totalPayments));
        }
    },

    /**
     * 合同订单列表修改数据时联动修改其它数据
     */
    orderCellOnBlurHandler: function ($comp) {
        var $td = $comp.parent();
        var $tr = $td.parent();
        var $trs = $tr.parent();
        var $row = $trs.children().index($tr);
        var $rowdata = $List_Contract_Order_Grid.fnGetData($row);
        var $name = $td.context.name;

        if ("productBox" == $name) {
            var newBoxes = SSCCommon.replaceComma($comp.val());
            if (SSCCommon.isBox(newBoxes)) {
                //箱数
                $rowdata.productBox = newBoxes;

                //箱数合计
                var totalBoxes = 0;
                var list = $List_Contract_Order_Grid.fnGetData();
                for (var i in list) {
                    var box = SSCCommon.replaceComma(list[i].productBox);
                    totalBoxes = totalBoxes + parseInt(box);
                }
                $("#totalBoxes").text(fmoney(totalBoxes, 0));

                //重量
                var oldWeightTon = SSCCommon.replaceComma($rowdata.productTonnage);
                var oldWeightKG = SSCCommon.multiply(oldWeightTon, 1000);
                var weight = $rowdata.weightVal;
                var newWeightKG = SSCCommon.multiply(weight, newBoxes);
                newWeightKG = SSCCommon.divide(newWeightKG, 1000);
                $rowdata.productTonnage = fmoney(newWeightKG, 4);

                //重量合计
                var totalTonnages = SSCCommon.replaceComma($("#totalTonnages").text());
                var totalKilograms = SSCCommon.multiply(totalTonnages, 1000);
                totalKilograms =  SSCCommon.add(totalKilograms, SSCCommon.subtract(newWeightKG, oldWeightKG));
                $("#totalTonnages").text(fmoney(totalKilograms, 4));

                //货值
                var oldValue = SSCCommon.replaceComma($rowdata.productValue);
                var price = SSCCommon.replaceComma($rowdata.settkementStandardPrice);
                var newValue = SSCCommon.multiply(newWeightKG, price);
                $rowdata.productValue = SSCCommon.roundFixed(newValue, 2);

                //货值合计
                var totalValues = SSCCommon.replaceComma($("#totalValues").text());
                totalValues = SSCCommon.add(totalValues, SSCCommon.subtract(newValue, oldValue));
                totalValues = SSCCommon.roundFixed(totalValues, 2);
                $("#totalValues").text(SSCCommon.formatMoney(totalValues));

                var percent = $.trim($rowdata.downPayment);
                if (SSCCommon.isPercent(percent)) {
                    //预付款
                    var oldPayment = SSCCommon.replaceComma($rowdata.paymentAmount);
                    var newPayment = SSCCommon.multiply(newValue, SSCCommon.divide(percent, 100));
                    $rowdata.paymentAmount = SSCCommon.roundFixed(newPayment, 2);

                    //预付款合计
                    var totalPayments = SSCCommon.replaceComma($("#totalPayments").text());
                    totalPayments = SSCCommon.add(totalPayments, SSCCommon.subtract(newPayment, oldPayment));
                    totalPayments = SSCCommon.roundFixed(totalPayments, 2);
                    $("#totalPayments").text(SSCCommon.formatMoney(totalPayments));
                }
            }
        }
        else if ("settkementStandardPrice" == $name) {
            var newPrice = SSCCommon.replaceComma($comp.val());
            if (SSCCommon.isMoney(newPrice)) {
                //货值
                var oldValue = SSCCommon.replaceComma($rowdata.productValue);
                var boxes = SSCCommon.replaceComma($rowdata.productBox);
                var weight = $rowdata.weightVal;
                var newValue = SSCCommon.multiply(SSCCommon.multiply(boxes, weight), newPrice);
                $rowdata.productValue = SSCCommon.roundFixed(newValue, 2);

                //货值合计
                var totalValues = SSCCommon.replaceComma($("#totalValues").text());
                totalValues = SSCCommon.add(totalValues, SSCCommon.subtract(newValue, oldValue));
                totalValues = SSCCommon.roundFixed(totalValues, 2);
                $("#totalValues").text(SSCCommon.formatMoney(totalValues));

                var percent = $.trim($rowdata.downPayment);
                if (SSCCommon.isPercent(percent)) {
                    //预付款
                    var oldPayment = SSCCommon.replaceComma($rowdata.paymentAmount);
                    var newPayment = SSCCommon.multiply(newValue, SSCCommon.divide(percent, 100));
                    $rowdata.paymentAmount = SSCCommon.roundFixed(newPayment, 2);

                    //预付款合计
                    var totalPayments = SSCCommon.replaceComma($("#totalPayments").text());
                    totalPayments = SSCCommon.add(totalPayments, SSCCommon.subtract(newPayment, oldPayment));
                    totalPayments = SSCCommon.roundFixed(totalPayments, 2);
                    $("#totalPayments").text(SSCCommon.formatMoney(totalPayments));
                }
            }
        }
        else if ("downPayment" == $name) {
            var newPercent = $.trim($comp.val());
            if (SSCCommon.isPercent(newPercent)) {
                //预付款
                var oldPayment = SSCCommon.replaceComma($rowdata.paymentAmount);
                var value = SSCCommon.replaceComma($rowdata.productValue);
                var newPayment = SSCCommon.multiply(value, SSCCommon.divide(newPercent, 100));
                $rowdata.paymentAmount = SSCCommon.roundFixed(newPayment, 2);

                //预付款合计
                var totalPayments = SSCCommon.replaceComma($("#totalPayments").text());
                totalPayments = SSCCommon.add(totalPayments, SSCCommon.subtract(newPayment, oldPayment));
                totalPayments = SSCCommon.roundFixed(totalPayments, 2);
                $("#totalPayments").text(SSCCommon.formatMoney(totalPayments));
            }
        }
    },

    /**
     * 若合同订单列表没有数据时，不显示合计行
     */
    orderDrawCallback: function () {
        var list = $List_Contract_Order_Grid.fnGetData();
        if (0 == list.length) {
            document.getElementById("order_sum_tr").style.display = "none";
        }
    },

    /**
     * 更新产品前，校验表单数据
     */
    validateOrder: function (rowdata) {
        var productBox = SSCCommon.replaceComma(rowdata.productBox);
        if (!SSCCommon.POSITIVE_INTEGER.test(productBox)) {
            $.alertMessage.info("箱数不能为空，且必须是正整数！");
            return false;
        }
        else if (parseInt(productBox) > SSCCommon.INT11) {
            $.alertMessage.info("箱数不能超过99999999！");
            return false;
        }

        var fobFreePackage = $.trim(rowdata.fobFreePackage);
        if (fobFreePackage.length > 0 && !SSCCommon.MONEY_REG.test(SSCCommon.replaceComma(fobFreePackage))) {
            $.alertMessage.info("不含包装离岸价格式错误，整数位最多15位，小数位最多2位！");
            return false;
        }

        var packageCost = $.trim(rowdata.packageCost);
        if (packageCost.length > 0 && !SSCCommon.MONEY_REG.test(SSCCommon.replaceComma(packageCost))) {
            $.alertMessage.info("包材成本格式错误，整数位最多15位，小数位最多2位！");
            return false;
        }

        var fobIncludePackage = $.trim(rowdata.fobIncludePackage);
        if (fobIncludePackage.length > 0 && !SSCCommon.MONEY_REG.test(SSCCommon.replaceComma(fobIncludePackage))) {
            $.alertMessage.info("含包装离岸价格式错误，整数位最多15位，小数位最多2位！");
            return false;
        }

        var trunkFreight = $.trim(rowdata.trunkFreight);
        if (trunkFreight.length > 0 && !SSCCommon.MONEY_REG.test(SSCCommon.replaceComma(trunkFreight))) {
            $.alertMessage.info("干线运费格式错误，整数位最多15位，小数位最多2位！");
            return false;
        }

        var cif = $.trim(rowdata.cif);
        if (cif.length > 0 && !SSCCommon.MONEY_REG.test(SSCCommon.replaceComma(cif))) {
            $.alertMessage.info("到岸价格式错误，整数位最多15位，小数位最多2位！");
            return false;
        }

        var settkementStandardPrice = $.trim(rowdata.settkementStandardPrice);
        if (!SSCCommon.isMoney(SSCCommon.replaceComma(settkementStandardPrice))) {
            $.alertMessage.info("本次结算标准价不能为空或0，且格式为：整数位最多15位，小数位最多2位！");
            return false;
        }

        var downPayment = $.trim(rowdata.downPayment);
        if (!SSCCommon.isPercent(downPayment)) {
            $.alertMessage.info("预付款比例不能为空，且只能为0~100之间的数字！");
            return false;
        }

        var remark = rowdata.remark;
        if (remark.length > 100) {
            $.alertMessage.info("备注不能超过100字符！");
            return false;
        }

        var totalValues = $("#totalValues").text();
        if (!SSCCommon.isMoney(SSCCommon.replaceComma(totalValues))) {
            $.alertMessage.info("货值合计不能为空或0，且格式为：整数位最多15位，小数位最多2位！");
            return false;
        }
        return true;
    },

    initContractPackage: function () {
        $List_Contract_Package_Grid = $("#SCC11304_contractPackage_list_grid").grid({
            paginate: false,
            actionHandler: SSC11304.pkgActionHandler,
            fnRowCallback: SSC11304.pkgRowCallback,
            editCellOnBlurHandler: SSC11304.pkgCellOnBlurHandler,
            fnDrawCallback: SSC11304.pkgDrawCallback
        })
    },

    pkgActionHandler: function (rowdata, coltype, row, col) {
        //更新包材
        if (coltype == "save") {
            if (SSC11304.validatePkg(rowdata)) {
                var contractId  = $("#contractId").val();
                $("#main-content").postUrl(Main.contextPath + "/SSC1130404/package/update", {
                    contractId: contractId,
                    packageDetailId: rowdata.packageDetailId,
                    cartonQuaSta: rowdata.cartonQuaSta,
                    cartonSpecSta: rowdata.cartonSpecSta,
                    innerBagQuaSta: rowdata.innerBagQuaSta,
                    innerBagSpecSta: rowdata.innerBagSpecSta,
                    cartonQua: rowdata.cartonQua,
                    cartonSpec: rowdata.cartonSpec,
                    cartonUseNum: SSCCommon.replaceComma(rowdata.cartonUseNum),
                    innerBagQua: rowdata.innerBagQua,
                    settlementMethod: rowdata.settlementMethod,
                    innerBagSpec: rowdata.innerBagSpec,
                    innerBagUseNum: SSCCommon.replaceComma(rowdata.innerBagUseNum),
                    supplyMode: rowdata.supplyMode,
                    auditMethod: rowdata.auditMethod,
                    ver: rowdata.ver
                }, function (data) {
                    if (data == "S") {
                        $("#main-content").postUrl(Main.contextPath + "/SSC11304/init", {contractId: contractId}, function() {
                            $("#headBreadCrumb").hide();
                            SSC11304.initTabs(1);
                        });
                    }
                    else {
                        $.alertMessage.info("操作失败！");
                    }
                }, {refreshHtml: false});
            }
        }
        //删除包材
        else if (coltype == "delete") {
            $.alertMessage.confirm("确定要删除这条数据吗？", function () {
                $.alertMessage.close();
                var contractId  = $("#contractId").val();
                $('#main-content').postUrl(Main.contextPath + "/SSC11304/delContractPackgeM", {
                    contractId: contractId,
                    packageDetailId: rowdata.packageDetailId,
                    ver: rowdata.ver
                }, function (data) {
                    if (data == "S") {
                        $("#main-content").postUrl(Main.contextPath + "/SSC11304/init", {contractId: contractId}, function() {
                            $("#headBreadCrumb").hide();
                            SSC11304.initTabs(1);
                        });
                    }
                    else {
                        $.alertMessage.info("删除失败！");
                    }
                }, {refreshHtml: false});
            });
        }
    },

    /**
     * 显示同包材列表合计
     */
    pkgRowCallback: function (row, rowdata) {
        if (0 == row._DT_RowIndex) {
            $("#totalCartons").text(fmoney(rowdata.totalCartons, 0));
            $("#totalInnerBags").text(fmoney(rowdata.totalInnerBags, 0));
        }
    },

    /**
     * 合同包材列表修改数据时联动修改其它数据
     */
    pkgCellOnBlurHandler: function ($comp) {
        var $td = $comp.parent();   //获得TD
        var $tr = $td.parent();     //获得TR
        var $trs = $tr.parent();    //获得所有行
        var $row = $trs.children().index($tr); //获得当前行
        var $rowdata = $List_Contract_Package_Grid.fnGetData($row);
        var $name = $td.context.name;    //获得当前列的name

        if ("cartonUseNum" == $name) {
            var newCartons = SSCCommon.replaceComma($comp.val());
            if (SSCCommon.isBoxGe0(newCartons)) {
                var oldCartons = SSCCommon.replaceComma($rowdata.cartonUseNum);
                var totalCartons = SSCCommon.replaceComma($("#totalCartons").text());
                totalCartons = SSCCommon.add(totalCartons, SSCCommon.subtract(newCartons, oldCartons));
                $("#totalCartons").text(fmoney(totalCartons, 0));
            }
        }
        else if ("innerBagUseNum" == $name) {
            var newInnerBags = SSCCommon.replaceComma($comp.val());
            if (SSCCommon.isBoxGe0(newInnerBags)) {
                var oldInnerBags = SSCCommon.replaceComma($rowdata.innerBagUseNum);
                var totalInnerBags = SSCCommon.replaceComma($("#totalInnerBags").text());
                totalInnerBags = SSCCommon.add(totalInnerBags, SSCCommon.subtract(newInnerBags, oldInnerBags));
                $("#totalInnerBags").text(fmoney(totalInnerBags, 0));
            }
        }
    },

    /**
     * 若合同包材列表没有数据时，不显示合计行
     */
    pkgDrawCallback: function () {
        var list = $List_Contract_Package_Grid.fnGetData();
        if (0 == list.length) {
            document.getElementById("pkg_sum_tr").style.display = "none";
        }
    },

    /**
     * 更新包材前，校验表单数据
     */
    validatePkg: function (rowdata) {
        var cartonQua = $.trim(rowdata.cartonQua);
        if (cartonQua.length <= 0) {
            $.alertMessage.info("(本地订单包材信息)纸箱质量标准不能为空！");
            return false;
        }

        var cartonSpec = $.trim(rowdata.cartonSpec);
        if (cartonSpec.length <= 0) {
            $.alertMessage.info("(本地订单包材信息)纸箱规格不能为空！");
            return false;
        }

        var innerBagQua = $.trim(rowdata.innerBagQua);
        if (innerBagQua.length <= 0) {
            $.alertMessage.info("(本地订单包材信息)内袋质量标准不能为空！");
            return false;
        }

        var innerBagSpec = $.trim(rowdata.innerBagSpec);
        if (innerBagSpec.length <= 0) {
            $.alertMessage.info("(本地订单包材信息)内袋规格不能为空！");
            return false;
        }

        var cartonUseNum = SSCCommon.replaceComma(rowdata.cartonUseNum);
        if (!SSCCommon.NATURAL_NUMBER.test(cartonUseNum)) {
            $.alertMessage.info("本次纸箱需求量不能为空，且只能为整数！");
            return false;
        }
        else if (parseInt(cartonUseNum) > SSCCommon.INT11) {
            $.alertMessage.info("本次纸箱需求量不能超过99999999！");
            return false;
        }

        var innerBagUseNum = SSCCommon.replaceComma(rowdata.innerBagUseNum);
        if (!SSCCommon.NATURAL_NUMBER.test(innerBagUseNum)) {
            $.alertMessage.info("本次内袋需求量不能为空，且只能为整数！");
            return false;
        }
        else if (parseInt(innerBagUseNum) > SSCCommon.INT11) {
            $.alertMessage.info("本次内袋需求量不能超过99999999！");
            return false;
        }
        return true;
    },

    initDeliveryPlan: function () {
        $List_Delivery_Plan_Grid = $("#SCC11304_deliveryPlan_list_grid").grid({
            paginate: false,
            actionHandler: SSC11304.dpActionHandler,
            editCellOnBlurHandler: SSC11304.dpCellOnBlurHandler
        })
    },

    dpActionHandler: function (rowdata, coltype, row, col) {
        //更新交货期计划
        if (coltype == "save") {
            if (SSC11304.validateDp(rowdata)) {
                var contractId  = $("#contractId").val();
                $('#main-content').postUrl(Main.contextPath + "/SSC11304/updateDeliveryPlan", {
                    contractId: contractId,
                    lotId: rowdata.lotId,
                    weightVal: rowdata.weightVal,
                    arriveBoxes: SSCCommon.replaceComma(rowdata.arriveBoxes),
                    arriveDateStr: rowdata.arriveDateStr,
                    remark: rowdata.remark,
                    delFlg: rowdata.delFlg,
                    ver: rowdata.ver
                }, function (data) {
                    if (data == "S") {
                        $("#main-content").postUrl(Main.contextPath + "/SSC11304/init", {contractId: contractId}, function() {
                            $("#headBreadCrumb").hide();
                            SSC11304.initTabs(2);
                        });
                    }
                    else {
                        $.alertMessage.info("操作失败！");
                    }
                }, {refreshHtml: false});
            }
        }
        //删除交货期计划
        else if (coltype == "delete") {
            $.alertMessage.confirm("确定要删除这条数据吗？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SSC11304/updateDeliveryPlan", {
                    contractId: rowdata.contractId,
                    lotId: rowdata.lotId,
                    arriveQut: rowdata.arriveQut,
                    arriveBoxes: SSCCommon.replaceComma(rowdata.arriveBoxes),
                    arriveDateStr: rowdata.arriveDateStr,
                    remark: rowdata.remark,
                    delFlg: 1,
                    ver: rowdata.ver
                }, function (data) {
                    if (data == "S") {
                        $("#main-content").postUrl(Main.contextPath + "/SSC11304/init", {contractId: rowdata.contractId}, function() {
                            $("#headBreadCrumb").hide();
                            SSC11304.initTabs(2);
                        });
                    }
                    else {
                        $.alertMessage.info("删除失败！");
                    }
                }, {refreshHtml: false});
            })
        }
    },

    /**
     * 修改到货箱数，联动修改到货重量
     */
    dpCellOnBlurHandler: function ($comp) {
        var $td = $comp.parent();
        var $tr = $td.parent();
        var $trs = $tr.parent();
        var $row = $trs.children().index($tr);
        var $rowdata = $List_Delivery_Plan_Grid.fnGetData($row);
        var $name = $td.context.name;

        if ("arriveBoxes" == $name) {
            var newArriveBoxes = SSCCommon.replaceComma($comp.val());
            var weightVal = $rowdata.weightVal;
            if (SSCCommon.POSITIVE_INTEGER.test(newArriveBoxes) && newArriveBoxes <= SSCCommon.INT11) {
                $rowdata.arriveQut = SSCCommon.divide(SSCCommon.multiply(newArriveBoxes, weightVal), 1000);
            }
        }
    },

    /**
     * 更新交货期计划前，校验表单数据
     */
    validateDp: function (rowdata) {
        var arriveDateStr = $.trim(rowdata.arriveDateStr);
        if (0 == arriveDateStr.length) {
            $.alertMessage.info("到货日期不能为空！");
            return false;
        }

        var arriveBoxes = SSCCommon.replaceComma(rowdata.arriveBoxes);  //分配箱数
        if (!SSCCommon.POSITIVE_INTEGER.test(arriveBoxes)) {
            $.alertMessage.info("到货箱数不能为空，且必须是正整数！");
            return false;
        }
        else if (parseInt(arriveBoxes) > SSCCommon.INT11) {
            $.alertMessage.info("到货箱数不能超过99999999！");
            return false;
        }
        else {
            var productName = rowdata.pdName;
            var deliveryPlans = $List_Delivery_Plan_Grid.fnGetData();
            var boxes = 0;  //已分配箱数

            for (var i in deliveryPlans) {
                var deliveryPlan = deliveryPlans[i];
                if (deliveryPlan.pdName == productName) {
                    var num = SSCCommon.replaceComma(deliveryPlan.arriveBoxes);
                    boxes += parseInt(num);
                }
            }
            boxes -= parseInt(arriveBoxes);

            productName = productName.substring(productName.indexOf(">")+1, productName.lastIndexOf("<"));
            var contractOrders = $List_Contract_Order_Grid.fnGetData();
            var totalBoxes = 0; //总箱数

            for (var i in contractOrders) {
                var contractOrder = contractOrders[i];
                if (contractOrder.pdName == productName) {
                    var num = SSCCommon.replaceComma(contractOrder.productBox);
                    totalBoxes = parseInt(num);
                }
            }

            var leftBoxes = totalBoxes - boxes; //待分配箱数
            if (arriveBoxes > leftBoxes) {
                $.alertMessage.info("待分配箱数为" + leftBoxes + "，到货箱数不能大于待分配箱数！");
                return false;
            }
        }

        var remark = rowdata.remark;
        if (remark.length > 100) {
            $.alertMessage.info("备注不能超过100字符！");
            return false;
        }
        return true;
    },

    initBusinessTerms: function() {
        var tranFeeStandard = $.trim($("input[name='tranFeeStandard']").val());
        if (tranFeeStandard.length > 0) {
            $("#tranFeeStandardStr").val(fmoney(tranFeeStandard, 2));
        }
    },

    clickBusinessTermsSaveButton: function () {
        $("#SSC11304_BT_SAVE").click(function () {
            if (SSC11304.validateBusinessTerms()) {
                SSC11304.saveBusinessTerms();
            }
        });
    },

    /**
     * 保存商务条款前，校验表单数据
     */
    validateBusinessTerms: function () {
        var paymentRatio = $.trim($("input[name='paymentRatio']").val());
        if (paymentRatio.length > 0 && !SSCCommon.isPercent(paymentRatio)) {
            $.alertMessage.info("预付款比例只能为0~100之间的数字！");
            return false;
        }

        var tranRes = $.trim($("input[name='tranRes']").val());
        if (tranRes.length > 100) {
            $.alertMessage.info("运输责任方不能超过100个字符！");
            return false;
        }

        var tranFeeStandard = SSCCommon.replaceComma($("#tranFeeStandardStr").val());
        if (null != tranFeeStandard && !SSCCommon.isMoneyGe0(tranFeeStandard)) {
            $.alertMessage.info("干线运费格式错误，整数位最多15位，小数位最多2位！");
            return false;
        }

        var contractVerPeriod = $.trim($("input[name='contractVerPeriod']").val());
        if (contractVerPeriod.length > 0) {
            if (!SSCCommon.NATURAL_NUMBER.test(contractVerPeriod)) {
                $.alertMessage.info("交货期延迟期限只能为数字！");
                return false;
            }
            else if (parseInt(contractVerPeriod) > 366) {
                $.alertMessage.info("交货期延迟期限不能超过366天！");
                return false;
            }
        }

        var purchaserAuthSig = $.trim($("input[name='purchaserAuthSig']").val());
        if (purchaserAuthSig.length > 100) {
            $.alertMessage.info("甲方授权签字人不能超过100个字符！");
            return false;
        }

        var supplierAuthSig = $.trim($("input[name='supplierAuthSig']").val());
        if (supplierAuthSig.length > 100) {
            $.alertMessage.info("乙方授权签字人不能超过100个字符！");
            return false;
        }

        var faxNum = $.trim($("input[name='faxNum']").val());
        if (faxNum.length > 0 && !SSCCommon.FAX_REG.test(faxNum)) {
            $.alertMessage.info("请输入正确的传真号码！");
            return false;
        }

        var purchaserAddr = $.trim($("input[name='purchaserAddr']").val());
        if (purchaserAddr.length > 100) {
            $.alertMessage.info("甲方地址不能超过100个字符！");
            return false;
        }

        var purchaserEmail = $.trim($("input[name='purchaserEmail']").val());
        if (purchaserEmail.length > 0 && !SSCCommon.EMAIL_REG.test(purchaserEmail)) {
            $.alertMessage.info("请输入正确的甲方对公邮箱！");
            return false;
        }

        var adminTrans = $.trim($("input[name='adminTrans']").val());
        if (adminTrans.length > 100) {
            $.alertMessage.info("行政流转人不能超过100个字符！");
            return false;
        }

        var adminTransQq = $.trim($("input[name='adminTransQq']").val());
        if (adminTransQq.length > 0 && !SSCCommon.QQ_REQ.test(adminTransQq)) {
            $.alertMessage.info("请输入正确的行政流转人QQ！");
            return false;
        }

        var negManager = $.trim($("input[name='negManager']").val());
        if (negManager.length > 100) {
            $.alertMessage.info("谈判管理人不能超过100个字符！");
            return false;
        }

        var negManagerPhonenum = $.trim($("input[name='negManagerPhonenum']").val());
        if (negManagerPhonenum.length > 0 && !SSCCommon.PHONE_REG.test(negManagerPhonenum)) {
            $.alertMessage.info("请输入正确的谈判管理人电话");
            return false;
        }

        var negManagerQq = $.trim($("input[name='negManagerQq']").val());
        if (negManagerQq.length > 0 && !SSCCommon.QQ_REQ.test(negManagerQq)) {
            $.alertMessage.info("请输入正确的谈判管理人QQ！");
            return false;
        }

        var qcManager = $.trim($("input[name='qcManager']").val());
        if (qcManager.length > 100) {
            $.alertMessage.info("品控管理人不能超过100个字符！");
            return false;
        }

        var qcManagerPhonenum = $.trim($("input[name='qcManagerPhonenum']").val());
        if (qcManagerPhonenum.length > 0 && !SSCCommon.PHONE_REG.test(qcManagerPhonenum)) {
            $.alertMessage.info("请输入正确的品控管理人电话");
            return false;
        }

        var qcManagerQq = $.trim($("input[name='qcManagerQq']").val());
        if (qcManagerQq.length > 0 && !SSCCommon.QQ_REQ.test(qcManagerQq)) {
            $.alertMessage.info("请输入正确的品控管理人QQ！");
            return false;
        }

        var contractSubj1 = $.trim($("input[name='contractSubj1']").val());
        if (contractSubj1.length > 100) {
            $.alertMessage.info("合同主体甲1不能超过100个字符！");
            return false;
        }

        var contractSubj2 = $.trim($("input[name='contractSubj2']").val());
        if (contractSubj2.length > 100) {
            $.alertMessage.info("合同主体甲2不能超过100个字符！");
            return false;
        }

        var paymentUnit1 = $.trim($("input[name='paymentUnit1']").val());
        if (paymentUnit1.length > 100) {
            $.alertMessage.info("付款单位甲1不能超过100个字符！");
            return false;
        }

        var paymentUnit2 = $.trim($("input[name='paymentUnit2']").val());
        if (paymentUnit2.length > 100) {
            $.alertMessage.info("付款单位甲2不能超过100个字符！");
            return false;
        }

        var supplierQq = $.trim($("input[name='supplierQq']").val());
        if (supplierQq.length > 0 && !SSCCommon.QQ_REQ.test(supplierQq)) {
            $.alertMessage.info("请输入正确的乙方公司QQ！");
            return false;
        }

        var supplierEmail = $.trim($("input[name='supplierEmail']").val());
        if (supplierEmail.length > 0 && !SSCCommon.EMAIL_REG.test(supplierEmail)) {
            $.alertMessage.info("请输入正确的乙方对外邮箱！");
            return false;
        }

        var marketManager = $.trim($("input[name='marketManager']").val());
        if (marketManager.length > 100) {
            $.alertMessage.info("营销负责人不能超过100个字符！");
            return false;
        }

        var marketManagerPhonenum = $.trim($("input[name='marketManagerPhonenum']").val());
        if (marketManagerPhonenum.length > 0 && !SSCCommon.PHONE_REG.test(marketManagerPhonenum)) {
            $.alertMessage.info("请输入正确的营销负责人电话");
            return false;
        }

        var contractDirector = $.trim($("input[name='contractDirector']").val());
        if (contractDirector.length > 100) {
            $.alertMessage.info("合同负责人不能超过100个字符！");
            return false;
        }

        var contractDirectorPhonenum = $.trim($("input[name='contractDirectorPhonenum']").val());
        if (contractDirectorPhonenum.length > 0 && !SSCCommon.PHONE_REG.test(contractDirectorPhonenum)) {
            $.alertMessage.info("请输入正确的合同负责人电话");
            return false;
        }

        var produceDirector = $.trim($("input[name='produceDirector']").val());
        if (produceDirector.length > 100) {
            $.alertMessage.info("生产负责人不能超过100个字符！");
            return false;
        }

        var produceDirectorPhonenum = $.trim($("input[name='produceDirectorPhonenum']").val());
        if (produceDirectorPhonenum.length > 0 && !SSCCommon.PHONE_REG.test(produceDirectorPhonenum)) {
            $.alertMessage.info("请输入正确的生产负责人电话");
            return false;
        }

        var qcDirector = $.trim($("input[name='qcDirector']").val());
        if (qcDirector.length > 100) {
            $.alertMessage.info("品控负责人不能超过100个字符！");
            return false;
        }

        var qcDirectorPhonenum = $.trim($("input[name='qcDirectorPhonenum']").val());
        if (qcDirectorPhonenum.length > 0 && !SSCCommon.PHONE_REG.test(qcDirectorPhonenum)) {
            $.alertMessage.info("请输入正确的品控负责人电话");
            return false;
        }

        var transDirector = $.trim($("input[name='transDirector']").val());
        if (transDirector.length > 100) {
            $.alertMessage.info("运输负责人不能超过100个字符！");
            return false;
        }

        var transDirectorPhonenum = $.trim($("input[name='transDirectorPhonenum']").val());
        if (transDirectorPhonenum.length > 0 && !SSCCommon.PHONE_REG.test(transDirectorPhonenum)) {
            $.alertMessage.info("请输入正确的运输负责人电话");
            return false;
        }

        var deliveryLocation = $.trim($("input[name='deliveryLocation']").val());
        if (deliveryLocation.length > 100) {
            $.alertMessage.info("本次订单的交付地点不能超过100个字符！");
            return false;
        }

        var purVerDirector = $.trim($("input[name='purVerDirector']").val());
        if (purVerDirector.length > 100) {
            $.alertMessage.info("甲方核销负责人不能超过100个字符！");
            return false;
        }

        var purVerDirectorNum = $.trim($("input[name='purVerDirectorNum']").val());
        if (purVerDirectorNum.length > 0 && !SSCCommon.PHONE_REG.test(purVerDirectorNum)) {
            $.alertMessage.info("请输入正确的甲方核销负责人电话");
            return false;
        }

        var suppVerDirector = $.trim($("input[name='suppVerDirector']").val());
        if (suppVerDirector.length > 100) {
            $.alertMessage.info("乙方核销负责人不能超过100个字符！");
            return false;
        }

        var suppVerDirectorNum = $.trim($("input[name='suppVerDirectorNum']").val());
        if (suppVerDirectorNum.length > 0 && !SSCCommon.PHONE_REG.test(suppVerDirectorNum)) {
            $.alertMessage.info("请输入正确的乙方核销负责人电话");
            return false;
        }
        return true;
    },

    saveBusinessTerms: function () {// 保存商务条款
        var tranFeeStandard = SSCCommon.replaceComma($("#tranFeeStandardStr").val());
        $("input[name='tranFeeStandard']").val(tranFeeStandard);    //将文本框的值放入隐藏域
        var formdata = getFormData($("#" + SSC11304.businessFormId));

        if (formdata.contractId > 0) {
            if (formdata.clauseId > 0) {// 修改
                $('#main-content').postUrl(Main.contextPath + "/SSC11304/updateBusinessTerms", formdata, function() {
                    $("#headBreadCrumb").hide();
                    SSC11304.initTabs(3);
                });
            } else {
                $('#main-content').postUrl(Main.contextPath + "/SSC11304/saveBusinessTerms", formdata, function() {
                    $("#headBreadCrumb").hide();
                    SSC11304.initTabs(3);
                });
            }
        } else {
            $.alertMessage.info("合同没有基本信息，无法保存商务条款！");
        }
    },

    /**
     * 采购商批准合同
     */
    clickPurchaserConfirmButton: function() {
        $("#SSC11304_PURCHASER_CONFIRM").click(function () {
            var status = $("#contractStatus").val();
            if (3 == status) {
                status = -2;
            }
            else {
                status = 2;
            }
            SSC11304.checkBeforeConfirm(status);
        });
    },

    /**
     * 生产商批准合同
     */
    clickSupplierConfirmButton: function() {
        $("#SSC11304_SUPPLIER_CONFIRM").click(function () {
            var status = $("#contractStatus").val();
            if (2 == status) {
                status = -3;
            }
            else {
                status = 3;
            }
            SSC11304.checkBeforeConfirm(status);
        });
    },

    checkBeforeConfirm: function(status) {
        var products = $List_Contract_Order_Grid.fnGetData();
        var packages = $List_Contract_Package_Grid.fnGetData();
        var deliveryPlans = $List_Delivery_Plan_Grid.fnGetData();
        var contractId = $.trim($("#contractId").val());
        var contractName = $.trim($("#contractName").val());
        var contractActDate = $.trim($("#contractActDate").val());
        var clauseId = $.trim($("#clauseId").val());

        if(changeFlag){
            $.alertMessage.info("请先保存数据");
            return;
        }
        if (0 == contractName.length) {
            $.alertMessage.info("合同名称不能为空！");
            return;
        }
        else if (contractName.length > 100) {
            $.alertMessage.info("合同名称长度不能超过100字符！");
            return;
        }
        else if (0 == products.length) {
            $.alertMessage.info("合同信息中没有产品，无法确认！");
            return;
        }
        else if (0 == deliveryPlans.length) {
            $.alertMessage.info("合同没有交货期计划，无法确认！");
            return;
        }

        $("#main-content").postUrl(Main.contextPath + "/SSC11304/beforeConfirmation", {contractId: contractId}, function(resp) {
            if ("no_first_percent" == resp) {
                $.alertMessage.info("产品信息中没有预付款比例，无法确认！");
                return;
            }

            var noPkgTip = "合同信息中没有包材，确定确认吗？";
            var noBtTip = "合同信息中没有商务条款，确定确认吗？";
            var partDpTip = "产品未全部分配交货期计划，确定确认吗？";

            var flag = true;
            for (var i in products) {
                var str = products[i].deliveryPlan;
                if (-1 == str.indexOf("已分配完")) {
                    flag = false;
                    break;
                }
            }

            if (0 == packages.length) {
                $.alertMessage.confirm(noPkgTip, function() {
                    $.alertMessage.close();

                    if (0 == clauseId.length) {
                        $.alertMessage.confirm(noBtTip, function() {
                            $.alertMessage.close();

                            if (!flag) {
                                $.alertMessage.confirm(partDpTip, function() {
                                    $.alertMessage.close();
                                    SSC11304.auditContract(status);
                                });
                            }
                            else {
                                SSC11304.auditContract(status);
                            }
                        });
                    }
                    else if (!flag) {
                        $.alertMessage.confirm(partDpTip, function() {
                            $.alertMessage.close();
                            SSC11304.auditContract(status);
                        });
                    }
                    else {
                        SSC11304.auditContract(status);
                    }
                });
            }
            else if (0 == clauseId.length) {
                $.alertMessage.confirm(noBtTip, function() {
                    $.alertMessage.close();

                    if (!flag) {
                        $.alertMessage.confirm(partDpTip, function() {
                            $.alertMessage.close();
                            SSC11304.auditContract(status);
                        });
                    }
                    else {
                        SSC11304.auditContract(status);
                    }
                });
            }
            else if (!flag) {
                $.alertMessage.confirm(partDpTip, function() {
                    $.alertMessage.close();
                    SSC11304.auditContract(status);
                });
            }
            else {
                SSC11304.auditContract(status);
            }
        }, {refreshHtml: false});
    },

    auditContract: function(status) {
        var contractId = $("#contractId").val();
        var contractActDateStr = $("#contractActDate").val();
        var ver = $("#ver").val();

        $("#main-content").postUrl(Main.contextPath + "/SSC11304/auditContract", {
            contractId: contractId,
            contractStatus: status,
            contractActDateStr: contractActDateStr,
            ver: ver
        }, Main.hiddenHeader);
    },

    /**
     * 点击合同基本信息的保存按钮，新增或更新数据
     */
    clickContractEditButton: function() {
        $("#SSC11304_CONTRACT_EDIT").click(function() {
            if (SSC11304.validateContract()) {
                SSC11304.saveOrUpdateContract();
            }
        });
    },

    /**
     * 新增或更新合同基本信息前，校验表单数据
     */
    validateContract: function() {
        var contractId = $.trim($("#contractId").val());
        var contractName = $.trim($("#contractName").val());

        if (0 == contractName.length) {
            $.alertMessage.info("合同名称不能为空！");
            return false;
        }
        else if (contractName.length > 100) {
            $.alertMessage.info("合同名称长度不能超过100个字符！");
            return false;
        }

        //新增校验
        if (0 == contractId.length) {
            var purchaser = $.trim($("#purchaserSelect").val());
            var supplier = $.trim($("#supplierSelect").val());

            if (0 == purchaser.length) {
                $.alertMessage.info("甲方(采购商)不能为空！");
                return false;
            }
            if (0 == supplier.length) {
                $.alertMessage.info("乙方(生产商)不能为空！");
                return false;
            }
        }
        return true;
    },

    /**
     * 新增或更新合同基本信息
     */
    saveOrUpdateContract: function() {
        var formdata = getFormData($("#contract_form"));
        $("#main-content").postUrl(Main.contextPath + "/SSC11304/saveOrUpdateContract", formdata, Main.hiddenHeader);
    },

    bindProductAttrCodeSelect: function () {
        $(SSC11304.purchaserId).change(function () {
            SSC11304.setParam(SSC11304.purchaserId, "purchaserId");
        });
        $(SSC11304.supplierId).change(function () {
            SSC11304.setParam(SSC11304.supplierId, "supplierId");
        });
    },

    setParam: function(id,epIdObj) {
        var name = $(id).find("option:selected").text().trim();
        var slCode = $(id).find("option:selected").val();
        var epId = $(id).find("option:selected").attr(epIdObj);
        if(id == SSC11304.purchaserId){
            $("#purchaserName").val(name);
            $("#purchaserCode").val(slCode);
            $("#" + epIdObj).val(epId);
        }else if(id == SSC11304.supplierId){
            $("#supplierName").val(name);
            $("#supplierCode").val(slCode);
            $("#" + epIdObj).val(epId);
        }
    },

    checkContractId: function () {
        if (SSC11304.contractId > 0) {
            return true;
        } else {
            $.alertMessage.info("合同没有基本信息，无法添加！");
            return false;
        }
    },

    bindAddDPButton: function () {
        $("#SSC11304_DP_NEW").click(function () {
            if (SSC11304.checkContractId()) {
                SSC11304.newDPData();
            }
        });
    },

    newDPData: function () {
        $.pdialog.open("新增交货期计划", Main.contextPath + "/SSC11304/addDeliveryPlan", {
            width: 750,
            height: 380
        }, {contractId: SSC11304.contractId});
    },

    bindAddPFButton: function () {
        $("#SSC11304_PKG_NEW").click(function () {
            if (SSC11304.checkContractId()) {
                SSC11304.newPFData();
            }
        });
    },

    bindAddConOrderButton: function () {
        $("#SSC11304_ORDER_NEW").click(function () {
            if (SSC11304.checkContractId()) {
                SSC11304.newConOrderData();
            }
        });
    },

    newConOrderData: function () {
        var purchaserCode = $("#purchaserCode").val();
        $.pdialog.open("新增合同订单", Main.contextPath + "/SSC1130403/product/add/pre", {
            width: 750,
            height: 480
        }, {
            contractId: SSC11304.contractId,
            purchaserCode: purchaserCode,
            supplierId: $("#supplierId").val().trim()
        });
    },

    newPFData: function () {
        $.pdialog.open("新增包材", Main.contextPath + "/SSC1130404/package/add/pre", {
            width: 750,
            height: 480
        }, {contractId: SSC11304.contractId});
    },

    bindNewButton: function () {
        $("#SSC11304_ORDER_ADD").click(function () {
            $('#main-content').postUrl(Main.contextPath + "/SSC11304/checkDeliveryPlanNum", {"contractId": SSC11304.contractId}, function (data) {
                if (data == 0) {
                    $.pdialog.open("生成发货订单", Main.contextPath + "/SSC11304/chooseInit", {width: "30%"}, {contractId: SSC11304.contractId});
                } else if (data == 2) {
                    $.alertMessage.info("该合同的发货订单已全部生成！");
                }
            }, {refreshHtml: false});
        });

        //发起付款申请
        $("#SSC11304_ORDER_CREATE").click(function () {
            Main.detailWindow(Main.contextPath + "/SSC11308/init/1",  {
                contractId: $("#contractId").val(),
                contractCode: $("#contractCode").val()
            }, "付款申请");
        });
    },

    /**
     * 点击再修改按钮，更新合同状态为待审核，使合同相关信息可以再次修改
     */
    clickEnableToModifyButton: function () {
        $("#SSC11304_ENABLE_TO_MODIFY").click(function () {
            var contractId = $("#contractId").val();
            $("#main-content").postUrl(Main.contextPath + "/SSC11303/enableToModify", {contractId: contractId}, function (resp) {
                if ("HX_EXIST" == resp) {
                    $.alertMessage.info("该合同已生成核销单，无法再修改！");
                }
                else if ("FK_EXIST" == resp) {
                    $.alertMessage.info("该合同已生成付款申请单，无法再修改！");
                }
                else if ("FH_EXIST" == resp) {
                    $.alertMessage.info("该合同已生成发货订单，无法再修改！");
                }
                else {
                    if ("S" == resp) {
                        $.alertMessage.info("合同状态更新为待审核。");
                        $("#main-content").postUrl(Main.contextPath + "/SSC11304/init", {contractId: contractId}, Main.hiddenHeader);
                    }
                    else {
                        $.alertMessage.info("再修改失败！");
                    }
                }
            }, {refreshHtml: false});
        });
    },

    /**
     * 跳转到中标确认书详情页面
     */
    gotoBidDetail: function () {
        var bidId = $("#bidId").val();
        var contractId = $("#contractId").val();
        Main.detailWindow(Main.contextPath + "/SSC11302/show",  {
            bidId: bidId,
            contractId: contractId
        }, "中标成交确认书详细");
    },

    /**
     * 跳转到核销单明细页面
     */
    gotoVerificationDetail: function () {
        $("#SSC11304_VERIFICATION_DETAIL").click(function () {
            var status = $("#contractStatus").val();
            if (status < 4) {
                $.alertMessage.info("合同未生效，无法生成核销单！");
                return;
            }
            else if (status > 4) {
                $.alertMessage.info("已生成核销单！");
                return;
            }
            Main.detailWindow(Main.contextPath + "/SSC11322/show",  {
                contractId: $("#contractId").val(),
                navigation: "contractDetail"
            }, "核销单详细");
        });
    },

    /**
     * 关联中标
     */
    clickAddBidButton:function(){
        $("#SSC11304_CONTRACT_ADDBID").click(function () {
            //    $.pdialog.open("选择中标基础信息", Main.contextPath + "/SSC11302/choseBidBaseInit",{width: "30%"},{
            //        contractId:$("#contractId").val()
            //    });
            $.pdialog.open("选择中标成交确认书", Main.contextPath + "/SSC11301/initBidInfo", {width: 1200, height: 500}, {
                "bidInputId": "chooseBisInfo",
                "bidStatus": "3",
                "bidFlag": "1",
                "delFlg": "0"
            });
        });
    }
};

$(document).ready(function() {
    SSC11304.initDate();
    SSC11304.initTabs();
    SSC11304.initContractOrder();
    SSC11304.initContractPackage();
    SSC11304.initDeliveryPlan();
    SSC11304.bindAddDPButton();
    SSC11304.bindAddConOrderButton();
    SSC11304.bindAddPFButton();
    SSC11304.clickContractEditButton();
    SSC11304.initBusinessTerms();
    SSC11304.clickBusinessTermsSaveButton();
    SSC11304.bindProductAttrCodeSelect();
    SSC11304.clickPurchaserConfirmButton();
    SSC11304.clickSupplierConfirmButton();
    SSC11304.bindNewButton();
    SSC11304.clickEnableToModifyButton();
    SSC11304.gotoVerificationDetail();
    SSC11304.bindLoadProEnterprises();
    SSC11304.clickAddBidButton();
    SSCCommon.disableEnterKey();        //必须放倒数第二行

    //付款申请跳转时，初始化左侧菜单栏
    if($("#navigation").val()=='requestDetail'){
        return;
    }
    //Main.hlLeftMainMenu("SSC11303");    //必须放最后一行
});