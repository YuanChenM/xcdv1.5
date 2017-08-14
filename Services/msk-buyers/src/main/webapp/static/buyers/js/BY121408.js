/**
 * 批发市场报告一览
 *
 */
var $List_Grid;
var BY121408 = {
    BY121408Grid: null,
    uploadButtonId: "BY121408_UPLOAD",
    downloadButtonId: "BY121408_DOWNLOAD",
    formId:"BY121408Form",
    uploadFormId:"BY121408Upload",
    initDataGrid: function () {
        $List_Grid = $('#BY121408_Grid').grid({
            actionHandler: BY121408.actionHandler,
            fnRowCallback :BY121408.fnRowHandler
        });
        this.bindUploadButton();
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if(coltype == "delete"){
            $.alertMessage.confirm("确定要删除文件吗？",function(){
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BY121408/deleteFile/"+rowdata.id,null,function(data){
                    if(data == '1'){
                        $.alertMessage.info("删除成功！");
                        $List_Grid.fnDraw();
                    }
                    if(data == '0'){
                        $.alertMessage.info("删除失败！");
                        return false;
                    }
                },{refreshHtml:false});
            })
        }
    },
    fnRowHandler: function (tr,rowdata) {
        var $td = $(tr).children('td').eq(4);
        $td.html(downloadExcel(rowdata));
    },
    // 绑定上传按钮
    bindUploadButton: function () {
        $("#" + BY121408.uploadButtonId).click(function () {
            if($("#BY121408Upload").validateForm()){
            var validator = mainValidation($("#" + BY121408.uploadFormId));
            var isValid = validator.form();
            if(isValid){
                var uploadFile = $("#" + BY121408.uploadFormId);
                $.core.uploadForm(uploadFile,true);
            }
          }

        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BY121408.initDataGrid();
});

function downloadExcel(rowdata){

    var fileServerIp =$("#fileServerIp").val();
    var serverId = rowdata.fileServerId;
   /* var fileServerIp = rowdata.fileServerIp;*/
    var fileName = rowdata.fileName;

    var fileServerId = serverId.replace(',','/');

    /*var url = fileServerIp + fileServerId + '/' + fileName;*/
    var url = fileServerIp + '?fid=' + fileServerId + '&fileName=' + fileName;

    var str = "<a download='"+fileName+"' class='cellButton download' title='' href='" + url + "' col='3'><img src='../static/images/action/download.png'></a>" +
    "<a class='cellButton delete' title='删除' href='javascript:void(0);' col='6'><img src='../static/images/action/delete.png'></a>";
    str = "<a target='_self' href='' >" + str + "</a>"
    return str;
}