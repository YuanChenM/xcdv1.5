
var BY121318 = {
    listGrid: null,
    periodStart: "#periodStart",
    periodEnd:"#periodEnd",
    createFileBurron:"BY121318_CREATE",
    init: function () {
        BY121318.listGrid = $('#BY121318_list_grid').grid({
            actionHandler: BY121318.actionHandler,
            fnRowCallback: BY121318.fnRowHandler
        });
        this.bindDatePicber(BY121318.periodStart);
        this.bindDatePicber(BY121318.periodEnd);
        this.createFile();
        this.closeDate();
    },
    fnRowHandler : function(tr,rowdata){
        var str;
        var serverId = rowdata.fileServerId;
        var fileName = rowdata.fileName;
        var fileServerId = serverId.replace(',','/');
        var $td = $(tr).children('td').eq(7);
        var url =  $("#fileServerIp").val() + '?fid=' + fileServerId + '&fileName=' + fileName + '.' + "xlsx";

        if(rowdata.fileStatus == '1'){
            str = "<a class='cellButton download' title='' href='" + url + "' col='6'><img src='../static/images/action/download.png'></a>" +
                "<a class='cellButton delete' title='删除' href='javascript:void(0);' col='6'><img src='../static/images/action/delete.png'></a>";
            $td.html("<a target='_self' href='' >" + str + "</a>");
        }
        if(rowdata.fileStatus == '3'){
            str = "<a class='cellButton delete' title='删除' href='javascript:void(0);' col='6'><img src='../static/images/action/delete.png'></a>";
            $td.html("<a target='_self' href='' >" + str + "</a>");
        }

    },
    actionHandler: function (rowdata, coltype, row, col) {
        if (coltype == "delete") {
            /*if(rowdata.fileStatus == "未生成"){
                $.alertMessage.info("文件还未生成,不能删除文件信息!",function(){
                    $.alertMessage.close();
                    return false;
                })
            }else{*/
                $.alertMessage.confirm("确定要删除文件吗？",function(){
                    $.alertMessage.close();
                    $('#main-content').postUrl(Main.contextPath + "/BY121318/reportFileDelete/"+rowdata.fileId,null,function(data){
                        if(data == 'S'){
                            $.alertMessage.info("删除成功");
                            BY121318.listGrid.fnDraw();
                        }
                    },{refreshHtml:false});
                })
            //}
        }
        if(coltype == "download"){
            if(rowdata.fileStatus == "未生成"){
                $.alertMessage.info("文件还未生成!",function(){
                    $.alertMessage.close();
                    return false;
                })
            }else{
                if (coltype == "download") {
                    $.core.downloadUrl(Main.contextPath + "/BY121318/reportDownLoad",rowdata);
                }
            }
        }
    },
    bindDatePicber : function(time){
        $(time).datepicker({
            showButtonPanel: true,
            dateFormat:'yy-mm-dd',
            currentText: "Today",
            closeText: "Clear",
            changeMonth: true,
            changeYear: true
        });
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    createFile: function(){
        $("#" + BY121318.createFileBurron).click(function() {
            if($("#periodStart").val() == ""){
                $.alertMessage.info("请选择所属期开始日期",function(){
                    $.alertMessage.close();
                });
                return false;
            }
            if($("#periodEnd").val() == ""){
                $.alertMessage.info("请选择所属期结束日期",function(){
                    $.alertMessage.close();
                });
                return false;
            }
            if($("#periodEnd").val() < $("#periodStart").val()){
                $.alertMessage.info("所属期结束时间必须大于开始时间",function(){
                    $.alertMessage.close();
                });
                return false;
            }
            var list= $("input[type='radio']:checked").val();
            if(list == null){
                $.alertMessage.info("请选择本期",function(){
                    $.alertMessage.close();
                });
                return false;
            }
            var buyerId = $("#buyerId").val();
            var formData = getFormData($("#createFileForm"));
            var flag = false;
            $('#main-content').postUrl(Main.contextPath + "/BY121318/generateBuyerReportManagerFileInfo/"+flag+"/"+buyerId,formData,function(data){
                if(data.status == 'S'){
                    var  fileId = data.result.filterMap.fileId;
                    if(data.result.filterMap.count == '1'){
                        $.alertMessage.info("报表文件生成中，请稍候点击下方列表中的查询按钮查看生成结果!");
                        BY121318.listGrid.fnDraw();
                        $('#main-content').postUrl(Main.contextPath + "/BY121318/createExcel/"+flag+"/"+fileId+"/"+buyerId,formData,function(data){

                        },{refreshHtml:false})

                    }else if (data.result.filterMap.count == '0'){
                        $.alertMessage.info("根据条件未查询到数据,无法生成文件！");
                        return false;
                    }else if(data.result.filterMap.count == '2'){
                        $.alertMessage.confirm("报表已存在,是否生成最新报表!", function () {
                            flag = true;
                            $('#main-content').postUrl(Main.contextPath + "/BY121318/generateBuyerReportManagerFileInfo/"+flag+"/"+buyerId,formData,function(datas){
                                $.alertMessage.info("报表文件生成中，请稍候点击下方列表中的查询按钮查看生成结果!");
                                BY121318.listGrid.fnDraw();
                                $('#main-content').postUrl(Main.contextPath + "/BY121318/createExcel/"+flag+"/"+datas.result.filterMap.fileId+"/"+buyerId,formData,function(){
                                },{refreshHtml:false})
                            },{refreshHtml:false})

                        })
                    }
                }else{
                    $.alertMessage.info(data.message,function(){
                        $.alertMessage.close();
                    })
                    BY121318.listGrid.fnDraw();
                }
            },{refreshHtml:false})
        });
    }
}

$(document).ready(function () {
    // 初始化调用
    BY121318.init();
});

