/**
 * 区列表JS
 * Created by yuan_chen on 16/2/22.
 */
var BY121105 = {
    BY121105Grid: null,
    detailRows:[],
    trIndex: 0,
    initDataGrid: function () {
        BY121105.BY121105Grid = $('#BY121105_Grid').grid({
            actionHandler: BY121105.actionHandler,
            can_add: BY121105.canAdd,
            can_abolish: BY121105.canAbolish,
            can_restore: BY121105.canRestore
        });
    },
    canAdd: function (rowdata){
        if(rowdata.districtId == null){
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
                $('#main-content').postUrl(Main.contextPath + "/BY121105/modify", rowdata);
            });
        }
        if(coltype=="delete"){
            $.alertMessage.confirm("你确定要废除这条数据吗？", function() {
                $.alertMessage.close();
                var retDateTime = new Date(rowdata.updTime);
                rowdata.updTime = retDateTime.format('yyyy-MM-dd hh:mm:ss');
                $('#main-content').postUrl(Main.contextPath + "/BY121105/abolish", rowdata);
            });
        }
        if(coltype=="check"){
            $.alertMessage.confirm("你确定要恢复这条数据吗？", function() {
                $.alertMessage.close();
                var retDateTime = new Date(rowdata.updTime);
                rowdata.updTime = retDateTime.format('yyyy-MM-dd hh:mm:ss');
                $('#main-content').postUrl(Main.contextPath + "/BY121105/restore", rowdata);
            });
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    BY121105.initDataGrid();
});