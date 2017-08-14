/**
 * Created by wang_jianzhou on 16/8/2.
 */
var SO15150801 = {
    LISTGRID : null,
    THISROWDATA : null,
    THISROWINDEX : null,
    init: function () {
        var salePlatform = $('#salePlatform').val();
        if(salePlatform == 1){
            $("#SO15150801Form").attr("action", Main.contextPath+"/SO15150801/search");
        }else{
            $("#SO15150801Form").attr("action", Main.contextPath+"/SO15150801/searchSp");
        }
        SO15150801.LISTGRID = $('#SO151508Grid').grid({
            fnRowCallback : SO15150801.rowCallback,
            editCellOnBlurHandler :SO15150801.cellOnBlurHandler
        });
        SO15150801.bindConfirmButton();
    },
    cellOnBlurHandler : function($comp){
        //获得TD
        var $td = $comp.parent();
        //获得TR
        var $tr = $td.parent();
        //获得所有行
        var $trs = $tr.parent();
        //获得当前行
        SO15150801.THISROWINDEX = $trs.children().index($tr);
        //获得当前行数据
        var num = $comp.val();
        var name = $td.context.name;
        SO15150801.THISROWDATA = SO15150801.LISTGRID.fnGetData(SO15150801.THISROWINDEX);
        if(name == "activeQty"){
            SO15150801.THISROWDATA.activeQty = num;
            SO15150801.THISROWDATA.lgcsCode = $("#lgcsCode").val();
            if(num != null && num != ''){
                var reg = /^[1-9]*[1-9][0-9]*$/;
                if(!reg.test(num)) {
                    $.alertMessage.info("订单数量请输入正整数!",function () {
                        SO15150801.THISROWDATA.activeQty = "";
                        SO15150801.LISTGRID.fnUpdate(SO15150801.THISROWDATA,SO15150801.THISROWINDEX,undefined,false,false);
                        $.alertMessage.close();
                    });
                    return;
                }
                $('#SO15150801Form').postUrl(Main.contextPath+"/SO15150801/searchPdPrice", SO15150801.THISROWDATA,
                    function(data) {
                        if(data.price == null || data.price == "" || data.priceCyclePeriod == null || data.priceCyclePeriod == "" || data.orderLevelName == null || data.orderLevelName == ""){
                            $.alertMessage.info("当前产品没有价盘信息!");
                            return;
                        }
                        SO15150801.THISROWDATA.price = data.price;
                        SO15150801.THISROWDATA.priceCyclePeriod = data.priceCyclePeriod;
                        SO15150801.THISROWDATA.orderLevelName = data.orderLevelName;
                        SO15150801.LISTGRID.fnUpdate(SO15150801.THISROWDATA,SO15150801.THISROWINDEX,undefined,false,false);
                    }, {refreshHtml: false}
                );
            }
        }
        if(name == "proDate"){
            if(num != null && num != ''){
                var result = num.match(/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/)
                if(result == null){
                    $.alertMessage.info("请输入正确日期!",function () {
                        SO15150801.THISROWDATA.proDate = "";
                        SO15150801.LISTGRID.fnUpdate(SO15150801.THISROWDATA,SO15150801.THISROWINDEX,undefined,false,false);
                        $.alertMessage.close();
                    });
                    return;
                }
            }
        }
    },
    rowCallback : function(tr,data){
    },

    bindConfirmButton : function(){
        $("#SO15150801_CONFIRM").click(function(){
            var reg = /^\d+$/;
            var selectProductList = SO15150801.LISTGRID.getChoiceData();
            if(selectProductList.length == 0){
                $.alertMessage.info("请选择产品!");
                return;
            }
            var sumPrice = 0.00;
            for(var j=0;j<selectProductList.length;j++){
                if(selectProductList[j].activeQty == null || selectProductList[j].activeQty == ""){
                    $.alertMessage.info("请输入下单数量!");
                    return;
                }
                if(!reg.test(selectProductList[j].activeQty) || selectProductList[j].activeQty.length>10){
                    $.alertMessage.info("请输入正确的下单数量!");
                    return;
                }
                if(selectProductList[j].price == null || selectProductList[j].price ==""){
                    $.alertMessage.info("未找到该产品单价，无法下单!");
                    return;
                }
                if(selectProductList[j].priceCyclePeriod == null || selectProductList[j].priceCyclePeriod ==""){
                    $.alertMessage.info("未找到该产品价盘周期，无法下单!");
                    return;
                }
                if(selectProductList[j].orderLevelName == null || selectProductList[j].orderLevelName ==""){
                    $.alertMessage.info("未找到该产品订单等级，无法下单!");
                    return;
                }
                if(selectProductList[j].pdName == null || selectProductList[j].pdName ==""){
                    $.alertMessage.info("未找到该产品名称，无法下单!");
                    return;
                }
                if(selectProductList[j].pdCode == null || selectProductList[j].pdCode ==""){
                    $.alertMessage.info("未找到该产品名编码，无法下单!");
                    return;
                }
                    if(selectProductList[j].proDate != null && selectProductList[j].proDate !=""){
                        var result = selectProductList[j].proDate.match(/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/)
                        if(result == null){
                            $.alertMessage.info("请输入正确日期!");
                            return;
                        }
                    }
            }
           
            $(".hiddenData").remove();
            $.each(selectProductList,function(index,context){
                var pdName = context["pdName"];
                var pdCode = context["pdCode"];
                var num = context["activeQty"];
                var proDate = context["proDate"];
                if (proDate == null){
                    proDate = "";
                }
                // 价盘周期
                var priceCycle = context["priceCyclePeriod"];
                var price = context["price"];
                /*var orderDetailLevel = context["orderLevelCode"];*/
                var orderLevelName = context["orderLevelName"];
                var supplierCode = context["supplierCode"];
                var supplierName = context["supplierName"];
                if (supplierCode == null){
                    supplierCode = "";
                }
                if (supplierName == null){
                    supplierName = "";
                }
                var trHtml = "<tr class='hiddenData'>";
                var formInput = "<input class='hidden' type='hidden' name='pdName' value="+pdName+">";
                formInput += "<input class='hidden' type='hidden' name='pdCode' value="+pdCode+">";
                formInput += "<input class='hidden' type='hidden' name='proDate' value="+proDate+">";
                formInput += "<input class='hidden' type='hidden' name='priceCycle' value="+priceCycle+">";
                formInput += "<input class='hidden' type='hidden' name='activeQty' value="+num+">";
                formInput += "<input class='hidden' type='hidden' name='price' value="+price+">";
                /*formInput += "<input class='hidden' type='hidden' name='orderDetailLevel' value="+orderDetailLevel+">";*/
                formInput += "<input class='hidden' type='hidden' name='supplierCode' value="+supplierCode+">";
                formInput += "<input class='hidden' type='hidden' name='supplierName' value="+supplierName+">";
                sumPrice += (parseFloat(price)*parseFloat(num));
                trHtml += "<td>"+pdCode+"</td>";
                trHtml += "<td>"+pdName+"</td>";
                trHtml += "<td align='right'>"+parseFloat(price).toFixed(2)+"</td>";
                trHtml += "<td align='right'>"+num+"</td>";
                trHtml += "<td>"+proDate+"</td>";
                trHtml += "<td hidden>"+formInput+"</td>";
                //trHtml += "<td>"+formInput+"<img src='"+Main.contextPath+"/static/core/images/action/delete.png'/></td>";
                trHtml += "</tr>";

                $("#productData").append(trHtml);
                $("#accordionProductData").accordion({ heightStyle: "content" });
                $.pdialog.close();
            });
            $("#orderAmount").val(parseFloat(sumPrice).toFixed(2));
        });
    }
}
/**
 *删除数组指定下标或指定对象
 */
Array.prototype.remove=function(obj){
    for(var i =0;i <this.length;i++){
        var temp = this[i];
        if(!isNaN(obj)){
            temp=i;
        }
        if(temp == obj){
            for(var j = i;j <this.length;j++){
                this[j]=this[j+1];
            }
            this.length = this.length-1;
        }
    }
}
$(document).ready(function() {
    //初始化调用
    SO15150801.init();
});
