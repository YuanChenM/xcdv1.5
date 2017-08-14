/**
 * 中标明细JS
 *
 */
var $List_Grid;

//验证文本框内容是否修改
function validateChangeData(){
    //数据修改标识
    var changeFlg = false;
    if($("#bidProjectName").val()!=$("#bidProjectNameChange").val()) {
        changeFlg = true;
    }
    if($("#startDate").val()!=$("#startDateChange").val()) {
        changeFlg = true;
    }
    if($("#endDate").val()!=$("#endDateChange").val()) {
        changeFlg = true;
    }

    var changeData = $List_Grid.getChangeData();
    if(changeData.length>=1){
        changeFlg = true;
    }

    return changeFlg;

}


var SSC11302 = {
    createContractsButtonId : "SSC11302_CREATECONTRACTS",
    startDate:"#startDate",
    endDate:"#endDate",
    init: function () {
        $List_Grid = $('#SSC11302_list_grid').grid({
            paginate: false,
            actionHandler: SSC11302.actionHandler,
            linkHandler: SSC11302.linkHandler,
            editCellOnBlurHandler :SSC11302.cellOnBlurHandler,
            fnRowCallback: SSC11302.rowCallback
        });
        this.closeDate();
        this.bindAddBtn();
        this.bindCreateContractButton();
        this.bindDatePicker(SSC11302.startDate);
        this.bindDatePicker(SSC11302.endDate);
        this.bindLoadProEnterprises(); //根据甲方加载对应的生产商

    },
    bindLoadProEnterprises: function() {
        $("#purchaserNameSelect").change(function(){
            var slCode=$(this).find("option:selected").val();
            var slMainClass=$(this).find("option:selected").attr("slMainClass");
            $('#main-content').postUrl(Main.contextPath + '/SSC11302/getProEnterprises',{slCode:slCode,slMainClass:slMainClass},function(data){
                $('#supplierNameSelect option:not(:first)').remove();
                $.each(data, function(i, item) {
                    $('#supplierNameSelect').append("<option supplierId='" + item.epId + "'supplierName='"+item.epName+"'value='" + item.slCode+ "'>"+ item.epName + "</option>");
                });
                $("#headBreadCrumb").hide();
            },{refreshHtml:false});
        });
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    bindDatePicker : function(time){
        $(time).datepicker({
            showButtonPanel: true,
            dateFormat:'yy-mm-dd',
            changeMonth: true,
            closeText: "清除",
            changeYear: true
        });
        $(time).attr("readonly","readonly");
    },

    rowCallback: function(tr, data) {
        if(tr._DT_RowIndex == 0){
            var sumProductQua = data.sumProductQua;
            sumProductQua = parseFloat(sumProductQua.toFixed(4));
            $("#sumProductQua").html(fmoney(sumProductQua,4));
            $("#sumProductBox").html(fmoney(data.sumProductBox,0));
            $("#sumProductValue").html(SSCCommon.formatMoney(data.sumProductValue));
        }
    },

    cellOnBlurHandler : function($comp){
        //获得TD
        var $td = $comp.parent();
        //获得TR
        var $tr = $td.parent();
        //获得所有行
        var $trs = $tr.parent();
        //获得当前行
        SSC11302.THISROWINDEX = $trs.children().index($tr);
        SSC11302.THISROWDATA = $List_Grid.fnGetData(SSC11302.THISROWINDEX);
        //获得当前列数据
        var num = SSCCommon.replaceComma($comp.val());
        if(!SSCCommon.NATURAL_NUMBER.test(num)){
            num = 0.00;
        }

        //获得当前列name
        var name = $td.context.name;

        var settkementStandardPrice = SSCCommon.clearComma(SSC11302.THISROWDATA["settkementStandardPrice"]);

        if(name == "productBox"){
            //获取净重
            var weight = SSC11302.THISROWDATA["weightVal"];
            //赋值
            var weightVal = SSCCommon.multiply(weight,num);
            SSC11302.THISROWDATA["productValue"] = parseFloat(SSCCommon.multiply(weightVal ,settkementStandardPrice)).toFixed(2);
        }
        if(name == "settkementStandardPrice"){
            //获取净重
            var weight = SSC11302.THISROWDATA["weightVal"];
            //赋值
            var weightVal = SSCCommon.multiply(weight ,num);
            SSC11302.THISROWDATA["productValue"] = (SSCCommon.multiply(weightVal ,SSCCommon.clearComma(SSC11302.THISROWDATA["productBox"]))).toFixed(2);
        }
        //计算合计
        var allData = $List_Grid.fnGetData();
        var sumProductBox = 0.0;
        var sumProductQua = 0.0;
        var sumProductValue = 0.0;
        var sumStandardPrice = 0.0;
        for (var i = 0; i < allData.length; i++) {
            var productBoxTemp = allData[i]["productBox"];
            if(SSCCommon.FORMAT_MONEY_REG.test(productBoxTemp)){
                productBoxTemp = SSCCommon.clearComma(productBoxTemp);
            }

            var priceTemp = allData[i]["settkementStandardPrice"];
            if(SSCCommon.FORMAT_MONEY_REG.test(priceTemp)){
                priceTemp = SSCCommon.clearComma(priceTemp);
            }


            var productQuaTemp = allData[i]["productQua"];
            if(SSCCommon.FORMAT_MONEY_REG.test(productQuaTemp)){
                productQuaTemp = SSCCommon.clearComma(productQuaTemp);
            }

            var productValueTemp = allData[i]["productValue"];
            if(SSCCommon.FORMAT_MONEY_REG.test(productValueTemp)){
                productValueTemp = SSCCommon.clearComma(productValueTemp);
            }

            if(name == "productBox"){
                if(SSC11302.THISROWINDEX == i){
                    sumProductBox = SSCCommon.add(sumProductBox, num);
                }else{
                    sumProductBox = SSCCommon.add(sumProductBox,productBoxTemp);
                }
                sumStandardPrice = SSCCommon.add(sumStandardPrice,priceTemp);
            }
            sumProductQua = SSCCommon.add(sumProductQua,productQuaTemp);
            sumProductValue = SSCCommon.add(sumProductValue,productValueTemp);
        }
        $("#sumProductBox").html(fmoney(sumProductBox,0));
        $("#sumProductQua").html(fmoney(sumProductQua,4));
        $("#sumProductValue").html(fmoney(sumProductValue,2));

    },
    actionHandler: function (rowdata, coltype, row, col) {

        if (coltype == "audit") {
            $.pdialog.open("产品质量标准", Main.contextPath + "/SSC11302/getPdStandards", {width: "50%", height: 800}, {
                classesCode:rowdata.classesCode,
                machiningCode:rowdata.machiningCode,
                breedCode:rowdata.breedCode
            });
        }



        if (coltype == "save") {
            if(!checkBidStatus()){
                return;
            }

            if(!checkChangeData()){
                return;
            }
            var bidId = $("#bidCode").val();
            var type = $("#type").val();
            $('#main-content').postUrl(Main.contextPath + "/SSC11302/save/",{
                detailId: rowdata.detailId,
                pdDesc:rowdata.pdDesc,
                bidId:bidId,
                weightVal:rowdata.weightVal,
                gradeName:rowdata.gradeName,
                productQua:SSCCommon.replaceComma(rowdata.productQua),
                productBox:SSCCommon.replaceComma(rowdata.productBox),
                fobFreePackage:SSCCommon.replaceComma(rowdata.fobFreePackage),
                packageCost:SSCCommon.replaceComma(rowdata.packageCost),
                fobIncludePackage:SSCCommon.replaceComma(rowdata.fobIncludePackage),
                trunkFreight:SSCCommon.replaceComma(rowdata.trunkFreight),
                cif:SSCCommon.replaceComma(rowdata.cif),
                settkementStandardPrice:SSCCommon.replaceComma(rowdata.settkementStandardPrice),
                productValue:SSCCommon.replaceComma(rowdata.productValue),
                remark:rowdata.remark,
                gradeName:rowdata.gradeName,
                ver:rowdata.ver
            }, function (data) {
                if (data == "S") {
                    $('#main-content').postUrl(Main.contextPath + "/SSC11302/insertBidBasicInfo/",{
                        bidId:bidId,
                        type:type,
                        bidProjectNo: $("#bidProjectNo").val(),
                        bidStatus:'0',
                        ver:$("#ver").val()
                    },function(data){
                        $.alertMessage.info("操作成功!");
                        $('#main-content').postUrl(Main.contextPath + "/SSC11302/init/"+type , {
                            bidId:bidId
                        },Main.hiddenHeader);
                    }, {refreshHtml: false});

                } else{
                    $.alertMessage.warn("操作失败!");
                }
            }, {refreshHtml: false});

        }
        if (coltype == "delete") {
            if(!checkBidStatus()){
                return;
            }
            $.alertMessage.confirm("确定删除这条数据吗？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SSC11302/delete/"  ,{
                    detailId:rowdata.detailId,
                    ver:rowdata.ver
                },function (data) {
                    if (data == "S") {
                        var type = $("#type").val();
                        var bidCode =$("#bidCode").val();
                        $("#type").val(type);
                        $("#bid").val(bidCode);
                        $('#main-content').postUrl(Main.contextPath + "/SSC11302/insertBidBasicInfo/",{
                            bidId:bidCode,
                            bidProjectNo: $("#bidProjectNo").val(),
                            bidStatus:'0',
                            type:type,
                            ver:$("#ver").val()
                        },function(data){
                            $('#main-content').postUrl(Main.contextPath + "/SSC11302/init/"+type , {
                                bidId:bidCode
                            },Main.hiddenHeader);

                        }, {refreshHtml: false})

                    } else{
                        $.alertMessage.warn("操作失败!");
                        $.alertMessage.close();
                    }

                }, {refreshHtml: false});
            })
        }

        /**
         * 验证数字格式
         * @param changeData
         * @returns {boolean}
         */
        function checkChangeData(){
            //获取改动的数据对象(数组)
            var changeData = $List_Grid.getChangeData();

            for(var i = 0;i < changeData.length;i++){
                var productBox = SSCCommon.replaceComma(changeData[i]["productBox"]);
                if(!SSCCommon.POSITIVE_INTEGER.test(productBox)){
                    $.alertMessage.info("箱数不能为空，且必须是大于0的整数！");
                    return false;
                }
                if(productBox > SSCCommon.INT11){
                    $.alertMessage.info("箱数不能超过99999999！");
                    return false;
                }

                var fobFreePackage = SSCCommon.replaceComma(changeData[i]["fobFreePackage"]);
                if(fobFreePackage!=null && fobFreePackage!=''){
                    if(!SSCCommon.MONEY_REG.test(fobFreePackage)){// > 999999999999999999.99
                        $.alertMessage.info("不含包装离岸价格式为：整数位最多15位，小数位最多2位！");
                        return false;
                    }
                }

                var packageCost = SSCCommon.replaceComma(changeData[i]["packageCost"]);
                if(packageCost!=null && packageCost!=''){
                    if(!SSCCommon.MONEY_REG.test(packageCost)){// > 999999999999999999.99
                        $.alertMessage.info("包材成本格式为：整数位最多15位，小数位最多2位！");
                        return false;
                    }
                }

                var fobIncludePackage = SSCCommon.replaceComma(changeData[i]["fobIncludePackage"]);
                if(fobIncludePackage!=null && fobIncludePackage!=''){
                    if(!SSCCommon.MONEY_REG.test(fobIncludePackage)){// > 999999999999999999.99
                        $.alertMessage.info("含离岸包装价格式为：整数位最多15位，小数位最多2位！");
                        return false;
                    }
                }

                var trunkFreight = SSCCommon.replaceComma(changeData[i]["trunkFreight"]);
                if(trunkFreight!=null && trunkFreight!=''){
                    if(!SSCCommon.MONEY_REG.test(trunkFreight)){// > 999999999999999999.99
                        $.alertMessage.info("干线运费格式为：整数位最多15位，小数位最多2位！");
                        return false;
                    }
                }

                var cif = SSCCommon.replaceComma(changeData[i]["cif"]);
                if(cif!=null && cif!=''){
                    if(!SSCCommon.MONEY_REG.test(cif)){// > 999999999999999999.99
                        $.alertMessage.info("到岸价格式为：整数位最多15位，小数位最多2位！");
                        return false;
                    }
                }

                var settkementStandardPrice = SSCCommon.replaceComma(changeData[i]["settkementStandardPrice"]);
                if(!SSCCommon.isMoney(settkementStandardPrice)){// > 999999999999999999.99
                    $.alertMessage.info("本次结算标准价不能为空或0，且格式为：整数位最多15位，小数位最多2位！");
                    return false;
                }

                if(changeData[i]["remark"].length > 101){
                    $.alertMessage.info("备注长度超过100字符，请重新输入");
                    return false;
                }
            }
            return true;
        }
    },


    /*跳转到详细页面*/
    linkHandler: function (rowdata, coltype, row, col) {
            $.pdialog.open("产品详细信息", Main.contextPath + "/SSC11302/showProduct", {width: "40%"}, {
                salesName: rowdata.salesName,
                scientificName: rowdata.scientificName,
                localName: rowdata.localName,
                featureName: rowdata.featureName,
                weightName: rowdata.weightName,
                normsName: rowdata.normsName
            });
    },
    /*生成合同按钮*/
    bindCreateContractButton : function() {
        $("#" + SSC11302.createContractsButtonId).click(function() {
            $.alertMessage.confirm("确定要生成合同吗?", function() {
                $.alertMessage.close();
                SSC11302.createContract();
            })

        });
    },
    createContract:function(){
        //判断中标状态 /**待确认  0*/   /**采购商已确认1*/  /**生产商已确认2*/   /**已确认3*/   /**执行中4*/    /**已结束5*/
        var bidStatus = $("#bidStatus").val();
        if(bidStatus!=3){
            $.alertMessage.info("采购商/中标商未确认,无法生成合同!");
             return;
        }
        var bid=$("#bidCode").val();
        if(bid==""){
            $.alertMessage.info("因中标详细没有保存，不能生成合同！");
            return;
        } else{
            $('#main-content').postUrl(Main.contextPath+"/SSC11304/checkBid",{"bidId":bid},function(data){
                if(data=="S"){
                    Main.detailWindow(Main.contextPath+"/SSC11304/createContracts",{
                        "bidId":bid,
                        "bidRelationType":0
                    },"合同详细");
                }else{
                    $.alertMessage.info("该中标明细已经生成合同！不能再次生成");
                }
            },{refreshHtml: false});
        }
    },

    bindAddBtn: function () {
        /*新增产品信息弹出框*/
        $("#SSC11302_ADDPRODUCT").click(function () {

           if(!checkBidStatus()){
               return;
           }
            var bidId = $("#bidCode").val();
            var slCode = $("#slCode").val();
            var supplierId = $("#supplierId").val();
            var ver = $("#ver").val();

            if(supplierId == "") {
                alert("no supplierId");
                return;
            }
            if(bidId== ""){
                $.alertMessage.info("中标成交确认书基本信息未保存，不能添加产品！");
                return;
            }

            $.pdialog.open("新增产品", Main.contextPath + "/SSC1130203/init", {width: 600, height: 380}, {
                slCode:slCode,
                bidId:bidId,
                supplierId: supplierId,
                bidProjectNo: $("#bidProjectNo").val(),
                type: $("#type").val(),
                ver: ver
            });
        })

        /*新增中标基本信息(上表格)*/
        $("#SSC1130202_ADD").click(function () {
            var bidProjectNo=$("#bidProjectNo").val();
            var purSelectObj = $("#purchaserNameSelect option:selected");
            var supSelectObj = $("#supplierNameSelect option:selected");
            var purchaserCode=purSelectObj.val();
            var purchaserName = purSelectObj.attr("purchaserName");
            var purchaserId = purSelectObj.attr("purchaserId");
            var supplierCode =supSelectObj.val();
            var supplierName = supSelectObj.attr("supplierName");
            var supplierId = supSelectObj.attr("supplierId");
            var bidProjectName=$("#bidProjectName").val();
            var bidStartDate=$("#startDate").val();
            var bidEndDate=$("#endDate").val();
            var bidId = $("#bidCode").val();
            var type = $("#type").val();
            var ver = $("#ver").val();


            if(purchaserName == undefined){
                purchaserName = $("#purchaserName").val();
            }
            if(purchaserName==''){
                $.alertMessage.info("招标公司名称不能为空！");
                return;
            }

            if(supplierName == undefined ){
                supplierName = $("#supplierName").val();
            }
            //if(supplierCode== undefined){
            //    supplierCode = $("#supplierCode").val();
            //}

            if(supplierName==''){
                $.alertMessage.info("中标公司名称不能为空！");
                return;
            }

            if( bidProjectName==''){
                $.alertMessage.info("招标项目名称不能为空！");
                return;
            }

            if(bidStartDate==''){
                $.alertMessage.info("开标开始时间不能为空！");
                return;
            }
            if(bidEndDate==''){
                $.alertMessage.info("开标截止时间不能为空！");
                return;
            }

            if(bidEndDate < bidStartDate){
                $.alertMessage.info("开标开始时间应该小于开标截止时间");
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/SSC11302/insertBidBasicInfo/",{
                bidId:bidId,
                bidProjectName:bidProjectName,
                bidProjectNo:bidProjectNo,
                purchaserName:purchaserName,
                purchaserCode:purchaserCode,
                purchaserId:purchaserId,
                supplierName:supplierName,
                supplierCode:supplierCode,
                supplierId:supplierId,
                startDate:bidStartDate,
                endDate:bidEndDate,
                bidStatus:'0',
                type:type,
                ver:ver
            },function(data){
                data =  eval("(" + data + ")");
                if(data.rsStatus=="S"){
                    $("#bidCode").val(data.bid);
                    $("#bid").val(data.bid);
                    $("#bidProjectNo").val(data.bidProjectNo);
                    $('#main-content').postUrl(Main.contextPath + "/SSC11302/init/"+type , {
                        bidProjectNo:data.bidProjectNo,
                        purchaserCode:purchaserCode,
                        bidProjectName:bidProjectName,
                        purchaserName:purchaserName,
                        supplierName:supplierName,
                        startDate:bidStartDate,
                        endDate:bidEndDate,
                        bidId:data.bid,
                        supplierId:supplierId,
                        bidStatus:'0',
                        ver:ver
                    },function(){
                        $.alertMessage.info(data.message);
                        $("#headBreadCrumb").hide();
                    });
                }else{
                    $.alertMessage.info(data.message);
                    $("#headBreadCrumb").hide();
                }
            },{refreshHtml: false})


        })

        //采购商确认按钮
        $("#SSC1130202_SCC").click(function (){

            if(!checkPd()){
                return;
            }

            if($("#bidProjectNo").val()==""){
                $.alertMessage.warn("输入的项目编号为空,请重新输入 !");
                return false;
            }

            if(validateChangeData()){
                $.alertMessage.confirm("数据未保存，确定要操作吗?", function() {
                    $.alertMessage.close();
                    buyerSubmit();
                })
            }else{
                buyerSubmit();
            }
        })

        //再修改
        $("#SSC1130202_REMODIFY").click(function (){

            $.alertMessage.confirm("再次修改后需要甲乙双方再次盖章", function() {
                $.alertMessage.close();
                var bidCode = $("#bidCode").val();
                var type = $("#type").val();
                var ver = $("#ver").val();
                var bidStatus = "0";
                $('#main-content').postUrl(Main.contextPath + "/SSC11302/modifyBidStatus/"+bidStatus,{
                    bidId:bidCode,
                    type:type,
                    ver:ver
                }, function (data) {
                    if (data == "S") {
                        $("#type").val(type);
                        $("#bid").val(bidCode);
                        $('#main-content').postUrl(Main.contextPath + "/SSC11302/init/"+type , {
                            slCode:$("#slCode").val(),
                            bidProjectNo:$("#bidProjectNo").val(),
                            bidProjectName:$("#bidProjectName").val(),
                            purchaserName:$("#purchaserName").val(),
                            supplierName:$("#supplierName").val(),
                            supplierId:$("#supplierId").val(),
                            startDate:$("#startDate").val(),
                            endDate:$("#endDate").val(),
                            bidStatus:bidStatus,
                            bidId:bidCode
                        },Main.hiddenHeader);
                    } else{
                        $.alertMessage.info("合同已经生成,无法再修改!");
                    }
                }, {refreshHtml: false});
            })
        })


        //中标商确认按钮
        $("#SSC1130202_BID").click(function (){
            if($("#bidProjectNo").val()==""){
                $.alertMessage.warn("输入的项目编号为空,请重新输入 !");
                return false;
            }

            if(!checkPd()){
                return;
            }

            if(validateChangeData()){
                $.alertMessage.confirm("数据未保存，确定要操作吗?", function() {
                    $.alertMessage.close();
                    suppSubmit();
                })
            }else{
                suppSubmit();
            }

        })
    }

}

/***
 * 采购商确认
 */
function buyerSubmit(){
    var bidCode = $("#bidCode").val();
    var type = $("#type").val();
    var bidStatus = "1";
    var bidStatusPage = $("#bidStatus").val();//页面上status值
    var ver = $("#ver").val();
    if(bidStatusPage=="1" ||bidStatusPage =="2"){
        bidStatus = "3";
    }
    $('#main-content').postUrl(Main.contextPath + "/SSC11302/modifyBidStatus/"+bidStatus,{
        bidId:bidCode,
        type:type,
        ver:ver
    }, function (data) {
        if (data == "S") {
            $("#type").val(type);
            $("#bid").val(bidCode);
            $('#main-content').postUrl(Main.contextPath + "/SSC11302/init/"+type , {
                slCode:$("#slCode").val(),
                bidProjectNo:$("#bidProjectNo").val(),
                bidProjectName:$("#bidProjectName").val(),
                purchaserName:$("#purchaserName").val(),
                supplierName:$("#supplierName").val(),
                supplierId:$("#supplierId").val(),
                startDate:$("#startDate").val(),
                endDate:$("#endDate").val(),
                bidStatus:bidStatus,
                bidId:bidCode,
                ver:ver
            },Main.hiddenHeader);

        } else{
            $.alertMessage.warn("采购商确认失败 !");
        }
    }, {refreshHtml: false});
}

//判断是否有产品
function checkPd(){
    var content = $("#SSC11302_list_grid").find('tr').eq(2).text();
    if(content=="对不起，没有查询到记录！"){
        $.alertMessage.info("请添加产品！");
        return false;
    }
    return true;
}

/**
 * 供应商确认
 */
function suppSubmit(){
    var bidStatus = 2;
    var bid=$("#bidCode").val();
    var type = $("#type").val();
    var ver = $("#ver").val();
    var bidStatusPage = $("#bidStatus").val();//页面上status值
    if(bidStatusPage=="1" ||bidStatusPage =="2"){
        bidStatus = "8";
    }
    $('#main-content').postUrl(Main.contextPath + "/SSC11302/modifyBidStatus/"+bidStatus,{
        bidId:bid,
        ver:ver
    }, function (data) {
        if (data == "S") {
            // $.alertMessage.info("中标商确认成功 !");
            $("#type").val(type);
            bidStatus = "3";
            $('#main-content').postUrl(Main.contextPath + "/SSC11302/init/"+type , {
                slCode:$("#slCode").val(),
                bidProjectNo:$("#bidProjectNo").val(),
                bidProjectName:$("#bidProjectName").val(),
                purchaserName:$("#purchaserName").val(),
                supplierName:$("#supplierName").val(),
                supplierId:$("#supplierId").val(),
                startDate:$("#startDate").val(),
                endDate:$("#endDate").val(),
                bidStatus:bidStatus,
                bidId:bid,
                ver:ver
            },Main.hiddenHeader);
        } else{
            $.alertMessage.warn("中标商确认失败 !");
        }
    }, {refreshHtml: false});
}

 //校验中标确认单状态:状态为3：已确认时，此时无法编辑
 function checkBidStatus(){
    var bidStatus = $("#bidStatus").val();//页面上status值
     if(bidStatus=="3"){
        $.alertMessage.info("中标成交书已确认,无法操作！");
         return false;
     }
     return true;
 }
//格式化金钱
 function formatMoney(num){
     num = num.toString().replace(/\$|\,/g,'');
     if(isNaN(num))
         num = "0";
     sign = (num == (num = Math.abs(num)));
     num = Math.floor(num*100+0.50000000001);
     cents = num%100;
     num = Math.floor(num/100).toString();
     if(cents<10)
         cents = "0" + cents;
     for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
         num = num.substring(0,num.length-(4*i+3))+','+
         num.substring(num.length-(4*i+3));
     return (((sign)?'':'-') + num + '.' + cents);
 }


$(document).ready(function () {
    // 初始化调用
    $.when(SSC11302.init()).done(function(){
        Main.hlLeftMainMenu("SSC11301")
    });
});

