var $List_Grid;
var BR121410 = {
    generateButtonId: "BR121410_GENERATE",
    formId: "BR121410Form",
    fileStartTime: "#fileStartTime",
    fileEndTime: "#fileEndTime",
    init: function () {
        $List_Grid = $('#BR121410_list_grid').grid({
            actionHandler: BR121410.actionHandler,
            fnRowCallback: BR121410.fnRowHandler,
            can_generate: BR121410.canGenerate,
            can_download: BR121410.canDownload
        });
        this.closeDate();
        this.bindDatePicber(fileStartTime);
        this.bindDatePicber(fileEndTime);
        this.bindSelectChange();
        this.createExcel();
    },
    bindDatePicber: function (time) {
        $(time).datepicker({
            showButtonPanel: true,
            dateFormat:'yy-mm-dd',
            changeMonth: true,
            changeYear: true,
            currentText: "Today",
            closeText: "Clear"
        });
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },

    fnRowHandler : function(tr,rowdata){
        var $td = $(tr).children('td').eq(4);
        var fileServerIp = $("#fileServerIp").val();
        if(rowdata.fileStatus == '2'){
            $td.html();
        }else{
            $td.html(downloadExcel(rowdata,fileServerIp));
        }
    },

    actionHandler: function (rowdata, coltype, row, col) {
       /* if (coltype == "download") {
            $.alertMessage.confirm("确定下载该报表？", function () {
                $.core.downloadUrl(Main.contextPath + "/BR121410/reportDownLoad",rowdata);
                $.alertMessage.close();
            });
        }*/
        if(coltype == "delete"){
            $.alertMessage.confirm("确定要删除文件吗？",function(){
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BR121410/deleteExcel/"+rowdata.fileId,null,function(data){
                    if(data.dataCount == '1'){
                        $.alertMessage.info("删除成功");
                        $List_Grid.fnDraw();
                    }
                    if(data.dataCount == '0'){
                        $.alertMessage.info("删除失败");
                        return false;
                    }
                },{refreshHtml:false});
            })
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
    },
    //绑定生成按钮
    createExcel: function () {
        $("#" + BR121410.generateButtonId).click(function () {
            var fileStartTime = $("#fileStartTime").val();
            var fileEndTime = $("#fileEndTime").val();

            var lgcsAreaName = "";
            var cityName = "";
            var lgcsAreaCode = "";
            var cityCode = "";

            var classesName = "";
            var machiningNameU = "";
            var classesCode ="";
            var machiningCodeU ="";

            if ($("#lgcsAreaCode option:selected").text() != "--请选择--") {
                lgcsAreaName = $("#lgcsAreaCode option:selected").text();
                lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            }

            if ($("#cityCode option:selected").text() != "--请选择--") {
                cityName = $("#cityCode option:selected").text();
                cityCode = $("#cityCode option:selected").val();

            }
            if ($("#classesCode option:selected").text() != "--请选择--") {
                classesName = $("#classesCode option:selected").text();
                classesCode = $("#classesCode option:selected").val();
            }
            if ($("#machiningCodeU option:selected").val() != "") {
                machiningNameU = $("#machiningCodeU option:selected").text();
                machiningCodeU = $("#machiningCodeU option:selected").val();
            }else{
                machiningNameU="";
            }
            if(fileStartTime == ""){
                $.alertMessage.info("报表开始时间不能为空!");
                return false;
            }else if(fileEndTime==""){
                $.alertMessage.info("报表结束时间不能为空!");
                return false;
            }else if(fileStartTime>fileEndTime){
                $.alertMessage.info("报表开始时间不能大于结束时间!");
                return false;
            }else if(lgcsAreaCode==""){
                $.alertMessage.info("请选择物流区!");
                return false;
            }else if(cityCode==""){
                $.alertMessage.info("请选择地区!");
                return false;
            }
            /*else if(classesCode==""){
                $.alertMessage.info("请选择产品一级分类!");
                return false;
            }else if(machiningCodeU==""){
                $.alertMessage.info("请选择产品二级分类!");
                return false;
            }*/
            var fileStatusFlag = '0';

            $('#main-content').postUrl(Main.contextPath + "/BR121410/generateBuyerPoolFileInfo", {
                "fileStartTime" : fileStartTime,
                "fileEndTime" : fileEndTime,
                "lgcsAreaName":lgcsAreaName,
                "cityName":cityName,
                "classesName":classesName,
                "machiningNameU":machiningNameU,
                "lgcsAreaCode" : lgcsAreaCode,
                "cityCode" : cityCode,
                "classesCode" : classesCode,
                "machiningCodeU" : machiningCodeU,
                "fileStatusFlag":fileStatusFlag

            }, function (data) {
                if(data.dataCount == '2'){
                    fileStatusFlag = '2';
                    $.alertMessage.info("报表文件生成中，请稍候点击下方列表中的查询按钮查看生成结果!");
                    $List_Grid.fnDraw();
                    var params = {
                        "fileStartTime" : fileStartTime,
                        "fileEndTime" : fileEndTime,
                        "lgcsAreaName":lgcsAreaName,
                        "cityName":cityName,
                        "classesName":classesName,
                        "machiningNameU":machiningNameU,
                        "lgcsAreaCode" : lgcsAreaCode,
                        "cityCode" : cityCode,
                        "classesCode" : classesCode,
                        "machiningCodeU" : machiningCodeU,
                        "fileStatusFlag":fileStatusFlag,
                        "fileId":data.fileId
                    }
                    $('#main-content').postUrl(Main.contextPath + "/BR121410/createExcel", params, function () {
                    }, {refreshHtml: false});
                }else if(data.dataCount == '0'){
                    $.alertMessage.info("根据条件未查询到数据,无法生成文件!");
                    return false;
                }else if(data.dataCount == '3'){

                    $.alertMessage.confirm("报表已存在,是否生成最新报表!", function () {
                         fileStatusFlag = '0';
                        $('#main-content').postUrl(Main.contextPath + "/BR121410/generateBuyerPoolFileInfo", {
                            "fileStartTime" : fileStartTime,
                            "fileEndTime" : fileEndTime,
                            "lgcsAreaName":lgcsAreaName,
                            "cityName":cityName,
                            "classesName":classesName,
                            "machiningNameU":machiningNameU,
                            "lgcsAreaCode" : lgcsAreaCode,
                            "cityCode" : cityCode,
                            "classesCode" : classesCode,
                            "machiningCodeU" : machiningCodeU,
                            "fileStatusFlag":fileStatusFlag,
                            "flag" : true
                        }, function (datas) {
                            $.alertMessage.info("报表文件生成中，请稍候点击下方列表中的查询按钮查看生成结果!");
                            $List_Grid.fnDraw();
                            fileStatusFlag = '2';
                            $('#main-content').postUrl(Main.contextPath + "/BR121410/createExcel", {
                                "fileStartTime" : fileStartTime,
                                "fileEndTime" : fileEndTime,
                                "lgcsAreaName":lgcsAreaName,
                                "cityName":cityName,
                                "classesName":classesName,
                                "machiningNameU":machiningNameU,
                                "lgcsAreaCode" : lgcsAreaCode,
                                "cityCode" : cityCode,
                                "classesCode" : classesCode,
                                "machiningCodeU" : machiningCodeU,
                                "fileStatusFlag":fileStatusFlag,
                                "fileId":datas.fileId
                            }, function () {}, {refreshHtml: false});
                        }, {refreshHtml: false});
                    })
                }
            }, {refreshHtml: false});
        });

    },
    bindSelectChange: function () {
        // 物流区变更
        $("#lgcsAreaCode").change(function () {
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            if (lgcsAreaCode == "") {
                $("#cityCode").html("");
                $("#cityCode").append("<option value=''>--请选择--</option>");
                $("#districtCode").html("");
                $("#districtCode").append("<option value=''>--请选择--</option>");
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/BR121401/lgcsAreaChange/" + lgcsAreaCode, null,
                function (data) {
                    $("#cityCode").html("");
                    $("#cityCode").append("<option value=''>--请选择--</option>");
                    $("#districtCode").html("");
                    $("#districtCode").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#cityCode").append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                    });
                }, {refreshHtml: false});
        });

        // 城市变更
        $("#cityCode").change(function () {
            var cityCode = $("#cityCode option:selected").val();
            if (cityCode == "") {
                $("#districtCode").html("");
                $("#districtCode").append("<option value=''>--请选择--</option>");
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/BR121401/cityChange/" + cityCode, null,
                function (data) {
                    $("#districtCode").html("");
                    $("#districtCode").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#districtCode").append("<option value='" + item.districtCode + "'>" + item.districtName + "</option>");
                    });
                },{refreshHtml:false});
        });

        //一级分类变更
        $("#classesCode").change(function () {
            var classesCode = $("#classesCode option:selected").val();
            if (classesCode == "") {
                $("#machiningCodeU").html("");
                $("#machiningCodeU").append("<option value=''>--请选择--</option>");
                return ;
            }
            $('#main-content').postUrl(Main.contextPath + "/BR121410/classesChange/" + classesCode, null,
                function (data) {
                    $("#machiningCodeU").html("");
                    $("#machiningCodeU").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#machiningCodeU").append("<option value='" + item.machiningCodeU + "'>" + item.machiningNameU + "</option>");
                    });
                }, {refreshHtml: false});
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BR121410.init();
});

document.onkeydown = function(e){
    var e = e || window.event;
    if(e.keyCode == 13) {
        return false;
    }
}
