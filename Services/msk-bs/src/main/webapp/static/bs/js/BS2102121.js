/**
 * 教育经历
 *
 * @author cx
 */

var BS2102121 = {
    BS2102121Grid:null,
    formId: "bs2102121FormId",
    addButtonId : "BS2102121_ADD",
    init: function () {
        this.bindAddButton();
        BS2102121.BS2102121Grid = $('#BS2102121_list_grid').grid({
            actionHandler: BS2102121.actionHandler
        });
    },

    refresh:function(){
        FormUtils.setFormValue(BS2102121.formId, "BS2102121");
        BS2102121.BS2102121Grid.fnDraw();
    },

    bindAddButton : function () {
        /** 操作按钮 */
        $("#" + BS2102121.addButtonId).click(function () {
            var param = {
                slCode:$("#slCode").val(),
                houseCode:$("#houseCode").val(),
                pageName:"BS2102118"
            };
            $.pdialog.open("新增培训经历", Main.contextPath + "/BS2102113/saveExperience", {width: "30%"}, param,"train");
        });
    },

    actionHandler: function (rowdata, coltype) {
        if (coltype == "delete") {
            $.alertMessage.confirm("确定要删除该培训经历数据吗？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BS2102113/delTrain/",
                    JSON.stringify(rowdata), function (data) {
                        if (data > 0) {
                            BS2102121.refresh();
                        } else {
                            $.alertMessage.info("删除失败");
                        }
                    }, {refreshHtml: false,dataType:"json",contentType : 'application/json;charset=utf-8'});
            });
        }
        if(coltype == "edit"){
            var param = {
                slCode:$("#slCode").val(),
                houseCode:$("#houseCode").val(),
                pageName:"BS2102118",
                trainId : rowdata.trainId
            };
            $.pdialog.open("编辑培训经历", Main.contextPath + "/BS2102113/saveExperience", {width: "30%"}, param,"train");
        }
    },


}

$(document).ready(function () {
    // 初始化调用
    BS2102121.init();
});
