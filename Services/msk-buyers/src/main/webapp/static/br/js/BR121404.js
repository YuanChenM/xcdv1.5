var $List_Grid;
var BR121404 = {
    init: function () {
        $List_Grid = $('#BR121404_list_grid').grid({
            actionHandler: BR121404.actionHandler,
            fnRowCallback: BR121404.fnRowHandler,
            can_generate: BR121404.canGenerate,
            can_download: BR121404.canDownload
        });
    },
    fnRowHandler : function(tr,rowdata){
        var str;
        var serverId = rowdata.fileServerId;
        var fileName = rowdata.fileName;
        var fileStatus = rowdata.fileStatus;
        var fileServerId = serverId.replace(',','/')
        var $td = $(tr).children('td').eq(3);
        var url = rowdata.fileServerIp + fileServerId +'/'+ fileName + '.xlsx';
        if(fileStatus == '0'){
            str = "<a class='cellButton save' title='生成' href='javascript:void(0);" + url + "' col='3'><img src='../static/images/action/save.png'></a>";
            $td.html(str);
        }else if(fileStatus == '1'){
            str = "<a class='cellButton download' title='' href='" + url + "' col='3'><img src='../static/images/action/download.png'></a>";
            $td.html("<a target='_self' href='' >" + str + "</a>");
        }
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "save") {
            var buyerId=$('#byId').val();
            $('#main-content').postUrl(Main.contextPath + "/BR121404/createExcel", rowdata, function () {
                   $.alertMessage.info("生成成功!");
                    $List_Grid.fnDraw();
            }, {refreshHtml: false});
        }
    },
    canGenerate: function(rowdata){
        if(rowdata.fileStatus == '0'){
            return true;
        }
        return false;
    },
    canDownload: function(rowdata){
        if(rowdata.fileStatus == '1'){
            return true;
        }
        return false;
    }
}
$(document).ready(function () {
    // 初始化调用
    BR121404.init();
});

