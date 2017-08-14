var BR121405 = {
    List_Grid: null,
    formId: "BR121405Form",
    fileStartTime: "#fileStartTime",
    fileEndTime: "#fileEndTime",
    fileCreateButton: "#BR121405_CREATE",
    init: function () {
        BR121405.List_Grid = $('#BR121405_list_grid').grid({
            actionHandler: BR121405.actionHandler,
            fnRowCallback: BR121405.fnRowHandler,
        });
        this.bindDatePicber(BR121405.fileStartTime);
        this.bindDatePicber(BR121405.fileEndTime);
        this.bindSelectChange();
        this.createFile();
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
    fnRowHandler : function(tr,rowdata){
        var $td = $(tr).children('td').eq(7);
        var fileServerIp = $("#fileServerIp").val();
        if(rowdata.fileStatus == '2'){
            $td.html();
        }else{
            $td.html(downloadExcel(rowdata,fileServerIp));
        }
    },
    //绑定下载按钮
    actionHandler: function (rowdata, coltype, row, col) {
        if(rowdata.fileStatus == "未生成"){
            $.alertMessage.info("文件还未生成!",function(){
                $.alertMessage.close();
                return false;
            })
        }
        if(coltype == "delete"){
            $.alertMessage.confirm("确定要删除文件吗？",function(){
                $.alertMessage.close();
                $('#main-content').postUrl(Main.contextPath + "/BR121405/deleteExcel/"+rowdata.fileId,null,function(data){
                    if(data.status == 'S'){
                        $.alertMessage.info("删除成功");
                        BR121405.List_Grid.fnDraw();
                    }else{
                        $.alertMessage.info("删除失败");
                        return false;
                    }
                },{refreshHtml:false});
            })
        }
    },
    bindSelectChange: function () {
        // 物流区变更
        $("#lgcsAreaCode").change(function () {
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            if (lgcsAreaCode == "") {
                $("#cityCode").html("");
                $("#cityCode").append("<option value=''>--请选择--</option>");
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/BR121405/lgcsAreaChange/" + lgcsAreaCode, null,
                function (data) {
                    $("#cityCode").html("");
                    $("#cityCode").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#cityCode").append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>");
                    });
                }, {refreshHtml: false});
        });
        //城市变更时,如果批发市场等级已选择,则查询批发市场信息
        $("#cityCode").change(function(){
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            var marketLevel = $("#marketLevel option:selected").val();
            var cityCode = $("#cityCode option:selected").val();
            if(marketLevel == ""){
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/BR121405/findMarketInfo/",{
                "marketLevel":marketLevel,
                "lgcsAreaCode":lgcsAreaCode,
                "cityCode":cityCode
            },function(data){
                $("#marketId").html("");
                $("#marketId").append("<option value=''>--请选择--</option>");

                $.each(data,function(i,item){
                    $("#marketId").append("<option value='"+item.terMarketId+"'>"+item.marketName+"</option>");
                })
            },{refreshHtml: false})
        });

        //产品一级分类变更
        $("#classesCode").change(function () {
            var classesCode = $("#classesCode option:selected").val();
            if (classesCode == "") {
                $("#machiningCode").html("");
                $("#machiningCode").append("<option value=''>--请选择--</option>");
                return ;
            }
            $('#main-content').postUrl(Main.contextPath + "/BR121405/classesChange/" + classesCode, null,
                function (data) {
                    $("#machiningCode").html("");
                    $("#machiningCode").append("<option value=''>--请选择--</option>");
                    $.each(data, function (i, item) {
                        $("#machiningCode").append("<option value='" + item.machiningCodeU + "'>" + item.machiningNameU + "</option>");
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
            $('#main-content').postUrl(Main.contextPath + "/BR121405/marketingsStatusClaChange/" + marketingsStatusCla,null,function(data){
                $("#marketingsStatus").html("");
                $("#marketingsStatus").append("<option value=''>--请选择--</option>");
                $.each(data,function(key,value){
                    $("#marketingsStatus").append("<option value='"+key+"'>"+value+"</option>");
                });

            },{refreshHtml: false});

        });
        //市场等级变更
        $("#marketLevel").change(function(){
            var marketLevel = $("#marketLevel option:selected").val();
            if(marketLevel == ""){
                $("#marketId").html("");
                $("#marketId").append("<option value=''>--请选择--</option>");
                return false;
            }
            var lgcsAreaCode = $("#lgcsAreaCode option:selected").val();
            var cityCode = $("#cityCode option:selected").val();
            if(lgcsAreaCode == ""){
                $.alertMessage.info("请选择物流区!",function(){
                    $.alertMessage.close();
                });
                return false;
            }
            if(cityCode == ""){
                $.alertMessage.info("请选择城市!",function(){
                    $.alertMessage.close();
                });
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/BR121405/findMarketInfo/",{
                "marketLevel":marketLevel,
                "lgcsAreaCode":lgcsAreaCode,
                "cityCode":cityCode
            },function(data){
                $("#marketId").html("");
                $("#marketId").append("<option value=''>--请选择--</option>");

                $.each(data,function(i,item){
                    $("#marketId").append("<option value='"+item.terMarketId+"'>"+item.marketName+"</option>");
                })
            },{refreshHtml: false})
        })
    },
    createFile: function(rowdata){
        $(BR121405.fileCreateButton).click(function(){
            var marketLevel = $("#marketLevel").val();
            var marketId = $("#marketId").val();
            if($("#fileStartTime").val() == ""){
                $.alertMessage.info("请选择报表时间",function(){
                    $.alertMessage.close();
                });
                return false;
            }
            if($("#lgcsAreaCode option:selected").val() == ""){
                $.alertMessage.info("请选择物流区",function(){
                    $.alertMessage.close();
                });
                return false;
            }
            if($("#cityCode option:selected").val() == ""){
                $.alertMessage.info("请选择城市",function(){
                    $.alertMessage.close();
                });
                return false;
            }
            if($("#classesCode option:selected").val() == ""){
                $.alertMessage.info("请选择产品一级分类",function(){
                    $.alertMessage.close();
                });
                return false;
            }
            if($("#machiningCode option:selected").val() == ""){
                $.alertMessage.info("请选择产品二级分类",function(){
                    $.alertMessage.close();
                });
                return false;
            }
            if(marketLevel != "" && marketId == ""){
                $.alertMessage.info("请选择单个批发市场",function(){
                    $.alertMessage.close();
                });
                return false;
            }
            var marketingsPeriodName = $("#marketingsStatusCla option:selected").text();
            $("#lgcsAreaName").val($("#lgcsAreaCode option:selected").text());
            $("#cityName").val($("#cityCode option:selected").text());
            $("#classesName").val($("#classesCode option:selected").text());
            $("#machiningName").val($("#machiningCode option:selected").text());
            if(marketingsPeriodName == '--请选择--'){
                $("#marketingsPeriodName").val('');
            }else{
                $("#marketingsPeriodName").val($("#marketingsStatusCla option:selected").text());
            }
            if($("#marketId option:selected").val() != ''){
                $("#marketName").val($("#marketId option:selected").text());
            }
            if($("#marketId option:selected").val() == ''){
                $("#marketName").val('');
            }
            var formData = getFormData($("#createFileForm"));
            var flag = false;
            //$('#main-content').postUrl($("#createFileForm").attr("action"),formData,function(data){
            $('#main-content').postUrl(Main.contextPath + "/BR121405/generateBuyerPoolFileInfo/"+flag,formData,function(data){
                if(data.status == 'S'){
                   var  fileId = data.result.filterMap.fileId;
                    if(data.result.filterMap.count == '1'){
                        $.alertMessage.info("报表文件生成中，请稍候点击下方列表中的查询按钮查看生成结果!");
                        BR121405.List_Grid.fnDraw();
                        $('#main-content').postUrl(Main.contextPath + "/BR121405/createExcel/"+flag+"/"+fileId,formData,function(data){

                        },{refreshHtml:false})

                    }else if (data.result.filterMap.count == '0'){
                        $.alertMessage.info("根据条件未查询到数据,无法生成文件！");
                    }else if(data.result.filterMap.count == '2'){
                        $.alertMessage.confirm("报表已存在,是否生成最新报表!", function () {
                            flag = true;
                            $('#main-content').postUrl(Main.contextPath + "/BR121405/generateBuyerPoolFileInfo/"+flag,formData,function(datas){
                                $.alertMessage.info("报表文件生成中，请稍候点击下方列表中的查询按钮查看生成结果!");
                                BR121405.List_Grid.fnDraw();
                                $('#main-content').postUrl(Main.contextPath + "/BR121405/createExcel/"+flag+"/"+datas.result.filterMap.fileId,formData,function(){
                                },{refreshHtml:false})
                            },{refreshHtml:false})

                        })
                    }
                }else{
                    $.alertMessage.info(data.message,function(){
                        $.alertMessage.close();
                    })
                    BR121405.List_Grid.fnDraw();
                }
            },{refreshHtml:false})
        });
    }
}
$(document).ready(function () {
    // 初始化调用
    BR121405.init();
});