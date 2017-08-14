/**
 * 定级管理JS
 *
 * @author cx
 */
var $List_Grid;
var BS2102111 = {
    formId: "BS2102111Form",
    saveDate: "BS2102111_SAVE",
    init: function () {
        $List_Grid = $('#bs2102111_list_grid').grid({

        });
        this.bindConfirmButton();
    },
    // 绑定按钮
    bindConfirmButton: function () {
        $("#" + BS2102111.saveDate).click(function () {
            var changeData = $List_Grid.getChangeData();// 获取改动的数据对象  是数组
            if (changeData.length == 0) {
                $.alertMessage.confirm("请先编辑要保存的数据再保存！", function () {
                    $.alertMessage.close();
                })
            } else {
                $.alertMessage.confirm("你确定要保存当前数据吗？", function () {
                    $.alertMessage.close();
                    var json = {};// 创建json 对象
                    for (i = 0; i < changeData.length; i++) {//  把数组的对象封装到json
                        json[i] = changeData[i];
                    }
                    var jsonStr = JSON.stringify(json);//  转成jsonStr
                    $('#main-content').postUrl(Main.contextPath + "/BS2102111/save", {"jsonStr": jsonStr}, function (data) {
                        $.alertMessage.info("修改已提交");
                        FormUtils.setFormValue(BS2102111.formId, "BS2102111");
                        $List_Grid.fnDraw();
                    }, {refreshHtml: false});
                })

            }
        });

    },
    search:function(){
        var param = {
            slCode:$("#slCode").val(),
            houseCode:$("#houseCode").val()
        };
        $('#main-content').postUrl(
            $("#" + BS2102111.formId).attr("action",Main.contextPath +"/BS2102111/search"), param, function () {
                $List_Grid.fnDraw();
            }, {refreshHtml: false})
    }

}
$(document).ready(function () {
    // 初始化调用
    BS2102111.init();

});
