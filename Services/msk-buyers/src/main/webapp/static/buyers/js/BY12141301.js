var $List_Grid;
var BY12141301 = {
    init: function () {
        this.bindEditButton();
    },
    bindEditButton: function () {
        $("#BY12141301_SAVE").click(function () {
            if ($("#BY12141301SaveForm").validateForm()) {
            var buyerStoreNo = $("#buyerStoreNo").val();
            var marketId = $("#marketId").val();
            var isTargetMerchant = $("#isTargetMerchant").val();
            var formData = getFormData($("#BY12141301SaveForm"));
            var addFlg = $("#addFlg").val();
            if (buyerStoreNo == null || buyerStoreNo == "") {
                $.alertMessage.info("输入的店铺号为空,请查证后再输入 !");
                return false;
            }
            //是否是目标买家
            obj = document.getElementsByName("salePds");
            check_val = [];
            for (var k = 0; k < obj.length; k++) {
                if (obj[k].checked)
                    check_val.push(obj[k].value);
            }
            if (check_val.length == 0) {
                $.alertMessage.warn("请至少选一种产品");
                return false;
            }else{
                if (addFlg == "1") {
                    //新增
                    $('#main-content').postUrl(Main.contextPath + "/BY12141301/save/", formData, function (data) {
                        if (data == "新增成功") {
                            $List_GridNonTarget.fnDraw();
                            $List_GridIsTarget.fnDraw();
                            $.pdialog.close();
                        } else if (data == "该店铺已存在,请查证") {
                            $.alertMessage.info("该店铺已存在,请查证!");
                            return false;
                        } else {
                            $.alertMessage.info("店铺新增成功");
                            $List_GridNonTarget.fnDraw();
                            $List_GridIsTarget.fnDraw();
                            $.pdialog.close();
                        }
                    }, {refreshHtml: false})
                } else {
                    //编辑
                    $('#main-content').postUrl(Main.contextPath + "/BY12141301/update/", formData, function (data) {
                        if (data == "编辑成功") {
                            $List_GridIsTarget.fnDraw();
                            $List_GridNonTarget.fnDraw();
                            $.pdialog.close();
                        }
                    }, {refreshHtml: false})
                }

            }

          }

        });

    }
}

$(document).ready(function () {
    // 初始化调用
    BY12141301.init();
});