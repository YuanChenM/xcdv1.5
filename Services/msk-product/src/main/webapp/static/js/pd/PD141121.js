/**
 * 物流区产品管理JS
 *
 * @author yuan_chen
 */
var PD141121 = {
    formId: "PD141121Form",
    batchSaveButtonId: "PD141121_BATCHSAVE",
    selectButtonId: "PD141121_SELECT",
    checkedButtonId:"PD141121_CHECKED",
    PD141121Grid: null,
    init: function () {
        $("a[name='selectLgcs']").bind("click", function() {
            $('#main-content').postUrl(Main.contextPath + "/PD141121/init/",{lgcsAreaCode:$(this).attr("selectLogistics"),lgcsAreaName:$(this).attr("selectLgcsName")});
        });
        this.bindSaveButton();
        this.bindSelectButton();
        this.bindChecked();
    },

    bindChecked: function () {
        $("#" + PD141121.checkedButtonId).click(function () {
            formData = getFormData($("#" + PD141121.formId));
            var lgcsAreaCode = $("#ss").val();
            /*$('#main-content').postUrl(Main.contextPath + "/PD14112101/init",{lgcsAreaCode:lgcsAreaCode});*/
            $.pdialog.open("物流区产品查看", Main.contextPath + "/PD141121/checkedList/"+lgcsAreaCode,{width:"1200px"});
        });
    },
    initDataGrid: function () {
        PD141121.PD141121Grid = $('#PD141121_Grid').grid({
            fnRowCallback:PD141121.rowCallback,
            actionHandler: PD141121.actionHandler,
            can_add: PD141121.canAdd,
            can_delete: PD141121.canDelete
        });
        /*$("#ss").removeAttr("name");*/

    },
    rowCallback:function(tr, rowdata){
        if(rowdata.checkFlag == '1'){
            var $td = $(tr).children('td').eq(16);
            var theB=$td[0];
            $(theB).removeAttr("class");
        }
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
                $.post(Main.contextPath + "/PD141121/add", rowdata,function(){
                    PD141121.PD141121Grid.fnDraw(true);
                });
            });
            $("#PD141121_Grid tr:eq("+row+") td:eq("+col+")").attr("disabled","disabled");
        }
        if(coltype=="delete"){
            $.alertMessage.confirm("你确定要删除这条数据吗？", function() {
                $.alertMessage.close();
                $.post(Main.contextPath + "/PD141121/delete", rowdata,function(){
                    PD141121.PD141121Grid.fnDraw(true);
                });
            });
        }
    },
    bindSaveButton: function () {

        $("#" + PD141121.batchSaveButtonId).click(function () {
            $.alertMessage.confirm("你确定要添加这条数据吗？", function () {
                $.alertMessage.close();
                formData = getFormData($("#" + PD141121.formId));
                $('#main-content').postUrl($("#" + PD141121.formId).attr("action"), formData);
            });
        });
    },
    bindSelectButton: function () {
        $("#" + PD141121.selectButtonId).click(function () {
            $.pdialog.open("物流区选择", Main.contextPath + "/PD141122/init", {width:"800px"});
        });
    }
};
$(document).ready(function () {
    // 初始化调用
    PD141121.init();
    PD141121.initDataGrid();

});