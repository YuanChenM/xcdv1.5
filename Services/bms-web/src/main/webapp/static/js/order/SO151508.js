var list = new Array();
var SO151508 = {
    forecastSendTime :"#forecastSendTime",
    forecastReceiveTime : "#forecastReceiveTime",
    receiveWaitTime : "#receiveWaitTime",
    init: function () {
        SO151508.focus();
        $("#buyersType").selectmenu({width: "140px"});
        $("#districtCode").selectmenu({width:"140px"});
        $("#orderType").selectmenu({width: "140px", change: SO151508.bindOrderTypeChange});
        $("#salePlatForm").selectmenu({width:"140px"});
        $("#orderSource").selectmenu({width:"140px"});
        $("#paymentType").selectmenu({width:"140px"});
        $("#buyerType").selectmenu({width:"140px"});
        $("#needInvoice").selectmenu({width:"140px"});
        $("#deliveryType").selectmenu({width:"140px"});
        $("#receiveTime").selectmenu({width:"140px"});
        $("#salePlatForm").attr('disabled', 'disabled');
        SO151508.getSelect();
        //$("#checkbox-newReceiveTime").selectmenu({width:"100px"});
        // this.bindDatePlugin(SO151508.forecastSendTime);
        // this.bindDatePlugin(SO151508.forecastReceiveTime);
        // this.bindDatePlugin(SO151508.receiveWaitTime);
        $("#checkbox-newReceiveTime").checkboxSelect();
        $("#checkbox-receiveEarliest").checkboxSelect();
        $("#checkbox-receiveLast").checkboxSelect();
        SO151508.setText();
        // SO151508.closeDate();
        SO151508.bindSearchProduct();
        SO151508.bindSaveOrder();
    },
   /* bindDatePlugin : function(orderTimeId){
        $(orderTimeId).datepicker({
            showButtonPanel: true,
            dateFormat:'yy-mm-dd',
            changeMonth: true,
            changeYear: true,
            closeText: 'Clear'
        });
    },*/
    /*closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },*/
    focus:function () {
        $("#sellerCode").focus();
    },
    getSelect:function () {
        debugger
        var valList = new Array();
        var objList = new Array();
        var newList = new Array();
        $("#receiveTime option").each(function(){ //遍历全部option
            var txt = $(this).text(); //获取option的内容
            var val = $(this).val();
            var obj = new Object();
            obj.txt = txt;
            obj.val = val;
            objList.push(obj);
            valList.push(val);
        });
        valList.sort();
        for(var i = 0; i<valList.length;i++){
           for(var j=0;j<objList.length;j++){
               if(valList[i] == objList[j].val){
                   newList.push(objList[j])
               }
           }
        }
        for(var i = 0; i<newList.length; i++){
            var val = newList[i].val;
            var txt = newList[i].txt;
            var opt = "<option value='"+ val +"'>"+ txt +"</option>";
            $("#checkbox-newReceiveTime").append(opt);
            $("#checkbox-receiveEarliest").append(opt);
            $("#checkbox-receiveLast").append(opt);
        }
    },

    setText:function(){
        /*$('.ui-selectmenu-text').each(function () {
            var myValue = '--请选择--';
            $(this).html(myValue);
            $(this).attr("style","text-align:center");
        });*/
    },

    bindOrderTypeChange: function () {
        var orderType = $("#orderType").val();
        if (orderType == 1 || orderType == 4) {
            $("#sellerCode").val("0000000");
            $("#sellerCode").attr('readonly', 'readonly');
            $("#sellerCode").attr('editmodel', 'false');
            $("#sellerName").val("神农客实业有限公司");
            $("#sellerName").attr('readonly', 'readonly');
            $("#sellerName").attr('editmodel', 'false');
            $('#salePlatForm-button .ui-selectmenu-text').text($('#salePlatForm option:eq(1)').text());
            $("#salePlatForm").val(1);
            $("#salePlatForm").attr('disabled', 'disabled');
            // $('#orderSource-button .ui-selectmenu-text').text($('#orderSource option:eq(2)').text());
            // $('#orderSource').val(1);
        } if(orderType == 2 || orderType == 7){
            $("#sellerCode").val("");
            $("#sellerCode").removeAttr('readonly');
            $("#sellerCode").attr('editmodel', 'true');
            $("#sellerName").val("");
            $("#sellerName").removeAttr('readonly');
            $("#sellerName").attr('editmodel', 'true');
            $('#salePlatForm-button .ui-selectmenu-text').text($('#salePlatForm option:eq(0)').text());
            $("#salePlatForm").val(2);
            $("#salePlatForm").attr('disabled', 'disabled');
            // $('#orderSource-button .ui-selectmenu-text').text($('#orderSource option:eq(1)').text());
            // $('#orderSource').val(2);
        }
        if(orderType == ''){
            $("#sellerCode").val("0000000");
            $("#sellerCode").removeAttr('readonly');
            $("#sellerCode").attr('editmodel', 'true');
            $("#sellerName").val("神农客实业有限公司");
            $("#sellerName").removeAttr('readonly');
            $("#sellerName").attr('editmodel', 'true');
        }
    },
    bindSearchProduct: function () {
        /**选择产品*/
        $("#SO151508_SEARCH").click(function () {
            if (!SO151508.selectPdValidate()) {
                return;
            };
            $.pdialog.open("卖家产品选择", Main.contextPath + "/SO15150801/init", {width: "80%", height: 450},
                {
                    lgcsCode: $("#districtCode").val(),
                    slCode: $("#sellerCode").val(),
                    salePlatform: $("#salePlatForm").val(),
                });
        });

    },
    bindSaveOrder: function () {
        $("#SO151508_SAVE").click(function () {
            if (!SO151508.setValidateList()) {
                return;
            };

            $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                $.alertMessage.close();
                var pdName = $("input[name='pdName']");
                var pdCode = $("input[name='pdCode']");
                var priceCycle = $("input[name='priceCycle']");
                var activeQty = $("input[name='activeQty']");
                var pdPrice = $("input[name='price']");
                var supplierCode = $("input[name='supplierCode']");
                var supplierName = $("input[name='supplierName']");
                var proDate = $("input[name='proDate']");
                var $SO151508Form = $("#SO151508Form");
                $(".hiddenInput").remove();
                $.each(pdName, function (index, context) {
                    var $pdName = $(context).val();
                    $SO151508Form.append("<input class='hiddenInput' type='hidden' name='orderDetailList[" + index + "].pdName' value=" + $pdName + ">");

                    var $pdCode = pdCode[index].value;
                    $SO151508Form.append("<input class='hiddenInput' type='hidden' name='orderDetailList[" + index + "].pdCode' value=" + $pdCode + ">");

                    var $priceCycle = priceCycle[index].value;
                    $SO151508Form.append("<input class='hiddenInput' type='hidden' name='orderDetailList[" + index + "].priceCyclePeriod' value=" + $priceCycle + ">");

                    var $activeQty = activeQty[index].value;
                    $SO151508Form.append("<input class='hiddenInput' type='hidden' name='orderDetailList[" + index + "].activeQty' value=" + $activeQty + ".00" +">");

                    var $pdPrice = pdPrice[index].value;
                    $SO151508Form.append("<input class='hiddenInput' type='hidden' name='orderDetailList[" + index + "].price' value=" + $pdPrice + ">");

                    var $supplierCode = supplierCode[index].value;
                    if($supplierCode != null && $supplierCode != ''){
                        $SO151508Form.append("<input class='hiddenInput' type='hidden' name='orderDetailList[" + index + "].supplierCode' value=" + $supplierCode + ">");
                    }
                    var $supplierName = supplierName[index].value;
                    if($supplierName != null && $supplierName != ''){
                        $SO151508Form.append("<input class='hiddenInput' type='hidden' name='orderDetailList[" + index + "].supplierName' value=" + $supplierName + ">");
                    }
                    var $proDate = proDate[index].value;
                    if($proDate != null && $proDate != ''){
                        $SO151508Form.append("<input class='hiddenInput' type='hidden' name='orderDetailList[" + index + "].proDate' value=" + $proDate + ">");
                    }
                });
                var formData = $SO151508Form.serializeArray();
                var salePlatForm = new Object();
                salePlatForm.name = "order.salePlatform";
                salePlatForm.value = $("#salePlatForm").val();
                formData.push(salePlatForm);
                $('#main-content').postUrl($("#SO151508Form").attr("action"), formData,function(message){
                    if(message == null || message == '') {
                        $.alertMessage.confirm("订单创建成功！",function () {
                            $.alertMessage.close();
                            $("#main-content").postUrl(Main.contextPath + "/SO151501/init");
                        });
                    }else {
                        $.alertMessage.info(message);
                    }
                },{refreshHtml:false});
            });
        });
    },
    setValidateList: function () {
        debugger
        var list = new Array();
        list.push($("#sellerCode"))
        list.push($("#sellerName"))
        list.push($("#districtCode"))
        list.push($("#buyerCode"))
        list.push($("#buyerName"))
        list.push($("#buyerType"))
        list.push($("#orderSource"))
        list.push($("#orderType"))
        list.push($("#paymentType"))
        list.push($("#deliveryType"))
        list.push($("#receiverName"))
        list.push($("#receiverTel"))
        list.push($("#receiverProvince"))
        list.push($("#receiverDistrict"))
        list.push($("#receiverCity"))
        list.push($("#receiverAddr"))
        for (var i = 0; i < list.length; i++) {
            var typeVal = list[i].val();
            var attr = list[i].attr("nativeinput");
            var text = list[i].attr("textname");
            if (attr = true && typeVal == "") {
                $.alertMessage.info("请输入：" + text + "！", function () {
                    $.alertMessage.close();
                });
                return false;
            }
        }
        return true;
    },
    selectPdValidate: function () {
        var selectPdList = new Array();
        selectPdList.push($("#sellerCode"))
        selectPdList.push($("#districtCode"))
        selectPdList.push($("#salePlatForm"))
        selectPdList.push($("#orderType"))
        for (var i = 0; i < selectPdList.length; i++) {
            var typeVal = selectPdList[i].val();
            var attr = selectPdList[i].attr("nativeinput");
            var text = selectPdList[i].attr("textname");
            if (attr = true && typeVal == "") {
                $.alertMessage.info("请输入： " + text + "!", function () {
                    $.alertMessage.close();
                });
                return false;
            }
        }
        return true;
    },

}
$(document).ready(function() {
    //初始化调用
    SO151508.init();
});
