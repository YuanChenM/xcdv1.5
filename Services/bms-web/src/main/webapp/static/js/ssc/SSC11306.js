/**
 * 发货订单明细JS
 *
 * @author yang_yang
 */
var $List_Grid;
//数据修改标识
var changeFlag = false;
function changeVal(){
    changeFlag = true;
}

var SSC11306 = {
    supplierId:"#supplierSelect",
    purchaserId:"#purchaserSelect",
    lgcsAreaCode:"#lgcsAreaCode",
    SSC11306Grid: null,
    formId:"SSC11306Form",
    init: function () {
        this.bindDatePicber("#etd");
        this.bindDatePicber("#eta");
        $List_Grid = $('#SSC11306_list_grid').grid({
            paging:false,
            actionHandler: SSC11306.actionHandler,
            editCellOnBlurHandler :SSC11306.cellOnBlurHandler/*,
            fnRowCallback: SSC11306.rowCallback*/
        });
        this.bindButton();
        this.inputBlur();
        this.bindLoadProEnterprises();
        this.closeDate();
    },
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
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "delete") {
            $.alertMessage.confirm("确定要删除这条数据吗？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SSC11306/delete/" ,{detailId:rowdata.detailId,deliveryId:$("#deliveryId").val(),ver:rowdata.ver},function(data) {
                    $('#main-content').postUrl(Main.contextPath + "/SSC11306/init/" , {
                        "deliveryId": $('#deliveryId').val()
                    },Main.hiddenHeader);
                    $.alertMessage.info("删除成功");
                },Main.hiddenHeader);
            });
        }
        if (coltype == "save") {
            //获取改动的数据对象(数组)
            var changeData = $List_Grid.getChangeData();
            if(changeData.length == 0){
                $.alertMessage.info("请编辑后再保存");
                return;
            }
            for(i = 0;i < changeData.length;i++){
                if(!SSCCommon.POSITIVE_INTEGER.test(changeData[i]["productBox"])){
                    $.alertMessage.info("发货箱数不能为空，且必须是正整数！");
                    return;
                }
                if(changeData[i]["productBox"] > SSCCommon.INT11){
                    $.alertMessage.info("发货箱数不能超过99999999！");
                    return;
                }
                if(!SSCCommon.WEIGHT_REG.test(changeData[i]["productQua"])){
                    $.alertMessage.info("发货数量格式错误（整数位最多12位，小数位最多4位）！");
                    return;
                }
                if(changeData[i]["trunkFreight"] != "" && !SSCCommon.MONEY_REG.test(changeData[i]["trunkFreight"])){
                    $.alertMessage.info("干线运费格式错误（整数位最多15位，小数位最多2位）！");
                    return;
                }
                if(changeData[i]["cif"] != "" && !SSCCommon.MONEY_REG.test(changeData[i]["cif"])){
                    $.alertMessage.info("到岸价格式错误（整数位最多15位，小数位最多2位）！");
                    return;
                }
                if(!SSCCommon.isMoney(changeData[i]["settkementStandardPrice"])){
                    $.alertMessage.info("结算标准价不能为空或0，且格式为：整数位最多15位，小数位最多2位！");
                    return;
                }
                if(!SSCCommon.MONEY_REG.test(changeData[i]["productValue"])){
                    $.alertMessage.info("货值格式错误（整数位最多15位，小数位最多2位）！");
                    return;
                }
                if(changeData[i]["remark"].length > 100){
                    $.alertMessage.info("备注长度超过100字符，请重新输入");
                    return;
                }
            }
            $.alertMessage.confirm("你确定要保存这条数据吗？", function() {
                $.alertMessage.close();
                //创建json对象
                var json = {};
                for(i = 0;i < changeData.length;i++){
                    //把数组的对象封装到json
                    json[i] = {"detailId":changeData[i]["detailId"],"productBox":changeData[i]["productBox"],"productQua":changeData[i]["productQua"],
                        "trunkFreight":changeData[i]["trunkFreight"],"cif":changeData[i]["cif"],
                        "settkementStandardPrice":changeData[i]["settkementStandardPrice"],
                        "productValue":changeData[i]["productValue"],"remark":changeData[i]["remark"],"ver":changeData[i]["ver"]};
                }
                //转成jsonStr
                var jsonStr = JSON.stringify(json);
                $("#jsonStr").val(jsonStr);
                formData = getFormData($("#SSC11306Form"));
                $('#main-content').postUrl(Main.contextPath + "/SSC11306/modifyOrderPd",formData,function(data) {
                    if(data == "S") {
                        $('#main-content').postUrl(Main.contextPath + "/SSC11306/init/" , {
                            "deliveryId": $('#deliveryId').val()
                        },Main.hiddenHeader)
                        $.alertMessage.info("操作成功");
                    } else {
                        $.alertMessage.warn("请刷新页面再试！");
                    }
                },{refreshHtml:false});
            });
        }
    },
    bindDatePicber: function (timeId) {
        $(timeId).datepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            changeMonth: true,
            closeText: "清除",
            changeYear: true
        });
        $(timeId).attr("readonly","readonly");

    },
    closeDate: function () {
        $(document).on("click", "button.ui-datepicker-close", function () {
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    rowCallback: function(tr, data) {
        if(tr._DT_RowIndex == 0){
            $("#sumProductBox").html(data.sumProductBox);
            $("#sumProductQua").html(data.sumProductQua);
            $("#sumStandardPrice").html(data.sumStandardPrice);
            $("#sumProductValue").html(data.sumProductValue);

            var transportCost = SSCCommon.roundFixed(SSCCommon.multiply($("#transportUnit").val() ,SSCCommon.divide($("#sumProductQua").html(),1000)),2);
            //Modify for Bug#2566 at 2016/09/09 by yang_yang Start
            $("#transportCost").val(transportCost);
            //Modify for Bug#2566 at 2016/09/09 by yang_yang End
            $("#transportCostStr").html(SSCCommon.formatMoney(transportCost));
        }
    },
    inputBlur: function(){
        //到货车次
        $("#deliveryBatchShow").blur(function(){
            var deliveryBatch = SSCCommon.clearComma($("#deliveryBatchShow").val());

            $("#deliveryBatch").val(deliveryBatch);
            $("#deliveryBatchShow").val(fmoney(deliveryBatch,0));
        });
        //每吨运费transportUnit = 预计公里数mileage * 运费单价(元/吨公里)freightUnit
        //本次运费transportCost =  每吨运费transportUnit * 预计发货吨数
        //公里数
        $("#mileageShow").blur(function(){
            var mileage = SSCCommon.clearComma($("#mileageShow").val());

            $("#mileage").val(mileage);

            if(SSCCommon.isMileage($("#mileage").val()) && !$("#freightSettleMethod1").is(":checked")){
                $("#mileageShow").val(fmoney(mileage,2));

                var transportUnit = SSCCommon.roundFixed(SSCCommon.multiply($("#mileage").val(),$("#freightUnit").val()),2);
                $("#transportUnit").val(transportUnit);
                if(transportUnit != ""){
                    $("#transportUnitShow").val(SSCCommon.formatMoney(transportUnit));
                }
                var transportCost = SSCCommon.roundFixed(SSCCommon.multiply(transportUnit,SSCCommon.divide(SSCCommon.clearComma($("#sumProductQua").html()),1000)),2);
                $("#transportCost").val(transportCost);
                $("#transportCostStr").html(SSCCommon.formatMoney(transportCost));
                //总金额
                var amount = SSCCommon.roundFixed(SSCCommon.add(transportCost,$("#sumProductValue").text()),2);
                $("#amount").val(amount);
                $("#amountStr").html(SSCCommon.formatMoney(amount));
            }
        });
        //运费单价
        $("#freightUnitShow").blur(function(){
            if($("#freightUnitShow").val() != ""){
                var freightUnit = SSCCommon.clearComma($("#freightUnitShow").val());
                $("#freightUnit").val(freightUnit);
                if(SSCCommon.MONEY_REG.test(freightUnit)) {
                    $("#freightUnitShow").val(SSCCommon.formatMoney(freightUnit));
                    var transportUnit = SSCCommon.roundFixed(SSCCommon.multiply($("#mileage").val(), freightUnit), 2);
                    $("#transportUnit").val(transportUnit);
                    $("#transportUnitShow").val(SSCCommon.formatMoney(transportUnit));
                    var transportCost = SSCCommon.roundFixed(SSCCommon.multiply(transportUnit, SSCCommon.divide(SSCCommon.clearComma($("#sumProductQua").html()), 1000)), 2);
                    $("#transportCost").val(transportCost);
                    $("#transportCostStr").html(SSCCommon.formatMoney(transportCost));

                    //总金额
                    var amount = SSCCommon.roundFixed(SSCCommon.add(transportCost, $("#sumProductValue").text()), 2);
                    $("#amount").val(amount);
                    $("#amountStr").html(SSCCommon.formatMoney(amount));
                }
            }
        });
        //每吨运费
        $("#transportUnitShow").blur(function(){
            if($("#transportUnitShow").val() != ""){
                var transportUnit = SSCCommon.clearComma($("#transportUnitShow").val());
                $("#transportUnit").val(transportUnit);
                if(SSCCommon.MONEY_REG.test(transportUnit)) {
                    var transportCost = SSCCommon.roundFixed(SSCCommon.multiply(transportUnit, SSCCommon.divide(SSCCommon.clearComma($("#sumProductQua").html()), 1000)), 2);
                    $("#transportUnitShow").val(SSCCommon.formatMoney($("#transportUnitShow").val()));
                    $("#transportCost").val(transportCost);
                    $("#transportCostStr").html(SSCCommon.formatMoney(transportCost));
                    //总金额
                    var amount = SSCCommon.roundFixed(SSCCommon.add(transportCost, $("#sumProductValue").text()), 2);
                    $("#amount").val(amount);
                    $("#amountStr").html(SSCCommon.formatMoney(amount));
                }
            }
        });
    },
    bindNameSelect: function() {
        $(SSC11306.purchaserId).change(function() {
            SSC11306.setParam(SSC11306.purchaserId,"purchaserId");
        });
        $(SSC11306.supplierId).change(function() {
            SSC11306.setParam(SSC11306.supplierId,"supplierId");
        });
        $(SSC11306.lgcsAreaCode).change(function() {
            SSC11306.setParam(SSC11306.lgcsAreaCode,"lgcsAreaCode");
        });
    },
    setParam: function(id,epIdObj) {
        var name = $(id).find("option:selected").text().trim();
        var epId = $(id).find("option:selected").attr(epIdObj);
        if(id == SSC11306.purchaserId){
            $("#purchaserName").val(name);
            $("#" + epIdObj).val(epId);
        }else if(id == SSC11306.supplierId){
            $("#supplierName").val(name);
            $("#" + epIdObj).val(epId);
        }else if(id == SSC11306.lgcsAreaCode){
            $("#lgcsAreaName").val(name);
            //$("#" + epIdObj).val(epId);
        }
    },
    contractCode: function () {
        Main.detailWindow(Main.contextPath + "/SSC11304/show/",  {
            "deliveryId":$("#deliveryId").val(),
            "contractId": $('#contractId').val()
        }, "合同详细");
    },
    cellOnBlurHandler : function($comp){
        //获得TD
        var $td = $comp.parent();
        //获得TR
        var $tr = $td.parent();
        //获得所有行
        var $trs = $tr.parent();
        //获得当前行
        SSC11306.THISROWINDEX = $trs.children().index($tr);
        SSC11306.THISROWDATA = $List_Grid.fnGetData(SSC11306.THISROWINDEX);
        //获得当前列数据
        var num = $comp.val();
        //获得当前列name
        var name = $td.context.name;
        if(name == "productBox"){
            //获取净重
            var weight = SSC11306.THISROWDATA["weightVal"];
            if (SSCCommon.NATURAL_NUMBER.test(num) && num <= SSCCommon.INT11) {
                //赋值
                SSC11306.THISROWDATA["productQua"] = SSCCommon.roundFixed(SSCCommon.multiply(weight ,num),4);
                SSC11306.THISROWDATA["productValue"] = SSCCommon.roundFixed(SSCCommon.multiply(SSC11306.THISROWDATA["productQua"] ,SSC11306.THISROWDATA["settkementStandardPrice"]),2);
            }
        }
        if(name == "settkementStandardPrice"){
            if (SSCCommon.isMoney(num)) {
                SSC11306.THISROWDATA["productValue"] = SSCCommon.roundFixed(SSCCommon.multiply(SSC11306.THISROWDATA["productQua"] ,num),2);
            }
        }

        //计算合计
         var allData = $List_Grid.fnGetData();
         var sumProductBox = 0.0;
         var sumProductQua = 0.0;
         var sumStandardPrice = 0.0;
         var sumProductValue = 0.0;
         for (var i = 0; i < allData.length; i++) {
         
         if(name == "productBox" && SSCCommon.NATURAL_NUMBER.test(num)){
         if(SSC11306.THISROWINDEX == i){
         sumProductBox = SSCCommon.add(sumProductBox, SSCCommon.clearComma(num));
         }else{
         sumProductBox = SSCCommon.add(sumProductBox, parseFloat(allData[i]["productBox"]));
         }
         sumStandardPrice = SSCCommon.add(sumStandardPrice,parseFloat(allData[i]["settkementStandardPrice"]));
         }
         else if(name == "settkementStandardPrice"){
         if(SSC11306.THISROWINDEX == i){
         sumStandardPrice = SSCCommon.add(sumStandardPrice,parseFloat(num));
         }else{
         sumStandardPrice = SSCCommon.add(sumStandardPrice,parseFloat(allData[i]["settkementStandardPrice"]));
         }
         sumProductBox = SSCCommon.add(sumProductBox, parseFloat(allData[i]["productBox"]));
         }
         else{
         sumProductBox = SSCCommon.add(sumProductBox, parseFloat(allData[i]["productBox"]));
         sumStandardPrice = SSCCommon.add(sumStandardPrice,parseFloat(allData[i]["settkementStandardPrice"]));
         }
         sumProductQua = SSCCommon.add(sumProductQua,parseFloat(allData[i]["productQua"]));
         sumProductValue = SSCCommon.add(sumProductValue,parseFloat(allData[i]["productValue"]));
         }

         $("#sumProductBox").html(fmoney(sumProductBox,0));
         $("#sumProductQua").html(fmoney(sumProductQua,4));
         $("#sumProductQuaShow").html(fmoney(sumProductQua,4));
         $("#sumStandardPrice").html(SSCCommon.roundFixed(sumStandardPrice,2));
         $("#sumProductValue").html(SSCCommon.roundFixed(sumProductValue,2));
         $("#sumProductValueShow").html(SSCCommon.formatMoney(SSCCommon.roundFixed(sumProductValue,2)));

         //本次运费transportCost =  每吨运费transportUnit * 预计发货吨数
         var transportCost = SSCCommon.roundFixed(SSCCommon.multiply($("#transportUnit").val() ,SSCCommon.divide(sumProductQua,1000)),2);
         $("#transportCost").val(transportCost);
         $("#transportCostStr").html(SSCCommon.formatMoney(transportCost));

         //总金额
         var amount = SSCCommon.roundFixed(SSCCommon.add(transportCost,$("#sumProductValue").text()),2);
         $("#amount").val(amount);
         $("#amountStr").html(SSCCommon.formatMoney(amount));

    },
    setInput:function(){
        $("#freightUnitShow").attr("disabled", false);
        $("#transportUnitShow").attr("disabled", false);
        $("#transportCost").attr("disabled", false);

        //总金额=货值合计+本次运费
        var amount = SSCCommon.roundFixed(SSCCommon.add($("#transportCost").val() , $("#sumProductValue").text()),2);
        $("#amount").val(amount);
        $("#amountStr").html(SSCCommon.formatMoney(amount));
    },
    bindButton:function(){
        //单选框选择
        $("#freightSettleMethod1").click(function(){
            $("#freightUnitShow").val("");
            $("#freightUnit").val("");
            $("#transportUnitShow").val("");
            $("#transportUnit").val("");
            $("#transportCost").val("");
            $("#transportCostStr").html("");
            $("#freightUnitShow").attr("disabled", true);
            $("#transportUnitShow").attr("disabled", true);

            //总金额=货值合计+本次运费
            var amount = SSCCommon.roundFixed(SSCCommon.add($("#transportCost").val() , $("#sumProductValue").text()),2);
            $("#amount").val(amount);
            $("#amountStr").html(SSCCommon.formatMoney(amount));
        });
        $("#freightSettleMethod2").click(function(){
            SSC11306.setInput();
        });
        $("#freightSettleMethod3").click(function(){
            SSC11306.setInput();
        });
        $("#freightSettleMethod4").click(function(){
            SSC11306.setInput();
        });
        //添加产品
        $("#SSC11306_ADD").click(function(){
            $.pdialog.open("添加产品", Main.contextPath + "/SSC11306/init2", {
                width: 550,
                height: 380
            },{
                deliveryId:$("#deliveryId").val(),
                deliveryCode:$("#deliveryCode").val(),
                purchaserCode:$("#purchaserCode").val(),
                contractId:$("#contractId").val(),
                supplierId:$("#supplierId").val(),
                contractRelationType:$("#contractRelationType").val()
            });
        });
        //保存基本信息及运费结算方式
        $("#SSC11306_SAVE").click(function(){
            if(!SSC11306.checkVal()){
                return;
            }
            
            if($("#deliveryId").val() != null && $("#deliveryId").val() != ""){
                $.alertMessage.confirm("确定要保存当前数据吗?", function() {
                    $.alertMessage.close();
                    formData = getFormData($("#SSC11306Form"));
                    $('#main-content').postUrl(Main.contextPath + "/SSC11306/modifyOrderBasic", formData,Main.hiddenHeader);
                });
            }else{
                $.alertMessage.confirm("确定要保存当前数据吗?", function() {
                    $.alertMessage.close();
                    formData = getFormData($("#SSC11306Form"));
                    $('#main-content').postUrl(Main.contextPath + "/SSC11306/saveOrderBasic", formData,function(data) {
                        //$.alertMessage.info("操作成功");
                        $("#headBreadCrumb").hide();
                    });
                });
            }
            
        });
        //保存所有信息
        $("#SSC11306_MODIFY").click(function(){
            if(!SSC11306.checkVal()){
                return;
            }
            //获取改动的数据对象(数组)
            var changeData = $List_Grid.getChangeData();
            for(i = 0;i < changeData.length;i++){
                if(!SSCCommon.POSITIVE_INTEGER.test(changeData[i]["productBox"])){
                    $.alertMessage.info("发货箱数不能为空，且必须是正整数！");
                    return;
                }
                if(changeData[i]["productBox"] > SSCCommon.INT11){
                    $.alertMessage.info("发货箱数不能超过99999999！");
                    return;
                }
                if(!SSCCommon.WEIGHT_REG.test(changeData[i]["productQua"])){//> 9999999999999999.9999
                    $.alertMessage.info("发货数量格式错误（整数位最多12位，小数位最多4位）！");
                    return;
                }
                if(!SSCCommon.MONEY_REG.test(changeData[i]["trunkFreight"])){// > 999999999999999999.99
                    $.alertMessage.info("干线运费格式错误（整数位最多15位，小数位最多2位）！");
                    return;
                }
                if(!SSCCommon.MONEY_REG.test(changeData[i]["cif"])){// > 999999999999999999.99
                    $.alertMessage.info("到岸价格式错误（整数位最多15位，小数位最多2位）！");
                    return;
                }
                if(!SSCCommon.isMoney(changeData[i]["settkementStandardPrice"])){// > 999999999999999999.99
                    $.alertMessage.info("结算标准价不能为空或0，且格式为：整数位最多15位，小数位最多2位！");
                    return;
                }
                if(!SSCCommon.MONEY_REG.test(changeData[i]["productValue"])){// > 999999999999999999.99
                    $.alertMessage.info("货值格式错误（整数位最多15位，小数位最多2位）！");
                    return;
                }
                if(changeData[i]["remark"].length > 100){
                    $.alertMessage.info("备注长度超过100字符，请重新输入");
                    return;
                }
            }
            if(!SSCCommon.MONEY_REG.test($("#amount").val())){
                $.alertMessage.info("总金额格式错误（整数位最多15位，小数位最多2位）！");
                return false;
            }
            var allData = $List_Grid.fnGetData();
            if(allData.length == 0){
                $.alertMessage.info("请先选择产品信息");
                return;
            }
            $.alertMessage.confirm("确定要保存当前数据吗?", function() {
                $.alertMessage.close();
                //创建json对象
                var json = {};
                for(i = 0;i < allData.length;i++){
                    //把数组的对象封装到json
                    json[i] = {"detailId":allData[i]["detailId"],"productBox":allData[i]["productBox"],"productQua":allData[i]["productQua"],
                        "trunkFreight":allData[i]["trunkFreight"],"cif":allData[i]["cif"],
                        "settkementStandardPrice":allData[i]["settkementStandardPrice"],
                        "productValue":allData[i]["productValue"],"remark":allData[i]["remark"]};
                }
                //转成jsonStr
                var jsonStr = JSON.stringify(json);
                $("#jsonStr").val(jsonStr);
                formData = getFormData($("#SSC11306Form"));
                $('#main-content').postUrl(Main.contextPath + "/SSC11306/modify", formData,function(data) {
                    $.alertMessage.info("操作成功");
                });
            })
        });
        //甲方审核
        $("#SSC11306_AUDIT").click(function(){
            //获取改动的数据对象(数组)
            var changeData = $List_Grid.getChangeData();
            if(changeData.length > 0){
                changeFlag = true;
            }
            if(changeFlag){
                $.alertMessage.info("请先保存数据");
                return;
            }
            if(!SSC11306.checkVal()){
                return;
            }
            var allData = $List_Grid.fnGetData();
            if(allData.length == 0){
                $.alertMessage.info("请先选择产品信息");
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/SSC11306/modifyOrderBasic/",{deliveryStatus:2,deliveryId:$("#deliveryId").val(),ver:$("#ver").val()},function(data) {
                $.alertMessage.info("审核成功");
                $("#headBreadCrumb").hide();
            });
        });
        //乙方确认
        $("#SSC11306_CONFIRM").click(function(){
            //获取改动的数据对象(数组)
            var changeData = $List_Grid.getChangeData();
            if(changeData.length > 0){
                changeFlag = true;
            }
            if(changeFlag){
                $.alertMessage.info("请先保存数据");
                return;
            }
            if(!SSC11306.checkVal()){
                return;
            }
            var allData = $List_Grid.fnGetData();
            if(allData.length == 0){
                $.alertMessage.info("请先选择产品信息");
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/SSC11306/modifyOrderBasic/",{deliveryStatus:3,deliveryId:$("#deliveryId").val(),ver:$("#ver").val()},function(data) {
                $.alertMessage.info("确认成功");
                $("#headBreadCrumb").hide();
            });
        });
        //再修改
        $("#SSC11306_EDIT").click(function(){
            $('#main-content').postUrl(Main.contextPath + "/SSC11316/findPreInto" ,{
                deliveryCode:$("#deliveryCode").val()
            },function(data) {
                //1 没有生成预入库通知单  2 已发车  3  未发车
                if(data.flag != 2){
                    $('#main-content').postUrl(Main.contextPath + "/SSC11308/checkDeliveryOrderPayed" ,{
                        deliveryId:$("#deliveryId").val()
                    },function(data) {
                        if(data == "S"){
                            $.alertMessage.info("该发货订单已生成付款申请且已付款,不允许再修改！");
                            $("#headBreadCrumb").hide();
                        }else{
                            $.alertMessage.confirm("再修改需要双方再次确认，是否修改?", function() {
                                $.alertMessage.close();
                                $('#main-content').postUrl(Main.contextPath + "/SSC11306/modifyOrderBasic/",{deliveryStatus:1,deliveryId:$("#deliveryId").val(),ver:$("#ver").val()},function(data) {
                                    $("#headBreadCrumb").hide();
                                });
                            })
                        }
                    },{refreshHtml:false});
                }else{
                    $.alertMessage.info("该发货订单已发车,不允许再修改！");
                    $("#headBreadCrumb").hide();
                }
            },{refreshHtml:false});
        });
        //发起付款申请
        $("#SSC11306_CREATE").click(function(){
            Main.detailWindow(Main.contextPath + "/SSC11308/init/2",  {
                deliveryId:$("#deliveryId").val(),
                deliveryCode:$("#deliveryCode").val()
            },"付款申请",{refreshHtml:false});
        });
        //关联合同
        $("#SSC11306_CONTRACT").click(function(){
            //$.pdialog.open("选择关联合同", Main.contextPath + "/SSC11306/chooseContract",{width: "30%"},null);

            $.pdialog.open("选择合同信息", Main.contextPath + "/SSC1130301/init", {width: 1200, height: 500}, {
                "isRelationContract":"true",//是否关联合同
                "contractStatusStr":"0,1,2,3,4,5,6"
            },"chooseEpDialog");
        });
        //重新关联合同
        $("#SSC11306_RCONTRACT").click(function(){
            //$.pdialog.open("选择关联合同", Main.contextPath + "/SSC11306/chooseContract",{width: "30%"},null);

            $.pdialog.open("选择合同信息", Main.contextPath + "/SSC1130301/init", {width: 1200, height: 500}, {
                "isRelationContract":"true",//是否关联合同
                "contractStatusStr":"0,1,2,3,4,5,6"
            },"chooseEpDialog");
        });
    },
    checkVal:function(){
        //无合同信息时发货车次必填
        var flag = false;
        if($("#contractId").val() == "" && $("#deliveryBatch").val() == "") {
            flag = true;
        }
        if($("#supplierSelect").val() == "" || $("#purchaserSelect").val() == "" || $("#deliveryWarehouseAddr").val() == ""
            || $("#arriveWarehouse").val() == "" || $("#arriveWarehouseAddr").val() == ""
            || $("#etd").val() == "" || $("#eta").val() == "" || $("#mileage").val() == "" || $("#lgcsAreaCode").val() == "" || flag) {
            $.alertMessage.info("发货基本信息所有输入项必填");
            return;
        }
        if($("#deliveryBatch").val() == 0){
            $.alertMessage.info("到货车次不能为0！");
            return false;
        }
        if(!SSCCommon.isMileage($("#mileage").val())){
            $.alertMessage.info("公里数不能0或空，且格式为：整数位最多8位，小数位最多2位！");
            return false;
        }
        if($("#freightUnit").val() != "" && !SSCCommon.MONEY_REG.test($("#freightUnit").val())){
            $.alertMessage.info("运费单价格式错误（整数位最多15位，小数位最多2位）！");
            return false;
        }
        if($("#transportUnit").val() != "" && !SSCCommon.MONEY_REG.test($("#transportUnit").val())){
            $.alertMessage.info("每吨运费格式错误（整数位最多15位，小数位最多2位）！");
            return false;
        }
        if($("#transportCost").val() != "" && !SSCCommon.MONEY_REG.test($("#transportCost").val())){
            $.alertMessage.info("本次运费格式错误（整数位最多15位，小数位最多2位）！");
            return false;
        }
        if(!$("#freightSettleMethod1").is(":checked") && $("#freightUnit").val() == ""){
            $.alertMessage.info("非到岸价运输时必须填写运费单价");
            return;
        }
        if($("#etd").val() > $("#eta").val()){
            $.alertMessage.info("到货日期应大于发货时间");
            return;
        }
        if($("#amount").val() !="" && !SSCCommon.MONEY_REG.test($("#amount").val())){
            $.alertMessage.info("总金额格式错误（整数位最多15位，小数位最多2位）！");
            return false;
        }

        return true;
    },
    //格式化
    formatMoney:function(){
        //到货车次
        if($("#deliveryBatch").val() != ""){
            $("#deliveryBatchShow").val(fmoney($("#deliveryBatch").val(),0));
        }
        //公里数
        if($("#mileage").val() != ""){
            $("#mileageShow").val(SSCCommon.formatMoney($("#mileage").val()));
        }
        //运费单价
        if($("#freightUnit").val() != ""){
            $("#freightUnitShow").val(SSCCommon.formatMoney($("#freightUnit").val()));
        }
        //每吨运费
        if($("#transportUnit").val() != ""){
            $("#transportUnitShow").val(SSCCommon.formatMoney($("#transportUnit").val()));
        }
    }/*,
    bindShowEpInfo:function(){
        $("#chooseEpInfo").click(function () {
            $.pdialog.open("选择企业信息", Main.contextPath + "/SSC1130803/init", {width: 1200, height: 500}, {
                "supplierInputId":"purchaserSelect"
            },"chooseEpDialog");
        })
    },
    bindShowRpInfo:function(){
        $("#chooseRpInfo").click(function () {
            $.pdialog.open("选择企业信息", Main.contextPath + "/SSC1130803/init", {width: 1200, height: 500}, {
                "supplierInputId":"supplierSelect"
            },"chooseEpDialog");
        })
    }*/
}

$(document).ready(function () {
    // 初始化调用
    SSC11306.init();
    SSC11306.bindNameSelect();
    var flag = $("#freightSettleMethod").val();
    var deliveryStatus = $("#deliveryStatus").val();
    if(deliveryStatus != 9){
        if(flag == "" || flag == 1){
            $("#freightUnitShow").attr("disabled", true);
            $("#transportUnitShow").attr("disabled", true);
        }else if(flag == 2){
            $("#freightUnitShow").attr("disabled", false);
            $("#transportUnitShow").attr("disabled", false);
        }else if(flag == 3){
            $("#freightUnitShow").attr("disabled", false);
            $("#transportUnitShow").attr("disabled", false);
        }else if(flag == 4){
            $("#freightUnitShow").attr("disabled", false);
            $("#transportUnitShow").attr("disabled", false);
        }
    }
    //禁用回车
    SSCCommon.disableEnterKey();
    //展示格式化后的金额
    SSCCommon.showFormatMoney();
    SSC11306.formatMoney();

    if( $("#navigation").val()=='requestDetail'){
        return;
    }
    Main.hlLeftMainMenu("SSC11305");

    /*SSC11306.bindShowEpInfo();
    SSC11306.bindShowRpInfo();*/

});
