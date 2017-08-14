var $List_Grid;
var BY121319 = {
    init: function () {
        $List_Grid = $('#BY121319_list_grid').grid({
            actionHandler: BY121319.actionHandler
        });
        this.bindAddBtn();
    },
    actionHandler: function (rowdata, coltype, row, col) {
    },

    bindAddBtn: function () {
        $("#BY121319_ADD").click(function () {
            var buyerId= $("#buyerId").val();
            var telMarketingStartTime = $("#telMarketingStartTime option:selected").val();
            var telMarketingEndTime = $("#telMarketingEndTime option:selected").val();
            if (telMarketingStartTime > telMarketingEndTime ) {
                $.alertMessage.info("开始时间不能大于结束时间!");
                return false;
            }
            if(telMarketingStartTime == telMarketingEndTime){
                if(telMarketingStartTime != ""){
                    $.alertMessage.info("开始时间不能等于结束时间!");
                    return false;
                }
            }
            if(telMarketingStartTime == "" && telMarketingEndTime != ""){
                $.alertMessage.info("开始时间没有选择,结束时间选择,请修改!");
                return false;
            }
            if(telMarketingStartTime != "" && telMarketingEndTime == ""){
                $.alertMessage.info("结束时间没有选择,开始时间选择,请修改!");
                return false;
            }
            var obj=document.getElementsByName("marketingTools");
            var tools='';
            for(var i=0; i<obj.length; i++){
                if(obj[i].checked) tools+=obj[i].value+',';
            }
            $('#main-content').postUrl(Main.contextPath + "/BY121319/save/", {
                marketingTools:tools,
                buyerId:buyerId,
                telMarketingStartTime: telMarketingStartTime,
                telMarketingEndTime: telMarketingEndTime
            }, function (data) {
                    if (data == "保存成功") {
                        $.alertMessage.info("保存成功 !");
                    }else{
                        $.alertMessage.warn("保存失败 !");
                    }
                }, {refreshHtml: false}
            );

        })

    }

}

$(document).ready(function () {
    // 初始化调用
    BY121319.init();
});

