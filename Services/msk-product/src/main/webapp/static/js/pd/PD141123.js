/**
 * 产品加工类型维护JS
 *
 * @author yuan_chen
 */
var $List_Grid;
var PD141123 = {
    formId: "PD141123Form",
    init: function () {
        $List_Grid = $('#PD141123_list_grid').grid({
            actionHandler: PD141123.actionHandler
        });
        this.searchData();
        FormUtils.init(PD141123.formId, "PD141123");
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "add") {
            $.pdialog.open("加工类别添加", Main.contextPath + "/PD14112301/init", {
                width: 600,
                height: 250
            }, {classesCode: rowdata.classesCode, classesName: rowdata.classesName});
        }
        if (coltype == "delete") {
            $.alertMessage.confirm("你确定要删除这条数据吗？", function () {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/PD14112301/delete", rowdata,function(data){
                    $List_Grid.fnDraw();
                },{refreshHtml:false},function(){
                    alert("删除失败")
                });
            });
        }
        if (coltype == "edit") {
            $.pdialog.open("加工类别修改", Main.contextPath + "/PD14112301/init/", {width: 600, height: 250}, rowdata);
        }
    },
    searchData: function () {
        var pdClasses=$('#pdClasses');
        pdClasses.change(function () {
            $List_Grid.fnDraw();//刷新当前的表单
        });
    },
};
$(document).ready(function () {
    // 初始化调用
    PD141123.init();
});