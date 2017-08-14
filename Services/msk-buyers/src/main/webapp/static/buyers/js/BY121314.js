/**
 * 批发市场新增
 * Created by zhou_yajun on 2016/7/8.
 */


var BY121314 = {
    BY121314_Grid : null,
    saveButton : "BY121314_SAVE",
    addButton : "BY121314_ADD",
    initDataGrid : function () {
        BY121314.BY121314_Grid = $("#BY121314_Grid").grid({
            actionHandler :BY121314.actionHandler
        });
        this.addHandler();
        this.saveHandler();
        this.changeHandler();
    },
    actionHandler: function(rowdata, coltype, row, col){
        if(coltype == "edit"){
            $.pdialog.open("买家配送信息编辑", Main.contextPath + "/BY1213141/init/" + rowdata.buyerId + "/" + rowdata.id, {
                    width: 400,
                    height: 500
                }
            )
        }
        if(coltype == "delete"){
            $.alertMessage.confirm("请确认要删除配送信息吗？",function(){
                $('#main-content').postUrl(Main.contextPath + "/BY1213141/delete/" + rowdata.id,null,function(data){
                    $.alertMessage.close();
                    BY121314.BY121314_Grid.fnDraw();
                },{refreshHtml:false})
            });
        }
    },
    //买家配送信息编辑
    addHandler: function(){
        $("#" + BY121314.addButton).click(function(){
            var buyerId = $("#buyerId").val();
            var id = "0";
            $.pdialog.open("买家配送信息编辑", Main.contextPath + "/BY1213141/init/" + buyerId + "/" + id, {
                    width: 400,
                    height: 500
                }
            )
        });
    },
    //批发市场基本信息保存
    saveHandler: function(){
        $("#" + BY121314.saveButton).click(function(){
            var habitRecVal = $("#habitRecTime option:selected").val();
            var habitRec = $("#habitRecTime option:selected").text();
            var earliestRecTime = $("#earliestRecTime").val();
            var latestRecTime = $("#latestRecTime").val();
            var formData = getFormData($("#BY121314SaveForm"));
            if(earliestRecTime>latestRecTime && earliestRecTime != "24:00"){
                $.alertMessage.info("最早收货时间不能大于最晚收货时间！");
                return false;
            }else if(latestRecTime == "24:00" && earliestRecTime<latestRecTime && habitRecVal=="13"){
                $.alertMessage.info("请仔细确认习惯收货时间段及对应的最早收货时间和最晚收货时间！");
                return false;
            }
            $('#main-content').postUrl($("#BY121314SaveForm").attr("action"), formData, function(){
                $.alertMessage.info("买家配送信息保存成功。",function(){
                    $.alertMessage.close();
                })
            },{refreshHtml:false});
        })
    },
    changeHandler: function(){
        $("#habitRecTime").change(function(){
            var habitRec = $("#habitRecTime option:selected").val();
            $("#earliestRecTime").html("");
            $("#latestRecTime").html("");
            if(habitRec == ""){
                $("#earliestRecTime").append("<option value=''>--请选择--</option>");
                $("#latestRecTime").append("<option value=''>--请选择--</option>");
                return false;
            }
            $('#main-content').postUrl(Main.contextPath + "/BY121314/recTimeChange/" + habitRec, null,
                function (data) {
                    $("#earliestRecTime").append("<option value=''>--请选择--</option>");
                    $("#latestRecTime").append("<option value=''>--请选择--</option>");
                    $.each(data.early, function (i, item) {
                        $("#earliestRecTime").append("<option value='" + item + "'>" + item + "</option>");
                    });
                    $.each(data.late,function (i,item){
                        $("#latestRecTime").append("<option value='" + item + "'>" + item + "</option>");
                    });

                }, {refreshHtml: false});

        });
    }
}

$(document).ready(function(){
    BY121314.initDataGrid();
})