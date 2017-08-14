var $List_Grid;
var SSC1130101 = {
    listGrid : null,
    thisRowData: null,
    thisRowIndex:null,
    formId:"SSC1130101Form",
    init: function () {
        SSC1130101.listGrid = $('#SSC1130101_list_grid').grid({
            actionHandler: SSC1130101.actionHandler,
            editCellOnBlurHandler :SSC1130101.cellOnBlurHandler
        });
        this.closeDate();
        this.bindDatePicker($(":input[name*='startDate']"));
        this.bindDatePicker($(":input[name*='endDate']"));
        this.bindConfirmButton();
        Main.hlLeftMainMenu("SSC1130101");
    },

    bindDatePicker : function(timeId){
        $(timeId).datepicker({
            dateFormat: "yy-mm-dd",
            closeText: "清除",
            showButtonPanel: true
        });
        $(timeId).attr("readonly","readonly");
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },

    cellOnBlurHandler : function($comp){
        //获得TD
        var $td = $comp.parent();
        //获得TR
        var $tr = $td.parent();
        //获得所有行
        var $trs = $tr.parent();
        //获得当前行
        SSC1130101.thisRowIndex = $trs.children().index($tr);
        //获得当前行数据
        var num=$comp.val();
        SSC1130101.thisRowData = SSC1130101.listGrid.fnGetData(SSC1130101.thisRowIndex);
    },
    bindConfirmButton:function(){
        $("#SSC1130101_CONFIRM").click(function(){
            var selectEpOne = SSC1130101.listGrid.getChoiceOne();
            var bidInputId =$("#bidInputId").val();
            if("chooseBisInfo"==bidInputId){
                var bidProjectNo=selectEpOne.bidProjectNo;
                var contractId = $("#contractId").val();
                $('#main-content').postUrl(Main.contextPath + '/SSC11302/checkBidBaseExist', {
                    bidProjectNo: bidProjectNo
                }, function (data) {
                    if(data.status=="S"){
                        $.pdialog.close();
                        if(contractId==""){
                            $("#bidProjectNoHref").text(bidProjectNo);
                            $("#bidProjectNo").val(bidProjectNo);
                        }else{
                            //更新合同基础信息
                            $("#main-content").postUrl(Main.contextPath + "/SSC11304/saveOrUpdateContractBase", {
                                bidId:data.result.bidId,
                                bidProjectNo:bidProjectNo,
                                contractId:contractId
                            },function(data2){
                                $.alertMessage.info("操作成功!");
                                $("#headBreadCrumb").hide();
                            });
                        }
                    }else{
                        $.alertMessage.info("该中标成交确认书已有关联的合同，请重新选择！");
                    }
                },{refreshHtml:false});
            }
            $.pdialog.close("chooseEpDialog");

        });
    }
}

$(document).ready(function () {
    // 初始化调用
    SSC1130101.init()
});

