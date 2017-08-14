var $List_Grid;
var BR121408 = {
    saveInButtonId: "BR121408_SAVEBTN",
    fileStartTime: "#fileStartTime",
    fileEndTime: "#fileEndTime",
    formId: "BR121408Form",
    init: function () {
        $List_Grid = $('#BR121408_list_grid').grid({
            actionHandler: BR121408.actionHandler,
            can_generate: BR121408.canGenerate,
            can_download: BR121408.canDownload,
            fnRowCallback: BR121408.fnRowHandler
        });
        this.bindDatePicber(fileStartTime);
        this.bindDatePicber(fileEndTime);
        this.bindSelectChange();
        this.searchData();
        this.closeDate();
    },
    closeDate : function(){
        $(document).on("click","button.ui-datepicker-close",function(){
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    bindDatePicber: function (time) {
        $(time).datepicker({
            showButtonPanel: true,
            dateFormat:'yy-mm-dd',
            currentText: "Today",
            closeText: "Clear",
            changeMonth: true,
            changeYear: true
        });
    },
    canDownload: function (rowdata) {
        if (rowdata.fileStatus == '1') {
            return true;
        }
        return false;
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
        if(coltype == "delete"){
            $.alertMessage.confirm("确定要删除文件吗？",function(){
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BR121408/deleteExcel/"+rowdata.fileId,null,function(data){
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
    canGenerate: function (rowdata) {
        if (rowdata.fileStatus == '0' || rowdata.fileStatus == '2') {
            return true;
        }
        return false;

    },

    //绑定生成按钮
    searchData: function () {
        $("#" + BR121408.saveInButtonId).click(function () {
            var fileStartTime = $("#fileStartTime").val();
            var fileEndTime = $("#fileEndTime").val();
            var lgcsAreaName = "";
            var cityName = "";
            var classesName = "";
            var machiningName = "";
            var lgcsAreaCode = "";
            var cityCode = "";
            var classesCode = "";
            var machiningCode = "";
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

            if ($("#machiningCode option:selected").text() != "--请选择--") {
                machiningName = $("#machiningCode option:selected").text();
                machiningCode = $("#machiningCode option:selected").val();
            }
            var fileStatusFlag = '0';
            var param = {
                "fileStartTime": fileStartTime,
                "fileEndTime": fileEndTime,
                "lgcsAreaCode": lgcsAreaCode,
                "cityCode": cityCode,
                "lgcsAreaName": lgcsAreaName,
                "cityName": cityName,
                "classesName": classesName,
                "machiningName": machiningName,
                "classesCode":classesCode,
                "machiningCode":machiningCode,
                "fileStatusFlag":fileStatusFlag
            };
            if(fileStartTime==""){
                $.alertMessage.info("报表开始时间不能为空!");
                return false;
            };
            if(fileEndTime==""){
                $.alertMessage.info("报表结束时间不能为空!");
                return false;
            };
            if(fileStartTime>fileEndTime){
                $.alertMessage.info("报表开始时间不能大于结束时间!");
                return false;
            };
            if(lgcsAreaCode==""){
                $.alertMessage.info("请选择物流区!");
                return false;
            };
            if(cityCode==""){
                $.alertMessage.info("请选择地区!");
                return false;
            };
            if(classesName ==""){
                $.alertMessage.info("请选择产品一级分类!");
                return false;
            };
            if(machiningName ==""){
                $.alertMessage.info("请选择产品二级分类!");
                return false;
            };
            $('#main-content').postUrl(Main.contextPath + "/BR121408/generateBuyerPoolFileInfo", param, function (data) {

                if(data.dataCount == '2'){
                    $.alertMessage.info("报表文件生成中，请稍候点击下方列表中的查询按钮查看生成结果!");
                    $List_Grid.fnDraw();
                    var fileStatusFlag = '2';
                    var params = {
                        "fileStartTime": fileStartTime,
                        "fileEndTime": fileEndTime,
                        "lgcsAreaCode": lgcsAreaCode,
                        "cityCode": cityCode,
                        "lgcsAreaName": lgcsAreaName,
                        "cityName": cityName,
                        "classesName": classesName,
                        "machiningName": machiningName,
                        "classesCode":classesCode,
                        "machiningCode":machiningCode,
                        "fileStatusFlag":fileStatusFlag,
                        "fileId":data.fileId
                    }
                    $('#main-content').postUrl(Main.contextPath + "/BR121408/createExcel", params, function () {
 /*                       $.alertMessage.info("文件生成成功!");*/
                        //$List_Grid.fnDraw();
                    }, {refreshHtml: false});

                }else if(data.dataCount == '0'){
                    $.alertMessage.info("根据条件未查询到数据,无法生成文件!");
                    return false;
                }else if(data.dataCount == '3'){
                    $.alertMessage.confirm("报表已存在,是否生成最新报表!", function () {
                        var fileStatusFlag = '0';
                        $('#main-content').postUrl(Main.contextPath + "/BR121408/generateBuyerPoolFileInfo", {
                            "fileStartTime": fileStartTime,
                            "fileEndTime": fileEndTime,
                            "lgcsAreaCode": lgcsAreaCode,
                            "cityCode": cityCode,
                            "lgcsAreaName": lgcsAreaName,
                            "cityName": cityName,
                            "classesName": classesName,
                            "machiningName": machiningName,
                            "classesCode": classesCode,
                            "machiningCode": machiningCode,
                            "fileStatusFlag": fileStatusFlag,
                            "flag": true
                        }, function (data) {
                            $.alertMessage.info("报表文件生成中，请稍候点击下方列表中的查询按钮查看生成结果!");
                            $List_Grid.fnDraw();
                            var fileStatusFlag = '2';
                            $('#main-content').postUrl(Main.contextPath + "/BR121408/createExcel", {
                                "fileStartTime": fileStartTime,
                                "fileEndTime": fileEndTime,
                                "lgcsAreaCode": lgcsAreaCode,
                                "cityCode": cityCode,
                                "lgcsAreaName": lgcsAreaName,
                                "cityName": cityName,
                                "classesName": classesName,
                                "machiningName": machiningName,
                                "classesCode": classesCode,
                                "machiningCode": machiningCode,
                                "fileStatusFlag": fileStatusFlag,
                                "fileId": data.fileId
                            }, function () {
                            }, {refreshHtml: false});
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


        //一级分类变更
        $("#classesCode").change(function () {
            var classesCode = $("#classesCode option:selected").val();
            if (classesCode == "") {
                $("#machiningCode").html("");
                $("#machiningCode").append("<option value=''>--请选择--</option>");
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/BR121408/classesChange/" + classesCode, null,
                function (data) {
                    $("#machiningCode").html("");
                    $("#machiningCode").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#machiningCode").append("<option value='" + item.machiningCodeU + "'>" + item.machiningNameU + "</option>");
                    });
                }, {refreshHtml: false});
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BR121408.init();
});

document.onkeydown = function(e){
    var e = e || window.event;
    if(e.keyCode == 13) {
        return false;
    }
}

