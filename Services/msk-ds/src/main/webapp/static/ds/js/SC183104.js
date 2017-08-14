/**
 * 实际历史一览
 * Created by fjm on 16/2/17.
 * @type {{SC183104Grid: null, initDataGrid: Function, actionHandler: Function}}
 */
var SC183104 = {
    SC183104Grid: null,
    startTime:"[name=filterMap\\[\\'inputDateStart\\'\\]Start]",
    endTime:"[name=filterMap\\[\\'inputDateEnd\\'\\]]",
    initDataGrid: function () {
        SC183104.SC183104Grid = $('#SC183104_Grid').grid({
            actionHandler:SC183104.actionHandler,
            fnRowCallback:SC183104.rowCallback
        });
        this.bindDatePicber(SC183104.startTime);
        this.bindDatePicber(SC183104.endTime);
        this.closeDate();
    },

    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    actionHandler:function(rowdata,coltype,row,col){
        if(coltype=="edit"){
                $.pdialog.open("供应商产品实际录入", Main.contextPath + "/SC183103/init", {
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
                        productName : rowdata.classesName,
                        currentHalfCode : rowdata.halfCodeZ,
                        planType : rowdata.pdStockTypeZ,
                        adjustDate : rowdata.inputDate,
                        suppDsId : rowdata.suppDsId,
                        virtualStockActualId : rowdata.actualFlowId
                    })
        };
        if(coltype=="delete"){
            $('#main-content').postUrl(Main.contextPath + "/SC183104/delete/", {
                suppDsId : rowdata.suppDsId,
                actualFlowId : rowdata.actualFlowId,
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
                        var startTime = $(SC183104.startTime).val();
                        var endTime = $(orderTimeId).val();
                        if (startTime != null && startTime != 'underfined' && startTime != '' && startTime > endTime) {
                            $(orderTimeId).val("");
                            $.alertMessage.info("输入正确的时间范围");
                        }
                    }
                    if (orderTimeId.endsWith("Start]")) {
                        var startTime = $(orderTimeId).val();
                        var endTime = $(SC183104.endTime).val();
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
        //var i = data.inputDate.toString();
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
    SC183104.initDataGrid();
    $("[name='halfCode']").width(100);
    $("[name='pdStockType']").width(120);
    $(SC183104.startTime).attr("readonly","true");
    $(SC183104.endTime).attr("readonly","true");
});
