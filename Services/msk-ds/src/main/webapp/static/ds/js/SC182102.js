
var SC182102 = {
    SC182102Grid: null,
    formId : "SC182102Form",
    saveButtonId : "SC182102_SAVE",
    modifyButtonId : "SC182102_MODIFY",
    printButtonId : "SC182102_PRINT",
    THISROWDATA : null,
    THISROWINDEX : null,
    TRINDEX : 0,
    initDataGrid: function () {
        SC182102.bindSelectChange();
        this.bindSavebutton();
        SC182102.bindModifybutton();
        SC182102.SC182102Grid = $('#SC182102_Grid').grid({
            paging:false,
            editCellOnBlurHandler :SC182102.cellOnBlurHandler,
            fnRowCallback: SC182102.rowCallback,
            fnDrawCallback :SC182102.drawCallback
        });
        this.bindComplete();
        /** #1919 add by likai on 2016/8/16 start */
        this.BindCheckPhoneNum("[name=\'deliveryResponTel\']");
        this.BindCheckPhoneNum("[name=\'deliveryExecuteTel\']");
        this.BindCheckPhoneNum("[name=\'transportUnitResponTel\']");
        this.BindCheckPhoneNum("[name=\'transportUnitExecuteTel\']");
        this.BindCheckPhoneNum("[name=\'stockResponTel\']");
        this.BindCheckPhoneNum("[name=\'stockExecuteTel\']");

        this.bindDatePicber("#deliveryStockTimeReq");
        this.closeDate();

        /** #1919 add by likai on 2016/8/16 end */
    },
    /** #1919 add by likai on 2016/8/16 start */

    BindCheckPhoneNum:function(name){

        $(name).change(function(){

            var phoneNum = $(name).val();
           var reg =/(1[3-9]\d{9}$)/;
            if(!reg.test(phoneNum)){
                if ("[name=\'deliveryResponTel\']" == name) {
                    $.alertMessage.info("发货责任人,联系电话格式不正确!");
                }
                if ("[name=\'deliveryExecuteTel\']" == name) {
                    $.alertMessage.info("发货执行人,联系电话格式不正确!");
                }
                if ("[name=\'transportUnitResponTel\']" == name) {
                    $.alertMessage.info("运输单位负责人,联系电话格式不正确!");
                }
                if ("[name=\'transportUnitExecuteTel\']" == name) {
                    $.alertMessage.info("运输单位执行人,联系电话格式不正确!");
                }
                if ("[name=\'stockResponTel\']" == name) {
                    $.alertMessage.info("仓管负责人,联系电话格式不正确!");
                }
                if ("[name=\'stockExecuteTel\']" == name) {
                    $.alertMessage.info("验收负责人,联系电话格式不正确!");
                }
                $(name).val("");
            }
        });

},

    bindDatePicber: function(manuDate){
        $(manuDate).datetimepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            timeFormat: 'HH:mm:ss',
            changeMonth: true,
            changeYear: true,
            showSecond: true,
            timeText: '时间',
            hourText: '小时',
            minuteText: '分钟',
            secondText: '秒钟',
            currentText: '现在时间',
            closeText: '清除'
        });
        $(manuDate).change(function () {
            var str = $(manuDate).val();
            if (str.length = 19) {
                // 判断年月日的格式2010-01-01 15:12:10
                var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01]) (0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/;
                if (!reg.test(str)) {
                    $(manuDate).val("");
                }
            } else {
                $(manuDate).val("");
            }
        });
    },
    /** #1919 add by likai on 2016/8/16 end */
    // Add for 3464 at 2016/11/01 by likai Start
    closeDate:function () {
        $(document).on("click", "button.ui-datepicker-close", function () {
            $.datepicker._curInst.input.val("");

        });
    },
    // Add for 3464 at 2016/11/01 by likai End
    
    bindSelectChange:function(){
        $("#distMonth").change(function(){
            $('#main-content').postUrl(Main.contextPath + "/SC181102/selectChange/", {
                distMonth:$("#distMonth").val()
            },function(data){
                var areaSelect = $("#areaCode");
                areaSelect.html('');
                var supplierCode = $("#supplierCode");
                supplierCode.html('');
                $.each(data,function(i,item){
                    areaSelect.append("<option value='" + item.areaCode + "'>"+ item.areaName+ "</option>");
                })
            },{refreshHtml:false})
            $('#main-content').postUrl(Main.contextPath + "/SC182102/init/false", {
                distMonth:$("#distMonth").val(),
                lgcsCode:$("#areaCode").val(),
                suppCode:$("#supplierCode").val(),
                halfCode:$("#halfCode").val()
                //$("#halfCode option:selected").text()
            },{refreshHtml:false})
        });

        $("#areaCode").change(function(){
            $('#main-content').postUrl(Main.contextPath + "/SC181102/selectChange/", {
                distMonth:$("#distMonth").val(),
                areaCode:$("#areaCode").val()
            },function(data){
                var supplierCode = $("#supplierCode");
                supplierCode.html('');
                $.each(data,function(i,item){
                    supplierCode.append("<option value='" + item.supplierCode + "'>"+ item.supplierName+ "</option>");
                })
            },{refreshHtml:false})
            $('#main-content').postUrl(Main.contextPath + "/SC182102/init/false", {
                distMonth:$("#distMonth").val(),
                lgcsCode:$("#areaCode").val(),
                suppCode:$("#supplierCode").val(),
                halfCode:$("#halfCode").val()
            },{refreshHtml:false})
        });
        $("#halfCode").change(function(){
            $('#main-content').postUrl(Main.contextPath + "/SC182102/init/false", {
                distMonth:$("#distMonth").val(),
                lgcsCode:$("#areaCode").val(),
                suppCode:$("#supplierCode").val(),
                halfCode:$("#halfCode").val()
            },{refreshHtml:false})
        });
        $("#supplierCode").change(function(){
            $('#main-content').postUrl(Main.contextPath + "/SC182102/init/false", {
                distMonth:$("#distMonth").val(),
                lgcsCode:$("#areaCode").val(),
                suppCode:$("#supplierCode").val(),
                halfCode:$("#halfCode").val()
            },{refreshHtml:false})
        });
    },
    bindSavebutton : function() {
        $("#" + SC182102.saveButtonId).click(function() {
            $.alertMessage.confirm("发货入库通知单必须按照车次生成，本次所选择的发货产品是否确定属于同一个车次？", function () {
                $.alertMessage.close();
                SC182102.saveData();
            })
        });
    },
    bindModifybutton : function() {
        $("#" + SC182102.modifyButtonId).click(function() {
            SC182102.modifyData();
        });
    },
    saveData : function() {
        // var validator = mainValidation($("#" + SC182102.formId));
        // var isValid = validator.form();

        if ($("#" + SC182102.formId).validateForm()) {
            var detailData = SC182102.SC182102Grid.fnGetData();
            //实际发货数量都为0
            var flag = true;
            for(var i = 0;i < detailData.length;i++){
                if((detailData[i]["sendActualNum"]+"").indexOf(" ")>=0){
                    $.alertMessage.info("实际发货箱数不能包含空格!");
                    $("input[name='actualNums']").remove();
                    $("input[name='productCodes']").remove();
                    return;
                }
                if(!/^\d+$/.test(detailData[i]["sendActualNum"]) || detailData[i]["sendActualNum"]>detailData[i]["sendNum"] || detailData[i]["sendActualNum"].length > 14){
                    $("input[name='actualNums']").remove();
                    $("input[name='productCodes']").remove();
                    $.alertMessage.info("实际发货箱数需为不大于计划箱数的正整数,长度不能大于14!");
                    return;
                }
                if(detailData[i]["sendActualNum"] != 0){
                    flag = false;
                }
                $($("#" + SC182102.formId)).append("<input type='hidden' name='actualNums' value='"+detailData[i]["sendActualNum"]+"'/>")
                    .append("<input type='hidden' name='productCodes' value='"+detailData[i]["productCode"]+"'/>");

            }


            if(flag){
                $("input[name='actualNums']").remove();
                $("input[name='productCodes']").remove();
                $.alertMessage.info("请输入实际发货箱数!");
                return;
            }

            $("#supplierName").val($("#supplierCode").find("option:selected").text());

            formData = getFormData($("#" + SC182102.formId));


            $('#main-content').postUrl(Main.contextPath+ "/SC182102/save",formData,function() {
            });

        };
    },
    modifyData : function() {
        var validator = mainValidation($("#" + SC182102.formId));
        var isValid = validator.form();

        if (isValid) {
            var detailData = SC182102.SC182102Grid.fnGetData();
            for(var i = 0;i < detailData.length;i++){

                if((detailData[i]["receiveNum"]+"").indexOf(" ")>=0){
                    $.alertMessage.info("实际收货箱数不能包含空格!");
                    $("input[name='actualNums']").remove();
                    $("input[name='productCodes']").remove();
                    return;
                }
                if (!/^-?\d+$/.test(detailData[i]["receiveNum"]) || detailData[i]["receiveNum"].length > 14 ){
                    $.alertMessage.info("实际收货箱数必须为长度不大于14的整数!");
                    return;
              }
                $($("#" + SC182102.formId))
                    .append("<input type='hidden' name='receiveActualNums' value='"+detailData[i]["receiveNum"]+"'/>");
            }

            formData = getFormData($("#" + SC182102.formId));

            $('#main-content').postUrl(Main.contextPath+ "/SC182102/modify",formData,function() {
            });
        };
    },
    cellOnBlurHandler : function($comp){
        //获得TD
        var $td = $comp.parent();
        //获得TR
        var $tr = $td.parent();
        //获得所有行
        var $trs = $tr.parent();
        //获得当前行
        SC182102.THISROWINDEX = $trs.children().index($tr);
        SC182102.THISROWDATA = SC182102.SC182102Grid.fnGetData(SC182102.THISROWINDEX);
        //获得当前列数据
        var num=$comp.val();
        if (isNaN(num)){
            num = 0;
        }
        var name=$comp.attr('name');
        //获取净重
        var netWeight = SC182102.THISROWDATA["netWeight"];
        if(name == 'receiveNum'){
            //获取元发货差异箱数
            var oldDiffNum = SC182102.THISROWDATA["differNum"];

            //获取计划发货箱数
            var sendNum =SC182102.THISROWDATA["sendNum"];

            //为差异箱数赋值
            SC182102.THISROWDATA["differNum"] = num - sendNum;

            //为差异重量赋值
            SC182102.THISROWDATA["differQty"] = (num - sendNum)* netWeight;
            //赋值
            SC182102.THISROWDATA["recriveQty"] = netWeight * num;
            SC182102.THISROWDATA["receiveNum"] = num;
            //重新计算合计数量
            var everyData = null;
            var differNum = 0;
            var differQty = 0;
            var receiveNum = 0;
            var receiveQty = 0;
            for(var i=0;i<$trs.children().size();i++){
                everyData = SC182102.SC182102Grid.fnGetData(i);
                differNum = differNum+everyData["differNum"];
                differQty = differQty+everyData["differQty"];
                receiveNum = receiveNum+parseInt(everyData["receiveNum"]);
                receiveQty = receiveQty+everyData["recriveQty"];
            }

            //为合计数量进行赋值操作
            $('#currentdifferNum').text(SC182102.formatNumber(differNum,0,1));
            $('#currentdifferQty').text(SC182102.formatNumber(differQty,2,1));
            $('#currentreceiveNum').text(SC182102.formatNumber(receiveNum,0,1));
            $('#currentrecriveQty').text(SC182102.formatNumber(receiveQty,2,1));
            $("#actualNums_"+ SC182102.THISROWINDEX).val(num);
        }
        else{
            SC182102.THISROWDATA["sendQty"] = num*netWeight;
            SC182102.THISROWDATA["sendActualNum"] = num;
            var sendActualQty = 0;
            var sendActualNum = 0;
            for(var i=0;i<$trs.children().size();i++){
                everyData = SC182102.SC182102Grid.fnGetData(i);
                sendActualQty = sendActualQty+everyData["sendQty"];
                sendActualNum = sendActualNum+parseInt(everyData["sendActualNum"]);
            }
            $('#currentsendActualNum').text(SC182102.formatNumber(sendActualNum,0,1));
            $('#currentsendActualQty').text(SC182102.formatNumber(sendActualQty,2,1));
            $("#actualNums_"+ SC182102.THISROWINDEX).val(num);

        }






    },
    bindComplete:function(){
        $("#"+SC182102.printButtonId).click(function(){
            var printUrl = $('#printUrl').val();
            var callBackUrl = $('#callBackUrl').val();
            var dform = $("<form>");   //定义一个form表单
            var printCallBackUrlName = "printCallBackUrl";
            dform.append('<input type="text" name="'+printCallBackUrlName+'" value='+callBackUrl+'>');
            var deliveryStockId =  $('#deliveryStockId').val()
            var isEdit  = $('#isEdit').val();
            var sourceFlg  = $('#sourceFlg').val();
            var callbackParamJson = "{\"deliveryStockId\":\"" + deliveryStockId + "\"," + "\"isEdit\":\"" + isEdit+"\"," + "\"sourceFlg\":\"" + sourceFlg +"\"}";
            var callBackParamName = "callBackParam";
            dform.append('<input type="text" name="'+callBackParamName+'" value='+callbackParamJson+'>');
            dform.attr('style', 'display:none');   //在form表单中添加查询参数
            dform.attr('target', 'newWin');
            dform.attr('method', 'post');
            dform.attr('action', printUrl);
            $('body').append(dform);  //将表单放置在web中
            window.open("about:blank","newWin","");//newWin 是上面form的target
            dform.submit();
        });
    },

    /**  #1765添加千分位合计 modify by renyi on 2016/8/30 start */
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

    drawCallback : function(){
        var dataList = SC182102.SC182102Grid.fnGetData();
        if(dataList.length == 0){
            $("#currentsendPlanNum").html("");
            $("#currentsendActualNum").html("");
            $("#currentreceiveNum").html("");
            $("#currentsendPlanQty").html("");
            $("#currentsendActualQty").html("");
            $("#currentrecriveQty").html("");
            $("#currentdifferNum").html("");
            $("#currentdifferQty").html("");

        }
    },

    rowCallback: function(tr, data) {
        if(tr._DT_RowIndex == 0){
            $("#currentsendPlanNum").html(SC182102.formatNumber(data.currentSendPlanNum,0,1));
            $("#currentsendActualNum").html(SC182102.formatNumber(data.currentSendActualNum,0,1));
            $("#currentreceiveNum").html(SC182102.formatNumber(data.currentReceiveNum,0,1));
            $("#currentsendPlanQty").html(SC182102.formatNumber(data.currentSendPlanQty,2,1));
            $("#currentsendActualQty").html(SC182102.formatNumber(data.currentSendActualQty,2,1));
            $("#currentrecriveQty").html(SC182102.formatNumber(data.currentRecriveQty,2,1));
            $("#currentdifferNum").html(SC182102.formatNumber(data.currentDifferNum,0,1));
            $("#currentdifferQty").html(SC182102.formatNumber(data.currentDifferQty,2,1));
        }
    }
    /** #1765添加千分位合计 modify by renyi on 2016/8/30  end */
    //Add for #2746 at 2016/09/20 by likai Start

    //Add for #2746 at 2016/09/20 by likai End
}
$(document).ready(function () {
    // 初始化调用
    SC182102.initDataGrid();


});
