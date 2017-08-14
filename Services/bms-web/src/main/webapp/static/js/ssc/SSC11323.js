/**
 * Created on 2016/8/3.
 */
var $List_Grid;
var SSC11323={
    formId:"SSC11323Form",
    init: function () {
        $List_Grid=$('#SSC11323_list_grid').grid({
            actionHandler:SSC11323.actionHandler,
            can_delete:SSC11323.canAbolish
            //can_abolish:SSC11323.canAbolish

        }
        );
        this.bindNewButton();
        this.bindDatePicker($(":input[name*='requestTimeStr']"));
        SSC11323.closeDate();
        this.bindButton();
    },
    actionHandler:function(rowdata, coltype, row, col){
        if(coltype=="detail"){
            if($("input[name='disableBtn']").val()==1){
                $.pdialog.open("发票详情",Main.contextPath+"/SSC11324/show",{
                    width: 900,
                    height: 600},
                    {
                        invoiceRequestId:rowdata.invoiceRequestId,
                        invoiceRequestCode:rowdata.invoiceRequestCode,
                        contractCode:rowdata.contractCode
                    },"chooseEpDialog"
                );
            }else{
                /*$('#main-content').postUrl(Main.contextPath+"/SSC11324/init/2",{
                    invoiceRequestId:rowdata.invoiceRequestId,
                    invoiceRequestCode:rowdata.invoiceRequestCode,
                    contractCode:rowdata.contractCode,
                    contractName:rowdata.contractName,
                    invoiceAmount:rowdata.invoiceAmount,
                    invoiceRequestDesc:rowdata.invoiceRequestDesc,
                    payer:rowdata.payer
                });*/

                Main.detailWindow(Main.contextPath + "/SSC11324/init/2", {
                    invoiceRequestId:rowdata.invoiceRequestId,
                    invoiceRequestCode:rowdata.invoiceRequestCode,
                    contractCode:rowdata.contractCode,
                    contractName:rowdata.contractName,
                    invoiceAmount:rowdata.invoiceAmount,
                    invoiceRequestDesc:rowdata.invoiceRequestDesc,
                    payer:rowdata.payer
                }, "发票申请详细");
            }

        }else if(coltype=="delete") {
            if(rowdata.status=="1"||rowdata.status=="2"||rowdata.status=="3"||rowdata.status=="4"){
                $.alertMessage.info("该数据已通过审批/审核,不能删除");
                return;
            }
            $.alertMessage.confirm("确定要删除这条数据吗？",function(){
                    $.alertMessage.close();
                    $('#main-content').postUrl(Main.contextPath+"/SSC11323/delete",{
                        invoiceRequestId:rowdata.invoiceRequestId,
                        invoiceRequestCode:rowdata.invoiceRequestCode,
                        contractCode:rowdata.contractCode,
                        contractName:rowdata.contractName,
                        invoiceAmount:rowdata.invoiceAmount,
                        invoiceRequestDesc:rowdata.invoiceRequestDesc,
                        ver:rowdata.ver,
                        payer:rowdata.payer});
                })
        }
    },
    canDelete: function (rowdata){
        if(rowdata.status=="4"||rowdata.status=="3"||rowdata.status=="2"){
            return false;
        }
        return true;
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

    bindNewButton:function(){
            $("#SSC11323_NEW").click(function(){
                $.pdialog.open("新增发票", Main.contextPath + "/SSC11323/chooseInit",{width:400,height:150},null);//默认高度修改
            });
    },
    canAbolish: function (rowdata) {
        var Status = rowdata.status;
        if (Status == "9") {
            return false;
        }
        return true;
    },
    bindButton: function(){
        //查询
        $("#SSC11323_SEARCH").click(function () {

            FormUtils.setFormValue("SSC11323Form", "SSC11323");
            $List_Grid.fnDraw();
        });
    }

}
$(document).ready(function(){
    SSC11323.init();
});