/**
 * 卖家货号列表JS
 *
 * @author pxg
 */
var $List_Grid;
var SL241132 = {
    formId: 'SL241132Form',
    uploadFormId: 'SL241132UploadForm',
    init: function () {
        $List_Grid = $('#SL241132_list_grid').grid({
            actionHandler:SL241132.actionHandler
        });
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if(coltype=='detail'){
            $.pdialog.open("卖家货号信息编辑",Main.contextPath + "/SL24113201/init",
                {
                    width:600,
                    height:500
                }, rowdata);
        }
    }
}
$(document).ready(function () {
    // 初始化调用
    SL241132.init();
});
document.onkeydown = function mykeyDown(e){
    var e = window.event || e;
    if(e.keyCode == 13) {
        return false;
    }
}