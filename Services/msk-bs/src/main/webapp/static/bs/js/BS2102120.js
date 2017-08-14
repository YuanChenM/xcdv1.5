/**
 * 教育经历
 *
 * @author cx
 */

var BS2102120 = {
    BS2102120Grid:null,
    formId: "bs2102120FormId",
    addButtonId : "BS2102120_ADD",
    init: function () {
        this.bindAddButton();
        BS2102120.BS2102120Grid = $('#BS2102120_list_grid').grid({
            actionHandler: BS2102120.actionHandler
        });
    },

    refresh:function(){
        FormUtils.setFormValue(BS2102120.formId, "BS2102120");
        BS2102120.BS2102120Grid.fnDraw();
    },

    bindAddButton : function () {
        /** 操作按钮 */
        $("#" + BS2102120.addButtonId).click(function () {
            var param = {
                slCode:$("#slCode").val(),
                houseCode:$("#houseCode").val(),
                pageName:"BS2102117"
            };
            $.pdialog.open("新增教育经历", Main.contextPath + "/BS2102113/saveExperience", {width: "30%"}, param,"edu");
        });
    },

    actionHandler: function (rowdata, coltype) {
        if (coltype == "delete") {
            $.alertMessage.confirm("确定要删除该教育经历数据吗？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BS2102113/delEdu/",
                    JSON.stringify(rowdata), function (data) {
                        if (data > 0) {
                            BS2102120.refresh();
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
                pageName:"BS2102117",
                eduId : rowdata.eduId
            };
            $.pdialog.open("编辑教育经历", Main.contextPath + "/BS2102113/saveExperience", {width: "30%"}, param,"edu");
        }
    },


}

$(document).ready(function () {
    // 初始化调用
    BS2102120.init();
});
