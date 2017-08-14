function downloadExcel(rowdata,fileServerIp){
        var serverId = rowdata.fileServerId;
        var fileServerIp = fileServerIp;
        var fileName = rowdata.fileName;
        var fileSuf = rowdata.fileSuf;
        var fileServerId = serverId.replace(',','/');
        var url = fileServerIp + '?fid=' + fileServerId + '&fileName=' + fileName + '.' + fileSuf;
        var str
        if(rowdata.fileStatus == '1'){
            str = "<a class='cellButton download' title='' href='" + url + "' col='3'><img src='../static/images/action/download.png'></a>" +
                "<a class='cellButton delete' title='删除' href='javascript:void(0);' col='6'><img src='../static/images/action/delete.png'></a>";
            str = "<a target='_self' href='' >" + str + "</a>"
        }else{
            str = "<a class='cellButton delete' title='删除' href='javascript:void(0);' col='6'><img src='../static/images/action/delete.png'></a>";
            str = "<a target='_self' href='' >" + str + "</a>"
        }
        return str;
}



