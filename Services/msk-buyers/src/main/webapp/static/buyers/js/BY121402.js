/**
 * 批发市场报告一览
 *
 */
var $List_Grid;
var BY121402 = {
    BY121402Grid: null,
    uploadButtonId: "BY121402_UPLOAD",
    downloadButtonId: "BY121402_DOWNLOAD",
    formId:"BY121402Form",
    uploadFormId:"BY121402Upload",
    initDataGrid: function () {
        $List_Grid = $('#BY121402_Grid').grid({
            fnRowCallback: BY121402.fnRowHandler,
            actionHandler: BY121402.actionHandler
        });
        this.bindUploadButton();
    },
    fnRowHandler : function(tr,rowdata){
        var $td = $(tr).children('td').eq(4);
        $td.html(downloadExcel(rowdata));
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if(coltype == "delete"){
            $.alertMessage.confirm("确定要删除文件吗？",function(){
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BY121402/deleteFile/"+rowdata.id,null,function(data){
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
     //绑定上传按钮
    bindUploadButton: function () {
        $("#" + BY121402.uploadButtonId).click(function () {
            if($("#BY121402Upload").validateForm()){
            var validator = mainValidation($("#" + BY121402.uploadFormId));
            var isValid = validator.form();
            if(isValid){
                var uploadFile = $("#" + BY121402.uploadFormId);
                $.core.uploadForm(uploadFile,true);
            }
            }

        });
    }

}
$(document).ready(function () {
    // 初始化调用
    BY121402.initDataGrid();
});


function downloadExcel(rowdata){
    var serverId = rowdata.fileServerId;
    var fileServerIp = $("#fileServiceIp").val();
    var fileName = rowdata.fileName;
    var fileServerId = serverId.replace(',','/');
    var fileSuff = rowdata.fileSuff;
    var url = fileServerIp + '?fid=' + fileServerId + '&fileName=' + fileName ;
    /* if(fileName.indexOf("xlsx")>0 || fileName.indexOf("xls")>0 || fileName.indexOf("docx")>0){
     var url = fileServerIp + fileServerId + '/' + fileName;
     }else{
     var url = fileServerIp + fileServerId;
     }*/

    var str = "<a download='"+fileName+"' class='cellButton download' title='' href='" + url + "' col='3'><img src='../static/images/action/download.png'></a>" +
        "<a class='cellButton delete' title='删除' href='javascript:void(0);' col='6'><img src='../static/images/action/delete.png'></a>";
    str = "<a target='_self' href='' >" + str + "</a>"
    return str;
}