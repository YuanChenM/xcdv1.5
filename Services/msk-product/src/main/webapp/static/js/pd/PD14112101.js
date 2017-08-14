/**
 * 物流区产品管理JS
 *
 * @author yuan_chen
 */
var PD14112101 = {
    formId: "PD14112101Form",
    PD14112101Grid: null,
    initDataGrid: function () {
        PD14112101.PD14112101Grid = $('#PD14112101_Grid').grid({
            actionHandler: PD14112101.actionHandler,
            can_add: PD14112101.canAdd,
            can_delete: PD14112101.canDelete
        });
        /*$("#ss").removeAttr("name");*/

    },
    canAdd: function (rowdata){
        if(rowdata.lgcsId == null){
            return true;
        }
        return false;
    },
    canDelete: function (rowdata){
        if(rowdata.lgcsId == null){
            return false;
        }
        return true;
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if(coltype=="add"){
            $.alertMessage.confirm("你确定要添加这条数据吗？", function() {
                $.alertMessage.close();
                $.post(Main.contextPath + "/PD14112101/add", rowdata,function(){
                    PD14112101.PD14112101Grid.fnDraw(true);
                });
            });
        }
        if(coltype=="delete"){
                $.alertMessage.confirm("你确定要删除这条数据吗？", function() {
                    $.alertMessage.close();
                    $.post(Main.contextPath + "/PD14112101/delete",rowdata,function(data){
                        if(data=="ok"){
                            //刷新表
                            $.alertMessage.info('操作成功！',function(opt){
                                $.alertMessage.close();
                                PD14112101.PD14112101Grid.fnDraw(true);
                            });//提示：操作成功
                        }
                        else{
                            $.alertMessage.error(data,function(opt){
                            });
                        }
                    });
                });
        }
    }
};
$(document).ready(function () {
    // 初始化调用
    PD14112101.initDataGrid();
});