/**
 * 实际入力信息JS
 * Created by yuan_chen on 16/1/13.
 */
var SC182203 = {
    SC182203Grid: null,
    initDataGrid: function () {
        SC182203.SC182203Grid = $('#SC182203_Grid').grid({
            actionHandler:SC182203.actionHandler
        });
    },
    actionHandler:function(rowdata,coltype,row,col){
        if(coltype=="edit"){
            // $('#main-content').postUrl(Main.contextPath + "/SC182205/init/", rowdata)
            Main.detailWindow(Main.contextPath + "/SC182205/init/", rowdata, "产品批次明细打印")
        };
        if(coltype=="delete"){
            $.alertMessage.confirm("你确定要删除这条数据吗？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SC182205/delete", rowdata);
            });
        }

    }

}
$(document).ready(function () {
    // 初始化调用
    SC182203.initDataGrid();
});