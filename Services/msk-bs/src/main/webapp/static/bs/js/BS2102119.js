/**
 * 工作经历
 *
 * @author cx
 */

var BS2102119 = {
    BS2102119Grid:null,
    formId: "bs2102119FormId",
    addButtonId : "BS2102119_ADD",
    init: function () {
        this.bindAddButton();
        BS2102119.BS2102119Grid = $('#BS2102119_list_grid').grid({
            actionHandler: BS2102119.actionHandler
        });
    },

    refresh:function(){
        FormUtils.setFormValue(BS2102119.formId, "BS2102119");
        BS2102119.BS2102119Grid.fnDraw();
    },

    bindAddButton : function () {
        /** 操作按钮 */
        $("#" + BS2102119.addButtonId).click(function () {
            var param = {
                slCode:$("#slCode").val(),
                houseCode:$("#houseCode").val(),
                pageName:"BS2102116"
            };
            $.pdialog.open("新增工作经历", Main.contextPath + "/BS2102113/saveExperience", {width: "30%"}, param,"work");
        });
    },

    actionHandler: function (rowdata, coltype) {
        if (coltype == "delete") {
            $.alertMessage.confirm("确定要删除该工作经历数据吗？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BS2102113/delWork/",
                    JSON.stringify(rowdata), function (data) {
                        if (data > 0) {
                            BS2102119.refresh();
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
                pageName:"BS2102116",
                workId : rowdata.workId
            };
            $.pdialog.open("编辑工作经历", Main.contextPath + "/BS2102113/saveExperience", {width: "30%"}, param,"work");
        }
    },
  

}

$(document).ready(function () {
    // 初始化调用
    BS2102119.init();
});
