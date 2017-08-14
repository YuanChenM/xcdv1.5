var $List_Grid;
var SP171170 = {
    formId: "SP171170Form",
    searchButtonId: 'SP171170_SEARCH',
    updateButtonId: 'SP171170_UPDATE',
    downLoadModelId: "SP171170_DOWNLOADMODEL",
    saveButtonId: 'SP171170_SAVE',
    init: function () {
        this.bindDatePicber('#priceDate');
        this.changeSelect();
        this.bindSearch();
        this.bindSave();
        this.bindUpdate();
        this.exportData();
        this.upload();
        this.downLoadModel();
        this.keyEnter();
        $List_Grid = $('#SP171170_list_grid').grid({
            editCellOnBlurHandler :SP171170.cellOnBlurHandler
        });
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
        this.closeDate();
        /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    },
    cellOnBlurHandler : function($comp){
        var reg = new RegExp("^\\d+(\\.\\d+)?$");
        //获得TD
        var $td = $comp.parent();
        //获得TR
        var $tr = $td.parent();
        //获得所有行
        var $trs = $tr.parent();
        //获得当前行
        SP171170.THISROWINDEX = $trs.children().index($tr);
        //alert(SC182102.THISROWINDEX);
        SP171170.THISROWDATA = $List_Grid.fnGetData(SP171170.THISROWINDEX);
        //获得当前列数据
        var num=$comp.val();
        if(num !='-' && !reg.test(num)){
            $.alertMessage.info("选中的公斤价不能输入非数字!");
            return;
        }
        // 获取当前行的name
        var name = $td.context.name;
        //获取净重
        var netWeight = SP171170.THISROWDATA["weightName"];
        //赋值
        var weight=netWeight.substring(0, netWeight.length-2)
        if(name=="supPriceofkg"){
            if(num=="-"){
                SP171170.THISROWDATA["supPriceofbox"] = "-";
            }else{
                SP171170.THISROWDATA["supPriceofbox"] = Math.round(num * weight*100)/100;
            }
        }
        if(name=="onePriceofkg"){
            if(num=="-"){
                SP171170.THISROWDATA["onepriceofbox"] = "-";
            }else{
                SP171170.THISROWDATA["onepriceofbox"] = Math.round(num * weight*100)/100;
            }
        }
        if(name=="twoPriceofkg"){
            if(num=="-"){
                SP171170.THISROWDATA["twoPriceofbox"] ="-";
            }else{
                SP171170.THISROWDATA["twoPriceofbox"] =Math.round(num * weight*100)/100;
            }
        }
        if(name=="threePriceofkg"){
            if(num=="-"){
                SP171170.THISROWDATA["threePriceofkg"] ="-";
            }else{
                SP171170.THISROWDATA["threepriceofbox"] = Math.round(num * weight*100)/100;
            }
        }
        if(name=="fourPriceofkg"){
            if(num=="-"){
                SP171170.THISROWDATA["fourPriceofbox"] ="-";
            }else{
                SP171170.THISROWDATA["fourPriceofbox"] = Math.round(num * weight*100)/100;
            }
        }
        if(name=="fivePriceofkg"){
            if(num=="-"){
                SP171170.THISROWDATA["fivepriceofbox"] ="-";
            }else{
                SP171170.THISROWDATA["fivepriceofbox"] =Math.round(num * weight*100)/100;
            }
        }
        if(name=="sixPriceofkg"){
            if(num=="-"){
                SP171170.THISROWDATA["sixPriceofbox"] ="-";
            }else{
                SP171170.THISROWDATA["sixPriceofbox"] = Math.round(num * weight*100)/100;
            }
        }
        if(name=="sevenPriceofkg"){
            if(num=="-"){
                SP171170.THISROWDATA["sevenpriceofbox"] ="-";
            }else{
                SP171170.THISROWDATA["sevenpriceofbox"] =Math.round(num * weight*100)/100;
            }
        }
        if(name=="eightPriceofkg"){
            if(num=="-"){
                SP171170.THISROWDATA["eightPriceofbox"] ="-";
            }else{
                SP171170.THISROWDATA["eightPriceofbox"] = Math.round(num * weight*100)/100;
            }
        }
        if(name=="ninePriceofkg"){
            if(num=="-"){
                SP171170.THISROWDATA["ninepriceofbox"] ="-"
            }else{
                SP171170.THISROWDATA["ninepriceofbox"] = Math.round(num * weight*100)/100;
            }
        }
        $("#actualNums_"+ SP171170.THISROWINDEX).val(num);
    },
    changeSelect: function () {
        var pdClasses = $('#pdClasses');
        var pdMachining = $('#pdMachining');
        pdClasses.change(function () {
            pdMachining.html('');
            pdMachining.append("<option value=''>请选择</option>");

            var pdClassesVal = pdClasses.val();
            var pdMachiningVal = $('#pdMachining').val();
            var classesCode =$("#pdClasses option:selected").val();
            SP171170.getpdMachining(pdClasses,pdClassesVal,pdMachining,pdMachiningVal);
        });

        //区域名称
        var logiareaCode = $('#logiareaCode');
        logiareaCode.change(function () {
            $('#logiareaName').val(logiareaCode.find("option:selected").text());
        });
    },
    getpdMachining:function(pdClasses,pdClassesVal,pdMachining,pdMachiningVal){
        $('#main-content').postUrl(Main.contextPath + "/SP171170/findMaching", {'classesCode': pdClassesVal}, function (data) {
            $('#classesName').val(pdClasses.find("option:selected").text());
            $.each(data, function (i, item) {
                if (item.machiningCode === pdMachiningVal) {
                    pdMachining.append("<option selected='selected' value='" + item.machiningCode + "'>" + item.machiningName + "</option>");
                } else {
                    pdMachining.append("<option value='" + item.machiningCode + "'>" + item.machiningName + "</option>");
                }
            });
        }, {refreshHtml: false});
    },
    bindDatePicber: function (priceDate) {
        $(priceDate).datepicker({
            prevText: '上月',         // 前选按钮提示
            nextText: '下月',         // 后选按钮提示
            changeYear: true,          // 年下拉菜单
            changeMonth: true,             // 月下拉菜单
            showButtonPanel: true,         // 显示按钮面板
            showMonthAfterYear: true,  // 月份显示在年后面
            currentText: "本月",         // 当前日期按钮提示文字
            closeText: 'Clear',           // 关闭按钮提示文字
            dateFormat: "y-mm",       // 日期格式
            //minDate: new Date(),			//最小日期
            onClose: function (dateText, inst) {// 关闭事件
                var month = $("#ui-datepicker-div .ui-datepicker-month").val();
                var year = $("#ui-datepicker-div .ui-datepicker-year").val();
                $(this).datepicker('setDate', new Date(year, month, 1));
            }
        });
    },
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  Start */
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    /**Add: 横展开添加日期清除按钮 2016/09/09   BY  任强  End */
    bindSearch:function(){
        $("#" + SP171170.searchButtonId).click(function () {
            var priceDate = $("#priceDate").val();
            var price = $("#pricecycle").val();
            var pricePeriod = priceDate.substr(0,2) + priceDate.substr(3,4) + price;
            $("#pricecyclePeriod").val(pricePeriod);
            var formData = getFormData($("#" + SP171170.formId));
            FormUtils.setFormValue(SP171170.formId, "SP171170");
            $List_Grid.fnDraw();
        });
    },
    bindSave:function(){
        var reg = new RegExp("^(-?\\d+)(\\.\\d+)?$");
        $("#" + SP171170.saveButtonId).click(function () {
                var changeData = $List_Grid.getChangeData();// 获取改动的数据对象  是数组
                if(changeData.length == 0){
                    $.alertMessage.confirm("请先编辑要保存的数据再保存！", function() {
                        $.alertMessage.close();
                    })
                } else{
                    $.alertMessage.confirm("你确定要保存当前数据吗？", function() {
                        $.alertMessage.close();
                        var json = {};// 创建json 对象
                        var priceDate = $("#priceDate").val();
                        var price = $("#pricecycle").val();
                        var pricePeriod = priceDate.substr(0,2) + priceDate.substr(3,4) + price;
                        for(i=0;i<changeData.length;i++){//  把数组的对象封装到json
                            //json[i] =changeData[i];
                            var kgVal = changeData[i]["weightName"].substring(0, changeData[i]["weightName"].length-2);
                            var supVal=changeData[i]["supPriceofkg"];
                            if(supVal=="-"){
                                supVal = "-1";
                            }
                            changeData[i]["supPriceofbox"] = supVal *kgVal;
                            json[i+"0"] = {"pricecyclePeriod":pricePeriod,"logiareaCode":changeData[i]["logiareaCode"],"wayCode":changeData[i]["wayCode"],"pdCode":changeData[i]["pdCode"],"wayGradeCode":changeData[i]["supGradeCode"],"priceofkg":supVal,"priceofbox":changeData[i]["supPriceofbox"]};
                            var oneVal=changeData[i]["onePriceofkg"];
                            if(oneVal=="-"){
                                oneVal="-1";
                            }
                            changeData[i]["onePriceofbox"] = oneVal *kgVal;

                            json[i+"1"] = {"pricecyclePeriod":pricePeriod,"logiareaCode":changeData[i]["logiareaCode"],"wayCode":changeData[i]["wayCode"],"pdCode":changeData[i]["pdCode"],"wayGradeCode":changeData[i]["oneGradeCode"],"priceofkg":oneVal,"priceofbox":changeData[i]["onePriceofbox"]};
                            var twoVal=changeData[i]["twoPriceofkg"];
                            if(twoVal=="-"){
                                twoVal="-1";
                            }
                            changeData[i]["twoPriceofbox"] = twoVal *kgVal;
                            json[i+"2"] = {"pricecyclePeriod":pricePeriod,"logiareaCode":changeData[i]["logiareaCode"],"wayCode":changeData[i]["wayCode"],"pdCode":changeData[i]["pdCode"],"wayGradeCode":changeData[i]["twoGradeCode"],"priceofkg":twoVal,"priceofbox":changeData[i]["twoPriceofbox"]};
                            var threeVal=changeData[i]["threePriceofkg"];
                            if(threeVal=="-"){
                                threeVal="-1";
                            }
                                changeData[i]["threePriceofbox"] =threeVal *kgVal;
                            json[i+"3"] = {"pricecyclePeriod":pricePeriod,"logiareaCode":changeData[i]["logiareaCode"],"wayCode":changeData[i]["wayCode"],"pdCode":changeData[i]["pdCode"],"wayGradeCode":changeData[i]["threeGradeCode"],"priceofkg":threeVal,"priceofbox": changeData[i]["threePriceofbox"]};
                            var fourVal=changeData[i]["fourPriceofkg"];
                            if(fourVal=="-"){
                                fourVal="-1";
                            }
                                changeData[i]["fourPriceofbox"] =fourVal *kgVal;
                            json[i+"4"] = {"pricecyclePeriod":pricePeriod,"logiareaCode":changeData[i]["logiareaCode"],"wayCode":changeData[i]["wayCode"],"pdCode":changeData[i]["pdCode"],"wayGradeCode":changeData[i]["fourGradeCode"],"priceofkg":fourVal,"priceofbox": changeData[i]["fourPriceofbox"]};
                            var fiveVal=changeData[i]["fivePriceofkg"];
                            if(fiveVal=="-"){
                                fiveVal="-1";
                            }
                            changeData[i]["fivePriceofbox"] = fiveVal *kgVal;
                            json[i+"5"] = {"pricecyclePeriod":pricePeriod,"logiareaCode":changeData[i]["logiareaCode"],"wayCode":changeData[i]["wayCode"],"pdCode":changeData[i]["pdCode"],"wayGradeCode":changeData[i]["fiveGradeCode"],"priceofkg":fiveVal,"priceofbox": changeData[i]["fivePriceofbox"]};
                            var sixVal=changeData[i]["sixPriceofkg"];
                            if(sixVal=="-"){
                                sixVal="-1";
                            }
                                changeData[i]["sixPriceofbox"] = sixVal *kgVal;
                            json[i+"6"] = {"pricecyclePeriod":pricePeriod,"logiareaCode":changeData[i]["logiareaCode"],"wayCode":changeData[i]["wayCode"],"pdCode":changeData[i]["pdCode"],"wayGradeCode":changeData[i]["sixGradeCode"],"priceofkg":sixVal,"priceofbox": changeData[i]["sixPriceofbox"]};
                            var sevenVal=changeData[i]["sevenPriceofkg"];
                            if(sevenVal=="-"){
                                sevenVal="-1";
                            }
                                changeData[i]["sevenPriceofbox"] =sevenVal*kgVal;
                            json[i+"7"] = {"pricecyclePeriod":pricePeriod,"logiareaCode":changeData[i]["logiareaCode"],"wayCode":changeData[i]["wayCode"],"pdCode":changeData[i]["pdCode"],"wayGradeCode":changeData[i]["sevenGradeCode"],"priceofkg":sevenVal,"priceofbox": changeData[i]["sevenPriceofbox"]};
                            var eightVal=changeData[i]["eightPriceofkg"];
                            if(eightVal=="-"){
                                eightVal="-1";
                            }
                                changeData[i]["eightPriceofbox"] = eightVal *kgVal;
                            json[i+"8"] = {"pricecyclePeriod":pricePeriod,"logiareaCode":changeData[i]["logiareaCode"],"wayCode":changeData[i]["wayCode"],"pdCode":changeData[i]["pdCode"],"wayGradeCode":changeData[i]["eightGradeCode"],"priceofkg":eightVal,"priceofbox": changeData[i]["eightPriceofbox"]};
                            var nineVal=changeData[i]["ninePriceofkg"];
                            if(nineVal=="-"){
                                nineVal="-1";
                            }
                                changeData[i]["ninePriceofbox"] = nineVal *kgVal;
                            json[i+"9"] = {"pricecyclePeriod":pricePeriod,"logiareaCode":changeData[i]["logiareaCode"],"wayCode":changeData[i]["wayCode"],"pdCode":changeData[i]["pdCode"],"wayGradeCode":changeData[i]["nineGradeCode"],"priceofkg":nineVal,"priceofbox": changeData[i]["ninePriceofbox"]};
                                if((supVal !='-' && !reg.test(supVal)) || (oneVal !='-' && !reg.test(oneVal)) || (twoVal !='-' && !reg.test(twoVal)) || (threeVal !='-' && !reg.test(threeVal))
                                    || (fourVal !='-' && !reg.test(fourVal)) ||(fiveVal !='-' && !reg.test(fiveVal)) || (sixVal !='-' && !reg.test(sixVal)) || (sevenVal !='-' &&!reg.test(sevenVal))
                                    ||(eightVal !='-' &&!reg.test(eightVal))   ||(nineVal !='-' && !reg.test(nineVal)) ){
                                    $.alertMessage.info("选中的第"+(i+1)+"行公斤价请输入数字!");
                                    return;
                                }
                        }
                        var jsonStr = JSON.stringify(json);//  转成jsonStr
                        var priceDate =$("#priceDate").val();
                        var pricecycle = $("#pricecycle").val();
                        var lgscAreaCode =$("#logiareaCode option:selected").val();
                        var classesCode =$("#pdClasses option:selected").val();
                        var machiningCode =$("#pdMachining option:selected").val();
                        var breedName =$("#breedName").val();
                        $('#main-content').postUrl(Main.contextPath +"/SP171170/modify", {"jsonStr":jsonStr,"priceDate":priceDate,"pricecycle":pricecycle
                        , 'lgcsAreaCode':lgscAreaCode,'classesCode':classesCode,'machiningCode':machiningCode,'breedName':breedName},function(data) {
                            $.alertMessage.info("修改已提交");
                        });
                    })
                }

        });
    },
    bindUpdate:function(){
        $("#" + SP171170.updateButtonId).click(function () {
            // 用来判断tableGrid是否能够编辑
            var priceDate =$("#priceDate").val();
            var pricecycle = $("#pricecycle").val();
            var lgscAreaCode =$("#logiareaCode option:selected").val();
            var classesCode =$("#pdClasses option:selected").val();
            var machiningCode =$("#pdMachining option:selected").val();
            var breedName =$("#breedName").val();
            $('#main-content').postUrl(Main.contextPath + "/SP171170/init", {'flag': 0,'priceDate':priceDate,'pricecycle':pricecycle,
                    'lgcsAreaCode':lgscAreaCode,'classesCode':classesCode,'machiningCode':machiningCode,'breedName':breedName},
                function (data) {

            });
        });
    },
    exportData:function(){
        $("#SP171170_EXPORT").click(function () {
            var val = $("#pricecycle option:selected").val();
            var cle =$("#pricecycle option:selected").text();
            var lgcsAreaCode = $("#logiareaCode").val();
            var classesCode = $("#pdClasses").val();
            var  machiningCode =$("#pdMachining").val();
            var priceDate = $("#priceDate").val();
            var price = $("#pricecycle").val();
            var breedName = $("#breedName").val();
            var pricePeriod = priceDate.substr(0,2) + priceDate.substr(3,4) + price;
            var title;
            if(null !=val && val !=''){
                title = priceDate+"("+cle+")";
            }else{
                title = priceDate;
            }

            var param = new Object();
            param['lgcsAreaCode'] = lgcsAreaCode;
            param['classesCode'] = classesCode;
            param['machiningCode'] = machiningCode;
            param['pricecyclePeriod'] = pricePeriod;
            param['title'] = title;
            param['breedName'] = breedName;
            //downloadAsync("priceSheetTemp","SP171172Logic","snk-price",title+".xlsx",param);
           downloadAsync("priceSheetTemp1","SPExcelSheetLogic","snk-price",title+".xlsx",param);
        });

    },
    downLoadModel: function(){
        $("#"+SP171170.downLoadModelId).click(function(){
            var val = $("#pricecycle option:selected").val();
            var cle =$("#pricecycle option:selected").text();
            var priceDate = $("#priceDate").val();
            var title;
            if(null !=val && val !=''){
                title = priceDate+"("+cle+")";
            }else{
                title = priceDate;
            }
            var param = new Object();
            param['title'] = title;
            param['isModel'] = "1";
            downloadAsync("priceSheetTemp1","SP171171Logic","snk-price",title+".xlsx",param);
        });
    },
    upload :function(){
        $("#SP171170_UPLOAD").click(function() {
            var file = $("#importFile").val();
            if(file ==''){
                alert("请选择文件");
            }else{
                var fileExt=file.substring(file.lastIndexOf("."),file.length)
                fileExt=fileExt.toLowerCase()
                if (fileExt =='.xlsx' ){
                    var loginId=$("#userId").val();
                    MainUtils.uploadExcelFile(Main.contextPath + "/comm/excel/SP171170/upload","file",{"userId":loginId});
                }else{
                    alert("请上传excel文件（后缀为.xlsx）");
                }
            }
        });
    },
    keyEnter:function() {
        document.onkeydown = function (e) {
            //捕捉回车事件
            var ev = (typeof event != 'undefined') ? window.event : e;
            if (ev.keyCode == 13) {
                var priceDate = $("#priceDate").val();
                var price = $("#pricecycle").val();
                var pricePeriod = priceDate.substr(0,2) + priceDate.substr(3,4) + price;
                var formData = getFormData($("#" + SP171170.formId));
                FormUtils.setFormValue(SP171170.formId, "SP171170");
                $List_Grid.fnDraw();
            }
        }
    }
};
$(document).ready(function () {
    // 初始化调用
    SP171170.init();
});
