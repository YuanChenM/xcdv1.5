var httpMethod = function(httpUrl, taskId, downloadUrl, fileName, isOpen){
    $.ajax({
        url: httpUrl + taskId,
        contentType: 'application/json',
        type: "GET",
        dataType: "json",
        data : {timeout:20000},
        success: function (data) {
            var message = data.data;
            if(message.status == '1'){
                //$(".fakeloader").hide();
                HDF.closeLoadingMask();
                //处理downloadUrl，为weed-fs可重命名url
                
                var url = '';
                if(isOpen){
                    url = message.info.split(',')[0] + '/' + message.info.split(',')[1] + '/' + fileName;
                }else{
                    url = '?fid=' + message.info + '&fileName=' + fileName;
                }

                window.open(downloadUrl + url,"newWin","");//newWin 是上面form的target
            }
            else if(message.status == '0'){
                httpMethod(httpUrl, taskId, downloadUrl, fileName);
            }
            else if(message.status == '2' || message.status == '3'){
                HDF.closeLoadingMask();
                alert("下载异常！");
            }
        }
    });
}
var downloadAsync = function(templateCode, serviceName, modelName, fileName, param, isOpen){
    MainUtils.showLoadingMask("下载中...");

    var printUrl = Main.contextPath +  $('#printUrl').val();
    var obj = new Object();
    obj['serviceName'] = serviceName;
    obj['modelName'] = modelName;
    obj['templateCode'] = templateCode;
    obj['param'] = param;
    if(isOpen == null){
        isOpen = false;
    }
    obj['isOpen'] = isOpen;
    $.ajax({
        url: printUrl,
        contentType: 'application/json',
        type: "post",
        dataType: "json",
        data: JSON.stringify(obj),
        success: function (data) {
            var taskId = data.data.taskID;
            var downloadUrl = data.data.downloadUrl;
            var websocket = null;
            //判断当前浏览器是否支持WebSocket
            //if('WebSocket' in window){
            if(false){
                var webSocketUrl = "ws://" +  window.location.host + Main.contextPath + '/websocket';
                websocket = new WebSocket(webSocketUrl);
                var interval = window.setInterval(function () {
                    websocket.send(taskId);
                }, 3000);
                //接收到消息的回调方法
                websocket.onmessage = function(event){
                    var message = $.parseJSON(event.data);
                    if(message["status"] == '1'){
                        HDF.closeLoadingMask();
                        var url = message["info"].split(',')[0] + '/' + message["info"].split(',')[1] + '/' + fileName;
                        window.open(downloadUrl + url,"newWin","");//newWin 是上面form的target
                        clearInterval(interval);
                        websocket.close();
                    }
                }
            }
            else{
                var httpUrl = Main.contextPath + '/async/print/check/';
                httpMethod(httpUrl, taskId, downloadUrl, fileName, isOpen);
            }
        }
    });
}