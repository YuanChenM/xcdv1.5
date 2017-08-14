var $List_Grid;
var BR121407 = {
    generateButtonId: "BR121407_GENERATE",
    formId: "BR121407Form",
    fileStartTime: "#fileStartTime",
    fileEndTime: "#fileEndTime",
    init: function () {
        $List_Grid = $('#BR121407_list_grid').grid({
            actionHandler: BR121407.actionHandler,
            fnRowCallback: BR121407.fnRowHandler,
            can_generate: BR121407.canGenerate,
            can_download: BR121407.canDownload
        });
        this.bindDatePicber(fileStartTime);
        this.bindDatePicber(fileEndTime);
        this.bindSelectChange();
        this.createExcel();
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
            changeMonth: true,
            currentText: "Today",
            closeText: "Clear",
            changeYear: true
        });
    },fnRowHandler : function(tr,rowdata){
        var $td = $(tr).children('td').eq(6);
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
                $('#main-content').postUrl(Main.contextPath + "/BR121407/deleteExcel/"+rowdata.fileId,null,function(data){
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
        $("#" + BR121407.generateButtonId).click(function () {
            var fileStartTime= $("#fileStartTime").val();
            var fileEndTime= $("#fileEndTime").val();
            var buyerType= $("#superiorType").val();
            var lgcsAreaCode=$("#lgcsAreaCode").val();
            var cityCode = $("#cityCode").val();
            var classesCode = $("#classesCode").val();
            var machiningCodeU = $("#machiningCodeU").val();
            var marketingsStatusCla = $("#marketingsStatusCla").val();
            var marketingsPeriodName = $("#marketingsStatusCla").find("option:selected").text();
            var marketingsStatus = $("#marketingsStatus").val();
            /*var marketingsStatusName = $("#marketingsStatus").find("option:selected").text();*/
            if(fileStartTime==""){
                $.alertMessage.info("报表开始时间不能为空!");
                return false;
            }else if(fileEndTime==""){
                $.alertMessage.info("报表结束时间不能为空!");
                return false;
            }else if(fileStartTime>fileEndTime){
                $.alertMessage.info("报表开始时间不能大于结束时间!");
                return false;
            }else if(buyerType==""){
                $.alertMessage.info("请选择买家类型!");
                return false;
            }else if(lgcsAreaCode==""){
                $.alertMessage.info("请选择物流区!");
                return false;
            }else if(cityCode==""){
                $.alertMessage.info("请选择地区!");
                return false;
            }else if(classesCode==""){
                $.alertMessage.info("请选择产品一级分类!");
                return false;
            }else if(machiningCodeU==""){
                $.alertMessage.info("请选择产品二级分类!");
                return false;
            }else if(marketingsStatusCla==""){
                $.alertMessage.info("请选择买家上线状态一级分类!");
                return false;
            }
            var fileStatusFlag = '0';
            $('#main-content').postUrl(Main.contextPath + "/BR121407/generateBuyerPoolFileInfo", {
                "fileStartTime" : fileStartTime,
                "fileEndTime" : fileEndTime,
                "buyerType" : buyerType,
                "lgcsAreaCode" : lgcsAreaCode,
                "cityCode" : cityCode,
                "classesCode" : classesCode,
                "machiningCodeU" : machiningCodeU,
                "marketingsStatusCla" : marketingsStatusCla,
                "marketingsPeriodName" : marketingsPeriodName,
                "marketingsStatus" : marketingsStatus,
                "fileStatusFlag":fileStatusFlag
                //"marketingsStatusName" : marketingsStatusName
            }, function (data) {
                if(data.dataCount == '2'){
                    fileStatusFlag = '2';
                    $.alertMessage.info("报表文件生成中，请稍候点击下方列表中的查询按钮查看生成结果!");
                    $List_Grid.fnDraw();
                    $('#main-content').postUrl(Main.contextPath + "/BR121407/createExcel", {
                        "fileStartTime" : fileStartTime,
                        "fileEndTime" : fileEndTime,
                        "buyerType" : buyerType,
                        "lgcsAreaCode" : lgcsAreaCode,
                        "cityCode" : cityCode,
                        "classesCode" : classesCode,
                        "machiningCodeU" : machiningCodeU,
                        "marketingsStatusCla" : marketingsStatusCla,
                        "marketingsPeriodName" : marketingsPeriodName,
                        "marketingsStatus" : marketingsStatus,
                        "fileStatusFlag":fileStatusFlag,
                        "fileId":data.fileId
                    }, function (data) {

                    }, {refreshHtml: false});
                }else if(data.dataCount == '0'){
                    $.alertMessage.info("根据条件未查询到数据,无法生成文件！");
                    return false;
                }else if(data.dataCount == '3'){
                    fileStatusFlag = '0'
                    $.alertMessage.confirm("报表已存在,是否生成最新报表!", function () {
                        $('#main-content').postUrl(Main.contextPath + "/BR121407/generateBuyerPoolFileInfo", {
                            "fileStartTime" : fileStartTime,
                            "fileEndTime" : fileEndTime,
                            "buyerType" : buyerType,
                            "lgcsAreaCode" : lgcsAreaCode,
                            "cityCode" : cityCode,
                            "classesCode" : classesCode,
                            "machiningCodeU" : machiningCodeU,
                            "marketingsStatusCla" : marketingsStatusCla,
                            "marketingsPeriodName" : marketingsPeriodName,
                            "marketingsStatus" : marketingsStatus,
                            "fileStatusFlag":fileStatusFlag,
                            "flag" : true
                        }, function (datas) {
                            $.alertMessage.info("报表文件生成中，请稍候点击下方列表中的查询按钮查看生成结果!");
                            $List_Grid.fnDraw();
                            fileStatusFlag = '2';
                            $('#main-content').postUrl(Main.contextPath + "/BR121407/createExcel", {
                                "fileStartTime" : fileStartTime,
                                "fileEndTime" : fileEndTime,
                                "buyerType" : buyerType,
                                "lgcsAreaCode" : lgcsAreaCode,
                                "cityCode" : cityCode,
                                "classesCode" : classesCode,
                                "machiningCodeU" : machiningCodeU,
                                "marketingsStatusCla" : marketingsStatusCla,
                                "marketingsPeriodName" : marketingsPeriodName,
                                "marketingsStatus" : marketingsStatus,
                                "fileStatusFlag":fileStatusFlag,
                                "fileId":datas.fileId
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
            $('#main-content').postUrl(Main.contextPath + "/BR121407/classesChange/" + classesCode, null,
                function (data) {
                    $("#machiningCodeU").html("");
                    $("#machiningCodeU").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#machiningCodeU").append("<option value='" + item.machiningCodeU + "'>" + item.machiningNameU + "</option>");
                    });

                }, {refreshHtml: false});
        });
        //上线状态一级分类变更
        $("#marketingsStatusCla").change(function(){
            var marketingsStatusCla = $("#marketingsStatusCla option:selected").val();
            if(marketingsStatusCla == ""){
                $("#marketingsStatus").html("");
                $("#marketingsStatus").append("<option value=''>--请选择--</option>");
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/BR121407/marketingsStatusClaChange/" + marketingsStatusCla,null,function(data){
                $("#marketingsStatus").html("");
                $("#marketingsStatus").append("<option value=''>--请选择--</option>");
                $.each(data,function(key,value){
                    $("#marketingsStatus").append("<option value='"+key+"'>"+value+"</option>");
                });

            },{refreshHtml: false});

        });

    }
}
$(document).ready(function () {
    // 初始化调用
    BR121407.init();
});

