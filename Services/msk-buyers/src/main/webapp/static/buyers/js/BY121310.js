var $List_Grid;
var BY121310 = {
    editButtonId: "BY121310_EDIT",
    init: function () {
        this.bindEditButton();
    },

    actionHandler: function (rowdata, coltype, row, col) {

    },
    bindEditButton: function () {
        $("#" + BY121310.editButtonId).click(function () {
            var formData = getFormData($("#BY121310SaveForm"));
            if ($("input[type='checkbox']").is(':checked') == false) {
                $.alertMessage.confirm("产品分类不能为空,请选择!");
                return false;
            } else {
                $('#main-content').postUrl(Main.contextPath + "/BY121310/update", formData, function (data) {
                    if (data == 0) {
                        $.alertMessage.confirm("产品保存失败!")
                    }
                    if(data == 1){
                        $.alertMessage.confirm("产品保存成功!")
                    }
                    $('#main-content').postUrl(Main.contextPath + "/BY121310/init/" + formData.buyerId)
                }, {refreshHtml: false});
            }

        });

        //cla_val = [];
        //var flag = true;
        //for(k in formData.buyerPdCla){
        //   mac_val = [];
        //   var str = new Array;
        //   for(s in formData.buyerPdMac){
        //       str = mac_val[s].split("_");
        //       if(str[0] != cla_val[k]){
        //           flag = false;
        //       }else{
        //           flag = true;
        //       }
        //   }
        //}
        //if(flag = true){
        //$('#main-content').postUrl(Main.contextPath + "/BY121310/update",formData)
        //}else{
        //alert("没有选择一级产品,请重新选择")
        //this.bindEditButton();

    }
}

$(document).ready(function () {
    // 初始化调用
    BY121310.init();
    checkMacCheckBoxs();
    checkClaCheckBoxs();
});

function checkClaCheckBoxs() {

    $("input[name='buyerPdCla']").click(function () {
        $('#checkTable').find('input[type=checkbox]').not(this).attr("checked", false);
    })


};

function checkMacCheckBoxs() {
    $("input[name='buyerPdMac']").click(function () {
        //var bylClassCode = $(this).attr("bylClassCode");
        $('#checkTable').find('input[type=checkbox]').not(this).attr("checked", false);
        //$('#buyerPdCla'+bylClassCode).prop('checked',true);
    })

}
