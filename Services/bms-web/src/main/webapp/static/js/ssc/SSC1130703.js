/**
 * Created by wu_honglei on 2016/7/5.
 */
var $List_Grid;
var SSC1130703 = {
    formId:"SSC1130703Form",
    addButtonId:"SSC1130703_ADD",
    init : function() {
        $List_Grid = $("#SSC1130703_list_grid").grid({
            actionHandler:SSC1130703.actionHandler
        })
        this.bindAddButton();
    },

    actionHandler: function (rowdata,coltype,row,col) {
        $('#main-content').postUrl(Main.contextPath + "/SSC1130701/search",{
            "paymentCode":$("#paymentCode").val()
        });
    },
    // 绑定新增按钮
    bindAddButton: function(){
        $("#" + SSC1130703.addButtonId).click(function () {
            $('#main-content').postUrl(Main.contextPath + "/SSC1130702/init",{
                "contractCode":$("#contractCode").val(),
                "contractName":$("#contractName").val(),
                "contractAmount":$("#contractAmount").val(),
                "paymentCode":$("#paymentCode").val()
            });
        });
    }

}
$(document).ready(function(){
    SSC1130703.init();
})