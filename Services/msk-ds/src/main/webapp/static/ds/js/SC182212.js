/**
 * Created by FJM on 2016/4/29.
 */


/*var SC182212 = {
    SC182212Grid: null,
    saveButtonId: "SC182212_SAVE",
    printButtonId:"SC182212_PRINT",
    searchButtonId: "SC182212_SEARCH",
    formId: "SC182212Form",

}
$(document).ready(function () {
    // 初始化调用
    SC182212.initDataGrid();
});*/
var $List_Grid;
var SC182212 = {
    formId:"sc182212FormId",
    init : function() {
        $List_Grid = $('#sc182212_list_grid').grid({
            actionHandler:SC182212.actionHandler
        });
    },

    actionHandler:function(rowdata,coltype,row,col){
        /** 操作按钮 */
        /*if(col==36){
            $('#main-content').postUrl(Main.contextPath + "/SC182212/delete/",{lotId:rowdata.lotId});
        }*/
        if(coltype=="delete"){
            $.alertMessage.confirm("你确定要删除这条数据吗？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SC182212/delete",{lotId:rowdata.lotId});
            });
        }
        if(coltype=="delete2"){
            $.alertMessage.confirm("你确定要删除这条数据吗？", function() {
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/SC182212/deleteMore",{lotId:rowdata.lotId});
            });
        }
    }
}
$(document).ready(function() {
    // 初始化调用
    SC182212.init();
});
