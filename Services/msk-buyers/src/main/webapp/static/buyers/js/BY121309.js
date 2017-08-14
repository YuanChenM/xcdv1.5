/**
 *  买家买家池已购产品目录
 * Created by tao_zhifa on 16/7/5.
 */

var $List_Grid;
var BY121309={
    initDataGrid : function () {
        $List_Grid = $("#BY121309_Grid").grid({
            actionHandler : BY121309.actionHandler,
            fnRowCallback : BY121309.rowCallback
        })
    },
    actionHandler :function(rowdate,coltype,row,col){

    },
    rowCallback : function (tr,data) {
        if (data.flag == "1") {
            $(tr).css("color", "red");
        } else {
            $(tr).css("color", "black");
        }
    }
}

$(document).ready(function(){
   BY121309.initDataGrid();
})