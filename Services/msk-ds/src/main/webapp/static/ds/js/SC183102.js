/**
 * 计划历史一览JS
 * Created by fjm on 16/2/18.
 * @type {{SC183102Grid: null, initDataGrid: Function, actionHandler: Function}}
 */
var SC183102 = {
    SC183102Grid: null,
    startTime:"[name=filterMap\\[\\'adjustDateStart\\'\\]Start]",
    endTime:"[name=filterMap\\[\\'adjustDateEnd\\'\\]]",
    initDataGrid: function () {
        SC183102.SC183102Grid = $('#SC183102_Grid').grid({
            actionHandler:SC183102.actionHandler,
            fnRowCallback:SC183102.rowCallback,
        });
        this.bindDatePicber(SC183102.startTime);
        this.bindDatePicber(SC183102.endTime);
        this.closeDate();
    },

    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    actionHandler:function(rowdata,coltype,row,col){
        if(coltype=="edit"){
            $.pdialog.open("供应商产品计划调整申请", Main.contextPath + "/SC183101/init", {
                    width: 800,
                    height: 550
                },
                {
                    entryMark : "2",
                    distMonth : rowdata.distMonth,
                    areaCode : rowdata.lgcsCode,
                    areaName : rowdata.lgcsName,
                    supplierCode : rowdata.suppCode,
                    supplierName : rowdata.suppName,
                    productName : rowdata.classesCode,
                    currentHalfCode : rowdata.halfCodeZ,
                    planType : rowdata.pdStockTypeZ,
                    adjustDate : rowdata.adjustDate,
                    suppDsId : rowdata.suppDsId,
                    virtualStockPlanId : rowdata.planFlowId
                })
        };

        if(coltype=="delete"){
            $('#main-content').postUrl(Main.contextPath + "/SC183102/delete/", {
                suppDsId : rowdata.suppDsId,
                planFlowId : rowdata.planFlowId,
            })};
    },

    bindDatePicber: function (orderTimeId) {
        $(orderTimeId).change(function () {
            var str = $(orderTimeId).val();
            if (str.length >= 8) {
                // 判断年月日的格式2010-01-01
                var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
                if (reg.test(str)) {
                    if (orderTimeId.endsWith("End\\'\\]]")) {
                        var startTime = $(SC183102.startTime).val();
                        var endTime = $(orderTimeId).val();
                        if (startTime != null && startTime != 'underfined' && startTime != '' && startTime > endTime) {
                            $(orderTimeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                    if (orderTimeId.endsWith("Start]")) {
                        var startTime = $(orderTimeId).val();
                        var endTime = $(SC183102.endTime).val();
                        if (endTime != null && endTime != 'underfined' && endTime != '' && startTime > endTime) {
                            $(orderTimeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                } else {
                    $(orderTimeId).val("");
                }
            } else {
                $(orderTimeId).val("");
            }
        });
    },
    rowCallback: function (tr, data) {
        var $td = $(tr).children('td').eq(18);
        var theB=$td[0].children[1];
        var theA=$td[0].children[0];
        //debugger;
        //var d = new Date();
        //var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
        //var dstr = new Date(str.replace(/-/g, "/"));
        //var a = data.halfCodeA;
        //var astr = new Date(a.replace(/-/g, "/"));
        //
        //var i = data.adjustDate.toString();
        //var istr = new Date(i.replace(/-/g, "/"));
        //if(istr-astr<0 || dstr-istr<0){
        //    theA.style.visibility="hidden";
        //    theB.style.visibility="hidden";
        //}
        var delFlg = data.delFlg;
        if(delFlg == 0){
            theA.style.visibility="hidden";
        }else if(delFlg == 1){
            theB.style.visibility="hidden";
        }else{
            theA.style.visibility="hidden";
            theB.style.visibility="hidden";
        }

    }

}
$(document).ready(function () {
    // 初始化调用
    SC183102.initDataGrid();
    $("[name='halfCode']").width(100);
    $("[name='pdStockType']").width(120);
    $("[name=filterMap\\[\\'adjustDateStart\\'\\]Start]").attr("readonly","true");
    $("[name=filterMap\\[\\'adjustDateEnd\\'\\]]").attr("readonly","true");
});
