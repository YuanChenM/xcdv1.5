
var $List_Grid;
var SC182213 = {
    formId:"sc182213FormId",
    init : function() {
        $List_Grid = $('#sc182213_list_grid').grid({
            actionHandler:SC182213.actionHandler
        });
    },

    actionHandler:function(rowdata,coltype,row,col){
        /** 操作按钮 */
        if(coltype=="delete"){
            $.alertMessage.confirm("你确定要删除这条数据吗？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SC182213/delete",{lotId:rowdata.lotId,serialId:rowdata.serialId});
            });
        }

        /*if(col==4){
            $('#main-content').postUrl(Main.contextPath + "/SC182213/delete/",{lotId:rowdata.lotId});
        }*/
    }
}
$(document).ready(function() {
    // 初始化调用
    SC182213.init();
});
/**
 * Created by air on 2016/5/12.
 */
