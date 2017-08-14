/**
 * Created by tao_zhifa on 2016/7/26.
 */

var $List_Grid;
var IBR121406 = {
    generateButtonId: "BR121406_GENERATE",
    formId: "BR121406Form",
    fileStartTime: "#fileStartTime",
    fileEndTime: "#fileEndTime",
    init: function () {
        $List_Grid = $("#BR121406_list_grid").grid({
            actionHandler: IBR121406.actionHandler,
            fnRowCallback: IBR121406.fnRowHandler,
            can_generate: IBR121406.canGenerate,
            can_download: IBR121406.canDownload
        });
        this.bindDatePicber(fileStartTime);
        this.bindDatePicber(fileEndTime);
        this.createExcel();
        this.bindSelectChang();
        this.closeDate();
    },
    fnRowHandler: function (tr, rowdata) {
        var $td = $(tr).children('td').eq(4);
        var fileServerIp = $("#fileServerIp").val();
        if (rowdata.fileStatus == '2') {
            $td.html();
        } else {
            $td.html(downloadExcel(rowdata,fileServerIp));
        }
    },
    actionHandler: function (rowdata, coltype, row, col) {
        if(coltype == "delete"){
            $.alertMessage.confirm("确定要删除文件吗？",function(){
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BR121406/deleteExcel/"+rowdata.fileId,null,function(data){
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
    bindDatePicber: function (time) {
        $(time).datepicker({
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            currentText: "Today",
            closeText: "Clear",
            changeMonth: true,
            changeYear: true
        });
    },
    closeDate: function () {
        $(document).on("click", "button.ui-datepicker-close", function () {
            $.datepicker._clearDate($.datepicker._curInst.input);
        });
    },
    bindSelectChang: function () {
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
                }, {refreshHtml: false});
        });
        //一级分类变更
        $("#classesCode").change(function () {
            var classesCode = $("#classesCode option:selected").val();
            if (classesCode == "") {
                $("#machiningCodeU").html("");
                $("#machiningCodeU").append("<option value=''>--请选择--</option>");
                return;
            }
            $('#main-content').postUrl(Main.contextPath + "/BR121406/classesChange/" + classesCode, null,
                function (data) {
                    $("#machiningCodeU").html("");
                    $("#machiningCodeU").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#machiningCodeU").append("<option value='" + item.machiningCodeU + "'>" + item.machiningNameU + "</option>");
                    });
                }, {refreshHtml: false});
        });
    },
    createExcel: function () {
        $("#" + IBR121406.generateButtonId).click(function () {
            var fileStartTime = $("#fileStartTime").val();
            var fileEndTime = $("#fileEndTime").val();
            var lgcsAreaCode = $("#lgcsAreaCode").val();
            var cityCode = $("#cityCode").val();
            var classesCode = $("#classesCode").val();
            var machiningCodeU = $("#machiningCodeU").val();
            if (fileStartTime == "") {
                $.alertMessage.info("报表开始时间不能为空!");
                return false;
            } else if (fileEndTime == "") {
                $.alertMessage.info("报表结束时间不能为空!");
                return false;
            } else if (fileStartTime > fileEndTime) {
                $.alertMessage.info("报表开始时间不能大于结束时间!");
                return false;
            } else if (lgcsAreaCode == "") {
                $.alertMessage.info("请选择物流区!");
                return false;
            } else if (cityCode == "") {
                $.alertMessage.info("请选择地区!");
                return false;
            } else if (classesCode == "") {
                $.alertMessage.info("请选择产品一级分类!");
                return false;
            } else if (machiningCodeU == "") {
                $.alertMessage.info("请选择产品二级分类!");
                return false;
            }
            var fileStatusFlag = '0';
            var params = {
                "fileStartTime": fileStartTime,
                "fileEndTime": fileEndTime,
                "lgcsAreaCode": lgcsAreaCode,
                "cityCode": cityCode,
                "classesCode": classesCode,
                "machiningCodeU": machiningCodeU,
                "fileStatusFlag": fileStatusFlag
            }

            $('#main-content').postUrl(Main.contextPath + "/BR121406/generateBuyerPoolFileInfo", params, function (data) {
                var fileId = data.fileId;
                if (data.dataCount == '2') {
                    $.alertMessage.info("报表文件生成中，请稍候点击下方列表中的查询按钮查看生成结果!");
                    $List_Grid.fnDraw();
                    fileStatusFlag = '2';
                    var param = {
                        "fileStartTime": fileStartTime,
                        "fileEndTime": fileEndTime,
                        "lgcsAreaCode": lgcsAreaCode,
                        "cityCode": cityCode,
                        "classesCode": classesCode,
                        "machiningCodeU": machiningCodeU,
                        "fileStatusFlag": fileStatusFlag,
                        "fileId": fileId
                    };
                    $('#main-content').postUrl(Main.contextPath + "/BR121406/createExcel", param, function () {
                    }, {refreshHtml: false});
                } else if (data.dataCount == '0') {
                    $.alertMessage.info("根据条件未查询到数据,无法生成文件！");
                    return false;
                } else if (data.dataCount == '3') {
                    fileStatusFlag = '0';
                    $.alertMessage.confirm("报表已存在,是否生成最新报表!", function () {
                        $('#main-content').postUrl(Main.contextPath + "/BR121406/generateBuyerPoolFileInfo", {
                            "fileStartTime": fileStartTime,
                            "fileEndTime": fileEndTime,
                            "lgcsAreaCode": lgcsAreaCode,
                            "cityCode": cityCode,
                            "classesCode": classesCode,
                            "machiningCodeU": machiningCodeU,
                            "fileStatusFlag": fileStatusFlag,
                            "flag" : true
                        }, function (dates) {
                            $.alertMessage.info("报表文件生成中，请稍候点击下方列表中的查询按钮查看生成结果!");
                            $List_Grid.fnDraw();
                            fileStatusFlag = '2';
                            var param = {
                                "fileStartTime": fileStartTime,
                                "fileEndTime": fileEndTime,
                                "lgcsAreaCode": lgcsAreaCode,
                                "cityCode": cityCode,
                                "classesCode": classesCode,
                                "machiningCodeU": machiningCodeU,
                                "fileStatusFlag": fileStatusFlag,
                                "fileId": dates.fileId
                            };
                            $('#main-content').postUrl(Main.contextPath + "/BR121406/createExcel", param, function () {
                            }, {refreshHtml: false});
                        }, {refreshHtml: false});
                    })
                }
            }, {refreshHtml: false});
        })
    },
    canGenerate: function (rowdata) {
        if (rowdata.fileStatus == '0') {
            return true;
        }
        return false;
    },
    canDownload: function (rowdata) {
        if (rowdata.fileStatus == '1') {
            return true;
        }
        return false;
    }
}


$(document).ready(function () {
    IBR121406.init();
});

document.onkeydown = function(e){
    var e = e || window.event;
    if(e.keyCode == 13) {
        return false;
    }
}