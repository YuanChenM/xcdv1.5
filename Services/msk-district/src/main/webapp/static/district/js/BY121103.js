/**
 * 物流区列表JS
 * Created by yuan_chen on 16/2/22.
 */
var BY121103 = {
    BY121103Grid: null,
    detailRows:[],
    trIndex: 0,
    initDataGrid: function () {
        BY121103.BY121103Grid = $('#BY121103_Grid').grid({
            actionHandler: BY121103.actionHandler,
            can_add: BY121103.canAdd,
            can_abolish: BY121103.canAbolish,
            can_restore: BY121103.canRestore
        });
    },
    canAdd: function (rowdata){
        if(rowdata.lgcsAreaId == null){
            return true;
        }
        return false;
    },
    canAbolish: function (rowdata){
        if(rowdata.delFlg == '0'){
            return true;
        }
        return false;
    },
    canRestore: function (rowdata){
        if(rowdata.delFlg == '1'){
            return true;
        }
        return false;
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "add") {
        }
        if (coltype == "save") {
            $.alertMessage.confirm("你确定要修改这条数据吗？", function() {
                $.alertMessage.close();
                var retDateTime = new Date(rowdata.updTime);
                rowdata.updTime = retDateTime.format('yyyy-MM-dd hh:mm:ss');
                $('#main-content').postUrl(Main.contextPath + "/BY121103/modify", rowdata);
            });
        }
        if (coltype == "edit") {
            $('#main-content').postUrl(Main.contextPath + "/BY121104/init/all/" + rowdata.lgcsAreaId);
        }
        if(coltype=="delete"){
            $.alertMessage.confirm("你确定要废除这条数据吗？", function() {
                $.alertMessage.close();
                var retDateTime = new Date(rowdata.updTime);
                rowdata.updTime = retDateTime.format('yyyy-MM-dd hh:mm:ss');
                $('#main-content').postUrl(Main.contextPath + "/BY121103/abolish", rowdata);
            });
        }
        if(coltype=="check"){
            $.alertMessage.confirm("你确定要恢复这条数据吗？", function() {
                $.alertMessage.close();
                var retDateTime = new Date(rowdata.updTime);
                rowdata.updTime = retDateTime.format('yyyy-MM-dd hh:mm:ss');
                $('#main-content').postUrl(Main.contextPath + "/BY121103/restore", rowdata);
            });
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    BY121103.initDataGrid();
});